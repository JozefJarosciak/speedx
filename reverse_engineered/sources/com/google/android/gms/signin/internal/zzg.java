package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzvx;
import com.google.android.gms.internal.zzvy;
import com.google.android.gms.signin.internal.zze.zza;

public class zzg extends zzk<zze> implements zzvx {
    private final boolean auv;
    private final Bundle auw;
    private final com.google.android.gms.common.internal.zzg tD;
    private Integer yb;

    public zzg(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzg zzg, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzg, connectionCallbacks, onConnectionFailedListener);
        this.auv = z;
        this.tD = zzg;
        this.auw = bundle;
        this.yb = zzg.zzasm();
    }

    public zzg(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzg zzg, zzvy zzvy, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, zzg, zza(zzg), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle zza(com.google.android.gms.common.internal.zzg zzg) {
        zzvy zzasl = zzg.zzasl();
        Integer zzasm = zzg.zzasm();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzg.getAccount());
        if (zzasm != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzasm.intValue());
        }
        if (zzasl != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzasl.zzbzl());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzasl.zzafr());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzasl.zzafu());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzasl.zzaft());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzasl.zzafv());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzasl.zzbzm());
            if (zzasl.zzbzn() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzasl.zzbzn().longValue());
            }
            if (zzasl.zzbzo() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzasl.zzbzo().longValue());
            }
        }
        return bundle;
    }

    private ResolveAccountRequest zzbzt() {
        Account zzaru = this.tD.zzaru();
        GoogleSignInAccount googleSignInAccount = null;
        if ("<<default account>>".equals(zzaru.name)) {
            googleSignInAccount = com.google.android.gms.auth.api.signin.internal.zzk.zzbc(getContext()).zzagj();
        }
        return new ResolveAccountRequest(zzaru, this.yb.intValue(), googleSignInAccount);
    }

    public void connect() {
        zza(new zzi(this));
    }

    public void zza(zzq zzq, boolean z) {
        try {
            ((zze) zzarw()).zza(zzq, this.yb.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzd zzd) {
        zzab.zzb((Object) zzd, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zze) zzarw()).zza(new SignInRequest(zzbzt()), zzd);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzd.zzb(new SignInResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    protected Bundle zzaeu() {
        if (!getContext().getPackageName().equals(this.tD.zzasi())) {
            this.auw.putString("com.google.android.gms.signin.internal.realClientPackageName", this.tD.zzasi());
        }
        return this.auw;
    }

    public boolean zzafk() {
        return this.auv;
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzkj(iBinder);
    }

    public void zzbzk() {
        try {
            ((zze) zzarw()).zzxs(this.yb.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    protected zze zzkj(IBinder iBinder) {
        return zza.zzki(iBinder);
    }

    protected String zzra() {
        return "com.google.android.gms.signin.service.START";
    }

    protected String zzrb() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
}
