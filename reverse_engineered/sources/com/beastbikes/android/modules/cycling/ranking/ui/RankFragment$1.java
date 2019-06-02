package com.beastbikes.android.modules.cycling.ranking.ui;

import android.location.Location;
import com.android.volley.VolleyError;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.locale.p104a.C1823a;

class RankFragment$1 implements C1823a {
    /* renamed from: a */
    final /* synthetic */ RankFragment f10192a;

    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.RankFragment$1$1 */
    class C21751 implements C1821c {
        /* renamed from: a */
        final /* synthetic */ RankFragment$1 f10191a;

        C21751(RankFragment$1 rankFragment$1) {
            this.f10191a = rankFragment$1;
        }

        /* renamed from: a */
        public void mo3252a(C1856b c1856b) {
            if (c1856b != null) {
                String d = c1856b.m9678d();
                if (RankFragment.a(this.f10191a.f10192a)) {
                    if (RankFragment.b(this.f10191a.f10192a) != null) {
                        RankFragment.b(this.f10191a.f10192a).cancel();
                    }
                    RankFragment.a(this.f10191a.f10192a, d);
                }
            }
        }

        /* renamed from: a */
        public void mo3251a(VolleyError volleyError) {
        }
    }

    RankFragment$1(RankFragment rankFragment) {
        this.f10192a = rankFragment;
    }

    /* renamed from: a */
    public void mo3253a(Location location) {
        new C1855a().m9671a(this.f10192a.getActivity(), this.f10192a.getRequestQueueFactory(), location.getLatitude(), location.getLongitude(), new C21751(this));
    }

    public void e_() {
    }
}
