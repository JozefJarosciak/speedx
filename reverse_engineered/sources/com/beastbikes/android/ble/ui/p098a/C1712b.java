package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.ui.binding.ResetBindPhoneActivity;

/* compiled from: BleBindDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.b */
public class C1712b extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f7845a;
    /* renamed from: b */
    private TextView f7846b;

    public C1712b(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_bind);
        m9257a();
    }

    /* renamed from: a */
    private void m9257a() {
        this.f7845a = (TextView) findViewById(C1373R.id.dialog_ble_bind_ok);
        this.f7846b = (TextView) findViewById(C1373R.id.dialog_ble_bind_cancel);
        this.f7845a.setOnClickListener(this);
        this.f7846b.setOnClickListener(this);
    }

    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case C1373R.id.dialog_ble_bind_ok:
                getContext().startActivity(new Intent(getContext(), ResetBindPhoneActivity.class));
                return;
            default:
                return;
        }
    }
}
