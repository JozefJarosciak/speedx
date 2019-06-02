package cn.sharesdk.google;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;

/* compiled from: ParcelWrite */
/* renamed from: cn.sharesdk.google.e */
public class C0650e {
    /* renamed from: b */
    private static void m2477b(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(SupportMenu.CATEGORY_MASK | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    /* renamed from: b */
    private static int m2476b(Parcel parcel, int i) {
        parcel.writeInt(SupportMenu.CATEGORY_MASK | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    /* renamed from: c */
    private static void m2478c(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    /* renamed from: a */
    public static int m2470a(Parcel parcel) {
        return C0650e.m2476b(parcel, 20293);
    }

    /* renamed from: a */
    public static void m2471a(Parcel parcel, int i) {
        C0650e.m2478c(parcel, i);
    }

    /* renamed from: a */
    public static void m2475a(Parcel parcel, int i, boolean z) {
        C0650e.m2477b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    /* renamed from: a */
    public static void m2472a(Parcel parcel, int i, int i2) {
        C0650e.m2477b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    /* renamed from: a */
    public static void m2474a(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int b = C0650e.m2476b(parcel, i);
            parcel.writeString(str);
            C0650e.m2478c(parcel, b);
        } else if (z) {
            C0650e.m2477b(parcel, i, 0);
        }
    }

    /* renamed from: a */
    public static void m2473a(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int b = C0650e.m2476b(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            C0650e.m2478c(parcel, b);
        } else if (z) {
            C0650e.m2477b(parcel, i, 0);
        }
    }
}
