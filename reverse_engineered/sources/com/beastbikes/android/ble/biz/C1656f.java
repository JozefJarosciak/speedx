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
import android.os.Build;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alibaba.fastjson.asm.Opcodes;
import com.android.internal.telephony.ITelephony;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.a$a;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p096a.C1615b;
import com.beastbikes.android.ble.biz.p097b.C1618b;
import com.beastbikes.android.ble.biz.p097b.C1621e;
import com.beastbikes.android.ble.biz.p097b.C1622f;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.biz.p097b.C1624h;
import com.beastbikes.android.ble.biz.p097b.C1625i;
import com.beastbikes.android.ble.biz.p097b.C1626j;
import com.beastbikes.android.ble.biz.p097b.C1627k;
import com.beastbikes.android.ble.biz.p097b.C1629m;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.dto.C1667b;
import com.beastbikes.android.ble.dto.C1668c;
import com.beastbikes.android.ble.dto.C1669d;
import com.beastbikes.android.ble.protocol.v1.AGpsInfoCharacteristic;
import com.beastbikes.android.ble.protocol.v1.BatterySensorCharacteristic;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoExtensionCharacteristic;
import com.beastbikes.android.ble.protocol.v1.OTAFirmwareInfoCharacteristic;
import com.beastbikes.android.ble.protocol.v1.OTARequestCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.PreviewDataCharacteristic;
import com.beastbikes.android.ble.protocol.v1.ProtocolParserImpl;
import com.beastbikes.android.ble.protocol.v1.SynchronizationDataCharacteristic;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.utils.aa;
import com.google.common.base.Ascii;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.commons.models.Position;
import io.rong.imlib.statistics.UserData;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(19)
/* compiled from: CentralInvocation */
/* renamed from: com.beastbikes.android.ble.biz.f */
public class C1656f extends C1613b implements C1371a {
    /* renamed from: c */
    private static final Logger f7485c = LoggerFactory.getLogger(C1656f.class);
    /* renamed from: s */
    private static C1656f f7486s;
    /* renamed from: a */
    ProtocolParserImpl f7487a;
    /* renamed from: b */
    BluetoothGatt f7488b;
    /* renamed from: d */
    private C1624h f7489d;
    /* renamed from: e */
    private C1625i f7490e;
    /* renamed from: f */
    private C1627k f7491f;
    /* renamed from: g */
    private C1618b f7492g;
    /* renamed from: h */
    private C1622f f7493h;
    /* renamed from: i */
    private C1621e f7494i;
    /* renamed from: j */
    private C1623g f7495j;
    /* renamed from: k */
    private C1626j f7496k;
    /* renamed from: l */
    private C1614a f7497l;
    /* renamed from: m */
    private Context f7498m;
    /* renamed from: n */
    private SharedPreferences f7499n;
    /* renamed from: o */
    private Handler f7500o;
    /* renamed from: p */
    private C1651c f7501p;
    /* renamed from: q */
    private List<Byte> f7502q = new ArrayList();
    /* renamed from: r */
    private byte[] f7503r;
    /* renamed from: t */
    private String f7504t;
    /* renamed from: u */
    private ByteBuffer f7505u;
    /* renamed from: v */
    private OTARequestCommandCharacteristic f7506v;

    /* renamed from: a */
    public void mo3146a(C1624h c1624h) {
        this.f7489d = c1624h;
    }

    /* renamed from: a */
    public void mo3147a(C1625i c1625i) {
        this.f7490e = c1625i;
    }

    /* renamed from: a */
    public void mo3148a(C1626j c1626j) {
        this.f7496k = c1626j;
    }

    /* renamed from: a */
    public void mo3149a(C1627k c1627k) {
        this.f7491f = c1627k;
    }

    /* renamed from: a */
    public void mo3140a(C1618b c1618b) {
        this.f7492g = c1618b;
    }

    /* renamed from: a */
    public void mo3144a(C1622f c1622f) {
        this.f7493h = c1622f;
    }

    /* renamed from: a */
    public void mo3143a(C1621e c1621e) {
        this.f7494i = c1621e;
    }

    /* renamed from: c */
    public C1623g mo3167c() {
        return this.f7495j;
    }

    /* renamed from: a */
    public void mo3145a(C1623g c1623g) {
        this.f7495j = c1623g;
    }

    /* renamed from: a */
    public void mo3151a(C1629m c1629m) {
    }

    /* renamed from: e */
    public static C1656f m8922e() {
        if (f7486s == null) {
            synchronized (C1656f.class) {
                f7486s = new C1656f();
            }
        }
        return f7486s;
    }

    private C1656f() {
    }

    /* renamed from: a */
    public void mo3139a(Context context, SharedPreferences sharedPreferences) {
        if (this.f7498m == null) {
            this.f7498m = context;
            this.f7487a = new ProtocolParserImpl();
            this.f7500o = new Handler(BeastBikes.j().getMainLooper());
            this.f7501p = new C1651c(context);
            this.f7499n = sharedPreferences;
        }
    }

    /* renamed from: i */
    public void mo3176i() {
    }

    /* renamed from: u */
    private BluetoothGattCharacteristic m8926u() {
        if (this.f7488b == null) {
            f7485c.error("getCommandRequestCharacter(), BluetoothGatt is null");
            return null;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service != null) {
            return service.getCharacteristic(a$a.f7188f);
        }
        f7485c.error("getCommandRequestCharacter(), BluetoothGattService is null");
        return null;
    }

    /* renamed from: v */
    private BluetoothGattCharacteristic m8927v() {
        if (this.f7488b == null) {
            return null;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return null;
        }
        return service.getCharacteristic(a$a.f7187e);
    }

    /* renamed from: w */
    private BluetoothGattCharacteristic m8928w() {
        if (this.f7488b == null) {
            return null;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return null;
        }
        return service.getCharacteristic(a$a.f7192j);
    }

    /* renamed from: x */
    private BluetoothGattCharacteristic m8929x() {
        if (this.f7488b == null) {
            return null;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service != null) {
            return service.getCharacteristic(a$a.f7191i);
        }
        return null;
    }

    /* renamed from: f */
    protected boolean m8967f() {
        if (this.f7488b == null) {
            return false;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$a.f7185c);
        if (characteristic == null) {
            return false;
        }
        characteristic.setWriteType(1);
        this.f7488b.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7488b.writeDescriptor(descriptor);
        f7485c.info("activitySampleCharacter uuid = " + characteristic.getUuid().toString() + "read = ; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: g */
    protected boolean m8970g() {
        if (this.f7488b == null) {
            return false;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$a.f7190h);
        if (characteristic == null) {
            return false;
        }
        characteristic.setWriteType(1);
        this.f7488b.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7488b.writeDescriptor(descriptor);
        f7485c.info("activitySyncCharacter uuid = " + characteristic.getUuid().toString() + "read = ; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: j */
    protected boolean m8973j() {
        if (this.f7488b == null) {
            return false;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$a.f7189g);
        if (characteristic == null) {
            return false;
        }
        characteristic.setWriteType(1);
        this.f7488b.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7488b.writeDescriptor(descriptor);
        f7485c.info("command notification uuid = " + characteristic.getUuid().toString() + "read = ; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: k */
    protected boolean m8974k() {
        if (this.f7488b == null) {
            return false;
        }
        BluetoothGattService service = this.f7488b.getService(a$a.f7183a);
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(a$a.f7186d);
        if (characteristic == null) {
            return false;
        }
        characteristic.setWriteType(1);
        this.f7488b.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(a$a.f7184b);
        if (descriptor == null) {
            return false;
        }
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        boolean writeDescriptor = this.f7488b.writeDescriptor(descriptor);
        f7485c.info("sensorCharacter uuid = " + characteristic.getUuid().toString() + "read = ; write = " + writeDescriptor);
        return writeDescriptor;
    }

    /* renamed from: l */
    protected boolean m8975l() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            f7485c.error("writeAuthKey(), characteristic is null");
            return false;
        }
        byte[] bArr = new byte[20];
        try {
            byte[] bytes = "0Yxa8Wxp!X".getBytes("UTF-8");
            bArr[0] = (byte) 1;
            bArr[1] = Ascii.FF;
            for (int i = 0; i < 17; i++) {
                if (i < bytes.length) {
                    bArr[i + 2] = bytes[i];
                } else {
                    bArr[i + 2] = (byte) 0;
                }
            }
            bArr[19] = this.f7487a.crc8(bArr);
            u.setValue(bArr);
            boolean writeCharacteristic = this.f7488b.writeCharacteristic(u);
            f7485c.info("write ble auth key to ble is " + writeCharacteristic);
            return writeCharacteristic;
        } catch (UnsupportedEncodingException e) {
            f7485c.error("Write ble auth key to ble error, " + e);
            return false;
        }
    }

    /* renamed from: b */
    public void mo3163b() {
        C1615b i = this.f7497l.m8744i();
        if (i != null) {
            i.m8776p();
        }
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 5;
            Calendar instance = Calendar.getInstance();
            bArr[2] = Byte.decode("0x" + (instance.get(1) - 2000)).byteValue();
            bArr[3] = Byte.decode("0x" + (instance.get(2) + 1)).byteValue();
            bArr[4] = Byte.decode("0x" + instance.get(5)).byteValue();
            bArr[5] = Byte.decode("0x" + instance.get(11)).byteValue();
            bArr[6] = Byte.decode("0x" + instance.get(12)).byteValue();
            bArr[7] = Byte.decode("0x" + instance.get(13)).byteValue();
            bArr[19] = this.f7487a.crc8(bArr);
            u.setValue(bArr);
            f7485c.trace("请求预览数据，" + this.f7488b.writeCharacteristic(u));
        }
    }

    /* renamed from: a */
    public boolean mo3161a(long j, int i, String str) {
        boolean z = false;
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 6;
            Calendar instance = Calendar.getInstance();
            if (j > 0) {
                instance.setTimeInMillis(j);
            }
            bArr[2] = Byte.decode("0x" + (instance.get(1) - 2000)).byteValue();
            bArr[3] = Byte.decode("0x" + (instance.get(2) + 1)).byteValue();
            if (i == 17) {
                bArr[3] = (byte) (instance.get(2) + 1);
            }
            bArr[4] = Byte.decode("0x" + instance.get(5)).byteValue();
            bArr[5] = Byte.decode("0x" + instance.get(11)).byteValue();
            bArr[6] = Byte.decode("0x" + instance.get(12)).byteValue();
            bArr[7] = Byte.decode("0x" + instance.get(13)).byteValue();
            bArr[19] = this.f7487a.crc8(bArr);
            u.setValue(bArr);
            u.setWriteType(1);
            z = this.f7488b.writeCharacteristic(u);
            f7485c.info("Sync activity start, finish time = " + j + ", " + z);
            if (z) {
                this.f7504t = str;
                this.f7497l.m8744i().m8774n().clear();
            }
        }
        return z;
    }

    /* renamed from: f */
    protected boolean m8968f(int i) {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 8;
        bArr[2] = (byte) i;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        boolean writeCharacteristic = this.f7488b.writeCharacteristic(u);
        this.f7497l.m8744i().m8751b(0);
        this.f7497l.m8744i().m8754c(0);
        f7485c.info("OTA升级激活Byte ＝ " + i + " is " + writeCharacteristic);
        return writeCharacteristic;
    }

    /* renamed from: m */
    protected boolean m8976m() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            return false;
        }
        f7485c.info("write to ota pack start ...");
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 9;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        return this.f7488b.writeCharacteristic(u);
    }

    /* renamed from: a */
    public void mo3136a(byte b, byte b2, int i) {
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = Ascii.VT;
            bArr[2] = b;
            bArr[3] = b2;
            byte[] a = aa.m12774a((short) i);
            bArr[4] = a[1];
            bArr[5] = a[0];
            bArr[19] = this.f7487a.crc8(bArr);
            u.setWriteType(1);
            u.setValue(bArr);
            f7485c.info("writeReceiveResponse(), flag = " + b + ", type = " + b2 + ", index = " + i + ", " + this.f7488b.writeCharacteristic(u));
        }
    }

    /* renamed from: a */
    public void mo3135a() {
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 1;
            bArr[19] = this.f7487a.crc8(bArr);
            u.setValue(bArr);
            this.f7488b.writeCharacteristic(u);
        }
    }

    /* renamed from: n */
    public boolean m8977n() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = Ascii.SO;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        this.f7497l.m8744i().m8774n().clear();
        return this.f7488b.writeCharacteristic(u);
    }

    /* renamed from: o */
    public boolean m8978o() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 2;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        return this.f7488b.writeCharacteristic(u);
    }

    /* renamed from: p */
    protected void m8979p() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            f7485c.error("Write device info extension is false, character is null");
            return;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 4;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        f7485c.error("Write device info extension is " + this.f7488b.writeCharacteristic(u));
    }

    /* renamed from: q */
    protected boolean m8980q() {
        BluetoothGattCharacteristic u = m8926u();
        if (u == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 13;
        bArr[19] = this.f7487a.crc8(bArr);
        u.setWriteType(1);
        u.setValue(bArr);
        return this.f7488b.writeCharacteristic(u);
    }

    /* renamed from: a */
    public void mo3137a(int i, String str, String str2) {
        FileInputStream fileInputStream;
        IOException e;
        Throwable th;
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            try {
                fileInputStream = new FileInputStream(str2);
                try {
                    int available = fileInputStream.available();
                    fileInputStream.close();
                    if (i == 4) {
                        available += 512;
                    }
                    m8923h(i);
                    this.f7497l.m8744i().m8747a((double) available);
                    byte[] bArr = new byte[20];
                    bArr[0] = (byte) 1;
                    bArr[1] = (byte) 7;
                    bArr[2] = (byte) i;
                    String[] split = str.split("\\.");
                    if (split.length == 3) {
                        bArr[3] = (byte) Integer.valueOf(split[0]).intValue();
                        bArr[4] = (byte) Integer.valueOf(split[1]).intValue();
                        bArr[5] = (byte) Integer.valueOf(split[2]).intValue();
                    }
                    if (available % 190 == 0) {
                        available /= 190;
                    } else {
                        available = (available / 190) + 1;
                    }
                    byte[] a = aa.m12774a((short) available);
                    bArr[6] = a[1];
                    bArr[7] = a[0];
                    bArr[19] = this.f7487a.crc8(bArr);
                    u.setValue(bArr);
                    u.setWriteType(1);
                    this.f7488b.writeCharacteristic(u);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e5) {
                e22 = e5;
                fileInputStream = null;
                e22.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
    }

    /* renamed from: a */
    public void m8936a(int i, String str, int i2) {
        BluetoothGattCharacteristic u = m8926u();
        if (u != null) {
            int i3;
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 7;
            bArr[2] = (byte) i;
            String[] split = str.split("\\.");
            if (split.length == 3) {
                bArr[3] = (byte) Integer.valueOf(split[0]).intValue();
                bArr[4] = (byte) Integer.valueOf(split[1]).intValue();
                bArr[5] = (byte) Integer.valueOf(split[2]).intValue();
            }
            if (i2 % 190 == 0) {
                i3 = i2 / 190;
            } else {
                i3 = (i2 / 190) + 1;
            }
            byte[] a = aa.m12774a((short) i3);
            bArr[6] = a[1];
            bArr[7] = a[0];
            bArr[19] = this.f7487a.crc8(bArr);
            u.setValue(bArr);
            this.f7488b.writeCharacteristic(u);
        }
    }

    /* renamed from: d */
    public void mo3169d() {
    }

    /* renamed from: a */
    public boolean mo3157a(byte b) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 3;
        bArr[2] = b;
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: b */
    public boolean mo3165b(int i) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = Ascii.FF;
        if (i == 1) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: a */
    public boolean mo3162a(boolean z) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 9;
        if (z) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: a */
    public boolean mo3158a(int i) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 6;
        if (i == 0) {
            bArr[2] = (byte) 0;
        } else {
            bArr[2] = (byte) 1;
        }
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: r */
    protected void m8981r() {
        BluetoothGattCharacteristic v = m8927v();
        if (v != null) {
            String str;
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 1;
            Calendar instance = Calendar.getInstance();
            bArr[2] = Byte.decode("0x" + (instance.get(1) - 2000)).byteValue();
            bArr[3] = Byte.decode("0x" + (instance.get(2) + 1)).byteValue();
            bArr[4] = Byte.decode("0x" + instance.get(5)).byteValue();
            bArr[5] = Byte.decode("0x" + instance.get(11)).byteValue();
            bArr[6] = Byte.decode("0x" + instance.get(12)).byteValue();
            bArr[7] = Byte.decode("0x" + instance.get(13)).byteValue();
            int i = ((instance.get(16) / 1000) / 3600) + ((instance.get(15) / 1000) / 3600);
            f7485c.info("当前时区：" + i);
            String str2 = "0x";
            if (i >= 0) {
                str = str2 + 0 + Integer.toHexString(i).toUpperCase();
            } else {
                str = str2 + 1 + Integer.toHexString(Math.abs(i)).toUpperCase();
            }
            bArr[8] = Byte.decode(str).byteValue();
            for (i = 9; i < 19; i++) {
                bArr[i] = (byte) 0;
            }
            bArr[19] = this.f7487a.crc8(bArr);
            v.setWriteType(1);
            v.setValue(bArr);
            f7485c.info("Write system time to ble... , isWrite = " + this.f7488b.writeCharacteristic(v));
        }
    }

    /* renamed from: s */
    protected void m8982s() {
        BluetoothGattCharacteristic v = m8927v();
        if (v != null) {
            byte[] bArr = new byte[20];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) 10;
            try {
                String str = Build.MODEL;
                if (!TextUtils.isEmpty(str)) {
                    int i = 0;
                    for (int i2 = 0; i2 < str.length(); i2++) {
                        byte[] bytes = String.valueOf(str.charAt(i2)).getBytes("UTF-8");
                        if (bytes.length + i <= 16) {
                            int length = bytes.length;
                            int i3 = 0;
                            while (i3 < length) {
                                bArr[i + 2] = bytes[i3];
                                i3++;
                                i++;
                            }
                        }
                    }
                }
                bArr[19] = this.f7487a.crc8(bArr);
                v.setValue(bArr);
                f7485c.info("writeUserDeviceConfig isWrite = " + this.f7488b.writeCharacteristic(v) + "; DeviceName = " + str + ", data[] = " + Arrays.toString(bArr));
            } catch (UnsupportedEncodingException e) {
                f7485c.error("writeUserDeviceConfig error, " + e);
            }
        }
    }

    /* renamed from: b */
    public boolean mo3166b(boolean z) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 2;
        if (z) {
            bArr[2] = (byte) 1;
        } else {
            bArr[2] = (byte) 0;
        }
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: e */
    public boolean mo3172e(int i) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 7;
        switch (i) {
            case 0:
                bArr[2] = (byte) 0;
                break;
            case 1:
                bArr[2] = (byte) 2;
                break;
            case 2:
                bArr[2] = (byte) 1;
                break;
        }
        bArr[19] = this.f7487a.crc8(bArr);
        v.setWriteType(1);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: a */
    public boolean mo3159a(int i, int i2, int i3) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        Object obj = new byte[20];
        obj[0] = 1;
        obj[1] = (byte) 8;
        obj[2] = (byte) i;
        System.arraycopy(aa.m12773a(i2), 0, obj, 3, 4);
        System.arraycopy(aa.m12773a(i3), 0, obj, 7, 4);
        obj[19] = this.f7487a.crc8(obj);
        v.setValue(obj);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: c */
    public boolean mo3168c(int i) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = Ascii.SO;
        byte[] a = aa.m12774a((short) i);
        bArr[2] = a[1];
        bArr[3] = a[0];
        bArr[19] = this.f7487a.crc8(bArr);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: d */
    public boolean mo3170d(int i) {
        BluetoothGattCharacteristic v = m8927v();
        if (v == null) {
            return false;
        }
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 1;
        bArr[1] = Ascii.VT;
        bArr[2] = (byte) i;
        bArr[19] = this.f7487a.crc8(bArr);
        v.setValue(bArr);
        return this.f7488b.writeCharacteristic(v);
    }

    /* renamed from: a */
    public boolean mo3160a(int i, int i2, int i3, int i4, String str) {
        return false;
    }

    /* renamed from: a */
    public int mo3134a(int i, String str, C1668c c1668c, ArrayList<Position> arrayList) {
        if (c1668c == null) {
            return 2;
        }
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
        if (i3 > m_AppUI.MSG_APP_DATA_OK) {
            return 1;
        }
        byte[] a = m8916a(c1668c);
        if (a == null) {
            f7485c.error("get route segments error");
            return 2;
        }
        try {
            byteArrayOutputStream.write(m8917a(c1668c, i2, this.f7487a.getCheckSum(a), i3, (ArrayList) arrayList));
            byteArrayOutputStream.write(a);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f7503r = byteArrayOutputStream.toByteArray();
            this.f7497l.m8744i().m8747a((double) this.f7503r.length);
            m8936a(i, str, this.f7503r.length);
            return 0;
        } catch (IOException e2) {
            e2.printStackTrace();
            f7485c.error("ByteOutputStream write error: " + e2);
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

    /* renamed from: a */
    private byte[] m8916a(C1668c c1668c) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Iterator it = c1668c.m9050c().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((C1667b) it.next()).m9047c().iterator();
                while (it2.hasNext()) {
                    byteArrayOutputStream.write(m8918a((C1669d) it2.next()));
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
            f7485c.error("getRouteSegments e: " + e2);
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

    /* renamed from: b */
    private void m8920b(int i, int i2, int i3) {
        if (this.f7503r == null || this.f7503r.length <= 0) {
            f7485c.error("route is null");
            return;
        }
        int i4;
        byte[] a;
        byte[] bArr = new byte[200];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) i2;
        if (i % 190 == 0) {
            i4 = i / 190;
        } else {
            i4 = (i / 190) + 1;
        }
        byte[] a2 = aa.m12774a((short) i4);
        bArr[2] = a2[1];
        bArr[3] = a2[0];
        a2 = aa.m12774a((short) i3);
        bArr[4] = a2[1];
        bArr[5] = a2[0];
        a2 = aa.m12774a((short) 190);
        if (i4 - 1 == i3) {
            int i5 = i - ((i4 - 1) * 190);
            int i6 = 190 - i5;
            f7485c.trace("写入路线数据的总个数 ＝ " + i4 + "; 实际总长 ＝ " + i + "; 差 ＝" + i6 + ";长 ＝" + (190 - i6));
            a = aa.m12774a((short) i5);
        } else {
            a = a2;
        }
        bArr[6] = a[1];
        bArr[7] = a[0];
        for (i4 = 0; i4 < 190; i4++) {
            if ((i3 * 190) + i4 >= this.f7503r.length) {
                bArr[i4 + 8] = (byte) 0;
            } else {
                bArr[i4 + 8] = this.f7503r[(i3 * 190) + i4];
            }
        }
        a = aa.m12774a((short) this.f7487a.crc16(bArr));
        bArr[Opcodes.IFNULL] = a[1];
        bArr[Opcodes.IFNONNULL] = a[0];
        this.f7505u = ByteBuffer.wrap(bArr);
        m8930y();
    }

    /* renamed from: y */
    private synchronized void m8930y() {
        if (this.f7505u == null) {
            f7485c.error("Ota ByteBuffer is null");
        } else if (this.f7505u.hasRemaining()) {
            byte[] bArr = new byte[20];
            this.f7505u.get(bArr, 0, 20);
            m8913a(bArr);
        } else {
            f7485c.error("Ota ByteBuffer hasRemaining is false");
        }
    }

    /* renamed from: a */
    private synchronized void m8913a(byte[] bArr) {
        BluetoothGattCharacteristic x = m8929x();
        if (x == null) {
            f7485c.info("writeRouteToCentral(), character is null");
        } else {
            x.setWriteType(1);
            x.setValue(bArr);
            this.f7488b.writeCharacteristic(x);
        }
    }

    /* renamed from: a */
    private byte[] m8917a(C1668c c1668c, int i, int i2, int i3, ArrayList<Position> arrayList) {
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
            f7485c.error("BLE-Navi", "Navigation way point is empty.");
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
    private byte[] m8918a(C1669d c1669d) {
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

    /* renamed from: a */
    public void mo3155a(String str, String str2, byte b) {
        if (m8980q()) {
            BluetoothGattCharacteristic w = m8928w();
            if (w != null) {
                byte[] bytes;
                int i;
                byte[] bArr = new byte[200];
                bArr[0] = (byte) 1;
                bArr[1] = b;
                int i2 = 0;
                int i3 = 0;
                while (i2 < str.length()) {
                    try {
                        bytes = String.valueOf(str.charAt(i2)).getBytes("UTF-8");
                        if (bytes.length + i3 > 16) {
                            break;
                        }
                        i = i3;
                        for (byte b2 : bytes) {
                            bArr[i + 2] = b2;
                            i++;
                        }
                        i2++;
                        i3 = i;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if (!TextUtils.isEmpty(str2)) {
                    i2 = 0;
                    i3 = 0;
                    while (i2 < str2.length()) {
                        bytes = String.valueOf(str2.charAt(i2)).getBytes("UTF-8");
                        if (bytes.length + i3 > Opcodes.GETFIELD) {
                            break;
                        }
                        i = i3;
                        for (byte b22 : bytes) {
                            bArr[i + 18] = b22;
                            i++;
                        }
                        i2++;
                        i3 = i;
                    }
                }
                byte[] a = aa.m12774a((short) this.f7487a.crc16(bArr));
                bArr[Opcodes.IFNULL] = a[1];
                bArr[Opcodes.IFNONNULL] = a[0];
                List arrayList = new ArrayList();
                for (byte valueOf : bArr) {
                    arrayList.add(Byte.valueOf(valueOf));
                }
                for (i2 = 0; i2 < 10; i2++) {
                    List subList = arrayList.subList(i2 * 20, (i2 * 20) + 20);
                    byte[] bArr2 = new byte[20];
                    for (i = 0; i < subList.size(); i++) {
                        bArr2[i] = ((Byte) subList.get(i)).byteValue();
                    }
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    w.setValue(bArr2);
                    w.setWriteType(1);
                    f7485c.trace("Write notification to ble device is " + this.f7488b.writeCharacteristic(w));
                }
            }
        }
    }

    /* renamed from: a */
    protected void m8935a(int i, int i2, int i3, List<Byte> list, int i4) {
        if (list != null && !list.isEmpty()) {
            byte[] bArr = new byte[200];
            bArr[0] = (byte) 1;
            bArr[1] = (byte) i;
            int i5 = i3 / 190;
            byte[] a = aa.m12774a((short) i5);
            bArr[2] = a[1];
            bArr[3] = a[0];
            a = aa.m12774a((short) i2);
            bArr[4] = a[1];
            bArr[5] = a[0];
            a = aa.m12774a((short) 190);
            if (i5 - 1 == i2) {
                int i6 = i3 - i4;
                f7485c.trace("写入ota数据的总个数 ＝ " + i3 + "; 实际总长 ＝ " + i4 + "; 差 ＝" + i6 + ";长 ＝" + (190 - i6));
                a = aa.m12774a((short) (190 - i6));
            }
            bArr[6] = a[1];
            bArr[7] = a[0];
            i5 = 0;
            while (i5 < 190) {
                if ((i2 * 190) + i5 < list.size()) {
                    bArr[i5 + 8] = ((Byte) list.get((i2 * 190) + i5)).byteValue();
                    i5++;
                } else {
                    return;
                }
            }
            a = aa.m12774a((short) this.f7487a.crc16(bArr));
            bArr[Opcodes.IFNULL] = a[1];
            bArr[Opcodes.IFNONNULL] = a[0];
            this.f7505u = ByteBuffer.wrap(bArr);
            m8930y();
        }
    }

    /* renamed from: a */
    private void m8912a(long j) {
        try {
            f7485c.warn("Thread [" + Thread.currentThread().getName() + "] sleep " + j);
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: z */
    private synchronized void m8931z() {
        if (this.f7497l.m8744i().m8756c()) {
            f7485c.info("此次连接已经认证过");
        } else {
            Intent intent = new Intent("com.beastbikes.android.ble.connected.action");
            intent.addCategory("android.intent.category.DEFAULT");
            this.f7498m.sendBroadcast(intent);
            f7485c.info("handleAuthResponse");
            m8908A();
            this.f7497l.m8744i().m8752b(true);
        }
    }

    /* renamed from: A */
    private void m8908A() {
        if (this.f7488b != null) {
            f7485c.info("registerNotify");
            m8983t();
            m8912a(1000);
            m8982s();
            if (this.f7497l != null) {
                C1614a b = C1661h.m8999a().m9005b(this.f7497l.m8728a());
                if (b != null) {
                    b.m8736b(true);
                    this.f7501p.m8886a(b);
                }
            }
        }
    }

    /* renamed from: t */
    void m8983t() {
        f7485c.trace("enableTxNotification ==============");
        C1615b i = this.f7497l.m8744i();
        if (!i.m8762e()) {
            m8912a(1000);
            i.m8755c(m8967f());
        }
        if (!i.m8768h()) {
            m8912a(1000);
            i.m8763f(m8970g());
        }
        if (!i.m8766g()) {
            m8912a(1000);
            i.m8761e(m8973j());
        }
        if (!i.m8764f()) {
            m8912a(1000);
            i.m8759d(m8974k());
        }
    }

    /* renamed from: b */
    private void m8921b(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null && this.f7497l != null) {
            C1615b i = this.f7497l.m8744i();
            if (!i.m8766g()) {
                f7485c.trace("查看command notification 是否注册成功, 如果没有注册成功则再次注册");
                i.m8761e(m8973j());
            }
            if (!i.m8762e()) {
                f7485c.trace("查看实时预览数据的notification是否注册成功，如果没有则再次注册");
                i.m8755c(m8967f());
            }
            if (!i.m8768h()) {
                f7485c.trace("查看数据同步的notification是否注册成功，如果没有则再次注册");
                i.m8763f(m8970g());
            }
            if (!i.m8764f()) {
                f7485c.trace("查看Sensor Notification 是否注册成功，如果没有则再次注册");
                i.m8759d(m8974k());
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value != null && value.length >= 20) {
                String uuid = bluetoothGattCharacteristic.getUuid().toString();
                if (uuid.equals(a$a.f7188f.toString())) {
                    switch (value[1]) {
                        case (byte) 6:
                            if (this.f7489d != null) {
                                this.f7489d.mo3222a();
                                break;
                            }
                            break;
                        case (byte) 9:
                            m8909B();
                            break;
                        case (byte) 12:
                            m8931z();
                            break;
                    }
                }
                if (a$a.f7191i.toString().equals(uuid)) {
                    m8930y();
                }
                if (a$a.f7187e.toString().equals(uuid)) {
                    switch (value[1]) {
                        case (byte) 1:
                            m8979p();
                            return;
                        case (byte) 8:
                            mo3163b();
                            return;
                        case (byte) 10:
                            m8981r();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    /* renamed from: h */
    private void m8923h(int i) {
        Exception e;
        Throwable th;
        if (this.f7502q == null) {
            this.f7502q = new ArrayList();
        }
        this.f7502q.clear();
        SharedPreferences sharedPreferences = this.f7498m.getSharedPreferences(this.f7498m.getPackageName(), 0);
        Object obj = "";
        switch (i) {
            case 1:
                obj = sharedPreferences.getString("beast.ble.img", "");
                break;
            case 2:
                obj = sharedPreferences.getString("beast.mcu.img", "");
                break;
            case 3:
                obj = sharedPreferences.getString("beast.ui.img", "");
                break;
            case 4:
                obj = sharedPreferences.getString("beast.a_gps.img", "");
                break;
            case 5:
                obj = sharedPreferences.getString("beast.font.img", "");
                break;
            case 6:
                obj = sharedPreferences.getString("beast.power.img", "");
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            FileInputStream fileInputStream = null;
            FileInputStream fileInputStream2;
            try {
                obj = new JSONObject(obj).optString("path");
                if (!TextUtils.isEmpty(obj)) {
                    fileInputStream2 = new FileInputStream(obj);
                    try {
                        int i2;
                        int available = fileInputStream2.available();
                        byte[] bArr = new byte[available];
                        fileInputStream2.read(bArr);
                        fileInputStream2.close();
                        if (i == 4) {
                            Object obj2 = new byte[512];
                            Object a = aa.m12773a(this.f7487a.getCheckSum(bArr));
                            System.arraycopy(a, 0, obj2, 0, a.length);
                            a = aa.m12773a(available);
                            System.arraycopy(a, 0, obj2, 4, a.length);
                            a = aa.m12773a((int) (System.currentTimeMillis() / 1000));
                            System.arraycopy(a, 0, obj2, d_ResultType.LONG_URL, a.length);
                            for (byte valueOf : obj2) {
                                this.f7502q.add(Byte.valueOf(valueOf));
                            }
                            available += 512;
                        }
                        this.f7497l.m8744i().m8758d(available);
                        for (byte valueOf2 : bArr) {
                            this.f7502q.add(Byte.valueOf(valueOf2));
                        }
                        if (available % 190 == 0) {
                            available /= 190;
                        } else {
                            i2 = ((available / 190) + 1) * 190;
                            while (available < i2) {
                                this.f7502q.add(Byte.valueOf((byte) 0));
                                available++;
                            }
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } else if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                throw th;
            }
        }
    }

    /* renamed from: a */
    public void mo3138a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value != null && value.length >= 20) {
                int totalDistance;
                int j;
                int k;
                if (uuid.equals(a$a.f7189g.toString())) {
                    if (value.length >= 2) {
                        String str;
                        switch (value[1]) {
                            case (byte) 1:
                                m8978o();
                                final DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic = (DeviceInfoCommandCharacteristic) this.f7487a.parseCommandCharacteristic(value);
                                if (deviceInfoCommandCharacteristic != null) {
                                    String a;
                                    String str2 = "";
                                    if (this.f7497l != null) {
                                        a = this.f7497l.m8728a();
                                    } else {
                                        a = str2;
                                    }
                                    C1614a b = C1661h.m8999a().m9005b(a);
                                    if (b != null) {
                                        BleDevice bleDevice;
                                        f7485c.info("设备信息：" + deviceInfoCommandCharacteristic.toString());
                                        b.m8735b(deviceInfoCommandCharacteristic.getHardwareType());
                                        BleDevice a2 = this.f7501p.m8881a(b.m8728a(), b.m8743h(), deviceInfoCommandCharacteristic.getHardwareType(), deviceInfoCommandCharacteristic.getBrandType(), this.f7488b.getDevice().getAddress(), true);
                                        str = "";
                                        str = "";
                                        if (a2 == null) {
                                            bleDevice = new BleDevice();
                                        } else {
                                            bleDevice = a2;
                                        }
                                        String frameId = bleDevice.getFrameId();
                                        String url = bleDevice.getUrl();
                                        bleDevice.setDeviceName(this.f7488b.getDevice().getName());
                                        bleDevice.setHardwareType(deviceInfoCommandCharacteristic.getHardwareType());
                                        bleDevice.setBrandType(deviceInfoCommandCharacteristic.getBrandType());
                                        bleDevice.setMacAddress(a);
                                        bleDevice.setDeviceId(this.f7488b.getDevice().getAddress());
                                        this.f7501p.m8888a(a, this.f7488b.getDevice().getAddress(), deviceInfoCommandCharacteristic.getHardwareType(), deviceInfoCommandCharacteristic.getBrandType(), url, frameId);
                                        b.m8744i().m8749a(deviceInfoCommandCharacteristic);
                                        if (this.f7496k != null) {
                                            this.f7500o.post(new Runnable(this) {
                                                /* renamed from: c */
                                                final /* synthetic */ C1656f f7480c;

                                                public void run() {
                                                    this.f7480c.f7496k.m8801a(bleDevice, deviceInfoCommandCharacteristic);
                                                }
                                            });
                                            break;
                                        }
                                    }
                                    f7485c.error("DeviceInfo is response, but CentralSession is null");
                                    return;
                                }
                                break;
                            case (byte) 2:
                                final AGpsInfoCharacteristic aGpsInfoCharacteristic = (AGpsInfoCharacteristic) this.f7487a.parseCommandCharacteristic(value);
                                if (aGpsInfoCharacteristic == null) {
                                    if (this.f7497l.m8744i().m8769i()) {
                                        m8977n();
                                        break;
                                    }
                                }
                                if (this.f7492g != null) {
                                    this.f7500o.post(new Runnable(this) {
                                        /* renamed from: b */
                                        final /* synthetic */ C1656f f7482b;

                                        public void run() {
                                            this.f7482b.f7492g.m8783a(aGpsInfoCharacteristic);
                                        }
                                    });
                                }
                                m8977n();
                                break;
                                break;
                            case (byte) 4:
                                mo3135a();
                                DeviceInfoExtensionCharacteristic deviceInfoExtensionCharacteristic = (DeviceInfoExtensionCharacteristic) this.f7487a.parseCommandCharacteristic(value);
                                if (deviceInfoExtensionCharacteristic != null) {
                                    str = C1614a.m8725a(this.f7488b.getDevice().getAddress());
                                    f7485c.info("DeviceInfo");
                                    deviceInfoExtensionCharacteristic.setMacAddr(str);
                                    this.f7497l.m8744i().m8748a(deviceInfoExtensionCharacteristic.getGuaranteeTime());
                                    totalDistance = deviceInfoExtensionCharacteristic.getTotalDistance() * 1000;
                                    C1614a b2 = C1661h.m8999a().m9005b(this.f7497l.m8728a());
                                    this.f7501p.m8886a(b2);
                                    if (!(this.f7491f == null || b2 == null)) {
                                        this.f7491f.m8803a(b2.m8728a(), (float) totalDistance);
                                        break;
                                    }
                                }
                                break;
                            case (byte) 6:
                                f7485c.trace("write to ble receive response = " + Arrays.toString(value));
                                switch (value[2]) {
                                    case (byte) 1:
                                    case (byte) 2:
                                        break;
                                    case (byte) 3:
                                        f7485c.error("write to ble ota packet timeout...");
                                        break;
                                    default:
                                        break;
                                }
                            case (byte) 7:
                                Object obj = (OTARequestCommandCharacteristic) this.f7487a.parseCommandCharacteristic(value);
                                if (obj != null) {
                                    f7485c.trace("Ota character = " + obj.toString());
                                    switch (value[3]) {
                                        case (byte) 1:
                                            if (this.f7490e != null) {
                                                this.f7490e.mo3226b(obj.getRequestPacketIndex());
                                            }
                                            f7485c.trace("请求OTA数据包" + obj.getProcessType());
                                            m8949a(obj);
                                            break;
                                        case (byte) 2:
                                            f7485c.info("ble接收Packet 数据包成功");
                                            totalDistance = obj.getProcessType();
                                            if (this.f7490e != null) {
                                                this.f7490e.mo3228c(totalDistance);
                                            }
                                            j = this.f7497l.m8744i().m8770j();
                                            k = this.f7497l.m8744i().m8771k();
                                            switch (totalDistance) {
                                                case 1:
                                                    j |= 1;
                                                    this.f7497l.m8744i().m8751b(j);
                                                    if (k != j) {
                                                        m8969g(2);
                                                        break;
                                                    }
                                                    f7485c.info("ota ble 升级完成，开始激活");
                                                    m8924i(j);
                                                    break;
                                                case 2:
                                                    j |= 2;
                                                    this.f7497l.m8744i().m8751b(j);
                                                    if (k != j) {
                                                        m8969g(3);
                                                        break;
                                                    }
                                                    f7485c.info("ota mcu 升级完成，开始激活");
                                                    m8924i(j);
                                                    break;
                                                case 3:
                                                    j |= 4;
                                                    this.f7497l.m8744i().m8751b(j);
                                                    if (k != j) {
                                                        m8969g(5);
                                                        break;
                                                    }
                                                    f7485c.info("ota ui 升级完成，开始激活");
                                                    m8924i(j);
                                                    break;
                                                case 4:
                                                    this.f7499n.edit().putLong("beast.ble.agps.last.update.time", System.currentTimeMillis()).apply();
                                                    break;
                                                case 5:
                                                    j |= 8;
                                                    this.f7497l.m8744i().m8751b(j);
                                                    if (k != j) {
                                                        m8969g(6);
                                                        break;
                                                    }
                                                    f7485c.info("ota font 升级完成，开始激活");
                                                    m8924i(j);
                                                    break;
                                                case 6:
                                                    j |= 32;
                                                    this.f7497l.m8744i().m8751b(j);
                                                    if (k == j) {
                                                        f7485c.info("ota power 升级完成，开始激活");
                                                        m8924i(j);
                                                        break;
                                                    }
                                                    break;
                                            }
                                            m8925j(totalDistance);
                                            break;
                                        case (byte) 3:
                                            m8949a(obj);
                                            if (this.f7490e != null) {
                                                this.f7490e.mo3227c();
                                                break;
                                            }
                                            break;
                                        case (byte) 4:
                                            totalDistance = obj.getProcessType();
                                            switch (totalDistance) {
                                                case 1:
                                                case 2:
                                                case 4:
                                                    break;
                                                case 3:
                                                    m8925j(totalDistance);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        default:
                                            break;
                                    }
                                }
                                return;
                            case (byte) 8:
                                f7485c.error("当APP请求把立端时，把立回复的信息, ProcessType = " + value[2] + ", ErrorCode = " + value[3]);
                                switch (value[3]) {
                                    case (byte) 0:
                                        if (this.f7489d != null) {
                                            this.f7489d.mo3225b();
                                        }
                                        this.f7497l.m8744i().m8760e(0);
                                        break;
                                    case (byte) 1:
                                    case (byte) 2:
                                    case (byte) 3:
                                    case (byte) 4:
                                    case (byte) 5:
                                    case (byte) 6:
                                        if (this.f7493h != null) {
                                            this.f7493h.m8791b();
                                            break;
                                        }
                                        break;
                                    case (byte) 16:
                                    case (byte) 17:
                                    case (byte) 18:
                                    case (byte) 19:
                                    case (byte) 20:
                                    case (byte) 21:
                                        if (this.f7489d != null) {
                                            this.f7489d.mo3223a(value[3]);
                                            break;
                                        }
                                        break;
                                    case (byte) 32:
                                    case (byte) 33:
                                    case (byte) 48:
                                        if (this.f7493h != null) {
                                            this.f7493h.m8791b();
                                        }
                                        if (this.f7489d != null) {
                                            this.f7489d.mo3223a(value[3]);
                                            break;
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            case (byte) 9:
                                switch (value[2]) {
                                    case (byte) 1:
                                        m8910C();
                                        break;
                                    default:
                                        break;
                                }
                        }
                    }
                    return;
                }
                if (uuid.equals(a$a.f7186d.toString())) {
                    switch (value[1]) {
                        case (byte) 4:
                            f7485c.info("onCharacteristicChanged: " + bluetoothGattCharacteristic.getUuid().toString() + ", " + Arrays.toString(bluetoothGattCharacteristic.getValue()) + ", Thread [" + Thread.currentThread().getName() + "]");
                            final BatterySensorCharacteristic batterySensorCharacteristic = (BatterySensorCharacteristic) this.f7487a.parseSensorCharacteristic(value);
                            if (this.f7496k != null) {
                                this.f7500o.post(new Runnable(this) {
                                    /* renamed from: b */
                                    final /* synthetic */ C1656f f7484b;

                                    public void run() {
                                        this.f7484b.f7496k.m8802a(batterySensorCharacteristic);
                                    }
                                });
                                break;
                            }
                            break;
                    }
                }
                if (uuid.equals(a$a.f7190h.toString())) {
                    for (byte valueOf : value) {
                        this.f7497l.m8744i().m8774n().add(Byte.valueOf(valueOf));
                    }
                    if (this.f7497l.m8744i().m8774n().size() != 200) {
                        f7485c.trace("数据包 != 200, size = " + this.f7497l.m8744i().m8774n().size());
                        return;
                    }
                    byte b3;
                    byte[] bArr = new byte[200];
                    for (j = 0; j < bArr.length; j++) {
                        bArr[j] = ((Byte) this.f7497l.m8744i().m8774n().get(j)).byteValue();
                    }
                    if (bArr.length > 2) {
                        b3 = bArr[1];
                    } else {
                        b3 = (byte) 1;
                    }
                    this.f7497l.m8744i().m8776p();
                    f7485c.trace("大数据解析", "数据包 = " + Arrays.toString(bArr));
                    if (b3 == (byte) 1) {
                        PreviewDataCharacteristic previewDataCharacteristic = (PreviewDataCharacteristic) this.f7487a.parseSyncDataCharacteristic(bArr);
                        f7485c.info("预览数据解析 = " + previewDataCharacteristic);
                        if (previewDataCharacteristic != null) {
                            k = previewDataCharacteristic.getCurrentPacketIndex();
                            if (m8914a(previewDataCharacteristic, C1614a.m8725a(this.f7488b.getDevice().getAddress()))) {
                                mo3136a((byte) 1, (byte) 1, k);
                            } else {
                                mo3136a((byte) 2, (byte) 1, 0);
                            }
                            if (this.f7493h != null) {
                                this.f7493h.m8790a(previewDataCharacteristic.getTotalPacketCount(), k);
                            }
                            if (!(this.f7493h == null || this.f7488b.getDevice() == null || previewDataCharacteristic.getCurrentPacketIndex() + 1 != previewDataCharacteristic.getTotalPacketCount())) {
                                m8912a(1000);
                                this.f7493h.m8789a();
                            }
                        } else {
                            mo3136a((byte) 2, (byte) 1, 0);
                            if (this.f7493h != null) {
                                this.f7493h.m8791b();
                                f7485c.error("预览数据时，数据解析失败");
                            }
                        }
                        this.f7497l.m8744i().m8774n().clear();
                    }
                    if (b3 == (byte) 2) {
                        if (this.f7497l == null || !this.f7497l.m8744i().m8775o()) {
                            SynchronizationDataCharacteristic synchronizationDataCharacteristic = (SynchronizationDataCharacteristic) this.f7487a.parseSyncDataCharacteristic(bArr);
                            if (synchronizationDataCharacteristic == null) {
                                mo3136a((byte) 2, (byte) 2, this.f7497l.m8744i().m8773m());
                                f7485c.error("Sync data null");
                                if (this.f7489d != null) {
                                    f7485c.error("同步数据时，数据解析失败");
                                }
                            } else if (synchronizationDataCharacteristic.getTotalPacketCount() > 0) {
                                f7485c.info("同步数据 = " + synchronizationDataCharacteristic.toString());
                                f7485c.info("同步数据的activityId = " + this.f7504t);
                                k = synchronizationDataCharacteristic.getCurrentPacketIndex();
                                if (m8915a(synchronizationDataCharacteristic, this.f7504t)) {
                                    mo3136a((byte) 1, (byte) 2, k);
                                } else {
                                    mo3136a((byte) 2, (byte) 2, k);
                                }
                                this.f7497l.m8744i().m8760e(k + 1);
                                totalDistance = synchronizationDataCharacteristic.getTotalPacketCount();
                                if (this.f7489d != null) {
                                    this.f7489d.mo3224a(k, totalDistance);
                                }
                            }
                            this.f7497l.m8744i().m8774n().clear();
                        } else {
                            f7485c.info("手动点击取消同步数据");
                            return;
                        }
                    }
                    if (b3 == (byte) 3) {
                        OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic = (OTAFirmwareInfoCharacteristic) this.f7487a.parseSyncDataCharacteristic(bArr);
                        if (this.f7494i != null) {
                            if (oTAFirmwareInfoCharacteristic == null) {
                                f7485c.error("OTA固件版本信息解析失败");
                            }
                            this.f7494i.m8788a(oTAFirmwareInfoCharacteristic);
                        }
                        this.f7497l.m8744i().m8774n().clear();
                    }
                    this.f7497l.m8744i().m8774n().clear();
                }
            }
        }
    }

    /* renamed from: i */
    private void m8924i(int i) {
        if (m8968f(i) && this.f7490e != null) {
            this.f7490e.mo3228c(-1);
        }
    }

    /* renamed from: a */
    public void m8949a(Object obj) {
        f7485c.info("接收到开始传输数据命令");
        if (obj != null) {
            this.f7506v = (OTARequestCommandCharacteristic) obj;
            if (!this.f7497l.m8744i().m8753b()) {
                f7485c.trace("handleOTAPacketUpdate(), Request Ota Packet Start " + m8976m());
            }
        }
    }

    /* renamed from: h */
    public void mo3175h() {
        if (this.f7490e != null) {
            this.f7490e.mo3228c(0);
        }
    }

    /* renamed from: a */
    public void mo3154a(Object obj, int i) {
        f7485c.info("handleServiceDiscovered()");
        if (obj == null) {
            f7485c.error("handleServiceDiscovered(), Object is null");
            return;
        }
        this.f7497l = (C1614a) obj;
        this.f7488b = this.f7497l.m8741f();
        if (!this.f7497l.m8744i().m8766g()) {
            this.f7497l.m8744i().m8761e(m8973j());
        }
        f7485c.info("doRequestBleAuth isWriteCD24=[" + this.f7497l.m8744i().m8766g() + "]");
        BluetoothDevice device = this.f7488b.getDevice();
        if (device == null || device.getBondState() != 12) {
            m8912a(1000);
            int i2 = 0;
            boolean l = m8975l();
            while (i2 < 9 && !r0) {
                m8912a(200);
                l = m8975l();
                if (!l) {
                    i2++;
                } else {
                    return;
                }
            }
            return;
        }
        m8931z();
    }

    /* renamed from: b */
    public void mo3164b(Object obj, int i) {
        if (obj == null) {
            f7485c.error("handleCharacteristicWrite(), Object is null");
            return;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) obj;
        if (i == 0) {
            m8921b(bluetoothGattCharacteristic);
        }
    }

    /* renamed from: B */
    private void m8909B() {
        if (this.f7506v == null) {
            f7485c.error("OTA");
            return;
        }
        int processType = this.f7506v.getProcessType();
        int requestPacketIndex = this.f7506v.getRequestPacketIndex();
        if (processType == 16) {
            m8920b(this.f7503r.length, processType, requestPacketIndex);
            return;
        }
        if (this.f7502q == null || this.f7502q.isEmpty()) {
            m8923h(processType);
        }
        m8935a(processType, requestPacketIndex, this.f7502q.size(), this.f7502q, this.f7497l.m8744i().m8772l());
    }

    /* renamed from: g */
    protected void m8969g(int i) {
        boolean z = false;
        SharedPreferences sharedPreferences = this.f7498m.getSharedPreferences(this.f7498m.getPackageName(), 0);
        Object obj = "";
        switch (i) {
            case 1:
                obj = sharedPreferences.getString("beast.ble.img", "");
                break;
            case 2:
                obj = sharedPreferences.getString("beast.mcu.img", "");
                break;
            case 3:
                obj = sharedPreferences.getString("beast.ui.img", "");
                break;
            case 4:
                obj = "";
                break;
            case 5:
                obj = sharedPreferences.getString("beast.font.img", "");
                break;
            case 6:
                obj = sharedPreferences.getString("beast.power.img", "");
                break;
        }
        CharSequence string = sharedPreferences.getString("beast.power.img", "");
        C1615b i2 = this.f7497l.m8744i();
        if (!TextUtils.isEmpty(string)) {
            z = true;
        }
        i2.m8765g(z);
        if (TextUtils.isEmpty(obj)) {
            int i3 = i + 1;
            if (i3 <= 6) {
                m8969g(i3);
                return;
            } else {
                m8924i(this.f7497l.m8744i().m8770j());
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(obj);
            String optString = jSONObject.optString("path");
            String optString2 = jSONObject.optString(MapboxEvent.ATTRIBUTE_VERSION);
            if (!TextUtils.isEmpty(optString)) {
                mo3137a(i, optString2, optString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: j */
    private void m8925j(int i) {
        SharedPreferences sharedPreferences = this.f7498m.getSharedPreferences(this.f7498m.getPackageName(), 0);
        String str = "";
        Object obj = "";
        switch (i) {
            case 1:
                str = "beast.ble.img";
                obj = sharedPreferences.getString("beast.ble.img", "");
                break;
            case 2:
                str = "beast.mcu.img";
                obj = sharedPreferences.getString("beast.mcu.img", "");
                break;
            case 3:
                str = "beast.ui.img";
                obj = sharedPreferences.getString("beast.ui.img", "");
                break;
            case 4:
                str = "beast.a_gps.img";
                obj = sharedPreferences.getString("beast.a_gps.img", "");
                break;
            case 5:
                str = "beast.font.img";
                obj = sharedPreferences.getString("beast.font.img", "");
                break;
            case 6:
                str = "beast.power.img";
                obj = sharedPreferences.getString("beast.power.img", "");
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            try {
                String optString = new JSONObject(obj).optString("path");
                if (!TextUtils.isEmpty(optString) && C2557f.m12834a(optString)) {
                    sharedPreferences.edit().remove(str).commit();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: C */
    private void m8910C() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f7498m.getSystemService(UserData.PHONE_KEY);
        try {
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getITelephony", (Class[]) null);
            declaredMethod.setAccessible(true);
            ((ITelephony) declaredMethod.invoke(telephonyManager, (Object[]) null)).endCall();
            f7485c.info("挂断电话");
        } catch (Exception e) {
            f7485c.error("Ble设备请求挂断电话失败，" + e);
        }
    }

    /* renamed from: a */
    private boolean m8914a(PreviewDataCharacteristic previewDataCharacteristic, String str) {
        if (this.f7488b.getDevice() != null) {
            return this.f7501p.m8891a(previewDataCharacteristic, this.f7488b.getDevice().getAddress(), str);
        }
        f7485c.error("SavePreviewActivity(), BluetoothGatt gatt device is null");
        return false;
    }

    /* renamed from: a */
    private boolean m8915a(SynchronizationDataCharacteristic synchronizationDataCharacteristic, String str) {
        return this.f7501p.m8892a(str, synchronizationDataCharacteristic);
    }
}
