package com.google.android.gms.internal;

import ch.qos.logback.core.CoreConstants;
import com.mapbox.services.commons.tidy.Tidy;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

final class zzamn implements zzamx<Date>, zzang<Date> {
    private final DateFormat bdO;
    private final DateFormat bdP;
    private final DateFormat bdQ;

    zzamn() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    public zzamn(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    zzamn(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    zzamn(DateFormat dateFormat, DateFormat dateFormat2) {
        this.bdO = dateFormat;
        this.bdP = dateFormat2;
        this.bdQ = new SimpleDateFormat(Tidy.DATE_FORMAT, Locale.US);
        this.bdQ.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private Date zza(zzamy zzamy) {
        Date parse;
        synchronized (this.bdP) {
            try {
                parse = this.bdP.parse(zzamy.zzczh());
            } catch (ParseException e) {
                try {
                    parse = this.bdO.parse(zzamy.zzczh());
                } catch (ParseException e2) {
                    try {
                        parse = this.bdQ.parse(zzamy.zzczh());
                    } catch (Throwable e3) {
                        throw new zzanh(zzamy.zzczh(), e3);
                    }
                }
            }
        }
        return parse;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(zzamn.class.getSimpleName());
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR).append(this.bdP.getClass().getSimpleName()).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return stringBuilder.toString();
    }

    public zzamy zza(Date date, Type type, zzanf zzanf) {
        zzamy zzane;
        synchronized (this.bdP) {
            zzane = new zzane(this.bdO.format(date));
        }
        return zzane;
    }

    public Date zza(zzamy zzamy, Type type, zzamw zzamw) throws zzanc {
        if (zzamy instanceof zzane) {
            Date zza = zza(zzamy);
            if (type == Date.class) {
                return zza;
            }
            if (type == Timestamp.class) {
                return new Timestamp(zza.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(zza.getTime());
            }
            String valueOf = String.valueOf(getClass());
            String valueOf2 = String.valueOf(type);
            throw new IllegalArgumentException(new StringBuilder((String.valueOf(valueOf).length() + 23) + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot deserialize to ").append(valueOf2).toString());
        }
        throw new zzanc("The date should be a string value");
    }

    public /* synthetic */ Object zzb(zzamy zzamy, Type type, zzamw zzamw) throws zzanc {
        return zza(zzamy, type, zzamw);
    }
}
