package com.beastbikes.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import org.apache.http.HttpStatus;

public class PullRefreshListView extends ListView implements OnScrollListener {
    /* renamed from: a */
    private float f12130a = -1.0f;
    /* renamed from: b */
    private Scroller f12131b;
    /* renamed from: c */
    private OnScrollListener f12132c;
    /* renamed from: d */
    private C2597b f12133d;
    /* renamed from: e */
    private C2618b f12134e;
    /* renamed from: f */
    private RelativeLayout f12135f;
    /* renamed from: g */
    private int f12136g;
    /* renamed from: h */
    private boolean f12137h = true;
    /* renamed from: i */
    private boolean f12138i = false;
    /* renamed from: j */
    private C2610a f12139j;
    /* renamed from: k */
    private boolean f12140k;
    /* renamed from: l */
    private boolean f12141l;
    /* renamed from: m */
    private boolean f12142m = false;
    /* renamed from: n */
    private int f12143n;
    /* renamed from: o */
    private int f12144o;
    /* renamed from: p */
    private AttributeSet f12145p;
    /* renamed from: q */
    private int f12146q;
    /* renamed from: r */
    private boolean f12147r;

    /* renamed from: com.beastbikes.android.widget.PullRefreshListView$1 */
    class C25941 implements OnGlobalLayoutListener {
        /* renamed from: a */
        final /* synthetic */ PullRefreshListView f12128a;

        C25941(PullRefreshListView pullRefreshListView) {
            this.f12128a = pullRefreshListView;
        }

        public void onGlobalLayout() {
            this.f12128a.f12136g = this.f12128a.f12135f.getHeight();
            this.f12128a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    /* renamed from: com.beastbikes.android.widget.PullRefreshListView$2 */
    class C25952 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ PullRefreshListView f12129a;

        C25952(PullRefreshListView pullRefreshListView) {
            this.f12129a = pullRefreshListView;
        }

        public void onClick(View view) {
            this.f12129a.m12975e();
        }
    }

    /* renamed from: com.beastbikes.android.widget.PullRefreshListView$a */
    public interface C2596a extends OnScrollListener {
        /* renamed from: a */
        void m12964a(View view);
    }

    /* renamed from: com.beastbikes.android.widget.PullRefreshListView$b */
    public interface C2597b {
        /* renamed from: a */
        void m12965a();

        void b_();
    }

    public PullRefreshListView(Context context) {
        super(context);
        setDividerHeight(0);
        m12969a(context);
    }

    public PullRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12145p = attributeSet;
        setDividerHeight(0);
        m12969a(context);
    }

    public PullRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12145p = attributeSet;
        this.f12146q = i;
        setDividerHeight(0);
        m12969a(context);
    }

    /* renamed from: a */
    private void m12969a(Context context) {
        if (this.f12145p != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(this.f12145p, R$styleable.PullRefreshListView, this.f12146q, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (index) {
                    case 0:
                        this.f12147r = obtainStyledAttributes.getBoolean(index, false);
                        break;
                    default:
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f12131b = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.f12134e = new C2618b(context, this.f12147r);
        this.f12135f = (RelativeLayout) this.f12134e.findViewById(C1373R.id.list_view_head);
        addHeaderView(this.f12134e);
        this.f12139j = new C2610a(context);
        this.f12134e.getViewTreeObserver().addOnGlobalLayoutListener(new C25941(this));
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.f12142m) {
            addFooterView(this.f12139j);
        }
        super.setAdapter(listAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.f12137h = z;
        if (this.f12137h) {
            this.f12135f.setVisibility(0);
        } else {
            this.f12135f.setVisibility(4);
        }
    }

    public void setPullLoadEnable(boolean z) {
        this.f12142m = z;
        this.f12140k = z;
        if (this.f12140k) {
            this.f12141l = false;
            this.f12139j.m13008b();
            this.f12139j.setState(0);
            setFooterDividersEnabled(true);
            this.f12139j.setOnClickListener(new C25952(this));
            return;
        }
        this.f12139j.m13007a();
        this.f12139j.setOnClickListener(null);
        setFooterDividersEnabled(false);
    }

    /* renamed from: a */
    public void m12976a() {
        if (this.f12138i) {
            this.f12138i = false;
            m12973c();
        }
    }

    /* renamed from: a */
    public void m12977a(int i) {
        if (this.f12141l) {
            this.f12141l = false;
            this.f12139j.setState(i);
        }
    }

    public void setRefreshTime(String str) {
    }

    /* renamed from: b */
    private void m12970b() {
        if (this.f12132c instanceof C2596a) {
            ((C2596a) this.f12132c).m12964a(this);
        }
    }

    /* renamed from: a */
    private void m12968a(float f) {
        this.f12134e.setVisiableHeight(((int) f) + this.f12134e.getVisiableHeight());
        if (this.f12137h && !this.f12138i) {
            if (this.f12134e.getVisiableHeight() > this.f12136g) {
                this.f12134e.setState(1);
            } else {
                this.f12134e.setState(0);
            }
        }
        setSelection(0);
    }

    /* renamed from: c */
    private void m12973c() {
        int visiableHeight = this.f12134e.getVisiableHeight();
        if (visiableHeight != 0) {
            if (!this.f12138i || visiableHeight > this.f12136g) {
                int i;
                if (!this.f12138i || visiableHeight <= this.f12136g) {
                    i = 0;
                } else {
                    i = this.f12136g;
                }
                this.f12144o = 0;
                this.f12131b.startScroll(0, visiableHeight, 0, i - visiableHeight, HttpStatus.SC_BAD_REQUEST);
                invalidate();
            }
        }
    }

    /* renamed from: b */
    private void m12971b(float f) {
        int bottomMargin = this.f12139j.getBottomMargin() + ((int) f);
        if (this.f12140k && !this.f12141l) {
            if (bottomMargin > 50) {
                this.f12139j.setState(1);
            } else {
                this.f12139j.setState(0);
            }
        }
        this.f12139j.setBottomMargin(bottomMargin);
    }

    /* renamed from: d */
    private void m12974d() {
        int bottomMargin = this.f12139j.getBottomMargin();
        if (bottomMargin > 0) {
            this.f12144o = 1;
            this.f12131b.startScroll(0, bottomMargin, 0, -bottomMargin, HttpStatus.SC_BAD_REQUEST);
            invalidate();
        }
    }

    /* renamed from: b */
    public void m12978b(int i) {
        this.f12134e.setViewBackgroundColor(i);
    }

    /* renamed from: e */
    private void m12975e() {
        this.f12141l = true;
        this.f12139j.setState(2);
        if (this.f12133d != null) {
            this.f12133d.b_();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f12130a == -1.0f) {
            this.f12130a = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.f12130a = motionEvent.getRawY();
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.f12130a;
                this.f12130a = motionEvent.getRawY();
                if (getFirstVisiblePosition() != 0 || (this.f12134e.getVisiableHeight() <= 0 && rawY <= 0.0f)) {
                    if (getLastVisiblePosition() == this.f12143n - 1 && (this.f12139j.getBottomMargin() > 0 || rawY < 0.0f)) {
                        m12971b((-rawY) / 1.8f);
                        break;
                    }
                }
                m12968a(rawY / 1.8f);
                m12970b();
                break;
                break;
            default:
                this.f12130a = -1.0f;
                if (getFirstVisiblePosition() != 0) {
                    if (getLastVisiblePosition() == this.f12143n - 1) {
                        if (this.f12140k && this.f12139j.getBottomMargin() > 50 && !this.f12141l) {
                            m12975e();
                        }
                        m12974d();
                        break;
                    }
                }
                if (this.f12137h && this.f12134e.getVisiableHeight() > this.f12136g) {
                    this.f12138i = true;
                    this.f12134e.setState(2);
                    if (this.f12133d != null) {
                        this.f12133d.m12965a();
                    }
                }
                m12973c();
                break;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void computeScroll() {
        if (this.f12131b.computeScrollOffset()) {
            if (this.f12144o == 0) {
                this.f12134e.setVisiableHeight(this.f12131b.getCurrY());
            } else {
                this.f12139j.setBottomMargin(this.f12131b.getCurrY());
            }
            postInvalidate();
            m12970b();
        }
        super.computeScroll();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f12132c = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.f12132c != null) {
            this.f12132c.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f12143n = i3;
        if (this.f12132c != null) {
            this.f12132c.onScroll(absListView, i, i2, i3);
        }
    }

    public void setListViewListener(C2597b c2597b) {
        this.f12133d = c2597b;
    }
}
