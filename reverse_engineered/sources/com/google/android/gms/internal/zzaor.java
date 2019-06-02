package com.google.android.gms.internal;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class zzaor implements Closeable, Flushable {
    private static final String[] bhK = new String[128];
    private static final String[] bhL = ((String[]) bhK.clone());
    private boolean bec;
    private boolean bed;
    private String bhM;
    private String bhN;
    private boolean bhn;
    private int[] bhv = new int[32];
    private int bhw = 0;
    private final Writer out;
    private String separator;

    static {
        for (int i = 0; i <= 31; i++) {
            bhK[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        bhK[34] = "\\\"";
        bhK[92] = "\\\\";
        bhK[9] = "\\t";
        bhK[8] = "\\b";
        bhK[10] = "\\n";
        bhK[13] = "\\r";
        bhK[12] = "\\f";
        bhL[60] = "\\u003c";
        bhL[62] = "\\u003e";
        bhL[38] = "\\u0026";
        bhL[61] = "\\u003d";
        bhL[39] = "\\u0027";
    }

    public zzaor(Writer writer) {
        zzaec(6);
        this.separator = ":";
        this.bec = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    /* renamed from: F */
    private int m16018F() {
        if (this.bhw != 0) {
            return this.bhv[this.bhw - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* renamed from: G */
    private void m16019G() throws IOException {
        if (this.bhN != null) {
            m16021I();
            zzte(this.bhN);
            this.bhN = null;
        }
    }

    /* renamed from: H */
    private void m16020H() throws IOException {
        if (this.bhM != null) {
            this.out.write("\n");
            int i = this.bhw;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.bhM);
            }
        }
    }

    /* renamed from: I */
    private void m16021I() throws IOException {
        int F = m16018F();
        if (F == 5) {
            this.out.write(44);
        } else if (F != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        m16020H();
        zzaee(4);
    }

    private void zzaec(int i) {
        if (this.bhw == this.bhv.length) {
            Object obj = new int[(this.bhw * 2)];
            System.arraycopy(this.bhv, 0, obj, 0, this.bhw);
            this.bhv = obj;
        }
        int[] iArr = this.bhv;
        int i2 = this.bhw;
        this.bhw = i2 + 1;
        iArr[i2] = i;
    }

    private void zzaee(int i) {
        this.bhv[this.bhw - 1] = i;
    }

    private zzaor zzc(int i, int i2, String str) throws IOException {
        int F = m16018F();
        if (F != i2 && F != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.bhN != null) {
            String str2 = "Dangling name: ";
            String valueOf = String.valueOf(this.bhN);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            this.bhw--;
            if (F == i2) {
                m16020H();
            }
            this.out.write(str);
            return this;
        }
    }

    private void zzdd(boolean z) throws IOException {
        switch (m16018F()) {
            case 1:
                zzaee(2);
                m16020H();
                return;
            case 2:
                this.out.append(CoreConstants.COMMA_CHAR);
                m16020H();
                return;
            case 4:
                this.out.append(this.separator);
                zzaee(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.bhn) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        if (this.bhn || z) {
            zzaee(7);
            return;
        }
        throw new IllegalStateException("JSON must start with an array or an object.");
    }

    private zzaor zzq(int i, String str) throws IOException {
        zzdd(true);
        zzaec(i);
        this.out.write(str);
        return this;
    }

    private void zzte(String str) throws IOException {
        int i = 0;
        String[] strArr = this.bed ? bhL : bhK;
        this.out.write("\"");
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            String str2;
            if (charAt < '') {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            } else {
                if (charAt == ' ') {
                    str2 = "\\u2028";
                } else if (charAt == ' ') {
                    str2 = "\\u2029";
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.out.write(str, i, length - i);
        }
        this.out.write("\"");
    }

    /* renamed from: D */
    public final boolean m16022D() {
        return this.bed;
    }

    /* renamed from: E */
    public final boolean m16023E() {
        return this.bec;
    }

    public void close() throws IOException {
        this.out.close();
        int i = this.bhw;
        if (i > 1 || (i == 1 && this.bhv[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.bhw = 0;
    }

    public void flush() throws IOException {
        if (this.bhw == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public boolean isLenient() {
        return this.bhn;
    }

    /* renamed from: n */
    public zzaor mo4202n() throws IOException {
        m16019G();
        return zzq(1, "[");
    }

    /* renamed from: o */
    public zzaor mo4203o() throws IOException {
        return zzc(1, 2, "]");
    }

    /* renamed from: p */
    public zzaor mo4204p() throws IOException {
        m16019G();
        return zzq(3, "{");
    }

    /* renamed from: q */
    public zzaor mo4205q() throws IOException {
        return zzc(3, 5, C0880h.f2222d);
    }

    /* renamed from: r */
    public zzaor mo4206r() throws IOException {
        if (this.bhN != null) {
            if (this.bec) {
                m16019G();
            } else {
                this.bhN = null;
                return this;
            }
        }
        zzdd(false);
        this.out.write("null");
        return this;
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.bhM = null;
            this.separator = ":";
            return;
        }
        this.bhM = str;
        this.separator = ": ";
    }

    public final void setLenient(boolean z) {
        this.bhn = z;
    }

    public zzaor zza(Number number) throws IOException {
        if (number == null) {
            return mo4206r();
        }
        m16019G();
        CharSequence obj = number.toString();
        if (this.bhn || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            zzdd(false);
            this.out.append(obj);
            return this;
        }
        String valueOf = String.valueOf(number);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 39).append("Numeric values must be finite, but was ").append(valueOf).toString());
    }

    public zzaor zzcp(long j) throws IOException {
        m16019G();
        zzdd(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public zzaor zzcz(boolean z) throws IOException {
        m16019G();
        zzdd(false);
        this.out.write(z ? "true" : "false");
        return this;
    }

    public final void zzdb(boolean z) {
        this.bed = z;
    }

    public final void zzdc(boolean z) {
        this.bec = z;
    }

    public zzaor zzta(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.bhN != null) {
            throw new IllegalStateException();
        } else if (this.bhw == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.bhN = str;
            return this;
        }
    }

    public zzaor zztb(String str) throws IOException {
        if (str == null) {
            return mo4206r();
        }
        m16019G();
        zzdd(false);
        zzte(str);
        return this;
    }
}
