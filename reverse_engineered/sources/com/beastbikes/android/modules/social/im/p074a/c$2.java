package com.beastbikes.android.modules.social.im.p074a;

import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$2 */
/* synthetic */ class c$2 {
    /* renamed from: a */
    static final /* synthetic */ int[] f11129a = new int[RongIMClient$ConnectionStatusListener$ConnectionStatus.values().length];

    static {
        try {
            f11129a[RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f11129a[RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f11129a[RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f11129a[RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f11129a[RongIMClient$ConnectionStatusListener$ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
