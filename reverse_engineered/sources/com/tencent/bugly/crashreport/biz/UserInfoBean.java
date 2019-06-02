package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.EnvironmentCompat;
import com.tencent.bugly.proguard.C4479y;
import java.util.Map;

/* compiled from: BUGLY */
public class UserInfoBean implements Parcelable {
    public static final Creator<UserInfoBean> CREATOR = new C44051();
    /* renamed from: a */
    public long f15209a;
    /* renamed from: b */
    public int f15210b;
    /* renamed from: c */
    public String f15211c;
    /* renamed from: d */
    public String f15212d;
    /* renamed from: e */
    public long f15213e;
    /* renamed from: f */
    public long f15214f;
    /* renamed from: g */
    public long f15215g;
    /* renamed from: h */
    public long f15216h;
    /* renamed from: i */
    public long f15217i;
    /* renamed from: j */
    public String f15218j;
    /* renamed from: k */
    public long f15219k = 0;
    /* renamed from: l */
    public boolean f15220l = false;
    /* renamed from: m */
    public String f15221m = EnvironmentCompat.MEDIA_UNKNOWN;
    /* renamed from: n */
    public String f15222n;
    /* renamed from: o */
    public int f15223o;
    /* renamed from: p */
    public int f15224p = -1;
    /* renamed from: q */
    public int f15225q = -1;
    /* renamed from: r */
    public Map<String, String> f15226r = null;
    /* renamed from: s */
    public Map<String, String> f15227s = null;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.UserInfoBean$1 */
    static class C44051 implements Creator<UserInfoBean> {
        C44051() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new UserInfoBean[i];
        }
    }

    public UserInfoBean(Parcel parcel) {
        boolean z = true;
        this.f15210b = parcel.readInt();
        this.f15211c = parcel.readString();
        this.f15212d = parcel.readString();
        this.f15213e = parcel.readLong();
        this.f15214f = parcel.readLong();
        this.f15215g = parcel.readLong();
        this.f15216h = parcel.readLong();
        this.f15217i = parcel.readLong();
        this.f15218j = parcel.readString();
        this.f15219k = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.f15220l = z;
        this.f15221m = parcel.readString();
        this.f15224p = parcel.readInt();
        this.f15225q = parcel.readInt();
        this.f15226r = C4479y.m17803b(parcel);
        this.f15227s = C4479y.m17803b(parcel);
        this.f15222n = parcel.readString();
        this.f15223o = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f15210b);
        parcel.writeString(this.f15211c);
        parcel.writeString(this.f15212d);
        parcel.writeLong(this.f15213e);
        parcel.writeLong(this.f15214f);
        parcel.writeLong(this.f15215g);
        parcel.writeLong(this.f15216h);
        parcel.writeLong(this.f15217i);
        parcel.writeString(this.f15218j);
        parcel.writeLong(this.f15219k);
        parcel.writeByte((byte) (this.f15220l ? 1 : 0));
        parcel.writeString(this.f15221m);
        parcel.writeInt(this.f15224p);
        parcel.writeInt(this.f15225q);
        C4479y.m17805b(parcel, this.f15226r);
        C4479y.m17805b(parcel, this.f15227s);
        parcel.writeString(this.f15222n);
        parcel.writeInt(this.f15223o);
    }
}
