package com.google.android.gms.location.places.personalized;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated
public final class zze extends zzd<PlaceUserData> implements Result {
    private final Status cc;

    public zze(DataHolder dataHolder) {
        this(dataHolder, PlacesStatusCodes.zzgz(dataHolder.getStatusCode()));
    }

    private zze(DataHolder dataHolder, Status status) {
        super(dataHolder, PlaceUserData.CREATOR);
        boolean z = dataHolder == null || dataHolder.getStatusCode() == status.getStatusCode();
        zzab.zzbn(z);
        this.cc = status;
    }

    public Status getStatus() {
        return this.cc;
    }
}
