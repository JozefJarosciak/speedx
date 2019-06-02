package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import ch.qos.logback.core.CoreConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;

public class PlayLoggerContext extends AbstractSafeParcelable {
    public static final zza CREATOR = new zza();
    public final int ash;
    public final int asi;
    public final String asj;
    public final String ask;
    public final boolean asl;
    public final String asm;
    public final boolean asn;
    public final int aso;
    public final String packageName;
    public final int versionCode;

    public PlayLoggerContext(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4, boolean z2, int i4) {
        this.versionCode = i;
        this.packageName = str;
        this.ash = i2;
        this.asi = i3;
        this.asj = str2;
        this.ask = str3;
        this.asl = z;
        this.asm = str4;
        this.asn = z2;
        this.aso = i4;
    }

    public PlayLoggerContext(String str, int i, int i2, String str2, String str3, String str4, boolean z, int i3) {
        this.versionCode = 1;
        this.packageName = (String) zzab.zzaa(str);
        this.ash = i;
        this.asi = i2;
        this.asm = str2;
        this.asj = str3;
        this.ask = str4;
        this.asl = !z;
        this.asn = z;
        this.aso = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayLoggerContext)) {
            return false;
        }
        PlayLoggerContext playLoggerContext = (PlayLoggerContext) obj;
        return this.versionCode == playLoggerContext.versionCode && this.packageName.equals(playLoggerContext.packageName) && this.ash == playLoggerContext.ash && this.asi == playLoggerContext.asi && zzaa.equal(this.asm, playLoggerContext.asm) && zzaa.equal(this.asj, playLoggerContext.asj) && zzaa.equal(this.ask, playLoggerContext.ask) && this.asl == playLoggerContext.asl && this.asn == playLoggerContext.asn && this.aso == playLoggerContext.aso;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.versionCode), this.packageName, Integer.valueOf(this.ash), Integer.valueOf(this.asi), this.asm, this.asj, this.ask, Boolean.valueOf(this.asl), Boolean.valueOf(this.asn), Integer.valueOf(this.aso));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("versionCode=").append(this.versionCode).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("package=").append(this.packageName).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("packageVersionCode=").append(this.ash).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("logSource=").append(this.asi).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("logSourceName=").append(this.asm).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("uploadAccount=").append(this.asj).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("loggingId=").append(this.ask).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("logAndroidId=").append(this.asl).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("isAnonymous=").append(this.asn).append(CoreConstants.COMMA_CHAR);
        stringBuilder.append("qosTier=").append(this.aso);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
