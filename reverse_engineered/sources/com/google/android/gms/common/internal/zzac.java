package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzac implements Creator<ResolveAccountRequest> {
    static void zza(ResolveAccountRequest resolveAccountRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountRequest.getAccount(), i, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zza(parcel, 4, resolveAccountRequest.zzatb(), i, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcg(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzge(i);
    }

    public ResolveAccountRequest zzcg(Parcel parcel) {
        GoogleSignInAccount googleSignInAccount = null;
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        Account account = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int i3;
            Account account2;
            int zzg;
            GoogleSignInAccount googleSignInAccount2;
            int zzck = zza.zzck(parcel);
            GoogleSignInAccount googleSignInAccount3;
            switch (zza.zzgi(zzck)) {
                case 1:
                    googleSignInAccount3 = googleSignInAccount;
                    i3 = i;
                    account2 = account;
                    zzg = zza.zzg(parcel, zzck);
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                case 2:
                    zzg = i2;
                    int i4 = i;
                    account2 = (Account) zza.zza(parcel, zzck, Account.CREATOR);
                    googleSignInAccount2 = googleSignInAccount;
                    i3 = i4;
                    break;
                case 3:
                    account2 = account;
                    zzg = i2;
                    googleSignInAccount3 = googleSignInAccount;
                    i3 = zza.zzg(parcel, zzck);
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                case 4:
                    googleSignInAccount2 = (GoogleSignInAccount) zza.zza(parcel, zzck, GoogleSignInAccount.CREATOR);
                    i3 = i;
                    account2 = account;
                    zzg = i2;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    googleSignInAccount2 = googleSignInAccount;
                    i3 = i;
                    account2 = account;
                    zzg = i2;
                    break;
            }
            i2 = zzg;
            account = account2;
            i = i3;
            googleSignInAccount = googleSignInAccount2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new ResolveAccountRequest(i2, account, i, googleSignInAccount);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ResolveAccountRequest[] zzge(int i) {
        return new ResolveAccountRequest[i];
    }
}
