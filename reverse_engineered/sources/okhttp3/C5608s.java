package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: MediaType */
/* renamed from: okhttp3.s */
public final class C5608s {
    /* renamed from: a */
    private static final Pattern f18059a = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    /* renamed from: b */
    private static final Pattern f18060b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    /* renamed from: c */
    private final String f18061c;
    /* renamed from: d */
    private final String f18062d;
    /* renamed from: e */
    private final String f18063e;
    /* renamed from: f */
    private final String f18064f;

    private C5608s(String str, String str2, String str3, String str4) {
        this.f18061c = str;
        this.f18062d = str2;
        this.f18063e = str3;
        this.f18064f = str4;
    }

    /* renamed from: a */
    public static C5608s m20418a(String str) {
        Matcher matcher = f18059a.matcher(str);
        if (!matcher.lookingAt()) {
            return null;
        }
        String toLowerCase = matcher.group(1).toLowerCase(Locale.US);
        String toLowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        Matcher matcher2 = f18060b.matcher(str);
        String str2 = null;
        for (int end = matcher.end(); end < str.length(); end = matcher2.end()) {
            matcher2.region(end, str.length());
            if (!matcher2.lookingAt()) {
                return null;
            }
            String group = matcher2.group(1);
            if (group != null && group.equalsIgnoreCase("charset")) {
                if (matcher2.group(2) != null) {
                    group = matcher2.group(2);
                } else {
                    group = matcher2.group(3);
                }
                if (str2 == null || group.equalsIgnoreCase(str2)) {
                    str2 = group;
                } else {
                    throw new IllegalArgumentException("Multiple different charsets: " + str);
                }
            }
        }
        return new C5608s(str, toLowerCase, toLowerCase2, str2);
    }

    /* renamed from: a */
    public String m20419a() {
        return this.f18062d;
    }

    /* renamed from: b */
    public String m20421b() {
        return this.f18063e;
    }

    /* renamed from: c */
    public Charset m20422c() {
        return this.f18064f != null ? Charset.forName(this.f18064f) : null;
    }

    /* renamed from: a */
    public Charset m20420a(Charset charset) {
        return this.f18064f != null ? Charset.forName(this.f18064f) : charset;
    }

    public String toString() {
        return this.f18061c;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C5608s) && ((C5608s) obj).f18061c.equals(this.f18061c);
    }

    public int hashCode() {
        return this.f18061c.hashCode();
    }
}
