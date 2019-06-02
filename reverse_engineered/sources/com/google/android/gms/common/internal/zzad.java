package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzad implements Creator<ResolveAccountResponse> {
    static void zza(ResolveAccountResponse resolveAccountResponse, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.wY, false);
        zzb.zza(parcel, 3, resolveAccountResponse.zzatd(), i, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzate());
        zzb.zza(parcel, 5, resolveAccountResponse.zzatf());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzch(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgf(i);
    }

    public ResolveAccountResponse zzch(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
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
                    connectionResult = (ConnectionResult) zza.zza(parcel, zzck, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ResolveAccountResponse[] zzgf(int i) {
        return new ResolveAccountResponse[i];
    }
}
