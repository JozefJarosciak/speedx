package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.Nullable;
import android.util.Log;

public class PropertyValue<T> {
    private static final String TAG = PropertyValue.class.getSimpleName();
    private final Object value;

    PropertyValue(Object obj) {
        this.value = obj;
    }

    public boolean isNull() {
        return this.value == null;
    }

    public boolean isFunction() {
        return !isNull() && (this.value instanceof Function);
    }

    public boolean isValue() {
        return (isNull() || isFunction()) ? false : true;
    }

    @Nullable
    public Function<T> getFunction() {
        if (isFunction()) {
            return (Function) this.value;
        }
        Log.w(TAG, "not a function, try value");
        return null;
    }

    @Nullable
    public T getValue() {
        if (isValue()) {
            return this.value;
        }
        Log.w(TAG, "not a value, try function");
        return null;
    }

    public String toString() {
        String str = "%s (%s)";
        Object[] objArr = new Object[2];
        objArr[0] = getClass().getSimpleName();
        objArr[1] = this.value != null ? this.value.getClass().getSimpleName() : null;
        return String.format(str, objArr);
    }
}
