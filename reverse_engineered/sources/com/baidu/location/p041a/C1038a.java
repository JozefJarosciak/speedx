package com.baidu.location.p041a;

import android.location.Location;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import com.avos.avoscloud.AVException;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1079d;
import com.baidu.platform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.baidu.location.a.a */
public class C1038a {
    /* renamed from: c */
    private static C1038a f2319c = null;
    /* renamed from: a */
    public boolean f2320a;
    /* renamed from: b */
    boolean f2321b;
    /* renamed from: d */
    private ArrayList<C1037a> f2322d;
    /* renamed from: e */
    private boolean f2323e;
    /* renamed from: f */
    private BDLocation f2324f;
    /* renamed from: g */
    private BDLocation f2325g;

    /* renamed from: com.baidu.location.a.a$a */
    private class C1037a {
        /* renamed from: a */
        public String f2314a = null;
        /* renamed from: b */
        public Messenger f2315b = null;
        /* renamed from: c */
        public LocationClientOption f2316c = new LocationClientOption();
        /* renamed from: d */
        public int f2317d = 0;
        /* renamed from: e */
        final /* synthetic */ C1038a f2318e;

        public C1037a(C1038a c1038a, Message message) {
            boolean z = true;
            this.f2318e = c1038a;
            this.f2315b = message.replyTo;
            this.f2314a = message.getData().getString("packName");
            this.f2316c.prodName = message.getData().getString("prodName");
            C1091b.m3989a().m3993a(this.f2316c.prodName, this.f2314a);
            this.f2316c.coorType = message.getData().getString("coorType");
            this.f2316c.addrType = message.getData().getString("addrType");
            this.f2316c.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            boolean z2 = C1100j.f2741l || this.f2316c.enableSimulateGps;
            C1100j.f2741l = z2;
            if (!C1100j.f2736g.equals("all")) {
                C1100j.f2736g = this.f2316c.addrType;
            }
            this.f2316c.openGps = message.getData().getBoolean("openGPS");
            this.f2316c.scanSpan = message.getData().getInt("scanSpan");
            this.f2316c.timeOut = message.getData().getInt("timeOut");
            this.f2316c.priority = message.getData().getInt("priority");
            this.f2316c.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.f2316c.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.f2316c.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            z2 = C1100j.f2737h || message.getData().getBoolean("isneedaptag", false);
            C1100j.f2737h = z2;
            if (!(C1100j.f2738i || message.getData().getBoolean("isneedaptagd", false))) {
                z = false;
            }
            C1100j.f2738i = z;
            C1100j.f2719P = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i >= C1100j.f2724U) {
                C1100j.f2724U = i;
            }
            i = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i >= C1100j.f2726W) {
                C1100j.f2726W = i;
            }
            i = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i >= C1100j.f2725V) {
                C1100j.f2725V = i;
            }
            if (this.f2316c.scanSpan >= 1000) {
            }
            if (this.f2316c.mIsNeedDeviceDirect || this.f2316c.isNeedAltitude) {
                C1060k.m3789a().m3794a(this.f2316c.mIsNeedDeviceDirect);
                C1060k.m3789a().m3796b(this.f2316c.isNeedAltitude);
                C1060k.m3789a().m3795b();
            }
            c1038a.f2321b |= this.f2316c.isNeedAltitude;
        }

        /* renamed from: a */
        private void m3636a(int i) {
            Message obtain = Message.obtain(null, i);
            try {
                if (this.f2315b != null) {
                    this.f2315b.send(obtain);
                }
                this.f2317d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f2317d++;
                }
            }
        }

        /* renamed from: a */
        private void m3637a(int i, Bundle bundle) {
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.f2315b != null) {
                    this.f2315b.send(obtain);
                }
                this.f2317d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f2317d++;
                }
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        private void m3638a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.f2315b != null) {
                    this.f2315b.send(obtain);
                }
                this.f2317d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f2317d++;
                }
            }
        }

        /* renamed from: a */
        public void m3641a() {
            if (!this.f2316c.location_change_notify) {
                return;
            }
            if (C1100j.f2731b) {
                m3636a(54);
            } else {
                m3636a(55);
            }
        }

        /* renamed from: a */
        public void m3642a(BDLocation bDLocation) {
            m3643a(bDLocation, 21);
        }

        /* renamed from: a */
        public void m3643a(BDLocation bDLocation, int i) {
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (C1060k.m3789a().m3802h() && (bDLocation2.getLocType() == 161 || bDLocation2.getLocType() == 66)) {
                bDLocation2.setAltitude(C1060k.m3789a().m3804j());
            }
            if (i == 21) {
                m3638a(27, "locStr", bDLocation2);
            }
            if (!(this.f2316c.coorType == null || this.f2316c.coorType.equals(CoordinateType.GCJ02))) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (!(longitude == Double.MIN_VALUE || latitude == Double.MIN_VALUE)) {
                    double[] coorEncrypt;
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals(CoordinateType.GCJ02)) || bDLocation2.getCoorType() == null) {
                        coorEncrypt = Jni.coorEncrypt(longitude, latitude, this.f2316c.coorType);
                        bDLocation2.setLongitude(coorEncrypt[0]);
                        bDLocation2.setLatitude(coorEncrypt[1]);
                        bDLocation2.setCoorType(this.f2316c.coorType);
                    } else if (!(bDLocation2.getCoorType() == null || !bDLocation2.getCoorType().equals(CoordinateType.WGS84) || this.f2316c.coorType.equals("bd09ll"))) {
                        coorEncrypt = Jni.coorEncrypt(longitude, latitude, "wgs842mc");
                        bDLocation2.setLongitude(coorEncrypt[0]);
                        bDLocation2.setLatitude(coorEncrypt[1]);
                        bDLocation2.setCoorType("wgs84mc");
                    }
                }
            }
            m3638a(i, "locStr", bDLocation2);
        }
    }

    private C1038a() {
        this.f2322d = null;
        this.f2323e = false;
        this.f2320a = false;
        this.f2321b = false;
        this.f2324f = null;
        this.f2325g = null;
        this.f2322d = new ArrayList();
    }

    /* renamed from: a */
    private C1037a m3644a(Messenger messenger) {
        if (this.f2322d == null) {
            return null;
        }
        Iterator it = this.f2322d.iterator();
        while (it.hasNext()) {
            C1037a c1037a = (C1037a) it.next();
            if (c1037a.f2315b.equals(messenger)) {
                return c1037a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C1038a m3645a() {
        if (f2319c == null) {
            f2319c = new C1038a();
        }
        return f2319c;
    }

    /* renamed from: a */
    private void m3646a(C1037a c1037a) {
        if (c1037a != null) {
            if (m3644a(c1037a.f2315b) != null) {
                c1037a.m3636a(14);
                return;
            }
            this.f2322d.add(c1037a);
            c1037a.m3636a(13);
        }
    }

    /* renamed from: e */
    private void m3647e() {
        m3648f();
        m3657d();
    }

    /* renamed from: f */
    private void m3648f() {
        Iterator it = this.f2322d.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            C1037a c1037a = (C1037a) it.next();
            if (c1037a.f2316c.openGps) {
                z2 = true;
            }
            z = c1037a.f2316c.location_change_notify ? true : z;
        }
        C1100j.f2730a = z;
        if (this.f2323e != z2) {
            this.f2323e = z2;
            C1079d.m3897a().m3929a(this.f2323e);
        }
    }

    /* renamed from: a */
    public void m3649a(Bundle bundle, int i) {
        Iterator it = this.f2322d.iterator();
        while (it.hasNext()) {
            try {
                C1037a c1037a = (C1037a) it.next();
                c1037a.m3637a(i, bundle);
                if (c1037a.f2317d > 4) {
                    it.remove();
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    /* renamed from: a */
    public void m3650a(Message message) {
        if (message != null && message.replyTo != null) {
            this.f2320a = true;
            m3646a(new C1037a(this, message));
            m3647e();
        }
    }

    /* renamed from: a */
    public void m3651a(BDLocation bDLocation) {
        boolean z = C1059j.f2458h;
        if (z) {
            C1059j.f2458h = false;
        }
        if (C1100j.f2724U >= 10000 && (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66)) {
            if (this.f2324f != null) {
                float[] fArr = new float[1];
                Location.distanceBetween(this.f2324f.getLatitude(), this.f2324f.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] > ((float) C1100j.f2726W) || z) {
                    this.f2324f = null;
                    this.f2324f = new BDLocation(bDLocation);
                } else {
                    return;
                }
            }
            this.f2324f = new BDLocation(bDLocation);
        }
        Iterator it;
        if (bDLocation.getLocType() != 161 || C1055i.m3757a().m3761b()) {
            it = this.f2322d.iterator();
            while (it.hasNext()) {
                try {
                    C1037a c1037a;
                    c1037a = (C1037a) it.next();
                    c1037a.m3642a(bDLocation);
                    if (c1037a.f2317d > 4) {
                        it.remove();
                    }
                } catch (Exception e) {
                    return;
                }
            }
            return;
        }
        if (this.f2325g == null) {
            this.f2325g = new BDLocation();
            this.f2325g.setLocType(505);
        }
        it = this.f2322d.iterator();
        while (it.hasNext()) {
            try {
                c1037a = (C1037a) it.next();
                c1037a.m3642a(this.f2325g);
                if (c1037a.f2317d > 4) {
                    it.remove();
                }
            } catch (Exception e2) {
                return;
            }
        }
    }

    /* renamed from: b */
    public void m3652b() {
        this.f2322d.clear();
        this.f2324f = null;
        m3647e();
    }

    /* renamed from: b */
    public void m3653b(Message message) {
        C1037a a = m3644a(message.replyTo);
        if (a != null) {
            this.f2322d.remove(a);
        }
        C1060k.m3789a().m3797c();
        m3647e();
    }

    /* renamed from: c */
    public String m3654c() {
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.f2322d.isEmpty()) {
            return "&prod=" + C1091b.f2669e + ":" + C1091b.f2668d;
        }
        C1037a c1037a = (C1037a) this.f2322d.get(0);
        if (c1037a.f2316c.prodName != null) {
            stringBuffer.append(c1037a.f2316c.prodName);
        }
        if (c1037a.f2314a != null) {
            stringBuffer.append(":");
            stringBuffer.append(c1037a.f2314a);
            stringBuffer.append("|");
        }
        String stringBuffer2 = stringBuffer.toString();
        return (stringBuffer2 == null || stringBuffer2.equals("")) ? null : "&prod=" + stringBuffer2;
    }

    /* renamed from: c */
    public boolean m3655c(Message message) {
        boolean z = true;
        C1037a a = m3644a(message.replyTo);
        if (a == null) {
            return false;
        }
        int i = a.f2316c.scanSpan;
        a.f2316c.scanSpan = message.getData().getInt("scanSpan", a.f2316c.scanSpan);
        if (a.f2316c.scanSpan < 1000) {
            C1060k.m3789a().m3797c();
            this.f2320a = false;
        } else {
            this.f2320a = true;
        }
        if (a.f2316c.scanSpan <= AVException.UNKNOWN || i >= 1000) {
            z = false;
        } else {
            if (a.f2316c.mIsNeedDeviceDirect || a.f2316c.isNeedAltitude) {
                C1060k.m3789a().m3794a(a.f2316c.mIsNeedDeviceDirect);
                C1060k.m3789a().m3796b(a.f2316c.isNeedAltitude);
                C1060k.m3789a().m3795b();
            }
            this.f2321b |= a.f2316c.isNeedAltitude;
        }
        a.f2316c.openGps = message.getData().getBoolean("openGPS", a.f2316c.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = a.f2316c;
        if (string == null || string.equals("")) {
            string = a.f2316c.coorType;
        }
        locationClientOption.coorType = string;
        string = message.getData().getString("addrType");
        locationClientOption = a.f2316c;
        if (string == null || string.equals("")) {
            string = a.f2316c.addrType;
        }
        locationClientOption.addrType = string;
        if (!C1100j.f2736g.equals(a.f2316c.addrType)) {
            C1059j.m3769c().m3787g();
        }
        a.f2316c.timeOut = message.getData().getInt("timeOut", a.f2316c.timeOut);
        a.f2316c.location_change_notify = message.getData().getBoolean("location_change_notify", a.f2316c.location_change_notify);
        a.f2316c.priority = message.getData().getInt("priority", a.f2316c.priority);
        m3647e();
        return z;
    }

    /* renamed from: d */
    public int m3656d(Message message) {
        if (message == null || message.replyTo == null) {
            return 1;
        }
        C1037a a = m3644a(message.replyTo);
        return (a == null || a.f2316c == null) ? 1 : a.f2316c.priority;
    }

    /* renamed from: d */
    public void m3657d() {
        Iterator it = this.f2322d.iterator();
        while (it.hasNext()) {
            ((C1037a) it.next()).m3641a();
        }
    }

    /* renamed from: e */
    public int m3658e(Message message) {
        if (message == null || message.replyTo == null) {
            return 1000;
        }
        C1037a a = m3644a(message.replyTo);
        return (a == null || a.f2316c == null) ? 1000 : a.f2316c.scanSpan;
    }
}
