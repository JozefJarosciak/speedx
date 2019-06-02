package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

class DevicesFragment$c extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ DevicesFragment f7578a;
    /* renamed from: b */
    private View f7579b;
    /* renamed from: c */
    private ImageView f7580c;
    /* renamed from: d */
    private ImageView f7581d;
    /* renamed from: e */
    private TextView f7582e;
    /* renamed from: f */
    private TextView f7583f;
    /* renamed from: g */
    private TextView f7584g;
    /* renamed from: h */
    private TextView f7585h;
    /* renamed from: i */
    private TextView f7586i;
    /* renamed from: j */
    private TextView f7587j;
    /* renamed from: k */
    private TextView f7588k;

    DevicesFragment$c(DevicesFragment devicesFragment, View view) {
        this.f7578a = devicesFragment;
        super(view);
        this.f7579b = view;
        this.f7580c = (ImageView) view.findViewById(C1373R.id.devices_item_connected_view);
        this.f7581d = (ImageView) view.findViewById(C1373R.id.devices_item_img_view);
        this.f7582e = (TextView) view.findViewById(C1373R.id.devices_item_name_view);
        this.f7583f = (TextView) view.findViewById(C1373R.id.devices_item_connect_status_view);
        this.f7584g = (TextView) view.findViewById(C1373R.id.devices_item_distance_view);
        this.f7585h = (TextView) view.findViewById(C1373R.id.devices_item_distance_label);
        this.f7586i = (TextView) view.findViewById(C1373R.id.devices_item_time_view);
        this.f7587j = (TextView) view.findViewById(C1373R.id.devices_item_count_view);
        this.f7588k = (TextView) view.findViewById(C1373R.id.devices_item_times);
        if (DevicesFragment.f(devicesFragment)) {
            this.f7585h.setText(devicesFragment.getResources().getString(C1373R.string.str_mileage) + "(KM)");
        } else {
            this.f7585h.setText(devicesFragment.getResources().getString(C1373R.string.str_mileage) + "(MI)");
        }
        this.f7588k.setText(devicesFragment.getString(C1373R.string.str_times));
    }
}
