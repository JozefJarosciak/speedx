package com.facebook.stetho.inspector.elements;

import ch.qos.logback.core.CoreConstants;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import java.util.HashMap;
import java.util.Map;

public abstract class Descriptor implements NodeDescriptor {
    private Host mHost;

    public interface Host extends ThreadBound {
        Descriptor getDescriptor(Object obj);

        void onAttributeModified(Object obj, String str, String str2);

        void onAttributeRemoved(Object obj, String str);
    }

    protected Descriptor() {
    }

    final void initialize(Host host) {
        Util.throwIfNull(host);
        Util.throwIfNotNull(this.mHost);
        this.mHost = host;
    }

    final boolean isInitialized() {
        return this.mHost != null;
    }

    protected final Host getHost() {
        return this.mHost;
    }

    public final boolean checkThreadAccess() {
        return getHost().checkThreadAccess();
    }

    public final void verifyThreadAccess() {
        getHost().verifyThreadAccess();
    }

    public final <V> V postAndWait(UncheckedCallable<V> uncheckedCallable) {
        return getHost().postAndWait((UncheckedCallable) uncheckedCallable);
    }

    public final void postAndWait(Runnable runnable) {
        getHost().postAndWait(runnable);
    }

    public final void postDelayed(Runnable runnable, long j) {
        getHost().postDelayed(runnable, j);
    }

    public final void removeCallbacks(Runnable runnable) {
        getHost().removeCallbacks(runnable);
    }

    protected static Map<String, String> parseSetAttributesAsTextArg(String str) {
        String str2 = "";
        String str3 = "";
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> hashMap = new HashMap();
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '=') {
                str3 = stringBuilder.toString();
                stringBuilder.setLength(0);
            } else if (charAt == CoreConstants.DOUBLE_QUOTE_CHAR) {
                if (i != 0) {
                    str2 = stringBuilder.toString();
                    stringBuilder.setLength(0);
                }
                i = i == 0 ? 1 : 0;
            } else if (charAt == ' ' && i == 0) {
                hashMap.put(str3, str2);
            } else {
                stringBuilder.append(charAt);
            }
        }
        if (!(str3.isEmpty() || str2.isEmpty())) {
            hashMap.put(str3, str2);
        }
        return hashMap;
    }
}
