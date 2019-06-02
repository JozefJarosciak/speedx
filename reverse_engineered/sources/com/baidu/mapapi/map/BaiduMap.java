package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.alipay.sdk.packet.C0861d;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo.SwitchFloorError;
import com.baidu.mapapi.map.MapStatus.Builder;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.Overlay.C1109a;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1233D;
import com.baidu.platform.comapi.map.C1235E;
import com.baidu.platform.comapi.map.C1237F;
import com.baidu.platform.comapi.map.C1249e;
import com.baidu.platform.comapi.map.C1252h;
import com.baidu.platform.comapi.map.C1255j;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduMap {
    public static final int MAP_TYPE_NONE = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    /* renamed from: e */
    private static final String f2825e = BaiduMap.class.getSimpleName();
    /* renamed from: A */
    private OnMapDrawFrameCallback f2826A;
    /* renamed from: B */
    private OnBaseIndoorMapListener f2827B;
    /* renamed from: C */
    private TileOverlay f2828C;
    /* renamed from: D */
    private HeatMap f2829D;
    /* renamed from: E */
    private Lock f2830E = new ReentrantLock();
    /* renamed from: F */
    private Lock f2831F = new ReentrantLock();
    /* renamed from: G */
    private InfoWindow f2832G;
    /* renamed from: H */
    private Marker f2833H;
    /* renamed from: I */
    private View f2834I;
    /* renamed from: J */
    private Marker f2835J;
    /* renamed from: K */
    private MyLocationData f2836K;
    /* renamed from: L */
    private MyLocationConfiguration f2837L;
    /* renamed from: M */
    private boolean f2838M;
    /* renamed from: N */
    private boolean f2839N;
    /* renamed from: O */
    private boolean f2840O;
    /* renamed from: P */
    private boolean f2841P;
    /* renamed from: Q */
    private Point f2842Q;
    /* renamed from: a */
    MapView f2843a;
    /* renamed from: b */
    TextureMapView f2844b;
    /* renamed from: c */
    WearMapView f2845c;
    /* renamed from: d */
    C1233D f2846d;
    /* renamed from: f */
    private Projection f2847f;
    /* renamed from: g */
    private UiSettings f2848g;
    /* renamed from: h */
    private C1255j f2849h;
    /* renamed from: i */
    private C1249e f2850i;
    /* renamed from: j */
    private C1237F f2851j;
    /* renamed from: k */
    private List<Overlay> f2852k;
    /* renamed from: l */
    private List<Marker> f2853l;
    /* renamed from: m */
    private List<Marker> f2854m;
    /* renamed from: n */
    private C1109a f2855n;
    /* renamed from: o */
    private OnMapStatusChangeListener f2856o;
    /* renamed from: p */
    private OnMapTouchListener f2857p;
    /* renamed from: q */
    private OnMapClickListener f2858q;
    /* renamed from: r */
    private OnMapLoadedCallback f2859r;
    /* renamed from: s */
    private OnMapRenderCallback f2860s;
    /* renamed from: t */
    private OnMapDoubleClickListener f2861t;
    /* renamed from: u */
    private OnMapLongClickListener f2862u;
    /* renamed from: v */
    private CopyOnWriteArrayList<OnMarkerClickListener> f2863v = new CopyOnWriteArrayList();
    /* renamed from: w */
    private CopyOnWriteArrayList<OnPolylineClickListener> f2864w = new CopyOnWriteArrayList();
    /* renamed from: x */
    private OnMarkerDragListener f2865x;
    /* renamed from: y */
    private OnMyLocationClickListener f2866y;
    /* renamed from: z */
    private SnapshotReadyCallback f2867z;

    public interface OnBaseIndoorMapListener {
        void onBaseIndoorMapMode(boolean z, MapBaseIndoorMapInfo mapBaseIndoorMapInfo);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);

        boolean onMapPoiClick(MapPoi mapPoi);
    }

    public interface OnMapDoubleClickListener {
        void onMapDoubleClick(LatLng latLng);
    }

    public interface OnMapDrawFrameCallback {
        void onMapDrawFrame(MapStatus mapStatus);

        @Deprecated
        void onMapDrawFrame(GL10 gl10, MapStatus mapStatus);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapRenderCallback {
        void onMapRenderFinished();
    }

    public interface OnMapStatusChangeListener {
        void onMapStatusChange(MapStatus mapStatus);

        void onMapStatusChangeFinish(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationClickListener {
        boolean onMyLocationClick();
    }

    public interface OnPolylineClickListener {
        boolean onPolylineClick(Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    BaiduMap(C1237F c1237f) {
        this.f2851j = c1237f;
        this.f2850i = this.f2851j.m4625b();
        this.f2846d = C1233D.TextureView;
        m4062c();
    }

    BaiduMap(C1255j c1255j) {
        this.f2849h = c1255j;
        this.f2850i = this.f2849h.m4760a();
        this.f2846d = C1233D.GLSurfaceView;
        m4062c();
    }

    /* renamed from: a */
    private Point m4053a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        for (String replaceAll : str.replaceAll("^\\{", "").replaceAll("\\}$", "").split(",")) {
            String[] split = replaceAll.replaceAll("\"", "").split(":");
            if ("x".equals(split[0])) {
                i2 = Integer.valueOf(split[1]).intValue();
            }
            if ("y".equals(split[0])) {
                i = Integer.valueOf(split[1]).intValue();
            }
        }
        return new Point(i2, i);
    }

    /* renamed from: a */
    private C1235E m4056a(MapStatusUpdate mapStatusUpdate) {
        if (this.f2850i == null) {
            return null;
        }
        return mapStatusUpdate.m4138a(this.f2850i, getMapStatus()).m4136b(this.f2850i.m4660D());
    }

    /* renamed from: a */
    private final void m4058a(MyLocationData myLocationData, MyLocationConfiguration myLocationConfiguration) {
        if (myLocationData != null && myLocationConfiguration != null && isMyLocationEnabled()) {
            Bundle bundle;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            LatLng latLng = new LatLng(myLocationData.latitude, myLocationData.longitude);
            GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
            try {
                jSONObject.put("type", 0);
                jSONObject2.put("ptx", ll2mc.getLongitudeE6());
                jSONObject2.put("pty", ll2mc.getLatitudeE6());
                jSONObject2.put("radius", (double) ((float) CoordUtil.getMCDistanceByOneLatLngAndRadius(latLng, (int) myLocationData.accuracy)));
                float f = myLocationData.direction;
                if (myLocationConfiguration.enableDirection) {
                    f = myLocationData.direction % 360.0f;
                    if (f > 180.0f) {
                        f -= 360.0f;
                    } else if (f < -180.0f) {
                        f += 360.0f;
                    }
                } else {
                    f = -1.0f;
                }
                jSONObject2.put("direction", (double) f);
                jSONObject2.put("iconarrownor", "NormalLocArrow");
                jSONObject2.put("iconarrownorid", 28);
                jSONObject2.put("iconarrowfoc", "FocusLocArrow");
                jSONObject2.put("iconarrowfocid", 29);
                jSONObject2.put("lineid", myLocationConfiguration.accuracyCircleStrokeColor);
                jSONObject2.put("areaid", myLocationConfiguration.accuracyCircleFillColor);
                jSONArray.put(jSONObject2);
                jSONObject.put(C0861d.f2139k, jSONArray);
                if (myLocationConfiguration.locationMode == LocationMode.COMPASS) {
                    jSONObject3.put("ptx", ll2mc.getLongitudeE6());
                    jSONObject3.put("pty", ll2mc.getLatitudeE6());
                    jSONObject3.put("radius", 0);
                    jSONObject3.put("direction", 0);
                    jSONObject3.put("iconarrownor", "direction_wheel");
                    jSONObject3.put("iconarrownorid", 54);
                    jSONObject3.put("iconarrowfoc", "direction_wheel");
                    jSONObject3.put("iconarrowfocid", 54);
                    jSONArray.put(jSONObject3);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (myLocationConfiguration.customMarker == null) {
                bundle = null;
            } else {
                List<BitmapDescriptor> arrayList = new ArrayList();
                arrayList.add(myLocationConfiguration.customMarker);
                Bundle bundle2 = new Bundle();
                ArrayList arrayList2 = new ArrayList();
                for (BitmapDescriptor bitmapDescriptor : arrayList) {
                    ParcelItem parcelItem = new ParcelItem();
                    Bundle bundle3 = new Bundle();
                    Bitmap bitmap = bitmapDescriptor.f2880a;
                    Buffer allocate = ByteBuffer.allocate((bitmap.getWidth() * bitmap.getHeight()) * 4);
                    bitmap.copyPixelsToBuffer(allocate);
                    bundle3.putByteArray("imgdata", allocate.array());
                    bundle3.putInt("imgindex", bitmapDescriptor.hashCode());
                    bundle3.putInt("imgH", bitmap.getHeight());
                    bundle3.putInt("imgW", bitmap.getWidth());
                    parcelItem.setBundle(bundle3);
                    arrayList2.add(parcelItem);
                }
                if (arrayList2.size() > 0) {
                    Parcelable[] parcelableArr = new ParcelItem[arrayList2.size()];
                    for (int i = 0; i < arrayList2.size(); i++) {
                        parcelableArr[i] = (ParcelItem) arrayList2.get(i);
                    }
                    bundle2.putParcelableArray("icondata", parcelableArr);
                }
                bundle = bundle2;
            }
            if (this.f2850i != null) {
                this.f2850i.m4687a(jSONObject.toString(), bundle);
            }
            switch (myLocationConfiguration.locationMode) {
                case COMPASS:
                    animateMapStatus(MapStatusUpdateFactory.newMapStatus(new Builder().rotate(myLocationData.direction).overlook(-45.0f).target(new LatLng(myLocationData.latitude, myLocationData.longitude)).targetScreen(getMapStatus().targetScreen).zoom(getMapStatus().zoom).build()));
                    return;
                case FOLLOWING:
                    animateMapStatus(MapStatusUpdateFactory.newMapStatus(new Builder().target(new LatLng(myLocationData.latitude, myLocationData.longitude)).zoom(getMapStatus().zoom).rotate(getMapStatus().rotate).overlook(getMapStatus().overlook).targetScreen(getMapStatus().targetScreen).build()));
                    return;
                case NORMAL:
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    private void m4062c() {
        this.f2852k = new CopyOnWriteArrayList();
        this.f2853l = new CopyOnWriteArrayList();
        this.f2854m = new CopyOnWriteArrayList();
        this.f2842Q = new Point((int) (SysOSUtil.getDensity() * 40.0f), (int) (SysOSUtil.getDensity() * 40.0f));
        this.f2848g = new UiSettings(this.f2850i);
        this.f2855n = new C1113a(this);
        this.f2850i.m4685a(new C1115b(this));
        this.f2850i.m4686a(new C1117c(this));
        this.f2850i.m4683a(new C1119d(this));
        this.f2838M = this.f2850i.m4658B();
        this.f2839N = this.f2850i.m4659C();
    }

    /* renamed from: a */
    void m4086a() {
        if (this.f2850i != null) {
            this.f2850i.m4742s();
        }
    }

    /* renamed from: a */
    void m4087a(HeatMap heatMap) {
        this.f2830E.lock();
        try {
            if (!(this.f2829D == null || this.f2850i == null || heatMap != this.f2829D)) {
                this.f2829D.m4130b();
                this.f2829D.m4131c();
                this.f2829D.f2941a = null;
                this.f2850i.m4732n();
                this.f2829D = null;
                this.f2850i.m4728l(false);
            }
            this.f2830E.unlock();
        } catch (Throwable th) {
            this.f2830E.unlock();
        }
    }

    /* renamed from: a */
    void m4088a(TileOverlay tileOverlay) {
        this.f2831F.lock();
        if (tileOverlay != null) {
            try {
                if (this.f2828C == tileOverlay) {
                    tileOverlay.m4204b();
                    tileOverlay.f3184a = null;
                    if (this.f2850i != null) {
                        this.f2850i.m4705c(false);
                    }
                }
            } catch (Throwable th) {
                this.f2828C = null;
                this.f2831F.unlock();
            }
        }
        this.f2828C = null;
        this.f2831F.unlock();
    }

    public void addHeatMap(HeatMap heatMap) {
        if (heatMap != null) {
            this.f2830E.lock();
            try {
                if (heatMap != this.f2829D) {
                    if (this.f2829D != null) {
                        this.f2829D.m4130b();
                        this.f2829D.m4131c();
                        this.f2829D.f2941a = null;
                        this.f2850i.m4732n();
                    }
                    this.f2829D = heatMap;
                    this.f2829D.f2941a = this;
                    this.f2850i.m4728l(true);
                    this.f2830E.unlock();
                }
            } finally {
                this.f2830E.unlock();
            }
        }
    }

    public final Overlay addOverlay(OverlayOptions overlayOptions) {
        if (overlayOptions == null) {
            return null;
        }
        Overlay a = overlayOptions.mo2614a();
        a.listener = this.f2855n;
        if (a instanceof Marker) {
            Marker marker = (Marker) a;
            if (!(marker.f3048n == null || marker.f3048n.size() == 0)) {
                this.f2853l.add(marker);
                if (this.f2850i != null) {
                    this.f2850i.m4703b(true);
                }
            }
            this.f2854m.add(marker);
        }
        Bundle bundle = new Bundle();
        a.mo2613a(bundle);
        if (this.f2850i != null) {
            this.f2850i.m4699b(bundle);
        }
        this.f2852k.add(a);
        return a;
    }

    public final List<Overlay> addOverlays(List<OverlayOptions> list) {
        if (list == null) {
            return null;
        }
        List<Overlay> arrayList = new ArrayList();
        Bundle[] bundleArr = new Bundle[list.size()];
        int i = 0;
        for (OverlayOptions overlayOptions : list) {
            if (overlayOptions != null) {
                Bundle bundle = new Bundle();
                Overlay a = overlayOptions.mo2614a();
                a.listener = this.f2855n;
                if (a instanceof Marker) {
                    Marker marker = (Marker) a;
                    if (!(marker.f3048n == null || marker.f3048n.size() == 0)) {
                        this.f2853l.add(marker);
                        if (this.f2850i != null) {
                            this.f2850i.m4703b(true);
                        }
                    }
                    this.f2854m.add(marker);
                }
                this.f2852k.add(a);
                arrayList.add(a);
                a.mo2613a(bundle);
                bundleArr[i] = bundle;
                i++;
            }
        }
        i = bundleArr.length / HttpStatus.SC_BAD_REQUEST;
        int i2 = 0;
        while (i2 < i + 1) {
            List arrayList2 = new ArrayList();
            int i3 = 0;
            while (i3 < HttpStatus.SC_BAD_REQUEST && (i2 * HttpStatus.SC_BAD_REQUEST) + i3 < bundleArr.length) {
                if (bundleArr[(i2 * HttpStatus.SC_BAD_REQUEST) + i3] != null) {
                    arrayList2.add(bundleArr[(i2 * HttpStatus.SC_BAD_REQUEST) + i3]);
                }
                i3++;
            }
            if (this.f2850i != null) {
                this.f2850i.m4688a(arrayList2);
            }
            i2++;
        }
        return arrayList;
    }

    public TileOverlay addTileLayer(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions == null) {
            return null;
        }
        if (this.f2828C != null) {
            this.f2828C.m4204b();
            this.f2828C.f3184a = null;
        }
        if (this.f2850i == null || !this.f2850i.m4693a(tileOverlayOptions.m4206a())) {
            return null;
        }
        TileOverlay a = tileOverlayOptions.m4207a(this);
        this.f2828C = a;
        return a;
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate) {
        animateMapStatus(mapStatusUpdate, 300);
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate, int i) {
        if (mapStatusUpdate != null && i > 0) {
            C1235E a = m4056a(mapStatusUpdate);
            if (this.f2850i == null) {
                return;
            }
            if (this.f2841P) {
                this.f2850i.m4682a(a, i);
            } else {
                this.f2850i.m4681a(a);
            }
        }
    }

    /* renamed from: b */
    boolean m4089b() {
        return this.f2850i == null ? false : this.f2850i.m4712d();
    }

    public final void clear() {
        this.f2852k.clear();
        this.f2853l.clear();
        this.f2854m.clear();
        if (this.f2850i != null) {
            this.f2850i.m4703b(false);
            this.f2850i.m4730m();
        }
        hideInfoWindow();
    }

    public final Point getCompassPosition() {
        return this.f2850i != null ? m4053a(this.f2850i.m4719g()) : null;
    }

    public MapBaseIndoorMapInfo getFocusedBaseIndoorMapInfo() {
        return this.f2850i.m4734o();
    }

    public final MyLocationConfiguration getLocationConfigeration() {
        return getLocationConfiguration();
    }

    public final MyLocationConfiguration getLocationConfiguration() {
        return this.f2837L;
    }

    public final MyLocationData getLocationData() {
        return this.f2836K;
    }

    public final MapStatus getMapStatus() {
        return this.f2850i == null ? null : MapStatus.m4133a(this.f2850i.m4660D());
    }

    public final LatLngBounds getMapStatusLimit() {
        return this.f2850i == null ? null : this.f2850i.m4661E();
    }

    public final int getMapType() {
        return this.f2850i == null ? 1 : !this.f2850i.m4727k() ? 3 : this.f2850i.m4725j() ? 2 : 1;
    }

    public List<Marker> getMarkersInBounds(LatLngBounds latLngBounds) {
        if (getMapStatus() == null) {
            return null;
        }
        List<Marker> arrayList = new ArrayList();
        if (this.f2854m.size() == 0) {
            return null;
        }
        for (Marker marker : this.f2854m) {
            if (latLngBounds.contains(marker.getPosition())) {
                arrayList.add(marker);
            }
        }
        return arrayList;
    }

    public final float getMaxZoomLevel() {
        return this.f2850i == null ? 0.0f : this.f2850i.f3754a;
    }

    public final float getMinZoomLevel() {
        return this.f2850i == null ? 0.0f : this.f2850i.f3755b;
    }

    public final Projection getProjection() {
        return this.f2847f;
    }

    public final UiSettings getUiSettings() {
        return this.f2848g;
    }

    public void hideInfoWindow() {
        if (this.f2832G != null) {
            if (this.f2832G.f2955b != null) {
                switch (this.f2846d) {
                    case TextureView:
                        if (this.f2844b != null) {
                            this.f2844b.removeView(this.f2834I);
                            break;
                        }
                        break;
                    case GLSurfaceView:
                        if (this.f2849h != null) {
                            this.f2843a.removeView(this.f2834I);
                            break;
                        }
                        break;
                }
                this.f2834I = null;
            }
            this.f2832G = null;
            this.f2833H.remove();
            this.f2833H = null;
        }
    }

    public final boolean isBaiduHeatMapEnabled() {
        return this.f2850i == null ? false : this.f2850i.m4722h();
    }

    public boolean isBaseIndoorMapMode() {
        return this.f2850i.m4737p();
    }

    public final boolean isBuildingsEnabled() {
        return this.f2850i == null ? false : this.f2850i.m4729l();
    }

    public final boolean isMyLocationEnabled() {
        return this.f2850i == null ? false : this.f2850i.m4741r();
    }

    public final boolean isSupportBaiduHeatMap() {
        return this.f2850i == null ? false : this.f2850i.m4724i();
    }

    public final boolean isTrafficEnabled() {
        return this.f2850i == null ? false : this.f2850i.m4718f();
    }

    public final void removeMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (this.f2863v.contains(onMarkerClickListener)) {
            this.f2863v.remove(onMarkerClickListener);
        }
    }

    public final void setBaiduHeatMapEnabled(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4716e(z);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4720g(z);
        }
    }

    public void setCompassIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("compass's icon can not be null");
        }
        this.f2850i.m4677a(bitmap);
    }

    public void setCompassPosition(Point point) {
        if (this.f2850i.m4692a(point)) {
            this.f2842Q = point;
        }
    }

    public final void setIndoorEnable(boolean z) {
        if (this.f2850i != null) {
            this.f2840O = z;
            this.f2850i.m4723i(z);
        }
        if (this.f2827B != null && !z) {
            this.f2827B.onBaseIndoorMapMode(false, null);
        }
    }

    public final void setMapStatus(MapStatusUpdate mapStatusUpdate) {
        if (mapStatusUpdate != null) {
            C1235E a = m4056a(mapStatusUpdate);
            if (this.f2850i != null) {
                this.f2850i.m4681a(a);
                if (this.f2856o != null) {
                    this.f2856o.onMapStatusChange(getMapStatus());
                }
            }
        }
    }

    public final void setMapStatusLimits(LatLngBounds latLngBounds) {
        if (this.f2850i != null) {
            this.f2850i.m4679a(latLngBounds);
            setMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));
        }
    }

    public final void setMapType(int i) {
        if (this.f2850i != null) {
            switch (i) {
                case 1:
                    this.f2850i.m4689a(false);
                    this.f2850i.m4738q(this.f2838M);
                    this.f2850i.m4740r(this.f2839N);
                    this.f2850i.m4711d(true);
                    this.f2850i.m4723i(this.f2840O);
                    break;
                case 2:
                    this.f2850i.m4689a(true);
                    this.f2850i.m4738q(this.f2838M);
                    this.f2850i.m4740r(this.f2839N);
                    this.f2850i.m4711d(true);
                    break;
                case 3:
                    if (this.f2850i.m4658B()) {
                        this.f2850i.m4738q(false);
                    }
                    if (this.f2850i.m4659C()) {
                        this.f2850i.m4740r(false);
                    }
                    this.f2850i.m4711d(false);
                    this.f2850i.m4723i(false);
                    break;
            }
            if (this.f2849h != null) {
                this.f2849h.m4762a(i);
            }
        }
    }

    public final void setMaxAndMinZoomLevel(float f, float f2) {
        if (f <= 21.0f && f2 >= 3.0f && f >= f2 && this.f2850i != null) {
            this.f2850i.m4675a(f, f2);
        }
    }

    public final void setMyLocationConfigeration(MyLocationConfiguration myLocationConfiguration) {
        setMyLocationConfiguration(myLocationConfiguration);
    }

    public final void setMyLocationConfiguration(MyLocationConfiguration myLocationConfiguration) {
        this.f2837L = myLocationConfiguration;
        m4058a(this.f2836K, this.f2837L);
    }

    public final void setMyLocationData(MyLocationData myLocationData) {
        this.f2836K = myLocationData;
        if (this.f2837L == null) {
            this.f2837L = new MyLocationConfiguration(LocationMode.NORMAL, false, null);
        }
        m4058a(myLocationData, this.f2837L);
    }

    public final void setMyLocationEnabled(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4726k(z);
        }
    }

    public final void setOnBaseIndoorMapListener(OnBaseIndoorMapListener onBaseIndoorMapListener) {
        this.f2827B = onBaseIndoorMapListener;
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        this.f2858q = onMapClickListener;
    }

    public final void setOnMapDoubleClickListener(OnMapDoubleClickListener onMapDoubleClickListener) {
        this.f2861t = onMapDoubleClickListener;
    }

    public final void setOnMapDrawFrameCallback(OnMapDrawFrameCallback onMapDrawFrameCallback) {
        this.f2826A = onMapDrawFrameCallback;
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback onMapLoadedCallback) {
        this.f2859r = onMapLoadedCallback;
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        this.f2862u = onMapLongClickListener;
    }

    public void setOnMapRenderCallbadk(OnMapRenderCallback onMapRenderCallback) {
        this.f2860s = onMapRenderCallback;
    }

    public final void setOnMapStatusChangeListener(OnMapStatusChangeListener onMapStatusChangeListener) {
        this.f2856o = onMapStatusChangeListener;
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        this.f2857p = onMapTouchListener;
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener != null && !this.f2863v.contains(onMarkerClickListener)) {
            this.f2863v.add(onMarkerClickListener);
        }
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        this.f2865x = onMarkerDragListener;
    }

    public final void setOnMyLocationClickListener(OnMyLocationClickListener onMyLocationClickListener) {
        this.f2866y = onMyLocationClickListener;
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        if (onPolylineClickListener != null) {
            this.f2864w.add(onPolylineClickListener);
        }
    }

    @Deprecated
    public final void setPadding(int i, int i2, int i3, int i4) {
        if (i >= 0 && i2 >= 0 && i3 >= 0 && i4 >= 0 && this.f2850i != null) {
            this.f2850i.m4660D();
            float width;
            float height;
            MapStatusUpdate newMapStatus;
            switch (this.f2846d) {
                case TextureView:
                    if (this.f2844b != null) {
                        width = ((float) ((this.f2844b.getWidth() - i) - i3)) / ((float) this.f2844b.getWidth());
                        height = ((float) ((this.f2844b.getHeight() - i2) - i4)) / ((float) this.f2844b.getHeight());
                        newMapStatus = MapStatusUpdateFactory.newMapStatus(new Builder().targetScreen(new Point(((this.f2844b.getWidth() + i) - i3) / 2, ((this.f2844b.getHeight() + i2) - i4) / 2)).build());
                        this.f2850i.m4692a(new Point((int) ((width * ((float) this.f2842Q.x)) + ((float) i)), (int) ((height * ((float) this.f2842Q.y)) + ((float) i2))));
                        setMapStatus(newMapStatus);
                        this.f2844b.setPadding(i, i2, i3, i4);
                        this.f2844b.invalidate();
                        return;
                    }
                    return;
                case GLSurfaceView:
                    if (this.f2843a != null) {
                        width = ((float) ((this.f2843a.getWidth() - i) - i3)) / ((float) this.f2843a.getWidth());
                        height = ((float) ((this.f2843a.getHeight() - i2) - i4)) / ((float) this.f2843a.getHeight());
                        newMapStatus = MapStatusUpdateFactory.newMapStatus(new Builder().targetScreen(new Point(((this.f2843a.getWidth() + i) - i3) / 2, ((this.f2843a.getHeight() + i2) - i4) / 2)).build());
                        this.f2850i.m4692a(new Point((int) ((width * ((float) this.f2842Q.x)) + ((float) i)), (int) ((height * ((float) this.f2842Q.y)) + ((float) i2))));
                        setMapStatus(newMapStatus);
                        this.f2843a.setPadding(i, i2, i3, i4);
                        this.f2843a.invalidate();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public final void setTrafficEnabled(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4717f(z);
        }
    }

    public final void setViewPadding(int i, int i2, int i3, int i4) {
        if (i >= 0 && i2 >= 0 && i3 >= 0 && i4 >= 0 && this.f2850i != null) {
            float f;
            switch (this.f2846d) {
                case TextureView:
                    if (this.f2844b != null) {
                        f = (float) i;
                        f = (float) i2;
                        this.f2850i.m4692a(new Point((int) (((((float) ((this.f2844b.getWidth() - i) - i3)) / ((float) this.f2844b.getWidth())) * ((float) this.f2842Q.x)) + f), (int) (((((float) ((this.f2844b.getHeight() - i2) - i4)) / ((float) this.f2844b.getHeight())) * ((float) this.f2842Q.y)) + f)));
                        this.f2844b.setPadding(i, i2, i3, i4);
                        this.f2844b.invalidate();
                        return;
                    }
                    return;
                case GLSurfaceView:
                    if (this.f2843a != null) {
                        f = (float) i;
                        f = (float) i2;
                        this.f2850i.m4692a(new Point((int) (((((float) ((this.f2843a.getWidth() - i) - i3)) / ((float) this.f2843a.getWidth())) * ((float) this.f2842Q.x)) + f), (int) (((((float) ((this.f2843a.getHeight() - i2) - i4)) / ((float) this.f2843a.getHeight())) * ((float) this.f2842Q.y)) + f)));
                        this.f2843a.setPadding(i, i2, i3, i4);
                        this.f2843a.invalidate();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        if (infoWindow != null) {
            hideInfoWindow();
            if (infoWindow.f2955b != null) {
                this.f2834I = infoWindow.f2955b;
                this.f2834I.destroyDrawingCache();
                LayoutParams build = new MapViewLayoutParams.Builder().layoutMode(ELayoutMode.mapMode).position(infoWindow.f2956c).yOffset(infoWindow.f2958e).build();
                switch (this.f2846d) {
                    case TextureView:
                        if (this.f2844b != null) {
                            this.f2844b.addView(this.f2834I, build);
                            break;
                        }
                        break;
                    case GLSurfaceView:
                        if (this.f2849h != null) {
                            this.f2843a.addView(this.f2834I, build);
                            break;
                        }
                        break;
                }
            }
            this.f2832G = infoWindow;
            Overlay a = new MarkerOptions().perspective(false).icon(infoWindow.f2955b != null ? BitmapDescriptorFactory.fromView(infoWindow.f2955b) : infoWindow.f2954a).position(infoWindow.f2956c).zIndex(Integer.MAX_VALUE).m4156a(infoWindow.f2958e).mo2614a();
            a.listener = this.f2855n;
            a.type = C1252h.popup;
            Bundle bundle = new Bundle();
            a.mo2613a(bundle);
            if (this.f2850i != null) {
                this.f2850i.m4699b(bundle);
            }
            this.f2852k.add(a);
            this.f2833H = (Marker) a;
        }
    }

    public final void showMapIndoorPoi(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4740r(z);
            this.f2839N = z;
        }
    }

    public final void showMapPoi(boolean z) {
        if (this.f2850i != null) {
            this.f2850i.m4738q(z);
            this.f2838M = z;
        }
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        this.f2867z = snapshotReadyCallback;
        switch (this.f2846d) {
            case TextureView:
                if (this.f2851j != null) {
                    this.f2851j.m4624a("anything", null);
                    return;
                }
                return;
            case GLSurfaceView:
                if (this.f2849h != null) {
                    this.f2849h.m4763a("anything", null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void snapshotScope(Rect rect, SnapshotReadyCallback snapshotReadyCallback) {
        this.f2867z = snapshotReadyCallback;
        switch (this.f2846d) {
            case TextureView:
                if (this.f2851j != null) {
                    this.f2851j.m4624a("anything", rect);
                    return;
                }
                return;
            case GLSurfaceView:
                if (this.f2849h != null) {
                    this.f2849h.m4763a("anything", rect);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public SwitchFloorError switchBaseIndoorMapFloor(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return SwitchFloorError.FLOOR_INFO_ERROR;
        }
        MapBaseIndoorMapInfo focusedBaseIndoorMapInfo = getFocusedBaseIndoorMapInfo();
        if (!str2.equals(focusedBaseIndoorMapInfo.f2963a)) {
            return SwitchFloorError.FOCUSED_ID_ERROR;
        }
        List floors = focusedBaseIndoorMapInfo.getFloors();
        return (floors == null || !floors.contains(str)) ? SwitchFloorError.FLOOR_OVERLFLOW : this.f2850i.m4695a(str, str2) ? SwitchFloorError.SWITCH_OK : SwitchFloorError.SWITCH_ERROR;
    }
}
