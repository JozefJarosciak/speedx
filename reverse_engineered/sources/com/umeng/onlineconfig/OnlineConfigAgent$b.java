package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.C4764c;
import com.umeng.onlineconfig.proguard.C4777g;

public class OnlineConfigAgent$b extends C4764c implements Runnable {
    /* renamed from: a */
    Context f16691a;
    /* renamed from: b */
    final /* synthetic */ OnlineConfigAgent f16692b;

    public OnlineConfigAgent$b(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.f16692b = onlineConfigAgent;
        this.f16691a = context.getApplicationContext();
    }

    public void run() {
        try {
            if (m18722c()) {
                m18721b();
            }
        } catch (Exception e) {
            OnlineConfigAgent.a(this.f16692b, null);
            OnlineConfigLog.m18729d(C4766a.f16698a, "request online config error", e);
        }
    }

    /* renamed from: a */
    public boolean mo6184a() {
        return true;
    }

    /* renamed from: b */
    private void m18721b() {
        C4768c c4768c = (C4768c) m18719a(new OnlineConfigAgent$a(this.f16692b, this.f16691a), C4768c.class);
        if (c4768c == null) {
            OnlineConfigAgent.a(this.f16692b, null);
            return;
        }
        if (OnlineConfigLog.LOG) {
            OnlineConfigLog.m18732i(C4766a.f16698a, "response : " + c4768c.f16709b);
        }
        if (c4768c.f16709b) {
            OnlineConfigAgent.a(this.f16692b, this.f16691a, c4768c);
            OnlineConfigAgent.a(this.f16692b, c4768c.f16708a);
            return;
        }
        OnlineConfigAgent.a(this.f16692b, null);
    }

    /* renamed from: c */
    private boolean m18722c() {
        boolean z = true;
        if (TextUtils.isEmpty(TextUtils.isEmpty(OnlineConfigAgent.a(this.f16692b)) ? C4777g.m18746a(this.f16691a) : OnlineConfigAgent.a(this.f16692b))) {
            OnlineConfigLog.m18730e(C4766a.f16698a, "Appkey is missing ,Please check AndroidManifest.xml or set appKey");
            return false;
        }
        boolean z2;
        OnlineConfigAgent$d onlineConfigAgent$d;
        SharedPreferences a;
        Editor edit;
        boolean z3 = OnlineConfigLog.LOG && C4777g.m18753g(this.f16691a);
        if (!z3) {
            SharedPreferences a2 = C4769d.m18741a(this.f16691a).m18742a();
            long j = a2.getLong("last_test_t", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - j > a2.getLong("oc_req_i", 600000)) {
                a2.edit().putLong("last_test_t", currentTimeMillis).commit();
                z2 = true;
                OnlineConfigLog.m18730e(C4766a.f16698a, "isDebug=" + z3 + ",isReqTimeout=" + z2);
                if (z3 && !z2) {
                    return false;
                }
                onlineConfigAgent$d = (OnlineConfigAgent$d) m18719a(new OnlineConfigAgent$c(this.f16692b, this.f16691a), OnlineConfigAgent$d.class);
                if (onlineConfigAgent$d != null) {
                    return false;
                }
                a = C4769d.m18741a(this.f16691a).m18742a();
                if (onlineConfigAgent$d.f16696a <= a.getLong("oc_mdf_t", 0)) {
                    z = false;
                }
                edit = a.edit();
                if (onlineConfigAgent$d.f16697b >= 0) {
                    edit.putLong("oc_req_i", onlineConfigAgent$d.f16697b);
                }
                if (onlineConfigAgent$d.f16696a >= 0) {
                    edit.putLong("oc_mdf_told", a.getLong("oc_mdf_t", 0));
                    edit.putLong("oc_mdf_t", onlineConfigAgent$d.f16696a);
                }
                edit.commit();
                return z;
            }
        }
        z2 = false;
        OnlineConfigLog.m18730e(C4766a.f16698a, "isDebug=" + z3 + ",isReqTimeout=" + z2);
        if (z3) {
        }
        onlineConfigAgent$d = (OnlineConfigAgent$d) m18719a(new OnlineConfigAgent$c(this.f16692b, this.f16691a), OnlineConfigAgent$d.class);
        if (onlineConfigAgent$d != null) {
            return false;
        }
        a = C4769d.m18741a(this.f16691a).m18742a();
        if (onlineConfigAgent$d.f16696a <= a.getLong("oc_mdf_t", 0)) {
            z = false;
        }
        edit = a.edit();
        if (onlineConfigAgent$d.f16697b >= 0) {
            edit.putLong("oc_req_i", onlineConfigAgent$d.f16697b);
        }
        if (onlineConfigAgent$d.f16696a >= 0) {
            edit.putLong("oc_mdf_told", a.getLong("oc_mdf_t", 0));
            edit.putLong("oc_mdf_t", onlineConfigAgent$d.f16696a);
        }
        edit.commit();
        return z;
    }
}
