package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.SettingActivity;

/* compiled from: BleDeviceActiveDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.e */
public class C1715e extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f7852a;
    /* renamed from: b */
    private TextView f7853b;
    /* renamed from: c */
    private TextView f7854c;
    /* renamed from: d */
    private TextView f7855d;
    /* renamed from: e */
    private String f7856e;
    /* renamed from: f */
    private String f7857f;
    /* renamed from: g */
    private C1696a f7858g;

    /* compiled from: BleDeviceActiveDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.e$a */
    public interface C1696a {
        /* renamed from: a */
        void mo3215a();
    }

    public C1715e(Context context, String str, String str2, C1696a c1696a) {
        super(context, C1373R.style.dialog_ble);
        this.f7858g = c1696a;
        this.f7856e = str;
        this.f7857f = str2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_active);
        m9260a();
    }

    /* renamed from: a */
    private void m9260a() {
        this.f7852a = (TextView) findViewById(C1373R.id.dialog_ble_model);
        this.f7853b = (TextView) findViewById(C1373R.id.dialog_ble_number);
        this.f7854c = (TextView) findViewById(C1373R.id.dialog_ble_to_active);
        this.f7855d = (TextView) findViewById(C1373R.id.dialog_ble_switch);
        this.f7854c.setOnClickListener(this);
        this.f7855d.setOnClickListener(this);
        this.f7852a.setText(this.f7856e);
        this.f7853b.setText(this.f7857f);
    }

    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case C1373R.id.dialog_ble_switch:
                getContext().startActivity(new Intent(getContext(), SettingActivity.class));
                return;
            case C1373R.id.dialog_ble_to_active:
                if (this.f7858g != null) {
                    this.f7858g.mo3215a();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
