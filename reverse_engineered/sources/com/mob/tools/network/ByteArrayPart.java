package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPart extends HTTPPart {
    private BufferedByteArrayOutputStream buffer;

    public ByteArrayPart append(byte[] bArr) throws Throwable {
        if (this.buffer == null) {
            this.buffer = new BufferedByteArrayOutputStream(bArr.length);
        }
        this.buffer.write(bArr);
        this.buffer.flush();
        return this;
    }

    protected InputStream getInputStream() throws Throwable {
        if (this.buffer == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        byte[] buffer = this.buffer.getBuffer();
        if (buffer == null || this.buffer.size() <= 0) {
            return new ByteArrayInputStream(new byte[0]);
        }
        return new ByteArrayInputStream(buffer, 0, this.buffer.size());
    }

    public String toString() {
        if (this.buffer == null) {
            return null;
        }
        byte[] buffer = this.buffer.getBuffer();
        if (buffer != null) {
            return Data.byteToHex(buffer, 0, this.buffer.size());
        }
        return null;
    }

    protected long length() throws Throwable {
        return this.buffer == null ? 0 : (long) this.buffer.size();
    }
}
