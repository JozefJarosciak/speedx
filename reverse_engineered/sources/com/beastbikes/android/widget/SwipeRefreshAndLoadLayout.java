package com.beastbikes.android.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.GridView;
import android.widget.ListView;
import com.beastbikes.android.C1373R;

public class SwipeRefreshAndLoadLayout extends SwipeRefreshLayout {
    /* renamed from: a */
    private final int f12178a;
    /* renamed from: b */
    private ListView f12179b;
    /* renamed from: c */
    private GridView f12180c;
    /* renamed from: d */
    private C2602a f12181d;
    /* renamed from: e */
    private float f12182e;
    /* renamed from: f */
    private float f12183f;
    /* renamed from: g */
    private boolean f12184g;
    /* renamed from: h */
    private View f12185h;
    /* renamed from: i */
    private boolean f12186i;

    /* renamed from: com.beastbikes.android.widget.SwipeRefreshAndLoadLayout$a */
    public interface C2602a {
        /* renamed from: a */
        void m12987a();
    }

    public SwipeRefreshAndLoadLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshAndLoadLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12184g = false;
        this.f12186i = true;
        this.f12178a = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f12185h = LayoutInflater.from(context).inflate(C1373R.layout.listview_footer, null, false);
        setColorSchemeResources(new int[]{C1373R.color.design_color_c7});
    }

    public void setChildListView(ListView listView) {
        this.f12179b = listView;
    }

    public void setChildGridView(GridView gridView) {
        this.f12180c = gridView;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f12182e = motionEvent.getRawY();
                break;
            case 1:
                if (this.f12186i) {
                    this.f12183f = motionEvent.getRawY();
                    if (m12988a()) {
                        m12991d();
                        break;
                    }
                }
                break;
            case 2:
                if (this.f12179b != null) {
                    this.f12183f = (float) ((int) motionEvent.getRawY());
                    break;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private boolean m12988a() {
        return m12989b() && !this.f12184g && m12990c();
    }

    /* renamed from: b */
    private boolean m12989b() {
        if (this.f12180c != null && this.f12180c.getCount() > 0 && this.f12180c.getLastVisiblePosition() == this.f12180c.getAdapter().getCount() - 1 && this.f12180c.getChildAt(this.f12180c.getChildCount() - 1).getBottom() <= this.f12180c.getHeight()) {
            return true;
        }
        if (this.f12179b == null || this.f12179b.getCount() <= 0 || this.f12179b.getLastVisiblePosition() != this.f12179b.getAdapter().getCount() - 1 || this.f12179b.getChildAt(this.f12179b.getChildCount() - 1).getBottom() > this.f12179b.getHeight()) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    private boolean m12990c() {
        return this.f12182e - this.f12183f >= ((float) this.f12178a);
    }

    /* renamed from: d */
    private void m12991d() {
        if (this.f12181d != null) {
            setLoading(true);
        }
    }

    public void setLoading(boolean z) {
        this.f12184g = z;
        if (this.f12180c != null) {
            if (this.f12184g) {
                if (isRefreshing()) {
                    setRefreshing(false);
                }
                this.f12180c.setSelection(this.f12180c.getAdapter().getCount() - 1);
                this.f12181d.m12987a();
                return;
            }
            this.f12182e = 0.0f;
            this.f12183f = 0.0f;
        } else if (this.f12179b == null) {
        } else {
            if (this.f12184g) {
                if (isRefreshing()) {
                    setRefreshing(false);
                }
                this.f12179b.addFooterView(this.f12185h);
                this.f12181d.m12987a();
                return;
            }
            this.f12179b.removeFooterView(this.f12185h);
            this.f12182e = 0.0f;
            this.f12183f = 0.0f;
        }
    }

    public void setCanLoad(boolean z) {
        this.f12186i = z;
        if (!z && this.f12179b != null) {
            this.f12179b.removeFooterView(this.f12185h);
        }
    }

    public void setOnLoadListener(C2602a c2602a) {
        this.f12181d = c2602a;
    }
}
