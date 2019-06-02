package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public final class Verify {
    public static void verify(boolean z) {
        if (!z) {
            throw new VerifyException();
        }
    }

    public static void verify(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, objArr));
        }
    }

    public static <T> T verifyNotNull(T t) {
        return verifyNotNull(t, "expected a non-null reference", new Object[0]);
    }

    public static <T> T verifyNotNull(T t, String str, Object... objArr) {
        verify(t != null, str, objArr);
        return t;
    }

    private Verify() {
    }
}
