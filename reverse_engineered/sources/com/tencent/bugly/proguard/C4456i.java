package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.i */
public final class C4456i {
    /* renamed from: a */
    private ByteBuffer f15671a;
    /* renamed from: b */
    private String f15672b;

    public C4456i(int i) {
        this.f15672b = "GBK";
        this.f15671a = ByteBuffer.allocate(i);
    }

    public C4456i() {
        this(128);
    }

    /* renamed from: a */
    public final ByteBuffer m17630a() {
        return this.f15671a;
    }

    /* renamed from: b */
    public final byte[] m17642b() {
        Object obj = new byte[this.f15671a.position()];
        System.arraycopy(this.f15671a.array(), 0, obj, 0, this.f15671a.position());
        return obj;
    }

    /* renamed from: a */
    private void m17627a(int i) {
        if (this.f15671a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f15671a.capacity() + i) << 1);
            allocate.put(this.f15671a.array(), 0, this.f15671a.position());
            this.f15671a = allocate;
        }
    }

    /* renamed from: b */
    private void m17628b(byte b, int i) {
        if (i < 15) {
            this.f15671a.put((byte) ((i << 4) | b));
        } else if (i < 256) {
            this.f15671a.put((byte) (b | 240));
            this.f15671a.put((byte) i);
        } else {
            throw new C4448b("tag is too large: " + i);
        }
    }

    /* renamed from: a */
    public final void m17640a(boolean z, int i) {
        m17631a((byte) (z ? 1 : 0), i);
    }

    /* renamed from: a */
    public final void m17631a(byte b, int i) {
        m17627a(3);
        if (b == (byte) 0) {
            m17628b(Ascii.FF, i);
            return;
        }
        m17628b((byte) 0, i);
        this.f15671a.put(b);
    }

    /* renamed from: a */
    public final void m17639a(short s, int i) {
        m17627a(4);
        if (s < (short) -128 || s > (short) 127) {
            m17628b((byte) 1, i);
            this.f15671a.putShort(s);
            return;
        }
        m17631a((byte) s, i);
    }

    /* renamed from: a */
    public final void m17632a(int i, int i2) {
        m17627a(6);
        if (i < -32768 || i > 32767) {
            m17628b((byte) 2, i2);
            this.f15671a.putInt(i);
            return;
        }
        m17639a((short) i, i2);
    }

    /* renamed from: a */
    public final void m17633a(long j, int i) {
        m17627a(10);
        if (j < -2147483648L || j > 2147483647L) {
            m17628b((byte) 3, i);
            this.f15671a.putLong(j);
            return;
        }
        m17632a((int) j, i);
    }

    /* renamed from: a */
    public final void m17636a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f15672b);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        m17627a(bytes.length + 10);
        if (bytes.length > 255) {
            m17628b((byte) 7, i);
            this.f15671a.putInt(bytes.length);
            this.f15671a.put(bytes);
            return;
        }
        m17628b((byte) 6, i);
        this.f15671a.put((byte) bytes.length);
        this.f15671a.put(bytes);
    }

    /* renamed from: a */
    public final <K, V> void m17638a(Map<K, V> map, int i) {
        m17627a(8);
        m17628b((byte) 8, i);
        m17632a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                m17635a(entry.getKey(), 0);
                m17635a(entry.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    public final void m17641a(byte[] bArr, int i) {
        m17627a(bArr.length + 8);
        m17628b((byte) 13, i);
        m17628b((byte) 0, 0);
        m17632a(bArr.length, 0);
        this.f15671a.put(bArr);
    }

    /* renamed from: a */
    public final <T> void m17637a(Collection<T> collection, int i) {
        m17627a(8);
        m17628b((byte) 9, i);
        m17632a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            for (T a : collection) {
                m17635a((Object) a, 0);
            }
        }
    }

    /* renamed from: a */
    public final void m17634a(C4447j c4447j, int i) {
        m17627a(2);
        m17628b((byte) 10, i);
        c4447j.mo6071a(this);
        m17627a(2);
        m17628b(Ascii.VT, 0);
    }

    /* renamed from: a */
    public final void m17635a(Object obj, int i) {
        int i2 = 1;
        if (obj instanceof Byte) {
            m17631a(((Byte) obj).byteValue(), i);
        } else if (obj instanceof Boolean) {
            if (!((Boolean) obj).booleanValue()) {
                i2 = 0;
            }
            m17631a((byte) i2, i);
        } else if (obj instanceof Short) {
            m17639a(((Short) obj).shortValue(), i);
        } else if (obj instanceof Integer) {
            m17632a(((Integer) obj).intValue(), i);
        } else if (obj instanceof Long) {
            m17633a(((Long) obj).longValue(), i);
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            m17627a(6);
            m17628b((byte) 4, i);
            this.f15671a.putFloat(floatValue);
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            m17627a(10);
            m17628b((byte) 5, i);
            this.f15671a.putDouble(doubleValue);
        } else if (obj instanceof String) {
            m17636a((String) obj, i);
        } else if (obj instanceof Map) {
            m17638a((Map) obj, i);
        } else if (obj instanceof List) {
            m17637a((List) obj, i);
        } else if (obj instanceof C4447j) {
            C4447j c4447j = (C4447j) obj;
            m17627a(2);
            m17628b((byte) 10, i);
            c4447j.mo6071a(this);
            m17627a(2);
            m17628b(Ascii.VT, 0);
        } else if (obj instanceof byte[]) {
            m17641a((byte[]) obj, i);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(zArr.length, 0);
            for (boolean z : zArr) {
                m17631a((byte) (z ? 1 : 0), 0);
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(sArr.length, 0);
            for (short a : sArr) {
                m17639a(a, 0);
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(iArr.length, 0);
            for (int a2 : iArr) {
                m17632a(a2, 0);
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(jArr.length, 0);
            for (long a3 : jArr) {
                m17633a(a3, 0);
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(fArr.length, 0);
            for (float f : fArr) {
                m17627a(6);
                m17628b((byte) 4, 0);
                this.f15671a.putFloat(f);
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(dArr.length, 0);
            for (double d : dArr) {
                m17627a(10);
                m17628b((byte) 5, 0);
                this.f15671a.putDouble(d);
            }
        } else if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m17627a(8);
            m17628b((byte) 9, i);
            m17632a(objArr.length, 0);
            for (Object a4 : objArr) {
                m17635a(a4, 0);
            }
        } else if (obj instanceof Collection) {
            m17637a((Collection) obj, i);
        } else {
            throw new C4448b("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int m17629a(String str) {
        this.f15672b = str;
        return 0;
    }
}
