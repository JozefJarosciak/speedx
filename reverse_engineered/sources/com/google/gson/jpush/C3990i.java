package com.google.gson.jpush;

import java.lang.reflect.Field;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: com.google.gson.jpush.i */
enum C3990i extends C3985d {
    C3990i(String str, int i) {
        super(str, 4);
    }

    /* renamed from: a */
    public final String mo5676a(Field field) {
        return C3985d.m16166a(field.getName(), HelpFormatter.DEFAULT_OPT_PREFIX).toLowerCase(Locale.ENGLISH);
    }
}
