package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collection;

@GwtCompatible(emulated = true)
public final class ObjectArrays {
    static final Object[] EMPTY_ARRAY = new Object[0];

    private ObjectArrays() {
    }

    @GwtIncompatible("Array.newInstance(Class, int)")
    public static <T> T[] newArray(Class<T> cls, int i) {
        return (Object[]) Array.newInstance(cls, i);
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        return Platform.newArray(tArr, i);
    }

    @GwtIncompatible("Array.newInstance(Class, int)")
    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        Object newArray = newArray((Class) cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, newArray, 0, tArr.length);
        System.arraycopy(tArr2, 0, newArray, tArr.length, tArr2.length);
        return newArray;
    }

    public static <T> T[] concat(T t, T[] tArr) {
        Object newArray = newArray((Object[]) tArr, tArr.length + 1);
        newArray[0] = t;
        System.arraycopy(tArr, 0, newArray, 1, tArr.length);
        return newArray;
    }

    public static <T> T[] concat(T[] tArr, T t) {
        T[] arraysCopyOf = arraysCopyOf(tArr, tArr.length + 1);
        arraysCopyOf[tArr.length] = t;
        return arraysCopyOf;
    }

    static <T> T[] arraysCopyOf(T[] tArr, int i) {
        Object newArray = newArray((Object[]) tArr, i);
        System.arraycopy(tArr, 0, newArray, 0, Math.min(tArr.length, i));
        return newArray;
    }

    static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = newArray((Object[]) tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    static <T> T[] toArrayImpl(Object[] objArr, int i, int i2, T[] tArr) {
        Preconditions.checkPositionIndexes(i, i + i2, objArr.length);
        if (tArr.length < i2) {
            tArr = newArray((Object[]) tArr, i2);
        } else if (tArr.length > i2) {
            tArr[i2] = null;
        }
        System.arraycopy(objArr, i, tArr, 0, i2);
        return tArr;
    }

    static Object[] toArrayImpl(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }

    static Object[] copyAsObjectArray(Object[] objArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, objArr.length);
        if (i2 == 0) {
            return EMPTY_ARRAY;
        }
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, i, objArr2, 0, i2);
        return objArr2;
    }

    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        int i = 0;
        for (Object obj : iterable) {
            int i2 = i + 1;
            objArr[i] = obj;
            i = i2;
        }
        return objArr;
    }

    static void swap(Object[] objArr, int i, int i2) {
        Object obj = objArr[i];
        objArr[i] = objArr[i2];
        objArr[i2] = obj;
    }

    static Object[] checkElementsNotNull(Object... objArr) {
        return checkElementsNotNull(objArr, objArr.length);
    }

    static Object[] checkElementsNotNull(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            checkElementNotNull(objArr[i2], i2);
        }
        return objArr;
    }

    static Object checkElementNotNull(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }
}
