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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class DevicesFragment$b extends Adapter<DevicesFragment$c> {
    /* renamed from: a */
    final /* synthetic */ DevicesFragment f7574a;
    /* renamed from: b */
    private C2534b f7575b;
    /* renamed from: c */
    private List<BleDevice> f7576c = new ArrayList();
    /* renamed from: d */
    private DecimalFormat f7577d;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9078a((DevicesFragment$c) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9077a(viewGroup, i);
    }

    DevicesFragment$b(DevicesFragment devicesFragment, List<BleDevice> list, C2534b c2534b) {
        this.f7574a = devicesFragment;
        this.f7576c = list;
        this.f7575b = c2534b;
        this.f7577d = new DecimalFormat("##.#");
    }

    /* renamed from: a */
    public void m9079a(List<BleDevice> list) {
        this.f7576c.clear();
        this.f7576c.addAll(list);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public DevicesFragment$c m9077a(ViewGroup viewGroup, int i) {
        return new DevicesFragment$c(this.f7574a, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.speedx_devices_item_view, null));
    }

    /* renamed from: a */
    public void m9078a(final DevicesFragment$c devicesFragment$c, int i) {
        BleDevice bleDevice = (BleDevice) this.f7576c.get(i);
        if (bleDevice == null) {
            DevicesFragment.a().error("onBindViewHolder(), BleDevice is null, position = " + i);
            return;
        }
        long j;
        if (TextUtils.equals(bleDevice.getMacAddress(), DevicesFragment.g(this.f7574a))) {
            devicesFragment$c.f7580c.setVisibility(0);
            devicesFragment$c.f7583f.setEnabled(true);
            devicesFragment$c.f7583f.setText(C1373R.string.str_ble_connection_state_connected);
        } else {
            devicesFragment$c.f7580c.setVisibility(8);
            devicesFragment$c.f7583f.setEnabled(false);
            devicesFragment$c.f7583f.setText(C1373R.string.str_ble_connection_state_disconnected);
        }
        devicesFragment$c.f7582e.setText(bleDevice.getDeviceName());
        if (TextUtils.isEmpty(bleDevice.getUrl())) {
            devicesFragment$c.f7581d.setImageResource(C1373R.drawable.ic_default_bike);
        } else {
            Picasso.with(this.f7574a.getContext()).load(bleDevice.getUrl()).error(C1373R.drawable.ic_default_bike).placeholder(C1373R.drawable.ic_default_bike).into(devicesFragment$c.f7581d);
        }
        double mileage = bleDevice.getMileage() / 1000.0d;
        if (!DevicesFragment.f(this.f7574a)) {
            mileage = C1849a.m9638a(mileage);
        }
        devicesFragment$c.f7584g.setText(String.valueOf(this.f7577d.format(mileage)));
        devicesFragment$c.f7587j.setText(String.valueOf(bleDevice.getCycleTimes()));
        long cycleTime = bleDevice.getCycleTime();
        if (cycleTime > 0) {
            j = cycleTime / 3600;
            cycleTime = (cycleTime % 3600) / 60;
        } else {
            cycleTime = 0;
            j = 0;
        }
        if (j <= 0) {
            devicesFragment$c.f7586i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(((float) cycleTime) / 60.0f)}));
        } else {
            devicesFragment$c.f7586i.setText(String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(j)}));
        }
        devicesFragment$c.f7579b.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ DevicesFragment$b f7573b;

            public void onClick(View view) {
                this.f7573b.f7575b.mo3520a(devicesFragment$c, devicesFragment$c.getAdapterPosition());
            }
        });
    }

    public int getItemCount() {
        return this.f7576c.size();
    }
}
