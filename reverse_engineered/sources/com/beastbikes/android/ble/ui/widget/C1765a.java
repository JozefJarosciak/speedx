package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;

/* compiled from: NavigationAddWayPoint */
/* renamed from: com.beastbikes.android.ble.ui.widget.a */
public class C1765a extends LinearLayout {
    /* renamed from: a */
    private ImageView f8070a;
    /* renamed from: b */
    private C1762a f8071b;

    /* compiled from: NavigationAddWayPoint */
    /* renamed from: com.beastbikes.android.ble.ui.widget.a$a */
    interface C1762a {
        /* renamed from: a */
        void mo3238a(View view);
    }

    /* compiled from: NavigationAddWayPoint */
    /* renamed from: com.beastbikes.android.ble.ui.widget.a$1 */
    class C17641 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1765a f8069a;

        C17641(C1765a c1765a) {
            this.f8069a = c1765a;
        }

        public void onClick(View view) {
            if (this.f8069a.f8071b != null) {
                this.f8069a.f8071b.mo3238a(view);
            }
        }
    }

    public C1765a(Context context) {
        super(context);
        m9386a(context);
    }

    public C1765a(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m9386a(context);
    }

    public C1765a(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9386a(context);
    }

    /* renamed from: a */
    private void m9386a(Context context) {
        C1765a.inflate(context, C1373R.layout.layout_navigation_add_way_point, this);
        this.f8070a = (ImageView) findViewById(C1373R.id.img_location_add_way_point);
        this.f8070a.setOnClickListener(new C17641(this));
    }

    public void setOnClickAddListener(C1762a c1762a) {
        if (c1762a == null) {
            throw new IllegalArgumentException("OnClickAddListener should not be null");
        }
        this.f8071b = c1762a;
    }
}
