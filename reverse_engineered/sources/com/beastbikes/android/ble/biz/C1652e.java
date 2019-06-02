package com.beastbikes.android.ble.biz;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p096a.C1615b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(19)
/* compiled from: CentralConnector */
/* renamed from: com.beastbikes.android.ble.biz.e */
public class C1652e extends BluetoothGattCallback {
    /* renamed from: a */
    private static final Logger f7471a = LoggerFactory.getLogger("BLE-CentralConnector");
    /* renamed from: b */
    private final Object f7472b = new Object();
    /* renamed from: c */
    private Context f7473c;
    /* renamed from: d */
    private C1597a f7474d;
    /* renamed from: e */
    private boolean f7475e;
    /* renamed from: f */
    private String f7476f;
    /* renamed from: g */
    private boolean f7477g;

    /* compiled from: CentralConnector */
    /* renamed from: com.beastbikes.android.ble.biz.e$a */
    public interface C1597a {
        /* renamed from: a */
        void mo3127a();

        /* renamed from: a */
        void mo3129a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

        /* renamed from: a */
        void mo3130a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

        /* renamed from: b */
        void mo3132b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

        /* renamed from: b */
        void mo3133b(C1614a c1614a);
    }

    public C1652e(C1597a c1597a, Context context) {
        this.f7473c = context;
        this.f7474d = c1597a;
    }

    /* renamed from: a */
    public synchronized void m8904a(C1614a c1614a) {
        if (c1614a == null) {
            f7471a.warn("Connection error, because the session to be connected is empty!");
        } else {
            C1614a b = C1661h.m8999a().m9004b();
            if (!(b == null || TextUtils.equals(c1614a.m8728a(), b.m8728a()))) {
                this.f7475e = false;
                m8906b(b);
            }
            this.f7477g = true;
            BluetoothDevice b2 = c1614a.m8734b();
            synchronized (this.f7472b) {
                if (b2 == null) {
                    f7471a.error("connect(), BluetoothDevice is null");
                } else {
                    if (c1614a.m8737c() == 0) {
                        c1614a.m8729a(2);
                        c1614a.m8731a(b2.connectGatt(this.f7473c, false, this));
                        c1614a.m8732a(new C1615b());
                        this.f7476f = c1614a.m8728a();
                        m8901a();
                        f7471a.info("########  To Connect session =[" + c1614a + "] ########");
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m8901a() {
        f7471a.info("stopScan()...");
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
        intent.setPackage(this.f7473c.getPackageName());
        this.f7473c.startService(intent);
    }

    /* renamed from: b */
    private void m8903b() {
        f7471a.info("startScan()...");
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_START_SCAN");
        intent.setPackage(this.f7473c.getPackageName());
        this.f7473c.startService(intent);
    }

    /* renamed from: b */
    public void m8906b(C1614a c1614a) {
        if (c1614a != null) {
            c1614a.m8738c(true);
            c1614a.m8729a(0);
            c1614a.m8736b(false);
            c1614a.m8733a(false);
            BluetoothGatt f = c1614a.m8741f();
            if (f != null) {
                f.disconnect();
                f.close();
                this.f7475e = false;
                f7471a.info("disConnect(), disconnect centralId = " + c1614a.m8728a());
            }
            this.f7476f = "";
            return;
        }
        f7471a.warn("Connect error , because session is empty!");
    }

    /* renamed from: c */
    public void m8907c(C1614a c1614a) {
        if (c1614a != null) {
            BluetoothDevice b = c1614a.m8734b();
            if (b != null) {
                try {
                    b.getClass().getMethod("removeBond", (Class[]) null).invoke(b, (Object[]) null);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toasts.show(this.f7473c, (int) C1373R.string.speed_force_activity_unbind_fail);
                }
                c1614a.m8738c(true);
                c1614a.m8733a(false);
                c1614a.m8736b(false);
                return;
            }
            return;
        }
        f7471a.warn("Connect error , because session is empty!");
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (bluetoothGatt != null) {
            f7471a.info("onConnectionStateChange status =[" + i + "], new State=[" + i2 + "]");
            switch (i) {
                case 0:
                    switch (i2) {
                        case 0:
                            m8902a(bluetoothGatt);
                            return;
                        case 2:
                            C1614a b = C1661h.m8999a().m9005b(this.f7476f);
                            if (b != null) {
                                f7471a.info("Current device is connected state = [" + b.m8737c() + "], connected = [" + this.f7475e + "]");
                                if (b.m8737c() != 3 && b.m8737c() != 4) {
                                    b.m8729a(3);
                                } else {
                                    return;
                                }
                            }
                            if (this.f7475e) {
                                Intent intent = new Intent("com.beastbikes.android.ble.connected.action");
                                intent.addCategory("android.intent.category.DEFAULT");
                                this.f7473c.sendBroadcast(intent);
                                if (b != null) {
                                    b.m8729a(4);
                                    if (this.f7474d != null) {
                                        b.m8731a(bluetoothGatt);
                                        this.f7474d.mo3133b(b);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            BluetoothDevice device = bluetoothGatt.getDevice();
                            boolean discoverServices = bluetoothGatt.discoverServices();
                            f7471a.info("DiscoverServices ret =[" + discoverServices + "] ,deviceName =[" + device.getName() + "] ,address= [" + device.getAddress() + "]");
                            if (!discoverServices) {
                                m8903b();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                default:
                    switch (i2) {
                        case 0:
                            f7471a.error("异常连接断开， status ＝ " + i + ", state = " + i2);
                            m8902a(bluetoothGatt);
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    /* renamed from: a */
    private synchronized void m8902a(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            f7471a.error("BluetoothGatt is null");
        } else {
            this.f7475e = false;
            C1614a b = C1661h.m8999a().m9005b(this.f7476f);
            if (b == null) {
                f7471a.error("CentralSession is null");
            } else if (b.m8737c() == 0) {
                f7471a.info("当前设备已处于连接断开状态");
            } else {
                if (b.m8737c() == 4 && this.f7477g) {
                    Toasts.showOnUiThreadWithResId(this.f7473c, C1373R.string.toast_bluetooth_disconnect);
                }
                b.m8729a(0);
                C1615b i = b.m8744i();
                i.m8752b(false);
                i.m8763f(false);
                i.m8761e(false);
                i.m8755c(false);
                i.m8759d(false);
                f7471a.info("handleDisconnected status=[" + i + "]");
                Intent intent = new Intent("com.beastbikes.android.ble.disconnected.action");
                intent.addCategory("android.intent.category.DEFAULT");
                this.f7473c.sendBroadcast(intent);
                if (this.f7474d != null) {
                    this.f7474d.mo3127a();
                }
                BluetoothDevice device = bluetoothGatt.getDevice();
                f7471a.error(device.getName() + "@" + device.getAddress() + " disconnected");
                bluetoothGatt.close();
                if (!b.m8745j()) {
                    m8903b();
                }
                if (VERSION.RELEASE.equals("5.1") || (VERSION.RELEASE.equals("7.0") && Build.MANUFACTURER.equals("Xiaomi"))) {
                    f7471a.error("版本号等于 5.1的做解绑处理");
                    m8907c(b);
                    b.m8738c(false);
                    b.m8736b(true);
                }
            }
        }
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (bluetoothGatt != null) {
            this.f7475e = true;
            f7471a.info("onServicesDiscovered gatt=[" + bluetoothGatt + "],status=[" + i + "], connected =[" + true + "]");
            for (BluetoothGattService uuid : bluetoothGatt.getServices()) {
                f7471a.error("GattService UUID = " + uuid.getUuid().toString());
            }
            if (i == 0) {
                C1614a c = C1661h.m8999a().m9007c(this.f7476f);
                if (c != null && this.f7474d != null) {
                    c.m8731a(bluetoothGatt);
                    this.f7474d.mo3133b(c);
                }
            }
        }
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (this.f7474d != null) {
            this.f7474d.mo3130a(bluetoothGatt, bluetoothGattCharacteristic, i);
        }
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (this.f7474d != null) {
            this.f7474d.mo3132b(bluetoothGatt, bluetoothGattCharacteristic, i);
        }
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.f7474d != null) {
            this.f7474d.mo3129a(bluetoothGatt, bluetoothGattCharacteristic);
        }
    }

    /* renamed from: a */
    public void m8905a(boolean z) {
        this.f7477g = z;
    }
}
