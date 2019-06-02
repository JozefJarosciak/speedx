package com.beastbikes.android.widget.stickylistlibrary.stickygridheaders;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StickyGridHeadersSimpleAdapterWrapper */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.e */
public class C2777e extends BaseAdapter implements C2766a {
    /* renamed from: a */
    private C2095d f12966a;
    /* renamed from: b */
    private C2776b[] f12967b;

    /* compiled from: StickyGridHeadersSimpleAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.e$a */
    private final class C2775a extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ C2777e f12962a;

        private C2775a(C2777e c2777e) {
            this.f12962a = c2777e;
        }

        public void onChanged() {
            this.f12962a.f12967b = this.f12962a.m13695a(this.f12962a.f12966a);
            this.f12962a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f12962a.f12967b = this.f12962a.m13695a(this.f12962a.f12966a);
            this.f12962a.notifyDataSetInvalidated();
        }
    }

    /* compiled from: StickyGridHeadersSimpleAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.e$b */
    private class C2776b {
        /* renamed from: a */
        final /* synthetic */ C2777e f12963a;
        /* renamed from: b */
        private int f12964b = 0;
        /* renamed from: c */
        private int f12965c;

        public C2776b(C2777e c2777e, int i) {
            this.f12963a = c2777e;
            this.f12965c = i;
        }

        /* renamed from: a */
        public int m13687a() {
            return this.f12964b;
        }

        /* renamed from: b */
        public int m13688b() {
            return this.f12965c;
        }

        /* renamed from: c */
        public void m13689c() {
            this.f12964b++;
        }
    }

    public C2777e(C2095d c2095d) {
        this.f12966a = c2095d;
        c2095d.registerDataSetObserver(new C2775a());
        this.f12967b = m13695a(c2095d);
    }

    public int getCount() {
        return this.f12966a.getCount();
    }

    /* renamed from: a */
    public int mo3558a(int i) {
        return this.f12967b[i].m13687a();
    }

    /* renamed from: a */
    public View mo3559a(int i, View view, ViewGroup viewGroup) {
        if (i > this.f12967b.length) {
            return null;
        }
        return this.f12966a.mo3399a(this.f12967b[i].m13688b(), view, viewGroup);
    }

    public Object getItem(int i) {
        return this.f12966a.getItem(i);
    }

    public long getItemId(int i) {
        return this.f12966a.getItemId(i);
    }

    public int getItemViewType(int i) {
        return this.f12966a.getItemViewType(i);
    }

    /* renamed from: a */
    public int mo3557a() {
        return this.f12967b.length;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.f12966a.getView(i, view, viewGroup);
    }

    public int getViewTypeCount() {
        return this.f12966a.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.f12966a.hasStableIds();
    }

    /* renamed from: a */
    protected C2776b[] m13695a(C2095d c2095d) {
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (int i = 0; i < c2095d.getCount(); i++) {
            Object b = c2095d.mo3400b(i);
            C2776b c2776b = (C2776b) hashMap.get(b);
            if (c2776b == null) {
                c2776b = new C2776b(this, i);
                arrayList.add(c2776b);
            }
            c2776b.m13689c();
            hashMap.put(b, c2776b);
        }
        return (C2776b[]) arrayList.toArray(new C2776b[arrayList.size()]);
    }
}
