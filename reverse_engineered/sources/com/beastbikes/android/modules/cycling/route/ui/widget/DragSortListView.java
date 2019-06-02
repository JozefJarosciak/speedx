package com.beastbikes.android.modules.cycling.route.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.beastbikes.android.R$styleable;
import com.google.common.primitives.Ints;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DragSortListView extends ListView {
    /* renamed from: A */
    private View[] f10406A = new View[1];
    /* renamed from: B */
    private C2205d f10407B;
    /* renamed from: C */
    private float f10408C = 0.33333334f;
    /* renamed from: D */
    private float f10409D = 0.33333334f;
    /* renamed from: E */
    private int f10410E;
    /* renamed from: F */
    private int f10411F;
    /* renamed from: G */
    private float f10412G;
    /* renamed from: H */
    private float f10413H;
    /* renamed from: I */
    private float f10414I;
    /* renamed from: J */
    private float f10415J;
    /* renamed from: K */
    private float f10416K = 0.5f;
    /* renamed from: L */
    private C2199c f10417L = new C22001(this);
    /* renamed from: M */
    private int f10418M;
    /* renamed from: N */
    private int f10419N;
    /* renamed from: O */
    private int f10420O;
    /* renamed from: P */
    private int f10421P;
    /* renamed from: Q */
    private int f10422Q;
    /* renamed from: R */
    private int f10423R = 0;
    /* renamed from: S */
    private boolean f10424S = false;
    /* renamed from: T */
    private boolean f10425T = false;
    /* renamed from: U */
    private C2210i f10426U = null;
    /* renamed from: V */
    private MotionEvent f10427V;
    /* renamed from: W */
    private int f10428W = 0;
    /* renamed from: a */
    private View f10429a;
    private float aa = 0.25f;
    private float ab = 0.0f;
    private C2203a ac;
    private boolean ad = false;
    private C2207f ae;
    private boolean af = false;
    private boolean ag = false;
    private C2211j ah = new C2211j(this, 3);
    private C2213l ai;
    private C2212k aj;
    private C2209g ak;
    private boolean al;
    private float am = 0.0f;
    private boolean an = false;
    private boolean ao = false;
    /* renamed from: b */
    private Point f10430b = new Point();
    /* renamed from: c */
    private Point f10431c = new Point();
    /* renamed from: d */
    private int f10432d;
    /* renamed from: e */
    private boolean f10433e = false;
    /* renamed from: f */
    private DataSetObserver f10434f;
    /* renamed from: g */
    private float f10435g = 1.0f;
    /* renamed from: h */
    private float f10436h = 1.0f;
    /* renamed from: i */
    private int f10437i;
    /* renamed from: j */
    private int f10438j;
    /* renamed from: k */
    private int f10439k;
    /* renamed from: l */
    private boolean f10440l = false;
    /* renamed from: m */
    private int f10441m;
    /* renamed from: n */
    private int f10442n;
    /* renamed from: o */
    private int f10443o;
    /* renamed from: p */
    private int f10444p;
    /* renamed from: q */
    private int f10445q;
    /* renamed from: r */
    private C2204b f10446r;
    /* renamed from: s */
    private C2192h f10447s;
    /* renamed from: t */
    private C2193m f10448t;
    /* renamed from: u */
    private boolean f10449u = true;
    /* renamed from: v */
    private int f10450v = 0;
    /* renamed from: w */
    private int f10451w = 1;
    /* renamed from: x */
    private int f10452x;
    /* renamed from: y */
    private int f10453y;
    /* renamed from: z */
    private int f10454z = 0;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$h */
    public interface C2192h {
        void a_(int i, int i2);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$m */
    public interface C2193m {
        /* renamed from: a */
        void mo3422a(int i);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$c */
    public interface C2199c {
        /* renamed from: a */
        float mo3424a(float f, long j);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$1 */
    class C22001 implements C2199c {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10354a;

        C22001(DragSortListView dragSortListView) {
            this.f10354a = dragSortListView;
        }

        /* renamed from: a */
        public float mo3424a(float f, long j) {
            return this.f10354a.f10416K * f;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$2 */
    class C22012 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10355a;

        C22012(DragSortListView dragSortListView) {
            this.f10355a = dragSortListView;
        }

        /* renamed from: a */
        private void m11271a() {
            if (this.f10355a.f10450v == 4) {
                this.f10355a.m11364a();
            }
        }

        public void onChanged() {
            m11271a();
        }

        public void onInvalidated() {
            m11271a();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$a */
    private class C2203a extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10358a;
        /* renamed from: b */
        private ListAdapter f10359b;

        public C2203a(final DragSortListView dragSortListView, ListAdapter listAdapter) {
            this.f10358a = dragSortListView;
            this.f10359b = listAdapter;
            this.f10359b.registerDataSetObserver(new DataSetObserver(this) {
                /* renamed from: b */
                final /* synthetic */ C2203a f10357b;

                public void onChanged() {
                    this.f10357b.notifyDataSetChanged();
                }

                public void onInvalidated() {
                    this.f10357b.notifyDataSetInvalidated();
                }
            });
        }

        /* renamed from: a */
        public ListAdapter m11272a() {
            return this.f10359b;
        }

        public long getItemId(int i) {
            return this.f10359b.getItemId(i);
        }

        public Object getItem(int i) {
            return this.f10359b.getItem(i);
        }

        public int getCount() {
            return this.f10359b.getCount();
        }

        public boolean areAllItemsEnabled() {
            return this.f10359b.areAllItemsEnabled();
        }

        public boolean isEnabled(int i) {
            return this.f10359b.isEnabled(i);
        }

        public int getItemViewType(int i) {
            return this.f10359b.getItemViewType(i);
        }

        public int getViewTypeCount() {
            return this.f10359b.getViewTypeCount();
        }

        public boolean hasStableIds() {
            return this.f10359b.hasStableIds();
        }

        public boolean isEmpty() {
            return this.f10359b.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View childAt;
            View view2;
            if (view != null) {
                view = (C2217b) view;
                childAt = view.getChildAt(0);
                view2 = this.f10359b.getView(i, childAt, this.f10358a);
                if (view2 != childAt) {
                    if (childAt != null) {
                        view.removeViewAt(0);
                    }
                    view.addView(view2);
                }
            } else {
                view2 = this.f10359b.getView(i, null, this.f10358a);
                if (view2 instanceof Checkable) {
                    childAt = new C2218c(this.f10358a.getContext());
                } else {
                    childAt = new C2217b(this.f10358a.getContext());
                }
                childAt.setLayoutParams(new LayoutParams(-1, -2));
                childAt.addView(view2);
                view = childAt;
            }
            this.f10358a.m11310a(this.f10358a.getHeaderViewsCount() + i, view, true);
            return view;
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$b */
    public interface C2204b {
        /* renamed from: a */
        void m11273a(int i, int i2);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$d */
    private class C2205d implements Runnable {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10360a;
        /* renamed from: b */
        private boolean f10361b;
        /* renamed from: c */
        private long f10362c;
        /* renamed from: d */
        private long f10363d;
        /* renamed from: e */
        private int f10364e;
        /* renamed from: f */
        private float f10365f;
        /* renamed from: g */
        private long f10366g;
        /* renamed from: h */
        private int f10367h;
        /* renamed from: i */
        private float f10368i;
        /* renamed from: j */
        private boolean f10369j = false;

        /* renamed from: a */
        public boolean m11276a() {
            return this.f10369j;
        }

        /* renamed from: b */
        public int m11277b() {
            return this.f10369j ? this.f10367h : -1;
        }

        public C2205d(DragSortListView dragSortListView) {
            this.f10360a = dragSortListView;
        }

        /* renamed from: a */
        public void m11274a(int i) {
            if (!this.f10369j) {
                this.f10361b = false;
                this.f10369j = true;
                this.f10366g = SystemClock.uptimeMillis();
                this.f10362c = this.f10366g;
                this.f10367h = i;
                this.f10360a.post(this);
            }
        }

        /* renamed from: a */
        public void m11275a(boolean z) {
            if (z) {
                this.f10360a.removeCallbacks(this);
                this.f10369j = false;
                return;
            }
            this.f10361b = true;
        }

        public void run() {
            if (this.f10361b) {
                this.f10369j = false;
                return;
            }
            View childAt;
            int firstVisiblePosition = this.f10360a.getFirstVisiblePosition();
            int lastVisiblePosition = this.f10360a.getLastVisiblePosition();
            int count = this.f10360a.getCount();
            int paddingTop = this.f10360a.getPaddingTop();
            int height = (this.f10360a.getHeight() - paddingTop) - this.f10360a.getPaddingBottom();
            int min = Math.min(this.f10360a.f10419N, this.f10360a.f10432d + this.f10360a.f10453y);
            int max = Math.max(this.f10360a.f10419N, this.f10360a.f10432d - this.f10360a.f10453y);
            if (this.f10367h == 0) {
                childAt = this.f10360a.getChildAt(0);
                if (childAt == null) {
                    this.f10369j = false;
                    return;
                } else if (firstVisiblePosition == 0 && childAt.getTop() == paddingTop) {
                    this.f10369j = false;
                    return;
                } else {
                    this.f10368i = this.f10360a.f10417L.mo3424a((this.f10360a.f10413H - ((float) max)) / this.f10360a.f10414I, this.f10362c);
                }
            } else {
                View childAt2 = this.f10360a.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.f10369j = false;
                    return;
                } else if (lastVisiblePosition != count - 1 || childAt2.getBottom() > height + paddingTop) {
                    this.f10368i = -this.f10360a.f10417L.mo3424a((((float) min) - this.f10360a.f10412G) / this.f10360a.f10415J, this.f10362c);
                } else {
                    this.f10369j = false;
                    return;
                }
            }
            this.f10363d = SystemClock.uptimeMillis();
            this.f10365f = (float) (this.f10363d - this.f10362c);
            this.f10364e = Math.round(this.f10368i * this.f10365f);
            if (this.f10364e >= 0) {
                this.f10364e = Math.min(height, this.f10364e);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.f10364e = Math.max(-height, this.f10364e);
            }
            childAt = this.f10360a.getChildAt(lastVisiblePosition - firstVisiblePosition);
            firstVisiblePosition = childAt.getTop() + this.f10364e;
            if (lastVisiblePosition == 0 && firstVisiblePosition > paddingTop) {
                firstVisiblePosition = paddingTop;
            }
            this.f10360a.af = true;
            this.f10360a.setSelectionFromTop(lastVisiblePosition, firstVisiblePosition - paddingTop);
            this.f10360a.layoutChildren();
            this.f10360a.invalidate();
            this.f10360a.af = false;
            this.f10360a.m11332d(lastVisiblePosition, childAt, false);
            this.f10362c = this.f10363d;
            this.f10360a.post(this);
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$e */
    public interface C2206e extends C2204b, C2192h, C2193m {
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$f */
    private class C2207f {
        /* renamed from: a */
        StringBuilder f10370a = new StringBuilder();
        /* renamed from: b */
        File f10371b = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
        /* renamed from: c */
        final /* synthetic */ DragSortListView f10372c;
        /* renamed from: d */
        private int f10373d = 0;
        /* renamed from: e */
        private int f10374e = 0;
        /* renamed from: f */
        private boolean f10375f = false;

        public C2207f(DragSortListView dragSortListView) {
            this.f10372c = dragSortListView;
            if (!this.f10371b.exists()) {
                try {
                    this.f10371b.createNewFile();
                    Log.d("mobeta", "file created");
                } catch (IOException e) {
                    Log.w("mobeta", "Could not create dslv_state.txt");
                    Log.d("mobeta", e.getMessage());
                }
            }
        }

        /* renamed from: a */
        public void m11278a() {
            this.f10370a.append("<DSLVStates>\n");
            this.f10374e = 0;
            this.f10375f = true;
        }

        /* renamed from: b */
        public void m11279b() {
            if (this.f10375f) {
                int i;
                this.f10370a.append("<DSLVState>\n");
                int childCount = this.f10372c.getChildCount();
                int firstVisiblePosition = this.f10372c.getFirstVisiblePosition();
                this.f10370a.append("    <Positions>");
                for (i = 0; i < childCount; i++) {
                    this.f10370a.append(firstVisiblePosition + i).append(",");
                }
                this.f10370a.append("</Positions>\n");
                this.f10370a.append("    <Tops>");
                for (i = 0; i < childCount; i++) {
                    this.f10370a.append(this.f10372c.getChildAt(i).getTop()).append(",");
                }
                this.f10370a.append("</Tops>\n");
                this.f10370a.append("    <Bottoms>");
                for (i = 0; i < childCount; i++) {
                    this.f10370a.append(this.f10372c.getChildAt(i).getBottom()).append(",");
                }
                this.f10370a.append("</Bottoms>\n");
                this.f10370a.append("    <FirstExpPos>").append(this.f10372c.f10438j).append("</FirstExpPos>\n");
                this.f10370a.append("    <FirstExpBlankHeight>").append(this.f10372c.m11314b(this.f10372c.f10438j) - this.f10372c.m11329d(this.f10372c.f10438j)).append("</FirstExpBlankHeight>\n");
                this.f10370a.append("    <SecondExpPos>").append(this.f10372c.f10439k).append("</SecondExpPos>\n");
                this.f10370a.append("    <SecondExpBlankHeight>").append(this.f10372c.m11314b(this.f10372c.f10439k) - this.f10372c.m11329d(this.f10372c.f10439k)).append("</SecondExpBlankHeight>\n");
                this.f10370a.append("    <SrcPos>").append(this.f10372c.f10441m).append("</SrcPos>\n");
                this.f10370a.append("    <SrcHeight>").append(this.f10372c.f10452x + this.f10372c.getDividerHeight()).append("</SrcHeight>\n");
                this.f10370a.append("    <ViewHeight>").append(this.f10372c.getHeight()).append("</ViewHeight>\n");
                this.f10370a.append("    <LastY>").append(this.f10372c.f10421P).append("</LastY>\n");
                this.f10370a.append("    <FloatY>").append(this.f10372c.f10432d).append("</FloatY>\n");
                this.f10370a.append("    <ShuffleEdges>");
                for (i = 0; i < childCount; i++) {
                    this.f10370a.append(this.f10372c.m11305a(firstVisiblePosition + i, this.f10372c.getChildAt(i).getTop())).append(",");
                }
                this.f10370a.append("</ShuffleEdges>\n");
                this.f10370a.append("</DSLVState>\n");
                this.f10373d++;
                if (this.f10373d > 1000) {
                    m11280c();
                    this.f10373d = 0;
                }
            }
        }

        /* renamed from: c */
        public void m11280c() {
            if (this.f10375f) {
                boolean z = true;
                FileWriter fileWriter;
                try {
                    if (this.f10374e == 0) {
                        z = false;
                    }
                    fileWriter = new FileWriter(this.f10371b, z);
                    try {
                        fileWriter.write(this.f10370a.toString());
                        this.f10370a.delete(0, this.f10370a.length());
                        fileWriter.flush();
                        fileWriter.close();
                        this.f10374e++;
                    } catch (IOException e) {
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e3) {
                    fileWriter = null;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                }
            }
        }

        /* renamed from: d */
        public void m11281d() {
            if (this.f10375f) {
                this.f10370a.append("</DSLVStates>\n");
                m11280c();
                this.f10375f = false;
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$n */
    private class C2208n implements Runnable {
        /* renamed from: a */
        private float f10376a;
        /* renamed from: b */
        protected long f10377b;
        /* renamed from: c */
        final /* synthetic */ DragSortListView f10378c;
        /* renamed from: d */
        private float f10379d;
        /* renamed from: e */
        private float f10380e;
        /* renamed from: f */
        private float f10381f = (this.f10379d / ((this.f10379d - 1.0f) * 2.0f));
        /* renamed from: g */
        private float f10382g = (1.0f / (1.0f - this.f10379d));
        /* renamed from: h */
        private float f10383h;
        /* renamed from: i */
        private boolean f10384i;

        public C2208n(DragSortListView dragSortListView, float f, int i) {
            this.f10378c = dragSortListView;
            this.f10379d = f;
            this.f10376a = (float) i;
            float f2 = 1.0f / ((this.f10379d * 2.0f) * (1.0f - this.f10379d));
            this.f10383h = f2;
            this.f10380e = f2;
        }

        /* renamed from: a */
        public float m11282a(float f) {
            if (f < this.f10379d) {
                return (this.f10380e * f) * f;
            }
            if (f < 1.0f - this.f10379d) {
                return this.f10381f + (this.f10382g * f);
            }
            return 1.0f - ((this.f10383h * (f - 1.0f)) * (f - 1.0f));
        }

        /* renamed from: c */
        public void m11286c() {
            this.f10377b = SystemClock.uptimeMillis();
            this.f10384i = false;
            mo3425a();
            this.f10378c.post(this);
        }

        /* renamed from: d */
        public void m11287d() {
            this.f10384i = true;
        }

        /* renamed from: a */
        public void mo3425a() {
        }

        /* renamed from: a */
        public void mo3426a(float f, float f2) {
        }

        /* renamed from: b */
        public void mo3427b() {
        }

        public void run() {
            if (!this.f10384i) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f10377b)) / this.f10376a;
                if (uptimeMillis >= 1.0f) {
                    mo3426a(1.0f, 1.0f);
                    mo3427b();
                    return;
                }
                mo3426a(uptimeMillis, m11282a(uptimeMillis));
                this.f10378c.post(this);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$g */
    private class C2209g extends C2208n {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10385a;
        /* renamed from: d */
        private int f10386d;
        /* renamed from: e */
        private int f10387e;
        /* renamed from: f */
        private float f10388f;
        /* renamed from: g */
        private float f10389g;

        public C2209g(DragSortListView dragSortListView, float f, int i) {
            this.f10385a = dragSortListView;
            super(dragSortListView, f, i);
        }

        /* renamed from: a */
        public void mo3425a() {
            this.f10386d = this.f10385a.f10437i;
            this.f10387e = this.f10385a.f10441m;
            this.f10385a.f10450v = 2;
            this.f10388f = (float) (this.f10385a.f10430b.y - m11288e());
            this.f10389g = (float) (this.f10385a.f10430b.x - this.f10385a.getPaddingLeft());
        }

        /* renamed from: e */
        private int m11288e() {
            int i = (this.f10385a.f10451w + this.f10385a.getDividerHeight()) / 2;
            View childAt = this.f10385a.getChildAt(this.f10386d - this.f10385a.getFirstVisiblePosition());
            if (childAt == null) {
                m11287d();
                return -1;
            } else if (this.f10386d == this.f10387e) {
                return childAt.getTop();
            } else {
                if (this.f10386d < this.f10387e) {
                    return childAt.getTop() - i;
                }
                return (childAt.getBottom() + i) - this.f10385a.f10452x;
            }
        }

        /* renamed from: a */
        public void mo3426a(float f, float f2) {
            int e = m11288e();
            float paddingLeft = (float) (this.f10385a.f10430b.x - this.f10385a.getPaddingLeft());
            float f3 = 1.0f - f2;
            if (f3 < Math.abs(((float) (this.f10385a.f10430b.y - e)) / this.f10388f) || f3 < Math.abs(paddingLeft / this.f10389g)) {
                this.f10385a.f10430b.y = e + ((int) (this.f10388f * f3));
                this.f10385a.f10430b.x = this.f10385a.getPaddingLeft() + ((int) (this.f10389g * f3));
                this.f10385a.m11321b(true);
            }
        }

        /* renamed from: b */
        public void mo3427b() {
            this.f10385a.m11337f();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$i */
    public interface C2210i {
        /* renamed from: a */
        void mo3428a(View view);

        /* renamed from: a */
        void mo3429a(View view, Point point, Point point2);

        /* renamed from: e */
        View mo3430e(int i);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$j */
    private class C2211j {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10390a;
        /* renamed from: b */
        private SparseIntArray f10391b;
        /* renamed from: c */
        private ArrayList<Integer> f10392c;
        /* renamed from: d */
        private int f10393d;

        public C2211j(DragSortListView dragSortListView, int i) {
            this.f10390a = dragSortListView;
            this.f10391b = new SparseIntArray(i);
            this.f10392c = new ArrayList(i);
            this.f10393d = i;
        }

        /* renamed from: a */
        public void m11297a(int i, int i2) {
            int i3 = this.f10391b.get(i, -1);
            if (i3 != i2) {
                if (i3 != -1) {
                    this.f10392c.remove(Integer.valueOf(i));
                } else if (this.f10391b.size() == this.f10393d) {
                    this.f10391b.delete(((Integer) this.f10392c.remove(0)).intValue());
                }
                this.f10391b.put(i, i2);
                this.f10392c.add(Integer.valueOf(i));
            }
        }

        /* renamed from: a */
        public int m11295a(int i) {
            return this.f10391b.get(i, -1);
        }

        /* renamed from: a */
        public void m11296a() {
            this.f10391b.clear();
            this.f10392c.clear();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$k */
    private class C2212k extends C2208n {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10394a;
        /* renamed from: d */
        private float f10395d;
        /* renamed from: e */
        private float f10396e;

        /* renamed from: a */
        public void mo3425a() {
            this.f10395d = (float) this.f10394a.f10443o;
            this.f10396e = (float) this.f10394a.f10453y;
        }

        /* renamed from: a */
        public void mo3426a(float f, float f2) {
            if (this.f10394a.f10450v != 4) {
                m11287d();
                return;
            }
            this.f10394a.f10443o = (int) ((this.f10396e * f2) + ((1.0f - f2) * this.f10395d));
            this.f10394a.f10430b.y = this.f10394a.f10419N - this.f10394a.f10443o;
            this.f10394a.m11321b(true);
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView$l */
    private class C2213l extends C2208n {
        /* renamed from: a */
        final /* synthetic */ DragSortListView f10397a;
        /* renamed from: d */
        private float f10398d;
        /* renamed from: e */
        private float f10399e;
        /* renamed from: f */
        private float f10400f;
        /* renamed from: g */
        private int f10401g = -1;
        /* renamed from: h */
        private int f10402h = -1;
        /* renamed from: i */
        private int f10403i;
        /* renamed from: j */
        private int f10404j;
        /* renamed from: k */
        private int f10405k;

        public C2213l(DragSortListView dragSortListView, float f, int i) {
            this.f10397a = dragSortListView;
            super(dragSortListView, f, i);
        }

        /* renamed from: a */
        public void mo3425a() {
            int i = -1;
            this.f10401g = -1;
            this.f10402h = -1;
            this.f10403i = this.f10397a.f10438j;
            this.f10404j = this.f10397a.f10439k;
            this.f10405k = this.f10397a.f10441m;
            this.f10397a.f10450v = 1;
            this.f10398d = (float) this.f10397a.f10430b.x;
            if (this.f10397a.al) {
                float width = ((float) this.f10397a.getWidth()) * 2.0f;
                if (this.f10397a.am == 0.0f) {
                    DragSortListView dragSortListView = this.f10397a;
                    if (this.f10398d >= 0.0f) {
                        i = 1;
                    }
                    dragSortListView.am = ((float) i) * width;
                    return;
                }
                float f = width * 2.0f;
                if (this.f10397a.am < 0.0f && this.f10397a.am > (-f)) {
                    this.f10397a.am = -f;
                    return;
                } else if (this.f10397a.am > 0.0f && this.f10397a.am < f) {
                    this.f10397a.am = f;
                    return;
                } else {
                    return;
                }
            }
            this.f10397a.m11352n();
        }

        /* renamed from: a */
        public void mo3426a(float f, float f2) {
            float f3 = 1.0f - f2;
            int firstVisiblePosition = this.f10397a.getFirstVisiblePosition();
            View childAt = this.f10397a.getChildAt(this.f10403i - firstVisiblePosition);
            if (this.f10397a.al) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.b)) / 1000.0f;
                if (uptimeMillis != 0.0f) {
                    float o = this.f10397a.am * uptimeMillis;
                    int width = this.f10397a.getWidth();
                    this.f10397a.am = ((((float) (this.f10397a.am > 0.0f ? 1 : -1)) * uptimeMillis) * ((float) width)) + this.f10397a.am;
                    this.f10398d += o;
                    this.f10397a.f10430b.x = (int) this.f10398d;
                    if (this.f10398d < ((float) width) && this.f10398d > ((float) (-width))) {
                        this.b = SystemClock.uptimeMillis();
                        this.f10397a.m11321b(true);
                        return;
                    }
                }
                return;
            }
            if (childAt != null) {
                if (this.f10401g == -1) {
                    this.f10401g = this.f10397a.m11315b(this.f10403i, childAt, false);
                    this.f10399e = (float) (childAt.getHeight() - this.f10401g);
                }
                int max = Math.max((int) (this.f10399e * f3), 1);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                layoutParams.height = max + this.f10401g;
                childAt.setLayoutParams(layoutParams);
            }
            if (this.f10404j != this.f10403i) {
                View childAt2 = this.f10397a.getChildAt(this.f10404j - firstVisiblePosition);
                if (childAt2 != null) {
                    if (this.f10402h == -1) {
                        this.f10402h = this.f10397a.m11315b(this.f10404j, childAt2, false);
                        this.f10400f = (float) (childAt2.getHeight() - this.f10402h);
                    }
                    int max2 = Math.max((int) (this.f10400f * f3), 1);
                    ViewGroup.LayoutParams layoutParams2 = childAt2.getLayoutParams();
                    layoutParams2.height = max2 + this.f10402h;
                    childAt2.setLayoutParams(layoutParams2);
                }
            }
        }

        /* renamed from: b */
        public void mo3427b() {
            this.f10397a.m11339g();
        }
    }

    public DragSortListView(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        int i2 = 150;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.DragSortListView, 0, 0);
            this.f10451w = Math.max(1, obtainStyledAttributes.getDimensionPixelSize(0, 1));
            this.ad = obtainStyledAttributes.getBoolean(5, false);
            if (this.ad) {
                this.ae = new C2207f(this);
            }
            this.f10435g = obtainStyledAttributes.getFloat(6, this.f10435g);
            this.f10436h = this.f10435g;
            this.f10449u = obtainStyledAttributes.getBoolean(10, this.f10449u);
            this.aa = Math.max(0.0f, Math.min(1.0f, 1.0f - obtainStyledAttributes.getFloat(7, 0.75f)));
            this.f10440l = this.aa > 0.0f;
            setDragScrollStart(obtainStyledAttributes.getFloat(1, this.f10408C));
            this.f10416K = obtainStyledAttributes.getFloat(2, this.f10416K);
            int i3 = obtainStyledAttributes.getInt(8, 150);
            int i4 = obtainStyledAttributes.getInt(9, 150);
            if (obtainStyledAttributes.getBoolean(17, true)) {
                boolean z = obtainStyledAttributes.getBoolean(12, false);
                int i5 = obtainStyledAttributes.getInt(4, 1);
                boolean z2 = obtainStyledAttributes.getBoolean(11, true);
                int i6 = obtainStyledAttributes.getInt(13, 0);
                int resourceId = obtainStyledAttributes.getResourceId(14, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(15, 0);
                int resourceId3 = obtainStyledAttributes.getResourceId(16, 0);
                int color = obtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
                Object c2216a = new C2216a(this, resourceId, i6, i5, resourceId3, resourceId2);
                c2216a.m11394b(z);
                c2216a.m11390a(z2);
                c2216a.m11379f(color);
                this.f10426U = c2216a;
                setOnTouchListener(c2216a);
            }
            obtainStyledAttributes.recycle();
            i = i4;
            i2 = i3;
        } else {
            i = 150;
        }
        this.f10407B = new C2205d(this);
        if (i2 > 0) {
            this.ai = new C2213l(this, 0.5f, i2);
        }
        if (i > 0) {
            this.ak = new C2209g(this, 0.5f, i);
        }
        this.f10427V = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.f10434f = new C22012(this);
    }

    public void setFloatAlpha(float f) {
        this.f10436h = f;
    }

    public float getFloatAlpha() {
        return this.f10436h;
    }

    public void setMaxScrollSpeed(float f) {
        this.f10416K = f;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.ac = new C2203a(this, listAdapter);
            listAdapter.registerDataSetObserver(this.f10434f);
            if (listAdapter instanceof C2192h) {
                setDropListener((C2192h) listAdapter);
            }
            if (listAdapter instanceof C2204b) {
                setDragListener((C2204b) listAdapter);
            }
            if (listAdapter instanceof C2193m) {
                setRemoveListener((C2193m) listAdapter);
            }
        } else {
            this.ac = null;
        }
        super.setAdapter(this.ac);
    }

    public ListAdapter getInputAdapter() {
        if (this.ac == null) {
            return null;
        }
        return this.ac.m11272a();
    }

    /* renamed from: a */
    private void m11309a(int i, Canvas canvas) {
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider != null && dividerHeight != 0) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(i - getFirstVisiblePosition());
            if (viewGroup != null) {
                int i2;
                int paddingLeft = getPaddingLeft();
                int width = getWidth() - getPaddingRight();
                int height = viewGroup.getChildAt(0).getHeight();
                if (i > this.f10441m) {
                    height += viewGroup.getTop();
                    i2 = height + dividerHeight;
                } else {
                    i2 = viewGroup.getBottom() - height;
                    height = i2 - dividerHeight;
                }
                canvas.save();
                canvas.clipRect(paddingLeft, height, width, i2);
                divider.setBounds(paddingLeft, height, width, i2);
                divider.draw(canvas);
                canvas.restore();
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f10450v != 0) {
            if (this.f10438j != this.f10441m) {
                m11309a(this.f10438j, canvas);
            }
            if (!(this.f10439k == this.f10438j || this.f10439k == this.f10441m)) {
                m11309a(this.f10439k, canvas);
            }
        }
        if (this.f10429a != null) {
            float f;
            int width = this.f10429a.getWidth();
            int height = this.f10429a.getHeight();
            int i = this.f10430b.x;
            int width2 = getWidth();
            if (i < 0) {
                i = -i;
            }
            if (i < width2) {
                f = ((float) (width2 - i)) / ((float) width2);
                f *= f;
            } else {
                f = 0.0f;
            }
            int i2 = (int) (f * (255.0f * this.f10436h));
            canvas.save();
            canvas.translate((float) this.f10430b.x, (float) this.f10430b.y);
            canvas.clipRect(0, 0, width, height);
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) width, (float) height, i2, 31);
            this.f10429a.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    /* renamed from: b */
    private int m11314b(int i) {
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return childAt.getHeight();
        }
        return m11323c(i, m11329d(i));
    }

    /* renamed from: a */
    private int m11305a(int i, int i2) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i <= headerViewsCount || i >= getCount() - footerViewsCount) {
            return i2;
        }
        headerViewsCount = getDividerHeight();
        footerViewsCount = this.f10452x - this.f10451w;
        int d = m11329d(i);
        int b = m11314b(i);
        if (this.f10439k <= this.f10441m) {
            if (i != this.f10439k || this.f10438j == this.f10439k) {
                if (i > this.f10439k && i <= this.f10441m) {
                    i2 -= footerViewsCount;
                }
            } else if (i == this.f10441m) {
                i2 = (i2 + b) - this.f10452x;
            } else {
                i2 = ((b - d) + i2) - footerViewsCount;
            }
        } else if (i > this.f10441m && i <= this.f10438j) {
            i2 += footerViewsCount;
        } else if (i == this.f10439k && this.f10438j != this.f10439k) {
            i2 += b - d;
        }
        if (i <= this.f10441m) {
            return (((this.f10452x - headerViewsCount) - m11329d(i - 1)) / 2) + i2;
        }
        return (((d - headerViewsCount) - this.f10452x) / 2) + i2;
    }

    /* renamed from: d */
    private boolean m11333d() {
        int count;
        boolean z;
        int firstVisiblePosition = getFirstVisiblePosition();
        int i = this.f10438j;
        View childAt = getChildAt(i - firstVisiblePosition);
        if (childAt == null) {
            i = firstVisiblePosition + (getChildCount() / 2);
            childAt = getChildAt(i - firstVisiblePosition);
        }
        firstVisiblePosition = childAt.getTop();
        int height = childAt.getHeight();
        int a = m11305a(i, firstVisiblePosition);
        int dividerHeight = getDividerHeight();
        if (this.f10432d >= a) {
            count = getCount();
            int i2 = height;
            height = firstVisiblePosition;
            firstVisiblePosition = a;
            int i3 = a;
            a = i;
            i = i3;
            while (a < count) {
                if (a != count - 1) {
                    height += dividerHeight + i2;
                    i2 = m11314b(a + 1);
                    firstVisiblePosition = m11305a(a + 1, height);
                    if (this.f10432d < firstVisiblePosition) {
                        break;
                    }
                    a++;
                    i = firstVisiblePosition;
                } else {
                    firstVisiblePosition = (height + dividerHeight) + i2;
                    break;
                }
            }
        }
        height = firstVisiblePosition;
        firstVisiblePosition = a;
        i3 = a;
        a = i;
        i = i3;
        while (a >= 0) {
            a--;
            firstVisiblePosition = m11314b(a);
            if (a != 0) {
                height -= firstVisiblePosition + dividerHeight;
                firstVisiblePosition = m11305a(a, height);
                if (this.f10432d >= firstVisiblePosition) {
                    break;
                }
                i = firstVisiblePosition;
            } else {
                firstVisiblePosition = (height - dividerHeight) - firstVisiblePosition;
                break;
            }
        }
        height = getHeaderViewsCount();
        dividerHeight = getFooterViewsCount();
        count = this.f10438j;
        int i4 = this.f10439k;
        float f = this.ab;
        if (this.f10440l) {
            int abs = Math.abs(firstVisiblePosition - i);
            if (this.f10432d >= firstVisiblePosition) {
                i3 = i;
                i = firstVisiblePosition;
                firstVisiblePosition = i3;
            }
            abs = (int) (((float) abs) * (this.aa * 0.5f));
            float f2 = (float) abs;
            i += abs;
            abs = firstVisiblePosition - abs;
            if (this.f10432d < i) {
                this.f10438j = a - 1;
                this.f10439k = a;
                this.ab = (((float) (i - this.f10432d)) * 0.5f) / f2;
            } else if (this.f10432d < abs) {
                this.f10438j = a;
                this.f10439k = a;
            } else {
                this.f10438j = a;
                this.f10439k = a + 1;
                this.ab = (1.0f + (((float) (firstVisiblePosition - this.f10432d)) / f2)) * 0.5f;
            }
        } else {
            this.f10438j = a;
            this.f10439k = a;
        }
        if (this.f10438j < height) {
            this.f10438j = height;
            this.f10439k = height;
            a = height;
        } else if (this.f10439k >= getCount() - dividerHeight) {
            a = (getCount() - dividerHeight) - 1;
            this.f10438j = a;
            this.f10439k = a;
        }
        if (this.f10438j == count && this.f10439k == i4 && this.ab == f) {
            z = false;
        } else {
            z = true;
        }
        if (a == this.f10437i) {
            return z;
        }
        if (this.f10446r != null) {
            this.f10446r.m11273a(this.f10437i - height, a - height);
        }
        this.f10437i = a;
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.ad) {
            this.ae.m11279b();
        }
    }

    /* renamed from: a */
    public void m11366a(int i) {
        this.al = false;
        m11367a(i, 0.0f);
    }

    /* renamed from: a */
    public void m11367a(int i, float f) {
        if (this.f10450v == 0 || this.f10450v == 4) {
            if (this.f10450v == 0) {
                this.f10441m = getHeaderViewsCount() + i;
                this.f10438j = this.f10441m;
                this.f10439k = this.f10441m;
                this.f10437i = this.f10441m;
                View childAt = getChildAt(this.f10441m - getFirstVisiblePosition());
                if (childAt != null) {
                    childAt.setVisibility(4);
                }
            }
            this.f10450v = 1;
            this.am = f;
            if (this.f10425T) {
                switch (this.f10428W) {
                    case 1:
                        super.onTouchEvent(this.f10427V);
                        break;
                    case 2:
                        super.onInterceptTouchEvent(this.f10427V);
                        break;
                }
            }
            if (this.ai != null) {
                this.ai.m11286c();
            } else {
                m11327c(i);
            }
        }
    }

    /* renamed from: a */
    public void m11364a() {
        if (this.f10450v == 4) {
            this.f10407B.m11275a(true);
            m11352n();
            m11335e();
            m11346k();
            if (this.f10425T) {
                this.f10450v = 3;
            } else {
                this.f10450v = 0;
            }
        }
    }

    /* renamed from: e */
    private void m11335e() {
        this.f10441m = -1;
        this.f10438j = -1;
        this.f10439k = -1;
        this.f10437i = -1;
    }

    /* renamed from: f */
    private void m11337f() {
        this.f10450v = 2;
        if (this.f10447s != null && this.f10437i >= 0 && this.f10437i < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.f10447s.a_(this.f10441m - headerViewsCount, this.f10437i - headerViewsCount);
        }
        m11352n();
        m11341h();
        m11335e();
        m11346k();
        if (this.f10425T) {
            this.f10450v = 3;
        } else {
            this.f10450v = 0;
        }
    }

    /* renamed from: g */
    private void m11339g() {
        m11327c(this.f10441m - getHeaderViewsCount());
    }

    /* renamed from: c */
    private void m11327c(int i) {
        this.f10450v = 1;
        if (this.f10448t != null) {
            this.f10448t.mo3422a(i);
        }
        m11352n();
        m11341h();
        m11335e();
        if (this.f10425T) {
            this.f10450v = 3;
        } else {
            this.f10450v = 0;
        }
    }

    /* renamed from: h */
    private void m11341h() {
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.f10441m < firstVisiblePosition) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                i = childAt.getTop();
            }
            setSelectionFromTop(firstVisiblePosition - 1, i - getPaddingTop());
        }
    }

    /* renamed from: a */
    public boolean m11371a(boolean z) {
        this.al = false;
        return m11374b(z, 0.0f);
    }

    /* renamed from: a */
    public boolean m11372a(boolean z, float f) {
        this.al = true;
        return m11374b(z, f);
    }

    /* renamed from: b */
    public boolean m11374b(boolean z, float f) {
        if (this.f10429a == null) {
            return false;
        }
        this.f10407B.m11275a(true);
        if (z) {
            m11367a(this.f10441m - getHeaderViewsCount(), f);
        } else if (this.ak != null) {
            this.ak.m11286c();
        } else {
            m11337f();
        }
        if (!this.ad) {
            return true;
        }
        this.ae.m11281d();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.ag) {
            this.ag = false;
            return false;
        } else if (!this.f10449u) {
            return super.onTouchEvent(motionEvent);
        } else {
            boolean z2 = this.f10424S;
            this.f10424S = false;
            if (!z2) {
                m11320b(motionEvent);
            }
            if (this.f10450v == 4) {
                m11370a(motionEvent);
                return true;
            }
            if (this.f10450v == 0 && super.onTouchEvent(motionEvent)) {
                z = true;
            }
            switch (motionEvent.getAction() & 255) {
                case 1:
                case 3:
                    m11343i();
                    return z;
                default:
                    if (!z) {
                        return z;
                    }
                    this.f10428W = 1;
                    return z;
            }
        }
    }

    /* renamed from: i */
    private void m11343i() {
        this.f10428W = 0;
        this.f10425T = false;
        if (this.f10450v == 3) {
            this.f10450v = 0;
        }
        this.f10436h = this.f10435g;
        this.an = false;
        this.ah.m11296a();
    }

    /* renamed from: b */
    private void m11320b(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            this.f10420O = this.f10418M;
            this.f10421P = this.f10419N;
        }
        this.f10418M = (int) motionEvent.getX();
        this.f10419N = (int) motionEvent.getY();
        if (action == 0) {
            this.f10420O = this.f10418M;
            this.f10421P = this.f10419N;
        }
        this.f10444p = ((int) motionEvent.getRawX()) - this.f10418M;
        this.f10445q = ((int) motionEvent.getRawY()) - this.f10419N;
    }

    /* renamed from: b */
    public boolean m11373b() {
        return this.an;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f10449u) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        boolean z;
        m11320b(motionEvent);
        this.f10424S = true;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.f10450v != 0) {
                this.ag = true;
                return true;
            }
            this.f10425T = true;
        }
        if (this.f10429a == null) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.an = true;
                z = true;
            } else {
                z = false;
            }
            switch (action) {
                case 1:
                case 3:
                    m11343i();
                    break;
                default:
                    if (!z) {
                        this.f10428W = 2;
                        break;
                    }
                    this.f10428W = 1;
                    break;
            }
        }
        z = true;
        if (action == 1 || action == 3) {
            this.f10425T = false;
        }
        return z;
    }

    public void setDragScrollStart(float f) {
        m11365a(f, f);
    }

    /* renamed from: a */
    public void m11365a(float f, float f2) {
        if (f2 > 0.5f) {
            this.f10409D = 0.5f;
        } else {
            this.f10409D = f2;
        }
        if (f > 0.5f) {
            this.f10408C = 0.5f;
        } else {
            this.f10408C = f;
        }
        if (getHeight() != 0) {
            m11345j();
        }
    }

    /* renamed from: b */
    private void m11319b(int i, int i2) {
        this.f10430b.x = i - this.f10442n;
        this.f10430b.y = i2 - this.f10443o;
        m11321b(true);
        int min = Math.min(i2, this.f10432d + this.f10453y);
        int max = Math.max(i2, this.f10432d - this.f10453y);
        int b = this.f10407B.m11277b();
        if (min > this.f10421P && min > this.f10411F && b != 1) {
            if (b != -1) {
                this.f10407B.m11275a(true);
            }
            this.f10407B.m11274a(1);
        } else if (max < this.f10421P && max < this.f10410E && b != 0) {
            if (b != -1) {
                this.f10407B.m11275a(true);
            }
            this.f10407B.m11274a(0);
        } else if (max >= this.f10410E && min <= this.f10411F && this.f10407B.m11276a()) {
            this.f10407B.m11275a(true);
        }
    }

    /* renamed from: j */
    private void m11345j() {
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        float f = (float) height;
        this.f10413H = ((float) paddingTop) + (this.f10408C * f);
        this.f10412G = (f * (1.0f - this.f10409D)) + ((float) paddingTop);
        this.f10410E = (int) this.f10413H;
        this.f10411F = (int) this.f10412G;
        this.f10414I = this.f10413H - ((float) paddingTop);
        this.f10415J = ((float) (paddingTop + height)) - this.f10412G;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m11345j();
    }

    /* renamed from: k */
    private void m11346k() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        lastVisiblePosition = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int max = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); max <= lastVisiblePosition; max++) {
            View childAt = getChildAt(max);
            if (childAt != null) {
                m11310a(firstVisiblePosition + max, childAt, false);
            }
        }
    }

    /* renamed from: a */
    private void m11310a(int i, View view, boolean z) {
        int c;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i == this.f10441m || i == this.f10438j || i == this.f10439k) {
            c = m11324c(i, view, z);
        } else {
            c = -2;
        }
        if (c != layoutParams.height) {
            layoutParams.height = c;
            view.setLayoutParams(layoutParams);
        }
        if (i == this.f10438j || i == this.f10439k) {
            if (i < this.f10441m) {
                ((C2217b) view).setGravity(80);
            } else if (i > this.f10441m) {
                ((C2217b) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        c = 0;
        if (i == this.f10441m && this.f10429a != null) {
            c = 4;
        }
        if (c != visibility) {
            view.setVisibility(c);
        }
    }

    /* renamed from: d */
    private int m11329d(int i) {
        if (i == this.f10441m) {
            return 0;
        }
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return m11315b(i, childAt, false);
        }
        int a = this.ah.m11295a(i);
        if (a != -1) {
            return a;
        }
        View view;
        ListAdapter adapter = getAdapter();
        int itemViewType = adapter.getItemViewType(i);
        int viewTypeCount = adapter.getViewTypeCount();
        if (viewTypeCount != this.f10406A.length) {
            this.f10406A = new View[viewTypeCount];
        }
        if (itemViewType < 0) {
            view = adapter.getView(i, null, this);
        } else if (this.f10406A[itemViewType] == null) {
            view = adapter.getView(i, null, this);
            this.f10406A[itemViewType] = view;
        } else {
            view = adapter.getView(i, this.f10406A[itemViewType], this);
        }
        a = m11315b(i, view, true);
        this.ah.m11297a(i, a);
        return a;
    }

    /* renamed from: b */
    private int m11315b(int i, View view, boolean z) {
        if (i == this.f10441m) {
            return 0;
        }
        if (i >= getHeaderViewsCount() && i < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            return layoutParams.height;
        }
        int height = view.getHeight();
        if (height != 0 && !z) {
            return height;
        }
        m11311a(view);
        return view.getMeasuredHeight();
    }

    /* renamed from: c */
    private int m11324c(int i, View view, boolean z) {
        return m11323c(i, m11315b(i, view, z));
    }

    /* renamed from: c */
    private int m11323c(int i, int i2) {
        getDividerHeight();
        Object obj = (!this.f10440l || this.f10438j == this.f10439k) ? null : 1;
        int i3 = this.f10452x - this.f10451w;
        int i4 = (int) (this.ab * ((float) i3));
        if (i == this.f10441m) {
            if (this.f10441m == this.f10438j) {
                if (obj != null) {
                    return i4 + this.f10451w;
                }
                return this.f10452x;
            } else if (this.f10441m == this.f10439k) {
                return this.f10452x - i4;
            } else {
                return this.f10451w;
            }
        } else if (i == this.f10438j) {
            if (obj != null) {
                return i2 + i4;
            }
            return i2 + i3;
        } else if (i == this.f10439k) {
            return (i2 + i3) - i4;
        } else {
            return i2;
        }
    }

    public void requestLayout() {
        if (!this.af) {
            super.requestLayout();
        }
    }

    /* renamed from: a */
    private int m11306a(int i, View view, int i2, int i3) {
        int i4;
        int d = m11329d(i);
        int height = view.getHeight();
        int c = m11323c(i, d);
        if (i != this.f10441m) {
            i4 = height - d;
            d = c - d;
        } else {
            d = c;
            i4 = height;
        }
        int i5 = this.f10452x;
        if (!(this.f10441m == this.f10438j || this.f10441m == this.f10439k)) {
            i5 -= this.f10451w;
        }
        if (i <= i2) {
            if (i > this.f10438j) {
                return (i5 - d) + 0;
            }
        } else if (i == i3) {
            if (i <= this.f10438j) {
                return (i4 - i5) + 0;
            }
            if (i == this.f10439k) {
                return (height - c) + 0;
            }
            return 0 + i4;
        } else if (i <= this.f10438j) {
            return 0 - i5;
        } else {
            if (i == this.f10439k) {
                return 0 - d;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private void m11311a(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.f10454z, getListPaddingLeft() + getListPaddingRight(), layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, Ints.MAX_POWER_OF_TWO);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    /* renamed from: l */
    private void m11349l() {
        if (this.f10429a != null) {
            m11311a(this.f10429a);
            this.f10452x = this.f10429a.getMeasuredHeight();
            this.f10453y = this.f10452x / 2;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f10429a != null) {
            if (this.f10429a.isLayoutRequested()) {
                m11349l();
            }
            this.f10433e = true;
        }
        this.f10454z = i;
    }

    protected void layoutChildren() {
        super.layoutChildren();
        if (this.f10429a != null) {
            if (this.f10429a.isLayoutRequested() && !this.f10433e) {
                m11349l();
            }
            this.f10429a.layout(0, 0, this.f10429a.getMeasuredWidth(), this.f10429a.getMeasuredHeight());
            this.f10433e = false;
        }
    }

    /* renamed from: a */
    protected boolean m11370a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        switch (motionEvent.getAction() & 255) {
            case 1:
                if (this.f10450v == 4) {
                    m11371a(false);
                }
                m11343i();
                break;
            case 2:
                m11319b((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
            case 3:
                if (this.f10450v == 4) {
                    m11364a();
                }
                m11343i();
                break;
        }
        return true;
    }

    /* renamed from: a */
    public boolean m11368a(int i, int i2, int i3, int i4) {
        if (!this.f10425T || this.f10426U == null) {
            return false;
        }
        View e = this.f10426U.mo3430e(i);
        if (e != null) {
            return m11369a(i, e, i2, i3, i4);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m11369a(int i, View view, int i2, int i3, int i4) {
        if (this.f10450v != 0 || !this.f10425T || this.f10429a != null || view == null || !this.f10449u) {
            return false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        int headerViewsCount = getHeaderViewsCount() + i;
        this.f10438j = headerViewsCount;
        this.f10439k = headerViewsCount;
        this.f10441m = headerViewsCount;
        this.f10437i = headerViewsCount;
        this.f10450v = 4;
        this.f10423R = 0;
        this.f10423R |= i2;
        this.f10429a = view;
        m11349l();
        this.f10442n = i3;
        this.f10443o = i4;
        this.f10422Q = this.f10419N;
        this.f10430b.x = this.f10418M - this.f10442n;
        this.f10430b.y = this.f10419N - this.f10443o;
        View childAt = getChildAt(this.f10441m - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setVisibility(4);
        }
        if (this.ad) {
            this.ae.m11278a();
        }
        switch (this.f10428W) {
            case 1:
                super.onTouchEvent(this.f10427V);
                break;
            case 2:
                super.onInterceptTouchEvent(this.f10427V);
                break;
        }
        requestLayout();
        if (this.aj == null) {
            return true;
        }
        this.aj.m11286c();
        return true;
    }

    /* renamed from: b */
    private void m11321b(boolean z) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt != null) {
            m11332d(firstVisiblePosition, childAt, z);
        }
    }

    /* renamed from: d */
    private void m11332d(int i, View view, boolean z) {
        this.af = true;
        m11351m();
        int i2 = this.f10438j;
        int i3 = this.f10439k;
        boolean d = m11333d();
        if (d) {
            m11346k();
            setSelectionFromTop(i, (m11306a(i, view, i2, i3) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if (d || z) {
            invalidate();
        }
        this.af = false;
    }

    /* renamed from: m */
    private void m11351m() {
        if (this.f10426U != null) {
            this.f10431c.set(this.f10418M, this.f10419N);
            this.f10426U.mo3429a(this.f10429a, this.f10430b, this.f10431c);
        }
        int i = this.f10430b.x;
        int i2 = this.f10430b.y;
        int paddingLeft = getPaddingLeft();
        if ((this.f10423R & 1) == 0 && i > paddingLeft) {
            this.f10430b.x = paddingLeft;
        } else if ((this.f10423R & 2) == 0 && i < paddingLeft) {
            this.f10430b.x = paddingLeft;
        }
        paddingLeft = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        i = getPaddingTop();
        if (firstVisiblePosition < paddingLeft) {
            i = getChildAt((paddingLeft - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.f10423R & 8) == 0 && firstVisiblePosition <= this.f10441m) {
            i = Math.max(getChildAt(this.f10441m - firstVisiblePosition).getTop(), i);
        }
        paddingLeft = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            paddingLeft = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.f10423R & 4) == 0 && lastVisiblePosition >= this.f10441m) {
            paddingLeft = Math.min(getChildAt(this.f10441m - firstVisiblePosition).getBottom(), paddingLeft);
        }
        if (i2 < i) {
            this.f10430b.y = i;
        } else if (this.f10452x + i2 > paddingLeft) {
            this.f10430b.y = paddingLeft - this.f10452x;
        }
        this.f10432d = this.f10430b.y + this.f10453y;
    }

    /* renamed from: n */
    private void m11352n() {
        if (this.f10429a != null) {
            this.f10429a.setVisibility(8);
            if (this.f10426U != null) {
                this.f10426U.mo3428a(this.f10429a);
            }
            this.f10429a = null;
            invalidate();
        }
    }

    public void setFloatViewManager(C2210i c2210i) {
        this.f10426U = c2210i;
    }

    public void setDragListener(C2204b c2204b) {
        this.f10446r = c2204b;
    }

    public void setDragEnabled(boolean z) {
        this.f10449u = z;
    }

    /* renamed from: c */
    public boolean m11375c() {
        return this.f10449u;
    }

    public void setDropListener(C2192h c2192h) {
        this.f10447s = c2192h;
    }

    public void setRemoveListener(C2193m c2193m) {
        this.f10448t = c2193m;
    }

    public void setDragSortListener(C2206e c2206e) {
        setDropListener(c2206e);
        setDragListener(c2206e);
        setRemoveListener(c2206e);
    }

    public void setDragScrollProfile(C2199c c2199c) {
        if (c2199c != null) {
            this.f10417L = c2199c;
        }
    }
}
