package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.j256.ormlite.field.FieldType;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C4406s;
import com.tencent.bugly.proguard.C4446a;
import com.tencent.bugly.proguard.C4447j;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4471t;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import com.tencent.bugly.proguard.al;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.biz.a */
public final class C4412a {
    /* renamed from: a */
    private Context f15237a;
    /* renamed from: b */
    private long f15238b;
    /* renamed from: c */
    private int f15239c;
    /* renamed from: d */
    private boolean f15240d = true;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$2 */
    class C44082 implements Runnable {
        /* renamed from: a */
        private /* synthetic */ C4412a f15230a;

        C44082(C4412a c4412a) {
            this.f15230a = c4412a;
        }

        public final void run() {
            try {
                this.f15230a.m17266c();
            } catch (Throwable th) {
                C4475w.m17748a(th);
            }
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$a */
    class C4409a implements Runnable {
        /* renamed from: a */
        private boolean f15231a;
        /* renamed from: b */
        private UserInfoBean f15232b;
        /* renamed from: c */
        private /* synthetic */ C4412a f15233c;

        public C4409a(C4412a c4412a, UserInfoBean userInfoBean, boolean z) {
            this.f15233c = c4412a;
            this.f15232b = userInfoBean;
            this.f15231a = z;
        }

        public final void run() {
            try {
                if (this.f15232b != null) {
                    UserInfoBean userInfoBean = this.f15232b;
                    if (userInfoBean != null) {
                        C4417a b = C4417a.m17304b();
                        if (b != null) {
                            userInfoBean.f15218j = b.m17333e();
                        }
                    }
                    C4475w.m17751c("[UserInfo] Record user info.", new Object[0]);
                    C4412a.m17263a(this.f15233c, this.f15232b, false);
                }
                if (this.f15231a) {
                    C4412a c4412a = this.f15233c;
                    C4474v a = C4474v.m17740a();
                    if (a != null) {
                        a.m17741a(new C44082(c4412a));
                    }
                }
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$b */
    class C4410b implements Runnable {
        /* renamed from: a */
        private /* synthetic */ C4412a f15234a;

        C4410b(C4412a c4412a) {
            this.f15234a = c4412a;
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.f15234a.f15238b) {
                C4474v.m17740a().m17742a(new C4410b(this.f15234a), (this.f15234a.f15238b - currentTimeMillis) + 5000);
                return;
            }
            this.f15234a.m17269a(3, false, 0);
            this.f15234a.m17268a();
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$c */
    class C4411c implements Runnable {
        /* renamed from: a */
        private long f15235a = 21600000;
        /* renamed from: b */
        private /* synthetic */ C4412a f15236b;

        public C4411c(C4412a c4412a, long j) {
            this.f15236b = c4412a;
            this.f15235a = j;
        }

        public final void run() {
            C4412a c4412a = this.f15236b;
            C4474v a = C4474v.m17740a();
            if (a != null) {
                a.m17741a(new C44082(c4412a));
            }
            c4412a = this.f15236b;
            long j = this.f15235a;
            C4474v.m17740a().m17742a(new C4411c(c4412a, j), j);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m17263a(C4412a c4412a, UserInfoBean userInfoBean, boolean z) {
        if (userInfoBean != null) {
            if (!(z || userInfoBean.f15210b == 1)) {
                List a = c4412a.m17267a(C4417a.m17303a(c4412a.f15237a).f15289d);
                if (a != null && a.size() >= 20) {
                    C4475w.m17747a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a.size()));
                    return;
                }
            }
            long a2 = C4464o.m17672a().m17687a("t_ui", C4412a.m17260a(userInfoBean), null, true);
            if (a2 >= 0) {
                C4475w.m17751c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a2));
                userInfoBean.f15209a = a2;
            }
        }
    }

    public C4412a(Context context, boolean z) {
        this.f15237a = context;
        this.f15240d = z;
    }

    /* renamed from: a */
    public final void m17269a(int i, boolean z, long j) {
        int i2 = 1;
        C4421a a = C4421a.m17388a();
        if (a == null || a.m17396c().f15321h || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.f15239c++;
            }
            C4417a a2 = C4417a.m17303a(this.f15237a);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.f15210b = i;
            userInfoBean.f15211c = a2.f15289d;
            userInfoBean.f15212d = a2.m17337g();
            userInfoBean.f15213e = System.currentTimeMillis();
            userInfoBean.f15214f = -1;
            userInfoBean.f15222n = a2.f15295j;
            if (i != 1) {
                i2 = 0;
            }
            userInfoBean.f15223o = i2;
            userInfoBean.f15220l = a2.m17325a();
            userInfoBean.f15221m = a2.f15301p;
            userInfoBean.f15215g = a2.f15302q;
            userInfoBean.f15216h = a2.f15303r;
            userInfoBean.f15217i = a2.f15304s;
            userInfoBean.f15219k = a2.f15305t;
            userInfoBean.f15226r = a2.m17357z();
            userInfoBean.f15227s = a2.m17310E();
            userInfoBean.f15224p = a2.m17311F();
            userInfoBean.f15225q = a2.m17312G();
            C4474v.m17740a().m17742a(new C4409a(this, userInfoBean, z), 0);
            return;
        }
        C4475w.m17753e("UserInfo is disable", new Object[0]);
    }

    /* renamed from: a */
    public final void m17268a() {
        this.f15238b = C4479y.m17800b() + 86400000;
        C4474v.m17740a().m17742a(new C4410b(this), (this.f15238b - System.currentTimeMillis()) + 5000);
    }

    /* renamed from: c */
    private synchronized void m17266c() {
        boolean z = false;
        synchronized (this) {
            if (this.f15240d) {
                C4471t a = C4471t.m17703a();
                if (a != null) {
                    C4421a a2 = C4421a.m17388a();
                    if (a2 != null && (!a2.m17395b() || a.m17733b(1001))) {
                        boolean z2;
                        List list;
                        String str = C4417a.m17303a(this.f15237a).f15289d;
                        List arrayList = new ArrayList();
                        List a3 = m17267a(str);
                        if (a3 != null) {
                            int i;
                            UserInfoBean userInfoBean;
                            int i2;
                            int size = a3.size() - 20;
                            if (size > 0) {
                                for (int i3 = 0; i3 < a3.size() - 1; i3++) {
                                    for (i = i3 + 1; i < a3.size(); i++) {
                                        if (((UserInfoBean) a3.get(i3)).f15213e > ((UserInfoBean) a3.get(i)).f15213e) {
                                            userInfoBean = (UserInfoBean) a3.get(i3);
                                            a3.set(i3, a3.get(i));
                                            a3.set(i, userInfoBean);
                                        }
                                    }
                                }
                                for (i2 = 0; i2 < size; i2++) {
                                    arrayList.add(a3.get(i2));
                                }
                            }
                            Iterator it = a3.iterator();
                            i = 0;
                            while (it.hasNext()) {
                                userInfoBean = (UserInfoBean) it.next();
                                if (userInfoBean.f15214f != -1) {
                                    it.remove();
                                    if (userInfoBean.f15213e < C4479y.m17800b()) {
                                        arrayList.add(userInfoBean);
                                    }
                                }
                                if (userInfoBean.f15213e <= System.currentTimeMillis() - 600000 || !(userInfoBean.f15210b == 1 || userInfoBean.f15210b == 4 || userInfoBean.f15210b == 3)) {
                                    i2 = i;
                                } else {
                                    i2 = i + 1;
                                }
                                i = i2;
                            }
                            if (i > 15) {
                                C4475w.m17752d("[UserInfo] Upload user info too many times in 10 min: %d", Integer.valueOf(i));
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            list = a3;
                        } else {
                            Object arrayList2 = new ArrayList();
                            z2 = true;
                        }
                        if (arrayList.size() > 0) {
                            C4412a.m17264a(arrayList);
                        }
                        if (!z2 || list.size() == 0) {
                            C4475w.m17751c("[UserInfo] There is no user info in local database.", new Object[0]);
                        } else {
                            C4475w.m17751c("[UserInfo] Upload user info(size: %d)", Integer.valueOf(list.size()));
                            C4447j a4 = C4446a.m17526a(list, this.f15239c == 1 ? 1 : 2);
                            if (a4 == null) {
                                C4475w.m17752d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                            } else {
                                byte[] a5 = C4446a.m17531a(a4);
                                if (a5 == null) {
                                    C4475w.m17752d("[UserInfo] Failed to encode data.", new Object[0]);
                                } else {
                                    al a6 = C4446a.m17523a(this.f15237a, a.f15734a ? 840 : 640, a5);
                                    if (a6 == null) {
                                        C4475w.m17752d("[UserInfo] Request package is null.", new Object[0]);
                                    } else {
                                        C4406s c44071 = new C4406s(this) {
                                            /* renamed from: b */
                                            private /* synthetic */ C4412a f15229b;

                                            /* renamed from: a */
                                            public final void mo6056a(boolean z) {
                                                if (z) {
                                                    C4475w.m17751c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                                                    long currentTimeMillis = System.currentTimeMillis();
                                                    for (UserInfoBean userInfoBean : list) {
                                                        userInfoBean.f15214f = currentTimeMillis;
                                                        C4412a.m17263a(this.f15229b, userInfoBean, true);
                                                    }
                                                }
                                            }
                                        };
                                        StrategyBean c = C4421a.m17388a().m17396c();
                                        String str2 = a.f15734a ? c.f15331r : c.f15333t;
                                        String str3 = a.f15734a ? StrategyBean.f15315b : StrategyBean.f15314a;
                                        C4471t a7 = C4471t.m17703a();
                                        if (this.f15239c == 1) {
                                            z = true;
                                        }
                                        a7.m17726a(1001, a6, str2, str3, c44071, z);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final void m17270b() {
        C4474v a = C4474v.m17740a();
        if (a != null) {
            a.m17741a(new C44082(this));
        }
    }

    /* renamed from: a */
    public final List<UserInfoBean> m17267a(String str) {
        Throwable th;
        Cursor cursor;
        Cursor a;
        try {
            a = C4464o.m17672a().m17688a("t_ui", null, C4479y.m17792a(str) ? null : "_pc = '" + str + "'", null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                List<UserInfoBean> arrayList = new ArrayList();
                while (a.moveToNext()) {
                    UserInfoBean a2 = C4412a.m17261a(a);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        try {
                            stringBuilder.append(" or _id").append(" = ").append(a.getLong(a.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX)));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    int a3 = C4464o.m17672a().m17686a("t_ui", stringBuilder2.substring(4), null, null, true);
                    C4475w.m17752d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(a3));
                }
                if (a != null) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static void m17264a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                stringBuilder.append(" or _id").append(" = ").append(((UserInfoBean) list.get(i)).f15209a);
                i++;
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() > 0) {
                stringBuilder2 = stringBuilder2.substring(4);
            }
            stringBuilder.setLength(0);
            try {
                int a = C4464o.m17672a().m17686a("t_ui", stringBuilder2, null, null, true);
                C4475w.m17751c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(a));
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static ContentValues m17260a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f15209a > 0) {
                contentValues.put(FieldType.FOREIGN_ID_FIELD_SUFFIX, Long.valueOf(userInfoBean.f15209a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f15213e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f15214f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f15210b));
            contentValues.put("_pc", userInfoBean.f15211c);
            contentValues.put("_dt", C4479y.m17795a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static UserInfoBean m17261a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            UserInfoBean userInfoBean = (UserInfoBean) C4479y.m17776a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.f15209a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
