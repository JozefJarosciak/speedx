package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.C4479y;
import java.util.Map;

/* compiled from: BUGLY */
public class StrategyBean implements Parcelable {
    public static final Creator<StrategyBean> CREATOR = new C44191();
    /* renamed from: a */
    public static String f15314a = "http://rqd.uu.qq.com/rqd/sync";
    /* renamed from: b */
    public static String f15315b = "http://android.bugly.qq.com/rqd/async";
    /* renamed from: c */
    public static String f15316c = "http://android.bugly.qq.com/rqd/async";
    /* renamed from: d */
    public static String f15317d;
    /* renamed from: e */
    public long f15318e;
    /* renamed from: f */
    public long f15319f;
    /* renamed from: g */
    public boolean f15320g;
    /* renamed from: h */
    public boolean f15321h;
    /* renamed from: i */
    public boolean f15322i;
    /* renamed from: j */
    public boolean f15323j;
    /* renamed from: k */
    public boolean f15324k;
    /* renamed from: l */
    public boolean f15325l;
    /* renamed from: m */
    public boolean f15326m;
    /* renamed from: n */
    public boolean f15327n;
    /* renamed from: o */
    public boolean f15328o;
    /* renamed from: p */
    public long f15329p;
    /* renamed from: q */
    public long f15330q;
    /* renamed from: r */
    public String f15331r;
    /* renamed from: s */
    public String f15332s;
    /* renamed from: t */
    public String f15333t;
    /* renamed from: u */
    public String f15334u;
    /* renamed from: v */
    public Map<String, String> f15335v;
    /* renamed from: w */
    public int f15336w;
    /* renamed from: x */
    public long f15337x;
    /* renamed from: y */
    public long f15338y;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.common.strategy.StrategyBean$1 */
    static class C44191 implements Creator<StrategyBean> {
        C44191() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new StrategyBean[i];
        }
    }

    public StrategyBean() {
        this.f15318e = -1;
        this.f15319f = -1;
        this.f15320g = true;
        this.f15321h = true;
        this.f15322i = true;
        this.f15323j = true;
        this.f15324k = false;
        this.f15325l = true;
        this.f15326m = true;
        this.f15327n = true;
        this.f15328o = true;
        this.f15330q = 30000;
        this.f15331r = f15315b;
        this.f15332s = f15316c;
        this.f15333t = f15314a;
        this.f15336w = 10;
        this.f15337x = 300000;
        this.f15338y = -1;
        this.f15319f = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("S(@L@L").append("@)");
        f15317d = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("*^@K#K").append("@!");
        this.f15334u = stringBuilder.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z = true;
        this.f15318e = -1;
        this.f15319f = -1;
        this.f15320g = true;
        this.f15321h = true;
        this.f15322i = true;
        this.f15323j = true;
        this.f15324k = false;
        this.f15325l = true;
        this.f15326m = true;
        this.f15327n = true;
        this.f15328o = true;
        this.f15330q = 30000;
        this.f15331r = f15315b;
        this.f15332s = f15316c;
        this.f15333t = f15314a;
        this.f15336w = 10;
        this.f15337x = 300000;
        this.f15338y = -1;
        try {
            boolean z2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("S(@L@L").append("@)");
            f15317d = stringBuilder.toString();
            this.f15319f = parcel.readLong();
            this.f15320g = parcel.readByte() == (byte) 1;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15321h = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15322i = z2;
            this.f15331r = parcel.readString();
            this.f15332s = parcel.readString();
            this.f15334u = parcel.readString();
            this.f15335v = C4479y.m17803b(parcel);
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15323j = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15324k = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15327n = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15328o = z2;
            this.f15330q = parcel.readLong();
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f15325l = z2;
            if (parcel.readByte() != (byte) 1) {
                z = false;
            }
            this.f15326m = z;
            this.f15329p = parcel.readLong();
            this.f15336w = parcel.readInt();
            this.f15337x = parcel.readLong();
            this.f15338y = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeLong(this.f15319f);
        parcel.writeByte((byte) (this.f15320g ? 1 : 0));
        if (this.f15321h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f15322i) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.f15331r);
        parcel.writeString(this.f15332s);
        parcel.writeString(this.f15334u);
        C4479y.m17805b(parcel, this.f15335v);
        if (this.f15323j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f15324k) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f15327n) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f15328o) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeLong(this.f15330q);
        if (this.f15325l) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.f15326m) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeLong(this.f15329p);
        parcel.writeInt(this.f15336w);
        parcel.writeLong(this.f15337x);
        parcel.writeLong(this.f15338y);
    }
}
