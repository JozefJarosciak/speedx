package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.media.TransportMediator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.alipay.sdk.util.C0880h;
import com.google.common.primitives.Ints;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VerticalViewPager extends ViewGroup {
    /* renamed from: a */
    private static final int[] f9101a = new int[]{16842931};
    private static final C2023g ag = new C2023g();
    /* renamed from: c */
    private static final Comparator<C2018b> f9102c = new C20131();
    /* renamed from: d */
    private static final Interpolator f9103d = new C20142();
    /* renamed from: A */
    private boolean f9104A;
    /* renamed from: B */
    private boolean f9105B;
    /* renamed from: C */
    private int f9106C;
    /* renamed from: D */
    private int f9107D;
    /* renamed from: E */
    private int f9108E;
    /* renamed from: F */
    private float f9109F;
    /* renamed from: G */
    private float f9110G;
    /* renamed from: H */
    private float f9111H;
    /* renamed from: I */
    private float f9112I;
    /* renamed from: J */
    private int f9113J = -1;
    /* renamed from: K */
    private VelocityTracker f9114K;
    /* renamed from: L */
    private int f9115L;
    /* renamed from: M */
    private int f9116M;
    /* renamed from: N */
    private int f9117N;
    /* renamed from: O */
    private int f9118O;
    /* renamed from: P */
    private boolean f9119P;
    /* renamed from: Q */
    private EdgeEffectCompat f9120Q;
    /* renamed from: R */
    private EdgeEffectCompat f9121R;
    /* renamed from: S */
    private boolean f9122S = true;
    /* renamed from: T */
    private boolean f9123T = false;
    /* renamed from: U */
    private boolean f9124U;
    /* renamed from: V */
    private int f9125V;
    /* renamed from: W */
    private OnPageChangeListener f9126W;
    private OnPageChangeListener aa;
    private C2021e ab;
    private PageTransformer ac;
    private Method ad;
    private int ae;
    private ArrayList<View> af;
    private final Runnable ah = new C20153(this);
    private int ai = 0;
    private float aj = 0.0f;
    /* renamed from: b */
    private int f9127b;
    /* renamed from: e */
    private final ArrayList<C2018b> f9128e = new ArrayList();
    /* renamed from: f */
    private final C2018b f9129f = new C2018b();
    /* renamed from: g */
    private final Rect f9130g = new Rect();
    /* renamed from: h */
    private PagerAdapter f9131h;
    /* renamed from: i */
    private int f9132i;
    /* renamed from: j */
    private int f9133j = -1;
    /* renamed from: k */
    private Parcelable f9134k = null;
    /* renamed from: l */
    private ClassLoader f9135l = null;
    /* renamed from: m */
    private Scroller f9136m;
    /* renamed from: n */
    private C2022f f9137n;
    /* renamed from: o */
    private int f9138o;
    /* renamed from: p */
    private Drawable f9139p;
    /* renamed from: q */
    private int f9140q;
    /* renamed from: r */
    private int f9141r;
    /* renamed from: s */
    private float f9142s = -3.4028235E38f;
    /* renamed from: t */
    private float f9143t = Float.MAX_VALUE;
    /* renamed from: u */
    private int f9144u;
    /* renamed from: v */
    private int f9145v;
    /* renamed from: w */
    private boolean f9146w;
    /* renamed from: x */
    private boolean f9147x;
    /* renamed from: y */
    private boolean f9148y;
    /* renamed from: z */
    private int f9149z = 1;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$1 */
    static class C20131 implements Comparator<C2018b> {
        C20131() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m10400a((C2018b) obj, (C2018b) obj2);
        }

        /* renamed from: a */
        public int m10400a(C2018b c2018b, C2018b c2018b2) {
            return c2018b.f9089b - c2018b2.f9089b;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$2 */
    static class C20142 implements Interpolator {
        C20142() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$3 */
    class C20153 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ VerticalViewPager f9084a;

        C20153(VerticalViewPager verticalViewPager) {
            this.f9084a = verticalViewPager;
        }

        public void run() {
            this.f9084a.setScrollState(0);
            this.f9084a.m10441c();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new C20161());
        /* renamed from: a */
        int f9085a;
        /* renamed from: b */
        Parcelable f9086b;
        /* renamed from: c */
        ClassLoader f9087c;

        /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$SavedState$1 */
        static class C20161 implements ParcelableCompatCreatorCallbacks<SavedState> {
            C20161() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return m10401a(parcel, classLoader);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m10402a(i);
            }

            /* renamed from: a */
            public SavedState m10401a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState[] m10402a(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f9085a);
            parcel.writeParcelable(this.f9086b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f9085a + C0880h.f2222d;
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.f9085a = parcel.readInt();
            this.f9086b = parcel.readParcelable(classLoader);
            this.f9087c = classLoader;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$a */
    interface C2017a {
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$b */
    static class C2018b {
        /* renamed from: a */
        Object f9088a;
        /* renamed from: b */
        int f9089b;
        /* renamed from: c */
        boolean f9090c;
        /* renamed from: d */
        float f9091d;
        /* renamed from: e */
        float f9092e;

        C2018b() {
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$c */
    public static class C2019c extends LayoutParams {
        /* renamed from: a */
        public boolean f9093a;
        /* renamed from: b */
        public int f9094b;
        /* renamed from: c */
        float f9095c = 0.0f;
        /* renamed from: d */
        boolean f9096d;
        /* renamed from: e */
        int f9097e;
        /* renamed from: f */
        int f9098f;

        public C2019c() {
            super(-1, -1);
        }

        public C2019c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VerticalViewPager.f9101a);
            this.f9094b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$d */
    class C2020d extends AccessibilityDelegateCompat {
        /* renamed from: a */
        final /* synthetic */ VerticalViewPager f9099a;

        C2020d(VerticalViewPager verticalViewPager) {
            this.f9099a = verticalViewPager;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat obtain = AccessibilityRecordCompat.obtain();
            obtain.setScrollable(m10403a());
            if (accessibilityEvent.getEventType() == 4096 && this.f9099a.f9131h != null) {
                obtain.setItemCount(this.f9099a.f9131h.getCount());
                obtain.setFromIndex(this.f9099a.f9132i);
                obtain.setToIndex(this.f9099a.f9132i);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(m10403a());
            if (this.f9099a.m10442c(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (this.f9099a.m10442c(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.f9099a.m10442c(1)) {
                        return false;
                    }
                    this.f9099a.setCurrentItem(this.f9099a.f9132i + 1);
                    return true;
                case 8192:
                    if (!this.f9099a.m10442c(-1)) {
                        return false;
                    }
                    this.f9099a.setCurrentItem(this.f9099a.f9132i - 1);
                    return true;
                default:
                    return false;
            }
        }

        /* renamed from: a */
        private boolean m10403a() {
            return this.f9099a.f9131h != null && this.f9099a.f9131h.getCount() > 1;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$e */
    interface C2021e {
        /* renamed from: a */
        void m10404a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$f */
    private class C2022f extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ VerticalViewPager f9100a;

        private C2022f(VerticalViewPager verticalViewPager) {
            this.f9100a = verticalViewPager;
        }

        public void onChanged() {
            this.f9100a.m10440b();
        }

        public void onInvalidated() {
            this.f9100a.m10440b();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager$g */
    static class C2023g implements Comparator<View> {
        C2023g() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m10405a((View) obj, (View) obj2);
        }

        /* renamed from: a */
        public int m10405a(View view, View view2) {
            C2019c c2019c = (C2019c) view.getLayoutParams();
            C2019c c2019c2 = (C2019c) view2.getLayoutParams();
            if (c2019c.f9093a != c2019c2.f9093a) {
                return c2019c.f9093a ? 1 : -1;
            } else {
                return c2019c.f9097e - c2019c2.f9097e;
            }
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        m10429a();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10429a();
    }

    /* renamed from: a */
    void m10429a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f9136m = new Scroller(context, f9103d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f9108E = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.f9115L = (int) (400.0f * f);
        this.f9116M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f9120Q = new EdgeEffectCompat(context);
        this.f9121R = new EdgeEffectCompat(context);
        this.f9117N = (int) (25.0f * f);
        this.f9118O = (int) (2.0f * f);
        this.f9106C = (int) (16.0f * f);
        ViewCompat.setAccessibilityDelegate(this, new C2020d(this));
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ah);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int i) {
        if (this.ai != i) {
            this.ai = i;
            if (this.ac != null) {
                m10417b(i != 0);
            }
            if (this.f9126W != null) {
                this.f9126W.onPageScrollStateChanged(i);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.f9131h != null) {
            this.f9131h.unregisterDataSetObserver(this.f9137n);
            this.f9131h.startUpdate(this);
            for (int i = 0; i < this.f9128e.size(); i++) {
                C2018b c2018b = (C2018b) this.f9128e.get(i);
                this.f9131h.destroyItem(this, c2018b.f9089b, c2018b.f9088a);
            }
            this.f9131h.finishUpdate(this);
            this.f9128e.clear();
            m10422g();
            this.f9132i = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.f9131h;
        this.f9131h = pagerAdapter;
        this.f9127b = 0;
        if (this.f9131h != null) {
            if (this.f9137n == null) {
                this.f9137n = new C2022f();
            }
            this.f9131h.registerDataSetObserver(this.f9137n);
            this.f9148y = false;
            boolean z = this.f9122S;
            this.f9122S = true;
            this.f9127b = this.f9131h.getCount();
            if (this.f9133j >= 0) {
                this.f9131h.restoreState(this.f9134k, this.f9135l);
                m10434a(this.f9133j, false, true);
                this.f9133j = -1;
                this.f9134k = null;
                this.f9135l = null;
            } else if (z) {
                requestLayout();
            } else {
                m10441c();
            }
        }
        if (this.ab != null && pagerAdapter2 != pagerAdapter) {
            this.ab.m10404a(pagerAdapter2, pagerAdapter);
        }
    }

    /* renamed from: g */
    private void m10422g() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((C2019c) getChildAt(i).getLayoutParams()).f9093a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.f9131h;
    }

    void setOnAdapterChangeListener(C2021e c2021e) {
        this.ab = c2021e;
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.f9148y = false;
        if (this.f9122S) {
            z = false;
        } else {
            z = true;
        }
        m10434a(i, z, false);
    }

    /* renamed from: a */
    public void m10433a(int i, boolean z) {
        this.f9148y = false;
        m10434a(i, z, false);
    }

    public int getCurrentItem() {
        return this.f9132i;
    }

    /* renamed from: a */
    void m10434a(int i, boolean z, boolean z2) {
        m10435a(i, z, z2, 0);
    }

    /* renamed from: a */
    void m10435a(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.f9131h == null || this.f9131h.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.f9132i != i || this.f9128e.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.f9131h.getCount()) {
                i = this.f9131h.getCount() - 1;
            }
            int i3 = this.f9149z;
            if (i > this.f9132i + i3 || i < this.f9132i - i3) {
                for (int i4 = 0; i4 < this.f9128e.size(); i4++) {
                    ((C2018b) this.f9128e.get(i4)).f9090c = true;
                }
            }
            if (this.f9132i != i) {
                z3 = true;
            }
            if (this.f9122S) {
                this.f9132i = i;
                if (z3 && this.f9126W != null) {
                    this.f9126W.onPageSelected(i);
                }
                if (z3 && this.aa != null) {
                    this.aa.onPageSelected(i);
                }
                requestLayout();
                return;
            }
            m10430a(i);
            m10410a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    /* renamed from: a */
    private void m10410a(int i, boolean z, int i2, boolean z2) {
        int max;
        C2018b b = m10438b(i);
        if (b != null) {
            max = (int) (Math.max(this.f9142s, Math.min(b.f9092e, this.f9143t)) * ((float) getClientHeight()));
        } else {
            max = 0;
        }
        if (z) {
            m10432a(0, max, i2);
            if (z2 && this.f9126W != null) {
                this.f9126W.onPageSelected(i);
            }
            if (z2 && this.aa != null) {
                this.aa.onPageSelected(i);
                return;
            }
            return;
        }
        if (z2 && this.f9126W != null) {
            this.f9126W.onPageSelected(i);
        }
        if (z2 && this.aa != null) {
            this.aa.onPageSelected(i);
        }
        m10414a(false);
        scrollTo(0, max);
        m10420e(max);
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f9126W = onPageChangeListener;
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ad == null) {
                try {
                    this.ad = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ad.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ae == 2) {
            i2 = (i - 1) - i2;
        }
        return ((C2019c) ((View) this.af.get(i2)).getLayoutParams()).f9098f;
    }

    public int getOffscreenPageLimit() {
        return this.f9149z;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.f9149z) {
            this.f9149z = i;
            m10441c();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.f9138o;
        this.f9138o = i;
        int height = getHeight();
        m10409a(height, height, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.f9138o;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f9139p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f9139p;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f9139p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    /* renamed from: a */
    float m10426a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    /* renamed from: a */
    void m10432a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            m10414a(false);
            m10441c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientHeight = getClientHeight();
        int i6 = clientHeight / 2;
        float a = (((float) i6) * m10426a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) clientHeight)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            clientHeight = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            clientHeight = (int) (((((float) Math.abs(i4)) / ((((float) clientHeight) * this.f9131h.getPageWidth(this.f9132i)) + ((float) this.f9138o))) + 1.0f) * 100.0f);
        }
        this.f9136m.startScroll(scrollX, scrollY, i4, i5, Math.min(clientHeight, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* renamed from: a */
    C2018b m10427a(int i, int i2) {
        C2018b c2018b = new C2018b();
        c2018b.f9089b = i;
        c2018b.f9088a = this.f9131h.instantiateItem(this, i);
        c2018b.f9091d = this.f9131h.getPageWidth(i);
        if (i2 < 0 || i2 >= this.f9128e.size()) {
            this.f9128e.add(c2018b);
        } else {
            this.f9128e.add(i2, c2018b);
        }
        return c2018b;
    }

    /* renamed from: b */
    void m10440b() {
        boolean z;
        int count = this.f9131h.getCount();
        this.f9127b = count;
        if (this.f9128e.size() >= (this.f9149z * 2) + 1 || this.f9128e.size() >= count) {
            z = false;
        } else {
            z = true;
        }
        boolean z2 = false;
        int i = this.f9132i;
        boolean z3 = z;
        int i2 = 0;
        while (i2 < this.f9128e.size()) {
            int i3;
            boolean z4;
            int i4;
            boolean z5;
            C2018b c2018b = (C2018b) this.f9128e.get(i2);
            int itemPosition = this.f9131h.getItemPosition(c2018b.f9088a);
            if (itemPosition == -1) {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            } else if (itemPosition == -2) {
                this.f9128e.remove(i2);
                i2--;
                if (!z2) {
                    this.f9131h.startUpdate(this);
                    z2 = true;
                }
                this.f9131h.destroyItem(this, c2018b.f9089b, c2018b.f9088a);
                if (this.f9132i == c2018b.f9089b) {
                    i3 = i2;
                    z4 = z2;
                    i4 = Math.max(0, Math.min(this.f9132i, count - 1));
                    z5 = true;
                } else {
                    i3 = i2;
                    z4 = z2;
                    i4 = i;
                    z5 = true;
                }
            } else if (c2018b.f9089b != itemPosition) {
                if (c2018b.f9089b == this.f9132i) {
                    i = itemPosition;
                }
                c2018b.f9089b = itemPosition;
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = true;
            } else {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            }
            z3 = z5;
            i = i4;
            z2 = z4;
            i2 = i3 + 1;
        }
        if (z2) {
            this.f9131h.finishUpdate(this);
        }
        Collections.sort(this.f9128e, f9102c);
        if (z3) {
            i4 = getChildCount();
            for (i2 = 0; i2 < i4; i2++) {
                C2019c c2019c = (C2019c) getChildAt(i2).getLayoutParams();
                if (!c2019c.f9093a) {
                    c2019c.f9095c = 0.0f;
                }
            }
            m10434a(i, false, true);
            requestLayout();
        }
    }

    /* renamed from: c */
    void m10441c() {
        m10430a(this.f9132i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    void m10430a(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.f9132i;
        r0 = r19;
        if (r4 == r0) goto L_0x033f;
    L_0x000a:
        r0 = r18;
        r2 = r0.f9132i;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
    L_0x0014:
        r0 = r18;
        r3 = r0.f9132i;
        r0 = r18;
        r3 = r0.m10438b(r3);
        r0 = r19;
        r1 = r18;
        r1.f9132i = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.f9131h;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.m10423h();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 33;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.f9148y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.m10423h();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.f9131h;
        r0 = r18;
        r2.startUpdate(r0);
        r0 = r18;
        r2 = r0.f9149z;
        r5 = 0;
        r0 = r18;
        r6 = r0.f9132i;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.f9131h;
        r12 = r5.getCount();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.f9132i;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.f9127b;
        if (r12 == r2) goto L_0x00da;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d0 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d0 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d0 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.f9127b;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.f9131h;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d0:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00da:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00dd:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x033c;
    L_0x00e7:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r5);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
        r7 = r2.f9089b;
        r0 = r18;
        r8 = r0.f9132i;
        if (r7 < r8) goto L_0x01cf;
    L_0x00f9:
        r7 = r2.f9089b;
        r0 = r18;
        r8 = r0.f9132i;
        if (r7 != r8) goto L_0x033c;
    L_0x0101:
        if (r2 != 0) goto L_0x0339;
    L_0x0103:
        if (r12 <= 0) goto L_0x0339;
    L_0x0105:
        r0 = r18;
        r2 = r0.f9132i;
        r0 = r18;
        r2 = r0.m10427a(r2, r5);
        r10 = r2;
    L_0x0110:
        if (r10 == 0) goto L_0x0180;
    L_0x0112:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d4;
    L_0x0117:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r8);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
    L_0x0121:
        r14 = r18.getClientHeight();
        if (r14 > 0) goto L_0x01d7;
    L_0x0127:
        r6 = 0;
    L_0x0128:
        r0 = r18;
        r7 = r0.f9132i;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x0138:
        if (r9 < 0) goto L_0x0142;
    L_0x013a:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x0216;
    L_0x013e:
        if (r9 >= r11) goto L_0x0216;
    L_0x0140:
        if (r2 != 0) goto L_0x01e6;
    L_0x0142:
        r6 = r10.f9091d;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x017b;
    L_0x014c:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x024c;
    L_0x0156:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r9);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
        r7 = r2;
    L_0x0161:
        if (r14 > 0) goto L_0x024f;
    L_0x0163:
        r2 = 0;
        r5 = r2;
    L_0x0165:
        r0 = r18;
        r2 = r0.f9132i;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0171:
        if (r9 >= r12) goto L_0x017b;
    L_0x0173:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029a;
    L_0x0177:
        if (r9 <= r13) goto L_0x029a;
    L_0x0179:
        if (r2 != 0) goto L_0x025c;
    L_0x017b:
        r0 = r18;
        r0.m10412a(r10, r8, r4);
    L_0x0180:
        r0 = r18;
        r4 = r0.f9131h;
        r0 = r18;
        r5 = r0.f9132i;
        if (r10 == 0) goto L_0x02e8;
    L_0x018a:
        r2 = r10.f9088a;
    L_0x018c:
        r0 = r18;
        r4.setPrimaryItem(r0, r5, r2);
        r0 = r18;
        r2 = r0.f9131h;
        r0 = r18;
        r2.finishUpdate(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a0:
        if (r4 >= r5) goto L_0x02eb;
    L_0x01a2:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2019c) r2;
        r2.f9098f = r4;
        r7 = r2.f9093a;
        if (r7 != 0) goto L_0x01cb;
    L_0x01b4:
        r7 = r2.f9095c;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01cb;
    L_0x01bb:
        r0 = r18;
        r6 = r0.m10428a(r6);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c3:
        r7 = r6.f9091d;
        r2.f9095c = r7;
        r6 = r6.f9089b;
        r2.f9097e = r6;
    L_0x01cb:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a0;
    L_0x01cf:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00dd;
    L_0x01d4:
        r2 = 0;
        goto L_0x0121;
    L_0x01d7:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.f9091d;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x0128;
    L_0x01e6:
        r15 = r2.f9089b;
        if (r9 != r15) goto L_0x0210;
    L_0x01ea:
        r15 = r2.f9090c;
        if (r15 != 0) goto L_0x0210;
    L_0x01ee:
        r0 = r18;
        r15 = r0.f9128e;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.f9131h;
        r2 = r2.f9088a;
        r0 = r18;
        r15.destroyItem(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0214;
    L_0x0206:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r5);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
    L_0x0210:
        r9 = r9 + -1;
        goto L_0x0138;
    L_0x0214:
        r2 = 0;
        goto L_0x0210;
    L_0x0216:
        if (r2 == 0) goto L_0x0230;
    L_0x0218:
        r15 = r2.f9089b;
        if (r9 != r15) goto L_0x0230;
    L_0x021c:
        r2 = r2.f9091d;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x022e;
    L_0x0223:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r5);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
        goto L_0x0210;
    L_0x022e:
        r2 = 0;
        goto L_0x0210;
    L_0x0230:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.m10427a(r9, r2);
        r2 = r2.f9091d;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024a;
    L_0x023f:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r5);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
        goto L_0x0210;
    L_0x024a:
        r2 = 0;
        goto L_0x0210;
    L_0x024c:
        r7 = 0;
        goto L_0x0161;
    L_0x024f:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x0165;
    L_0x025c:
        r11 = r2.f9089b;
        if (r9 != r11) goto L_0x0332;
    L_0x0260:
        r11 = r2.f9090c;
        if (r11 != 0) goto L_0x0332;
    L_0x0264:
        r0 = r18;
        r11 = r0.f9128e;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.f9131h;
        r2 = r2.f9088a;
        r0 = r18;
        r11.destroyItem(r0, r9, r2);
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x0298;
    L_0x0280:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r7);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
    L_0x028a:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x028f:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0171;
    L_0x0298:
        r2 = 0;
        goto L_0x028a;
    L_0x029a:
        if (r2 == 0) goto L_0x02c1;
    L_0x029c:
        r11 = r2.f9089b;
        if (r9 != r11) goto L_0x02c1;
    L_0x02a0:
        r2 = r2.f9091d;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02bf;
    L_0x02af:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r7);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
    L_0x02b9:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02bf:
        r2 = 0;
        goto L_0x02b9;
    L_0x02c1:
        r0 = r18;
        r2 = r0.m10427a(r9, r7);
        r7 = r7 + 1;
        r2 = r2.f9091d;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02e6;
    L_0x02d6:
        r0 = r18;
        r2 = r0.f9128e;
        r2 = r2.get(r7);
        r2 = (com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.C2018b) r2;
    L_0x02e0:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02e6:
        r2 = 0;
        goto L_0x02e0;
    L_0x02e8:
        r2 = 0;
        goto L_0x018c;
    L_0x02eb:
        r18.m10423h();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f4:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0330;
    L_0x02fa:
        r0 = r18;
        r2 = r0.m10439b(r2);
    L_0x0300:
        if (r2 == 0) goto L_0x030a;
    L_0x0302:
        r2 = r2.f9089b;
        r0 = r18;
        r4 = r0.f9132i;
        if (r2 == r4) goto L_0x002f;
    L_0x030a:
        r2 = 0;
    L_0x030b:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0311:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.m10428a(r4);
        if (r5 == 0) goto L_0x032d;
    L_0x031f:
        r5 = r5.f9089b;
        r0 = r18;
        r6 = r0.f9132i;
        if (r5 != r6) goto L_0x032d;
    L_0x0327:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x032d:
        r2 = r2 + 1;
        goto L_0x030b;
    L_0x0330:
        r2 = 0;
        goto L_0x0300;
    L_0x0332:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x0339:
        r10 = r2;
        goto L_0x0110;
    L_0x033c:
        r2 = r6;
        goto L_0x0101;
    L_0x033f:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager.a(int):void");
    }

    /* renamed from: h */
    private void m10423h() {
        if (this.ae != 0) {
            if (this.af == null) {
                this.af = new ArrayList();
            } else {
                this.af.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.af.add(getChildAt(i));
            }
            Collections.sort(this.af, ag);
        }
    }

    /* renamed from: a */
    private void m10412a(C2018b c2018b, int i, C2018b c2018b2) {
        float f;
        float f2;
        int i2;
        C2018b c2018b3;
        int i3;
        int count = this.f9131h.getCount();
        int clientHeight = getClientHeight();
        if (clientHeight > 0) {
            f = ((float) this.f9138o) / ((float) clientHeight);
        } else {
            f = 0.0f;
        }
        if (c2018b2 != null) {
            clientHeight = c2018b2.f9089b;
            int i4;
            if (clientHeight < c2018b.f9089b) {
                f2 = (c2018b2.f9092e + c2018b2.f9091d) + f;
                i4 = clientHeight + 1;
                i2 = 0;
                while (i4 <= c2018b.f9089b && i2 < this.f9128e.size()) {
                    c2018b3 = (C2018b) this.f9128e.get(i2);
                    while (i4 > c2018b3.f9089b && i2 < this.f9128e.size() - 1) {
                        i2++;
                        c2018b3 = (C2018b) this.f9128e.get(i2);
                    }
                    while (i4 < c2018b3.f9089b) {
                        f2 += this.f9131h.getPageWidth(i4) + f;
                        i4++;
                    }
                    c2018b3.f9092e = f2;
                    f2 += c2018b3.f9091d + f;
                    i4++;
                }
            } else if (clientHeight > c2018b.f9089b) {
                i2 = this.f9128e.size() - 1;
                f2 = c2018b2.f9092e;
                i4 = clientHeight - 1;
                while (i4 >= c2018b.f9089b && i2 >= 0) {
                    c2018b3 = (C2018b) this.f9128e.get(i2);
                    while (i4 < c2018b3.f9089b && i2 > 0) {
                        i2--;
                        c2018b3 = (C2018b) this.f9128e.get(i2);
                    }
                    while (i4 > c2018b3.f9089b) {
                        f2 -= this.f9131h.getPageWidth(i4) + f;
                        i4--;
                    }
                    f2 -= c2018b3.f9091d + f;
                    c2018b3.f9092e = f2;
                    i4--;
                }
            }
        }
        int size = this.f9128e.size();
        float f3 = c2018b.f9092e;
        i2 = c2018b.f9089b - 1;
        this.f9142s = c2018b.f9089b == 0 ? c2018b.f9092e : -3.4028235E38f;
        this.f9143t = c2018b.f9089b == count + -1 ? (c2018b.f9092e + c2018b.f9091d) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            c2018b3 = (C2018b) this.f9128e.get(i3);
            f2 = f3;
            while (i2 > c2018b3.f9089b) {
                f2 -= this.f9131h.getPageWidth(i2) + f;
                i2--;
            }
            f3 = f2 - (c2018b3.f9091d + f);
            c2018b3.f9092e = f3;
            if (c2018b3.f9089b == 0) {
                this.f9142s = f3;
            }
            i2--;
        }
        f3 = (c2018b.f9092e + c2018b.f9091d) + f;
        i2 = c2018b.f9089b + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            c2018b3 = (C2018b) this.f9128e.get(i3);
            f2 = f3;
            while (i2 < c2018b3.f9089b) {
                f2 = (this.f9131h.getPageWidth(i2) + f) + f2;
                i2++;
            }
            if (c2018b3.f9089b == count - 1) {
                this.f9143t = (c2018b3.f9091d + f2) - 1.0f;
            }
            c2018b3.f9092e = f2;
            f3 = f2 + (c2018b3.f9091d + f);
            i2++;
        }
        this.f9123T = false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f9085a = this.f9132i;
        if (this.f9131h != null) {
            savedState.f9086b = this.f9131h.saveState();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.f9131h != null) {
                this.f9131h.restoreState(savedState.f9086b, savedState.f9087c);
                m10434a(savedState.f9085a, false, true);
                return;
            }
            this.f9133j = savedState.f9085a;
            this.f9134k = savedState.f9086b;
            this.f9135l = savedState.f9087c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (checkLayoutParams(layoutParams)) {
            layoutParams2 = layoutParams;
        } else {
            layoutParams2 = generateLayoutParams(layoutParams);
        }
        C2019c c2019c = (C2019c) layoutParams2;
        c2019c.f9093a |= view instanceof C2017a;
        if (!this.f9146w) {
            super.addView(view, i, layoutParams2);
        } else if (c2019c == null || !c2019c.f9093a) {
            c2019c.f9096d = true;
            addViewInLayout(view, i, layoutParams2);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.f9146w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* renamed from: a */
    C2018b m10428a(View view) {
        for (int i = 0; i < this.f9128e.size(); i++) {
            C2018b c2018b = (C2018b) this.f9128e.get(i);
            if (this.f9131h.isViewFromObject(view, c2018b.f9088a)) {
                return c2018b;
            }
        }
        return null;
    }

    /* renamed from: b */
    C2018b m10439b(View view) {
        while (true) {
            VerticalViewPager parent = view.getParent();
            if (parent == this) {
                return m10428a(view);
            }
            if (parent != null && (parent instanceof View)) {
                view = parent;
            }
        }
        return null;
    }

    /* renamed from: b */
    C2018b m10438b(int i) {
        for (int i2 = 0; i2 < this.f9128e.size(); i2++) {
            C2018b c2018b = (C2018b) this.f9128e.get(i2);
            if (c2018b.f9089b == i) {
                return c2018b;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f9122S = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredHeight = getMeasuredHeight();
        this.f9107D = Math.min(measuredHeight / 10, this.f9106C);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            C2019c c2019c;
            int i5;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                c2019c = (C2019c) childAt.getLayoutParams();
                if (c2019c != null && c2019c.f9093a) {
                    int i6 = c2019c.f9094b & 7;
                    int i7 = c2019c.f9094b & 112;
                    i3 = Integer.MIN_VALUE;
                    i5 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = Ints.MAX_POWER_OF_TWO;
                    } else if (obj2 != null) {
                        i5 = Ints.MAX_POWER_OF_TWO;
                    }
                    if (c2019c.width != -2) {
                        i7 = Ints.MAX_POWER_OF_TWO;
                        i3 = c2019c.width != -1 ? c2019c.width : measuredWidth;
                    } else {
                        i7 = i3;
                        i3 = measuredWidth;
                    }
                    if (c2019c.height != -2) {
                        i5 = Ints.MAX_POWER_OF_TWO;
                        if (c2019c.height != -1) {
                            measuredHeight = c2019c.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredHeight, i5));
                            if (obj != null) {
                                paddingTop -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                measuredWidth -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredHeight = paddingTop;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredHeight, i5));
                    if (obj != null) {
                        paddingTop -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        measuredWidth -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.f9144u = MeasureSpec.makeMeasureSpec(measuredWidth, Ints.MAX_POWER_OF_TWO);
        this.f9145v = MeasureSpec.makeMeasureSpec(paddingTop, Ints.MAX_POWER_OF_TWO);
        this.f9146w = true;
        m10441c();
        this.f9146w = false;
        i3 = getChildCount();
        for (i5 = 0; i5 < i3; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                c2019c = (C2019c) childAt2.getLayoutParams();
                if (c2019c == null || !c2019c.f9093a) {
                    childAt2.measure(this.f9144u, MeasureSpec.makeMeasureSpec((int) (c2019c.f9095c * ((float) paddingTop)), Ints.MAX_POWER_OF_TWO));
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            m10409a(i2, i4, this.f9138o, this.f9138o);
        }
    }

    /* renamed from: a */
    private void m10409a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.f9128e.isEmpty()) {
            C2018b b = m10438b(this.f9132i);
            int min = (int) ((b != null ? Math.min(b.f9092e, this.f9143t) : 0.0f) * ((float) ((i - getPaddingTop()) - getPaddingBottom())));
            if (min != getScrollY()) {
                m10414a(false);
                scrollTo(getScrollX(), min);
                return;
            }
            return;
        }
        int paddingTop = (int) (((float) (((i - getPaddingTop()) - getPaddingBottom()) + i3)) * (((float) getScrollY()) / ((float) (((i2 - getPaddingTop()) - getPaddingBottom()) + i4))));
        scrollTo(getScrollX(), paddingTop);
        if (!this.f9136m.isFinished()) {
            int duration = this.f9136m.getDuration() - this.f9136m.timePassed();
            C2018b b2 = m10438b(this.f9132i);
            if (b2 != null) {
                this.f9136m.startScroll(0, paddingTop, 0, (int) (b2.f9092e * ((float) i)), duration);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        C2019c c2019c;
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollY = getScrollY();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                c2019c = (C2019c) childAt.getLayoutParams();
                if (c2019c.f9093a) {
                    int i9 = c2019c.f9094b & 112;
                    switch (c2019c.f9094b & 7) {
                        case 1:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case 5:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case 16:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case 48:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case 80:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    measuredWidth += scrollY;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i6 - paddingTop) - paddingBottom;
        for (paddingBottom = 0; paddingBottom < childCount; paddingBottom++) {
            View childAt2 = getChildAt(paddingBottom);
            if (childAt2.getVisibility() != 8) {
                c2019c = (C2019c) childAt2.getLayoutParams();
                if (!c2019c.f9093a) {
                    C2018b a = m10428a(childAt2);
                    if (a != null) {
                        i6 = ((int) (a.f9092e * ((float) max))) + paddingTop;
                        if (c2019c.f9096d) {
                            c2019c.f9096d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((i5 - paddingLeft) - paddingRight, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec((int) (c2019c.f9095c * ((float) max)), Ints.MAX_POWER_OF_TWO));
                        }
                        childAt2.layout(paddingLeft, i6, childAt2.getMeasuredWidth() + paddingLeft, childAt2.getMeasuredHeight() + i6);
                    }
                }
            }
        }
        this.f9140q = paddingLeft;
        this.f9141r = i5 - paddingRight;
        this.f9125V = i7;
        if (this.f9122S) {
            m10410a(this.f9132i, false, 0, false);
        }
        this.f9122S = false;
    }

    public void computeScroll() {
        if (this.f9136m.isFinished() || !this.f9136m.computeScrollOffset()) {
            m10414a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f9136m.getCurrX();
        int currY = this.f9136m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!m10420e(currY)) {
                this.f9136m.abortAnimation();
                scrollTo(currX, 0);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* renamed from: e */
    private boolean m10420e(int i) {
        if (this.f9128e.size() == 0) {
            this.f9124U = false;
            m10431a(0, 0.0f, 0);
            if (this.f9124U) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        C2018b i2 = m10424i();
        int clientHeight = getClientHeight();
        int i3 = this.f9138o + clientHeight;
        float f = ((float) this.f9138o) / ((float) clientHeight);
        int i4 = i2.f9089b;
        float f2 = ((((float) i) / ((float) clientHeight)) - i2.f9092e) / (i2.f9091d + f);
        clientHeight = (int) (((float) i3) * f2);
        this.f9124U = false;
        m10431a(i4, f2, clientHeight);
        if (this.f9124U) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* renamed from: a */
    protected void m10431a(int i, float f, int i2) {
        int paddingTop;
        int paddingBottom;
        int i3;
        if (this.f9125V > 0) {
            int scrollY = getScrollY();
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
            int height = getHeight();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                C2019c c2019c = (C2019c) childAt.getLayoutParams();
                if (c2019c.f9093a) {
                    int max;
                    switch (c2019c.f9094b & 112) {
                        case 16:
                            max = Math.max((height - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i4 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                        case 48:
                            max = childAt.getHeight() + paddingTop;
                            i4 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = max;
                            max = i4;
                            break;
                        case 80:
                            max = (height - paddingBottom) - childAt.getMeasuredHeight();
                            i4 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                        default:
                            max = paddingTop;
                            i4 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                    }
                    max = (max + scrollY) - childAt.getTop();
                    if (max != 0) {
                        childAt.offsetTopAndBottom(max);
                    }
                } else {
                    i4 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = i4;
                }
                i3++;
                i4 = paddingTop;
                paddingTop = paddingBottom;
                paddingBottom = i4;
            }
        }
        if (this.f9126W != null) {
            this.f9126W.onPageScrolled(i, f, i2);
        }
        if (this.aa != null) {
            this.aa.onPageScrolled(i, f, i2);
        }
        if (this.ac != null) {
            paddingBottom = getScrollY();
            i3 = getChildCount();
            for (paddingTop = 0; paddingTop < i3; paddingTop++) {
                View childAt2 = getChildAt(paddingTop);
                if (!((C2019c) childAt2.getLayoutParams()).f9093a) {
                    this.ac.transformPage(childAt2, ((float) (childAt2.getTop() - paddingBottom)) / ((float) getClientHeight()));
                }
            }
        }
        this.f9124U = true;
    }

    /* renamed from: a */
    private void m10414a(boolean z) {
        int scrollX;
        boolean z2 = this.ai == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.f9136m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f9136m.getCurrX();
            int currY = this.f9136m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.f9148y = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.f9128e.size(); scrollX++) {
            C2018b c2018b = (C2018b) this.f9128e.get(scrollX);
            if (c2018b.f9090c) {
                c2018b.f9090c = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.postOnAnimation(this, this.ah);
        } else {
            this.ah.run();
        }
    }

    /* renamed from: a */
    private boolean m10415a(float f, float f2) {
        return (f < ((float) this.f9107D) && f2 > 0.0f) || (f > ((float) (getHeight() - this.f9107D)) && f2 < 0.0f);
    }

    /* renamed from: b */
    private void m10417b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int i2;
            if (z) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            ViewCompat.setLayerType(getChildAt(i), i2, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f9104A = false;
            this.f9105B = false;
            this.f9113J = -1;
            if (this.f9114K == null) {
                return false;
            }
            this.f9114K.recycle();
            this.f9114K = null;
            return false;
        }
        if (action != 0) {
            if (this.f9104A) {
                return true;
            }
            if (this.f9105B) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = motionEvent.getX();
                this.f9111H = x;
                this.f9109F = x;
                x = motionEvent.getY();
                this.f9112I = x;
                this.f9110G = x;
                this.f9113J = MotionEventCompat.getPointerId(motionEvent, 0);
                this.f9105B = false;
                this.f9136m.computeScrollOffset();
                if (this.ai == 2 && Math.abs(this.f9136m.getFinalY() - this.f9136m.getCurrY()) > this.f9118O) {
                    this.f9136m.abortAnimation();
                    this.f9148y = false;
                    m10441c();
                    this.f9104A = true;
                    m10419c(true);
                    setScrollState(1);
                    break;
                }
                m10414a(false);
                this.f9104A = false;
                break;
                break;
            case 2:
                action = this.f9113J;
                if (action != -1) {
                    action = MotionEventCompat.findPointerIndex(motionEvent, action);
                    float y = MotionEventCompat.getY(motionEvent, action);
                    float f = y - this.f9110G;
                    float abs = Math.abs(f);
                    float x2 = MotionEventCompat.getX(motionEvent, action);
                    float abs2 = Math.abs(x2 - this.f9111H);
                    if (f == 0.0f || m10415a(this.f9110G, f) || !m10437a(this, false, (int) f, (int) x2, (int) y)) {
                        if (abs > ((float) this.f9108E) && 0.5f * abs > abs2) {
                            this.f9104A = true;
                            m10419c(true);
                            setScrollState(1);
                            this.f9110G = f > 0.0f ? this.f9112I + ((float) this.f9108E) : this.f9112I - ((float) this.f9108E);
                            this.f9109F = x2;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.f9108E)) {
                            this.f9105B = true;
                        }
                        if (this.f9104A && m10418b(y)) {
                            ViewCompat.postInvalidateOnAnimation(this);
                            break;
                        }
                    }
                    this.f9109F = x2;
                    this.f9110G = y;
                    this.f9105B = true;
                    return false;
                }
                break;
            case 6:
                m10411a(motionEvent);
                break;
        }
        if (this.f9114K == null) {
            this.f9114K = VelocityTracker.obtain();
        }
        this.f9114K.addMovement(motionEvent);
        return this.f9104A;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f9132i == 0 && motionEvent.getY() < this.aj && !this.f9104A) {
            return false;
        }
        if (this.f9119P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.f9131h == null || this.f9131h.getCount() == 0) {
            return false;
        }
        if (this.f9114K == null) {
            this.f9114K = VelocityTracker.obtain();
        }
        this.f9114K.addMovement(motionEvent);
        float x;
        int yVelocity;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f9136m.abortAnimation();
                this.f9148y = false;
                m10441c();
                x = motionEvent.getX();
                this.f9111H = x;
                this.f9109F = x;
                x = motionEvent.getY();
                this.f9112I = x;
                this.f9110G = x;
                this.f9113J = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (this.f9104A) {
                    VelocityTracker velocityTracker = this.f9114K;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.f9116M);
                    yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.f9113J);
                    this.f9148y = true;
                    int clientHeight = getClientHeight();
                    int scrollY = getScrollY();
                    C2018b i = m10424i();
                    m10435a(m10406a(i.f9089b, ((((float) scrollY) / ((float) clientHeight)) - i.f9092e) / i.f9091d, yVelocity, (int) (MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.f9113J)) - this.f9112I)), true, true, yVelocity);
                    this.f9113J = -1;
                    m10425j();
                    z = this.f9121R.onRelease() | this.f9120Q.onRelease();
                    break;
                }
                break;
            case 2:
                if (!this.f9104A) {
                    yVelocity = MotionEventCompat.findPointerIndex(motionEvent, this.f9113J);
                    float y = MotionEventCompat.getY(motionEvent, yVelocity);
                    float abs = Math.abs(y - this.f9110G);
                    float x2 = MotionEventCompat.getX(motionEvent, yVelocity);
                    x = Math.abs(x2 - this.f9109F);
                    if (abs > ((float) this.f9108E) && abs > x) {
                        this.f9104A = true;
                        m10419c(true);
                        if (y - this.f9112I > 0.0f) {
                            x = this.f9112I + ((float) this.f9108E);
                        } else {
                            x = this.f9112I - ((float) this.f9108E);
                        }
                        this.f9110G = x;
                        this.f9109F = x2;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.f9104A) {
                    z = false | m10418b(MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.f9113J)));
                    break;
                }
                break;
            case 3:
                if (this.f9104A) {
                    m10410a(this.f9132i, true, 0, false);
                    this.f9113J = -1;
                    m10425j();
                    z = this.f9121R.onRelease() | this.f9120Q.onRelease();
                    break;
                }
                motionEvent.setAction(3);
                return super.onTouchEvent(motionEvent);
            case 5:
                yVelocity = MotionEventCompat.getActionIndex(motionEvent);
                this.f9110G = MotionEventCompat.getY(motionEvent, yVelocity);
                this.f9113J = MotionEventCompat.getPointerId(motionEvent, yVelocity);
                break;
            case 6:
                m10411a(motionEvent);
                this.f9110G = MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.f9113J));
                break;
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    /* renamed from: c */
    private void m10419c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* renamed from: b */
    private boolean m10418b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.f9110G - f;
        this.f9110G = f;
        float scrollY = ((float) getScrollY()) + f3;
        int clientHeight = getClientHeight();
        float f4 = ((float) clientHeight) * this.f9142s;
        float f5 = ((float) clientHeight) * this.f9143t;
        C2018b c2018b = (C2018b) this.f9128e.get(0);
        C2018b c2018b2 = (C2018b) this.f9128e.get(this.f9128e.size() - 1);
        if (c2018b.f9089b != 0) {
            f4 = c2018b.f9092e * ((float) clientHeight);
            z = false;
        } else {
            z = true;
        }
        if (c2018b2.f9089b != this.f9131h.getCount() - 1) {
            f2 = c2018b2.f9092e * ((float) clientHeight);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollY < f4) {
            if (z) {
                z3 = this.f9120Q.onPull(Math.abs(f4 - scrollY) / ((float) clientHeight));
            }
        } else if (scrollY > f2) {
            if (z2) {
                z3 = this.f9121R.onPull(Math.abs(scrollY - f2) / ((float) clientHeight));
            }
            f4 = f2;
        } else {
            f4 = scrollY;
        }
        this.f9109F += f4 - ((float) ((int) f4));
        scrollTo(getScrollX(), (int) f4);
        m10420e((int) f4);
        return z3;
    }

    /* renamed from: i */
    private C2018b m10424i() {
        float f;
        int clientHeight = getClientHeight();
        float scrollY = clientHeight > 0 ? ((float) getScrollY()) / ((float) clientHeight) : 0.0f;
        if (clientHeight > 0) {
            f = ((float) this.f9138o) / ((float) clientHeight);
        } else {
            f = 0.0f;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        C2018b c2018b = null;
        while (i2 < this.f9128e.size()) {
            int i3;
            C2018b c2018b2;
            C2018b c2018b3 = (C2018b) this.f9128e.get(i2);
            C2018b c2018b4;
            if (obj != null || c2018b3.f9089b == i + 1) {
                c2018b4 = c2018b3;
                i3 = i2;
                c2018b2 = c2018b4;
            } else {
                c2018b3 = this.f9129f;
                c2018b3.f9092e = (f2 + f3) + f;
                c2018b3.f9089b = i + 1;
                c2018b3.f9091d = this.f9131h.getPageWidth(c2018b3.f9089b);
                c2018b4 = c2018b3;
                i3 = i2 - 1;
                c2018b2 = c2018b4;
            }
            f2 = c2018b2.f9092e;
            f3 = (c2018b2.f9091d + f2) + f;
            if (obj == null && scrollY < f2) {
                return c2018b;
            }
            if (scrollY < f3 || i3 == this.f9128e.size() - 1) {
                return c2018b2;
            }
            f3 = f2;
            i = c2018b2.f9089b;
            obj = null;
            f2 = c2018b2.f9091d;
            c2018b = c2018b2;
            i2 = i3 + 1;
        }
        return c2018b;
    }

    /* renamed from: a */
    private int m10406a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.f9117N || Math.abs(i2) <= this.f9115L) {
            i = (int) ((i >= this.f9132i ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.f9128e.size() <= 0) {
            return i;
        }
        return Math.max(((C2018b) this.f9128e.get(0)).f9089b, Math.min(i, ((C2018b) this.f9128e.get(this.f9128e.size() - 1)).f9089b));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.f9131h != null && this.f9131h.getCount() > 1)) {
            int height;
            int width;
            if (!this.f9120Q.isFinished()) {
                overScrollMode = canvas.save();
                height = getHeight();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), this.f9142s * ((float) height));
                this.f9120Q.setSize(width, height);
                i = 0 | this.f9120Q.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
            if (!this.f9121R.isFinished()) {
                overScrollMode = canvas.save();
                height = getHeight();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((float) ((-width) - getPaddingLeft()), (-(this.f9143t + 1.0f)) * ((float) height));
                this.f9121R.setSize(width, height);
                i |= this.f9121R.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
        } else {
            this.f9120Q.finish();
            this.f9121R.finish();
        }
        if (i != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f9138o > 0 && this.f9139p != null && this.f9128e.size() > 0 && this.f9131h != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float f = ((float) this.f9138o) / ((float) height);
            C2018b c2018b = (C2018b) this.f9128e.get(0);
            float f2 = c2018b.f9092e;
            int size = this.f9128e.size();
            int i = c2018b.f9089b;
            int i2 = ((C2018b) this.f9128e.get(size - 1)).f9089b;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > c2018b.f9089b && i3 < size) {
                    i3++;
                    c2018b = (C2018b) this.f9128e.get(i3);
                }
                if (i4 == c2018b.f9089b) {
                    f3 = (c2018b.f9092e + c2018b.f9091d) * ((float) height);
                    f2 = (c2018b.f9092e + c2018b.f9091d) + f;
                } else {
                    float pageWidth = this.f9131h.getPageWidth(i4);
                    f3 = (f2 + pageWidth) * ((float) height);
                    f2 += pageWidth + f;
                }
                if (((float) this.f9138o) + f3 > ((float) scrollY)) {
                    this.f9139p.setBounds(this.f9140q, (int) f3, this.f9141r, (int) ((((float) this.f9138o) + f3) + 0.5f));
                    this.f9139p.draw(canvas);
                }
                if (f3 <= ((float) (scrollY + height))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private void m10411a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.f9113J) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.f9110G = MotionEventCompat.getY(motionEvent, actionIndex);
            this.f9113J = MotionEventCompat.getPointerId(motionEvent, actionIndex);
            if (this.f9114K != null) {
                this.f9114K.clear();
            }
        }
    }

    /* renamed from: j */
    private void m10425j() {
        this.f9104A = false;
        this.f9105B = false;
        if (this.f9114K != null) {
            this.f9114K.recycle();
            this.f9114K = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.f9147x != z) {
            this.f9147x = z;
        }
    }

    /* renamed from: c */
    public boolean m10442c(int i) {
        boolean z = true;
        if (this.f9131h == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        if (i < 0) {
            if (scrollY <= ((int) (((float) clientHeight) * this.f9142s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollY >= ((int) (((float) clientHeight) * this.f9143t))) {
                z = false;
            }
            return z;
        }
    }

    /* renamed from: a */
    protected boolean m10437a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom() && i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight()) {
                    if (m10437a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && ViewCompat.canScrollVertically(view, -i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m10436a(keyEvent);
    }

    /* renamed from: a */
    public boolean m10436a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return m10444d(17);
            case 22:
                return m10444d(66);
            case 61:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                    return m10444d(2);
                }
                if (KeyEventCompat.hasModifiers(keyEvent, 1)) {
                    return m10444d(1);
                }
                return false;
            default:
                return false;
        }
    }

    /* renamed from: d */
    public boolean m10444d(int i) {
        View view;
        boolean d;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (VerticalViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 33 || i == 1) {
                d = m10443d();
            } else {
                if (i == TransportMediator.KEYCODE_MEDIA_RECORD || i == 2) {
                    d = m10445e();
                }
                d = false;
            }
        } else if (i == 33) {
            d = (view == null || m10407a(this.f9130g, findNextFocus).top < m10407a(this.f9130g, view).top) ? findNextFocus.requestFocus() : m10443d();
        } else {
            if (i == TransportMediator.KEYCODE_MEDIA_RECORD) {
                d = (view == null || m10407a(this.f9130g, findNextFocus).bottom > m10407a(this.f9130g, view).bottom) ? findNextFocus.requestFocus() : m10445e();
            }
            d = false;
        }
        if (d) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return d;
    }

    /* renamed from: a */
    private Rect m10407a(Rect rect, View view) {
        Rect rect2;
        if (rect == null) {
            rect2 = new Rect();
        } else {
            rect2 = rect;
        }
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        VerticalViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    /* renamed from: d */
    boolean m10443d() {
        if (this.f9132i <= 0) {
            return false;
        }
        m10433a(this.f9132i - 1, true);
        return true;
    }

    /* renamed from: e */
    boolean m10445e() {
        if (this.f9131h == null || this.f9132i >= this.f9131h.getCount() - 1) {
            return false;
        }
        m10433a(this.f9132i + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    C2018b a = m10428a(childAt);
                    if (a != null && a.f9089b == this.f9132i) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C2018b a = m10428a(childAt);
                if (a != null && a.f9089b == this.f9132i) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                C2018b a = m10428a(childAt);
                if (a != null && a.f9089b == this.f9132i && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C2018b a = m10428a(childAt);
                if (a != null && a.f9089b == this.f9132i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C2019c();
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C2019c) && super.checkLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2019c(getContext(), attributeSet);
    }

    public void setPosY(float f) {
        this.aj = f;
    }
}
