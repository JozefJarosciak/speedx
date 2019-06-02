package p203u.aly;

import android.content.Context;
import android.text.TextUtils;
import p203u.aly.cf.C5932a;

/* compiled from: ABTest */
/* renamed from: u.aly.z */
public class C5961z implements C5846t {
    /* renamed from: h */
    private static C5961z f19119h = null;
    /* renamed from: a */
    private boolean f19120a = false;
    /* renamed from: b */
    private int f19121b = -1;
    /* renamed from: c */
    private int f19122c = -1;
    /* renamed from: d */
    private int f19123d = -1;
    /* renamed from: e */
    private float f19124e = 0.0f;
    /* renamed from: f */
    private float f19125f = 0.0f;
    /* renamed from: g */
    private Context f19126g = null;

    /* renamed from: a */
    public static synchronized C5961z m22062a(Context context) {
        C5961z c5961z;
        synchronized (C5961z.class) {
            if (f19119h == null) {
                C5932a b = cf.m21838a(context).m21846b();
                f19119h = new C5961z(context, b.m21832b(null), b.m21836d(0));
            }
            c5961z = f19119h;
        }
        return c5961z;
    }

    private C5961z(Context context, String str, int i) {
        this.f19126g = context;
        m22067a(str, i);
    }

    /* renamed from: b */
    private float m22064b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    /* renamed from: a */
    public void m22067a(String str, int i) {
        this.f19122c = i;
        String a = bx.m21783a(this.f19126g);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.f19120a = false;
            return;
        }
        try {
            this.f19124e = m22064b(a, 12);
            this.f19125f = m22064b(a, 6);
            if (str.startsWith("SIG7")) {
                m22065b(str);
            } else if (str.startsWith("FIXED")) {
                m22066c(str);
            }
        } catch (Throwable e) {
            this.f19120a = false;
            ah.m21160b("v:" + str, e);
        }
    }

    /* renamed from: a */
    public static boolean m22063a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(",").length;
        int parseInt = Integer.parseInt(split[1]);
        if (length < parseInt || parseInt < 1) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private void m22065b(String str) {
        if (str != null) {
            float floatValue;
            String[] split = str.split("\\|");
            if (split[2].equals("SIG13")) {
                floatValue = Float.valueOf(split[3]).floatValue();
            } else {
                floatValue = 0.0f;
            }
            if (this.f19124e > floatValue) {
                this.f19120a = false;
                return;
            }
            String[] split2;
            float[] fArr = null;
            if (split[0].equals("SIG7")) {
                split2 = split[1].split(",");
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split2[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (af.m21130l(this.f19126g)) {
                    this.f19120a = false;
                    return;
                }
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i3 = 0; i3 < split2.length; i3++) {
                        iArr[i3] = Integer.valueOf(split2[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            float f = 0.0f;
            int i4 = 0;
            while (i4 < fArr.length) {
                f += fArr[i4];
                if (this.f19125f < f) {
                    break;
                }
                i4++;
            }
            i4 = -1;
            if (i4 != -1) {
                this.f19120a = true;
                this.f19123d = i4 + 1;
                if (iArr != null) {
                    this.f19121b = iArr[i4];
                    return;
                }
                return;
            }
            this.f19120a = false;
        }
    }

    /* renamed from: c */
    private void m22066c(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.f19124e > f) {
                this.f19120a = false;
                return;
            }
            int intValue;
            if (split[0].equals("FIXED")) {
                intValue = Integer.valueOf(split[1]).intValue();
            } else {
                intValue = -1;
            }
            int[] iArr = null;
            String[] split2;
            if (split[4].equals("RPT")) {
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (af.m21130l(this.f19126g)) {
                    this.f19120a = false;
                    return;
                }
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i2 = 0; i2 < split2.length; i2++) {
                        iArr[i2] = Integer.valueOf(split2[i2]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (intValue != -1) {
                this.f19120a = true;
                this.f19123d = intValue;
                if (iArr != null) {
                    this.f19121b = iArr[intValue - 1];
                    return;
                }
                return;
            }
            this.f19120a = false;
        }
    }

    /* renamed from: a */
    public boolean m22070a() {
        return this.f19120a;
    }

    /* renamed from: b */
    public int m22071b() {
        return this.f19121b;
    }

    /* renamed from: a */
    public void m22068a(av avVar) {
        if (this.f19120a) {
            avVar.f18721b.f18657f.put("client_test", Integer.valueOf(this.f19123d));
        }
    }

    /* renamed from: a */
    public void mo7171a(C5932a c5932a) {
        m22067a(c5932a.m21832b(null), c5932a.m21836d(0));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" p13:");
        stringBuilder.append(this.f19124e);
        stringBuilder.append(" p07:");
        stringBuilder.append(this.f19125f);
        stringBuilder.append(" policy:");
        stringBuilder.append(this.f19121b);
        stringBuilder.append(" interval:");
        stringBuilder.append(this.f19122c);
        return stringBuilder.toString();
    }
}
