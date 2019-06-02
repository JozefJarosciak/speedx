package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.j256.ormlite.field.FieldType;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C4406s;
import com.tencent.bugly.proguard.C4446a;
import com.tencent.bugly.proguard.C4447j;
import com.tencent.bugly.proguard.C4462n;
import com.tencent.bugly.proguard.C4464o;
import com.tencent.bugly.proguard.C4466q;
import com.tencent.bugly.proguard.C4471t;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.ai;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.b */
public final class C4433b {
    /* renamed from: a */
    private static int f15435a = 0;
    /* renamed from: b */
    private Context f15436b;
    /* renamed from: c */
    private C4471t f15437c;
    /* renamed from: d */
    private C4464o f15438d;
    /* renamed from: e */
    private C4421a f15439e;
    /* renamed from: f */
    private C4462n f15440f;
    /* renamed from: g */
    private BuglyStrategy$a f15441g;

    public C4433b(int i, Context context, C4471t c4471t, C4464o c4464o, C4421a c4421a, BuglyStrategy$a buglyStrategy$a, C4462n c4462n) {
        f15435a = i;
        this.f15436b = context;
        this.f15437c = c4471t;
        this.f15438d = c4464o;
        this.f15439e = c4421a;
        this.f15441g = buglyStrategy$a;
        this.f15440f = c4462n;
    }

    /* renamed from: a */
    private static List<C4423a> m17428a(List<C4423a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<C4423a> arrayList = new ArrayList();
        for (C4423a c4423a : list) {
            if (c4423a.f15403d && c4423a.f15401b <= currentTimeMillis - 86400000) {
                arrayList.add(c4423a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private CrashDetailBean m17425a(List<C4423a> list, CrashDetailBean crashDetailBean) {
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3 = null;
        List arrayList = new ArrayList(10);
        for (C4423a c4423a : list) {
            if (c4423a.f15404e) {
                arrayList.add(c4423a);
            }
        }
        if (arrayList.size() > 0) {
            List b = m17433b(arrayList);
            if (b != null && b.size() > 0) {
                Collections.sort(b);
                int i = 0;
                while (i < b.size()) {
                    crashDetailBean2 = (CrashDetailBean) b.get(i);
                    if (i != 0) {
                        if (crashDetailBean2.f15392s != null) {
                            String[] split = crashDetailBean2.f15392s.split("\n");
                            if (split != null) {
                                for (String str : split) {
                                    if (!crashDetailBean3.f15392s.contains(str)) {
                                        crashDetailBean3.f15393t++;
                                        crashDetailBean3.f15392s += str + "\n";
                                    }
                                }
                            }
                        }
                        crashDetailBean2 = crashDetailBean3;
                    }
                    i++;
                    crashDetailBean3 = crashDetailBean2;
                }
                crashDetailBean2 = crashDetailBean3;
                if (crashDetailBean2 != null) {
                    crashDetailBean.f15383j = true;
                    crashDetailBean.f15393t = 0;
                    crashDetailBean.f15392s = "";
                    crashDetailBean3 = crashDetailBean;
                } else {
                    crashDetailBean3 = crashDetailBean2;
                }
                for (C4423a c4423a2 : list) {
                    if (!(c4423a2.f15404e || c4423a2.f15403d || crashDetailBean3.f15392s.contains(c4423a2.f15401b))) {
                        crashDetailBean3.f15393t++;
                        crashDetailBean3.f15392s += c4423a2.f15401b + "\n";
                    }
                }
                if (crashDetailBean3.f15391r == crashDetailBean.f15391r && !crashDetailBean3.f15392s.contains(crashDetailBean.f15391r)) {
                    crashDetailBean3.f15393t++;
                    crashDetailBean3.f15392s += crashDetailBean.f15391r + "\n";
                    return crashDetailBean3;
                }
            }
        }
        crashDetailBean2 = null;
        if (crashDetailBean2 != null) {
            crashDetailBean3 = crashDetailBean2;
        } else {
            crashDetailBean.f15383j = true;
            crashDetailBean.f15393t = 0;
            crashDetailBean.f15392s = "";
            crashDetailBean3 = crashDetailBean;
        }
        for (C4423a c4423a22 : list) {
            crashDetailBean3.f15393t++;
            crashDetailBean3.f15392s += c4423a22.f15401b + "\n";
        }
        return crashDetailBean3.f15391r == crashDetailBean.f15391r ? crashDetailBean3 : crashDetailBean3;
    }

    /* renamed from: a */
    public final boolean m17440a(CrashDetailBean crashDetailBean) {
        return m17441a(crashDetailBean, -123456789);
    }

    /* renamed from: a */
    public final boolean m17441a(CrashDetailBean crashDetailBean, int i) {
        if (crashDetailBean == null) {
            return true;
        }
        if (!(C4436c.f15461l == null || C4436c.f15461l.isEmpty())) {
            C4475w.m17751c("Crash filter for crash stack is: %s", C4436c.f15461l);
            if (crashDetailBean.f15390q.contains(C4436c.f15461l)) {
                C4475w.m17752d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (!(C4436c.f15462m == null || C4436c.f15462m.isEmpty())) {
            C4475w.m17751c("Crash regular filter for crash stack is: %s", C4436c.f15462m);
            if (Pattern.compile(C4436c.f15462m).matcher(crashDetailBean.f15390q).find()) {
                C4475w.m17752d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        int i2 = crashDetailBean.f15375b;
        String str = crashDetailBean.f15387n;
        str = crashDetailBean.f15389p;
        str = crashDetailBean.f15390q;
        long j = crashDetailBean.f15391r;
        str = crashDetailBean.f15386m;
        str = crashDetailBean.f15378e;
        str = crashDetailBean.f15376c;
        if (this.f15440f != null) {
            C4462n c4462n = this.f15440f;
            String str2 = crashDetailBean.f15399z;
            if (!c4462n.m17663c()) {
                return true;
            }
        }
        if (crashDetailBean.f15375b != 2) {
            C4466q c4466q = new C4466q();
            c4466q.f15716b = 1;
            c4466q.f15717c = crashDetailBean.f15399z;
            c4466q.f15718d = crashDetailBean.f15352A;
            c4466q.f15719e = crashDetailBean.f15391r;
            this.f15438d.m17695b(1);
            this.f15438d.m17694a(c4466q);
            C4475w.m17749b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            C4475w.m17749b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<C4423a> b = m17432b();
        List list = null;
        if (b != null && b.size() > 0) {
            List arrayList = new ArrayList(10);
            List<C4423a> arrayList2 = new ArrayList(10);
            arrayList.addAll(C4433b.m17428a((List) b));
            b.removeAll(arrayList);
            if (!C4402b.f15204c && C4436c.f15452c) {
                int i3 = 0;
                for (C4423a c4423a : b) {
                    if (crashDetailBean.f15394u.equals(c4423a.f15402c)) {
                        if (c4423a.f15404e) {
                            i3 = true;
                        }
                        arrayList2.add(c4423a);
                    }
                    i3 = i3;
                }
                if (i3 != 0 || arrayList2.size() >= 2) {
                    C4475w.m17747a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a = m17425a((List) arrayList2, crashDetailBean);
                    for (C4423a c4423a2 : arrayList2) {
                        if (c4423a2.f15400a != a.f15374a) {
                            arrayList.add(c4423a2);
                        }
                    }
                    m17443c(a);
                    C4433b.m17434c(arrayList);
                    C4475w.m17749b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        m17443c(crashDetailBean);
        if (!(list == null || list.isEmpty())) {
            C4433b.m17434c(list);
        }
        C4475w.m17749b("[crash] save crash success", new Object[0]);
        return false;
    }

    /* renamed from: a */
    public final List<CrashDetailBean> m17437a() {
        StrategyBean c = C4421a.m17388a().m17396c();
        if (c == null) {
            C4475w.m17752d("have not synced remote!", new Object[0]);
            return null;
        } else if (c.f15320g) {
            long currentTimeMillis = System.currentTimeMillis();
            long b = C4479y.m17800b();
            List b2 = m17432b();
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            List arrayList = new ArrayList();
            Iterator it = b2.iterator();
            while (it.hasNext()) {
                C4423a c4423a = (C4423a) it.next();
                if (c4423a.f15401b < b - C4436c.f15455f) {
                    it.remove();
                    arrayList.add(c4423a);
                } else if (c4423a.f15403d) {
                    if (c4423a.f15401b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!c4423a.f15404e) {
                        it.remove();
                        arrayList.add(c4423a);
                    }
                } else if (((long) c4423a.f15405f) >= 3 && c4423a.f15401b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(c4423a);
                }
            }
            if (arrayList.size() > 0) {
                C4433b.m17434c(arrayList);
            }
            List arrayList2 = new ArrayList();
            List<CrashDetailBean> b3 = m17433b(b2);
            if (b3 != null && b3.size() > 0) {
                String str = C4417a.m17304b().f15295j;
                Iterator it2 = b3.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!str.equals(crashDetailBean.f15379f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                C4433b.m17436d(arrayList2);
            }
            return b3;
        } else {
            C4475w.m17752d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C4475w.m17749b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public final void m17438a(CrashDetailBean crashDetailBean, long j, boolean z) {
        boolean z2 = false;
        if (C4436c.f15460k) {
            C4475w.m17747a("try to upload right now", new Object[0]);
            List arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            if (crashDetailBean.f15375b == 7) {
                z2 = true;
            }
            m17439a(arrayList, 3000, z, z2, z);
            if (this.f15440f != null) {
                C4462n c4462n = this.f15440f;
                int i = crashDetailBean.f15375b;
            }
        }
    }

    /* renamed from: a */
    public final void m17439a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        if (!C4417a.m17303a(this.f15436b).f15290e || this.f15437c == null) {
            return;
        }
        if (z3 || this.f15437c.m17733b(C4436c.f15450a)) {
            StrategyBean c = this.f15439e.m17396c();
            if (!c.f15320g) {
                C4475w.m17752d("remote report is disable!", new Object[0]);
                C4475w.m17749b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            } else if (list != null && list.size() != 0) {
                try {
                    C4447j c4447j;
                    String str = this.f15437c.f15734a ? c.f15332s : c.f15333t;
                    String str2 = this.f15437c.f15734a ? StrategyBean.f15316c : StrategyBean.f15314a;
                    int i = this.f15437c.f15734a ? 830 : 630;
                    Context context = this.f15436b;
                    C4417a b = C4417a.m17304b();
                    if (context == null || list == null || list.size() == 0 || b == null) {
                        C4475w.m17752d("enEXPPkg args == null!", new Object[0]);
                        c4447j = null;
                    } else {
                        C4447j akVar = new ak();
                        akVar.f15577a = new ArrayList();
                        for (CrashDetailBean a : list) {
                            akVar.f15577a.add(C4433b.m17427a(context, a, b));
                        }
                        c4447j = akVar;
                    }
                    if (c4447j == null) {
                        C4475w.m17752d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] a2 = C4446a.m17531a(c4447j);
                    if (a2 == null) {
                        C4475w.m17752d("send encode fail!", new Object[0]);
                        return;
                    }
                    al a3 = C4446a.m17523a(this.f15436b, i, a2);
                    if (a3 == null) {
                        C4475w.m17752d("request package is null.", new Object[0]);
                        return;
                    }
                    C4406s c44321 = new C4406s(this) {
                        /* renamed from: b */
                        private /* synthetic */ C4433b f15434b;

                        /* renamed from: a */
                        public final void mo6056a(boolean z) {
                            C4433b c4433b = this.f15434b;
                            C4433b.m17430a(z, list);
                        }
                    };
                    if (z) {
                        this.f15437c.m17725a(f15435a, a3, str, str2, c44321, j, z2);
                    } else {
                        this.f15437c.m17726a(f15435a, a3, str, str2, c44321, false);
                    }
                } catch (Throwable th) {
                    C4475w.m17753e("req cr error %s", th.toString());
                    if (!C4475w.m17750b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m17430a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C4475w.m17751c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                C4475w.m17751c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f15376c, Integer.valueOf(crashDetailBean.f15385l), Boolean.valueOf(crashDetailBean.f15377d), Boolean.valueOf(crashDetailBean.f15383j));
                crashDetailBean.f15385l++;
                crashDetailBean.f15377d = z;
                C4475w.m17751c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f15376c, Integer.valueOf(crashDetailBean.f15385l), Boolean.valueOf(crashDetailBean.f15377d), Boolean.valueOf(crashDetailBean.f15383j));
            }
            for (CrashDetailBean crashDetailBean2 : list) {
                C4436c.m17444a().m17450a(crashDetailBean2);
            }
            C4475w.m17751c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            C4475w.m17749b("[crash] upload fail.", new Object[0]);
        }
    }

    /* renamed from: b */
    public final void m17442b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            if (this.f15441g != null || this.f15440f != null) {
                try {
                    int i;
                    String b;
                    C4475w.m17747a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.f15375b) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 1;
                            break;
                        case 3:
                            i = 4;
                            break;
                        case 4:
                            i = 3;
                            break;
                        case 5:
                            i = 5;
                            break;
                        case 6:
                            i = 6;
                            break;
                        case 7:
                            i = 7;
                            break;
                        default:
                            return;
                    }
                    int i2 = crashDetailBean.f15375b;
                    String str = crashDetailBean.f15387n;
                    str = crashDetailBean.f15389p;
                    str = crashDetailBean.f15390q;
                    long j = crashDetailBean.f15391r;
                    Map map = null;
                    if (this.f15440f != null) {
                        C4462n c4462n = this.f15440f;
                        b = this.f15440f.m17662b();
                        if (b != null) {
                            map = new HashMap(1);
                            map.put("userData", b);
                        }
                    } else if (this.f15441g != null) {
                        map = this.f15441g.onCrashHandleStart(i, crashDetailBean.f15387n, crashDetailBean.f15388o, crashDetailBean.f15390q);
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.f15365N = new LinkedHashMap(map.size());
                        for (Entry entry : map.entrySet()) {
                            if (!C4479y.m17792a((String) entry.getKey())) {
                                b = (String) entry.getKey();
                                if (b.length() > 100) {
                                    b = b.substring(0, 100);
                                    C4475w.m17752d("setted key length is over limit %d substring to %s", Integer.valueOf(100), b);
                                }
                                String str2 = b;
                                if (C4479y.m17792a((String) entry.getValue()) || ((String) entry.getValue()).length() <= 30000) {
                                    str = ((String) entry.getValue());
                                } else {
                                    str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    C4475w.m17752d("setted %s value length is over limit %d substring", str2, Integer.valueOf(30000));
                                }
                                crashDetailBean.f15365N.put(str2, str);
                                C4475w.m17747a("add setted key %s value size:%d", str2, Integer.valueOf(str.length()));
                            }
                        }
                    }
                    C4475w.m17747a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArr = null;
                    if (this.f15440f != null) {
                        bArr = this.f15440f.m17661a();
                    } else if (this.f15441g != null) {
                        bArr = this.f15441g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.f15387n, crashDetailBean.f15388o, crashDetailBean.f15390q);
                    }
                    crashDetailBean.f15370S = bArr;
                    if (crashDetailBean.f15370S != null) {
                        if (crashDetailBean.f15370S.length > 30000) {
                            C4475w.m17752d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.f15370S.length), Integer.valueOf(30000));
                        }
                        C4475w.m17747a("add extra bytes %d ", Integer.valueOf(crashDetailBean.f15370S.length));
                    }
                } catch (Throwable th) {
                    C4475w.m17752d("crash handle callback somthing wrong! %s", th.getClass().getName());
                    if (!C4475w.m17748a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private static ContentValues m17435d(CrashDetailBean crashDetailBean) {
        int i = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            int i2;
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f15374a > 0) {
                contentValues.put(FieldType.FOREIGN_ID_FIELD_SUFFIX, Long.valueOf(crashDetailBean.f15374a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f15391r));
            contentValues.put("_s1", crashDetailBean.f15394u);
            String str = "_up";
            if (crashDetailBean.f15377d) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            contentValues.put(str, Integer.valueOf(i2));
            String str2 = "_me";
            if (!crashDetailBean.f15383j) {
                i = 0;
            }
            contentValues.put(str2, Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f15385l));
            contentValues.put("_dt", C4479y.m17795a((Parcelable) crashDetailBean));
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
    private static CrashDetailBean m17424a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C4479y.m17776a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.f15374a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: c */
    public final void m17443c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            ContentValues d = C4433b.m17435d(crashDetailBean);
            if (d != null) {
                long a = C4464o.m17672a().m17687a("t_cr", d, null, true);
                if (a >= 0) {
                    C4475w.m17751c("insert %s success!", "t_cr");
                    crashDetailBean.f15374a = a;
                }
            }
        }
    }

    /* renamed from: b */
    private List<CrashDetailBean> m17433b(List<C4423a> list) {
        Throwable th;
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (C4423a c4423a : list) {
            stringBuilder.append(" or _id").append(" = ").append(c4423a.f15400a);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.length() > 0) {
            stringBuilder2 = stringBuilder2.substring(4);
        }
        stringBuilder.setLength(0);
        Cursor a;
        try {
            a = C4464o.m17672a().m17688a("t_cr", null, stringBuilder2, null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                List<CrashDetailBean> arrayList = new ArrayList();
                while (a.moveToNext()) {
                    CrashDetailBean a2 = C4433b.m17424a(a);
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
                String stringBuilder3 = stringBuilder.toString();
                if (stringBuilder3.length() > 0) {
                    int a3 = C4464o.m17672a().m17686a("t_cr", stringBuilder3.substring(4), null, null, true);
                    C4475w.m17752d("deleted %s illegle data %d", "t_cr", Integer.valueOf(a3));
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

    /* renamed from: b */
    private static C4423a m17431b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            C4423a c4423a = new C4423a();
            c4423a.f15400a = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            c4423a.f15401b = cursor.getLong(cursor.getColumnIndex("_tm"));
            c4423a.f15402c = cursor.getString(cursor.getColumnIndex("_s1"));
            c4423a.f15403d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            c4423a.f15404e = z;
            c4423a.f15405f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return c4423a;
        } catch (Throwable th) {
            if (C4475w.m17748a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private List<C4423a> m17432b() {
        Throwable th;
        Cursor cursor = null;
        List<C4423a> arrayList = new ArrayList();
        Cursor a;
        try {
            a = C4464o.m17672a().m17688a("t_cr", new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (a.moveToNext()) {
                    C4423a b = C4433b.m17431b(a);
                    if (b != null) {
                        arrayList.add(b);
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
                    int a2 = C4464o.m17672a().m17686a("t_cr", stringBuilder2.substring(4), null, null, true);
                    C4475w.m17752d("deleted %s illegle data %d", "t_cr", Integer.valueOf(a2));
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

    /* renamed from: c */
    private static void m17434c(List<C4423a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (C4423a c4423a : list) {
                stringBuilder.append(" or _id").append(" = ").append(c4423a.f15400a);
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() > 0) {
                stringBuilder2 = stringBuilder2.substring(4);
            }
            stringBuilder.setLength(0);
            try {
                int a = C4464o.m17672a().m17686a("t_cr", stringBuilder2, null, null, true);
                C4475w.m17751c("deleted %s data %d", "t_cr", Integer.valueOf(a));
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private static void m17436d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        stringBuilder.append(" or _id").append(" = ").append(crashDetailBean.f15374a);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        stringBuilder2 = stringBuilder2.substring(4);
                    }
                    stringBuilder.setLength(0);
                    int a = C4464o.m17672a().m17686a("t_cr", stringBuilder2, null, null, true);
                    C4475w.m17751c("deleted %s data %d", "t_cr", Integer.valueOf(a));
                }
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static aj m17427a(Context context, CrashDetailBean crashDetailBean, C4417a c4417a) {
        boolean z = true;
        if (context == null || crashDetailBean == null || c4417a == null) {
            C4475w.m17752d("enExp args == null", new Object[0]);
            return null;
        }
        ai a;
        aj ajVar = new aj();
        switch (crashDetailBean.f15375b) {
            case 0:
                ajVar.f15555a = crashDetailBean.f15383j ? "200" : "100";
                break;
            case 1:
                ajVar.f15555a = crashDetailBean.f15383j ? "201" : "101";
                break;
            case 2:
                ajVar.f15555a = crashDetailBean.f15383j ? "202" : "102";
                break;
            case 3:
                ajVar.f15555a = crashDetailBean.f15383j ? "203" : "103";
                break;
            case 4:
                ajVar.f15555a = crashDetailBean.f15383j ? "204" : "104";
                break;
            case 5:
                ajVar.f15555a = crashDetailBean.f15383j ? "207" : "107";
                break;
            case 6:
                ajVar.f15555a = crashDetailBean.f15383j ? "206" : "106";
                break;
            case 7:
                ajVar.f15555a = crashDetailBean.f15383j ? "208" : "108";
                break;
            default:
                C4475w.m17753e("crash type error! %d", Integer.valueOf(crashDetailBean.f15375b));
                break;
        }
        ajVar.f15556b = crashDetailBean.f15391r;
        ajVar.f15557c = crashDetailBean.f15387n;
        ajVar.f15558d = crashDetailBean.f15388o;
        ajVar.f15559e = crashDetailBean.f15389p;
        ajVar.f15561g = crashDetailBean.f15390q;
        ajVar.f15562h = crashDetailBean.f15398y;
        ajVar.f15563i = crashDetailBean.f15376c;
        ajVar.f15564j = null;
        ajVar.f15566l = crashDetailBean.f15386m;
        ajVar.f15567m = crashDetailBean.f15378e;
        ajVar.f15560f = crashDetailBean.f15352A;
        ajVar.f15574t = C4417a.m17304b().m17340i();
        ajVar.f15568n = null;
        if (crashDetailBean.f15382i != null && crashDetailBean.f15382i.size() > 0) {
            ajVar.f15569o = new ArrayList();
            for (Entry entry : crashDetailBean.f15382i.entrySet()) {
                ag agVar = new ag();
                agVar.f15535a = ((PlugInBean) entry.getValue()).f15257a;
                agVar.f15537c = ((PlugInBean) entry.getValue()).f15259c;
                agVar.f15538d = ((PlugInBean) entry.getValue()).f15258b;
                agVar.f15536b = c4417a.m17349r();
                ajVar.f15569o.add(agVar);
            }
        }
        if (crashDetailBean.f15381h != null && crashDetailBean.f15381h.size() > 0) {
            ajVar.f15570p = new ArrayList();
            for (Entry entry2 : crashDetailBean.f15381h.entrySet()) {
                agVar = new ag();
                agVar.f15535a = ((PlugInBean) entry2.getValue()).f15257a;
                agVar.f15537c = ((PlugInBean) entry2.getValue()).f15259c;
                agVar.f15538d = ((PlugInBean) entry2.getValue()).f15258b;
                ajVar.f15570p.add(agVar);
            }
        }
        if (crashDetailBean.f15383j) {
            int size;
            ajVar.f15565k = crashDetailBean.f15393t;
            if (crashDetailBean.f15392s != null && crashDetailBean.f15392s.length() > 0) {
                if (ajVar.f15571q == null) {
                    ajVar.f15571q = new ArrayList();
                }
                try {
                    ajVar.f15571q.add(new ai((byte) 1, "alltimes.txt", crashDetailBean.f15392s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    ajVar.f15571q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(ajVar.f15565k);
            if (ajVar.f15571q != null) {
                size = ajVar.f15571q.size();
            } else {
                size = 0;
            }
            objArr[1] = Integer.valueOf(size);
            C4475w.m17751c(str, objArr);
        }
        if (crashDetailBean.f15396w != null) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            try {
                ajVar.f15571q.add(new ai((byte) 1, "log.txt", crashDetailBean.f15396w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                ajVar.f15571q = null;
            }
        }
        if (!C4479y.m17792a(crashDetailBean.f15371T)) {
            Object aiVar;
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            try {
                aiVar = new ai((byte) 1, "crashInfos.txt", crashDetailBean.f15371T.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e22) {
                e22.printStackTrace();
                aiVar = null;
            }
            if (aiVar != null) {
                C4475w.m17751c("attach crash infos", new Object[0]);
                ajVar.f15571q.add(aiVar);
            }
        }
        if (crashDetailBean.f15372U != null) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            a = C4433b.m17426a("backupRecord.zip", context, crashDetailBean.f15372U);
            if (a != null) {
                C4475w.m17751c("attach backup record", new Object[0]);
                ajVar.f15571q.add(a);
            }
        }
        if (crashDetailBean.f15397x != null && crashDetailBean.f15397x.length > 0) {
            a = new ai((byte) 2, "buglylog.zip", crashDetailBean.f15397x);
            C4475w.m17751c("attach user log", new Object[0]);
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            ajVar.f15571q.add(a);
        }
        if (crashDetailBean.f15375b == 3) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            if (crashDetailBean.f15365N != null && crashDetailBean.f15365N.containsKey("BUGLY_CR_01")) {
                try {
                    ajVar.f15571q.add(new ai((byte) 1, "anrMessage.txt", ((String) crashDetailBean.f15365N.get("BUGLY_CR_01")).getBytes("utf-8")));
                    C4475w.m17751c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                    ajVar.f15571q = null;
                }
                crashDetailBean.f15365N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.f15395v != null) {
                a = C4433b.m17426a("trace.zip", context, crashDetailBean.f15395v);
                if (a != null) {
                    C4475w.m17751c("attach traces", new Object[0]);
                    ajVar.f15571q.add(a);
                }
            }
        }
        if (crashDetailBean.f15375b == 1) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            if (crashDetailBean.f15395v != null) {
                a = C4433b.m17426a("tomb.zip", context, crashDetailBean.f15395v);
                if (a != null) {
                    C4475w.m17751c("attach tombs", new Object[0]);
                    ajVar.f15571q.add(a);
                }
            }
        }
        if (!(c4417a.f15262B == null || c4417a.f15262B.isEmpty())) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : c4417a.f15262B) {
                stringBuilder.append(append);
            }
            try {
                ajVar.f15571q.add(new ai((byte) 1, "martianlog.txt", stringBuilder.toString().getBytes("utf-8")));
                C4475w.m17751c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e2222) {
                e2222.printStackTrace();
            }
        }
        if (crashDetailBean.f15370S != null && crashDetailBean.f15370S.length > 0) {
            if (ajVar.f15571q == null) {
                ajVar.f15571q = new ArrayList();
            }
            ajVar.f15571q.add(new ai((byte) 1, "userExtraByteData", crashDetailBean.f15370S));
            C4475w.m17751c("attach extraData", new Object[0]);
        }
        ajVar.f15572r = new HashMap();
        ajVar.f15572r.put("A9", crashDetailBean.f15353B);
        ajVar.f15572r.put("A11", crashDetailBean.f15354C);
        ajVar.f15572r.put("A10", crashDetailBean.f15355D);
        ajVar.f15572r.put("A23", crashDetailBean.f15379f);
        ajVar.f15572r.put("A7", c4417a.f15291f);
        ajVar.f15572r.put("A6", c4417a.m17350s());
        ajVar.f15572r.put("A5", c4417a.m17349r());
        ajVar.f15572r.put("A22", c4417a.m17339h());
        ajVar.f15572r.put("A2", crashDetailBean.f15357F);
        ajVar.f15572r.put("A1", crashDetailBean.f15356E);
        ajVar.f15572r.put("A24", c4417a.f15293h);
        ajVar.f15572r.put("A17", crashDetailBean.f15358G);
        ajVar.f15572r.put("A3", c4417a.m17342k());
        ajVar.f15572r.put("A16", c4417a.m17344m());
        ajVar.f15572r.put("A25", c4417a.m17345n());
        ajVar.f15572r.put("A14", c4417a.m17343l());
        ajVar.f15572r.put("A15", c4417a.m17354w());
        ajVar.f15572r.put("A13", c4417a.m17355x());
        ajVar.f15572r.put("A34", crashDetailBean.f15399z);
        if (c4417a.f15309x != null) {
            ajVar.f15572r.put("productIdentify", c4417a.f15309x);
        }
        try {
            ajVar.f15572r.put("A26", URLEncoder.encode(crashDetailBean.f15359H, "utf-8"));
        } catch (UnsupportedEncodingException e22222) {
            e22222.printStackTrace();
        }
        if (crashDetailBean.f15375b == 1) {
            ajVar.f15572r.put("A27", crashDetailBean.f15361J);
            ajVar.f15572r.put("A28", crashDetailBean.f15360I);
            ajVar.f15572r.put("A29", crashDetailBean.f15384k);
        }
        ajVar.f15572r.put("A30", crashDetailBean.f15362K);
        ajVar.f15572r.put("A18", crashDetailBean.f15363L);
        ajVar.f15572r.put("A36", (!crashDetailBean.f15364M));
        ajVar.f15572r.put("F02", c4417a.f15302q);
        ajVar.f15572r.put("F03", c4417a.f15303r);
        ajVar.f15572r.put("F04", c4417a.m17333e());
        ajVar.f15572r.put("F05", c4417a.f15304s);
        ajVar.f15572r.put("F06", c4417a.f15301p);
        ajVar.f15572r.put("F08", c4417a.f15307v);
        ajVar.f15572r.put("F09", c4417a.f15308w);
        ajVar.f15572r.put("F10", c4417a.f15305t);
        if (crashDetailBean.f15366O >= 0) {
            ajVar.f15572r.put("C01", crashDetailBean.f15366O);
        }
        if (crashDetailBean.f15367P >= 0) {
            ajVar.f15572r.put("C02", crashDetailBean.f15367P);
        }
        if (crashDetailBean.f15368Q != null && crashDetailBean.f15368Q.size() > 0) {
            for (Entry entry22 : crashDetailBean.f15368Q.entrySet()) {
                ajVar.f15572r.put("C03_" + ((String) entry22.getKey()), entry22.getValue());
            }
        }
        if (crashDetailBean.f15369R != null && crashDetailBean.f15369R.size() > 0) {
            for (Entry entry222 : crashDetailBean.f15369R.entrySet()) {
                ajVar.f15572r.put("C04_" + ((String) entry222.getKey()), entry222.getValue());
            }
        }
        ajVar.f15573s = null;
        if (crashDetailBean.f15365N != null && crashDetailBean.f15365N.size() > 0) {
            ajVar.f15573s = crashDetailBean.f15365N;
            C4475w.m17747a("setted message size %d", Integer.valueOf(ajVar.f15573s.size()));
        }
        String append2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.f15387n;
        objArr2[1] = crashDetailBean.f15376c;
        objArr2[2] = c4417a.m17333e();
        objArr2[3] = Long.valueOf((crashDetailBean.f15391r - crashDetailBean.f15363L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.f15384k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.f15364M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.f15383j);
        if (crashDetailBean.f15375b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.f15393t);
        objArr2[9] = crashDetailBean.f15392s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.f15377d);
        objArr2[11] = Integer.valueOf(ajVar.f15572r.size());
        C4475w.m17751c(append2, objArr2);
        return ajVar;
    }

    /* renamed from: a */
    private static ai m17426a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        if (str2 == null || context == null) {
            C4475w.m17752d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        C4475w.m17751c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (C4479y.m17791a(file, file2, 5000)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[1000];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                        byteArrayOutputStream.flush();
                    }
                    C4475w.m17751c("read bytes :%d", Integer.valueOf(byteArrayOutputStream.toByteArray().length));
                    ai aiVar = new ai((byte) 2, file2.getName(), bArr);
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        if (!C4475w.m17748a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    if (file2.exists()) {
                        C4475w.m17751c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return aiVar;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!C4475w.m17748a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                if (!C4475w.m17748a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            return null;
                        }
                        C4475w.m17751c("del tmp", new Object[0]);
                        file2.delete();
                        return null;
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th32) {
                                if (!C4475w.m17748a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            C4475w.m17751c("del tmp", new Object[0]);
                            file2.delete();
                        }
                        throw e2;
                    }
                }
            } catch (Throwable th322) {
                fileInputStream = null;
                e2 = th322;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (file2.exists()) {
                    C4475w.m17751c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw e2;
            }
        }
        C4475w.m17752d("zip fail!", new Object[0]);
        return null;
    }

    /* renamed from: a */
    public static void m17429a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        C4417a b = C4417a.m17304b();
        if (b != null) {
            C4475w.m17753e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            C4475w.m17753e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            C4475w.m17753e("# PKG NAME: %s", b.f15288c);
            C4475w.m17753e("# APP VER: %s", b.f15295j);
            C4475w.m17753e("# LAUNCH TIME: %s", C4479y.m17782a(new Date(C4417a.m17304b().f15286a)));
            C4475w.m17753e("# CRASH TYPE: %s", str);
            C4475w.m17753e("# CRASH TIME: %s", str2);
            C4475w.m17753e("# CRASH PROCESS: %s", str3);
            if (thread != null) {
                C4475w.m17753e("# CRASH THREAD: %s", thread.getName());
            }
            if (crashDetailBean != null) {
                C4475w.m17753e("# REPORT ID: %s", crashDetailBean.f15376c);
                String str5 = "# CRASH DEVICE: %s %s";
                Object[] objArr = new Object[2];
                objArr[0] = b.f15292g;
                objArr[1] = b.m17355x().booleanValue() ? "ROOTED" : "UNROOT";
                C4475w.m17753e(str5, objArr);
                C4475w.m17753e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f15353B), Long.valueOf(crashDetailBean.f15354C), Long.valueOf(crashDetailBean.f15355D));
                C4475w.m17753e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f15356E), Long.valueOf(crashDetailBean.f15357F), Long.valueOf(crashDetailBean.f15358G));
                if (!C4479y.m17792a(crashDetailBean.f15361J)) {
                    C4475w.m17753e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f15361J, crashDetailBean.f15360I);
                } else if (crashDetailBean.f15375b == 3) {
                    str5 = "# EXCEPTION ANR MESSAGE:\n %s";
                    objArr = new Object[1];
                    objArr[0] = crashDetailBean.f15365N == null ? "null" : ((String) crashDetailBean.f15365N.get("BUGLY_CR_01"));
                    C4475w.m17753e(str5, objArr);
                }
            }
            if (!C4479y.m17792a(str4)) {
                C4475w.m17753e("# CRASH STACK: ", new Object[0]);
                C4475w.m17753e(str4, new Object[0]);
            }
            C4475w.m17753e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
