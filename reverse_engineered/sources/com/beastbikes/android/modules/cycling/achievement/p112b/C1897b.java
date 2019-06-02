package com.beastbikes.android.modules.cycling.achievement.p112b;

import android.app.Activity;
import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementStageDto;
import com.beastbikes.android.modules.cycling.achievement.p063a.C1893a;
import com.beastbikes.android.modules.cycling.achievement.p113c.C1899b;
import com.beastbikes.framework.android.p056e.C2794a;
import java.util.List;

/* compiled from: AchievementsStagePresenter */
/* renamed from: com.beastbikes.android.modules.cycling.achievement.b.b */
public class C1897b {
    /* renamed from: a */
    private C1899b f8465a;
    /* renamed from: b */
    private C2794a f8466b;
    /* renamed from: c */
    private C1893a f8467c;

    public C1897b(C1899b c1899b) {
        this.f8465a = c1899b;
        Activity a = c1899b.m9793a();
        this.f8466b = a.getAsyncTaskQueue();
        this.f8467c = new C1893a(a);
    }

    /* renamed from: a */
    public void m9790a(final long j, final String str) {
        this.f8466b.m13740a(new AsyncTask<String, Void, List<AchievementStageDto>>(this) {
            /* renamed from: c */
            final /* synthetic */ C1897b f8464c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9786a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9787a((List) obj);
            }

            /* renamed from: a */
            protected List<AchievementStageDto> m9786a(String... strArr) {
                return this.f8464c.f8467c.m9780a(j, str);
            }

            /* renamed from: a */
            protected void m9787a(List<AchievementStageDto> list) {
                if (list != null && list.size() > 0) {
                    this.f8464c.f8465a.m9794a(list);
                }
            }
        }, new String[0]);
    }
}
