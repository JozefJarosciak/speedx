package ch.qos.logback.core.encoder;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.Layout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class LayoutWrappingEncoder<E> extends EncoderBase<E> {
    private Charset charset;
    private boolean immediateFlush = true;
    protected Layout<E> layout;

    private void appendIfNotNull(StringBuilder stringBuilder, String str) {
        if (str != null) {
            stringBuilder.append(str);
        }
    }

    private byte[] convertToBytes(String str) {
        if (this.charset == null) {
            return str.getBytes();
        }
        try {
            return str.getBytes(this.charset.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("An existing charset cannot possibly be unsupported.");
        }
    }

    public void close() throws IOException {
        writeFooter();
    }

    public void doEncode(E e) throws IOException {
        this.outputStream.write(convertToBytes(this.layout.doLayout(e)));
        if (this.immediateFlush) {
            this.outputStream.flush();
        }
    }

    public Charset getCharset() {
        return this.charset;
    }

    public Layout<E> getLayout() {
        return this.layout;
    }

    public void init(OutputStream outputStream) throws IOException {
        super.init(outputStream);
        writeHeader();
    }

    public boolean isImmediateFlush() {
        return this.immediateFlush;
    }

    public boolean isStarted() {
        return false;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void setImmediateFlush(boolean z) {
        this.immediateFlush = z;
    }

    public void setLayout(Layout<E> layout) {
        this.layout = layout;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
        if (this.outputStream != null) {
            try {
                this.outputStream.flush();
            } catch (IOException e) {
            }
        }
    }

    void writeFooter() throws IOException {
        if (this.layout != null && this.outputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            appendIfNotNull(stringBuilder, this.layout.getPresentationFooter());
            appendIfNotNull(stringBuilder, this.layout.getFileFooter());
            if (stringBuilder.length() > 0) {
                this.outputStream.write(convertToBytes(stringBuilder.toString()));
                this.outputStream.flush();
            }
        }
    }

    void writeHeader() throws IOException {
        if (this.layout != null && this.outputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            appendIfNotNull(stringBuilder, this.layout.getFileHeader());
            appendIfNotNull(stringBuilder, this.layout.getPresentationHeader());
            if (stringBuilder.length() > 0) {
                stringBuilder.append(CoreConstants.LINE_SEPARATOR);
                this.outputStream.write(convertToBytes(stringBuilder.toString()));
                this.outputStream.flush();
            }
        }
    }
}
