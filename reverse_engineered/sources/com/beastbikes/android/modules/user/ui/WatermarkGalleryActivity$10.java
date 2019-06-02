package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.modules.user.dto.C2412b;
import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.filter.p155b.C2441a;
import java.util.List;

class WatermarkGalleryActivity$10 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ int f11796a;
    /* renamed from: b */
    final /* synthetic */ C2441a f11797b;
    /* renamed from: c */
    final /* synthetic */ boolean f11798c;
    /* renamed from: d */
    final /* synthetic */ LayoutParams f11799d;
    /* renamed from: e */
    final /* synthetic */ List f11800e;
    /* renamed from: f */
    final /* synthetic */ List f11801f;
    /* renamed from: g */
    final /* synthetic */ WatermarkGalleryActivity f11802g;

    WatermarkGalleryActivity$10(WatermarkGalleryActivity watermarkGalleryActivity, int i, C2441a c2441a, boolean z, LayoutParams layoutParams, List list, List list2) {
        this.f11802g = watermarkGalleryActivity;
        this.f11796a = i;
        this.f11797b = c2441a;
        this.f11798c = z;
        this.f11799d = layoutParams;
        this.f11800e = list;
        this.f11801f = list2;
    }

    public void onClick(View view) {
        WatermarkGalleryActivity.u(this.f11802g).removeAllViews();
        WatermarkGalleryActivity.a(this.f11802g, this.f11796a);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        WatermarkGalleryActivity.a(this.f11802g, this.f11797b);
        View c2441a = new C2441a(this.f11802g);
        c2441a.setIsChineseVersion(this.f11798c);
        c2441a.setActivityDto(WatermarkGalleryActivity.v(this.f11802g));
        WatermarkGalleryActivity.b(this.f11802g, c2441a);
        c2441a.setLayoutParams(this.f11799d);
        c2441a.setReverseMode(this.f11797b.m12300a());
        c2441a.setWaterMark((C2412b) this.f11800e.get(this.f11796a));
        if (this.f11801f != null && this.f11801f.size() > 0) {
            for (int i = 0; i < this.f11801f.size(); i++) {
                this.f11802g.a(new C2414c((C2414c) this.f11801f.get(i)), c2441a);
            }
        }
        c2441a.m12301b();
        WatermarkGalleryActivity.u(this.f11802g).addView(c2441a, layoutParams);
    }
}
