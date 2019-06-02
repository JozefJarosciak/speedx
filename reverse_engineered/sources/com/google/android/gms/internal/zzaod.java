package com.google.android.gms.internal;

import com.mapbox.services.commons.tidy.Tidy;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzaod extends zzank<Date> {
    public static final zzanl bfE = new C33591();
    private final DateFormat bdO = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat bdP = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat bdQ = m16003g();

    /* renamed from: com.google.android.gms.internal.zzaod$1 */
    static class C33591 implements zzanl {
        C33591() {
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            return zzaoo.m16036s() == Date.class ? new zzaod() : null;
        }
    }

    /* renamed from: g */
    private static DateFormat m16003g() {
        DateFormat simpleDateFormat = new SimpleDateFormat(Tidy.DATE_FORMAT, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    private synchronized Date zzsz(String str) {
        Date parse;
        try {
            parse = this.bdP.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.bdO.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.bdQ.parse(str);
                } catch (Throwable e3) {
                    throw new zzanh(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void zza(zzaor zzaor, Date date) throws IOException {
        if (date == null) {
            zzaor.mo4206r();
        } else {
            zzaor.zztb(this.bdO.format(date));
        }
    }

    public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
        return zzk(zzaop);
    }

    public Date zzk(zzaop zzaop) throws IOException {
        if (zzaop.mo4189h() != zzaoq.bhH) {
            return zzsz(zzaop.nextString());
        }
        zzaop.nextNull();
        return null;
    }
}
