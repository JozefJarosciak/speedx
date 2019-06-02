package com.google.gson.jpush.internal;

import java.io.Writer;

final class ah extends Writer {
    /* renamed from: a */
    private final Appendable f14497a;
    /* renamed from: b */
    private final ai f14498b;

    private ah(Appendable appendable) {
        this.f14498b = new ai();
        this.f14497a = appendable;
    }

    public final void close() {
    }

    public final void flush() {
    }

    public final void write(int i) {
        this.f14497a.append((char) i);
    }

    public final void write(char[] cArr, int i, int i2) {
        this.f14498b.f14499a = cArr;
        this.f14497a.append(this.f14498b, i, i + i2);
    }
}
