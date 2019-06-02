package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.internal.zzs.zza;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class zzk<T> implements Comparable<zzk<T>> {
    private final zza zzac;
    private final int zzad;
    private final String zzae;
    private final int zzaf;
    private final zzm.zza zzag;
    private Integer zzah;
    private zzl zzai;
    private boolean zzaj;
    private boolean zzak;
    private boolean zzal;
    private long zzam;
    private zzo zzan;
    private zzb.zza zzao;

    public zzk(int i, String str, zzm.zza zza) {
        this.zzac = zza.zzbj ? new zza() : null;
        this.zzaj = true;
        this.zzak = false;
        this.zzal = false;
        this.zzam = 0;
        this.zzao = null;
        this.zzad = i;
        this.zzae = str;
        this.zzag = zza;
        zza(new zzd());
        this.zzaf = zzb(str);
    }

    private byte[] zza(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Encoding not supported: ";
            String valueOf = String.valueOf(str);
            throw new RuntimeException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
        }
    }

    private static int zzb(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zzc((zzk) obj);
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return this.zzad;
    }

    public String getUrl() {
        return this.zzae;
    }

    public boolean isCanceled() {
        return false;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(zzf()));
        valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        str = "[ ] ";
        String valueOf2 = String.valueOf(getUrl());
        String valueOf3 = String.valueOf(zzr());
        String valueOf4 = String.valueOf(this.zzah);
        return new StringBuilder(((((String.valueOf(str).length() + 3) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()).append(str).append(valueOf2).append(" ").append(valueOf).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public final zzk<?> zza(int i) {
        this.zzah = Integer.valueOf(i);
        return this;
    }

    public zzk<?> zza(zzb.zza zza) {
        this.zzao = zza;
        return this;
    }

    public zzk<?> zza(zzl zzl) {
        this.zzai = zzl;
        return this;
    }

    public zzk<?> zza(zzo zzo) {
        this.zzan = zzo;
        return this;
    }

    protected abstract zzm<T> zza(zzi zzi);

    protected abstract void zza(T t);

    protected zzr zzb(zzr zzr) {
        return zzr;
    }

    public int zzc(zzk<T> zzk) {
        zzk$zza zzr = zzr();
        zzk$zza zzr2 = zzk.zzr();
        return zzr == zzr2 ? this.zzah.intValue() - zzk.zzah.intValue() : zzr2.ordinal() - zzr.ordinal();
    }

    public void zzc(zzr zzr) {
        if (this.zzag != null) {
            this.zzag.zze(zzr);
        }
    }

    public void zzc(String str) {
        if (zza.zzbj) {
            this.zzac.zza(str, Thread.currentThread().getId());
        } else if (this.zzam == 0) {
            this.zzam = SystemClock.elapsedRealtime();
        }
    }

    void zzd(String str) {
        if (this.zzai != null) {
            this.zzai.zzf(this);
        }
        if (zza.zzbj) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzk$1(this, str, id));
                return;
            }
            this.zzac.zza(str, id);
            this.zzac.zzd(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.zzam >= 3000) {
            zzs.zzb("%d ms: %s", new Object[]{Long.valueOf(SystemClock.elapsedRealtime() - this.zzam), toString()});
        }
    }

    public int zzf() {
        return this.zzaf;
    }

    public String zzg() {
        return getUrl();
    }

    public zzb.zza zzh() {
        return this.zzao;
    }

    @Deprecated
    protected Map<String, String> zzi() throws zza {
        return zzm();
    }

    @Deprecated
    protected String zzj() {
        return zzn();
    }

    @Deprecated
    public String zzk() {
        return zzo();
    }

    @Deprecated
    public byte[] zzl() throws zza {
        Map zzi = zzi();
        return (zzi == null || zzi.size() <= 0) ? null : zza(zzi, zzj());
    }

    protected Map<String, String> zzm() throws zza {
        return null;
    }

    protected String zzn() {
        return "UTF-8";
    }

    public String zzo() {
        String str = "application/x-www-form-urlencoded; charset=";
        String valueOf = String.valueOf(zzn());
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public byte[] zzp() throws zza {
        Map zzm = zzm();
        return (zzm == null || zzm.size() <= 0) ? null : zza(zzm, zzn());
    }

    public final boolean zzq() {
        return this.zzaj;
    }

    public zzk$zza zzr() {
        return zzk$zza.zzat;
    }

    public final int zzs() {
        return this.zzan.zzc();
    }

    public zzo zzt() {
        return this.zzan;
    }

    public void zzu() {
        this.zzal = true;
    }

    public boolean zzv() {
        return this.zzal;
    }
}
