package com.beastbikes.android.modules.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.utils.C2553b;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.geometry.LatLngBounds.Builder;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapView.OnMapChangedListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap.SnapshotReadyCallback;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: Map4MapBox */
/* renamed from: com.beastbikes.android.modules.map.c */
public class C2305c extends C2296d<LatLng, Icon> implements OnGestureListener, OnTouchListener, OnMapChangedListener, OnMapReadyCallback {
    /* renamed from: m */
    private RelativeLayout f10905m;
    /* renamed from: n */
    private MapView f10906n;
    /* renamed from: o */
    private MapboxMap f10907o;
    /* renamed from: p */
    private IconFactory f10908p;
    /* renamed from: q */
    private boolean f10909q = false;
    /* renamed from: r */
    private boolean f10910r = false;
    /* renamed from: s */
    private final String f10911s = "mapbox://styles/speedx/cilixxfb3000jazkr6zt4qcgn";
    /* renamed from: t */
    private GestureDetector f10912t;
    /* renamed from: u */
    private boolean f10913u;
    /* renamed from: v */
    private ImageView f10914v;

    /* compiled from: Map4MapBox */
    /* renamed from: com.beastbikes.android.modules.map.c$1 */
    class C23031 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2305c f10902a;

        C23031(C2305c c2305c) {
            this.f10902a = c2305c;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f10902a.d != null) {
                if (motionEvent.getAction() == 1) {
                    this.f10902a.d.requestDisallowInterceptTouchEvent(false);
                } else {
                    this.f10902a.d.requestDisallowInterceptTouchEvent(true);
                }
            }
            return false;
        }
    }

    /* renamed from: b */
    protected /* synthetic */ Object mo3451b(Point point) {
        return m11771a(point);
    }

    /* renamed from: b */
    public /* synthetic */ void mo3453b(Object obj, Object obj2) {
        m11784a((LatLng) obj, (LatLng) obj2);
    }

    /* renamed from: c */
    public /* synthetic */ Object mo3455c(List list, int i, int i2) {
        return m11770a(list, i, i2);
    }

    /* renamed from: d */
    public /* synthetic */ Object mo3458d(List list) {
        return m11785b(list);
    }

    public C2305c(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C1373R.layout.speedx_map_with_mapbox, this);
    }

    /* renamed from: a */
    protected void mo3445a() {
        super.mo3445a();
        this.f10906n.onResume();
        this.f10910r = true;
    }

    /* renamed from: b */
    protected void mo3452b() {
        super.mo3452b();
        if (this.f10910r) {
            this.f10906n.onPause();
            this.f10910r = false;
        }
    }

    /* renamed from: c */
    public void mo3456c() {
        super.mo3456c();
        this.f10906n.onDestroy();
    }

    /* renamed from: j */
    public void mo3475j() {
        super.mo3475j();
    }

    /* renamed from: a */
    protected void mo3473a(Bundle bundle) {
        super.mo3473a(bundle);
        if (this.f10906n != null) {
            this.f10906n.onSaveInstanceState(bundle);
        }
    }

    /* renamed from: d */
    protected void mo3459d() {
        this.f10905m = (RelativeLayout) findViewById(C1373R.id.mapbox_mapview_rl);
    }

    /* renamed from: i */
    protected void mo3464i() {
        if (this.f10907o != null) {
            this.f10907o.clear();
        }
    }

    public void setMapStyle(boolean z) {
        if (this.f10906n == null) {
            return;
        }
        if (z) {
            this.f10906n.setStyleUrl("mapbox://styles/speedx/cilixxfb3000jazkr6zt4qcgn");
            if (this.f10907o != null) {
                this.f10907o.getUiSettings().setAllGesturesEnabled(false);
                return;
            }
            return;
        }
        this.f10906n.setStyleUrl(Style.DARK);
    }

    /* renamed from: a */
    protected LatLng m11771a(Point point) {
        return this.f10907o.getProjection().fromScreenLocation(new PointF(point));
    }

    /* renamed from: a */
    protected String mo3444a(List<LatLng> list) {
        if (list == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LatLng latLng = (LatLng) it.next();
            stringBuilder.append(latLng.getLatitude()).append(",").append(latLng.getLongitude());
            if (it.hasNext()) {
                stringBuilder.append('|');
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: e */
    public void mo3460e() {
    }

    /* renamed from: f */
    public void mo3461f() {
    }

    /* renamed from: g */
    public void mo3462g() {
    }

    /* renamed from: a */
    public void mo3447a(float f) {
    }

    /* renamed from: b */
    public void mo3474b(Bundle bundle) {
        super.mo3474b(bundle);
        this.f10906n.onCreate(bundle);
    }

    /* renamed from: a */
    public void mo3449a(Activity activity, C2286e c2286e, boolean z, ScrollView scrollView) {
        m11697b(activity, c2286e, z, scrollView);
        MapboxAccountManager.start(activity.getApplicationContext(), BeastBikes.getMapBoxAccessToken());
        MapboxMapOptions mapboxMapOptions = new MapboxMapOptions();
        mapboxMapOptions.attributionEnabled(false);
        mapboxMapOptions.logoEnabled(false);
        mapboxMapOptions.zoomControlsEnabled(false);
        mapboxMapOptions.rotateGesturesEnabled(false);
        mapboxMapOptions.zoomGesturesEnabled(true);
        mapboxMapOptions.compassEnabled(false);
        mapboxMapOptions.scrollGesturesEnabled(true);
        mapboxMapOptions.styleUrl(Style.DARK);
        this.f10906n = new MapView(activity, mapboxMapOptions);
        this.f10908p = IconFactory.getInstance(activity);
        this.f10905m.addView(this.f10906n);
        this.f10906n.getMapAsync(this);
        this.f10912t = new GestureDetector(activity, this);
        this.f10906n.getChildAt(0).setOnTouchListener(new C23031(this));
    }

    /* renamed from: a */
    public void m11784a(LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && this.f10907o != null) {
            this.f10907o.clear();
            this.f10907o.addMarker((MarkerOptions) ((MarkerOptions) new MarkerOptions().icon(this.f10908p.fromResource(C1373R.drawable.ic_activity_detail_start))).position(latLng));
            this.f10907o.addMarker((MarkerOptions) ((MarkerOptions) new MarkerOptions().icon(this.f10908p.fromResource(C1373R.drawable.ic_activity_finish_end))).position(latLng2));
        }
    }

    /* renamed from: a */
    protected Object m11772a(LatLng latLng, int i) {
        if (latLng == null || this.f10907o == null) {
            return null;
        }
        Object addMarker = this.f10907o.addMarker((MarkerOptions) ((MarkerOptions) new MarkerOptions().icon(this.f10908p.fromResource(i))).position(latLng));
        m11699b(addMarker);
        return addMarker;
    }

    /* renamed from: a */
    protected Object m11773a(LatLng latLng, Icon icon) {
        if (latLng == null || this.f10907o == null || icon == null) {
            return null;
        }
        Object addMarker = this.f10907o.addMarker((MarkerOptions) ((MarkerOptions) new MarkerOptions().icon(icon)).position(latLng));
        m11699b(addMarker);
        return addMarker;
    }

    /* renamed from: a */
    public Polyline m11770a(List<LatLng> list, int i, int i2) {
        Polyline polyline = null;
        if (!(list == null || list.size() < 2 || this.f10907o == null || this.f10909q)) {
            int parseColor = this.b ? Color.parseColor("#dedede") : Color.parseColor("#ff102d");
            int size = list.size();
            if (size <= 5 || this.b) {
                polyline = this.f10907o.addPolyline(new PolylineOptions().addAll(list).color(parseColor).width(4.0f));
            } else {
                List a = C2553b.m12783a(size);
                Polyline polyline2 = null;
                int i3 = 5;
                while (i3 < list.size()) {
                    if (i3 < a.size()) {
                        size = ((Integer) a.get(i3)).intValue();
                    } else {
                        size = parseColor;
                    }
                    Iterable arrayList = new ArrayList();
                    for (parseColor = 0; parseColor <= 5; parseColor++) {
                        arrayList.add(list.get(i3 - parseColor));
                    }
                    Polyline addPolyline = this.f10907o.addPolyline(new PolylineOptions().addAll(arrayList).color(size).width(4.0f));
                    i3++;
                    parseColor = size;
                    polyline2 = addPolyline;
                }
                polyline = polyline2;
            }
            if (polyline != null) {
                m11694a((Object) polyline);
            }
        }
        return polyline;
    }

    /* renamed from: b */
    public Polyline m11785b(List<LatLng> list) {
        Polyline polyline = null;
        if (!(list == null || list.size() < 2 || this.f10907o == null || this.f10909q)) {
            int parseColor = this.b ? Color.parseColor("#dedede") : Color.parseColor("#ff102d");
            int size = list.size();
            if (size <= 5 || this.b) {
                polyline = this.f10907o.addPolyline(new PolylineOptions().addAll(list).color(parseColor).width(4.0f));
            } else {
                List a = C2553b.m12783a(size);
                Polyline polyline2 = null;
                int i = 5;
                while (i < list.size()) {
                    if (i < a.size()) {
                        size = ((Integer) a.get(i)).intValue();
                    } else {
                        size = parseColor;
                    }
                    Iterable arrayList = new ArrayList();
                    for (parseColor = 0; parseColor <= 5; parseColor++) {
                        arrayList.add(list.get(i - parseColor));
                    }
                    Polyline addPolyline = this.f10907o.addPolyline(new PolylineOptions().addAll(arrayList).color(size).width(4.0f));
                    i++;
                    parseColor = size;
                    polyline2 = addPolyline;
                }
                polyline = polyline2;
            }
            if (polyline != null) {
                m11694a((Object) polyline);
            }
        }
        return polyline;
    }

    /* renamed from: a */
    protected void mo3446a(double d, double d2) {
        this.f10907o.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(d, d2)));
    }

    /* renamed from: c */
    public void mo3457c(List<LatLng> list) {
        if (list != null && !list.isEmpty() && this.f10907o != null && !this.f10909q) {
            Builder builder = new Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include((LatLng) list.get(i));
            }
            LatLngBounds build = builder.build();
            DisplayMetrics dm = getDm();
            this.f10907o.animateCamera(CameraUpdateFactory.newLatLngBounds(build, 20, 100, 20, (dm.heightPixels - dm.widthPixels) + 20), 1000);
        }
    }

    /* renamed from: b */
    public void mo3454b(List<LatLng> list, int i, int i2) {
        if (list != null && !list.isEmpty() && this.f10907o != null && !this.f10909q) {
            Builder builder = new Builder();
            for (int i3 = 0; i3 < list.size(); i3++) {
                builder.include((LatLng) list.get(i3));
            }
            this.f10907o.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 20, 20, 20, (getDm().heightPixels - i2) + 20), 1000);
        }
    }

    /* renamed from: a */
    protected void mo3448a(int i, int i2) {
        this.f10907o.animateCamera(CameraUpdateFactory.scrollBy((float) i, (float) i2), 1000);
    }

    /* renamed from: a */
    public void mo3450a(final C1950e c1950e) {
        if (c1950e != null && this.f10907o != null) {
            this.f10907o.snapshot(new SnapshotReadyCallback(this) {
                /* renamed from: b */
                final /* synthetic */ C2305c f10904b;

                public void onSnapshotReady(Bitmap bitmap) {
                    c1950e.mo3296a(bitmap);
                }
            });
        }
    }

    /* renamed from: h */
    public void mo3463h() {
        super.mo3463h();
        DisplayMetrics dm = getDm();
        LayoutParams layoutParams = this.f10906n.getLayoutParams();
        layoutParams.width = dm.widthPixels;
        layoutParams.height = dm.heightPixels;
        this.f10906n.setLayoutParams(layoutParams);
    }

    public void onMapReady(MapboxMap mapboxMap) {
        if (mapboxMap != null && !this.f10909q) {
            this.f10907o = mapboxMap;
            if (this.c != null) {
                this.c.mo3440a();
            }
            this.e = (float) this.f10907o.getMaxZoom();
            this.f = (float) this.f10907o.getMinZoom();
            this.f10906n.addOnMapChangedListener(this);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.h == null) {
            return this.f10912t.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 && this.f10913u) {
            this.f10913u = false;
            LatLng fromScreenLocation = this.f10907o.getProjection().fromScreenLocation(new PointF((float) (this.f10914v.getLeft() + (this.f10914v.getWidth() / 2)), (float) this.f10914v.getBottom()));
            this.h.mo3204b(fromScreenLocation.getLatitude(), fromScreenLocation.getLongitude());
        }
        return this.f10912t.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f10913u = true;
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onMapChanged(int i) {
        if ((i == 3 || i == 4) && this.l != null) {
            this.l.mo3327i();
        }
    }
}
