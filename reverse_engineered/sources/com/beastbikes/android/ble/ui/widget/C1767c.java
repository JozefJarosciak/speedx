package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.NavigationLocation;

/* compiled from: WayPointView */
/* renamed from: com.beastbikes.android.ble.ui.widget.c */
public class C1767c extends RelativeLayout implements OnClickListener {
    /* renamed from: a */
    private TextView f8072a;
    /* renamed from: b */
    private C1763a f8073b;
    /* renamed from: c */
    private int f8074c;

    /* compiled from: WayPointView */
    /* renamed from: com.beastbikes.android.ble.ui.widget.c$a */
    interface C1763a {
        /* renamed from: a */
        void mo3239a(View view, int i);

        /* renamed from: a */
        void mo3240a(ViewGroup viewGroup, View view, int i);
    }

    public C1767c(Context context, int i) {
        super(context);
        this.f8074c = i;
        m9388a(context);
    }

    public C1767c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9388a(context);
    }

    public C1767c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9388a(context);
    }

    /* renamed from: a */
    private void m9388a(Context context) {
        C1767c.inflate(context, C1373R.layout.layout_way_point, this);
        this.f8072a = (TextView) findViewById(C1373R.id.tv_navigation_way_point);
        ImageView imageView = (ImageView) findViewById(C1373R.id.img_navigation_way_point_delete);
        this.f8072a.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_navigation_way_point:
                if (this.f8073b != null) {
                    this.f8073b.mo3239a(view, this.f8074c);
                    return;
                }
                return;
            case C1373R.id.img_navigation_way_point_delete:
                if (this.f8073b != null) {
                    this.f8073b.mo3240a(this, view, this.f8074c);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setOnWayPointClickListener(C1763a c1763a) {
        if (c1763a == null) {
            throw new IllegalArgumentException("OnWayPointClickListener should not be null");
        }
        this.f8073b = c1763a;
    }

    public void setPosition(int i) {
        this.f8074c = i;
    }

    public int getPosition() {
        return this.f8074c;
    }

    public void setWayPointName(NavigationLocation navigationLocation) {
        if (navigationLocation != null && !TextUtils.isEmpty(navigationLocation.getName())) {
            this.f8072a.setText(navigationLocation.getName());
            this.f8072a.setTextColor(Color.parseColor("#111111"));
            setTag(navigationLocation);
        }
    }
}
