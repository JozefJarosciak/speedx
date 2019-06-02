package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class StickyListHeadersListView$3 implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ OnTouchListener f12970a;
    /* renamed from: b */
    final /* synthetic */ StickyListHeadersListView f12971b;

    StickyListHeadersListView$3(StickyListHeadersListView stickyListHeadersListView, OnTouchListener onTouchListener) {
        this.f12971b = stickyListHeadersListView;
        this.f12970a = onTouchListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f12970a.onTouch(this.f12971b, motionEvent);
    }
}
