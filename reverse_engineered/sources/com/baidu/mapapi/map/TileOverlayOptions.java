package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;

public final class TileOverlayOptions {
    /* renamed from: c */
    private static Bundle f3189c;
    /* renamed from: j */
    private static final String f3190j = TileOverlayOptions.class.getSimpleName();
    /* renamed from: a */
    private int f3191a = 20971520;
    /* renamed from: b */
    private TileProvider f3192b;
    /* renamed from: d */
    private int f3193d = 20;
    public int datasource;
    /* renamed from: e */
    private int f3194e = 3;
    /* renamed from: f */
    private int f3195f = 15786414;
    /* renamed from: g */
    private int f3196g = -20037726;
    /* renamed from: h */
    private int f3197h = -15786414;
    /* renamed from: i */
    private int f3198i = 20037726;
    public String urlString;

    public TileOverlayOptions() {
        f3189c = new Bundle();
        f3189c.putInt("rectr", this.f3195f);
        f3189c.putInt("rectb", this.f3196g);
        f3189c.putInt("rectl", this.f3197h);
        f3189c.putInt("rectt", this.f3198i);
    }

    /* renamed from: a */
    private TileOverlayOptions m4205a(int i, int i2) {
        this.f3193d = i;
        this.f3194e = i2;
        return this;
    }

    /* renamed from: a */
    Bundle m4206a() {
        f3189c.putString("url", this.urlString);
        f3189c.putInt("datasource", this.datasource);
        f3189c.putInt("maxDisplay", this.f3193d);
        f3189c.putInt("minDisplay", this.f3194e);
        f3189c.putInt("sdktiletmpmax", this.f3191a);
        return f3189c;
    }

    /* renamed from: a */
    TileOverlay m4207a(BaiduMap baiduMap) {
        return new TileOverlay(baiduMap, this.f3192b);
    }

    public TileOverlayOptions setMaxTileTmp(int i) {
        this.f3191a = i;
        return this;
    }

    public TileOverlayOptions setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bound can not be null");
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLngBounds.northeast);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(latLngBounds.southwest);
        double latitudeE6 = ll2mc.getLatitudeE6();
        double longitudeE6 = ll2mc2.getLongitudeE6();
        double latitudeE62 = ll2mc2.getLatitudeE6();
        double longitudeE62 = ll2mc.getLongitudeE6();
        if (latitudeE6 <= latitudeE62 || longitudeE62 <= longitudeE6) {
            Log.e(f3190j, "bounds is illegal, use default bounds");
        } else {
            f3189c.putInt("rectr", (int) longitudeE62);
            f3189c.putInt("rectb", (int) latitudeE62);
            f3189c.putInt("rectl", (int) longitudeE6);
            f3189c.putInt("rectt", (int) latitudeE6);
        }
        return this;
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        if (tileProvider == null) {
            return null;
        }
        if (tileProvider instanceof UrlTileProvider) {
            this.datasource = 1;
            String tileUrl = ((UrlTileProvider) tileProvider).getTileUrl();
            if (tileUrl != null && !"".equals(tileUrl) && tileUrl.contains("{x}") && tileUrl.contains("{y}") && tileUrl.contains("{z}")) {
                this.urlString = tileUrl;
            } else {
                Log.e(f3190j, "tile url template is illegal, must contains {x}、{y}、{z}");
                return null;
            }
        } else if (tileProvider instanceof FileTileProvider) {
            this.datasource = 0;
        } else {
            Log.e(f3190j, "tileProvider must be UrlTileProvider or FileTileProvider");
            return null;
        }
        this.f3192b = tileProvider;
        int maxDisLevel = tileProvider.getMaxDisLevel();
        int minDisLevel = tileProvider.getMinDisLevel();
        if (maxDisLevel > 21 || minDisLevel < 3) {
            Log.e(f3190j, "display level is illegal");
            return this;
        }
        m4205a(maxDisLevel, minDisLevel);
        return this;
    }
}
