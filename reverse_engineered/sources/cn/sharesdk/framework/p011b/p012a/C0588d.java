package cn.sharesdk.framework.p011b.p012a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import cn.sharesdk.framework.utils.C0621d;
import com.avos.avoscloud.AVStatus;
import com.j256.ormlite.field.FieldType;
import java.util.ArrayList;

/* compiled from: MessageUtils */
/* renamed from: cn.sharesdk.framework.b.a.d */
public class C0588d {
    /* renamed from: a */
    public static int f1262a = 0;
    /* renamed from: b */
    public static int f1263b = 1;
    /* renamed from: c */
    public static int f1264c = 2;

    /* renamed from: a */
    public static synchronized long m2035a(Context context, String str, long j) {
        long a;
        synchronized (C0588d.class) {
            if (str != null) {
                if (str.trim() != "") {
                    C0586b a2 = C0586b.m2030a(context);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("post_time", Long.valueOf(j));
                    contentValues.put("message_data", str.toString());
                    a = a2.m2033a(AVStatus.MESSAGE_TAG, contentValues);
                }
            }
            a = -1;
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized long m2036a(Context context, ArrayList<String> arrayList) {
        long j;
        synchronized (C0588d.class) {
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
                C0621d.m2279a().i("delete COUNT == %s", new Object[]{Integer.valueOf(C0586b.m2030a(context).m2032a(AVStatus.MESSAGE_TAG, "_id in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null))});
                j = (long) C0586b.m2030a(context).m2032a(AVStatus.MESSAGE_TAG, "_id in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null);
            }
        }
        return j;
    }

    /* renamed from: a */
    private static synchronized ArrayList<C0587c> m2038a(Context context, String str, String[] strArr) {
        ArrayList<C0587c> arrayList;
        synchronized (C0588d.class) {
            arrayList = new ArrayList();
            C0587c c0587c = new C0587c();
            StringBuilder stringBuilder = new StringBuilder();
            Cursor a = C0586b.m2030a(context).m2034a(AVStatus.MESSAGE_TAG, new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, "post_time", "message_data"}, str, strArr, null);
            StringBuilder stringBuilder2 = stringBuilder;
            C0587c c0587c2 = c0587c;
            while (a != null && a.moveToNext()) {
                c0587c2.f1261b.add(a.getString(0));
                if (c0587c2.f1261b.size() == 100) {
                    stringBuilder2.append(a.getString(2));
                    c0587c2.f1260a = stringBuilder2.toString();
                    arrayList.add(c0587c2);
                    c0587c2 = new C0587c();
                    stringBuilder2 = new StringBuilder();
                } else {
                    stringBuilder2.append(a.getString(2) + "\n");
                }
            }
            a.close();
            if (c0587c2.f1261b.size() != 0) {
                c0587c2.f1260a = stringBuilder2.toString().substring(0, stringBuilder2.length() - 1);
                arrayList.add(c0587c2);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static synchronized ArrayList<C0587c> m2037a(Context context) {
        ArrayList<C0587c> a;
        synchronized (C0588d.class) {
            if (C0586b.m2030a(context).m2031a(AVStatus.MESSAGE_TAG) > 0) {
                a = C0588d.m2038a(context, null, null);
            } else {
                a = new ArrayList();
            }
        }
        return a;
    }
}
