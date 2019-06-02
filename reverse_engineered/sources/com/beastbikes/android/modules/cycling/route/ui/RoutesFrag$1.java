package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Context;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.ui.C2496a;

class RoutesFrag$1 implements OnMenuItemClickListener {
    /* renamed from: a */
    final /* synthetic */ Context f10346a;
    /* renamed from: b */
    final /* synthetic */ String f10347b;
    /* renamed from: c */
    final /* synthetic */ RoutesFrag f10348c;

    RoutesFrag$1(RoutesFrag routesFrag, Context context, String str) {
        this.f10348c = routesFrag;
        this.f10346a = context;
        this.f10347b = str;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        RoutesFrag.a(this.f10348c, new C2496a(this.f10346a));
        RoutesFrag.a(this.f10348c).m12572a(C1373R.string.str_loading);
        RoutesFrag.a(this.f10348c, this.f10347b);
        return true;
    }
}
