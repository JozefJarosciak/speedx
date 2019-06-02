package com.beastbikes.android.widget.stickylistlibrary.p156a.p172a;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.beastbikes.android.widget.stickylistlibrary.p156a.C2470c;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p175d.C2753b;
import com.google.common.primitives.Ints;

/* compiled from: HeaderViewCache */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.a.b */
public class C2748b implements C2747a {
    /* renamed from: a */
    private final C2470c f12870a;
    /* renamed from: b */
    private final LongSparseArray<View> f12871b = new LongSparseArray();
    /* renamed from: c */
    private final LongSparseArray<ViewHolder> f12872c = new LongSparseArray();
    /* renamed from: d */
    private final C2753b f12873d;

    public C2748b(C2470c c2470c, C2753b c2753b) {
        this.f12870a = c2470c;
        this.f12873d = c2753b;
    }

    /* renamed from: a */
    public View mo3554a(RecyclerView recyclerView, int i) {
        long a = this.f12870a.mo3494a(i);
        if (a == 0) {
            return new View(recyclerView.getContext());
        }
        View view = (View) this.f12871b.get(a);
        ViewHolder viewHolder = (ViewHolder) this.f12872c.get(a);
        if (viewHolder != null) {
            this.f12870a.mo3495a(viewHolder, i);
        }
        if (view != null) {
            return view;
        }
        int makeMeasureSpec;
        int makeMeasureSpec2;
        ViewHolder f = this.f12870a.mo3496f(recyclerView);
        this.f12870a.mo3495a(f, i);
        View view2 = f.itemView;
        if (view2.getLayoutParams() == null) {
            view2.setLayoutParams(new LayoutParams(-2, -2));
        }
        if (this.f12873d.mo3555a(recyclerView) == 1) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), Ints.MAX_POWER_OF_TWO);
            makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 0);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 0);
            makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), Ints.MAX_POWER_OF_TWO);
        }
        view2.measure(ViewGroup.getChildMeasureSpec(makeMeasureSpec, recyclerView.getPaddingLeft() + recyclerView.getPaddingRight(), view2.getLayoutParams().width), ViewGroup.getChildMeasureSpec(makeMeasureSpec2, recyclerView.getPaddingTop() + recyclerView.getPaddingBottom(), view2.getLayoutParams().height));
        view2.layout(0, 0, view2.getMeasuredWidth(), view2.getMeasuredHeight());
        this.f12871b.put(a, view2);
        this.f12872c.put(a, f);
        return view2;
    }
}
