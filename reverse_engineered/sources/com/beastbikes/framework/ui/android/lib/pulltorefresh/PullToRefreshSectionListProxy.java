package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import com.beastbikes.framework.ui.android.lib.list.BaseSectionListAdapter;
import com.beastbikes.framework.ui.android.lib.list.OnAdapterChangeListener;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import com.beastbikes.framework.ui.android.lib.list.PageRefreshData;
import java.util.ArrayList;

public class PullToRefreshSectionListProxy<K, G extends Groupable<C>, C> extends PullToRefreshProxy<K, ExpandableSectionList> {
    private static final String TAG = "absproxy";
    private static final int scrollBy = 150;
    protected BaseSectionListAdapter<G, C> adapter;
    private boolean isLastPage = true;
    protected long lastModify = -1;
    private K maxId;
    private ArrayList<G> newCacheData = null;
    private K newMaxId = null;
    protected Pageable<K> pageable;
    protected boolean shouldCache = false;
    private final Runnable showMoreDataRunnable = new C28441();

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshSectionListProxy$1 */
    class C28441 implements Runnable {
        C28441() {
        }

        public void run() {
            PullToRefreshSectionListProxy.this.scrollToShowMoreData();
        }
    }

    public PullToRefreshSectionListProxy(BaseSectionListAdapter<G, C> baseSectionListAdapter, final PullToRefreshBase<ExpandableSectionList> pullToRefreshBase, String str, PullRefeshListener<K> pullRefeshListener, Pageable<K> pageable) {
        super(pullToRefreshBase, str, pullRefeshListener);
        this.adapter = baseSectionListAdapter;
        this.adapter.setDataChangeListener(new OnAdapterChangeListener() {
            public void onDataChanged(int i) {
                if (i < 1) {
                    pullToRefreshBase.showEmptyView();
                } else {
                    pullToRefreshBase.hideEmptyView();
                }
            }
        });
        this.pageable = pageable;
        this.maxId = this.pageable.defValue();
        this.newMaxId = this.pageable.defValue();
        hidePullUp();
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        ((ExpandableSectionList) this.internalView).setOnChildClickListener(onChildClickListener);
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onStart() {
        if (this.internalView != null) {
            ((ExpandableSectionList) this.internalView).setAdapter(this.adapter);
        }
    }

    public void onStop() {
        if (this.internalView != null) {
            ((ExpandableSectionList) this.internalView).setAdapter((ExpandableListAdapter) null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.shouldCache && this.newCacheData != null && this.newCacheData.size() > 0) {
            this.pageable.cacheData(this.cacheKey, this.newCacheData, this.lastModify, this.newMaxId);
            Log.d(TAG, "destroy max id is " + this.newMaxId);
        }
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
        onRefreshFinished();
    }

    public void onLoadSucessfully(ArrayList<G> arrayList) {
        onRefreshFinished();
        if (arrayList != null) {
            this.adapter.add(arrayList);
            this.adapter.notifyDataSetChanged();
            if (this.shouldCache && arrayList.size() > 0) {
                this.newCacheData = arrayList;
            }
        }
    }

    public void onLoadSucessfully(PageRefreshData<K, G> pageRefreshData) {
        if (pageRefreshData != null) {
            this.lastModify = pageRefreshData.lastModify;
            if (pageRefreshData.isModified) {
                this.adapter.clear();
            }
            onLoadSucessfully(pageRefreshData, pageRefreshData.data);
            return;
        }
        onLoadSucessfully(null, null);
    }

    public void onLoadSucessfully(PageData<K, G> pageData) {
        if (pageData != null) {
            onLoadSucessfully(pageData, pageData.data);
        } else {
            onLoadSucessfully(null, null);
        }
    }

    protected void onLoadSucessfully(PageData<K, G> pageData, ArrayList<G> arrayList) {
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
                    this.adapter.clear();
                    this.adapter.add(arrayList);
                    this.isLastPage = pageData.page_is_last;
                    this.newCacheData = arrayList;
                    this.newMaxId = chooseMaxId;
                    break;
                case more:
                    this.maxId = chooseMaxId;
                    this.adapter.add(arrayList);
                    this.isLastPage = pageData.page_is_last;
                    this.handler.postDelayed(this.showMoreDataRunnable, 200);
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

    private void scrollToShowMoreData() {
        try {
            final ExpandableSectionList expandableSectionList = (ExpandableSectionList) getInternalView();
            final int firstVisiblePosition = expandableSectionList.getFirstVisiblePosition();
            if (firstVisiblePosition >= 0) {
                View childAt = expandableSectionList.getChildAt(0);
                if (childAt != null) {
                    int bottom = childAt.getBottom();
                    int top = childAt.getTop();
                    if (bottom > 150) {
                        this.handler.post(new Smoothable(this.handler, 150, top, top - 150) {
                            public void doSmooth(int i) {
                                expandableSectionList.setSelectionFromTop(firstVisiblePosition, i);
                            }
                        });
                        return;
                    }
                    this.handler.post(new Smoothable(this.handler, 150, bottom, bottom - 150) {
                        public void doSmooth(int i) {
                            expandableSectionList.setSelectionFromTop(firstVisiblePosition + 1, i);
                        }
                    });
                    return;
                }
                this.handler.post(new Smoothable(this.handler, 150, 0, 30) {
                    public void doSmooth(int i) {
                        expandableSectionList.scrollBy(0, i);
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public BaseSectionListAdapter<G, C> getAdapter() {
        return this.adapter;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.pullView.setOnTouchListener(onTouchListener);
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }
}
