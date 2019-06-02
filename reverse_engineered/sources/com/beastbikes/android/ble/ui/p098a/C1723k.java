package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleSyncErrorDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.k */
public class C1723k extends Dialog implements OnClickListener {
    public C1723k(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_sync_error);
        m9267a();
    }

    public void show() {
        getWindow().setType(2003);
        super.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_ble_sync_error_close:
                dismiss();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m9267a() {
        ((TextView) findViewById(C1373R.id.dialog_ble_sync_error_close)).setOnClickListener(this);
    }
}
