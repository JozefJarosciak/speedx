package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.GridView;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView> {

    class InternalGridView extends GridView {
        public InternalGridView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ContextMenuInfo getContextMenuInfo() {
            return super.getContextMenuInfo();
        }
    }

    public PullToRefreshGridView(Context context) {
        super(context);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshGridView(Context context, int i) {
        super(context, i);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDisableScrollingWhileRefreshing(false);
    }

    public ContextMenuInfo getContextMenuInfo() {
        return ((InternalGridView) getRefreshableView()).getContextMenuInfo();
    }

    protected GridView createRefreshableView(Context context, AttributeSet attributeSet) {
        GridView internalGridView = new InternalGridView(context, attributeSet);
        internalGridView.setId(16908298);
        return internalGridView;
    }

    protected void addRefreshableView(Context context, GridView gridView) {
        super.addRefreshableView(context, gridView);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
