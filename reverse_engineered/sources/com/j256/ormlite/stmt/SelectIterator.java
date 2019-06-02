package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public class SelectIterator<T, ID> implements CloseableIterator<T> {
    private static final Logger logger = LoggerFactory.getLogger(SelectIterator.class);
    private boolean alreadyMoved;
    private final Dao<T, ID> classDao;
    private boolean closed;
    private final CompiledStatement compiledStmt;
    private final DatabaseConnection connection;
    private final ConnectionSource connectionSource;
    private final Class<?> dataClass;
    private boolean first = true;
    private T last;
    private final DatabaseResults results;
    private int rowC;
    private final GenericRowMapper<T> rowMapper;
    private final String statement;

    public SelectIterator(Class<?> cls, Dao<T, ID> dao, GenericRowMapper<T> genericRowMapper, ConnectionSource connectionSource, DatabaseConnection databaseConnection, CompiledStatement compiledStatement, String str, ObjectCache objectCache) throws SQLException {
        this.dataClass = cls;
        this.classDao = dao;
        this.rowMapper = genericRowMapper;
        this.connectionSource = connectionSource;
        this.connection = databaseConnection;
        this.compiledStmt = compiledStatement;
        this.results = compiledStatement.runQuery(objectCache);
        this.statement = str;
        if (str != null) {
            logger.debug("starting iterator @{} for '{}'", Integer.valueOf(hashCode()), (Object) str);
        }
    }

    public boolean hasNextThrow() throws SQLException {
        if (this.closed) {
            return false;
        }
        if (this.alreadyMoved) {
            return true;
        }
        boolean first;
        if (this.first) {
            this.first = false;
            first = this.results.first();
        } else {
            first = this.results.next();
        }
        if (!first) {
            close();
        }
        this.alreadyMoved = true;
        return first;
    }

    public boolean hasNext() {
        try {
            return hasNextThrow();
        } catch (Throwable e) {
            this.last = null;
            closeQuietly();
            throw new IllegalStateException("Errors getting more results of " + this.dataClass, e);
        }
    }

    public T first() throws SQLException {
        if (this.closed) {
            return null;
        }
        this.first = false;
        if (this.results.first()) {
            return getCurrent();
        }
        return null;
    }

    public T previous() throws SQLException {
        if (this.closed) {
            return null;
        }
        this.first = false;
        if (this.results.previous()) {
            return getCurrent();
        }
        return null;
    }

    public T current() throws SQLException {
        if (this.closed) {
            return null;
        }
        if (this.first) {
            return first();
        }
        return getCurrent();
    }

    public T nextThrow() throws SQLException {
        if (this.closed) {
            return null;
        }
        if (!this.alreadyMoved) {
            boolean first;
            if (this.first) {
                this.first = false;
                first = this.results.first();
            } else {
                first = this.results.next();
            }
            if (!first) {
                this.first = false;
                return null;
            }
        }
        this.first = false;
        return getCurrent();
    }

    public T next() {
        Throwable th;
        try {
            T nextThrow = nextThrow();
            if (nextThrow != null) {
                return nextThrow;
            }
            th = null;
            this.last = null;
            closeQuietly();
            throw new IllegalStateException("Could not get next result for " + this.dataClass, th);
        } catch (SQLException e) {
            th = e;
        }
    }

    public T moveRelative(int i) throws SQLException {
        if (this.closed) {
            return null;
        }
        this.first = false;
        if (this.results.moveRelative(i)) {
            return getCurrent();
        }
        return null;
    }

    public void removeThrow() throws SQLException {
        if (this.last == null) {
            throw new IllegalStateException("No last " + this.dataClass + " object to remove. Must be called after a call to next.");
        } else if (this.classDao == null) {
            throw new IllegalStateException("Cannot remove " + this.dataClass + " object because classDao not initialized");
        } else {
            try {
                this.classDao.delete(this.last);
            } finally {
                this.last = null;
            }
        }
    }

    public void remove() {
        try {
            removeThrow();
        } catch (Throwable e) {
            closeQuietly();
            throw new IllegalStateException("Could not delete " + this.dataClass + " object " + this.last, e);
        }
    }

    public void close() throws SQLException {
        if (!this.closed) {
            this.compiledStmt.close();
            this.closed = true;
            this.last = null;
            if (this.statement != null) {
                logger.debug("closed iterator @{} after {} rows", Integer.valueOf(hashCode()), Integer.valueOf(this.rowC));
            }
            this.connectionSource.releaseConnection(this.connection);
        }
    }

    public void closeQuietly() {
        try {
            close();
        } catch (SQLException e) {
        }
    }

    public DatabaseResults getRawResults() {
        return this.results;
    }

    public void moveToNext() {
        this.last = null;
        this.first = false;
        this.alreadyMoved = false;
    }

    private T getCurrent() throws SQLException {
        this.last = this.rowMapper.mapRow(this.results);
        this.alreadyMoved = false;
        this.rowC++;
        return this.last;
    }
}
