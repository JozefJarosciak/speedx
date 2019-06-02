package okhttp3;

import ch.qos.logback.core.CoreConstants;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.google.common.base.Ascii;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.C5586l;
import okhttp3.internal.http.C5568f;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: Cookie */
/* renamed from: okhttp3.k */
public final class C5591k {
    /* renamed from: a */
    private static final Pattern f18023a = Pattern.compile("(\\d{2,4})[^\\d]*");
    /* renamed from: b */
    private static final Pattern f18024b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    /* renamed from: c */
    private static final Pattern f18025c = Pattern.compile("(\\d{1,2})[^\\d]*");
    /* renamed from: d */
    private static final Pattern f18026d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    /* renamed from: e */
    private final String f18027e;
    /* renamed from: f */
    private final String f18028f;
    /* renamed from: g */
    private final long f18029g;
    /* renamed from: h */
    private final String f18030h;
    /* renamed from: i */
    private final String f18031i;
    /* renamed from: j */
    private final boolean f18032j;
    /* renamed from: k */
    private final boolean f18033k;
    /* renamed from: l */
    private final boolean f18034l;
    /* renamed from: m */
    private final boolean f18035m;

    private C5591k(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f18027e = str;
        this.f18028f = str2;
        this.f18029g = j;
        this.f18030h = str3;
        this.f18031i = str4;
        this.f18032j = z;
        this.f18033k = z2;
        this.f18035m = z3;
        this.f18034l = z4;
    }

    /* renamed from: a */
    public String m20374a() {
        return this.f18027e;
    }

    /* renamed from: b */
    public String m20375b() {
        return this.f18028f;
    }

    /* renamed from: b */
    private static boolean m20373b(HttpUrl httpUrl, String str) {
        String f = httpUrl.m19676f();
        if (f.equals(str)) {
            return true;
        }
        if (f.endsWith(str) && f.charAt((f.length() - str.length()) - 1) == CoreConstants.DOT && !C5586l.m20341c(f)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static C5591k m20371a(HttpUrl httpUrl, String str) {
        return C5591k.m20370a(System.currentTimeMillis(), httpUrl, str);
    }

    /* renamed from: a */
    static C5591k m20370a(long j, HttpUrl httpUrl, String str) {
        int length = str.length();
        int a = C5586l.m20316a(str, 0, length, ';');
        int a2 = C5586l.m20316a(str, 0, a, '=');
        if (a2 == a) {
            return null;
        }
        String c = C5586l.m20340c(str, 0, a2);
        if (c.isEmpty()) {
            return null;
        }
        String substring;
        String c2 = C5586l.m20340c(str, a2 + 1, a);
        long j2 = 253402300799999L;
        long j3 = -1;
        String str2 = null;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        a++;
        while (a < length) {
            long j4;
            int a3 = C5586l.m20316a(str, a, length, ';');
            int a4 = C5586l.m20316a(str, a, a3, '=');
            String c3 = C5586l.m20340c(str, a, a4);
            String c4 = a4 < a3 ? C5586l.m20340c(str, a4 + 1, a3) : "";
            if (c3.equalsIgnoreCase("expires")) {
                try {
                    j2 = C5591k.m20368a(c4, 0, c4.length());
                    z4 = true;
                    c4 = str2;
                    j4 = j2;
                } catch (IllegalArgumentException e) {
                    c4 = str2;
                    j4 = j2;
                }
            } else {
                if (c3.equalsIgnoreCase("max-age")) {
                    try {
                        j3 = C5591k.m20367a(c4);
                        z4 = true;
                        c4 = str2;
                        j4 = j2;
                    } catch (NumberFormatException e2) {
                        c4 = str2;
                        j4 = j2;
                    }
                } else {
                    if (c3.equalsIgnoreCase("domain")) {
                        try {
                            c4 = C5591k.m20372b(c4);
                            z3 = false;
                            j4 = j2;
                        } catch (IllegalArgumentException e3) {
                            c4 = str2;
                            j4 = j2;
                        }
                    } else {
                        if (c3.equalsIgnoreCase("path")) {
                            str3 = c4;
                            c4 = str2;
                            j4 = j2;
                        } else {
                            if (c3.equalsIgnoreCase("secure")) {
                                z = true;
                                c4 = str2;
                                j4 = j2;
                            } else {
                                if (c3.equalsIgnoreCase("httponly")) {
                                    z2 = true;
                                    c4 = str2;
                                    j4 = j2;
                                } else {
                                    c4 = str2;
                                    j4 = j2;
                                }
                            }
                        }
                    }
                }
            }
            String str4 = c4;
            a = a3 + 1;
            j2 = j4;
            str2 = str4;
        }
        if (j3 == Long.MIN_VALUE) {
            j3 = Long.MIN_VALUE;
        } else if (j3 != -1) {
            j3 = (j3 <= 9223372036854775L ? j3 * 1000 : Long.MAX_VALUE) + j;
            if (j3 < j || j3 > 253402300799999L) {
                j3 = 253402300799999L;
            }
        } else {
            j3 = j2;
        }
        if (str2 == null) {
            str2 = httpUrl.m19676f();
        } else if (!C5591k.m20373b(httpUrl, str2)) {
            return null;
        }
        if (str3 == null || !str3.startsWith("/")) {
            str3 = httpUrl.m19678h();
            a = str3.lastIndexOf(47);
            substring = a != 0 ? str3.substring(0, a) : "/";
        } else {
            substring = str3;
        }
        return new C5591k(c, c2, j3, str2, substring, z, z2, z3, z4);
    }

    /* renamed from: a */
    private static long m20368a(String str, int i, int i2) {
        int a = C5591k.m20366a(str, i, i2, false);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        Matcher matcher = f18026d.matcher(str);
        while (a < i2) {
            int a2 = C5591k.m20366a(str, a + 1, i2, true);
            matcher.region(a, a2);
            if (i3 == -1 && matcher.usePattern(f18026d).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
                i4 = Integer.parseInt(matcher.group(2));
                i5 = Integer.parseInt(matcher.group(3));
            } else if (i6 == -1 && matcher.usePattern(f18025c).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else if (i7 == -1 && matcher.usePattern(f18024b).matches()) {
                i7 = f18024b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i8 == -1 && matcher.usePattern(f18023a).matches()) {
                i8 = Integer.parseInt(matcher.group(1));
            }
            a = C5591k.m20366a(str, a2 + 1, i2, false);
        }
        if (i8 >= 70 && i8 <= 99) {
            i8 += 1900;
        }
        if (i8 >= 0 && i8 <= 69) {
            i8 += m_AppUI.MSG_APP_DATA_OK;
        }
        if (i8 < 1601) {
            throw new IllegalArgumentException();
        } else if (i7 == -1) {
            throw new IllegalArgumentException();
        } else if (i6 < 1 || i6 > 31) {
            throw new IllegalArgumentException();
        } else if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        } else if (i4 < 0 || i4 > 59) {
            throw new IllegalArgumentException();
        } else if (i5 < 0 || i5 > 59) {
            throw new IllegalArgumentException();
        } else {
            Calendar gregorianCalendar = new GregorianCalendar(C5586l.f18009d);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i8);
            gregorianCalendar.set(2, i7 - 1);
            gregorianCalendar.set(5, i6);
            gregorianCalendar.set(11, i3);
            gregorianCalendar.set(12, i4);
            gregorianCalendar.set(13, i5);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    /* renamed from: a */
    private static int m20366a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            Object obj;
            char charAt = str.charAt(i3);
            Object obj2 = ((charAt >= ' ' || charAt == '\t') && charAt < Ascii.MAX && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && charAt != CoreConstants.COLON_CHAR)))) ? null : 1;
            if (z) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj2 == obj) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: a */
    private static long m20367a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                return Long.MIN_VALUE;
            } else {
                return Long.MAX_VALUE;
            }
        }
    }

    /* renamed from: b */
    private static String m20372b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String b = C5586l.m20336b(str);
        if (b != null) {
            return b;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public static List<C5591k> m20369a(HttpUrl httpUrl, C5607q c5607q) {
        List c = c5607q.m20417c(HttpHeaders.SET_COOKIE);
        List list = null;
        int size = c.size();
        for (int i = 0; i < size; i++) {
            C5591k a = C5591k.m20371a(httpUrl, (String) c.get(i));
            if (a != null) {
                List arrayList;
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(a);
                list = arrayList;
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f18027e);
        stringBuilder.append('=');
        stringBuilder.append(this.f18028f);
        if (this.f18034l) {
            if (this.f18029g == Long.MIN_VALUE) {
                stringBuilder.append("; max-age=0");
            } else {
                stringBuilder.append("; expires=").append(C5568f.m20224a(new Date(this.f18029g)));
            }
        }
        if (!this.f18035m) {
            stringBuilder.append("; domain=").append(this.f18030h);
        }
        stringBuilder.append("; path=").append(this.f18031i);
        if (this.f18032j) {
            stringBuilder.append("; secure");
        }
        if (this.f18033k) {
            stringBuilder.append("; httponly");
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5591k)) {
            return false;
        }
        C5591k c5591k = (C5591k) obj;
        if (c5591k.f18027e.equals(this.f18027e) && c5591k.f18028f.equals(this.f18028f) && c5591k.f18030h.equals(this.f18030h) && c5591k.f18031i.equals(this.f18031i) && c5591k.f18029g == this.f18029g && c5591k.f18032j == this.f18032j && c5591k.f18033k == this.f18033k && c5591k.f18034l == this.f18034l && c5591k.f18035m == this.f18035m) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = (((((((((this.f18027e.hashCode() + 527) * 31) + this.f18028f.hashCode()) * 31) + this.f18030h.hashCode()) * 31) + this.f18031i.hashCode()) * 31) + ((int) (this.f18029g ^ (this.f18029g >>> 32)))) * 31;
        if (this.f18032j) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.f18033k) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.f18034l) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + hashCode) * 31;
        if (!this.f18035m) {
            i2 = 1;
        }
        return i + i2;
    }
}
