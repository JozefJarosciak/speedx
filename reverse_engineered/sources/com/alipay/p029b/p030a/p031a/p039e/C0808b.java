package com.alipay.p029b.p030a.p031a.p039e;

import com.alipay.p029b.p030a.p031a.p032a.C0790b;
import com.alipay.p029b.p030a.p031a.p035c.p037b.C0799a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.alipay.b.a.a.e.b */
public final class C0808b {
    /* renamed from: a */
    private File f1893a = null;
    /* renamed from: b */
    private C0799a f1894b = null;

    public C0808b(String str, C0799a c0799a) {
        this.f1893a = new File(str);
        this.f1894b = c0799a;
    }

    /* renamed from: a */
    private static String m3131a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return jSONObject.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    private synchronized void m3133b() {
        int i = 0;
        synchronized (this) {
            if (this.f1893a != null) {
                if (this.f1893a.exists() && this.f1893a.isDirectory() && this.f1893a.list().length != 0) {
                    int i2;
                    String str;
                    List arrayList = new ArrayList();
                    for (Object add : this.f1893a.list()) {
                        arrayList.add(add);
                    }
                    Collections.sort(arrayList);
                    String str2 = (String) arrayList.get(arrayList.size() - 1);
                    int size = arrayList.size();
                    int i3;
                    if (!str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                        i3 = size;
                        str = str2;
                        i2 = i3;
                    } else if (arrayList.size() >= 2) {
                        i3 = size - 1;
                        str = (String) arrayList.get(arrayList.size() - 2);
                        i2 = i3;
                    }
                    size = !this.f1894b.mo2420a(C0808b.m3131a(C0790b.m3028a(this.f1893a.getAbsolutePath(), str))) ? i2 - 1 : i2;
                    while (i < size) {
                        new File(this.f1893a, (String) arrayList.get(i)).delete();
                        i++;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final void m3134a() {
        new Thread(new C0809c(this)).start();
    }
}
