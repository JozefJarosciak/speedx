package com.beastbikes.android.ble.biz;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alibaba.fastjson.asm.Opcodes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$a;
import com.beastbikes.android.a$b;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1618b;
import com.beastbikes.android.ble.biz.p097b.C1619c;
import com.beastbikes.android.ble.biz.p097b.C1620d;
import com.beastbikes.android.ble.biz.p097b.C1621e;
import com.beastbikes.android.ble.biz.p097b.C1622f;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.biz.p097b.C1624h;
import com.beastbikes.android.ble.biz.p097b.C1625i;
import com.beastbikes.android.ble.biz.p097b.C1626j;
import com.beastbikes.android.ble.biz.p097b.C1627k;
import com.beastbikes.android.ble.biz.p097b.C1628l;
import com.beastbikes.android.ble.biz.p097b.C1629m;
import com.beastbikes.android.ble.biz.p097b.C1630n;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.dto.C1667b;
import com.beastbikes.android.ble.dto.C1668c;
import com.beastbikes.android.ble.dto.C1669d;
import com.beastbikes.android.ble.dto.S605FirmwareInfo;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.OTARequestCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.ProtocolParserImpl;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.business.BusinessException;
import com.google.common.base.Ascii;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.services.commons.models.Position;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(19)
public class S605CentralInvocation extends C1613b implements Parcelable, C1371a, C1603b {
    public static final Creator<S605CentralInvocation> CREATOR = new C16129();
    /* renamed from: a */
    private static final Logger f7355a = LoggerFactory.getLogger(S605CentralInvocation.class);
    /* renamed from: b */
    private static S605CentralInvocation f7356b;
    /* renamed from: c */
    private BluetoothGatt f7357c;
    /* renamed from: d */
    private ProtocolParserImpl f7358d = new ProtocolParserImpl();
    /* renamed from: e */
    private C1614a f7359e;
    /* renamed from: f */
    private C2389c f7360f;
    /* renamed from: g */
    private SharedPreferences f7361g;
    /* renamed from: h */
    private Context f7362h;
    /* renamed from: i */
    private C1651c f7363i;
    /* renamed from: j */
    private Handler f7364j = new Handler(Looper.getMainLooper());
    /* renamed from: k */
    private byte[] f7365k;
    /* renamed from: l */
    private C1626j f7366l;
    /* renamed from: m */
    private C1629m f7367m;
    /* renamed from: n */
    private C1630n f7368n;
    /* renamed from: o */
    private C1628l f7369o;
    /* renamed from: p */
    private C1625i f7370p;
    /* renamed from: q */
    private C1619c f7371q;
    /* renamed from: r */
    private C1620d f7372r;
    /* renamed from: s */
    private C1623g f7373s;
    /* renamed from: t */
    private ArrayList<ArrayList<Integer>> f7374t;
    /* renamed from: u */
    private List<LatLng> f7375u;
    /* renamed from: v */
    private OTARequestCommandCharacteristic f7376v;
    /* renamed from: w */
    private HashMap<Integer, byte[]> f7377w = new HashMap();
    /* renamed from: x */
    private ByteBuffer f7378x;

    /* renamed from: com.beastbikes.android.ble.biz.S605CentralInvocation$1 */
    class C16041 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ S605CentralInvocation f7342a;

        C16041(S605CentralInvocation s605CentralInvocation) {
            this.f7342a = s605CentralInvocation;
        }

        public void run() {
            this.f7342a.f7371q.m8784a();
        }
    }

    /* renamed from: com.beastbikes.android.ble.biz.S605CentralInvocation$6 */
    class C16096 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ S605CentralInvocation f7352a;

        C16096(S605CentralInvocation s605CentralInvocation) {
            this.f7352a = s605CentralInvocation;
        }

        public void run() {
            this.f7352a.f7371q.m8785a(this.f7352a.f7374t);
        }
    }

    /* renamed from: com.beastbikes.android.ble.biz.S605CentralInvocation$7 */
    class C16107 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ S605CentralInvocation f7353a;

        C16107(S605CentralInvocation s605CentralInvocation) {
            this.f7353a = s605CentralInvocation;
        }

        public void run() {
            this.f7353a.f7372r.m8786a();
        }
    }

    /* renamed from: com.beastbikes.android.ble.biz.S605CentralInvocation$8 */
    class C16118 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ S605CentralInvocation f7354a;

        C16118(S605CentralInvocation s605CentralInvocation) {
            this.f7354a = s605CentralInvocation;
        }

        public void run() {
            this.f7354a.f7372r.m8787b();
        }
    }

    /* renamed from: com.beastbikes.android.ble.biz.S605CentralInvocation$9 */
    static class C16129 implements Creator<S605CentralInvocation> {
        C16129() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m8624a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m8625a(i);
        }

        /* renamed from: a */
        public S605CentralInvocation m8624a(Parcel parcel) {
            return S605CentralInvocation.f7356b;
        }

        /* renamed from: a */
        public S605CentralInvocation[] m8625a(int i) {
            return new S605CentralInvocation[0];
        }
    }

    /* renamed from: j */
    public static S605CentralInvocation m8658j() {
        if (f7356b == null) {
            synchronized (S605CentralInvocation.class) {
                f7356b = new S605CentralInvocation();
            }
        }
        return f7356b;
    }

    private S605CentralInvocation() {
    }

    /* renamed from: a */
    public void mo3139a(Context context, SharedPreferences sharedPreferences) {
        this.f7362h = context;
        this.f7360f = new C2389c(context);
        this.f7363i = new C1651c(context);
        this.f7361g = sharedPreferences;
    }

    @Nullable
    /* renamed from: p */
    private BluetoothGattCharacteristic m8661p() {
        if (this.f7357c == null) {
            f7355a.error("getS605RequestCharacter(), BluetoothGatt is null");
            return null;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service != null) {
            return service.getCharacteristic(a$b.f7195c);
        }
        f7355a.error("getS605RequestCharacter(), BluetoothGattService is null");
        return null;
    }

    @Nullable
    /* renamed from: q */
    private BluetoothGattCharacteristic m8662q() {
        if (this.f7357c == null) {
            f7355a.error("getS605ConfigurationCharacter(), BluetoothGatt is null");
            return null;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service != null) {
            return service.getCharacteristic(a$b.f7194b);
        }
        f7355a.error("getS605ConfigurationCharacter(), BluetoothGattService is null");
        return null;
    }

    @Nullable
    /* renamed from: r */
    private BluetoothGattCharacteristic m8663r() {
        if (this.f7357c == null) {
            f7355a.error("getS605OtaCharacter(), BluetoothGatt is null");
            return null;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service != null) {
            return service.getCharacteristic(a$b.f7197e);
        }
        f7355a.error("getS605OtaCharacter(), BluetoothGattService is null");
        return null;
    }

    /* renamed from: s */
    private boolean m8664s() {
        if (this.f7357c == null) {
            f7355a.error("registerS605ResponseNotify(), BluetoothGatt is null");
            return false;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service == null) {
            f7355a.error("registerS605ResponseNotify(), BluetoothGattService is null");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$b.f7196d);
        if (characteristic == null) {
            f7355a.error("registerS605ResponseNotify(), commandResponseCharacter is null");
            return false;
        }
        characteristic.setWriteType(1);
        this.f7357c.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            f7355a.error("registerS605ResponseNotify(), command BluetoothGattDescriptor is null");
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7357c.writeDescriptor(descriptor);
        f7355a.info("S605 command response notification uuid = " + characteristic.getUuid().toString() + "; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: t */
    private boolean m8665t() {
        if (this.f7357c == null) {
            f7355a.error("registerS605Notify(), BluetoothGatt is null");
            return false;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service == null) {
            f7355a.error("registerS605Notify(), BluetoothGattService is null");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$b.f7199g);
        if (characteristic == null) {
            f7355a.error("registerS605Notify(), notify is null");
            return false;
        }
        characteristic.setWriteType(1);
        this.f7357c.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            f7355a.error("registerS605Notify(), notify BluetoothGattDescriptor is null");
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7357c.writeDescriptor(descriptor);
        f7355a.info("S605 notification uuid = " + characteristic.getUuid().toString() + "; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: u */
    private boolean m8666u() {
        if (this.f7357c == null) {
            f7355a.error("registerS605ConfigNotify(), BluetoothGatt is null");
            return false;
        }
        BluetoothGattService service = this.f7357c.getService(a$b.f7193a);
        if (service == null) {
            f7355a.error("registerS605ConfigNotify(), BluetoothGattService is null");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$b.f7198f);
        if (characteristic == null) {
            f7355a.error("registerS605ConfigNotify(), characteristic is null");
            return false;
        }
        characteristic.setWriteType(1);
        this.f7357c.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            f7355a.error("registerS605ConfigNotify(), config notify BluetoothGattDescriptor is null");
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7357c.writeDescriptor(descriptor);
        f7355a.info("S605 config notification uuid = " + characteristic.getUuid().toString() + "; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: v */
    private void m8667v() {
        if (!this.f7359e.m8744i().m8766g()) {
            this.f7359e.m8744i().m8761e(m8664s());
        }
        if (!this.f7359e.m8744i().m8764f()) {
            m8635a(200);
            this.f7359e.m8744i().m8759d(m8665t());
        }
        if (!this.f7359e.m8744i().m8768h()) {
            m8635a(200);
            this.f7359e.m8744i().m8763f(m8666u());
        }
    }

    /* renamed from: a */
    public void mo3138a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            f7355a.error("handleCharacteristicChanged() characteristic is null");
        } else {
            m8645c(bluetoothGattCharacteristic);
        }
    }

    /* renamed from: b */
    public void mo3164b(Object obj, int i) {
        if (obj == null) {
            f7355a.error("handleCharacteristicWrite() characteristic is null");
        } else if (i == 0) {
            m8642b((BluetoothGattCharacteristic) obj);
        } else {
            f7355a.error("handleCharacteristicWrite() BluetoothGatt statue is not success, status = " + i);
        }
    }

    /* renamed from: h */
    public void mo3175h() {
    }

    /* renamed from: a */
    public void m8693a(Object obj) {
        f7355a.info("接收到开始传输数据命令");
        if (obj != null) {
            this.f7376v = (OTARequestCommandCharacteristic) obj;
            if (!this.f7359e.m8744i().m8753b()) {
                f7355a.info("handleOTAPacketUpdate(), request ota packet start " + m8724n());
            }
        }
    }

    /* renamed from: a */
    public void mo3154a(Object obj, int i) {
        if (obj != null) {
            this.f7359e = (C1614a) obj;
            this.f7357c = this.f7359e.m8741f();
            m8667v();
            m8635a(1000);
            boolean l = m8722l();
            int i2 = 0;
            while (!l && i2 < 3) {
                m8635a(200);
                i2++;
                l = m8722l();
            }
        }
    }

    /* renamed from: a */
    public void mo3155a(String str, String str2, byte b) {
    }

    /* renamed from: a */
    public boolean mo3161a(long j, int i, String str) {
        return false;
    }

    /* renamed from: a */
    public void mo3145a(C1623g c1623g) {
        this.f7373s = c1623g;
    }

    /* renamed from: a */
    public void mo3149a(C1627k c1627k) {
    }

    /* renamed from: a */
    public void mo3148a(C1626j c1626j) {
        this.f7366l = c1626j;
    }

    /* renamed from: a */
    public void mo3140a(C1618b c1618b) {
    }

    /* renamed from: a */
    public void mo3143a(C1621e c1621e) {
    }

    /* renamed from: c */
    public C1623g mo3167c() {
        return this.f7373s;
    }

    /* renamed from: a */
    public void mo3144a(C1622f c1622f) {
    }

    /* renamed from: a */
    public void mo3146a(C1624h c1624h) {
    }

    /* renamed from: a */
    public void mo3147a(C1625i c1625i) {
        this.f7370p = c1625i;
    }

    /* renamed from: a */
    public void mo3151a(C1629m c1629m) {
        this.f7367m = c1629m;
    }

    /* renamed from: a */
    public void mo3152a(C1630n c1630n) {
        this.f7368n = c1630n;
    }

    /* renamed from: a */
    public void mo3150a(C1628l c1628l) {
        this.f7369o = c1628l;
    }

    /* renamed from: a */
    public void mo3141a(C1619c c1619c) {
        this.f7371q = c1619c;
    }

    /* renamed from: a */
    public void mo3142a(C1620d c1620d) {
        this.f7372r = c1620d;
    }

    /* renamed from: a */
    public boolean m8702a(String str) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("setWifiPassword() character is null");
            return false;
        }
        Object obj = new byte[20];
        obj[0] = 2;
        obj[1] = 2;
        if (!TextUtils.isEmpty(str)) {
            try {
                Object bytes = str.getBytes("UTF-8");
                System.arraycopy(bytes, 0, obj, 2, bytes.length);
            } catch (UnsupportedEncodingException e) {
                f7355a.error("setWifiPassword(), password getBytes is error, e = " + e);
                e.printStackTrace();
            }
        }
        obj[19] = this.f7358d.crc8(obj);
        q.setValue(obj);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: f */
    public boolean m8715f(int i) {
        int i2 = 200;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            f7355a.error("setUserInfo(), The AvUser is null");
            return false;
        }
        LocalUser a;
        try {
            a = this.f7360f.m12118a(currentUser.getObjectId());
        } catch (BusinessException e) {
            f7355a.error("setUserInfo(), getLocalUser is error, e = " + e);
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            f7355a.error("setUserInfo(), LocalUser is null");
            return false;
        }
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("setUserInfo(), Bluetooth Configuration Character is null");
            return false;
        }
        Object obj = new byte[20];
        obj[0] = 2;
        obj[1] = 1;
        switch (i) {
            case 0:
                obj[2] = null;
                Object a2 = aa.m12773a(a.getSpeedxId());
                System.arraycopy(a2, 0, obj, 3, a2.length);
                obj[7] = (byte) a.getGender();
                a2 = C1616a.m8781a(a.getBirthday());
                System.arraycopy(a2, 0, obj, 8, a2.length);
                byte[] a3 = aa.m12774a((short) ((int) a.getHeight()));
                obj[12] = a3[1];
                obj[13] = a3[0];
                byte[] a4 = aa.m12774a((short) ((int) a.getWeight()));
                obj[14] = a4[1];
                obj[15] = a4[0];
                if (this.f7361g != null) {
                    i2 = this.f7361g.getInt("PREF.USER.CURRENT.TRAIN.TARGET", 200);
                }
                byte[] a5 = aa.m12774a((short) i2);
                obj[16] = a5[1];
                obj[17] = a5[0];
                break;
            case 1:
                obj[2] = 1;
                Object nickname = a.getNickname();
                if (!TextUtils.isEmpty(nickname)) {
                    try {
                        nickname = nickname.getBytes("UTF-8");
                        System.arraycopy(nickname, 0, obj, 3, nickname.length);
                        break;
                    } catch (UnsupportedEncodingException e2) {
                        f7355a.error("setUserInfo(), the nick name getBytes error, e = " + e2);
                        e2.printStackTrace();
                        break;
                    }
                }
                break;
        }
        obj[19] = this.f7358d.crc8(obj);
        q.setValue(obj);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: a */
    public boolean mo3160a(int i, int i2, int i3, int i4, String str) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            f7355a.error("setTrainTargetConfig(), The AvUser is null");
            return false;
        }
        LocalUser a;
        try {
            a = this.f7360f.m12118a(currentUser.getObjectId());
        } catch (BusinessException e) {
            f7355a.error("setTrainTargetConfig(), getLocalUser is error, e = " + e);
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            f7355a.error("setTrainTargetConfig(), LocalUser is null");
            return false;
        }
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("setTrainTargetConfig(), Bluetooth Configuration Character is null");
            return false;
        }
        Object obj = new byte[20];
        obj[0] = 2;
        obj[1] = 1;
        obj[2] = 2;
        Object a2 = aa.m12773a(a.getSpeedxId());
        System.arraycopy(a2, 0, obj, 3, a2.length);
        obj[7] = (byte) i2;
        a2 = C1616a.m8781a(str);
        System.arraycopy(a2, 0, obj, 8, a2.length);
        byte[] a3 = aa.m12774a((short) i3);
        obj[12] = a3[1];
        obj[13] = a3[0];
        a3 = aa.m12774a((short) i4);
        obj[14] = a3[1];
        obj[15] = a3[0];
        if (this.f7361g != null && i == 0) {
            i = this.f7361g.getInt("PREF.USER.CURRENT.TRAIN.TARGET", 200);
        }
        a3 = aa.m12774a((short) i);
        obj[16] = a3[1];
        obj[17] = a3[0];
        obj[19] = this.f7358d.crc8(obj);
        q.setValue(obj);
        q.setWriteType(1);
        return this.f7357c.writeCharacteristic(q);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: g */
    public boolean m8717g(int r7) {
        /*
        r6 = this;
        r5 = 2;
        r1 = 0;
        r0 = r6.f7361g;
        if (r0 != 0) goto L_0x000f;
    L_0x0006:
        r0 = f7355a;
        r2 = "setSessionToken(), UserSp is null";
        r0.info(r2);
        r0 = r1;
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = r6.f7361g;
        r2 = "PREF.USER.SESSION.TOKEN";
        r3 = "";
        r0 = r0.getString(r2, r3);
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 == 0) goto L_0x003f;
    L_0x001f:
        r0 = new com.beastbikes.android.authentication.a.a;
        r2 = r6.f7362h;
        r0.<init>(r2);
        r0 = r0.m8473d();
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x003f;
    L_0x0030:
        r2 = r6.f7361g;
        r2 = r2.edit();
        r3 = "PREF.USER.SESSION.TOKEN";
        r2 = r2.putString(r3, r0);
        r2.apply();
    L_0x003f:
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 == 0) goto L_0x004e;
    L_0x0045:
        r0 = f7355a;
        r2 = "setSessionToken(), The session token is null";
        r0.error(r2);
        r0 = r1;
        goto L_0x000e;
    L_0x004e:
        r2 = r6.m8662q();
        if (r2 != 0) goto L_0x005d;
    L_0x0054:
        r0 = f7355a;
        r2 = "setSessionToken(), Bluetooth Configuration Character is null";
        r0.error(r2);
        r0 = r1;
        goto L_0x000e;
    L_0x005d:
        r3 = 20;
        r3 = new byte[r3];
        r3[r1] = r5;
        r4 = 1;
        r3[r4] = r1;
        r1 = (byte) r7;
        r3[r5] = r1;
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 Catch:{ UnsupportedEncodingException -> 0x008e }
        switch(r7) {
            case 0: goto L_0x0086;
            case 1: goto L_0x00ab;
            case 2: goto L_0x00b4;
            default: goto L_0x0072;
        };
    L_0x0072:
        r0 = 19;
        r1 = r6.f7358d;
        r1 = r1.crc8(r3);
        r3[r0] = r1;
        r2.setValue(r3);
        r0 = r6.f7357c;
        r0 = r0.writeCharacteristic(r2);
        goto L_0x000e;
    L_0x0086:
        r1 = 0;
        r4 = 3;
        r5 = 16;
        java.lang.System.arraycopy(r0, r1, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x008e }
        goto L_0x0072;
    L_0x008e:
        r0 = move-exception;
        r1 = f7355a;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Session token parser is error, e = ";
        r4 = r4.append(r5);
        r4 = r4.append(r0);
        r4 = r4.toString();
        r1.error(r4);
        r0.printStackTrace();
        goto L_0x0072;
    L_0x00ab:
        r1 = 16;
        r4 = 3;
        r5 = 16;
        java.lang.System.arraycopy(r0, r1, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x008e }
        goto L_0x0072;
    L_0x00b4:
        r1 = 32;
        r4 = 3;
        r5 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x008e }
        r5 = r5 + -32;
        java.lang.System.arraycopy(r0, r1, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x008e }
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.ble.biz.S605CentralInvocation.g(int):boolean");
    }

    /* renamed from: k */
    public boolean m8721k() {
        byte b = (byte) 0;
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("setSystemTime(), Bluetooth Configuration Character is null");
            return false;
        }
        Object obj = new byte[20];
        obj[0] = (byte) 2;
        obj[1] = Ascii.VT;
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        try {
            System.arraycopy(valueOf.getBytes("UTF-8"), 0, obj, 2, 10);
        } catch (UnsupportedEncodingException e) {
            f7355a.error("Set System Time is error, e = " + e);
            e.printStackTrace();
        }
        String id = TimeZone.getDefault().getID();
        String[] stringArray = this.f7362h.getResources().getStringArray(C1373R.array.S605_time_zone);
        for (int i = 0; i < stringArray.length; i++) {
            if (id.equals(stringArray[i])) {
                b = (byte) (i + 1);
                break;
            }
        }
        obj[12] = b;
        obj[19] = this.f7358d.crc8(obj);
        q.setValue(obj);
        boolean writeCharacteristic = this.f7357c.writeCharacteristic(q);
        f7355a.info("setSystemTime(), SystemTime = " + valueOf + ", zoneId = " + id + ", " + writeCharacteristic);
        return writeCharacteristic;
    }

    /* renamed from: a */
    public void mo3153a(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            m8633A();
            f7355a.error("setOfflineMapDownload(), Latlng is null");
            return;
        }
        this.f7375u = new ArrayList();
        this.f7375u.add(latLng);
        this.f7375u.add(latLng2);
        m8657i(0);
    }

    /* renamed from: a */
    public void mo3156a(ArrayList<ArrayList<Integer>> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            f7355a.error("setCyclingPageConfig(), ArrayList Configs is null");
            return;
        }
        this.f7374t = arrayList;
        m8659j(0);
    }

    /* renamed from: a */
    public boolean mo3162a(boolean z) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("writeMessageConfig(), S605 Configuration characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 9;
        if (z) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7358d.crc8(bArr);
        q.setWriteType(1);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: b */
    public boolean mo3166b(boolean z) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("writeVibrationWakeConfig(), S605 Configuration characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 5;
        if (z) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7358d.crc8(bArr);
        q.setWriteType(1);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: a */
    public boolean mo3157a(byte b) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("writeWheel(), S605 Configuration characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 6;
        bArr[2] = b;
        bArr[19] = this.f7358d.crc8(bArr);
        q.setWriteType(1);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: a */
    public boolean mo3158a(int i) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("writeLocaleConfig(), S605 Configuration characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 7;
        if (i == 0) {
            bArr[2] = (byte) 0;
        } else {
            bArr[2] = (byte) 1;
        }
        bArr[19] = this.f7358d.crc8(bArr);
        q.setWriteType(1);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: b */
    public boolean mo3165b(int i) {
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("writeMileageUnitConfig(), S605 Configuration characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 8;
        if (i == 1) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7358d.crc8(bArr);
        q.setWriteType(1);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: h */
    public boolean m8719h(int r9) {
        /*
        r8 = this;
        r7 = 2;
        r6 = 32;
        r0 = 0;
        r5 = 16;
        r1 = r8.m8662q();
        if (r1 != 0) goto L_0x0014;
    L_0x000c:
        r1 = f7355a;
        r2 = "setDeviceName(), S605 Configuration characteristic is null";
        r1.error(r2);
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = android.os.Build.MODEL;
        r3 = android.text.TextUtils.isEmpty(r2);
        if (r3 == 0) goto L_0x0024;
    L_0x001c:
        r1 = f7355a;
        r2 = "setDeviceName(), Device Name is null";
        r1.error(r2);
        goto L_0x0013;
    L_0x0024:
        r3 = 20;
        r3 = new byte[r3];
        r3[r0] = r7;
        r0 = 1;
        r4 = 10;
        r3[r0] = r4;
        r0 = (byte) r9;
        r3[r7] = r0;
        r0 = "UTF-8";
        r0 = r2.getBytes(r0);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        switch(r9) {
            case 0: goto L_0x004f;
            case 1: goto L_0x0066;
            case 2: goto L_0x0087;
            default: goto L_0x003b;
        };
    L_0x003b:
        r0 = 19;
        r2 = r8.f7358d;
        r2 = r2.crc8(r3);
        r3[r0] = r2;
        r1.setValue(r3);
        r0 = r8.f7357c;
        r0 = r0.writeCharacteristic(r1);
        goto L_0x0013;
    L_0x004f:
        r2 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        if (r2 < r5) goto L_0x005f;
    L_0x0052:
        r2 = 0;
        r4 = 3;
        r5 = 16;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x005a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x003b;
    L_0x005f:
        r2 = 0;
        r4 = 3;
        r5 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x0066:
        r2 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        if (r2 > r5) goto L_0x0071;
    L_0x0069:
        r0 = f7355a;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        r2 = "setDeviceName(), Device Name size <= 16";
        r0.info(r2);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x0071:
        r2 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        if (r2 < r6) goto L_0x007d;
    L_0x0074:
        r2 = 16;
        r4 = 3;
        r5 = 16;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x007d:
        r2 = 16;
        r4 = 3;
        r5 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        r5 = r5 + -16;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x0087:
        r2 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        if (r2 > r6) goto L_0x0092;
    L_0x008a:
        r0 = f7355a;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        r2 = "setDeviceName(), Device Name size <= 32";
        r0.info(r2);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x0092:
        r2 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        if (r2 < r6) goto L_0x009e;
    L_0x0095:
        r2 = 32;
        r4 = 3;
        r5 = 16;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
    L_0x009e:
        r2 = 32;
        r4 = 3;
        r5 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x005a }
        r5 = r5 + -32;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x005a }
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.ble.biz.S605CentralInvocation.h(int):boolean");
    }

    /* renamed from: a */
    public boolean mo3159a(int i, int i2, int i3) {
        return false;
    }

    /* renamed from: c */
    public boolean mo3168c(int i) {
        return false;
    }

    /* renamed from: d */
    public boolean mo3170d(int i) {
        return false;
    }

    /* renamed from: e */
    public boolean mo3172e(int i) {
        return false;
    }

    /* renamed from: l */
    public boolean m8722l() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("setAuthKey(), character is null");
            return false;
        }
        try {
            Object obj = new byte[20];
            Object bytes = "0Yxa8Wxp!X".getBytes("UTF-8");
            obj[0] = (byte) 2;
            obj[1] = (byte) 0;
            System.arraycopy(bytes, 0, obj, 2, bytes.length);
            obj[19] = this.f7358d.crc8(obj);
            p.setValue(obj);
            boolean writeCharacteristic = this.f7357c.writeCharacteristic(p);
            f7355a.info("write S605 ble auth key to ble is " + writeCharacteristic);
            return writeCharacteristic;
        } catch (UnsupportedEncodingException e) {
            f7355a.error("Write S605 ble auth key to ble error, " + e);
            return false;
        }
    }

    /* renamed from: m */
    public boolean m8723m() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("checkFirmwareUpdate(), character is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 1;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        return this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: n */
    public boolean m8724n() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("requestOtaPacketStart(), character is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 9;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        return this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: a */
    public void mo3135a() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("writeDeviceInfoRequest(), character is null");
            return;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 3;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: d */
    public void mo3169d() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("requestTrainTarget(), character is null");
            return;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 4;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: e */
    public void mo3171e() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("requestWifiInfo(), character is null");
            return;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 5;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: g */
    public void mo3174g() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("requestWifiInfo(), character is null");
            return;
        }
        if (this.f7374t != null) {
            this.f7374t.clear();
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 6;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: f */
    public void mo3173f() {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.error("requestUpdateStart(), character is null");
            return;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 2;
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: b */
    public void mo3163b() {
    }

    /* renamed from: a */
    public void mo3137a(int i, String str, String str2) {
    }

    /* renamed from: a */
    public void mo3136a(byte b, byte b2, int i) {
    }

    /* renamed from: a */
    public void m8675a(int i, String str, int i2) {
        BluetoothGattCharacteristic p = m8661p();
        if (p == null) {
            f7355a.info("writeOTAStartRequest(), character is null");
            return;
        }
        int i3;
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 7;
        bArr[2] = (byte) i;
        if (i2 % 190 == 0) {
            i3 = i2 / 190;
        } else {
            i3 = (i2 / 190) + 1;
        }
        byte[] a = aa.m12774a((short) i3);
        bArr[3] = a[1];
        bArr[4] = a[0];
        f7355a.info("writeOTAStartRequest(), totalPacketCount = " + i3);
        bArr[19] = this.f7358d.crc8(bArr);
        p.setValue(bArr);
        this.f7357c.writeCharacteristic(p);
    }

    /* renamed from: a */
    public int mo3134a(int i, String str, C1668c c1668c, ArrayList<Position> arrayList) {
        if (c1668c == null) {
            return 2;
        }
        this.f7359e.m8744i().m8750a(false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator it = c1668c.m9050c().iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Iterator it2 = ((C1667b) it.next()).m9047c().iterator();
            while (it2.hasNext()) {
                int size = ((C1669d) it2.next()).m9051a().size();
                i2 += (size * 10) + 32;
                i3 += size;
            }
        }
        if (i3 > 1000) {
            return 1;
        }
        byte[] a = m8637a(c1668c);
        if (a == null) {
            f7355a.error("get route segments error");
            return 2;
        }
        try {
            byteArrayOutputStream.write(m8638a(c1668c, i2, this.f7358d.getCheckSum(a), i3, (ArrayList) arrayList));
            byteArrayOutputStream.write(a);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f7365k = byteArrayOutputStream.toByteArray();
            f7355a.info("Routes = " + aa.m12770a(this.f7365k));
            this.f7359e.m8744i().m8747a((double) this.f7365k.length);
            m8675a(i, str, this.f7365k.length);
            return 0;
        } catch (IOException e2) {
            e2.printStackTrace();
            f7355a.error("ByteOutputStream write error: " + e2);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
            return 2;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            throw th;
        }
    }

    /* renamed from: i */
    public void mo3176i() {
    }

    /* renamed from: b */
    private void m8642b(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            f7355a.error("parserRequestCharacteristic() characteristic is null");
            return;
        }
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null || value.length < 20) {
            f7355a.error("parserRequestCharacteristic() value is error");
            return;
        }
        byte b = value[1];
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        if (a$b.f7194b.toString().equals(uuid)) {
            byte b2;
            switch (b) {
                case (byte) 0:
                    b2 = value[2];
                    if (b2 == (byte) 0) {
                        m8717g(1);
                    }
                    if (b2 == (byte) 1) {
                        m8717g(2);
                        break;
                    }
                    break;
                case (byte) 1:
                    b2 = value[2];
                    if (b2 == (byte) 0) {
                        m8715f(1);
                    }
                    if (b2 == (byte) 1) {
                        m8719h(0);
                    }
                    if (b2 == (byte) 2) {
                        m8715f(1);
                        break;
                    }
                    break;
                case (byte) 3:
                    b2 = value[2];
                    if (b2 == (byte) 0) {
                        m8657i(1);
                    }
                    if (b2 == (byte) 1) {
                        m8671z();
                        break;
                    }
                    break;
                case (byte) 4:
                    byte b3 = value[2];
                    b2 = value[3];
                    if (b2 != b3 - 1) {
                        m8659j(b2 + 1);
                        break;
                    }
                    f7355a.info("骑行数据页配置信息完成");
                    if (!(this.f7364j == null || this.f7371q == null)) {
                        this.f7364j.post(new C16041(this));
                        break;
                    }
                case (byte) 10:
                    b2 = value[2];
                    if (b2 < (byte) 2) {
                        m8719h(b2 + 1);
                        break;
                    } else {
                        mo3135a();
                        break;
                    }
                case (byte) 11:
                    m8723m();
                    break;
            }
        }
        if (a$b.f7195c.toString().equals(uuid)) {
            switch (b) {
                case (byte) 0:
                    m8717g(0);
                    break;
                case (byte) 3:
                    m8721k();
                    break;
                case (byte) 9:
                    m8669x();
                    break;
            }
        }
        if (a$b.f7197e.toString().equals(uuid)) {
            m8670y();
        }
    }

    /* renamed from: c */
    private void m8645c(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            f7355a.error("parserResponseCharacteristic(), characteristic is null");
            return;
        }
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 20) {
            f7355a.error("parserResponseCharacteristic(), values length is not 20");
            return;
        }
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        if (a$b.f7196d.toString().equals(uuid)) {
            switch (value[1]) {
                case (byte) 0:
                    m8636a(value);
                    break;
                case (byte) 1:
                    m8654g(value);
                    break;
                case (byte) 2:
                    m8643b(value);
                    break;
                case (byte) 3:
                    m8646c(value);
                    break;
                case (byte) 4:
                    m8648d(value);
                    break;
                case (byte) 5:
                    m8656i(value);
                    break;
                case (byte) 7:
                    m8650e(value);
                    break;
            }
        }
        if (a$b.f7199g.toString().equals(uuid)) {
            switch (value[1]) {
                case (byte) 0:
                    m8668w();
                    break;
            }
        }
        if (a$b.f7198f.toString().equals(uuid)) {
            switch (value[1]) {
                case (byte) 0:
                    Intent intent;
                    if (value[2] == (byte) 0) {
                        m8715f(0);
                        intent = new Intent("com.beastbikes.android.ble.connected.action");
                        intent.addCategory("android.intent.category.DEFAULT");
                        this.f7362h.sendBroadcast(intent);
                        return;
                    }
                    intent = new Intent("com.beastbikes.android.connect.no.token");
                    intent.addCategory("android.intent.category.DEFAULT");
                    this.f7362h.sendBroadcast(intent);
                    return;
                case (byte) 2:
                    m8652f(value);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m8636a(byte[] bArr) {
        byte b = bArr[2];
        Object obj = new byte[16];
        System.arraycopy(bArr, 3, obj, 0, obj.length);
        this.f7377w.put(Integer.valueOf(b), obj);
        if (this.f7377w.size() == 3) {
            Object obj2 = new byte[48];
            for (int i = 0; i < this.f7377w.size(); i++) {
                byte[] bArr2 = (byte[]) this.f7377w.get(Integer.valueOf(i));
                System.arraycopy(bArr2, 0, obj2, i * 16, bArr2.length);
            }
            String[] split = new String(obj2).trim().split(",");
            final S605FirmwareInfo s605FirmwareInfo = new S605FirmwareInfo();
            if (split.length == 3) {
                s605FirmwareInfo.setRomCurrVersion(split[0]);
                s605FirmwareInfo.setMid(split[1]);
                s605FirmwareInfo.setMacAddress(split[2]);
            }
            this.f7377w.clear();
            f7355a.info("S605 Firmware Info: " + s605FirmwareInfo.toString());
            if (this.f7369o != null && this.f7364j != null) {
                this.f7364j.post(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ S605CentralInvocation f7344b;

                    public void run() {
                        this.f7344b.f7369o.m8804a(s605FirmwareInfo);
                    }
                });
            }
        }
    }

    /* renamed from: b */
    private void m8643b(byte[] bArr) {
        final DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic = new DeviceInfoCommandCharacteristic();
        deviceInfoCommandCharacteristic.setLocale(bArr[2]);
        deviceInfoCommandCharacteristic.setMileageUnit(bArr[3]);
        deviceInfoCommandCharacteristic.setHardwareType(bArr[4]);
        deviceInfoCommandCharacteristic.setBrandType(bArr[5]);
        deviceInfoCommandCharacteristic.setWheelType(bArr[6]);
        deviceInfoCommandCharacteristic.setNotification(bArr[7]);
        deviceInfoCommandCharacteristic.setShakeUp(bArr[8]);
        f7355a.info("parserDeviceInfo(), DeviceInfo = " + deviceInfoCommandCharacteristic.toString());
        if (this.f7359e == null) {
            f7355a.error("parserDeviceInfo(), CentralSession is null");
            return;
        }
        BleDevice bleDevice = null;
        BluetoothDevice b = this.f7359e.m8734b();
        if (b != null) {
            bleDevice = this.f7363i.m8881a(this.f7359e.m8728a(), b.getName(), deviceInfoCommandCharacteristic.getHardwareType(), deviceInfoCommandCharacteristic.getBrandType(), b.getAddress(), true);
        }
        if (this.f7366l != null && this.f7364j != null) {
            this.f7364j.post(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ S605CentralInvocation f7347c;

                public void run() {
                    this.f7347c.f7366l.m8801a(bleDevice, deviceInfoCommandCharacteristic);
                }
            });
        }
    }

    /* renamed from: c */
    private void m8646c(byte[] bArr) {
        Object obj = new byte[16];
        System.arraycopy(bArr, 2, obj, 0, obj.length);
        final String trim = new String(obj).trim();
        f7355a.info("WiFi UUID:" + trim);
        if (this.f7368n != null && this.f7364j != null) {
            this.f7364j.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ S605CentralInvocation f7349b;

                public void run() {
                    this.f7349b.f7368n.m8806a(trim);
                }
            });
        }
    }

    /* renamed from: d */
    private void m8648d(byte[] bArr) {
        final TrainTargetDto trainTargetDto = new TrainTargetDto();
        trainTargetDto.setSex(bArr[2]);
        r1 = new byte[4];
        System.arraycopy(bArr, 3, r1, 0, 4);
        trainTargetDto.setBirthday(C1616a.m8779a(r1, this.f7358d));
        trainTargetDto.setHeight((double) aa.m12775b(new byte[]{bArr[7], bArr[8]}));
        trainTargetDto.setWeight((double) aa.m12775b(new byte[]{bArr[9], bArr[10]}));
        trainTargetDto.setFtp(aa.m12775b(new byte[]{bArr[11], bArr[12]}));
        if (this.f7367m != null && this.f7364j != null) {
            this.f7364j.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ S605CentralInvocation f7351b;

                public void run() {
                    this.f7351b.f7367m.m8805a(trainTargetDto);
                }
            });
        }
    }

    /* renamed from: e */
    private void m8650e(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        short b3 = aa.m12775b(new byte[]{bArr[4], bArr[5]});
        Object oTARequestCommandCharacteristic = new OTARequestCommandCharacteristic();
        oTARequestCommandCharacteristic.setProcessType(b);
        oTARequestCommandCharacteristic.setFlags(b2);
        oTARequestCommandCharacteristic.setRequestPacketIndex(b3);
        switch (b2) {
            case (byte) 1:
                f7355a.trace("同步数据，请求下一个数据" + b3);
                if (this.f7370p != null) {
                    this.f7370p.mo3226b(b3);
                }
                m8693a(oTARequestCommandCharacteristic);
                return;
            case (byte) 2:
                f7355a.info("同步数据完成");
                if (this.f7370p != null) {
                    this.f7370p.mo3228c(b);
                    return;
                }
                return;
            case (byte) 3:
                f7355a.error("同步数据中控CRC16效验失败");
                m8693a(oTARequestCommandCharacteristic);
                return;
            default:
                return;
        }
    }

    /* renamed from: w */
    private void m8668w() {
        if (this.f7364j != null) {
            Intent intent = new Intent("s605.dialog.type.wifi.password");
            intent.putExtra("s605.central.invocation", f7356b);
            this.f7362h.sendBroadcast(intent);
        }
    }

    /* renamed from: f */
    private void m8652f(byte[] bArr) {
        byte b = bArr[2];
        Object obj = new byte[16];
        System.arraycopy(bArr, 3, obj, 0, obj.length);
        String trim = new String(obj).trim();
        if (this.f7364j != null) {
            Intent intent = new Intent("s605.dialog.type.wifi.status");
            intent.putExtra("s605.dialog.wifi.status", b);
            intent.putExtra("s605.dialog.wifi.uuid", trim);
            this.f7362h.sendBroadcast(intent);
        }
    }

    /* renamed from: g */
    private void m8654g(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        if (this.f7364j != null) {
            Intent intent = new Intent("s605.dialog.update.success");
            intent.putExtra("s605.dialog.wifi.status", b2);
            intent.putExtra("dialog.update.type", b);
            this.f7362h.sendBroadcast(intent);
        }
    }

    /* renamed from: x */
    private void m8669x() {
        if (this.f7376v == null) {
            f7355a.error("writeOTAPacketInfo(), OtaRequestPacketCharacteristic is null");
            return;
        }
        int processType = this.f7376v.getProcessType();
        int requestPacketIndex = this.f7376v.getRequestPacketIndex();
        if (processType == 16) {
            m8641b(this.f7365k.length, processType, requestPacketIndex);
        }
    }

    /* renamed from: b */
    private synchronized void m8641b(int i, int i2, int i3) {
        synchronized (this) {
            if (this.f7365k == null || this.f7365k.length <= 0) {
                f7355a.error("writeRouteToCentral(), route is null");
            } else {
                BluetoothGattCharacteristic r = m8663r();
                if (r == null) {
                    f7355a.info("writeRouteToCentral(), character is null");
                } else {
                    int i4;
                    f7355a.trace("writeRouteToCentral(), totalCount = " + i + ", packetType = " + i2 + ", packetIndex = " + i3);
                    r.setWriteType(1);
                    byte[] bArr = new byte[200];
                    bArr[0] = (byte) 2;
                    bArr[1] = (byte) i2;
                    if (i % 190 == 0) {
                        i4 = i / 190;
                    } else {
                        i4 = (i / 190) + 1;
                    }
                    byte[] a = aa.m12774a((short) i4);
                    bArr[2] = a[1];
                    bArr[3] = a[0];
                    a = aa.m12774a((short) i3);
                    bArr[4] = a[1];
                    bArr[5] = a[0];
                    a = aa.m12774a((short) 190);
                    if (i4 - 1 == i3) {
                        int i5 = i - ((i4 - 1) * 190);
                        int i6 = 190 - i5;
                        f7355a.trace("写入路线数据的总个数 ＝ " + i4 + "; 实际总长 ＝ " + i + "; 差 ＝" + i6 + ";长 ＝" + (190 - i6));
                        a = aa.m12774a((short) i5);
                    }
                    bArr[6] = a[1];
                    bArr[7] = a[0];
                    for (int i7 = 0; i7 < 190; i7++) {
                        if ((i3 * 190) + i7 >= this.f7365k.length) {
                            bArr[i7 + 8] = (byte) 0;
                        } else {
                            bArr[i7 + 8] = this.f7365k[(i3 * 190) + i7];
                        }
                    }
                    byte[] a2 = aa.m12774a((short) this.f7358d.crc16(bArr));
                    bArr[Opcodes.IFNULL] = a2[1];
                    bArr[Opcodes.IFNONNULL] = a2[0];
                    this.f7378x = ByteBuffer.wrap(bArr);
                    m8670y();
                }
            }
        }
    }

    /* renamed from: h */
    private synchronized void m8655h(byte[] bArr) {
        BluetoothGattCharacteristic r = m8663r();
        if (r == null) {
            f7355a.info("writeRouteToCentral(), character is null");
        } else {
            r.setWriteType(1);
            r.setValue(bArr);
            this.f7357c.writeCharacteristic(r);
        }
    }

    /* renamed from: y */
    private synchronized void m8670y() {
        if (this.f7378x == null) {
            f7355a.error("Ota ByteBuffer is null");
        } else if (this.f7378x.hasRemaining()) {
            byte[] bArr = new byte[20];
            this.f7378x.get(bArr, 0, 20);
            m8655h(bArr);
        } else {
            f7355a.error("Ota ByteBuffer hasRemaining is false");
        }
    }

    /* renamed from: a */
    private byte[] m8638a(C1668c c1668c, int i, int i2, int i3, ArrayList<Position> arrayList) {
        int i4;
        Object obj = new byte[512];
        byte[] a = aa.m12773a(i2);
        for (i4 = 0; i4 < a.length; i4++) {
            obj[i4] = a[i4];
        }
        a = aa.m12773a(i);
        for (i4 = 0; i4 < a.length; i4++) {
            obj[i4 + 4] = a[i4];
        }
        if (C1849a.m9641a()) {
            obj[8] = (byte) 0;
        } else {
            obj[8] = (byte) 1;
        }
        double d = 0.0d;
        double d2 = 0.0d;
        Iterator it = c1668c.m9050c().iterator();
        int i5 = 0;
        while (it.hasNext()) {
            C1667b c1667b = (C1667b) it.next();
            d += c1667b.m9045a();
            d2 += c1667b.m9046b();
            i5 = c1667b.m9047c().size() + i5;
        }
        byte[] a2 = aa.m12773a((int) d);
        for (i4 = 0; i4 < a2.length; i4++) {
            obj[i4 + 9] = a2[i4];
        }
        byte[] a3 = aa.m12773a((int) d2);
        for (i4 = 0; i4 < a3.length; i4++) {
            obj[i4 + 13] = a3[i4];
        }
        a3 = aa.m12773a(i3);
        for (i4 = 0; i4 < a3.length; i4++) {
            obj[i4 + 17] = a3[i4];
        }
        byte[] a4 = aa.m12774a((short) i5);
        obj[21] = a4[1];
        obj[22] = a4[0];
        if (arrayList == null || arrayList.size() == 0) {
            f7355a.error("BLE-Navi", "Navigation way point is empty.");
            obj[23] = (byte) 0;
        } else {
            obj[23] = (byte) arrayList.size();
            for (i5 = 0; i5 < arrayList.size(); i5++) {
                Position position = (Position) arrayList.get(i5);
                Object a5 = C1616a.m8780a(position.getLatitude());
                Object a6 = C1616a.m8780a(position.getLongitude());
                System.arraycopy(a5, 0, obj, (i5 * 10) + 24, 5);
                System.arraycopy(a6, 0, obj, ((i5 * 10) + 24) + 5, 5);
            }
        }
        return obj;
    }

    /* renamed from: a */
    private byte[] m8637a(C1668c c1668c) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Iterator it = c1668c.m9050c().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((C1667b) it.next()).m9047c().iterator();
                while (it2.hasNext()) {
                    byteArrayOutputStream.write(m8639a((C1669d) it2.next()));
                }
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (IOException e) {
                e.printStackTrace();
                return toByteArray;
            }
        } catch (Exception e2) {
            f7355a.error("getRouteSegments e: " + e2);
            try {
                byteArrayOutputStream.close();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private byte[] m8639a(C1669d c1669d) {
        List a = c1669d.m9051a();
        int size = a.size();
        byte[] bArr = new byte[((size * 10) + 32)];
        byte[] a2 = aa.m12774a((short) size);
        bArr[0] = a2[1];
        bArr[1] = a2[0];
        int i = 32;
        for (int i2 = 0; i2 < size; i2++) {
            double[] coordinates = ((Position) a.get(i2)).getCoordinates();
            byte[] a3 = C1616a.m8780a(coordinates[1]);
            int length = a3.length;
            int i3 = i;
            i = 0;
            while (i < length) {
                bArr[i3] = a3[i];
                i++;
                i3++;
            }
            i = i3;
            for (byte b : C1616a.m8780a(coordinates[0])) {
                bArr[i] = b;
                i++;
            }
        }
        return bArr;
    }

    /* renamed from: i */
    private boolean m8657i(int i) {
        if (this.f7375u == null || this.f7375u.size() != 2) {
            m8633A();
            f7355a.error("setOfflineMapDownload(), Offline Map List is null");
            return false;
        }
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            m8633A();
            f7355a.error("setOfflineMapDownload(), Bluetooth Configuration Character is null");
            return false;
        }
        LatLng latLng = (LatLng) this.f7375u.get(i);
        Object obj = new byte[20];
        obj[0] = 2;
        obj[1] = 3;
        obj[2] = (byte) i;
        Object a = C1616a.m8780a(latLng.getLatitude());
        System.arraycopy(a, 0, obj, 3, a.length);
        Object a2 = C1616a.m8780a(latLng.getLongitude());
        System.arraycopy(a2, 0, obj, 8, a2.length);
        obj[19] = this.f7358d.crc8(obj);
        q.setValue(obj);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: j */
    private boolean m8659j(int i) {
        if (this.f7374t == null || i >= this.f7374t.size()) {
            f7355a.error("setCyclingPageConfig(), Cycling Config Data is null");
            return false;
        }
        BluetoothGattCharacteristic q = m8662q();
        if (q == null) {
            f7355a.error("setCyclingPageConfig(), Bluetooth Configuration Character is null");
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 4;
        bArr[2] = (byte) this.f7374t.size();
        bArr[3] = (byte) i;
        ArrayList arrayList = (ArrayList) this.f7374t.get(i);
        if (arrayList != null && arrayList.size() > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                bArr[i2 + 4] = ((Integer) arrayList.get(i2)).byteValue();
            }
        }
        bArr[19] = this.f7358d.crc8(bArr);
        q.setValue(bArr);
        return this.f7357c.writeCharacteristic(q);
    }

    /* renamed from: i */
    private void m8656i(byte[] bArr) {
        byte b = bArr[2];
        if (b <= (byte) 0) {
            f7355a.error("parserCyclingPageConfig(), Page total count size 0");
            return;
        }
        if (this.f7374t == null) {
            this.f7374t = new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int i2 = bArr[i + 4] & 255;
            if (i2 != 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        this.f7374t.add(arrayList);
        if (this.f7374t.size() == b) {
            f7355a.info("骑行数据页配置信息解析完成");
            if (this.f7364j != null && this.f7371q != null) {
                this.f7364j.post(new C16096(this));
            }
        }
    }

    /* renamed from: z */
    private void m8671z() {
        if (this.f7364j != null && this.f7372r != null) {
            this.f7364j.post(new C16107(this));
        }
    }

    /* renamed from: A */
    private void m8633A() {
        if (this.f7364j != null && this.f7372r != null) {
            this.f7364j.post(new C16118(this));
        }
    }

    /* renamed from: a */
    private void m8635a(long j) {
        try {
            f7355a.warn("Thread [" + Thread.currentThread().getName() + "] sleep " + j);
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
