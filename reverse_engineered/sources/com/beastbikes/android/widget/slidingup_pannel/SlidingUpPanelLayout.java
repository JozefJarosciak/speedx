package com.beastbikes.android.widget.slidingup_pannel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.beastbikes.android.widget.slidingup_pannel.C2740b.C2733a;
import com.google.common.primitives.Ints;
import java.util.List;

public class SlidingUpPanelLayout extends ViewGroup {
    /* renamed from: a */
    private static final String f12759a = SlidingUpPanelLayout.class.getSimpleName();
    /* renamed from: b */
    private static PanelState f12760b = PanelState.COLLAPSED;
    /* renamed from: c */
    private static final int[] f12761c = new int[]{16842927};
    /* renamed from: A */
    private boolean f12762A;
    /* renamed from: B */
    private float f12763B;
    /* renamed from: C */
    private float f12764C;
    /* renamed from: D */
    private float f12765D;
    /* renamed from: E */
    private boolean f12766E;
    /* renamed from: F */
    private List<C2736c> f12767F;
    /* renamed from: G */
    private OnClickListener f12768G;
    /* renamed from: H */
    private final C2740b f12769H;
    /* renamed from: I */
    private boolean f12770I;
    /* renamed from: J */
    private final Rect f12771J;
    /* renamed from: K */
    private boolean f12772K;
    /* renamed from: L */
    private int f12773L;
    /* renamed from: d */
    private int f12774d;
    /* renamed from: e */
    private int f12775e;
    /* renamed from: f */
    private final Paint f12776f;
    /* renamed from: g */
    private Drawable f12777g;
    /* renamed from: h */
    private int f12778h;
    /* renamed from: i */
    private int f12779i;
    /* renamed from: j */
    private int f12780j;
    /* renamed from: k */
    private boolean f12781k;
    /* renamed from: l */
    private boolean f12782l;
    /* renamed from: m */
    private boolean f12783m;
    /* renamed from: n */
    private View f12784n;
    /* renamed from: o */
    private int f12785o;
    /* renamed from: p */
    private View f12786p;
    /* renamed from: q */
    private int f12787q;
    /* renamed from: r */
    private C2737a f12788r;
    /* renamed from: s */
    private View f12789s;
    /* renamed from: t */
    private View f12790t;
    /* renamed from: u */
    private PanelState f12791u;
    /* renamed from: v */
    private PanelState f12792v;
    /* renamed from: w */
    private float f12793w;
    /* renamed from: x */
    private int f12794x;
    /* renamed from: y */
    private float f12795y;
    /* renamed from: z */
    private boolean f12796z;

    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout$1 */
    class C27311 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ SlidingUpPanelLayout f12754a;

        C27311(SlidingUpPanelLayout slidingUpPanelLayout) {
            this.f12754a = slidingUpPanelLayout;
        }

        public void onClick(View view) {
            if (!this.f12754a.isEnabled() || !this.f12754a.m13508a()) {
                return;
            }
            if (this.f12754a.f12791u == PanelState.EXPANDED || this.f12754a.f12791u == PanelState.ANCHORED) {
                this.f12754a.setPanelState(PanelState.COLLAPSED);
            } else if (this.f12754a.f12795y < 1.0f) {
                this.f12754a.setPanelState(PanelState.ANCHORED);
            } else {
                this.f12754a.setPanelState(PanelState.EXPANDED);
            }
        }
    }

    public enum PanelState {
        EXPANDED,
        COLLAPSED,
        ANCHORED,
        HIDDEN,
        DRAGGING
    }

    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout$a */
    private class C2734a extends C2733a {
        /* renamed from: a */
        final /* synthetic */ SlidingUpPanelLayout f12756a;

        private C2734a(SlidingUpPanelLayout slidingUpPanelLayout) {
            this.f12756a = slidingUpPanelLayout;
        }

        /* renamed from: a */
        public boolean mo3552a(View view, int i) {
            if (!this.f12756a.f12796z && view == this.f12756a.f12789s) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public void mo3549a(int i) {
            if (this.f12756a.f12769H.m13534a() == 0) {
                this.f12756a.f12793w = this.f12756a.m13485a(this.f12756a.f12789s.getTop());
                this.f12756a.m13500e();
                if (this.f12756a.f12793w == 1.0f) {
                    this.f12756a.m13511c();
                    this.f12756a.setPanelStateInternal(PanelState.EXPANDED);
                } else if (this.f12756a.f12793w == 0.0f) {
                    this.f12756a.setPanelStateInternal(PanelState.COLLAPSED);
                } else if (this.f12756a.f12793w < 0.0f) {
                    this.f12756a.setPanelStateInternal(PanelState.HIDDEN);
                    this.f12756a.f12789s.setVisibility(4);
                } else {
                    this.f12756a.m13511c();
                    this.f12756a.setPanelStateInternal(PanelState.ANCHORED);
                }
            }
        }

        /* renamed from: b */
        public void mo3553b(View view, int i) {
            this.f12756a.m13512d();
        }

        /* renamed from: a */
        public void mo3551a(View view, int i, int i2, int i3, int i4) {
            this.f12756a.m13494b(i2);
            this.f12756a.invalidate();
        }

        /* renamed from: a */
        public void mo3550a(View view, float f, float f2) {
            int b;
            if (this.f12756a.f12781k) {
                f2 = -f2;
            }
            if (f2 > 0.0f && this.f12756a.f12793w <= this.f12756a.f12795y) {
                b = this.f12756a.m13488a(this.f12756a.f12795y);
            } else if (f2 > 0.0f && this.f12756a.f12793w > this.f12756a.f12795y) {
                b = this.f12756a.m13488a(1.0f);
            } else if (f2 < 0.0f && this.f12756a.f12793w >= this.f12756a.f12795y) {
                b = this.f12756a.m13488a(this.f12756a.f12795y);
            } else if (f2 < 0.0f && this.f12756a.f12793w < this.f12756a.f12795y) {
                b = this.f12756a.m13488a(0.0f);
            } else if (this.f12756a.f12793w >= (this.f12756a.f12795y + 1.0f) / 2.0f) {
                b = this.f12756a.m13488a(1.0f);
            } else if (this.f12756a.f12793w >= this.f12756a.f12795y / 2.0f) {
                b = this.f12756a.m13488a(this.f12756a.f12795y);
            } else {
                b = this.f12756a.m13488a(0.0f);
            }
            this.f12756a.f12769H.m13538a(view.getLeft(), b);
            this.f12756a.invalidate();
        }

        /* renamed from: a */
        public int mo3547a(View view) {
            return this.f12756a.f12794x;
        }

        /* renamed from: a */
        public int mo3548a(View view, int i, int i2) {
            int b = this.f12756a.m13488a(0.0f);
            int b2 = this.f12756a.m13488a(1.0f);
            if (this.f12756a.f12781k) {
                return Math.min(Math.max(i, b2), b);
            }
            return Math.min(Math.max(i, b), b2);
        }
    }

    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout$b */
    public static class C2735b extends MarginLayoutParams {
        /* renamed from: b */
        private static final int[] f12757b = new int[]{16843137};
        /* renamed from: a */
        public float f12758a = 0.0f;

        public C2735b() {
            super(-1, -1);
        }

        public C2735b(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public C2735b(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public C2735b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f12757b);
            if (obtainStyledAttributes != null) {
                this.f12758a = obtainStyledAttributes.getFloat(0, 0.0f);
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout$c */
    public interface C2736c {
        /* renamed from: a */
        void m13483a(View view, float f);

        /* renamed from: a */
        void m13484a(View view, PanelState panelState, PanelState panelState2);
    }

    public SlidingUpPanelLayout(Context context) {
        this(context, null);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SlidingUpPanelLayout(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
        r9 = this;
        r8 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r1 = 0;
        r7 = 1;
        r6 = 0;
        r5 = -1;
        r9.<init>(r10, r11, r12);
        r0 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r9.f12774d = r0;
        r0 = -285212672; // 0xffffffffef000000 float:-3.9614081E28 double:NaN;
        r9.f12775e = r0;
        r0 = new android.graphics.Paint;
        r0.<init>();
        r9.f12776f = r0;
        r9.f12777g = r1;
        r9.f12778h = r5;
        r9.f12779i = r5;
        r9.f12780j = r5;
        r9.f12782l = r6;
        r9.f12783m = r7;
        r9.f12785o = r5;
        r0 = new com.beastbikes.android.widget.slidingup_pannel.a;
        r0.<init>();
        r9.f12788r = r0;
        r0 = f12760b;
        r9.f12791u = r0;
        r0 = f12760b;
        r9.f12792v = r0;
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r9.f12795y = r0;
        r9.f12766E = r6;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r9.f12767F = r0;
        r9.f12770I = r7;
        r0 = new android.graphics.Rect;
        r0.<init>();
        r9.f12771J = r0;
        r9.f12772K = r7;
        r0 = r9.isInEditMode();
        if (r0 == 0) goto L_0x0058;
    L_0x0053:
        r9.f12777g = r1;
        r9.f12769H = r1;
    L_0x0057:
        return;
    L_0x0058:
        if (r11 == 0) goto L_0x0135;
    L_0x005a:
        r0 = f12761c;
        r0 = r10.obtainStyledAttributes(r11, r0);
        if (r0 == 0) goto L_0x0069;
    L_0x0062:
        r2 = r0.getInt(r6, r6);
        r9.setGravity(r2);
    L_0x0069:
        if (r0 == 0) goto L_0x006e;
    L_0x006b:
        r0.recycle();
    L_0x006e:
        r0 = com.beastbikes.android.R$styleable.SlidingUpPanelLayout;
        r2 = r10.obtainStyledAttributes(r11, r0);
        if (r2 == 0) goto L_0x0135;
    L_0x0076:
        r0 = r2.getDimensionPixelSize(r6, r5);
        r9.f12778h = r0;
        r0 = r2.getDimensionPixelSize(r7, r5);
        r9.f12779i = r0;
        r0 = 2;
        r0 = r2.getDimensionPixelSize(r0, r5);
        r9.f12780j = r0;
        r0 = 4;
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r0 = r2.getInt(r0, r3);
        r9.f12774d = r0;
        r0 = 3;
        r3 = -285212672; // 0xffffffffef000000 float:-3.9614081E28 double:NaN;
        r0 = r2.getColor(r0, r3);
        r9.f12775e = r0;
        r0 = 5;
        r0 = r2.getResourceId(r0, r5);
        r9.f12785o = r0;
        r0 = 6;
        r0 = r2.getResourceId(r0, r5);
        r9.f12787q = r0;
        r0 = 7;
        r0 = r2.getBoolean(r0, r6);
        r9.f12782l = r0;
        r0 = 8;
        r0 = r2.getBoolean(r0, r7);
        r9.f12783m = r0;
        r0 = 9;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = r2.getFloat(r0, r3);
        r9.f12795y = r0;
        r0 = com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout.PanelState.values();
        r3 = 10;
        r4 = f12760b;
        r4 = r4.ordinal();
        r3 = r2.getInt(r3, r4);
        r0 = r0[r3];
        r9.f12791u = r0;
        r0 = 11;
        r0 = r2.getResourceId(r0, r5);
        if (r0 == r5) goto L_0x0133;
    L_0x00de:
        r0 = android.view.animation.AnimationUtils.loadInterpolator(r10, r0);
    L_0x00e2:
        r2.recycle();
    L_0x00e5:
        r2 = r10.getResources();
        r2 = r2.getDisplayMetrics();
        r2 = r2.density;
        r3 = r9.f12778h;
        if (r3 != r5) goto L_0x00f9;
    L_0x00f3:
        r3 = 0;
        r3 = r3 * r2;
        r3 = r3 + r8;
        r3 = (int) r3;
        r9.f12778h = r3;
    L_0x00f9:
        r3 = r9.f12779i;
        if (r3 != r5) goto L_0x0104;
    L_0x00fd:
        r3 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r3 = r3 * r2;
        r3 = r3 + r8;
        r3 = (int) r3;
        r9.f12779i = r3;
    L_0x0104:
        r3 = r9.f12780j;
        if (r3 != r5) goto L_0x010d;
    L_0x0108:
        r3 = 0;
        r3 = r3 * r2;
        r3 = (int) r3;
        r9.f12780j = r3;
    L_0x010d:
        r3 = r9.f12779i;
        if (r3 <= 0) goto L_0x0130;
    L_0x0111:
        r3 = r9.f12781k;
        if (r3 == 0) goto L_0x0115;
    L_0x0115:
        r9.setWillNotDraw(r6);
        r3 = new com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout$a;
        r3.<init>();
        r0 = com.beastbikes.android.widget.slidingup_pannel.C2740b.m13517a(r9, r8, r0, r3);
        r9.f12769H = r0;
        r0 = r9.f12769H;
        r1 = r9.f12774d;
        r1 = (float) r1;
        r1 = r1 * r2;
        r0.m13535a(r1);
        r9.f12762A = r7;
        goto L_0x0057;
    L_0x0130:
        r9.f12777g = r1;
        goto L_0x0115;
    L_0x0133:
        r0 = r1;
        goto L_0x00e2;
    L_0x0135:
        r0 = r1;
        goto L_0x00e5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.f12785o != -1) {
            setDragView(findViewById(this.f12785o));
        }
        if (this.f12787q != -1) {
            setScrollableView(findViewById(this.f12787q));
        }
    }

    public void setGravity(int i) {
        if (i == 48 || i == 80) {
            this.f12781k = i == 80;
            if (!this.f12770I) {
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("gravity must be set to either top or bottom");
    }

    public void setCoveredFadeColor(int i) {
        this.f12775e = i;
        requestLayout();
    }

    public int getCoveredFadeColor() {
        return this.f12775e;
    }

    public void setTouchEnabled(boolean z) {
        this.f12762A = z;
    }

    /* renamed from: a */
    public boolean m13508a() {
        return (!this.f12762A || this.f12789s == null || this.f12791u == PanelState.HIDDEN) ? false : true;
    }

    public void setPanelHeight(int i) {
        if (getPanelHeight() != i) {
            this.f12778h = i;
            if (!this.f12770I) {
                requestLayout();
            }
            if (getPanelState() == PanelState.COLLAPSED) {
                m13510b();
                invalidate();
            }
        }
    }

    /* renamed from: b */
    protected void m13510b() {
        m13509a(0.0f, 0);
    }

    public int getShadowHeight() {
        return this.f12779i;
    }

    public void setShadowHeight(int i) {
        this.f12779i = i;
        if (!this.f12770I) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return this.f12778h;
    }

    public int getCurrentParallaxOffset() {
        int max = (int) (((float) this.f12780j) * Math.max(this.f12793w, 0.0f));
        return this.f12781k ? -max : max;
    }

    public void setParallaxOffset(int i) {
        this.f12780j = i;
        if (!this.f12770I) {
            requestLayout();
        }
    }

    public int getMinFlingVelocity() {
        return this.f12774d;
    }

    public void setMinFlingVelocity(int i) {
        this.f12774d = i;
    }

    /* renamed from: a */
    public void m13507a(C2736c c2736c) {
        synchronized (this.f12767F) {
            this.f12767F.add(c2736c);
        }
    }

    public void setFadeOnClickListener(OnClickListener onClickListener) {
        this.f12768G = onClickListener;
    }

    public void setDragView(View view) {
        if (this.f12784n != null) {
            this.f12784n.setOnClickListener(null);
        }
        this.f12784n = view;
        if (this.f12784n != null) {
            this.f12784n.setClickable(true);
            this.f12784n.setFocusable(false);
            this.f12784n.setFocusableInTouchMode(false);
            this.f12784n.setOnClickListener(new C27311(this));
        }
    }

    public void setDragView(int i) {
        this.f12785o = i;
        setDragView(findViewById(i));
    }

    public void setScrollableView(View view) {
        this.f12786p = view;
    }

    public void setScrollableViewHelper(C2737a c2737a) {
        this.f12788r = c2737a;
    }

    public void setAnchorPoint(float f) {
        if (f > 0.0f && f <= 1.0f) {
            this.f12795y = f;
            this.f12770I = true;
            requestLayout();
        }
    }

    public float getAnchorPoint() {
        return this.f12795y;
    }

    public void setOverlayed(boolean z) {
        this.f12782l = z;
    }

    public void setClipPanel(boolean z) {
        this.f12783m = z;
    }

    /* renamed from: a */
    void m13505a(View view) {
        synchronized (this.f12767F) {
            for (C2736c a : this.f12767F) {
                a.m13483a(view, this.f12793w);
            }
        }
    }

    /* renamed from: a */
    void m13506a(View view, PanelState panelState, PanelState panelState2) {
        synchronized (this.f12767F) {
            for (C2736c a : this.f12767F) {
                a.m13484a(view, panelState, panelState2);
            }
        }
        sendAccessibilityEvent(32);
    }

    /* renamed from: c */
    void m13511c() {
        int i = 0;
        if (getChildCount() != 0) {
            int i2;
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int i3;
            int i4;
            int i5;
            if (this.f12789s == null || !m13496b(this.f12789s)) {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i2 = 0;
            } else {
                i2 = this.f12789s.getLeft();
                i5 = this.f12789s.getRight();
                i4 = this.f12789s.getTop();
                i3 = this.f12789s.getBottom();
            }
            View childAt = getChildAt(0);
            paddingLeft = Math.max(paddingLeft, childAt.getLeft());
            paddingTop = Math.max(paddingTop, childAt.getTop());
            width = Math.min(width, childAt.getRight());
            height = Math.min(height, childAt.getBottom());
            if (paddingLeft >= i2 && paddingTop >= r2 && width <= r3 && height <= r0) {
                i = 4;
            }
            childAt.setVisibility(i);
        }
    }

    /* renamed from: d */
    void m13512d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    /* renamed from: b */
    private static boolean m13496b(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f12770I = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f12770I = true;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != Ints.MAX_POWER_OF_TWO && mode != Integer.MIN_VALUE) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode2 == Ints.MAX_POWER_OF_TWO || mode2 == Integer.MIN_VALUE) {
            int childCount = getChildCount();
            if (childCount != 2) {
                throw new IllegalStateException("Sliding up panel layout must have exactly 2 children!");
            }
            this.f12790t = getChildAt(0);
            this.f12789s = getChildAt(1);
            if (this.f12784n == null) {
                setDragView(this.f12789s);
            }
            if (this.f12789s.getVisibility() != 0) {
                this.f12791u = PanelState.HIDDEN;
            }
            int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                C2735b c2735b = (C2735b) childAt.getLayoutParams();
                if (childAt.getVisibility() != 8 || i3 != 0) {
                    int i4;
                    if (childAt == this.f12790t) {
                        if (this.f12782l || this.f12791u == PanelState.HIDDEN) {
                            mode2 = paddingTop;
                        } else {
                            mode2 = paddingTop - this.f12778h;
                        }
                        i4 = mode2;
                        mode2 = paddingLeft - (c2735b.leftMargin + c2735b.rightMargin);
                    } else if (childAt == this.f12789s) {
                        i4 = paddingTop - c2735b.topMargin;
                        mode2 = paddingLeft;
                    } else {
                        mode2 = paddingLeft;
                        i4 = paddingTop;
                    }
                    if (c2735b.width == -2) {
                        mode2 = MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE);
                    } else if (c2735b.width == -1) {
                        mode2 = MeasureSpec.makeMeasureSpec(mode2, Ints.MAX_POWER_OF_TWO);
                    } else {
                        mode2 = MeasureSpec.makeMeasureSpec(c2735b.width, Ints.MAX_POWER_OF_TWO);
                    }
                    if (c2735b.height == -2) {
                        mode = MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
                    } else {
                        if (c2735b.f12758a > 0.0f && c2735b.f12758a < 1.0f) {
                            i4 = (int) (c2735b.f12758a * ((float) i4));
                        } else if (c2735b.height != -1) {
                            i4 = c2735b.height;
                        }
                        mode = MeasureSpec.makeMeasureSpec(i4, Ints.MAX_POWER_OF_TWO);
                    }
                    childAt.measure(mode2, mode);
                    if (childAt == this.f12789s) {
                        this.f12794x = this.f12789s.getMeasuredHeight() - this.f12778h;
                    }
                }
                i3++;
            }
            setMeasuredDimension(size, size2);
        } else {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f12770I) {
            switch (this.f12791u) {
                case EXPANDED:
                    this.f12793w = 1.0f;
                    break;
                case ANCHORED:
                    this.f12793w = this.f12795y;
                    break;
                case HIDDEN:
                    int a = m13488a(0.0f);
                    if (this.f12781k) {
                        i5 = this.f12778h;
                    } else {
                        i5 = -this.f12778h;
                    }
                    this.f12793w = m13485a(i5 + a);
                    break;
                default:
                    this.f12793w = 0.0f;
                    break;
            }
        }
        int i6 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            C2735b c2735b = (C2735b) childAt.getLayoutParams();
            if (!(childAt.getVisibility() == 8 && (i6 == 0 || this.f12770I))) {
                int measuredHeight = childAt.getMeasuredHeight();
                if (childAt == this.f12789s) {
                    a = m13488a(this.f12793w);
                } else {
                    a = paddingTop;
                }
                if (!(this.f12781k || childAt != this.f12790t || this.f12782l)) {
                    a = m13488a(this.f12793w) + this.f12789s.getMeasuredHeight();
                }
                i5 = c2735b.leftMargin + paddingLeft;
                childAt.layout(i5, a, childAt.getMeasuredWidth() + i5, measuredHeight + a);
            }
            i6++;
        }
        if (this.f12770I) {
            m13511c();
        }
        m13500e();
        this.f12770I = false;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            this.f12770I = true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f12766E || !m13508a()) {
            this.f12769H.m13551d();
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        float abs = Math.abs(x - this.f12764C);
        float abs2 = Math.abs(y - this.f12765D);
        int b = this.f12769H.m13542b();
        switch (actionMasked) {
            case 0:
                this.f12796z = false;
                this.f12764C = x;
                this.f12765D = y;
                if (!m13491a(this.f12784n, (int) x, (int) y)) {
                    this.f12769H.m13550c();
                    this.f12796z = true;
                    return false;
                }
                break;
            case 1:
            case 3:
                if (this.f12769H.m13552e()) {
                    this.f12769H.m13544b(motionEvent);
                    return true;
                } else if (abs2 <= ((float) b) && abs <= ((float) b) && this.f12793w > 0.0f && !m13491a(this.f12789s, (int) this.f12764C, (int) this.f12765D) && this.f12768G != null) {
                    playSoundEffect(0);
                    this.f12768G.onClick(this);
                    return true;
                }
            case 2:
                if (abs2 > ((float) b) && abs > abs2) {
                    this.f12769H.m13550c();
                    this.f12796z = true;
                    return false;
                }
        }
        return this.f12769H.m13539a(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !m13508a()) {
            return super.onTouchEvent(motionEvent);
        }
        try {
            this.f12769H.m13544b(motionEvent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i = -1;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (isEnabled() && m13508a() && (!this.f12796z || actionMasked == 0)) {
            float y = motionEvent.getY();
            if (actionMasked == 0) {
                this.f12766E = false;
                this.f12763B = y;
            } else if (actionMasked == 2) {
                float f = y - this.f12763B;
                this.f12763B = y;
                if (!m13491a(this.f12786p, (int) this.f12764C, (int) this.f12765D)) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (((float) (this.f12781k ? 1 : -1)) * f <= 0.0f) {
                    if (this.f12781k) {
                        i = 1;
                    }
                    if (((float) i) * f < 0.0f) {
                        if (this.f12793w < 1.0f) {
                            this.f12766E = false;
                            return onTouchEvent(motionEvent);
                        }
                        if (!this.f12766E && this.f12769H.m13552e()) {
                            this.f12769H.m13550c();
                            motionEvent.setAction(0);
                        }
                        this.f12766E = true;
                        return super.dispatchTouchEvent(motionEvent);
                    }
                } else if (this.f12788r.m13513a(this.f12786p, this.f12781k) > 0) {
                    this.f12766E = true;
                    return super.dispatchTouchEvent(motionEvent);
                } else {
                    if (this.f12766E) {
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.setAction(3);
                        super.dispatchTouchEvent(obtain);
                        obtain.recycle();
                        motionEvent.setAction(0);
                    }
                    this.f12766E = false;
                    return onTouchEvent(motionEvent);
                }
            } else if (actionMasked == 1 && this.f12766E) {
                this.f12769H.m13536a(0);
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        this.f12769H.m13551d();
        return super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private boolean m13491a(View view, int i, int i2) {
        boolean z = true;
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + view.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + view.getHeight()) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    private int m13488a(float f) {
        int measuredHeight = this.f12789s != null ? this.f12789s.getMeasuredHeight() : 0;
        int i = (int) (((float) this.f12794x) * f);
        if (this.f12781k) {
            return ((getMeasuredHeight() - getPaddingBottom()) - this.f12778h) - i;
        }
        return ((getPaddingTop() - measuredHeight) + this.f12778h) + i;
    }

    /* renamed from: a */
    private float m13485a(int i) {
        int a = m13488a(0.0f);
        return this.f12781k ? ((float) (a - i)) / ((float) this.f12794x) : ((float) (i - a)) / ((float) this.f12794x);
    }

    public PanelState getPanelState() {
        return this.f12791u;
    }

    public void setPanelState(PanelState panelState) {
        if (panelState == null || panelState == PanelState.DRAGGING) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        } else if (!isEnabled()) {
        } else {
            if ((this.f12770I || this.f12789s != null) && panelState != this.f12791u && this.f12791u != PanelState.DRAGGING) {
                if (this.f12770I) {
                    setPanelStateInternal(panelState);
                    return;
                }
                if (this.f12791u == PanelState.HIDDEN) {
                    this.f12789s.setVisibility(0);
                    requestLayout();
                }
                switch (panelState) {
                    case EXPANDED:
                        m13509a(1.0f, 0);
                        return;
                    case ANCHORED:
                        m13509a(this.f12795y, 0);
                        return;
                    case HIDDEN:
                        int i;
                        int a = m13488a(0.0f);
                        if (this.f12781k) {
                            i = this.f12778h;
                        } else {
                            i = -this.f12778h;
                        }
                        m13509a(m13485a(i + a), 0);
                        return;
                    case COLLAPSED:
                        m13509a(0.0f, 0);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void setPanelStateInternal(PanelState panelState) {
        if (this.f12791u != panelState) {
            PanelState panelState2 = this.f12791u;
            this.f12791u = panelState;
            m13506a((View) this, panelState2, panelState);
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: e */
    private void m13500e() {
        if (this.f12780j > 0) {
            ViewCompat.setTranslationY(this.f12790t, (float) getCurrentParallaxOffset());
        }
    }

    /* renamed from: b */
    private void m13494b(int i) {
        if (this.f12791u != PanelState.DRAGGING) {
            this.f12792v = this.f12791u;
        }
        setPanelStateInternal(PanelState.DRAGGING);
        this.f12793w = m13485a(i);
        m13500e();
        m13505a(this.f12789s);
        C2735b c2735b = (C2735b) this.f12790t.getLayoutParams();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) - this.f12778h;
        if (this.f12793w <= 0.0f && !this.f12782l) {
            c2735b.height = this.f12781k ? i - getPaddingBottom() : ((getHeight() - getPaddingBottom()) - this.f12789s.getMeasuredHeight()) - i;
            if (c2735b.height == height) {
                c2735b.height = -1;
            }
            this.f12790t.requestLayout();
        } else if (c2735b.height != -1 && !this.f12782l) {
            c2735b.height = -1;
            this.f12790t.requestLayout();
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        int save = canvas.save(2);
        if (this.f12789s == null || this.f12789s == view) {
            drawChild = super.drawChild(canvas, view, j);
        } else {
            canvas.getClipBounds(this.f12771J);
            if (!this.f12782l) {
                if (this.f12781k) {
                    this.f12771J.bottom = Math.min(this.f12771J.bottom, this.f12789s.getTop());
                } else {
                    this.f12771J.top = Math.max(this.f12771J.top, this.f12789s.getBottom());
                }
            }
            if (this.f12783m) {
                canvas.clipRect(this.f12771J);
            }
            drawChild = super.drawChild(canvas, view, j);
            if (this.f12775e != 0 && this.f12793w > 0.0f) {
                this.f12776f.setColor((((int) (((float) ((this.f12775e & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.f12793w)) << 24) | (this.f12775e & ViewCompat.MEASURED_SIZE_MASK));
                canvas.drawRect(this.f12771J, this.f12776f);
            }
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    /* renamed from: a */
    boolean m13509a(float f, int i) {
        if (!isEnabled() || this.f12789s == null) {
            return false;
        }
        if (!this.f12769H.m13540a(this.f12789s, this.f12789s.getLeft(), m13488a(f))) {
            return false;
        }
        m13512d();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (this.f12769H != null && this.f12769H.m13541a(true)) {
            if (isEnabled()) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.f12769H.m13551d();
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f12777g != null && this.f12789s != null) {
            int top;
            int top2;
            int right = this.f12789s.getRight();
            if (this.f12781k) {
                top = this.f12789s.getTop() - this.f12779i;
                top2 = this.f12789s.getTop();
            } else {
                top = this.f12789s.getBottom();
                top2 = this.f12789s.getBottom() + this.f12779i;
            }
            this.f12777g.setBounds(this.f12789s.getLeft(), top, right, top2);
            this.f12777g.draw(canvas);
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C2735b();
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new C2735b((MarginLayoutParams) layoutParams) : new C2735b(layoutParams);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C2735b) && super.checkLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2735b(getContext(), attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putSerializable("sliding_state", this.f12791u != PanelState.DRAGGING ? this.f12791u : this.f12792v);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f12791u = (PanelState) bundle.getSerializable("sliding_state");
            this.f12791u = this.f12791u == null ? f12760b : this.f12791u;
            parcelable = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setScrollDown(boolean z) {
        this.f12772K = z;
        this.f12769H.m13545b(this.f12772K);
    }

    public int getDefaultHeight() {
        return this.f12773L;
    }

    public void setDefaultHeight(int i) {
        this.f12773L = i;
        this.f12769H.m13543b(this.f12773L);
    }
}
