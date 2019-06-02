package com.beastbikes.android.modules.train.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.widget.materialdesign.mdswitch.C2671e;

/* compiled from: TrainCourseIdeaItemView */
/* renamed from: com.beastbikes.android.modules.train.ui.widget.a */
public class C2386a extends LinearLayout {
    /* renamed from: a */
    private TextView f11331a;

    public C2386a(Context context) {
        super(context);
        m12115a(context);
    }

    public C2386a(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m12115a(context);
    }

    public C2386a(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12115a(context);
    }

    /* renamed from: a */
    private void m12115a(Context context) {
        setOrientation(0);
        setGravity(17);
        View view = new View(context);
        view.setBackgroundColor(-1);
        addView(view, new LayoutParams(C2671e.m13275a(context, 6), C2671e.m13275a(context, 6)));
        this.f11331a = new TextView(context);
        this.f11331a.setGravity(16);
        this.f11331a.setTextColor(-1);
        this.f11331a.setTextSize(1, 13.0f);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        int a = C2671e.m13275a(context, 10);
        layoutParams.setMargins(a, a, 0, a);
        addView(this.f11331a, layoutParams);
    }

    public void setIdea(String str) {
        this.f11331a.setText(str);
        invalidate();
    }
}
