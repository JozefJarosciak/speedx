package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.sdk.util.C0880h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import p203u.aly.av.C5866l;
import p203u.aly.av.C5869o;

/* compiled from: ViewPageTracker */
/* renamed from: u.aly.y */
public class C5960y {
    /* renamed from: a */
    private final Map<String, Long> f19117a = new HashMap();
    /* renamed from: b */
    private final ArrayList<C5866l> f19118b = new ArrayList();

    /* renamed from: a */
    public void m22060a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f19117a) {
                this.f19117a.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    /* renamed from: b */
    public void m22061b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.f19117a) {
                l = (Long) this.f19117a.remove(str);
            }
            if (l == null) {
                ah.m21164c("please call 'onPageStart(%s)' before onPageEnd", str);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            synchronized (this.f19118b) {
                C5866l c5866l = new C5866l();
                c5866l.f18647a = str;
                c5866l.f18648b = currentTimeMillis;
                this.f19118b.add(c5866l);
            }
        }
    }

    /* renamed from: a */
    public void m22058a() {
        String str = null;
        long j = 0;
        synchronized (this.f19117a) {
            for (Entry entry : this.f19117a.entrySet()) {
                String str2;
                long j2;
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str2 = (String) entry.getKey();
                    j2 = longValue;
                } else {
                    j2 = j;
                    str2 = str;
                }
                str = str2;
                j = j2;
            }
        }
        if (str != null) {
            m22061b(str);
        }
    }

    /* renamed from: a */
    public void m22059a(Context context) {
        SharedPreferences a = C5955u.m22014a(context);
        Editor edit = a.edit();
        if (this.f19118b.size() > 0) {
            Object string = a.getString("activities", "");
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(string);
                stringBuilder.append(C0880h.f2220b);
            }
            synchronized (this.f19118b) {
                Iterator it = this.f19118b.iterator();
                while (it.hasNext()) {
                    C5866l c5866l = (C5866l) it.next();
                    stringBuilder.append(String.format("[\"%s\",%d]", new Object[]{c5866l.f18647a, Long.valueOf(c5866l.f18648b)}));
                    stringBuilder.append(C0880h.f2220b);
                }
                this.f19118b.clear();
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            edit.remove("activities");
            edit.putString("activities", stringBuilder.toString());
        }
        edit.commit();
    }

    /* renamed from: a */
    public static void m22057a(SharedPreferences sharedPreferences, C5869o c5869o) {
        Object string = sharedPreferences.getString("activities", "");
        if (!TextUtils.isEmpty(string)) {
            try {
                String[] split = string.split(C0880h.f2220b);
                for (String jSONArray : split) {
                    JSONArray jSONArray2 = new JSONArray(jSONArray);
                    C5866l c5866l = new C5866l();
                    c5866l.f18647a = jSONArray2.getString(0);
                    c5866l.f18648b = (long) jSONArray2.getInt(1);
                    c5869o.f18712h.add(c5866l);
                }
            } catch (Throwable e) {
                ah.m21157a(e);
            }
        }
    }
}
