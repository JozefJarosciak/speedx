package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import ch.qos.logback.classic.net.SyslogAppender;

public final class ConnectionEvent extends StatsEvent {
    public static final Creator<ConnectionEvent> CREATOR = new zza();
    final int mVersionCode;
    private final long zM;
    private int zN;
    private final String zO;
    private final String zP;
    private final String zQ;
    private final String zR;
    private final String zS;
    private final String zT;
    private final long zU;
    private final long zV;
    private long zW;

    ConnectionEvent(int i, long j, int i2, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this.mVersionCode = i;
        this.zM = j;
        this.zN = i2;
        this.zO = str;
        this.zP = str2;
        this.zQ = str3;
        this.zR = str4;
        this.zW = -1;
        this.zS = str5;
        this.zT = str6;
        this.zU = j2;
        this.zV = j3;
    }

    public ConnectionEvent(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this(1, j, i, str, str2, str3, str4, str5, str6, j2, j3);
    }

    public int getEventType() {
        return this.zN;
    }

    public long getTimeMillis() {
        return this.zM;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public String zzauj() {
        return this.zO;
    }

    public String zzauk() {
        return this.zP;
    }

    public String zzaul() {
        return this.zQ;
    }

    public String zzaum() {
        return this.zR;
    }

    public String zzaun() {
        return this.zS;
    }

    public String zzauo() {
        return this.zT;
    }

    public long zzaup() {
        return this.zW;
    }

    public long zzauq() {
        return this.zV;
    }

    public long zzaur() {
        return this.zU;
    }

    public String zzaus() {
        String valueOf = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String valueOf2 = String.valueOf(zzauj());
        String valueOf3 = String.valueOf(zzauk());
        String valueOf4 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String valueOf5 = String.valueOf(zzaul());
        String valueOf6 = String.valueOf(zzaum());
        String valueOf7 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        String str = this.zS == null ? "" : this.zS;
        String valueOf8 = String.valueOf(SyslogAppender.DEFAULT_STACKTRACE_PATTERN);
        return new StringBuilder(((((((((String.valueOf(valueOf).length() + 22) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()) + String.valueOf(valueOf7).length()) + String.valueOf(str).length()) + String.valueOf(valueOf8).length()).append(valueOf).append(valueOf2).append("/").append(valueOf3).append(valueOf4).append(valueOf5).append("/").append(valueOf6).append(valueOf7).append(str).append(valueOf8).append(zzauq()).toString();
    }
}
