package com.mob.tools.gui;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

public abstract class PullToRequestGridAdapter extends PullToRequestBaseListAdapter {
    private PullToRequestBaseAdapter adapter;
    private boolean fling;
    private ScrollableGridView gridView = onNewGridView(getContext());
    private OnListStopScrollListener osListener;
    private boolean pullUpReady;

    /* renamed from: com.mob.tools.gui.PullToRequestGridAdapter$1 */
    class C42641 implements OnScrollListener {
        private int firstVisibleItem;
        private int visibleItemCount;

        C42641() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            PullToRequestGridAdapter.this.fling = i == 2;
            if (i != 0) {
                return;
            }
            if (PullToRequestGridAdapter.this.osListener != null) {
                PullToRequestGridAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
            } else if (PullToRequestGridAdapter.this.adapter != null) {
                PullToRequestGridAdapter.this.adapter.notifyDataSetChanged();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.firstVisibleItem = i;
            this.visibleItemCount = i2;
            View childAt = absListView.getChildAt(i2 - 1);
            PullToRequestGridAdapter pullToRequestGridAdapter = PullToRequestGridAdapter.this;
            boolean z = i + i2 == i3 && childAt != null && childAt.getBottom() <= absListView.getBottom();
            pullToRequestGridAdapter.pullUpReady = z;
            PullToRequestGridAdapter.this.onScroll(PullToRequestGridAdapter.this.gridView, i, i2, i3);
        }
    }

    public PullToRequestGridAdapter(PullToRequestView pullToRequestView) {
        super(pullToRequestView);
        this.gridView.setOnScrollListener(new C42641());
        this.adapter = new PullToRequestBaseAdapter(this);
        this.gridView.setAdapter(this.adapter);
    }

    protected ScrollableGridView onNewGridView(Context context) {
        return new ScrollableGridView(context);
    }

    public Scrollable getBodyView() {
        return this.gridView;
    }

    public boolean isPullDownReady() {
        return this.gridView.isReadyToPull();
    }

    public boolean isPullUpReady() {
        return this.pullUpReady;
    }

    public GridView getGridView() {
        return this.gridView;
    }

    public boolean isFling() {
        return this.fling;
    }

    public void onScroll(Scrollable scrollable, int i, int i2, int i3) {
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.adapter.notifyDataSetChanged();
    }

    public void setHorizontalSpacing(int i) {
        this.gridView.setHorizontalSpacing(i);
    }

    public void setVerticalSpacing(int i) {
        this.gridView.setVerticalSpacing(i);
    }

    public void setNumColumns(int i) {
        this.gridView.setNumColumns(i);
    }

    public void setColumnWidth(int i) {
        this.gridView.setColumnWidth(i);
    }

    public void setStretchMode(int i) {
        this.gridView.setStretchMode(i);
    }
}
