package com.beastbikes.android.sphere.restful.p078a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: HttpGet */
/* renamed from: com.beastbikes.android.sphere.restful.a.c */
public @interface C1446c {
    /* renamed from: a */
    String m7975a();

    /* renamed from: b */
    String m7976b() default "UTF-8";
}
