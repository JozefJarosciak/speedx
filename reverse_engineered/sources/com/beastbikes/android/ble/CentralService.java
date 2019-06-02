package com.beastbikes.android.ble;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$a;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.C1613b;
import com.beastbikes.android.ble.biz.C1652e;
import com.beastbikes.android.ble.biz.C1652e.C1597a;
import com.beastbikes.android.ble.biz.C1656f;
import com.beastbikes.android.ble.biz.C1660g;
import com.beastbikes.android.ble.biz.C1660g.C1598b;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.S605CentralInvocation;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1617a;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(19)
public class CentralService extends Service implements C1371a, C1597a, C1598b {
    /* renamed from: a */
    private static final Logger f7325a = LoggerFactory.getLogger("BLE-CentralService");
    /* renamed from: b */
    private BluetoothAdapter f7326b;
    /* renamed from: c */
    private C1660g f7327c;
    /* renamed from: d */
    private C1652e f7328d;
    /* renamed from: e */
    private C1613b f7329e;
    /* renamed from: f */
    private SharedPreferences f7330f;
    /* renamed from: g */
    private volatile Looper f7331g;
    /* renamed from: h */
    private volatile C1594a f7332h;
    /* renamed from: i */
    private C1595b f7333i;
    /* renamed from: j */
    private C1617a f7334j;
    /* renamed from: k */
    private String f7335k;
    /* renamed from: l */
    private String f7336l;
    /* renamed from: m */
    private int f7337m = 0;

    /* renamed from: com.beastbikes.android.ble.CentralService$a */
    private final class C1594a extends Handler {
        /* renamed from: a */
        final /* synthetic */ CentralService f7322a;

        public C1594a(CentralService centralService, Looper looper) {
            this.f7322a = centralService;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    this.f7322a.f7329e.mo3175h();
                    return;
                case 3:
                    this.f7322a.f7329e.mo3154a(message.obj, message.arg1);
                    return;
                case 4:
                    this.f7322a.f7329e.mo3164b(message.obj, message.arg1);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.beastbikes.android.ble.CentralService$b */
    private final class C1595b extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ CentralService f7323a;

        private C1595b(CentralService centralService) {
            this.f7323a = centralService;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                CentralService.f7325a.info("BondState =[" + ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getBondState() + "]");
            }
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10)) {
                    case 10:
                        CentralService.f7325a.info("Bluetooth close !");
                        this.f7323a.f7327c.m8998b();
                        break;
                    case 12:
                        CentralService.f7325a.info("Bluetooth open !");
                        this.f7323a.f7326b = BluetoothAdapter.getDefaultAdapter();
                        this.f7323a.f7327c.m8997a(true);
                        break;
                }
            }
            if ("ble.action.notification.posted.or.removed".equals(action) && this.f7323a.f7329e != null) {
                this.f7323a.f7329e.mo3155a(intent.getStringExtra("notification_title"), intent.getStringExtra("notification_text"), intent.getByteExtra("notification_type", (byte) 1));
            }
        }
    }

    /* renamed from: com.beastbikes.android.ble.CentralService$c */
    public class C1596c extends Binder {
        /* renamed from: a */
        final /* synthetic */ CentralService f7324a;

        public C1596c(CentralService centralService) {
            this.f7324a = centralService;
        }

        /* renamed from: a */
        public CentralService m8563a() {
            return this.f7324a;
        }
    }

    public void onCreate() {
        super.onCreate();
        f7325a.info("......... CentralService onCreate .........");
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            f7325a.error("Unregistered user !");
            return;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            Toasts.show((Context) this, (int) C1373R.string.speed_force_alert_bluetooth_not_support);
            f7325a.error("Bluetooth not support!");
            stopSelf();
            return;
        }
        HandlerThread handlerThread = new HandlerThread("CentralService]");
        handlerThread.start();
        this.f7331g = handlerThread.getLooper();
        this.f7332h = new C1594a(this, this.f7331g);
        this.f7326b = bluetoothManager.getAdapter();
        this.f7330f = getSharedPreferences(currentUser.getObjectId(), 0);
        this.f7329e = C1656f.m8922e();
        this.f7329e.mo3139a((Context) this, this.f7330f);
        this.f7327c = new C1660g(this.f7326b, this, this);
        this.f7328d = new C1652e(this, this);
        m8575e();
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.NOTIFICATION_LISTENER");
        intent.setPackage(getPackageName());
        startService(intent);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return i2;
        }
        if (intent.hasExtra("central_invocation_type")) {
            this.f7337m = intent.getIntExtra("central_invocation_type", this.f7337m);
            if (this.f7337m == 1) {
                this.f7329e = S605CentralInvocation.m8658j();
            } else {
                this.f7329e = C1656f.m8922e();
            }
            this.f7329e.mo3139a((Context) this, this.f7330f);
        }
        CharSequence stringExtra = intent.getStringExtra(C0861d.f2143o);
        this.f7335k = intent.getStringExtra("extra_central_id");
        this.f7336l = intent.getStringExtra("extra_central_name");
        if (TextUtils.isEmpty(stringExtra)) {
            return super.onStartCommand(intent, i, i2);
        }
        f7325a.info("###### onStartCommand cmd=" + stringExtra + ",startId" + i2 + ", centralId=" + this.f7335k + " ######");
        C1614a a = C1661h.m8999a().m9001a(this.f7335k);
        if (this.f7327c == null) {
            this.f7327c = new C1660g(this.f7326b, this, this);
        }
        if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_START_SCAN")) {
            this.f7327c.m8996a();
        } else if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_SCAN_AND_CONNECT")) {
            C1614a b = C1661h.m8999a().m9004b();
            if (b != null) {
                this.f7328d.m8906b(b);
            }
            if (a != null) {
                a.m8738c(false);
                a.m8736b(true);
            }
            this.f7327c.m8996a();
        } else if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_CONNECT")) {
            this.f7328d.m8904a(a);
        } else if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_DISCONNECT")) {
            this.f7328d.m8906b(a);
            stopSelf();
        } else if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_UNBOUND")) {
            this.f7328d.m8907c(a);
        } else if (TextUtils.equals(stringExtra, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN")) {
            if (this.f7328d != null) {
                this.f7328d.m8905a(false);
            }
            this.f7327c.m8998b();
        }
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        return new C1596c(this);
    }

    public void onDestroy() {
        f7325a.info("......... CentralService onDestroy .........");
        if (this.f7333i != null) {
            unregisterReceiver(this.f7333i);
            this.f7333i = null;
        }
        if (this.f7327c != null) {
            this.f7327c.m8998b();
        }
        if (this.f7331g != null) {
            this.f7331g.quit();
        }
        if (this.f7329e != null) {
            this.f7329e.mo3176i();
        }
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.NOTIFICATION_LISTENER");
        intent.setPackage(getPackageName());
        startService(intent);
        stopService(intent);
        super.onDestroy();
    }

    /* renamed from: a */
    public void mo3131a(C1614a c1614a) {
        f7325a.info("onScanResult isAddNew=[" + C1661h.m8999a().m9012g() + "], session =[" + c1614a + "]");
        if (c1614a != null) {
            List a = C1661h.m8999a().m9002a(c1614a);
            if (this.f7334j != null) {
                this.f7334j.m8782a(a, c1614a);
            }
            if (!C1661h.m8999a().m9012g()) {
                if (!c1614a.m8739d() && c1614a.m8740e() != 4) {
                    return;
                }
                if (c1614a.m8742g() || TextUtils.equals(c1614a.m8728a(), this.f7335k) || TextUtils.equals(c1614a.m8743h(), this.f7336l)) {
                    if (c1614a.m8740e() == 4) {
                        this.f7329e = S605CentralInvocation.m8658j();
                    } else {
                        this.f7329e = C1656f.m8922e();
                    }
                    this.f7329e.mo3139a((Context) this, this.f7330f);
                    this.f7328d.m8904a(c1614a);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo3128a(int i) {
        if (this.f7334j != null) {
            this.f7334j.m8782a(C1661h.m8999a().m9008d(), null);
        }
    }

    /* renamed from: a */
    public void mo3127a() {
        this.f7332h.sendMessage(this.f7332h.obtainMessage(2));
    }

    /* renamed from: b */
    public void mo3133b(C1614a c1614a) {
        this.f7332h.sendMessage(this.f7332h.obtainMessage(3, 0, 0, c1614a));
    }

    /* renamed from: a */
    public void mo3130a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        f7325a.info("onCharacteristicWrite: " + bluetoothGattCharacteristic.getUuid().toString() + ", " + aa.m12770a(bluetoothGattCharacteristic.getValue()) + ", status = " + i + " Thread [" + Thread.currentThread().getName() + "]");
        this.f7332h.sendMessage(this.f7332h.obtainMessage(4, i, 0, bluetoothGattCharacteristic));
    }

    /* renamed from: b */
    public void mo3132b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        f7325a.info("onCharacteristicRead: " + bluetoothGattCharacteristic.getUuid().toString() + ", " + aa.m12770a(bluetoothGattCharacteristic.getValue()) + ", status = " + i + " Thread [" + Thread.currentThread().getName() + "]");
        if (this.f7329e != null) {
            this.f7329e.mo3138a(bluetoothGattCharacteristic);
        }
    }

    /* renamed from: a */
    public void mo3129a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            if (!(uuid.equals(a$a.f7185c.toString()) || uuid.equals(a$a.f7186d.toString()))) {
                f7325a.info("onCharacteristicChanged: " + bluetoothGattCharacteristic.getUuid().toString() + ", " + aa.m12770a(bluetoothGattCharacteristic.getValue()) + ", Thread [" + Thread.currentThread().getName() + "]");
            }
        }
        if (this.f7329e != null) {
            this.f7329e.mo3138a(bluetoothGattCharacteristic);
        }
    }

    /* renamed from: a */
    public void m8581a(C1617a c1617a) {
        this.f7334j = c1617a;
    }

    /* renamed from: b */
    public C1602a m8582b() {
        return this.f7329e;
    }

    /* renamed from: c */
    public C1603b m8585c() {
        if (this.f7329e instanceof C1603b) {
            return (C1603b) this.f7329e;
        }
        return null;
    }

    /* renamed from: e */
    private void m8575e() {
        if (this.f7333i == null) {
            this.f7333i = new C1595b();
        }
        if (!this.f7333i.isOrderedBroadcast()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("ble.action.notification.posted.or.removed");
            registerReceiver(this.f7333i, intentFilter);
        }
    }
}
