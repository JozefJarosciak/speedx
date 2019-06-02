package com.google.gson.jpush.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.google.gson.jpush.annotations.c */
public @interface C1481c {
    /* renamed from: a */
    String m8259a();

    /* renamed from: b */
    String[] m8260b() default {};
}
