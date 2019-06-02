package com.beastbikes.android.modules.cycling.stage.p134b;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

/* compiled from: EndlessRecyclerOnScrollListener */
/* renamed from: com.beastbikes.android.modules.cycling.stage.b.a */
public abstract class C2237a extends OnScrollListener {
    /* renamed from: a */
    public static String f10672a = C2237a.class.getSimpleName();
    /* renamed from: b */
    int f10673b;
    /* renamed from: c */
    int f10674c;
    /* renamed from: d */
    int f10675d;
    /* renamed from: e */
    private int f10676e = 0;
    /* renamed from: f */
    private boolean f10677f = true;
    /* renamed from: g */
    private int f10678g = 5;
    /* renamed from: h */
    private int f10679h = 1;
    /* renamed from: i */
    private LinearLayoutManager f10680i;

    /* renamed from: a */
    public abstract void mo3438a(int i);

    public C2237a(LinearLayoutManager linearLayoutManager) {
        this.f10680i = linearLayoutManager;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.f10674c = recyclerView.getChildCount();
        this.f10675d = this.f10680i.getItemCount();
        this.f10673b = this.f10680i.findFirstVisibleItemPosition();
        if (this.f10677f && this.f10675d > this.f10676e) {
            this.f10677f = false;
            this.f10676e = this.f10675d;
        }
        if (!this.f10677f && this.f10675d - this.f10674c <= this.f10673b + this.f10678g) {
            this.f10679h++;
            mo3438a(this.f10679h);
            this.f10677f = true;
        }
    }
}
