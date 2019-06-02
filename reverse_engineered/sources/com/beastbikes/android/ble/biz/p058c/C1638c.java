package com.beastbikes.android.ble.biz.p058c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.beastbikes.android.a$a;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleHRSensorManager */
/* renamed from: com.beastbikes.android.ble.biz.c.c */
class C1638c extends C1634f {
    /* renamed from: b */
    private static final Logger f7430b = LoggerFactory.getLogger(C1638c.class);
    /* renamed from: c */
    private boolean f7431c;
    /* renamed from: d */
    private boolean f7432d;
    /* renamed from: e */
    private List<C1646k> f7433e = new ArrayList();

    /* compiled from: BleHRSensorManager */
    /* renamed from: com.beastbikes.android.ble.biz.c.c$2 */
    class C16372 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1638c f7429a;

        C16372(C1638c c1638c) {
            this.f7429a = c1638c;
        }

        public void run() {
            for (C1646k c1646k : this.f7429a.f7433e) {
                ((C1648i) c1646k).mo3274b();
            }
        }
    }

    C1638c() {
    }

    /* renamed from: a */
    void mo3179a(C1646k c1646k) {
        this.f7433e.add(c1646k);
    }

    /* renamed from: b */
    void mo3184b(C1646k c1646k) {
        if (this.f7433e.contains(c1646k)) {
            this.f7433e.remove(c1646k);
        }
    }

    /* renamed from: a */
    void mo3181a(boolean z) {
        this.f7432d = z;
        if (!z && this.f7431c) {
            m8823e();
        }
    }

    /* renamed from: a */
    boolean mo3182a() {
        return this.f7432d;
    }

    /* renamed from: b */
    public int mo3183b() {
        return 1;
    }

    @TargetApi(18)
    /* renamed from: a */
    void mo3178a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase("00002A37-0000-1000-8000-00805F9B34FB")) {
            int i;
            if ((bluetoothGattCharacteristic.getProperties() & 1) != 0) {
                i = 18;
            } else {
                i = 17;
            }
            i = bluetoothGattCharacteristic.getIntValue(i, 1).intValue();
            f7430b.info("HR Data： heartRate = " + i + " BPM");
            if (!this.f7433e.isEmpty()) {
                this.a.post(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1638c f7428b;

                    public void run() {
                        for (C1646k c1646k : this.f7428b.f7433e) {
                            ((C1648i) c1646k).mo3275b(i);
                        }
                    }
                });
            }
        }
    }

    @TargetApi(18)
    /* renamed from: a */
    void mo3177a(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            f7430b.info("HR Service Discovered - Success， status = " + i);
            for (BluetoothGattService uuid : bluetoothGatt.getServices()) {
                f7430b.info("Service UUID : " + uuid.getUuid().toString().toUpperCase());
            }
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("0000180D-0000-1000-8000-00805F9B34FB"));
            if (service != null) {
                for (BluetoothGattCharacteristic uuid2 : service.getCharacteristics()) {
                    f7430b.info("#### HR Sensor - Characteristic UUID : " + uuid2.getUuid().toString().toUpperCase());
                }
                BluetoothGattCharacteristic uuid22 = service.getCharacteristic(UUID.fromString("00002A37-0000-1000-8000-00805F9B34FB"));
                if (uuid22 != null) {
                    bluetoothGatt.setCharacteristicNotification(uuid22, true);
                    BluetoothGattDescriptor descriptor = uuid22.getDescriptor(a$a.f7184b);
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.writeDescriptor(descriptor);
                    return;
                }
                return;
            }
            return;
        }
        f7430b.info("onServicesDiscovered, status : " + i);
    }

    /* renamed from: c */
    void mo3185c() {
        this.f7431c = true;
        f7430b.info("HRSensor is connected");
    }

    /* renamed from: d */
    void mo3186d() {
        this.f7431c = false;
        f7430b.info("HRSensor is disconnected");
        if (!this.f7433e.isEmpty()) {
            this.a.post(new C16372(this));
        }
    }

    /* renamed from: a */
    public void mo3180a(C1384l c1384l) {
        super.mo3180a(c1384l);
    }
}
