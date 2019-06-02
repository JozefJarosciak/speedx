package com.beastbikes.android.widget.slidingup_pannel;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/* compiled from: ScrollableViewHelper */
/* renamed from: com.beastbikes.android.widget.slidingup_pannel.a */
public class C2737a {
    /* renamed from: a */
    public int m13513a(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        if (view instanceof ScrollView) {
            if (z) {
                return view.getScrollY();
            }
            ScrollView scrollView = (ScrollView) view;
            return scrollView.getChildAt(0).getBottom() - (scrollView.getHeight() + scrollView.getScrollY());
        } else if ((view instanceof ListView) && ((ListView) view).getChildCount() > 0) {
            ListView listView = (ListView) view;
            if (listView.getAdapter() == null) {
                return 0;
            }
            View childAt;
            if (z) {
                childAt = listView.getChildAt(0);
                return (listView.getFirstVisiblePosition() * childAt.getHeight()) - childAt.getTop();
            }
            childAt = listView.getChildAt(listView.getChildCount() - 1);
            return (childAt.getBottom() + (((listView.getAdapter().getCount() - listView.getLastVisiblePosition()) - 1) * childAt.getHeight())) - listView.getBottom();
        } else if (!(view instanceof RecyclerView) || ((RecyclerView) view).getChildCount() <= 0) {
            return 0;
        } else {
            RecyclerView recyclerView = (RecyclerView) view;
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (recyclerView.getAdapter() == null) {
                return 0;
            }
            View childAt2;
            if (z) {
                childAt2 = recyclerView.getChildAt(0);
                return (recyclerView.getChildLayoutPosition(childAt2) * layoutManager.getDecoratedMeasuredHeight(childAt2)) - layoutManager.getDecoratedTop(childAt2);
            }
            childAt2 = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
            return (layoutManager.getDecoratedBottom(childAt2) + ((recyclerView.getAdapter().getItemCount() - 1) * layoutManager.getDecoratedMeasuredHeight(childAt2))) - recyclerView.getBottom();
        }
    }
}
