package com.beastbikes.android.modules.cycling.stage.p135c;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.stage.dto.C2264c;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2261e;
import java.util.ArrayList;

/* compiled from: StageScorePresenter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.c.e */
public class C2256e {
    /* renamed from: a */
    private C2261e f10730a;
    /* renamed from: b */
    private C2236a f10731b;

    public C2256e(C2261e c2261e) {
        this.f10730a = c2261e;
        this.f10731b = new C2236a(c2261e.m11586a());
    }

    /* renamed from: a */
    public void m11557a(final String str, final String str2) {
        this.f10730a.m11589b();
        this.f10730a.m11586a().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, ArrayList<C2264c>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2256e f10729c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11553a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11554a((ArrayList) obj);
            }

            /* renamed from: a */
            protected ArrayList<C2264c> m11553a(Void... voidArr) {
                try {
                    return this.f10729c.f10731b.m11503a(str, str2);
                } catch (Exception e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m11554a(ArrayList<C2264c> arrayList) {
                this.f10729c.f10730a.m11590c();
                if (arrayList == null || arrayList.isEmpty()) {
                    this.f10729c.f10730a.m11587a("null or empty");
                } else {
                    this.f10729c.f10730a.m11588a((ArrayList) arrayList);
                }
            }
        }, new Void[0]);
    }
}
