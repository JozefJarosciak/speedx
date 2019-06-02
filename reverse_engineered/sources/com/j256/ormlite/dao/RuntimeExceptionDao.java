package com.j256.ormlite.dao;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.ObjectFactory;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RuntimeExceptionDao<T, ID> implements CloseableIterable<T> {
    private static final Level LOG_LEVEL = Level.DEBUG;
    private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionDao.class);
    private Dao<T, ID> dao;

    public RuntimeExceptionDao(Dao<T, ID> dao) {
        this.dao = dao;
    }

    public static <T, ID> RuntimeExceptionDao<T, ID> createDao(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        return new RuntimeExceptionDao(DaoManager.createDao(connectionSource, cls));
    }

    public static <T, ID> RuntimeExceptionDao<T, ID> createDao(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        return new RuntimeExceptionDao(DaoManager.createDao(connectionSource, databaseTableConfig));
    }

    public T queryForId(ID id) {
        try {
            return this.dao.queryForId(id);
        } catch (Throwable e) {
            logMessage(e, "queryForId threw exception on: " + id);
            throw new RuntimeException(e);
        }
    }

    public T queryForFirst(PreparedQuery<T> preparedQuery) {
        try {
            return this.dao.queryForFirst(preparedQuery);
        } catch (Throwable e) {
            logMessage(e, "queryForFirst threw exception on: " + preparedQuery);
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForAll() {
        try {
            return this.dao.queryForAll();
        } catch (Throwable e) {
            logMessage(e, "queryForAll threw exception");
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForEq(String str, Object obj) {
        try {
            return this.dao.queryForEq(str, obj);
        } catch (Throwable e) {
            logMessage(e, "queryForEq threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForMatching(T t) {
        try {
            return this.dao.queryForMatching(t);
        } catch (Throwable e) {
            logMessage(e, "queryForMatching threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForMatchingArgs(T t) {
        try {
            return this.dao.queryForMatchingArgs(t);
        } catch (Throwable e) {
            logMessage(e, "queryForMatchingArgs threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForFieldValues(Map<String, Object> map) {
        try {
            return this.dao.queryForFieldValues(map);
        } catch (Throwable e) {
            logMessage(e, "queryForFieldValues threw exception");
            throw new RuntimeException(e);
        }
    }

    public List<T> queryForFieldValuesArgs(Map<String, Object> map) {
        try {
            return this.dao.queryForFieldValuesArgs(map);
        } catch (Throwable e) {
            logMessage(e, "queryForFieldValuesArgs threw exception");
            throw new RuntimeException(e);
        }
    }

    public T queryForSameId(T t) {
        try {
            return this.dao.queryForSameId(t);
        } catch (Throwable e) {
            logMessage(e, "queryForSameId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public QueryBuilder<T, ID> queryBuilder() {
        return this.dao.queryBuilder();
    }

    public UpdateBuilder<T, ID> updateBuilder() {
        return this.dao.updateBuilder();
    }

    public DeleteBuilder<T, ID> deleteBuilder() {
        return this.dao.deleteBuilder();
    }

    public List<T> query(PreparedQuery<T> preparedQuery) {
        try {
            return this.dao.query(preparedQuery);
        } catch (Throwable e) {
            logMessage(e, "query threw exception on: " + preparedQuery);
            throw new RuntimeException(e);
        }
    }

    public int create(T t) {
        try {
            return this.dao.create(t);
        } catch (Throwable e) {
            logMessage(e, "create threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public T createIfNotExists(T t) {
        try {
            return this.dao.createIfNotExists(t);
        } catch (Throwable e) {
            logMessage(e, "createIfNotExists threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public Dao$CreateOrUpdateStatus createOrUpdate(T t) {
        try {
            return this.dao.createOrUpdate(t);
        } catch (Throwable e) {
            logMessage(e, "createOrUpdate threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public int update(T t) {
        try {
            return this.dao.update((Object) t);
        } catch (Throwable e) {
            logMessage(e, "update threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public int updateId(T t, ID id) {
        try {
            return this.dao.updateId(t, id);
        } catch (Throwable e) {
            logMessage(e, "updateId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public int update(PreparedUpdate<T> preparedUpdate) {
        try {
            return this.dao.update((PreparedUpdate) preparedUpdate);
        } catch (Throwable e) {
            logMessage(e, "update threw exception on: " + preparedUpdate);
            throw new RuntimeException(e);
        }
    }

    public int refresh(T t) {
        try {
            return this.dao.refresh(t);
        } catch (Throwable e) {
            logMessage(e, "refresh threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public int delete(T t) {
        try {
            return this.dao.delete((Object) t);
        } catch (Throwable e) {
            logMessage(e, "delete threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public int deleteById(ID id) {
        try {
            return this.dao.deleteById(id);
        } catch (Throwable e) {
            logMessage(e, "deleteById threw exception on: " + id);
            throw new RuntimeException(e);
        }
    }

    public int delete(Collection<T> collection) {
        try {
            return this.dao.delete((Collection) collection);
        } catch (Throwable e) {
            logMessage(e, "delete threw exception on: " + collection);
            throw new RuntimeException(e);
        }
    }

    public int deleteIds(Collection<ID> collection) {
        try {
            return this.dao.deleteIds(collection);
        } catch (Throwable e) {
            logMessage(e, "deleteIds threw exception on: " + collection);
            throw new RuntimeException(e);
        }
    }

    public int delete(PreparedDelete<T> preparedDelete) {
        try {
            return this.dao.delete((PreparedDelete) preparedDelete);
        } catch (Throwable e) {
            logMessage(e, "delete threw exception on: " + preparedDelete);
            throw new RuntimeException(e);
        }
    }

    public CloseableIterator<T> iterator() {
        return this.dao.iterator();
    }

    public CloseableIterator<T> closeableIterator() {
        return this.dao.closeableIterator();
    }

    public CloseableIterator<T> iterator(int i) {
        return this.dao.iterator(i);
    }

    public CloseableWrappedIterable<T> getWrappedIterable() {
        return this.dao.getWrappedIterable();
    }

    public CloseableWrappedIterable<T> getWrappedIterable(PreparedQuery<T> preparedQuery) {
        return this.dao.getWrappedIterable(preparedQuery);
    }

    public void closeLastIterator() {
        try {
            this.dao.closeLastIterator();
        } catch (Throwable e) {
            logMessage(e, "closeLastIterator threw exception");
            throw new RuntimeException(e);
        }
    }

    public CloseableIterator<T> iterator(PreparedQuery<T> preparedQuery) {
        try {
            return this.dao.iterator((PreparedQuery) preparedQuery);
        } catch (Throwable e) {
            logMessage(e, "iterator threw exception on: " + preparedQuery);
            throw new RuntimeException(e);
        }
    }

    public CloseableIterator<T> iterator(PreparedQuery<T> preparedQuery, int i) {
        try {
            return this.dao.iterator(preparedQuery, i);
        } catch (Throwable e) {
            logMessage(e, "iterator threw exception on: " + preparedQuery);
            throw new RuntimeException(e);
        }
    }

    public GenericRawResults<String[]> queryRaw(String str, String... strArr) {
        try {
            return this.dao.queryRaw(str, strArr);
        } catch (Throwable e) {
            logMessage(e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public long queryRawValue(String str, String... strArr) {
        try {
            return this.dao.queryRawValue(str, strArr);
        } catch (Throwable e) {
            logMessage(e, "queryRawValue threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public <UO> GenericRawResults<UO> queryRaw(String str, RawRowMapper<UO> rawRowMapper, String... strArr) {
        try {
            return this.dao.queryRaw(str, (RawRowMapper) rawRowMapper, strArr);
        } catch (Throwable e) {
            logMessage(e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public <UO> GenericRawResults<UO> queryRaw(String str, DataType[] dataTypeArr, RawRowObjectMapper<UO> rawRowObjectMapper, String... strArr) {
        try {
            return this.dao.queryRaw(str, dataTypeArr, rawRowObjectMapper, strArr);
        } catch (Throwable e) {
            logMessage(e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public GenericRawResults<Object[]> queryRaw(String str, DataType[] dataTypeArr, String... strArr) {
        try {
            return this.dao.queryRaw(str, dataTypeArr, strArr);
        } catch (Throwable e) {
            logMessage(e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public int executeRaw(String str, String... strArr) {
        try {
            return this.dao.executeRaw(str, strArr);
        } catch (Throwable e) {
            logMessage(e, "executeRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public int executeRawNoArgs(String str) {
        try {
            return this.dao.executeRawNoArgs(str);
        } catch (Throwable e) {
            logMessage(e, "executeRawNoArgs threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public int updateRaw(String str, String... strArr) {
        try {
            return this.dao.updateRaw(str, strArr);
        } catch (Throwable e) {
            logMessage(e, "updateRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    public <CT> CT callBatchTasks(Callable<CT> callable) {
        try {
            return this.dao.callBatchTasks(callable);
        } catch (Throwable e) {
            logMessage(e, "callBatchTasks threw exception on: " + callable);
            throw new RuntimeException(e);
        }
    }

    public String objectToString(T t) {
        return this.dao.objectToString(t);
    }

    public boolean objectsEqual(T t, T t2) {
        try {
            return this.dao.objectsEqual(t, t2);
        } catch (Throwable e) {
            logMessage(e, "objectsEqual threw exception on: " + t + " and " + t2);
            throw new RuntimeException(e);
        }
    }

    public ID extractId(T t) {
        try {
            return this.dao.extractId(t);
        } catch (Throwable e) {
            logMessage(e, "extractId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    public Class<T> getDataClass() {
        return this.dao.getDataClass();
    }

    public FieldType findForeignFieldType(Class<?> cls) {
        return this.dao.findForeignFieldType(cls);
    }

    public boolean isUpdatable() {
        return this.dao.isUpdatable();
    }

    public boolean isTableExists() {
        try {
            return this.dao.isTableExists();
        } catch (Throwable e) {
            logMessage(e, "isTableExists threw exception");
            throw new RuntimeException(e);
        }
    }

    public long countOf() {
        try {
            return this.dao.countOf();
        } catch (Throwable e) {
            logMessage(e, "countOf threw exception");
            throw new RuntimeException(e);
        }
    }

    public long countOf(PreparedQuery<T> preparedQuery) {
        try {
            return this.dao.countOf(preparedQuery);
        } catch (Throwable e) {
            logMessage(e, "countOf threw exception on " + preparedQuery);
            throw new RuntimeException(e);
        }
    }

    public void assignEmptyForeignCollection(T t, String str) {
        try {
            this.dao.assignEmptyForeignCollection(t, str);
        } catch (Throwable e) {
            logMessage(e, "assignEmptyForeignCollection threw exception on " + str);
            throw new RuntimeException(e);
        }
    }

    public <FT> ForeignCollection<FT> getEmptyForeignCollection(String str) {
        try {
            return this.dao.getEmptyForeignCollection(str);
        } catch (Throwable e) {
            logMessage(e, "getEmptyForeignCollection threw exception on " + str);
            throw new RuntimeException(e);
        }
    }

    public void setObjectCache(boolean z) {
        try {
            this.dao.setObjectCache(z);
        } catch (Throwable e) {
            logMessage(e, "setObjectCache(" + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    public ObjectCache getObjectCache() {
        return this.dao.getObjectCache();
    }

    public void setObjectCache(ObjectCache objectCache) {
        try {
            this.dao.setObjectCache(objectCache);
        } catch (Throwable e) {
            logMessage(e, "setObjectCache threw exception on " + objectCache);
            throw new RuntimeException(e);
        }
    }

    public void clearObjectCache() {
        this.dao.clearObjectCache();
    }

    public T mapSelectStarRow(DatabaseResults databaseResults) {
        try {
            return this.dao.mapSelectStarRow(databaseResults);
        } catch (Throwable e) {
            logMessage(e, "mapSelectStarRow threw exception on results");
            throw new RuntimeException(e);
        }
    }

    public GenericRowMapper<T> getSelectStarRowMapper() {
        try {
            return this.dao.getSelectStarRowMapper();
        } catch (Throwable e) {
            logMessage(e, "getSelectStarRowMapper threw exception");
            throw new RuntimeException(e);
        }
    }

    public boolean idExists(ID id) {
        try {
            return this.dao.idExists(id);
        } catch (Throwable e) {
            logMessage(e, "idExists threw exception on " + id);
            throw new RuntimeException(e);
        }
    }

    public DatabaseConnection startThreadConnection() {
        try {
            return this.dao.startThreadConnection();
        } catch (Throwable e) {
            logMessage(e, "startThreadConnection() threw exception");
            throw new RuntimeException(e);
        }
    }

    public void endThreadConnection(DatabaseConnection databaseConnection) {
        try {
            this.dao.endThreadConnection(databaseConnection);
        } catch (Throwable e) {
            logMessage(e, "endThreadConnection(" + databaseConnection + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public void setAutoCommit(boolean z) {
        try {
            this.dao.setAutoCommit(z);
        } catch (Throwable e) {
            logMessage(e, "setAutoCommit(" + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    public void setAutoCommit(DatabaseConnection databaseConnection, boolean z) {
        try {
            this.dao.setAutoCommit(databaseConnection, z);
        } catch (Throwable e) {
            logMessage(e, "setAutoCommit(" + databaseConnection + "," + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public boolean isAutoCommit() {
        try {
            return this.dao.isAutoCommit();
        } catch (Throwable e) {
            logMessage(e, "isAutoCommit() threw exception");
            throw new RuntimeException(e);
        }
    }

    public boolean isAutoCommit(DatabaseConnection databaseConnection) {
        try {
            return this.dao.isAutoCommit(databaseConnection);
        } catch (Throwable e) {
            logMessage(e, "isAutoCommit(" + databaseConnection + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    public void commit(DatabaseConnection databaseConnection) {
        try {
            this.dao.commit(databaseConnection);
        } catch (Throwable e) {
            logMessage(e, "commit(" + databaseConnection + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    public void rollBack(DatabaseConnection databaseConnection) {
        try {
            this.dao.rollBack(databaseConnection);
        } catch (Throwable e) {
            logMessage(e, "rollBack(" + databaseConnection + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    public void setObjectFactory(ObjectFactory<T> objectFactory) {
        this.dao.setObjectFactory(objectFactory);
    }

    public RawRowMapper<T> getRawRowMapper() {
        return this.dao.getRawRowMapper();
    }

    public ConnectionSource getConnectionSource() {
        return this.dao.getConnectionSource();
    }

    private void logMessage(Exception exception, String str) {
        logger.log(LOG_LEVEL, exception, str);
    }
}
