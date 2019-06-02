package com.beastbikes.android.ble.biz.p058c;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.framework.android.ApplicationContext;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleSensorConnectManager */
/* renamed from: com.beastbikes.android.ble.biz.c.e */
public class C1386e implements C1371a, C1384l, C1385n {
    /* renamed from: a */
    private static final Logger f4115a = LoggerFactory.getLogger(C1386e.class);
    /* renamed from: b */
    private static C1386e f4116b;
    /* renamed from: c */
    private HashMap<String, C1634f> f4117c;
    /* renamed from: d */
    private ArrayList<String> f4118d = new ArrayList();
    /* renamed from: e */
    private Context f4119e = ApplicationContext.m5242j().getApplicationContext();
    /* renamed from: f */
    private volatile boolean f4120f = false;
    /* renamed from: g */
    private volatile boolean f4121g = false;
    /* renamed from: h */
    private C1651c f4122h;
    /* renamed from: i */
    private C1650m f4123i;

    private C1386e() {
    }

    /* renamed from: a */
    public static C1386e m5413a() {
        if (f4116b == null) {
            synchronized (C1386e.class) {
                f4116b = new C1386e();
            }
        }
        return f4116b;
    }

    /* renamed from: b */
    public static C1386e m5414b() {
        if (f4116b == null) {
            synchronized (C1386e.class) {
                f4116b = new C1386e();
            }
        }
        return f4116b;
    }

    /* renamed from: a */
    public void mo2747a(String str, BluetoothDevice bluetoothDevice) {
        if (this.f4117c != null && this.f4117c.containsKey(str) && bluetoothDevice != null) {
            if (!((C1634f) this.f4117c.get(str)).a()) {
                f4115a.info("scanCallBack(), the devices is has not connect");
            } else if (this.f4120f) {
                f4115a.info("scanCallBack(), the BluetoothGatt is connecting");
            } else if (this.f4118d.contains(bluetoothDevice.getAddress())) {
                f4115a.info("scanCallBack(), " + bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress() + " is connected or connecting");
            } else {
                f4115a.info("scanCallBack(), device = " + bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress());
                ((C1634f) this.f4117c.get(str)).a(this.f4119e, bluetoothDevice);
                this.f4118d.add(bluetoothDevice.getAddress());
                this.f4120f = true;
            }
        }
    }

    /* renamed from: a */
    public void mo2746a(int i, BluetoothDevice bluetoothDevice) {
        this.f4120f = false;
        if (bluetoothDevice != null && this.f4122h != null) {
            this.f4122h.a(i, bluetoothDevice);
            if (this.f4123i != null) {
                this.f4123i.a(i, bluetoothDevice.getName());
            }
        }
    }

    /* renamed from: b */
    public void mo2748b(int i, BluetoothDevice bluetoothDevice) {
        this.f4120f = false;
        this.f4118d.remove(bluetoothDevice.getAddress());
        if (this.f4123i != null) {
            this.f4123i.d(i);
        }
    }

    /* renamed from: a */
    public void m5419a(C1650m c1650m) {
        this.f4123i = c1650m;
    }

    /* renamed from: c */
    public void m5427c() {
        boolean z = true;
        if (!this.f4121g) {
            this.f4121g = true;
            this.f4122h = new C1651c(this.f4119e);
            this.f4117c = new HashMap();
            C1635b c1635b = new C1635b();
            c1635b.a(this);
            if (!(C1454a.m7990a().m7996a(this.f4119e, "PREF.SENSOR.CADENCE", false) || C1454a.m7990a().m7996a(this.f4119e, "PREF.SENSOR.SPEED", false) || C1454a.m7990a().m7996a(this.f4119e, "00001816-0000-1000-8000-00805F9B34FB", false))) {
                z = false;
            }
            c1635b.a(z);
            this.f4117c.put("00001816-0000-1000-8000-00805F9B34FB", c1635b);
            C1638c c1638c = new C1638c();
            c1638c.a(this);
            c1638c.a(C1454a.m7990a().m7996a(this.f4119e, "PREF.SENSOR.HR", false));
            this.f4117c.put("0000180D-0000-1000-8000-00805F9B34FB", c1638c);
            C1641d c1641d = new C1641d();
            c1641d.a(this);
            c1641d.a(C1454a.m7990a().m7996a(this.f4119e, "PREF.SENSOR.POWER", false));
            this.f4117c.put("00001818-0000-1000-8000-00805F9B34FB", c1641d);
            C1645g.a().a(this);
        }
    }

    /* renamed from: d */
    public void m5429d() {
        if (this.f4121g) {
            this.f4120f = false;
            this.f4121g = false;
            C1645g.a().b();
            if (this.f4117c != null) {
                for (String str : this.f4117c.keySet()) {
                    ((C1634f) this.f4117c.get(str)).e();
                }
                this.f4117c.clear();
                this.f4117c = null;
            }
            this.f4118d.clear();
        }
    }

    /* renamed from: a */
    public void m5416a(C1647h c1647h) {
        if (this.f4117c != null && this.f4117c.containsKey("00001816-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001816-0000-1000-8000-00805F9B34FB")).a(c1647h);
        }
    }

    /* renamed from: b */
    public void m5423b(C1647h c1647h) {
        if (this.f4117c != null && this.f4117c.containsKey("00001816-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001816-0000-1000-8000-00805F9B34FB")).b(c1647h);
        }
    }

    /* renamed from: a */
    public void m5417a(C1648i c1648i) {
        if (this.f4117c != null && this.f4117c.containsKey("0000180D-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("0000180D-0000-1000-8000-00805F9B34FB")).a(c1648i);
        }
    }

    /* renamed from: b */
    public void m5424b(C1648i c1648i) {
        if (this.f4117c != null && this.f4117c.containsKey("0000180D-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("0000180D-0000-1000-8000-00805F9B34FB")).a(c1648i);
        }
    }

    /* renamed from: a */
    public void m5418a(C1649j c1649j) {
        if (this.f4117c != null && this.f4117c.containsKey("00001818-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001818-0000-1000-8000-00805F9B34FB")).a(c1649j);
        }
    }

    /* renamed from: b */
    public void m5425b(C1649j c1649j) {
        if (this.f4117c != null && this.f4117c.containsKey("00001818-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001818-0000-1000-8000-00805F9B34FB")).a(c1649j);
        }
    }

    /* renamed from: a */
    public void m5421a(boolean z) {
        C1454a.m7990a().m7993a(this.f4119e, "PREF.SENSOR.SPEED.AND.CADENCE", Boolean.valueOf(z)).apply();
        if (this.f4117c != null && this.f4117c.containsKey("00001816-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001816-0000-1000-8000-00805F9B34FB")).a(z);
        }
    }

    /* renamed from: b */
    public void m5426b(boolean z) {
        C1454a.m7990a().m7993a(this.f4119e, "PREF.SENSOR.HR", Boolean.valueOf(z)).apply();
        if (this.f4117c != null && this.f4117c.containsKey("0000180D-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("0000180D-0000-1000-8000-00805F9B34FB")).a(z);
        }
    }

    /* renamed from: c */
    public void m5428c(boolean z) {
        C1454a.m7990a().m7993a(this.f4119e, "PREF.SENSOR.POWER", Boolean.valueOf(z)).apply();
        if (this.f4117c != null && this.f4117c.containsKey("00001818-0000-1000-8000-00805F9B34FB")) {
            ((C1634f) this.f4117c.get("00001818-0000-1000-8000-00805F9B34FB")).a(z);
        }
    }
}
