package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.internal.C5586l;
import org.apache.http.protocol.HttpDateGenerator;

/* compiled from: HttpDate */
/* renamed from: okhttp3.internal.http.f */
public final class C5568f {
    /* renamed from: a */
    private static final ThreadLocal<DateFormat> f17938a = new C55671();
    /* renamed from: b */
    private static final String[] f17939b = new String[]{HttpDateGenerator.PATTERN_RFC1123, "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    /* renamed from: c */
    private static final DateFormat[] f17940c = new DateFormat[f17939b.length];

    /* compiled from: HttpDate */
    /* renamed from: okhttp3.internal.http.f$1 */
    static class C55671 extends ThreadLocal<DateFormat> {
        C55671() {
        }

        protected /* synthetic */ Object initialValue() {
            return m20223a();
        }

        /* renamed from: a */
        protected DateFormat m20223a() {
            DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(C5586l.f18009d);
            return simpleDateFormat;
        }
    }

    /* renamed from: a */
    public static Date m20225a(String str) {
        int i = 0;
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = ((DateFormat) f17938a.get()).parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        synchronized (f17939b) {
            int length = f17939b.length;
            while (i < length) {
                DateFormat dateFormat = f17940c[i];
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(f17939b[i], Locale.US);
                    dateFormat.setTimeZone(C5586l.f18009d);
                    f17940c[i] = dateFormat;
                }
                parsePosition.setIndex(0);
                parse = dateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return parse;
                }
                i++;
            }
            return null;
        }
    }

    /* renamed from: a */
    public static String m20224a(Date date) {
        return ((DateFormat) f17938a.get()).format(date);
    }
}
