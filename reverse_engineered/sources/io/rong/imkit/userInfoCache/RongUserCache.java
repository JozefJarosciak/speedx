package io.rong.imkit.userInfoCache;

import java.util.LinkedHashMap;

class RongUserCache<K, V> {
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int size;

    RongUserCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i;
        this.map = new LinkedHashMap(0, 0.75f, true);
    }

    void clear() {
        synchronized (this) {
            this.map.clear();
        }
    }

    V get(K k) {
        if (k == null) {
            return null;
        }
        V v;
        synchronized (this) {
            v = this.map.get(k);
        }
        return v;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    V put(K r4, V r5) {
        /*
        r3 = this;
        if (r4 == 0) goto L_0x0004;
    L_0x0002:
        if (r5 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.NullPointerException;
        r1 = "key == null || value == null";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        monitor-enter(r3);
        r0 = r3.size;	 Catch:{ all -> 0x004e }
        r0 = r0 + 1;
        r3.size = r0;	 Catch:{ all -> 0x004e }
        r0 = r3.map;	 Catch:{ all -> 0x004e }
        r1 = r0.put(r4, r5);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0021;
    L_0x001b:
        r0 = r3.size;	 Catch:{ all -> 0x004e }
        r0 = r0 + -1;
        r3.size = r0;	 Catch:{ all -> 0x004e }
    L_0x0021:
        r0 = r3.size;	 Catch:{ all -> 0x004e }
        r2 = r3.maxSize;	 Catch:{ all -> 0x004e }
        if (r0 <= r2) goto L_0x004b;
    L_0x0027:
        r0 = r3.map;	 Catch:{ all -> 0x004e }
        r0 = r0.entrySet();	 Catch:{ all -> 0x004e }
        r0 = r0.iterator();	 Catch:{ all -> 0x004e }
        r0 = r0.next();	 Catch:{ all -> 0x004e }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x003c;
    L_0x0039:
        monitor-exit(r3);	 Catch:{ all -> 0x004e }
        r0 = r1;
    L_0x003b:
        return r0;
    L_0x003c:
        r2 = r3.map;	 Catch:{ all -> 0x004e }
        r0 = r0.getKey();	 Catch:{ all -> 0x004e }
        r2.remove(r0);	 Catch:{ all -> 0x004e }
        r0 = r3.size;	 Catch:{ all -> 0x004e }
        r0 = r0 + -1;
        r3.size = r0;	 Catch:{ all -> 0x004e }
    L_0x004b:
        monitor-exit(r3);	 Catch:{ all -> 0x004e }
        r0 = r1;
        goto L_0x003b;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x004e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.userInfoCache.RongUserCache.put(java.lang.Object, java.lang.Object):V");
    }
}
