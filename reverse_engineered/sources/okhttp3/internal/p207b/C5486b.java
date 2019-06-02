package okhttp3.internal.p207b;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.asm.Opcodes;
import javax.security.auth.x500.X500Principal;

/* compiled from: DistinguishedNameParser */
/* renamed from: okhttp3.internal.b.b */
final class C5486b {
    /* renamed from: a */
    private final String f17660a;
    /* renamed from: b */
    private final int f17661b = this.f17660a.length();
    /* renamed from: c */
    private int f17662c;
    /* renamed from: d */
    private int f17663d;
    /* renamed from: e */
    private int f17664e;
    /* renamed from: f */
    private int f17665f;
    /* renamed from: g */
    private char[] f17666g;

    public C5486b(X500Principal x500Principal) {
        this.f17660a = x500Principal.getName("RFC2253");
    }

    /* renamed from: a */
    private String m19790a() {
        while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] == ' ') {
            this.f17662c++;
        }
        if (this.f17662c == this.f17661b) {
            return null;
        }
        this.f17663d = this.f17662c;
        this.f17662c++;
        while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] != '=' && this.f17666g[this.f17662c] != ' ') {
            this.f17662c++;
        }
        if (this.f17662c >= this.f17661b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
        }
        this.f17664e = this.f17662c;
        if (this.f17666g[this.f17662c] == ' ') {
            while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] != '=' && this.f17666g[this.f17662c] == ' ') {
                this.f17662c++;
            }
            if (this.f17666g[this.f17662c] != '=' || this.f17662c == this.f17661b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
            }
        }
        this.f17662c++;
        while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] == ' ') {
            this.f17662c++;
        }
        if (this.f17664e - this.f17663d > 4 && this.f17666g[this.f17663d + 3] == CoreConstants.DOT && ((this.f17666g[this.f17663d] == 'O' || this.f17666g[this.f17663d] == 'o') && ((this.f17666g[this.f17663d + 1] == 'I' || this.f17666g[this.f17663d + 1] == 'i') && (this.f17666g[this.f17663d + 2] == 'D' || this.f17666g[this.f17663d + 2] == 'd')))) {
            this.f17663d += 4;
        }
        return new String(this.f17666g, this.f17663d, this.f17664e - this.f17663d);
    }

    /* renamed from: b */
    private String m19791b() {
        this.f17662c++;
        this.f17663d = this.f17662c;
        this.f17664e = this.f17663d;
        while (this.f17662c != this.f17661b) {
            if (this.f17666g[this.f17662c] == CoreConstants.DOUBLE_QUOTE_CHAR) {
                this.f17662c++;
                while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] == ' ') {
                    this.f17662c++;
                }
                return new String(this.f17666g, this.f17663d, this.f17664e - this.f17663d);
            }
            if (this.f17666g[this.f17662c] == CoreConstants.ESCAPE_CHAR) {
                this.f17666g[this.f17664e] = m19794e();
            } else {
                this.f17666g[this.f17664e] = this.f17666g[this.f17662c];
            }
            this.f17662c++;
            this.f17664e++;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
    }

    /* renamed from: c */
    private String m19792c() {
        if (this.f17662c + 4 >= this.f17661b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
        }
        int i;
        this.f17663d = this.f17662c;
        this.f17662c++;
        while (this.f17662c != this.f17661b && this.f17666g[this.f17662c] != '+' && this.f17666g[this.f17662c] != CoreConstants.COMMA_CHAR && this.f17666g[this.f17662c] != ';') {
            int i2;
            if (this.f17666g[this.f17662c] == ' ') {
                this.f17664e = this.f17662c;
                this.f17662c++;
                while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] == ' ') {
                    this.f17662c++;
                }
                i = this.f17664e - this.f17663d;
                if (i >= 5 || (i & 1) == 0) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
                }
                byte[] bArr = new byte[(i / 2)];
                int i3 = this.f17663d + 1;
                for (i2 = 0; i2 < bArr.length; i2++) {
                    bArr[i2] = (byte) m19789a(i3);
                    i3 += 2;
                }
                return new String(this.f17666g, this.f17663d, i);
            }
            if (this.f17666g[this.f17662c] >= 'A' && this.f17666g[this.f17662c] <= 'F') {
                char[] cArr = this.f17666g;
                i2 = this.f17662c;
                cArr[i2] = (char) (cArr[i2] + 32);
            }
            this.f17662c++;
        }
        this.f17664e = this.f17662c;
        i = this.f17664e - this.f17663d;
        if (i >= 5) {
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
    }

    /* renamed from: d */
    private String m19793d() {
        this.f17663d = this.f17662c;
        this.f17664e = this.f17662c;
        while (this.f17662c < this.f17661b) {
            char[] cArr;
            int i;
            switch (this.f17666g[this.f17662c]) {
                case ' ':
                    this.f17665f = this.f17664e;
                    this.f17662c++;
                    cArr = this.f17666g;
                    i = this.f17664e;
                    this.f17664e = i + 1;
                    cArr[i] = ' ';
                    while (this.f17662c < this.f17661b && this.f17666g[this.f17662c] == ' ') {
                        cArr = this.f17666g;
                        i = this.f17664e;
                        this.f17664e = i + 1;
                        cArr[i] = ' ';
                        this.f17662c++;
                    }
                    if (this.f17662c != this.f17661b && this.f17666g[this.f17662c] != CoreConstants.COMMA_CHAR && this.f17666g[this.f17662c] != '+' && this.f17666g[this.f17662c] != ';') {
                        break;
                    }
                    return new String(this.f17666g, this.f17663d, this.f17665f - this.f17663d);
                    break;
                case '+':
                case ',':
                case ';':
                    return new String(this.f17666g, this.f17663d, this.f17664e - this.f17663d);
                case '\\':
                    cArr = this.f17666g;
                    i = this.f17664e;
                    this.f17664e = i + 1;
                    cArr[i] = m19794e();
                    this.f17662c++;
                    break;
                default:
                    cArr = this.f17666g;
                    i = this.f17664e;
                    this.f17664e = i + 1;
                    cArr[i] = this.f17666g[this.f17662c];
                    this.f17662c++;
                    break;
            }
        }
        return new String(this.f17666g, this.f17663d, this.f17664e - this.f17663d);
    }

    /* renamed from: e */
    private char m19794e() {
        this.f17662c++;
        if (this.f17662c == this.f17661b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f17660a);
        }
        switch (this.f17666g[this.f17662c]) {
            case ' ':
            case '\"':
            case '#':
            case '%':
            case '*':
            case '+':
            case ',':
            case ';':
            case '<':
            case '=':
            case '>':
            case '\\':
            case '_':
                return this.f17666g[this.f17662c];
            default:
                return m19795f();
        }
    }

    /* renamed from: f */
    private char m19795f() {
        int a = m19789a(this.f17662c);
        this.f17662c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < Opcodes.CHECKCAST || a > 247) {
            return '?';
        }
        int i;
        if (a <= 223) {
            i = 1;
            a &= 31;
        } else if (a <= 239) {
            i = 2;
            a &= 15;
        } else {
            i = 3;
            a &= 7;
        }
        int i2 = a;
        for (a = 0; a < i; a++) {
            this.f17662c++;
            if (this.f17662c == this.f17661b || this.f17666g[this.f17662c] != CoreConstants.ESCAPE_CHAR) {
                return '?';
            }
            this.f17662c++;
            int a2 = m19789a(this.f17662c);
            this.f17662c++;
            if ((a2 & Opcodes.CHECKCAST) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (a2 & 63);
        }
        return (char) i2;
    }

    /* renamed from: a */
    private int m19789a(int i) {
        if (i + 1 >= this.f17661b) {
            throw new IllegalStateException("Malformed DN: " + this.f17660a);
        }
        int i2;
        int i3;
        char c = this.f17666g[i];
        if (c >= '0' && c <= '9') {
            i2 = c - 48;
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 87;
        } else if (c < 'A' || c > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f17660a);
        } else {
            i2 = c - 55;
        }
        char c2 = this.f17666g[i + 1];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - 48;
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 87;
        } else if (c2 < 'A' || c2 > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f17660a);
        } else {
            i3 = c2 - 55;
        }
        return (i2 << 4) + i3;
    }

    /* renamed from: a */
    public String m19796a(String str) {
        this.f17662c = 0;
        this.f17663d = 0;
        this.f17664e = 0;
        this.f17665f = 0;
        this.f17666g = this.f17660a.toCharArray();
        String a = m19790a();
        if (a == null) {
            return null;
        }
        do {
            String str2 = "";
            if (this.f17662c == this.f17661b) {
                return null;
            }
            switch (this.f17666g[this.f17662c]) {
                case '\"':
                    str2 = m19791b();
                    break;
                case '#':
                    str2 = m19792c();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = m19793d();
                    break;
            }
            if (str.equalsIgnoreCase(a)) {
                return str2;
            }
            if (this.f17662c >= this.f17661b) {
                return null;
            }
            if (this.f17666g[this.f17662c] == CoreConstants.COMMA_CHAR || this.f17666g[this.f17662c] == ';' || this.f17666g[this.f17662c] == '+') {
                this.f17662c++;
                a = m19790a();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f17660a);
            }
        } while (a != null);
        throw new IllegalStateException("Malformed DN: " + this.f17660a);
    }
}
