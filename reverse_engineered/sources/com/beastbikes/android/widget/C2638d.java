package com.beastbikes.android.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SwipeRefreshLoadRecyclerView */
/* renamed from: com.beastbikes.android.widget.d */
public class C2638d extends FrameLayout {
    /* renamed from: a */
    private Context f12340a;
    /* renamed from: b */
    private ViewGroup f12341b;
    /* renamed from: c */
    private LinearLayoutManager f12342c;
    /* renamed from: d */
    private SwipeRefreshLayout f12343d;
    /* renamed from: e */
    private RecyclerView f12344e;
    /* renamed from: f */
    private List<?> f12345f;
    /* renamed from: g */
    private Object f12346g;
    /* renamed from: h */
    private C2633d f12347h;
    /* renamed from: i */
    private boolean f12348i;
    /* renamed from: j */
    private boolean f12349j;
    /* renamed from: k */
    private boolean f12350k;
    /* renamed from: l */
    private int f12351l;
    /* renamed from: m */
    private C2631b f12352m;
    /* renamed from: n */
    private C2077a f12353n;

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$c */
    public interface C2076c {
        /* renamed from: a */
        ViewHolder mo3371a();

        /* renamed from: a */
        void mo3372a(ViewHolder viewHolder, Object obj, int i, boolean z);
    }

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$a */
    public static abstract class C2077a implements C2076c {
        /* renamed from: b */
        public ViewHolder mo3419b() {
            return null;
        }
    }

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$1 */
    class C26291 implements OnRefreshListener {
        /* renamed from: a */
        final /* synthetic */ C2638d f12319a;

        C26291(C2638d c2638d) {
            this.f12319a = c2638d;
        }

        public void onRefresh() {
            if (!this.f12319a.f12348i && !this.f12319a.f12349j) {
                this.f12319a.f12348i = true;
                this.f12319a.f12343d.setRefreshing(true);
                if (this.f12319a.f12352m != null) {
                    this.f12319a.f12352m.m13111a();
                }
            }
        }
    }

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$2 */
    class C26302 extends OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ C2638d f12320a;

        C26302(C2638d c2638d) {
            this.f12320a = c2638d;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            this.f12320a.f12351l = this.f12320a.f12342c.findLastVisibleItemPosition();
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (this.f12320a.f12350k && !this.f12320a.f12348i && !this.f12320a.f12349j && this.f12320a.f12347h.m13117b() && this.f12320a.f12351l + 1 == this.f12320a.f12347h.getItemCount()) {
                this.f12320a.f12349j = true;
                this.f12320a.f12343d.setEnabled(false);
                if (this.f12320a.f12342c.findLastVisibleItemPosition() > this.f12320a.f12345f.size() - 2) {
                    this.f12320a.f12348i = true;
                    this.f12320a.f12348i = false;
                    if (this.f12320a.f12352m != null) {
                        this.f12320a.f12352m.a_();
                    }
                }
            }
        }
    }

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$b */
    public interface C2631b {
        /* renamed from: a */
        void m13111a();

        void a_();
    }

    /* compiled from: SwipeRefreshLoadRecyclerView */
    /* renamed from: com.beastbikes.android.widget.d$d */
    public class C2633d extends Adapter {
        /* renamed from: a */
        final /* synthetic */ C2638d f12324a;
        /* renamed from: b */
        private List<?> f12325b;
        /* renamed from: c */
        private boolean f12326c;
        /* renamed from: d */
        private boolean f12327d;
        /* renamed from: e */
        private boolean f12328e;
        /* renamed from: f */
        private ViewHolder f12329f;
        /* renamed from: g */
        private ViewHolder f12330g;
        /* renamed from: h */
        private int f12331h = 0;

        /* compiled from: SwipeRefreshLoadRecyclerView */
        /* renamed from: com.beastbikes.android.widget.d$d$a */
        public class C2632a extends ViewHolder {
            /* renamed from: a */
            public final ProgressBar f12321a;
            /* renamed from: b */
            public final TextView f12322b;
            /* renamed from: c */
            final /* synthetic */ C2633d f12323c;

            public C2632a(C2633d c2633d, View view) {
                this.f12323c = c2633d;
                super(view);
                this.f12321a = (ProgressBar) view.findViewById(C1373R.id.progress_view);
                this.f12322b = (TextView) view.findViewById(C1373R.id.tv_content);
            }
        }

        /* renamed from: a */
        public int m13113a() {
            if (this.f12325b == null || this.f12325b.size() == 0) {
                return 0;
            }
            return this.f12325b.size();
        }

        public C2633d(C2638d c2638d, List<?> list) {
            this.f12324a = c2638d;
            this.f12325b = list;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == -2147483647) {
                return new C2632a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_view_load_more, viewGroup, false));
            }
            if (i == Integer.MIN_VALUE) {
                if (this.f12324a.f12353n != null) {
                    this.f12330g = this.f12324a.f12353n.mo3419b();
                }
                return this.f12330g;
            }
            if (this.f12324a.f12353n != null) {
                this.f12329f = this.f12324a.f12353n.mo3371a();
            }
            return this.f12329f;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (!(viewHolder instanceof C2632a)) {
                Object obj = null;
                if (this.f12327d) {
                    if (i == 0) {
                        obj = this.f12324a.f12346g;
                    }
                    if (i >= 1 && i <= getItemCount()) {
                        obj = this.f12325b.get(i - 1);
                    }
                    if (i == this.f12331h) {
                        this.f12324a.f12353n.mo3372a(viewHolder, obj, i - 1, true);
                        return;
                    } else {
                        this.f12324a.f12353n.mo3372a(viewHolder, obj, i - 1, false);
                        return;
                    }
                }
                if (i >= 0 && i <= getItemCount() - 1) {
                    obj = this.f12325b.get(i);
                }
                if (i == this.f12331h) {
                    this.f12324a.f12353n.mo3372a(viewHolder, obj, i, true);
                } else {
                    this.f12324a.f12353n.mo3372a(viewHolder, obj, i, false);
                }
            } else if (this.f12328e) {
                ((C2632a) viewHolder).f12321a.setVisibility(0);
                ((C2632a) viewHolder).f12322b.setText(this.f12324a.getResources().getText(C1373R.string.loadmore));
            } else {
                ((C2632a) viewHolder).f12321a.setVisibility(8);
                ((C2632a) viewHolder).f12322b.setText(this.f12324a.getResources().getString(C1373R.string.nomoredate));
            }
        }

        public int getItemCount() {
            int i = 1;
            m13112d();
            if (m13113a() == 0) {
                int size = this.f12325b.size();
                if (!this.f12327d) {
                    i = 0;
                }
                return i + size;
            }
            size = (this.f12326c ? 1 : 0) + this.f12325b.size();
            if (!this.f12327d) {
                i = 0;
            }
            return i + size;
        }

        /* renamed from: d */
        private void m13112d() {
            int i = 1;
            int i2;
            if (this.f12326c) {
                int size = this.f12325b.size();
                if (this.f12326c) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i2 += size;
                if (!this.f12327d) {
                    i = 0;
                }
                this.f12331h = (i2 + i) - 2;
                return;
            }
            i2 = (this.f12326c ? 1 : 0) + this.f12325b.size();
            if (!this.f12327d) {
                i = 0;
            }
            this.f12331h = (i2 + i) - 1;
        }

        public int getItemViewType(int i) {
            if (i == 0) {
                if (this.f12327d) {
                    return Integer.MIN_VALUE;
                }
                return -2147483646;
            } else if (i == getItemCount() - 1 && this.f12326c && m13113a() > 0) {
                return -2147483647;
            } else {
                return -2147483646;
            }
        }

        /* renamed from: a */
        public void m13114a(boolean z) {
            this.f12326c = z;
            this.f12324a.f12347h.notifyDataSetChanged();
        }

        /* renamed from: b */
        public void m13116b(boolean z) {
            this.f12327d = z;
        }

        /* renamed from: b */
        public boolean m13117b() {
            return this.f12328e;
        }

        /* renamed from: c */
        public void m13118c(boolean z) {
            if (this.f12328e != z) {
                this.f12328e = z;
                notifyDataSetChanged();
            }
        }

        /* renamed from: a */
        public void m13115a(boolean z, boolean z2) {
            if (this.f12328e != z || this.f12326c != z2) {
                this.f12328e = z;
                this.f12326c = z2;
                notifyDataSetChanged();
            }
        }

        /* renamed from: c */
        public boolean m13119c() {
            return this.f12326c;
        }
    }

    public C2638d(Context context, ViewGroup viewGroup, List<?> list, int i) {
        this(context, null, viewGroup, list, i);
    }

    public C2638d(Context context, AttributeSet attributeSet, ViewGroup viewGroup, List<?> list, int i) {
        super(context, attributeSet);
        this.f12345f = new ArrayList();
        this.f12348i = false;
        this.f12349j = false;
        this.f12350k = true;
        this.f12340a = context;
        this.f12341b = viewGroup;
        this.f12345f = list;
        m13141c(i);
    }

    /* renamed from: b */
    private void m13137b(int i) {
        switch (i) {
            case 1:
                this.f12347h.m13116b(true);
                return;
            case 2:
                this.f12347h.m13114a(true);
                return;
            case 3:
                this.f12347h.m13116b(true);
                this.f12347h.m13114a(true);
                return;
            default:
                return;
        }
    }

    public void setRecyclerCallBack(C2631b c2631b) {
        this.f12352m = (C2631b) new WeakReference(c2631b).get();
    }

    public void setAdapter(C2077a c2077a) {
        if (c2077a != null) {
            this.f12353n = c2077a;
        }
    }

    /* renamed from: c */
    private void m13141c(int i) {
        View inflate = LayoutInflater.from(this.f12340a).inflate(C1373R.layout.layout_swiperecyclerview, this.f12341b);
        this.f12343d = (SwipeRefreshLayout) inflate.findViewById(C1373R.id.swipe_refresh_layout);
        this.f12344e = (RecyclerView) inflate.findViewById(C1373R.id.recycler_view);
        this.f12343d.setEnabled(true);
        this.f12343d.setColorSchemeResources(new int[]{C1373R.color.design_color_c7});
        RecyclerView recyclerView = this.f12344e;
        LayoutManager linearLayoutManager = new LinearLayoutManager(this.f12340a);
        this.f12342c = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        this.f12347h = new C2633d(this, this.f12345f);
        m13137b(i);
        this.f12347h.m13118c(true);
        this.f12344e.setAdapter(this.f12347h);
        this.f12343d.setOnRefreshListener(new C26291(this));
        this.f12344e.addOnScrollListener(new C26302(this));
    }

    public void setRefreshEnable(boolean z) {
        if (this.f12343d != null) {
            this.f12343d.setEnabled(z);
        }
    }

    /* renamed from: a */
    public void m13150a() {
        this.f12349j = false;
        this.f12348i = false;
        this.f12348i = false;
        this.f12343d.setEnabled(true);
        this.f12343d.setRefreshing(false);
    }

    /* renamed from: b */
    public void m13153b() {
        if (this.f12347h != null) {
            this.f12347h.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m13151a(int i) {
        if (this.f12347h != null) {
            this.f12347h.notifyItemChanged(i);
            this.f12344e.setItemAnimator(null);
        }
    }

    /* renamed from: a */
    public void m13152a(boolean z) {
        this.f12347h.m13115a(z, this.f12347h.m13119c());
    }

    public void setHasFooter(boolean z) {
        this.f12347h.m13114a(z);
    }

    public void setCanLoadMore(boolean z) {
        this.f12350k = z;
    }

    public void setHasHeader(boolean z) {
        this.f12347h.m13116b(z);
    }

    public Object getHeaderDate() {
        return this.f12346g;
    }

    /* renamed from: c */
    public boolean m13154c() {
        return this.f12348i;
    }

    public void setHeaderDate(Object obj) {
        this.f12346g = obj;
        this.f12347h.notifyDataSetChanged();
    }
}
