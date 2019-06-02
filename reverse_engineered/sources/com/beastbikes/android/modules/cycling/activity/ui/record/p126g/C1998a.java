package com.beastbikes.android.modules.cycling.activity.ui.record.p126g;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.p119h.C1957a;
import com.beastbikes.android.modules.cycling.p111a.C1892g;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.p153c.C2408a;
import com.beastbikes.framework.android.p056e.C2796c;
import com.beastbikes.framework.business.BusinessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CyclingCompletedPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a */
public class C1998a {
    /* renamed from: A */
    private List<C2411a> f8971A;
    /* renamed from: B */
    private List<C2411a> f8972B;
    /* renamed from: a */
    private C1957a f8973a;
    /* renamed from: b */
    private CyclingCompletedActivity f8974b;
    /* renamed from: c */
    private C1398a f8975c;
    /* renamed from: d */
    private ActivityDTO f8976d;
    /* renamed from: e */
    private double f8977e = 0.0d;
    /* renamed from: f */
    private double f8978f;
    /* renamed from: g */
    private double f8979g;
    /* renamed from: h */
    private double f8980h;
    /* renamed from: i */
    private double f8981i;
    /* renamed from: j */
    private double f8982j;
    /* renamed from: k */
    private double f8983k;
    /* renamed from: l */
    private double f8984l;
    /* renamed from: m */
    private double f8985m;
    /* renamed from: n */
    private double f8986n;
    /* renamed from: o */
    private ArrayList<Double> f8987o = new ArrayList();
    /* renamed from: p */
    private ArrayList<Double> f8988p = new ArrayList();
    /* renamed from: q */
    private ArrayList<Double> f8989q = new ArrayList();
    /* renamed from: r */
    private ArrayList<Double> f8990r = new ArrayList();
    /* renamed from: s */
    private ArrayList<Double> f8991s = new ArrayList();
    /* renamed from: t */
    private ArrayList<Double> f8992t = new ArrayList();
    /* renamed from: u */
    private int f8993u = HttpStatus.SC_BAD_REQUEST;
    /* renamed from: v */
    private ProfileDTO f8994v;
    /* renamed from: w */
    private String f8995w;
    /* renamed from: x */
    private C2796c f8996x;
    /* renamed from: y */
    private String f8997y;
    /* renamed from: z */
    private ArrayList<C2411a> f8998z;

    /* compiled from: CyclingCompletedPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a$1 */
    class C19901 extends AsyncTask<String, Void, ProfileDTO> {
        /* renamed from: a */
        final /* synthetic */ C1998a f8957a;

        C19901(C1998a c1998a) {
            this.f8957a = c1998a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10239a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10240a((ProfileDTO) obj);
        }

        protected void onPreExecute() {
            this.f8957a.f8973a.mo3325g();
        }

        /* renamed from: a */
        protected ProfileDTO m10239a(String... strArr) {
            ProfileDTO b;
            BusinessException e;
            try {
                C2389c c2389c = new C2389c(this.f8957a.f8974b);
                b = c2389c.m12131b(this.f8957a.f8973a.mo3316c());
                if (b == null) {
                    try {
                        b = c2389c.m12136c(this.f8957a.f8973a.mo3316c());
                    } catch (BusinessException e2) {
                        e = e2;
                        e.printStackTrace();
                        return b;
                    }
                }
            } catch (BusinessException e3) {
                BusinessException businessException = e3;
                b = null;
                e = businessException;
                e.printStackTrace();
                return b;
            }
            return b;
        }

        /* renamed from: a */
        protected void m10240a(ProfileDTO profileDTO) {
            super.onPostExecute(profileDTO);
            if (profileDTO != null) {
                if (this.f8957a.f8976d != null) {
                    this.f8957a.f8976d.setAvatarUrl(profileDTO.getAvatar());
                    this.f8957a.f8976d.setNickname(profileDTO.getNickname());
                    this.f8957a.f8973a.mo3313b(this.f8957a.f8976d);
                }
                this.f8957a.f8994v = profileDTO;
            } else {
                this.f8957a.f8973a.mo3320d("");
            }
            this.f8957a.m10302a(this.f8957a.f8995w);
        }
    }

    /* compiled from: CyclingCompletedPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a$2 */
    class C19912 extends AsyncTask<String, Void, ActivityDTO> {
        /* renamed from: a */
        final /* synthetic */ C1998a f8958a;

        C19912(C1998a c1998a) {
            this.f8958a = c1998a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10241a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10242a((ActivityDTO) obj);
        }

        /* renamed from: a */
        protected ActivityDTO m10241a(String... strArr) {
            try {
                return this.f8958a.f8975c.a(this.f8958a.f8973a.mo3316c(), strArr[0]);
            } catch (Exception e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m10242a(ActivityDTO activityDTO) {
            boolean z = true;
            if (activityDTO == null) {
                this.f8958a.f8973a.mo3322e("");
                this.f8958a.f8973a.mo3326h();
                return;
            }
            this.f8958a.f8976d = activityDTO;
            this.f8958a.m10305b(this.f8958a.f8976d);
            this.f8958a.f8973a.mo3304a(this.f8958a.f8976d);
            C1957a a = this.f8958a.f8973a;
            ActivityDTO c = this.f8958a.f8976d;
            boolean z2 = AVUser.getCurrentUser() != null && TextUtils.equals(AVUser.getCurrentUser().getObjectId(), this.f8958a.f8973a.mo3316c());
            a.mo3308a(c, z2);
            int showRepair = activityDTO.getShowRepair();
            if (activityDTO.isFake() || activityDTO.isNuked() || !TextUtils.isEmpty(this.f8958a.f8976d.getCentralId())) {
                showRepair = 2;
            }
            C1998a c1998a = this.f8958a;
            String d = this.f8958a.f8995w;
            if (showRepair != 1) {
                z = false;
            }
            c1998a.m10258a(d, z, activityDTO.isRepair());
        }
    }

    /* compiled from: CyclingCompletedPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a$5 */
    class C19945 implements ErrorListener {
        /* renamed from: a */
        final /* synthetic */ C1998a f8965a;

        C19945(C1998a c1998a) {
            this.f8965a = c1998a;
        }

        public void onErrorResponse(VolleyError volleyError) {
            this.f8965a.f8973a.mo3309a("get elevation error", volleyError.getMessage());
            this.f8965a.m10259a(this.f8965a.f8990r);
        }
    }

    /* compiled from: CyclingCompletedPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a$6 */
    class C19956 implements C1821c {
        /* renamed from: a */
        final /* synthetic */ C1998a f8966a;

        C19956(C1998a c1998a) {
            this.f8966a = c1998a;
        }

        /* renamed from: a */
        public void mo3252a(C1856b c1856b) {
            if (c1856b != null) {
                this.f8966a.f8997y = c1856b.m9678d();
                this.f8966a.f8973a.mo3317c(this.f8966a.f8997y);
            }
        }

        /* renamed from: a */
        public void mo3251a(VolleyError volleyError) {
        }
    }

    /* compiled from: CyclingCompletedPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.a$a */
    private final class C1997a implements Comparator<C2411a> {
        /* renamed from: a */
        final /* synthetic */ C1998a f8970a;

        private C1997a(C1998a c1998a) {
            this.f8970a = c1998a;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m10249a((C2411a) obj, (C2411a) obj2);
        }

        /* renamed from: a */
        public int m10249a(C2411a c2411a, C2411a c2411a2) {
            if (c2411a.m12232h() < 1763194880 || c2411a.m12232h() == c2411a2.m12232h()) {
                return (int) (c2411a.m12233i() - c2411a2.m12233i());
            }
            return c2411a.m12232h() > c2411a2.m12232h() ? 1 : -1;
        }
    }

    public C1998a(CyclingCompletedActivity cyclingCompletedActivity, C1957a c1957a) {
        this.f8974b = cyclingCompletedActivity;
        this.f8973a = c1957a;
        this.f8995w = c1957a.mo3319d();
        this.f8996x = cyclingCompletedActivity.getRequestQueueFactory();
        this.f8975c = new C1398a(cyclingCompletedActivity);
    }

    /* renamed from: a */
    public void m10301a(ActivityDTO activityDTO) {
        if (activityDTO != null) {
            this.f8976d = activityDTO;
        }
        this.f8974b.getAsyncTaskQueue().m13740a(new C19901(this), new String[0]);
    }

    /* renamed from: a */
    public void m10302a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f8974b.getAsyncTaskQueue().m13740a(new C19912(this), str);
        }
    }

    /* renamed from: b */
    public void m10305b(ActivityDTO activityDTO) {
        boolean z = true;
        if (activityDTO != null) {
            if (TextUtils.isEmpty(this.f8995w)) {
                this.f8995w = activityDTO.getActivityIdentifier();
            }
            String e = this.f8973a.mo3321e();
            String f = this.f8973a.mo3323f();
            C1957a c1957a = this.f8973a;
            if (activityDTO.getIsPrivate() != 1) {
                z = false;
            }
            c1957a.mo3312a(z);
            activityDTO.setTotalDistance(activityDTO.getTotalDistance() / 1000.0d);
            activityDTO.setRepairDistance(activityDTO.getRepairDistance() / 1000.0d);
            activityDTO.setNickname(f);
            activityDTO.setAvatarUrl(e);
            if (TextUtils.isEmpty(activityDTO.getTitle()) || activityDTO.getTitle().equals("null") || TextUtils.isEmpty(activityDTO.getTitle().trim())) {
                activityDTO.setTitle(C2408a.m12216a(activityDTO.getStartTime()));
            }
            if ((activityDTO.getVelocity() <= 0.0d || Double.isNaN(activityDTO.getVelocity())) && activityDTO.getElapsedTime() != 0.0d) {
                activityDTO.setVelocity((activityDTO.getTotalDistance() / activityDTO.getElapsedTime()) * 3600.0d);
            }
            if (!C1849a.m9645b(this.f8974b)) {
                activityDTO.setTotalDistance(C1849a.m9638a(activityDTO.getTotalDistance()));
                activityDTO.setVelocity(C1849a.m9648d(activityDTO.getVelocity()));
                activityDTO.setMaxVelocity(C1849a.m9648d(activityDTO.getMaxVelocity()));
                activityDTO.setRiseTotal(C1849a.m9646c(activityDTO.getRiseTotal()));
                activityDTO.setMaxAltitude(C1849a.m9646c(activityDTO.getMaxAltitude()));
                activityDTO.setUphillDistance(C1849a.m9646c(activityDTO.getUphillDistance()));
                activityDTO.setRepairDistance(C1849a.m9638a(activityDTO.getRepairDistance()));
            }
        }
    }

    /* renamed from: a */
    private void m10258a(final String str, final boolean z, final boolean z2) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "";
            if (this.f8976d != null) {
                str2 = this.f8976d.getActivityId();
                this.f8977e = this.f8976d.getMaxVelocity();
            }
            this.f8974b.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, List<C2411a>>(this) {
                /* renamed from: d */
                final /* synthetic */ C1998a f8962d;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m10243a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m10244a((List) obj);
                }

                /* renamed from: a */
                protected List<C2411a> m10243a(String... strArr) {
                    try {
                        LocalActivity b = this.f8962d.f8975c.b(str);
                        boolean z = false;
                        if (!(b == null || b.isNewSamples() || !z2)) {
                            z = true;
                        }
                        List<C2411a> a = this.f8962d.f8975c.a(strArr[0], this.f8962d.f8973a.mo3316c(), strArr[1], z);
                        if (a == null || a.isEmpty()) {
                            return null;
                        }
                        Collections.sort(a, new C1997a());
                        this.f8962d.f8990r.clear();
                        this.f8962d.f8971A = new ArrayList();
                        this.f8962d.f8972B = new ArrayList();
                        this.f8962d.f8998z = new ArrayList();
                        this.f8962d.f8979g = this.f8962d.f8976d.getMaxAltitude();
                        this.f8962d.f8981i = this.f8962d.f8976d.getMaxCardiacRate();
                        this.f8962d.f8984l = this.f8962d.f8976d.getMaxCadence();
                        this.f8962d.f8986n = this.f8962d.f8976d.getMaxPower();
                        LatLng latLng = null;
                        for (C2411a c2411a : a) {
                            double e = c2411a.m12229e();
                            double f = c2411a.m12230f();
                            if (!(e == 0.0d || f == 0.0d || e == Double.MIN_VALUE || f == Double.MIN_VALUE)) {
                                if (!c2411a.m12239o()) {
                                    this.f8962d.f8979g = Math.max(this.f8962d.f8979g, c2411a.m12231g());
                                    if (c2411a.m12234j() > this.f8962d.f8977e) {
                                        c2411a.m12222a(this.f8962d.f8977e);
                                        this.f8962d.f8978f = c2411a.m12233i() / 1000.0d;
                                    }
                                    if (c2411a.m12231g() >= this.f8962d.f8979g) {
                                        this.f8962d.f8979g = c2411a.m12231g();
                                        this.f8962d.f8980h = c2411a.m12233i();
                                    }
                                    if (c2411a.m12236l() >= this.f8962d.f8981i) {
                                        c2411a.m12224b(this.f8962d.f8981i);
                                        this.f8962d.f8982j = c2411a.m12233i();
                                    }
                                    if (c2411a.m12237m() >= this.f8962d.f8984l) {
                                        c2411a.m12226c(this.f8962d.f8984l);
                                        this.f8962d.f8983k = c2411a.m12233i();
                                    }
                                    if (c2411a.m12240p() >= this.f8962d.f8986n) {
                                        c2411a.m12228d(this.f8962d.f8986n);
                                        this.f8962d.f8985m = c2411a.m12233i();
                                    }
                                    this.f8962d.f8972B.add(c2411a);
                                }
                                LatLng latLng2 = new LatLng(e, f);
                                if (latLng == null || DistanceUtil.getDistance(latLng, latLng2) <= 200.0d) {
                                    this.f8962d.f8971A.add(c2411a);
                                    latLng = latLng2;
                                } else {
                                    latLng = latLng2;
                                }
                            }
                        }
                        this.f8962d.f8976d.setMaxVelocity(this.f8962d.f8977e);
                        this.f8962d.f8976d.setMaxAltitude(this.f8962d.f8979g);
                        this.f8962d.f8998z.addAll(this.f8962d.f8972B);
                        if (this.f8962d.f8976d.getTotalDistance() > 50.0d) {
                            this.f8962d.f8972B = C1892g.m9769a(1.0E-4f, true, this.f8962d.f8972B);
                        }
                        if (this.f8962d.f8976d.getRepairDistance() > 50.0d) {
                            this.f8962d.f8971A = C1892g.m9769a(1.0E-4f, true, this.f8962d.f8971A);
                        }
                        if (this.f8962d.f8972B == null || this.f8962d.f8972B.isEmpty()) {
                            return null;
                        }
                        if (this.f8962d.f8972B.size() <= HttpStatus.SC_BAD_REQUEST) {
                            this.f8962d.m10260a(this.f8962d.f8972B);
                        } else {
                            this.f8962d.m10270c(this.f8962d.f8972B);
                            this.f8962d.m10266b(this.f8962d.f8972B);
                        }
                        return this.f8962d.f8972B;
                    } catch (BusinessException e2) {
                        this.f8962d.f8973a.mo3324f(e2.getMessage());
                        return null;
                    }
                }

                /* renamed from: a */
                protected void m10244a(List<C2411a> list) {
                    this.f8962d.f8973a.mo3326h();
                    if (list != null && !list.isEmpty()) {
                        int maxHeartRate;
                        this.f8962d.m10304a(z);
                        this.f8962d.f8973a.mo3307a(this.f8962d.f8976d, (List) list, this.f8962d.f8998z);
                        this.f8962d.f8973a.mo3310a(this.f8962d.f8987o);
                        this.f8962d.f8973a.mo3306a(this.f8962d.f8976d, this.f8962d.f8992t);
                        if (this.f8962d.f8994v != null) {
                            maxHeartRate = this.f8962d.f8994v.getMaxHeartRate();
                            String[] split = this.f8962d.f8994v.getBirthday().split(HelpFormatter.DEFAULT_OPT_PREFIX);
                            if (TextUtils.isDigitsOnly(split[0])) {
                                maxHeartRate = (int) (208.754d - (((double) (Calendar.getInstance().get(1) - Integer.parseInt(split[0]))) * 0.734d));
                            }
                        } else {
                            maxHeartRate = 0;
                        }
                        this.f8962d.f8973a.mo3305a(this.f8962d.f8976d, maxHeartRate, this.f8962d.f8988p);
                        this.f8962d.f8973a.mo3318c(this.f8962d.f8989q);
                        this.f8962d.m10274d((List) list);
                        this.f8962d.m10278e((List) list);
                    }
                }
            }, str2, str);
        }
    }

    /* renamed from: a */
    private void m10260a(List<C2411a> list) {
        for (C2411a c2411a : list) {
            this.f8987o.add(Double.valueOf(c2411a.m12234j()));
            this.f8988p.add(Double.valueOf(c2411a.m12236l()));
            this.f8989q.add(Double.valueOf(c2411a.m12237m()));
            this.f8992t.add(Double.valueOf(c2411a.m12240p()));
        }
        this.f8993u = list.size();
    }

    /* renamed from: b */
    private void m10266b(List<C2411a> list) {
        int i;
        int i2;
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(list);
        this.f8987o.add(Double.valueOf(0.0d));
        arrayList2.add(Double.valueOf(0.0d));
        for (i = 0; i < 5; i++) {
            C2411a c2411a = (C2411a) arrayList.remove(0);
            this.f8987o.add(Double.valueOf(c2411a.m12234j()));
            arrayList2.add(Double.valueOf(c2411a.m12233i() / 1000.0d));
        }
        int size = arrayList.size();
        i = 5;
        while (i > 0) {
            i2 = size - 1;
            c2411a = (C2411a) arrayList.remove(size - i);
            this.f8987o.add(Double.valueOf(c2411a.m12234j()));
            arrayList2.add(Double.valueOf(c2411a.m12233i() / 1000.0d));
            i--;
            size = i2;
        }
        int size2 = arrayList.size();
        int i3 = size2 / 387;
        int i4 = 0;
        int i5 = 6;
        while (i4 < 387) {
            i2 = i4 * i3;
            if (i2 >= size2) {
                double j = ((C2411a) arrayList.get(size2 - 1)).m12234j();
                if (j > this.f8976d.getMaxVelocity()) {
                    j = this.f8976d.getMaxVelocity();
                }
                this.f8987o.add(i5, Double.valueOf(j));
                arrayList2.add(i5, Double.valueOf(((C2411a) arrayList.get(size2 - 1)).m12233i() / 1000.0d));
                size = i5 + 1;
            } else {
                c2411a = (C2411a) arrayList.get(i2);
                i = (i4 + 1) * i3;
                if (i >= size2) {
                    i = size2 - 1;
                }
                C2411a c2411a2 = (C2411a) arrayList.get(i);
                long h = (c2411a2.m12232h() / 1000) - (c2411a.m12232h() / 1000);
                double d;
                if (h <= 0) {
                    double d2 = 0.0d;
                    while (i2 < (i4 + 1) * i3) {
                        d2 += ((C2411a) arrayList.get(i2)).m12234j();
                        i2++;
                    }
                    d = d2 / ((double) i3);
                    if (d > this.f8976d.getMaxVelocity()) {
                        d = this.f8976d.getMaxVelocity();
                    }
                    this.f8987o.add(i5, Double.valueOf(d));
                } else {
                    d = ((c2411a2.m12233i() - c2411a.m12233i()) / ((double) h)) * 3.6d;
                    if (d > this.f8976d.getMaxVelocity()) {
                        d = this.f8976d.getMaxVelocity();
                    }
                    this.f8987o.add(i5, Double.valueOf(d));
                }
                arrayList2.add(i5, Double.valueOf(c2411a.m12233i() / 1000.0d));
                size = i5 + 1;
            }
            i4++;
            i5 = size;
        }
        this.f8987o.add(Double.valueOf(0.0d));
        arrayList2.add(Double.valueOf(this.f8976d.getTotalDistance()));
        i2 = this.f8987o.size();
        i = 1;
        while (i < i2) {
            if (this.f8978f >= ((Double) arrayList2.get(i - 1)).doubleValue() && this.f8978f < ((Double) arrayList2.get(i)).doubleValue()) {
                this.f8987o.add(i, Double.valueOf(this.f8976d.getMaxVelocity()));
                arrayList2.add(i, Double.valueOf(this.f8978f));
                break;
            }
            i++;
        }
        this.f8993u = this.f8987o.size();
    }

    /* renamed from: c */
    private void m10270c(List<C2411a> list) {
        int i;
        int i2;
        int size = list.size();
        int i3 = size / HttpStatus.SC_BAD_REQUEST;
        for (int i4 = 0; i4 < HttpStatus.SC_BAD_REQUEST; i4++) {
            i = i4 * i3;
            if (i >= size) {
                this.f8991s.add(Double.valueOf(((C2411a) list.get(size - 1)).m12233i() / 1000.0d));
                if (((C2411a) list.get(size - 1)).m12236l() > this.f8981i) {
                    this.f8988p.add(Double.valueOf(this.f8981i));
                } else {
                    this.f8988p.add(Double.valueOf(((C2411a) list.get(size - 1)).m12236l()));
                }
                this.f8989q.add(Double.valueOf(((C2411a) list.get(size - 1)).m12237m()));
                this.f8992t.add(Double.valueOf(((C2411a) list.get(size - 1)).m12240p()));
            } else {
                C2411a c2411a = (C2411a) list.get(i);
                double d = 0.0d;
                double d2 = 0.0d;
                double d3 = 0.0d;
                for (i2 = i; i2 < (i4 + 1) * i3; i2++) {
                    d += ((C2411a) list.get(i2)).m12236l();
                    d2 += ((C2411a) list.get(i2)).m12237m();
                    d3 += ((C2411a) list.get(i2)).m12240p();
                }
                if (d / ((double) i3) > this.f8981i) {
                    this.f8988p.add(Double.valueOf(this.f8981i));
                } else {
                    this.f8988p.add(Double.valueOf(d / ((double) i3)));
                }
                if (d2 / ((double) i3) > this.f8984l) {
                    this.f8989q.add(Double.valueOf(this.f8984l));
                } else {
                    this.f8989q.add(Double.valueOf(d2 / ((double) i3)));
                }
                if (d3 / ((double) i3) > this.f8986n) {
                    this.f8992t.add(Double.valueOf(this.f8986n));
                } else {
                    this.f8992t.add(Double.valueOf(d3 / ((double) i3)));
                }
                if (i4 == 0) {
                    this.f8991s.add(Double.valueOf(0.0d));
                } else if (i4 == 399) {
                    this.f8991s.add(Double.valueOf(this.f8976d.getTotalDistance()));
                } else {
                    this.f8991s.add(Double.valueOf(c2411a.m12233i() / 1000.0d));
                }
            }
        }
        i2 = this.f8988p.size();
        i = 1;
        while (i < i2) {
            if (this.f8982j >= ((Double) this.f8991s.get(i - 1)).doubleValue() && this.f8982j < ((Double) this.f8991s.get(i)).doubleValue()) {
                this.f8988p.remove(i - 1);
                this.f8988p.add(i - 1, Double.valueOf(this.f8981i));
                this.f8991s.remove(i - 1);
                this.f8991s.add(i - 1, Double.valueOf(this.f8982j));
                break;
            }
            i++;
        }
        i2 = this.f8989q.size();
        i = 1;
        while (i < i2) {
            if (this.f8983k >= ((Double) this.f8991s.get(i - 1)).doubleValue() && this.f8983k < ((Double) this.f8991s.get(i)).doubleValue()) {
                this.f8989q.remove(i - 1);
                this.f8989q.add(i - 1, Double.valueOf(this.f8984l));
                this.f8991s.remove(i - 1);
                this.f8991s.add(i - 1, Double.valueOf(this.f8983k));
                break;
            }
            i++;
        }
        i2 = this.f8992t.size();
        i = 1;
        while (i < i2) {
            if (this.f8985m < ((Double) this.f8991s.get(i - 1)).doubleValue() || this.f8985m >= ((Double) this.f8991s.get(i)).doubleValue()) {
                i++;
            } else {
                this.f8992t.remove(i - 1);
                this.f8992t.add(i - 1, Double.valueOf(this.f8986n));
                this.f8991s.remove(i - 1);
                this.f8991s.add(i - 1, Double.valueOf(this.f8985m));
                return;
            }
        }
    }

    /* renamed from: d */
    private void m10274d(List<C2411a> list) {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        C2411a c2411a;
        double f;
        if (size <= 200) {
            for (C2411a c2411a2 : list) {
                double e = c2411a2.m12229e();
                f = c2411a2.m12230f();
                if (e <= 90.0d && e >= -90.0d && f <= 180.0d && f >= -180.0d) {
                    this.f8990r.add(Double.valueOf(c2411a2.m12231g()));
                    stringBuilder.append(e).append(",").append(f).append('|');
                }
            }
        } else {
            int i2 = size / 200;
            for (int i3 = 0; i3 < 200; i3++) {
                i = i3 * i2;
                double f2;
                if (i >= size) {
                    c2411a2 = (C2411a) list.get(size - 1);
                    f = c2411a2.m12229e();
                    f2 = c2411a2.m12230f();
                    if (f <= 90.0d && f >= -90.0d && f2 <= 180.0d && f2 >= -180.0d) {
                        this.f8990r.add(Double.valueOf(c2411a2.m12231g()));
                        stringBuilder.append(f).append(",").append(f2).append('|');
                    }
                } else {
                    c2411a2 = (C2411a) list.get(i);
                    f = c2411a2.m12229e();
                    f2 = c2411a2.m12230f();
                    if (f <= 90.0d && f >= -90.0d && f2 <= 180.0d && f2 >= -180.0d) {
                        this.f8990r.add(Double.valueOf(c2411a2.m12231g()));
                        stringBuilder.append(f).append(",").append(f2).append('|');
                    }
                }
            }
        }
        if (stringBuilder.length() <= 0) {
            ArrayList arrayList = new ArrayList();
            for (i = 0; i < this.f8993u; i++) {
                arrayList.add(Double.valueOf(0.0d));
            }
            this.f8973a.mo3315b(arrayList);
            return;
        }
        m10265b(stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    /* renamed from: b */
    private void m10265b(String str) {
        if (!TextUtils.isEmpty(str)) {
            final ArrayList arrayList = new ArrayList();
            boolean a = C1849a.m9641a();
            StringBuilder stringBuilder = new StringBuilder(a ? "http://maps.google.cn/maps/api/elevation/json?path=" : "https://maps.googleapis.com/maps/api/elevation/json?path=");
            stringBuilder.append(str).append("&samples=").append(this.f8993u);
            if (!a) {
                stringBuilder.append("&key=AIzaSyC6rtQRpblQN3RWVE3OCK_c8q4QhWVSnfg");
            }
            this.f8996x.m13745a(new JsonObjectRequest(stringBuilder.toString(), null, new Listener<JSONObject>(this) {
                /* renamed from: b */
                final /* synthetic */ C1998a f8964b;

                public /* synthetic */ void onResponse(Object obj) {
                    m10245a((JSONObject) obj);
                }

                /* renamed from: a */
                public void m10245a(JSONObject jSONObject) {
                    if ("OK".equals(jSONObject.optString("status"))) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("results");
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            try {
                                arrayList.add(Double.valueOf(((JSONObject) optJSONArray.get(i)).optDouble("elevation")));
                            } catch (JSONException e) {
                                this.f8964b.f8973a.mo3309a("get elevation error", e.getMessage());
                            }
                        }
                        this.f8964b.f8976d.setMaxAltitude(((Double) Collections.max(arrayList)).doubleValue());
                        this.f8964b.f8973a.mo3315b(arrayList);
                    } else if ("INVALID_REQUEST".equals(jSONObject.optString("status"))) {
                        this.f8964b.f8973a.mo3309a("get elevation error", this.f8964b.f8974b.getString(C1373R.string.route_elevation_activity_error));
                        this.f8964b.m10259a(this.f8964b.f8990r);
                    } else {
                        this.f8964b.f8973a.mo3309a("get elevation error", this.f8964b.f8974b.getString(C1373R.string.route_elevation_activity_error));
                        this.f8964b.m10259a(this.f8964b.f8990r);
                    }
                }
            }, new C19945(this)), this.f8974b);
        }
    }

    /* renamed from: a */
    private void m10259a(ArrayList<Double> arrayList) {
        int size = arrayList.size();
        if (size <= this.f8993u) {
            this.f8973a.mo3315b((ArrayList) arrayList);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        int i = size / this.f8993u;
        for (int i2 = 0; i2 < this.f8993u; i2++) {
            int i3 = i2 * i;
            if (i3 >= size) {
                arrayList2.add(arrayList.get(size - 1));
            } else {
                arrayList2.add(arrayList.get(i3));
            }
        }
        this.f8973a.mo3315b(arrayList2);
    }

    /* renamed from: e */
    private void m10278e(List<C2411a> list) {
        if (list != null && !list.isEmpty() && list.size() > 0) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null && this.f8973a.mo3316c().equals(currentUser.getObjectId())) {
                C2411a c2411a = (C2411a) list.get(0);
                new C1855a().m9671a(this.f8974b, this.f8996x, c2411a.m12229e(), c2411a.m12230f(), new C19956(this));
            }
        }
    }

    /* renamed from: a */
    public void m10304a(boolean z) {
        if (z) {
            if (this.f8971A != null && this.f8971A.size() > 2) {
                this.f8973a.mo3311a(this.f8971A);
            }
        } else if (this.f8972B != null && this.f8972B.size() > 2) {
            this.f8973a.mo3311a(this.f8972B);
        }
    }

    /* renamed from: a */
    public List<C2411a> m10300a() {
        if (this.f8976d == null) {
            return null;
        }
        return this.f8976d.getShowRepair() == 1 ? this.f8971A : this.f8972B;
    }

    /* renamed from: a */
    public void m10303a(final String str, final boolean z) {
        new AsyncTask<String, Void, Void>(this) {
            /* renamed from: c */
            final /* synthetic */ C1998a f8969c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10248a((String[]) objArr);
            }

            /* renamed from: a */
            protected Void m10248a(String... strArr) {
                this.f8969c.f8975c.b(str, z ? 1 : 0);
                return null;
            }
        }.execute(new String[0]);
    }
}
