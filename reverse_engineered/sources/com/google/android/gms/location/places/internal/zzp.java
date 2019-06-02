package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zza;

public class zzp implements PlacePhotoMetadata {
    private final String afX;
    private final CharSequence afY;
    private int mIndex;
    private final int zzbrm;
    private final int zzbrn;

    public zzp(String str, int i, int i2, CharSequence charSequence, int i3) {
        this.afX = str;
        this.zzbrm = i;
        this.zzbrn = i2;
        this.afY = charSequence;
        this.mIndex = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp zzp = (zzp) obj;
        return zzp.zzbrm == this.zzbrm && zzp.zzbrn == this.zzbrn && zzaa.equal(zzp.afX, this.afX) && zzaa.equal(zzp.afY, this.afY);
    }

    public /* synthetic */ Object freeze() {
        return zzboy();
    }

    public CharSequence getAttributions() {
        return this.afY;
    }

    public int getMaxHeight() {
        return this.zzbrn;
    }

    public int getMaxWidth() {
        return this.zzbrm;
    }

    public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        final int i3 = i;
        final int i4 = i2;
        return googleApiClient.zzc(new zza<zze>(this, Places.GEO_DATA_API, googleApiClient) {
            final /* synthetic */ zzp agb;

            protected void zza(zze zze) throws RemoteException {
                zze.zza(new zzf((zza) this), this.agb.afX, i3, i4, this.agb.mIndex);
            }
        });
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.zzbrm), Integer.valueOf(this.zzbrn), this.afX, this.afY);
    }

    public boolean isDataValid() {
        return true;
    }

    public PlacePhotoMetadata zzboy() {
        return this;
    }
}
