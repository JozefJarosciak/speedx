package com.google.android.gms.location.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class ParcelableGeofence extends AbstractSafeParcelable implements Geofence {
    public static final zzo CREATOR = new zzo();
    private final int acH;
    private final short acJ;
    private final double acK;
    private final double acL;
    private final float acM;
    private final int acN;
    private final int acO;
    private final long aec;
    private final int mVersionCode;
    private final String zzbvu;

    public ParcelableGeofence(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        zzkp(str);
        zzf(f);
        zza(d, d2);
        int zztl = zztl(i2);
        this.mVersionCode = i;
        this.acJ = s;
        this.zzbvu = str;
        this.acK = d;
        this.acL = d2;
        this.acM = f;
        this.aec = j;
        this.acH = zztl;
        this.acN = i3;
        this.acO = i4;
    }

    public ParcelableGeofence(String str, int i, short s, double d, double d2, float f, long j, int i2, int i3) {
        this(1, str, i, s, d, d2, f, j, i2, i3);
    }

    private static void zza(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    public static ParcelableGeofence zzaa(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        ParcelableGeofence parcelableGeofence = (ParcelableGeofence) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return parcelableGeofence;
    }

    private static void zzf(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void zzkp(String str) {
        if (str == null || str.length() > 100) {
            String str2 = "requestId is null or too long: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    private static int zztl(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    @SuppressLint({"DefaultLocale"})
    private static String zztm(int i) {
        switch (i) {
            case 1:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ParcelableGeofence)) {
            return false;
        }
        ParcelableGeofence parcelableGeofence = (ParcelableGeofence) obj;
        return this.acM != parcelableGeofence.acM ? false : this.acK != parcelableGeofence.acK ? false : this.acL != parcelableGeofence.acL ? false : this.acJ == parcelableGeofence.acJ;
    }

    public long getExpirationTime() {
        return this.aec;
    }

    public double getLatitude() {
        return this.acK;
    }

    public double getLongitude() {
        return this.acL;
    }

    public String getRequestId() {
        return this.zzbvu;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.acK);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.acL);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.acM)) * 31) + this.acJ) * 31) + this.acH;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{zztm(this.acJ), this.zzbvu, Integer.valueOf(this.acH), Double.valueOf(this.acK), Double.valueOf(this.acL), Float.valueOf(this.acM), Integer.valueOf(this.acN / 1000), Integer.valueOf(this.acO), Long.valueOf(this.aec)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo zzo = CREATOR;
        zzo.zza(this, parcel, i);
    }

    public short zzbnq() {
        return this.acJ;
    }

    public float zzbnr() {
        return this.acM;
    }

    public int zzbns() {
        return this.acH;
    }

    public int zzbnt() {
        return this.acN;
    }

    public int zzbnu() {
        return this.acO;
    }
}
