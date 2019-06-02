package com.beastbikes.p052a.p053a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.C0882j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: APICache */
/* renamed from: com.beastbikes.a.a.a */
public class C1370a extends SQLiteOpenHelper {
    /* renamed from: a */
    private static final String[] f3990a = new String[]{"api", "params", C0882j.f2229c};
    /* renamed from: b */
    private static final Logger f3991b = LoggerFactory.getLogger(C1370a.class);
    /* renamed from: c */
    private static C1370a f3992c = null;

    /* renamed from: a */
    public static synchronized C1370a m5238a(Context context) {
        C1370a c1370a;
        synchronized (C1370a.class) {
            if (f3992c == null) {
                f3992c = new C1370a(context.getApplicationContext());
            }
            c1370a = f3992c;
        }
        return c1370a;
    }

    private C1370a(Context context) {
        super(context, "leancloud.sqlite", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_cache(api TEXT NOT NULL, params TEXT, result TEXT, PRIMARY KEY(api, params))");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* renamed from: a */
    public void m5239a() {
        try {
            getWritableDatabase().delete("api_cache", "", new String[0]);
        } catch (Throwable e) {
            f3991b.error("Clear API cache error", e);
        }
    }
}
