package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import ch.qos.logback.classic.net.SyslogAppender;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new zzg();
    private final String AA;
    private final int AB;
    private final List<String> AC;
    private final String AD;
    private int AE;
    private final String AF;
    private final float AG;
    private final String Ay;
    private final String Az;
    private final long mTimeout;
    final int mVersionCode;
    private final long zM;
    private int zN;
    private final long zU;
    private long zW;

    WakeLockEvent(int i, long j, int i2, String str, int i3, List<String> list, String str2, long j2, int i4, String str3, String str4, float f, long j3, String str5) {
        this.mVersionCode = i;
        this.zM = j;
        this.zN = i2;
        this.Ay = str;
        this.Az = str3;
        this.AA = str5;
        this.AB = i3;
        this.zW = -1;
        this.AC = list;
        this.AD = str2;
        this.zU = j2;
        this.AE = i4;
        this.AF = str4;
        this.AG = f;
        this.mTimeout = j3;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5);
    }

    public int getEventType() {
        return this.zN;
    }

    public long getTimeMillis() {
        return this.zM;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzauo() {
        return this.AD;
    }

    public long zzaup() {
        return this.zW;
    }

    public long zzaur() {
        return this.zU;
    }

    public String zzaus() {
        String valueOf = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String valueOf2 = String.valueOf(zzauv());
        String valueOf3 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        int zzauy = zzauy();
        String valueOf4 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String join = zzauz() == null ? "" : TextUtils.join(",", zzauz());
        String valueOf5 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        int zzava = zzava();
        String valueOf6 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String zzauw = zzauw() == null ? "" : zzauw();
        String valueOf7 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String zzavb = zzavb() == null ? "" : zzavb();
        String valueOf8 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        float zzavc = zzavc();
        String valueOf9 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String zzaux = zzaux() == null ? "" : zzaux();
        return new StringBuilder(((((((((((((String.valueOf(valueOf).length() + 37) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(join).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()) + String.valueOf(zzauw).length()) + String.valueOf(valueOf7).length()) + String.valueOf(zzavb).length()) + String.valueOf(valueOf8).length()) + String.valueOf(valueOf9).length()) + String.valueOf(zzaux).length()).append(valueOf).append(valueOf2).append(valueOf3).append(zzauy).append(valueOf4).append(join).append(valueOf5).append(zzava).append(valueOf6).append(zzauw).append(valueOf7).append(zzavb).append(valueOf8).append(zzavc).append(valueOf9).append(zzaux).toString();
    }

    public String zzauv() {
        return this.Ay;
    }

    public String zzauw() {
        return this.Az;
    }

    public String zzaux() {
        return this.AA;
    }

    public int zzauy() {
        return this.AB;
    }

    public List<String> zzauz() {
        return this.AC;
    }

    public int zzava() {
        return this.AE;
    }

    public String zzavb() {
        return this.AF;
    }

    public float zzavc() {
        return this.AG;
    }

    public long zzavd() {
        return this.mTimeout;
    }
}
