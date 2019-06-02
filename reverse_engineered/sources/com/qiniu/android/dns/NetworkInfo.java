package com.qiniu.android.dns;

public final class NetworkInfo {
    /* renamed from: a */
    public static final NetworkInfo f15103a = new NetworkInfo(NetSatus.NO_NETWORK, 0);
    /* renamed from: b */
    public static final NetworkInfo f15104b = new NetworkInfo(NetSatus.WIFI, 0);
    /* renamed from: c */
    public final int f15105c;
    /* renamed from: d */
    public final NetSatus f15106d;

    public enum NetSatus {
        NO_NETWORK,
        WIFI,
        MOBILE
    }

    public NetworkInfo(NetSatus netSatus, int i) {
        this.f15106d = netSatus;
        this.f15105c = i;
    }
}
