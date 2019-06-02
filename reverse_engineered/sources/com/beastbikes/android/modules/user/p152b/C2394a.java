package com.beastbikes.android.modules.user.p152b;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.ui.CyclingRecordActivity;
import com.beastbikes.android.modules.user.view.C2526a;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.framework.business.BusinessException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CyclingRecordPresenter */
/* renamed from: com.beastbikes.android.modules.user.b.a */
public class C2394a {
    /* renamed from: a */
    private static final Logger f11345a = LoggerFactory.getLogger(C2394a.class);
    /* renamed from: b */
    private C2526a f11346b;
    /* renamed from: c */
    private WeakReference<CyclingRecordActivity> f11347c;
    /* renamed from: d */
    private C1398a f11348d;
    /* renamed from: e */
    private C2350b f11349e;
    /* renamed from: f */
    private long f11350f;
    /* renamed from: g */
    private long f11351g;
    /* renamed from: h */
    private String f11352h;
    /* renamed from: i */
    private int f11353i = 1;
    /* renamed from: j */
    private int f11354j = 20;
    /* renamed from: k */
    private List<C2409c> f11355k = new ArrayList();
    /* renamed from: l */
    private ArrayList<String> f11356l;

    /* compiled from: CyclingRecordPresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.a$1 */
    class C23901 extends AsyncTask<Void, Void, List<C2409c>> {
        /* renamed from: a */
        final /* synthetic */ C2394a f11340a;

        C23901(C2394a c2394a) {
            this.f11340a = c2394a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12144a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12145a((List) obj);
        }

        /* renamed from: a */
        protected List<C2409c> m12144a(Void... voidArr) {
            if (this.f11340a.f11356l == null || this.f11340a.f11356l.isEmpty()) {
                this.f11340a.f11356l = this.f11340a.f11348d.c();
            }
            return this.f11340a.f11348d.a(this.f11340a.f11346b.m12684h(), this.f11340a.f11350f, this.f11340a.f11351g, this.f11340a.f11352h, this.f11340a.f11353i, this.f11340a.f11354j);
        }

        /* renamed from: a */
        protected void m12145a(List<C2409c> list) {
            boolean z = false;
            if (list == null || list.isEmpty()) {
                C2394a.f11345a.error("getRoutes(), onPostExecute is null");
                this.f11340a.f11346b.m12679a(false);
            } else {
                C2526a c = this.f11340a.f11346b;
                if (list.size() == this.f11340a.f11354j) {
                    z = true;
                }
                c.m12679a(z);
                this.f11340a.f11355k.addAll(list);
            }
            this.f11340a.f11346b.m12678a(this.f11340a.f11355k);
        }
    }

    /* compiled from: CyclingRecordPresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.a$2 */
    class C23912 extends AsyncTask<Void, Void, ArrayList<String>> {
        /* renamed from: a */
        final /* synthetic */ C2394a f11341a;

        C23912(C2394a c2394a) {
            this.f11341a = c2394a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12146a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12147a((ArrayList) obj);
        }

        /* renamed from: a */
        protected ArrayList<String> m12146a(Void... voidArr) {
            return this.f11341a.f11348d.c();
        }

        /* renamed from: a */
        protected void m12147a(ArrayList<String> arrayList) {
            if (arrayList != null && !arrayList.isEmpty()) {
                this.f11341a.f11356l = arrayList;
                this.f11341a.f11346b.m12677a(this.f11341a.f11356l);
            }
        }
    }

    /* compiled from: CyclingRecordPresenter */
    /* renamed from: com.beastbikes.android.modules.user.b.a$4 */
    class C23934 extends AsyncTask<Void, Void, List<C2409c>> {
        /* renamed from: a */
        final /* synthetic */ C2394a f11344a;

        C23934(C2394a c2394a) {
            this.f11344a = c2394a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12150a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12151a((List) obj);
        }

        /* renamed from: a */
        protected List<C2409c> m12150a(Void... voidArr) {
            try {
                List<LocalActivity> c = this.f11344a.f11348d.c(this.f11344a.f11346b.m12684h(), this.f11344a.f11346b.m12685i());
                if (c == null || c.isEmpty()) {
                    return null;
                }
                List<C2409c> arrayList = new ArrayList();
                for (LocalActivity localActivity : c) {
                    if (localActivity.getTotalDistance() > 0.0d) {
                        arrayList.add(new ActivityDTO(localActivity));
                    }
                }
                return arrayList;
            } catch (BusinessException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m12151a(List<C2409c> list) {
            if (list != null && list.size() > 0) {
                this.f11344a.f11355k.addAll(list);
            }
            this.f11344a.m12162h();
        }
    }

    public C2394a(C2526a c2526a) {
        this.f11346b = c2526a;
        this.f11347c = new WeakReference(this.f11346b.m12683g());
        if (this.f11347c.get() != null) {
            this.f11348d = new C1398a((Activity) this.f11347c.get());
            this.f11349e = new C2350b((Context) this.f11347c.get());
        }
    }

    /* renamed from: h */
    private void m12162h() {
        if (this.f11347c.get() != null) {
            ((CyclingRecordActivity) this.f11347c.get()).getAsyncTaskQueue().m13740a(new C23901(this), new Void[0]);
        }
    }

    /* renamed from: a */
    public void m12166a() {
        if (this.f11356l != null && !this.f11356l.isEmpty()) {
            this.f11346b.m12677a(this.f11356l);
        } else if (this.f11347c.get() != null) {
            ((CyclingRecordActivity) this.f11347c.get()).getAsyncTaskQueue().m13740a(new C23912(this), new Void[0]);
        }
    }

    /* renamed from: a */
    public void m12168a(final ActivityDTO activityDTO) {
        if (activityDTO != null && this.f11347c.get() != null) {
            ((CyclingRecordActivity) this.f11347c.get()).getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Boolean>(this) {
                /* renamed from: b */
                final /* synthetic */ C2394a f11343b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12148a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12149a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m12148a(String... strArr) {
                    try {
                        String str = strArr[0];
                        boolean a = this.f11343b.f11348d.a(str);
                        if (a) {
                            LocalActivity b = this.f11343b.f11348d.b(str);
                            if (!(b == null || b.isSynced() || b.getCourseId() <= 0)) {
                                this.f11343b.f11349e.m12007d(b.getTrainId());
                            }
                            this.f11343b.f11348d.c(str);
                            this.f11343b.f11348d.e(str);
                            C2394a.f11345a.info("Delete LocalActivity activityId = " + str + "success");
                        }
                        return Boolean.valueOf(a);
                    } catch (BusinessException e) {
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m12149a(Boolean bool) {
                    if (bool.booleanValue() && this.f11343b.f11355k.contains(activityDTO)) {
                        this.f11343b.f11355k.remove(activityDTO);
                    }
                    this.f11343b.f11346b.m12676a(activityDTO, bool.booleanValue());
                }
            }, activityDTO.getActivityIdentifier());
        }
    }

    /* renamed from: b */
    public void m12170b() {
        if (this.f11347c.get() != null) {
            ((CyclingRecordActivity) this.f11347c.get()).getAsyncTaskQueue().m13740a(new C23934(this), new Void[0]);
        }
    }

    /* renamed from: a */
    public void m12169a(String str) {
        this.f11350f = 0;
        this.f11351g = 0;
        this.f11352h = str;
        this.f11353i = 1;
        this.f11355k.clear();
        m12170b();
    }

    /* renamed from: c */
    public void m12171c() {
        this.f11350f = 0;
        this.f11351g = 0;
        this.f11352h = null;
        this.f11353i = 1;
        this.f11355k.clear();
        m12170b();
    }

    /* renamed from: a */
    public void m12167a(long j, long j2) {
        this.f11350f = j;
        this.f11351g = j2;
        this.f11352h = null;
        this.f11353i = 1;
        this.f11355k.clear();
        m12170b();
    }

    /* renamed from: d */
    public void m12172d() {
        this.f11353i = 1;
        this.f11355k.clear();
        m12170b();
    }

    /* renamed from: e */
    public void m12173e() {
        this.f11353i++;
        m12162h();
    }

    /* renamed from: f */
    public void m12174f() {
        if (this.f11355k != null) {
            this.f11355k.clear();
            this.f11355k = null;
        }
        if (this.f11356l != null) {
            this.f11356l.clear();
            this.f11356l = null;
        }
    }
}
