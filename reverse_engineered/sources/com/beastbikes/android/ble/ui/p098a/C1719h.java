package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

/* compiled from: BleHeartGuideDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.h */
public class C1719h extends Dialog implements OnClickListener {
    public C1719h(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_heart_guide);
        m9264a();
    }

    /* renamed from: a */
    private void m9264a() {
        findViewById(C1373R.id.dialog_ble_heart_guide_iknow_tv).setOnClickListener(this);
    }

    public void onClick(View view) {
        dismiss();
    }
}
