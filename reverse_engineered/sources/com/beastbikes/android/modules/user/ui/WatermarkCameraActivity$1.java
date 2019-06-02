package com.beastbikes.android.modules.user.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.beastbikes.framework.android.p088g.C2801d;

class WatermarkCameraActivity$1 implements OnGlobalLayoutListener {
    /* renamed from: a */
    final /* synthetic */ View f11787a;
    /* renamed from: b */
    final /* synthetic */ View f11788b;
    /* renamed from: c */
    final /* synthetic */ Context f11789c;
    /* renamed from: d */
    final /* synthetic */ WatermarkCameraActivity f11790d;

    WatermarkCameraActivity$1(WatermarkCameraActivity watermarkCameraActivity, View view, View view2, Context context) {
        this.f11790d = watermarkCameraActivity;
        this.f11787a = view;
        this.f11788b = view2;
        this.f11789c = context;
    }

    @SuppressLint({"NewApi"})
    public void onGlobalLayout() {
        int width = WatermarkCameraActivity.a(this.f11790d).getWidth();
        WatermarkCameraActivity.a(this.f11790d, WatermarkCameraActivity.a(this.f11790d).getHeight());
        WatermarkCameraActivity.b(this.f11790d, (WatermarkCameraActivity.b(this.f11790d) - width) / 2);
        this.f11787a.getLayoutParams().height = WatermarkCameraActivity.c(this.f11790d);
        this.f11788b.getLayoutParams().height = WatermarkCameraActivity.c(this.f11790d);
        WatermarkCameraActivity.d(this.f11790d).getLayoutParams().width = width;
        WatermarkCameraActivity.d(this.f11790d).getLayoutParams().height = WatermarkCameraActivity.b(this.f11790d);
        if (VERSION.SDK_INT >= 16) {
            WatermarkCameraActivity.a(this.f11790d).getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            WatermarkCameraActivity.a(this.f11790d).getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(new MarginLayoutParams(-1, -2));
        layoutParams.topMargin = -(WatermarkCameraActivity.c(this.f11790d) - C2801d.m13756a(this.f11789c, 45.0f));
        WatermarkCameraActivity.e(this.f11790d).setLayoutParams(layoutParams);
        WatermarkCameraActivity.d(this.f11790d).setOffset(WatermarkCameraActivity.c(this.f11790d));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) WatermarkCameraActivity.f(this.f11790d).getLayoutParams();
        marginLayoutParams.topMargin = WatermarkCameraActivity.a(this.f11790d).getWidth();
        WatermarkCameraActivity.f(this.f11790d).setLayoutParams(marginLayoutParams);
    }
}
