package com.baidu.platform.comapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.WinRound;
import com.baidu.mapapi.model.inner.Point;
import com.mapbox.mapboxsdk.style.layers.Property;

/* renamed from: com.baidu.platform.comapi.map.E */
public class C1235E {
    /* renamed from: t */
    private static final String f3677t = C1235E.class.getSimpleName();
    /* renamed from: a */
    public float f3678a = 12.0f;
    /* renamed from: b */
    public int f3679b = 0;
    /* renamed from: c */
    public int f3680c = 0;
    /* renamed from: d */
    public double f3681d = 1.2958162E7d;
    /* renamed from: e */
    public double f3682e = 4825907.0d;
    /* renamed from: f */
    public int f3683f = -1;
    /* renamed from: g */
    public int f3684g = -1;
    /* renamed from: h */
    public long f3685h = 0;
    /* renamed from: i */
    public long f3686i = 0;
    /* renamed from: j */
    public WinRound f3687j = new WinRound();
    /* renamed from: k */
    public C1234a f3688k = new C1234a(this);
    /* renamed from: l */
    public boolean f3689l = false;
    /* renamed from: m */
    public double f3690m;
    /* renamed from: n */
    public double f3691n;
    /* renamed from: o */
    public int f3692o;
    /* renamed from: p */
    public String f3693p;
    /* renamed from: q */
    public float f3694q;
    /* renamed from: r */
    public boolean f3695r;
    /* renamed from: s */
    public int f3696s;

    /* renamed from: com.baidu.platform.comapi.map.E$a */
    public class C1234a {
        /* renamed from: a */
        public long f3668a = 0;
        /* renamed from: b */
        public long f3669b = 0;
        /* renamed from: c */
        public long f3670c = 0;
        /* renamed from: d */
        public long f3671d = 0;
        /* renamed from: e */
        public Point f3672e = new Point(0, 0);
        /* renamed from: f */
        public Point f3673f = new Point(0, 0);
        /* renamed from: g */
        public Point f3674g = new Point(0, 0);
        /* renamed from: h */
        public Point f3675h = new Point(0, 0);
        /* renamed from: i */
        final /* synthetic */ C1235E f3676i;

        public C1234a(C1235E c1235e) {
            this.f3676i = c1235e;
        }
    }

    /* renamed from: a */
    public Bundle m4616a(C1249e c1249e) {
        int i = 1;
        if (this.f3678a < c1249e.f3755b) {
            this.f3678a = c1249e.f3755b;
        }
        if (this.f3678a > c1249e.f3754a) {
            this.f3678a = c1249e.f3754a;
        }
        while (this.f3679b < 0) {
            this.f3679b += 360;
        }
        this.f3679b %= 360;
        if (this.f3680c > 0) {
            this.f3680c = 0;
        }
        if (this.f3680c < -45) {
            this.f3680c = -45;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("level", (double) this.f3678a);
        bundle.putDouble("rotation", (double) this.f3679b);
        bundle.putDouble("overlooking", (double) this.f3680c);
        bundle.putDouble("centerptx", this.f3681d);
        bundle.putDouble("centerpty", this.f3682e);
        bundle.putInt("left", this.f3687j.left);
        bundle.putInt("right", this.f3687j.right);
        bundle.putInt(Property.TEXT_ANCHOR_TOP, this.f3687j.top);
        bundle.putInt(Property.TEXT_ANCHOR_BOTTOM, this.f3687j.bottom);
        if (this.f3683f >= 0 && this.f3684g >= 0 && this.f3683f <= this.f3687j.right && this.f3684g <= this.f3687j.bottom && this.f3687j.right > 0 && this.f3687j.bottom > 0) {
            int i2 = this.f3684g - ((this.f3687j.bottom - this.f3687j.top) / 2);
            this.f3685h = (long) (this.f3683f - ((this.f3687j.right - this.f3687j.left) / 2));
            this.f3686i = (long) (-i2);
            bundle.putLong("xoffset", this.f3685h);
            bundle.putLong("yoffset", this.f3686i);
        }
        bundle.putInt("lbx", this.f3688k.f3672e.f3293x);
        bundle.putInt("lby", this.f3688k.f3672e.f3294y);
        bundle.putInt("ltx", this.f3688k.f3673f.f3293x);
        bundle.putInt("lty", this.f3688k.f3673f.f3294y);
        bundle.putInt("rtx", this.f3688k.f3674g.f3293x);
        bundle.putInt("rty", this.f3688k.f3674g.f3294y);
        bundle.putInt("rbx", this.f3688k.f3675h.f3293x);
        bundle.putInt("rby", this.f3688k.f3675h.f3294y);
        bundle.putInt("bfpp", this.f3689l ? 1 : 0);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", this.f3692o);
        bundle.putString("panoid", this.f3693p);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", this.f3694q);
        String str = "isbirdeye";
        if (!this.f3695r) {
            i = 0;
        }
        bundle.putInt(str, i);
        bundle.putInt("ssext", this.f3696s);
        return bundle;
    }

    /* renamed from: a */
    public void m4617a(Bundle bundle) {
        boolean z = true;
        this.f3678a = (float) bundle.getDouble("level");
        this.f3679b = (int) bundle.getDouble("rotation");
        this.f3680c = (int) bundle.getDouble("overlooking");
        this.f3681d = bundle.getDouble("centerptx");
        this.f3682e = bundle.getDouble("centerpty");
        this.f3687j.left = bundle.getInt("left");
        this.f3687j.right = bundle.getInt("right");
        this.f3687j.top = bundle.getInt(Property.TEXT_ANCHOR_TOP);
        this.f3687j.bottom = bundle.getInt(Property.TEXT_ANCHOR_BOTTOM);
        this.f3685h = bundle.getLong("xoffset");
        this.f3686i = bundle.getLong("yoffset");
        if (!(this.f3687j.right == 0 || this.f3687j.bottom == 0)) {
            int i = (this.f3687j.bottom - this.f3687j.top) / 2;
            int i2 = (int) (-this.f3686i);
            this.f3683f = ((this.f3687j.right - this.f3687j.left) / 2) + ((int) this.f3685h);
            this.f3684g = i2 + i;
        }
        this.f3688k.f3668a = bundle.getLong("gleft");
        this.f3688k.f3669b = bundle.getLong("gright");
        this.f3688k.f3670c = bundle.getLong("gtop");
        this.f3688k.f3671d = bundle.getLong("gbottom");
        if (this.f3688k.f3668a <= -20037508) {
            this.f3688k.f3668a = -20037508;
        }
        if (this.f3688k.f3669b >= 20037508) {
            this.f3688k.f3669b = 20037508;
        }
        if (this.f3688k.f3670c >= 20037508) {
            this.f3688k.f3670c = 20037508;
        }
        if (this.f3688k.f3671d <= -20037508) {
            this.f3688k.f3671d = -20037508;
        }
        this.f3688k.f3672e.f3293x = bundle.getInt("lbx");
        this.f3688k.f3672e.f3294y = bundle.getInt("lby");
        this.f3688k.f3673f.f3293x = bundle.getInt("ltx");
        this.f3688k.f3673f.f3294y = bundle.getInt("lty");
        this.f3688k.f3674g.f3293x = bundle.getInt("rtx");
        this.f3688k.f3674g.f3294y = bundle.getInt("rty");
        this.f3688k.f3675h.f3293x = bundle.getInt("rbx");
        this.f3688k.f3675h.f3294y = bundle.getInt("rby");
        this.f3689l = bundle.getInt("bfpp") == 1;
        this.f3690m = bundle.getDouble("adapterzoomunit");
        this.f3691n = bundle.getDouble("zoomunit");
        this.f3693p = bundle.getString("panoid");
        this.f3694q = bundle.getFloat("siangle");
        if (bundle.getInt("isbirdeye") == 0) {
            z = false;
        }
        this.f3695r = z;
        this.f3696s = bundle.getInt("ssext");
    }
}
