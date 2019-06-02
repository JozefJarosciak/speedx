package com.google.gson.jpush;

import java.lang.reflect.Field;
import java.util.Locale;

/* renamed from: com.google.gson.jpush.h */
enum C3989h extends C3985d {
    C3989h(String str, int i) {
        super(str, 3);
    }

    /* renamed from: a */
    public final String mo5676a(Field field) {
        return C3985d.m16166a(field.getName(), "_").toLowerCase(Locale.ENGLISH);
    }
}
