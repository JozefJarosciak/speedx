package com.beastbikes.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: ListViewFooter */
/* renamed from: com.beastbikes.android.widget.a */
public class C2610a extends LinearLayout {
    /* renamed from: a */
    private View f12203a;
    /* renamed from: b */
    private View f12204b;
    /* renamed from: c */
    private TextView f12205c;
    /* renamed from: d */
    private LinearLayout f12206d;
    /* renamed from: e */
    private int f12207e;

    public C2610a(Context context) {
        super(context);
        m13006a(context);
    }

    public C2610a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13006a(context);
    }

    public void setState(int i) {
        this.f12207e = i;
        this.f12205c.setVisibility(4);
        this.f12204b.setVisibility(4);
        this.f12205c.setVisibility(4);
        if (i == 1) {
            this.f12205c.setVisibility(0);
            this.f12205c.setText(C1373R.string.list_view_footer_hint_ready);
        } else if (i == 2) {
            this.f12204b.setVisibility(0);
        } else if (i == 0) {
            this.f12205c.setVisibility(0);
            this.f12205c.setText(C1373R.string.list_view_footer_hint_normal);
        } else {
            this.f12205c.setVisibility(0);
            this.f12205c.setText(C1373R.string.list_view_footer_hint_no_more);
            this.f12206d.setVisibility(8);
        }
    }

    public int getState() {
        return this.f12207e;
    }

    public void setBottomMargin(int i) {
        if (i >= 0) {
            LayoutParams layoutParams = (LayoutParams) this.f12203a.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.f12203a.setLayoutParams(layoutParams);
        }
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.f12203a.getLayoutParams()).bottomMargin;
    }

    /* renamed from: a */
    public void m13007a() {
        LayoutParams layoutParams = (LayoutParams) this.f12203a.getLayoutParams();
        layoutParams.height = 0;
        this.f12203a.setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    public void m13008b() {
        LayoutParams layoutParams = (LayoutParams) this.f12203a.getLayoutParams();
        layoutParams.height = -2;
        this.f12203a.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private void m13006a(Context context) {
        this.f12206d = (LinearLayout) LayoutInflater.from(context).inflate(C1373R.layout.list_view_footer_more, null);
        addView(this.f12206d);
        this.f12206d.setLayoutParams(new LayoutParams(-1, -2));
        this.f12203a = this.f12206d.findViewById(C1373R.id.list_view_footer_content);
        this.f12204b = this.f12206d.findViewById(C1373R.id.list_view_footer_progressbar);
        this.f12205c = (TextView) this.f12206d.findViewById(C1373R.id.list_view_footer_hint_textview);
    }
}
