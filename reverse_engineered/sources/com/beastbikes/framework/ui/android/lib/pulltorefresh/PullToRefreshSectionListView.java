package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;

public class PullToRefreshSectionListView extends PullToRefreshAdapterViewBase<ExpandableSectionList> {

    class InternalSectionListView extends ExpandableSectionList {
        public InternalSectionListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ContextMenuInfo getContextMenuInfo() {
            return super.getContextMenuInfo();
        }
    }

    public PullToRefreshSectionListView(Context context) {
        super(context);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshSectionListView(Context context, int i) {
        super(context, i);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshSectionListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDisableScrollingWhileRefreshing(false);
    }

    public ContextMenuInfo getContextMenuInfo() {
        return ((InternalSectionListView) getRefreshableView()).getContextMenuInfo();
    }

    protected final ExpandableSectionList createRefreshableView(Context context, AttributeSet attributeSet) {
        Object internalSectionListView = new InternalSectionListView(context, attributeSet);
        internalSectionListView.setId(16908298);
        internalSectionListView.setSelector(17170445);
        setOnScrollListener(internalSectionListView);
        return internalSectionListView;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ((ExpandableSectionList) this.refreshableView).configHeader(getScrollY());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ((ExpandableSectionList) this.refreshableView).configHeader(getScrollY());
    }
}
