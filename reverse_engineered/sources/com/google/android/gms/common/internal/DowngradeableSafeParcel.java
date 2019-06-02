package com.google.android.gms.common.internal;

import ch.qos.logback.core.joran.action.ActionConst;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable {
    private static final Object ye = new Object();
    private static ClassLoader yf = null;
    private static Integer yg = null;
    private boolean yh = false;

    protected static ClassLoader zzaso() {
        synchronized (ye) {
        }
        return null;
    }

    protected static Integer zzasp() {
        synchronized (ye) {
        }
        return null;
    }

    private static boolean zzd(Class<?> cls) {
        boolean z = false;
        try {
            z = SafeParcelable.NULL.equals(cls.getField(ActionConst.NULL).get(null));
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e2) {
        }
        return z;
    }

    protected static boolean zzhl(String str) {
        ClassLoader zzaso = zzaso();
        if (zzaso == null) {
            return true;
        }
        try {
            return zzd(zzaso.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean zzasq() {
        return false;
    }
}
