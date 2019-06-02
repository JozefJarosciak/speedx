package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.C1666a;

/* compiled from: FirstMaintenanceDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.o */
public class C1730o extends Dialog {
    /* renamed from: a */
    private TextView f7876a;
    /* renamed from: b */
    private TextView f7877b;
    /* renamed from: c */
    private TextView f7878c;
    /* renamed from: d */
    private TextView f7879d;
    /* renamed from: e */
    private TextView f7880e;
    /* renamed from: f */
    private TextView f7881f;
    /* renamed from: g */
    private TextView f7882g;
    /* renamed from: h */
    private C1666a f7883h;

    /* compiled from: FirstMaintenanceDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.o$1 */
    class C17291 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1730o f7875a;

        C17291(C1730o c1730o) {
            this.f7875a = c1730o;
        }

        public void run() {
            this.f7875a.dismiss();
        }
    }

    public C1730o(Context context, C1666a c1666a) {
        super(context, C1373R.style.dialog_ble);
        getWindow().setType(2003);
        this.f7883h = c1666a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_first_maintenance);
        m9272a();
        m9273b();
    }

    /* renamed from: a */
    private void m9272a() {
        this.f7876a = (TextView) findViewById(C1373R.id.first_maintenance_vale1);
        this.f7877b = (TextView) findViewById(C1373R.id.first_maintenance_vale2);
        this.f7878c = (TextView) findViewById(C1373R.id.first_maintenance_vale3);
        this.f7879d = (TextView) findViewById(C1373R.id.first_maintenance_vale4);
        this.f7880e = (TextView) findViewById(C1373R.id.first_maintenance_vale5);
        this.f7881f = (TextView) findViewById(C1373R.id.first_maintenance_vale6);
        this.f7882g = (TextView) findViewById(C1373R.id.first_maintenance_vale7);
    }

    /* renamed from: b */
    private void m9273b() {
        if (this.f7883h != null) {
            this.f7876a.setText(this.f7883h.m9038b());
            this.f7877b.setText(this.f7883h.m9037a() == 0 ? C1373R.string.route_self_activity_un_used : C1373R.string.route_self_activity_used);
            this.f7878c.setText(this.f7883h.m9039c());
            this.f7879d.setText(this.f7883h.m9044h());
            this.f7880e.setText(getContext().getString(C1373R.string.expire_at) + this.f7883h.m9040d());
            this.f7881f.setText(this.f7883h.m9043g());
            this.f7882g.setText(this.f7883h.m9042f());
            if (this.f7883h.m9041e() > 0) {
                this.f7882g.postDelayed(new C17291(this), (long) (this.f7883h.m9041e() * 1000));
            }
        }
    }
}
