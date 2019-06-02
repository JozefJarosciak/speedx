package com.beastbikes.android.widget.p081b;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.beastbikes.android.C1373R;
import java.lang.ref.WeakReference;

/* compiled from: SpeedXRecyclerView */
/* renamed from: com.beastbikes.android.widget.b.k */
public class C2617k extends FrameLayout implements OnRefreshListener {
    /* renamed from: a */
    private SwipeRefreshLayout f12211a;
    /* renamed from: b */
    private RecyclerView f12212b;
    /* renamed from: c */
    private LinearLayoutManager f12213c;
    /* renamed from: d */
    private ViewGroup f12214d;
    /* renamed from: e */
    private C2471h f12215e;
    /* renamed from: f */
    private C2616a f12216f;

    /* compiled from: SpeedXRecyclerView */
    /* renamed from: com.beastbikes.android.widget.b.k$1 */
    class C26151 extends OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ C2617k f12210a;

        C26151(C2617k c2617k) {
            this.f12210a = c2617k;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (this.f12210a.f12213c.findLastVisibleItemPosition() == this.f12210a.f12215e.getItemCount() - 1 && this.f12210a.f12216f != null) {
                this.f12210a.f12216f.m13014f();
            }
        }
    }

    /* compiled from: SpeedXRecyclerView */
    /* renamed from: com.beastbikes.android.widget.b.k$a */
    public interface C2616a {
        /* renamed from: e */
        void m13013e();

        /* renamed from: f */
        void m13014f();
    }

    public C2617k(Context context, ViewGroup viewGroup) {
        this(context, null, viewGroup);
    }

    public C2617k(Context context, AttributeSet attributeSet, ViewGroup viewGroup) {
        super(context, attributeSet);
        this.f12214d = viewGroup;
        m13017b();
    }

    /* renamed from: b */
    private void m13017b() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.layout_swiperecyclerview, this.f12214d);
        this.f12211a = (SwipeRefreshLayout) inflate.findViewById(C1373R.id.swipe_refresh_layout);
        this.f12212b = (RecyclerView) inflate.findViewById(C1373R.id.recycler_view);
        this.f12211a.setEnabled(true);
        this.f12211a.setColorSchemeResources(new int[]{C1373R.color.design_color_c7});
        RecyclerView recyclerView = this.f12212b;
        LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.f12213c = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        this.f12211a.setOnRefreshListener(this);
        this.f12212b.addOnScrollListener(new C26151(this));
    }

    public void onRefresh() {
        this.f12211a.setRefreshing(true);
        if (this.f12216f != null) {
            this.f12216f.m13013e();
        }
    }

    /* renamed from: a */
    public void m13020a(ItemDecoration itemDecoration) {
        this.f12212b.addItemDecoration(itemDecoration);
    }

    /* renamed from: a */
    public void m13019a() {
        this.f12211a.setRefreshing(false);
    }

    /* renamed from: a */
    public void m13021a(boolean z) {
        if (this.f12215e != null) {
            this.f12215e.m12402c(z);
        }
    }

    public void setAdapter(C2471h c2471h) {
        this.f12215e = c2471h;
        this.f12215e.m12399b(true);
        this.f12212b.setAdapter(this.f12215e);
    }

    public void setHasHeader(boolean z) {
        if (this.f12215e != null) {
            this.f12215e.m12396a(z);
            this.f12215e.notifyDataSetChanged();
        }
    }

    public void setHasFooter(boolean z) {
        if (this.f12215e != null) {
            this.f12215e.m12399b(z);
            this.f12215e.notifyDataSetChanged();
        }
    }

    public void setRecyclerCallBack(C2616a c2616a) {
        this.f12216f = (C2616a) new WeakReference(c2616a).get();
    }
}
