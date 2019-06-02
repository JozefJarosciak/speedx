package com.loopj.android.http;

class AssertUtils {
    private AssertUtils() {
    }

    public static void asserts(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
