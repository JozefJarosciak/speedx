package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleUpdateSuccessDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.l */
public class C1724l extends Dialog implements OnClickListener {
    /* renamed from: a */
    private int f7866a;
    /* renamed from: b */
    private int f7867b;

    public C1724l(Context context, int i, int i2) {
        super(context, C1373R.style.dialog_ble);
        this.f7866a = i;
        this.f7867b = i2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_update_success);
        m9268a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_ble_update_success_close:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void show() {
        getWindow().setType(2003);
        super.show();
    }

    /* renamed from: a */
    private void m9268a() {
        TextView textView = (TextView) findViewById(C1373R.id.dialog_ble_update_success_desc);
        switch (this.f7866a) {
            case 0:
                if (this.f7867b != 0) {
                    textView.setText(C1373R.string.str_ble_disconnect_wifi_and_auto_update);
                    break;
                } else {
                    textView.setText(C1373R.string.str_ble_central_start_update);
                    break;
                }
            case 1:
                if (this.f7867b != 0) {
                    textView.setText(C1373R.string.str_ble_disconnect_wifi_and_auto_download);
                    break;
                } else {
                    textView.setText(C1373R.string.str_ble_central_start_download);
                    break;
                }
        }
        ((TextView) findViewById(C1373R.id.dialog_ble_update_success_close)).setOnClickListener(this);
    }
}
