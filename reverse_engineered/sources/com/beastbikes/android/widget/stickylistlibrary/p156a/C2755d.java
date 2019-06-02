package com.beastbikes.android.widget.stickylistlibrary.p156a;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.SparseArray;
import android.view.View;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p172a.C2747a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p172a.C2748b;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p173b.C2750a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p174c.C2752a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p175d.C2753b;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p175d.C2754a;

/* compiled from: StickyRecyclerHeadersDecoration */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.d */
public class C2755d extends ItemDecoration {
    /* renamed from: a */
    private final C2470c f12883a;
    /* renamed from: b */
    private final C2751b f12884b;
    /* renamed from: c */
    private final SparseArray<Rect> f12885c;
    /* renamed from: d */
    private final C2747a f12886d;
    /* renamed from: e */
    private final C2753b f12887e;
    /* renamed from: f */
    private final C2749a f12888f;
    /* renamed from: g */
    private final C2752a f12889g;
    /* renamed from: h */
    private final C2750a f12890h;
    /* renamed from: i */
    private final Rect f12891i;

    public C2755d(C2470c c2470c) {
        this(c2470c, new C2754a(), new C2750a(), null);
    }

    private C2755d(C2470c c2470c, C2753b c2753b, C2750a c2750a, C2751b c2751b) {
        this(c2470c, c2753b, c2750a, new C2752a(c2753b), new C2748b(c2470c, c2753b), c2751b);
    }

    private C2755d(C2470c c2470c, C2753b c2753b, C2750a c2750a, C2752a c2752a, C2747a c2747a, C2751b c2751b) {
        this(c2470c, c2752a, c2753b, c2750a, c2747a, new C2749a(c2470c, c2747a, c2753b, c2750a), c2751b);
    }

    private C2755d(C2470c c2470c, C2752a c2752a, C2753b c2753b, C2750a c2750a, C2747a c2747a, C2749a c2749a, C2751b c2751b) {
        this.f12885c = new SparseArray();
        this.f12891i = new Rect();
        this.f12883a = c2470c;
        this.f12886d = c2747a;
        this.f12887e = c2753b;
        this.f12889g = c2752a;
        this.f12890h = c2750a;
        this.f12888f = c2749a;
        this.f12884b = c2751b;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition != -1 && this.f12888f.m13633a(childAdapterPosition, this.f12887e.mo3556b(recyclerView))) {
            m13644a(rect, m13645a(recyclerView, childAdapterPosition), this.f12887e.mo3555a(recyclerView));
        }
    }

    /* renamed from: a */
    private void m13644a(Rect rect, View view, int i) {
        this.f12890h.m13636a(this.f12891i, view);
        if (i == 1) {
            rect.top = (view.getHeight() + this.f12891i.top) + this.f12891i.bottom;
        } else {
            rect.left = (view.getWidth() + this.f12891i.left) + this.f12891i.right;
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        super.onDrawOver(canvas, recyclerView, state);
        int childCount = recyclerView.getChildCount();
        if (childCount > 0 && this.f12883a.mo3493a() > 0) {
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
                if (childAdapterPosition != -1) {
                    boolean a = this.f12888f.m13634a(childAt, this.f12887e.mo3555a(recyclerView), childAdapterPosition);
                    if (a || this.f12888f.m13633a(childAdapterPosition, this.f12887e.mo3556b(recyclerView))) {
                        Rect rect;
                        View a2 = this.f12886d.mo3554a(recyclerView, childAdapterPosition);
                        Rect rect2 = (Rect) this.f12885c.get(childAdapterPosition);
                        if (rect2 == null) {
                            rect = new Rect();
                            this.f12885c.put(childAdapterPosition, rect);
                        } else {
                            rect = rect2;
                        }
                        this.f12888f.m13632a(rect, recyclerView, a2, childAt, a);
                        this.f12889g.m13638a(recyclerView, canvas, a2, rect);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public View m13645a(RecyclerView recyclerView, int i) {
        return this.f12886d.mo3554a(recyclerView, i);
    }
}
