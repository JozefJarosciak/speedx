package com.beastbikes.android.widget.stickylistlibrary.p156a;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p172a.C2747a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p173b.C2750a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p175d.C2753b;

/* compiled from: HeaderPositionCalculator */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.a */
public class C2749a {
    /* renamed from: a */
    private final C2470c f12874a;
    /* renamed from: b */
    private final C2753b f12875b;
    /* renamed from: c */
    private final C2747a f12876c;
    /* renamed from: d */
    private final C2750a f12877d;
    /* renamed from: e */
    private final Rect f12878e = new Rect();
    /* renamed from: f */
    private final Rect f12879f = new Rect();

    public C2749a(C2470c c2470c, C2747a c2747a, C2753b c2753b, C2750a c2750a) {
        this.f12874a = c2470c;
        this.f12876c = c2747a;
        this.f12875b = c2753b;
        this.f12877d = c2750a;
    }

    /* renamed from: a */
    public boolean m13634a(View view, int i, int i2) {
        int top;
        int i3;
        this.f12877d.m13636a(this.f12878e, view);
        if (i == 1) {
            top = view.getTop();
            i3 = this.f12878e.top;
        } else {
            top = view.getLeft();
            i3 = this.f12878e.left;
        }
        if (top > i3 || this.f12874a.mo3494a(i2) < 0) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public boolean m13633a(int i, boolean z) {
        boolean z2 = true;
        if (m13627a(i)) {
            return false;
        }
        long a = this.f12874a.mo3494a(i);
        if (a < 0) {
            return false;
        }
        long j = -1;
        int i2 = (z ? 1 : -1) + i;
        if (!m13627a(i2)) {
            j = this.f12874a.mo3494a(i2);
        }
        if (z) {
            i2 = this.f12874a.mo3493a() - 1;
        } else {
            i2 = 0;
        }
        if (i != i2 && a == r4) {
            z2 = false;
        }
        return z2;
    }

    /* renamed from: a */
    private boolean m13627a(int i) {
        return i < 0 || i >= this.f12874a.mo3493a();
    }

    /* renamed from: a */
    public void m13632a(Rect rect, RecyclerView recyclerView, View view, View view2, boolean z) {
        m13625a(rect, recyclerView, view, view2, this.f12875b.mo3555a(recyclerView));
        if (z && m13628a(recyclerView, view)) {
            View b = m13631b(recyclerView, view);
            View a = this.f12876c.mo3554a(recyclerView, recyclerView.getChildAdapterPosition(b));
            m13626a(recyclerView, this.f12875b.mo3555a(recyclerView), rect, view, b, a);
        }
    }

    /* renamed from: a */
    private void m13625a(Rect rect, RecyclerView recyclerView, View view, View view2, int i) {
        int i2;
        int i3 = 0;
        this.f12877d.m13636a(this.f12878e, view);
        LayoutParams layoutParams = view2.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
            i3 = marginLayoutParams.leftMargin;
            i2 = marginLayoutParams.topMargin;
        } else {
            i2 = 0;
        }
        if (i == 1) {
            i3 = (view2.getLeft() - i3) + this.f12878e.left;
            i2 = Math.max(((view2.getTop() - i2) - view.getHeight()) - this.f12878e.bottom, m13624a(recyclerView) + this.f12878e.top);
        } else {
            i2 = (view2.getTop() - i2) + this.f12878e.top;
            i3 = Math.max(((view2.getLeft() - i3) - view.getWidth()) - this.f12878e.right, m13630b(recyclerView) + this.f12878e.left);
        }
        rect.set(i3, i2, view.getWidth() + i3, view.getHeight() + i2);
    }

    /* renamed from: a */
    private boolean m13628a(RecyclerView recyclerView, View view) {
        View b = m13631b(recyclerView, view);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(b);
        if (childAdapterPosition == -1) {
            return false;
        }
        boolean b2 = this.f12875b.mo3556b(recyclerView);
        if (childAdapterPosition <= 0 || !m13633a(childAdapterPosition, b2)) {
            return false;
        }
        View a = this.f12876c.mo3554a(recyclerView, childAdapterPosition);
        this.f12877d.m13636a(this.f12878e, a);
        this.f12877d.m13636a(this.f12879f, view);
        if (this.f12875b.mo3555a(recyclerView) == 1) {
            if (((b.getTop() - this.f12878e.bottom) - a.getHeight()) - this.f12878e.top < ((recyclerView.getPaddingTop() + view.getBottom()) + this.f12879f.top) + this.f12879f.bottom) {
                return true;
            }
            return false;
        } else if (((b.getLeft() - this.f12878e.right) - a.getWidth()) - this.f12878e.left < ((recyclerView.getPaddingLeft() + view.getRight()) + this.f12879f.left) + this.f12879f.right) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    private void m13626a(RecyclerView recyclerView, int i, Rect rect, View view, View view2, View view3) {
        this.f12877d.m13636a(this.f12878e, view3);
        this.f12877d.m13636a(this.f12879f, view);
        int a;
        int top;
        if (i == 1) {
            a = (m13624a(recyclerView) + this.f12879f.top) + this.f12879f.bottom;
            top = ((((view2.getTop() - view3.getHeight()) - this.f12878e.bottom) - this.f12878e.top) - view.getHeight()) - a;
            if (top < a) {
                rect.top += top;
                return;
            }
            return;
        }
        a = (m13630b(recyclerView) + this.f12879f.left) + this.f12879f.right;
        top = ((((view2.getLeft() - view3.getWidth()) - this.f12878e.right) - this.f12878e.left) - view.getWidth()) - a;
        if (top < a) {
            rect.left += top;
        }
    }

    /* renamed from: b */
    private View m13631b(RecyclerView recyclerView, View view) {
        boolean b = this.f12875b.mo3556b(recyclerView);
        int i = b ? -1 : 1;
        int childCount = b ? recyclerView.getChildCount() - 1 : 0;
        while (childCount >= 0 && childCount <= recyclerView.getChildCount() - 1) {
            View childAt = recyclerView.getChildAt(childCount);
            if (!m13629a(recyclerView, childAt, view, this.f12875b.mo3555a(recyclerView))) {
                return childAt;
            }
            childCount += i;
        }
        return null;
    }

    /* renamed from: a */
    private boolean m13629a(RecyclerView recyclerView, View view, View view2, int i) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.f12877d.m13636a(this.f12878e, view2);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1 || this.f12876c.mo3554a(recyclerView, childAdapterPosition) != view2) {
            return false;
        }
        if (i == 1) {
            if (view.getTop() - layoutParams.topMargin >= ((m13624a(recyclerView) + view2.getBottom()) + this.f12878e.bottom) + this.f12878e.top) {
                return false;
            }
        } else if (view.getLeft() - layoutParams.leftMargin >= ((m13630b(recyclerView) + view2.getRight()) + this.f12878e.right) + this.f12878e.left) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private int m13624a(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager().getClipToPadding()) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    /* renamed from: b */
    private int m13630b(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager().getClipToPadding()) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }
}
