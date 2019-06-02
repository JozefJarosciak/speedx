package com.beastbikes.framework.ui.android.utils;

import android.content.Context;
import android.view.View;

public abstract class ViewHolder<T> {
    private final View view;

    public abstract void bind(T t);

    public static <T extends ViewHolder<?>> T as(View view) {
        return (ViewHolder) view.getTag();
    }

    protected ViewHolder(View view) {
        this.view = view;
        view.setTag(this);
        ViewIntrospector.introspect(view, (Object) this);
    }

    public Context getContext() {
        return this.view.getContext();
    }

    public void bind(T t, boolean z) {
    }
}
