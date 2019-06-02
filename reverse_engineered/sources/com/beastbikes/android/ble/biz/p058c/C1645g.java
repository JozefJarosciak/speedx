package com.beastbikes.android.ble.biz.p058c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build.VERSION;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleSensorScanner */
/* renamed from: com.beastbikes.android.ble.biz.c.g */
public class C1645g {
    /* renamed from: a */
    private static Logger f7443a = LoggerFactory.getLogger(C1645g.class);
    /* renamed from: b */
    private static C1645g f7444b;
    /* renamed from: i */
    private static C1385n f7445i;
    /* renamed from: c */
    private BluetoothAdapter f7446c;
    /* renamed from: d */
    private boolean f7447d;
    /* renamed from: e */
    private final Object f7448e = new Object();
    /* renamed from: f */
    private LeScanCallback f7449f;
    /* renamed from: g */
    private BluetoothLeScanner f7450g;
    /* renamed from: h */
    private C1644a f7451h;

    /* compiled from: BleSensorScanner */
    /* renamed from: com.beastbikes.android.ble.biz.c.g$1 */
    class C16431 implements LeScanCallback {
        /* renamed from: a */
        final /* synthetic */ C1645g f7442a;

        C16431(C1645g c1645g) {
            this.f7442a = c1645g;
        }

        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (bluetoothDevice == null) {
                C1645g.f7443a.error("onLeScan device is null");
                return;
            }
            C1645g.f7443a.info("onLeScan device = " + bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress());
            if (C1645g.f7445i != null) {
                C1645g.f7445i.a("0000180D-0000-1000-8000-00805F9B34FB", bluetoothDevice);
            }
        }
    }

    @TargetApi(21)
    /* compiled from: BleSensorScanner */
    /* renamed from: com.beastbikes.android.ble.biz.c.g$a */
    private static class C1644a extends ScanCallback {
        private C1644a() {
        }

        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        public void onScanFailed(int i) {
            super.onScanFailed(i);
        }

        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (C1645g.f7445i == null) {
                C1645g.f7443a.error("onScanResult IScanCallBack is null");
                return;
            }
            BluetoothDevice device = scanResult.getDevice();
            if (device == null) {
                C1645g.f7443a.error("onScanResult device is null");
                return;
            }
            C1645g.f7443a.info("onScanResult device = " + device.getName() + ":" + device.getAddress());
            ScanRecord scanRecord = scanResult.getScanRecord();
            if (scanRecord == null) {
                C1645g.f7443a.error("onScanResult scanRecord is null");
                return;
            }
            List serviceUuids = scanRecord.getServiceUuids();
            if (serviceUuids != null) {
                if (serviceUuids.contains(ParcelUuid.fromString("00001816-0000-1000-8000-00805F9B34FB"))) {
                    C1645g.f7445i.a("00001816-0000-1000-8000-00805F9B34FB", device);
                }
                if (serviceUuids.contains(ParcelUuid.fromString("0000180D-0000-1000-8000-00805F9B34FB"))) {
                    C1645g.f7445i.a("0000180D-0000-1000-8000-00805F9B34FB", device);
                }
                if (serviceUuids.contains(ParcelUuid.fromString("00001818-0000-1000-8000-00805F9B34FB"))) {
                    C1645g.f7445i.a("00001818-0000-1000-8000-00805F9B34FB", device);
                }
            }
        }
    }

    /* renamed from: a */
    public static C1645g m8857a() {
        if (f7444b == null) {
            synchronized (C1645g.class) {
                f7444b = new C1645g();
            }
        }
        return f7444b;
    }

    private C1645g() {
    }

    /* renamed from: a */
    public void m8864a(C1385n c1385n) {
        f7445i = c1385n;
        if (this.f7446c == null) {
            this.f7446c = BluetoothAdapter.getDefaultAdapter();
        }
        if (!this.f7447d && this.f7446c.isEnabled()) {
            synchronized (this.f7448e) {
                this.f7447d = true;
                if (VERSION.SDK_INT >= 21) {
                    m8860e();
                } else {
                    m8861f();
                }
                f7443a.info("start(), startScan is start...");
            }
        }
    }

    @TargetApi(21)
    /* renamed from: e */
    private void m8860e() {
        if (this.f7450g == null) {
            this.f7450g = this.f7446c.getBluetoothLeScanner();
        }
        if (this.f7451h == null) {
            this.f7451h = new C1644a();
        }
        List arrayList = new ArrayList();
        ScanFilter build = new Builder().setServiceUuid(ParcelUuid.fromString("0000180D-0000-1000-8000-00805F9B34FB")).build();
        ScanFilter build2 = new Builder().setServiceUuid(ParcelUuid.fromString("00001816-0000-1000-8000-00805F9B34FB")).build();
        ScanFilter build3 = new Builder().setServiceUuid(ParcelUuid.fromString("00001818-0000-1000-8000-00805F9B34FB")).build();
        arrayList.add(build);
        arrayList.add(build2);
        arrayList.add(build3);
        this.f7450g.startScan(arrayList, new ScanSettings.Builder().setScanMode(2).build(), this.f7451h);
    }

    @TargetApi(18)
    /* renamed from: f */
    private void m8861f() {
        if (this.f7449f == null) {
            this.f7449f = new C16431(this);
        }
        this.f7446c.startLeScan(new UUID[]{UUID.fromString("0000180D-0000-1000-8000-00805F9B34FB")}, this.f7449f);
        this.f7446c.startLeScan(new UUID[]{UUID.fromString("00001818-0000-1000-8000-00805F9B34FB")}, this.f7449f);
        this.f7446c.startLeScan(new UUID[]{UUID.fromString("00001816-0000-1000-8000-00805F9B34FB")}, this.f7449f);
    }

    /* renamed from: b */
    public void m8865b() {
        if (this.f7447d) {
            synchronized (this.f7448e) {
                this.f7447d = false;
                if (VERSION.SDK_INT >= 21) {
                    m8862g();
                } else {
                    m8863h();
                }
                f7443a.info("stop(), stopScan is start...");
            }
        }
    }

    @TargetApi(21)
    /* renamed from: g */
    private void m8862g() {
        if (this.f7450g != null && this.f7451h != null) {
            this.f7450g.stopScan(this.f7451h);
        }
    }

    @TargetApi(18)
    /* renamed from: h */
    private void m8863h() {
        if (this.f7449f != null) {
            if (this.f7446c == null) {
                this.f7446c = BluetoothAdapter.getDefaultAdapter();
            }
            this.f7446c.stopLeScan(this.f7449f);
        }
    }
}
