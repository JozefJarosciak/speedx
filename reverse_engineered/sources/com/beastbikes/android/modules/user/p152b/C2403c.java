package com.beastbikes.android.modules.user.p152b;

import android.os.AsyncTask;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.message.p071a.C2310a;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.view.C2469c;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.business.BusinessException;

/* compiled from: ProfilePresenter */
/* renamed from: com.beastbikes.android.modules.user.b.c */
public class C2403c {
    /* renamed from: a */
    private C2469c f11372a;
    /* renamed from: b */
    private C2794a f11373b;
    /* renamed from: c */
    private C2389c f11374c;
    /* renamed from: d */
    private C2310a f11375d;

    /* compiled from: ProfilePresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.c$1 */
    class C23991 extends AsyncTask<String, Void, ProfileDTO> {
        /* renamed from: a */
        final /* synthetic */ C2403c f11368a;

        C23991(C2403c c2403c) {
            this.f11368a = c2403c;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12189a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12190a((ProfileDTO) obj);
        }

        /* renamed from: a */
        protected ProfileDTO m12189a(String... strArr) {
            try {
                return this.f11368a.f11374c.m12136c(strArr[0]);
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m12190a(ProfileDTO profileDTO) {
            if (profileDTO != null) {
                this.f11368a.f11372a.mo3487a(profileDTO);
            }
        }
    }

    /* compiled from: ProfilePresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.c$2 */
    class C24002 extends AsyncTask<String, Void, Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2403c f11369a;

        C24002(C2403c c2403c) {
            this.f11369a = c2403c;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12191a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12192a((Boolean) obj);
        }

        /* renamed from: a */
        protected Boolean m12191a(String... strArr) {
            return Boolean.valueOf(this.f11369a.f11374c.m12140f(strArr[0]));
        }

        /* renamed from: a */
        protected void m12192a(Boolean bool) {
            this.f11369a.f11372a.mo3489a(bool.booleanValue());
        }
    }

    /* compiled from: ProfilePresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.c$3 */
    class C24013 extends AsyncTask<String, Void, UserDetailDTO> {
        /* renamed from: a */
        final /* synthetic */ C2403c f11370a;

        C24013(C2403c c2403c) {
            this.f11370a = c2403c;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12193a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12194a((UserDetailDTO) obj);
        }

        /* renamed from: a */
        protected UserDetailDTO m12193a(String... strArr) {
            try {
                return this.f11370a.f11374c.m12138d(strArr[0]);
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m12194a(UserDetailDTO userDetailDTO) {
            this.f11370a.f11372a.mo3488a(userDetailDTO);
        }
    }

    /* compiled from: ProfilePresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.c$4 */
    class C24024 extends AsyncTask<Long, Void, Integer> {
        /* renamed from: a */
        final /* synthetic */ C2403c f11371a;

        C24024(C2403c c2403c) {
            this.f11371a = c2403c;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12195a((Long[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12196a((Integer) obj);
        }

        /* renamed from: a */
        protected Integer m12195a(Long... lArr) {
            try {
                return Integer.valueOf(this.f11371a.f11375d.m11805a(lArr[0].longValue()));
            } catch (BusinessException e) {
                return Integer.valueOf(-1);
            }
        }

        /* renamed from: a */
        protected void m12196a(Integer num) {
            this.f11371a.f11372a.mo3490b(num.intValue());
        }
    }

    public C2403c(C2469c c2469c) {
        this.f11372a = c2469c;
        SessionFragment c = c2469c.mo3491c();
        this.f11373b = c.getAsyncTaskQueue();
        this.f11374c = new C2389c(c.getActivity());
        this.f11375d = new C2310a(c.getActivity());
    }

    /* renamed from: a */
    public void m12200a() {
        this.f11373b.m13740a(new C23991(this), this.f11372a.mo3486a());
    }

    /* renamed from: b */
    public void m12201b() {
        this.f11373b.m13740a(new C24002(this), this.f11372a.mo3486a());
    }

    /* renamed from: c */
    public void m12202c() {
        this.f11373b.m13740a(new C24013(this), this.f11372a.mo3486a());
    }

    /* renamed from: d */
    public void m12203d() {
        this.f11373b.m13740a(new C24024(this), Long.valueOf(this.f11372a.mo3492d()));
    }
}
