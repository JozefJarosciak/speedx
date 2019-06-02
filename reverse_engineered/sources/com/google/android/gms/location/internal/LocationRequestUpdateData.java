package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zze;
import com.google.android.gms.location.zze.zza;

public class LocationRequestUpdateData extends AbstractSafeParcelable {
    public static final zzn CREATOR = new zzn();
    int adX;
    LocationRequestInternal adY;
    zze adZ;
    zzd aea;
    zzg aeb;
    PendingIntent mPendingIntent;
    private final int mVersionCode;

    LocationRequestUpdateData(int i, int i2, LocationRequestInternal locationRequestInternal, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        zzg zzg = null;
        this.mVersionCode = i;
        this.adX = i2;
        this.adY = locationRequestInternal;
        this.adZ = iBinder == null ? null : zza.zzgs(iBinder);
        this.mPendingIntent = pendingIntent;
        this.aea = iBinder2 == null ? null : zzd.zza.zzgr(iBinder2);
        if (iBinder3 != null) {
            zzg = zzg.zza.zzgu(iBinder3);
        }
        this.aeb = zzg;
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, PendingIntent pendingIntent, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, null, pendingIntent, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, zzd zzd, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, null, null, zzd.asBinder(), zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, zze zze, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, zze.asBinder(), null, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(zzd zzd, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, null, null, zzd.asBinder(), zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(zze zze, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, zze.asBinder(), null, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zzb(PendingIntent pendingIntent, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, null, pendingIntent, null, zzg != null ? zzg.asBinder() : null);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzn.zza(this, parcel, i);
    }

    IBinder zzbnn() {
        return this.adZ == null ? null : this.adZ.asBinder();
    }

    IBinder zzbno() {
        return this.aea == null ? null : this.aea.asBinder();
    }

    IBinder zzbnp() {
        return this.aeb == null ? null : this.aeb.asBinder();
    }
}
