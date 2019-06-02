package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.C2326a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: OfflineExpandableListAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.b$b */
class b$b extends ViewHolder<C2322a> {
    /* renamed from: a */
    final /* synthetic */ C2316b f6000a;
    @C1458a(a = 2131755844)
    /* renamed from: b */
    private TextView f6001b;

    public /* synthetic */ void bind(Object obj) {
        m7225a((C2322a) obj);
    }

    protected b$b(C2316b c2316b, View view) {
        this.f6000a = c2316b;
        super(view);
    }

    /* renamed from: a */
    public void m7225a(C2322a c2322a) {
        this.f6001b.setText(c2322a.e().toString() + "(" + C2326a.a((long) c2322a.i()) + ")");
    }
}
