package com.beastbikes.android.ble.ui.p098a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.ui.p099b.C1745a;

/* compiled from: BleDialogWithMsgAndOneButton */
/* renamed from: com.beastbikes.android.ble.ui.a.f */
public class C1716f extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f7859a;
    /* renamed from: b */
    private Button f7860b;
    /* renamed from: c */
    private String f7861c;
    /* renamed from: d */
    private String f7862d;
    /* renamed from: e */
    private C1745a f7863e;

    public C1716f(@NonNull Context context, String str, String str2, C1745a c1745a) {
        super(context, C1373R.style.dialog_ble);
        this.f7861c = str;
        this.f7862d = str2;
        this.f7863e = c1745a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.layout_dialog_with_msg_and_one_button);
        m9261a();
    }

    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case C1373R.id.btn_ble_dialog_bottom:
                if (this.f7863e != null) {
                    this.f7863e.onButtonClick(view);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m9261a() {
        this.f7859a = (TextView) findViewById(C1373R.id.tv_ble_dialog_msg);
        this.f7860b = (Button) findViewById(C1373R.id.btn_ble_dialog_bottom);
        this.f7859a.setText(this.f7861c);
        this.f7860b.setText(this.f7862d);
        this.f7860b.setOnClickListener(this);
    }
}
