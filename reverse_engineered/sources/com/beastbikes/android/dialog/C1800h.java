package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.beastbikes.android.C1373R;

/* compiled from: InputDialog */
/* renamed from: com.beastbikes.android.dialog.h */
public class C1800h extends Dialog implements TextWatcher, OnClickListener {
    /* renamed from: a */
    private TextView f8192a;
    /* renamed from: b */
    private EditText f8193b;
    /* renamed from: c */
    private Button f8194c;
    /* renamed from: d */
    private Button f8195d;
    /* renamed from: e */
    private String f8196e;
    /* renamed from: f */
    private String f8197f;
    /* renamed from: g */
    private boolean f8198g = true;
    /* renamed from: h */
    private C1799a f8199h;
    /* renamed from: i */
    private int f8200i = 20;
    /* renamed from: j */
    private String f8201j;
    /* renamed from: k */
    private String f8202k;

    /* compiled from: InputDialog */
    /* renamed from: com.beastbikes.android.dialog.h$1 */
    class C17981 implements OnEditorActionListener {
        /* renamed from: a */
        final /* synthetic */ C1800h f8191a;

        C17981(C1800h c1800h) {
            this.f8191a = c1800h;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            return keyEvent.getKeyCode() == 66;
        }
    }

    /* compiled from: InputDialog */
    /* renamed from: com.beastbikes.android.dialog.h$a */
    public interface C1799a {
        /* renamed from: b */
        void mo3314b(String str);
    }

    public C1800h(Context context, String str, String str2, C1799a c1799a) {
        super(context, C1373R.style.message_dialog);
        this.f8196e = str;
        this.f8197f = str2;
        this.f8199h = c1799a;
        setCancelable(false);
    }

    public C1800h(Context context, String str, String str2, C1799a c1799a, int i, boolean z, boolean z2) {
        super(context, C1373R.style.message_dialog);
        this.f8196e = str;
        this.f8197f = str2;
        this.f8199h = c1799a;
        this.f8200i = i;
        setCancelable(z);
        this.f8198g = z2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.message_dialog);
        m9503a();
        m9504b();
    }

    /* renamed from: a */
    private void m9503a() {
        this.f8192a = (TextView) findViewById(C1373R.id.tv_hint);
        this.f8193b = (EditText) findViewById(C1373R.id.edit);
        this.f8194c = (Button) findViewById(C1373R.id.cancle);
        this.f8195d = (Button) findViewById(C1373R.id.sure);
        this.f8194c.setOnClickListener(this);
        this.f8195d.setOnClickListener(this);
        this.f8193b.requestFocus();
    }

    /* renamed from: b */
    private void m9504b() {
        if (TextUtils.isEmpty(this.f8196e)) {
            this.f8192a.setVisibility(8);
        } else {
            this.f8192a.setText(this.f8196e);
            this.f8192a.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.f8197f)) {
            this.f8193b.setHint(this.f8197f);
        }
        if (!TextUtils.isEmpty(this.f8201j)) {
            this.f8194c.setText(this.f8201j);
        }
        if (!TextUtils.isEmpty(this.f8202k)) {
            this.f8195d.setText(this.f8202k);
        }
        this.f8193b.setFilters(new InputFilter[]{new LengthFilter(this.f8200i)});
        if (!this.f8198g) {
            this.f8193b.setOnEditorActionListener(new C17981(this));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cancle:
                dismiss();
                return;
            case C1373R.id.sure:
                this.f8199h.mo3314b(this.f8193b.getText().toString());
                return;
            default:
                return;
        }
    }

    public void afterTextChanged(Editable editable) {
        this.f8195d.setEnabled(this.f8193b.getText().length() > 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
