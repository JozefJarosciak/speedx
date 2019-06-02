package com.beastbikes.android.ble.biz;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CentralSessionHandler */
/* renamed from: com.beastbikes.android.ble.biz.h */
public class C1661h {
    /* renamed from: a */
    private static final Logger f7522a = LoggerFactory.getLogger("BLE-CentralSessionHandler");
    /* renamed from: b */
    private static C1661h f7523b = new C1661h();
    /* renamed from: c */
    private LinkedHashMap<String, C1614a> f7524c = new LinkedHashMap();
    /* renamed from: d */
    private List<C1614a> f7525d = new ArrayList();
    /* renamed from: e */
    private boolean f7526e = false;

    private C1661h() {
    }

    /* renamed from: a */
    public static C1661h m8999a() {
        return f7523b;
    }

    /* renamed from: a */
    public C1614a m9000a(BluetoothDevice bluetoothDevice, int i, boolean z, String str) {
        if (bluetoothDevice == null) {
            return null;
        }
        C1614a b = m9005b(str);
        C1614a b2 = m9004b();
        if (b == null) {
            b = new C1614a(bluetoothDevice, 0, z, i, str);
            this.f7524c.put(b.m8728a(), b);
            return b;
        }
        if (b2 == null) {
            b.m8729a(0);
        } else if (TextUtils.equals(b.m8728a(), b2.m8728a())) {
            b.m8729a(b.m8737c());
        } else {
            b.m8729a(0);
        }
        b.m8730a(bluetoothDevice);
        b.m8733a(z);
        b.m8735b(i);
        return b;
    }

    /* renamed from: a */
    public C1614a m9001a(String str) {
        if (TextUtils.isEmpty(str)) {
            f7522a.debug("centralId is empty !");
            return null;
        }
        C1614a b = m9005b(str);
        if (b != null) {
            return b;
        }
        b = new C1614a(str);
        this.f7524c.put(b.m8728a(), b);
        return b;
    }

    /* renamed from: b */
    public C1614a m9005b(String str) {
        return (C1614a) this.f7524c.get(str);
    }

    /* renamed from: b */
    public C1614a m9004b() {
        for (Entry value : this.f7524c.entrySet()) {
            C1614a c1614a = (C1614a) value.getValue();
            if (c1614a.m8737c() == 4) {
                return c1614a;
            }
        }
        return null;
    }

    /* renamed from: c */
    public C1614a m9006c() {
        for (Entry value : this.f7524c.entrySet()) {
            C1614a c1614a = (C1614a) value.getValue();
            if (c1614a.m8737c() == 2) {
                return c1614a;
            }
        }
        return null;
    }

    @TargetApi(18)
    /* renamed from: c */
    public C1614a m9007c(String str) {
        if (TextUtils.isEmpty(str)) {
            f7522a.error("sessionDiscovered(), centralId is null");
            return null;
        }
        C1614a b = m9005b(str);
        if (b == null) {
            f7522a.error("sessionDiscovered() -> sessionMatch(), session is null");
            return null;
        }
        for (Entry value : this.f7524c.entrySet()) {
            C1614a c1614a = (C1614a) value.getValue();
            if (c1614a.m8728a().equals(b.m8728a())) {
                c1614a.m8736b(true);
                c1614a.m8729a(4);
            } else {
                c1614a.m8729a(0);
                c1614a.m8731a(null);
                c1614a.m8730a(null);
                c1614a.m8733a(false);
                c1614a.m8736b(false);
                c1614a.m8738c(false);
                c1614a.m8732a(null);
            }
        }
        return b;
    }

    /* renamed from: d */
    public List<C1614a> m9008d() {
        return this.f7525d;
    }

    /* renamed from: a */
    public List<C1614a> m9002a(C1614a c1614a) {
        if (this.f7525d.contains(c1614a)) {
            f7522a.info("updateScanResult scanResults contains[" + c1614a.m8728a() + "]");
            return null;
        }
        int size = this.f7525d.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(c1614a.m8728a(), ((C1614a) this.f7525d.get(i)).m8728a())) {
                this.f7525d.remove(i);
                this.f7525d.add(i, c1614a);
                return this.f7525d;
            }
        }
        this.f7525d.add(c1614a);
        return this.f7525d;
    }

    /* renamed from: e */
    public void m9010e() {
        if (this.f7525d != null) {
            this.f7525d.clear();
        }
    }

    /* renamed from: d */
    public boolean m9009d(String str) {
        C1614a b = m9005b(str);
        if (b == null || b.m8737c() != 4) {
            return false;
        }
        return true;
    }

    /* renamed from: f */
    public boolean m9011f() {
        return m9004b() != null;
    }

    /* renamed from: g */
    public boolean m9012g() {
        return this.f7526e;
    }

    /* renamed from: a */
    public void m9003a(boolean z) {
        this.f7526e = z;
    }
}
