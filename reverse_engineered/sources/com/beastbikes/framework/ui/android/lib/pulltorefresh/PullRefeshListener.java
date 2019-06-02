package com.beastbikes.framework.ui.android.lib.pulltorefresh;

public interface PullRefeshListener<K> {
    void loadMore(K k);

    void loadNormal();

    void loadRefreshMore(K k, long j);

    boolean shouldRefreshingHeaderOnStart();
}
