package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class CyclingHorizontalCheckView extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    private CheckedTextView f9012a;
    /* renamed from: b */
    private CheckedTextView f9013b;
    /* renamed from: c */
    private C2002a f9014c;
    /* renamed from: d */
    private int f9015d;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.CyclingHorizontalCheckView$a */
    public interface C2002a {
        /* renamed from: a */
        void m10327a(CyclingHorizontalCheckView cyclingHorizontalCheckView, View view, boolean z);
    }

    public CyclingHorizontalCheckView(Context context) {
        super(context);
        m10328a(context, null, 0);
    }

    public CyclingHorizontalCheckView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10328a(context, attributeSet, 0);
    }

    public CyclingHorizontalCheckView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10328a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m10328a(Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, C1373R.layout.layout_cycling_horizontal_check, this);
        this.f9012a = (CheckedTextView) findViewById(C1373R.id.tv_check_left);
        this.f9013b = (CheckedTextView) findViewById(C1373R.id.tv_check_right);
        this.f9012a.setOnClickListener(this);
        this.f9013b.setOnClickListener(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CyclingHorizontalCheckView, i, 0);
        CharSequence string = obtainStyledAttributes.getString(0);
        this.f9015d = obtainStyledAttributes.getInt(1, 0);
        obtainStyledAttributes.recycle();
        this.f9012a.setText(string);
        this.f9013b.setText(string);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_check_left:
                if (!this.f9012a.isChecked()) {
                    this.f9012a.toggle();
                    m10329a(view, true);
                    return;
                }
                return;
            case C1373R.id.tv_check_right:
                if (!this.f9013b.isChecked()) {
                    this.f9013b.toggle();
                    m10329a(view, false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m10329a(View view, boolean z) {
        if (z) {
            if (this.f9012a.isChecked()) {
                this.f9013b.setChecked(false);
                this.f9013b.setEnabled(false);
                if (this.f9014c != null) {
                    this.f9014c.m10327a(this, view, true);
                }
            }
        } else if (this.f9013b.isChecked()) {
            this.f9012a.setChecked(false);
            this.f9012a.setEnabled(false);
            if (this.f9014c != null) {
                this.f9014c.m10327a(this, view, false);
            }
        }
    }

    public void setOnCheckViewChangedListener(C2002a c2002a) {
        this.f9014c = c2002a;
    }

    public String getText() {
        String charSequence = this.f9012a.getText().toString();
        if (TextUtils.equals(charSequence, getContext().getString(C1373R.string.str_nil))) {
            return "";
        }
        return charSequence;
    }

    public int getChartType() {
        return this.f9015d;
    }

    public void setCheck(boolean z) {
        if (z) {
            this.f9012a.setChecked(true);
            this.f9013b.setChecked(false);
            this.f9013b.setEnabled(false);
            return;
        }
        this.f9013b.setChecked(true);
        this.f9012a.setChecked(false);
        this.f9012a.setEnabled(false);
    }

    /* renamed from: a */
    public void m10330a() {
        this.f9012a.setChecked(false);
        this.f9012a.setEnabled(false);
        this.f9013b.setChecked(false);
        this.f9013b.setEnabled(false);
    }

    /* renamed from: b */
    public void m10331b() {
        this.f9012a.setChecked(false);
        this.f9012a.setEnabled(true);
        this.f9013b.setChecked(false);
        this.f9013b.setEnabled(true);
    }
}
