package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.internal.zzn;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer<PlaceLikelihood> implements Result {
    private final String aeC;
    private final Status cc;
    private final Context mContext;
    private final int zzaxm;

    public static class zza {
        static int zztt(int i) {
            switch (i) {
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                    return i;
                default:
                    throw new IllegalArgumentException("invalid source: " + i);
            }
        }
    }

    public PlaceLikelihoodBuffer(DataHolder dataHolder, int i, Context context) {
        super(dataHolder);
        this.mContext = context;
        this.cc = PlacesStatusCodes.zzgz(dataHolder.getStatusCode());
        this.zzaxm = zza.zztt(i);
        if (dataHolder == null || dataHolder.zzaqy() == null) {
            this.aeC = null;
        } else {
            this.aeC = dataHolder.zzaqy().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
        }
    }

    public static int zzak(Bundle bundle) {
        return bundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
    }

    public PlaceLikelihood get(int i) {
        return new zzn(this.tk, i, this.mContext);
    }

    @Nullable
    public CharSequence getAttributions() {
        return this.aeC;
    }

    public Status getStatus() {
        return this.cc;
    }

    public String toString() {
        return zzaa.zzz(this).zzg("status", getStatus()).zzg("attributions", this.aeC).toString();
    }
}
