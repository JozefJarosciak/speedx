package com.baidu.mapapi.map;

import android.util.Log;

/* renamed from: com.baidu.mapapi.map.t */
class C1137t implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f3274a;
    /* renamed from: b */
    final /* synthetic */ int f3275b;
    /* renamed from: c */
    final /* synthetic */ int f3276c;
    /* renamed from: d */
    final /* synthetic */ String f3277d;
    /* renamed from: e */
    final /* synthetic */ TileOverlay f3278e;

    C1137t(TileOverlay tileOverlay, int i, int i2, int i3, String str) {
        this.f3278e = tileOverlay;
        this.f3274a = i;
        this.f3275b = i2;
        this.f3276c = i3;
        this.f3277d = str;
    }

    public void run() {
        Tile tile = ((FileTileProvider) this.f3278e.f3188g).getTile(this.f3274a, this.f3275b, this.f3276c);
        if (tile == null) {
            Log.e(TileOverlay.f3182b, "FileTile pic is null");
        } else if (tile.width == 256 && tile.height == 256) {
            this.f3278e.m4197a(this.f3274a + "_" + this.f3275b + "_" + this.f3276c, tile);
        } else {
            Log.e(TileOverlay.f3182b, "FileTile pic must be 256 * 256");
        }
        this.f3278e.f3187e.remove(this.f3277d);
    }
}
