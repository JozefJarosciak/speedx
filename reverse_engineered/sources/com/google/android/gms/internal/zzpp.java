package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzpp extends zzps {
    private final SparseArray<zza> ss = new SparseArray();

    private class zza implements OnConnectionFailedListener {
        public final int st;
        public final GoogleApiClient su;
        public final OnConnectionFailedListener sv;
        final /* synthetic */ zzpp sw;

        public zza(zzpp zzpp, int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.sw = zzpp;
            this.st = i;
            this.su = googleApiClient;
            this.sv = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.st);
            printWriter.println(":");
            this.su.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            this.sw.zzb(connectionResult, this.st);
        }

        public void zzaom() {
            this.su.unregisterConnectionFailedListener(this);
            this.su.disconnect();
        }
    }

    private zzpp(zzqp zzqp) {
        super(zzqp);
        this.va.zza("AutoManageHelper", (zzqo) this);
    }

    public static zzpp zza(zzqn zzqn) {
        zzqp zzc = zzqo.zzc(zzqn);
        zzpp zzpp = (zzpp) zzc.zza("AutoManageHelper", zzpp.class);
        return zzpp != null ? zzpp : new zzpp(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.ss.size(); i++) {
            ((zza) this.ss.valueAt(i)).dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.ss);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (!this.sB) {
            for (int i = 0; i < this.ss.size(); i++) {
                ((zza) this.ss.valueAt(i)).su.connect();
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.ss.size(); i++) {
            ((zza) this.ss.valueAt(i)).su.disconnect();
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzab.zzb((Object) googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzab.zza(this.ss.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + " " + this.mStarted + " " + this.sB);
        this.ss.put(i, new zza(this, i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.sB) {
            String valueOf = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 11).append("connecting ").append(valueOf).toString());
            googleApiClient.connect();
        }
    }

    protected void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza = (zza) this.ss.get(i);
        if (zza != null) {
            zzff(i);
            OnConnectionFailedListener onConnectionFailedListener = zza.sv;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    protected void zzaol() {
        for (int i = 0; i < this.ss.size(); i++) {
            ((zza) this.ss.valueAt(i)).su.connect();
        }
    }

    public void zzff(int i) {
        zza zza = (zza) this.ss.get(i);
        this.ss.remove(i);
        if (zza != null) {
            zza.zzaom();
        }
    }
}
