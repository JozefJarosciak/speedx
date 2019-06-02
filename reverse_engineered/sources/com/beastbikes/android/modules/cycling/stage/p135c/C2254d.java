package com.beastbikes.android.modules.cycling.stage.p135c;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.stage.dto.C2263b;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2260d;

/* compiled from: StageRankListPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.c.d */
public class C2254d {
    /* renamed from: a */
    private C2260d f10724a;
    /* renamed from: b */
    private C2236a f10725b;
    /* renamed from: c */
    private int f10726c = 1;

    public C2254d(C2260d c2260d) {
        this.f10724a = c2260d;
        this.f10725b = new C2236a(this.f10724a.m11580a());
    }

    /* renamed from: a */
    public void m11551a(final int i) {
        this.f10724a.m11580a().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, C2263b>(this) {
            /* renamed from: b */
            final /* synthetic */ C2254d f10721b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11543a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11544a((C2263b) obj);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                this.f10721b.f10724a.m11583b();
            }

            /* renamed from: a */
            protected C2263b m11543a(Void... voidArr) {
                try {
                    return this.f10721b.f10725b.m11500a(i, this.f10721b.f10726c);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11544a(C2263b c2263b) {
                this.f10721b.f10724a.m11585c();
                if (c2263b == null || c2263b.m11601a() == null) {
                    this.f10721b.f10724a.m11582a(true);
                } else if (c2263b.m11601a().isEmpty()) {
                    this.f10721b.f10724a.m11584b(true);
                } else {
                    this.f10721b.f10724a.m11581a(c2263b.m11601a(), false);
                    this.f10721b.f10726c = this.f10721b.f10726c + 1;
                }
            }
        }, new Void[0]);
    }

    /* renamed from: b */
    public void m11552b(final int i) {
        this.f10724a.m11580a().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, C2263b>(this) {
            /* renamed from: b */
            final /* synthetic */ C2254d f10723b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11545a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11546a((C2263b) obj);
            }

            /* renamed from: a */
            protected C2263b m11545a(Void... voidArr) {
                try {
                    return this.f10723b.f10725b.m11500a(i, this.f10723b.f10726c);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11546a(C2263b c2263b) {
                if (c2263b == null || c2263b.m11601a() == null) {
                    this.f10723b.f10724a.m11582a(false);
                } else if (c2263b.m11601a().isEmpty()) {
                    this.f10723b.f10724a.m11581a(c2263b.m11601a(), true);
                    this.f10723b.f10726c = this.f10723b.f10726c + 1;
                } else {
                    this.f10723b.f10724a.m11584b(false);
                }
            }
        }, new Void[0]);
    }
}
