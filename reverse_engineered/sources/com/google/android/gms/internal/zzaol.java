package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzaol extends zzank<Time> {
    public static final zzanl bfE = new C33661();
    private final DateFormat bge = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: com.google.android.gms.internal.zzaol$1 */
    static class C33661 implements zzanl {
        C33661() {
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            return zzaoo.m16036s() == Time.class ? new zzaol() : null;
        }
    }

    public synchronized void zza(zzaor zzaor, Time time) throws IOException {
        zzaor.zztb(time == null ? null : this.bge.format(time));
    }

    public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
        return zzn(zzaop);
    }

    public synchronized Time zzn(zzaop zzaop) throws IOException {
        Time time;
        if (zzaop.mo4189h() == zzaoq.bhH) {
            zzaop.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.bge.parse(zzaop.nextString()).getTime());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
        return time;
    }
}
