package com.j256.ormlite.android;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.BaseConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseConnectionProxyFactory;
import java.sql.SQLException;

public class AndroidConnectionSource extends BaseConnectionSource implements ConnectionSource {
    private static DatabaseConnectionProxyFactory connectionProxyFactory;
    private static final Logger logger = LoggerFactory.getLogger(AndroidConnectionSource.class);
    private boolean cancelQueriesEnabled;
    private DatabaseConnection connection;
    private final DatabaseType databaseType;
    private final SQLiteOpenHelper helper;
    private volatile boolean isOpen;
    private final SQLiteDatabase sqliteDatabase;

    public AndroidConnectionSource(SQLiteOpenHelper sQLiteOpenHelper) {
        this.connection = null;
        this.isOpen = true;
        this.databaseType = new SqliteAndroidDatabaseType();
        this.cancelQueriesEnabled = false;
        this.helper = sQLiteOpenHelper;
        this.sqliteDatabase = null;
    }

    public AndroidConnectionSource(SQLiteDatabase sQLiteDatabase) {
        this.connection = null;
        this.isOpen = true;
        this.databaseType = new SqliteAndroidDatabaseType();
        this.cancelQueriesEnabled = false;
        this.helper = null;
        this.sqliteDatabase = sQLiteDatabase;
    }

    public DatabaseConnection getReadOnlyConnection() throws SQLException {
        return getReadWriteConnection();
    }

    public DatabaseConnection getReadWriteConnection() throws SQLException {
        DatabaseConnection savedConnection = getSavedConnection();
        if (savedConnection != null) {
            return savedConnection;
        }
        if (this.connection == null) {
            Object writableDatabase;
            if (this.sqliteDatabase == null) {
                try {
                    writableDatabase = this.helper.getWritableDatabase();
                } catch (Throwable e) {
                    throw SqlExceptionUtil.create("Getting a writable database from helper " + this.helper + " failed", e);
                }
            }
            writableDatabase = this.sqliteDatabase;
            this.connection = new AndroidDatabaseConnection(writableDatabase, true, this.cancelQueriesEnabled);
            if (connectionProxyFactory != null) {
                this.connection = connectionProxyFactory.createProxy(this.connection);
            }
            logger.trace("created connection {} for db {}, helper {}", this.connection, writableDatabase, this.helper);
        } else {
            logger.trace("{}: returning read-write connection {}, helper {}", (Object) this, this.connection, this.helper);
        }
        return this.connection;
    }

    public void releaseConnection(DatabaseConnection databaseConnection) {
    }

    public boolean saveSpecialConnection(DatabaseConnection databaseConnection) throws SQLException {
        return saveSpecial(databaseConnection);
    }

    public void clearSpecialConnection(DatabaseConnection databaseConnection) {
        clearSpecial(databaseConnection, logger);
    }

    public void close() {
        this.isOpen = false;
    }

    public void closeQuietly() {
        close();
    }

    public DatabaseType getDatabaseType() {
        return this.databaseType;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public static void setDatabaseConnectionProxyFactory(DatabaseConnectionProxyFactory databaseConnectionProxyFactory) {
        connectionProxyFactory = databaseConnectionProxyFactory;
    }

    public boolean isCancelQueriesEnabled() {
        return this.cancelQueriesEnabled;
    }

    public void setCancelQueriesEnabled(boolean z) {
        this.cancelQueriesEnabled = z;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }
}
