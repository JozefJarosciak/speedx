package com.baidu.mapapi.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mapapi.map.WearMapView.OnDismissCallback;

public class SwipeDismissView extends RelativeLayout {
    /* renamed from: a */
    OnDismissCallback f3127a = null;

    public SwipeDismissView(Context context, AttributeSet attributeSet, int i, View view) {
        super(context, attributeSet, i);
        m4175a(context, view);
    }

    public SwipeDismissView(Context context, AttributeSet attributeSet, View view) {
        super(context, attributeSet);
        m4175a(context, view);
    }

    public SwipeDismissView(Context context, View view) {
        super(context);
        m4175a(context, view);
    }

    /* renamed from: a */
    void m4175a(Context context, View view) {
        setOnTouchListener(new SwipeDismissTouchListener(view, new Object(), new C1133p(this)));
    }

    public void setCallback(OnDismissCallback onDismissCallback) {
        this.f3127a = onDismissCallback;
    }
}
