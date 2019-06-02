package p203u.aly;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.C4731a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: UMEntry */
/* renamed from: u.aly.av */
public class av implements Serializable {
    /* renamed from: c */
    public static long f18718c = 0;
    /* renamed from: d */
    private static final long f18719d = -5254997387189944418L;
    /* renamed from: a */
    public C5868n f18720a = new C5868n();
    /* renamed from: b */
    public C5867m f18721b = new C5867m();

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$b */
    public static class C5855b implements Serializable {
        /* renamed from: b */
        private static final long f18606b = 395432415169525323L;
        /* renamed from: a */
        public long f18607a = 0;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$c */
    public static class C5856c implements Serializable {
        /* renamed from: c */
        private static final long f18608c = -6648526015472635581L;
        /* renamed from: a */
        public String f18609a = null;
        /* renamed from: b */
        public String f18610b = null;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$d */
    public static class C5857d implements Serializable {
        /* renamed from: c */
        private static final long f18611c = -4761083466478982295L;
        /* renamed from: a */
        public Map<String, List<C5858e>> f18612a = new HashMap();
        /* renamed from: b */
        public Map<String, List<C5859f>> f18613b = new HashMap();
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$e */
    public static class C5858e implements Serializable {
        /* renamed from: f */
        private static final long f18614f = 8614138410597604223L;
        /* renamed from: a */
        public long f18615a = 0;
        /* renamed from: b */
        public long f18616b = 0;
        /* renamed from: c */
        public int f18617c = 0;
        /* renamed from: d */
        public int f18618d = 0;
        /* renamed from: e */
        public List<String> f18619e = new ArrayList();
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$f */
    public static final class C5859f implements Serializable {
        /* renamed from: d */
        private static final long f18620d = -7569163627707250811L;
        /* renamed from: a */
        public int f18621a = 0;
        /* renamed from: b */
        public long f18622b = 0;
        /* renamed from: c */
        public String f18623c = null;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$g */
    public static class C5860g implements Serializable {
        /* renamed from: d */
        private static final long f18624d = -1010993116426830703L;
        /* renamed from: a */
        public Integer f18625a = Integer.valueOf(0);
        /* renamed from: b */
        public long f18626b = 0;
        /* renamed from: c */
        public boolean f18627c = false;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$h */
    public static class C5861h implements Serializable {
        /* renamed from: c */
        private static final long f18628c = -7833224895044623144L;
        /* renamed from: a */
        public String f18629a = null;
        /* renamed from: b */
        public List<C5864j> f18630b = new ArrayList();
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$i */
    public static class C5863i implements Serializable, C5862n {
        /* renamed from: d */
        private static final long f18631d = -7911804253674023187L;
        /* renamed from: a */
        public long f18632a = 0;
        /* renamed from: b */
        public long f18633b = 0;
        /* renamed from: c */
        public String f18634c = null;

        /* renamed from: a */
        public void mo7172a(av avVar) {
            if (avVar.f18721b.f18660i != null) {
                avVar.f18721b.f18660i.add(this);
            }
        }
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$j */
    public static class C5864j implements Serializable, C5862n {
        /* renamed from: g */
        private static final long f18635g = -1062440179015494286L;
        /* renamed from: a */
        public int f18636a = 0;
        /* renamed from: b */
        public String f18637b = null;
        /* renamed from: c */
        public String f18638c = null;
        /* renamed from: d */
        public long f18639d = 0;
        /* renamed from: e */
        public long f18640e = 0;
        /* renamed from: f */
        public Map<String, Object> f18641f = new HashMap();

        /* renamed from: a */
        public void mo7172a(av avVar) {
            int i;
            C5861h c5861h;
            int i2 = 0;
            if (this.f18637b == null) {
                this.f18637b = C5958w.m22030a();
            }
            if (avVar.f18721b.f18652a != null) {
                try {
                    if (this.f18636a == 1) {
                        int size = avVar.f18721b.f18652a.size();
                        if (size > 0) {
                            i = 0;
                            while (i < size) {
                                c5861h = (C5861h) avVar.f18721b.f18652a.get(i);
                                if (TextUtils.isEmpty(c5861h.f18629a) || !c5861h.f18629a.equals(this.f18637b)) {
                                    i++;
                                } else {
                                    avVar.f18721b.f18652a.remove(c5861h);
                                    c5861h.f18630b.add(this);
                                    avVar.f18721b.f18652a.add(c5861h);
                                    return;
                                }
                            }
                            c5861h = new C5861h();
                            c5861h.f18629a = this.f18637b;
                            c5861h.f18630b.add(this);
                            if (!avVar.f18721b.f18652a.contains(c5861h)) {
                                avVar.f18721b.f18652a.add(c5861h);
                            }
                        } else {
                            c5861h = new C5861h();
                            c5861h.f18629a = this.f18637b;
                            c5861h.f18630b.add(this);
                            avVar.f18721b.f18652a.add(c5861h);
                        }
                    }
                } catch (Throwable th) {
                    ah.m21157a(th);
                }
            }
            if (avVar.f18721b.f18653b == null) {
                return;
            }
            if (this.f18636a == 2) {
                i = avVar.f18721b.f18653b.size();
                if (i > 0) {
                    while (i2 < i) {
                        c5861h = (C5861h) avVar.f18721b.f18653b.get(i2);
                        if (TextUtils.isEmpty(c5861h.f18629a) || !c5861h.f18629a.equals(this.f18637b)) {
                            i2++;
                        } else {
                            avVar.f18721b.f18653b.remove(c5861h);
                            c5861h.f18630b.add(this);
                            avVar.f18721b.f18653b.add(c5861h);
                            return;
                        }
                    }
                    c5861h = new C5861h();
                    c5861h.f18629a = this.f18637b;
                    c5861h.f18630b.add(this);
                    avVar.f18721b.f18653b.add(c5861h);
                    return;
                }
                c5861h = new C5861h();
                c5861h.f18629a = this.f18637b;
                c5861h.f18630b.add(this);
                avVar.f18721b.f18653b.add(c5861h);
            }
        }
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$k */
    public static final class C5865k implements Serializable {
        /* renamed from: d */
        private static final long f18642d = -1397960951960451474L;
        /* renamed from: a */
        public double f18643a = 0.0d;
        /* renamed from: b */
        public double f18644b = 0.0d;
        /* renamed from: c */
        public long f18645c = 0;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$l */
    public static final class C5866l implements Serializable {
        /* renamed from: e */
        private static final long f18646e = 2506525905874738341L;
        /* renamed from: a */
        public String f18647a = null;
        /* renamed from: b */
        public long f18648b = 0;
        /* renamed from: c */
        public long f18649c = 0;
        /* renamed from: d */
        public long f18650d = 0;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$m */
    public static class C5867m implements Serializable {
        /* renamed from: k */
        private static final long f18651k = 5703014667657688269L;
        /* renamed from: a */
        public List<C5861h> f18652a = new ArrayList();
        /* renamed from: b */
        public List<C5861h> f18653b = new ArrayList();
        /* renamed from: c */
        public List<C5869o> f18654c = new ArrayList();
        /* renamed from: d */
        public C5855b f18655d = new C5855b();
        /* renamed from: e */
        public C5860g f18656e = new C5860g();
        /* renamed from: f */
        public Map<String, Integer> f18657f = new HashMap();
        /* renamed from: g */
        public C5856c f18658g = new C5856c();
        /* renamed from: h */
        public C5857d f18659h = new C5857d();
        /* renamed from: i */
        public List<C5863i> f18660i = new ArrayList();
        /* renamed from: j */
        public String f18661j = null;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$n */
    public static class C5868n implements Serializable {
        /* renamed from: P */
        private static final long f18662P = 4568484649280698573L;
        /* renamed from: A */
        public String f18663A = Build.DEVICE;
        /* renamed from: B */
        public String f18664B = null;
        /* renamed from: C */
        public String f18665C = null;
        /* renamed from: D */
        public long f18666D = 8;
        /* renamed from: E */
        public String f18667E = null;
        /* renamed from: F */
        public String f18668F = null;
        /* renamed from: G */
        public String f18669G = null;
        /* renamed from: H */
        public String f18670H = null;
        /* renamed from: I */
        public String f18671I = null;
        /* renamed from: J */
        public String f18672J = null;
        /* renamed from: K */
        public long f18673K = 0;
        /* renamed from: L */
        public long f18674L = 0;
        /* renamed from: M */
        public long f18675M = 0;
        /* renamed from: N */
        public String f18676N = null;
        /* renamed from: O */
        public String f18677O = null;
        /* renamed from: a */
        public String f18678a = null;
        /* renamed from: b */
        public String f18679b = null;
        /* renamed from: c */
        public String f18680c = null;
        /* renamed from: d */
        public String f18681d = null;
        /* renamed from: e */
        public String f18682e = null;
        /* renamed from: f */
        public String f18683f = null;
        /* renamed from: g */
        public String f18684g = null;
        /* renamed from: h */
        public int f18685h = 0;
        /* renamed from: i */
        public String f18686i = C4731a.f16605a;
        /* renamed from: j */
        public String f18687j = C4731a.f16606b;
        /* renamed from: k */
        public String f18688k = "Android";
        /* renamed from: l */
        public String f18689l = null;
        /* renamed from: m */
        public int f18690m = 0;
        /* renamed from: n */
        public String f18691n = null;
        /* renamed from: o */
        public String f18692o = af.m21110a();
        /* renamed from: p */
        public String f18693p = "Android";
        /* renamed from: q */
        public String f18694q = VERSION.RELEASE;
        /* renamed from: r */
        public String f18695r = null;
        /* renamed from: s */
        public String f18696s = null;
        /* renamed from: t */
        public String f18697t = null;
        /* renamed from: u */
        public String f18698u = Build.MODEL;
        /* renamed from: v */
        public String f18699v = Build.BOARD;
        /* renamed from: w */
        public String f18700w = Build.BRAND;
        /* renamed from: x */
        public long f18701x = Build.TIME;
        /* renamed from: y */
        public String f18702y = Build.MANUFACTURER;
        /* renamed from: z */
        public String f18703z = Build.ID;
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$o */
    public static class C5869o implements Serializable, C5862n {
        /* renamed from: g */
        public static Map<String, C5866l> f18704g = new HashMap();
        /* renamed from: k */
        private static final long f18705k = 8683938900576888953L;
        /* renamed from: a */
        public int f18706a = 0;
        /* renamed from: b */
        public String f18707b = null;
        /* renamed from: c */
        public long f18708c = 0;
        /* renamed from: d */
        public long f18709d = 0;
        /* renamed from: e */
        public long f18710e = 0;
        /* renamed from: f */
        public boolean f18711f = false;
        /* renamed from: h */
        public List<C5866l> f18712h = new ArrayList();
        /* renamed from: i */
        public C5870p f18713i = new C5870p();
        /* renamed from: j */
        public C5865k f18714j = new C5865k();

        /* renamed from: a */
        public void mo7172a(av avVar) {
            if (avVar.f18721b.f18654c != null) {
                avVar.f18721b.f18654c.add(this);
            }
        }
    }

    /* compiled from: UMEntry */
    /* renamed from: u.aly.av$p */
    public static final class C5870p implements Serializable {
        /* renamed from: c */
        private static final long f18715c = -7629272972021970177L;
        /* renamed from: a */
        public long f18716a = 0;
        /* renamed from: b */
        public long f18717b = 0;
    }

    /* renamed from: a */
    public boolean m21240a() {
        if (this.f18720a.f18697t == null || this.f18720a.f18696s == null || this.f18720a.f18695r == null || this.f18720a.f18678a == null || this.f18720a.f18679b == null || this.f18720a.f18683f == null || this.f18720a.f18682e == null || this.f18720a.f18681d == null) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public void m21241b() {
        this.f18720a = new C5868n();
        this.f18721b = new C5867m();
        f18718c = 0;
    }
}
