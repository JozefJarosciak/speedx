package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ResizeLayout extends LinearLayout {
    /* renamed from: a */
    private OnResizeListener f1241a;

    public interface OnResizeListener {
        void OnResize(int i, int i2, int i3, int i4);
    }

    /* renamed from: a */
    public void m2013a(OnResizeListener onResizeListener) {
        this.f1241a = onResizeListener;
    }

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f1241a != null) {
            this.f1241a.OnResize(i, i2, i3, i4);
        }
    }
}
