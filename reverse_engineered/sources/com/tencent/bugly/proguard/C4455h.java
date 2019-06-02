package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.h */
public final class C4455h {
    /* renamed from: a */
    private ByteBuffer f15669a;
    /* renamed from: b */
    private String f15670b = "GBK";

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.h$a */
    public static class C4454a {
        /* renamed from: a */
        public byte f15667a;
        /* renamed from: b */
        public int f15668b;
    }

    public C4455h(byte[] bArr) {
        this.f15669a = ByteBuffer.wrap(bArr);
    }

    public C4455h(byte[] bArr, int i) {
        this.f15669a = ByteBuffer.wrap(bArr);
        this.f15669a.position(4);
    }

    /* renamed from: a */
    public final void m17623a(byte[] bArr) {
        if (this.f15669a != null) {
            this.f15669a.clear();
        }
        this.f15669a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m17602a(C4454a c4454a, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        c4454a.f15667a = (byte) (b & 15);
        c4454a.f15668b = (b & 240) >> 4;
        if (c4454a.f15668b != 15) {
            return 1;
        }
        c4454a.f15668b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private boolean m17606a(int i) {
        try {
            C4454a c4454a = new C4454a();
            while (true) {
                int a = C4455h.m17602a(c4454a, this.f15669a.duplicate());
                if (i > c4454a.f15668b && c4454a.f15667a != Ascii.VT) {
                    this.f15669a.position(a + this.f15669a.position());
                    m17605a(c4454a.f15667a);
                }
            }
            if (i == c4454a.f15668b) {
                return true;
            }
            return false;
        } catch (C4453g e) {
            return false;
        } catch (BufferUnderflowException e2) {
            return false;
        }
    }

    /* renamed from: a */
    private void m17604a() {
        C4454a c4454a = new C4454a();
        do {
            C4455h.m17602a(c4454a, this.f15669a);
            m17605a(c4454a.f15667a);
        } while (c4454a.f15667a != Ascii.VT);
    }

    /* renamed from: a */
    private void m17605a(byte b) {
        int i = 0;
        int a;
        C4454a c4454a;
        switch (b) {
            case (byte) 0:
                this.f15669a.position(this.f15669a.position() + 1);
                return;
            case (byte) 1:
                this.f15669a.position(2 + this.f15669a.position());
                return;
            case (byte) 2:
                this.f15669a.position(this.f15669a.position() + 4);
                return;
            case (byte) 3:
                this.f15669a.position(this.f15669a.position() + 8);
                return;
            case (byte) 4:
                this.f15669a.position(this.f15669a.position() + 4);
                return;
            case (byte) 5:
                this.f15669a.position(this.f15669a.position() + 8);
                return;
            case (byte) 6:
                i = this.f15669a.get();
                if (i < 0) {
                    i += 256;
                }
                this.f15669a.position(i + this.f15669a.position());
                return;
            case (byte) 7:
                this.f15669a.position(this.f15669a.getInt() + this.f15669a.position());
                return;
            case (byte) 8:
                a = m17616a(0, 0, true);
                while (i < (a << 1)) {
                    c4454a = new C4454a();
                    C4455h.m17602a(c4454a, this.f15669a);
                    m17605a(c4454a.f15667a);
                    i++;
                }
                return;
            case (byte) 9:
                a = m17616a(0, 0, true);
                while (i < a) {
                    c4454a = new C4454a();
                    C4455h.m17602a(c4454a, this.f15669a);
                    m17605a(c4454a.f15667a);
                    i++;
                }
                return;
            case (byte) 10:
                m17604a();
                return;
            case (byte) 11:
            case (byte) 12:
                return;
            case (byte) 13:
                C4454a c4454a2 = new C4454a();
                C4455h.m17602a(c4454a2, this.f15669a);
                if (c4454a2.f15667a != (byte) 0) {
                    throw new C4453g("skipField with invalid type, type value: " + b + ", " + c4454a2.f15667a);
                }
                this.f15669a.position(m17616a(0, 0, true) + this.f15669a.position());
                return;
            default:
                throw new C4453g("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean m17624a(int i, boolean z) {
        if (m17615a((byte) 0, i, z) != (byte) 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final byte m17615a(byte b, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 0:
                    return this.f15669a.get();
                case (byte) 12:
                    return (byte) 0;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final short m17622a(short s, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 0:
                    return (short) this.f15669a.get();
                case (byte) 1:
                    return this.f15669a.getShort();
                case (byte) 12:
                    return (short) 0;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final int m17616a(int i, int i2, boolean z) {
        if (m17606a(i2)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 0:
                    return this.f15669a.get();
                case (byte) 1:
                    return this.f15669a.getShort();
                case (byte) 2:
                    return this.f15669a.getInt();
                case (byte) 12:
                    return 0;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final long m17618a(long j, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 0:
                    return (long) this.f15669a.get();
                case (byte) 1:
                    return (long) this.f15669a.getShort();
                case (byte) 2:
                    return (long) this.f15669a.getInt();
                case (byte) 3:
                    return this.f15669a.getLong();
                case (byte) 12:
                    return 0;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    private float m17601a(float f, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 4:
                    return this.f15669a.getFloat();
                case (byte) 12:
                    return 0.0f;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    private double m17600a(double d, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 4:
                    return (double) this.f15669a.getFloat();
                case (byte) 5:
                    return this.f15669a.getDouble();
                case (byte) 12:
                    return 0.0d;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: b */
    public final String m17625b(int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            int i2;
            byte[] bArr;
            switch (c4454a.f15667a) {
                case (byte) 6:
                    i2 = this.f15669a.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    bArr = new byte[i2];
                    this.f15669a.get(bArr);
                    try {
                        return new String(bArr, this.f15670b);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case (byte) 7:
                    i2 = this.f15669a.getInt();
                    if (i2 > 104857600 || i2 < 0) {
                        throw new C4453g("String too long: " + i2);
                    }
                    bArr = new byte[i2];
                    this.f15669a.get(bArr);
                    try {
                        return new String(bArr, this.f15670b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr);
                    }
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> m17621a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m17603a(new HashMap(), map, i, z);
    }

    /* renamed from: a */
    private <K, V> Map<K, V> m17603a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Entry entry = (Entry) map2.entrySet().iterator().next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 8:
                    int a = m17616a(0, 0, true);
                    if (a < 0) {
                        throw new C4453g("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(m17620a(key, 0, true), m17620a(value, 1, true));
                    }
                    return map;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: d */
    private boolean[] m17609d(int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a < 0) {
                        throw new C4453g("size invalid: " + a);
                    }
                    boolean[] zArr = new boolean[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        boolean z2;
                        if (m17615a((byte) 0, 0, true) != (byte) 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zArr[i2] = z2;
                    }
                    return zArr;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: c */
    public final byte[] m17626c(int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            byte[] bArr;
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a < 0) {
                        throw new C4453g("size invalid: " + a);
                    }
                    bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = m17615a(bArr[0], 0, true);
                    }
                    return bArr;
                case (byte) 13:
                    C4454a c4454a2 = new C4454a();
                    C4455h.m17602a(c4454a2, this.f15669a);
                    if (c4454a2.f15667a != (byte) 0) {
                        throw new C4453g("type mismatch, tag: " + i + ", type: " + c4454a.f15667a + ", " + c4454a2.f15667a);
                    }
                    int a2 = m17616a(0, 0, true);
                    if (a2 < 0) {
                        throw new C4453g("invalid size, tag: " + i + ", type: " + c4454a.f15667a + ", " + c4454a2.f15667a + ", size: " + a2);
                    }
                    bArr = new byte[a2];
                    this.f15669a.get(bArr);
                    return bArr;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: e */
    private short[] m17610e(int i, boolean z) {
        short[] sArr = null;
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a >= 0) {
                        sArr = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr[i2] = m17622a(sArr[0], 0, true);
                        }
                        break;
                    }
                    throw new C4453g("size invalid: " + a);
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return sArr;
    }

    /* renamed from: f */
    private int[] m17611f(int i, boolean z) {
        int[] iArr = null;
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a >= 0) {
                        iArr = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr[i2] = m17616a(iArr[0], 0, true);
                        }
                        break;
                    }
                    throw new C4453g("size invalid: " + a);
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return iArr;
    }

    /* renamed from: g */
    private long[] m17612g(int i, boolean z) {
        long[] jArr = null;
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a >= 0) {
                        jArr = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr[i2] = m17618a(jArr[0], 0, true);
                        }
                        break;
                    }
                    throw new C4453g("size invalid: " + a);
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return jArr;
    }

    /* renamed from: h */
    private float[] m17613h(int i, boolean z) {
        float[] fArr = null;
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a >= 0) {
                        fArr = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr[i2] = m17601a(fArr[0], 0, true);
                        }
                        break;
                    }
                    throw new C4453g("size invalid: " + a);
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return fArr;
    }

    /* renamed from: i */
    private double[] m17614i(int i, boolean z) {
        double[] dArr = null;
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a >= 0) {
                        dArr = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr[i2] = m17600a(dArr[0], 0, true);
                        }
                        break;
                    }
                    throw new C4453g("size invalid: " + a);
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return dArr;
    }

    /* renamed from: a */
    private <T> T[] m17607a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return m17608b(tArr[0], i, z);
        }
        throw new C4453g("unable to get type of key and value.");
    }

    /* renamed from: b */
    private <T> T[] m17608b(T t, int i, boolean z) {
        if (m17606a(i)) {
            C4454a c4454a = new C4454a();
            C4455h.m17602a(c4454a, this.f15669a);
            switch (c4454a.f15667a) {
                case (byte) 9:
                    int a = m17616a(0, 0, true);
                    if (a < 0) {
                        throw new C4453g("size invalid: " + a);
                    }
                    Object[] objArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        objArr[i2] = m17620a((Object) t, 0, true);
                    }
                    return objArr;
                default:
                    throw new C4453g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C4453g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final C4447j m17619a(C4447j c4447j, int i, boolean z) {
        C4447j c4447j2 = null;
        if (m17606a(i)) {
            try {
                c4447j2 = (C4447j) c4447j.getClass().newInstance();
                C4454a c4454a = new C4454a();
                C4455h.m17602a(c4454a, this.f15669a);
                if (c4454a.f15667a != (byte) 10) {
                    throw new C4453g("type mismatch.");
                }
                c4447j2.mo6070a(this);
                m17604a();
            } catch (Exception e) {
                throw new C4453g(e.getMessage());
            }
        } else if (z) {
            throw new C4453g("require field not exist.");
        }
        return c4447j2;
    }

    /* renamed from: a */
    public final <T> Object m17620a(T t, int i, boolean z) {
        int i2 = 0;
        if (t instanceof Byte) {
            return Byte.valueOf(m17615a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            boolean z2;
            if (m17615a((byte) 0, i, z) != (byte) 0) {
                z2 = true;
            }
            return Boolean.valueOf(z2);
        } else if (t instanceof Short) {
            return Short.valueOf(m17622a((short) 0, i, z));
        } else {
            if (t instanceof Integer) {
                return Integer.valueOf(m17616a(0, i, z));
            }
            if (t instanceof Long) {
                return Long.valueOf(m17618a(0, i, z));
            }
            if (t instanceof Float) {
                return Float.valueOf(m17601a(0.0f, i, z));
            }
            if (t instanceof Double) {
                return Double.valueOf(m17600a(0.0d, i, z));
            }
            if (t instanceof String) {
                return String.valueOf(m17625b(i, z));
            }
            if (t instanceof Map) {
                return (HashMap) m17603a(new HashMap(), (Map) t, i, z);
            } else if (t instanceof List) {
                List list = (List) t;
                if (list == null || list.isEmpty()) {
                    return new ArrayList();
                }
                Object[] b = m17608b(list.get(0), i, z);
                if (b == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                while (i2 < b.length) {
                    arrayList.add(b[i2]);
                    i2++;
                }
                return arrayList;
            } else if (t instanceof C4447j) {
                return m17619a((C4447j) t, i, z);
            } else {
                if (!t.getClass().isArray()) {
                    throw new C4453g("read object error: unsupport type.");
                } else if ((t instanceof byte[]) || (t instanceof Byte[])) {
                    return m17626c(i, z);
                } else {
                    if (t instanceof boolean[]) {
                        return m17609d(i, z);
                    }
                    if (t instanceof short[]) {
                        return m17610e(i, z);
                    }
                    if (t instanceof int[]) {
                        return m17611f(i, z);
                    }
                    if (t instanceof long[]) {
                        return m17612g(i, z);
                    }
                    if (t instanceof float[]) {
                        return m17613h(i, z);
                    }
                    if (t instanceof double[]) {
                        return m17614i(i, z);
                    }
                    return m17607a((Object[]) t, i, z);
                }
            }
        }
    }

    /* renamed from: a */
    public final int m17617a(String str) {
        this.f15670b = str;
        return 0;
    }
}
