package com.beastbikes.android.home.p106b;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.home.C1835a.C1830b;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;

/* compiled from: TabViewHolder */
/* renamed from: com.beastbikes.android.home.b.c */
public class C1844c implements OnClickListener, OnTouchListener {
    /* renamed from: a */
    private View f8307a;
    /* renamed from: b */
    private ImageView f8308b;
    /* renamed from: c */
    private TextView f8309c;
    /* renamed from: d */
    private TextView f8310d;
    /* renamed from: e */
    private String f8311e;
    /* renamed from: f */
    private boolean f8312f = false;
    /* renamed from: g */
    private GestureDetector f8313g;
    /* renamed from: h */
    private C1830b f8314h;

    /* compiled from: TabViewHolder */
    /* renamed from: com.beastbikes.android.home.b.c$a */
    public interface C1843a {
        /* renamed from: a */
        void m9623a();
    }

    public C1844c(int i, int i2, String str, Context context, ViewGroup viewGroup, int i3, final C1843a c1843a) {
        if (this.f8307a == null) {
            this.f8307a = LayoutInflater.from(context).inflate(C1373R.layout.tab_item, null);
        }
        this.f8308b = (ImageView) this.f8307a.findViewById(C1373R.id.tab_item_icon);
        this.f8309c = (TextView) this.f8307a.findViewById(C1373R.id.tab_item_title);
        this.f8310d = (TextView) this.f8307a.findViewById(C1373R.id.tab_item_dot);
        viewGroup.addView(this.f8307a);
        LayoutParams layoutParams = (LayoutParams) this.f8307a.getLayoutParams();
        layoutParams.width = DensityUtil.getWidth(context) / i3;
        this.f8307a.setLayoutParams(layoutParams);
        this.f8307a.setTag(str);
        this.f8311e = str;
        this.f8308b.setImageResource(i2);
        this.f8309c.setText(context.getString(i));
        this.f8307a.setOnClickListener(this);
        this.f8313g = new GestureDetector(context, new SimpleOnGestureListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1844c f8306b;

            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                if (c1843a != null) {
                    c1843a.m9623a();
                }
                return super.onDoubleTapEvent(motionEvent);
            }
        });
        this.f8307a.setOnTouchListener(this);
    }

    /* renamed from: a */
    public String m9624a() {
        return this.f8311e;
    }

    /* renamed from: a */
    public void m9626a(boolean z) {
        if (this.f8307a != null) {
            this.f8307a.setSelected(z);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f8313g.onTouchEvent(motionEvent);
    }

    public void onClick(View view) {
        if (view == this.f8307a && this.f8314h != null) {
            this.f8314h.mo3255a(m9624a());
        }
    }

    /* renamed from: a */
    public void m9625a(C1830b c1830b) {
        this.f8314h = c1830b;
    }
}
