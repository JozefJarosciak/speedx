package com.j256.ormlite.dao;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.StatementBuilder$StatementType;
import com.j256.ormlite.stmt.StatementExecutor;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.ObjectFactory;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

public abstract class BaseDaoImpl<T, ID> implements Dao<T, ID> {
    private static final ThreadLocal<List<BaseDaoImpl<?, ?>>> daoConfigLevelLocal = new C41011();
    private static ReferenceObjectCache defaultObjectCache;
    protected ConnectionSource connectionSource;
    protected final Class<T> dataClass;
    protected DatabaseType databaseType;
    private boolean initialized;
    protected CloseableIterator<T> lastIterator;
    private ObjectCache objectCache;
    protected ObjectFactory<T> objectFactory;
    protected StatementExecutor<T, ID> statementExecutor;
    protected DatabaseTableConfig<T> tableConfig;
    protected TableInfo<T, ID> tableInfo;

    /* renamed from: com.j256.ormlite.dao.BaseDaoImpl$1 */
    static class C41011 extends ThreadLocal<List<BaseDaoImpl<?, ?>>> {
        C41011() {
        }

        protected List<BaseDaoImpl<?, ?>> initialValue() {
            return new ArrayList(10);
        }
    }

    /* renamed from: com.j256.ormlite.dao.BaseDaoImpl$2 */
    class C41022 implements CloseableIterable<T> {
        C41022() {
        }

        public Iterator<T> iterator() {
            return closeableIterator();
        }

        public CloseableIterator<T> closeableIterator() {
            try {
                return BaseDaoImpl.this.createIterator(-1);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not build iterator for " + BaseDaoImpl.this.dataClass, e);
            }
        }
    }

    protected BaseDaoImpl(Class<T> cls) throws SQLException {
        this(null, cls, null);
    }

    protected BaseDaoImpl(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        this(connectionSource, cls, null);
    }

    protected BaseDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        this(connectionSource, databaseTableConfig.getDataClass(), databaseTableConfig);
    }

    private BaseDaoImpl(ConnectionSource connectionSource, Class<T> cls, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        this.dataClass = cls;
        this.tableConfig = databaseTableConfig;
        if (connectionSource != null) {
            this.connectionSource = connectionSource;
            initialize();
        }
    }

    public void initialize() throws SQLException {
        if (!this.initialized) {
            if (this.connectionSource == null) {
                throw new IllegalStateException("connectionSource was never set on " + getClass().getSimpleName());
            }
            this.databaseType = this.connectionSource.getDatabaseType();
            if (this.databaseType == null) {
                throw new IllegalStateException("connectionSource is getting a null DatabaseType in " + getClass().getSimpleName());
            }
            if (this.tableConfig == null) {
                this.tableInfo = new TableInfo(this.connectionSource, this, this.dataClass);
            } else {
                this.tableConfig.extractFieldTypes(this.connectionSource);
                this.tableInfo = new TableInfo(this.databaseType, this, this.tableConfig);
            }
            this.statementExecutor = new StatementExecutor(this.databaseType, this.tableInfo, this);
            List list = (List) daoConfigLevelLocal.get();
            list.add(this);
            if (list.size() <= 1) {
                int i = 0;
                while (i < list.size()) {
                    BaseDaoImpl baseDaoImpl;
                    try {
                        baseDaoImpl = (BaseDaoImpl) list.get(i);
                        DaoManager.registerDao(this.connectionSource, baseDaoImpl);
                        for (FieldType configDaoInformation : baseDaoImpl.getTableInfo().getFieldTypes()) {
                            configDaoInformation.configDaoInformation(this.connectionSource, baseDaoImpl.getDataClass());
                        }
                        baseDaoImpl.initialized = true;
                        i++;
                    } catch (SQLException e) {
                        DaoManager.unregisterDao(this.connectionSource, baseDaoImpl);
                        throw e;
                    } catch (Throwable th) {
                        list.clear();
                        daoConfigLevelLocal.remove();
                    }
                }
                list.clear();
                daoConfigLevelLocal.remove();
            }
        }
    }

    public T queryForId(ID id) throws SQLException {
        checkForInitialized();
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            T queryForId = this.statementExecutor.queryForId(readOnlyConnection, id, this.objectCache);
            return queryForId;
        } finally {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public T queryForFirst(PreparedQuery<T> preparedQuery) throws SQLException {
        checkForInitialized();
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            T queryForFirst = this.statementExecutor.queryForFirst(readOnlyConnection, preparedQuery, this.objectCache);
            return queryForFirst;
        } finally {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public List<T> queryForAll() throws SQLException {
        checkForInitialized();
        return this.statementExecutor.queryForAll(this.connectionSource, this.objectCache);
    }

    public List<T> queryForEq(String str, Object obj) throws SQLException {
        return queryBuilder().where().eq(str, obj).query();
    }

    public QueryBuilder<T, ID> queryBuilder() {
        checkForInitialized();
        return new QueryBuilder(this.databaseType, this.tableInfo, this);
    }

    public UpdateBuilder<T, ID> updateBuilder() {
        checkForInitialized();
        return new UpdateBuilder(this.databaseType, this.tableInfo, this);
    }

    public DeleteBuilder<T, ID> deleteBuilder() {
        checkForInitialized();
        return new DeleteBuilder(this.databaseType, this.tableInfo, this);
    }

    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        checkForInitialized();
        return this.statementExecutor.query(this.connectionSource, preparedQuery, this.objectCache);
    }

    public List<T> queryForMatching(T t) throws SQLException {
        return queryForMatching(t, false);
    }

    public List<T> queryForMatchingArgs(T t) throws SQLException {
        return queryForMatching(t, true);
    }

    public List<T> queryForFieldValues(Map<String, Object> map) throws SQLException {
        return queryForFieldValues(map, false);
    }

    public List<T> queryForFieldValuesArgs(Map<String, Object> map) throws SQLException {
        return queryForFieldValues(map, true);
    }

    public T queryForSameId(T t) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return null;
        }
        Object extractId = extractId(t);
        if (extractId != null) {
            return queryForId(extractId);
        }
        return null;
    }

    public int create(T t) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return 0;
        }
        if (t instanceof BaseDaoEnabled) {
            ((BaseDaoEnabled) t).setDao(this);
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int create = this.statementExecutor.create(readWriteConnection, t, this.objectCache);
            return create;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public T createIfNotExists(T t) throws SQLException {
        if (t == null) {
            return null;
        }
        T queryForSameId = queryForSameId(t);
        if (queryForSameId != null) {
            return queryForSameId;
        }
        create(t);
        return t;
    }

    public Dao$CreateOrUpdateStatus createOrUpdate(T t) throws SQLException {
        if (t == null) {
            return new Dao$CreateOrUpdateStatus(false, false, 0);
        }
        Object extractId = extractId(t);
        if (extractId == null || !idExists(extractId)) {
            return new Dao$CreateOrUpdateStatus(true, false, create(t));
        }
        return new Dao$CreateOrUpdateStatus(false, true, update((Object) t));
    }

    public int update(T t) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int update = this.statementExecutor.update(readWriteConnection, t, this.objectCache);
            return update;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int updateId(T t, ID id) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int updateId = this.statementExecutor.updateId(readWriteConnection, t, id, this.objectCache);
            return updateId;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int update(PreparedUpdate<T> preparedUpdate) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int update = this.statementExecutor.update(readWriteConnection, preparedUpdate);
            return update;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int refresh(T t) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return 0;
        }
        if (t instanceof BaseDaoEnabled) {
            ((BaseDaoEnabled) t).setDao(this);
        }
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            int refresh = this.statementExecutor.refresh(readOnlyConnection, t, this.objectCache);
            return refresh;
        } finally {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public int delete(T t) throws SQLException {
        checkForInitialized();
        if (t == null) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int delete = this.statementExecutor.delete(readWriteConnection, t, this.objectCache);
            return delete;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int deleteById(ID id) throws SQLException {
        checkForInitialized();
        if (id == null) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int deleteById = this.statementExecutor.deleteById(readWriteConnection, id, this.objectCache);
            return deleteById;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int delete(Collection<T> collection) throws SQLException {
        checkForInitialized();
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int deleteObjects = this.statementExecutor.deleteObjects(readWriteConnection, collection, this.objectCache);
            return deleteObjects;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int deleteIds(Collection<ID> collection) throws SQLException {
        checkForInitialized();
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int deleteIds = this.statementExecutor.deleteIds(readWriteConnection, collection, this.objectCache);
            return deleteIds;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int delete = this.statementExecutor.delete(readWriteConnection, preparedDelete);
            return delete;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public CloseableIterator<T> iterator() {
        return iterator(-1);
    }

    public CloseableIterator<T> closeableIterator() {
        return iterator(-1);
    }

    public CloseableIterator<T> iterator(int i) {
        checkForInitialized();
        this.lastIterator = createIterator(i);
        return this.lastIterator;
    }

    public CloseableWrappedIterable<T> getWrappedIterable() {
        checkForInitialized();
        return new CloseableWrappedIterableImpl(new C41022());
    }

    public CloseableWrappedIterable<T> getWrappedIterable(final PreparedQuery<T> preparedQuery) {
        checkForInitialized();
        return new CloseableWrappedIterableImpl(new CloseableIterable<T>() {
            public Iterator<T> iterator() {
                return closeableIterator();
            }

            public CloseableIterator<T> closeableIterator() {
                try {
                    return BaseDaoImpl.this.createIterator(preparedQuery, -1);
                } catch (Throwable e) {
                    throw new IllegalStateException("Could not build prepared-query iterator for " + BaseDaoImpl.this.dataClass, e);
                }
            }
        });
    }

    public void closeLastIterator() throws SQLException {
        if (this.lastIterator != null) {
            this.lastIterator.close();
            this.lastIterator = null;
        }
    }

    public CloseableIterator<T> iterator(PreparedQuery<T> preparedQuery) throws SQLException {
        return iterator(preparedQuery, -1);
    }

    public CloseableIterator<T> iterator(PreparedQuery<T> preparedQuery, int i) throws SQLException {
        checkForInitialized();
        this.lastIterator = createIterator(preparedQuery, i);
        return this.lastIterator;
    }

    public GenericRawResults<String[]> queryRaw(String str, String... strArr) throws SQLException {
        checkForInitialized();
        try {
            return this.statementExecutor.queryRaw(this.connectionSource, str, strArr, this.objectCache);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not perform raw query for " + str, e);
        }
    }

    public <GR> GenericRawResults<GR> queryRaw(String str, RawRowMapper<GR> rawRowMapper, String... strArr) throws SQLException {
        checkForInitialized();
        try {
            return this.statementExecutor.queryRaw(this.connectionSource, str, (RawRowMapper) rawRowMapper, strArr, this.objectCache);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not perform raw query for " + str, e);
        }
    }

    public <UO> GenericRawResults<UO> queryRaw(String str, DataType[] dataTypeArr, RawRowObjectMapper<UO> rawRowObjectMapper, String... strArr) throws SQLException {
        checkForInitialized();
        try {
            return this.statementExecutor.queryRaw(this.connectionSource, str, dataTypeArr, rawRowObjectMapper, strArr, this.objectCache);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not perform raw query for " + str, e);
        }
    }

    public GenericRawResults<Object[]> queryRaw(String str, DataType[] dataTypeArr, String... strArr) throws SQLException {
        checkForInitialized();
        try {
            return this.statementExecutor.queryRaw(this.connectionSource, str, dataTypeArr, strArr, this.objectCache);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not perform raw query for " + str, e);
        }
    }

    public long queryRawValue(String str, String... strArr) throws SQLException {
        checkForInitialized();
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            long queryForLong = this.statementExecutor.queryForLong(readOnlyConnection, str, strArr);
            this.connectionSource.releaseConnection(readOnlyConnection);
            return queryForLong;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not perform raw value query for " + str, e);
        } catch (Throwable th) {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public int executeRaw(String str, String... strArr) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int executeRaw = this.statementExecutor.executeRaw(readWriteConnection, str, strArr);
            this.connectionSource.releaseConnection(readWriteConnection);
            return executeRaw;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not run raw execute statement " + str, e);
        } catch (Throwable th) {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int executeRawNoArgs(String str) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int executeRawNoArgs = this.statementExecutor.executeRawNoArgs(readWriteConnection, str);
            this.connectionSource.releaseConnection(readWriteConnection);
            return executeRawNoArgs;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not run raw execute statement " + str, e);
        } catch (Throwable th) {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public int updateRaw(String str, String... strArr) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            int updateRaw = this.statementExecutor.updateRaw(readWriteConnection, str, strArr);
            this.connectionSource.releaseConnection(readWriteConnection);
            return updateRaw;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not run raw update statement " + str, e);
        } catch (Throwable th) {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public <CT> CT callBatchTasks(Callable<CT> callable) throws SQLException {
        checkForInitialized();
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            CT callBatchTasks = this.statementExecutor.callBatchTasks(readWriteConnection, this.connectionSource.saveSpecialConnection(readWriteConnection), callable);
            return callBatchTasks;
        } finally {
            this.connectionSource.clearSpecialConnection(readWriteConnection);
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public String objectToString(T t) {
        checkForInitialized();
        return this.tableInfo.objectToString(t);
    }

    public boolean objectsEqual(T t, T t2) throws SQLException {
        checkForInitialized();
        for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
            if (!fieldType.getDataPersister().dataIsEqual(fieldType.extractJavaFieldValue(t), fieldType.extractJavaFieldValue(t2))) {
                return false;
            }
        }
        return true;
    }

    public ID extractId(T t) throws SQLException {
        checkForInitialized();
        FieldType idField = this.tableInfo.getIdField();
        if (idField != null) {
            return idField.extractJavaFieldValue(t);
        }
        throw new SQLException("Class " + this.dataClass + " does not have an id field");
    }

    public Class<T> getDataClass() {
        return this.dataClass;
    }

    public FieldType findForeignFieldType(Class<?> cls) {
        checkForInitialized();
        for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
            if (fieldType.getType() == cls) {
                return fieldType;
            }
        }
        return null;
    }

    public boolean isUpdatable() {
        return this.tableInfo.isUpdatable();
    }

    public boolean isTableExists() throws SQLException {
        checkForInitialized();
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            boolean isTableExists = readOnlyConnection.isTableExists(this.tableInfo.getTableName());
            return isTableExists;
        } finally {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public long countOf() throws SQLException {
        long queryForCountStar;
        checkForInitialized();
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            queryForCountStar = this.statementExecutor.queryForCountStar(readOnlyConnection);
            return queryForCountStar;
        } finally {
            queryForCountStar = this.connectionSource;
            queryForCountStar.releaseConnection(readOnlyConnection);
        }
    }

    public long countOf(PreparedQuery<T> preparedQuery) throws SQLException {
        checkForInitialized();
        if (preparedQuery.getType() != StatementBuilder$StatementType.SELECT_LONG) {
            throw new IllegalArgumentException("Prepared query is not of type " + StatementBuilder$StatementType.SELECT_LONG + ", did you call QueryBuilder.setCountOf(true)?");
        }
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        long queryForLong;
        try {
            queryForLong = this.statementExecutor.queryForLong(readOnlyConnection, preparedQuery);
            return queryForLong;
        } finally {
            queryForLong = this.connectionSource;
            queryForLong.releaseConnection(readOnlyConnection);
        }
    }

    public void assignEmptyForeignCollection(T t, String str) throws SQLException {
        makeEmptyForeignCollection(t, str);
    }

    public <FT> ForeignCollection<FT> getEmptyForeignCollection(String str) throws SQLException {
        return makeEmptyForeignCollection(null, str);
    }

    public void setObjectCache(boolean z) throws SQLException {
        if (z) {
            if (this.objectCache != null) {
                return;
            }
            if (this.tableInfo.getIdField() == null) {
                throw new SQLException("Class " + this.dataClass + " must have an id field to enable the object cache");
            }
            synchronized (BaseDaoImpl.class) {
                if (defaultObjectCache == null) {
                    defaultObjectCache = ReferenceObjectCache.makeWeakCache();
                }
                this.objectCache = defaultObjectCache;
            }
            this.objectCache.registerClass(this.dataClass);
        } else if (this.objectCache != null) {
            this.objectCache.clear(this.dataClass);
            this.objectCache = null;
        }
    }

    public void setObjectCache(ObjectCache objectCache) throws SQLException {
        if (objectCache != null) {
            if (!(this.objectCache == null || this.objectCache == objectCache)) {
                this.objectCache.clear(this.dataClass);
            }
            if (this.tableInfo.getIdField() == null) {
                throw new SQLException("Class " + this.dataClass + " must have an id field to enable the object cache");
            }
            this.objectCache = objectCache;
            this.objectCache.registerClass(this.dataClass);
        } else if (this.objectCache != null) {
            this.objectCache.clear(this.dataClass);
            this.objectCache = null;
        }
    }

    public ObjectCache getObjectCache() {
        return this.objectCache;
    }

    public void clearObjectCache() {
        if (this.objectCache != null) {
            this.objectCache.clear(this.dataClass);
        }
    }

    public static synchronized void clearAllInternalObjectCaches() {
        synchronized (BaseDaoImpl.class) {
            if (defaultObjectCache != null) {
                defaultObjectCache.clearAll();
                defaultObjectCache = null;
            }
        }
    }

    public T mapSelectStarRow(DatabaseResults databaseResults) throws SQLException {
        return this.statementExecutor.getSelectStarRowMapper().mapRow(databaseResults);
    }

    public GenericRowMapper<T> getSelectStarRowMapper() throws SQLException {
        return this.statementExecutor.getSelectStarRowMapper();
    }

    public RawRowMapper<T> getRawRowMapper() {
        return this.statementExecutor.getRawRowMapper();
    }

    public boolean idExists(ID id) throws SQLException {
        DatabaseConnection readOnlyConnection = this.connectionSource.getReadOnlyConnection();
        try {
            boolean ifExists = this.statementExecutor.ifExists(readOnlyConnection, id);
            return ifExists;
        } finally {
            this.connectionSource.releaseConnection(readOnlyConnection);
        }
    }

    public DatabaseConnection startThreadConnection() throws SQLException {
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        this.connectionSource.saveSpecialConnection(readWriteConnection);
        return readWriteConnection;
    }

    public void endThreadConnection(DatabaseConnection databaseConnection) throws SQLException {
        this.connectionSource.clearSpecialConnection(databaseConnection);
        this.connectionSource.releaseConnection(databaseConnection);
    }

    public void setAutoCommit(boolean z) throws SQLException {
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            setAutoCommit(readWriteConnection, z);
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public void setAutoCommit(DatabaseConnection databaseConnection, boolean z) throws SQLException {
        databaseConnection.setAutoCommit(z);
    }

    public boolean isAutoCommit() throws SQLException {
        DatabaseConnection readWriteConnection = this.connectionSource.getReadWriteConnection();
        try {
            boolean isAutoCommit = isAutoCommit(readWriteConnection);
            return isAutoCommit;
        } finally {
            this.connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public boolean isAutoCommit(DatabaseConnection databaseConnection) throws SQLException {
        return databaseConnection.isAutoCommit();
    }

    public void commit(DatabaseConnection databaseConnection) throws SQLException {
        databaseConnection.commit(null);
    }

    public void rollBack(DatabaseConnection databaseConnection) throws SQLException {
        databaseConnection.rollback(null);
    }

    public ObjectFactory<T> getObjectFactory() {
        return this.objectFactory;
    }

    public void setObjectFactory(ObjectFactory<T> objectFactory) {
        checkForInitialized();
        this.objectFactory = objectFactory;
    }

    public DatabaseTableConfig<T> getTableConfig() {
        return this.tableConfig;
    }

    public TableInfo<T, ID> getTableInfo() {
        return this.tableInfo;
    }

    public ConnectionSource getConnectionSource() {
        return this.connectionSource;
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public void setTableConfig(DatabaseTableConfig<T> databaseTableConfig) {
        this.tableConfig = databaseTableConfig;
    }

    static <T, ID> Dao<T, ID> createDao(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        return new BaseDaoImpl<T, ID>(connectionSource, cls) {
            public /* bridge */ /* synthetic */ Iterator iterator() {
                return super.iterator();
            }
        };
    }

    static <T, ID> Dao<T, ID> createDao(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        return new BaseDaoImpl<T, ID>(connectionSource, databaseTableConfig) {
            public /* bridge */ /* synthetic */ Iterator iterator() {
                return super.iterator();
            }
        };
    }

    protected void checkForInitialized() {
        if (!this.initialized) {
            throw new IllegalStateException("you must call initialize() before you can use the dao");
        }
    }

    private <FT> ForeignCollection<FT> makeEmptyForeignCollection(T t, String str) throws SQLException {
        Object obj;
        checkForInitialized();
        if (t == null) {
            obj = null;
        } else {
            obj = extractId(t);
        }
        for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
            if (fieldType.getColumnName().equals(str)) {
                ForeignCollection buildForeignCollection = fieldType.buildForeignCollection(t, obj);
                if (t != null) {
                    fieldType.assignField(t, buildForeignCollection, true, null);
                }
                return buildForeignCollection;
            }
        }
        throw new IllegalArgumentException("Could not find a field named " + str);
    }

    private CloseableIterator<T> createIterator(int i) {
        try {
            return this.statementExecutor.buildIterator(this, this.connectionSource, i, this.objectCache);
        } catch (Throwable e) {
            throw new IllegalStateException("Could not build iterator for " + this.dataClass, e);
        }
    }

    private CloseableIterator<T> createIterator(PreparedQuery<T> preparedQuery, int i) throws SQLException {
        try {
            return this.statementExecutor.buildIterator(this, this.connectionSource, preparedQuery, this.objectCache, i);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Could not build prepared-query iterator for " + this.dataClass, e);
        }
    }

    private List<T> queryForMatching(T t, boolean z) throws SQLException {
        checkForInitialized();
        QueryBuilder queryBuilder = queryBuilder();
        Where where = queryBuilder.where();
        FieldType[] fieldTypes = this.tableInfo.getFieldTypes();
        int length = fieldTypes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            FieldType fieldType = fieldTypes[i];
            Object fieldValueIfNotDefault = fieldType.getFieldValueIfNotDefault(t);
            if (fieldValueIfNotDefault != null) {
                Object selectArg;
                if (z) {
                    selectArg = new SelectArg(fieldValueIfNotDefault);
                } else {
                    selectArg = fieldValueIfNotDefault;
                }
                where.eq(fieldType.getColumnName(), selectArg);
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (i2 == 0) {
            return Collections.emptyList();
        }
        where.and(i2);
        return queryBuilder.query();
    }

    private List<T> queryForFieldValues(Map<String, Object> map, boolean z) throws SQLException {
        checkForInitialized();
        QueryBuilder queryBuilder = queryBuilder();
        Where where = queryBuilder.where();
        for (Entry entry : map.entrySet()) {
            Object selectArg;
            Object value = entry.getValue();
            if (z) {
                selectArg = new SelectArg(value);
            } else {
                selectArg = value;
            }
            where.eq((String) entry.getKey(), selectArg);
        }
        if (map.size() == 0) {
            return Collections.emptyList();
        }
        where.and(map.size());
        return queryBuilder.query();
    }
}
