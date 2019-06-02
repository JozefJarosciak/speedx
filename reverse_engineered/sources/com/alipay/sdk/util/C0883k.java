package com.alipay.sdk.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;

/* renamed from: com.alipay.sdk.util.k */
public final class C0883k {
    /* renamed from: a */
    private static final String f2230a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";

    /* renamed from: a */
    public static String m3457a(Context context) {
        if (context == null) {
            return C0844a.f2045a;
        }
        String str = C0844a.f2045a;
        if (TextUtils.isEmpty(str)) {
            return C0844a.f2045a;
        }
        return str;
    }

    /* renamed from: b */
    private static String m3458b(Context context) {
        String str = null;
        Cursor query = context.getContentResolver().query(Uri.parse(f2230a), null, null, null, null);
        if (query != null && query.getCount() > 0) {
            if (query.moveToFirst()) {
                str = query.getString(query.getColumnIndex("url"));
            }
            query.close();
        }
        return str;
    }
}
