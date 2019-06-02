package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;

/* compiled from: OfflineMapManagerAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.d$a$1 */
class d$a$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f11036a;
    /* renamed from: b */
    final /* synthetic */ d$a f11037b;

    d$a$1(d$a d_a, C2621c c2621c) {
        this.f11037b = d_a;
        this.f11036a = c2621c;
    }

    public void onClick(View view) {
        this.f11037b.f6008a.f11041e.remove(d$a.a(this.f11037b).m11876g());
        C2580w.m12905a(this.f11037b.f6008a.f11040d, "删除离线地图", null);
        d$a.a(this.f11037b).m11867a(0);
        if (this.f11037b.f6008a.f11042f != null) {
            this.f11037b.f6008a.f11042f.m11863a(d$a.a(this.f11037b), true);
        }
        this.f11036a.m13069b();
    }
}
