package io.github.ibuildthecloud.dstack.lock.impl;

import io.github.ibuildthecloud.dstack.async.utils.AsyncUtils;
import io.github.ibuildthecloud.dstack.lock.Lock;
import io.github.ibuildthecloud.dstack.lock.LockDelegator;
import io.github.ibuildthecloud.dstack.lock.LockManager;
import io.github.ibuildthecloud.dstack.lock.definition.LockDefinition;
import io.github.ibuildthecloud.dstack.lock.definition.MultiLockDefinition;
import io.github.ibuildthecloud.dstack.lock.provider.LockProvider;
import io.github.ibuildthecloud.dstack.util.type.InitializationTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;

import org.apache.cloudstack.managed.context.NoExceptionRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.SettableFuture;

public class LockDelegatorImpl implements LockDelegator, InitializationTask {

    private static final Logger log = LoggerFactory.getLogger(LockDelegatorImpl.class);

    LockManager lockManager;
    Map<String,Lock> holding = new HashMap<String,Lock>();
    BlockingQueue<LockOp> ops = new LinkedBlockingQueue<LockOp>();
    ExecutorService executorService;
    boolean shutdown = false;

    @Override
    public boolean tryLock(LockDefinition lockDef) {
        return doOp(lockDef, true);
    }

    @Override
    public boolean unlock(LockDefinition lockDef) {
        return doOp(lockDef, false);
    }

    protected boolean doOp(LockDefinition lockDef, boolean lock) {
        SettableFuture<Boolean> future = SettableFuture.create();
        ops.add(new LockOp(lockDef, lock, future));

        return AsyncUtils.get(future);
    }

    @Override
    public void start() {
        executorService.execute(new NoExceptionRunnable() {
            @Override
            protected void doRun() throws Exception {
                runLoop();
            }
        });
    }

    @Override
    public void stop() {
        shutdown = true;
    }

    protected void runLoop() {
        while ( true ) {
            LockOp op = null;
            while ( true ) {
                try {
                    op = ops.take();
                    try {
                        if ( op.lock ) {
                            lock(op);
                        } else {
                            unlock(op);
                        }
                    } catch ( Throwable t ) {
                        op.future.set(false);
                        log.error("Exception in lock delegator, lockdef [{}]", op.lock, t);
                    }
                } catch (Throwable t ) {
                    if ( shutdown ) {
                        return;
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        log.info("Interrupted", e);
                    }
                }

                if ( shutdown ) {
                    return;
                }
            }
        }
    }

    protected void lock(LockOp op) {
        if ( op.def instanceof MultiLockDefinition ) {
            log.error("Can not lock a multilock with a lock delegator");
            op.future.set(false);
            return;
        }

        if ( holding.containsKey(op.def.getLockId()) ) {
            op.future.set(true);
            return;
        }

        LockProvider lockProvider = lockManager.getLockProvider();
        boolean success = false;
        Lock lock = lockProvider.getLock(op.def);
        try {
            success = lock.tryLock();
            if ( success ) {
                log.trace("Acquired lock [{}]", op.def.getLockId());
            }
        } finally {
            if ( success ) {
                holding.put(op.def.getLockId(), lock);
                op.future.set(true);
            } else {
                op.future.set(false);
                lockProvider.releaseLock(lock);
            }
        }
    }

    protected void unlock(LockOp op) {
        Lock lock = holding.get(op.def.getLockId());
        if ( lock != null ) {
            LockProvider lockProvider = lockManager.getLockProvider();
            lock.unlock();
            log.trace("Released lock [{}]", op.def.getLockId());
            holding.remove(op.def.getLockId());
            lockProvider.releaseLock(lock);
        }
        op.future.set(true);
    }

    public LockManager getLockManager() {
        return lockManager;
    }

    @Inject
    public void setLockManager(LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Inject
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    private static final class LockOp {
        LockDefinition def;
        boolean lock;
        SettableFuture<Boolean> future;

        public LockOp(LockDefinition def, boolean lock, SettableFuture<Boolean> future) {
            super();
            this.def = def;
            this.lock = lock;
            this.future = future;
        }
    }
}