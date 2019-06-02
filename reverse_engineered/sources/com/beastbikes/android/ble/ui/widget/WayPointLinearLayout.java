package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.ble.ui.widget.C1765a.C1762a;
import com.beastbikes.android.ble.ui.widget.C1767c.C1763a;
import java.util.ArrayList;

public class WayPointLinearLayout extends LinearLayout implements C1762a, C1763a {
    /* renamed from: a */
    private C1765a f8065a;
    /* renamed from: b */
    private C1687a f8066b;
    /* renamed from: c */
    private ArrayList<C1767c> f8067c;
    /* renamed from: d */
    private ArrayList<C1766b> f8068d;

    /* renamed from: com.beastbikes.android.ble.ui.widget.WayPointLinearLayout$a */
    public interface C1687a {
        /* renamed from: a */
        void mo3199a();

        /* renamed from: a */
        void mo3202a(NavigationLocation navigationLocation, int i);

        /* renamed from: c */
        void mo3205c(int i);
    }

    public WayPointLinearLayout(Context context) {
        super(context);
        m9378a(context);
    }

    public WayPointLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m9378a(context);
    }

    public WayPointLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9378a(context);
    }

    /* renamed from: a */
    public void mo3238a(View view) {
        if (this.f8066b != null) {
            this.f8066b.mo3199a();
        }
    }

    /* renamed from: a */
    public void mo3239a(View view, int i) {
        if (this.f8066b != null) {
            this.f8066b.mo3205c(i);
        }
    }

    /* renamed from: a */
    public void mo3240a(ViewGroup viewGroup, View view, int i) {
        if (this.f8066b != null) {
            this.f8066b.mo3202a((NavigationLocation) viewGroup.getTag(), i);
        }
        m9380a(i);
    }

    /* renamed from: a */
    private void m9378a(Context context) {
        setOrientation(1);
        this.f8065a = new C1765a(context);
        addView(this.f8065a);
        this.f8065a.setOnClickAddListener(this);
    }

    /* renamed from: a */
    public void m9379a() {
        if (this.f8067c == null) {
            this.f8067c = new ArrayList();
        }
        if (this.f8068d == null) {
            this.f8068d = new ArrayList();
        }
        int size = this.f8067c.size();
        View c1766b = new C1766b(getContext());
        View c1767c = new C1767c(getContext(), size);
        removeView(this.f8065a);
        addView(c1766b);
        addView(c1767c);
        addView(this.f8065a);
        this.f8067c.add(c1767c);
        this.f8068d.add(c1766b);
        if (this.f8067c.size() >= 3) {
            removeView(this.f8065a);
            View c1766b2 = new C1766b(getContext());
            addView(c1766b2);
            this.f8068d.add(c1766b2);
        }
        c1767c.setOnWayPointClickListener(this);
    }

    /* renamed from: a */
    public void m9380a(int i) {
        if (i >= 0 && this.f8067c != null && this.f8067c.size() > 0 && this.f8068d != null && this.f8068d.size() > 0) {
            int size = this.f8067c.size();
            if (i < size) {
                removeView((View) this.f8067c.remove(i));
                removeView((View) this.f8068d.remove(i));
            }
            int size2 = this.f8067c.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((C1767c) this.f8067c.get(i2)).setPosition(i2);
            }
            if (size2 < 3 && size >= 3) {
                removeView((View) this.f8068d.remove(this.f8068d.size() - 1));
                addView(this.f8065a);
            }
        }
    }

    /* renamed from: a */
    public void m9381a(int i, NavigationLocation navigationLocation) {
        if (this.f8067c != null && i < this.f8067c.size()) {
            ((C1767c) this.f8067c.get(i)).setWayPointName(navigationLocation);
        }
    }

    public void setOnClickWayPointListener(C1687a c1687a) {
        if (c1687a == null) {
            throw new IllegalArgumentException("OnClickWayPointListener should not be null");
        }
        this.f8066b = c1687a;
    }
}
