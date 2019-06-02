package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.C4744d;
import java.io.File;
import org.json.JSONObject;

/* compiled from: Envelope */
/* renamed from: u.aly.bx */
public class bx {
    /* renamed from: a */
    private final byte[] f18934a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    /* renamed from: b */
    private final int f18935b = 1;
    /* renamed from: c */
    private final int f18936c = 0;
    /* renamed from: d */
    private String f18937d = "1.0";
    /* renamed from: e */
    private String f18938e = null;
    /* renamed from: f */
    private byte[] f18939f = null;
    /* renamed from: g */
    private byte[] f18940g = null;
    /* renamed from: h */
    private byte[] f18941h = null;
    /* renamed from: i */
    private int f18942i = 0;
    /* renamed from: j */
    private int f18943j = 0;
    /* renamed from: k */
    private int f18944k = 0;
    /* renamed from: l */
    private byte[] f18945l = null;
    /* renamed from: m */
    private byte[] f18946m = null;
    /* renamed from: n */
    private boolean f18947n = false;

    private bx(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.f18938e = str;
        this.f18944k = bArr.length;
        this.f18945l = ae.m21107a(bArr);
        this.f18943j = (int) (System.currentTimeMillis() / 1000);
        this.f18946m = bArr2;
    }

    /* renamed from: a */
    public static String m21783a(Context context) {
        SharedPreferences a = C5955u.m22014a(context);
        if (a == null) {
            return null;
        }
        return a.getString("signature", null);
    }

    /* renamed from: a */
    public void m21791a(String str) {
        this.f18939f = C4744d.m18642a(str);
    }

    /* renamed from: a */
    public String m21789a() {
        return C4744d.m18640a(this.f18939f);
    }

    /* renamed from: a */
    public void m21790a(int i) {
        this.f18942i = i;
    }

    /* renamed from: a */
    public void m21792a(boolean z) {
        this.f18947n = z;
    }

    /* renamed from: a */
    public static bx m21784a(Context context, String str, byte[] bArr) {
        try {
            String o = af.m21133o(context);
            String c = af.m21119c(context);
            SharedPreferences a = C5955u.m22014a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            bx bxVar = new bx(bArr, str, (c + o).getBytes());
            bxVar.m21791a(string);
            bxVar.m21790a(i);
            bxVar.m21793b();
            a.edit().putInt("serial", i + 1).putString("signature", bxVar.m21789a()).commit();
            bxVar.m21794b(context);
            return bxVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static bx m21786b(Context context, String str, byte[] bArr) {
        try {
            String o = af.m21133o(context);
            String c = af.m21119c(context);
            SharedPreferences a = C5955u.m22014a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            bx bxVar = new bx(bArr, str, (c + o).getBytes());
            bxVar.m21792a(true);
            bxVar.m21791a(string);
            bxVar.m21790a(i);
            bxVar.m21793b();
            a.edit().putInt("serial", i + 1).putString("signature", bxVar.m21789a()).commit();
            bxVar.m21794b(context);
            return bxVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void m21793b() {
        if (this.f18939f == null) {
            this.f18939f = m21787d();
        }
        if (this.f18947n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f18939f, 1, bArr, 0, 16);
                this.f18945l = C4744d.m18643a(this.f18945l, bArr);
            } catch (Exception e) {
            }
        }
        this.f18940g = m21785a(this.f18939f, this.f18943j);
        this.f18941h = m21788e();
    }

    /* renamed from: a */
    private byte[] m21785a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] b = C4744d.m18644b(this.f18946m);
        byte[] b2 = C4744d.m18644b(this.f18945l);
        int length = b.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (i2 = 0; i2 < 2; i2++) {
            bArr2[i2] = bArr[i2];
            bArr2[(bArr2.length - i2) - 1] = bArr[(bArr.length - i2) - 1];
        }
        byte[] bArr3 = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        while (i3 < bArr2.length) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3 % 4]);
            i3++;
        }
        return bArr2;
    }

    /* renamed from: d */
    private byte[] m21787d() {
        return m21785a(this.f18934a, (int) (System.currentTimeMillis() / 1000));
    }

    /* renamed from: e */
    private byte[] m21788e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C4744d.m18640a(this.f18939f));
        stringBuilder.append(this.f18942i);
        stringBuilder.append(this.f18943j);
        stringBuilder.append(this.f18944k);
        stringBuilder.append(C4744d.m18640a(this.f18940g));
        return C4744d.m18644b(stringBuilder.toString().getBytes());
    }

    /* renamed from: c */
    public byte[] m21795c() {
        bp bgVar = new bg();
        bgVar.m21649a(this.f18937d);
        bgVar.m21655b(this.f18938e);
        bgVar.m21661c(C4744d.m18640a(this.f18939f));
        bgVar.m21648a(this.f18942i);
        bgVar.m21660c(this.f18943j);
        bgVar.m21663d(this.f18944k);
        bgVar.m21651a(this.f18945l);
        bgVar.m21667e(this.f18947n ? 1 : 0);
        bgVar.m21664d(C4744d.m18640a(this.f18940g));
        bgVar.m21668e(C4744d.m18640a(this.f18941h));
        try {
            return new an().m21192a(bgVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void m21794b(Context context) {
        String str = this.f18938e;
        String c = cf.m21838a(context).m21846b().m21835c(null);
        String a = C4744d.m18640a(this.f18939f);
        Object obj = new byte[16];
        System.arraycopy(this.f18939f, 2, obj, 0, 16);
        String a2 = C4744d.m18640a(C4744d.m18644b(obj));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", str);
            if (c != null) {
                jSONObject.put("umid", c);
            }
            jSONObject.put("signature", a);
            jSONObject.put("checksum", a2);
            str = jSONObject.toString();
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            ag.m21147a(new File(file, "exchangeIdentity.json"), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.f18937d}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.f18938e}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{C4744d.m18640a(this.f18939f)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.f18942i)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.f18943j)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.f18944k)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{C4744d.m18640a(this.f18940g)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{C4744d.m18640a(this.f18941h)}));
        String str = "codex : %d";
        Object[] objArr = new Object[1];
        if (!this.f18947n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }
}
