package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleConnectTipDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.d */
public class C1714d extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f7847a;
    /* renamed from: b */
    private TextView f7848b;
    /* renamed from: c */
    private ImageView f7849c;
    /* renamed from: d */
    private boolean f7850d;
    /* renamed from: e */
    private OnClickListener f7851e;

    public C1714d(Context context, boolean z) {
        super(context, C1373R.style.dialog_ble);
        this.f7850d = z;
    }

    public C1714d(Context context, boolean z, OnClickListener onClickListener) {
        this(context, z);
        this.f7851e = onClickListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_connect_tip);
        setCanceledOnTouchOutside(false);
        m9259a();
    }

    /* renamed from: a */
    private void m9259a() {
        findViewById(C1373R.id.dialog_ble_connect_ok).setOnClickListener(this);
        this.f7847a = (TextView) findViewById(C1373R.id.dialog_ble_connect_title);
        this.f7848b = (TextView) findViewById(C1373R.id.dialog_ble_connect_message);
        this.f7849c = (ImageView) findViewById(C1373R.id.dialog_ble_connect_image);
        if (this.f7850d) {
            this.f7847a.setText(C1373R.string.dialog_ble_connect_speedx_title);
            this.f7848b.setText(C1373R.string.dialog_ble_connect_speedx_message);
            this.f7849c.setImageResource(C1373R.drawable.ic_ble_speedx);
            return;
        }
        this.f7847a.setText(C1373R.string.dialog_ble_connect_speedforce_title);
        this.f7848b.setText(C1373R.string.dialog_ble_connect_speedforce_message);
        this.f7849c.setImageResource(C1373R.drawable.ic_ble_speedforce);
    }

    public void onClick(View view) {
        dismiss();
        if (this.f7851e != null) {
            this.f7851e.onClick(view);
        }
    }
}
