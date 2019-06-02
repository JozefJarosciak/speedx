package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshBase.OnLastItemVisibleListener;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements OnScrollListener {
    private int lastSavedFirstVisibleItem = -1;
    private OnLastItemVisibleListener onLastItemVisibleListener;
    private OnScrollListener onScrollListener;

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshAdapterViewBase$1 */
    class C28381 implements OnClickListener {
        C28381() {
        }

        public void onClick(View view) {
            if (PullToRefreshAdapterViewBase.this.refreshableView instanceof ListView) {
                ((ListView) PullToRefreshAdapterViewBase.this.refreshableView).setSelection(0);
            } else if (PullToRefreshAdapterViewBase.this.refreshableView instanceof GridView) {
                ((GridView) PullToRefreshAdapterViewBase.this.refreshableView).setSelection(0);
            }
        }
    }

    public abstract ContextMenuInfo getContextMenuInfo();

    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        ((AbsListView) this.refreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, int i) {
        super(context, i);
        ((AbsListView) this.refreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ((AbsListView) this.refreshableView).setOnScrollListener(this);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.onLastItemVisibleListener != null && i2 > 0 && i + i2 == i3 && i != this.lastSavedFirstVisibleItem) {
            this.lastSavedFirstVisibleItem = i;
            this.onLastItemVisibleListener.onLastItemVisible();
        }
        if (this.onScrollListener != null) {
            this.onScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.onScrollListener != null) {
            this.onScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void setBackToTopView(ImageView imageView) {
        imageView.setOnClickListener(new C28381());
    }

    public final void setOnLastItemVisibleListener(OnLastItemVisibleListener onLastItemVisibleListener) {
        this.onLastItemVisibleListener = onLastItemVisibleListener;
    }

    public final void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    protected boolean isReadyForPullDown() {
        return isFirstItemVisible();
    }

    protected boolean isReadyForPullUp() {
        return isLastItemVisible();
    }

    private boolean isFirstItemVisible() {
        if (((AbsListView) this.refreshableView).getCount() == 0) {
            return true;
        }
        if (((AbsListView) this.refreshableView).getFirstVisiblePosition() == 0) {
            View childAt = ((AbsListView) this.refreshableView).getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() >= ((AbsListView) this.refreshableView).getTop();
            }
        }
        return false;
    }

    private boolean isLastItemVisible() {
        int count = ((AbsListView) this.refreshableView).getCount();
        int lastVisiblePosition = ((AbsListView) this.refreshableView).getLastVisiblePosition();
        if (count == 0) {
            return true;
        }
        if (lastVisiblePosition == count - 1) {
            View childAt = ((AbsListView) this.refreshableView).getChildAt(lastVisiblePosition - ((AbsListView) this.refreshableView).getFirstVisiblePosition());
            if (childAt != null) {
                boolean z;
                if (childAt.getBottom() <= ((AbsListView) this.refreshableView).getBottom()) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }
}
