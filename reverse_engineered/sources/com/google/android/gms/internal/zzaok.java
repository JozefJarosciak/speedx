package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzaok extends zzank<Date> {
    public static final zzanl bfE = new C33651();
    private final DateFormat bge = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: com.google.android.gms.internal.zzaok$1 */
    static class C33651 implements zzanl {
        C33651() {
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            return zzaoo.m16036s() == Date.class ? new zzaok() : null;
        }
    }

    public synchronized void zza(zzaor zzaor, Date date) throws IOException {
        zzaor.zztb(date == null ? null : this.bge.format(date));
    }

    public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
        return zzm(zzaop);
    }

    public synchronized Date zzm(zzaop zzaop) throws IOException {
        Date date;
        if (zzaop.mo4189h() == zzaoq.bhH) {
            zzaop.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.bge.parse(zzaop.nextString()).getTime());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
        return date;
    }
}
