package p203u.aly;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: UMCCDBHelper */
/* renamed from: u.aly.ao */
class ao extends SQLiteOpenHelper {
    /* renamed from: b */
    private static Context f18592b;
    /* renamed from: a */
    private String f18593a;

    /* compiled from: UMCCDBHelper */
    /* renamed from: u.aly.ao$a */
    private static class C5854a {
        /* renamed from: a */
        private static final ao f18591a = new ao(ao.f18592b, bj.m21698a(ao.f18592b), "cc.db", null, 1);
    }

    /* renamed from: a */
    public static synchronized ao m21195a(Context context) {
        ao a;
        synchronized (ao.class) {
            f18592b = context;
            a = C5854a.f18591a;
        }
        return a;
    }

    private ao(Context context, String str, String str2, CursorFactory cursorFactory, int i) {
        this(new bn(context, str), str2, cursorFactory, i);
    }

    private ao(Context context, String str, CursorFactory cursorFactory, int i) {
        if (str == null || str.equals("")) {
            str = "cc.db";
        }
        super(context, str, cursorFactory, i);
        m21197b();
    }

    /* renamed from: b */
    private void m21197b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!(m21200a("aggregated", writableDatabase) && m21200a("aggregated_cache", writableDatabase))) {
                m21199c(writableDatabase);
            }
            if (!m21200a("system", writableDatabase)) {
                m21198b(writableDatabase);
            }
            if (!m21200a("limitedck", writableDatabase)) {
                m21196a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public boolean m21200a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z;
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            m21199c(sQLiteDatabase);
            m21198b(sQLiteDatabase);
            m21196a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    private boolean m21196a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f18593a = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            sQLiteDatabase.execSQL(this.f18593a);
            return true;
        } catch (SQLException e) {
            ah.m21165d("create reference table error!");
            return false;
        }
    }

    /* renamed from: b */
    private boolean m21198b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f18593a = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            sQLiteDatabase.execSQL(this.f18593a);
            return true;
        } catch (SQLException e) {
            ah.m21165d("create system table error!");
            return false;
        }
    }

    /* renamed from: c */
    private boolean m21199c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f18593a = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f18593a);
            this.f18593a = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f18593a);
            return true;
        } catch (SQLException e) {
            ah.m21165d("create aggregated table error!");
            return false;
        }
    }
}
