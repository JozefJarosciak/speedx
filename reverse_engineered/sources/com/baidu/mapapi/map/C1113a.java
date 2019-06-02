package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.Overlay.C1109a;

/* renamed from: com.baidu.mapapi.map.a */
class C1113a implements C1109a {
    /* renamed from: a */
    final /* synthetic */ BaiduMap f3239a;

    C1113a(BaiduMap baiduMap) {
        this.f3239a = baiduMap;
    }

    /* renamed from: a */
    public void mo2623a(Overlay overlay) {
        if (overlay != null && this.f3239a.f2852k.contains(overlay)) {
            Bundle a = overlay.mo2621a();
            if (this.f3239a.f2850i != null) {
                this.f3239a.f2850i.m4710d(a);
            }
            this.f3239a.f2852k.remove(overlay);
        }
        if (overlay != null && this.f3239a.f2854m.contains(overlay)) {
            this.f3239a.f2854m.remove(overlay);
        }
        if (overlay != null && this.f3239a.f2853l.contains(overlay)) {
            Marker marker = (Marker) overlay;
            if (marker.f3048n != null) {
                this.f3239a.f2853l.remove(marker);
                if (this.f3239a.f2853l.size() == 0 && this.f3239a.f2850i != null) {
                    this.f3239a.f2850i.m4703b(false);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo2624b(Overlay overlay) {
        if (overlay != null && this.f3239a.f2852k.contains(overlay)) {
            if (overlay instanceof Marker) {
                Marker marker = (Marker) overlay;
                if (!(marker.f3048n == null || marker.f3048n.size() == 0)) {
                    if (this.f3239a.f2853l.contains(marker)) {
                        this.f3239a.f2853l.remove(marker);
                    }
                    this.f3239a.f2853l.add(marker);
                    if (this.f3239a.f2850i != null) {
                        this.f3239a.f2850i.m4703b(true);
                    }
                }
            }
            Bundle bundle = new Bundle();
            if (this.f3239a.f2850i != null) {
                this.f3239a.f2850i.m4704c(overlay.mo2613a(bundle));
            }
        }
        if (this.f3239a.f2854m.contains(overlay)) {
            this.f3239a.f2854m.remove(overlay);
        }
        if (overlay instanceof Marker) {
            this.f3239a.f2854m.add((Marker) overlay);
        }
    }
}
