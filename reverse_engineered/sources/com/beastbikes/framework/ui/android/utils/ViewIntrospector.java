package com.beastbikes.framework.ui.android.utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ViewIntrospector {
    private static final String FIND_VIEW_BY_ID = "findViewById";
    private final Method findViewById;
    private final Object view;

    public static void introspect(View view, Object obj) {
        new ViewIntrospector(view).introspect(obj);
    }

    public static void introspect(Activity activity, Object obj) {
        new ViewIntrospector(activity).introspect(obj);
    }

    public static void introspect(Window window, Object obj) {
        new ViewIntrospector(window).introspect(obj);
    }

    public ViewIntrospector(View view) {
        this.view = view;
        try {
            this.findViewById = view.getClass().getMethod(FIND_VIEW_BY_ID, new Class[]{Integer.TYPE});
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ViewIntrospector(Activity activity) {
        this.view = activity;
        try {
            this.findViewById = activity.getClass().getMethod(FIND_VIEW_BY_ID, new Class[]{Integer.TYPE});
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ViewIntrospector(Window window) {
        this.view = window;
        try {
            this.findViewById = window.getClass().getMethod(FIND_VIEW_BY_ID, new Class[]{Integer.TYPE});
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void introspect(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (View.class.isAssignableFrom(field.getType()) && ((C1458a) field.getAnnotation(C1458a.class)) != null) {
                field.setAccessible(true);
                try {
                    field.set(obj, this.findViewById.invoke(this.view, new Object[]{Integer.valueOf(r0.a())}));
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
