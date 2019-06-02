package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {

    class InternalListView extends ListView {
        public InternalListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ContextMenuInfo getContextMenuInfo() {
            return super.getContextMenuInfo();
        }
    }

    public PullToRefreshListView(Context context) {
        super(context);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshListView(Context context, int i) {
        super(context, i);
        setDisableScrollingWhileRefreshing(false);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDisableScrollingWhileRefreshing(false);
    }

    public ContextMenuInfo getContextMenuInfo() {
        return ((InternalListView) getRefreshableView()).getContextMenuInfo();
    }

    protected ListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ListView internalListView = new InternalListView(context, attributeSet);
        internalListView.setId(16908298);
        internalListView.setSelector(17170445);
        return internalListView;
    }
}
