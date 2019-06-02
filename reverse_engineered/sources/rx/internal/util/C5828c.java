package rx.internal.util;

import rx.internal.util.p214b.C5810i;

/* compiled from: OpenHashSet */
/* renamed from: rx.internal.util.c */
public final class C5828c<T> {
    /* renamed from: a */
    final float f18513a;
    /* renamed from: b */
    int f18514b;
    /* renamed from: c */
    int f18515c;
    /* renamed from: d */
    int f18516d;
    /* renamed from: e */
    T[] f18517e;

    public C5828c() {
        this(16, 0.75f);
    }

    public C5828c(int i, float f) {
        this.f18513a = f;
        int a = C5810i.m21003a(i);
        this.f18514b = a - 1;
        this.f18516d = (int) (((float) a) * f);
        this.f18517e = new Object[a];
    }

    /* renamed from: a */
    public boolean m21023a(T t) {
        Object[] objArr = this.f18517e;
        int i = this.f18514b;
        int a = C5828c.m21020a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj != null) {
            if (obj.equals(t)) {
                return false;
            }
            do {
                a = (a + 1) & i;
                obj = objArr[a];
                if (obj == null) {
                }
            } while (!obj.equals(t));
            return false;
        }
        objArr[a] = t;
        a = this.f18515c + 1;
        this.f18515c = a;
        if (a >= this.f18516d) {
            m21024b();
        }
        return true;
    }

    /* renamed from: b */
    public boolean m21025b(T t) {
        Object[] objArr = this.f18517e;
        int i = this.f18514b;
        int a = C5828c.m21020a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj == null) {
            return false;
        }
        if (obj.equals(t)) {
            return m21022a(a, objArr, i);
        }
        do {
            a = (a + 1) & i;
            obj = objArr[a];
            if (obj == null) {
                return false;
            }
        } while (!obj.equals(t));
        return m21022a(a, objArr, i);
    }

    /* renamed from: a */
    boolean m21022a(int i, T[] tArr, int i2) {
        this.f18515c--;
        while (true) {
            Object obj;
            int i3 = (i + 1) & i2;
            while (true) {
                obj = tArr[i3];
                if (obj == null) {
                    tArr[i] = null;
                    return true;
                }
                int a = C5828c.m21020a(obj.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= a || a > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                } else {
                    if (i >= a && a > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                }
            }
            tArr[i] = obj;
            i = i3;
        }
    }

    /* renamed from: a */
    public void m21021a() {
        this.f18515c = 0;
        this.f18517e = new Object[0];
    }

    /* renamed from: b */
    void m21024b() {
        Object[] objArr = this.f18517e;
        int length = objArr.length;
        int i = length << 1;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i];
        int i3 = length;
        length = this.f18515c;
        while (true) {
            int i4 = length - 1;
            if (length != 0) {
                do {
                    i3--;
                } while (objArr[i3] == null);
                length = C5828c.m21020a(objArr[i3].hashCode()) & i2;
                if (objArr2[length] != null) {
                    do {
                        length = (length + 1) & i2;
                    } while (objArr2[length] != null);
                }
                objArr2[length] = objArr[i3];
                length = i4;
            } else {
                this.f18514b = i2;
                this.f18516d = (int) (((float) i) * this.f18513a);
                this.f18517e = objArr2;
                return;
            }
        }
    }

    /* renamed from: a */
    static int m21020a(int i) {
        int i2 = -1640531527 * i;
        return i2 ^ (i2 >>> 16);
    }

    /* renamed from: c */
    public T[] m21026c() {
        return this.f18517e;
    }
}
