package com.avos.avoscloud;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

class AVMultiPartEntity extends MultipartEntity {
    private final ProgressListener listener;

    public static class CountingOutputStream extends FilterOutputStream {
        private final ProgressListener listener;
        private long transferred = 0;

        public CountingOutputStream(OutputStream outputStream, ProgressListener progressListener) {
            super(outputStream);
            this.listener = progressListener;
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.out.write(bArr, i, i2);
            this.transferred += (long) i2;
            this.listener.transferred(this.transferred);
        }

        public void write(int i) throws IOException {
            this.out.write(i);
            this.transferred++;
            this.listener.transferred(this.transferred);
        }
    }

    public interface ProgressListener {
        void transferred(long j);
    }

    public AVMultiPartEntity(ProgressListener progressListener) {
        this.listener = progressListener;
    }

    public AVMultiPartEntity(HttpMultipartMode httpMultipartMode, ProgressListener progressListener) {
        super(httpMultipartMode);
        this.listener = progressListener;
    }

    public AVMultiPartEntity(HttpMultipartMode httpMultipartMode, String str, Charset charset, ProgressListener progressListener) {
        super(httpMultipartMode, str, charset);
        this.listener = progressListener;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(new CountingOutputStream(outputStream, this.listener));
    }
}
