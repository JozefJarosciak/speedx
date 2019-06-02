package com.google.android.gms.internal;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.ActionConst;
import com.avos.avoscloud.AVException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class zzaop implements Closeable {
    private static final char[] bhm = ")]}'\n".toCharArray();
    private int aYn = 0;
    private boolean bhn = false;
    private final char[] bho = new char[1024];
    private int bhp = 0;
    private int bhq = 0;
    private int bhr = 0;
    private long bhs;
    private int bht;
    private String bhu;
    private int[] bhv = new int[32];
    private int bhw = 0;
    private String[] bhx;
    private int[] bhy;
    private final Reader in;
    private int pos = 0;

    /* renamed from: com.google.android.gms.internal.zzaop$1 */
    static class C33771 extends zzanu {
        C33771() {
        }

        public void zzi(zzaop zzaop) throws IOException {
            if (zzaop instanceof zzaof) {
                ((zzaof) zzaop).m16017k();
                return;
            }
            int zzag = zzaop.bhr;
            if (zzag == 0) {
                zzag = zzaop.m16007u();
            }
            if (zzag == 13) {
                zzaop.bhr = 9;
            } else if (zzag == 12) {
                zzaop.bhr = 8;
            } else if (zzag == 14) {
                zzaop.bhr = 10;
            } else {
                String valueOf = String.valueOf(zzaop.mo4189h());
                int zzai = zzaop.getLineNumber();
                int zzaj = zzaop.getColumnNumber();
                String path = zzaop.getPath();
                throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" ").append(" at line ").append(zzai).append(" column ").append(zzaj).append(" path ").append(path).toString());
            }
        }
    }

    static {
        zzanu.bff = new C33771();
    }

    public zzaop(Reader reader) {
        int[] iArr = this.bhv;
        int i = this.bhw;
        this.bhw = i + 1;
        iArr[i] = 6;
        this.bhx = new String[32];
        this.bhy = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    /* renamed from: A */
    private void m16004A() throws IOException {
        char c;
        do {
            if (this.pos < this.aYn || zzaed(1)) {
                char[] cArr = this.bho;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.bhp++;
                    this.bhq = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    /* renamed from: B */
    private char m16005B() throws IOException {
        if (this.pos != this.aYn || zzaed(1)) {
            char[] cArr = this.bho;
            int i = this.pos;
            this.pos = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\n':
                    this.bhp++;
                    this.bhq = this.pos;
                    return c;
                case 'b':
                    return '\b';
                case 'f':
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case AVException.OBJECT_TOO_LARGE /*116*/:
                    return '\t';
                case 'u':
                    if (this.pos + 4 <= this.aYn || zzaed(4)) {
                        int i2 = this.pos;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.bho[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= 'f') {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                String str = "\\u";
                                String valueOf = String.valueOf(new String(this.bho, this.pos, 4));
                                throw new NumberFormatException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.pos += 4;
                        return c;
                    }
                    throw zztd("Unterminated escape sequence");
                default:
                    return c;
            }
        }
        throw zztd("Unterminated escape sequence");
    }

    /* renamed from: C */
    private void m16006C() throws IOException {
        zzda(true);
        this.pos--;
        if (this.pos + bhm.length <= this.aYn || zzaed(bhm.length)) {
            int i = 0;
            while (i < bhm.length) {
                if (this.bho[this.pos + i] == bhm[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.pos += bhm.length;
        }
    }

    private int getColumnNumber() {
        return (this.pos - this.bhq) + 1;
    }

    private int getLineNumber() {
        return this.bhp + 1;
    }

    /* renamed from: u */
    private int m16007u() throws IOException {
        int zzda;
        int i = this.bhv[this.bhw - 1];
        if (i == 1) {
            this.bhv[this.bhw - 1] = 2;
        } else if (i == 2) {
            switch (zzda(true)) {
                case 44:
                    break;
                case 59:
                    m16012z();
                    break;
                case 93:
                    this.bhr = 4;
                    return 4;
                default:
                    throw zztd("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.bhv[this.bhw - 1] = 4;
            if (i == 5) {
                switch (zzda(true)) {
                    case 44:
                        break;
                    case 59:
                        m16012z();
                        break;
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        this.bhr = 2;
                        return 2;
                    default:
                        throw zztd("Unterminated object");
                }
            }
            zzda = zzda(true);
            switch (zzda) {
                case 34:
                    this.bhr = 13;
                    return 13;
                case 39:
                    m16012z();
                    this.bhr = 12;
                    return 12;
                case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                    if (i != 5) {
                        this.bhr = 2;
                        return 2;
                    }
                    throw zztd("Expected name");
                default:
                    m16012z();
                    this.pos--;
                    if (zze((char) zzda)) {
                        this.bhr = 14;
                        return 14;
                    }
                    throw zztd("Expected name");
            }
        } else if (i == 4) {
            this.bhv[this.bhw - 1] = 5;
            switch (zzda(true)) {
                case 58:
                    break;
                case 61:
                    m16012z();
                    if ((this.pos < this.aYn || zzaed(1)) && this.bho[this.pos] == '>') {
                        this.pos++;
                        break;
                    }
                default:
                    throw zztd("Expected ':'");
            }
        } else if (i == 6) {
            if (this.bhn) {
                m16006C();
            }
            this.bhv[this.bhw - 1] = 7;
        } else if (i == 7) {
            if (zzda(false) == -1) {
                this.bhr = 17;
                return 17;
            }
            m16012z();
            this.pos--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (zzda(true)) {
            case 34:
                if (this.bhw == 1) {
                    m16012z();
                }
                this.bhr = 9;
                return 9;
            case 39:
                m16012z();
                this.bhr = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.bhr = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.bhr = 4;
                    return 4;
                }
                break;
            case AVException.INVALID_ACL /*123*/:
                this.bhr = 1;
                return 1;
            default:
                this.pos--;
                if (this.bhw == 1) {
                    m16012z();
                }
                zzda = m16008v();
                if (zzda != 0) {
                    return zzda;
                }
                zzda = m16009w();
                if (zzda != 0) {
                    return zzda;
                }
                if (zze(this.bho[this.pos])) {
                    m16012z();
                    this.bhr = 10;
                    return 10;
                }
                throw zztd("Expected value");
        }
        if (i == 1 || i == 2) {
            m16012z();
            this.pos--;
            this.bhr = 7;
            return 7;
        }
        throw zztd("Unexpected value");
    }

    /* renamed from: v */
    private int m16008v() throws IOException {
        String str;
        int i;
        char c = this.bho[this.pos];
        String str2;
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = ActionConst.NULL;
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.pos + i2 >= this.aYn && !zzaed(i2 + 1)) {
                return 0;
            }
            char c2 = this.bho[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.pos + length < this.aYn || zzaed(length + 1)) && zze(this.bho[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.bhr = i;
        return i;
    }

    /* renamed from: w */
    private int m16009w() throws IOException {
        char[] cArr = this.bho;
        int i = this.pos;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.aYn;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (zzaed(i4 + 1)) {
                    i6 = this.pos;
                    i5 = this.aYn;
                } else if (i3 != 2 && i2 != 0 && (j != Long.MIN_VALUE || obj != null)) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.bhs = j;
                    this.pos += i4;
                    this.bhr = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.bht = i4;
                    this.bhr = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case '+':
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case '-':
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case 'E':
                case 'e':
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (zze(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.bht = i4;
            this.bhr = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    /* renamed from: x */
    private String m16010x() throws IOException {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
            if (this.pos + i < this.aYn) {
                switch (this.bho[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case AVException.INVALID_ACL /*123*/:
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m16012z();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.bho.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.bho, this.pos, i);
                this.pos = i + this.pos;
                i = !zzaed(1) ? 0 : 0;
            } else if (zzaed(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.bho, this.pos, i);
            } else {
                stringBuilder.append(this.bho, this.pos, i);
                str = stringBuilder.toString();
            }
            this.pos = i + this.pos;
            return str;
        }
    }

    /* renamed from: y */
    private void m16011y() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.aYn) {
                switch (this.bho[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case AVException.INVALID_ACL /*123*/:
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m16012z();
                        break;
                    default:
                        i++;
                }
                this.pos = i + this.pos;
                return;
            }
            this.pos = i + this.pos;
        } while (zzaed(1));
    }

    /* renamed from: z */
    private void m16012z() throws IOException {
        if (!this.bhn) {
            throw zztd("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void zzaec(int i) {
        if (this.bhw == this.bhv.length) {
            Object obj = new int[(this.bhw * 2)];
            Object obj2 = new int[(this.bhw * 2)];
            Object obj3 = new String[(this.bhw * 2)];
            System.arraycopy(this.bhv, 0, obj, 0, this.bhw);
            System.arraycopy(this.bhy, 0, obj2, 0, this.bhw);
            System.arraycopy(this.bhx, 0, obj3, 0, this.bhw);
            this.bhv = obj;
            this.bhy = obj2;
            this.bhx = obj3;
        }
        int[] iArr = this.bhv;
        int i2 = this.bhw;
        this.bhw = i2 + 1;
        iArr[i2] = i;
    }

    private boolean zzaed(int i) throws IOException {
        Object obj = this.bho;
        this.bhq -= this.pos;
        if (this.aYn != this.pos) {
            this.aYn -= this.pos;
            System.arraycopy(obj, this.pos, obj, 0, this.aYn);
        } else {
            this.aYn = 0;
        }
        this.pos = 0;
        do {
            int read = this.in.read(obj, this.aYn, obj.length - this.aYn);
            if (read == -1) {
                return false;
            }
            this.aYn = read + this.aYn;
            if (this.bhp == 0 && this.bhq == 0 && this.aYn > 0 && obj[0] == '﻿') {
                this.pos++;
                this.bhq++;
                i++;
            }
        } while (this.aYn < i);
        return true;
    }

    private int zzda(boolean z) throws IOException {
        char[] cArr = this.bho;
        int i = this.pos;
        int i2 = this.aYn;
        while (true) {
            int lineNumber;
            if (i == i2) {
                this.pos = i;
                if (zzaed(1)) {
                    i = this.pos;
                    i2 = this.aYn;
                } else if (!z) {
                    return -1;
                } else {
                    String valueOf = String.valueOf("End of input at line ");
                    lineNumber = getLineNumber();
                    throw new EOFException(new StringBuilder(String.valueOf(valueOf).length() + 30).append(valueOf).append(lineNumber).append(" column ").append(getColumnNumber()).toString());
                }
            }
            lineNumber = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.bhp++;
                this.bhq = lineNumber;
                i = lineNumber;
            } else if (c == ' ' || c == '\r') {
                i = lineNumber;
            } else if (c == '\t') {
                i = lineNumber;
            } else if (c == '/') {
                this.pos = lineNumber;
                if (lineNumber == i2) {
                    this.pos--;
                    boolean zzaed = zzaed(2);
                    this.pos++;
                    if (!zzaed) {
                        return c;
                    }
                }
                m16012z();
                switch (cArr[this.pos]) {
                    case '*':
                        this.pos++;
                        if (zztc("*/")) {
                            i = this.pos + 2;
                            i2 = this.aYn;
                            break;
                        }
                        throw zztd("Unterminated comment");
                    case '/':
                        this.pos++;
                        m16004A();
                        i = this.pos;
                        i2 = this.aYn;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.pos = lineNumber;
                m16012z();
                m16004A();
                i = this.pos;
                i2 = this.aYn;
            } else {
                this.pos = lineNumber;
                return c;
            }
        }
    }

    private boolean zze(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case AVException.INVALID_ACL /*123*/:
            case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m16012z();
                break;
            default:
                return true;
        }
        return false;
    }

    private String zzf(char c) throws IOException {
        char[] cArr = this.bho;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = this.pos;
            int i2 = this.aYn;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    return stringBuilder.toString();
                }
                if (c2 == CoreConstants.ESCAPE_CHAR) {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    stringBuilder.append(m16005B());
                    i = this.pos;
                    i2 = this.aYn;
                    i4 = i;
                } else if (c2 == '\n') {
                    this.bhp++;
                    this.bhq = i4;
                }
                i3 = i4;
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.pos = i3;
        } while (zzaed(1));
        throw zztd("Unterminated string");
    }

    private void zzg(char c) throws IOException {
        char[] cArr = this.bho;
        do {
            int i = this.pos;
            int i2 = this.aYn;
            int i3 = i;
            while (i3 < i2) {
                i = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i;
                    return;
                }
                if (c2 == CoreConstants.ESCAPE_CHAR) {
                    this.pos = i;
                    m16005B();
                    i = this.pos;
                    i2 = this.aYn;
                } else if (c2 == '\n') {
                    this.bhp++;
                    this.bhq = i;
                }
                i3 = i;
            }
            this.pos = i3;
        } while (zzaed(1));
        throw zztd("Unterminated string");
    }

    private boolean zztc(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.aYn && !zzaed(str.length())) {
                return false;
            }
            if (this.bho[this.pos] == '\n') {
                this.bhp++;
                this.bhq = this.pos + 1;
            } else {
                int i = 0;
                while (i < str.length()) {
                    if (this.bho[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private IOException zztd(String str) throws IOException {
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new zzaos(new StringBuilder((String.valueOf(str).length() + 45) + String.valueOf(path).length()).append(str).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void beginArray() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 3) {
            zzaec(1);
            this.bhy[this.bhw - 1] = 0;
            this.bhr = 0;
            return;
        }
        String valueOf = String.valueOf(mo4189h());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 74) + String.valueOf(path).length()).append("Expected BEGIN_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void beginObject() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 1) {
            zzaec(3);
            this.bhr = 0;
            return;
        }
        String valueOf = String.valueOf(mo4189h());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 75) + String.valueOf(path).length()).append("Expected BEGIN_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void close() throws IOException {
        this.bhr = 0;
        this.bhv[0] = 8;
        this.bhw = 1;
        this.in.close();
    }

    public void endArray() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 4) {
            this.bhw--;
            int[] iArr = this.bhy;
            int i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
            this.bhr = 0;
            return;
        }
        String valueOf = String.valueOf(mo4189h());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 72) + String.valueOf(path).length()).append("Expected END_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void endObject() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 2) {
            this.bhw--;
            this.bhx[this.bhw] = null;
            int[] iArr = this.bhy;
            int i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
            this.bhr = 0;
            return;
        }
        String valueOf = String.valueOf(mo4189h());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 73) + String.valueOf(path).length()).append("Expected END_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String getPath() {
        StringBuilder append = new StringBuilder().append(CoreConstants.DOLLAR);
        int i = this.bhw;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.bhv[i2]) {
                case 1:
                case 2:
                    append.append('[').append(this.bhy[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append(CoreConstants.DOT);
                    if (this.bhx[i2] == null) {
                        break;
                    }
                    append.append(this.bhx[i2]);
                    break;
                default:
                    break;
            }
        }
        return append.toString();
    }

    /* renamed from: h */
    public zzaoq mo4189h() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        switch (i) {
            case 1:
                return zzaoq.BEGIN_OBJECT;
            case 2:
                return zzaoq.END_OBJECT;
            case 3:
                return zzaoq.BEGIN_ARRAY;
            case 4:
                return zzaoq.END_ARRAY;
            case 5:
            case 6:
                return zzaoq.BOOLEAN;
            case 7:
                return zzaoq.bhH;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzaoq.STRING;
            case 12:
            case 13:
            case 14:
                return zzaoq.NAME;
            case 15:
            case 16:
                return zzaoq.NUMBER;
            case 17:
                return zzaoq.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public boolean hasNext() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.bhn;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 5) {
            this.bhr = 0;
            int[] iArr = this.bhy;
            i = this.bhw - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.bhr = 0;
            int[] iArr2 = this.bhy;
            r2 = this.bhw - 1;
            iArr2[r2] = iArr2[r2] + 1;
            return false;
        } else {
            String valueOf = String.valueOf(mo4189h());
            r2 = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 72) + String.valueOf(path).length()).append("Expected a boolean but was ").append(valueOf).append(" at line ").append(r2).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 15) {
            this.bhr = 0;
            int[] iArr = this.bhy;
            int i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.bhs;
        }
        if (i == 16) {
            this.bhu = new String(this.bho, this.pos, this.bht);
            this.pos += this.bht;
        } else if (i == 8 || i == 9) {
            this.bhu = zzf(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
        } else if (i == 10) {
            this.bhu = m16010x();
        } else if (i != 11) {
            String valueOf = String.valueOf(mo4189h());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 71) + String.valueOf(path).length()).append("Expected a double but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhr = 11;
        double parseDouble = Double.parseDouble(this.bhu);
        if (this.bhn || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.bhu = null;
            this.bhr = 0;
            int[] iArr2 = this.bhy;
            columnNumber = this.bhw - 1;
            iArr2[columnNumber] = iArr2[columnNumber] + 1;
            return parseDouble;
        }
        columnNumber = getLineNumber();
        int columnNumber2 = getColumnNumber();
        String path2 = getPath();
        throw new zzaos(new StringBuilder(String.valueOf(path2).length() + 102).append("JSON forbids NaN and infinities: ").append(parseDouble).append(" at line ").append(columnNumber).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
    }

    public int nextInt() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.bhs;
            if (this.bhs != ((long) i)) {
                long j = this.bhs;
                int lineNumber = getLineNumber();
                int columnNumber = getColumnNumber();
                String path = getPath();
                throw new NumberFormatException(new StringBuilder(String.valueOf(path).length() + 89).append("Expected an int but was ").append(j).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.bhr = 0;
            iArr = this.bhy;
            i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            String valueOf;
            int columnNumber2;
            String path2;
            if (i == 16) {
                this.bhu = new String(this.bho, this.pos, this.bht);
                this.pos += this.bht;
            } else if (i == 8 || i == 9) {
                this.bhu = zzf(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
                try {
                    i = Integer.parseInt(this.bhu);
                    this.bhr = 0;
                    iArr = this.bhy;
                    i2 = this.bhw - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                valueOf = String.valueOf(mo4189h());
                i2 = getLineNumber();
                columnNumber2 = getColumnNumber();
                path2 = getPath();
                throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path2).length()).append("Expected an int but was ").append(valueOf).append(" at line ").append(i2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bhr = 11;
            double parseDouble = Double.parseDouble(this.bhu);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                valueOf = this.bhu;
                i2 = getLineNumber();
                columnNumber2 = getColumnNumber();
                path2 = getPath();
                throw new NumberFormatException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path2).length()).append("Expected an int but was ").append(valueOf).append(" at line ").append(i2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bhu = null;
            this.bhr = 0;
            iArr = this.bhy;
            i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    public long nextLong() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 15) {
            this.bhr = 0;
            int[] iArr = this.bhy;
            int i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.bhs;
        }
        long parseLong;
        int i3;
        if (i == 16) {
            this.bhu = new String(this.bho, this.pos, this.bht);
            this.pos += this.bht;
        } else if (i == 8 || i == 9) {
            this.bhu = zzf(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
            try {
                parseLong = Long.parseLong(this.bhu);
                this.bhr = 0;
                int[] iArr2 = this.bhy;
                i3 = this.bhw - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            String valueOf = String.valueOf(mo4189h());
            int lineNumber = getLineNumber();
            i3 = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a long but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(i3).append(" path ").append(path).toString());
        }
        this.bhr = 11;
        double parseDouble = Double.parseDouble(this.bhu);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            valueOf = this.bhu;
            lineNumber = getLineNumber();
            i3 = getColumnNumber();
            path = getPath();
            throw new NumberFormatException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a long but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(i3).append(" path ").append(path).toString());
        }
        this.bhu = null;
        this.bhr = 0;
        iArr2 = this.bhy;
        i3 = this.bhw - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    public String nextName() throws IOException {
        String x;
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 14) {
            x = m16010x();
        } else if (i == 12) {
            x = zzf(CoreConstants.SINGLE_QUOTE_CHAR);
        } else if (i == 13) {
            x = zzf(CoreConstants.DOUBLE_QUOTE_CHAR);
        } else {
            String valueOf = String.valueOf(mo4189h());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhr = 0;
        this.bhx[this.bhw - 1] = x;
        return x;
    }

    public void nextNull() throws IOException {
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 7) {
            this.bhr = 0;
            int[] iArr = this.bhy;
            int i2 = this.bhw - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        String valueOf = String.valueOf(mo4189h());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 67) + String.valueOf(path).length()).append("Expected null but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String nextString() throws IOException {
        String x;
        int lineNumber;
        int i = this.bhr;
        if (i == 0) {
            i = m16007u();
        }
        if (i == 10) {
            x = m16010x();
        } else if (i == 8) {
            x = zzf(CoreConstants.SINGLE_QUOTE_CHAR);
        } else if (i == 9) {
            x = zzf(CoreConstants.DOUBLE_QUOTE_CHAR);
        } else if (i == 11) {
            x = this.bhu;
            this.bhu = null;
        } else if (i == 15) {
            x = Long.toString(this.bhs);
        } else if (i == 16) {
            x = new String(this.bho, this.pos, this.bht);
            this.pos += this.bht;
        } else {
            String valueOf = String.valueOf(mo4189h());
            lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 71) + String.valueOf(path).length()).append("Expected a string but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhr = 0;
        int[] iArr = this.bhy;
        lineNumber = this.bhw - 1;
        iArr[lineNumber] = iArr[lineNumber] + 1;
        return x;
    }

    public final void setLenient(boolean z) {
        this.bhn = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.bhr;
            if (i2 == 0) {
                i2 = m16007u();
            }
            if (i2 == 3) {
                zzaec(1);
                i++;
            } else if (i2 == 1) {
                zzaec(3);
                i++;
            } else if (i2 == 4) {
                this.bhw--;
                i--;
            } else if (i2 == 2) {
                this.bhw--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                m16011y();
            } else if (i2 == 8 || i2 == 12) {
                zzg(CoreConstants.SINGLE_QUOTE_CHAR);
            } else if (i2 == 9 || i2 == 13) {
                zzg(CoreConstants.DOUBLE_QUOTE_CHAR);
            } else if (i2 == 16) {
                this.pos += this.bht;
            }
            this.bhr = 0;
        } while (i != 0);
        int[] iArr = this.bhy;
        int i3 = this.bhw - 1;
        iArr[i3] = iArr[i3] + 1;
        this.bhx[this.bhw - 1] = "null";
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        int lineNumber = getLineNumber();
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(getColumnNumber()).toString();
    }
}
