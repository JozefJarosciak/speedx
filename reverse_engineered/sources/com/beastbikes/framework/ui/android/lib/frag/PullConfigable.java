package com.beastbikes.framework.ui.android.lib.frag;

import android.view.View;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshBase;

public interface PullConfigable<V extends View> {
    void configRefresh();

    void configView(PullToRefreshBase<V> pullToRefreshBase, V v);
}
