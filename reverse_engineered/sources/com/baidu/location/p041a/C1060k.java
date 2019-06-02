package com.baidu.location.p041a;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.baidu.location.C1102f;
import com.baidu.location.p044c.C1088a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.baidu.location.a.k */
public class C1060k implements SensorEventListener {
    /* renamed from: d */
    private static C1060k f2491d;
    /* renamed from: a */
    private float[] f2492a;
    /* renamed from: b */
    private float[] f2493b;
    /* renamed from: c */
    private SensorManager f2494c;
    /* renamed from: e */
    private float f2495e;
    /* renamed from: f */
    private double f2496f = Double.MIN_VALUE;
    /* renamed from: g */
    private boolean f2497g = false;
    /* renamed from: h */
    private boolean f2498h = false;
    /* renamed from: i */
    private boolean f2499i = false;
    /* renamed from: j */
    private boolean f2500j = false;
    /* renamed from: k */
    private float f2501k = 0.0f;
    /* renamed from: l */
    private long f2502l = 0;
    /* renamed from: m */
    private Map<Integer, List<Float>> f2503m = Collections.synchronizedMap(new HashMap());
    /* renamed from: n */
    private boolean f2504n = false;
    /* renamed from: o */
    private long f2505o = 0;
    /* renamed from: p */
    private boolean f2506p = false;

    private C1060k() {
        try {
            if (this.f2494c == null) {
                this.f2494c = (SensorManager) C1102f.getServiceContext().getSystemService("sensor");
            }
            if (this.f2494c.getDefaultSensor(6) != null) {
                this.f2500j = true;
            }
        } catch (Exception e) {
            this.f2500j = false;
        }
    }

    /* renamed from: a */
    public static synchronized C1060k m3789a() {
        C1060k c1060k;
        synchronized (C1060k.class) {
            if (f2491d == null) {
                f2491d = new C1060k();
            }
            c1060k = f2491d;
        }
        return c1060k;
    }

    /* renamed from: l */
    private void m3793l() {
        if (this.f2494c != null) {
            Sensor defaultSensor = this.f2494c.getDefaultSensor(6);
            if (!(defaultSensor == null || this.f2506p)) {
                this.f2494c.registerListener(f2491d, defaultSensor, 3);
                this.f2506p = true;
            }
            if (!this.f2498h) {
                C1088a.m3978a().postDelayed(new C1061l(this), 2000);
            }
        }
    }

    /* renamed from: a */
    public void m3794a(boolean z) {
        this.f2497g = z;
    }

    /* renamed from: b */
    public synchronized void m3795b() {
        if (!this.f2504n) {
            if (this.f2497g || this.f2499i) {
                if (this.f2494c == null) {
                    this.f2494c = (SensorManager) C1102f.getServiceContext().getSystemService("sensor");
                }
                if (this.f2494c != null) {
                    Sensor defaultSensor = this.f2494c.getDefaultSensor(11);
                    if (defaultSensor != null && this.f2497g) {
                        this.f2494c.registerListener(this, defaultSensor, 3);
                    }
                    defaultSensor = this.f2494c.getDefaultSensor(6);
                    if (defaultSensor != null && this.f2499i) {
                        this.f2494c.registerListener(this, defaultSensor, 3);
                        this.f2506p = true;
                    }
                }
                this.f2504n = true;
            }
        }
    }

    /* renamed from: b */
    public void m3796b(boolean z) {
    }

    /* renamed from: c */
    public synchronized void m3797c() {
        if (this.f2504n) {
            if (this.f2494c != null) {
                this.f2494c.unregisterListener(this);
                this.f2494c = null;
                this.f2506p = false;
            }
            this.f2504n = false;
            this.f2501k = 0.0f;
            this.f2503m.clear();
        }
    }

    /* renamed from: d */
    public void m3798d() {
        if (!this.f2499i && this.f2500j) {
            if (this.f2498h || System.currentTimeMillis() - this.f2505o > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
                this.f2505o = System.currentTimeMillis();
                m3793l();
            }
        }
    }

    /* renamed from: e */
    public float m3799e() {
        return (!this.f2500j || this.f2502l <= 0) ? 0.0f : this.f2498h ? m3800f() : this.f2501k > 0.0f ? this.f2501k : 0.0f;
    }

    /* renamed from: f */
    public float m3800f() {
        float f = 0.0f;
        if (this.f2500j && this.f2502l > 0 && this.f2503m.size() > 0) {
            int i = 0;
            for (Integer num : this.f2503m.keySet()) {
                i = num.intValue() > i ? num.intValue() : i;
            }
            if (this.f2503m.get(Integer.valueOf(i)) != null) {
                List<Float> list = (List) this.f2503m.get(Integer.valueOf(i));
                for (Float floatValue : list) {
                    f = floatValue.floatValue() + f;
                }
                f /= (float) list.size();
            }
        }
        this.f2503m.clear();
        return f;
    }

    /* renamed from: g */
    public boolean m3801g() {
        return this.f2497g;
    }

    /* renamed from: h */
    public boolean m3802h() {
        return this.f2499i;
    }

    /* renamed from: i */
    public float m3803i() {
        return this.f2495e;
    }

    /* renamed from: j */
    public double m3804j() {
        return this.f2496f;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @SuppressLint({"NewApi"})
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 6:
                try {
                    this.f2493b = (float[]) sensorEvent.values.clone();
                    this.f2501k = this.f2493b[0];
                    this.f2502l = System.currentTimeMillis();
                    if (this.f2498h) {
                        int i = (int) (this.f2502l / 1000);
                        if (this.f2503m.get(Integer.valueOf(i)) == null) {
                            this.f2503m.put(Integer.valueOf(i), new ArrayList());
                        }
                        ((List) this.f2503m.get(Integer.valueOf(i))).add(Float.valueOf(this.f2501k));
                    }
                    this.f2496f = (double) SensorManager.getAltitude(1013.25f, this.f2493b[0]);
                    return;
                } catch (Exception e) {
                    return;
                }
            case 11:
                this.f2492a = (float[]) sensorEvent.values.clone();
                if (this.f2492a != null) {
                    float[] fArr = new float[9];
                    try {
                        SensorManager.getRotationMatrixFromVector(fArr, this.f2492a);
                        float[] fArr2 = new float[3];
                        SensorManager.getOrientation(fArr, fArr2);
                        this.f2495e = (float) Math.toDegrees((double) fArr2[0]);
                        this.f2495e = (float) Math.floor(this.f2495e >= 0.0f ? (double) this.f2495e : (double) (this.f2495e + 360.0f));
                        return;
                    } catch (Exception e2) {
                        this.f2495e = 0.0f;
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
