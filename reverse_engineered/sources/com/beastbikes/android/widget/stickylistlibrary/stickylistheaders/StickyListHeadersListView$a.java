package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.database.DataSetObserver;

class StickyListHeadersListView$a extends DataSetObserver {
    /* renamed from: a */
    final /* synthetic */ StickyListHeadersListView f12972a;

    private StickyListHeadersListView$a(StickyListHeadersListView stickyListHeadersListView) {
        this.f12972a = stickyListHeadersListView;
    }

    public void onChanged() {
        StickyListHeadersListView.e(this.f12972a);
    }

    public void onInvalidated() {
        StickyListHeadersListView.e(this.f12972a);
    }
}
