/**
 * This class is generated by jOOQ
 */
package io.github.ibuildthecloud.dstack.core.model;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "instance", schema = "dstack")
public interface Instance extends java.io.Serializable {

	/**
	 * Setter for <code>dstack.instance.id</code>.
	 */
	public void setId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.id</code>.
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId();

	/**
	 * Setter for <code>dstack.instance.name</code>.
	 */
	public void setName(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.name</code>.
	 */
	@javax.persistence.Column(name = "name", length = 255)
	public java.lang.String getName();

	/**
	 * Setter for <code>dstack.instance.kind</code>.
	 */
	public void setKind(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.kind</code>.
	 */
	@javax.persistence.Column(name = "kind", nullable = false, length = 255)
	public java.lang.String getKind();

	/**
	 * Setter for <code>dstack.instance.account_id</code>.
	 */
	public void setAccountId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.account_id</code>.
	 */
	@javax.persistence.Column(name = "account_id", nullable = false, precision = 19)
	public java.lang.Long getAccountId();

	/**
	 * Setter for <code>dstack.instance.uuid</code>.
	 */
	public void setUuid(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.uuid</code>.
	 */
	@javax.persistence.Column(name = "uuid", nullable = false, length = 128)
	public java.lang.String getUuid();

	/**
	 * Setter for <code>dstack.instance.description</code>.
	 */
	public void setDescription(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.description</code>.
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	public java.lang.String getDescription();

	/**
	 * Setter for <code>dstack.instance.requested_state</code>.
	 */
	public void setRequestedState(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.requested_state</code>.
	 */
	@javax.persistence.Column(name = "requested_state", length = 255)
	public java.lang.String getRequestedState();

	/**
	 * Setter for <code>dstack.instance.state</code>.
	 */
	public void setState(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.state</code>.
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 255)
	public java.lang.String getState();

	/**
	 * Setter for <code>dstack.instance.allocation_state</code>.
	 */
	public void setAllocationState(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.allocation_state</code>.
	 */
	@javax.persistence.Column(name = "allocation_state", nullable = false, length = 255)
	public java.lang.String getAllocationState();

	/**
	 * Setter for <code>dstack.instance.post_compute_state</code>.
	 */
	public void setPostComputeState(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.post_compute_state</code>.
	 */
	@javax.persistence.Column(name = "post_compute_state", length = 255)
	public java.lang.String getPostComputeState();

	/**
	 * Setter for <code>dstack.instance.image_id</code>.
	 */
	public void setImageId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.image_id</code>.
	 */
	@javax.persistence.Column(name = "image_id", precision = 19)
	public java.lang.Long getImageId();

	/**
	 * Setter for <code>dstack.instance.offering_id</code>.
	 */
	public void setOfferingId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.offering_id</code>.
	 */
	@javax.persistence.Column(name = "offering_id", precision = 19)
	public java.lang.Long getOfferingId();

	/**
	 * Setter for <code>dstack.instance.requested_offering_id</code>.
	 */
	public void setRequestedOfferingId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.requested_offering_id</code>.
	 */
	@javax.persistence.Column(name = "requested_offering_id", precision = 19)
	public java.lang.Long getRequestedOfferingId();

	/**
	 * Setter for <code>dstack.instance.on_crash</code>.
	 */
	public void setOnCrash(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.on_crash</code>.
	 */
	@javax.persistence.Column(name = "on_crash", nullable = false, length = 255)
	public java.lang.String getOnCrash();

	/**
	 * Setter for <code>dstack.instance.hostname</code>.
	 */
	public void setHostname(java.lang.String value);

	/**
	 * Getter for <code>dstack.instance.hostname</code>.
	 */
	@javax.persistence.Column(name = "hostname", length = 255)
	public java.lang.String getHostname();

	/**
	 * Setter for <code>dstack.instance.created</code>.
	 */
	public void setCreated(java.util.Date value);

	/**
	 * Getter for <code>dstack.instance.created</code>.
	 */
	@javax.persistence.Column(name = "created")
	public java.util.Date getCreated();

	/**
	 * Setter for <code>dstack.instance.removed</code>.
	 */
	public void setRemoved(java.util.Date value);

	/**
	 * Getter for <code>dstack.instance.removed</code>.
	 */
	@javax.persistence.Column(name = "removed")
	public java.util.Date getRemoved();

	/**
	 * Setter for <code>dstack.instance.remove_time</code>.
	 */
	public void setRemoveTime(java.util.Date value);

	/**
	 * Getter for <code>dstack.instance.remove_time</code>.
	 */
	@javax.persistence.Column(name = "remove_time")
	public java.util.Date getRemoveTime();

	/**
	 * Setter for <code>dstack.instance.data</code>.
	 */
	public void setData(java.util.Map<String,Object> value);

	/**
	 * Getter for <code>dstack.instance.data</code>.
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	public java.util.Map<String,Object> getData();

	/**
	 * Setter for <code>dstack.instance.zone_id</code>.
	 */
	public void setZoneId(java.lang.Long value);

	/**
	 * Getter for <code>dstack.instance.zone_id</code>.
	 */
	@javax.persistence.Column(name = "zone_id", nullable = false, precision = 19)
	public java.lang.Long getZoneId();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface Instance
	 */
	public void from(io.github.ibuildthecloud.dstack.core.model.Instance from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface Instance
	 */
	public <E extends io.github.ibuildthecloud.dstack.core.model.Instance> E into(E into);
}