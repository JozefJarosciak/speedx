package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

abstract class ViewHighlighter {

    private static final class NoopHighlighter extends ViewHighlighter {
        private NoopHighlighter() {
        }

        public void clearHighlight() {
        }

        public void setHighlightedView(View view, int i) {
        }
    }

    @TargetApi(18)
    private static final class OverlayHighlighter extends ViewHighlighter {
        private AtomicInteger mContentColor = new AtomicInteger();
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
        private final Runnable mHighlightViewOnUiThreadRunnable = new C31671();
        private View mHighlightedView;
        private AtomicReference<View> mViewToHighlight = new AtomicReference();

        /* renamed from: com.facebook.stetho.inspector.elements.android.ViewHighlighter$OverlayHighlighter$1 */
        class C31671 implements Runnable {
            C31671() {
            }

            public void run() {
                OverlayHighlighter.this.highlightViewOnUiThread();
            }
        }

        public void clearHighlight() {
            setHighlightedViewImpl(null, 0);
        }

        public void setHighlightedView(View view, int i) {
            setHighlightedViewImpl((View) Util.throwIfNull(view), i);
        }

        private void setHighlightedViewImpl(View view, int i) {
            this.mHandler.removeCallbacks(this.mHighlightViewOnUiThreadRunnable);
            this.mViewToHighlight.set(view);
            this.mContentColor.set(i);
            this.mHandler.postDelayed(this.mHighlightViewOnUiThreadRunnable, 100);
        }

        private void highlightViewOnUiThread() {
            View view = (View) this.mViewToHighlight.getAndSet(null);
            if (view != this.mHighlightedView) {
                if (this.mHighlightedView != null) {
                    this.mHighlightOverlays.removeHighlight(this.mHighlightedView);
                }
                if (view != null) {
                    this.mHighlightOverlays.highlightView(view, this.mContentColor.get());
                }
                this.mHighlightedView = view;
            }
        }
    }

    public abstract void clearHighlight();

    public abstract void setHighlightedView(View view, int i);

    public static ViewHighlighter newInstance() {
        if (VERSION.SDK_INT >= 18) {
            return new OverlayHighlighter();
        }
        LogUtil.m15288w("Running on pre-JBMR2: View highlighting is not supported");
        return new NoopHighlighter();
    }

    protected ViewHighlighter() {
    }
}
