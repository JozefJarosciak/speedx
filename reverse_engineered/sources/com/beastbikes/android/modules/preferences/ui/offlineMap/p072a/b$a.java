package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.OfflineMapActivity;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.C2326a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: OfflineExpandableListAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.b$a */
class b$a extends ViewHolder<C2322a> implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2316b f5994a;
    @C1458a(a = 2131755845)
    /* renamed from: b */
    private TextView f5995b;
    @C1458a(a = 2131755846)
    /* renamed from: c */
    private TextView f5996c;
    @C1458a(a = 2131755847)
    /* renamed from: d */
    private TextView f5997d;
    @C1458a(a = 2131755848)
    /* renamed from: e */
    private TextView f5998e;
    /* renamed from: f */
    private C2322a f5999f;

    public /* synthetic */ void bind(Object obj) {
        m7223a((C2322a) obj);
    }

    public b$a(C2316b c2316b, View view) {
        this.f5994a = c2316b;
        super(view);
        this.f5998e.setOnClickListener(this);
    }

    /* renamed from: a */
    public void m7223a(C2322a c2322a) {
        this.f5999f = c2322a;
        this.f5995b.setText(c2322a.e());
        this.f5996c.setText("(" + C2326a.a((long) c2322a.i()) + ")");
        if (c2322a.c() == 0) {
            this.f5998e.setVisibility(0);
        } else {
            this.f5998e.setVisibility(4);
        }
        switch (c2322a.c()) {
            case 0:
                this.f5997d.setVisibility(4);
                this.f5998e.setVisibility(0);
                this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.offline_map_setting_activity_download));
                return;
            case 1:
                this.f5997d.setVisibility(0);
                this.f5998e.setVisibility(0);
                this.f5997d.setText(c2322a.b() + "%");
                this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.activity_state_label_pause));
                return;
            case 2:
                this.f5997d.setVisibility(0);
                this.f5998e.setVisibility(0);
                this.f5997d.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.offline_map_setting_activity_wait));
                this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.activity_state_label_pause));
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
                this.f5997d.setVisibility(0);
                this.f5998e.setVisibility(0);
                this.f5997d.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.offline_map_setting_activity_download_pausing));
                this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.offline_map_setting_activity_download_start));
                return;
            case 4:
                if (c2322a.d()) {
                    this.f5997d.setVisibility(4);
                    this.f5998e.setVisibility(0);
                    this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.update_dialog_button_update_immediately));
                    return;
                }
                this.f5997d.setVisibility(4);
                this.f5998e.setVisibility(0);
                this.f5998e.setText(C2316b.a(this.f5994a).getResources().getText(C1373R.string.offline_map_setting_activity_already_downloaded));
                return;
            default:
                this.f5997d.setVisibility(4);
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.offlinemap_fragment_city_list_tvDownload:
                int g;
                if (this.f5999f.c() == 0 || this.f5999f.c() == 3) {
                    g = this.f5999f.g();
                    if (g > 0) {
                        C2316b.b(this.f5994a).start(g);
                        if (this.f5999f.h() == null) {
                            this.f5999f.a(C2316b.b(this.f5994a).getUpdateInfo(g));
                        }
                        this.f5999f.a(2);
                        if (C2316b.c(this.f5994a) != null) {
                            C2316b.c(this.f5994a).a(this.f5999f, false);
                            return;
                        }
                        return;
                    }
                    return;
                } else if (this.f5999f.c() == 1 || this.f5999f.c() == 2) {
                    g = this.f5999f.g();
                    if (g > 0) {
                        C2316b.b(this.f5994a).pause(g);
                        this.f5999f.a(3);
                        if (C2316b.c(this.f5994a) != null) {
                            C2316b.c(this.f5994a).a(this.f5999f, false);
                            return;
                        }
                        return;
                    }
                    return;
                } else {
                    if (this.f5999f.d()) {
                        C2316b.b(this.f5994a).remove(this.f5999f.g());
                        this.f5999f.a(0);
                        if (C2316b.c(this.f5994a) != null) {
                            C2316b.c(this.f5994a).a(this.f5999f, true);
                        }
                        C2316b.b(this.f5994a).start(this.f5999f.g());
                    }
                    if ((C2316b.a(this.f5994a) instanceof OfflineMapActivity) && this.f5999f.c() == 4) {
                        ((OfflineMapActivity) C2316b.a(this.f5994a)).m7221a();
                        return;
                    }
                    return;
                }
            default:
                return;
        }
    }
}
