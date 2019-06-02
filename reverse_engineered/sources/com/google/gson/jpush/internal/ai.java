package com.google.gson.jpush.internal;

final class ai implements CharSequence {
    /* renamed from: a */
    char[] f14499a;

    ai() {
    }

    public final char charAt(int i) {
        return this.f14499a[i];
    }

    public final int length() {
        return this.f14499a.length;
    }

    public final CharSequence subSequence(int i, int i2) {
        return new String(this.f14499a, i, i2 - i);
    }
}
