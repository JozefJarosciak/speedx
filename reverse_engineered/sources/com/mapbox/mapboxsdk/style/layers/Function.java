package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import java.util.HashMap;
import java.util.Map;

public class Function<T> {
    private Float base;
    private final Function$Stop<Float, T>[] stops;

    @SafeVarargs
    public static <T> Function<T> zoom(@Size(min = 1) @NonNull Function$Stop<Float, T>... function$StopArr) {
        return new Function(function$StopArr);
    }

    @SafeVarargs
    public static <T> Function<T> zoom(@FloatRange(from = 0.0d, fromInclusive = false, to = 1.0d, toInclusive = false) float f, @Size(min = 1) @NonNull Function$Stop<Float, T>... function$StopArr) {
        return new Function(function$StopArr).withBase(f);
    }

    public static <T> Function$Stop<Float, T> stop(float f, Property<T> property) {
        return new Function$Stop(Float.valueOf(f), property.value);
    }

    Function(@Size(min = 1) @NonNull Function$Stop<Float, T>[] function$StopArr) {
        this.stops = function$StopArr;
    }

    Function<T> withBase(float f) {
        this.base = Float.valueOf(f);
        return this;
    }

    @Nullable
    public Float getBase() {
        return this.base;
    }

    public Function$Stop<Float, T>[] getStops() {
        return this.stops;
    }

    Map<String, Object> toValueObject() {
        Object obj = new Object[this.stops.length];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = this.stops[i].toValueObject();
        }
        Map<String, Object> hashMap = new HashMap();
        if (this.base != null) {
            hashMap.put("base", this.base);
        }
        hashMap.put("stops", obj);
        return hashMap;
    }
}
