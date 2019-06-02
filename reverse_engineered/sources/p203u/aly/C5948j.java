package p203u.aly;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import p203u.aly.av.C5864j;

/* compiled from: EventTracker */
/* renamed from: u.aly.j */
public class C5948j {
    /* renamed from: a */
    private final int f19070a = 128;
    /* renamed from: b */
    private final int f19071b = 256;
    /* renamed from: c */
    private final int f19072c = 10;
    /* renamed from: d */
    private Context f19073d;
    /* renamed from: e */
    private C5943f f19074e;

    public C5948j(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null, can't track event");
        }
        this.f19073d = context.getApplicationContext();
        this.f19074e = C5943f.m21932a(this.f19073d);
    }

    /* renamed from: a */
    public void m21976a(String str, String str2, long j, int i) {
        if (m21974a(str) && m21975b(str2)) {
            Object obj;
            Map hashMap = new HashMap();
            if (str2 == null) {
                obj = "";
            } else {
                String str3 = str2;
            }
            hashMap.put(str, obj);
            C5862n c5864j = new C5864j();
            c5864j.f18638c = str;
            c5864j.f18639d = System.currentTimeMillis();
            if (j > 0) {
                c5864j.f18640e = j;
            }
            c5864j.f18636a = 1;
            hashMap = c5864j.f18641f;
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put(str, str2);
            if (c5864j.f18637b == null) {
                c5864j.f18637b = C5958w.m22034g(this.f19073d);
            }
            this.f19074e.mo7223a(c5864j);
        }
    }

    /* renamed from: a */
    private boolean m21974a(String str) {
        if (str != null) {
            int length = str.trim().getBytes().length;
            if (length > 0 && length <= 128) {
                return true;
            }
        }
        ah.m21165d("Event id is empty or too long in tracking Event");
        return false;
    }

    /* renamed from: b */
    private boolean m21975b(String str) {
        if (str == null || str.trim().getBytes().length <= 256) {
            return true;
        }
        ah.m21165d("Event label or value is empty or too long in tracking Event");
        return false;
    }
}
