package com.beastbikes.android.modules.cycling.activity.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.modules.cycling.activity.ui.BleSensorListActivity.C1400b;
import com.beastbikes.android.widget.p081b.C2534b;
import java.util.List;

class BleSensorListActivity$a extends Adapter<C1400b> {
    /* renamed from: a */
    private List<BleDevice> f8598a;
    /* renamed from: b */
    private C2534b f8599b;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9922a((C1400b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9921a(viewGroup, i);
    }

    BleSensorListActivity$a(List<BleDevice> list, C2534b c2534b) {
        this.f8598a = list;
        this.f8599b = c2534b;
    }

    /* renamed from: a */
    public C1400b m9921a(ViewGroup viewGroup, int i) {
        return new C1400b(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.view_sensor_device_list, null));
    }

    /* renamed from: a */
    public void m9922a(final C1400b c1400b, int i) {
        BleDevice bleDevice = (BleDevice) this.f8598a.get(i);
        if (bleDevice != null) {
            c1400b.a(bleDevice);
            C1400b.a(c1400b).setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ BleSensorListActivity$a f8597b;

                public void onClick(View view) {
                    this.f8597b.f8599b.mo3520a(c1400b, c1400b.getAdapterPosition());
                }
            });
        }
    }

    public int getItemCount() {
        return this.f8598a.size();
    }
}
