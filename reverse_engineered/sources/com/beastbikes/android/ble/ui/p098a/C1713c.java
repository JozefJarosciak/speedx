package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

/* compiled from: BleCadenceGuideDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.c */
public class C1713c extends Dialog implements OnClickListener {
    public C1713c(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_cadence_guide);
        m9258a();
    }

    public void onClick(View view) {
        dismiss();
    }

    /* renamed from: a */
    private void m9258a() {
        findViewById(C1373R.id.dialog_ble_cadence_guide_iknow_tv).setOnClickListener(this);
    }
}
