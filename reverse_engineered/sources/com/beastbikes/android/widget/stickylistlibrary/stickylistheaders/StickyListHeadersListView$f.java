package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class StickyListHeadersListView$f implements OnScrollListener {
    /* renamed from: a */
    final /* synthetic */ StickyListHeadersListView f12974a;

    private StickyListHeadersListView$f(StickyListHeadersListView stickyListHeadersListView) {
        this.f12974a = stickyListHeadersListView;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (StickyListHeadersListView.f(this.f12974a) != null) {
            StickyListHeadersListView.f(this.f12974a).onScroll(absListView, i, i2, i3);
        }
        StickyListHeadersListView.a(this.f12974a, StickyListHeadersListView.g(this.f12974a).m13721a());
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (StickyListHeadersListView.f(this.f12974a) != null) {
            StickyListHeadersListView.f(this.f12974a).onScrollStateChanged(absListView, i);
        }
    }
}
