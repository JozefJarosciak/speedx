package okhttp3;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import okhttp3.internal.C5496c;
import okhttp3.internal.C5498e;

/* compiled from: Cache */
/* renamed from: okhttp3.c */
public final class C5463c implements Closeable, Flushable {
    /* renamed from: a */
    final C5498e f17596a;
    /* renamed from: b */
    private final C5496c f17597b;

    public void flush() throws IOException {
        this.f17597b.flush();
    }

    public void close() throws IOException {
        this.f17597b.close();
    }
}
