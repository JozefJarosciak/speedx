package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.C2326a;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: OfflineMapManagerAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.d$a */
class d$a extends ViewHolder<C2322a> implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2318d f6008a;
    @C1458a(a = 2131755841)
    /* renamed from: b */
    private TextView f6009b;
    @C1458a(a = 2131755842)
    /* renamed from: c */
    private TextView f6010c;
    @C1458a(a = 2131755843)
    /* renamed from: d */
    private TextView f6011d;
    /* renamed from: e */
    private C2322a f6012e;

    public /* synthetic */ void bind(Object obj) {
        m7228a((C2322a) obj);
    }

    public d$a(C2318d c2318d, View view) {
        this.f6008a = c2318d;
        super(view);
        this.f6011d.setOnClickListener(this);
    }

    /* renamed from: a */
    public void m7228a(C2322a c2322a) {
        this.f6012e = c2322a;
        this.f6009b.setText(c2322a.e());
        this.f6010c.setText("(" + C2326a.a((long) c2322a.i()) + ")");
        if (c2322a.c() != 1) {
            if (c2322a.c() == 4) {
                if (!c2322a.d()) {
                }
            } else if (c2322a.c() != 3 && c2322a.c() != 0 && c2322a.c() < 5 && c2322a.c() == 2) {
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.offlinemap_fragment_download_manager_btnRemove:
                C2621c c2621c = new C2621c(C2318d.a(this.f6008a));
                c2621c.a(C2318d.a(this.f6008a).getResources().getText(C1373R.string.club_create_tip_title));
                c2621c.b(C1373R.string.dialog_sure_or_delete);
                c2621c.a(C1373R.string.delete, new d$a$1(this, c2621c));
                c2621c.b(C1373R.string.cancel, new d$a$2(this, c2621c));
                c2621c.a();
                return;
            default:
                return;
        }
    }
}
