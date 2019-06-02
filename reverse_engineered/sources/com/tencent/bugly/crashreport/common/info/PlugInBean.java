package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: BUGLY */
public class PlugInBean implements Parcelable {
    public static final Creator<PlugInBean> CREATOR = new C44161();
    /* renamed from: a */
    public final String f15257a;
    /* renamed from: b */
    public final String f15258b;
    /* renamed from: c */
    public final String f15259c;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.common.info.PlugInBean$1 */
    static class C44161 implements Creator<PlugInBean> {
        C44161() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PlugInBean[i];
        }
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f15257a = str;
        this.f15258b = str2;
        this.f15259c = str3;
    }

    public String toString() {
        return "plid:" + this.f15257a + " plV:" + this.f15258b + " plUUID:" + this.f15259c;
    }

    public PlugInBean(Parcel parcel) {
        this.f15257a = parcel.readString();
        this.f15258b = parcel.readString();
        this.f15259c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f15257a);
        parcel.writeString(this.f15258b);
        parcel.writeString(this.f15259c);
    }
}
