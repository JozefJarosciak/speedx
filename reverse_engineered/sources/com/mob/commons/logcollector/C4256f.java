package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobLog;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: MessageUtils */
/* renamed from: com.mob.commons.logcollector.f */
public class C4256f {
    /* renamed from: a */
    public static synchronized long m16929a(Context context, long j, String str, int i, String str2) throws Throwable {
        long j2;
        synchronized (C4256f.class) {
            if (TextUtils.isEmpty(str)) {
                j2 = -1;
            } else {
                C4252b a = C4252b.m16896a(context);
                ContentValues contentValues = new ContentValues();
                contentValues.put("exception_time", Long.valueOf(j));
                contentValues.put("exception_msg", str.toString());
                contentValues.put("exception_level", Integer.valueOf(i));
                contentValues.put("exception_md5", str2);
                j2 = a.m16899a("table_exception", contentValues);
            }
        }
        return j2;
    }

    /* renamed from: a */
    public static synchronized long m16930a(Context context, ArrayList<String> arrayList) throws Throwable {
        long j;
        synchronized (C4256f.class) {
            if (arrayList == null) {
                j = 0;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < arrayList.size(); i++) {
                    stringBuilder.append("'");
                    stringBuilder.append((String) arrayList.get(i));
                    stringBuilder.append("'");
                    stringBuilder.append(",");
                }
                MobLog.getInstance().m16939i("delete COUNT == %s", Integer.valueOf(C4252b.m16896a(context).m16898a("table_exception", "exception_md5 in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null)));
                j = (long) C4252b.m16896a(context).m16898a("table_exception", "exception_md5 in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null);
            }
        }
        return j;
    }

    /* renamed from: a */
    private static synchronized ArrayList<C4255e> m16931a(Context context, String str, String[] strArr) throws Throwable {
        ArrayList<C4255e> arrayList;
        synchronized (C4256f.class) {
            arrayList = new ArrayList();
            C4255e c4255e = new C4255e();
            C4252b a = C4252b.m16896a(context);
            String str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception group by exception_md5 having max(_id)";
            if (!(TextUtils.isEmpty(str) || strArr == null || strArr.length <= 0)) {
                str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception where " + str + " group by exception_md5 having max(_id)";
            }
            Cursor a2 = a.m16900a(str2, strArr);
            while (a2 != null && a2.moveToNext()) {
                c4255e.f14945b.add(a2.getString(0));
                HashMap hashMap = new HashMap();
                hashMap.put("type", Integer.valueOf(a2.getInt(1)));
                hashMap.put("errat", Long.valueOf(a2.getLong(2)));
                hashMap.put("msg", Base64.encodeToString(a2.getString(3).getBytes(), 2));
                hashMap.put("times", Integer.valueOf(a2.getInt(4)));
                c4255e.f14944a.add(hashMap);
                if (c4255e.f14945b.size() == 50) {
                    arrayList.add(c4255e);
                    c4255e = new C4255e();
                    break;
                }
            }
            a2.close();
            if (c4255e.f14945b.size() != 0) {
                arrayList.add(c4255e);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static synchronized ArrayList<C4255e> m16932a(Context context, String[] strArr) throws Throwable {
        ArrayList<C4255e> a;
        synchronized (C4256f.class) {
            String str = "exception_level = ?";
            if (strArr == null || strArr.length <= 0) {
                str = null;
                strArr = null;
            }
            if (C4252b.m16896a(context).m16897a("table_exception") > 0) {
                a = C4256f.m16931a(context, str, strArr);
            } else {
                a = new ArrayList();
            }
        }
        return a;
    }
}
