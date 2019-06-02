package com.beastbikes.android.modules.cycling.club.ui.widget.richeditor;

import android.os.Handler;
import android.os.Message;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.p062c.C1882d;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p088g.C2798a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: PushImageManage */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.b */
public class C2168b {
    /* renamed from: a */
    Handler f10152a = new C21672(this);
    /* renamed from: b */
    private ArrayList<String> f10153b = new ArrayList();
    /* renamed from: c */
    private Map<String, String> f10154c = new HashMap();
    /* renamed from: d */
    private String f10155d;
    /* renamed from: e */
    private C2794a f10156e;
    /* renamed from: f */
    private C2080a f10157f;
    /* renamed from: g */
    private int f10158g;

    /* compiled from: PushImageManage */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.b$a */
    public interface C2080a {
        /* renamed from: a */
        void mo3373a();

        /* renamed from: a */
        void mo3374a(String str);
    }

    /* compiled from: PushImageManage */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.b$1 */
    class C21661 implements C1882d {
        /* renamed from: a */
        final /* synthetic */ C2168b f10150a;

        C21661(C2168b c2168b) {
            this.f10150a = c2168b;
        }

        /* renamed from: a */
        public void mo3362a(String str) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = str;
            this.f10150a.f10152a.sendMessage(obtain);
        }

        /* renamed from: a */
        public void mo3361a() {
            if (this.f10150a.f10157f != null) {
                this.f10150a.f10157f.mo3373a();
            }
        }
    }

    /* compiled from: PushImageManage */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.b$2 */
    class C21672 extends Handler {
        /* renamed from: a */
        final /* synthetic */ C2168b f10151a;

        C21672(C2168b c2168b) {
            this.f10151a = c2168b;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.f10151a.f10154c.put(this.f10151a.f10153b.get(this.f10151a.f10158g), (String) message.obj);
            }
            if (this.f10151a.f10158g >= this.f10151a.f10153b.size() - 1) {
                this.f10151a.m11114a();
                return;
            }
            this.f10151a.f10158g = this.f10151a.f10158g + 1;
            this.f10151a.m11116a((String) this.f10151a.f10153b.get(this.f10151a.f10158g));
        }
    }

    public C2168b(String str, C2794a c2794a) {
        this.f10153b.addAll(C2169c.m11127b(str));
        this.f10155d = str;
        this.f10156e = c2794a;
    }

    /* renamed from: a */
    public void m11122a(C2080a c2080a) {
        this.f10157f = c2080a;
        if (this.f10153b.isEmpty()) {
            c2080a.mo3374a(this.f10155d);
            return;
        }
        this.f10158g = 0;
        m11116a((String) this.f10153b.get(this.f10158g));
    }

    /* renamed from: a */
    private void m11116a(String str) {
        String a = C2798a.m13751a(UUID.randomUUID().toString());
        C1880a c1880a = new C1880a(BeastBikes.j());
        a = c1880a.m9745f() + a;
        c1880a.m9737a(new C21661(this));
        c1880a.m9740a(a, str, a);
    }

    /* renamed from: a */
    private void m11114a() {
        for (String str : this.f10154c.keySet()) {
            this.f10155d = this.f10155d.replace(str, (CharSequence) this.f10154c.get(str));
        }
        if (this.f10157f != null) {
            this.f10157f.mo3374a(this.f10155d);
        }
    }
}
