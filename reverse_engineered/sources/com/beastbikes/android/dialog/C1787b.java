package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: AlertOneButtonDialog */
/* renamed from: com.beastbikes.android.dialog.b */
public class C1787b extends Dialog implements OnClickListener {
    /* renamed from: a */
    private String f8170a;

    public C1787b(Context context, String str) {
        super(context, C1373R.style.dialog_ble);
        this.f8170a = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
        setContentView(C1373R.layout.dialog_one_button);
        TextView textView = (TextView) findViewById(C1373R.id.dialog_one_button_content);
        ((TextView) findViewById(C1373R.id.dialog_one_button)).setOnClickListener(this);
        textView.setText(this.f8170a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.dialog_one_button:
                dismiss();
                return;
            default:
                return;
        }
    }
}
