package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class zzaof extends zzaop {
    private static final Reader bfJ = new C33601();
    private static final Object bfK = new Object();
    private final List<Object> bfL = new ArrayList();

    /* renamed from: com.google.android.gms.internal.zzaof$1 */
    static class C33601 extends Reader {
        C33601() {
        }

        public void close() throws IOException {
            throw new AssertionError();
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    public zzaof(zzamy zzamy) {
        super(bfJ);
        this.bfL.add(zzamy);
    }

    /* renamed from: i */
    private Object m16014i() {
        return this.bfL.get(this.bfL.size() - 1);
    }

    /* renamed from: j */
    private Object m16015j() {
        return this.bfL.remove(this.bfL.size() - 1);
    }

    private void zza(zzaoq zzaoq) throws IOException {
        if (mo4189h() != zzaoq) {
            String valueOf = String.valueOf(zzaoq);
            String valueOf2 = String.valueOf(mo4189h());
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
        }
    }

    public void beginArray() throws IOException {
        zza(zzaoq.BEGIN_ARRAY);
        this.bfL.add(((zzamv) m16014i()).iterator());
    }

    public void beginObject() throws IOException {
        zza(zzaoq.BEGIN_OBJECT);
        this.bfL.add(((zzanb) m16014i()).entrySet().iterator());
    }

    public void close() throws IOException {
        this.bfL.clear();
        this.bfL.add(bfK);
    }

    public void endArray() throws IOException {
        zza(zzaoq.END_ARRAY);
        m16015j();
        m16015j();
    }

    public void endObject() throws IOException {
        zza(zzaoq.END_OBJECT);
        m16015j();
        m16015j();
    }

    /* renamed from: h */
    public zzaoq mo4189h() throws IOException {
        if (this.bfL.isEmpty()) {
            return zzaoq.END_DOCUMENT;
        }
        Object i = m16014i();
        if (i instanceof Iterator) {
            boolean z = this.bfL.get(this.bfL.size() - 2) instanceof zzanb;
            Iterator it = (Iterator) i;
            if (!it.hasNext()) {
                return z ? zzaoq.END_OBJECT : zzaoq.END_ARRAY;
            } else {
                if (z) {
                    return zzaoq.NAME;
                }
                this.bfL.add(it.next());
                return mo4189h();
            }
        } else if (i instanceof zzanb) {
            return zzaoq.BEGIN_OBJECT;
        } else {
            if (i instanceof zzamv) {
                return zzaoq.BEGIN_ARRAY;
            }
            if (i instanceof zzane) {
                zzane zzane = (zzane) i;
                if (zzane.zzczw()) {
                    return zzaoq.STRING;
                }
                if (zzane.zzczu()) {
                    return zzaoq.BOOLEAN;
                }
                if (zzane.zzczv()) {
                    return zzaoq.NUMBER;
                }
                throw new AssertionError();
            } else if (i instanceof zzana) {
                return zzaoq.bhH;
            } else {
                if (i == bfK) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    public boolean hasNext() throws IOException {
        zzaoq h = mo4189h();
        return (h == zzaoq.END_OBJECT || h == zzaoq.END_ARRAY) ? false : true;
    }

    /* renamed from: k */
    public void m16017k() throws IOException {
        zza(zzaoq.NAME);
        Entry entry = (Entry) ((Iterator) m16014i()).next();
        this.bfL.add(entry.getValue());
        this.bfL.add(new zzane((String) entry.getKey()));
    }

    public boolean nextBoolean() throws IOException {
        zza(zzaoq.BOOLEAN);
        return ((zzane) m16015j()).zzczl();
    }

    public double nextDouble() throws IOException {
        zzaoq h = mo4189h();
        if (h == zzaoq.NUMBER || h == zzaoq.STRING) {
            double zzczi = ((zzane) m16014i()).zzczi();
            if (isLenient() || !(Double.isNaN(zzczi) || Double.isInfinite(zzczi))) {
                m16015j();
                return zzczi;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + zzczi);
        }
        String valueOf = String.valueOf(zzaoq.NUMBER);
        String valueOf2 = String.valueOf(h);
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public int nextInt() throws IOException {
        zzaoq h = mo4189h();
        if (h == zzaoq.NUMBER || h == zzaoq.STRING) {
            int zzczk = ((zzane) m16014i()).zzczk();
            m16015j();
            return zzczk;
        }
        String valueOf = String.valueOf(zzaoq.NUMBER);
        String valueOf2 = String.valueOf(h);
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public long nextLong() throws IOException {
        zzaoq h = mo4189h();
        if (h == zzaoq.NUMBER || h == zzaoq.STRING) {
            long zzczj = ((zzane) m16014i()).zzczj();
            m16015j();
            return zzczj;
        }
        String valueOf = String.valueOf(zzaoq.NUMBER);
        String valueOf2 = String.valueOf(h);
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public String nextName() throws IOException {
        zza(zzaoq.NAME);
        Entry entry = (Entry) ((Iterator) m16014i()).next();
        this.bfL.add(entry.getValue());
        return (String) entry.getKey();
    }

    public void nextNull() throws IOException {
        zza(zzaoq.bhH);
        m16015j();
    }

    public String nextString() throws IOException {
        zzaoq h = mo4189h();
        if (h == zzaoq.STRING || h == zzaoq.NUMBER) {
            return ((zzane) m16015j()).zzczh();
        }
        String valueOf = String.valueOf(zzaoq.STRING);
        String valueOf2 = String.valueOf(h);
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public void skipValue() throws IOException {
        if (mo4189h() == zzaoq.NAME) {
            nextName();
        } else {
            m16015j();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
