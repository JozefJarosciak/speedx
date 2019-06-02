package com.beastbikes.android.modules.user.p152b;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.biz.C1916c;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.ProfileDataFragment;
import com.beastbikes.android.modules.user.view.C2527b;
import java.util.HashMap;

/* compiled from: ProfileDataPresenter */
/* renamed from: com.beastbikes.android.modules.user.b.b */
public class C2398b {
    /* renamed from: a */
    private C2527b f11363a;
    /* renamed from: b */
    private ProfileDataFragment f11364b;
    /* renamed from: c */
    private C2389c f11365c = new C2389c(this.f11364b.getActivity());
    /* renamed from: d */
    private C1916c f11366d = new C1916c(this.f11364b.getActivity());
    /* renamed from: e */
    private C2350b f11367e = new C2350b(this.f11364b.getActivity());

    /* compiled from: ProfileDataPresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.b$1 */
    class C23951 extends AsyncTask<String, Void, UserDetailDTO> {
        /* renamed from: a */
        final /* synthetic */ C2398b f11357a;

        C23951(C2398b c2398b) {
            this.f11357a = c2398b;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12175a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12176a((UserDetailDTO) obj);
        }

        /* renamed from: a */
        protected UserDetailDTO m12175a(String... strArr) {
            return this.f11357a.f11365c.m12123a();
        }

        /* renamed from: a */
        protected void m12176a(UserDetailDTO userDetailDTO) {
            if (this.f11357a.f11364b.getActivity() != null && !this.f11357a.f11364b.getActivity().isFinishing() && userDetailDTO != null) {
                this.f11357a.f11363a.m12688a(userDetailDTO);
            }
        }
    }

    public C2398b(C2527b c2527b) {
        this.f11363a = c2527b;
        this.f11364b = c2527b.m12686a();
    }

    /* renamed from: a */
    public void m12186a() {
        this.f11364b.getAsyncTaskQueue().m13740a(new C23951(this), new String[0]);
    }

    /* renamed from: a */
    public void m12187a(final double d) {
        this.f11364b.getAsyncTaskQueue().m13740a(new AsyncTask<Double, Void, Boolean>(this) {
            /* renamed from: b */
            final /* synthetic */ C2398b f11359b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12177a((Double[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12178a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m12177a(Double... dArr) {
                return Boolean.valueOf(this.f11359b.f11366d.m9901a(d * 1000.0d));
            }

            /* renamed from: a */
            protected void m12178a(Boolean bool) {
                if (bool.booleanValue()) {
                    this.f11359b.f11363a.m12687a(d);
                }
            }
        }, new Double[0]);
    }

    /* renamed from: a */
    public void m12188a(final int i, final int i2) {
        this.f11364b.getAsyncTaskQueue().m13740a(new AsyncTask<Integer, Void, HashMap<String, CalendarDto>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2398b f11362c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12179a((Integer[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12180a((HashMap) obj);
            }

            /* renamed from: a */
            protected HashMap<String, CalendarDto> m12179a(Integer... numArr) {
                return this.f11362c.f11367e.m12001a(i, i2, 1);
            }

            /* renamed from: a */
            protected void m12180a(HashMap<String, CalendarDto> hashMap) {
                if (hashMap != null) {
                    this.f11362c.f11363a.m12689a((HashMap) hashMap);
                }
            }
        }, new Integer[0]);
    }
}
