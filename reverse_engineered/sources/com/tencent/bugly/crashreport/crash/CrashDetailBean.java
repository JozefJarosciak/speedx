package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C4479y;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Creator<CrashDetailBean> CREATOR = new C44221();
    /* renamed from: A */
    public String f15352A = "";
    /* renamed from: B */
    public long f15353B = -1;
    /* renamed from: C */
    public long f15354C = -1;
    /* renamed from: D */
    public long f15355D = -1;
    /* renamed from: E */
    public long f15356E = -1;
    /* renamed from: F */
    public long f15357F = -1;
    /* renamed from: G */
    public long f15358G = -1;
    /* renamed from: H */
    public String f15359H = "";
    /* renamed from: I */
    public String f15360I = "";
    /* renamed from: J */
    public String f15361J = "";
    /* renamed from: K */
    public String f15362K = "";
    /* renamed from: L */
    public long f15363L = -1;
    /* renamed from: M */
    public boolean f15364M = false;
    /* renamed from: N */
    public Map<String, String> f15365N = null;
    /* renamed from: O */
    public int f15366O = -1;
    /* renamed from: P */
    public int f15367P = -1;
    /* renamed from: Q */
    public Map<String, String> f15368Q = null;
    /* renamed from: R */
    public Map<String, String> f15369R = null;
    /* renamed from: S */
    public byte[] f15370S = null;
    /* renamed from: T */
    public String f15371T = null;
    /* renamed from: U */
    public String f15372U = null;
    /* renamed from: V */
    private String f15373V = "";
    /* renamed from: a */
    public long f15374a = -1;
    /* renamed from: b */
    public int f15375b = 0;
    /* renamed from: c */
    public String f15376c = UUID.randomUUID().toString();
    /* renamed from: d */
    public boolean f15377d = false;
    /* renamed from: e */
    public String f15378e = "";
    /* renamed from: f */
    public String f15379f = "";
    /* renamed from: g */
    public String f15380g = "";
    /* renamed from: h */
    public Map<String, PlugInBean> f15381h = null;
    /* renamed from: i */
    public Map<String, PlugInBean> f15382i = null;
    /* renamed from: j */
    public boolean f15383j = false;
    /* renamed from: k */
    public boolean f15384k = false;
    /* renamed from: l */
    public int f15385l = 0;
    /* renamed from: m */
    public String f15386m = "";
    /* renamed from: n */
    public String f15387n = "";
    /* renamed from: o */
    public String f15388o = "";
    /* renamed from: p */
    public String f15389p = "";
    /* renamed from: q */
    public String f15390q = "";
    /* renamed from: r */
    public long f15391r = -1;
    /* renamed from: s */
    public String f15392s = null;
    /* renamed from: t */
    public int f15393t = 0;
    /* renamed from: u */
    public String f15394u = "";
    /* renamed from: v */
    public String f15395v = "";
    /* renamed from: w */
    public String f15396w = null;
    /* renamed from: x */
    public byte[] f15397x = null;
    /* renamed from: y */
    public Map<String, String> f15398y = null;
    /* renamed from: z */
    public String f15399z = "";

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.CrashDetailBean$1 */
    static class C44221 implements Creator<CrashDetailBean> {
        C44221() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        CrashDetailBean crashDetailBean = (CrashDetailBean) obj;
        if (crashDetailBean != null) {
            long j = this.f15391r - crashDetailBean.f15391r;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }

    public CrashDetailBean(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f15375b = parcel.readInt();
        this.f15376c = parcel.readString();
        this.f15377d = parcel.readByte() == (byte) 1;
        this.f15378e = parcel.readString();
        this.f15379f = parcel.readString();
        this.f15380g = parcel.readString();
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.f15383j = z;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.f15384k = z;
        this.f15385l = parcel.readInt();
        this.f15386m = parcel.readString();
        this.f15387n = parcel.readString();
        this.f15388o = parcel.readString();
        this.f15389p = parcel.readString();
        this.f15390q = parcel.readString();
        this.f15391r = parcel.readLong();
        this.f15392s = parcel.readString();
        this.f15393t = parcel.readInt();
        this.f15394u = parcel.readString();
        this.f15395v = parcel.readString();
        this.f15396w = parcel.readString();
        this.f15398y = C4479y.m17803b(parcel);
        this.f15399z = parcel.readString();
        this.f15352A = parcel.readString();
        this.f15353B = parcel.readLong();
        this.f15354C = parcel.readLong();
        this.f15355D = parcel.readLong();
        this.f15356E = parcel.readLong();
        this.f15357F = parcel.readLong();
        this.f15358G = parcel.readLong();
        this.f15359H = parcel.readString();
        this.f15373V = parcel.readString();
        this.f15360I = parcel.readString();
        this.f15361J = parcel.readString();
        this.f15362K = parcel.readString();
        this.f15363L = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.f15364M = z2;
        this.f15365N = C4479y.m17803b(parcel);
        this.f15381h = C4479y.m17787a(parcel);
        this.f15382i = C4479y.m17787a(parcel);
        this.f15366O = parcel.readInt();
        this.f15367P = parcel.readInt();
        this.f15368Q = C4479y.m17803b(parcel);
        this.f15369R = C4479y.m17803b(parcel);
        this.f15370S = parcel.createByteArray();
        this.f15397x = parcel.createByteArray();
        this.f15371T = parcel.readString();
        this.f15372U = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.f15375b);
        parcel.writeString(this.f15376c);
        parcel.writeByte((byte) (this.f15377d ? 1 : 0));
        parcel.writeString(this.f15378e);
        parcel.writeString(this.f15379f);
        parcel.writeString(this.f15380g);
        if (this.f15383j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f15384k) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.f15385l);
        parcel.writeString(this.f15386m);
        parcel.writeString(this.f15387n);
        parcel.writeString(this.f15388o);
        parcel.writeString(this.f15389p);
        parcel.writeString(this.f15390q);
        parcel.writeLong(this.f15391r);
        parcel.writeString(this.f15392s);
        parcel.writeInt(this.f15393t);
        parcel.writeString(this.f15394u);
        parcel.writeString(this.f15395v);
        parcel.writeString(this.f15396w);
        C4479y.m17805b(parcel, this.f15398y);
        parcel.writeString(this.f15399z);
        parcel.writeString(this.f15352A);
        parcel.writeLong(this.f15353B);
        parcel.writeLong(this.f15354C);
        parcel.writeLong(this.f15355D);
        parcel.writeLong(this.f15356E);
        parcel.writeLong(this.f15357F);
        parcel.writeLong(this.f15358G);
        parcel.writeString(this.f15359H);
        parcel.writeString(this.f15373V);
        parcel.writeString(this.f15360I);
        parcel.writeString(this.f15361J);
        parcel.writeString(this.f15362K);
        parcel.writeLong(this.f15363L);
        if (!this.f15364M) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        C4479y.m17805b(parcel, this.f15365N);
        C4479y.m17788a(parcel, this.f15381h);
        C4479y.m17788a(parcel, this.f15382i);
        parcel.writeInt(this.f15366O);
        parcel.writeInt(this.f15367P);
        C4479y.m17805b(parcel, this.f15368Q);
        C4479y.m17805b(parcel, this.f15369R);
        parcel.writeByteArray(this.f15370S);
        parcel.writeByteArray(this.f15397x);
        parcel.writeString(this.f15371T);
        parcel.writeString(this.f15372U);
    }
}
