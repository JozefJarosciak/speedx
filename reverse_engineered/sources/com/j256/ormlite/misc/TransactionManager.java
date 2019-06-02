package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager {
    private static final String SAVE_POINT_PREFIX = "ORMLITE";
    private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
    private static AtomicInteger savePointCounter = new AtomicInteger();
    private ConnectionSource connectionSource;

    public TransactionManager(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
        initialize();
    }

    public void initialize() {
        if (this.connectionSource == null) {
            throw new IllegalStateException("dataSource was not set on " + getClass().getSimpleName());
        }
    }

    public <T> T callInTransaction(Callable<T> callable) throws SQLException {
        return callInTransaction(this.connectionSource, callable);
    }

    public static <T> T callInTransaction(ConnectionSource connectionSource, Callable<T> callable) throws SQLException {
        DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection();
        try {
            T callInTransaction = callInTransaction(readWriteConnection, connectionSource.saveSpecialConnection(readWriteConnection), connectionSource.getDatabaseType(), callable);
            return callInTransaction;
        } finally {
            connectionSource.clearSpecialConnection(readWriteConnection);
            connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public static <T> T callInTransaction(DatabaseConnection databaseConnection, DatabaseType databaseType, Callable<T> callable) throws SQLException {
        return callInTransaction(databaseConnection, false, databaseType, callable);
    }

    public static <T> T callInTransaction(DatabaseConnection databaseConnection, boolean z, DatabaseType databaseType, Callable<T> callable) throws SQLException {
        Savepoint savepoint;
        boolean z2;
        T call;
        boolean z3 = false;
        if (!z) {
            try {
                if (!databaseType.isNestedSavePointsSupported()) {
                    savepoint = null;
                    z2 = false;
                    call = callable.call();
                    if (z2) {
                        commit(databaseConnection, savepoint);
                    }
                    if (z3) {
                        databaseConnection.setAutoCommit(true);
                        logger.debug("restored auto-commit to true");
                    }
                    return call;
                }
            } catch (Throwable e) {
                if (z2) {
                    try {
                        rollBack(databaseConnection, savepoint);
                    } catch (SQLException e2) {
                        logger.error(e, "after commit exception, rolling back to save-point also threw exception");
                    }
                }
                throw e;
            } catch (Throwable e3) {
                if (z2) {
                    try {
                        rollBack(databaseConnection, savepoint);
                    } catch (SQLException e4) {
                        logger.error(e3, "after commit exception, rolling back to save-point also threw exception");
                    }
                }
                throw SqlExceptionUtil.create("Transaction callable threw non-SQL exception", e3);
            } catch (Throwable th) {
                if (z3) {
                    databaseConnection.setAutoCommit(true);
                    logger.debug("restored auto-commit to true");
                }
            }
        }
        if (databaseConnection.isAutoCommitSupported()) {
            z3 = databaseConnection.isAutoCommit();
            if (z3) {
                databaseConnection.setAutoCommit(false);
                logger.debug("had to set auto-commit to false");
            }
        }
        Savepoint savePoint = databaseConnection.setSavePoint(SAVE_POINT_PREFIX + savePointCounter.incrementAndGet());
        if (savePoint == null) {
            logger.debug("started savePoint transaction");
        } else {
            logger.debug("started savePoint transaction {}", savePoint.getSavepointName());
        }
        savepoint = savePoint;
        z2 = true;
        call = callable.call();
        if (z2) {
            commit(databaseConnection, savepoint);
        }
        if (z3) {
            databaseConnection.setAutoCommit(true);
            logger.debug("restored auto-commit to true");
        }
        return call;
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    private static void commit(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        Object savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.commit(savepoint);
        if (savepointName == null) {
            logger.debug("committed savePoint transaction");
        } else {
            logger.debug("committed savePoint transaction {}", savepointName);
        }
    }

    private static void rollBack(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        Object savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.rollback(savepoint);
        if (savepointName == null) {
            logger.debug("rolled back savePoint transaction");
        } else {
            logger.debug("rolled back savePoint transaction {}", savepointName);
        }
    }
}
