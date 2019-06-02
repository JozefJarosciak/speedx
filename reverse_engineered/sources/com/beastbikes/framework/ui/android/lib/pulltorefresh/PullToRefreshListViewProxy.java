package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.view.View;
import android.widget.ListView;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import java.util.ArrayList;

public class PullToRefreshListViewProxy<K, D> extends PullToRefreshAbsListViewProxy<K, D, ListView> {
    private static final int scrollBy = 150;
    private View footerView = null;
    private View headerView = null;
    private final Runnable showMoreDataRunnable = new C28391();

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshListViewProxy$1 */
    class C28391 implements Runnable {
        C28391() {
        }

        public void run() {
            PullToRefreshListViewProxy.this.scrollToShowMoreData();
        }
    }

    public PullToRefreshListViewProxy(BaseListAdapter<D> baseListAdapter, PullToRefreshAdapterViewBase<ListView> pullToRefreshAdapterViewBase, String str, PullRefeshListener<K> pullRefeshListener, Pageable<K> pageable) {
        super(baseListAdapter, pullToRefreshAdapterViewBase, str, pullRefeshListener, pageable);
    }

    public PullToRefreshListViewProxy(BaseListAdapter<D> baseListAdapter, PullToRefreshAdapterViewBase<ListView> pullToRefreshAdapterViewBase, String str, PullRefeshListener<K> pullRefeshListener, View view, View view2, Pageable<K> pageable) {
        super(baseListAdapter, pullToRefreshAdapterViewBase, str, pullRefeshListener, pageable);
        this.headerView = view;
        this.footerView = view2;
    }

    public void onCreate() {
        super.onCreate();
        ((ListView) this.internalView).setHeaderDividersEnabled(false);
        if (this.headerView != null) {
            ((ListView) this.internalView).addHeaderView(this.headerView);
        }
        if (this.footerView != null) {
            ((ListView) this.internalView).addFooterView(this.footerView);
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void onLoadSucessfully(PageData<K, D> pageData, ArrayList<D> arrayList) {
        Event event = this.currentEvent;
        super.onLoadSucessfully(pageData, arrayList);
        if (arrayList != null && arrayList.size() >= 1) {
            switch (event) {
                case more:
                    this.handler.postDelayed(this.showMoreDataRunnable, 250);
                    return;
                default:
                    return;
            }
        }
    }

    private void scrollToShowMoreData() {
        final ListView listView = (ListView) getInternalView();
        if (this.adapter.getCount() > listView.getLastVisiblePosition()) {
            final int firstVisiblePosition = listView.getFirstVisiblePosition();
            if (firstVisiblePosition >= 0) {
                View childAt = listView.getChildAt(0);
                if (childAt != null) {
                    int bottom = childAt.getBottom();
                    int top = childAt.getTop();
                    if (bottom > 150) {
                        this.handler.post(new Smoothable(this.handler, 150, top, top - 150) {
                            public void doSmooth(int i) {
                                listView.setSelectionFromTop(firstVisiblePosition, i);
                            }
                        });
                        return;
                    }
                    this.handler.post(new Smoothable(this.handler, 150, bottom, bottom - 150) {
                        public void doSmooth(int i) {
                            listView.setSelectionFromTop(firstVisiblePosition + 1, i);
                        }
                    });
                    return;
                }
                this.handler.post(new Smoothable(this.handler, 150, 0, 30) {
                    public void doSmooth(int i) {
                        listView.scrollBy(0, i);
                    }
                });
            }
        }
    }
}
