package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.ui.binding.widget.C2520d.C2508c;

public class CountryListView extends RelativeLayout implements OnTouchListener {
    /* renamed from: a */
    private C2520d f11898a;
    /* renamed from: b */
    private TextView f11899b;
    /* renamed from: c */
    private LinearLayout f11900c;
    /* renamed from: d */
    private C2515b f11901d;

    public CountryListView(Context context) {
        super(context);
        m12617a(context);
    }

    public CountryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12617a(context);
    }

    public CountryListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12617a(context);
    }

    /* renamed from: a */
    private void m12617a(Context context) {
        this.f11898a = new C2520d(context);
        this.f11898a.setDividerHeight(1);
        this.f11901d = new C2515b(context, this.f11898a);
        this.f11898a.setAdapter(this.f11901d);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int a = m12616a(context, 9);
        layoutParams.setMargins(a, 0, a, 0);
        addView(this.f11898a, layoutParams);
        this.f11899b = new TextView(context);
        this.f11899b.setTextColor(context.getResources().getColor(C1373R.color.smssdk_white));
        this.f11899b.setBackgroundResource(C1373R.drawable.country_group_scroll_down);
        this.f11899b.setTextSize(1, 48.0f);
        this.f11899b.setTypeface(Typeface.DEFAULT);
        this.f11899b.setVisibility(8);
        this.f11899b.setGravity(17);
        int a2 = m12616a(context, 80);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams2.addRule(13);
        addView(this.f11899b, layoutParams2);
        this.f11900c = new LinearLayout(context);
        this.f11900c.setBackgroundResource(C1373R.drawable.country_group_scroll_up);
        this.f11900c.setOrientation(1);
        this.f11900c.setOnTouchListener(this);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(15);
        layoutParams.rightMargin = m12616a(context, 5);
        addView(this.f11900c, layoutParams);
        m12618b(context);
    }

    /* renamed from: b */
    private void m12618b(Context context) {
        this.f11900c.removeAllViews();
        int a = this.f11901d.mo3513a();
        int a2 = m12616a(getContext(), 3);
        int a3 = m12616a(getContext(), 2);
        for (int i = 0; i < a; i++) {
            View textView = new TextView(context);
            textView.setText(this.f11901d.mo3519b(i));
            textView.setGravity(17);
            textView.setPadding(a2, a3, a2, a3);
            textView.setTextColor(getResources().getColor(C1373R.color.smssdk_white));
            this.f11900c.addView(textView);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                view.setBackgroundResource(C1373R.drawable.country_group_scroll_down);
                m12619a((ViewGroup) view, motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
            case 3:
                view.setBackgroundResource(C1373R.drawable.country_group_scroll_up);
                this.f11899b.setVisibility(8);
                break;
            case 2:
                m12619a((ViewGroup) view, motionEvent.getX(), motionEvent.getY());
                break;
        }
        return true;
    }

    /* renamed from: a */
    public void m12619a(ViewGroup viewGroup, float f, float f2) {
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (i < childCount) {
            TextView textView = (TextView) viewGroup.getChildAt(i);
            if (f < ((float) textView.getLeft()) || f > ((float) textView.getRight()) || f2 < ((float) textView.getTop()) || f2 > ((float) textView.getBottom())) {
                i++;
            } else {
                this.f11898a.setSelection(i);
                this.f11899b.setVisibility(0);
                this.f11899b.setText(textView.getText());
                return;
            }
        }
    }

    public void setOnItemClickListener(C2508c c2508c) {
        this.f11898a.setOnItemClickListener(c2508c);
    }

    /* renamed from: a */
    public String[] m12620a(int i, int i2) {
        return this.f11901d.m12641a(i, i2);
    }

    /* renamed from: a */
    private int m12616a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }
}
