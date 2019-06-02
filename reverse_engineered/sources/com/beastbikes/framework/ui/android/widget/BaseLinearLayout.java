package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;

public abstract class BaseLinearLayout extends LinearLayout {
    public abstract void destroy();

    public abstract void onCreateView();

    public BaseLinearLayout(Context context) {
        super(context);
        introspect();
    }

    public BaseLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        introspect();
    }

    private void introspect() {
        C1459b c1459b = (C1459b) getClass().getAnnotation(C1459b.class);
        if (c1459b != null) {
            LayoutInflater.from(getContext()).inflate(c1459b.a(), this);
        }
        ViewIntrospector.introspect((View) this, (Object) this);
        onCreateView();
    }
}
