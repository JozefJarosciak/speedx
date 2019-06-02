package com.beastbikes.android.widget.p081b;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.stickylistlibrary.p156a.C2470c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RecyclerViewAdapter */
/* renamed from: com.beastbikes.android.widget.b.h */
public abstract class C2471h<T extends C2409c> extends Adapter<C2613i> implements C2470c<C2613i> {
    /* renamed from: a */
    public boolean f11564a;
    /* renamed from: b */
    public C2611f f11565b;
    /* renamed from: c */
    public List<T> f11566c = new ArrayList();
    /* renamed from: d */
    private boolean f11567d;
    /* renamed from: e */
    private boolean f11568e;

    /* renamed from: a */
    public abstract C2613i mo3497a(ViewGroup viewGroup);

    /* renamed from: a */
    public abstract void mo3498a(C2613i c2613i, int i);

    /* renamed from: b */
    public abstract C2613i mo3499b(ViewGroup viewGroup);

    /* renamed from: b */
    public abstract void mo3500b(C2613i c2613i, int i);

    /* renamed from: c */
    public abstract C2613i mo3501c(ViewGroup viewGroup);

    /* renamed from: c */
    public abstract void mo3502c(C2613i c2613i, int i);

    /* renamed from: d */
    public abstract C2613i mo3503d(ViewGroup viewGroup);

    /* renamed from: d */
    public abstract void mo3504d(C2613i c2613i, int i);

    /* renamed from: a */
    public /* synthetic */ void mo3495a(ViewHolder viewHolder, int i) {
        m12408f((C2613i) viewHolder, i);
    }

    /* renamed from: f */
    public /* synthetic */ ViewHolder mo3496f(ViewGroup viewGroup) {
        return m12405e(viewGroup);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m12406e((C2613i) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m12390a(viewGroup, i);
    }

    /* renamed from: a */
    public C2613i m12390a(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return mo3499b(viewGroup);
        }
        if (i == 2) {
            C2613i c = mo3501c(viewGroup);
            if (c == null) {
                return new C1456a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_view_load_more, viewGroup, false));
            }
            return c;
        } else if (i == 3) {
            return new C1456a(new View(viewGroup.getContext()));
        } else {
            return mo3497a(viewGroup);
        }
    }

    /* renamed from: e */
    public void m12406e(C2613i c2613i, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            mo3498a(c2613i, i);
        }
        if (itemViewType == 2) {
            mo3500b(c2613i, i);
        }
        if (itemViewType == 0) {
            mo3504d(c2613i, i);
        }
    }

    /* renamed from: a */
    public int mo3493a() {
        return this.f11566c.size();
    }

    public int getItemCount() {
        int i = 1;
        if (this.f11566c != null) {
            int size = (this.f11567d ? 1 : 0) + this.f11566c.size();
            if (!this.f11568e) {
                i = 0;
            }
            return i + size;
        } else if (this.f11567d) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getItemViewType(int i) {
        if (this.f11566c == null || this.f11566c.isEmpty()) {
            return 3;
        }
        if (i == 0 && this.f11567d) {
            return 1;
        }
        if (i == getItemCount() - 1 && this.f11568e) {
            return 2;
        }
        C2409c c2409c = (C2409c) this.f11566c.get(i);
        if (c2409c != null) {
            return c2409c.getItemType();
        }
        return super.getItemViewType(i);
    }

    /* renamed from: e */
    public C2613i m12405e(ViewGroup viewGroup) {
        return mo3503d(viewGroup);
    }

    /* renamed from: f */
    public void m12408f(C2613i c2613i, int i) {
        mo3502c(c2613i, i);
    }

    /* renamed from: a */
    public long mo3494a(int i) {
        if (this.f11566c == null || i >= this.f11566c.size()) {
            return 0;
        }
        C2409c c2409c = (C2409c) this.f11566c.get(i);
        if (c2409c == null) {
            return 0;
        }
        return c2409c.getHeaderId();
    }

    /* renamed from: a */
    public synchronized void m12395a(List<T> list) {
        if (list != null) {
            this.f11566c.clear();
            this.f11566c.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m12393a(C2611f c2611f) {
        this.f11565b = c2611f;
    }

    /* renamed from: a */
    public void m12392a(T t) {
        if (this.f11566c.contains(t)) {
            this.f11566c.remove(t);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m12396a(boolean z) {
        this.f11567d = z;
    }

    /* renamed from: b */
    public void m12399b(boolean z) {
        this.f11568e = z;
    }

    /* renamed from: c */
    public void m12402c(boolean z) {
        this.f11564a = z;
    }
}
