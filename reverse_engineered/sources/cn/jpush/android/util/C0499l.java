package cn.jpush.android.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

/* renamed from: cn.jpush.android.util.l */
public final class C0499l {
    /* renamed from: a */
    public static ArrayList<String> m1762a(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            CharSequence optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static JSONArray m1763a(ArrayList<String> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put((String) it.next());
            }
        }
        return jSONArray;
    }
}
