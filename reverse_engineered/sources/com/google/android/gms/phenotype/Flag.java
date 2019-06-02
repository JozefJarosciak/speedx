package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class Flag extends AbstractSafeParcelable implements Comparable<Flag> {
    public static final Creator<Flag> CREATOR = new zzb();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza asg = new zza();
    final boolean abu;
    final double abw;
    final long asc;
    final byte[] asd;
    public final int ase;
    public final int asf;
    final int mVersionCode;
    public final String name;
    final String zr;

    public static class zza implements Comparator<Flag> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((Flag) obj, (Flag) obj2);
        }

        public int zza(Flag flag, Flag flag2) {
            return flag.asf == flag2.asf ? flag.name.compareTo(flag2.name) : flag.asf - flag2.asf;
        }
    }

    Flag(int i, String str, long j, boolean z, double d, String str2, byte[] bArr, int i2, int i3) {
        this.mVersionCode = i;
        this.name = str;
        this.asc = j;
        this.abu = z;
        this.abw = d;
        this.zr = str2;
        this.asd = bArr;
        this.ase = i2;
        this.asf = i3;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        return j < j2 ? -1 : j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        return str == str2 ? 0 : str == null ? -1 : str2 == null ? 1 : str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        return z == z2 ? 0 : z ? 1 : -1;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((Flag) obj);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Flag)) {
            return false;
        }
        Flag flag = (Flag) obj;
        if (this.mVersionCode != flag.mVersionCode || !zzaa.equal(this.name, flag.name) || this.ase != flag.ase || this.asf != flag.asf) {
            return false;
        }
        switch (this.ase) {
            case 1:
                return this.asc == flag.asc;
            case 2:
                return this.abu == flag.abu;
            case 3:
                return this.abw == flag.abw;
            case 4:
                return zzaa.equal(this.zr, flag.zr);
            case 5:
                return Arrays.equals(this.asd, flag.asd);
            default:
                throw new AssertionError("Invalid enum value: " + this.ase);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Flag(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.name);
        stringBuilder.append(", ");
        switch (this.ase) {
            case 1:
                stringBuilder.append(this.asc);
                break;
            case 2:
                stringBuilder.append(this.abu);
                break;
            case 3:
                stringBuilder.append(this.abw);
                break;
            case 4:
                stringBuilder.append("'");
                stringBuilder.append(this.zr);
                stringBuilder.append("'");
                break;
            case 5:
                if (this.asd != null) {
                    stringBuilder.append("'");
                    stringBuilder.append(new String(this.asd, UTF_8));
                    stringBuilder.append("'");
                    break;
                }
                stringBuilder.append("null");
                break;
            default:
                throw new AssertionError("Invalid enum value: " + this.ase);
        }
        stringBuilder.append(", ");
        stringBuilder.append(this.ase);
        stringBuilder.append(", ");
        stringBuilder.append(this.asf);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public int zza(Flag flag) {
        int i = 0;
        int compareTo = this.name.compareTo(flag.name);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = compare(this.ase, flag.ase);
        if (compareTo != 0) {
            return compareTo;
        }
        switch (this.ase) {
            case 1:
                return compare(this.asc, flag.asc);
            case 2:
                return compare(this.abu, flag.abu);
            case 3:
                return Double.compare(this.abw, flag.abw);
            case 4:
                return compare(this.zr, flag.zr);
            case 5:
                if (this.asd == flag.asd) {
                    return 0;
                }
                if (this.asd == null) {
                    return -1;
                }
                if (flag.asd == null) {
                    return 1;
                }
                while (i < Math.min(this.asd.length, flag.asd.length)) {
                    compareTo = compare(this.asd[i], flag.asd[i]);
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    i++;
                }
                return compare(this.asd.length, flag.asd.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.ase);
        }
    }
}
