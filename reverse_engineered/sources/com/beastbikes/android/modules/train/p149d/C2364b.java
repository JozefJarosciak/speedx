package com.beastbikes.android.modules.train.p149d;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainInfoDTO;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.train.p148c.C2354b;
import com.beastbikes.android.modules.train.ui.TrainCourseInfoActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: TrainCourseInfoPresenter */
/* renamed from: com.beastbikes.android.modules.train.d.b */
public class C2364b {
    /* renamed from: a */
    private static final Logger f11229a = LoggerFactory.getLogger("TrainCourseInfoPresenter");
    /* renamed from: b */
    private C2354b f11230b;
    /* renamed from: c */
    private TrainCourseInfoActivity f11231c;
    /* renamed from: d */
    private C2350b f11232d = new C2350b(this.f11231c);

    public C2364b(C2354b c2354b) {
        this.f11230b = c2354b;
        this.f11231c = c2354b.m12022b();
    }

    /* renamed from: a */
    public void m12056a() {
        final int f = this.f11230b.m12026f();
        if (f > 0) {
            this.f11231c.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, TrainCourseDTO>(this) {
                /* renamed from: b */
                final /* synthetic */ C2364b f11220b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12043a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12044a((TrainCourseDTO) obj);
                }

                protected void onPreExecute() {
                    this.f11220b.f11230b.m12023c();
                }

                /* renamed from: a */
                protected TrainCourseDTO m12043a(Void... voidArr) {
                    return this.f11220b.f11232d.m12003b(f);
                }

                /* renamed from: a */
                protected void m12044a(TrainCourseDTO trainCourseDTO) {
                    if (trainCourseDTO == null) {
                        C2364b.f11229a.error("GetSingleTrainCourseInfo failed");
                        this.f11220b.f11230b.m12025e();
                    } else {
                        this.f11220b.f11230b.m12019a(trainCourseDTO);
                    }
                    this.f11220b.f11230b.m12024d();
                }
            }, new Void[0]);
        }
    }

    /* renamed from: b */
    public void m12058b() {
        final int f = this.f11230b.m12026f();
        if (f > 0) {
            this.f11231c.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, TrainCourseDTO>(this) {
                /* renamed from: b */
                final /* synthetic */ C2364b f11222b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12045a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12046a((TrainCourseDTO) obj);
                }

                protected void onPreExecute() {
                    this.f11222b.f11230b.m12023c();
                }

                /* renamed from: a */
                protected TrainCourseDTO m12045a(Void... voidArr) {
                    TrainInfoDTO c = this.f11222b.f11232d.m12006c(f);
                    if (c == null) {
                        return null;
                    }
                    return this.f11222b.f11232d.m12003b(c.getCourseId());
                }

                /* renamed from: a */
                protected void m12046a(TrainCourseDTO trainCourseDTO) {
                    if (trainCourseDTO == null) {
                        C2364b.f11229a.error("GetSingleTrainCourseInfo failed");
                        this.f11222b.f11230b.m12025e();
                    } else {
                        this.f11222b.f11230b.m12019a(trainCourseDTO);
                    }
                    this.f11222b.f11230b.m12024d();
                }
            }, new Void[0]);
        }
    }

    /* renamed from: c */
    public void m12059c() {
        final int f = this.f11230b.m12026f();
        if (f > 0) {
            this.f11231c.getAsyncTaskQueue().m13740a(new AsyncTask<Integer, Void, TrainInfoDTO>(this) {
                /* renamed from: b */
                final /* synthetic */ C2364b f11224b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12047a((Integer[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12048a((TrainInfoDTO) obj);
                }

                protected void onPreExecute() {
                    this.f11224b.f11230b.m12023c();
                }

                /* renamed from: a */
                protected TrainInfoDTO m12047a(Integer... numArr) {
                    return this.f11224b.f11232d.m12006c(f);
                }

                /* renamed from: a */
                protected void m12048a(TrainInfoDTO trainInfoDTO) {
                    if (trainInfoDTO == null) {
                        this.f11224b.f11230b.m12027g();
                        C2364b.f11229a.error("GetLongTrainInfo failed");
                    } else {
                        this.f11224b.f11230b.m12020a(trainInfoDTO);
                    }
                    this.f11224b.f11230b.m12024d();
                }
            }, new Integer[0]);
        }
    }

    /* renamed from: a */
    public void m12057a(final int i) {
        if (i > 0) {
            this.f11231c.getAsyncTaskQueue().m13740a(new AsyncTask<Integer, Void, Boolean>(this) {
                /* renamed from: b */
                final /* synthetic */ C2364b f11226b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12049a((Integer[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12050a((Boolean) obj);
                }

                protected void onPreExecute() {
                    this.f11226b.f11230b.m12023c();
                }

                /* renamed from: a */
                protected Boolean m12049a(Integer... numArr) {
                    return Boolean.valueOf(this.f11226b.f11232d.m12007d(i));
                }

                /* renamed from: a */
                protected void m12050a(Boolean bool) {
                    if (bool == null || !bool.booleanValue()) {
                        C2364b.f11229a.error("Finish train failed!");
                        this.f11226b.f11230b.m12029i();
                    } else {
                        this.f11226b.f11230b.m12028h();
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            currentUser.setTrainingId(-1);
                            currentUser.setTrainingType(0);
                            AVUser.saveCurrentUser(currentUser);
                        }
                    }
                    this.f11226b.f11230b.m12024d();
                }
            }, new Integer[0]);
        }
    }

    /* renamed from: d */
    public void m12060d() {
        final int f = this.f11230b.m12026f();
        if (f > 0) {
            this.f11231c.getAsyncTaskQueue().m13740a(new AsyncTask<Integer, Void, TrainResultDTO>(this) {
                /* renamed from: b */
                final /* synthetic */ C2364b f11228b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m12051a((Integer[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m12052a((TrainResultDTO) obj);
                }

                /* renamed from: a */
                protected TrainResultDTO m12051a(Integer... numArr) {
                    return this.f11228b.f11232d.m12000a(f);
                }

                /* renamed from: a */
                protected void m12052a(TrainResultDTO trainResultDTO) {
                    this.f11228b.f11230b.m12021a(trainResultDTO);
                    if (trainResultDTO != null) {
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            currentUser.setTrainingId(trainResultDTO.getTrainId());
                            currentUser.setTrainingType(1);
                            AVUser.saveCurrentUser(currentUser);
                        }
                    }
                }
            }, new Integer[0]);
        }
    }
}
