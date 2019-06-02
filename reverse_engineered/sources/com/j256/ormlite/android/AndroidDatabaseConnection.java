package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.misc.VersionUtils;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.StatementBuilder$StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import java.sql.SQLException;
import java.sql.Savepoint;

public class AndroidDatabaseConnection implements DatabaseConnection {
    private static final String ANDROID_VERSION = "VERSION__4.48__";
    private static final String[] NO_STRING_ARGS = new String[0];
    private static Logger logger = LoggerFactory.getLogger(AndroidDatabaseConnection.class);
    private final boolean cancelQueriesEnabled;
    private final SQLiteDatabase db;
    private final boolean readWrite;

    /* renamed from: com.j256.ormlite.android.AndroidDatabaseConnection$1 */
    static /* synthetic */ class C40981 {
        static final /* synthetic */ int[] $SwitchMap$com$j256$ormlite$field$SqlType = new int[SqlType.values().length];

        static {
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SHORT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.INTEGER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.FLOAT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DOUBLE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE_ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SERIALIZABLE.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BLOB.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BIG_DECIMAL.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.UNKNOWN.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    private static class OurSavePoint implements Savepoint {
        private String name;

        public OurSavePoint(String str) {
            this.name = str;
        }

        public int getSavepointId() {
            return 0;
        }

        public String getSavepointName() {
            return this.name;
        }
    }

    static {
        VersionUtils.checkCoreVersusAndroidVersions(ANDROID_VERSION);
    }

    public AndroidDatabaseConnection(SQLiteDatabase sQLiteDatabase, boolean z) {
        this(sQLiteDatabase, z, false);
    }

    public AndroidDatabaseConnection(SQLiteDatabase sQLiteDatabase, boolean z, boolean z2) {
        this.db = sQLiteDatabase;
        this.readWrite = z;
        this.cancelQueriesEnabled = z2;
        logger.trace("{}: db {} opened, read-write = {}", (Object) this, (Object) sQLiteDatabase, Boolean.valueOf(z));
    }

    public boolean isAutoCommitSupported() {
        return true;
    }

    public boolean isAutoCommit() throws SQLException {
        try {
            boolean inTransaction = this.db.inTransaction();
            logger.trace("{}: in transaction is {}", (Object) this, Boolean.valueOf(inTransaction));
            return !inTransaction;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("problems getting auto-commit from database", e);
        }
    }

    public void setAutoCommit(boolean z) {
        if (z) {
            if (this.db.inTransaction()) {
                this.db.setTransactionSuccessful();
                this.db.endTransaction();
            }
        } else if (!this.db.inTransaction()) {
            this.db.beginTransaction();
        }
    }

    public Savepoint setSavePoint(String str) throws SQLException {
        try {
            this.db.beginTransaction();
            logger.trace("{}: save-point set with name {}", (Object) this, (Object) str);
            return new OurSavePoint(str);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("problems beginning transaction " + str, e);
        }
    }

    public boolean isReadWrite() {
        return this.readWrite;
    }

    public void commit(Savepoint savepoint) throws SQLException {
        try {
            this.db.setTransactionSuccessful();
            this.db.endTransaction();
            if (savepoint == null) {
                logger.trace("{}: transaction is successfuly ended", (Object) this);
            } else {
                logger.trace("{}: transaction {} is successfuly ended", (Object) this, savepoint.getSavepointName());
            }
        } catch (Throwable e) {
            if (savepoint == null) {
                throw SqlExceptionUtil.create("problems commiting transaction", e);
            }
            throw SqlExceptionUtil.create("problems commiting transaction " + savepoint.getSavepointName(), e);
        }
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        try {
            this.db.endTransaction();
            if (savepoint == null) {
                logger.trace("{}: transaction is ended, unsuccessfuly", (Object) this);
            } else {
                logger.trace("{}: transaction {} is ended, unsuccessfuly", (Object) this, savepoint.getSavepointName());
            }
        } catch (Throwable e) {
            if (savepoint == null) {
                throw SqlExceptionUtil.create("problems rolling back transaction", e);
            }
            throw SqlExceptionUtil.create("problems rolling back transaction " + savepoint.getSavepointName(), e);
        }
    }

    public int executeStatement(String str, int i) throws SQLException {
        return AndroidCompiledStatement.execSql(this.db, str, str, NO_STRING_ARGS);
    }

    public CompiledStatement compileStatement(String str, StatementBuilder$StatementType statementBuilder$StatementType, FieldType[] fieldTypeArr, int i) {
        Object androidCompiledStatement = new AndroidCompiledStatement(str, this.db, statementBuilder$StatementType, this.cancelQueriesEnabled);
        logger.trace("{}: compiled statement got {}: {}", (Object) this, androidCompiledStatement, (Object) str);
        return androidCompiledStatement;
    }

    public int insert(String str, Object[] objArr, FieldType[] fieldTypeArr, GeneratedKeyHolder generatedKeyHolder) throws SQLException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = this.db.compileStatement(str);
            bindArgs(sQLiteStatement, objArr, fieldTypeArr);
            long executeInsert = sQLiteStatement.executeInsert();
            if (generatedKeyHolder != null) {
                generatedKeyHolder.addKey(Long.valueOf(executeInsert));
            }
            logger.trace("{}: insert statement is compiled and executed, changed {}: {}", (Object) this, Integer.valueOf(1), (Object) str);
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            return 1;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("inserting to database failed: " + str, e);
        } catch (Throwable th) {
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        }
    }

    public int update(String str, Object[] objArr, FieldType[] fieldTypeArr) throws SQLException {
        return update(str, objArr, fieldTypeArr, "updated");
    }

    public int delete(String str, Object[] objArr, FieldType[] fieldTypeArr) throws SQLException {
        return update(str, objArr, fieldTypeArr, "deleted");
    }

    public <T> Object queryForOne(String str, Object[] objArr, FieldType[] fieldTypeArr, GenericRowMapper<T> genericRowMapper, ObjectCache objectCache) throws SQLException {
        Throwable e;
        Throwable th;
        Object obj = null;
        Cursor rawQuery;
        try {
            rawQuery = this.db.rawQuery(str, toStrings(objArr));
            try {
                Object androidDatabaseResults = new AndroidDatabaseResults(rawQuery, objectCache);
                logger.trace("{}: queried for one result: {}", (Object) this, (Object) str);
                if (androidDatabaseResults.first()) {
                    obj = genericRowMapper.mapRow(androidDatabaseResults);
                    if (androidDatabaseResults.next()) {
                        obj = MORE_THAN_ONE;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    } else if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
                return obj;
            } catch (android.database.SQLException e2) {
                e = e2;
                try {
                    throw SqlExceptionUtil.create("queryForOne from database failed: " + str, e);
                } catch (Throwable th2) {
                    e = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw e;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            rawQuery = null;
            e = th;
            throw SqlExceptionUtil.create("queryForOne from database failed: " + str, e);
        } catch (Throwable e32) {
            th = e32;
            rawQuery = null;
            e = th;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw e;
        }
    }

    public long queryForLong(String str) throws SQLException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = this.db.compileStatement(str);
            long simpleQueryForLong = sQLiteStatement.simpleQueryForLong();
            logger.trace("{}: query for long simple query returned {}: {}", (Object) this, Long.valueOf(simpleQueryForLong), (Object) str);
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            return simpleQueryForLong;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("queryForLong from database failed: " + str, e);
        } catch (Throwable th) {
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        }
    }

    public long queryForLong(String str, Object[] objArr, FieldType[] fieldTypeArr) throws SQLException {
        Cursor cursor = null;
        try {
            long j;
            cursor = this.db.rawQuery(str, toStrings(objArr));
            AndroidDatabaseResults androidDatabaseResults = new AndroidDatabaseResults(cursor, null);
            if (androidDatabaseResults.first()) {
                j = androidDatabaseResults.getLong(0);
            } else {
                j = 0;
            }
            logger.trace("{}: query for long raw query returned {}: {}", (Object) this, Long.valueOf(j), (Object) str);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("queryForLong from database failed: " + str, e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void close() throws SQLException {
        try {
            this.db.close();
            logger.trace("{}: db {} closed", (Object) this, this.db);
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("problems closing the database connection", e);
        }
    }

    public void closeQuietly() {
        try {
            close();
        } catch (SQLException e) {
        }
    }

    public boolean isClosed() throws SQLException {
        try {
            boolean isOpen = this.db.isOpen();
            logger.trace("{}: db {} isOpen returned {}", (Object) this, this.db, Boolean.valueOf(isOpen));
            return !isOpen;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("problems detecting if the database is closed", e);
        }
    }

    public boolean isTableExists(String str) {
        Cursor rawQuery = this.db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + str + "'", null);
        try {
            boolean z;
            if (rawQuery.getCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            logger.trace("{}: isTableExists '{}' returned {}", (Object) this, (Object) str, Boolean.valueOf(z));
            return z;
        } finally {
            rawQuery.close();
        }
    }

    private int update(String str, Object[] objArr, FieldType[] fieldTypeArr, String str2) throws SQLException {
        SQLiteStatement sQLiteStatement;
        Throwable th;
        SQLiteStatement compileStatement;
        try {
            compileStatement = this.db.compileStatement(str);
            try {
                int simpleQueryForLong;
                bindArgs(compileStatement, objArr, fieldTypeArr);
                compileStatement.execute();
                if (compileStatement != null) {
                    compileStatement.close();
                    sQLiteStatement = null;
                } else {
                    sQLiteStatement = compileStatement;
                }
                try {
                    compileStatement = this.db.compileStatement("SELECT CHANGES()");
                    try {
                        simpleQueryForLong = (int) compileStatement.simpleQueryForLong();
                        if (compileStatement != null) {
                            compileStatement.close();
                        }
                    } catch (android.database.SQLException e) {
                        simpleQueryForLong = 1;
                        if (compileStatement != null) {
                            compileStatement.close();
                        }
                        logger.trace("{} statement is compiled and executed, changed {}: {}", (Object) str2, Integer.valueOf(simpleQueryForLong), (Object) str);
                        return simpleQueryForLong;
                    } catch (Throwable th2) {
                        th = th2;
                        if (compileStatement != null) {
                            compileStatement.close();
                        }
                        throw th;
                    }
                } catch (android.database.SQLException e2) {
                    compileStatement = sQLiteStatement;
                    simpleQueryForLong = 1;
                    if (compileStatement != null) {
                        compileStatement.close();
                    }
                    logger.trace("{} statement is compiled and executed, changed {}: {}", (Object) str2, Integer.valueOf(simpleQueryForLong), (Object) str);
                    return simpleQueryForLong;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    compileStatement = sQLiteStatement;
                    th = th4;
                    if (compileStatement != null) {
                        compileStatement.close();
                    }
                    throw th;
                }
                logger.trace("{} statement is compiled and executed, changed {}: {}", (Object) str2, Integer.valueOf(simpleQueryForLong), (Object) str);
                return simpleQueryForLong;
            } catch (android.database.SQLException e3) {
                th = e3;
                try {
                    throw SqlExceptionUtil.create("updating database failed: " + str, th);
                } catch (Throwable th5) {
                    th = th5;
                    if (compileStatement != null) {
                        compileStatement.close();
                    }
                    throw th;
                }
            }
        } catch (android.database.SQLException e4) {
            th = e4;
            compileStatement = null;
            throw SqlExceptionUtil.create("updating database failed: " + str, th);
        } catch (Throwable th6) {
            th = th6;
            compileStatement = null;
            if (compileStatement != null) {
                compileStatement.close();
            }
            throw th;
        }
    }

    private void bindArgs(SQLiteStatement sQLiteStatement, Object[] objArr, FieldType[] fieldTypeArr) throws SQLException {
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj == null) {
                    sQLiteStatement.bindNull(i + 1);
                } else {
                    SqlType sqlType = fieldTypeArr[i].getSqlType();
                    switch (C40981.$SwitchMap$com$j256$ormlite$field$SqlType[sqlType.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            sQLiteStatement.bindString(i + 1, obj.toString());
                            break;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            sQLiteStatement.bindLong(i + 1, ((Number) obj).longValue());
                            break;
                        case 9:
                        case 10:
                            sQLiteStatement.bindDouble(i + 1, ((Number) obj).doubleValue());
                            break;
                        case 11:
                        case 12:
                            sQLiteStatement.bindBlob(i + 1, (byte[]) obj);
                            break;
                        case 13:
                        case 14:
                        case 15:
                            throw new SQLException("Invalid Android type: " + sqlType);
                        default:
                            throw new SQLException("Unknown sql argument type: " + sqlType);
                    }
                }
            }
        }
    }

    private String[] toStrings(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                strArr[i] = null;
            } else {
                strArr[i] = obj.toString();
            }
        }
        return strArr;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }
}
