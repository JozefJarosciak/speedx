package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.wxop.stat.common.C4545q;
import java.util.ArrayList;
import java.util.List;

class bc extends SQLiteOpenHelper {
    /* renamed from: a */
    private String f16031a = "";
    /* renamed from: b */
    private Context f16032b = null;

    public bc(Context context, String str) {
        super(context, str, null, 3);
        this.f16031a = str;
        this.f16032b = context.getApplicationContext();
        if (StatConfig.isDebugEnable()) {
            au.f15994h.m18012i("SQLiteOpenHelper " + this.f16031a);
        }
    }

    /* renamed from: a */
    private void m18006a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        String str = null;
        try {
            query = sQLiteDatabase.query("user", null, null, null, null, null, null);
            try {
                ContentValues contentValues = new ContentValues();
                if (query.moveToNext()) {
                    str = query.getString(0);
                    query.getInt(1);
                    query.getString(2);
                    query.getLong(3);
                    contentValues.put("uid", C4545q.m18103b(str));
                }
                if (str != null) {
                    sQLiteDatabase.update("user", contentValues, "uid=?", new String[]{str});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    au.f15994h.m18011e(th);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private void m18007b(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        Cursor cursor;
        try {
            query = sQLiteDatabase.query("events", null, null, null, null, null, null);
            try {
                List<bd> arrayList = new ArrayList();
                while (query.moveToNext()) {
                    arrayList.add(new bd(query.getLong(0), query.getString(1), query.getInt(2), query.getInt(3)));
                }
                ContentValues contentValues = new ContentValues();
                for (bd bdVar : arrayList) {
                    contentValues.put("content", C4545q.m18103b(bdVar.f16034b));
                    sQLiteDatabase.update("events", contentValues, "event_id=?", new String[]{Long.toString(bdVar.f16033a)});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public synchronized void close() {
        super.close();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        sQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
        sQLiteDatabase.execSQL("CREATE INDEX if not exists status_idx ON events(status)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        au.f15994h.debug("upgrade DB from oldVersion " + i + " to newVersion " + i2);
        if (i == 1) {
            sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            m18006a(sQLiteDatabase);
            m18007b(sQLiteDatabase);
        }
        if (i == 2) {
            m18006a(sQLiteDatabase);
            m18007b(sQLiteDatabase);
        }
    }
}
