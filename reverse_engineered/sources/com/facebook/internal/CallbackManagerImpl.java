package com.facebook.internal;

import com.alipay.sdk.authjs.C0840a;
import com.facebook.C1472c;
import java.util.HashMap;
import java.util.Map;

public final class CallbackManagerImpl {
    /* renamed from: a */
    private static Map<Integer, C2992a> f13525a = new HashMap();

    public enum RequestCodeOffset {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7),
        DeviceShare(8);
        
        private final int offset;

        private RequestCodeOffset(int i) {
            this.offset = i;
        }

        public int toRequestCode() {
            return C1472c.m() + this.offset;
        }
    }

    /* renamed from: com.facebook.internal.CallbackManagerImpl$a */
    public interface C2992a {
    }

    /* renamed from: a */
    public static synchronized void m14522a(int i, C2992a c2992a) {
        synchronized (CallbackManagerImpl.class) {
            C3049t.m14790a((Object) c2992a, C0840a.f2027c);
            if (!f13525a.containsKey(Integer.valueOf(i))) {
                f13525a.put(Integer.valueOf(i), c2992a);
            }
        }
    }
}
