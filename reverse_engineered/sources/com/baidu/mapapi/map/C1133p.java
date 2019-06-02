package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks;

/* renamed from: com.baidu.mapapi.map.p */
class C1133p implements DismissCallbacks {
    /* renamed from: a */
    final /* synthetic */ SwipeDismissView f3270a;

    C1133p(SwipeDismissView swipeDismissView) {
        this.f3270a = swipeDismissView;
    }

    public boolean canDismiss(Object obj) {
        return true;
    }

    public void onDismiss(View view, Object obj) {
        if (this.f3270a.f3127a != null) {
            this.f3270a.f3127a.onDismiss();
        }
    }

    public void onNotify() {
        if (this.f3270a.f3127a != null) {
            this.f3270a.f3127a.onNotify();
        }
    }
}
