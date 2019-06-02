package com.beastbikes.framework.ui.android.lib.frag;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.framework.ui.android.BaseFragment;
import com.beastbikes.framework.ui.android.C2824R;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullRefeshListener;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshBase;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshProxy;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullableView;

public abstract class FragBasePull<K, V extends View> extends BaseFragment implements OnItemClickListener, PullRefeshListener<K> {
    private static final String TAG = "fragment";
    private boolean configured = false;
    protected V internalView;
    private PullConfigable<V> pullConfigableListener;
    protected PullToRefreshProxy<K, V> pullProxy;
    protected PullToRefreshBase<V> pullView;

    protected abstract int layoutResource();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(layoutResource(), viewGroup, false);
        this.pullView = (PullToRefreshBase) inflate.findViewById(C2824R.id.pullRefreshAbsListView);
        this.internalView = this.pullView.getRefreshableView();
        this.pullProxy = getPullProxy();
        this.pullProxy.setMinInterval(getMinInterval());
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.pullProxy.onCreate();
        if (this.pullConfigableListener != null && !this.configured) {
            this.configured = true;
            this.pullConfigableListener.configView(this.pullView, this.internalView);
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        if (this.pullProxy != null) {
            this.pullProxy.onDestroy();
        }
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
    }

    public long getMinInterval() {
        return 0;
    }

    protected void showListContextMenu() {
        this.pullProxy.showContextMenu();
    }

    protected String cacheKey() {
        return null;
    }

    protected PullableView getPullHeader() {
        return null;
    }

    protected PullToRefreshProxy<K, V> getPullProxy() {
        PullToRefreshProxy<K, V> pullToRefreshProxy = new PullToRefreshProxy(this.pullView, cacheKey(), this);
        pullToRefreshProxy.setPullHeader(getPullHeader());
        return pullToRefreshProxy;
    }

    public boolean shouldRefreshingHeaderOnStart() {
        return true;
    }

    public void setPullConfiguableListener(PullConfigable<V> pullConfigable) {
        this.pullConfigableListener = pullConfigable;
        if (this.pullProxy != null && !this.configured) {
            this.configured = true;
            this.pullConfigableListener.configView(this.pullView, this.internalView);
        }
    }

    public void pullToRefresh() {
        this.pullProxy.pullDownToRefresh();
    }

    public void pullToRefresh(boolean z) {
        this.pullProxy.pullDownToRefresh(z);
    }
}
