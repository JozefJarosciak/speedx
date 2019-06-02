package com.beastbikes.android.widget.stickylistlibrary.stickygridheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.C2771b.C2769b;
import com.google.common.primitives.Ints;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StickyGridHeadersGridView extends GridView implements OnScrollListener, OnItemClickListener, OnItemLongClickListener, OnItemSelectedListener {
    /* renamed from: a */
    static final String f12906a = StickyGridHeadersGridView.class.getSimpleName();
    /* renamed from: i */
    private static final String f12907i = ("Error supporting platform " + VERSION.SDK_INT + ".");
    /* renamed from: A */
    private OnItemClickListener f12908A;
    /* renamed from: B */
    private OnItemLongClickListener f12909B;
    /* renamed from: C */
    private OnItemSelectedListener f12910C;
    /* renamed from: D */
    private C2765e f12911D;
    /* renamed from: E */
    private OnScrollListener f12912E;
    /* renamed from: F */
    private int f12913F;
    /* renamed from: G */
    private View f12914G;
    /* renamed from: H */
    private Runnable f12915H;
    /* renamed from: I */
    private int f12916I;
    /* renamed from: J */
    private int f12917J;
    /* renamed from: b */
    public C2761a f12918b;
    /* renamed from: c */
    public C2762b f12919c;
    /* renamed from: d */
    protected C2771b f12920d;
    /* renamed from: e */
    protected boolean f12921e;
    /* renamed from: f */
    protected int f12922f;
    /* renamed from: g */
    protected int f12923g;
    /* renamed from: h */
    boolean f12924h;
    /* renamed from: j */
    private boolean f12925j;
    /* renamed from: k */
    private final Rect f12926k;
    /* renamed from: l */
    private boolean f12927l;
    /* renamed from: m */
    private boolean f12928m;
    /* renamed from: n */
    private int f12929n;
    /* renamed from: o */
    private long f12930o;
    /* renamed from: p */
    private DataSetObserver f12931p;
    /* renamed from: q */
    private int f12932q;
    /* renamed from: r */
    private boolean f12933r;
    /* renamed from: s */
    private int f12934s;
    /* renamed from: t */
    private boolean f12935t;
    /* renamed from: u */
    private float f12936u;
    /* renamed from: v */
    private int f12937v;
    /* renamed from: w */
    private boolean f12938w;
    /* renamed from: x */
    private int f12939x;
    /* renamed from: y */
    private C2763c f12940y;
    /* renamed from: z */
    private C2764d f12941z;

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$1 */
    class C27561 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ StickyGridHeadersGridView f12892a;

        C27561(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.f12892a = stickyGridHeadersGridView;
        }

        public void onChanged() {
            this.f12892a.m13664c();
        }

        public void onInvalidated() {
            this.f12892a.m13664c();
        }
    }

    class RuntimePlatformSupportException extends RuntimeException {
        private static final long serialVersionUID = -6512098808936536538L;
        /* renamed from: a */
        final /* synthetic */ StickyGridHeadersGridView f12898a;

        public RuntimePlatformSupportException(StickyGridHeadersGridView stickyGridHeadersGridView, Exception exception) {
            this.f12898a = stickyGridHeadersGridView;
            super(StickyGridHeadersGridView.f12907i, exception);
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C27591();
        /* renamed from: a */
        boolean f12899a;

        /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$SavedState$1 */
        static class C27591 implements Creator<SavedState> {
            C27591() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m13646a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m13647a(i);
            }

            /* renamed from: a */
            public SavedState m13646a(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] m13647a(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f12899a = parcel.readByte() != (byte) 0;
        }

        public String toString() {
            return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.f12899a + C0880h.f2222d;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte((byte) (this.f12899a ? 1 : 0));
        }
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$f */
    private class C2760f {
        /* renamed from: a */
        private int f12900a;
        /* renamed from: c */
        final /* synthetic */ StickyGridHeadersGridView f12901c;

        private C2760f(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.f12901c = stickyGridHeadersGridView;
        }

        /* renamed from: a */
        public void m13648a() {
            this.f12900a = this.f12901c.getWindowAttachCount();
        }

        /* renamed from: b */
        public boolean m13649b() {
            return this.f12901c.hasWindowFocus() && this.f12901c.getWindowAttachCount() == this.f12900a;
        }
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$a */
    private class C2761a extends C2760f implements Runnable {
        /* renamed from: a */
        final /* synthetic */ StickyGridHeadersGridView f12902a;

        private C2761a(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.f12902a = stickyGridHeadersGridView;
            super();
        }

        public void run() {
            View a = this.f12902a.m13667a(this.f12902a.f12922f);
            if (a != null) {
                boolean z;
                long a2 = this.f12902a.m13660b(this.f12902a.f12922f);
                if (!m13649b() || this.f12902a.f12921e) {
                    z = false;
                } else {
                    z = this.f12902a.m13671b(a, a2);
                }
                if (z) {
                    this.f12902a.f12923g = -2;
                    this.f12902a.setPressed(false);
                    a.setPressed(false);
                    return;
                }
                this.f12902a.f12923g = 2;
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$b */
    final class C2762b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ StickyGridHeadersGridView f12903a;

        C2762b(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.f12903a = stickyGridHeadersGridView;
        }

        public void run() {
            if (this.f12903a.f12923g == 0) {
                this.f12903a.f12923g = 1;
                View a = this.f12903a.m13667a(this.f12903a.f12922f);
                if (a != null && !this.f12903a.f12924h) {
                    if (this.f12903a.f12921e) {
                        this.f12903a.f12923g = 2;
                        return;
                    }
                    a.setPressed(true);
                    this.f12903a.setPressed(true);
                    this.f12903a.refreshDrawableState();
                    int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    if (this.f12903a.isLongClickable()) {
                        if (this.f12903a.f12918b == null) {
                            this.f12903a.f12918b = new C2761a();
                        }
                        this.f12903a.f12918b.m13648a();
                        this.f12903a.postDelayed(this.f12903a.f12918b, (long) longPressTimeout);
                        return;
                    }
                    this.f12903a.f12923g = 2;
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$c */
    public interface C2763c {
        /* renamed from: a */
        void m13650a(AdapterView<?> adapterView, View view, long j);
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$d */
    public interface C2764d {
        /* renamed from: a */
        boolean m13651a(AdapterView<?> adapterView, View view, long j);
    }

    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView$e */
    private class C2765e extends C2760f implements Runnable {
        /* renamed from: a */
        int f12904a;
        /* renamed from: b */
        final /* synthetic */ StickyGridHeadersGridView f12905b;

        private C2765e(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.f12905b = stickyGridHeadersGridView;
            super();
        }

        public void run() {
            if (!this.f12905b.f12921e && this.f12905b.f12920d != null && this.f12905b.f12920d.getCount() > 0 && this.f12904a != -1 && this.f12904a < this.f12905b.f12920d.getCount() && m13649b()) {
                View a = this.f12905b.m13667a(this.f12904a);
                if (a != null) {
                    this.f12905b.m13669a(a, this.f12905b.m13660b(this.f12904a));
                }
            }
        }
    }

    /* renamed from: a */
    private static PointerCoords[] m13658a(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        PointerCoords[] pointerCoordsArr = new PointerCoords[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerCoordsArr[i] = new PointerCoords();
            motionEvent.getPointerCoords(i, pointerCoordsArr[i]);
        }
        return pointerCoordsArr;
    }

    /* renamed from: b */
    private static int[] m13662b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
        }
        return iArr;
    }

    public StickyGridHeadersGridView(Context context) {
        this(context, null);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842865);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12925j = true;
        this.f12926k = new Rect();
        this.f12930o = -1;
        this.f12931p = new C27561(this);
        this.f12935t = true;
        this.f12939x = 1;
        this.f12913F = 0;
        this.f12924h = false;
        super.setOnScrollListener(this);
        setVerticalFadingEdgeEnabled(false);
        if (!this.f12938w) {
            this.f12937v = -1;
        }
        this.f12916I = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /* renamed from: a */
    public View m13667a(int i) {
        if (i == -2) {
            return this.f12914G;
        }
        try {
            return (View) getChildAt(i).getTag();
        } catch (Exception e) {
            return null;
        }
    }

    public View getStickiedHeader() {
        return this.f12914G;
    }

    public boolean getStickyHeaderIsTranscluent() {
        return !this.f12935t;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f12908A.onItemClick(adapterView, view, this.f12920d.m13683c(i).f12948b, j);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return this.f12909B.onItemLongClick(adapterView, view, this.f12920d.m13683c(i).f12948b, j);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.f12910C.onItemSelected(adapterView, view, this.f12920d.m13683c(i).f12948b, j);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        this.f12910C.onNothingSelected(adapterView);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f12925j = savedState.f12899a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f12899a = this.f12925j;
        return savedState;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.f12912E != null) {
            this.f12912E.onScroll(absListView, i, i2, i3);
        }
        if (VERSION.SDK_INT >= 8) {
            m13665c(i);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.f12912E != null) {
            this.f12912E.onScrollStateChanged(absListView, i);
        }
        this.f12913F = i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View a;
        View view;
        int action = motionEvent.getAction();
        boolean z = this.f12924h;
        if (this.f12924h) {
            a = m13667a(this.f12922f);
            if (this.f12922f == -2) {
                view = a;
            } else {
                view = getChildAt(this.f12922f);
            }
            if (action == 1 || action == 3) {
                this.f12924h = false;
            }
            if (a != null) {
                a.dispatchTouchEvent(m13654a(motionEvent, this.f12922f));
                a.invalidate();
                a.postDelayed(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ StickyGridHeadersGridView f12894b;

                    public void run() {
                        this.f12894b.invalidate(0, view.getTop(), this.f12894b.getWidth(), view.getTop() + view.getHeight());
                    }
                }, (long) ViewConfiguration.getPressedStateDuration());
                invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
            }
        }
        switch (action & 255) {
            case 0:
                if (this.f12919c == null) {
                    this.f12919c = new C2762b(this);
                }
                postDelayed(this.f12919c, (long) ViewConfiguration.getTapTimeout());
                int y = (int) motionEvent.getY();
                this.f12936u = (float) y;
                this.f12922f = m13652a((float) y);
                if (!(this.f12922f == -1 || this.f12913F == 2)) {
                    view = m13667a(this.f12922f);
                    if (view != null) {
                        if (view.dispatchTouchEvent(m13654a(motionEvent, this.f12922f))) {
                            this.f12924h = true;
                            view.setPressed(true);
                        }
                        view.invalidate();
                        if (this.f12922f != -2) {
                            view = getChildAt(this.f12922f);
                        }
                        invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
                    }
                    this.f12923g = 0;
                    return true;
                }
            case 1:
                if (this.f12923g == -2) {
                    this.f12923g = -1;
                    return true;
                } else if (!(this.f12923g == -1 || this.f12922f == -1)) {
                    a = m13667a(this.f12922f);
                    if (!(z || a == null)) {
                        if (this.f12923g != 0) {
                            a.setPressed(false);
                        }
                        if (this.f12911D == null) {
                            this.f12911D = new C2765e();
                        }
                        final C2765e c2765e = this.f12911D;
                        c2765e.f12904a = this.f12922f;
                        c2765e.m13648a();
                        if (this.f12923g == 0 || this.f12923g == 1) {
                            Handler handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.f12923g == 0 ? this.f12919c : this.f12918b);
                            }
                            if (this.f12921e) {
                                this.f12923g = -1;
                            } else {
                                this.f12923g = 1;
                                a.setPressed(true);
                                setPressed(true);
                                if (this.f12915H != null) {
                                    removeCallbacks(this.f12915H);
                                }
                                this.f12915H = new Runnable(this) {
                                    /* renamed from: c */
                                    final /* synthetic */ StickyGridHeadersGridView f12897c;

                                    public void run() {
                                        this.f12897c.f12922f = -1;
                                        this.f12897c.f12915H = null;
                                        this.f12897c.f12923g = -1;
                                        a.setPressed(false);
                                        this.f12897c.setPressed(false);
                                        a.invalidate();
                                        this.f12897c.invalidate(0, a.getTop(), this.f12897c.getWidth(), a.getHeight());
                                        if (!this.f12897c.f12921e) {
                                            c2765e.run();
                                        }
                                    }
                                };
                                postDelayed(this.f12915H, (long) ViewConfiguration.getPressedStateDuration());
                            }
                        } else if (!this.f12921e) {
                            c2765e.run();
                        }
                    }
                    this.f12923g = -1;
                    return true;
                }
                break;
            case 2:
                if (this.f12922f != -1 && Math.abs(motionEvent.getY() - this.f12936u) > ((float) this.f12916I)) {
                    this.f12923g = -1;
                    view = m13667a(this.f12922f);
                    if (view != null) {
                        view.setPressed(false);
                        view.invalidate();
                    }
                    Handler handler2 = getHandler();
                    if (handler2 != null) {
                        handler2.removeCallbacks(this.f12918b);
                    }
                    this.f12922f = -1;
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public boolean m13669a(View view, long j) {
        if (this.f12940y == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.f12940y.m13650a(this, view, j);
        return true;
    }

    /* renamed from: b */
    public boolean m13671b(View view, long j) {
        boolean a;
        if (this.f12941z != null) {
            a = this.f12941z.m13651a(this, view, j);
        } else {
            a = false;
        }
        if (a) {
            if (view != null) {
                view.sendAccessibilityEvent(2);
            }
            performHapticFeedback(0);
        }
        return a;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.f12920d == null || this.f12931p == null)) {
            this.f12920d.unregisterDataSetObserver(this.f12931p);
        }
        if (!this.f12928m) {
            this.f12927l = true;
        }
        if (listAdapter instanceof C2766a) {
            listAdapter = (C2766a) listAdapter;
        } else if (listAdapter instanceof C2095d) {
            r3 = new C2777e((C2095d) listAdapter);
        } else {
            r3 = new C2773c(listAdapter);
        }
        this.f12920d = new C2771b(getContext(), this, listAdapter);
        this.f12920d.registerDataSetObserver(this.f12931p);
        m13664c();
        super.setAdapter(this.f12920d);
    }

    public void setAreHeadersSticky(boolean z) {
        if (z != this.f12925j) {
            this.f12925j = z;
            requestLayout();
        }
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.f12927l = z;
        this.f12928m = true;
    }

    public void setColumnWidth(int i) {
        super.setColumnWidth(i);
        this.f12929n = i;
    }

    public void setHeadersIgnorePadding(boolean z) {
        this.f12933r = z;
    }

    public void setHorizontalSpacing(int i) {
        super.setHorizontalSpacing(i);
        this.f12934s = i;
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.f12938w = true;
        this.f12937v = i;
        if (i != -1 && this.f12920d != null) {
            this.f12920d.m13681a(i);
        }
    }

    public void setOnHeaderClickListener(C2763c c2763c) {
        this.f12940y = c2763c;
    }

    public void setOnHeaderLongClickListener(C2764d c2764d) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.f12941z = c2764d;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f12908A = onItemClickListener;
        super.setOnItemClickListener(this);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.f12909B = onItemLongClickListener;
        super.setOnItemLongClickListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.f12910C = onItemSelectedListener;
        super.setOnItemSelectedListener(this);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f12912E = onScrollListener;
    }

    public void setStickyHeaderIsTranscluent(boolean z) {
        this.f12935t = !z;
    }

    public void setVerticalSpacing(int i) {
        super.setVerticalSpacing(i);
        this.f12917J = i;
    }

    /* renamed from: a */
    private int m13652a(float f) {
        if (this.f12914G != null && f <= ((float) this.f12932q)) {
            return -2;
        }
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                View childAt = getChildAt(i);
                int bottom = childAt.getBottom();
                int top = childAt.getTop();
                if (f <= ((float) bottom) && f >= ((float) top)) {
                    return i;
                }
            }
            firstVisiblePosition += this.f12939x;
            i += this.f12939x;
        }
        return -1;
    }

    private int getHeaderHeight() {
        if (this.f12914G != null) {
            return this.f12914G.getMeasuredHeight();
        }
        return 0;
    }

    /* renamed from: b */
    private long m13660b(int i) {
        if (i == -2) {
            return this.f12930o;
        }
        return this.f12920d.m13682b(getFirstVisiblePosition() + i);
    }

    /* renamed from: b */
    private void m13661b() {
        if (this.f12914G != null) {
            int makeMeasureSpec;
            int makeMeasureSpec2;
            if (this.f12933r) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), Ints.MAX_POWER_OF_TWO);
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), Ints.MAX_POWER_OF_TWO);
            }
            LayoutParams layoutParams = this.f12914G.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            } else {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(layoutParams.height, Ints.MAX_POWER_OF_TWO);
            }
            this.f12914G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.f12914G.measure(makeMeasureSpec, makeMeasureSpec2);
            if (this.f12933r) {
                this.f12914G.layout(getLeft(), 0, getRight(), this.f12914G.getMeasuredHeight());
            } else {
                this.f12914G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.f12914G.getMeasuredHeight());
            }
        }
    }

    /* renamed from: c */
    private void m13664c() {
        this.f12932q = 0;
        m13666c(null);
        this.f12930o = Long.MIN_VALUE;
    }

    /* renamed from: c */
    private void m13665c(int i) {
        if (this.f12920d != null && this.f12920d.getCount() != 0 && this.f12925j && getChildAt(0) != null) {
            long b;
            int i2 = i - this.f12939x;
            if (i2 < 0) {
                i2 = i;
            }
            int i3 = this.f12939x + i;
            if (i3 >= this.f12920d.getCount()) {
                i3 = i;
            }
            if (this.f12917J == 0) {
                b = this.f12920d.m13682b(i);
                i3 = i;
            } else if (this.f12917J < 0) {
                this.f12920d.m13682b(i);
                if (getChildAt(this.f12939x).getTop() <= 0) {
                    b = this.f12920d.m13682b(i3);
                } else {
                    b = this.f12920d.m13682b(i);
                    i3 = i;
                }
            } else {
                i3 = getChildAt(0).getTop();
                if (i3 <= 0 || i3 >= this.f12917J) {
                    b = this.f12920d.m13682b(i);
                    i3 = i;
                } else {
                    b = this.f12920d.m13682b(i2);
                    i3 = i2;
                }
            }
            if (this.f12930o != b) {
                m13666c(this.f12920d.m13679a(i3, this.f12914G, (ViewGroup) this));
                m13661b();
                this.f12930o = b;
            }
            int childCount = getChildCount();
            if (childCount != 0) {
                View view = null;
                i2 = 99999;
                int i4 = 0;
                while (i4 < childCount) {
                    View view2;
                    View childAt = super.getChildAt(i4);
                    if (this.f12927l) {
                        i3 = childAt.getTop() - getPaddingTop();
                    } else {
                        i3 = childAt.getTop();
                    }
                    if (i3 < 0) {
                        view2 = view;
                    } else if (this.f12920d.getItemId(getPositionForView(childAt)) != -1 || i3 >= r0) {
                        view2 = view;
                    } else {
                        i2 = i3;
                        view2 = childAt;
                    }
                    i4 = this.f12939x + i4;
                    view = view2;
                }
                i2 = getHeaderHeight();
                if (view == null) {
                    this.f12932q = i2;
                    if (this.f12927l) {
                        this.f12932q += getPaddingTop();
                    }
                } else if (i == 0 && super.getChildAt(0).getTop() > 0 && !this.f12927l) {
                    this.f12932q = 0;
                } else if (this.f12927l) {
                    this.f12932q = Math.min(view.getTop(), getPaddingTop() + i2);
                    this.f12932q = this.f12932q < getPaddingTop() ? i2 + getPaddingTop() : this.f12932q;
                } else {
                    this.f12932q = Math.min(view.getTop(), i2);
                    if (this.f12932q >= 0) {
                        i2 = this.f12932q;
                    }
                    this.f12932q = i2;
                }
            }
        }
    }

    /* renamed from: c */
    private void m13666c(View view) {
        m13670b(this.f12914G);
        m13668a(view);
        this.f12914G = view;
    }

    /* renamed from: a */
    private MotionEvent m13654a(MotionEvent motionEvent, int i) {
        if (i == -2) {
            return motionEvent;
        }
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        int[] b = m13662b(motionEvent);
        PointerCoords[] a = m13658a(motionEvent);
        int metaState = motionEvent.getMetaState();
        float xPrecision = motionEvent.getXPrecision();
        float yPrecision = motionEvent.getYPrecision();
        int deviceId = motionEvent.getDeviceId();
        int edgeFlags = motionEvent.getEdgeFlags();
        int source = motionEvent.getSource();
        int flags = motionEvent.getFlags();
        View childAt = getChildAt(i);
        for (int i2 = 0; i2 < pointerCount; i2++) {
            PointerCoords pointerCoords = a[i2];
            pointerCoords.y -= (float) childAt.getTop();
        }
        return MotionEvent.obtain(downTime, eventTime, action, pointerCount, b, a, metaState, xPrecision, yPrecision, deviceId, edgeFlags, source, flags);
    }

    protected void dispatchDraw(Canvas canvas) {
        Object obj;
        if (VERSION.SDK_INT < 8) {
            m13665c(getFirstVisiblePosition());
        }
        if (this.f12914G != null && this.f12925j && this.f12914G.getVisibility() == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        int headerHeight = getHeaderHeight();
        int i = this.f12932q - headerHeight;
        if (obj != null && this.f12935t) {
            if (this.f12933r) {
                this.f12926k.left = 0;
                this.f12926k.right = getWidth();
            } else {
                this.f12926k.left = getPaddingLeft();
                this.f12926k.right = getWidth() - getPaddingRight();
            }
            this.f12926k.top = this.f12932q;
            this.f12926k.bottom = getHeight();
            canvas.save();
            canvas.clipRect(this.f12926k);
        }
        super.dispatchDraw(canvas);
        List arrayList = new ArrayList();
        int i2 = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                arrayList.add(Integer.valueOf(i2));
            }
            firstVisiblePosition += this.f12939x;
            i2 += this.f12939x;
        }
        int i3 = 0;
        while (i3 < arrayList.size()) {
            View childAt = getChildAt(((Integer) arrayList.get(i3)).intValue());
            try {
                View view = (View) childAt.getTag();
                Object obj2 = (((long) ((C2769b) childAt).getHeaderId()) == this.f12930o && childAt.getTop() < 0 && this.f12925j) ? 1 : null;
                if (view.getVisibility() == 0 && obj2 == null) {
                    if (this.f12933r) {
                        i2 = MeasureSpec.makeMeasureSpec(getWidth(), Ints.MAX_POWER_OF_TWO);
                    } else {
                        i2 = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), Ints.MAX_POWER_OF_TWO);
                    }
                    int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                    view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    view.measure(i2, makeMeasureSpec);
                    if (this.f12933r) {
                        view.layout(getLeft(), 0, getRight(), childAt.getHeight());
                    } else {
                        view.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), childAt.getHeight());
                    }
                    if (this.f12933r) {
                        this.f12926k.left = 0;
                        this.f12926k.right = getWidth();
                    } else {
                        this.f12926k.left = getPaddingLeft();
                        this.f12926k.right = getWidth() - getPaddingRight();
                    }
                    this.f12926k.bottom = childAt.getBottom();
                    this.f12926k.top = childAt.getTop();
                    canvas.save();
                    canvas.clipRect(this.f12926k);
                    if (this.f12933r) {
                        canvas.translate(0.0f, (float) childAt.getTop());
                    } else {
                        canvas.translate((float) getPaddingLeft(), (float) childAt.getTop());
                    }
                    view.draw(canvas);
                    canvas.restore();
                }
                i3++;
            } catch (Exception e) {
                return;
            }
        }
        if (obj != null && this.f12935t) {
            canvas.restore();
        } else if (obj == null) {
            return;
        }
        if (this.f12933r) {
            firstVisiblePosition = getWidth();
        } else {
            firstVisiblePosition = (getWidth() - getPaddingLeft()) - getPaddingRight();
        }
        if (this.f12914G.getWidth() != firstVisiblePosition) {
            if (this.f12933r) {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec(getWidth(), Ints.MAX_POWER_OF_TWO);
            } else {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), Ints.MAX_POWER_OF_TWO);
            }
            i2 = MeasureSpec.makeMeasureSpec(0, 0);
            this.f12914G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.f12914G.measure(firstVisiblePosition, i2);
            if (this.f12933r) {
                this.f12914G.layout(getLeft(), 0, getRight(), this.f12914G.getHeight());
            } else {
                this.f12914G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.f12914G.getHeight());
            }
        }
        if (this.f12933r) {
            this.f12926k.left = 0;
            this.f12926k.right = getWidth();
        } else {
            this.f12926k.left = getPaddingLeft();
            this.f12926k.right = getWidth() - getPaddingRight();
        }
        this.f12926k.bottom = i + headerHeight;
        if (this.f12927l) {
            this.f12926k.top = getPaddingTop();
        } else {
            this.f12926k.top = 0;
        }
        canvas.save();
        canvas.clipRect(this.f12926k);
        if (this.f12933r) {
            canvas.translate(0.0f, (float) i);
        } else {
            canvas.translate((float) getPaddingLeft(), (float) i);
        }
        if (this.f12932q != headerHeight) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (this.f12932q * 255) / headerHeight, 31);
        }
        this.f12914G.draw(canvas);
        if (this.f12932q != headerHeight) {
            canvas.restore();
        }
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1;
        if (this.f12937v == -1) {
            if (this.f12929n > 0) {
                int max = Math.max((MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight(), 0);
                int i4 = max / this.f12929n;
                if (i4 > 0) {
                    while (i4 != 1 && (this.f12929n * i4) + ((i4 - 1) * this.f12934s) > max) {
                        i4--;
                    }
                    i3 = i4;
                }
            } else {
                i3 = 2;
            }
            this.f12939x = i3;
        } else {
            this.f12939x = this.f12937v;
        }
        if (this.f12920d != null) {
            this.f12920d.m13681a(this.f12939x);
        }
        m13661b();
        super.onMeasure(i, i2);
    }

    /* renamed from: a */
    void m13668a(View view) {
        if (view != null) {
            try {
                View.class.getDeclaredField("mAttachInfo").setAccessible(true);
                Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", new Class[]{Class.forName("android.view.View$AttachInfo"), Integer.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[]{r0.get(this), Integer.valueOf(8)});
            } catch (Exception e) {
                throw new RuntimePlatformSupportException(this, e);
            } catch (Exception e2) {
                throw new RuntimePlatformSupportException(this, e2);
            } catch (Exception e22) {
                throw new RuntimePlatformSupportException(this, e22);
            } catch (Exception e222) {
                throw new RuntimePlatformSupportException(this, e222);
            } catch (Exception e2222) {
                throw new RuntimePlatformSupportException(this, e2222);
            } catch (Exception e22222) {
                throw new RuntimePlatformSupportException(this, e22222);
            }
        }
    }

    /* renamed from: b */
    void m13670b(View view) {
        if (view != null) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[0]);
            } catch (Exception e) {
                throw new RuntimePlatformSupportException(this, e);
            } catch (Exception e2) {
                throw new RuntimePlatformSupportException(this, e2);
            } catch (Exception e22) {
                throw new RuntimePlatformSupportException(this, e22);
            } catch (Exception e222) {
                throw new RuntimePlatformSupportException(this, e222);
            }
        }
    }
}
