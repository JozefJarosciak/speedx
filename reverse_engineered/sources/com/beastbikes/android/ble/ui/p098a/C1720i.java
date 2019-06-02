package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

/* compiled from: BlePairTipDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.i */
public class C1720i extends Dialog implements OnClickListener {
    public C1720i(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_pair);
        m9265a();
    }

    /* renamed from: a */
    private void m9265a() {
        findViewById(C1373R.id.dialog_ble_pair_ok).setOnClickListener(this);
    }

    public void onClick(View view) {
        dismiss();
    }
}
