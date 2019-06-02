package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

class SpeedXDevicesActivity$b extends Adapter<SpeedXDevicesActivity$c> {
    /* renamed from: a */
    final /* synthetic */ SpeedXDevicesActivity f7723a;
    /* renamed from: b */
    private C2534b f7724b;
    /* renamed from: c */
    private List<BleDevice> f7725c;
    /* renamed from: d */
    private DecimalFormat f7726d = new DecimalFormat("##");

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9200a((SpeedXDevicesActivity$c) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9199a(viewGroup, i);
    }

    SpeedXDevicesActivity$b(SpeedXDevicesActivity speedXDevicesActivity, List<BleDevice> list, C2534b c2534b) {
        this.f7723a = speedXDevicesActivity;
        this.f7725c = list;
        this.f7724b = c2534b;
    }

    /* renamed from: a */
    public SpeedXDevicesActivity$c m9199a(ViewGroup viewGroup, int i) {
        return new SpeedXDevicesActivity$c(this.f7723a, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.speedx_devices_item_view, null));
    }

    /* renamed from: a */
    public void m9200a(final SpeedXDevicesActivity$c speedXDevicesActivity$c, int i) {
        BleDevice bleDevice = (BleDevice) this.f7725c.get(i);
        if (bleDevice == null) {
            SpeedXDevicesActivity.a().error("onBindViewHolder(), BleDevice is null, position = " + i);
            return;
        }
        long j;
        if (TextUtils.equals(bleDevice.getMacAddress(), SpeedXDevicesActivity.f(this.f7723a))) {
            speedXDevicesActivity$c.f7729c.setVisibility(0);
            speedXDevicesActivity$c.f7732f.setEnabled(true);
            speedXDevicesActivity$c.f7732f.setText(C1373R.string.str_ble_connection_state_connected);
        } else {
            speedXDevicesActivity$c.f7729c.setVisibility(8);
            speedXDevicesActivity$c.f7732f.setEnabled(false);
            speedXDevicesActivity$c.f7732f.setText(C1373R.string.str_ble_connection_state_disconnected);
        }
        speedXDevicesActivity$c.f7731e.setText(bleDevice.getDeviceName());
        if (TextUtils.isEmpty(bleDevice.getUrl())) {
            speedXDevicesActivity$c.f7730d.setImageResource(C1373R.drawable.ic_default_bike);
        } else {
            Picasso.with(this.f7723a.getBaseContext()).load(bleDevice.getUrl()).error(C1373R.drawable.ic_default_bike).placeholder(C1373R.drawable.ic_default_bike).into(speedXDevicesActivity$c.f7730d);
        }
        double mileage = bleDevice.getMileage() / 1000.0d;
        if (!SpeedXDevicesActivity.e(this.f7723a)) {
            mileage = C1849a.m9638a(mileage);
        }
        speedXDevicesActivity$c.f7733g.setText(String.valueOf(this.f7726d.format(mileage)));
        speedXDevicesActivity$c.f7736j.setText(String.valueOf(bleDevice.getCycleTimes()));
        long cycleTime = bleDevice.getCycleTime();
        if (cycleTime > 0) {
            j = cycleTime / 3600;
            cycleTime = (cycleTime % 3600) / 60;
        } else {
            cycleTime = 0;
            j = 0;
        }
        if (j <= 0) {
            speedXDevicesActivity$c.f7735i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(((float) cycleTime) / 60.0f)}));
        } else {
            speedXDevicesActivity$c.f7735i.setText(String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(j)}));
        }
        speedXDevicesActivity$c.f7728b.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ SpeedXDevicesActivity$b f7722b;

            public void onClick(View view) {
                this.f7722b.f7724b.mo3520a(speedXDevicesActivity$c, speedXDevicesActivity$c.getAdapterPosition());
            }
        });
    }

    public int getItemCount() {
        return this.f7725c.size();
    }
}
