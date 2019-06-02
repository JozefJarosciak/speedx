package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class zzamp {
    private final Field bdR;

    public zzamp(Field field) {
        zzanq.zzaa(field);
        this.bdR = field;
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return this.bdR.getAnnotation(cls);
    }
}
