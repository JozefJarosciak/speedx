package com.beastbikes.android.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;

/* compiled from: ListViewHeader */
/* renamed from: com.beastbikes.android.widget.b */
public class C2618b extends LinearLayout {
    /* renamed from: a */
    private LinearLayout f12217a;
    /* renamed from: b */
    private ImageView f12218b;
    /* renamed from: c */
    private int f12219c = 0;
    /* renamed from: d */
    private AnimationDrawable f12220d;
    /* renamed from: e */
    private boolean f12221e;

    public C2618b(Context context, boolean z) {
        super(context);
        this.f12221e = z;
        m13022a(context);
    }

    public C2618b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13022a(context);
    }

    /* renamed from: a */
    private void m13022a(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.f12217a = (LinearLayout) LayoutInflater.from(context).inflate(C1373R.layout.list_view_head_refresh, null);
        addView(this.f12217a, layoutParams);
        setGravity(80);
        this.f12218b = (ImageView) findViewById(C1373R.id.list_view_head_refresh);
        if (this.f12221e) {
            this.f12218b.setImageResource(C1373R.drawable.frame_refresh_white_anim);
        } else {
            this.f12218b.setImageResource(C1373R.drawable.frame_refresh);
        }
        this.f12220d = (AnimationDrawable) this.f12218b.getDrawable();
    }

    public void setRefreshAnim(int i) {
        this.f12218b.setImageResource(i);
        this.f12220d = (AnimationDrawable) this.f12218b.getDrawable();
    }

    public void setViewBackgroundColor(int i) {
        this.f12217a.setBackgroundColor(getResources().getColor(i));
    }

    public void setState(int i) {
        if (i != this.f12219c) {
            if (i == 2) {
                this.f12220d.start();
            } else {
                this.f12220d.selectDrawable(0);
                this.f12220d.stop();
            }
            switch (i) {
            }
            this.f12219c = i;
        }
    }

    public void setVisiableHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f12217a.getLayoutParams();
        layoutParams.height = i;
        this.f12217a.setLayoutParams(layoutParams);
    }

    public int getVisiableHeight() {
        return this.f12217a.getHeight();
    }
}
