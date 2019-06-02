package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.dao.RawRowObjectMapper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.mapped.MappedCreate;
import com.j256.ormlite.stmt.mapped.MappedDelete;
import com.j256.ormlite.stmt.mapped.MappedDeleteCollection;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.stmt.mapped.MappedRefresh;
import com.j256.ormlite.stmt.mapped.MappedUpdate;
import com.j256.ormlite.stmt.mapped.MappedUpdateId;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class StatementExecutor<T, ID> implements GenericRowMapper<String[]> {
    private static Logger logger = LoggerFactory.getLogger(StatementExecutor.class);
    private static final FieldType[] noFieldTypes = new FieldType[0];
    private String countStarQuery;
    private final Dao<T, ID> dao;
    private final DatabaseType databaseType;
    private FieldType[] ifExistsFieldTypes;
    private String ifExistsQuery;
    private MappedDelete<T, ID> mappedDelete;
    private MappedCreate<T, ID> mappedInsert;
    private MappedQueryForId<T, ID> mappedQueryForId;
    private MappedRefresh<T, ID> mappedRefresh;
    private MappedUpdate<T, ID> mappedUpdate;
    private MappedUpdateId<T, ID> mappedUpdateId;
    private PreparedQuery<T> preparedQueryForAll;
    private RawRowMapper<T> rawRowMapper;
    private final TableInfo<T, ID> tableInfo;

    private static class ObjectArrayRowMapper implements GenericRowMapper<Object[]> {
        private final DataType[] columnTypes;

        public ObjectArrayRowMapper(DataType[] dataTypeArr) {
            this.columnTypes = dataTypeArr;
        }

        public Object[] mapRow(DatabaseResults databaseResults) throws SQLException {
            int columnCount = databaseResults.getColumnCount();
            Object[] objArr = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                DataType dataType;
                if (i >= this.columnTypes.length) {
                    dataType = DataType.STRING;
                } else {
                    dataType = this.columnTypes[i];
                }
                objArr[i] = dataType.getDataPersister().resultToJava(null, databaseResults, i);
            }
            return objArr;
        }
    }

    private static class UserRawRowMapper<UO> implements GenericRowMapper<UO> {
        private String[] columnNames;
        private final RawRowMapper<UO> mapper;
        private final GenericRowMapper<String[]> stringRowMapper;

        public UserRawRowMapper(RawRowMapper<UO> rawRowMapper, GenericRowMapper<String[]> genericRowMapper) {
            this.mapper = rawRowMapper;
            this.stringRowMapper = genericRowMapper;
        }

        public UO mapRow(DatabaseResults databaseResults) throws SQLException {
            return this.mapper.mapRow(getColumnNames(databaseResults), (String[]) this.stringRowMapper.mapRow(databaseResults));
        }

        private String[] getColumnNames(DatabaseResults databaseResults) throws SQLException {
            if (this.columnNames != null) {
                return this.columnNames;
            }
            this.columnNames = databaseResults.getColumnNames();
            return this.columnNames;
        }
    }

    private static class UserRawRowObjectMapper<UO> implements GenericRowMapper<UO> {
        private String[] columnNames;
        private final DataType[] columnTypes;
        private final RawRowObjectMapper<UO> mapper;

        public UserRawRowObjectMapper(RawRowObjectMapper<UO> rawRowObjectMapper, DataType[] dataTypeArr) {
            this.mapper = rawRowObjectMapper;
            this.columnTypes = dataTypeArr;
        }

        public UO mapRow(DatabaseResults databaseResults) throws SQLException {
            int columnCount = databaseResults.getColumnCount();
            Object[] objArr = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                if (i >= this.columnTypes.length) {
                    objArr[i] = null;
                } else {
                    objArr[i] = this.columnTypes[i].getDataPersister().resultToJava(null, databaseResults, i);
                }
            }
            return this.mapper.mapRow(getColumnNames(databaseResults), this.columnTypes, objArr);
        }

        private String[] getColumnNames(DatabaseResults databaseResults) throws SQLException {
            if (this.columnNames != null) {
                return this.columnNames;
            }
            this.columnNames = databaseResults.getColumnNames();
            return this.columnNames;
        }
    }

    public StatementExecutor(DatabaseType databaseType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao) {
        this.databaseType = databaseType;
        this.tableInfo = tableInfo;
        this.dao = dao;
    }

    public T queryForId(DatabaseConnection databaseConnection, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedQueryForId == null) {
            this.mappedQueryForId = MappedQueryForId.build(this.databaseType, this.tableInfo, null);
        }
        return this.mappedQueryForId.execute(databaseConnection, id, objectCache);
    }

    public T queryForFirst(DatabaseConnection databaseConnection, PreparedStmt<T> preparedStmt, ObjectCache objectCache) throws SQLException {
        Throwable th;
        T t = null;
        CompiledStatement compile = preparedStmt.compile(databaseConnection, StatementBuilder$StatementType.SELECT);
        DatabaseResults runQuery;
        try {
            runQuery = compile.runQuery(objectCache);
            try {
                if (runQuery.first()) {
                    logger.debug("query-for-first of '{}' returned at least 1 result", preparedStmt.getStatement());
                    t = preparedStmt.mapRow(runQuery);
                    if (runQuery != null) {
                        runQuery.close();
                    }
                    compile.close();
                } else {
                    logger.debug("query-for-first of '{}' returned at 0 results", preparedStmt.getStatement());
                    if (runQuery != null) {
                        runQuery.close();
                    }
                    compile.close();
                }
                return t;
            } catch (Throwable th2) {
                th = th2;
                if (runQuery != null) {
                    runQuery.close();
                }
                compile.close();
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            runQuery = null;
            th = th4;
            if (runQuery != null) {
                runQuery.close();
            }
            compile.close();
            throw th;
        }
    }

    public List<T> queryForAll(ConnectionSource connectionSource, ObjectCache objectCache) throws SQLException {
        prepareQueryForAll();
        return query(connectionSource, this.preparedQueryForAll, objectCache);
    }

    public long queryForCountStar(DatabaseConnection databaseConnection) throws SQLException {
        if (this.countStarQuery == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("SELECT COUNT(*) FROM ");
            this.databaseType.appendEscapedEntityName(stringBuilder, this.tableInfo.getTableName());
            this.countStarQuery = stringBuilder.toString();
        }
        long queryForLong = databaseConnection.queryForLong(this.countStarQuery);
        logger.debug("query of '{}' returned {}", this.countStarQuery, Long.valueOf(queryForLong));
        return queryForLong;
    }

    public long queryForLong(DatabaseConnection databaseConnection, PreparedStmt<T> preparedStmt) throws SQLException {
        DatabaseResults databaseResults = null;
        CompiledStatement compile = preparedStmt.compile(databaseConnection, StatementBuilder$StatementType.SELECT_LONG);
        try {
            databaseResults = compile.runQuery(null);
            if (databaseResults.first()) {
                long j = databaseResults.getLong(0);
                return j;
            }
            throw new SQLException("No result found in queryForLong: " + preparedStmt.getStatement());
        } finally {
            if (databaseResults != null) {
                databaseResults.close();
            }
            compile.close();
        }
    }

    public long queryForLong(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        CompiledStatement compileStatement;
        Throwable th;
        DatabaseResults databaseResults = null;
        logger.debug("executing raw query for long: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        try {
            compileStatement = databaseConnection.compileStatement(str, StatementBuilder$StatementType.SELECT, noFieldTypes, -1);
            try {
                assignStatementArguments(compileStatement, strArr);
                databaseResults = compileStatement.runQuery(null);
                if (databaseResults.first()) {
                    long j = databaseResults.getLong(0);
                    if (databaseResults != null) {
                        databaseResults.close();
                    }
                    if (compileStatement != null) {
                        compileStatement.close();
                    }
                    return j;
                }
                throw new SQLException("No result found in queryForLong: " + str);
            } catch (Throwable th2) {
                th = th2;
                if (databaseResults != null) {
                    databaseResults.close();
                }
                if (compileStatement != null) {
                    compileStatement.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compileStatement = null;
            if (databaseResults != null) {
                databaseResults.close();
            }
            if (compileStatement != null) {
                compileStatement.close();
            }
            throw th;
        }
    }

    public List<T> query(ConnectionSource connectionSource, PreparedStmt<T> preparedStmt, ObjectCache objectCache) throws SQLException {
        SelectIterator buildIterator = buildIterator(null, connectionSource, preparedStmt, objectCache, -1);
        try {
            List<T> arrayList = new ArrayList();
            while (buildIterator.hasNextThrow()) {
                arrayList.add(buildIterator.nextThrow());
            }
            logger.debug("query of '{}' returned {} results", preparedStmt.getStatement(), Integer.valueOf(arrayList.size()));
            return arrayList;
        } finally {
            buildIterator.close();
        }
    }

    public SelectIterator<T, ID> buildIterator(BaseDaoImpl<T, ID> baseDaoImpl, ConnectionSource connectionSource, int i, ObjectCache objectCache) throws SQLException {
        prepareQueryForAll();
        return buildIterator(baseDaoImpl, connectionSource, this.preparedQueryForAll, objectCache, i);
    }

    public GenericRowMapper<T> getSelectStarRowMapper() throws SQLException {
        prepareQueryForAll();
        return this.preparedQueryForAll;
    }

    public RawRowMapper<T> getRawRowMapper() {
        if (this.rawRowMapper == null) {
            this.rawRowMapper = new RawRowMapperImpl(this.tableInfo);
        }
        return this.rawRowMapper;
    }

    public SelectIterator<T, ID> buildIterator(BaseDaoImpl<T, ID> baseDaoImpl, ConnectionSource connectionSource, PreparedStmt<T> preparedStmt, ObjectCache objectCache, int i) throws SQLException {
        Throwable th;
        CompiledStatement compiledStatement = null;
        DatabaseConnection readOnlyConnection = connectionSource.getReadOnlyConnection();
        CompiledStatement compile;
        try {
            compile = preparedStmt.compile(readOnlyConnection, StatementBuilder$StatementType.SELECT, i);
            try {
                SelectIterator<T, ID> selectIterator = new SelectIterator(this.tableInfo.getDataClass(), baseDaoImpl, preparedStmt, connectionSource, readOnlyConnection, compile, preparedStmt.getStatement(), objectCache);
                if (compiledStatement != null) {
                    compiledStatement.close();
                }
                if (compiledStatement != null) {
                    connectionSource.releaseConnection(compiledStatement);
                }
                return selectIterator;
            } catch (Throwable th2) {
                th = th2;
                if (compile != null) {
                    compile.close();
                }
                if (readOnlyConnection != null) {
                    connectionSource.releaseConnection(readOnlyConnection);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compile = compiledStatement;
            if (compile != null) {
                compile.close();
            }
            if (readOnlyConnection != null) {
                connectionSource.releaseConnection(readOnlyConnection);
            }
            throw th;
        }
    }

    public GenericRawResults<String[]> queryRaw(ConnectionSource connectionSource, String str, String[] strArr, ObjectCache objectCache) throws SQLException {
        CompiledStatement compileStatement;
        Throwable th;
        CompiledStatement compiledStatement = null;
        logger.debug("executing raw query for: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection readOnlyConnection = connectionSource.getReadOnlyConnection();
        try {
            compileStatement = readOnlyConnection.compileStatement(str, StatementBuilder$StatementType.SELECT, noFieldTypes, -1);
            try {
                assignStatementArguments(compileStatement, strArr);
                GenericRawResults rawResultsImpl = new RawResultsImpl(connectionSource, readOnlyConnection, str, String[].class, compileStatement, this, objectCache);
                if (compiledStatement != null) {
                    compiledStatement.close();
                }
                if (compiledStatement != null) {
                    connectionSource.releaseConnection(compiledStatement);
                }
                return rawResultsImpl;
            } catch (Throwable th2) {
                th = th2;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                if (readOnlyConnection != null) {
                    connectionSource.releaseConnection(readOnlyConnection);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compileStatement = compiledStatement;
            if (compileStatement != null) {
                compileStatement.close();
            }
            if (readOnlyConnection != null) {
                connectionSource.releaseConnection(readOnlyConnection);
            }
            throw th;
        }
    }

    public <UO> GenericRawResults<UO> queryRaw(ConnectionSource connectionSource, String str, RawRowMapper<UO> rawRowMapper, String[] strArr, ObjectCache objectCache) throws SQLException {
        Throwable th;
        CompiledStatement compiledStatement = null;
        logger.debug("executing raw query for: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection readOnlyConnection = connectionSource.getReadOnlyConnection();
        CompiledStatement compileStatement;
        try {
            compileStatement = readOnlyConnection.compileStatement(str, StatementBuilder$StatementType.SELECT, noFieldTypes, -1);
            try {
                assignStatementArguments(compileStatement, strArr);
                GenericRawResults rawResultsImpl = new RawResultsImpl(connectionSource, readOnlyConnection, str, String[].class, compileStatement, new UserRawRowMapper(rawRowMapper, this), objectCache);
                if (compiledStatement != null) {
                    compiledStatement.close();
                }
                if (compiledStatement != null) {
                    connectionSource.releaseConnection(compiledStatement);
                }
                return rawResultsImpl;
            } catch (Throwable th2) {
                th = th2;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                if (readOnlyConnection != null) {
                    connectionSource.releaseConnection(readOnlyConnection);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compileStatement = compiledStatement;
            if (compileStatement != null) {
                compileStatement.close();
            }
            if (readOnlyConnection != null) {
                connectionSource.releaseConnection(readOnlyConnection);
            }
            throw th;
        }
    }

    public <UO> GenericRawResults<UO> queryRaw(ConnectionSource connectionSource, String str, DataType[] dataTypeArr, RawRowObjectMapper<UO> rawRowObjectMapper, String[] strArr, ObjectCache objectCache) throws SQLException {
        CompiledStatement compileStatement;
        Throwable th;
        CompiledStatement compiledStatement = null;
        logger.debug("executing raw query for: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection readOnlyConnection = connectionSource.getReadOnlyConnection();
        try {
            compileStatement = readOnlyConnection.compileStatement(str, StatementBuilder$StatementType.SELECT, noFieldTypes, -1);
            try {
                assignStatementArguments(compileStatement, strArr);
                GenericRawResults rawResultsImpl = new RawResultsImpl(connectionSource, readOnlyConnection, str, String[].class, compileStatement, new UserRawRowObjectMapper(rawRowObjectMapper, dataTypeArr), objectCache);
                if (compiledStatement != null) {
                    compiledStatement.close();
                }
                if (compiledStatement != null) {
                    connectionSource.releaseConnection(compiledStatement);
                }
                return rawResultsImpl;
            } catch (Throwable th2) {
                th = th2;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                if (readOnlyConnection != null) {
                    connectionSource.releaseConnection(readOnlyConnection);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compileStatement = compiledStatement;
            if (compileStatement != null) {
                compileStatement.close();
            }
            if (readOnlyConnection != null) {
                connectionSource.releaseConnection(readOnlyConnection);
            }
            throw th;
        }
    }

    public GenericRawResults<Object[]> queryRaw(ConnectionSource connectionSource, String str, DataType[] dataTypeArr, String[] strArr, ObjectCache objectCache) throws SQLException {
        CompiledStatement compileStatement;
        Throwable th;
        CompiledStatement compiledStatement = null;
        logger.debug("executing raw query for: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection readOnlyConnection = connectionSource.getReadOnlyConnection();
        try {
            compileStatement = readOnlyConnection.compileStatement(str, StatementBuilder$StatementType.SELECT, noFieldTypes, -1);
            try {
                assignStatementArguments(compileStatement, strArr);
                GenericRawResults rawResultsImpl = new RawResultsImpl(connectionSource, readOnlyConnection, str, Object[].class, compileStatement, new ObjectArrayRowMapper(dataTypeArr), objectCache);
                if (compiledStatement != null) {
                    compiledStatement.close();
                }
                if (compiledStatement != null) {
                    connectionSource.releaseConnection(compiledStatement);
                }
                return rawResultsImpl;
            } catch (Throwable th2) {
                th = th2;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                if (readOnlyConnection != null) {
                    connectionSource.releaseConnection(readOnlyConnection);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            compileStatement = compiledStatement;
            if (compileStatement != null) {
                compileStatement.close();
            }
            if (readOnlyConnection != null) {
                connectionSource.releaseConnection(readOnlyConnection);
            }
            throw th;
        }
    }

    public int updateRaw(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        logger.debug("running raw update statement: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("update arguments: {}", (Object) strArr);
        }
        CompiledStatement compileStatement = databaseConnection.compileStatement(str, StatementBuilder$StatementType.UPDATE, noFieldTypes, -1);
        try {
            assignStatementArguments(compileStatement, strArr);
            int runUpdate = compileStatement.runUpdate();
            return runUpdate;
        } finally {
            compileStatement.close();
        }
    }

    public int executeRawNoArgs(DatabaseConnection databaseConnection, String str) throws SQLException {
        logger.debug("running raw execute statement: {}", (Object) str);
        return databaseConnection.executeStatement(str, -1);
    }

    public int executeRaw(DatabaseConnection databaseConnection, String str, String[] strArr) throws SQLException {
        logger.debug("running raw execute statement: {}", (Object) str);
        if (strArr.length > 0) {
            logger.trace("execute arguments: {}", (Object) strArr);
        }
        CompiledStatement compileStatement = databaseConnection.compileStatement(str, StatementBuilder$StatementType.EXECUTE, noFieldTypes, -1);
        try {
            assignStatementArguments(compileStatement, strArr);
            int runExecute = compileStatement.runExecute();
            return runExecute;
        } finally {
            compileStatement.close();
        }
    }

    public int create(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedInsert == null) {
            this.mappedInsert = MappedCreate.build(this.databaseType, this.tableInfo);
        }
        return this.mappedInsert.insert(this.databaseType, databaseConnection, t, objectCache);
    }

    public int update(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedUpdate == null) {
            this.mappedUpdate = MappedUpdate.build(this.databaseType, this.tableInfo);
        }
        return this.mappedUpdate.update(databaseConnection, t, objectCache);
    }

    public int updateId(DatabaseConnection databaseConnection, T t, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedUpdateId == null) {
            this.mappedUpdateId = MappedUpdateId.build(this.databaseType, this.tableInfo);
        }
        return this.mappedUpdateId.execute(databaseConnection, t, id, objectCache);
    }

    public int update(DatabaseConnection databaseConnection, PreparedUpdate<T> preparedUpdate) throws SQLException {
        CompiledStatement compile = preparedUpdate.compile(databaseConnection, StatementBuilder$StatementType.UPDATE);
        try {
            int runUpdate = compile.runUpdate();
            return runUpdate;
        } finally {
            compile.close();
        }
    }

    public int refresh(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedRefresh == null) {
            this.mappedRefresh = MappedRefresh.build(this.databaseType, this.tableInfo);
        }
        return this.mappedRefresh.executeRefresh(databaseConnection, t, objectCache);
    }

    public int delete(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        if (this.mappedDelete == null) {
            this.mappedDelete = MappedDelete.build(this.databaseType, this.tableInfo);
        }
        return this.mappedDelete.delete(databaseConnection, t, objectCache);
    }

    public int deleteById(DatabaseConnection databaseConnection, ID id, ObjectCache objectCache) throws SQLException {
        if (this.mappedDelete == null) {
            this.mappedDelete = MappedDelete.build(this.databaseType, this.tableInfo);
        }
        return this.mappedDelete.deleteById(databaseConnection, id, objectCache);
    }

    public int deleteObjects(DatabaseConnection databaseConnection, Collection<T> collection, ObjectCache objectCache) throws SQLException {
        return MappedDeleteCollection.deleteObjects(this.databaseType, this.tableInfo, databaseConnection, collection, objectCache);
    }

    public int deleteIds(DatabaseConnection databaseConnection, Collection<ID> collection, ObjectCache objectCache) throws SQLException {
        return MappedDeleteCollection.deleteIds(this.databaseType, this.tableInfo, databaseConnection, collection, objectCache);
    }

    public int delete(DatabaseConnection databaseConnection, PreparedDelete<T> preparedDelete) throws SQLException {
        CompiledStatement compile = preparedDelete.compile(databaseConnection, StatementBuilder$StatementType.DELETE);
        try {
            int runUpdate = compile.runUpdate();
            return runUpdate;
        } finally {
            compile.close();
        }
    }

    public <CT> CT callBatchTasks(DatabaseConnection databaseConnection, boolean z, Callable<CT> callable) throws SQLException {
        boolean z2 = false;
        if (this.databaseType.isBatchUseTransaction()) {
            return TransactionManager.callInTransaction(databaseConnection, z, this.databaseType, callable);
        }
        try {
            if (databaseConnection.isAutoCommitSupported()) {
                z2 = databaseConnection.isAutoCommit();
                if (z2) {
                    databaseConnection.setAutoCommit(false);
                    logger.debug("disabled auto-commit on table {} before batch tasks", this.tableInfo.getTableName());
                }
            }
            CT call = callable.call();
            if (!z2) {
                return call;
            }
            databaseConnection.setAutoCommit(true);
            logger.debug("re-enabled auto-commit on table {} after batch tasks", this.tableInfo.getTableName());
            return call;
        } catch (SQLException e) {
            throw e;
        } catch (Throwable e2) {
            throw SqlExceptionUtil.create("Batch tasks callable threw non-SQL exception", e2);
        } catch (Throwable th) {
            if (z2) {
                databaseConnection.setAutoCommit(true);
                logger.debug("re-enabled auto-commit on table {} after batch tasks", this.tableInfo.getTableName());
            }
        }
    }

    public String[] mapRow(DatabaseResults databaseResults) throws SQLException {
        int columnCount = databaseResults.getColumnCount();
        String[] strArr = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            strArr[i] = databaseResults.getString(i);
        }
        return strArr;
    }

    public boolean ifExists(DatabaseConnection databaseConnection, ID id) throws SQLException {
        if (this.ifExistsQuery == null) {
            QueryBuilder queryBuilder = new QueryBuilder(this.databaseType, this.tableInfo, this.dao);
            queryBuilder.selectRaw(new String[]{"COUNT(*)"});
            queryBuilder.where().eq(this.tableInfo.getIdField().getColumnName(), new SelectArg());
            this.ifExistsQuery = queryBuilder.prepareStatementString();
            this.ifExistsFieldTypes = new FieldType[]{this.tableInfo.getIdField()};
        }
        long queryForLong = databaseConnection.queryForLong(this.ifExistsQuery, new Object[]{id}, this.ifExistsFieldTypes);
        logger.debug("query of '{}' returned {}", this.ifExistsQuery, Long.valueOf(queryForLong));
        if (queryForLong != 0) {
            return true;
        }
        return false;
    }

    private void assignStatementArguments(CompiledStatement compiledStatement, String[] strArr) throws SQLException {
        for (int i = 0; i < strArr.length; i++) {
            compiledStatement.setObject(i, strArr[i], SqlType.STRING);
        }
    }

    private void prepareQueryForAll() throws SQLException {
        if (this.preparedQueryForAll == null) {
            this.preparedQueryForAll = new QueryBuilder(this.databaseType, this.tableInfo, this.dao).prepare();
        }
    }
}
