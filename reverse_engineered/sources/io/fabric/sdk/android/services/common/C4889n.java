package io.fabric.sdk.android.services.common;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: QueueFile */
/* renamed from: io.fabric.sdk.android.services.common.n */
public class C4889n implements Closeable {
    /* renamed from: b */
    private static final Logger f17196b = Logger.getLogger(C4889n.class.getName());
    /* renamed from: a */
    int f17197a;
    /* renamed from: c */
    private final RandomAccessFile f17198c;
    /* renamed from: d */
    private int f17199d;
    /* renamed from: e */
    private C4887a f17200e;
    /* renamed from: f */
    private C4887a f17201f;
    /* renamed from: g */
    private final byte[] f17202g = new byte[16];

    /* compiled from: QueueFile */
    /* renamed from: io.fabric.sdk.android.services.common.n$c */
    public interface C4624c {
        /* renamed from: a */
        void mo6141a(InputStream inputStream, int i) throws IOException;
    }

    /* compiled from: QueueFile */
    /* renamed from: io.fabric.sdk.android.services.common.n$a */
    static class C4887a {
        /* renamed from: a */
        static final C4887a f17190a = new C4887a(0, 0);
        /* renamed from: b */
        final int f17191b;
        /* renamed from: c */
        final int f17192c;

        C4887a(int i, int i2) {
            this.f17191b = i;
            this.f17192c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[" + "position = " + this.f17191b + ", length = " + this.f17192c + "]";
        }
    }

    /* compiled from: QueueFile */
    /* renamed from: io.fabric.sdk.android.services.common.n$b */
    private final class C4888b extends InputStream {
        /* renamed from: a */
        final /* synthetic */ C4889n f17193a;
        /* renamed from: b */
        private int f17194b;
        /* renamed from: c */
        private int f17195c;

        private C4888b(C4889n c4889n, C4887a c4887a) {
            this.f17193a = c4889n;
            this.f17194b = c4889n.m19208b(c4887a.f17191b + 4);
            this.f17195c = c4887a.f17192c;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            C4889n.m19210b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.f17195c <= 0) {
                return -1;
            } else {
                if (i2 > this.f17195c) {
                    i2 = this.f17195c;
                }
                this.f17193a.m19211b(this.f17194b, bArr, i, i2);
                this.f17194b = this.f17193a.m19208b(this.f17194b + i2);
                this.f17195c -= i2;
                return i2;
            }
        }

        public int read() throws IOException {
            if (this.f17195c == 0) {
                return -1;
            }
            this.f17193a.f17198c.seek((long) this.f17194b);
            int read = this.f17193a.f17198c.read();
            this.f17194b = this.f17193a.m19208b(this.f17194b + 1);
            this.f17195c--;
            return read;
        }
    }

    public C4889n(File file) throws IOException {
        if (!file.exists()) {
            C4889n.m19206a(file);
        }
        this.f17198c = C4889n.m19209b(file);
        m19213c();
    }

    /* renamed from: b */
    private static void m19212b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    /* renamed from: a */
    private static void m19207a(byte[] bArr, int... iArr) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            C4889n.m19212b(bArr, i2, iArr[i]);
            i2 += 4;
            i++;
        }
    }

    /* renamed from: a */
    private static int m19199a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    /* renamed from: c */
    private void m19213c() throws IOException {
        this.f17198c.seek(0);
        this.f17198c.readFully(this.f17202g);
        this.f17197a = C4889n.m19199a(this.f17202g, 0);
        if (((long) this.f17197a) > this.f17198c.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f17197a + ", Actual length: " + this.f17198c.length());
        }
        this.f17199d = C4889n.m19199a(this.f17202g, 4);
        int a = C4889n.m19199a(this.f17202g, 8);
        int a2 = C4889n.m19199a(this.f17202g, 12);
        this.f17200e = m19200a(a);
        this.f17201f = m19200a(a2);
    }

    /* renamed from: a */
    private void m19203a(int i, int i2, int i3, int i4) throws IOException {
        C4889n.m19207a(this.f17202g, i, i2, i3, i4);
        this.f17198c.seek(0);
        this.f17198c.write(this.f17202g);
    }

    /* renamed from: a */
    private C4887a m19200a(int i) throws IOException {
        if (i == 0) {
            return C4887a.f17190a;
        }
        this.f17198c.seek((long) i);
        return new C4887a(i, this.f17198c.readInt());
    }

    /* renamed from: a */
    private static void m19206a(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b = C4889n.m19209b(file2);
        try {
            b.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            b.seek(0);
            byte[] bArr = new byte[16];
            C4889n.m19207a(bArr, 4096, 0, 0, 0);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    /* renamed from: b */
    private static RandomAccessFile m19209b(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    /* renamed from: b */
    private int m19208b(int i) {
        return i < this.f17197a ? i : (i + 16) - this.f17197a;
    }

    /* renamed from: a */
    private void m19204a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = m19208b(i);
        if (b + i3 <= this.f17197a) {
            this.f17198c.seek((long) b);
            this.f17198c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.f17197a - b;
        this.f17198c.seek((long) b);
        this.f17198c.write(bArr, i2, i4);
        this.f17198c.seek(16);
        this.f17198c.write(bArr, i2 + i4, i3 - i4);
    }

    /* renamed from: b */
    private void m19211b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = m19208b(i);
        if (b + i3 <= this.f17197a) {
            this.f17198c.seek((long) b);
            this.f17198c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.f17197a - b;
        this.f17198c.seek((long) b);
        this.f17198c.readFully(bArr, i2, i4);
        this.f17198c.seek(16);
        this.f17198c.readFully(bArr, i2 + i4, i3 - i4);
    }

    /* renamed from: a */
    public void m19219a(byte[] bArr) throws IOException {
        m19220a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public synchronized void m19220a(byte[] bArr, int i, int i2) throws IOException {
        C4889n.m19210b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        m19214c(i2);
        boolean b = m19222b();
        if (b) {
            i3 = 16;
        } else {
            i3 = m19208b((this.f17201f.f17191b + 4) + this.f17201f.f17192c);
        }
        C4887a c4887a = new C4887a(i3, i2);
        C4889n.m19212b(this.f17202g, 0, i2);
        m19204a(c4887a.f17191b, this.f17202g, 0, 4);
        m19204a(c4887a.f17191b + 4, bArr, i, i2);
        m19203a(this.f17197a, this.f17199d + 1, b ? c4887a.f17191b : this.f17200e.f17191b, c4887a.f17191b);
        this.f17201f = c4887a;
        this.f17199d++;
        if (b) {
            this.f17200e = this.f17201f;
        }
    }

    /* renamed from: a */
    public int m19217a() {
        if (this.f17199d == 0) {
            return 16;
        }
        if (this.f17201f.f17191b >= this.f17200e.f17191b) {
            return (((this.f17201f.f17191b - this.f17200e.f17191b) + 4) + this.f17201f.f17192c) + 16;
        }
        return (((this.f17201f.f17191b + 4) + this.f17201f.f17192c) + this.f17197a) - this.f17200e.f17191b;
    }

    /* renamed from: d */
    private int m19215d() {
        return this.f17197a - m19217a();
    }

    /* renamed from: b */
    public synchronized boolean m19222b() {
        return this.f17199d == 0;
    }

    /* renamed from: c */
    private void m19214c(int i) throws IOException {
        int i2 = i + 4;
        int d = m19215d();
        if (d < i2) {
            int i3 = this.f17197a;
            do {
                d += i3;
                i3 <<= 1;
            } while (d < i2);
            m19216d(i3);
            i2 = m19208b((this.f17201f.f17191b + 4) + this.f17201f.f17192c);
            if (i2 < this.f17200e.f17191b) {
                FileChannel channel = this.f17198c.getChannel();
                channel.position((long) this.f17197a);
                int i4 = i2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f17201f.f17191b < this.f17200e.f17191b) {
                d = (this.f17197a + this.f17201f.f17191b) - 16;
                m19203a(i3, this.f17199d, this.f17200e.f17191b, d);
                this.f17201f = new C4887a(d, this.f17201f.f17192c);
            } else {
                m19203a(i3, this.f17199d, this.f17200e.f17191b, this.f17201f.f17191b);
            }
            this.f17197a = i3;
        }
    }

    /* renamed from: d */
    private void m19216d(int i) throws IOException {
        this.f17198c.setLength((long) i);
        this.f17198c.getChannel().force(true);
    }

    /* renamed from: a */
    public synchronized void m19218a(C4624c c4624c) throws IOException {
        int i = this.f17200e.f17191b;
        for (int i2 = 0; i2 < this.f17199d; i2++) {
            C4887a a = m19200a(i);
            c4624c.mo6141a(new C4888b(a), a.f17192c);
            i = m19208b(a.f17192c + (a.f17191b + 4));
        }
    }

    /* renamed from: b */
    private static <T> T m19210b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public synchronized void close() throws IOException {
        this.f17198c.close();
    }

    /* renamed from: a */
    public boolean m19221a(int i, int i2) {
        return (m19217a() + 4) + i <= i2;
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.f17197a);
        stringBuilder.append(", size=").append(this.f17199d);
        stringBuilder.append(", first=").append(this.f17200e);
        stringBuilder.append(", last=").append(this.f17201f);
        stringBuilder.append(", element lengths=[");
        try {
            m19218a(new C4624c(this) {
                /* renamed from: a */
                boolean f17187a = true;
                /* renamed from: c */
                final /* synthetic */ C4889n f17189c;

                /* renamed from: a */
                public void mo6141a(InputStream inputStream, int i) throws IOException {
                    if (this.f17187a) {
                        this.f17187a = false;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(i);
                }
            });
        } catch (Throwable e) {
            f17196b.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
