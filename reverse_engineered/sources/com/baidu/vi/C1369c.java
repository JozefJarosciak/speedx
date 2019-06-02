package com.baidu.vi;

import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/* renamed from: com.baidu.vi.c */
public class C1369c {
    /* renamed from: a */
    public String f3987a;
    /* renamed from: b */
    public int f3988b;
    /* renamed from: c */
    public int f3989c;

    /* renamed from: com.baidu.vi.c$1 */
    static /* synthetic */ class C13681 {
        /* renamed from: a */
        static final /* synthetic */ int[] f3986a = new int[State.values().length];

        static {
            try {
                f3986a[State.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3986a[State.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3986a[State.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3986a[State.DISCONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3986a[State.SUSPENDED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public C1369c(NetworkInfo networkInfo) {
        this.f3987a = networkInfo.getTypeName();
        this.f3988b = networkInfo.getType();
        switch (C13681.f3986a[networkInfo.getState().ordinal()]) {
            case 1:
                this.f3989c = 2;
                return;
            case 2:
                this.f3989c = 1;
                return;
            default:
                this.f3989c = 0;
                return;
        }
    }
}
