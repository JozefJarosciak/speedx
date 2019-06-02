package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;

public final class Scope extends AbstractSafeParcelable {
    public static final Creator<Scope> CREATOR = new zze();
    final int mVersionCode;
    private final String sf;

    Scope(int i, String str) {
        zzab.zzh(str, "scopeUri must not be null or empty");
        this.mVersionCode = i;
        this.sf = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Scope) ? false : this.sf.equals(((Scope) obj).sf);
    }

    public int hashCode() {
        return this.sf.hashCode();
    }

    public String toString() {
        return this.sf;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public String zzaoh() {
        return this.sf;
    }
}
