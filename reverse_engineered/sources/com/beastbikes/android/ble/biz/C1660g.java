package com.beastbikes.android.ble.biz;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CentralScanner */
/* renamed from: com.beastbikes.android.ble.biz.g */
public class C1660g {
    /* renamed from: a */
    private static final Logger f7513a = LoggerFactory.getLogger("BLE-CentralScanner");
    /* renamed from: b */
    private BluetoothAdapter f7514b;
    /* renamed from: c */
    private ScanCallback f7515c = null;
    /* renamed from: d */
    private LeScanCallback f7516d;
    /* renamed from: e */
    private boolean f7517e = false;
    /* renamed from: f */
    private final Object f7518f = new Object();
    /* renamed from: g */
    private C1598b f7519g;
    /* renamed from: h */
    private Context f7520h;
    /* renamed from: i */
    private C1651c f7521i;

    /* compiled from: CentralScanner */
    /* renamed from: com.beastbikes.android.ble.biz.g$b */
    public interface C1598b {
        /* renamed from: a */
        void mo3128a(int i);

        /* renamed from: a */
        void mo3131a(C1614a c1614a);
    }

    /* compiled from: CentralScanner */
    /* renamed from: com.beastbikes.android.ble.biz.g$2 */
    class C16582 implements LeScanCallback {
        /* renamed from: a */
        final /* synthetic */ C1660g f7509a;

        C16582(C1660g c1660g) {
            this.f7509a = c1660g;
        }

        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (this.f7509a.f7517e) {
                this.f7509a.m8985a(bluetoothDevice, bArr);
            } else {
                this.f7509a.f7514b.stopLeScan(this);
            }
        }
    }

    /* compiled from: CentralScanner */
    /* renamed from: com.beastbikes.android.ble.biz.g$a */
    private static class C1659a {
        /* renamed from: a */
        byte f7510a;
        /* renamed from: b */
        String f7511b;
        /* renamed from: c */
        Short f7512c;

        private C1659a() {
        }
    }

    public C1660g(BluetoothAdapter bluetoothAdapter, C1598b c1598b, Context context) {
        this.f7514b = bluetoothAdapter;
        this.f7519g = c1598b;
        this.f7520h = context;
    }

    /* renamed from: a */
    public void m8997a(boolean z) {
        if (z) {
            this.f7517e = false;
        }
        m8996a();
    }

    /* renamed from: a */
    public void m8996a() {
        if (this.f7514b != null) {
            f7513a.info("######## Start scanning =[" + this.f7517e + "],enabled=[" + this.f7514b.isEnabled() + "] ########");
            if (!this.f7517e && this.f7514b.isEnabled()) {
                synchronized (this.f7518f) {
                    this.f7517e = true;
                    if (VERSION.SDK_INT >= 21) {
                        m8994f();
                    } else {
                        m8995g();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public synchronized void m8998b() {
        f7513a.info("######## Stop scanning =[" + this.f7517e + "] ########");
        if (this.f7517e) {
            if (VERSION.SDK_INT < 21) {
                m8992d();
            } else {
                m8993e();
            }
            if (this.f7519g != null) {
                this.f7519g.mo3128a(0);
            }
            synchronized (this.f7518f) {
                this.f7517e = false;
            }
        }
    }

    @TargetApi(18)
    /* renamed from: d */
    private void m8992d() {
        if (this.f7514b != null && this.f7516d != null) {
            this.f7514b.stopLeScan(this.f7516d);
            this.f7516d = null;
        }
    }

    @TargetApi(21)
    /* renamed from: e */
    private void m8993e() {
        if (this.f7514b != null && this.f7514b.getBluetoothLeScanner() != null && this.f7515c != null) {
            this.f7514b.getBluetoothLeScanner().stopScan(this.f7515c);
            this.f7515c = null;
        }
    }

    @TargetApi(21)
    /* renamed from: f */
    private void m8994f() {
        if (this.f7514b.isEnabled()) {
            final BluetoothLeScanner bluetoothLeScanner = this.f7514b.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                this.f7515c = new ScanCallback(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1660g f7508b;

                    public void onScanResult(int i, ScanResult scanResult) {
                        super.onScanResult(i, scanResult);
                        if (!this.f7508b.f7517e) {
                            bluetoothLeScanner.stopScan(this);
                            this.f7508b.f7517e = false;
                        }
                        ScanRecord scanRecord = scanResult.getScanRecord();
                        if (scanRecord != null) {
                            this.f7508b.m8985a(scanResult.getDevice(), scanRecord.getBytes());
                        }
                    }

                    public void onBatchScanResults(List<ScanResult> list) {
                        super.onBatchScanResults(list);
                    }

                    public void onScanFailed(int i) {
                        super.onScanFailed(i);
                        C1660g.f7513a.error("Bluetooth scan failed, errorCode is " + i);
                        if (i == 2) {
                            bluetoothLeScanner.stopScan(this);
                        }
                        if (this.f7508b.f7519g != null) {
                            this.f7508b.f7519g.mo3128a(i);
                        }
                    }
                };
                bluetoothLeScanner.startScan(this.f7515c);
                f7513a.info("startLeScan");
            }
        }
    }

    @TargetApi(18)
    /* renamed from: g */
    private void m8995g() {
        if (this.f7514b.isEnabled()) {
            this.f7516d = new C16582(this);
            boolean startLeScan = this.f7514b.startLeScan(this.f7516d);
            if (!startLeScan) {
                if (this.f7519g != null) {
                    this.f7519g.mo3128a(-1);
                }
                this.f7514b.stopLeScan(this.f7516d);
                this.f7517e = false;
                m8996a();
            }
            f7513a.info("Start scan ＝[ " + startLeScan + "]");
        }
    }

    /* renamed from: a */
    private void m8985a(BluetoothDevice bluetoothDevice, byte[] bArr) {
        C1614a a = m8984a(bArr, bluetoothDevice);
        if (this.f7519g != null && a != null) {
            this.f7519g.mo3131a(a);
        }
    }

    /* renamed from: a */
    private C1614a m8984a(byte[] bArr, BluetoothDevice bluetoothDevice) {
        int i = 2;
        boolean z = true;
        if (bluetoothDevice == null) {
            return null;
        }
        C1659a c1659a = new C1659a();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() > 2) {
            byte b = order.get();
            if (b == (byte) 0) {
                return null;
            }
            int i2 = (byte) (b - 1);
            switch (order.get()) {
                case (byte) -1:
                    c1659a.f7512c = Short.valueOf(order.getShort());
                    i2 = (byte) (i2 - 2);
                    break;
                case (byte) 1:
                    c1659a.f7510a = order.get();
                    i2 = (byte) (i2 - 1);
                    break;
                case (byte) 9:
                    byte[] bArr2 = new byte[i2];
                    order.get(bArr2, 0, i2);
                    c1659a.f7511b = new String(bArr2).trim();
                    i2 = 0;
                    break;
                case (byte) 22:
                    if (i2 < 9) {
                        return null;
                    }
                    short s = order.getShort();
                    r0 = new byte[6];
                    order.get(r0, 0, 6);
                    String format = String.format("%02X%02X%02X%02X%02X%02X", new Object[]{Byte.valueOf(r0[0]), Byte.valueOf(r0[1]), Byte.valueOf(r0[2]), Byte.valueOf(r0[3]), Byte.valueOf(r0[4]), Byte.valueOf(r0[5])});
                    int i3 = order.get();
                    i2 = s & 65535;
                    if (bluetoothDevice.getBondState() == 12) {
                        i3 = (byte) 1;
                    }
                    if (VERSION.RELEASE.equals("5.1") || (VERSION.RELEASE.equals("7.0") && Build.MANUFACTURER.equals("Xiaomi"))) {
                        if (this.f7521i == null) {
                            this.f7521i = new C1651c(this.f7520h);
                        }
                        if (this.f7521i.m8880a(format) != null) {
                            i3 = 1;
                        }
                    }
                    f7513a.info("isConnect = " + i3 + ", UUID = " + Integer.toHexString(i2) + ", DeviceName = " + bluetoothDevice.getName() + ", Address = " + format + ", adv_data=" + Arrays.toString(bArr));
                    switch (i2) {
                        case 52496:
                            i = 0;
                            break;
                        case 52497:
                            i = 1;
                            break;
                        case 52498:
                            break;
                        case 52499:
                            i = 3;
                            break;
                        case 52500:
                            i = 4;
                            break;
                        default:
                            i = 0;
                            break;
                    }
                    C1661h a = C1661h.m8999a();
                    if (i3 != 1) {
                        z = false;
                    }
                    return a.m9000a(bluetoothDevice, i, z, format);
            }
            if (i2 > 0) {
                try {
                    order.position(i2 + order.position());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
