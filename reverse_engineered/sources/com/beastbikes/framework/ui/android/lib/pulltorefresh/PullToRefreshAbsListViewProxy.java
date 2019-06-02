package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.lib.list.OnAdapterChangeListener;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import com.beastbikes.framework.ui.android.lib.list.PageRefreshData;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PullToRefreshAbsListViewProxy<K, D, V extends AbsListView> extends PullToRefreshProxy<K, V> implements AbsListProxable<K, D> {
    private static final String TAG = "absproxy";
    protected BaseListAdapter<D> adapter;
    private boolean isLastPage = true;
    protected long lastModify = -1;
    private K maxId;
    private ArrayList<D> newCacheData = null;
    private K newMaxId = null;
    protected Pageable<K> pageable;
    protected boolean shouldCache = false;
    private boolean shouldShowEmptyView = true;

    public PullToRefreshAbsListViewProxy(BaseListAdapter<D> baseListAdapter, final PullToRefreshAdapterViewBase<V> pullToRefreshAdapterViewBase, String str, PullRefeshListener<K> pullRefeshListener, Pageable<K> pageable) {
        super(pullToRefreshAdapterViewBase, str, pullRefeshListener);
        this.adapter = baseListAdapter;
        if (this.adapter != null) {
            baseListAdapter.setDataChangedListener(new OnAdapterChangeListener() {
                public void onDataChanged(int i) {
                    if (!PullToRefreshAbsListViewProxy.this.shouldShowEmptyView) {
                        return;
                    }
                    if (i < 1) {
                        pullToRefreshAdapterViewBase.showEmptyView();
                    } else {
                        pullToRefreshAdapterViewBase.hideEmptyView();
                    }
                }
            });
        } else {
            pullToRefreshAdapterViewBase.showEmptyView();
        }
        this.shouldCache = false;
        this.pageable = pageable;
        this.maxId = this.pageable.defValue();
        this.newMaxId = this.pageable.defValue();
        hidePullUp();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        ((AbsListView) this.internalView).setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        ((AbsListView) this.internalView).setOnItemLongClickListener(onItemLongClickListener);
    }

    public void onCreate() {
        super.onCreate();
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (Integer.parseInt(VERSION.SDK) > 10) {
            ((AbsListView) this.internalView).setAdapter(listAdapter);
            return;
        }
        try {
            ((AbsListView) this.internalView).getClass().getMethod("setAdapter", new Class[]{ListAdapter.class}).invoke(this.internalView, new Object[]{listAdapter});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public void onStart() {
        setAdapter(this.adapter);
    }

    public void onStop() {
        setAdapter(null);
    }

    public void saveCacheData() {
        if (this.shouldCache && this.newCacheData != null && this.newCacheData.size() > 0) {
            this.pageable.cacheData(this.cacheKey, this.newCacheData, this.lastModify, this.newMaxId);
            Log.d(TAG, "destroy max id is " + this.newMaxId);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        saveCacheData();
    }

    public void onPullUpRefresh() {
        if (!isRefreshing()) {
            this.currentEvent = Event.more;
            if (this.lastModify >= 0) {
                this.pullListener.loadRefreshMore(this.maxId, this.lastModify);
            } else {
                this.pullListener.loadMore(this.maxId);
            }
        }
    }

    public void refreshList() {
        this.adapter.notifyDataSetChanged();
    }

    public void onLoadFailed(String str) {
        this.adapter.notifyDataSetChanged();
        onRefreshFinished();
    }

    public void onLoadSucessfully(List<D> list) {
        onRefreshFinished();
        if (list != null) {
            this.newCacheData = (ArrayList) list;
            this.adapter.clearItems();
            this.adapter.add((List) list);
            this.adapter.notifyDataSetChanged();
        }
        showPullUp();
    }

    public void onLoadSucessAddfully(List<D> list) {
        onRefreshFinished();
        if (list != null && list.size() != 0) {
            this.adapter.add((List) list);
            this.adapter.notifyDataSetChanged();
            showPullUp();
        }
    }

    public void onLoadSucessfully(PageRefreshData<K, D> pageRefreshData) {
        if (pageRefreshData != null) {
            this.lastModify = pageRefreshData.lastModify;
            if (pageRefreshData.isModified) {
                this.adapter.clearItems();
            }
            onLoadSucessfully(pageRefreshData, pageRefreshData.data);
            return;
        }
        onLoadSucessfully(null, null);
    }

    public void onLoadSucessfully(PageData<K, D> pageData) {
        if (pageData != null) {
            onLoadSucessfully(pageData, pageData.data);
        } else {
            onLoadSucessfully(null, null);
        }
    }

    protected void onLoadSucessfully(PageData<K, D> pageData, ArrayList<D> arrayList) {
        if (pageData != null) {
            Object chooseMaxId;
            if (arrayList == null || arrayList.size() == 0) {
                chooseMaxId = this.pageable.chooseMaxId(this.maxId, pageData.maxId);
            } else {
                chooseMaxId = this.pageable.chooseMaxId(this.maxId, pageData.maxId);
            }
            switch (getCurrentEvent()) {
                case normal:
                    this.maxId = chooseMaxId;
                    this.adapter.clearItems();
                    this.adapter.add((List) arrayList);
                    this.isLastPage = pageData.page_is_last;
                    this.newCacheData = arrayList;
                    this.newMaxId = chooseMaxId;
                    break;
                case more:
                    this.maxId = chooseMaxId;
                    this.adapter.add((List) arrayList);
                    this.isLastPage = pageData.page_is_last;
                    break;
                default:
                    break;
            }
        }
        this.isLastPage = true;
        if (this.isLastPage) {
            hidePullUp();
        } else {
            showPullUp();
        }
        onRefreshFinished();
    }

    public BaseListAdapter<D> getAdapter() {
        return this.adapter;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.pullView.setOnTouchListener(onTouchListener);
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public void hideEmptyViewShow() {
        this.shouldShowEmptyView = false;
        if (this.pullView != null) {
            this.pullView.hideEmptyView();
        }
    }
}
