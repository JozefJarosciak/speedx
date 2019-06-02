package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class CollectPreconditions {
    CollectPreconditions() {
    }

    static void checkEntryNotNull(Object obj, Object obj2) {
        String valueOf;
        if (obj == null) {
            valueOf = String.valueOf(String.valueOf(obj2));
            throw new NullPointerException(new StringBuilder(valueOf.length() + 24).append("null key in entry: null=").append(valueOf).toString());
        } else if (obj2 == null) {
            valueOf = String.valueOf(String.valueOf(obj));
            throw new NullPointerException(new StringBuilder(valueOf.length() + 26).append("null value in entry: ").append(valueOf).append("=null").toString());
        }
    }

    static int checkNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 40).append(valueOf).append(" cannot be negative but was: ").append(i).toString());
    }

    static void checkRemove(boolean z) {
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
    }
}
