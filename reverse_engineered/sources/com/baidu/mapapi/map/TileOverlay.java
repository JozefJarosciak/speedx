package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.common.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public final class TileOverlay {
    /* renamed from: b */
    private static final String f3182b = TileOverlay.class.getSimpleName();
    /* renamed from: f */
    private static int f3183f = 0;
    /* renamed from: a */
    BaiduMap f3184a;
    /* renamed from: c */
    private ExecutorService f3185c = Executors.newFixedThreadPool(1);
    /* renamed from: d */
    private HashMap<String, Tile> f3186d = new HashMap();
    /* renamed from: e */
    private HashSet<String> f3187e = new HashSet();
    /* renamed from: g */
    private TileProvider f3188g;

    public TileOverlay(BaiduMap baiduMap, TileProvider tileProvider) {
        this.f3184a = baiduMap;
        this.f3188g = tileProvider;
    }

    /* renamed from: a */
    private synchronized Tile m4194a(String str) {
        Tile tile;
        if (this.f3186d.containsKey(str)) {
            tile = (Tile) this.f3186d.get(str);
            this.f3186d.remove(str);
        } else {
            tile = null;
        }
        return tile;
    }

    /* renamed from: a */
    private synchronized void m4197a(String str, Tile tile) {
        this.f3186d.put(str, tile);
    }

    /* renamed from: b */
    private synchronized boolean m4199b(String str) {
        return this.f3187e.contains(str);
    }

    /* renamed from: c */
    private synchronized void m4201c(String str) {
        this.f3187e.add(str);
    }

    /* renamed from: a */
    Tile m4202a(int i, int i2, int i3) {
        String str = i + "_" + i2 + "_" + i3;
        Tile a = m4194a(str);
        if (a != null) {
            return a;
        }
        if (this.f3184a != null && f3183f == 0) {
            MapStatus mapStatus = this.f3184a.getMapStatus();
            f3183f = (((mapStatus.f2981a.f3687j.bottom - mapStatus.f2981a.f3687j.top) / 256) + 2) * (((mapStatus.f2981a.f3687j.right - mapStatus.f2981a.f3687j.left) / 256) + 2);
        }
        if (this.f3186d.size() > f3183f) {
            m4203a();
        }
        if (!(m4199b(str) || this.f3185c.isShutdown())) {
            try {
                m4201c(str);
                this.f3185c.execute(new C1137t(this, i, i2, i3, str));
            } catch (RejectedExecutionException e) {
                Log.e(f3182b, "ThreadPool excepiton");
            } catch (Exception e2) {
                Log.e(f3182b, "fileDir is not legal");
            }
        }
        return null;
    }

    /* renamed from: a */
    synchronized void m4203a() {
        Logger.logE(f3182b, "clearTaskSet");
        this.f3187e.clear();
        this.f3186d.clear();
    }

    /* renamed from: b */
    void m4204b() {
        this.f3185c.shutdownNow();
    }

    public boolean clearTileCache() {
        return this.f3184a.m4089b();
    }

    public void removeTileOverlay() {
        if (this.f3184a != null) {
            this.f3184a.m4088a(this);
        }
    }
}
