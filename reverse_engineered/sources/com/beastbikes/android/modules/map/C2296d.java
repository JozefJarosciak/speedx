package com.beastbikes.android.modules.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.beastbikes.android.modules.map.SpeedxMap.C1685b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MapBase */
/* renamed from: com.beastbikes.android.modules.map.d */
public abstract class C2296d<K, M> extends LinearLayout {
    /* renamed from: a */
    protected Activity f10866a;
    /* renamed from: b */
    protected boolean f10867b;
    /* renamed from: c */
    protected C2286e f10868c;
    /* renamed from: d */
    protected ScrollView f10869d;
    /* renamed from: e */
    protected float f10870e;
    /* renamed from: f */
    protected float f10871f;
    /* renamed from: g */
    protected float f10872g = 14.0f;
    /* renamed from: h */
    protected C1685b f10873h;
    /* renamed from: i */
    protected C1686a f10874i;
    /* renamed from: j */
    protected C2306c f10875j;
    /* renamed from: k */
    protected C2307d f10876k;
    /* renamed from: l */
    protected C1958b f10877l;
    /* renamed from: m */
    private ArrayList<Object> f10878m = new ArrayList();
    /* renamed from: n */
    private ArrayList<Object> f10879n = new ArrayList();

    /* compiled from: MapBase */
    /* renamed from: com.beastbikes.android.modules.map.d$a */
    public interface C1686a {
        /* renamed from: c */
        void mo3198c(double d, double d2);
    }

    /* compiled from: MapBase */
    /* renamed from: com.beastbikes.android.modules.map.d$e */
    public interface C1950e {
        /* renamed from: a */
        void mo3296a(Bitmap bitmap);
    }

    /* compiled from: MapBase */
    /* renamed from: com.beastbikes.android.modules.map.d$b */
    public interface C1958b {
        /* renamed from: i */
        void mo3327i();
    }

    /* compiled from: MapBase */
    /* renamed from: com.beastbikes.android.modules.map.d$c */
    public interface C2306c {
        /* renamed from: a */
        void m11802a(Object obj);
    }

    /* compiled from: MapBase */
    /* renamed from: com.beastbikes.android.modules.map.d$d */
    public interface C2307d {
        /* renamed from: b */
        void m11803b(Object obj);
    }

    /* renamed from: a */
    protected abstract Object mo3442a(K k, int i);

    /* renamed from: a */
    protected abstract Object mo3443a(K k, M m);

    /* renamed from: a */
    protected abstract String mo3444a(List<K> list);

    /* renamed from: a */
    protected abstract void mo3446a(double d, double d2);

    /* renamed from: a */
    protected abstract void mo3448a(int i, int i2);

    /* renamed from: a */
    protected abstract void mo3449a(Activity activity, C2286e c2286e, boolean z, ScrollView scrollView);

    /* renamed from: a */
    protected abstract void mo3450a(C1950e c1950e);

    /* renamed from: b */
    protected abstract Object mo3451b(Point point);

    /* renamed from: b */
    protected abstract void mo3453b(K k, K k2);

    /* renamed from: b */
    protected abstract void mo3454b(List<K> list, int i, int i2);

    /* renamed from: c */
    protected abstract Object mo3455c(List<K> list, int i, int i2);

    /* renamed from: c */
    protected abstract void mo3457c(List<K> list);

    /* renamed from: d */
    protected abstract Object mo3458d(List<K> list);

    /* renamed from: d */
    protected abstract void mo3459d();

    /* renamed from: e */
    public abstract void mo3460e();

    /* renamed from: f */
    public abstract void mo3461f();

    /* renamed from: g */
    public abstract void mo3462g();

    /* renamed from: i */
    protected abstract void mo3464i();

    public C2296d(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m11694a(Object obj) {
        if (obj != null) {
            this.f10878m.add(obj);
        }
    }

    /* renamed from: b */
    public void m11699b(Object obj) {
        if (obj != null) {
            this.f10879n.add(obj);
        }
    }

    public ArrayList<Object> getAllPolylines() {
        return this.f10878m;
    }

    public ArrayList<Object> getAllMarkers() {
        return this.f10879n;
    }

    public void setOnLocationChangeListener(C1686a c1686a) {
        this.f10874i = c1686a;
    }

    /* renamed from: a */
    public void mo3447a(float f) {
        this.f10872g = f;
    }

    public float getZoomLevel() {
        return this.f10872g;
    }

    public float getMaxZoomLevel() {
        return this.f10870e;
    }

    public float getMinZoomLevel() {
        return this.f10871f;
    }

    public void setOnMapStatusChangeListener(C1685b c1685b) {
        this.f10873h = c1685b;
    }

    public void setOnMapChangedFinishedListener(C1958b c1958b) {
        this.f10877l = c1958b;
    }

    public void setOnMarkClickListener(C2306c c2306c) {
        this.f10875j = c2306c;
    }

    public void setOnPolylineClickListener(C2307d c2307d) {
        this.f10876k = c2307d;
    }

    /* renamed from: a */
    protected void mo3445a() {
    }

    /* renamed from: b */
    protected void mo3452b() {
    }

    /* renamed from: c */
    public void mo3456c() {
    }

    /* renamed from: j */
    public void mo3475j() {
    }

    /* renamed from: a */
    protected void mo3473a(Bundle bundle) {
    }

    /* renamed from: b */
    public void mo3474b(Bundle bundle) {
    }

    /* renamed from: h */
    public void mo3463h() {
    }

    public void setMapStyle(boolean z) {
    }

    /* renamed from: b */
    protected void m11697b(Activity activity, C2286e c2286e, boolean z, ScrollView scrollView) {
        this.f10866a = activity;
        this.f10868c = c2286e;
        this.f10867b = z;
        this.f10869d = scrollView;
        mo3459d();
    }

    protected DisplayMetrics getDm() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f10866a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
