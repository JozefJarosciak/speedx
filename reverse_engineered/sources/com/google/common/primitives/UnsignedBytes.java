package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Comparator;
import sun.misc.Unsafe;

public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = (byte) -1;
    private static final int UNSIGNED_MASK = 255;

    @VisibleForTesting
    static class LexicographicalComparatorHolder {
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();
        static final String UNSAFE_COMPARATOR_NAME = String.valueOf(LexicographicalComparatorHolder.class.getName()).concat("$UnsafeComparator");

        enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < min; i++) {
                    int compare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        @VisibleForTesting
        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = false;
            static final int BYTE_ARRAY_BASE_OFFSET = 0;
            static final Unsafe theUnsafe = null;

            /* renamed from: com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1 */
            static class C38091 implements PrivilegedExceptionAction<Unsafe> {
                C38091() {
                }

                public Unsafe run() throws Exception {
                    Class cls = Unsafe.class;
                    for (Field field : cls.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (cls.isInstance(obj)) {
                            return (Unsafe) cls.cast(obj);
                        }
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }
            }

            static {
                BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
                theUnsafe = getUnsafe();
                BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
                if (theUnsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new AssertionError();
                }
            }

            private static Unsafe getUnsafe() {
                Unsafe unsafe;
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException e) {
                    try {
                        unsafe = (Unsafe) AccessController.doPrivileged(new C38091());
                    } catch (PrivilegedActionException e2) {
                        throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                    }
                }
                return unsafe;
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i = min / 8;
                int i2 = 0;
                while (i2 < i * 8) {
                    long j = theUnsafe.getLong(bArr, ((long) BYTE_ARRAY_BASE_OFFSET) + ((long) i2));
                    long j2 = theUnsafe.getLong(bArr2, ((long) BYTE_ARRAY_BASE_OFFSET) + ((long) i2));
                    if (j == j2) {
                        i2 += 8;
                    } else if (BIG_ENDIAN) {
                        return UnsignedLongs.compare(j, j2);
                    } else {
                        i2 = Long.numberOfTrailingZeros(j ^ j2) & -8;
                        return (int) (((j >>> i2) & 255) - ((j2 >>> i2) & 255));
                    }
                }
                for (i *= 8; i < min; i++) {
                    i2 = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (i2 != 0) {
                        return i2;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        LexicographicalComparatorHolder() {
        }

        static Comparator<byte[]> getBestComparator() {
            try {
                return (Comparator) Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants()[0];
            } catch (Throwable th) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    private UnsignedBytes() {
    }

    public static int toInt(byte b) {
        return b & 255;
    }

    public static byte checkedCast(long j) {
        if ((j >> 8) == 0) {
            return (byte) ((int) j);
        }
        throw new IllegalArgumentException("Out of range: " + j);
    }

    public static byte saturatedCast(long j) {
        if (j > ((long) toInt((byte) -1))) {
            return (byte) -1;
        }
        if (j < 0) {
            return (byte) 0;
        }
        return (byte) ((int) j);
    }

    public static int compare(byte b, byte b2) {
        return toInt(b) - toInt(b2);
    }

    public static byte min(byte... bArr) {
        boolean z;
        int i = 1;
        if (bArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        int toInt = toInt(bArr[0]);
        while (i < bArr.length) {
            int toInt2 = toInt(bArr[i]);
            if (toInt2 < toInt) {
                toInt = toInt2;
            }
            i++;
        }
        return (byte) toInt;
    }

    public static byte max(byte... bArr) {
        boolean z;
        int i = 1;
        if (bArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        int toInt = toInt(bArr[0]);
        while (i < bArr.length) {
            int toInt2 = toInt(bArr[i]);
            if (toInt2 > toInt) {
                toInt = toInt2;
            }
            i++;
        }
        return (byte) toInt;
    }

    @Beta
    public static String toString(byte b) {
        return toString(b, 10);
    }

    @Beta
    public static String toString(byte b, int i) {
        boolean z;
        if (i < 2 || i > 36) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", Integer.valueOf(i));
        return Integer.toString(toInt(b), i);
    }

    @Beta
    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    @Beta
    public static byte parseUnsignedByte(String str, int i) {
        int parseInt = Integer.parseInt((String) Preconditions.checkNotNull(str), i);
        if ((parseInt >> 8) == 0) {
            return (byte) parseInt;
        }
        throw new NumberFormatException("out of range: " + parseInt);
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * (str.length() + 3));
        stringBuilder.append(toInt(bArr[0]));
        for (int i = 1; i < bArr.length; i++) {
            stringBuilder.append(str).append(toString(bArr[i]));
        }
        return stringBuilder.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return PureJavaComparator.INSTANCE;
    }
}
