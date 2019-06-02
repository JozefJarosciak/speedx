package com.beastbikes.android.ble.biz.p096a;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;

/* compiled from: CentralSession */
/* renamed from: com.beastbikes.android.ble.biz.a.a */
public class C1614a {
    /* renamed from: a */
    private String f7379a;
    /* renamed from: b */
    private BluetoothDevice f7380b;
    /* renamed from: c */
    private BluetoothGatt f7381c;
    /* renamed from: d */
    private int f7382d;
    /* renamed from: e */
    private boolean f7383e = false;
    /* renamed from: f */
    private int f7384f;
    /* renamed from: g */
    private boolean f7385g = false;
    /* renamed from: h */
    private boolean f7386h = false;
    /* renamed from: i */
    private C1615b f7387i = new C1615b();

    public C1614a(BluetoothDevice bluetoothDevice, int i, boolean z, int i2, String str) {
        this.f7380b = bluetoothDevice;
        this.f7382d = i;
        this.f7379a = str;
        this.f7384f = i2;
        this.f7383e = z;
    }

    public C1614a(String str) {
        this.f7379a = str;
    }

    /* renamed from: a */
    public String m8728a() {
        return this.f7379a;
    }

    /* renamed from: b */
    public BluetoothDevice m8734b() {
        return this.f7380b;
    }

    /* renamed from: a */
    public void m8730a(BluetoothDevice bluetoothDevice) {
        this.f7380b = bluetoothDevice;
    }

    /* renamed from: c */
    public int m8737c() {
        return this.f7382d;
    }

    /* renamed from: a */
    public void m8729a(int i) {
        this.f7382d = i;
    }

    /* renamed from: d */
    public boolean m8739d() {
        return this.f7383e;
    }

    /* renamed from: a */
    public void m8733a(boolean z) {
        this.f7383e = z;
    }

    /* renamed from: e */
    public int m8740e() {
        return this.f7384f;
    }

    /* renamed from: b */
    public void m8735b(int i) {
        this.f7384f = i;
    }

    /* renamed from: f */
    public BluetoothGatt m8741f() {
        return this.f7381c;
    }

    /* renamed from: a */
    public void m8731a(BluetoothGatt bluetoothGatt) {
        this.f7381c = bluetoothGatt;
    }

    /* renamed from: g */
    public boolean m8742g() {
        return this.f7385g;
    }

    /* renamed from: b */
    public void m8736b(boolean z) {
        this.f7385g = z;
    }

    /* renamed from: a */
    public static String m8725a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        StringBuilder stringBuilder = new StringBuilder();
        for (int length = split.length - 1; length >= 0; length--) {
            stringBuilder.append(split[length]);
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static String m8726b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int length = str.length() - 1; length >= 0; length--) {
            if (length % 2 == 0) {
                stringBuilder.append(str.charAt(length));
                stringBuilder.append(str.charAt(length + 1));
                if (length != 0) {
                    stringBuilder.append(":");
                }
            }
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (obj != null) {
            C1614a c1614a = (C1614a) obj;
            if (this.f7383e == c1614a.m8739d() && TextUtils.equals(this.f7379a, c1614a.m8728a())) {
                return true;
            }
        }
        return super.equals(obj);
    }

    /* renamed from: c */
    public static boolean m8727c(int i) {
        switch (i) {
            case 0:
            case 2:
                return false;
            default:
                return true;
        }
    }

    /* renamed from: h */
    public String m8743h() {
        if (this.f7380b == null) {
            return "";
        }
        String name = this.f7380b.getName();
        if (TextUtils.isEmpty(name) || name.equals("null")) {
            return "";
        }
        return name;
    }

    /* renamed from: i */
    public C1615b m8744i() {
        if (this.f7387i == null) {
            this.f7387i = new C1615b();
        }
        return this.f7387i;
    }

    /* renamed from: a */
    public void m8732a(C1615b c1615b) {
        this.f7387i = c1615b;
    }

    /* renamed from: j */
    public boolean m8745j() {
        return this.f7386h;
    }

    /* renamed from: c */
    public void m8738c(boolean z) {
        this.f7386h = z;
    }

    public String toString() {
        return "{  centralId=" + this.f7379a + ", available=" + this.f7383e + ", autoAttach=" + this.f7385g + ", state=" + this.f7382d + ", bluetoothDevice=" + this.f7380b + ", bluetoothGatt=" + this.f7381c + ", hdType=" + this.f7384f + ", unBound=" + this.f7386h + ", name='" + m8743h() + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
