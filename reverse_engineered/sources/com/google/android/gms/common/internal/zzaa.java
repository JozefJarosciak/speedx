package com.google.android.gms.common.internal;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzaa {

    public static final class zza {
        private final List<String> yU;
        private final Object zzcmy;

        private zza(Object obj) {
            this.zzcmy = zzab.zzaa(obj);
            this.yU = new ArrayList();
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.zzcmy.getClass().getSimpleName()).append(CoreConstants.CURLY_LEFT);
            int size = this.yU.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.yU.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append(CoreConstants.CURLY_RIGHT).toString();
        }

        public zza zzg(String str, Object obj) {
            List list = this.yU;
            String str2 = (String) zzab.zzaa(str);
            String valueOf = String.valueOf(String.valueOf(obj));
            list.add(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(valueOf).length()).append(str2).append(SimpleComparison.EQUAL_TO_OPERATION).append(valueOf).toString());
            return this;
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static zza zzz(Object obj) {
        return new zza(obj);
    }
}
