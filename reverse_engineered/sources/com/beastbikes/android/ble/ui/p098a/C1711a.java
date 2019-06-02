package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleActiveFailedDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.a */
public class C1711a extends Dialog {
    /* renamed from: a */
    private String f7844a;

    public C1711a(Context context, String str) {
        super(context, C1373R.style.dialog_ble);
        this.f7844a = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.dialog_ble_active_failed);
        TextView textView = (TextView) findViewById(C1373R.id.dialog_ble_active_failed_message);
        if (!TextUtils.isEmpty(this.f7844a)) {
            textView.setText(this.f7844a);
        }
    }
}
