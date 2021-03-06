/**
 * This class is generated by jOOQ
 */
package io.cattle.platform.core.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NetworkServiceProviderInstanceMapTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord> {

	private static final long serialVersionUID = 1712732525;

	/**
	 * The singleton instance of <code>cattle.network_service_provider_instance_map</code>
	 */
	public static final io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable NETWORK_SERVICE_PROVIDER_INSTANCE_MAP = new io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord.class;
	}

	/**
	 * The column <code>cattle.network_service_provider_instance_map.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.network_service_provider_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.Long> NETWORK_SERVICE_PROVIDER_ID = createField("network_service_provider_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.network_service_provider_instance_map.instance_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.Long> INSTANCE_ID = createField("instance_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>cattle.network_service_provider_instance_map</code> table reference
	 */
	public NetworkServiceProviderInstanceMapTable() {
		this("network_service_provider_instance_map", null);
	}

	/**
	 * Create an aliased <code>cattle.network_service_provider_instance_map</code> table reference
	 */
	public NetworkServiceProviderInstanceMapTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable.NETWORK_SERVICE_PROVIDER_INSTANCE_MAP);
	}

	private NetworkServiceProviderInstanceMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord> aliased) {
		this(alias, aliased, null);
	}

	private NetworkServiceProviderInstanceMapTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord>>asList(io.cattle.platform.core.model.Keys.KEY_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP_PRIMARY, io.cattle.platform.core.model.Keys.KEY_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP_IDX_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.NetworkServiceProviderInstanceMapRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_NSPIM_NETWORK_SERVICE_PROVIDER_ID, io.cattle.platform.core.model.Keys.FK_NETWORK_SERVICE_PROVIDER_INSTANCE_MAP__INSTANCE_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.NetworkServiceProviderInstanceMapTable(name, null);
	}
}
