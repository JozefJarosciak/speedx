package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WrapperViewList */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.f */
class C2786f extends ListView {
    /* renamed from: a */
    private C2779a f12992a;
    /* renamed from: b */
    private List<View> f12993b;
    /* renamed from: c */
    private int f12994c;
    /* renamed from: d */
    private Rect f12995d = new Rect();
    /* renamed from: e */
    private Field f12996e;
    /* renamed from: f */
    private boolean f12997f = true;
    /* renamed from: g */
    private boolean f12998g = false;

    /* compiled from: WrapperViewList */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.f$a */
    interface C2779a {
        /* renamed from: a */
        void mo3561a(Canvas canvas);
    }

    public C2786f(Context context) {
        super(context);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mSelectorRect");
            declaredField.setAccessible(true);
            this.f12995d = (Rect) declaredField.get(this);
            if (VERSION.SDK_INT >= 14) {
                this.f12996e = AbsListView.class.getDeclaredField("mSelectorPosition");
                this.f12996e.setAccessible(true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public boolean performItemClick(View view, int i, long j) {
        if (view instanceof C2783e) {
            view = ((C2783e) view).f12986a;
        }
        return super.performItemClick(view, i, j);
    }

    /* renamed from: b */
    private void m13718b() {
        if (!this.f12995d.isEmpty()) {
            int c = m13720c();
            if (c >= 0) {
                View childAt = getChildAt(c - m13721a());
                if (childAt instanceof C2783e) {
                    C2783e c2783e = (C2783e) childAt;
                    this.f12995d.top = c2783e.f12990e + c2783e.getTop();
                }
            }
        }
    }

    /* renamed from: c */
    private int m13720c() {
        if (this.f12996e == null) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getBottom() == this.f12995d.bottom) {
                    return i + m13721a();
                }
            }
        } else {
            try {
                return this.f12996e.getInt(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }

    protected void dispatchDraw(Canvas canvas) {
        m13718b();
        if (this.f12994c != 0) {
            canvas.save();
            Rect clipBounds = canvas.getClipBounds();
            clipBounds.top = this.f12994c;
            canvas.clipRect(clipBounds);
            super.dispatchDraw(canvas);
            canvas.restore();
        } else {
            super.dispatchDraw(canvas);
        }
        this.f12992a.mo3561a(canvas);
    }

    /* renamed from: a */
    void m13723a(C2779a c2779a) {
        this.f12992a = c2779a;
    }

    public void addFooterView(View view) {
        super.addFooterView(view);
        m13719b(view);
    }

    public void addFooterView(View view, Object obj, boolean z) {
        super.addFooterView(view, obj, z);
        m13719b(view);
    }

    /* renamed from: b */
    private void m13719b(View view) {
        if (this.f12993b == null) {
            this.f12993b = new ArrayList();
        }
        this.f12993b.add(view);
    }

    public boolean removeFooterView(View view) {
        if (!super.removeFooterView(view)) {
            return false;
        }
        this.f12993b.remove(view);
        return true;
    }

    /* renamed from: a */
    boolean m13725a(View view) {
        if (this.f12993b == null) {
            return false;
        }
        return this.f12993b.contains(view);
    }

    /* renamed from: a */
    void m13722a(int i) {
        this.f12994c = i;
    }

    /* renamed from: a */
    int m13721a() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (VERSION.SDK_INT >= 11) {
            return firstVisiblePosition;
        }
        int i;
        for (i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getBottom() >= 0) {
                i += firstVisiblePosition;
                break;
            }
        }
        i = firstVisiblePosition;
        if (!this.f12997f && getPaddingTop() > 0 && i > 0 && getChildAt(0).getTop() > 0) {
            i--;
        }
        return i;
    }

    public void setClipToPadding(boolean z) {
        this.f12997f = z;
        super.setClipToPadding(z);
    }

    /* renamed from: a */
    public void m13724a(boolean z) {
        this.f12998g = z;
    }

    protected void layoutChildren() {
        if (!this.f12998g) {
            super.layoutChildren();
        }
    }
}
