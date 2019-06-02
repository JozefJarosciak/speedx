package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1114l;
import com.baidu.platform.comapi.map.C1235E;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.baidu.mapapi.map.u */
class C1138u implements C1114l {
    /* renamed from: a */
    final /* synthetic */ WearMapView f3279a;

    C1138u(WearMapView wearMapView) {
        this.f3279a = wearMapView;
    }

    /* renamed from: a */
    public void mo2625a() {
        if (this.f3279a.f3219d != null && this.f3279a.f3219d.m4760a() != null) {
            float f = this.f3279a.f3219d.m4760a().m4660D().f3678a;
            if (this.f3279a.f3235x != f) {
                CharSequence format;
                int intValue = ((Integer) WearMapView.f3212u.get((int) f)).intValue();
                int i = (int) (((double) intValue) / this.f3279a.f3219d.m4760a().m4660D().f3690m);
                this.f3279a.f3231p.setPadding(i / 2, 0, i / 2, 0);
                if (intValue >= 1000) {
                    format = String.format(" %d公里 ", new Object[]{Integer.valueOf(intValue / 1000)});
                } else {
                    format = String.format(" %d米 ", new Object[]{Integer.valueOf(intValue)});
                }
                this.f3279a.f3229n.setText(format);
                this.f3279a.f3230o.setText(format);
                this.f3279a.f3235x = f;
            }
            this.f3279a.requestLayout();
        }
    }

    /* renamed from: a */
    public void mo2626a(Bitmap bitmap) {
    }

    /* renamed from: a */
    public void mo2627a(MotionEvent motionEvent) {
    }

    /* renamed from: a */
    public void mo2628a(GeoPoint geoPoint) {
    }

    /* renamed from: a */
    public void mo2629a(C1235E c1235e) {
    }

    /* renamed from: a */
    public void mo2630a(String str) {
    }

    /* renamed from: a */
    public void mo2631a(GL10 gl10, C1235E c1235e) {
    }

    /* renamed from: a */
    public void mo2632a(boolean z) {
    }

    /* renamed from: b */
    public void mo2633b() {
    }

    /* renamed from: b */
    public void mo2634b(GeoPoint geoPoint) {
    }

    /* renamed from: b */
    public void mo2635b(C1235E c1235e) {
    }

    /* renamed from: b */
    public boolean mo2636b(String str) {
        return false;
    }

    /* renamed from: c */
    public void mo2637c() {
    }

    /* renamed from: c */
    public void mo2638c(GeoPoint geoPoint) {
    }

    /* renamed from: c */
    public void mo2639c(C1235E c1235e) {
    }

    /* renamed from: d */
    public void mo2640d() {
    }

    /* renamed from: d */
    public void mo2641d(GeoPoint geoPoint) {
    }

    /* renamed from: e */
    public void mo2642e() {
    }

    /* renamed from: e */
    public void mo2643e(GeoPoint geoPoint) {
    }

    /* renamed from: f */
    public void mo2644f() {
    }
}
