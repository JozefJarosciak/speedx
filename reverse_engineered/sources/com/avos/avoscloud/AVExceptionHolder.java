package com.avos.avoscloud;

public final class AVExceptionHolder {
    private static final ThreadLocal<AVException> local = new C09121();

    /* renamed from: com.avos.avoscloud.AVExceptionHolder$1 */
    static class C09121 extends ThreadLocal<AVException> {
        C09121() {
        }

        protected AVException initialValue() {
            return null;
        }
    }

    public static final void add(AVException aVException) {
        local.set(aVException);
    }

    public static final boolean exists() {
        return local.get() != null;
    }

    public static final AVException remove() {
        AVException aVException = (AVException) local.get();
        local.remove();
        return aVException;
    }
}
