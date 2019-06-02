package com.tencent.bugly.proguard;

import android.content.Context;
import ch.qos.logback.classic.spi.CallerData;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.bugly.C4401a;
import com.tencent.bugly.C4402b;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.a */
public class C4446a {
    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f15529a = new HashMap();
    /* renamed from: b */
    protected String f15530b;
    /* renamed from: c */
    C4455h f15531c;
    /* renamed from: d */
    private HashMap<String, Object> f15532d;

    /* renamed from: a */
    public static af m17522a(int i) {
        if (i == 1) {
            return new ae();
        }
        if (i == 3) {
            return new ad();
        }
        return null;
    }

    C4446a() {
        HashMap hashMap = new HashMap();
        this.f15532d = new HashMap();
        this.f15530b = "GBK";
        this.f15531c = new C4455h();
    }

    /* renamed from: a */
    public static ap m17525a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        ap apVar = new ap();
        apVar.f15632a = userInfoBean.f15213e;
        apVar.f15636e = userInfoBean.f15218j;
        apVar.f15635d = userInfoBean.f15211c;
        apVar.f15634c = userInfoBean.f15212d;
        apVar.f15638g = C4417a.m17304b().m17340i();
        apVar.f15639h = userInfoBean.f15223o == 1;
        switch (userInfoBean.f15210b) {
            case 1:
                apVar.f15633b = (byte) 1;
                break;
            case 2:
                apVar.f15633b = (byte) 4;
                break;
            case 3:
                apVar.f15633b = (byte) 2;
                break;
            case 4:
                apVar.f15633b = (byte) 3;
                break;
            default:
                if (userInfoBean.f15210b >= 10 && userInfoBean.f15210b < 20) {
                    apVar.f15633b = (byte) userInfoBean.f15210b;
                    break;
                }
                C4475w.m17753e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f15210b));
                return null;
                break;
        }
        apVar.f15637f = new HashMap();
        if (userInfoBean.f15224p >= 0) {
            apVar.f15637f.put("C01", userInfoBean.f15224p);
        }
        if (userInfoBean.f15225q >= 0) {
            apVar.f15637f.put("C02", userInfoBean.f15225q);
        }
        if (userInfoBean.f15226r != null && userInfoBean.f15226r.size() > 0) {
            for (Entry entry : userInfoBean.f15226r.entrySet()) {
                apVar.f15637f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.f15227s != null && userInfoBean.f15227s.size() > 0) {
            for (Entry entry2 : userInfoBean.f15227s.entrySet()) {
                apVar.f15637f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        apVar.f15637f.put("A36", (!userInfoBean.f15220l));
        apVar.f15637f.put("F02", userInfoBean.f15215g);
        apVar.f15637f.put("F03", userInfoBean.f15216h);
        apVar.f15637f.put("F04", userInfoBean.f15218j);
        apVar.f15637f.put("F05", userInfoBean.f15217i);
        apVar.f15637f.put("F06", userInfoBean.f15221m);
        apVar.f15637f.put("F10", userInfoBean.f15219k);
        C4475w.m17751c("summary type %d vm:%d", Byte.valueOf(apVar.f15633b), Integer.valueOf(apVar.f15637f.size()));
        return apVar;
    }

    /* renamed from: a */
    public void mo6073a(String str) {
        this.f15530b = str;
    }

    /* renamed from: a */
    public static String m17528a(ArrayList<String> arrayList) {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        for (i = 0; i < arrayList.size(); i++) {
            Object obj = (String) arrayList.get(i);
            if (obj.equals("java.lang.Integer") || obj.equals("int")) {
                obj = "int32";
            } else if (obj.equals("java.lang.Boolean") || obj.equals("boolean")) {
                obj = "bool";
            } else if (obj.equals("java.lang.Byte") || obj.equals("byte")) {
                obj = "char";
            } else if (obj.equals("java.lang.Double") || obj.equals("double")) {
                obj = "double";
            } else if (obj.equals("java.lang.Float") || obj.equals("float")) {
                obj = "float";
            } else if (obj.equals("java.lang.Long") || obj.equals("long")) {
                obj = "int64";
            } else if (obj.equals("java.lang.Short") || obj.equals("short")) {
                obj = "short";
            } else if (obj.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (obj.equals("java.lang.String")) {
                obj = "string";
            } else if (obj.equals("java.util.List")) {
                obj = "list";
            } else if (obj.equals("java.util.Map")) {
                obj = "map";
            }
            arrayList.set(i, obj);
        }
        Collections.reverse(arrayList);
        for (i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (str.equals("list")) {
                arrayList.set(i - 1, new StringBuilder(SimpleComparison.LESS_THAN_OPERATION).append((String) arrayList.get(i - 1)).toString());
                arrayList.set(0, ((String) arrayList.get(0)) + SimpleComparison.GREATER_THAN_OPERATION);
            } else if (str.equals("map")) {
                arrayList.set(i - 1, new StringBuilder(SimpleComparison.LESS_THAN_OPERATION).append((String) arrayList.get(i - 1)).append(",").toString());
                arrayList.set(0, ((String) arrayList.get(0)) + SimpleComparison.GREATER_THAN_OPERATION);
            } else if (str.equals("Array")) {
                arrayList.set(i - 1, new StringBuilder(SimpleComparison.LESS_THAN_OPERATION).append((String) arrayList.get(i - 1)).toString());
                arrayList.set(0, ((String) arrayList.get(0)) + SimpleComparison.GREATER_THAN_OPERATION);
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public <T> void mo6074a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C4456i c4456i = new C4456i();
            c4456i.m17629a(this.f15530b);
            c4456i.m17635a((Object) t, 0);
            Object a = C4457k.m17647a(c4456i.m17630a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            m17529a(arrayList, (Object) t);
            hashMap.put(C4446a.m17528a(arrayList), a);
            this.f15532d.remove(str);
            this.f15529a.put(str, hashMap);
        }
    }

    /* renamed from: a */
    public static aq m17526a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        C4417a b = C4417a.m17304b();
        if (b == null) {
            return null;
        }
        b.m17351t();
        aq aqVar = new aq();
        aqVar.f15643b = b.f15289d;
        aqVar.f15644c = b.m17339h();
        ArrayList arrayList = new ArrayList();
        for (UserInfoBean a : list) {
            ap a2 = C4446a.m17525a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        aqVar.f15645d = arrayList;
        aqVar.f15646e = new HashMap();
        aqVar.f15646e.put("A7", b.f15291f);
        aqVar.f15646e.put("A6", b.m17350s());
        aqVar.f15646e.put("A5", b.m17349r());
        aqVar.f15646e.put("A2", b.m17347p());
        aqVar.f15646e.put("A1", b.m17347p());
        aqVar.f15646e.put("A24", b.f15293h);
        aqVar.f15646e.put("A17", b.m17348q());
        aqVar.f15646e.put("A15", b.m17354w());
        aqVar.f15646e.put("A13", b.m17355x());
        aqVar.f15646e.put("F08", b.f15307v);
        aqVar.f15646e.put("F09", b.f15308w);
        Map E = b.m17310E();
        if (E != null && E.size() > 0) {
            for (Entry entry : E.entrySet()) {
                aqVar.f15646e.put("C04_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        switch (i) {
            case 1:
                aqVar.f15642a = (byte) 1;
                break;
            case 2:
                aqVar.f15642a = (byte) 2;
                break;
            default:
                C4475w.m17753e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return aqVar;
    }

    /* renamed from: a */
    public static <T extends C4447j> T m17527a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            C4447j c4447j = (C4447j) cls.newInstance();
            C4455h c4455h = new C4455h(bArr);
            c4455h.m17617a("utf-8");
            c4447j.mo6070a(c4455h);
            return c4447j;
        } catch (Throwable th) {
            if (!C4475w.m17750b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static al m17523a(Context context, int i, byte[] bArr) {
        C4417a b = C4417a.m17304b();
        StrategyBean c = C4421a.m17388a().m17396c();
        if (b == null || c == null) {
            C4475w.m17753e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            al alVar = new al();
            synchronized (b) {
                alVar.f15580a = 1;
                alVar.f15581b = b.m17335f();
                alVar.f15582c = b.f15288c;
                alVar.f15583d = b.f15295j;
                alVar.f15584e = b.f15297l;
                b.getClass();
                alVar.f15585f = "2.4.0";
                alVar.f15586g = i;
                alVar.f15587h = bArr == null ? "".getBytes() : bArr;
                alVar.f15588i = b.f15292g;
                alVar.f15589j = b.f15293h;
                alVar.f15590k = new HashMap();
                alVar.f15591l = b.m17333e();
                alVar.f15592m = c.f15329p;
                alVar.f15594o = b.m17339h();
                alVar.f15595p = C4418b.m17368e(context);
                alVar.f15596q = System.currentTimeMillis();
                alVar.f15597r = b.m17342k();
                alVar.f15598s = b.m17341j();
                alVar.f15599t = b.m17344m();
                alVar.f15600u = b.m17343l();
                alVar.f15601v = b.m17345n();
                alVar.f15602w = alVar.f15595p;
                b.getClass();
                alVar.f15593n = "com.tencent.bugly";
                alVar.f15590k.put("A26", b.m17356y());
                alVar.f15590k.put("F11", b.f15311z);
                alVar.f15590k.put("F12", b.f15310y);
                alVar.f15590k.put("G1", b.m17352u());
                alVar.f15590k.put("G2", b.m17315K());
                alVar.f15590k.put("G3", b.m17316L());
                alVar.f15590k.put("G4", b.m17317M());
                alVar.f15590k.put("G5", b.m17318N());
                alVar.f15590k.put("G6", b.m17319O());
                alVar.f15590k.put("G7", Long.toString(b.m17320P()));
                alVar.f15590k.put("D3", b.f15296k);
                if (C4402b.f15203b != null) {
                    for (C4401a c4401a : C4402b.f15203b) {
                        if (!(c4401a.versionKey == null || c4401a.version == null)) {
                            alVar.f15590k.put(c4401a.versionKey, c4401a.version);
                        }
                    }
                }
            }
            C4471t a = C4471t.m17703a();
            if (!(a == null || a.f15734a || bArr == null)) {
                alVar.f15587h = C4479y.m17798a(alVar.f15587h, 2, 1, c.f15334u);
                if (alVar.f15587h == null) {
                    C4475w.m17753e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map D = b.m17309D();
            if (D != null) {
                for (Entry entry : D.entrySet()) {
                    alVar.f15590k.put(entry.getKey(), entry.getValue());
                }
            }
            return alVar;
        } catch (Throwable th) {
            if (!C4475w.m17750b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private void m17529a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                m17529a((ArrayList) arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add(CallerData.NA);
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                m17529a((ArrayList) arrayList, list.get(0));
            } else {
                arrayList.add(CallerData.NA);
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                m17529a((ArrayList) arrayList, obj2);
                return;
            }
            arrayList.add(CallerData.NA);
            arrayList.add(CallerData.NA);
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    /* renamed from: a */
    public byte[] mo6076a() {
        C4456i c4456i = new C4456i(0);
        c4456i.m17629a(this.f15530b);
        c4456i.m17638a(this.f15529a, 0);
        return C4457k.m17647a(c4456i.m17630a());
    }

    /* renamed from: a */
    public void mo6075a(byte[] bArr) {
        this.f15531c.m17623a(bArr);
        this.f15531c.m17617a(this.f15530b);
        Map hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f15529a = this.f15531c.m17621a(hashMap, 0, false);
    }

    /* renamed from: a */
    public static byte[] m17530a(al alVar) {
        try {
            C4450d c4450d = new C4450d();
            c4450d.mo6077b();
            c4450d.mo6073a("utf-8");
            c4450d.m17593b(1);
            c4450d.m17594b("RqdServer");
            c4450d.m17595c("sync");
            c4450d.mo6074a("detail", alVar);
            return c4450d.mo6076a();
        } catch (Throwable th) {
            if (!C4475w.m17750b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static am m17524a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                am amVar;
                C4450d c4450d = new C4450d();
                c4450d.mo6077b();
                c4450d.mo6073a("utf-8");
                c4450d.mo6075a(bArr);
                Object b = c4450d.m17587b("detail", new am());
                if (am.class.isInstance(b)) {
                    amVar = (am) am.class.cast(b);
                } else {
                    amVar = null;
                }
                if (z || amVar == null || amVar.f15608c == null || amVar.f15608c.length <= 0) {
                    return amVar;
                }
                C4475w.m17751c("resp buf %d", Integer.valueOf(amVar.f15608c.length));
                amVar.f15608c = C4479y.m17810b(amVar.f15608c, 2, 1, StrategyBean.f15317d);
                if (amVar.f15608c != null) {
                    return amVar;
                }
                C4475w.m17753e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!C4475w.m17750b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m17531a(C4447j c4447j) {
        try {
            C4456i c4456i = new C4456i();
            c4456i.m17629a("utf-8");
            c4447j.mo6071a(c4456i);
            return c4456i.m17642b();
        } catch (Throwable th) {
            if (!C4475w.m17750b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
