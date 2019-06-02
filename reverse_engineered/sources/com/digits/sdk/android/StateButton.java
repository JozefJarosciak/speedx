package com.digits.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StateButton extends RelativeLayout {
    /* renamed from: a */
    TextView f13106a;
    /* renamed from: b */
    ProgressBar f13107b;
    /* renamed from: c */
    ImageView f13108c;
    /* renamed from: d */
    CharSequence f13109d;
    /* renamed from: e */
    CharSequence f13110e;
    /* renamed from: f */
    CharSequence f13111f;
    /* renamed from: g */
    C2918j f13112g;
    /* renamed from: h */
    int f13113h;

    public StateButton(Context context) {
        this(context, null);
    }

    public StateButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StateButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13877a(context, attributeSet);
        mo3632a(context);
    }

    /* renamed from: a */
    void m13877a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2876R.styleable.StateButton);
        this.f13111f = obtainStyledAttributes.getText(C2876R.styleable.StateButton_startStateText);
        this.f13109d = obtainStyledAttributes.getText(C2876R.styleable.StateButton_progressStateText);
        this.f13110e = obtainStyledAttributes.getText(C2876R.styleable.StateButton_finishStateText);
        m13879c();
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    void mo3632a(Context context) {
        this.f13113h = bu.m14175a(getResources(), context.getTheme());
        this.f13112g = new C2918j(getResources());
        this.f13112g.m14208a((View) this, this.f13113h);
        this.f13112g.m14209a(this.f13106a, this.f13113h);
        m13874a();
        m13878b();
    }

    /* renamed from: a */
    void m13874a() {
        this.f13108c.setColorFilter(getTextColor(), Mode.SRC_IN);
    }

    /* renamed from: b */
    void m13878b() {
        this.f13107b.setIndeterminateDrawable(getProgressDrawable());
    }

    int getTextColor() {
        return this.f13112g.m14206a(this.f13113h);
    }

    Drawable getProgressDrawable() {
        return bu.m14178a(this.f13113h) ? getResources().getDrawable(C2876R.drawable.progress_dark) : getResources().getDrawable(C2876R.drawable.progress_light);
    }

    /* renamed from: a */
    public void m13875a(int i, int i2, int i3) {
        Context context = getContext();
        this.f13111f = context.getString(i);
        this.f13109d = context.getString(i2);
        this.f13110e = context.getString(i3);
    }

    /* renamed from: c */
    void m13879c() {
        inflate(getContext(), C2876R.layout.dgts__state_button, this);
        this.f13106a = (TextView) findViewById(C2876R.id.dgts__state_button);
        this.f13107b = (ProgressBar) findViewById(C2876R.id.dgts__state_progress);
        this.f13108c = (ImageView) findViewById(C2876R.id.dgts__state_success);
        m13883g();
    }

    /* renamed from: d */
    public void m13880d() {
        setClickable(false);
        this.f13106a.setText(this.f13109d);
        this.f13107b.setVisibility(0);
        this.f13108c.setVisibility(8);
    }

    /* renamed from: e */
    public void m13881e() {
        setClickable(false);
        this.f13106a.setText(this.f13110e);
        this.f13107b.setVisibility(8);
        this.f13108c.setVisibility(0);
    }

    /* renamed from: f */
    public void m13882f() {
        m13883g();
    }

    /* renamed from: g */
    public void m13883g() {
        setClickable(true);
        this.f13106a.setText(this.f13111f);
        this.f13107b.setVisibility(8);
        this.f13108c.setVisibility(8);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.f13106a.setEnabled(z);
        this.f13107b.setEnabled(z);
        this.f13108c.setEnabled(z);
    }
}
