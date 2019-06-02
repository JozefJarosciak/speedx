package com.beastbikes.android.sphere.restful.p161b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.C0882j;
import java.util.Map;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: APICache */
/* renamed from: com.beastbikes.android.sphere.restful.b.a */
public class C2539a extends SQLiteOpenHelper {
    /* renamed from: a */
    private static final String[] f12003a = new String[]{"api", "params", C0882j.f2229c};
    /* renamed from: b */
    private static final Logger f12004b = LoggerFactory.getLogger(C2539a.class);
    /* renamed from: c */
    private static C2539a f12005c = null;

    /* renamed from: a */
    public static synchronized C2539a m12728a(Context context) {
        C2539a c2539a;
        synchronized (C2539a.class) {
            if (f12005c == null) {
                f12005c = new C2539a(context.getApplicationContext());
            }
            c2539a = f12005c;
        }
        return c2539a;
    }

    private C2539a(Context context) {
        super(context, "leancloud.sqlite", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_cache(api TEXT NOT NULL, params TEXT, result TEXT, PRIMARY KEY(api, params))");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* renamed from: a */
    public String m12729a(String str, Map<String, Object> map) {
        Cursor query;
        Throwable e;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        JSONObject jSONObject = new JSONObject(map);
        try {
            query = readableDatabase.query("api_cache", f12003a, "api=? and params=?", new String[]{str, jSONObject.toString()}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        String string = query.getString(query.getColumnIndex(C0882j.f2229c));
                        if (query == null || query.isClosed()) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f12004b.error("Get API " + str + " cache error", e);
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        query.close();
                        throw e;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return null;
        } catch (Exception e3) {
            e = e3;
            query = null;
            f12004b.error("Get API " + str + " cache error", e);
            query.close();
            return null;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw e;
        }
    }

    /* renamed from: a */
    public boolean m12730a(String str, Map<String, Object> map, String str2) {
        boolean z;
        Throwable e;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = new String[]{str, new JSONObject(map).toString()};
        ContentValues contentValues = new ContentValues();
        Cursor query;
        try {
            query = writableDatabase.query("api_cache", f12003a, "api=? and params=?", strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        contentValues.put(C0882j.f2229c, str2);
                        if (-1 != writableDatabase.update("api_cache", contentValues, "api=? and params=?", strArr)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return z;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f12004b.error("Set API " + str + " cache error", e);
                        z = false;
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return z;
                    } catch (Throwable th) {
                        e = th;
                        query.close();
                        throw e;
                    }
                }
            }
            contentValues.put("api", str);
            contentValues.put("params", r9.toString());
            contentValues.put(C0882j.f2229c, str2);
            z = -1 != writableDatabase.insert("api_cache", null, contentValues);
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f12004b.error("Set API " + str + " cache error", e);
            z = false;
            query.close();
            return z;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw e;
        }
        return z;
    }
}
