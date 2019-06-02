package com.beastbikes.android.modules.cycling.activity.ui.record.p123c;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/* compiled from: GridSpaceItemDecoration */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.c.a */
public class C1978a extends ItemDecoration {
    /* renamed from: a */
    private int f8892a;
    /* renamed from: b */
    private Drawable f8893b;

    public C1978a(int i, int i2) {
        this.f8892a = i;
        this.f8893b = new ColorDrawable(i2);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        m10191a(canvas, recyclerView);
        m10192b(canvas, recyclerView);
    }

    /* renamed from: a */
    private int m10188a(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return layoutManager instanceof StaggeredGridLayoutManager ? ((StaggeredGridLayoutManager) layoutManager).getSpanCount() : -1;
    }

    /* renamed from: a */
    private boolean m10189a(RecyclerView recyclerView, int i, int i2, int i3) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((i + 1) % i2 == 0) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if ((i + 1) % i2 == 0) {
                    return true;
                }
            } else if (i >= i3 - (i3 % i2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m10190b(RecyclerView recyclerView, int i, int i2, int i3) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (i >= i3 - (i3 % i2)) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if (i >= i3 - (i3 % i2)) {
                    return true;
                }
            } else if ((i + 1) % i2 == 0) {
                return true;
            }
        }
        return false;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int a = m10188a(recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (m10190b(recyclerView, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), a, itemCount)) {
            if (m10189a(recyclerView, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), a, itemCount)) {
                rect.set(this.f8892a / 2, this.f8892a / 2, 0, 0);
            } else {
                rect.set(0, 0, this.f8892a, 0);
            }
        } else if (m10189a(recyclerView, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), a, itemCount)) {
            rect.set(0, 0, 0, this.f8892a);
        } else {
            rect.set(0, 0, this.f8892a, this.f8892a);
        }
    }

    /* renamed from: a */
    public void m10191a(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - layoutParams.leftMargin;
            int right = (childAt.getRight() + layoutParams.rightMargin) + this.f8892a;
            int bottom = layoutParams.bottomMargin + childAt.getBottom();
            this.f8893b.setBounds(left, bottom, right, this.f8892a + bottom);
            this.f8893b.draw(canvas);
        }
    }

    /* renamed from: b */
    public void m10192b(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            int right = layoutParams.rightMargin + childAt.getRight();
            this.f8893b.setBounds(right, top, this.f8892a + right, bottom);
            this.f8893b.draw(canvas);
        }
    }
}
