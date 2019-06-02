package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p088g.C1465f;

/* compiled from: BleWifiPasswordDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.m */
public class C1726m extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f7868a;
    /* renamed from: b */
    private TextView f7869b;
    /* renamed from: c */
    private EditText f7870c;
    /* renamed from: d */
    private C1725a f7871d;

    /* compiled from: BleWifiPasswordDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.m$a */
    public interface C1725a {
        /* renamed from: a */
        void mo3260a(String str);
    }

    public C1726m(Context context, C1725a c1725a) {
        super(context, C1373R.style.dialog_ble);
        this.f7871d = c1725a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_wifi_password);
        m9270a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_ble_wifi_password_cancel:
                dismiss();
                return;
            case C1373R.id.dialog_ble_wifi_password_sure:
                String obj = this.f7870c.getText().toString();
                if (this.f7871d != null) {
                    this.f7871d.mo3260a(obj);
                }
                dismiss();
                return;
            default:
                return;
        }
    }

    public void show() {
        if (C1465f.c(getContext())) {
            getWindow().setType(2003);
        }
        super.show();
    }

    /* renamed from: a */
    private void m9270a() {
        this.f7868a = (TextView) findViewById(C1373R.id.dialog_ble_wifi_password_sure);
        this.f7869b = (TextView) findViewById(C1373R.id.dialog_ble_wifi_password_cancel);
        this.f7870c = (EditText) findViewById(C1373R.id.dialog_ble_wifi_password_et);
        this.f7868a.setOnClickListener(this);
        this.f7869b.setOnClickListener(this);
    }
}
