package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

/* compiled from: SectionIndexerAdapterWrapper */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.c */
class C2785c extends C2782a implements SectionIndexer {
    /* renamed from: b */
    SectionIndexer f12991b;

    C2785c(Context context, C2108d c2108d) {
        super(context, c2108d);
        this.f12991b = (SectionIndexer) c2108d;
    }

    public int getPositionForSection(int i) {
        return this.f12991b.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.f12991b.getSectionForPosition(i);
    }

    public Object[] getSections() {
        return this.f12991b.getSections();
    }
}
