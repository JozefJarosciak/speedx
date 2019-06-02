package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.google.common.primitives.Ints;

public class StickyListHeadersListView extends FrameLayout {
    /* renamed from: a */
    private C2786f f6830a;
    /* renamed from: b */
    private View f6831b;
    /* renamed from: c */
    private Long f6832c;
    /* renamed from: d */
    private Integer f6833d;
    /* renamed from: e */
    private Integer f6834e;
    /* renamed from: f */
    private OnScrollListener f6835f;
    /* renamed from: g */
    private C2782a f6836g;
    /* renamed from: h */
    private boolean f6837h;
    /* renamed from: i */
    private boolean f6838i;
    /* renamed from: j */
    private boolean f6839j;
    /* renamed from: k */
    private int f6840k;
    /* renamed from: l */
    private int f6841l;
    /* renamed from: m */
    private int f6842m;
    /* renamed from: n */
    private int f6843n;
    /* renamed from: o */
    private int f6844o;
    /* renamed from: p */
    private float f6845p;
    /* renamed from: q */
    private boolean f6846q;
    /* renamed from: r */
    private float f6847r;
    /* renamed from: s */
    private StickyListHeadersListView$c f6848s;
    /* renamed from: t */
    private StickyListHeadersListView$e f6849t;
    /* renamed from: u */
    private StickyListHeadersListView$d f6850u;
    /* renamed from: v */
    private StickyListHeadersListView$a f6851v;
    /* renamed from: w */
    private Drawable f6852w;
    /* renamed from: x */
    private int f6853x;
    /* renamed from: y */
    private boolean f6854y;

    public StickyListHeadersListView(Context context) {
        this(context, null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1373R.attr.stickyListHeadersListViewStyle);
    }

    @TargetApi(11)
    public StickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        boolean z = true;
        super(context, attributeSet, i);
        this.f6837h = true;
        this.f6838i = true;
        this.f6839j = true;
        this.f6840k = 0;
        this.f6841l = 0;
        this.f6842m = 0;
        this.f6843n = 0;
        this.f6844o = 0;
        this.f6854y = true;
        this.f6847r = (float) ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f6830a = new C2786f(context);
        this.f6852w = this.f6830a.getDivider();
        this.f6853x = this.f6830a.getDividerHeight();
        this.f6830a.setDivider(null);
        this.f6830a.setDividerHeight(0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.StickyListHeadersListView, i, 0);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
                this.f6841l = obtainStyledAttributes.getDimensionPixelSize(2, dimensionPixelSize);
                this.f6842m = obtainStyledAttributes.getDimensionPixelSize(3, dimensionPixelSize);
                this.f6843n = obtainStyledAttributes.getDimensionPixelSize(4, dimensionPixelSize);
                this.f6844o = obtainStyledAttributes.getDimensionPixelSize(5, dimensionPixelSize);
                setPadding(this.f6841l, this.f6842m, this.f6843n, this.f6844o);
                this.f6838i = obtainStyledAttributes.getBoolean(8, true);
                super.setClipToPadding(true);
                this.f6830a.setClipToPadding(this.f6838i);
                int i2 = obtainStyledAttributes.getInt(6, 512);
                this.f6830a.setVerticalScrollBarEnabled((i2 & 512) != 0);
                C2786f c2786f = this.f6830a;
                if ((i2 & 256) == 0) {
                    z = false;
                }
                c2786f.setHorizontalScrollBarEnabled(z);
                if (VERSION.SDK_INT >= 9) {
                    this.f6830a.setOverScrollMode(obtainStyledAttributes.getInt(19, 0));
                }
                this.f6830a.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelSize(7, this.f6830a.getVerticalFadingEdgeLength()));
                int i3 = obtainStyledAttributes.getInt(21, 0);
                if (i3 == 4096) {
                    this.f6830a.setVerticalFadingEdgeEnabled(false);
                    this.f6830a.setHorizontalFadingEdgeEnabled(true);
                } else if (i3 == 8192) {
                    this.f6830a.setVerticalFadingEdgeEnabled(true);
                    this.f6830a.setHorizontalFadingEdgeEnabled(false);
                } else {
                    this.f6830a.setVerticalFadingEdgeEnabled(false);
                    this.f6830a.setHorizontalFadingEdgeEnabled(false);
                }
                this.f6830a.setCacheColorHint(obtainStyledAttributes.getColor(14, this.f6830a.getCacheColorHint()));
                if (VERSION.SDK_INT >= 11) {
                    this.f6830a.setChoiceMode(obtainStyledAttributes.getInt(17, this.f6830a.getChoiceMode()));
                }
                this.f6830a.setDrawSelectorOnTop(obtainStyledAttributes.getBoolean(10, false));
                this.f6830a.setFastScrollEnabled(obtainStyledAttributes.getBoolean(18, this.f6830a.isFastScrollEnabled()));
                if (VERSION.SDK_INT >= 11) {
                    this.f6830a.setFastScrollAlwaysVisible(obtainStyledAttributes.getBoolean(20, this.f6830a.isFastScrollAlwaysVisible()));
                }
                this.f6830a.setScrollBarStyle(obtainStyledAttributes.getInt(0, 0));
                if (obtainStyledAttributes.hasValue(9)) {
                    this.f6830a.setSelector(obtainStyledAttributes.getDrawable(9));
                }
                this.f6830a.setScrollingCacheEnabled(obtainStyledAttributes.getBoolean(12, this.f6830a.isScrollingCacheEnabled()));
                if (obtainStyledAttributes.hasValue(15)) {
                    this.f6852w = obtainStyledAttributes.getDrawable(15);
                }
                this.f6830a.setStackFromBottom(obtainStyledAttributes.getBoolean(11, false));
                this.f6853x = obtainStyledAttributes.getDimensionPixelSize(16, this.f6853x);
                this.f6830a.setTranscriptMode(obtainStyledAttributes.getInt(13, 0));
                this.f6837h = obtainStyledAttributes.getBoolean(23, true);
                this.f6839j = obtainStyledAttributes.getBoolean(24, true);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f6830a.a(new StickyListHeadersListView$g(this, null));
        this.f6830a.setOnScrollListener(new StickyListHeadersListView$f(this, null));
        addView(this.f6830a);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        m8034c(this.f6831b);
    }

    /* renamed from: b */
    private void m8029b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        } else if (layoutParams.height == -1 || layoutParams.width == -2) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: c */
    private void m8034c(View view) {
        if (view != null) {
            measureChild(view, MeasureSpec.makeMeasureSpec((getMeasuredWidth() - this.f6841l) - this.f6843n, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f6830a.layout(0, 0, this.f6830a.getMeasuredWidth(), getHeight());
        if (this.f6831b != null) {
            int i5 = ((MarginLayoutParams) this.f6831b.getLayoutParams()).topMargin;
            this.f6831b.layout(this.f6841l, i5, this.f6831b.getMeasuredWidth() + this.f6841l, this.f6831b.getMeasuredHeight() + i5);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.f6830a.getVisibility() == 0 || this.f6830a.getAnimation() != null) {
            drawChild(canvas, this.f6830a, 0);
        }
    }

    /* renamed from: b */
    private void m8027b() {
        if (this.f6831b != null) {
            removeView(this.f6831b);
            this.f6831b = null;
            this.f6832c = null;
            this.f6833d = null;
            this.f6834e = null;
            this.f6830a.a(0);
            m8032c();
        }
    }

    /* renamed from: b */
    private void m8028b(int i) {
        int i2 = 0;
        int count = this.f6836g == null ? 0 : this.f6836g.getCount();
        if (count != 0 && this.f6837h) {
            int headerViewsCount = i - this.f6830a.getHeaderViewsCount();
            if (this.f6830a.getChildCount() > 0 && this.f6830a.getChildAt(0).getBottom() < m8035d()) {
                headerViewsCount++;
            }
            int i3 = this.f6830a.getChildCount() != 0 ? 1 : 0;
            int i4;
            if (i3 == 0 || this.f6830a.getFirstVisiblePosition() != 0 || this.f6830a.getChildAt(0).getTop() < m8035d()) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            if (headerViewsCount > count - 1 || headerViewsCount < 0) {
                i2 = 1;
            }
            if (i3 != 0 && r1 == 0 && r3 == 0) {
                m8033c(headerViewsCount);
            } else {
                m8027b();
            }
        }
    }

    /* renamed from: c */
    private void m8033c(int i) {
        int min;
        if (this.f6833d == null || this.f6833d.intValue() != i) {
            this.f6833d = Integer.valueOf(i);
            long b = this.f6836g.b(i);
            if (this.f6832c == null || this.f6832c.longValue() != b) {
                this.f6832c = Long.valueOf(b);
                View a = this.f6836g.a(this.f6833d.intValue(), this.f6831b, this);
                if (this.f6831b != a) {
                    if (a == null) {
                        throw new NullPointerException("header may not be null");
                    }
                    m8037d(a);
                }
                m8029b(this.f6831b);
                m8034c(this.f6831b);
                if (this.f6850u != null) {
                    this.f6850u.a(this, this.f6831b, i, this.f6832c.longValue());
                }
                this.f6834e = null;
            }
        }
        int d = m8035d();
        for (int i2 = 0; i2 < this.f6830a.getChildCount(); i2++) {
            View childAt = this.f6830a.getChildAt(i2);
            Object obj = ((childAt instanceof C2783e) && ((C2783e) childAt).a()) ? 1 : null;
            boolean a2 = this.f6830a.a(childAt);
            if (childAt.getTop() >= m8035d() && (obj != null || a2)) {
                min = Math.min(childAt.getTop() - this.f6831b.getMeasuredHeight(), d);
                break;
            }
        }
        min = d;
        setHeaderOffet(min);
        if (!(this.f6839j || this.f6830a == null || this.f6831b == null || this.f6834e == null)) {
            this.f6830a.a(this.f6831b.getMeasuredHeight() + this.f6834e.intValue());
        }
        m8032c();
    }

    /* renamed from: d */
    private void m8037d(View view) {
        if (this.f6831b != null) {
            removeView(this.f6831b);
        }
        this.f6831b = view;
        addView(this.f6831b);
        if (this.f6848s != null) {
            this.f6831b.setOnClickListener(new StickyListHeadersListView$1(this));
        }
        this.f6831b.setClickable(true);
    }

    /* renamed from: c */
    private void m8032c() {
        int d = m8035d();
        int childCount = this.f6830a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f6830a.getChildAt(i);
            if (childAt instanceof C2783e) {
                C2783e c2783e = (C2783e) childAt;
                if (c2783e.a()) {
                    View view = c2783e.f12989d;
                    if (c2783e.getTop() < d) {
                        if (view.getVisibility() != 4) {
                            view.setVisibility(4);
                        }
                    } else if (view.getVisibility() != 0) {
                        view.setVisibility(0);
                    }
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void setHeaderOffet(int i) {
        if (this.f6834e == null || this.f6834e.intValue() != i) {
            this.f6834e = Integer.valueOf(i);
            if (VERSION.SDK_INT >= 11) {
                this.f6831b.setTranslationY((float) this.f6834e.intValue());
            } else {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f6831b.getLayoutParams();
                marginLayoutParams.topMargin = this.f6834e.intValue();
                this.f6831b.setLayoutParams(marginLayoutParams);
            }
            if (this.f6849t != null) {
                this.f6849t.a(this, this.f6831b, -this.f6834e.intValue());
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 0) {
            this.f6845p = motionEvent.getY();
            boolean z = this.f6831b != null && this.f6845p <= ((float) (this.f6831b.getHeight() + this.f6834e.intValue()));
            this.f6846q = z;
        }
        if (!this.f6846q) {
            return this.f6830a.dispatchTouchEvent(motionEvent);
        }
        if (this.f6831b != null && Math.abs(this.f6845p - motionEvent.getY()) <= this.f6847r) {
            return this.f6831b.dispatchTouchEvent(motionEvent);
        }
        if (this.f6831b != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.f6831b.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
        MotionEvent obtain2 = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX(), this.f6845p, motionEvent.getMetaState());
        obtain2.setAction(0);
        z = this.f6830a.dispatchTouchEvent(obtain2);
        obtain2.recycle();
        this.f6846q = false;
        return z;
    }

    /* renamed from: d */
    private boolean m8038d(int i) {
        return i == 0 || this.f6836g.b(i) != this.f6836g.b(i - 1);
    }

    /* renamed from: a */
    public int m8045a(int i) {
        if (m8038d(Math.max(0, i - getHeaderViewsCount()))) {
            return 0;
        }
        View a = this.f6836g.a(i, null, this.f6830a);
        if (a == null) {
            throw new NullPointerException("header may not be null");
        }
        m8029b(a);
        m8034c(a);
        return a.getMeasuredHeight();
    }

    /* renamed from: d */
    private int m8035d() {
        return (this.f6838i ? this.f6842m : 0) + this.f6840k;
    }

    public void setAreHeadersSticky(boolean z) {
        this.f6837h = z;
        if (z) {
            m8028b(this.f6830a.a());
        } else {
            m8027b();
        }
        this.f6830a.invalidate();
    }

    /* renamed from: a */
    public boolean m8048a() {
        return this.f6837h;
    }

    @Deprecated
    public boolean getAreHeadersSticky() {
        return m8048a();
    }

    public void setStickyHeaderTopOffset(int i) {
        this.f6840k = i;
        m8028b(this.f6830a.a());
    }

    public int getStickyHeaderTopOffset() {
        return this.f6840k;
    }

    public void setDrawingListUnderStickyHeader(boolean z) {
        this.f6839j = z;
        this.f6830a.a(0);
    }

    public void setOnHeaderClickListener(StickyListHeadersListView$c stickyListHeadersListView$c) {
        this.f6848s = stickyListHeadersListView$c;
        if (this.f6836g == null) {
            return;
        }
        if (this.f6848s != null) {
            this.f6836g.a(new StickyListHeadersListView$b(this, null));
            if (this.f6831b != null) {
                this.f6831b.setOnClickListener(new StickyListHeadersListView$2(this));
                return;
            }
            return;
        }
        this.f6836g.a(null);
    }

    public void setOnStickyHeaderOffsetChangedListener(StickyListHeadersListView$e stickyListHeadersListView$e) {
        this.f6849t = stickyListHeadersListView$e;
    }

    public void setOnStickyHeaderChangedListener(StickyListHeadersListView$d stickyListHeadersListView$d) {
        this.f6850u = stickyListHeadersListView$d;
    }

    public int getListChildCount() {
        return this.f6830a.getChildCount();
    }

    public ListView getWrappedList() {
        return this.f6830a;
    }

    /* renamed from: e */
    private boolean m8040e(int i) {
        if (VERSION.SDK_INT >= i) {
            return true;
        }
        Log.e("StickyListHeaders", "Api lvl must be at least " + i + " to call this method");
        return false;
    }

    public void setAdapter(C2108d c2108d) {
        if (c2108d == null) {
            if (this.f6836g instanceof C2785c) {
                ((C2785c) this.f6836g).f12991b = null;
            }
            if (this.f6836g != null) {
                this.f6836g.f12979a = null;
            }
            this.f6830a.setAdapter(null);
            m8027b();
            return;
        }
        if (this.f6836g != null) {
            this.f6836g.unregisterDataSetObserver(this.f6851v);
        }
        if (c2108d instanceof SectionIndexer) {
            this.f6836g = new C2785c(getContext(), c2108d);
        } else {
            this.f6836g = new C2782a(getContext(), c2108d);
        }
        this.f6851v = new StickyListHeadersListView$a(this, null);
        this.f6836g.registerDataSetObserver(this.f6851v);
        if (this.f6848s != null) {
            this.f6836g.a(new StickyListHeadersListView$b(this, null));
        } else {
            this.f6836g.a(null);
        }
        this.f6836g.a(this.f6852w, this.f6853x);
        this.f6830a.setAdapter(this.f6836g);
        m8027b();
    }

    public C2108d getAdapter() {
        return this.f6836g == null ? null : this.f6836g.f12979a;
    }

    public void setDivider(Drawable drawable) {
        this.f6852w = drawable;
        if (this.f6836g != null) {
            this.f6836g.a(this.f6852w, this.f6853x);
        }
    }

    public void setDividerHeight(int i) {
        this.f6853x = i;
        if (this.f6836g != null) {
            this.f6836g.a(this.f6852w, this.f6853x);
        }
    }

    public Drawable getDivider() {
        return this.f6852w;
    }

    public int getDividerHeight() {
        return this.f6853x;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f6835f = onScrollListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (onTouchListener != null) {
            this.f6830a.setOnTouchListener(new StickyListHeadersListView$3(this, onTouchListener));
        } else {
            this.f6830a.setOnTouchListener(null);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f6830a.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.f6830a.setOnItemLongClickListener(onItemLongClickListener);
    }

    /* renamed from: a */
    public void m8047a(View view) {
        this.f6830a.addHeaderView(view);
    }

    public int getHeaderViewsCount() {
        return this.f6830a.getHeaderViewsCount();
    }

    public int getFooterViewsCount() {
        return this.f6830a.getFooterViewsCount();
    }

    public void setEmptyView(View view) {
        this.f6830a.setEmptyView(view);
    }

    public View getEmptyView() {
        return this.f6830a.getEmptyView();
    }

    public boolean isVerticalScrollBarEnabled() {
        return this.f6830a.isVerticalScrollBarEnabled();
    }

    public boolean isHorizontalScrollBarEnabled() {
        return this.f6830a.isHorizontalScrollBarEnabled();
    }

    public void setVerticalScrollBarEnabled(boolean z) {
        this.f6830a.setVerticalScrollBarEnabled(z);
    }

    public void setHorizontalScrollBarEnabled(boolean z) {
        this.f6830a.setHorizontalScrollBarEnabled(z);
    }

    @TargetApi(9)
    public int getOverScrollMode() {
        if (m8040e(9)) {
            return this.f6830a.getOverScrollMode();
        }
        return 0;
    }

    @TargetApi(9)
    public void setOverScrollMode(int i) {
        if (m8040e(9) && this.f6830a != null) {
            this.f6830a.setOverScrollMode(i);
        }
    }

    public void setSelection(int i) {
        m8046a(i, 0);
    }

    /* renamed from: a */
    public void m8046a(int i, int i2) {
        int i3 = 0;
        int a = (this.f6836g == null ? 0 : m8045a(i)) + i2;
        if (!this.f6838i) {
            i3 = this.f6842m;
        }
        this.f6830a.setSelectionFromTop(i, a - i3);
    }

    public void setSelector(Drawable drawable) {
        this.f6830a.setSelector(drawable);
    }

    public void setSelector(int i) {
        this.f6830a.setSelector(i);
    }

    public int getFirstVisiblePosition() {
        return this.f6830a.getFirstVisiblePosition();
    }

    public int getLastVisiblePosition() {
        return this.f6830a.getLastVisiblePosition();
    }

    @TargetApi(11)
    public void setChoiceMode(int i) {
        this.f6830a.setChoiceMode(i);
    }

    @TargetApi(11)
    public int getCheckedItemCount() {
        if (m8040e(11)) {
            return this.f6830a.getCheckedItemCount();
        }
        return 0;
    }

    @TargetApi(8)
    public long[] getCheckedItemIds() {
        if (m8040e(8)) {
            return this.f6830a.getCheckedItemIds();
        }
        return null;
    }

    @TargetApi(11)
    public int getCheckedItemPosition() {
        return this.f6830a.getCheckedItemPosition();
    }

    @TargetApi(11)
    public SparseBooleanArray getCheckedItemPositions() {
        return this.f6830a.getCheckedItemPositions();
    }

    public int getCount() {
        return this.f6830a.getCount();
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        this.f6830a.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public boolean showContextMenu() {
        return this.f6830a.showContextMenu();
    }

    public void setClipToPadding(boolean z) {
        if (this.f6830a != null) {
            this.f6830a.setClipToPadding(z);
        }
        this.f6838i = z;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f6841l = i;
        this.f6842m = i2;
        this.f6843n = i3;
        this.f6844o = i4;
        if (this.f6830a != null) {
            this.f6830a.setPadding(i, i2, i3, i4);
        }
        super.setPadding(0, 0, 0, 0);
        requestLayout();
    }

    public int getPaddingLeft() {
        return this.f6841l;
    }

    public int getPaddingTop() {
        return this.f6842m;
    }

    public int getPaddingRight() {
        return this.f6843n;
    }

    public int getPaddingBottom() {
        return this.f6844o;
    }

    public void setFastScrollEnabled(boolean z) {
        this.f6830a.setFastScrollEnabled(z);
    }

    @TargetApi(11)
    public void setFastScrollAlwaysVisible(boolean z) {
        if (m8040e(11)) {
            this.f6830a.setFastScrollAlwaysVisible(z);
        }
    }

    public void setScrollBarStyle(int i) {
        this.f6830a.setScrollBarStyle(i);
    }

    public int getScrollBarStyle() {
        return this.f6830a.getScrollBarStyle();
    }

    @TargetApi(11)
    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (m8040e(11)) {
            this.f6830a.setMultiChoiceModeListener(multiChoiceModeListener);
        }
    }

    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() == BaseSavedState.EMPTY_STATE) {
            return this.f6830a.onSaveInstanceState();
        }
        throw new IllegalStateException("Handling non empty state of parent class is not implemented");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(BaseSavedState.EMPTY_STATE);
        this.f6830a.onRestoreInstanceState(parcelable);
    }

    @TargetApi(14)
    public boolean canScrollVertically(int i) {
        return this.f6830a.canScrollVertically(i);
    }

    public void setTranscriptMode(int i) {
        this.f6830a.setTranscriptMode(i);
    }

    public void setBlockLayoutChildren(boolean z) {
        this.f6830a.a(z);
    }

    public void setStackFromBottom(boolean z) {
        this.f6830a.setStackFromBottom(z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f6854y) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setCanTouch(boolean z) {
        this.f6854y = z;
    }
}
