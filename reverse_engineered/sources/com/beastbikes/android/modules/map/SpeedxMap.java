package com.beastbikes.android.modules.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.modules.map.C2296d.C1686a;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.modules.map.C2296d.C1958b;
import com.beastbikes.android.modules.map.C2296d.C2306c;
import com.beastbikes.android.modules.map.C2296d.C2307d;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.android.utils.C2558g;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedxMap extends RelativeLayout implements C2286e {
    /* renamed from: a */
    private static final Logger f10838a = LoggerFactory.getLogger(SpeedxMap.class.getName());
    /* renamed from: b */
    private C2296d f10839b;
    /* renamed from: c */
    private FrameLayout f10840c;
    /* renamed from: d */
    private MapType f10841d;
    /* renamed from: e */
    private CoordinateConverter f10842e;
    /* renamed from: f */
    private List<LatLng> f10843f = null;
    /* renamed from: g */
    private List<com.google.android.gms.maps.model.LatLng> f10844g = null;
    /* renamed from: h */
    private List<com.mapbox.mapboxsdk.geometry.LatLng> f10845h = null;
    /* renamed from: i */
    private C1959a f10846i;
    /* renamed from: j */
    private boolean f10847j;
    /* renamed from: k */
    private C1686a f10848k;
    /* renamed from: l */
    private C2306c f10849l;
    /* renamed from: m */
    private C2307d f10850m;
    /* renamed from: n */
    private int f10851n = 0;

    /* renamed from: com.beastbikes.android.modules.map.SpeedxMap$b */
    public interface C1685b {
        /* renamed from: a */
        void mo3200a(double d, double d2);

        /* renamed from: a */
        void mo3196a(String str, String str2);

        /* renamed from: b */
        void mo3204b(double d, double d2);
    }

    /* renamed from: com.beastbikes.android.modules.map.SpeedxMap$a */
    public interface C1959a {
        /* renamed from: a */
        void mo3330a();
    }

    public SpeedxMap(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(C1373R.layout.speedx_map_layout, this);
        this.f10842e = new CoordinateConverter();
        this.f10842e.from(CoordType.GPS);
    }

    /* renamed from: c */
    public void m11675c() {
        if (this.f10839b != null) {
            this.f10839b.mo3445a();
        }
    }

    /* renamed from: d */
    public void m11676d() {
        if (this.f10839b != null) {
            this.f10839b.mo3452b();
        }
    }

    /* renamed from: e */
    public void m11677e() {
        if (this.f10839b != null) {
            this.f10839b.mo3456c();
        }
    }

    /* renamed from: f */
    public void m11678f() {
        if (this.f10839b != null) {
            this.f10839b.mo3475j();
        }
    }

    /* renamed from: a */
    public void m11660a(Bundle bundle) {
        if (this.f10839b != null) {
            this.f10839b.mo3474b(bundle);
        }
    }

    /* renamed from: b */
    public void m11673b(Bundle bundle) {
        if (this.f10839b != null) {
            this.f10839b.mo3473a(bundle);
        }
    }

    /* renamed from: g */
    public void m11679g() {
        if (this.f10839b != null) {
            this.f10839b.mo3463h();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f10840c = (FrameLayout) findViewById(C1373R.id.map_container);
    }

    /* renamed from: a */
    public void m11664a(MapType mapType, Activity activity, boolean z, ScrollView scrollView, C1959a c1959a) {
        this.f10847j = z;
        this.f10841d = mapType;
        this.f10846i = c1959a;
        setUp(mapType);
        this.f10839b.mo3449a(activity, this, z, scrollView);
    }

    /* renamed from: a */
    public void m11661a(MapType mapType, Activity activity) {
        setUp(mapType);
        this.f10841d = mapType;
        this.f10841d = mapType;
        if (this.f10839b != null) {
            this.f10839b.mo3449a(activity, this, false, null);
        }
    }

    /* renamed from: a */
    public void m11662a(MapType mapType, Activity activity, C1686a c1686a) {
        this.f10848k = c1686a;
        this.f10841d = mapType;
        setUp(mapType);
        if (this.f10839b != null) {
            this.f10839b.mo3449a(activity, this, false, null);
        }
    }

    /* renamed from: a */
    public void m11663a(MapType mapType, Activity activity, C1686a c1686a, C2306c c2306c, C2307d c2307d, C1959a c1959a) {
        this.f10848k = c1686a;
        this.f10849l = c2306c;
        this.f10850m = c2307d;
        this.f10846i = c1959a;
        this.f10841d = mapType;
        setUp(mapType);
        if (this.f10839b != null) {
            this.f10839b.mo3449a(activity, this, false, null);
        }
    }

    private void setUp(MapType mapType) {
        if (!(this.f10839b == null || this.f10840c == null)) {
            this.f10840c.removeAllViews();
            this.f10839b.removeAllViews();
            this.f10839b = null;
        }
        switch (mapType) {
            case BaiDu:
                this.f10839b = new C2297a(getContext());
                this.f10851n = 1;
                break;
            case Google:
                this.f10839b = new C2302b(getContext());
                this.f10851n = 2;
                break;
            case MapBox:
                this.f10839b = new C2305c(getContext());
                this.f10851n = 0;
                break;
            default:
                this.f10839b = new C2297a(getContext());
                this.f10851n = 1;
                break;
        }
        if (this.f10840c != null) {
            this.f10840c.addView(this.f10839b);
        }
        if (this.f10848k != null) {
            this.f10839b.setOnLocationChangeListener(this.f10848k);
        }
        if (this.f10849l != null) {
            this.f10839b.setOnMarkClickListener(this.f10849l);
        }
        if (this.f10850m != null) {
            this.f10839b.setOnPolylineClickListener(this.f10850m);
        }
    }

    /* renamed from: a */
    public void m11668a(List<C2411a> list) {
        if (list != null && list.size() > 1 && this.f10839b != null) {
            this.f10839b.mo3464i();
            switch (this.f10841d) {
                case BaiDu:
                    this.f10843f = m11648b((List) list);
                    if (!(this.f10843f == null || this.f10843f.size() < 2 || this.f10843f == null)) {
                        this.f10839b.mo3453b(this.f10843f.get(0), this.f10843f.get(this.f10843f.size() - 1));
                        this.f10839b.mo3458d(this.f10843f);
                        this.f10839b.mo3457c(this.f10843f);
                        break;
                    }
                case Google:
                    this.f10844g = m11650c((List) list);
                    if (!(this.f10844g == null || this.f10844g.size() < 2 || this.f10844g == null)) {
                        this.f10839b.mo3453b(this.f10844g.get(0), this.f10844g.get(this.f10844g.size() - 1));
                        this.f10839b.mo3458d(this.f10844g);
                        this.f10839b.mo3457c(this.f10844g);
                        break;
                    }
                case MapBox:
                    this.f10845h = m11652d(list);
                    if (!(this.f10845h == null || this.f10845h.size() < 2 || this.f10845h == null)) {
                        this.f10839b.mo3453b(this.f10845h.get(0), this.f10845h.get(this.f10845h.size() - 1));
                        this.f10839b.mo3458d(this.f10845h);
                        this.f10839b.mo3457c(this.f10845h);
                        break;
                    }
            }
            m11670a(this.f10847j);
        }
    }

    /* renamed from: a */
    public void m11667a(com.mapbox.mapboxsdk.geometry.LatLng latLng, int i) {
        if (latLng != null && this.f10839b != null) {
            switch (this.f10841d) {
                case BaiDu:
                    this.f10839b.mo3442a(C2558g.m12851g(latLng.getLatitude(), latLng.getLongitude()), i);
                    return;
                case Google:
                    this.f10839b.mo3442a(C2558g.m12841a(latLng.getLatitude(), latLng.getLongitude()), i);
                    return;
                case MapBox:
                    this.f10839b.mo3442a((Object) latLng, i);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public Object m11654a(StagePointDTO stagePointDTO, int i) {
        if (stagePointDTO == null || this.f10839b == null) {
            return null;
        }
        switch (this.f10841d) {
            case BaiDu:
                return this.f10839b.mo3442a(C2558g.m12851g(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), i);
            case Google:
                return this.f10839b.mo3442a(C2558g.m12841a(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), i);
            case MapBox:
                return this.f10839b.mo3442a(new com.mapbox.mapboxsdk.geometry.LatLng(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), i);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public Object m11655a(StagePointDTO stagePointDTO, Object obj) {
        if (stagePointDTO == null || this.f10839b == null) {
            return null;
        }
        switch (this.f10841d) {
            case BaiDu:
                return this.f10839b.mo3443a(C2558g.m12851g(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), obj);
            case Google:
                return this.f10839b.mo3443a(C2558g.m12841a(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), obj);
            case MapBox:
                return this.f10839b.mo3443a(new com.mapbox.mapboxsdk.geometry.LatLng(stagePointDTO.getLatitude(), stagePointDTO.getLongitude()), obj);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public void m11671a(com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr, int i, int i2) {
        if (latLngArr != null && latLngArr.length > 1 && this.f10839b != null) {
            switch (this.f10841d) {
                case BaiDu:
                    this.f10843f = m11646a(latLngArr);
                    if (this.f10843f != null && this.f10843f.size() >= 2 && this.f10843f != null) {
                        this.f10839b.mo3455c(this.f10843f, i, i2);
                        this.f10839b.mo3457c(this.f10843f);
                        return;
                    }
                    return;
                case Google:
                    this.f10844g = m11649b(latLngArr);
                    if (this.f10844g != null && this.f10844g.size() >= 2 && this.f10844g != null) {
                        this.f10839b.mo3455c(this.f10844g, i, i2);
                        this.f10839b.mo3457c(this.f10844g);
                        return;
                    }
                    return;
                case MapBox:
                    this.f10845h = m11651c(latLngArr);
                    if (this.f10845h != null && this.f10845h.size() >= 2 && this.f10845h != null) {
                        this.f10839b.mo3455c(this.f10845h, i, i2);
                        this.f10839b.mo3457c(this.f10845h);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public Object m11656a(List<StagePointDTO> list, int i, int i2) {
        if (list == null || list.size() <= 1 || this.f10839b == null) {
            return null;
        }
        com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr = new com.mapbox.mapboxsdk.geometry.LatLng[list.size()];
        for (int i3 = 0; i3 < latLngArr.length; i3++) {
            StagePointDTO stagePointDTO = (StagePointDTO) list.get(i3);
            com.mapbox.mapboxsdk.geometry.LatLng latLng = new com.mapbox.mapboxsdk.geometry.LatLng();
            latLng.setLatitude(stagePointDTO.getLatitude());
            latLng.setLongitude(stagePointDTO.getLongitude());
            latLngArr[i3] = latLng;
        }
        switch (this.f10841d) {
            case BaiDu:
                this.f10843f = m11646a(latLngArr);
                if (!(this.f10843f == null || this.f10843f.size() < 2 || this.f10843f == null)) {
                    return this.f10839b.mo3455c(this.f10843f, i, i2);
                }
            case Google:
                this.f10844g = m11649b(latLngArr);
                if (!(this.f10844g == null || this.f10844g.size() < 2 || this.f10844g == null)) {
                    return this.f10839b.mo3455c(this.f10844g, i, i2);
                }
            case MapBox:
                this.f10845h = m11651c(latLngArr);
                if (!(this.f10845h == null || this.f10845h.size() < 2 || this.f10845h == null)) {
                    return this.f10839b.mo3455c(this.f10845h, i, i2);
                }
        }
        return null;
    }

    /* renamed from: h */
    public void m11680h() {
        if (this.f10839b != null) {
            this.f10839b.mo3464i();
        }
    }

    /* renamed from: a */
    public void m11670a(boolean z) {
        if (this.f10839b != null) {
            switch (this.f10841d) {
                case BaiDu:
                case Google:
                    return;
                case MapBox:
                    this.f10839b.setMapStyle(z);
                    return;
                default:
                    return;
            }
        }
    }

    public ArrayList getPolylines() {
        if (this.f10839b == null) {
            return null;
        }
        return this.f10839b.getAllPolylines();
    }

    public ArrayList getMarkers() {
        if (this.f10839b == null) {
            return null;
        }
        return this.f10839b.getAllMarkers();
    }

    public void setOnMapChangeFinishedListener(C1958b c1958b) {
        this.f10839b.setOnMapChangedFinishedListener(c1958b);
    }

    /* renamed from: a */
    public Object m11653a(Point point) {
        return this.f10839b.mo3451b(point);
    }

    public String getElevations() {
        if (this.f10839b == null) {
            return null;
        }
        switch (this.f10841d) {
            case BaiDu:
                return this.f10839b.mo3444a(this.f10843f);
            case Google:
                return this.f10839b.mo3444a(this.f10844g);
            case MapBox:
                return this.f10839b.mo3444a(this.f10845h);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public void m11665a(C1950e c1950e) {
        if (this.f10839b != null && c1950e != null) {
            this.f10839b.mo3450a(c1950e);
        }
    }

    /* renamed from: a */
    public void m11658a(double d, double d2) {
        if (this.f10839b != null) {
            List arrayList;
            switch (this.f10841d) {
                case BaiDu:
                    arrayList = new ArrayList();
                    arrayList.add(new LatLng(d, d2));
                    this.f10839b.mo3457c(arrayList);
                    return;
                case Google:
                    arrayList = new ArrayList();
                    arrayList.add(new com.google.android.gms.maps.model.LatLng(d, d2));
                    this.f10839b.mo3457c(arrayList);
                    return;
                case MapBox:
                    arrayList = new ArrayList();
                    arrayList.add(new com.mapbox.mapboxsdk.geometry.LatLng(d, d2));
                    this.f10839b.mo3457c(arrayList);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m11666a(com.mapbox.mapboxsdk.geometry.LatLng latLng) {
        if (this.f10839b != null) {
            this.f10839b.mo3446a(latLng.getLatitude(), latLng.getLongitude());
        }
    }

    /* renamed from: a */
    public void m11659a(float f) {
        this.f10839b.mo3447a(f);
    }

    /* renamed from: b */
    public void m11674b(List<StagePointDTO> list, int i, int i2) {
        if (list != null && list.size() > 1 && this.f10839b != null) {
            com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr = new com.mapbox.mapboxsdk.geometry.LatLng[list.size()];
            for (int i3 = 0; i3 < latLngArr.length; i3++) {
                StagePointDTO stagePointDTO = (StagePointDTO) list.get(i3);
                com.mapbox.mapboxsdk.geometry.LatLng latLng = new com.mapbox.mapboxsdk.geometry.LatLng();
                latLng.setLatitude(stagePointDTO.getLatitude());
                latLng.setLongitude(stagePointDTO.getLongitude());
                latLngArr[i3] = latLng;
            }
            if (this.f10839b != null) {
                switch (this.f10841d) {
                    case BaiDu:
                        this.f10839b.mo3454b(m11646a(latLngArr), i, i2);
                        return;
                    case Google:
                        this.f10839b.mo3454b(m11649b(latLngArr), i, i2);
                        return;
                    case MapBox:
                        this.f10839b.mo3454b(m11651c(latLngArr), i, i2);
                        return;
                    default:
                        return;
                }
            }
            f10838a.error("mapBase is null!");
        }
    }

    public void scrollBy(int i, int i2) {
        if (this.f10839b != null) {
            switch (this.f10841d) {
                case BaiDu:
                    if (this.f10843f != null) {
                        this.f10839b.mo3448a(i, i2);
                        return;
                    } else {
                        f10838a.error("baiduPoints is null");
                        return;
                    }
                case Google:
                    if (this.f10844g != null) {
                        this.f10839b.mo3448a(i, i2);
                        return;
                    } else {
                        f10838a.error("googlePoints is null");
                        return;
                    }
                case MapBox:
                    if (this.f10845h != null) {
                        this.f10839b.mo3448a(i, i2);
                        return;
                    } else {
                        f10838a.error("mapBoxPoints is null");
                        return;
                    }
                default:
                    return;
            }
        }
        f10838a.error("mapBase is null!");
    }

    public float getZoomLevel() {
        return this.f10839b.getZoomLevel();
    }

    public float getMaxZoomLevel() {
        return this.f10839b.getMaxZoomLevel();
    }

    public float getMinZoomLevel() {
        return this.f10839b.getMinZoomLevel();
    }

    /* renamed from: i */
    public void m11681i() {
        this.f10839b.mo3460e();
    }

    /* renamed from: j */
    public void m11682j() {
        this.f10839b.mo3461f();
    }

    public void setOnMapStatusChangeListener(C1685b c1685b) {
        if (this.f10839b != null) {
            this.f10839b.setOnMapStatusChangeListener(c1685b);
        }
    }

    /* renamed from: k */
    public void m11683k() {
        this.f10839b.mo3462g();
    }

    /* renamed from: b */
    private LatLng m11647b(double d, double d2) {
        LatLng latLng = new LatLng(d, d2);
        switch (this.f10851n) {
            case 1:
                this.f10842e.coord(latLng);
                return this.f10842e.convert();
            case 2:
                com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(d, d2);
                return new LatLng(a.latitude, a.longitude);
            default:
                return latLng;
        }
    }

    /* renamed from: b */
    private List<LatLng> m11648b(List<C2411a> list) {
        if (this.f10842e == null || list == null || list.size() <= 0) {
            return null;
        }
        List<LatLng> arrayList = new ArrayList();
        for (C2411a c2411a : list) {
            double e = c2411a.m12229e();
            double f = c2411a.m12230f();
            if (e <= 90.0d && e >= -90.0d && f <= 180.0d && f >= -180.0d) {
                arrayList.add(m11647b(e, f));
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private List<com.google.android.gms.maps.model.LatLng> m11650c(List<C2411a> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<com.google.android.gms.maps.model.LatLng> arrayList = new ArrayList();
        for (C2411a c2411a : list) {
            double e = c2411a.m12229e();
            double f = c2411a.m12230f();
            if (e <= 90.0d && e >= -90.0d && f <= 180.0d && f >= -180.0d) {
                LatLng b = m11647b(e, f);
                arrayList.add(new com.google.android.gms.maps.model.LatLng(b.latitude, b.longitude));
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    private List<com.mapbox.mapboxsdk.geometry.LatLng> m11652d(List<C2411a> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<com.mapbox.mapboxsdk.geometry.LatLng> arrayList = new ArrayList();
        for (C2411a c2411a : list) {
            double e = c2411a.m12229e();
            double f = c2411a.m12230f();
            if (e <= 90.0d && e >= -90.0d && f <= 180.0d && f >= -180.0d) {
                LatLng b = m11647b(e, f);
                arrayList.add(new com.mapbox.mapboxsdk.geometry.LatLng(b.latitude, b.longitude));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private List<LatLng> m11646a(com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr) {
        if (this.f10842e == null || latLngArr == null || latLngArr.length <= 0) {
            return null;
        }
        List<LatLng> arrayList = new ArrayList();
        for (com.mapbox.mapboxsdk.geometry.LatLng latLng : latLngArr) {
            double latitude = latLng.getLatitude();
            double longitude = latLng.getLongitude();
            if (latitude <= 90.0d && latitude >= -90.0d && longitude <= 180.0d && longitude >= -180.0d) {
                this.f10842e.coord(new LatLng(latitude, longitude));
                arrayList.add(this.f10842e.convert());
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<com.google.android.gms.maps.model.LatLng> m11649b(com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr) {
        if (latLngArr == null || latLngArr.length <= 0) {
            return null;
        }
        List<com.google.android.gms.maps.model.LatLng> arrayList = new ArrayList();
        for (com.mapbox.mapboxsdk.geometry.LatLng latLng : latLngArr) {
            double latitude = latLng.getLatitude();
            double longitude = latLng.getLongitude();
            if (latitude <= 90.0d && latitude >= -90.0d && longitude <= 180.0d && longitude >= -180.0d) {
                com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(latitude, longitude);
                arrayList.add(new com.google.android.gms.maps.model.LatLng(a.latitude, a.longitude));
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private List<com.mapbox.mapboxsdk.geometry.LatLng> m11651c(com.mapbox.mapboxsdk.geometry.LatLng[] latLngArr) {
        if (latLngArr == null || latLngArr.length <= 0) {
            return null;
        }
        List<com.mapbox.mapboxsdk.geometry.LatLng> arrayList = new ArrayList();
        for (com.mapbox.mapboxsdk.geometry.LatLng latLng : latLngArr) {
            double latitude = latLng.getLatitude();
            double longitude = latLng.getLongitude();
            if (latitude <= 90.0d && latitude >= -90.0d && longitude <= 180.0d && longitude >= -180.0d) {
                arrayList.add(latLng);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo3440a() {
        if (this.f10846i != null) {
            this.f10846i.mo3330a();
        }
    }

    /* renamed from: b */
    public void mo3441b() {
        m11670a(this.f10847j);
    }

    /* renamed from: a */
    public void m11669a(List<C2411a> list, int i) {
        this.f10851n = i;
        m11668a((List) list);
    }

    public int getmCoordinateType() {
        return this.f10851n;
    }

    public void setmCoordinateType(int i) {
        this.f10851n = i;
    }
}
