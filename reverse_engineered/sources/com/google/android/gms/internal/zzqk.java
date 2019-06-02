package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R$string;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.internal.zzz;

@Deprecated
public final class zzqk {
    private static zzqk uR;
    private static Object zzamp = new Object();
    private final String uS;
    private final Status uT;
    private final String uU;
    private final String uV;
    private final String uW;
    private final boolean uX;
    private final boolean uY;
    private final String zzcjj;

    zzqk(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R$string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.uY = z;
            z = z2;
        } else {
            this.uY = false;
        }
        this.uX = z;
        zzai zzai = new zzai(context);
        this.uU = zzai.getString("firebase_database_url");
        this.uW = zzai.getString("google_storage_bucket");
        this.uV = zzai.getString("gcm_defaultSenderId");
        this.uS = zzai.getString("google_api_key");
        Object zzcf = zzz.zzcf(context);
        if (zzcf == null) {
            zzcf = zzai.getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzcf)) {
            this.uT = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzcjj = null;
            return;
        }
        this.zzcjj = zzcf;
        this.uT = Status.sg;
    }

    zzqk(String str, boolean z) {
        this(str, z, null, null, null);
    }

    zzqk(String str, boolean z, String str2, String str3, String str4) {
        this.zzcjj = str;
        this.uS = null;
        this.uT = Status.sg;
        this.uX = z;
        this.uY = !z;
        this.uU = str2;
        this.uV = str4;
        this.uW = str3;
    }

    public static String zzaqk() {
        return zzgy("getGoogleAppId").zzcjj;
    }

    public static boolean zzaql() {
        return zzgy("isMeasurementExplicitlyDisabled").uY;
    }

    public static Status zzc(Context context, String str, boolean z) {
        Status zzgx;
        zzab.zzb(context, "Context must not be null.");
        zzab.zzh(str, "App ID must be nonempty.");
        synchronized (zzamp) {
            if (uR != null) {
                zzgx = uR.zzgx(str);
            } else {
                uR = new zzqk(str, z);
                zzgx = uR.uT;
            }
        }
        return zzgx;
    }

    public static Status zzcb(Context context) {
        Status status;
        zzab.zzb(context, "Context must not be null.");
        synchronized (zzamp) {
            if (uR == null) {
                uR = new zzqk(context);
            }
            status = uR.uT;
        }
        return status;
    }

    private static zzqk zzgy(String str) {
        zzqk zzqk;
        synchronized (zzamp) {
            if (uR == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzqk = uR;
        }
        return zzqk;
    }

    Status zzgx(String str) {
        if (this.zzcjj == null || this.zzcjj.equals(str)) {
            return Status.sg;
        }
        String str2 = this.zzcjj;
        return new Status(10, new StringBuilder(String.valueOf(str2).length() + 97).append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(str2).append("'.").toString());
    }
}
