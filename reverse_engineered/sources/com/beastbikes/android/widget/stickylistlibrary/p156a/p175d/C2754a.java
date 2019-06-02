package com.beastbikes.android.widget.stickylistlibrary.p156a.p175d;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

/* compiled from: LinearLayoutOrientationProvider */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.d.a */
public class C2754a implements C2753b {
    /* renamed from: a */
    public int mo3555a(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        m13641a(layoutManager);
        return ((LinearLayoutManager) layoutManager).getOrientation();
    }

    /* renamed from: b */
    public boolean mo3556b(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        m13641a(layoutManager);
        return ((LinearLayoutManager) layoutManager).getReverseLayout();
    }

    /* renamed from: a */
    private void m13641a(LayoutManager layoutManager) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            throw new IllegalStateException("StickyListHeadersDecoration can only be used with a LinearLayoutManager.");
        }
    }
}
