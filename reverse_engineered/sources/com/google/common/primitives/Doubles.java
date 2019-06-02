package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Pattern;

@GwtCompatible(emulated = true)
public final class Doubles {
    public static final int BYTES = 8;
    @GwtIncompatible("regular expressions")
    static final Pattern FLOATING_POINT_PATTERN = fpPattern();

    @GwtCompatible
    private static class DoubleArrayAsList extends AbstractList<Double> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final double[] array;
        final int end;
        final int start;

        DoubleArrayAsList(double[] dArr) {
            this(dArr, 0, dArr.length);
        }

        DoubleArrayAsList(double[] dArr, int i, int i2) {
            this.array = dArr;
            this.start = i;
            this.end = i2;
        }

        public int size() {
            return this.end - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public Double get(int i) {
            Preconditions.checkElementIndex(i, size());
            return Double.valueOf(this.array[this.start + i]);
        }

        public boolean contains(Object obj) {
            return (obj instanceof Double) && Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end) != -1;
        }

        public int indexOf(Object obj) {
            if (obj instanceof Double) {
                int access$000 = Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end);
                if (access$000 >= 0) {
                    return access$000 - this.start;
                }
            }
            return -1;
        }

        public int lastIndexOf(Object obj) {
            if (obj instanceof Double) {
                int access$100 = Doubles.lastIndexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end);
                if (access$100 >= 0) {
                    return access$100 - this.start;
                }
            }
            return -1;
        }

        public Double set(int i, Double d) {
            Preconditions.checkElementIndex(i, size());
            double d2 = this.array[this.start + i];
            this.array[this.start + i] = ((Double) Preconditions.checkNotNull(d)).doubleValue();
            return Double.valueOf(d2);
        }

        public List<Double> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            if (i == i2) {
                return Collections.emptyList();
            }
            return new DoubleArrayAsList(this.array, this.start + i, this.start + i2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DoubleArrayAsList)) {
                return super.equals(obj);
            }
            DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) obj;
            int size = size();
            if (doubleArrayAsList.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.array[this.start + i] != doubleArrayAsList.array[doubleArrayAsList.start + i]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 1;
            for (int i2 = this.start; i2 < this.end; i2++) {
                i = (i * 31) + Doubles.hashCode(this.array[i2]);
            }
            return i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(size() * 12);
            stringBuilder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; i++) {
                stringBuilder.append(", ").append(this.array[i]);
            }
            return stringBuilder.append(']').toString();
        }

        double[] toDoubleArray() {
            int size = size();
            Object obj = new double[size];
            System.arraycopy(this.array, this.start, obj, 0, size);
            return obj;
        }
    }

    private static final class DoubleConverter extends Converter<String, Double> implements Serializable {
        static final DoubleConverter INSTANCE = new DoubleConverter();
        private static final long serialVersionUID = 1;

        private DoubleConverter() {
        }

        protected Double doForward(String str) {
            return Double.valueOf(str);
        }

        protected String doBackward(Double d) {
            return d.toString();
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        public int compare(double[] dArr, double[] dArr2) {
            int min = Math.min(dArr.length, dArr2.length);
            for (int i = 0; i < min; i++) {
                int compare = Double.compare(dArr[i], dArr2[i]);
                if (compare != 0) {
                    return compare;
                }
            }
            return dArr.length - dArr2.length;
        }
    }

    private Doubles() {
    }

    public static int hashCode(double d) {
        return Double.valueOf(d).hashCode();
    }

    public static int compare(double d, double d2) {
        return Double.compare(d, d2);
    }

    public static boolean isFinite(double d) {
        int i = 1;
        int i2 = Double.NEGATIVE_INFINITY < d ? 1 : 0;
        if (d >= Double.POSITIVE_INFINITY) {
            i = 0;
        }
        return i & i2;
    }

    public static boolean contains(double[] dArr, double d) {
        for (double d2 : dArr) {
            if (d2 == d) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(double[] dArr, double d) {
        return indexOf(dArr, d, 0, dArr.length);
    }

    private static int indexOf(double[] dArr, double d, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (dArr[i3] == d) {
                return i3;
            }
        }
        return -1;
    }

    public static int indexOf(double[] dArr, double[] dArr2) {
        Preconditions.checkNotNull(dArr, "array");
        Preconditions.checkNotNull(dArr2, "target");
        if (dArr2.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < (dArr.length - dArr2.length) + 1) {
            int i2 = 0;
            while (i2 < dArr2.length) {
                if (dArr[i + i2] != dArr2[i2]) {
                    i++;
                } else {
                    i2++;
                }
            }
            return i;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d) {
        return lastIndexOf(dArr, d, 0, dArr.length);
    }

    private static int lastIndexOf(double[] dArr, double d, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            if (dArr[i3] == d) {
                return i3;
            }
        }
        return -1;
    }

    public static double min(double... dArr) {
        boolean z;
        int i = 1;
        if (dArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        double d = dArr[0];
        while (i < dArr.length) {
            d = Math.min(d, dArr[i]);
            i++;
        }
        return d;
    }

    public static double max(double... dArr) {
        boolean z;
        int i = 1;
        if (dArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        double d = dArr[0];
        while (i < dArr.length) {
            d = Math.max(d, dArr[i]);
            i++;
        }
        return d;
    }

    public static double[] concat(double[]... dArr) {
        int i = 0;
        for (double[] length : dArr) {
            i += length.length;
        }
        Object obj = new double[i];
        i = 0;
        for (Object obj2 : dArr) {
            System.arraycopy(obj2, 0, obj, i, obj2.length);
            i += obj2.length;
        }
        return obj;
    }

    @Beta
    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.INSTANCE;
    }

    public static double[] ensureCapacity(double[] dArr, int i, int i2) {
        boolean z;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Invalid minLength: %s", Integer.valueOf(i));
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Invalid padding: %s", Integer.valueOf(i2));
        return dArr.length < i ? copyOf(dArr, i + i2) : dArr;
    }

    private static double[] copyOf(double[] dArr, int i) {
        Object obj = new double[i];
        System.arraycopy(dArr, 0, obj, 0, Math.min(dArr.length, i));
        return obj;
    }

    public static String join(String str, double... dArr) {
        Preconditions.checkNotNull(str);
        if (dArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(dArr.length * 12);
        stringBuilder.append(dArr[0]);
        for (int i = 1; i < dArr.length; i++) {
            stringBuilder.append(str).append(dArr[i]);
        }
        return stringBuilder.toString();
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static double[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).toDoubleArray();
        }
        Object[] toArray = collection.toArray();
        int length = toArray.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = ((Number) Preconditions.checkNotNull(toArray[i])).doubleValue();
        }
        return dArr;
    }

    public static List<Double> asList(double... dArr) {
        if (dArr.length == 0) {
            return Collections.emptyList();
        }
        return new DoubleArrayAsList(dArr);
    }

    @GwtIncompatible("regular expressions")
    private static Pattern fpPattern() {
        String concat = String.valueOf("(?:\\d++(?:\\.\\d*+)?|\\.\\d++)").concat("(?:[eE][+-]?\\d++)?[fFdD]?");
        String valueOf = String.valueOf(String.valueOf("(?:\\p{XDigit}++(?:\\.\\p{XDigit}*+)?|\\.\\p{XDigit}++)"));
        valueOf = new StringBuilder(valueOf.length() + 25).append("0[xX]").append(valueOf).append("[pP][+-]?\\d++[fFdD]?").toString();
        concat = String.valueOf(String.valueOf(concat));
        valueOf = String.valueOf(String.valueOf(valueOf));
        return Pattern.compile(new StringBuilder((concat.length() + 23) + valueOf.length()).append("[+-]?(?:NaN|Infinity|").append(concat).append("|").append(valueOf).append(")").toString());
    }

    @GwtIncompatible("regular expressions")
    @Beta
    public static Double tryParse(String str) {
        if (FLOATING_POINT_PATTERN.matcher(str).matches()) {
            try {
                return Double.valueOf(Double.parseDouble(str));
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }
}
