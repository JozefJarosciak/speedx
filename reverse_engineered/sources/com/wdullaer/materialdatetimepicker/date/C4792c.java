package com.wdullaer.materialdatetimepicker.date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.wdullaer.materialdatetimepicker.C4783c;
import com.wdullaer.materialdatetimepicker.date.C4789b.C4787a;
import com.wdullaer.materialdatetimepicker.date.C4795d.C4793a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: DayPickerView */
/* renamed from: com.wdullaer.materialdatetimepicker.date.c */
public abstract class C4792c extends ListView implements OnScrollListener, C4787a {
    /* renamed from: a */
    public static int f16788a = -1;
    /* renamed from: p */
    private static SimpleDateFormat f16789p = new SimpleDateFormat("yyyy", Locale.getDefault());
    /* renamed from: b */
    protected int f16790b = 6;
    /* renamed from: c */
    protected boolean f16791c = false;
    /* renamed from: d */
    protected int f16792d = 7;
    /* renamed from: e */
    protected float f16793e = 1.0f;
    /* renamed from: f */
    protected Context f16794f;
    /* renamed from: g */
    protected Handler f16795g;
    /* renamed from: h */
    protected C4793a f16796h = new C4793a();
    /* renamed from: i */
    protected C4795d f16797i;
    /* renamed from: j */
    protected C4793a f16798j = new C4793a();
    /* renamed from: k */
    protected int f16799k;
    /* renamed from: l */
    protected long f16800l;
    /* renamed from: m */
    protected int f16801m = 0;
    /* renamed from: n */
    protected int f16802n = 0;
    /* renamed from: o */
    protected C4791a f16803o = new C4791a(this);
    /* renamed from: q */
    private C4784a f16804q;
    /* renamed from: r */
    private boolean f16805r;

    /* compiled from: DayPickerView */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.c$a */
    protected class C4791a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4792c f16786a;
        /* renamed from: b */
        private int f16787b;

        protected C4791a(C4792c c4792c) {
            this.f16786a = c4792c;
        }

        /* renamed from: a */
        public void m18818a(AbsListView absListView, int i) {
            this.f16786a.f16795g.removeCallbacks(this);
            this.f16787b = i;
            this.f16786a.f16795g.postDelayed(this, 40);
        }

        public void run() {
            int i = 1;
            this.f16786a.f16802n = this.f16787b;
            if (Log.isLoggable("MonthFragment", 3)) {
                Log.d("MonthFragment", "new scroll state: " + this.f16787b + " old state: " + this.f16786a.f16801m);
            }
            if (this.f16787b != 0 || this.f16786a.f16801m == 0 || this.f16786a.f16801m == 1) {
                this.f16786a.f16801m = this.f16787b;
                return;
            }
            this.f16786a.f16801m = this.f16787b;
            View childAt = this.f16786a.getChildAt(0);
            int i2 = 0;
            while (childAt != null && childAt.getBottom() <= 0) {
                i2++;
                childAt = this.f16786a.getChildAt(i2);
            }
            if (childAt != null) {
                i2 = this.f16786a.getFirstVisiblePosition();
                int lastVisiblePosition = this.f16786a.getLastVisiblePosition();
                if (i2 == 0 || lastVisiblePosition == this.f16786a.getCount() - 1) {
                    i = 0;
                }
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                i2 = this.f16786a.getHeight() / 2;
                if (i != 0 && top < C4792c.f16788a) {
                    if (bottom > i2) {
                        this.f16786a.smoothScrollBy(top, 250);
                    } else {
                        this.f16786a.smoothScrollBy(bottom, 250);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public abstract C4795d mo6199a(Context context, C4784a c4784a);

    public C4792c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18825a(context);
    }

    public C4792c(Context context, C4784a c4784a) {
        super(context);
        m18825a(context);
        setController(c4784a);
    }

    public void setController(C4784a c4784a) {
        this.f16804q = c4784a;
        this.f16804q.mo6188a((C4787a) this);
        m18828c();
        mo6197a();
    }

    /* renamed from: a */
    public void m18825a(Context context) {
        this.f16795g = new Handler();
        setLayoutParams(new LayoutParams(-1, -1));
        setDrawSelectorOnTop(false);
        this.f16794f = context;
        m18829d();
    }

    /* renamed from: b */
    public void m18827b() {
        m18828c();
    }

    /* renamed from: c */
    protected void m18828c() {
        if (this.f16797i == null) {
            this.f16797i = mo6199a(getContext(), this.f16804q);
        } else {
            this.f16797i.m18837a(this.f16796h);
        }
        setAdapter(this.f16797i);
    }

    /* renamed from: d */
    protected void m18829d() {
        setCacheColorHint(0);
        setDivider(null);
        setItemsCanFocus(true);
        setFastScrollEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(this);
        setFadingEdgeLength(0);
        setFriction(ViewConfiguration.getScrollFriction() * this.f16793e);
    }

    /* renamed from: a */
    public boolean m18826a(C4793a c4793a, boolean z, boolean z2, boolean z3) {
        if (z2) {
            this.f16796h.m18832a(c4793a);
        }
        this.f16798j.m18832a(c4793a);
        int f = ((c4793a.f16806a - this.f16804q.mo6194f()) * 12) + c4793a.f16807b;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            View childAt = getChildAt(i);
            if (childAt != null) {
                int top = childAt.getTop();
                if (Log.isLoggable("MonthFragment", 3)) {
                    Log.d("MonthFragment", "child at " + (i2 - 1) + " has top " + top);
                }
                if (top >= 0) {
                    break;
                }
                i = i2;
            } else {
                break;
            }
        }
        if (childAt != null) {
            i = getPositionForView(childAt);
        } else {
            i = 0;
        }
        if (z2) {
            this.f16797i.m18837a(this.f16796h);
        }
        if (Log.isLoggable("MonthFragment", 3)) {
            Log.d("MonthFragment", "GoTo position " + f);
        }
        if (f != i || z3) {
            setMonthDisplayed(this.f16798j);
            this.f16801m = 2;
            if (z) {
                smoothScrollToPositionFromTop(f, f16788a, 250);
                return true;
            }
            m18824a(f);
            return false;
        } else if (!z2) {
            return false;
        } else {
            setMonthDisplayed(this.f16796h);
            return false;
        }
    }

    /* renamed from: a */
    public void m18824a(final int i) {
        clearFocus();
        post(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C4792c f16785b;

            public void run() {
                this.f16785b.setSelection(i);
            }
        });
        onScrollStateChanged(this, 0);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        C4797e c4797e = (C4797e) absListView.getChildAt(0);
        if (c4797e != null) {
            this.f16800l = (long) ((absListView.getFirstVisiblePosition() * c4797e.getHeight()) - c4797e.getBottom());
            this.f16801m = this.f16802n;
        }
    }

    protected void setMonthDisplayed(C4793a c4793a) {
        this.f16799k = c4793a.f16807b;
        invalidateViews();
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.f16803o.m18818a(absListView, i);
    }

    public int getMostVisiblePosition() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int height = getHeight();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < height) {
            View childAt = getChildAt(i2);
            if (childAt == null) {
                break;
            }
            int bottom = childAt.getBottom();
            i = Math.min(bottom, height) - Math.max(0, childAt.getTop());
            if (i > i4) {
                i3 = i2;
            } else {
                i = i4;
            }
            i2++;
            i4 = i;
            i = bottom;
        }
        return i3 + firstVisiblePosition;
    }

    /* renamed from: a */
    public void mo6197a() {
        m18826a(this.f16804q.mo6185a(), false, true, true);
    }

    /* renamed from: e */
    private C4793a m18821e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof C4797e) {
                C4793a accessibilityFocus = ((C4797e) childAt).getAccessibilityFocus();
                if (accessibilityFocus != null) {
                    if (VERSION.SDK_INT != 17) {
                        return accessibilityFocus;
                    }
                    ((C4797e) childAt).m18860d();
                    return accessibilityFocus;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private boolean m18819a(C4793a c4793a) {
        if (c4793a == null) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((childAt instanceof C4797e) && ((C4797e) childAt).m18854a(c4793a)) {
                return true;
            }
        }
        return false;
    }

    protected void layoutChildren() {
        C4793a e = m18821e();
        super.layoutChildren();
        if (this.f16805r) {
            this.f16805r = false;
        } else {
            m18819a(e);
        }
    }

    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setItemCount(-1);
    }

    /* renamed from: b */
    private static String m18820b(C4793a c4793a) {
        Calendar instance = Calendar.getInstance();
        instance.set(c4793a.f16806a, c4793a.f16807b, c4793a.f16808c);
        return (("" + instance.getDisplayName(2, 2, Locale.getDefault())) + " ") + f16789p.format(instance.getTime());
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 21) {
            accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_BACKWARD);
            accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_FORWARD);
            return;
        }
        accessibilityNodeInfo.addAction(4096);
        accessibilityNodeInfo.addAction(8192);
    }

    @SuppressLint({"NewApi"})
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 4096 && i != 8192) {
            return super.performAccessibilityAction(i, bundle);
        }
        int firstVisiblePosition = getFirstVisiblePosition();
        C4793a c4793a = new C4793a((firstVisiblePosition / 12) + this.f16804q.mo6194f(), firstVisiblePosition % 12, 1);
        if (i == 4096) {
            c4793a.f16807b++;
            if (c4793a.f16807b == 12) {
                c4793a.f16807b = 0;
                c4793a.f16806a++;
            }
        } else if (i == 8192) {
            View childAt = getChildAt(0);
            if (childAt != null && childAt.getTop() >= -1) {
                c4793a.f16807b--;
                if (c4793a.f16807b == -1) {
                    c4793a.f16807b = 11;
                    c4793a.f16806a--;
                }
            }
        }
        C4783c.m18768a((View) this, C4792c.m18820b(c4793a));
        m18826a(c4793a, true, false, true);
        this.f16805r = true;
        return true;
    }
}
