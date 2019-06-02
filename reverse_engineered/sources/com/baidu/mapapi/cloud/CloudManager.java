package com.baidu.mapapi.cloud;

import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comjni.map.cloud.C1284a;
import com.baidu.platform.comjni.map.cloud.ICloudCenter;

public class CloudManager {
    /* renamed from: a */
    private static final String f2770a = CloudManager.class.getSimpleName();
    /* renamed from: b */
    private static CloudManager f2771b;
    /* renamed from: c */
    private ICloudCenter f2772c;

    /* renamed from: a */
    private boolean m4029a(BaseCloudSearchInfo baseCloudSearchInfo) {
        if (baseCloudSearchInfo == null) {
            return false;
        }
        String a = baseCloudSearchInfo.mo2610a();
        return (a == null || a.equals("")) ? false : this.f2772c.mo2679a(a);
    }

    public static CloudManager getInstance() {
        if (f2771b == null) {
            f2771b = new CloudManager();
        }
        return f2771b;
    }

    public boolean boundSearch(BoundSearchInfo boundSearchInfo) {
        return m4029a(boundSearchInfo);
    }

    public void destroy() {
        if (this.f2772c != null) {
            this.f2772c = null;
            BMapManager.destroy();
        }
    }

    public boolean detailSearch(DetailSearchInfo detailSearchInfo) {
        if (detailSearchInfo == null) {
            return false;
        }
        String a = detailSearchInfo.mo2610a();
        return (a == null || a.equals("")) ? false : this.f2772c.mo2680b(a);
    }

    public void init(CloudListener cloudListener) {
        if (this.f2772c == null) {
            BMapManager.init();
            this.f2772c = new C1284a();
            this.f2772c.mo2678a(cloudListener);
        }
    }

    public boolean localSearch(LocalSearchInfo localSearchInfo) {
        return m4029a(localSearchInfo);
    }

    public boolean nearbySearch(NearbySearchInfo nearbySearchInfo) {
        return m4029a(nearbySearchInfo);
    }

    public boolean rgcSearch(CloudRgcInfo cloudRgcInfo) {
        if (cloudRgcInfo == null) {
            return false;
        }
        String a = cloudRgcInfo.m4032a();
        return (a == null || a.equals("")) ? false : this.f2772c.mo2681c(a);
    }
}
