package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

class SpeedXDevicesActivity$c extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ SpeedXDevicesActivity f7727a;
    /* renamed from: b */
    private View f7728b;
    /* renamed from: c */
    private ImageView f7729c;
    /* renamed from: d */
    private ImageView f7730d;
    /* renamed from: e */
    private TextView f7731e;
    /* renamed from: f */
    private TextView f7732f;
    /* renamed from: g */
    private TextView f7733g;
    /* renamed from: h */
    private TextView f7734h;
    /* renamed from: i */
    private TextView f7735i;
    /* renamed from: j */
    private TextView f7736j;
    /* renamed from: k */
    private TextView f7737k;

    SpeedXDevicesActivity$c(SpeedXDevicesActivity speedXDevicesActivity, View view) {
        this.f7727a = speedXDevicesActivity;
        super(view);
        this.f7728b = view;
        this.f7729c = (ImageView) view.findViewById(C1373R.id.devices_item_connected_view);
        this.f7730d = (ImageView) view.findViewById(C1373R.id.devices_item_img_view);
        this.f7731e = (TextView) view.findViewById(C1373R.id.devices_item_name_view);
        this.f7732f = (TextView) view.findViewById(C1373R.id.devices_item_connect_status_view);
        this.f7733g = (TextView) view.findViewById(C1373R.id.devices_item_distance_view);
        this.f7734h = (TextView) view.findViewById(C1373R.id.devices_item_distance_label);
        this.f7735i = (TextView) view.findViewById(C1373R.id.devices_item_time_view);
        this.f7736j = (TextView) view.findViewById(C1373R.id.devices_item_count_view);
        this.f7737k = (TextView) view.findViewById(C1373R.id.devices_item_times);
        if (SpeedXDevicesActivity.e(speedXDevicesActivity)) {
            this.f7734h.setText(speedXDevicesActivity.getResources().getString(C1373R.string.str_mileage) + "(KM)");
        } else {
            this.f7734h.setText(speedXDevicesActivity.getResources().getString(C1373R.string.str_mileage) + "(MI)");
        }
        this.f7737k.setText(speedXDevicesActivity.getString(C1373R.string.str_times));
    }
}
