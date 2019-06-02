package com.beastbikes.android.modules.user.ui.binding.widget;

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

/* compiled from: AddFriendRemarksDialog */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.a */
public class C2513a extends Dialog implements TextWatcher, OnClickListener {
    /* renamed from: a */
    private TextView f11906a;
    /* renamed from: b */
    private EditText f11907b;
    /* renamed from: c */
    private Button f11908c;
    /* renamed from: d */
    private Button f11909d;
    /* renamed from: e */
    private String f11910e;
    /* renamed from: f */
    private String f11911f;
    /* renamed from: g */
    private boolean f11912g = true;
    /* renamed from: h */
    private C2488a f11913h;
    /* renamed from: i */
    private int f11914i = 20;
    /* renamed from: j */
    private String f11915j;
    /* renamed from: k */
    private String f11916k;

    /* compiled from: AddFriendRemarksDialog */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.a$a */
    public interface C2488a {
        /* renamed from: a */
        void mo3506a(String str);
    }

    /* compiled from: AddFriendRemarksDialog */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.a$1 */
    class C25121 implements OnEditorActionListener {
        /* renamed from: a */
        final /* synthetic */ C2513a f11905a;

        C25121(C2513a c2513a) {
            this.f11905a = c2513a;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            return keyEvent.getKeyCode() == 66;
        }
    }

    public C2513a(Context context, String str, String str2, C2488a c2488a) {
        super(context, C1373R.style.message_dialog);
        this.f11910e = str;
        this.f11911f = str2;
        this.f11913h = c2488a;
        setCancelable(false);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.dialog_add_friendre_marks);
        m12624a();
        m12625b();
    }

    /* renamed from: a */
    private void m12624a() {
        this.f11906a = (TextView) findViewById(C1373R.id.tv_hint);
        this.f11907b = (EditText) findViewById(C1373R.id.edit);
        this.f11908c = (Button) findViewById(C1373R.id.cancle);
        this.f11909d = (Button) findViewById(C1373R.id.sure);
        this.f11908c.setOnClickListener(this);
        this.f11909d.setOnClickListener(this);
        this.f11907b.requestFocus();
    }

    /* renamed from: b */
    private void m12625b() {
        if (TextUtils.isEmpty(this.f11910e)) {
            this.f11906a.setVisibility(8);
        } else {
            this.f11906a.setText(this.f11910e);
            this.f11906a.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.f11911f)) {
            this.f11907b.setHint(this.f11911f);
        }
        if (!TextUtils.isEmpty(this.f11915j)) {
            this.f11908c.setText(this.f11915j);
        }
        if (!TextUtils.isEmpty(this.f11916k)) {
            this.f11909d.setText(this.f11916k);
        }
        this.f11907b.setFilters(new InputFilter[]{new LengthFilter(this.f11914i)});
        if (!this.f11912g) {
            this.f11907b.setOnEditorActionListener(new C25121(this));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cancle:
                dismiss();
                return;
            case C1373R.id.sure:
                this.f11913h.mo3506a(this.f11907b.getText().toString());
                return;
            default:
                return;
        }
    }

    public void afterTextChanged(Editable editable) {
        this.f11909d.setEnabled(this.f11907b.getText().length() > 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
