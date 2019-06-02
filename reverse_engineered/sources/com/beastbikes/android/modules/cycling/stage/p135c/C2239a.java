package com.beastbikes.android.modules.cycling.stage.p135c;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.stage.dto.C2262a;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2257a;
import java.util.ArrayList;

/* compiled from: ProfileScorePresenter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.c.a */
public class C2239a {
    /* renamed from: a */
    private C2257a f10684a;
    /* renamed from: b */
    private C2236a f10685b = new C2236a(this.f10684a.m11558a());

    public C2239a(C2257a c2257a) {
        this.f10684a = c2257a;
    }

    /* renamed from: a */
    public void m11512a(final int i, final String str) {
        this.f10684a.m11558a().getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, ArrayList<C2262a>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2239a f10683c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11508a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11509a((ArrayList) obj);
            }

            protected void onPreExecute() {
                this.f10683c.f10684a.m11560b();
            }

            /* renamed from: a */
            protected ArrayList<C2262a> m11508a(Void... voidArr) {
                return this.f10683c.f10685b.m11501a(i, str);
            }

            /* renamed from: a */
            protected void m11509a(ArrayList<C2262a> arrayList) {
                this.f10683c.f10684a.m11561c();
                if (arrayList == null) {
                    this.f10683c.f10684a.m11562d();
                } else if (arrayList.isEmpty()) {
                    this.f10683c.f10684a.m11563e();
                } else {
                    this.f10683c.f10684a.m11559a(arrayList);
                }
            }
        }, new Void[0]);
    }
}
