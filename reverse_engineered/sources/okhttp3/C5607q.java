package okhttp3;

import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import okhttp3.internal.C5586l;
import okhttp3.internal.http.C5568f;

/* compiled from: Headers */
/* renamed from: okhttp3.q */
public final class C5607q {
    /* renamed from: a */
    private final String[] f18058a;

    /* compiled from: Headers */
    /* renamed from: okhttp3.q$a */
    public static final class C5606a {
        /* renamed from: a */
        private final List<String> f18057a = new ArrayList(20);

        /* renamed from: a */
        C5606a m20403a(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return m20407b(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return m20407b("", str.substring(1));
            }
            return m20407b("", str);
        }

        /* renamed from: a */
        public C5606a m20404a(String str, String str2) {
            m20402d(str, str2);
            return m20407b(str, str2);
        }

        /* renamed from: b */
        C5606a m20407b(String str, String str2) {
            this.f18057a.add(str);
            this.f18057a.add(str2.trim());
            return this;
        }

        /* renamed from: b */
        public C5606a m20406b(String str) {
            int i = 0;
            while (i < this.f18057a.size()) {
                if (str.equalsIgnoreCase((String) this.f18057a.get(i))) {
                    this.f18057a.remove(i);
                    this.f18057a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        /* renamed from: c */
        public C5606a m20408c(String str, String str2) {
            m20402d(str, str2);
            m20406b(str);
            m20407b(str, str2);
            return this;
        }

        /* renamed from: d */
        private void m20402d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            } else {
                int i;
                char charAt;
                int length = str.length();
                for (i = 0; i < length; i++) {
                    charAt = str.charAt(i);
                    if (charAt <= '\u001f' || charAt >= Ascii.MAX) {
                        throw new IllegalArgumentException(C5586l.m20319a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                    }
                }
                if (str2 == null) {
                    throw new NullPointerException("value == null");
                }
                length = str2.length();
                for (i = 0; i < length; i++) {
                    charAt = str2.charAt(i);
                    if (charAt <= '\u001f' || charAt >= Ascii.MAX) {
                        throw new IllegalArgumentException(C5586l.m20319a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt), Integer.valueOf(i), str, str2));
                    }
                }
            }
        }

        /* renamed from: a */
        public C5607q m20405a() {
            return new C5607q();
        }
    }

    private C5607q(C5606a c5606a) {
        this.f18058a = (String[]) c5606a.f18057a.toArray(new String[c5606a.f18057a.size()]);
    }

    private C5607q(String[] strArr) {
        this.f18058a = strArr;
    }

    /* renamed from: a */
    public String m20413a(String str) {
        return C5607q.m20409a(this.f18058a, str);
    }

    /* renamed from: b */
    public Date m20415b(String str) {
        String a = m20413a(str);
        return a != null ? C5568f.m20225a(a) : null;
    }

    /* renamed from: a */
    public int m20411a() {
        return this.f18058a.length / 2;
    }

    /* renamed from: a */
    public String m20412a(int i) {
        return this.f18058a[i * 2];
    }

    /* renamed from: b */
    public String m20414b(int i) {
        return this.f18058a[(i * 2) + 1];
    }

    /* renamed from: c */
    public List<String> m20417c(String str) {
        int a = m20411a();
        List list = null;
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(m20412a(i))) {
                if (list == null) {
                    list = new ArrayList(2);
                }
                list.add(m20414b(i));
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    /* renamed from: b */
    public C5606a m20416b() {
        C5606a c5606a = new C5606a();
        Collections.addAll(c5606a.f18057a, this.f18058a);
        return c5606a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C5607q) && Arrays.equals(((C5607q) obj).f18058a, this.f18058a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f18058a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int a = m20411a();
        for (int i = 0; i < a; i++) {
            stringBuilder.append(m20412a(i)).append(": ").append(m20414b(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private static String m20409a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C5607q m20410a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        } else if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        } else {
            int i;
            String[] strArr2 = (String[]) strArr.clone();
            for (i = 0; i < strArr2.length; i++) {
                if (strArr2[i] == null) {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
                strArr2[i] = strArr2[i].trim();
            }
            i = 0;
            while (i < strArr2.length) {
                String str = strArr2[i];
                String str2 = strArr2[i + 1];
                if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
                }
            }
            return new C5607q(strArr2);
        }
    }
}
