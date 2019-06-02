package cn.sharesdk.google;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import java.util.ArrayList;

/* compiled from: PersonStructure */
/* renamed from: cn.sharesdk.google.f */
public class C0651f {
    /* renamed from: a */
    public static int m2480a(Parcel parcel) {
        return parcel.readInt();
    }

    /* renamed from: a */
    public static int m2479a(int i) {
        return 65535 & i;
    }

    /* renamed from: a */
    public static int m2481a(Parcel parcel, int i) {
        if ((i & SupportMenu.CATEGORY_MASK) != SupportMenu.CATEGORY_MASK) {
            return (i >> 16) & 65535;
        }
        return parcel.readInt();
    }

    /* renamed from: b */
    public static void m2486b(Parcel parcel, int i) {
        parcel.setDataPosition(C0651f.m2481a(parcel, i) + parcel.dataPosition());
    }

    /* renamed from: a */
    private static void m2483a(Parcel parcel, int i, int i2) throws Throwable {
        int a = C0651f.m2481a(parcel, i);
        if (a != i2) {
            throw new Throwable("Expected size " + i2 + " got " + a + " (0x" + Integer.toHexString(a) + ")");
        }
    }

    /* renamed from: b */
    public static int m2484b(Parcel parcel) throws Throwable {
        int a = C0651f.m2480a(parcel);
        int a2 = C0651f.m2481a(parcel, a);
        int dataPosition = parcel.dataPosition();
        if (C0651f.m2479a(a) != 20293) {
            throw new Throwable("Expected object header. Got 0x" + Integer.toHexString(a));
        }
        a = dataPosition + a2;
        if (a >= dataPosition && a <= parcel.dataSize()) {
            return a;
        }
        throw new Throwable("Size read is invalid start=" + dataPosition + " end=" + a);
    }

    /* renamed from: c */
    public static boolean m2487c(Parcel parcel, int i) throws Throwable {
        C0651f.m2483a(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    /* renamed from: d */
    public static int m2488d(Parcel parcel, int i) throws Throwable {
        C0651f.m2483a(parcel, i, 4);
        return parcel.readInt();
    }

    /* renamed from: e */
    public static String m2489e(Parcel parcel, int i) {
        int a = C0651f.m2481a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return readString;
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m2482a(Parcel parcel, int i, Creator<T> creator) {
        int a = C0651f.m2481a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return parcelable;
    }

    /* renamed from: b */
    public static <T> ArrayList<T> m2485b(Parcel parcel, int i, Creator<T> creator) {
        int a = C0651f.m2481a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArrayList;
    }
}
