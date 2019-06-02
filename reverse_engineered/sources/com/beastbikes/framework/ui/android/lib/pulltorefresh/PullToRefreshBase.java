package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.framework.ui.android.C2824R;
import com.google.common.primitives.Ints;
import java.util.Date;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout {
    public static final int ANIMATION_DURATION_MS = 150;
    private static final int DP_HORIZONTAL_SCROLL = 55;
    static final float FRICTION = 2.0f;
    private static final String LOG = "mypull";
    static final int MANUAL_REFRESHING = 5;
    public static final int MODE_BOTH = 3;
    public static final int MODE_NONE = 0;
    public static final int MODE_PULL_DOWN_TO_REFRESH = 1;
    public static final int MODE_PULL_UP_TO_REFRESH = 2;
    static final int PULL_DOWN_TO_REFRESH = 1;
    static final int PULL_UP_TO_REFRESH = 2;
    static final int REFRESHING = 4;
    static final int RELEASE_TO_REFRESH = 3;
    private boolean canPullDownRefresh = true;
    private boolean canPullUpRefresh = true;
    private int currentMode;
    private SmoothScrollRunnable currentSmoothScrollRunnable;
    private boolean disableScrollingWhileRefreshing = true;
    View emptyView;
    private int footerHeight;
    private PullableView footerLayout;
    private final Handler handler = new Handler();
    private int headerHeight;
    private PullableView headerLayout;
    private int headerOffset;
    private int horizontalSlop;
    private float initialMotionX;
    private float initialMotionY;
    private boolean isBeingDragged = false;
    private boolean isFlingHandled = false;
    private boolean isFlingInvoked = false;
    private boolean isPullToRefreshEnabled = true;
    private float lastMotionX;
    private float lastMotionY;
    private int mode = 3;
    private OnFlingListener onFlingListener;
    private OnRefreshListener onRefreshListener;
    private View pullFooterView;
    private View pullHeaderView;
    FrameLayout refreshContainer;
    T refreshableView;
    private int state = 1;
    private int touchSlop;

    public interface OnRefreshListener {
        void onPullDownRefresh();

        void onPullUpRefresh();
    }

    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    final class SmoothScrollRunnable implements Runnable {
        static final int ANIMATION_FPS = 16;
        private boolean continueRunning = true;
        private int currentY = -1;
        private final Handler handler;
        private final Interpolator interpolator;
        private final int scrollFromY;
        private final int scrollToY;
        private long startTime = -1;

        public SmoothScrollRunnable(Handler handler, int i, int i2) {
            this.handler = handler;
            this.scrollFromY = i;
            this.scrollToY = i2;
            this.interpolator = new AccelerateDecelerateInterpolator();
        }

        public void run() {
            if (this.startTime == -1) {
                this.startTime = System.currentTimeMillis();
            } else {
                float f = (float) (this.scrollFromY - this.scrollToY);
                this.currentY = this.scrollFromY - Math.round(this.interpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.startTime) * 1000) / 150, 1000), 0)) / 1000.0f) * f);
                PullToRefreshBase.this.setHeaderScroll(this.currentY);
            }
            if (this.continueRunning && this.scrollToY != this.currentY) {
                this.handler.postDelayed(this, 16);
            }
        }

        public void stop() {
            this.continueRunning = false;
            this.handler.removeCallbacks(this);
        }
    }

    protected abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    protected abstract boolean isReadyForPullDown();

    protected abstract boolean isReadyForPullUp();

    public PullToRefreshBase(Context context) {
        super(context);
        init(context, null);
    }

    public PullToRefreshBase(Context context, int i) {
        super(context);
        this.mode = i;
        init(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public final T getRefreshableView() {
        return this.refreshableView;
    }

    public final boolean isPullToRefreshEnabled() {
        return this.isPullToRefreshEnabled;
    }

    public final boolean isDisableScrollingWhileRefreshing() {
        return this.disableScrollingWhileRefreshing;
    }

    public final boolean isRefreshing() {
        return this.state == 4 || this.state == 5;
    }

    public final void setDisableScrollingWhileRefreshing(boolean z) {
        this.disableScrollingWhileRefreshing = z;
    }

    public final void onRefreshComplete() {
        if (this.state != 1) {
            resetHeader();
        }
    }

    public final void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public final void setOnFlingListener(OnFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }

    public final void setPullToRefreshEnabled(boolean z) {
        this.isPullToRefreshEnabled = z;
    }

    public void setReleaseLabel(String str) {
        if (this.headerLayout != null) {
            this.headerLayout.setReleaseLabel(str);
        }
        if (this.footerLayout != null) {
            this.footerLayout.setReleaseLabel(str);
        }
    }

    public void setPullLabel(String str) {
        if (this.headerLayout != null) {
            this.headerLayout.setPullLabel(str);
        }
        if (this.footerLayout != null) {
            this.footerLayout.setPullLabel(str);
        }
    }

    public void setRefreshingLabel(String str) {
        if (this.headerLayout != null) {
            this.headerLayout.setRefreshingLabel(str);
        }
        if (this.footerLayout != null) {
            this.footerLayout.setRefreshingLabel(str);
        }
    }

    public void setPullHeaderBackgroundColor(int i) {
        if (this.headerLayout != null) {
            this.headerLayout.getView().setBackgroundColor(i);
        }
        if (this.footerLayout != null) {
            this.footerLayout.getView().setBackgroundColor(i);
        }
    }

    public final void setRefreshing(boolean z) {
        if (!isRefreshing()) {
            this.headerLayout.updateTimeLabel();
            this.headerLayout.setUpdateTime(getTime());
            this.currentMode = 1;
            setRefreshingInternal(z);
            this.state = 5;
            this.onRefreshListener.onPullDownRefresh();
        }
    }

    public final boolean hasPullFromTop() {
        return this.currentMode != 2;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        getRefreshableView().setVisibility(i);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (isTouchAfterFling(motionEvent)) {
            return true;
        }
        if (!this.isPullToRefreshEnabled) {
            return false;
        }
        if (isRefreshing() && this.disableScrollingWhileRefreshing) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                float y = motionEvent.getY();
                this.initialMotionY = y;
                this.lastMotionY = y;
                this.isFlingInvoked = false;
                return true;
            case 1:
            case 3:
                if (!this.isBeingDragged) {
                    return false;
                }
                this.isBeingDragged = false;
                if (this.state != 3 || this.onRefreshListener == null) {
                    if (this.state == 4 || this.state == 5) {
                        smoothScrollTo(this.currentMode == 1 ? -this.headerHeight : this.footerHeight);
                    } else {
                        smoothScrollTo(0);
                    }
                } else if (this.currentMode == 1 && this.canPullDownRefresh) {
                    setRefreshingInternal(true);
                    this.headerLayout.setUpdateTime(getTime());
                    this.onRefreshListener.onPullDownRefresh();
                } else if (this.currentMode == 2 && this.canPullUpRefresh) {
                    setRefreshingInternal(true);
                    this.footerLayout.setUpdateTime(getTime());
                    this.onRefreshListener.onPullUpRefresh();
                } else {
                    smoothScrollTo(0);
                }
                return true;
            case 2:
                if (!this.isBeingDragged) {
                    return false;
                }
                this.lastMotionY = motionEvent.getY();
                pullEvent();
                return true;
            default:
                return false;
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (isTouchAfterFling(motionEvent)) {
            return true;
        }
        if (!this.isPullToRefreshEnabled) {
            return false;
        }
        if (isRefreshing() && this.disableScrollingWhileRefreshing) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.isBeingDragged = false;
            return false;
        } else if (action != 0 && this.isBeingDragged) {
            return true;
        } else {
            float y;
            switch (action) {
                case 0:
                    y = motionEvent.getY();
                    this.initialMotionY = y;
                    this.lastMotionY = y;
                    y = motionEvent.getX();
                    this.initialMotionX = y;
                    this.lastMotionX = y;
                    this.isBeingDragged = false;
                    this.isFlingInvoked = false;
                    break;
                case 2:
                    float x;
                    float abs;
                    if (!this.isFlingInvoked) {
                        y = Math.abs(motionEvent.getY() - this.initialMotionY);
                        x = motionEvent.getX() - this.initialMotionX;
                        abs = Math.abs(x);
                        Log.d(LOG, "x1: " + this.initialMotionX + " y1:" + this.initialMotionY + "xd: " + abs + " yd: " + y);
                        if (abs > ((float) this.horizontalSlop) && abs > y && this.onFlingListener != null) {
                            if (x < ((float) this.horizontalSlop)) {
                                this.isFlingInvoked = true;
                                this.isFlingHandled = this.onFlingListener.onFlingToLeft(this.initialMotionX, this.initialMotionY, motionEvent.getX(), motionEvent.getY());
                            } else {
                                this.isFlingInvoked = true;
                                this.isFlingHandled = this.onFlingListener.onFlingToRight(this.initialMotionX, this.initialMotionY, motionEvent.getX(), motionEvent.getY());
                            }
                        }
                    }
                    if (isReadyForPull() && !this.isFlingHandled) {
                        y = motionEvent.getY();
                        x = y - this.lastMotionY;
                        abs = Math.abs(x);
                        float abs2 = Math.abs(motionEvent.getX() - this.lastMotionX);
                        if (abs > ((float) this.touchSlop) && abs > abs2) {
                            if ((this.mode != 1 && this.mode != 3) || x < 1.0E-4f || !isReadyForPullDown()) {
                                if ((this.mode == 2 || this.mode == 3) && x <= 1.0E-4f && isReadyForPullUp()) {
                                    this.lastMotionY = y;
                                    this.isBeingDragged = true;
                                    if (this.mode == 3 || this.mode == 2) {
                                        this.footerLayout.updateTimeLabel();
                                        this.currentMode = 2;
                                        Log.d(LOG, "current mode is set to: " + this.currentMode);
                                        break;
                                    }
                                }
                            }
                            this.lastMotionY = y;
                            this.isBeingDragged = true;
                            if (this.mode == 3 || this.mode == 1) {
                                this.headerLayout.updateTimeLabel();
                                this.currentMode = 1;
                                Log.d(LOG, "current mode is set to: " + this.currentMode);
                                break;
                            }
                        }
                    }
                    break;
            }
            if (this.isBeingDragged || this.isFlingHandled) {
                z = true;
            }
            return z;
        }
    }

    private boolean isTouchAfterFling(MotionEvent motionEvent) {
        if (!this.isFlingHandled || this.onFlingListener == null || motionEvent.getAction() != 0) {
            return false;
        }
        this.onFlingListener.onTouchedAfterFlinged(motionEvent.getX(), motionEvent.getY());
        this.isFlingHandled = false;
        return true;
    }

    protected void addRefreshableView(Context context, T t) {
        this.refreshContainer.addView(t, -1, -1);
    }

    protected final int getCurrentMode() {
        return this.currentMode;
    }

    protected final int getMode() {
        return this.mode;
    }

    protected void resetHeader() {
        this.state = 1;
        this.isBeingDragged = false;
        if (this.headerLayout != null) {
            this.headerLayout.reset();
        }
        if (this.footerLayout != null) {
            this.footerLayout.reset();
        }
        smoothScrollTo(0);
    }

    protected void setRefreshingInternal(boolean z) {
        this.state = 4;
        if (this.headerLayout != null) {
            this.headerLayout.refreshing();
        }
        if (this.footerLayout != null) {
            this.footerLayout.refreshing();
        }
        if (z) {
            smoothScrollTo(this.currentMode == 1 ? -(this.headerHeight + this.headerOffset) : this.footerHeight);
        }
    }

    protected final void setHeaderScroll(int i) {
        scrollTo(0, i);
    }

    protected final void smoothScrollTo(int i) {
        if (this.currentSmoothScrollRunnable != null) {
            this.currentSmoothScrollRunnable.stop();
        }
        if (getScrollY() != i) {
            this.currentSmoothScrollRunnable = new SmoothScrollRunnable(this.handler, getScrollY(), i);
            this.handler.post(this.currentSmoothScrollRunnable);
        }
    }

    private void init(Context context, AttributeSet attributeSet) {
        setOrientation(1);
        this.touchSlop = ViewConfiguration.getTouchSlop();
        this.horizontalSlop = DensityUtil.dip2px(55.0f, getContext());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2824R.styleable.PullToRefresh);
        if (obtainStyledAttributes.hasValue(C2824R.styleable.PullToRefresh_ptrMode)) {
            this.mode = obtainStyledAttributes.getInteger(C2824R.styleable.PullToRefresh_ptrMode, 3);
        }
        this.refreshContainer = new FrameLayout(context);
        addView(this.refreshContainer, new LayoutParams(-1, 0, 1.0f));
        this.refreshableView = createRefreshableView(context, attributeSet);
        addRefreshableView(context, this.refreshableView);
        String string = context.getString(C2824R.string.pull_down_to_refresh_pull_label);
        String string2 = context.getString(C2824R.string.pull_up_to_refresh_pull_label);
        String string3 = context.getString(C2824R.string.pull_to_refresh_refreshing_label);
        String string4 = context.getString(C2824R.string.pull_to_refresh_release_label);
        if (this.mode == 1 || this.mode == 3) {
            setPullHeaderView(new LoadingLayout(context, 1, string4, string, string3));
        }
        if (this.mode == 2 || this.mode == 3) {
            setPullFooterView(new LoadingLayout(context, 2, string4, string2, string3));
        }
        if (obtainStyledAttributes.hasValue(C2824R.styleable.PullToRefresh_ptrHeaderBackground)) {
            setBackgroundResource(obtainStyledAttributes.getResourceId(C2824R.styleable.PullToRefresh_ptrHeaderBackground, -1));
        }
        if (obtainStyledAttributes.hasValue(C2824R.styleable.PullToRefresh_adapterViewBackground)) {
            this.refreshableView.setBackgroundResource(obtainStyledAttributes.getResourceId(C2824R.styleable.PullToRefresh_adapterViewBackground, -1));
        }
        obtainStyledAttributes.recycle();
        refreshPadding();
        if (this.mode != 3) {
            this.currentMode = this.mode;
        }
    }

    private void refreshPadding() {
        switch (this.mode) {
            case 2:
                setPadding(0, 0, 0, -this.footerHeight);
                return;
            case 3:
                setPadding(0, -this.headerHeight, 0, -this.footerHeight);
                return;
            default:
                setPadding(0, -this.headerHeight, 0, 0);
                return;
        }
    }

    private void measureView(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, Ints.MAX_POWER_OF_TWO);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    private boolean pullEvent() {
        int round;
        int scrollY = getScrollY();
        switch (this.currentMode) {
            case 2:
                round = Math.round(Math.max(this.initialMotionY - this.lastMotionY, 0.0f) / FRICTION);
                if (this.state == 4) {
                    round += this.footerHeight;
                    break;
                }
                break;
            default:
                round = Math.round(Math.min(this.initialMotionY - this.lastMotionY, 0.0f) / FRICTION);
                if (this.state == 4 || this.state == 5) {
                    round -= this.headerHeight;
                    break;
                }
        }
        setHeaderScroll(round);
        if (round != 0) {
            if (this.state < 4) {
                switch (this.currentMode) {
                    case 1:
                        this.headerLayout.updateRefresh(round, this.headerHeight);
                        break;
                    case 2:
                        this.footerLayout.updateRefresh(round, this.footerHeight);
                        break;
                }
            }
            if (this.state == 1 && (this.headerHeight < Math.abs(round) || this.footerHeight < Math.abs(round))) {
                this.state = 3;
                switch (this.currentMode) {
                    case 1:
                        this.headerLayout.releaseToRefresh();
                        return true;
                    case 2:
                        this.footerLayout.releaseToRefresh();
                        return true;
                    default:
                        return true;
                }
            } else if (this.state == 3 && this.headerHeight >= Math.abs(round)) {
                this.state = 1;
                switch (this.currentMode) {
                    case 1:
                        this.headerLayout.pullToRefresh();
                        return true;
                    case 2:
                        this.footerLayout.pullToRefresh();
                        return true;
                    default:
                        return true;
                }
            }
        }
        return scrollY != round;
    }

    private boolean isReadyForPull() {
        return isReadyForPullUp() || isReadyForPullDown();
    }

    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }

    public void disablePull() {
        this.pullFooterView.setVisibility(4);
        this.pullHeaderView.setVisibility(4);
        this.canPullDownRefresh = false;
        this.canPullUpRefresh = false;
    }

    public void enablePull() {
        this.pullFooterView.setVisibility(0);
        this.pullHeaderView.setVisibility(0);
        this.canPullDownRefresh = true;
        this.canPullUpRefresh = true;
    }

    public void disablePullUp() {
        this.pullFooterView.setVisibility(4);
        this.canPullUpRefresh = false;
    }

    public void enablePullUp() {
        this.pullFooterView.setVisibility(0);
        this.canPullUpRefresh = true;
    }

    public void disablePullDown() {
        this.pullHeaderView.setVisibility(4);
        this.canPullDownRefresh = false;
    }

    public long getUpdateTime() {
        return this.headerLayout.getUpdateTime();
    }

    public void setUpdateTime(long j) {
        this.headerLayout.setUpdateTime(j);
    }

    private long getTime() {
        return new Date().getTime();
    }

    public void setEmptyView(View view) {
        if (this.emptyView != view) {
            this.refreshContainer.removeView(this.emptyView);
        }
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            layoutParams.topMargin = -DensityUtil.dip2px(40.0f, getContext());
            this.refreshContainer.addView(view, 0, layoutParams);
            this.emptyView = view;
            this.emptyView.setVisibility(8);
        }
    }

    public void addEmptyView(View view) {
        if (view != null) {
            this.refreshContainer.removeAllViews();
            this.refreshContainer.addView(view);
        }
    }

    public void showEmptyView() {
        if (this.emptyView != null) {
            this.emptyView.setVisibility(0);
            this.emptyView.bringToFront();
            this.refreshContainer.bringChildToFront(this.emptyView);
        }
    }

    public void hideEmptyView() {
        if (this.emptyView != null) {
            this.emptyView.setVisibility(8);
        }
    }

    public void setPullHeaderView(PullableView pullableView) {
        if (pullableView != null) {
            if (this.pullHeaderView != null) {
                removeView(this.pullHeaderView);
            }
            this.headerLayout = pullableView;
            this.pullHeaderView = this.headerLayout.getView();
            addView(this.pullHeaderView, 0, new LayoutParams(-1, -2));
            measureView(this.pullHeaderView);
            this.headerHeight = this.pullHeaderView.getMeasuredHeight();
            refreshPadding();
        }
    }

    public void setPullHeaderTextColor(int i) {
        if (this.headerLayout != null) {
            this.headerLayout.setTextColor(i);
        }
        if (this.footerLayout != null) {
            this.footerLayout.setTextColor(i);
        }
    }

    public void setPullFooterView(PullableView pullableView) {
        if (pullableView != null) {
            if (this.pullFooterView != null) {
                removeView(this.pullFooterView);
            }
            this.footerLayout = pullableView;
            this.pullFooterView = this.footerLayout.getView();
            addView(this.pullFooterView, new LayoutParams(-1, -2));
            measureView(this.pullFooterView);
            this.footerHeight = this.pullFooterView.getMeasuredHeight();
            refreshPadding();
        }
    }

    public void setHeaderOffset(int i) {
        this.headerOffset = i;
    }
}
