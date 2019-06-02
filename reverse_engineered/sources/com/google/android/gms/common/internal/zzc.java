package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<AuthAccountRequest> {
    static void zza(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.wY, false);
        zzb.zza(parcel, 3, authAccountRequest.ro, i, false);
        zzb.zza(parcel, 4, authAccountRequest.wZ, false);
        zzb.zza(parcel, 5, authAccountRequest.xa, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfx(i);
    }

    public AuthAccountRequest zzcd(Parcel parcel) {
        Integer num = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    iBinder = zza.zzr(parcel, zzck);
                    break;
                case 3:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzck, Scope.CREATOR);
                    break;
                case 4:
                    num2 = zza.zzh(parcel, zzck);
                    break;
                case 5:
                    num = zza.zzh(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new AuthAccountRequest(i, iBinder, scopeArr, num2, num);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AuthAccountRequest[] zzfx(int i) {
        return new AuthAccountRequest[i];
    }
}
