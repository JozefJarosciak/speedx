package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p088g.C1465f;

/* compiled from: BleWifiStatusDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.n */
public class C1728n extends Dialog {
    /* renamed from: a */
    private int f7873a;
    /* renamed from: b */
    private String f7874b;

    /* compiled from: BleWifiStatusDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.n$1 */
    class C17271 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1728n f7872a;

        C17271(C1728n c1728n) {
            this.f7872a = c1728n;
        }

        public void onClick(View view) {
            this.f7872a.dismiss();
        }
    }

    public C1728n(Context context, int i, String str) {
        super(context, C1373R.style.dialog_ble);
        this.f7873a = i;
        this.f7874b = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_wifi_status);
        m9271a();
    }

    public void show() {
        if (C1465f.c(getContext())) {
            getWindow().setType(2003);
        }
        super.show();
    }

    /* renamed from: a */
    private void m9271a() {
        TextView textView = (TextView) findViewById(C1373R.id.dialog_ble_wifi_status_tv);
        CharSequence string = getContext().getString(C1373R.string.str_device_wifi_connect_success);
        switch (this.f7873a) {
            case 0:
                string = getContext().getString(C1373R.string.str_device_wifi_connect_success);
                break;
            case 1:
                string = getContext().getString(C1373R.string.str_device_wifi_passsword_invaild);
                break;
            case 2:
                string = getContext().getString(C1373R.string.str_device_wifi_connect_failed);
                break;
        }
        textView.setText(string);
        ((TextView) findViewById(C1373R.id.dialog_ble_wifi_close_tv)).setOnClickListener(new C17271(this));
    }
}
