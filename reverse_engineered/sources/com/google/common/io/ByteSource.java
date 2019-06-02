package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

public abstract class ByteSource {
    private static final int BUF_SIZE = 4096;
    private static final byte[] countBuffer = new byte[4096];

    private final class AsCharSource extends CharSource {
        private final Charset charset;

        private AsCharSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(ByteSource.this.toString()));
            String valueOf2 = String.valueOf(String.valueOf(this.charset));
            return new StringBuilder((valueOf.length() + 15) + valueOf2.length()).append(valueOf).append(".asCharSource(").append(valueOf2).append(")").toString();
        }
    }

    private static class ByteArrayByteSource extends ByteSource {
        protected final byte[] bytes;

        protected ByteArrayByteSource(byte[] bArr) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes);
        }

        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        public boolean isEmpty() {
            return this.bytes.length == 0;
        }

        public long size() {
            return (long) this.bytes.length;
        }

        public byte[] read() {
            return (byte[]) this.bytes.clone();
        }

        public long copyTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.bytes);
            return (long) this.bytes.length;
        }

        public <T> T read(ByteProcessor<T> byteProcessor) throws IOException {
            byteProcessor.processBytes(this.bytes, 0, this.bytes.length);
            return byteProcessor.getResult();
        }

        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(Ascii.truncate(BaseEncoding.base16().encode(this.bytes), 30, "...")));
            return new StringBuilder(valueOf.length() + 17).append("ByteSource.wrap(").append(valueOf).append(")").toString();
        }
    }

    private static final class ConcatenatedByteSource extends ByteSource {
        private final Iterable<? extends ByteSource> sources;

        ConcatenatedByteSource(Iterable<? extends ByteSource> iterable) {
            this.sources = (Iterable) Preconditions.checkNotNull(iterable);
        }

        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        public boolean isEmpty() throws IOException {
            for (ByteSource isEmpty : this.sources) {
                if (!isEmpty.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        public long size() throws IOException {
            long j = 0;
            for (ByteSource size : this.sources) {
                j = size.size() + j;
            }
            return j;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.sources));
            return new StringBuilder(valueOf.length() + 19).append("ByteSource.concat(").append(valueOf).append(")").toString();
        }
    }

    private static final class EmptyByteSource extends ByteArrayByteSource {
        private static final EmptyByteSource INSTANCE = new EmptyByteSource();

        private EmptyByteSource() {
            super(new byte[0]);
        }

        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        public byte[] read() {
            return this.bytes;
        }

        public String toString() {
            return "ByteSource.empty()";
        }
    }

    private final class SlicedByteSource extends ByteSource {
        private final long length;
        private final long offset;

        private SlicedByteSource(long j, long j2) {
            boolean z;
            if (j >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", Long.valueOf(j));
            if (j2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", Long.valueOf(j2));
            this.offset = j;
            this.length = j2;
        }

        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        private InputStream sliceStream(InputStream inputStream) throws IOException {
            Closer create;
            if (this.offset > 0) {
                try {
                    ByteStreams.skipFully(inputStream, this.offset);
                } catch (Throwable th) {
                    create.close();
                }
            }
            return ByteStreams.limit(inputStream, this.length);
        }

        public ByteSource slice(long j, long j2) {
            boolean z;
            if (j >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", Long.valueOf(j));
            if (j2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", Long.valueOf(j2));
            return ByteSource.this.slice(this.offset + j, Math.min(j2, this.length - j));
        }

        public boolean isEmpty() throws IOException {
            return this.length == 0 || super.isEmpty();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(ByteSource.this.toString()));
            long j = this.offset;
            return new StringBuilder(valueOf.length() + 50).append(valueOf).append(".slice(").append(j).append(", ").append(this.length).append(")").toString();
        }
    }

    public abstract InputStream openStream() throws IOException;

    protected ByteSource() {
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream openStream = openStream();
        return openStream instanceof BufferedInputStream ? (BufferedInputStream) openStream : new BufferedInputStream(openStream);
    }

    public ByteSource slice(long j, long j2) {
        return new SlicedByteSource(j, j2);
    }

    public boolean isEmpty() throws IOException {
        Closer create = Closer.create();
        try {
            boolean z = ((InputStream) create.register(openStream())).read() == -1;
            create.close();
            return z;
        } catch (Throwable th) {
            create.close();
        }
    }

    public long size() throws IOException {
        long countBySkipping;
        Closer create = Closer.create();
        try {
            countBySkipping = countBySkipping((InputStream) create.register(openStream()));
            create.close();
        } catch (IOException e) {
            create.close();
            create = Closer.create();
            countBySkipping = countByReading((InputStream) create.register(openStream()));
            create.close();
        } catch (Throwable th) {
            try {
                RuntimeException rethrow = create.rethrow(th);
            } catch (Throwable th2) {
                create.close();
            }
        }
        return countBySkipping;
    }

    private long countBySkipping(InputStream inputStream) throws IOException {
        long j = 0;
        while (true) {
            long skip = inputStream.skip((long) Math.min(inputStream.available(), Integer.MAX_VALUE));
            if (skip > 0) {
                j += skip;
            } else if (inputStream.read() == -1) {
                return j;
            } else {
                if (j == 0 && inputStream.available() == 0) {
                    throw new IOException();
                }
                j++;
            }
        }
    }

    private long countByReading(InputStream inputStream) throws IOException {
        long j = 0;
        while (true) {
            long read = (long) inputStream.read(countBuffer);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public long copyTo(OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(outputStream);
        Closer create = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) create.register(openStream()), outputStream);
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
        }
    }

    public long copyTo(ByteSink byteSink) throws IOException {
        Preconditions.checkNotNull(byteSink);
        Closer create = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) create.register(openStream()), (OutputStream) create.register(byteSink.openStream()));
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
        }
    }

    public byte[] read() throws IOException {
        Closer create = Closer.create();
        try {
            byte[] toByteArray = ByteStreams.toByteArray((InputStream) create.register(openStream()));
            create.close();
            return toByteArray;
        } catch (Throwable th) {
            create.close();
        }
    }

    @Beta
    public <T> T read(ByteProcessor<T> byteProcessor) throws IOException {
        Preconditions.checkNotNull(byteProcessor);
        Closer create = Closer.create();
        try {
            T readBytes = ByteStreams.readBytes((InputStream) create.register(openStream()), byteProcessor);
            create.close();
            return readBytes;
        } catch (Throwable th) {
            create.close();
        }
    }

    public HashCode hash(HashFunction hashFunction) throws IOException {
        Object newHasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(newHasher));
        return newHasher.hash();
    }

    public boolean contentEquals(ByteSource byteSource) throws IOException {
        Preconditions.checkNotNull(byteSource);
        byte[] bArr = new byte[4096];
        byte[] bArr2 = new byte[4096];
        Closer create = Closer.create();
        try {
            InputStream inputStream = (InputStream) create.register(openStream());
            InputStream inputStream2 = (InputStream) create.register(byteSource.openStream());
            int read;
            do {
                read = ByteStreams.read(inputStream, bArr, 0, 4096);
                if (!(read == ByteStreams.read(inputStream2, bArr2, 0, 4096) && Arrays.equals(bArr, bArr2))) {
                    create.close();
                    return false;
                }
            } while (read == 4096);
            create.close();
            return true;
        } catch (Throwable th) {
            create.close();
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> iterable) {
        return new ConcatenatedByteSource(iterable);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> it) {
        return concat(ImmutableList.copyOf(it));
    }

    public static ByteSource concat(ByteSource... byteSourceArr) {
        return concat(ImmutableList.copyOf(byteSourceArr));
    }

    public static ByteSource wrap(byte[] bArr) {
        return new ByteArrayByteSource(bArr);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }
}
