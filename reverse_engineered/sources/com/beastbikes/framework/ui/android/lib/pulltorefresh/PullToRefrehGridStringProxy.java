package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.widget.GridView;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;

public class PullToRefrehGridStringProxy<D> extends PullToRefreshAbsListViewProxy<String, D, GridView> {
    public PullToRefrehGridStringProxy(BaseListAdapter<D> baseListAdapter, PullToRefreshAdapterViewBase<GridView> pullToRefreshAdapterViewBase, String str, PullRefeshListener<String> pullRefeshListener, Pageable<String> pageable) {
        super(baseListAdapter, pullToRefreshAdapterViewBase, str, pullRefeshListener, pageable);
    }
}
