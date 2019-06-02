package com.google.android.gms.common.internal;

import java.util.Iterator;

public class zzy {
    private final String separator;

    private zzy(String str) {
        this.separator = str;
    }

    public static zzy zzhr(String str) {
        return new zzy(str);
    }

    public final String zza(Iterable<?> iterable) {
        return zza(new StringBuilder(), iterable).toString();
    }

    public final StringBuilder zza(StringBuilder stringBuilder, Iterable<?> iterable) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            stringBuilder.append(zzy(it.next()));
            while (it.hasNext()) {
                stringBuilder.append(this.separator);
                stringBuilder.append(zzy(it.next()));
            }
        }
        return stringBuilder;
    }

    CharSequence zzy(Object obj) {
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
