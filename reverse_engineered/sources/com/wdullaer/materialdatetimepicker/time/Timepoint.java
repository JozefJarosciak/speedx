package com.wdullaer.materialdatetimepicker.time;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;

public class Timepoint implements Parcelable, Comparable<Timepoint> {
    public static final Creator<Timepoint> CREATOR = new C48111();
    /* renamed from: a */
    private int f16909a;
    /* renamed from: b */
    private int f16910b;
    /* renamed from: c */
    private int f16911c;

    /* renamed from: com.wdullaer.materialdatetimepicker.time.Timepoint$1 */
    static class C48111 implements Creator<Timepoint> {
        C48111() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18904a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18905a(i);
        }

        /* renamed from: a */
        public Timepoint m18904a(Parcel parcel) {
            return new Timepoint(parcel);
        }

        /* renamed from: a */
        public Timepoint[] m18905a(int i) {
            return new Timepoint[i];
        }
    }

    public enum TYPE {
        HOUR,
        MINUTE,
        SECOND
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m18907a((Timepoint) obj);
    }

    public Timepoint(Timepoint timepoint) {
        this(timepoint.f16909a, timepoint.f16910b, timepoint.f16911c);
    }

    public Timepoint(int i, int i2, int i3) {
        this.f16909a = i % 24;
        this.f16910b = i2 % 60;
        this.f16911c = i3 % 60;
    }

    public Timepoint(int i, int i2) {
        this(i, i2, 0);
    }

    public Timepoint(int i) {
        this(i, 0);
    }

    public Timepoint(Parcel parcel) {
        this.f16909a = parcel.readInt();
        this.f16910b = parcel.readInt();
        this.f16911c = parcel.readInt();
    }

    /* renamed from: a */
    public int m18906a() {
        return this.f16909a;
    }

    /* renamed from: b */
    public int m18908b() {
        return this.f16910b;
    }

    /* renamed from: c */
    public int m18909c() {
        return this.f16911c;
    }

    /* renamed from: d */
    public boolean m18910d() {
        return this.f16909a < 12;
    }

    /* renamed from: e */
    public boolean m18911e() {
        return this.f16909a >= 12 && this.f16909a < 24;
    }

    /* renamed from: f */
    public void m18912f() {
        if (this.f16909a >= 12) {
            this.f16909a %= 12;
        }
    }

    /* renamed from: g */
    public void m18913g() {
        if (this.f16909a < 12) {
            this.f16909a = (this.f16909a + 12) % 24;
        }
    }

    public boolean equals(Object obj) {
        try {
            Timepoint timepoint = (Timepoint) obj;
            if (timepoint.m18906a() == this.f16909a && timepoint.m18908b() == this.f16910b && timepoint.m18909c() == this.f16911c) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    /* renamed from: a */
    public int m18907a(@NonNull Timepoint timepoint) {
        return (((this.f16909a - timepoint.f16909a) * 3600) + ((this.f16910b - timepoint.f16910b) * 60)) + (this.f16911c - timepoint.f16911c);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f16909a);
        parcel.writeInt(this.f16910b);
        parcel.writeInt(this.f16911c);
    }

    public int describeContents() {
        return 0;
    }
}
