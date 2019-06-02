package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.StickyListHeadersListView;

public class MyViewPager extends ViewPager {
    /* renamed from: a */
    private SwipeRefreshAndLoadLayout f10053a;
    /* renamed from: b */
    private StickyListHeadersListView f10054b;
    /* renamed from: c */
    private SlidingUpPanelLayout f10055c;

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyViewPager(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m11036a(SwipeRefreshAndLoadLayout swipeRefreshAndLoadLayout, StickyListHeadersListView stickyListHeadersListView) {
        this.f10053a = swipeRefreshAndLoadLayout;
        this.f10054b = stickyListHeadersListView;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f10053a != null) {
            this.f10053a.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10054b != null) {
            this.f10054b.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10055c != null) {
            this.f10055c.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f10053a != null) {
            this.f10053a.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10054b != null) {
            this.f10054b.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10055c != null) {
            this.f10055c.requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f10053a != null) {
            this.f10053a.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10054b != null) {
            this.f10054b.requestDisallowInterceptTouchEvent(true);
        }
        if (this.f10055c != null) {
            this.f10055c.requestDisallowInterceptTouchEvent(true);
        }
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                if (this.f10053a != null) {
                    this.f10053a.setEnabled(true);
                }
                if (this.f10054b != null) {
                    this.f10054b.setCanTouch(true);
                }
                if (this.f10055c != null) {
                    this.f10055c.setEnabled(true);
                    break;
                }
                break;
            case 2:
                if (this.f10053a != null) {
                    this.f10053a.setEnabled(false);
                }
                if (this.f10054b != null) {
                    this.f10054b.setCanTouch(false);
                }
                if (this.f10055c != null) {
                    this.f10055c.setEnabled(false);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
