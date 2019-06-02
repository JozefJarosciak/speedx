package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: BleDownloadMapHintDialog */
/* renamed from: com.beastbikes.android.ble.ui.a.g */
public class C1718g extends Dialog implements OnClickListener {
    /* renamed from: a */
    private C1717a f7864a;

    /* compiled from: BleDownloadMapHintDialog */
    /* renamed from: com.beastbikes.android.ble.ui.a.g$a */
    public interface C1717a {
        /* renamed from: c */
        void m9262c();
    }

    public C1718g(Context context, C1717a c1717a) {
        super(context, C1373R.style.dialog_ble);
        this.f7864a = c1717a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_ble_download_map_hint);
        m9263a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_ble_download_cancel:
                dismiss();
                return;
            case C1373R.id.dialog_ble_download_map:
                this.f7864a.m9262c();
                dismiss();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m9263a() {
        ((TextView) findViewById(C1373R.id.dialog_ble_download_cancel)).setOnClickListener(this);
        ((TextView) findViewById(C1373R.id.dialog_ble_download_map)).setOnClickListener(this);
    }
}
