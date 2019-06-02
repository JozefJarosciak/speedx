package com.beastbikes.android.modules.cycling.achievement.p112b;

import android.app.Activity;
import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.achievement.dto.UserAchievementDto;
import com.beastbikes.android.modules.cycling.achievement.p063a.C1893a;
import com.beastbikes.android.modules.cycling.achievement.p113c.C1898a;
import com.beastbikes.framework.android.p056e.C2794a;

/* compiled from: AchievementSelfPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.achievement.b.a */
public class C1895a {
    /* renamed from: a */
    private C1898a f8459a;
    /* renamed from: b */
    private C2794a f8460b;
    /* renamed from: c */
    private C1893a f8461c;

    public C1895a(C1898a c1898a) {
        this.f8459a = c1898a;
        Activity a = c1898a.m9791a();
        this.f8460b = a.getAsyncTaskQueue();
        this.f8461c = new C1893a(a);
    }

    /* renamed from: a */
    public void m9785a(final String str) {
        this.f8460b.m13740a(new AsyncTask<String, Void, UserAchievementDto>(this) {
            /* renamed from: b */
            final /* synthetic */ C1895a f8458b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9781a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9782a((UserAchievementDto) obj);
            }

            /* renamed from: a */
            protected UserAchievementDto m9781a(String... strArr) {
                return this.f8458b.f8461c.m9779a(str);
            }

            /* renamed from: a */
            protected void m9782a(UserAchievementDto userAchievementDto) {
                if (userAchievementDto != null) {
                    this.f8458b.f8459a.m9792a(userAchievementDto);
                }
            }
        }, new String[0]);
    }
}
