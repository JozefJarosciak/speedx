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

/* compiled from: BlePowerSensorManager */
/* renamed from: com.beastbikes.android.ble.biz.c.d */
class C1641d extends C1634f {
    /* renamed from: b */
    private static final Logger f7437b = LoggerFactory.getLogger(C1641d.class);
    /* renamed from: c */
    private boolean f7438c;
    /* renamed from: d */
    private boolean f7439d;
    /* renamed from: e */
    private List<C1646k> f7440e = new ArrayList();

    /* compiled from: BlePowerSensorManager */
    /* renamed from: com.beastbikes.android.ble.biz.c.d$2 */
    class C16402 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1641d f7436a;

        C16402(C1641d c1641d) {
            this.f7436a = c1641d;
        }

        public void run() {
            for (C1646k c1646k : this.f7436a.f7440e) {
                ((C1649j) c1646k).mo3276c();
            }
        }
    }

    C1641d() {
    }

    /* renamed from: a */
    void mo3179a(C1646k c1646k) {
        this.f7440e.add(c1646k);
    }

    /* renamed from: b */
    void mo3184b(C1646k c1646k) {
        if (this.f7440e.contains(c1646k)) {
            this.f7440e.remove(c1646k);
        }
    }

    /* renamed from: a */
    void mo3181a(boolean z) {
        this.f7439d = z;
        if (!z && this.f7438c) {
            m8823e();
        }
    }

    /* renamed from: a */
    boolean mo3182a() {
        return this.f7439d;
    }

    /* renamed from: b */
    public int mo3183b() {
        return 2;
    }

    @TargetApi(18)
    /* renamed from: a */
    void mo3178a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase("00002A63-0000-1000-8000-00805F9B34FB")) {
            bluetoothGattCharacteristic.getProperties();
            final int intValue = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
            f7437b.info("Power Data： power = " + intValue + " W");
            if (!this.f7440e.isEmpty()) {
                this.a.post(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1641d f7435b;

                    public void run() {
                        for (C1646k c1646k : this.f7435b.f7440e) {
                            ((C1649j) c1646k).mo3277c(intValue);
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
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("00001818-0000-1000-8000-00805F9B34FB"));
            if (service != null) {
                f7437b.info("Power Service Discovered - Success， status = " + i);
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("00002A63-0000-1000-8000-00805F9B34FB"));
                if (characteristic != null) {
                    bluetoothGatt.setCharacteristicNotification(characteristic, true);
                    BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.writeDescriptor(descriptor);
                    return;
                }
                return;
            }
            return;
        }
        f7437b.info("Power service discover failed, status = " + i);
    }

    /* renamed from: c */
    void mo3185c() {
        this.f7438c = true;
    }

    /* renamed from: d */
    void mo3186d() {
        this.f7438c = false;
        f7437b.info("PowerSensor is disconnected");
        if (!this.f7440e.isEmpty()) {
            this.a.post(new C16402(this));
        }
    }

    /* renamed from: a */
    public void mo3180a(C1384l c1384l) {
        super.mo3180a(c1384l);
    }
}
