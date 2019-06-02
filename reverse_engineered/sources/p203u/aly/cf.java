package p203u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import com.umeng.analytics.C4742b;
import com.umeng.analytics.C4744d;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: ImprintHandler */
/* renamed from: u.aly.cf */
public class cf {
    /* renamed from: a */
    private static final byte[] f18986a = "pbl0".getBytes();
    /* renamed from: e */
    private static cf f18987e;
    /* renamed from: b */
    private C5846t f18988b;
    /* renamed from: c */
    private C5932a f18989c = new C5932a();
    /* renamed from: d */
    private bd f18990d = null;
    /* renamed from: f */
    private Context f18991f;

    /* compiled from: ImprintHandler */
    /* renamed from: u.aly.cf$a */
    public static class C5932a {
        /* renamed from: a */
        private int f18973a = -1;
        /* renamed from: b */
        private int f18974b = -1;
        /* renamed from: c */
        private int f18975c = -1;
        /* renamed from: d */
        private int f18976d = -1;
        /* renamed from: e */
        private int f18977e = -1;
        /* renamed from: f */
        private String f18978f = null;
        /* renamed from: g */
        private int f18979g = -1;
        /* renamed from: h */
        private String f18980h = null;
        /* renamed from: i */
        private int f18981i = -1;
        /* renamed from: j */
        private int f18982j = -1;
        /* renamed from: k */
        private String f18983k = null;
        /* renamed from: l */
        private String f18984l = null;
        /* renamed from: m */
        private String f18985m = null;

        C5932a() {
        }

        /* renamed from: a */
        public void m21828a(bd bdVar) {
            if (bdVar != null) {
                this.f18973a = m21823a(bdVar, "defcon");
                this.f18974b = m21823a(bdVar, "latent");
                this.f18975c = m21823a(bdVar, "codex");
                this.f18976d = m21823a(bdVar, "report_policy");
                this.f18977e = m21823a(bdVar, "report_interval");
                this.f18978f = m21824b(bdVar, "client_test");
                this.f18979g = m21823a(bdVar, "test_report_interval");
                this.f18980h = m21824b(bdVar, "umid");
                this.f18981i = m21823a(bdVar, "integrated_test");
                this.f18982j = m21823a(bdVar, "latent_hours");
                this.f18983k = m21824b(bdVar, GeocodingCriteria.TYPE_COUNTRY);
                this.f18984l = m21824b(bdVar, "domain_p");
                this.f18985m = m21824b(bdVar, "domain_s");
            }
        }

        /* renamed from: a */
        public String m21827a(String str) {
            if (this.f18983k != null) {
                return this.f18983k;
            }
            return str;
        }

        /* renamed from: a */
        public int m21825a(int i) {
            return (this.f18973a != -1 && this.f18973a <= 3 && this.f18973a >= 0) ? this.f18973a : i;
        }

        /* renamed from: b */
        public int m21831b(int i) {
            return (this.f18974b != -1 && this.f18974b >= 0 && this.f18974b <= 1800) ? this.f18974b * 1000 : i;
        }

        /* renamed from: c */
        public int m21834c(int i) {
            if (this.f18975c == 0 || this.f18975c == 1 || this.f18975c == -1) {
                return this.f18975c;
            }
            return i;
        }

        /* renamed from: a */
        public int[] m21830a(int i, int i2) {
            if (this.f18976d == -1 || !C4742b.m18637a(this.f18976d)) {
                return new int[]{i, i2};
            }
            if (this.f18977e == -1 || this.f18977e < 90 || this.f18977e > 86400) {
                this.f18977e = 90;
            }
            return new int[]{this.f18976d, this.f18977e * 1000};
        }

        /* renamed from: b */
        public String m21832b(String str) {
            return (this.f18978f == null || !C5961z.m22063a(this.f18978f)) ? str : this.f18978f;
        }

        /* renamed from: d */
        public int m21836d(int i) {
            return (this.f18979g == -1 || this.f18979g < 90 || this.f18979g > 86400) ? i : this.f18979g * 1000;
        }

        /* renamed from: a */
        public boolean m21829a() {
            return this.f18979g != -1;
        }

        /* renamed from: c */
        public String m21835c(String str) {
            return this.f18980h;
        }

        /* renamed from: b */
        public boolean m21833b() {
            return this.f18981i == 1;
        }

        /* renamed from: a */
        public long m21826a(long j) {
            return (this.f18982j != -1 && this.f18982j >= 48) ? 3600000 * ((long) this.f18982j) : j;
        }

        /* renamed from: a */
        private int m21823a(bd bdVar, String str) {
            if (bdVar == null || !bdVar.m21507f()) {
                return -1;
            }
            be beVar = (be) bdVar.m21505d().get(str);
            if (beVar == null || TextUtils.isEmpty(beVar.m21550c())) {
                return -1;
            }
            try {
                return Integer.parseInt(beVar.m21550c().trim());
            } catch (Exception e) {
                return -1;
            }
        }

        /* renamed from: b */
        private String m21824b(bd bdVar, String str) {
            if (bdVar == null || !bdVar.m21507f()) {
                return null;
            }
            be beVar = (be) bdVar.m21505d().get(str);
            if (beVar == null || TextUtils.isEmpty(beVar.m21550c())) {
                return null;
            }
            return beVar.m21550c();
        }
    }

    cf(Context context) {
        this.f18991f = context;
    }

    /* renamed from: a */
    public static synchronized cf m21838a(Context context) {
        cf cfVar;
        synchronized (cf.class) {
            if (f18987e == null) {
                f18987e = new cf(context);
                f18987e.m21848c();
            }
            cfVar = f18987e;
        }
        return cfVar;
    }

    /* renamed from: a */
    public void m21844a(C5846t c5846t) {
        this.f18988b = c5846t;
    }

    /* renamed from: a */
    public String m21842a(bd bdVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(bdVar.m21505d()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((be) entry.getValue()).m21553e()) {
                stringBuilder.append(((be) entry.getValue()).m21550c());
            }
            stringBuilder.append(((be) entry.getValue()).m21554f());
            stringBuilder.append(((be) entry.getValue()).m21557i());
        }
        stringBuilder.append(bdVar.f18814b);
        return ag.m21146a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    /* renamed from: c */
    private boolean m21840c(bd bdVar) {
        if (!bdVar.m21511j().equals(m21842a(bdVar))) {
            return false;
        }
        for (be beVar : bdVar.m21505d().values()) {
            byte[] a = C4744d.m18642a(beVar.m21557i());
            byte[] a2 = m21845a(beVar);
            for (int i = 0; i < 4; i++) {
                if (a[i] != a2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public byte[] m21845a(be beVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(beVar.m21554f());
        byte[] array = allocate.array();
        byte[] bArr = f18986a;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: b */
    public void m21847b(bd bdVar) {
        String str = null;
        if (bdVar != null && m21840c(bdVar)) {
            Object obj = null;
            synchronized (this) {
                bd d;
                bd bdVar2 = this.f18990d;
                String j = bdVar2 == null ? null : bdVar2.m21511j();
                if (bdVar2 == null) {
                    d = m21841d(bdVar);
                } else {
                    d = m21837a(bdVar2, bdVar);
                }
                this.f18990d = d;
                if (d != null) {
                    str = d.m21511j();
                }
                if (!m21839a(j, str)) {
                    obj = 1;
                }
            }
            if (this.f18990d != null && r0 != null) {
                this.f18989c.m21828a(this.f18990d);
                if (this.f18988b != null) {
                    this.f18988b.mo7171a(this.f18989c);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m21839a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private bd m21837a(bd bdVar, bd bdVar2) {
        if (bdVar2 != null) {
            Map d = bdVar.m21505d();
            for (Entry entry : bdVar2.m21505d().entrySet()) {
                if (((be) entry.getValue()).m21553e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            bdVar.m21492a(bdVar2.m21508g());
            bdVar.m21493a(m21842a(bdVar));
        }
        return bdVar;
    }

    /* renamed from: d */
    private bd m21841d(bd bdVar) {
        Map d = bdVar.m21505d();
        List<String> arrayList = new ArrayList(d.size() / 2);
        for (Entry entry : d.entrySet()) {
            if (!((be) entry.getValue()).m21553e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return bdVar;
    }

    /* renamed from: a */
    public synchronized bd m21843a() {
        return this.f18990d;
    }

    /* renamed from: b */
    public C5932a m21846b() {
        return this.f18989c;
    }

    /* renamed from: c */
    public void m21848c() {
        InputStream openFileInput;
        byte[] b;
        Exception e;
        bd bdVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.f18991f.getFilesDir(), ".imprint").exists()) {
            try {
                openFileInput = this.f18991f.openFileInput(".imprint");
                try {
                    b = ag.m21151b(openFileInput);
                    ag.m21152c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        ag.m21152c(openFileInput);
                        if (b == null) {
                            try {
                                bdVar = new bd();
                                new al().m21191a(bdVar, b);
                                this.f18990d = bdVar;
                                this.f18989c.m21828a(bdVar);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        ag.m21152c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                ag.m21152c(openFileInput);
                if (b == null) {
                    bdVar = new bd();
                    new al().m21191a(bdVar, b);
                    this.f18990d = bdVar;
                    this.f18989c.m21828a(bdVar);
                }
            } catch (Throwable th3) {
                th = th3;
                ag.m21152c(inputStream);
                throw th;
            }
            if (b == null) {
                bdVar = new bd();
                new al().m21191a(bdVar, b);
                this.f18990d = bdVar;
                this.f18989c.m21828a(bdVar);
            }
        }
    }

    /* renamed from: d */
    public void m21849d() {
        if (this.f18990d != null) {
            try {
                ag.m21148a(new File(this.f18991f.getFilesDir(), ".imprint"), new an().m21192a(this.f18990d));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
