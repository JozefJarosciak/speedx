package com.beastbikes.android.ble.biz.p058c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.beastbikes.android.C1371a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleSensorManager */
/* renamed from: com.beastbikes.android.ble.biz.c.f */
public abstract class C1634f implements C1371a {
    /* renamed from: b */
    private static final Logger f7417b = LoggerFactory.getLogger(C1634f.class);
    /* renamed from: a */
    Handler f7418a = new Handler(Looper.getMainLooper());
    /* renamed from: c */
    private BluetoothGatt f7419c;
    /* renamed from: d */
    private C1642a f7420d = new C1642a(this);
    /* renamed from: e */
    private C1384l f7421e;

    @TargetApi(18)
    /* compiled from: BleSensorManager */
    /* renamed from: com.beastbikes.android.ble.biz.c.f$a */
    private static class C1642a extends BluetoothGattCallback {
        /* renamed from: a */
        private C1634f f7441a;

        C1642a(C1634f c1634f) {
            this.f7441a = c1634f;
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            BluetoothDevice device = bluetoothGatt.getDevice();
            if (device == null) {
                C1634f.f7417b.debug("onConnectionStateChange(), BluetoothDevice is null ");
                return;
            }
            C1634f.f7417b.info("onConnectionStateChange status = " + i + ", newState = " + i2);
            if (i2 == 2) {
                this.f7441a.f7419c = bluetoothGatt;
                this.f7441a.mo3185c();
                bluetoothGatt.discoverServices();
                if (this.f7441a.f7421e != null) {
                    this.f7441a.f7421e.a(this.f7441a.mo3183b(), device);
                    return;
                }
                return;
            }
            this.f7441a.mo3186d();
            bluetoothGatt.close();
            this.f7441a.f7419c = null;
            if (this.f7441a.f7421e != null) {
                this.f7441a.f7421e.b(this.f7441a.mo3183b(), device);
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            this.f7441a.mo3177a(bluetoothGatt, i);
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            this.f7441a.mo3178a(bluetoothGattCharacteristic);
        }
    }

    /* renamed from: a */
    abstract void mo3177a(BluetoothGatt bluetoothGatt, int i);

    /* renamed from: a */
    abstract void mo3178a(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    /* renamed from: a */
    abstract void mo3179a(C1646k c1646k);

    /* renamed from: a */
    abstract void mo3181a(boolean z);

    /* renamed from: a */
    abstract boolean mo3182a();

    /* renamed from: b */
    public abstract int mo3183b();

    /* renamed from: b */
    abstract void mo3184b(C1646k c1646k);

    /* renamed from: c */
    abstract void mo3185c();

    /* renamed from: d */
    abstract void mo3186d();

    C1634f() {
    }

    @TargetApi(18)
    /* renamed from: a */
    public synchronized void m8814a(Context context, BluetoothDevice bluetoothDevice) {
        f7417b.info("connect(), device = " + bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress());
        this.f7419c = bluetoothDevice.connectGatt(context, true, this.f7420d);
    }

    @TargetApi(18)
    /* renamed from: e */
    public synchronized void m8823e() {
        if (this.f7419c != null) {
            this.f7419c.disconnect();
        }
        if (this.f7421e != null) {
            this.f7421e = null;
        }
    }

    /* renamed from: a */
    public void mo3180a(C1384l c1384l) {
        this.f7421e = c1384l;
    }
}
