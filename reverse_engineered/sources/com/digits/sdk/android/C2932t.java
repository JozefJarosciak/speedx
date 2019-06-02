package com.digits.sdk.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.provider.ContactsContract.Data;
import com.android.volley.DefaultRetryPolicy;
import com.digits.sdk.p177a.C2865b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* compiled from: ContactsHelper */
/* renamed from: com.digits.sdk.android.t */
class C2932t {
    /* renamed from: a */
    private static final String[] f13345a = new String[]{"mimetype", "lookup", "data2", "data3", "is_primary", "data1", "data1", "data2", "data3", "is_primary", "data1", "data2", "data3"};
    /* renamed from: b */
    private static final String[] f13346b = new String[]{"vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/name"};
    /* renamed from: c */
    private final Context f13347c;

    C2932t(Context context) {
        this.f13347c = context;
    }

    /* renamed from: a */
    public Cursor m14249a() {
        HashSet hashSet = new HashSet(Arrays.asList(f13345a));
        String[] strArr = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return this.f13347c.getContentResolver().query(Data.CONTENT_URI.buildUpon().appendQueryParameter("limit", Integer.toString(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS)).build(), strArr, "mimetype=? OR mimetype=? OR mimetype=?", f13346b, null);
    }

    /* renamed from: a */
    public List<String> m14250a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return Collections.emptyList();
        }
        int columnIndex = cursor.getColumnIndex("mimetype");
        int columnIndex2 = cursor.getColumnIndex("lookup");
        Map hashMap = new HashMap();
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            ContentValues contentValues = new ContentValues();
            contentValues.put("mimetype", string);
            Object obj = -1;
            switch (string.hashCode()) {
                case -1569536764:
                    if (string.equals("vnd.android.cursor.item/email_v2")) {
                        obj = 1;
                        break;
                    }
                    break;
                case -1079224304:
                    if (string.equals("vnd.android.cursor.item/name")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 684173810:
                    if (string.equals("vnd.android.cursor.item/phone_v2")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, contentValues, "data2");
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data3");
                    DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, contentValues, "is_primary");
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data1");
                    break;
                case 1:
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data1");
                    DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, contentValues, "data2");
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data3");
                    DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, contentValues, "is_primary");
                    break;
                case 2:
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data1");
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data2");
                    DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentValues, "data3");
                    break;
                default:
                    continue;
            }
            string = cursor.getString(columnIndex2);
            List list = (List) hashMap.get(string);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(string, list);
            }
            list.add(contentValues);
        }
        return m14248a(hashMap);
    }

    /* renamed from: a */
    private List<String> m14248a(Map<String, List<ContentValues>> map) {
        List<String> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        C2865b c2865b = new C2865b(-1073741823, "UTF-8");
        for (String str : map.keySet()) {
            List<ContentValues> list = (List) map.get(str);
            Object obj = null;
            hashMap.clear();
            c2865b.m13788a();
            for (ContentValues contentValues : list) {
                Object obj2;
                String asString = contentValues.getAsString("mimetype");
                if ("vnd.android.cursor.item/phone_v2".equals(asString) || "vnd.android.cursor.item/email_v2".equals(asString)) {
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                List list2 = (List) hashMap.get(asString);
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(asString, list2);
                }
                list2.add(contentValues);
                obj = obj2;
            }
            if (obj != null) {
                c2865b.m13786a((List) hashMap.get("vnd.android.cursor.item/name")).m13787a((List) hashMap.get("vnd.android.cursor.item/phone_v2"), null).m13795b((List) hashMap.get("vnd.android.cursor.item/email_v2"));
                arrayList.add(c2865b.toString());
            }
        }
        return arrayList;
    }
}
