package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<SignInResponse> {
    static void zza(SignInResponse signInResponse, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, signInResponse.mVersionCode);
        zzb.zza(parcel, 2, signInResponse.zzatd(), i, false);
        zzb.zza(parcel, 3, signInResponse.zzbzv(), i, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzqq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzxv(i);
    }

    public SignInResponse zzqq(Parcel parcel) {
        ResolveAccountResponse resolveAccountResponse = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < zzcl) {
            ConnectionResult connectionResult2;
            int zzg;
            ResolveAccountResponse resolveAccountResponse2;
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    ResolveAccountResponse resolveAccountResponse3 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    zzg = zza.zzg(parcel, zzck);
                    resolveAccountResponse2 = resolveAccountResponse3;
                    break;
                case 2:
                    zzg = i;
                    ConnectionResult connectionResult3 = (ConnectionResult) zza.zza(parcel, zzck, ConnectionResult.CREATOR);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult3;
                    break;
                case 3:
                    resolveAccountResponse2 = (ResolveAccountResponse) zza.zza(parcel, zzck, ResolveAccountResponse.CREATOR);
                    connectionResult2 = connectionResult;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    zzg = i;
                    break;
            }
            i = zzg;
            connectionResult = connectionResult2;
            resolveAccountResponse = resolveAccountResponse2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new SignInResponse(i, connectionResult, resolveAccountResponse);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public SignInResponse[] zzxv(int i) {
        return new SignInResponse[i];
    }
}
