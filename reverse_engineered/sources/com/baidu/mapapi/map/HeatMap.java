package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.SparseIntArray;
import com.baidu.mapapi.map.C1127l.C1112a;
import com.baidu.mapapi.model.LatLng;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class HeatMap {
    public static final Gradient DEFAULT_GRADIENT = new Gradient(f2938d, f2939e);
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    /* renamed from: b */
    private static final String f2936b = HeatMap.class.getSimpleName();
    /* renamed from: c */
    private static final SparseIntArray f2937c = new SparseIntArray();
    /* renamed from: d */
    private static final int[] f2938d = new int[]{Color.rgb(0, 0, 200), Color.rgb(0, 225, 0), Color.rgb(255, 0, 0)};
    /* renamed from: e */
    private static final float[] f2939e = new float[]{0.08f, 0.4f, 1.0f};
    /* renamed from: r */
    private static int f2940r = 0;
    /* renamed from: a */
    BaiduMap f2941a;
    /* renamed from: f */
    private C1127l<WeightedLatLng> f2942f;
    /* renamed from: g */
    private Collection<WeightedLatLng> f2943g;
    /* renamed from: h */
    private int f2944h;
    /* renamed from: i */
    private Gradient f2945i;
    /* renamed from: j */
    private double f2946j;
    /* renamed from: k */
    private C1121f f2947k;
    /* renamed from: l */
    private int[] f2948l;
    /* renamed from: m */
    private double[] f2949m;
    /* renamed from: n */
    private double[] f2950n;
    /* renamed from: o */
    private HashMap<String, Tile> f2951o;
    /* renamed from: p */
    private ExecutorService f2952p;
    /* renamed from: q */
    private HashSet<String> f2953q;

    public static class Builder {
        /* renamed from: a */
        private Collection<WeightedLatLng> f2932a;
        /* renamed from: b */
        private int f2933b = 12;
        /* renamed from: c */
        private Gradient f2934c = HeatMap.DEFAULT_GRADIENT;
        /* renamed from: d */
        private double f2935d = 0.6d;

        public HeatMap build() {
            if (this.f2932a != null) {
                return new HeatMap();
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }

        public Builder data(Collection<LatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            } else if (!collection.contains(null)) {
                return weightedData(HeatMap.m4125c((Collection) collection));
            } else {
                throw new IllegalArgumentException("input points can not contain null.");
            }
        }

        public Builder gradient(Gradient gradient) {
            if (gradient == null) {
                throw new IllegalArgumentException("gradient can not be null");
            }
            this.f2934c = gradient;
            return this;
        }

        public Builder opacity(double d) {
            this.f2935d = d;
            if (this.f2935d >= 0.0d && this.f2935d <= 1.0d) {
                return this;
            }
            throw new IllegalArgumentException("Opacity must be in range [0, 1]");
        }

        public Builder radius(int i) {
            this.f2933b = i;
            if (this.f2933b >= 10 && this.f2933b <= 50) {
                return this;
            }
            throw new IllegalArgumentException("Radius not within bounds.");
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            } else if (collection.contains(null)) {
                throw new IllegalArgumentException("input points can not contain null.");
            } else {
                Collection arrayList = new ArrayList();
                for (WeightedLatLng weightedLatLng : collection) {
                    LatLng latLng = weightedLatLng.latLng;
                    if (latLng.latitude < 0.37532d || latLng.latitude > 54.562495d || latLng.longitude < 72.508319d || latLng.longitude > 135.942198d) {
                        arrayList.add(weightedLatLng);
                    }
                }
                collection.removeAll(arrayList);
                this.f2932a = collection;
                return this;
            }
        }
    }

    static {
        f2937c.put(3, 8388608);
        f2937c.put(4, AccessibilityEventCompat.TYPE_WINDOWS_CHANGED);
        f2937c.put(5, 2097152);
        f2937c.put(6, 1048576);
        f2937c.put(7, 524288);
        f2937c.put(8, 262144);
        f2937c.put(9, 131072);
        f2937c.put(10, 65536);
        f2937c.put(11, 32768);
        f2937c.put(12, 16384);
        f2937c.put(13, 8192);
        f2937c.put(14, 4096);
        f2937c.put(15, 2048);
        f2937c.put(16, 1024);
        f2937c.put(17, 512);
        f2937c.put(18, 256);
        f2937c.put(19, 128);
        f2937c.put(20, 64);
    }

    private HeatMap(Builder builder) {
        this.f2951o = new HashMap();
        this.f2952p = Executors.newFixedThreadPool(1);
        this.f2953q = new HashSet();
        this.f2943g = builder.f2932a;
        this.f2944h = builder.f2933b;
        this.f2945i = builder.f2934c;
        this.f2946j = builder.f2935d;
        this.f2949m = m4119a(this.f2944h, ((double) this.f2944h) / 3.0d);
        m4114a(this.f2945i);
        m4123b(this.f2943g);
    }

    /* renamed from: a */
    private static double m4110a(Collection<WeightedLatLng> collection, C1121f c1121f, int i, int i2) {
        double d = c1121f.f3243a;
        double d2 = c1121f.f3245c;
        double d3 = c1121f.f3244b;
        double d4 = c1121f.f3246d;
        double d5 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / (d2 - d > d4 - d3 ? d2 - d : d4 - d3);
        LongSparseArray longSparseArray = new LongSparseArray();
        d4 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            LongSparseArray longSparseArray2;
            int i3 = (int) ((((double) weightedLatLng.mo2622a().x) - d) * d5);
            int i4 = (int) ((((double) weightedLatLng.mo2622a().y) - d3) * d5);
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray.get((long) i3);
            if (longSparseArray3 == null) {
                longSparseArray3 = new LongSparseArray();
                longSparseArray.put((long) i3, longSparseArray3);
                longSparseArray2 = longSparseArray3;
            } else {
                longSparseArray2 = longSparseArray3;
            }
            Double d6 = (Double) longSparseArray2.get((long) i4);
            if (d6 == null) {
                d6 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(weightedLatLng.intensity + d6.doubleValue());
            longSparseArray2.put((long) i4, valueOf);
            d4 = valueOf.doubleValue() > d4 ? valueOf.doubleValue() : d4;
        }
        return d4;
    }

    /* renamed from: a */
    private static Bitmap m4111a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[(length2 * length2)];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    /* renamed from: a */
    private static Tile m4112a(Bitmap bitmap) {
        Buffer allocate = ByteBuffer.allocate((bitmap.getWidth() * bitmap.getHeight()) * 4);
        bitmap.copyPixelsToBuffer(allocate);
        return new Tile(256, 256, allocate.array());
    }

    /* renamed from: a */
    private void m4114a(Gradient gradient) {
        this.f2945i = gradient;
        this.f2948l = gradient.m4103a(this.f2946j);
    }

    /* renamed from: a */
    private synchronized void m4116a(String str, Tile tile) {
        this.f2951o.put(str, tile);
    }

    /* renamed from: a */
    private synchronized boolean m4117a(String str) {
        return this.f2953q.contains(str);
    }

    /* renamed from: a */
    private double[] m4118a(int i) {
        int i2 = 11;
        double[] dArr = new double[20];
        for (int i3 = 5; i3 < 11; i3++) {
            dArr[i3] = m4110a(this.f2943g, this.f2947k, i, (int) (1280.0d * Math.pow(2.0d, (double) (i3 - 3))));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
        }
        while (i2 < 20) {
            dArr[i2] = dArr[10];
            i2++;
        }
        return dArr;
    }

    /* renamed from: a */
    private static double[] m4119a(int i, double d) {
        double[] dArr = new double[((i * 2) + 1)];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    /* renamed from: a */
    private static double[][] m4120a(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i = length - (floor * 2);
        int i2 = (floor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length});
        int i3 = 0;
        while (i3 < length) {
            int i4;
            for (i4 = 0; i4 < length; i4++) {
                int i5;
                double d = dArr[i3][i4];
                if (d != 0.0d) {
                    i5 = (i2 < i3 + floor ? i2 : i3 + floor) + 1;
                    int i6 = floor > i3 - floor ? floor : i3 - floor;
                    while (i6 < i5) {
                        double[] dArr4 = dArr3[i6];
                        dArr4[i4] = dArr4[i4] + (dArr2[i6 - (i3 - floor)] * d);
                        i6++;
                    }
                }
            }
            i3++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{i, i});
        for (i = floor; i < i2 + 1; i++) {
            i3 = 0;
            while (i3 < length) {
                d = dArr3[i][i3];
                if (d != 0.0d) {
                    i5 = (i2 < i3 + floor ? i2 : i3 + floor) + 1;
                    i4 = floor > i3 - floor ? floor : i3 - floor;
                    while (i4 < i5) {
                        dArr4 = dArr5[i - floor];
                        int i7 = i4 - floor;
                        dArr4[i7] = dArr4[i7] + (dArr2[i4 - (i3 - floor)] * d);
                        i4++;
                    }
                }
                i3++;
            }
        }
        return dArr5;
    }

    /* renamed from: b */
    private void m4121b(int i, int i2, int i3) {
        double d = (double) f2937c.get(i3);
        double d2 = (((double) this.f2944h) * d) / 256.0d;
        double d3 = ((2.0d * d2) + d) / ((double) ((this.f2944h * 2) + 256));
        if (i >= 0 && i2 >= 0) {
            double d4 = (((double) i) * d) - d2;
            double d5 = (d * ((double) (i2 + 1))) + d2;
            C1121f c1121f = new C1121f(d4, (((double) (i + 1)) * d) + d2, (((double) i2) * d) - d2, d5);
            if (c1121f.m4286a(new C1121f(this.f2947k.f3243a - d2, this.f2947k.f3245c + d2, this.f2947k.f3244b - d2, d2 + this.f2947k.f3246d))) {
                Collection<WeightedLatLng> a = this.f2942f.m4313a(c1121f);
                if (!a.isEmpty()) {
                    double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{(this.f2944h * 2) + 256, (this.f2944h * 2) + 256});
                    for (WeightedLatLng weightedLatLng : a) {
                        Point a2 = weightedLatLng.mo2622a();
                        int i4 = (int) ((((double) a2.x) - d4) / d3);
                        int i5 = (int) ((d5 - ((double) a2.y)) / d3);
                        if (i4 >= (this.f2944h * 2) + 256) {
                            i4 = ((this.f2944h * 2) + 256) - 1;
                        }
                        if (i5 >= (this.f2944h * 2) + 256) {
                            i5 = ((this.f2944h * 2) + 256) - 1;
                        }
                        double[] dArr2 = dArr[i4];
                        dArr2[i5] = dArr2[i5] + weightedLatLng.intensity;
                    }
                    Bitmap a3 = m4111a(m4120a(dArr, this.f2949m), this.f2948l, this.f2950n[i3 - 1]);
                    Tile a4 = m4112a(a3);
                    a3.recycle();
                    m4116a(i + "_" + i2 + "_" + i3, a4);
                    if (this.f2951o.size() > f2940r) {
                        m4129a();
                    }
                    if (this.f2941a != null) {
                        this.f2941a.m4086a();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private synchronized void m4122b(String str) {
        this.f2953q.add(str);
    }

    /* renamed from: b */
    private void m4123b(Collection<WeightedLatLng> collection) {
        this.f2943g = collection;
        if (this.f2943g.isEmpty()) {
            throw new IllegalArgumentException("No input points.");
        }
        this.f2947k = m4126d(this.f2943g);
        this.f2942f = new C1127l(this.f2947k);
        for (C1112a a : this.f2943g) {
            this.f2942f.m4314a(a);
        }
        this.f2950n = m4118a(this.f2944h);
    }

    /* renamed from: c */
    private synchronized Tile m4124c(String str) {
        Tile tile;
        if (this.f2951o.containsKey(str)) {
            tile = (Tile) this.f2951o.get(str);
            this.f2951o.remove(str);
        } else {
            tile = null;
        }
        return tile;
    }

    /* renamed from: c */
    private static Collection<WeightedLatLng> m4125c(Collection<LatLng> collection) {
        Collection arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    /* renamed from: d */
    private static C1121f m4126d(Collection<WeightedLatLng> collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d = (double) weightedLatLng.mo2622a().x;
        double d2 = (double) weightedLatLng.mo2622a().x;
        double d3 = (double) weightedLatLng.mo2622a().y;
        double d4 = (double) weightedLatLng.mo2622a().y;
        while (it.hasNext()) {
            weightedLatLng = (WeightedLatLng) it.next();
            double d5 = (double) weightedLatLng.mo2622a().x;
            double d6 = (double) weightedLatLng.mo2622a().y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new C1121f(d, d2, d3, d4);
    }

    /* renamed from: d */
    private synchronized void m4127d() {
        this.f2951o.clear();
    }

    /* renamed from: a */
    Tile m4128a(int i, int i2, int i3) {
        String str = i + "_" + i2 + "_" + i3;
        Tile c = m4124c(str);
        if (c != null) {
            return c;
        }
        if (!m4117a(str)) {
            if (this.f2941a != null && f2940r == 0) {
                MapStatus mapStatus = this.f2941a.getMapStatus();
                f2940r = ((((mapStatus.f2981a.f3687j.bottom - mapStatus.f2981a.f3687j.top) / 256) + 2) * (((mapStatus.f2981a.f3687j.right - mapStatus.f2981a.f3687j.left) / 256) + 2)) * 4;
            }
            if (this.f2951o.size() > f2940r) {
                m4129a();
            }
            if (!this.f2952p.isShutdown()) {
                try {
                    this.f2952p.execute(new C1122g(this, i, i2, i3));
                    m4122b(str);
                } catch (RejectedExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    synchronized void m4129a() {
        this.f2953q.clear();
        this.f2951o.clear();
    }

    /* renamed from: b */
    void m4130b() {
        m4127d();
    }

    /* renamed from: c */
    void m4131c() {
        this.f2952p.shutdownNow();
    }

    public void removeHeatMap() {
        if (this.f2941a != null) {
            this.f2941a.m4087a(this);
        }
    }
}
