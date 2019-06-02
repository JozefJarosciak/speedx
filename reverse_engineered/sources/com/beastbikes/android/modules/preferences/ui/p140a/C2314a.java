package com.beastbikes.android.modules.preferences.ui.p140a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: StravaUnBindDialog */
/* renamed from: com.beastbikes.android.modules.preferences.ui.a.a */
public class C2314a extends Dialog implements OnClickListener {
    /* renamed from: a */
    C2312a f11012a;
    /* renamed from: b */
    private TextView f11013b;
    /* renamed from: c */
    private TextView f11014c;

    /* compiled from: StravaUnBindDialog */
    /* renamed from: com.beastbikes.android.modules.preferences.ui.a.a$a */
    public interface C2312a {
        /* renamed from: a */
        void mo3477a();
    }

    public C2314a(Context context, C2312a c2312a) {
        super(context, C1373R.style.dialog_ble);
        this.f11012a = c2312a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_strava_disconnect_tip);
        m11848a();
    }

    /* renamed from: a */
    private void m11848a() {
        this.f11013b = (TextView) findViewById(C1373R.id.dialog_ok);
        this.f11014c = (TextView) findViewById(C1373R.id.dialog_cancel);
        this.f11013b.setOnClickListener(this);
        this.f11014c.setOnClickListener(this);
    }

    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case C1373R.id.dialog_ok:
                if (this.f11012a != null) {
                    this.f11012a.mo3477a();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
