package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.dialog.C1786a;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.android.widget.p081b.C2613i;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@C1459b(a = 2130903081)
public class BleSensorListActivity extends SessionFragmentActivity implements OnClickListener, C2534b {
    @C1458a(a = 2131755403)
    /* renamed from: a */
    private ViewGroup f4499a;
    /* renamed from: b */
    private TextView f4500b;
    @C1458a(a = 2131755405)
    /* renamed from: c */
    private RecyclerView f4501c;
    /* renamed from: d */
    private C1651c f4502d;
    /* renamed from: e */
    private BleSensorListActivity$a f4503e;
    /* renamed from: f */
    private List<BleDevice> f4504f = new ArrayList();

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.BleSensorListActivity$b */
    private static class C1400b extends C2613i<BleDevice> implements OnClickListener {
        @C1458a(a = 2131757773)
        /* renamed from: a */
        private TextView f4495a;
        @C1458a(a = 2131757774)
        /* renamed from: b */
        private TextView f4496b;
        @C1458a(a = 2131757775)
        /* renamed from: c */
        private ImageView f4497c;
        /* renamed from: d */
        private SimpleDateFormat f4498d;

        public C1400b(View view) {
            super(view);
            if (C1849a.a()) {
                this.f4498d = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINESE);
            } else {
                this.f4498d = new SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault());
            }
        }

        /* renamed from: a */
        public void m5902a(BleDevice bleDevice) {
            if (bleDevice != null) {
                this.f4495a.setText(bleDevice.getDeviceName());
                this.f4496b.setText(this.f4498d.format(Long.valueOf(bleDevice.getLastBindTime())));
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        super.setTitle(getIntent().getStringExtra("sensor_title"));
        this.f4502d = new C1651c(this);
        m5904a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.sensor_diameter_setting_view:
                m5909b();
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    public void m5913b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    public void m5912a(ViewHolder viewHolder, int i) {
        BleDevice bleDevice = (BleDevice) this.f4504f.get(i);
        if (bleDevice != null) {
            new C1786a(this, getString(C1373R.string.dialog_sure_or_delete), new BleSensorListActivity$1(this, bleDevice)).show();
        }
    }

    /* renamed from: a */
    private void m5904a() {
        int intExtra = getIntent().getIntExtra("sensor_type", 1);
        if (intExtra == 3 || intExtra == 5) {
            this.f4499a.setVisibility(0);
            this.f4499a.setOnClickListener(this);
            ((TextView) this.f4499a.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_diameter_setting);
            this.f4500b = (TextView) this.f4499a.findViewById(C1373R.id.speed_force_setting_item_value);
            this.f4500b.setText(C1454a.m7990a().m7994a((Context) this, "PREF.SENSOR.DIAMETER.LABEL", "700*23"));
        }
        this.f4501c.setLayoutManager(new LinearLayoutManager(this));
        this.f4503e = new BleSensorListActivity$a(this.f4504f, this);
        this.f4501c.setAdapter(this.f4503e);
        this.f4503e.notifyDataSetChanged();
        m5905a(intExtra);
    }

    /* renamed from: b */
    private void m5909b() {
        String[] stringArray = getResources().getStringArray(C1373R.array.sensor_speed_wheel_brand_num);
        Object arrayList = new ArrayList();
        Collections.addAll(arrayList, stringArray);
        C1805k c1805k = new C1805k(this, new BleSensorListActivity$2(this));
        c1805k.show();
        c1805k.a(arrayList);
        c1805k.b(C1454a.m7990a().m7994a((Context) this, "PREF.SENSOR.DIAMETER.LABEL", stringArray[1]));
    }

    /* renamed from: a */
    private void m5905a(int i) {
        getAsyncTaskQueue().a(new BleSensorListActivity$3(this, i), new Object[0]);
    }

    /* renamed from: a */
    private void m5906a(BleDevice bleDevice) {
        getAsyncTaskQueue().a(new BleSensorListActivity$4(this, bleDevice), new Void[0]);
    }
}
