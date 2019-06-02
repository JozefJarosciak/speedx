package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.DatabaseTableConfigLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public abstract class OrmLiteSqliteOpenHelper extends SQLiteOpenHelper {
    protected static Logger logger = LoggerFactory.getLogger(OrmLiteSqliteOpenHelper.class);
    protected boolean cancelQueriesEnabled;
    protected AndroidConnectionSource connectionSource;
    private volatile boolean isOpen;

    public abstract void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource);

    public abstract void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2);

    public OrmLiteSqliteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.connectionSource = new AndroidConnectionSource(this);
        this.isOpen = true;
        logger.trace("{}: constructed connectionSource {}", this, this.connectionSource);
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i, int i2) {
        this(context, str, cursorFactory, i, openFileId(context, i2));
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i, File file) {
        this(context, str, cursorFactory, i, openFile(file));
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i, InputStream inputStream) {
        super(context, str, cursorFactory, i);
        this.connectionSource = new AndroidConnectionSource(this);
        this.isOpen = true;
        if (inputStream != null) {
            try {
                DaoManager.addCachedDatabaseConfigs(DatabaseTableConfigLoader.loadDatabaseConfigFromReader(new BufferedReader(new InputStreamReader(inputStream), 4096)));
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            } catch (Throwable e2) {
                throw new IllegalStateException("Could not load object config file", e2);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        }
    }

    public ConnectionSource getConnectionSource() {
        if (!this.isOpen) {
            logger.warn(new IllegalStateException(), "Getting connectionSource was called after closed");
        }
        return this.connectionSource;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        DatabaseConnection androidDatabaseConnection;
        boolean z = true;
        ConnectionSource connectionSource = getConnectionSource();
        DatabaseConnection specialConnection = connectionSource.getSpecialConnection();
        if (specialConnection == null) {
            androidDatabaseConnection = new AndroidDatabaseConnection(sQLiteDatabase, true, this.cancelQueriesEnabled);
            try {
                connectionSource.saveSpecialConnection(androidDatabaseConnection);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not save special connection", e);
            }
        }
        z = false;
        androidDatabaseConnection = specialConnection;
        try {
            onCreate(sQLiteDatabase, connectionSource);
        } finally {
            if (z) {
                connectionSource.clearSpecialConnection(androidDatabaseConnection);
            }
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z = true;
        ConnectionSource connectionSource = getConnectionSource();
        DatabaseConnection specialConnection = connectionSource.getSpecialConnection();
        DatabaseConnection androidDatabaseConnection;
        if (specialConnection == null) {
            androidDatabaseConnection = new AndroidDatabaseConnection(sQLiteDatabase, true, this.cancelQueriesEnabled);
            try {
                connectionSource.saveSpecialConnection(androidDatabaseConnection);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not save special connection", e);
            }
        }
        z = false;
        androidDatabaseConnection = specialConnection;
        try {
            onUpgrade(sQLiteDatabase, connectionSource, i, i2);
        } finally {
            if (z) {
                connectionSource.clearSpecialConnection(androidDatabaseConnection);
            }
        }
    }

    public void close() {
        super.close();
        this.connectionSource.close();
        this.isOpen = false;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public <D extends Dao<T, ?>, T> D getDao(Class<T> cls) throws SQLException {
        return DaoManager.createDao(getConnectionSource(), cls);
    }

    public <D extends RuntimeExceptionDao<T, ?>, T> D getRuntimeExceptionDao(Class<T> cls) {
        try {
            return new RuntimeExceptionDao(getDao(cls));
        } catch (Throwable e) {
            throw new RuntimeException("Could not create RuntimeExcepitionDao for class " + cls, e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    private static InputStream openFileId(Context context, int i) {
        InputStream openRawResource = context.getResources().openRawResource(i);
        if (openRawResource != null) {
            return openRawResource;
        }
        throw new IllegalStateException("Could not find object config file with id " + i);
    }

    private static InputStream openFile(File file) {
        if (file == null) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not open config file " + file, e);
        }
    }
}
