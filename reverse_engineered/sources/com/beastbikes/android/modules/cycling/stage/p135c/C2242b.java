package com.beastbikes.android.modules.cycling.stage.p135c;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.p070a.C2236a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2258b;
import com.beastbikes.android.modules.cycling.stage.ui.StagesCollectionFrag;
import com.beastbikes.framework.android.p056e.C2794a;
import java.util.List;

/* compiled from: StageCollectionPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.c.b */
public class C2242b {
    /* renamed from: a */
    private C2258b f10689a;
    /* renamed from: b */
    private C2794a f10690b;
    /* renamed from: c */
    private C2236a f10691c;
    /* renamed from: d */
    private double f10692d;
    /* renamed from: e */
    private double f10693e;

    /* compiled from: StageCollectionPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.c.b$2 */
    class C22412 extends AsyncTask<Void, Void, List<StageDTO>> {
        /* renamed from: a */
        final /* synthetic */ C2242b f10688a;

        C22412(C2242b c2242b) {
            this.f10688a = c2242b;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11514a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11515a((List) obj);
        }

        /* renamed from: a */
        protected List<StageDTO> m11514a(Void... voidArr) {
            return this.f10688a.f10691c.m11504a();
        }

        /* renamed from: a */
        protected void m11515a(List<StageDTO> list) {
            if (list != null && list.size() > 0) {
                this.f10688a.f10689a.m11565a(list);
            }
        }
    }

    public C2242b(C2258b c2258b) {
        this.f10689a = c2258b;
        StagesCollectionFrag a = c2258b.m11564a();
        this.f10690b = a.getAsyncTaskQueue();
        this.f10691c = new C2236a(a.getActivity());
        SharedPreferences sharedPreferences = a.getActivity().getSharedPreferences(C1848b.m9630a().getClass().getName(), 0);
        this.f10692d = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f10693e = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
    }

    /* renamed from: a */
    public void m11519a(final long j) {
        new AsyncTask<String, Void, Void>(this) {
            /* renamed from: b */
            final /* synthetic */ C2242b f10687b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11513a((String[]) objArr);
            }

            /* renamed from: a */
            protected Void m11513a(String... strArr) {
                StageDTO a = this.f10687b.f10691c.m11499a(j);
                if (a != null) {
                    this.f10687b.f10691c.m11505a(a);
                }
                return null;
            }
        }.execute(new String[0]);
    }

    /* renamed from: a */
    public void m11518a() {
        this.f10690b.m13740a(new C22412(this), new Void[0]);
    }
}
