package io.rong.imkit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class ProviderContainerView extends FrameLayout {
    Map<Class<? extends IContainerItemProvider>, View> mContentViewMap;
    View mInflateView;
    int mMaxContainSize = 3;
    Map<Class<? extends IContainerItemProvider>, AtomicInteger> mViewCounterMap;

    public ProviderContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            init(attributeSet);
        }
    }

    private void init(AttributeSet attributeSet) {
        this.mViewCounterMap = new HashMap();
        this.mContentViewMap = new HashMap();
    }

    public <T extends IContainerItemProvider> View inflate(T t) {
        View view = null;
        if (this.mInflateView != null) {
            this.mInflateView.setVisibility(8);
        }
        if (this.mContentViewMap.containsKey(t.getClass())) {
            view = (View) this.mContentViewMap.get(t.getClass());
            this.mInflateView = view;
            ((AtomicInteger) this.mViewCounterMap.get(t.getClass())).incrementAndGet();
        }
        if (view == null) {
            recycle();
            view = t.newView(getContext(), this);
            if (view != null) {
                super.addView(view);
                this.mContentViewMap.put(t.getClass(), view);
                this.mViewCounterMap.put(t.getClass(), new AtomicInteger());
            }
            this.mInflateView = view;
        } else if (view.getVisibility() == 8) {
            view.setVisibility(0);
        }
        return view;
    }

    public View getCurrentInflateView() {
        return this.mInflateView;
    }

    public void containerViewLeft() {
        if (this.mInflateView != null) {
            ((LayoutParams) this.mInflateView.getLayoutParams()).gravity = 19;
        }
    }

    public void containerViewRight() {
        if (this.mInflateView != null) {
            ((LayoutParams) this.mInflateView.getLayoutParams()).gravity = 21;
        }
    }

    public void containerViewCenter() {
        if (this.mInflateView != null) {
            ((LayoutParams) this.mInflateView.getLayoutParams()).gravity = 17;
        }
    }

    private void recycle() {
        if (this.mInflateView != null && getChildCount() >= this.mMaxContainSize) {
            Entry entry = null;
            for (Entry entry2 : this.mViewCounterMap.entrySet()) {
                Entry entry22;
                if (entry == null) {
                    entry = entry22;
                }
                if (((AtomicInteger) entry.getValue()).get() <= ((AtomicInteger) entry22.getValue()).get()) {
                    entry22 = entry;
                }
                entry = entry22;
            }
            this.mViewCounterMap.remove(entry.getKey());
            removeView((View) this.mContentViewMap.remove(entry.getKey()));
        }
    }
}
