package com.beastbikes.android.ble.biz.p058c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.a$a;
import com.beastbikes.android.utils.p080a.C1454a;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleCSCSensorManager */
/* renamed from: com.beastbikes.android.ble.biz.c.b */
class C1635b extends C1634f {
    /* renamed from: b */
    private static final Logger f7422b = LoggerFactory.getLogger(C1635b.class);
    /* renamed from: c */
    private boolean f7423c;
    /* renamed from: d */
    private boolean f7424d;
    /* renamed from: e */
    private List<C1646k> f7425e = new ArrayList();
    /* renamed from: f */
    private int f7426f = C1454a.a().a(BeastBikes.j().getApplicationContext(), "PREF.SENSOR.DIAMETER.VALUE", 2099);

    /* compiled from: BleCSCSensorManager */
    /* renamed from: com.beastbikes.android.ble.biz.c.b$2 */
    class C16332 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1635b f7416a;

        C16332(C1635b c1635b) {
            this.f7416a = c1635b;
        }

        public void run() {
            for (C1646k c1646k : this.f7416a.f7425e) {
                ((C1647h) c1646k).mo3271a();
            }
        }
    }

    C1635b() {
    }

    /* renamed from: a */
    void mo3179a(C1646k c1646k) {
        this.f7425e.add(c1646k);
    }

    /* renamed from: b */
    void mo3184b(C1646k c1646k) {
        if (this.f7425e.contains(c1646k)) {
            this.f7425e.remove(c1646k);
        }
    }

    /* renamed from: a */
    void mo3181a(boolean z) {
        this.f7424d = z;
        if (!z && this.f7423c) {
            m8823e();
        }
    }

    /* renamed from: a */
    boolean mo3182a() {
        return this.f7424d;
    }

    /* renamed from: b */
    public int mo3183b() {
        return 5;
    }

    @TargetApi(18)
    /* renamed from: a */
    protected void mo3178a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int i = 1;
        int i2 = 0;
        if ("00002A5B-0000-1000-8000-00805F9B34FB".equalsIgnoreCase(bluetoothGattCharacteristic.getUuid().toString())) {
            int intValue;
            int intValue2;
            int i3;
            byte b = bluetoothGattCharacteristic.getValue()[0];
            if (((b & 1) > 0 ? 1 : 0) != 0) {
                intValue = bluetoothGattCharacteristic.getIntValue(20, 1).intValue();
                intValue2 = bluetoothGattCharacteristic.getIntValue(18, 5).intValue();
                i3 = 7;
            } else {
                intValue2 = 0;
                intValue = 0;
                i3 = 1;
            }
            if ((b & 2) <= 0) {
                i = 0;
            }
            if (i != 0) {
                i = bluetoothGattCharacteristic.getIntValue(18, i3).intValue();
                i2 = bluetoothGattCharacteristic.getIntValue(18, i3 + 2).intValue();
            } else {
                i = 0;
            }
            final double a = C1631a.m8807a(intValue, intValue2, (float) this.f7426f);
            i2 = C1631a.m8808a(i, i2);
            if (!this.f7425e.isEmpty()) {
                f7422b.info("Speed = " + a + ", Cadence = " + i2);
                this.a.post(new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ C1635b f7415c;

                    public void run() {
                        for (C1646k c1646k : this.f7415c.f7425e) {
                            ((C1647h) c1646k).mo3272a(a);
                            ((C1647h) c1646k).mo3273a(i2);
                        }
                    }
                });
            }
        }
    }

    @TargetApi(18)
    /* renamed from: a */
    protected void mo3177a(BluetoothGatt bluetoothGatt, int i) {
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("00001816-0000-1000-8000-00805F9B34FB"));
        if (service != null) {
            for (BluetoothGattCharacteristic uuid : service.getCharacteristics()) {
                f7422b.info("#### CSC Sensor - Characteristic UUID : " + uuid.getUuid().toString().toUpperCase());
            }
            BluetoothGattCharacteristic uuid2 = service.getCharacteristic(UUID.fromString("00002A5B-0000-1000-8000-00805F9B34FB"));
            if (uuid2 != null) {
                bluetoothGatt.setCharacteristicNotification(uuid2, true);
                BluetoothGattDescriptor descriptor = uuid2.getDescriptor(a$a.f7184b);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                bluetoothGatt.writeDescriptor(descriptor);
            }
        }
    }

    /* renamed from: c */
    void mo3185c() {
        this.f7423c = true;
    }

    /* renamed from: d */
    void mo3186d() {
        this.f7423c = false;
        f7422b.info("CSCSensor is disconnected");
        if (!this.f7425e.isEmpty()) {
            this.a.post(new C16332(this));
        }
    }

    /* renamed from: a */
    public void mo3180a(C1384l c1384l) {
        super.mo3180a(c1384l);
    }
}
