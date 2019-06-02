package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import java.util.LinkedList;
import java.util.List;

/* compiled from: AdapterWrapper */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.a */
class C2782a extends BaseAdapter implements C2108d {
    /* renamed from: a */
    C2108d f12979a;
    /* renamed from: b */
    private final List<View> f12980b = new LinkedList();
    /* renamed from: c */
    private final Context f12981c;
    /* renamed from: d */
    private Drawable f12982d;
    /* renamed from: e */
    private int f12983e;
    /* renamed from: f */
    private C2778a f12984f;
    /* renamed from: g */
    private DataSetObserver f12985g = new C27801(this);

    /* compiled from: AdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.a$a */
    interface C2778a {
        /* renamed from: a */
        void mo3560a(View view, int i, long j);
    }

    /* compiled from: AdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.a$1 */
    class C27801 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ C2782a f12976a;

        C27801(C2782a c2782a) {
            this.f12976a = c2782a;
        }

        public void onInvalidated() {
            this.f12976a.f12980b.clear();
            super.notifyDataSetInvalidated();
        }

        public void onChanged() {
            super.notifyDataSetChanged();
        }
    }

    public /* synthetic */ View getView(int i, View view, ViewGroup viewGroup) {
        return m13715b(i, view, viewGroup);
    }

    C2782a(Context context, C2108d c2108d) {
        this.f12981c = context;
        this.f12979a = c2108d;
        c2108d.registerDataSetObserver(this.f12985g);
    }

    /* renamed from: a */
    void m13712a(Drawable drawable, int i) {
        this.f12982d = drawable;
        this.f12983e = i;
        notifyDataSetChanged();
    }

    public boolean areAllItemsEnabled() {
        return this.f12979a.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.f12979a.isEnabled(i);
    }

    public int getCount() {
        return this.f12979a.getCount();
    }

    public Object getItem(int i) {
        return this.f12979a.getItem(i);
    }

    public long getItemId(int i) {
        return this.f12979a.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.f12979a.hasStableIds();
    }

    public int getItemViewType(int i) {
        return this.f12979a.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.f12979a.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.f12979a.isEmpty();
    }

    /* renamed from: a */
    private void m13706a(C2783e c2783e) {
        View view = c2783e.f12989d;
        if (view != null) {
            view.setVisibility(0);
            this.f12980b.add(view);
        }
    }

    /* renamed from: a */
    private View m13704a(C2783e c2783e, final int i) {
        View a = this.f12979a.mo3410a(i, c2783e.f12989d == null ? m13703a() : c2783e.f12989d, c2783e);
        if (a == null) {
            throw new NullPointerException("Header view must not be null.");
        }
        a.setClickable(true);
        a.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2782a f12978b;

            public void onClick(View view) {
                if (this.f12978b.f12984f != null) {
                    this.f12978b.f12984f.mo3560a(view, i, this.f12978b.f12979a.mo3411b(i));
                }
            }
        });
        return a;
    }

    /* renamed from: a */
    private View m13703a() {
        if (this.f12980b.size() > 0) {
            return (View) this.f12980b.remove(0);
        }
        return null;
    }

    /* renamed from: a */
    private boolean m13707a(int i) {
        return i != 0 && this.f12979a.mo3411b(i) == this.f12979a.mo3411b(i - 1);
    }

    /* renamed from: b */
    public C2783e m13715b(int i, View view, ViewGroup viewGroup) {
        C2783e c2783e = view == null ? new C2783e(this.f12981c) : (C2783e) view;
        View view2 = this.f12979a.getView(i, c2783e.f12986a, viewGroup);
        View view3 = null;
        if (m13707a(i)) {
            m13706a(c2783e);
        } else {
            view3 = m13704a(c2783e, i);
        }
        if ((view2 instanceof Checkable) && !(c2783e instanceof C2784b)) {
            view = new C2784b(this.f12981c);
        } else if (!(view2 instanceof Checkable) && (c2783e instanceof C2784b)) {
            view = new C2783e(this.f12981c);
        }
        view.m13716a(view2, view3, this.f12982d, this.f12983e);
        return view;
    }

    /* renamed from: a */
    public void m13713a(C2778a c2778a) {
        this.f12984f = c2778a;
    }

    public boolean equals(Object obj) {
        return this.f12979a.equals(obj);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return ((BaseAdapter) this.f12979a).getDropDownView(i, view, viewGroup);
    }

    public int hashCode() {
        return this.f12979a.hashCode();
    }

    public void notifyDataSetChanged() {
        ((BaseAdapter) this.f12979a).notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated() {
        ((BaseAdapter) this.f12979a).notifyDataSetInvalidated();
    }

    public String toString() {
        return this.f12979a.toString();
    }

    /* renamed from: a */
    public View mo3410a(int i, View view, ViewGroup viewGroup) {
        return this.f12979a.mo3410a(i, view, viewGroup);
    }

    /* renamed from: b */
    public long mo3411b(int i) {
        return this.f12979a.mo3411b(i);
    }
}
