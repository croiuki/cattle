package io.cattle.platform.servicediscovery.deployment.impl;

import static io.cattle.platform.core.model.tables.LoadBalancerTable.LOAD_BALANCER;
import io.cattle.iaas.healthcheck.service.HealthcheckService;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.LoadBalancerConstants;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.LoadBalancer;
import io.cattle.platform.core.model.LoadBalancerHostMap;
import io.cattle.platform.core.model.Service;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.object.resource.ResourcePredicate;
import io.cattle.platform.servicediscovery.deployment.DeploymentUnitInstance;
import io.cattle.platform.servicediscovery.deployment.InstanceUnit;
import io.cattle.platform.servicediscovery.deployment.impl.DeploymentManagerImpl.DeploymentServiceContext;

import java.util.Map;

public class LoadBalancerDeploymentUnitInstance extends DeploymentUnitInstance implements InstanceUnit {
    LoadBalancerHostMap hostMap;
    protected Instance instance;

    public LoadBalancerDeploymentUnitInstance() {
        super(null, null, null);
    }

    public LoadBalancerDeploymentUnitInstance(String uuid, Service service,
            LoadBalancerHostMap hostMap, DeploymentServiceContext context, Map<String, String> labels) {
        super(context, uuid, service);
        this.hostMap = hostMap;
        if (hostMap != null) {
            Instance instance = context.lbInstanceMgr.getLoadBalancerInstance(this.hostMap);
            if (instance != null) {
                this.instance = instance;
                this.exposeMap = context.exposeMapDao.findInstanceExposeMap(this.instance);
            }
        }
    }

    @Override
    public boolean isError() {
        if (this.hostMap.getState().equals(CommonStatesConstants.ACTIVE)) {
            if (this.instance == null || this.instance.getRemoved() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove() {
        // 1) remove host map
        context.objectProcessManager.scheduleProcessInstanceAsync(
                LoadBalancerConstants.PROCESS_LB_HOST_MAP_REMOVE,
                context.objectManager.reload(hostMap), null);
        // 2) remove the mapping
        if (this.exposeMap != null) {
            context.objectProcessManager.scheduleStandardProcessAsync(StandardProcess.REMOVE, exposeMap, null);
        }
    }

    @Override
    public DeploymentUnitInstance start(Map<String, Object> deployParams) {
        if (createNew()) {
            LoadBalancer lb = context.objectManager.findAny(LoadBalancer.class, LOAD_BALANCER.SERVICE_ID,
                    service.getId(),
                    LOAD_BALANCER.REMOVED, null);

            Map<String, Object> launchConfig = context.sdService.buildServiceInstanceLaunchData(service,
                    deployParams);
            this.hostMap = context.lbService.addHostWLaunchConfigToLoadBalancer(lb, launchConfig);
            this.instance = context.lbInstanceMgr.getLoadBalancerInstance(this.hostMap);
            this.exposeMap = context.exposeMapDao.findInstanceExposeMap(this.instance);
        } else if (this.instance != null) {
            if (InstanceConstants.STATE_STOPPED.equals(instance.getState())) {
                context.objectProcessManager.scheduleProcessInstanceAsync(
                        InstanceConstants.PROCESS_START, instance, null);
            }
        }
        return this;
    }

    @Override
    public boolean createNew() {
        return this.hostMap == null;
    }

    @Override
    public DeploymentUnitInstance waitForStart() {
        this.hostMap = context.resourceMonitor.waitFor(this.hostMap, new ResourcePredicate<LoadBalancerHostMap>() {
            @Override
            public boolean evaluate(LoadBalancerHostMap obj) {
                return obj != null && CommonStatesConstants.ACTIVE.equals(obj.getState());
            }
        });
        this.instance = context.lbInstanceMgr.getLoadBalancerInstance(this.hostMap);
        return this;
    }
    
    @Override
    public void stop() {
        if (this.instance != null && instance.getState().equals(InstanceConstants.STATE_RUNNING)) {
            context.objectProcessManager.scheduleProcessInstanceAsync(InstanceConstants.PROCESS_STOP,
                    instance, null);
        }
    }

    @Override
    public boolean isStarted() {
        boolean mapActive = this.hostMap.getState().equalsIgnoreCase(CommonStatesConstants.ACTIVE);
        boolean instanceRunning = this.instance != null
                && this.instance.getState().equalsIgnoreCase(InstanceConstants.STATE_RUNNING);

        return mapActive && instanceRunning;
    }

    @Override
    public Instance getInstance() {
        return instance;
    }

    @Override
    public boolean isUnhealthy() {
        if (this.instance != null) {
            return this.instance.getHealthState() != null && (this.instance.getHealthState().equalsIgnoreCase(
                    HealthcheckService.HEALTH_STATE_UNHEALTHY) || this.instance.getHealthState().equalsIgnoreCase(
                    HealthcheckService.HEALTH_STATE_UPDATING_UNHEALTHY));
        }
        return false;
    }
}