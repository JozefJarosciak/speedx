package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import java.io.Serializable;

public class PullProxyFactory {
    private static final Long defaultLong = Long.valueOf(-1);

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.PullProxyFactory$1 */
    static class C28341 implements Pageable<Long> {
        C28341() {
        }

        public Long initMaxId(String str) {
            return Long.valueOf(0);
        }

        public boolean initIsLastPage(Long l) {
            return l.longValue() <= 0;
        }

        public Long chooseMaxId(Long l, Long l2) {
            return (l2 == null || l2.longValue() <= 0) ? l : l2;
        }

        public void cacheData(String str, Serializable serializable, long j, Long l) {
        }

        public Long defValue() {
            return PullProxyFactory.defaultLong;
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.pulltorefresh.PullProxyFactory$2 */
    static class C28352 implements Pageable<String> {
        C28352() {
        }

        public String initMaxId(String str) {
            return "";
        }

        public boolean initIsLastPage(String str) {
            return str == null;
        }

        public String chooseMaxId(String str, String str2) {
            return str2 != null ? str2 : str;
        }

        public void cacheData(String str, Serializable serializable, long j, String str2) {
        }

        public String defValue() {
            return null;
        }
    }

    public static <T> Pageable<T> getPageable(Class<T> cls) {
        if (cls == Long.class) {
            return getDefaultLongPageable();
        }
        if (cls == String.class) {
            return getDefaultStringPageable();
        }
        return null;
    }

    public static Pageable<Long> getDefaultLongPageable() {
        return new C28341();
    }

    public static Pageable<String> getDefaultStringPageable() {
        return new C28352();
    }
}
