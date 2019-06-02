package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: AlertDialog */
/* renamed from: com.beastbikes.android.dialog.a */
public class C1786a extends Dialog implements OnClickListener {
    /* renamed from: a */
    private TextView f8158a;
    /* renamed from: b */
    private TextView f8159b;
    /* renamed from: c */
    private Button f8160c;
    /* renamed from: d */
    private Button f8161d;
    /* renamed from: e */
    private String f8162e;
    /* renamed from: f */
    private String f8163f;
    /* renamed from: g */
    private String f8164g = getContext().getResources().getString(C1373R.string.activity_alert_dialog_text_cancel);
    /* renamed from: h */
    private String f8165h = getContext().getResources().getString(C1373R.string.activity_alert_dialog_text_ok);
    /* renamed from: i */
    private int f8166i;
    /* renamed from: j */
    private int f8167j = 16;
    /* renamed from: k */
    private C1784a f8168k;
    /* renamed from: l */
    private C1785b f8169l;

    /* compiled from: AlertDialog */
    /* renamed from: com.beastbikes.android.dialog.a$a */
    public interface C1784a {
        /* renamed from: a */
        void mo3403a(int i);

        /* renamed from: b */
        void mo3404b(int i);
    }

    /* compiled from: AlertDialog */
    /* renamed from: com.beastbikes.android.dialog.a$b */
    public interface C1785b {
        /* renamed from: a */
        void mo3283a();
    }

    public C1786a(Context context, String str, C1785b c1785b) {
        super(context, C1373R.style.alert_dialog);
        this.f8163f = str;
        this.f8169l = c1785b;
    }

    public C1786a(Context context, String str, String str2, C1784a c1784a, int i) {
        super(context, C1373R.style.alert_dialog);
        this.f8162e = str;
        this.f8163f = str2;
        this.f8168k = c1784a;
        this.f8166i = i;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.dialog_alert_layout);
        m9475a();
        m9476b();
    }

    /* renamed from: a */
    private void m9475a() {
        this.f8158a = (TextView) findViewById(C1373R.id.tv_title);
        this.f8159b = (TextView) findViewById(C1373R.id.tv_content);
        this.f8160c = (Button) findViewById(C1373R.id.cancle);
        this.f8161d = (Button) findViewById(C1373R.id.sure);
        this.f8160c.setOnClickListener(this);
        this.f8161d.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m9476b() {
        if (TextUtils.isEmpty(this.f8162e)) {
            this.f8158a.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.f8163f)) {
            this.f8159b.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.f8165h)) {
            this.f8161d.setVisibility(8);
        }
        this.f8158a.setText(this.f8162e);
        this.f8159b.setText(this.f8163f);
        this.f8159b.setTextSize((float) this.f8167j);
        this.f8160c.setText(this.f8164g);
        this.f8161d.setText(this.f8165h);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cancle:
                dismiss();
                if (this.f8168k != null) {
                    this.f8168k.mo3404b(this.f8166i);
                    return;
                }
                return;
            case C1373R.id.sure:
                dismiss();
                if (this.f8168k != null) {
                    this.f8168k.mo3403a(this.f8166i);
                }
                if (this.f8169l != null) {
                    this.f8169l.mo3283a();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
