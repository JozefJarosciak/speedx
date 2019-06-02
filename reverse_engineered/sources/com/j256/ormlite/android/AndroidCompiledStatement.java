package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.android.compat.ApiCompatibility;
import com.j256.ormlite.android.compat.ApiCompatibility.CancellationHook;
import com.j256.ormlite.android.compat.ApiCompatibilityUtils;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.StatementBuilder$StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AndroidCompiledStatement implements CompiledStatement {
    private static final String[] NO_STRING_ARGS = new String[0];
    private static final ApiCompatibility apiCompatibility = ApiCompatibilityUtils.getCompatibility();
    private static Logger logger = LoggerFactory.getLogger(AndroidCompiledStatement.class);
    private List<Object> args;
    private final boolean cancelQueriesEnabled;
    private CancellationHook cancellationHook;
    private Cursor cursor;
    private final SQLiteDatabase db;
    private Integer max;
    private final String sql;
    private final StatementBuilder$StatementType type;

    /* renamed from: com.j256.ormlite.android.AndroidCompiledStatement$1 */
    static /* synthetic */ class C40971 {
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
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SHORT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.INTEGER.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.FLOAT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DOUBLE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE_ARRAY.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SERIALIZABLE.ordinal()] = 13;
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

    public AndroidCompiledStatement(String str, SQLiteDatabase sQLiteDatabase, StatementBuilder$StatementType statementBuilder$StatementType, boolean z) {
        this.sql = str;
        this.db = sQLiteDatabase;
        this.type = statementBuilder$StatementType;
        this.cancelQueriesEnabled = z;
    }

    public int getColumnCount() throws SQLException {
        return getCursor().getColumnCount();
    }

    public String getColumnName(int i) throws SQLException {
        return getCursor().getColumnName(i);
    }

    public DatabaseResults runQuery(ObjectCache objectCache) throws SQLException {
        if (this.type.isOkForQuery()) {
            return new AndroidDatabaseResults(getCursor(), objectCache);
        }
        throw new IllegalArgumentException("Cannot call query on a " + this.type + " statement");
    }

    public int runUpdate() throws SQLException {
        if (this.type.isOkForUpdate()) {
            String str;
            if (this.max == null) {
                str = this.sql;
            } else {
                str = this.sql + " " + this.max;
            }
            return execSql(this.db, "runUpdate", str, getArgArray());
        }
        throw new IllegalArgumentException("Cannot call update on a " + this.type + " statement");
    }

    public int runExecute() throws SQLException {
        if (this.type.isOkForExecute()) {
            return execSql(this.db, "runExecute", this.sql, getArgArray());
        }
        throw new IllegalArgumentException("Cannot call execute on a " + this.type + " statement");
    }

    public void close() throws SQLException {
        if (this.cursor != null) {
            try {
                this.cursor.close();
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Problems closing Android cursor", e);
            }
        }
        this.cancellationHook = null;
    }

    public void closeQuietly() {
        try {
            close();
        } catch (SQLException e) {
        }
    }

    public void cancel() {
        if (this.cancellationHook != null) {
            this.cancellationHook.cancel();
        }
    }

    public void setObject(int i, Object obj, SqlType sqlType) throws SQLException {
        isInPrep();
        if (this.args == null) {
            this.args = new ArrayList();
        }
        if (obj == null) {
            this.args.add(i, null);
            return;
        }
        switch (C40971.$SwitchMap$com$j256$ormlite$field$SqlType[sqlType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                this.args.add(i, obj.toString());
                return;
            case 12:
            case 13:
                this.args.add(i, obj);
                return;
            case 14:
            case 15:
                throw new SQLException("Invalid Android type: " + sqlType);
            default:
                throw new SQLException("Unknown sql argument type: " + sqlType);
        }
    }

    public void setMaxRows(int i) throws SQLException {
        isInPrep();
        this.max = Integer.valueOf(i);
    }

    public void setQueryTimeout(long j) {
    }

    public Cursor getCursor() throws SQLException {
        if (this.cursor == null) {
            String str = null;
            try {
                Object obj;
                if (this.max == null) {
                    obj = this.sql;
                } else {
                    obj = this.sql + " " + this.max;
                }
                if (this.cancelQueriesEnabled) {
                    this.cancellationHook = apiCompatibility.createCancellationHook();
                }
                this.cursor = apiCompatibility.rawQuery(this.db, obj, getStringArray(), this.cancellationHook);
                this.cursor.moveToFirst();
                logger.trace("{}: started rawQuery cursor for: {}", (Object) this, obj);
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Problems executing Android query: " + str, e);
            }
        }
        return this.cursor;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    static int execSql(SQLiteDatabase sQLiteDatabase, String str, String str2, Object[] objArr) throws SQLException {
        int simpleQueryForLong;
        Throwable th;
        try {
            sQLiteDatabase.execSQL(str2, objArr);
            SQLiteStatement compileStatement;
            try {
                compileStatement = sQLiteDatabase.compileStatement("SELECT CHANGES()");
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
                    logger.trace("executing statement {} changed {} rows: {}", (Object) str, Integer.valueOf(simpleQueryForLong), (Object) str2);
                    return simpleQueryForLong;
                } catch (Throwable th2) {
                    th = th2;
                    if (compileStatement != null) {
                        compileStatement.close();
                    }
                    throw th;
                }
            } catch (android.database.SQLException e2) {
                compileStatement = null;
                simpleQueryForLong = 1;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                logger.trace("executing statement {} changed {} rows: {}", (Object) str, Integer.valueOf(simpleQueryForLong), (Object) str2);
                return simpleQueryForLong;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                compileStatement = null;
                th = th4;
                if (compileStatement != null) {
                    compileStatement.close();
                }
                throw th;
            }
            logger.trace("executing statement {} changed {} rows: {}", (Object) str, Integer.valueOf(simpleQueryForLong), (Object) str2);
            return simpleQueryForLong;
        } catch (Throwable th5) {
            throw SqlExceptionUtil.create("Problems executing " + str + " Android statement: " + str2, th5);
        }
    }

    private void isInPrep() throws SQLException {
        if (this.cursor != null) {
            throw new SQLException("Query already run. Cannot add argument values.");
        }
    }

    private Object[] getArgArray() {
        if (this.args == null) {
            return NO_STRING_ARGS;
        }
        return this.args.toArray(new Object[this.args.size()]);
    }

    private String[] getStringArray() {
        if (this.args == null) {
            return NO_STRING_ARGS;
        }
        return (String[]) this.args.toArray(new String[this.args.size()]);
    }
}
