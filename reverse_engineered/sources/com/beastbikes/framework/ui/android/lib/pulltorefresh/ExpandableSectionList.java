package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.beastbikes.framework.ui.android.C2824R;
import com.beastbikes.framework.ui.android.lib.list.BaseSectionListAdapter;

public class ExpandableSectionList extends ExpandableListView implements OnScrollListener {
    private static final int INVALID_LAYOUT_ID = -1;
    private static final String TAG = "section";
    private int bottom = 0;
    private boolean hasTouched = false;
    private boolean headerShow = false;
    private int indicatorGroupHeight;
    private int indicatorGroupId = -1;
    private boolean isCollapsable = true;
    private MotionEvent lastEvent = null;
    private int left = 0;
    private BaseSectionListAdapter<?, ?> mAdapter;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private int mHeaderViewWidth;
    private OnScrollListener mockScrollListener;
    private int right = 0;
    private int scrollOffset;
    private int top = 0;

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.ExpandableSectionList$1 */
    class C28321 implements OnTouchListener {
        C28321() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.ExpandableSectionList$2 */
    class C28332 extends DataSetObserver {
        C28332() {
        }

        public void onChanged() {
            ExpandableSectionList.this.indicatorGroupId = -1;
        }
    }

    public ExpandableSectionList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2824R.styleable.SectionHeader);
        if (obtainStyledAttributes.hasValue(C2824R.styleable.SectionHeader_header_layout)) {
            int resourceId = obtainStyledAttributes.getResourceId(C2824R.styleable.SectionHeader_header_layout, -1);
            if (resourceId != -1) {
                View inflate = LayoutInflater.from(context).inflate(resourceId, this, false);
                inflate.setOnTouchListener(new C28321());
                setPinnedHeaderView(inflate);
                if (obtainStyledAttributes.hasValue(C2824R.styleable.SectionHeader_is_collapsable)) {
                    this.isCollapsable = obtainStyledAttributes.getBoolean(C2824R.styleable.SectionHeader_is_collapsable, true);
                }
            }
        }
        obtainStyledAttributes.recycle();
        super.setOnScrollListener(this);
    }

    public void setPinnedHeaderView(View view) {
        this.mHeaderView = view;
        if (this.mHeaderView != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }

    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        super.setAdapter(expandableListAdapter);
        this.mAdapter = (BaseSectionListAdapter) expandableListAdapter;
        if (this.mAdapter != null) {
            this.mAdapter.expandAll();
            this.mAdapter.registerDataSetObserver(new C28332());
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mHeaderView != null) {
            measureChild(this.mHeaderView, i, i2);
            this.mHeaderViewWidth = this.mHeaderView.getMeasuredWidth();
            this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mHeaderView != null) {
            this.mHeaderView.layout(0, 0, this.mHeaderViewWidth, this.mHeaderViewHeight);
            configHeader(0);
        }
    }

    protected void configHeader(int i) {
        if (this.mHeaderView != null) {
            int i2;
            int pointToPosition = pointToPosition(10, i + 1);
            if (pointToPosition != -1) {
                long expandableListPosition = getExpandableListPosition(pointToPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                boolean z = packedPositionChild == -1 && packedPositionGroup == -1;
                this.headerShow = z;
                if (packedPositionChild == -1) {
                    if (this.headerShow) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    View childAt = getChildAt(i2 + (pointToPosition - getFirstVisiblePosition()));
                    if (childAt != null) {
                        this.indicatorGroupHeight = childAt.getHeight();
                    }
                }
                if (this.indicatorGroupHeight == 0) {
                    return;
                }
                if (packedPositionGroup != this.indicatorGroupId) {
                    if (!this.headerShow) {
                        Log.d(TAG, "refresh group view " + packedPositionGroup);
                        this.mAdapter.getGroupView(packedPositionGroup, isGroupExpanded(packedPositionGroup), this.mHeaderView, null);
                    }
                    this.indicatorGroupId = packedPositionGroup;
                }
            }
            if (this.indicatorGroupId == -1) {
                this.headerShow = true;
                return;
            }
            i2 = this.indicatorGroupHeight;
            int pointToPosition2 = pointToPosition(10, (this.indicatorGroupHeight + i) + 1);
            if (!(pointToPosition2 == -1 || ExpandableListView.getPackedPositionGroup(getExpandableListPosition(pointToPosition2)) == this.indicatorGroupId)) {
                i2 = getChildAt(pointToPosition2 - getFirstVisiblePosition()).getTop() - i;
            }
            this.left = 0;
            this.top = -(this.indicatorGroupHeight - i2);
            this.right = this.mHeaderViewWidth;
            this.bottom = this.mHeaderViewHeight + this.top;
            this.mHeaderView.layout(this.left, this.top + i, this.right, this.bottom + i);
            this.scrollOffset = i;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.lastEvent = motionEvent;
        this.hasTouched = true;
        if (this.mHeaderView != null && this.isCollapsable) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    if (isGroupExpanded(this.indicatorGroupId) && x > this.left && x < this.right && y > this.top && y < this.bottom) {
                        collapseGroup(this.indicatorGroupId);
                        return true;
                    }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void singleClick() {
        if (this.lastEvent != null) {
            this.lastEvent.setAction(0);
            super.dispatchTouchEvent(this.lastEvent);
            this.lastEvent.setAction(3);
            super.dispatchTouchEvent(this.lastEvent);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!this.headerShow && this.mHeaderView != null && this.scrollOffset >= 0 && this.hasTouched) {
            drawChild(canvas, this.mHeaderView, getDrawingTime());
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0) {
            this.lastEvent = null;
        }
        if (this.mockScrollListener != null) {
            this.mockScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mHeaderView != null) {
            configHeader(0);
        }
        if (this.mockScrollListener != null) {
            this.mockScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mockScrollListener = onScrollListener;
    }
}
