package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.graphics.Canvas;
import android.os.Build.VERSION;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.C2786f.C2779a;

class StickyListHeadersListView$g implements C2779a {
    /* renamed from: a */
    final /* synthetic */ StickyListHeadersListView f12975a;

    private StickyListHeadersListView$g(StickyListHeadersListView stickyListHeadersListView) {
        this.f12975a = stickyListHeadersListView;
    }

    /* renamed from: a */
    public void mo3561a(Canvas canvas) {
        if (VERSION.SDK_INT < 8) {
            StickyListHeadersListView.a(this.f12975a, StickyListHeadersListView.g(this.f12975a).m13721a());
        }
        if (StickyListHeadersListView.a(this.f12975a) == null) {
            return;
        }
        if (StickyListHeadersListView.h(this.f12975a)) {
            canvas.save();
            canvas.clipRect(0, StickyListHeadersListView.i(this.f12975a), this.f12975a.getRight(), this.f12975a.getBottom());
            StickyListHeadersListView.a(this.f12975a, canvas, StickyListHeadersListView.a(this.f12975a), 0);
            canvas.restore();
            return;
        }
        StickyListHeadersListView.b(this.f12975a, canvas, StickyListHeadersListView.a(this.f12975a), 0);
    }
}
