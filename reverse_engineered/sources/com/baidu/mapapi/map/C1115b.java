package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.MotionEvent;
import com.alipay.sdk.sys.C0869a;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnPolylineClickListener;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1114l;
import com.baidu.platform.comapi.map.C1235E;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.mapapi.map.b */
class C1115b implements C1114l {
    /* renamed from: a */
    final /* synthetic */ BaiduMap f3240a;

    C1115b(BaiduMap baiduMap) {
        this.f3240a = baiduMap;
    }

    /* renamed from: a */
    public void mo2625a() {
    }

    /* renamed from: a */
    public void mo2626a(Bitmap bitmap) {
        if (this.f3240a.f2867z != null) {
            this.f3240a.f2867z.onSnapshotReady(bitmap);
        }
    }

    /* renamed from: a */
    public void mo2627a(MotionEvent motionEvent) {
        if (this.f3240a.f2857p != null) {
            this.f3240a.f2857p.onTouch(motionEvent);
        }
    }

    /* renamed from: a */
    public void mo2628a(GeoPoint geoPoint) {
        if (this.f3240a.f2858q != null) {
            this.f3240a.f2858q.onMapClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    /* renamed from: a */
    public void mo2629a(C1235E c1235e) {
        if (this.f3240a.f2834I != null) {
            this.f3240a.f2834I.setVisibility(4);
        }
        if (this.f3240a.f2856o != null) {
            this.f3240a.f2856o.onMapStatusChangeStart(MapStatus.m4133a(c1235e));
        }
    }

    /* renamed from: a */
    public void mo2630a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONArray("dataset").optJSONObject(0);
            GeoPoint b = this.f3240a.f2850i.m4696b(jSONObject.optInt("px"), jSONObject.optInt("py"));
            int optInt = optJSONObject.optInt(C0869a.f2166g);
            if (optInt == 17) {
                if (this.f3240a.f2858q != null) {
                    MapPoi mapPoi = new MapPoi();
                    mapPoi.m4132a(optJSONObject);
                    this.f3240a.f2858q.onMapPoiClick(mapPoi);
                }
            } else if (optInt == 18) {
                if (this.f3240a.f2866y != null) {
                    this.f3240a.f2866y.onMyLocationClick();
                } else {
                    mo2628a(b);
                }
            } else if (optInt == 19) {
                if (this.f3240a.f2850i != null) {
                    C1235E D = this.f3240a.f2850i.m4660D();
                    D.f3680c = 0;
                    D.f3679b = 0;
                    this.f3240a.f2850i.m4682a(D, 300);
                }
            } else if (optInt == 90909) {
                String optString = optJSONObject.optString("marker_id");
                if (this.f3240a.f2832G == null || !optString.equals(this.f3240a.f2833H.p)) {
                    for (Overlay overlay : this.f3240a.f2852k) {
                        if ((overlay instanceof Marker) && overlay.f2804p.equals(optString)) {
                            if (this.f3240a.f2863v.isEmpty()) {
                                mo2628a(b);
                            } else {
                                Iterator it = this.f3240a.f2863v.iterator();
                                while (it.hasNext()) {
                                    ((OnMarkerClickListener) it.next()).onMarkerClick((Marker) overlay);
                                }
                                return;
                            }
                        }
                    }
                    return;
                }
                OnInfoWindowClickListener onInfoWindowClickListener = this.f3240a.f2832G.f2957d;
                if (onInfoWindowClickListener != null) {
                    onInfoWindowClickListener.onInfoWindowClick();
                } else {
                    mo2628a(b);
                }
            } else if (optInt == 90910) {
                String optString2 = optJSONObject.optString("polyline_id");
                for (Overlay overlay2 : this.f3240a.f2852k) {
                    if ((overlay2 instanceof Polyline) && overlay2.f2804p.equals(optString2)) {
                        if (this.f3240a.f2864w.isEmpty()) {
                            mo2628a(b);
                        } else {
                            Iterator it2 = this.f3240a.f2864w.iterator();
                            while (it2.hasNext()) {
                                ((OnPolylineClickListener) it2.next()).onPolylineClick((Polyline) overlay2);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo2631a(GL10 gl10, C1235E c1235e) {
        if (this.f3240a.f2826A != null) {
            this.f3240a.f2826A.onMapDrawFrame(MapStatus.m4133a(c1235e));
        }
    }

    /* renamed from: a */
    public void mo2632a(boolean z) {
        if (this.f3240a.f2827B != null) {
            this.f3240a.f2827B.onBaseIndoorMapMode(z, this.f3240a.getFocusedBaseIndoorMapInfo());
        }
    }

    /* renamed from: b */
    public void mo2633b() {
        this.f3240a.f2847f = new Projection(this.f3240a.f2850i);
        this.f3240a.f2841P = true;
        if (this.f3240a.f2859r != null) {
            this.f3240a.f2859r.onMapLoaded();
        }
    }

    /* renamed from: b */
    public void mo2634b(GeoPoint geoPoint) {
        if (this.f3240a.f2861t != null) {
            this.f3240a.f2861t.onMapDoubleClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    /* renamed from: b */
    public void mo2635b(C1235E c1235e) {
        if (this.f3240a.f2856o != null) {
            this.f3240a.f2856o.onMapStatusChange(MapStatus.m4133a(c1235e));
        }
    }

    /* renamed from: b */
    public boolean mo2636b(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONArray("dataset").optJSONObject(0);
            if (optJSONObject.optInt(C0869a.f2166g) == 90909) {
                String optString = optJSONObject.optString("marker_id");
                if (this.f3240a.f2833H == null || !optString.equals(this.f3240a.f2833H.p)) {
                    for (Overlay overlay : this.f3240a.f2852k) {
                        if ((overlay instanceof Marker) && overlay.f2804p.equals(optString)) {
                            Marker marker = (Marker) overlay;
                            if (marker.f3040f) {
                                this.f3240a.f2835J = marker;
                                Point toScreenLocation = this.f3240a.f2847f.toScreenLocation(this.f3240a.f2835J.f3035a);
                                this.f3240a.f2835J.setPosition(this.f3240a.f2847f.fromScreenLocation(new Point(toScreenLocation.x, toScreenLocation.y - 60)));
                                if (this.f3240a.f2865x != null) {
                                    this.f3240a.f2865x.onMarkerDragStart(this.f3240a.f2835J);
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: c */
    public void mo2637c() {
        if (this.f3240a.f2860s != null) {
            this.f3240a.f2860s.onMapRenderFinished();
        }
    }

    /* renamed from: c */
    public void mo2638c(GeoPoint geoPoint) {
        if (this.f3240a.f2862u != null) {
            this.f3240a.f2862u.onMapLongClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    /* renamed from: c */
    public void mo2639c(C1235E c1235e) {
        if (this.f3240a.f2834I != null) {
            this.f3240a.f2834I.setVisibility(0);
        }
        if (this.f3240a.f2856o != null) {
            this.f3240a.f2856o.onMapStatusChangeFinish(MapStatus.m4133a(c1235e));
        }
    }

    /* renamed from: d */
    public void mo2640d() {
        this.f3240a.f2830E.lock();
        try {
            if (this.f3240a.f2829D != null) {
                this.f3240a.f2829D.m4129a();
            }
            this.f3240a.f2830E.unlock();
        } catch (Throwable th) {
            this.f3240a.f2830E.unlock();
        }
    }

    /* renamed from: d */
    public void mo2641d(GeoPoint geoPoint) {
        if (this.f3240a.f2835J != null && this.f3240a.f2835J.f3040f) {
            Point toScreenLocation = this.f3240a.f2847f.toScreenLocation(CoordUtil.mc2ll(geoPoint));
            this.f3240a.f2835J.setPosition(this.f3240a.f2847f.fromScreenLocation(new Point(toScreenLocation.x, toScreenLocation.y - 60)));
            if (this.f3240a.f2865x != null && this.f3240a.f2835J.f3040f) {
                this.f3240a.f2865x.onMarkerDrag(this.f3240a.f2835J);
            }
        }
    }

    /* renamed from: e */
    public void mo2642e() {
        this.f3240a.f2830E.lock();
        try {
            if (this.f3240a.f2829D != null) {
                this.f3240a.f2829D.m4129a();
                this.f3240a.f2850i.m4732n();
            }
            this.f3240a.f2830E.unlock();
        } catch (Throwable th) {
            this.f3240a.f2830E.unlock();
        }
    }

    /* renamed from: e */
    public void mo2643e(GeoPoint geoPoint) {
        if (this.f3240a.f2835J != null && this.f3240a.f2835J.f3040f) {
            Point toScreenLocation = this.f3240a.f2847f.toScreenLocation(CoordUtil.mc2ll(geoPoint));
            this.f3240a.f2835J.setPosition(this.f3240a.f2847f.fromScreenLocation(new Point(toScreenLocation.x, toScreenLocation.y - 60)));
            if (this.f3240a.f2865x != null && this.f3240a.f2835J.f3040f) {
                this.f3240a.f2865x.onMarkerDragEnd(this.f3240a.f2835J);
            }
            this.f3240a.f2835J = null;
        }
    }

    /* renamed from: f */
    public void mo2644f() {
        this.f3240a.f2850i.m4703b(false);
        this.f3240a.f2830E.lock();
        try {
            if (this.f3240a.f2829D != null) {
                this.f3240a.m4087a(this.f3240a.f2829D);
            }
            this.f3240a.f2830E.unlock();
        } catch (Throwable th) {
            this.f3240a.f2830E.unlock();
        }
    }
}
