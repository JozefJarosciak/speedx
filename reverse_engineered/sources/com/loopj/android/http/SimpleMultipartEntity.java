package com.loopj.android.http;

import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

class SimpleMultipartEntity implements HttpEntity {
    private static final byte[] CR_LF = STR_CR_LF.getBytes();
    private static final String LOG_TAG = "SimpleMultipartEntity";
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final String STR_CR_LF = "\r\n";
    private static final byte[] TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n".getBytes();
    private final String boundary;
    private final byte[] boundaryEnd;
    private final byte[] boundaryLine;
    private int bytesWritten;
    private final List<FilePart> fileParts = new ArrayList();
    private boolean isRepeatable;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ResponseHandlerInterface progressHandler;
    private int totalSize;

    private class FilePart {
        public File file;
        public byte[] header;

        public FilePart(String str, File file, String str2, String str3) {
            if (TextUtils.isEmpty(str3)) {
                str3 = file.getName();
            }
            this.header = createHeader(str, str3, str2);
            this.file = file;
        }

        public FilePart(String str, File file, String str2) {
            this.header = createHeader(str, file.getName(), str2);
            this.file = file;
        }

        private byte[] createHeader(String str, String str2, String str3) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(SimpleMultipartEntity.this.boundaryLine);
                byteArrayOutputStream.write(SimpleMultipartEntity.this.createContentDisposition(str, str2));
                byteArrayOutputStream.write(SimpleMultipartEntity.this.createContentType(str3));
                byteArrayOutputStream.write(SimpleMultipartEntity.TRANSFER_ENCODING_BINARY);
                byteArrayOutputStream.write(SimpleMultipartEntity.CR_LF);
            } catch (Throwable e) {
                Log.e(SimpleMultipartEntity.LOG_TAG, "createHeader ByteArrayOutputStream exception", e);
            }
            return byteArrayOutputStream.toByteArray();
        }

        public long getTotalLength() {
            return (this.file.length() + ((long) SimpleMultipartEntity.CR_LF.length)) + ((long) this.header.length);
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.header);
            SimpleMultipartEntity.this.updateProgress(this.header.length);
            InputStream fileInputStream = new FileInputStream(this.file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                    SimpleMultipartEntity.this.updateProgress(read);
                } else {
                    outputStream.write(SimpleMultipartEntity.CR_LF);
                    SimpleMultipartEntity.this.updateProgress(SimpleMultipartEntity.CR_LF.length);
                    outputStream.flush();
                    AsyncHttpClient.silentCloseInputStream(fileInputStream);
                    return;
                }
            }
        }
    }

    public SimpleMultipartEntity(ResponseHandlerInterface responseHandlerInterface) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
        }
        this.boundary = stringBuilder.toString();
        this.boundaryLine = (HelpFormatter.DEFAULT_LONG_OPT_PREFIX + this.boundary + STR_CR_LF).getBytes();
        this.boundaryEnd = (HelpFormatter.DEFAULT_LONG_OPT_PREFIX + this.boundary + HelpFormatter.DEFAULT_LONG_OPT_PREFIX + STR_CR_LF).getBytes();
        this.progressHandler = responseHandlerInterface;
    }

    public void addPart(String str, String str2, String str3) {
        try {
            this.out.write(this.boundaryLine);
            this.out.write(createContentDisposition(str));
            this.out.write(createContentType(str3));
            this.out.write(CR_LF);
            this.out.write(str2.getBytes());
            this.out.write(CR_LF);
        } catch (Throwable e) {
            Log.e(LOG_TAG, "addPart ByteArrayOutputStream exception", e);
        }
    }

    public void addPartWithCharset(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "UTF-8";
        }
        addPart(str, str2, "text/plain; charset=" + str3);
    }

    public void addPart(String str, String str2) {
        addPartWithCharset(str, str2, null);
    }

    public void addPart(String str, File file) {
        addPart(str, file, null);
    }

    public void addPart(String str, File file, String str2) {
        this.fileParts.add(new FilePart(str, file, normalizeContentType(str2)));
    }

    public void addPart(String str, File file, String str2, String str3) {
        this.fileParts.add(new FilePart(str, file, normalizeContentType(str2), str3));
    }

    public void addPart(String str, String str2, InputStream inputStream, String str3) throws IOException {
        this.out.write(this.boundaryLine);
        this.out.write(createContentDisposition(str, str2));
        this.out.write(createContentType(str3));
        this.out.write(TRANSFER_ENCODING_BINARY);
        this.out.write(CR_LF);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                this.out.write(bArr, 0, read);
            } else {
                this.out.write(CR_LF);
                this.out.flush();
                AsyncHttpClient.silentCloseOutputStream(this.out);
                return;
            }
        }
    }

    private String normalizeContentType(String str) {
        return str == null ? "application/octet-stream" : str;
    }

    private byte[] createContentType(String str) {
        return ("Content-Type: " + normalizeContentType(str) + STR_CR_LF).getBytes();
    }

    private byte[] createContentDisposition(String str) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + STR_CR_LF).getBytes();
    }

    private byte[] createContentDisposition(String str, String str2) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + "; filename=\"" + str2 + "\"" + STR_CR_LF).getBytes();
    }

    private void updateProgress(int i) {
        this.bytesWritten += i;
        this.progressHandler.sendProgressMessage(this.bytesWritten, this.totalSize);
    }

    public long getContentLength() {
        long size = (long) this.out.size();
        long j = size;
        for (FilePart totalLength : this.fileParts) {
            size = totalLength.getTotalLength();
            if (size < 0) {
                return -1;
            }
            j = size + j;
        }
        return ((long) this.boundaryEnd.length) + j;
    }

    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.boundary);
    }

    public boolean isChunked() {
        return false;
    }

    public void setIsRepeatable(boolean z) {
        this.isRepeatable = z;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.bytesWritten = 0;
        this.totalSize = (int) getContentLength();
        this.out.writeTo(outputStream);
        updateProgress(this.out.size());
        for (FilePart writeTo : this.fileParts) {
            writeTo.writeTo(outputStream);
        }
        outputStream.write(this.boundaryEnd);
        updateProgress(this.boundaryEnd.length);
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("getContent() is not supported. Use writeTo() instead.");
    }
}
