package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public abstract class zzps extends zzqo implements OnCancelListener {
    protected boolean mStarted;
    protected final GoogleApiAvailability rX;
    protected boolean sB;
    private ConnectionResult sC;
    private int sD;
    private final Handler sE;

    private class zza implements Runnable {
        final /* synthetic */ zzps sF;

        private zza(zzps zzps) {
            this.sF = zzps;
        }

        @MainThread
        public void run() {
            if (!this.sF.mStarted) {
                return;
            }
            if (this.sF.sC.hasResolution()) {
                this.sF.va.startActivityForResult(GoogleApiActivity.zzb(this.sF.getActivity(), this.sF.sC.getResolution(), this.sF.sD, false), 1);
            } else if (this.sF.rX.isUserResolvableError(this.sF.sC.getErrorCode())) {
                this.sF.rX.zza(this.sF.getActivity(), this.sF.va, this.sF.sC.getErrorCode(), 2, this.sF);
            } else if (this.sF.sC.getErrorCode() == 18) {
                final Dialog zza = this.sF.rX.zza(this.sF.getActivity(), this.sF);
                this.sF.rX.zza(this.sF.getActivity().getApplicationContext(), new com.google.android.gms.internal.zzqj.zza(this) {
                    final /* synthetic */ zza sH;

                    public void zzaor() {
                        this.sH.sF.zzaoq();
                        if (zza.isShowing()) {
                            zza.dismiss();
                        }
                    }
                });
            } else {
                this.sF.zza(this.sF.sC, this.sF.sD);
            }
        }
    }

    protected zzps(zzqp zzqp) {
        this(zzqp, GoogleApiAvailability.getInstance());
    }

    zzps(zzqp zzqp, GoogleApiAvailability googleApiAvailability) {
        super(zzqp);
        this.sD = -1;
        this.sE = new Handler(Looper.getMainLooper());
        this.rX = googleApiAvailability;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r6, int r7, android.content.Intent r8) {
        /*
        r5 = this;
        r4 = 18;
        r2 = 13;
        r0 = 1;
        r1 = 0;
        switch(r6) {
            case 1: goto L_0x0027;
            case 2: goto L_0x0010;
            default: goto L_0x0009;
        };
    L_0x0009:
        r0 = r1;
    L_0x000a:
        if (r0 == 0) goto L_0x003d;
    L_0x000c:
        r5.zzaoq();
    L_0x000f:
        return;
    L_0x0010:
        r2 = r5.rX;
        r3 = r5.getActivity();
        r2 = r2.isGooglePlayServicesAvailable(r3);
        if (r2 != 0) goto L_0x0047;
    L_0x001c:
        r1 = r5.sC;
        r1 = r1.getErrorCode();
        if (r1 != r4) goto L_0x000a;
    L_0x0024:
        if (r2 != r4) goto L_0x000a;
    L_0x0026:
        goto L_0x000f;
    L_0x0027:
        r3 = -1;
        if (r7 == r3) goto L_0x000a;
    L_0x002a:
        if (r7 != 0) goto L_0x0009;
    L_0x002c:
        if (r8 == 0) goto L_0x0045;
    L_0x002e:
        r0 = "<<ResolutionFailureErrorDetail>>";
        r0 = r8.getIntExtra(r0, r2);
    L_0x0034:
        r2 = new com.google.android.gms.common.ConnectionResult;
        r3 = 0;
        r2.<init>(r0, r3);
        r5.sC = r2;
        goto L_0x0009;
    L_0x003d:
        r0 = r5.sC;
        r1 = r5.sD;
        r5.zza(r0, r1);
        goto L_0x000f;
    L_0x0045:
        r0 = r2;
        goto L_0x0034;
    L_0x0047:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzps.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), this.sD);
        zzaoq();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.sB = bundle.getBoolean("resolving_error", false);
            if (this.sB) {
                this.sD = bundle.getInt("failed_client_id", -1);
                this.sC = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.sB);
        if (this.sB) {
            bundle.putInt("failed_client_id", this.sD);
            bundle.putInt("failed_status", this.sC.getErrorCode());
            bundle.putParcelable("failed_resolution", this.sC.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    protected abstract void zzaol();

    protected void zzaoq() {
        this.sD = -1;
        this.sB = false;
        this.sC = null;
        zzaol();
    }

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.sB) {
            this.sB = true;
            this.sD = i;
            this.sC = connectionResult;
            this.sE.post(new zza());
        }
    }
}
