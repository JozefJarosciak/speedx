package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.os.Handler;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshBase.OnRefreshListener;

public class PullToRefreshProxy<K, V extends View> implements OnRefreshListener {
    private static final long MIN_INTERVAL = 300000;
    protected String cacheKey;
    protected Event currentEvent = Event.none;
    protected Handler handler = new Handler();
    protected V internalView;
    protected long minInterval = MIN_INTERVAL;
    protected boolean pullDownDisabled = false;
    protected PullRefeshListener<K> pullListener;
    protected boolean pullUpDisabled = false;
    protected PullToRefreshBase<V> pullView;

    public PullToRefreshProxy(PullToRefreshBase<V> pullToRefreshBase, String str, PullRefeshListener<K> pullRefeshListener) {
        this.cacheKey = str;
        this.pullView = pullToRefreshBase;
        this.pullListener = pullRefeshListener;
        this.pullView.setOnRefreshListener(this);
        this.internalView = this.pullView.getRefreshableView();
        restoreUpdateTime();
    }

    public void onDestroy() {
        onRefreshFinished();
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        this.internalView.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public void setBackGround(int i) {
        this.pullView.setBackgroundResource(i);
        this.internalView.setBackgroundResource(i);
    }

    public void setBackGroudColor(int i) {
        this.pullView.setBackgroundColor(i);
        this.internalView.setBackgroundColor(i);
    }

    public void showContextMenu() {
        this.internalView.showContextMenu();
    }

    public void onCreate() {
        long currentTimeMillis = System.currentTimeMillis() - 0;
        if (this.minInterval == 0) {
            this.currentEvent = Event.none;
            this.pullView.setRefreshing(true);
        } else if (this.minInterval > 0 && currentTimeMillis > this.minInterval) {
            if (this.pullListener.shouldRefreshingHeaderOnStart()) {
                this.currentEvent = Event.none;
                this.pullView.setRefreshing(true);
            } else if (this.pullListener != null) {
                this.pullListener.loadNormal();
            }
        }
    }

    public void setMinInterval(long j) {
        this.minInterval = j;
    }

    public PullToRefreshBase<V> getPullView() {
        return this.pullView;
    }

    public V getInternalView() {
        return this.internalView;
    }

    public Event getCurrentEvent() {
        return this.currentEvent;
    }

    public void onPullDownRefresh() {
        if (!isRefreshing()) {
            this.currentEvent = Event.normal;
            this.pullListener.loadNormal();
        }
    }

    public void pullDownToRefresh() {
        pullDownToRefresh(true);
    }

    public void pullDownToRefresh(boolean z) {
        if (!isRefreshing()) {
            this.pullView.setRefreshing(z);
        }
    }

    public void onPullUpRefresh() {
        if (!isRefreshing()) {
            this.currentEvent = Event.more;
            this.pullListener.loadMore(null);
        }
    }

    public void disablePull() {
        this.pullDownDisabled = true;
        this.pullUpDisabled = true;
        this.pullView.disablePull();
    }

    public void enablePull() {
        this.pullDownDisabled = false;
        this.pullUpDisabled = false;
        this.pullView.enablePull();
    }

    public boolean isPullUpEnabled() {
        return !this.pullUpDisabled;
    }

    public void enablePullUp() {
        enablePull();
        disablePullDown();
    }

    public boolean isPullDownEnabled() {
        return !this.pullDownDisabled;
    }

    public void enablePullDown() {
        enablePull();
        disablePullUp();
    }

    public void disablePullUp() {
        this.pullUpDisabled = true;
        this.pullView.disablePullUp();
    }

    public void disablePullDown() {
        this.pullDownDisabled = true;
        this.pullView.disablePullDown();
    }

    public void showPullUp() {
        if (!this.pullUpDisabled) {
            this.pullView.enablePullUp();
        }
    }

    public void hidePullUp() {
        this.pullView.disablePullUp();
    }

    public void setPullHeader(PullableView pullableView) {
        if (pullableView != null) {
            this.pullView.setPullHeaderView(pullableView);
        }
    }

    public void setRefreshing(boolean z) {
        this.pullView.setRefreshing(z);
    }

    public void onRefreshFinished() {
        this.pullView.onRefreshComplete();
        this.currentEvent = Event.none;
    }

    protected boolean isRefreshing() {
        return this.currentEvent != Event.none;
    }

    private void restoreUpdateTime() {
        this.pullView.setUpdateTime(0);
    }
}
