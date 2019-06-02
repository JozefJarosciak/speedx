package com.beastbikes.android.modules.user.p152b;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.p077a.C2387a;
import com.beastbikes.android.modules.user.ui.SearchRegionActivity;
import com.beastbikes.android.modules.user.view.C2528e;
import java.util.ArrayList;

/* compiled from: SearchRegionPresenter */
/* renamed from: com.beastbikes.android.modules.user.b.d */
public class C2405d {
    /* renamed from: a */
    private C2528e f11379a;
    /* renamed from: b */
    private C2387a f11380b = new C2387a(this.f11381c);
    /* renamed from: c */
    private SearchRegionActivity f11381c;
    /* renamed from: d */
    private AsyncTask<Void, Void, ArrayList<SearchRegionDTO>> f11382d;

    public C2405d(C2528e c2528e) {
        this.f11379a = c2528e;
        this.f11381c = c2528e.m12691i();
    }

    /* renamed from: a */
    public void m12208a(final String str, final String str2) {
        if (this.f11382d != null) {
            this.f11382d.cancel(true);
            this.f11382d = null;
        }
        this.f11382d = new AsyncTask<Void, Void, ArrayList<SearchRegionDTO>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2405d f11378c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12204a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12205a((ArrayList) obj);
            }

            /* renamed from: a */
            protected ArrayList<SearchRegionDTO> m12204a(Void... voidArr) {
                return this.f11378c.f11380b.m12116a(str, str2);
            }

            /* renamed from: a */
            protected void m12205a(ArrayList<SearchRegionDTO> arrayList) {
                this.f11378c.f11379a.m12690a(arrayList);
            }
        };
        this.f11381c.getAsyncTaskQueue().m13740a(this.f11382d, new Void[0]);
    }
}
