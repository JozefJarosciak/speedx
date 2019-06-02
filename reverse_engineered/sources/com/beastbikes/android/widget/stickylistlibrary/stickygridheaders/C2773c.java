package com.beastbikes.android.widget.stickylistlibrary.stickygridheaders;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/* compiled from: StickyGridHeadersListAdapterWrapper */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.c */
public class C2773c extends BaseAdapter implements C2766a {
    /* renamed from: a */
    private DataSetObserver f12960a = new C27721(this);
    /* renamed from: b */
    private ListAdapter f12961b;

    /* compiled from: StickyGridHeadersListAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.c$1 */
    class C27721 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ C2773c f12959a;

        C27721(C2773c c2773c) {
            this.f12959a = c2773c;
        }

        public void onChanged() {
            this.f12959a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f12959a.notifyDataSetInvalidated();
        }
    }

    public C2773c(ListAdapter listAdapter) {
        this.f12961b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f12960a);
        }
    }

    public int getCount() {
        if (this.f12961b == null) {
            return 0;
        }
        return this.f12961b.getCount();
    }

    /* renamed from: a */
    public int mo3558a(int i) {
        return 0;
    }

    /* renamed from: a */
    public View mo3559a(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public Object getItem(int i) {
        if (this.f12961b == null) {
            return null;
        }
        return this.f12961b.getItem(i);
    }

    public long getItemId(int i) {
        return this.f12961b.getItemId(i);
    }

    public int getItemViewType(int i) {
        return this.f12961b.getItemViewType(i);
    }

    /* renamed from: a */
    public int mo3557a() {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.f12961b.getView(i, view, viewGroup);
    }

    public int getViewTypeCount() {
        return this.f12961b.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.f12961b.hasStableIds();
    }
}
