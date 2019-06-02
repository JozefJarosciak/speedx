package com.google.android.gms.location.places.internal;

import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzafb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zzt extends zzc {
    private final String TAG = "SafeDataBufferRef";

    public zzt(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    protected <E extends SafeParcelable> E zza(String str, Creator<E> creator) {
        byte[] zzc = zzc(str, null);
        return zzc == null ? null : com.google.android.gms.common.internal.safeparcel.zzc.zza(zzc, creator);
    }

    protected <E extends SafeParcelable> List<E> zza(String str, Creator<E> creator, List<E> list) {
        byte[] zzc = zzc(str, null);
        if (zzc == null) {
            return list;
        }
        try {
            zzafb zzas = zzafb.zzas(zzc);
            if (zzas.aMK == null) {
                return list;
            }
            List<E> arrayList = new ArrayList(zzas.aMK.length);
            for (byte[] zza : zzas.aMK) {
                arrayList.add(com.google.android.gms.common.internal.safeparcel.zzc.zza(zza, creator));
            }
            return arrayList;
        } catch (Throwable e) {
            if (!Log.isLoggable("SafeDataBufferRef", 6)) {
                return list;
            }
            Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            return list;
        }
    }

    protected List<Integer> zza(String str, List<Integer> list) {
        byte[] zzc = zzc(str, null);
        if (zzc == null) {
            return list;
        }
        try {
            zzafb zzas = zzafb.zzas(zzc);
            if (zzas.aMJ == null) {
                return list;
            }
            List<Integer> arrayList = new ArrayList(zzas.aMJ.length);
            for (int valueOf : zzas.aMJ) {
                arrayList.add(Integer.valueOf(valueOf));
            }
            return arrayList;
        } catch (Throwable e) {
            if (!Log.isLoggable("SafeDataBufferRef", 6)) {
                return list;
            }
            Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            return list;
        }
    }

    protected String zzao(String str, String str2) {
        return (!zzhf(str) || zzhh(str)) ? str2 : getString(str);
    }

    protected float zzb(String str, float f) {
        return (!zzhf(str) || zzhh(str)) ? f : getFloat(str);
    }

    protected List<String> zzb(String str, List<String> list) {
        byte[] zzc = zzc(str, null);
        if (zzc != null) {
            try {
                zzafb zzas = zzafb.zzas(zzc);
                if (zzas.aMI != null) {
                    list = Arrays.asList(zzas.aMI);
                }
            } catch (Throwable e) {
                if (Log.isLoggable("SafeDataBufferRef", 6)) {
                    Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
                }
            }
        }
        return list;
    }

    protected byte[] zzc(String str, byte[] bArr) {
        return (!zzhf(str) || zzhh(str)) ? bArr : getByteArray(str);
    }

    protected boolean zzn(String str, boolean z) {
        return (!zzhf(str) || zzhh(str)) ? z : getBoolean(str);
    }

    protected int zzx(String str, int i) {
        return (!zzhf(str) || zzhh(str)) ? i : getInteger(str);
    }
}
