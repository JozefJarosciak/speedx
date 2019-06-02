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

/* compiled from: OfflineMapHotCitiesAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.c$a */
class c$a extends ViewHolder<C2322a> implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2317c f6002a;
    @C1458a(a = 2131755845)
    /* renamed from: b */
    private TextView f6003b;
    @C1458a(a = 2131755846)
    /* renamed from: c */
    private TextView f6004c;
    @C1458a(a = 2131755847)
    /* renamed from: d */
    private TextView f6005d;
    @C1458a(a = 2131755848)
    /* renamed from: e */
    private TextView f6006e;
    /* renamed from: f */
    private C2322a f6007f;

    public /* synthetic */ void bind(Object obj) {
        m7226a((C2322a) obj);
    }

    public c$a(C2317c c2317c, View view) {
        this.f6002a = c2317c;
        super(view);
        this.f6006e.setOnClickListener(this);
    }

    /* renamed from: a */
    public void m7226a(C2322a c2322a) {
        if (c2322a != null) {
            this.f6007f = c2322a;
            this.f6003b.setText(c2322a.e());
            this.f6004c.setText("(" + C2326a.a((long) c2322a.i()) + ")");
            if (c2322a.c() == 0) {
                this.f6006e.setVisibility(0);
            } else {
                this.f6006e.setVisibility(4);
            }
            switch (c2322a.c()) {
                case 0:
                    this.f6005d.setVisibility(4);
                    this.f6006e.setVisibility(0);
                    this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.offline_map_setting_activity_download));
                    return;
                case 1:
                    this.f6005d.setVisibility(0);
                    this.f6006e.setVisibility(0);
                    this.f6005d.setText(c2322a.b() + "%");
                    this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.activity_state_label_pause));
                    return;
                case 2:
                    this.f6005d.setVisibility(0);
                    this.f6006e.setVisibility(0);
                    this.f6005d.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.offline_map_setting_activity_wait));
                    this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.activity_state_label_pause));
                    return;
                case 3:
                case 5:
                case 6:
                case 7:
                case 8:
                    this.f6005d.setVisibility(0);
                    this.f6006e.setVisibility(0);
                    this.f6005d.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.offline_map_setting_activity_download_pausing));
                    this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.offline_map_setting_activity_download_start));
                    return;
                case 4:
                    if (c2322a.d()) {
                        this.f6005d.setVisibility(4);
                        this.f6006e.setVisibility(0);
                        this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.update_dialog_button_update_immediately));
                        return;
                    }
                    this.f6005d.setVisibility(4);
                    this.f6006e.setVisibility(0);
                    this.f6006e.setText(C2317c.a(this.f6002a).getResources().getString(C1373R.string.offline_map_setting_activity_already_downloaded));
                    return;
                default:
                    this.f6005d.setVisibility(4);
                    return;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.offlinemap_fragment_city_list_tvDownload:
                int g;
                if (this.f6007f.c() == 0 || this.f6007f.c() == 3) {
                    g = this.f6007f.g();
                    if (g > 0) {
                        C2317c.b(this.f6002a).start(g);
                        if (this.f6007f.h() == null) {
                            this.f6007f.a(C2317c.b(this.f6002a).getUpdateInfo(g));
                        }
                        this.f6007f.a(2);
                        if (C2317c.c(this.f6002a) != null) {
                            C2317c.c(this.f6002a).a(this.f6007f, false);
                            return;
                        }
                        return;
                    }
                    return;
                } else if (this.f6007f.c() == 1 || this.f6007f.c() == 2) {
                    g = this.f6007f.g();
                    if (g > 0) {
                        C2317c.b(this.f6002a).pause(g);
                        this.f6007f.a(3);
                        if (C2317c.c(this.f6002a) != null) {
                            C2317c.c(this.f6002a).a(this.f6007f, false);
                            return;
                        }
                        return;
                    }
                    return;
                } else {
                    if (this.f6007f.d() || this.f6007f.c() >= 5) {
                        C2317c.b(this.f6002a).remove(this.f6007f.g());
                        this.f6007f.a(0);
                        if (C2317c.c(this.f6002a) != null) {
                            C2317c.c(this.f6002a).a(this.f6007f, true);
                        }
                        C2317c.b(this.f6002a).start(this.f6007f.g());
                    }
                    if ((C2317c.a(this.f6002a) instanceof OfflineMapActivity) && this.f6007f.c() == 4) {
                        ((OfflineMapActivity) C2317c.a(this.f6002a)).m7221a();
                        return;
                    }
                    return;
                }
            default:
                return;
        }
    }
}
