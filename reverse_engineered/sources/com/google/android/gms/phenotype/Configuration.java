package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    public static final Creator<Configuration> CREATOR = new zza();
    public final int arY;
    public final Flag[] arZ;
    public final String[] asa;
    public final Map<String, Flag> asb = new TreeMap();
    final int mVersionCode;

    Configuration(int i, int i2, Flag[] flagArr, String[] strArr) {
        this.mVersionCode = i;
        this.arY = i2;
        this.arZ = flagArr;
        for (Flag flag : flagArr) {
            this.asb.put(flag.name, flag);
        }
        this.asa = strArr;
        if (this.asa != null) {
            Arrays.sort(this.asa);
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((Configuration) obj);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        return this.mVersionCode == configuration.mVersionCode && this.arY == configuration.arY && zzaa.equal(this.asb, configuration.asb) && Arrays.equals(this.asa, configuration.asa);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Configuration(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.arY);
        stringBuilder.append(", ");
        stringBuilder.append("(");
        for (Flag append : this.asb.values()) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        stringBuilder.append(")");
        stringBuilder.append(", ");
        stringBuilder.append("(");
        if (this.asa != null) {
            for (String append2 : this.asa) {
                stringBuilder.append(append2);
                stringBuilder.append(", ");
            }
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(")");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public int zza(Configuration configuration) {
        return this.arY - configuration.arY;
    }
}
