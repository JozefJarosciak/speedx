package io.rong.push.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcelUtils {
    public static final int EXIST_SEPARATOR = 1;
    public static final int NON_SEPARATOR = 0;

    public static void writeToParcel(Parcel parcel, String str) {
        if (str != null) {
            parcel.writeInt(1);
            parcel.writeString(str);
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Long l) {
        if (l != null) {
            parcel.writeInt(1);
            parcel.writeLong(l.longValue());
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Integer num) {
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Float f) {
        if (f != null) {
            parcel.writeInt(1);
            parcel.writeFloat(f.floatValue());
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Double d) {
        if (d != null) {
            parcel.writeInt(1);
            parcel.writeDouble(d.doubleValue());
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Map map) {
        if (map != null) {
            parcel.writeInt(1);
            parcel.writeMap(map);
            return;
        }
        parcel.writeInt(0);
    }

    public static void writeToParcel(Parcel parcel, Date date) {
        if (date != null) {
            parcel.writeInt(1);
            parcel.writeLong(date.getTime());
            return;
        }
        parcel.writeInt(0);
    }

    public static Float readFloatFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? Float.valueOf(parcel.readFloat()) : null;
    }

    public static Double readDoubleFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? Double.valueOf(parcel.readDouble()) : null;
    }

    public static Date readDateFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? new Date(parcel.readLong()) : null;
    }

    public static Integer readIntFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? Integer.valueOf(parcel.readInt()) : null;
    }

    public static Long readLongFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? Long.valueOf(parcel.readLong()) : null;
    }

    public static String readFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? parcel.readString() : null;
    }

    public static Map readMapFromParcel(Parcel parcel) {
        return parcel.readInt() == 1 ? parcel.readHashMap(HashMap.class.getClassLoader()) : null;
    }

    public static <T extends Parcelable> T readFromParcel(Parcel parcel, Class<T> cls) {
        if (parcel.readInt() == 1) {
            return parcel.readParcelable(cls.getClassLoader());
        }
        return null;
    }

    public static <T extends Parcelable> void writeToParcel(Parcel parcel, T t) {
        if (t != null) {
            parcel.writeInt(1);
            parcel.writeParcelable(t, 0);
            return;
        }
        parcel.writeInt(0);
    }

    public static <T extends List<?>> void writeToParcel(Parcel parcel, T t) {
        if (t != null) {
            parcel.writeInt(1);
            parcel.writeList(t);
            return;
        }
        parcel.writeInt(0);
    }

    public static <T> ArrayList<T> readListFromParcel(Parcel parcel, Class<T> cls) {
        return parcel.readInt() == 1 ? parcel.readArrayList(cls.getClassLoader()) : null;
    }

    public static void writeListToParcel(Parcel parcel, List<?> list) {
        if (list != null) {
            parcel.writeInt(1);
            parcel.writeList(list);
            return;
        }
        parcel.writeInt(0);
    }

    public static <T extends Parcelable> T bytesToParcelable(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T readFromParcel = readFromParcel(obtain, cls);
        obtain.recycle();
        return readFromParcel;
    }

    public static byte[] parcelableToByte(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, parcelable);
        return obtain.marshall();
    }

    public static <T extends Parcelable> List<T> bytesToParcelableList(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        List readListFromParcel = readListFromParcel(obtain, cls);
        obtain.recycle();
        return readListFromParcel;
    }

    public static byte[] parcelableListToByte(List<? extends Parcelable> list) {
        if (list == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        writeListToParcel(obtain, list);
        return obtain.marshall();
    }
}
