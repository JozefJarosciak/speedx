package com.beastbikes.android.ble.biz.p096a;

import ch.qos.logback.core.CoreConstants;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import java.util.ArrayList;
import java.util.List;

/* compiled from: InvocationParam */
/* renamed from: com.beastbikes.android.ble.biz.a.b */
public class C1615b {
    /* renamed from: a */
    private float f7388a;
    /* renamed from: b */
    private int f7389b;
    /* renamed from: c */
    private boolean f7390c = false;
    /* renamed from: d */
    private boolean f7391d = false;
    /* renamed from: e */
    private boolean f7392e;
    /* renamed from: f */
    private double f7393f;
    /* renamed from: g */
    private boolean f7394g;
    /* renamed from: h */
    private boolean f7395h;
    /* renamed from: i */
    private boolean f7396i;
    /* renamed from: j */
    private boolean f7397j;
    /* renamed from: k */
    private boolean f7398k;
    /* renamed from: l */
    private boolean f7399l;
    /* renamed from: m */
    private int f7400m;
    /* renamed from: n */
    private int f7401n;
    /* renamed from: o */
    private int f7402o;
    /* renamed from: p */
    private int f7403p = 0;
    /* renamed from: q */
    private List<Byte> f7404q = new ArrayList();
    /* renamed from: r */
    private DeviceInfoCommandCharacteristic f7405r;

    /* renamed from: a */
    public int m8746a() {
        return this.f7389b;
    }

    /* renamed from: a */
    public void m8748a(int i) {
        this.f7389b = i;
    }

    /* renamed from: b */
    public boolean m8753b() {
        return this.f7390c;
    }

    /* renamed from: a */
    public void m8750a(boolean z) {
        this.f7390c = z;
    }

    /* renamed from: c */
    public boolean m8756c() {
        return this.f7392e;
    }

    /* renamed from: b */
    public void m8752b(boolean z) {
        this.f7392e = z;
    }

    /* renamed from: d */
    public double m8757d() {
        return this.f7393f;
    }

    /* renamed from: a */
    public void m8747a(double d) {
        this.f7393f = d;
    }

    /* renamed from: e */
    public boolean m8762e() {
        return this.f7394g;
    }

    /* renamed from: c */
    public void m8755c(boolean z) {
        this.f7394g = z;
    }

    /* renamed from: f */
    public boolean m8764f() {
        return this.f7395h;
    }

    /* renamed from: d */
    public void m8759d(boolean z) {
        this.f7395h = z;
    }

    /* renamed from: g */
    public boolean m8766g() {
        return this.f7396i;
    }

    /* renamed from: e */
    public void m8761e(boolean z) {
        this.f7396i = z;
    }

    /* renamed from: h */
    public boolean m8768h() {
        return this.f7397j;
    }

    /* renamed from: f */
    public void m8763f(boolean z) {
        this.f7397j = z;
    }

    /* renamed from: g */
    public void m8765g(boolean z) {
        this.f7398k = z;
    }

    /* renamed from: i */
    public boolean m8769i() {
        return this.f7399l;
    }

    /* renamed from: j */
    public int m8770j() {
        return this.f7400m;
    }

    /* renamed from: b */
    public void m8751b(int i) {
        this.f7400m = i;
    }

    /* renamed from: k */
    public int m8771k() {
        return this.f7401n;
    }

    /* renamed from: c */
    public void m8754c(int i) {
        this.f7401n = i;
    }

    /* renamed from: l */
    public int m8772l() {
        return this.f7402o;
    }

    /* renamed from: d */
    public void m8758d(int i) {
        this.f7402o = i;
    }

    /* renamed from: m */
    public int m8773m() {
        return this.f7403p;
    }

    /* renamed from: e */
    public void m8760e(int i) {
        this.f7403p = i;
    }

    /* renamed from: n */
    public List<Byte> m8774n() {
        return this.f7404q;
    }

    /* renamed from: o */
    public boolean m8775o() {
        return this.f7391d;
    }

    /* renamed from: h */
    public void m8767h(boolean z) {
        this.f7391d = z;
    }

    /* renamed from: p */
    public void m8776p() {
        if (this.f7404q != null) {
            this.f7404q.clear();
        }
    }

    public String toString() {
        return "InvocationParam{totalDistance=" + this.f7388a + ", guaranteeTime=" + this.f7389b + ", cancelUpdate=" + this.f7390c + ", hasAuthkey=" + this.f7392e + ", fileLength=" + this.f7393f + ", isWriteCD20=" + this.f7394g + ", isWriteCD22=" + this.f7395h + ", isWriteCD24=" + this.f7396i + ", isWriteCD26=" + this.f7397j + ", hasPowerImg=" + this.f7398k + ", updateGps=" + this.f7399l + ", otaUpdateByte=" + this.f7400m + ", imgLength=" + this.f7402o + ", previewIndex=" + this.f7403p + ", activityData=" + this.f7404q + CoreConstants.CURLY_RIGHT;
    }

    /* renamed from: q */
    public DeviceInfoCommandCharacteristic m8777q() {
        return this.f7405r;
    }

    /* renamed from: a */
    public void m8749a(DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic) {
        this.f7405r = deviceInfoCommandCharacteristic;
    }
}
