package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;
import rx.p208a.C5694b;

class TotallyRankFragment$2 implements C5694b<C1563a> {
    /* renamed from: a */
    final /* synthetic */ TotallyRankFragment f10202a;

    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.TotallyRankFragment$2$1 */
    class C21761 extends AsyncTask<Void, Void, List<RankDTO>> {
        /* renamed from: a */
        final /* synthetic */ TotallyRankFragment$2 f10201a;

        C21761(TotallyRankFragment$2 totallyRankFragment$2) {
            this.f10201a = totallyRankFragment$2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11158a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11159a((List) obj);
        }

        /* renamed from: a */
        protected List<RankDTO> m11158a(Void... voidArr) {
            try {
                return TotallyRankFragment.g(this.f10201a.f10202a).m11133a(TotallyRankFragment.d(this.f10201a.f10202a), TotallyRankFragment.e(this.f10201a.f10202a), 1, TotallyRankFragment.f(this.f10201a.f10202a));
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m11159a(List<RankDTO> list) {
            if (list != null) {
                TotallyRankFragment.a(this.f10201a.f10202a).clear();
                TotallyRankFragment.a(this.f10201a.f10202a).addAll(list);
                TotallyRankFragment.b(this.f10201a.f10202a).m13153b();
            }
        }
    }

    TotallyRankFragment$2(TotallyRankFragment totallyRankFragment) {
        this.f10202a = totallyRankFragment;
    }

    public /* synthetic */ void call(Object obj) {
        m11160a((C1563a) obj);
    }

    /* renamed from: a */
    public void m11160a(C1563a c1563a) {
        TotallyRankFragment.a(this.f10202a, c1563a.m8533a());
        TotallyRankFragment.c(this.f10202a);
        this.f10202a.getAsyncTaskQueue().m13740a(new C21761(this), new Void[0]);
    }
}
