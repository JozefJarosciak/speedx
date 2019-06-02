package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzc.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzae {
    private static Context ahZ;
    private static zzc aia;

    private static Context getRemoteContext(Context context) {
        if (ahZ == null) {
            if (zzbqd()) {
                ahZ = context.getApplicationContext();
            } else {
                ahZ = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return ahZ;
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return zzf(((ClassLoader) zzab.zzaa(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            String str2 = "Unable to find dynamic class ";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public static boolean zzbqd() {
        return false;
    }

    private static Class<?> zzbqe() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static zzc zzdk(Context context) throws GooglePlayServicesNotAvailableException {
        zzab.zzaa(context);
        if (aia != null) {
            return aia;
        }
        zzdl(context);
        aia = zzdm(context);
        try {
            aia.zzh(zze.zzae(getRemoteContext(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return aia;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static void zzdl(Context context) throws GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (isGooglePlayServicesAvailable) {
            case 0:
                return;
            default:
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static zzc zzdm(Context context) {
        if (zzbqd()) {
            Log.i(zzae.class.getSimpleName(), "Making Creator statically");
            return (zzc) zzf(zzbqe());
        }
        Log.i(zzae.class.getSimpleName(), "Making Creator dynamically");
        return zza.zzhh((IBinder) zza(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static <T> T zzf(Class<?> cls) {
        String str;
        String valueOf;
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            str = "Unable to instantiate the dynamic class ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (IllegalAccessException e2) {
            str = "Unable to call the default constructor of ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }
}
