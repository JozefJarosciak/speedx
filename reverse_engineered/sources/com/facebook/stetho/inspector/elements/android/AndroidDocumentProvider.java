package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Predicate;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.ViewUtil;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.DocumentProvider;
import com.facebook.stetho.inspector.elements.DocumentProviderListener;
import com.facebook.stetho.inspector.elements.NodeDescriptor;
import com.facebook.stetho.inspector.elements.ObjectDescriptor;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayList;
import java.util.List;

final class AndroidDocumentProvider extends ThreadBoundProxy implements DocumentProvider, AndroidDescriptorHost {
    private static final int INSPECT_HOVER_COLOR = 1077952767;
    private static final int INSPECT_OVERLAY_COLOR = 1090519039;
    private static final long REPORT_CHANGED_INTERVAL_MS = 1000;
    private final Application mApplication;
    private final DescriptorMap mDescriptorMap;
    private final AndroidDocumentRoot mDocumentRoot;
    private final ViewHighlighter mHighlighter;
    private final InspectModeHandler mInspectModeHandler;
    private boolean mIsReportChangesTimerPosted = false;
    private DocumentProviderListener mListener;
    private final Runnable mReportChangesTimer = new C31581();

    /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$1 */
    class C31581 implements Runnable {
        C31581() {
        }

        public void run() {
            AndroidDocumentProvider.this.mIsReportChangesTimerPosted = false;
            if (AndroidDocumentProvider.this.mListener != null) {
                AndroidDocumentProvider.this.mListener.onPossiblyChanged();
                AndroidDocumentProvider.this.mIsReportChangesTimerPosted = true;
                AndroidDocumentProvider.this.postDelayed(this, 1000);
            }
        }
    }

    private final class InspectModeHandler {
        private List<View> mOverlays;
        private final Predicate<View> mViewSelector;

        /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$InspectModeHandler$1 */
        class C31601 implements Predicate<View> {
            C31601() {
            }

            public boolean apply(View view) {
                return !(view instanceof DocumentHiddenView);
            }
        }

        /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$InspectModeHandler$2 */
        class C31612 implements Accumulator<Window> {
            C31612() {
            }

            public void store(Window window) {
                if (window.peekDecorView() instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) window.peekDecorView();
                    View overlayView = new OverlayView(AndroidDocumentProvider.this.mApplication);
                    LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    viewGroup.addView(overlayView, layoutParams);
                    viewGroup.bringChildToFront(overlayView);
                    InspectModeHandler.this.mOverlays.add(overlayView);
                }
            }
        }

        private final class OverlayView extends DocumentHiddenView {
            public OverlayView(Context context) {
                super(context);
            }

            protected void onDraw(Canvas canvas) {
                canvas.drawColor(AndroidDocumentProvider.INSPECT_OVERLAY_COLOR);
                super.onDraw(canvas);
            }

            public boolean onTouchEvent(MotionEvent motionEvent) {
                if (getParent() instanceof View) {
                    View hitTest = ViewUtil.hitTest((View) getParent(), motionEvent.getX(), motionEvent.getY(), InspectModeHandler.this.mViewSelector);
                    if (!(motionEvent.getAction() == 3 || hitTest == null)) {
                        AndroidDocumentProvider.this.mHighlighter.setHighlightedView(hitTest, AndroidDocumentProvider.INSPECT_HOVER_COLOR);
                        if (motionEvent.getAction() == 1 && AndroidDocumentProvider.this.mListener != null) {
                            AndroidDocumentProvider.this.mListener.onInspectRequested(hitTest);
                        }
                    }
                }
                return true;
            }
        }

        private InspectModeHandler() {
            this.mViewSelector = new C31601();
        }

        public void enable() {
            AndroidDocumentProvider.this.verifyThreadAccess();
            if (this.mOverlays != null) {
                disable();
            }
            this.mOverlays = new ArrayList();
            AndroidDocumentProvider.this.getWindows(new C31612());
        }

        public void disable() {
            AndroidDocumentProvider.this.verifyThreadAccess();
            if (this.mOverlays != null) {
                for (int i = 0; i < this.mOverlays.size(); i++) {
                    View view = (View) this.mOverlays.get(i);
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                this.mOverlays = null;
            }
        }
    }

    public AndroidDocumentProvider(Application application, ThreadBound threadBound) {
        super(threadBound);
        this.mApplication = (Application) Util.throwIfNull(application);
        this.mDocumentRoot = new AndroidDocumentRoot(application);
        this.mDescriptorMap = new DescriptorMap().beginInit().register(Activity.class, new ActivityDescriptor()).register(AndroidDocumentRoot.class, this.mDocumentRoot).register(Application.class, new ApplicationDescriptor()).register(Dialog.class, new DialogDescriptor());
        DialogFragmentDescriptor.register(this.mDescriptorMap);
        FragmentDescriptor.register(this.mDescriptorMap).register(Object.class, new ObjectDescriptor()).register(TextView.class, new TextViewDescriptor()).register(View.class, new ViewDescriptor()).register(ViewGroup.class, new ViewGroupDescriptor()).register(Window.class, new WindowDescriptor()).setHost(this).endInit();
        this.mHighlighter = ViewHighlighter.newInstance();
        this.mInspectModeHandler = new InspectModeHandler();
    }

    public void dispose() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
        this.mInspectModeHandler.disable();
        removeCallbacks(this.mReportChangesTimer);
        this.mIsReportChangesTimerPosted = false;
        this.mListener = null;
    }

    public void setListener(DocumentProviderListener documentProviderListener) {
        verifyThreadAccess();
        this.mListener = documentProviderListener;
        if (this.mListener == null && this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = false;
            removeCallbacks(this.mReportChangesTimer);
        } else if (this.mListener != null && !this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = true;
            postDelayed(this.mReportChangesTimer, 1000);
        }
    }

    public Object getRootElement() {
        verifyThreadAccess();
        return this.mDocumentRoot;
    }

    public NodeDescriptor getNodeDescriptor(Object obj) {
        verifyThreadAccess();
        return getDescriptor(obj);
    }

    public void highlightElement(Object obj, int i) {
        verifyThreadAccess();
        View highlightingView = getHighlightingView(obj);
        if (highlightingView == null) {
            this.mHighlighter.clearHighlight();
        } else {
            this.mHighlighter.setHighlightedView(highlightingView, i);
        }
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
    }

    public void setInspectModeEnabled(boolean z) {
        verifyThreadAccess();
        if (z) {
            this.mInspectModeHandler.enable();
        } else {
            this.mInspectModeHandler.disable();
        }
    }

    public void setAttributesAsText(Object obj, String str) {
        verifyThreadAccess();
        Descriptor descriptor = this.mDescriptorMap.get(obj.getClass());
        if (descriptor != null) {
            descriptor.setAttributesAsText(obj, str);
        }
    }

    public Descriptor getDescriptor(Object obj) {
        return obj == null ? null : this.mDescriptorMap.get(obj.getClass());
    }

    public void onAttributeModified(Object obj, String str, String str2) {
        if (this.mListener != null) {
            this.mListener.onAttributeModified(obj, str, str2);
        }
    }

    public void onAttributeRemoved(Object obj, String str) {
        if (this.mListener != null) {
            this.mListener.onAttributeRemoved(obj, str);
        }
    }

    public View getHighlightingView(Object obj) {
        if (obj == null) {
            return null;
        }
        Descriptor descriptor = null;
        Class cls = obj.getClass();
        View view = null;
        while (view == null && cls != null) {
            Descriptor descriptor2 = this.mDescriptorMap.get(cls);
            if (descriptor2 == null) {
                return null;
            }
            if (descriptor2 != descriptor && (descriptor2 instanceof HighlightableDescriptor)) {
                view = ((HighlightableDescriptor) descriptor2).getViewForHighlighting(obj);
            }
            cls = cls.getSuperclass();
            descriptor = descriptor2;
        }
        return view;
    }

    private void getWindows(final Accumulator<Window> accumulator) {
        Descriptor descriptor = getDescriptor(this.mApplication);
        if (descriptor != null) {
            descriptor.getChildren(this.mApplication, new Accumulator<Object>() {
                public void store(Object obj) {
                    if (obj instanceof Window) {
                        accumulator.store((Window) obj);
                        return;
                    }
                    Descriptor descriptor = AndroidDocumentProvider.this.getDescriptor(obj);
                    if (descriptor != null) {
                        descriptor.getChildren(obj, this);
                    }
                }
            });
        }
    }
}
