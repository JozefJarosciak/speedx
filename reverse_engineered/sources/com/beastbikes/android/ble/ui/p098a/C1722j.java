package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleS605ConnectErrorDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.j */
public class C1722j extends Dialog {

    /* compiled from: BleS605ConnectErrorDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.j$1 */
    class C17211 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1722j f7865a;

        C17211(C1722j c1722j) {
            this.f7865a = c1722j;
        }

        public void onClick(View view) {
            this.f7865a.dismiss();
        }
    }

    public C1722j(Context context) {
        super(context, C1373R.style.dialog_ble);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_connect_error);
        m9266a();
    }

    /* renamed from: a */
    private void m9266a() {
        ((TextView) findViewById(C1373R.id.dialog_ble_connect_error_close)).setOnClickListener(new C17211(this));
    }
}
