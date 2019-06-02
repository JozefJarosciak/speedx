package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.android.utils.p163b.C2552b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.widget.BaseLinearLayout;

@C1459b(a = 2130903485)
public class ValidCodeView extends BaseLinearLayout implements TextWatcher {
    @C1458a(a = 2131757093)
    /* renamed from: a */
    private EditText f6786a;
    @C1458a(a = 2131757094)
    /* renamed from: b */
    private TextView f6787b;
    /* renamed from: c */
    private long f6788c = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
    /* renamed from: d */
    private long f6789d = 1000;
    /* renamed from: e */
    private ValidCodeView$a f6790e;
    /* renamed from: f */
    private ValidCodeView$b f6791f;
    /* renamed from: g */
    private boolean f6792g = false;

    public ValidCodeView(Context context) {
        super(context);
    }

    public ValidCodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCreateView() {
        this.f6787b.setOnClickListener(new ValidCodeView$1(this));
        this.f6786a.addTextChangedListener(this);
    }

    public void destroy() {
        this.f6790e.cancel();
    }

    /* renamed from: a */
    public void m7970a(ValidCodeView$b validCodeView$b, long j, int i) {
        this.f6791f = validCodeView$b;
        this.f6788c = j;
        this.f6786a.setHint(i);
        this.f6790e = new ValidCodeView$a(this, this.f6788c, this.f6789d);
        C2574s.a().a(C2552b.class).a(new ValidCodeView$2(this));
    }

    /* renamed from: a */
    public void m7969a(ValidCodeView$b validCodeView$b, int i) {
        m7970a(validCodeView$b, ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD, i);
    }

    /* renamed from: a */
    public void m7968a() {
        if (this.f6791f == null) {
            throw new RuntimeException("You must impl valid listener! ");
        }
        this.f6788c = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
        this.f6791f.b();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f6791f != null) {
            this.f6791f.a(charSequence.length() >= 4);
        }
    }

    public void afterTextChanged(Editable editable) {
    }

    public String getText() {
        if (TextUtils.isEmpty(this.f6786a.getText())) {
            return null;
        }
        return this.f6786a.getText().toString();
    }

    public void setEnable(boolean z) {
        if (!this.f6792g) {
            this.f6787b.setEnabled(z);
            this.f6787b.setClickable(z);
        }
    }

    /* renamed from: b */
    public boolean m7971b() {
        return this.f6786a.getText().length() >= 4;
    }
}
