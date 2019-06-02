package com.beastbikes.android.modules.cycling.route.ui.widget;

import android.graphics.Point;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;

/* compiled from: DragSortController */
/* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.a */
public class C2216a extends C2215d implements OnGestureListener, OnTouchListener {
    /* renamed from: a */
    private int f10460a;
    /* renamed from: b */
    private boolean f10461b;
    /* renamed from: c */
    private int f10462c;
    /* renamed from: d */
    private boolean f10463d;
    /* renamed from: e */
    private boolean f10464e;
    /* renamed from: f */
    private GestureDetector f10465f;
    /* renamed from: g */
    private GestureDetector f10466g;
    /* renamed from: h */
    private int f10467h;
    /* renamed from: i */
    private int f10468i;
    /* renamed from: j */
    private int f10469j;
    /* renamed from: k */
    private int f10470k;
    /* renamed from: l */
    private int[] f10471l;
    /* renamed from: m */
    private int f10472m;
    /* renamed from: n */
    private int f10473n;
    /* renamed from: o */
    private int f10474o;
    /* renamed from: p */
    private int f10475p;
    /* renamed from: q */
    private boolean f10476q;
    /* renamed from: r */
    private float f10477r;
    /* renamed from: s */
    private int f10478s;
    /* renamed from: t */
    private int f10479t;
    /* renamed from: u */
    private int f10480u;
    /* renamed from: v */
    private boolean f10481v;
    /* renamed from: w */
    private DragSortListView f10482w;
    /* renamed from: x */
    private int f10483x;
    /* renamed from: y */
    private OnGestureListener f10484y;

    /* compiled from: DragSortController */
    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.a$1 */
    class C22141 extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ C2216a f10455a;

        C22141(C2216a c2216a) {
            this.f10455a = c2216a;
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.f10455a.f10463d && this.f10455a.f10464e) {
                int width = this.f10455a.f10482w.getWidth() / 5;
                if (f > this.f10455a.f10477r) {
                    if (this.f10455a.f10483x > (-width)) {
                        this.f10455a.f10482w.m11372a(true, f);
                    }
                } else if (f < (-this.f10455a.f10477r) && this.f10455a.f10483x < width) {
                    this.f10455a.f10482w.m11372a(true, f);
                }
                this.f10455a.f10464e = false;
            }
            return false;
        }
    }

    public C2216a(DragSortListView dragSortListView) {
        this(dragSortListView, 0, 0, 1);
    }

    public C2216a(DragSortListView dragSortListView, int i, int i2, int i3) {
        this(dragSortListView, i, i2, i3, 0);
    }

    public C2216a(DragSortListView dragSortListView, int i, int i2, int i3, int i4) {
        this(dragSortListView, i, i2, i3, i4, 0);
    }

    public C2216a(DragSortListView dragSortListView, int i, int i2, int i3, int i4, int i5) {
        super(dragSortListView);
        this.f10460a = 0;
        this.f10461b = true;
        this.f10463d = false;
        this.f10464e = false;
        this.f10468i = -1;
        this.f10469j = -1;
        this.f10470k = -1;
        this.f10471l = new int[2];
        this.f10476q = false;
        this.f10477r = 500.0f;
        this.f10484y = new C22141(this);
        this.f10482w = dragSortListView;
        this.f10465f = new GestureDetector(dragSortListView.getContext(), this);
        this.f10466g = new GestureDetector(dragSortListView.getContext(), this.f10484y);
        this.f10466g.setIsLongpressEnabled(false);
        this.f10467h = ViewConfiguration.get(dragSortListView.getContext()).getScaledTouchSlop();
        this.f10478s = i;
        this.f10479t = i4;
        this.f10480u = i5;
        m11393b(i3);
        m11388a(i2);
    }

    /* renamed from: a */
    public void m11388a(int i) {
        this.f10460a = i;
    }

    /* renamed from: a */
    public void m11390a(boolean z) {
        this.f10461b = z;
    }

    /* renamed from: b */
    public void m11393b(int i) {
        this.f10462c = i;
    }

    /* renamed from: b */
    public void m11394b(boolean z) {
        this.f10463d = z;
    }

    /* renamed from: c */
    public void m11396c(int i) {
        this.f10478s = i;
    }

    /* renamed from: d */
    public void m11398d(int i) {
        this.f10479t = i;
    }

    /* renamed from: a */
    public boolean m11391a(int i, int i2, int i3) {
        int i4 = 0;
        if (this.f10461b && !this.f10464e) {
            i4 = 12;
        }
        if (this.f10463d && this.f10464e) {
            i4 = (i4 | 1) | 2;
        }
        this.f10476q = this.f10482w.m11368a(i - this.f10482w.getHeaderViewsCount(), i4, i2, i3);
        return this.f10476q;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f10482w.m11375c() && !this.f10482w.m11373b()) {
            this.f10465f.onTouchEvent(motionEvent);
            if (this.f10463d && this.f10476q && this.f10462c == 1) {
                this.f10466g.onTouchEvent(motionEvent);
            }
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f10474o = (int) motionEvent.getX();
                    this.f10475p = (int) motionEvent.getY();
                    break;
                case 1:
                    if (this.f10463d && this.f10464e) {
                        int i;
                        if (this.f10483x >= 0) {
                            i = this.f10483x;
                        } else {
                            i = -this.f10483x;
                        }
                        if (i > this.f10482w.getWidth() / 2) {
                            this.f10482w.m11372a(true, 0.0f);
                            break;
                        }
                    }
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            this.f10464e = false;
            this.f10476q = false;
        }
        return false;
    }

    /* renamed from: a */
    public void mo3429a(View view, Point point, Point point2) {
        if (this.f10463d && this.f10464e) {
            this.f10483x = point.x;
        }
    }

    /* renamed from: a */
    public int m11386a(MotionEvent motionEvent) {
        return m11395c(motionEvent);
    }

    /* renamed from: b */
    public int m11392b(MotionEvent motionEvent) {
        return this.f10462c == 1 ? m11397d(motionEvent) : -1;
    }

    /* renamed from: c */
    public int m11395c(MotionEvent motionEvent) {
        return m11387a(motionEvent, this.f10478s);
    }

    /* renamed from: d */
    public int m11397d(MotionEvent motionEvent) {
        return m11387a(motionEvent, this.f10480u);
    }

    /* renamed from: a */
    public int m11387a(MotionEvent motionEvent, int i) {
        int pointToPosition = this.f10482w.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.f10482w.getHeaderViewsCount();
        int footerViewsCount = this.f10482w.getFooterViewsCount();
        int count = this.f10482w.getCount();
        if (pointToPosition != -1 && pointToPosition >= headerViewsCount && pointToPosition < count - footerViewsCount) {
            View childAt = this.f10482w.getChildAt(pointToPosition - this.f10482w.getFirstVisiblePosition());
            count = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            View findViewById = i == 0 ? childAt : childAt.findViewById(i);
            if (findViewById != null) {
                findViewById.getLocationOnScreen(this.f10471l);
                if (count > this.f10471l[0] && rawY > this.f10471l[1] && count < this.f10471l[0] + findViewById.getWidth()) {
                    if (rawY < findViewById.getHeight() + this.f10471l[1]) {
                        this.f10472m = childAt.getLeft();
                        this.f10473n = childAt.getTop();
                        return pointToPosition;
                    }
                }
            }
        }
        return -1;
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (this.f10463d && this.f10462c == 0) {
            this.f10470k = m11387a(motionEvent, this.f10479t);
        }
        this.f10468i = m11386a(motionEvent);
        if (this.f10468i != -1 && this.f10460a == 0) {
            m11391a(this.f10468i, ((int) motionEvent.getX()) - this.f10472m, ((int) motionEvent.getY()) - this.f10473n);
        }
        this.f10464e = false;
        this.f10481v = true;
        this.f10483x = 0;
        this.f10469j = m11392b(motionEvent);
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int x2 = (int) motionEvent2.getX();
        int y2 = (int) motionEvent2.getY();
        int i = x2 - this.f10472m;
        int i2 = y2 - this.f10473n;
        if (!(!this.f10481v || this.f10476q || (this.f10468i == -1 && this.f10469j == -1))) {
            if (this.f10468i != -1) {
                if (this.f10460a == 1 && Math.abs(y2 - y) > this.f10467h && this.f10461b) {
                    m11391a(this.f10468i, i, i2);
                } else if (this.f10460a != 0 && Math.abs(x2 - x) > this.f10467h && this.f10463d) {
                    this.f10464e = true;
                    m11391a(this.f10469j, i, i2);
                }
            } else if (this.f10469j != -1) {
                if (Math.abs(x2 - x) > this.f10467h && this.f10463d) {
                    this.f10464e = true;
                    m11391a(this.f10469j, i, i2);
                } else if (Math.abs(y2 - y) > this.f10467h) {
                    this.f10481v = false;
                }
            }
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.f10468i != -1 && this.f10460a == 2) {
            this.f10482w.performHapticFeedback(0);
            m11391a(this.f10468i, this.f10474o - this.f10472m, this.f10475p - this.f10473n);
        }
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.f10463d && this.f10462c == 0 && this.f10470k != -1) {
            this.f10482w.m11366a(this.f10470k - this.f10482w.getHeaderViewsCount());
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }
}
