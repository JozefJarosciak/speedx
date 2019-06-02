package com.github.mikephil.charting.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Entry extends C3219f implements Parcelable {
    public static final Creator<Entry> CREATOR = new C32221();
    /* renamed from: a */
    private float f13984a = 0.0f;

    /* renamed from: com.github.mikephil.charting.data.Entry$1 */
    static class C32221 implements Creator<Entry> {
        C32221() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15547a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15548a(i);
        }

        /* renamed from: a */
        public Entry m15547a(Parcel parcel) {
            return new Entry(parcel);
        }

        /* renamed from: a */
        public Entry[] m15548a(int i) {
            return new Entry[i];
        }
    }

    public Entry(float f, float f2) {
        super(f2);
        this.f13984a = f;
    }

    public Entry(float f, float f2, Object obj) {
        super(f2, obj);
        this.f13984a = f;
    }

    /* renamed from: i */
    public float m15450i() {
        return this.f13984a;
    }

    public String toString() {
        return "Entry, x: " + this.f13984a + " y: " + mo3912b();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f13984a);
        parcel.writeFloat(mo3912b());
        if (m15449h() == null) {
            parcel.writeInt(0);
        } else if (m15449h() instanceof Parcelable) {
            parcel.writeInt(1);
            parcel.writeParcelable((Parcelable) m15449h(), i);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    protected Entry(Parcel parcel) {
        this.f13984a = parcel.readFloat();
        m15445a(parcel.readFloat());
        if (parcel.readInt() == 1) {
            m15446a((Object) parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
