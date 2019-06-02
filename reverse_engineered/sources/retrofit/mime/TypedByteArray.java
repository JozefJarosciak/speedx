package retrofit.mime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TypedByteArray implements TypedInput, TypedOutput {
    private final byte[] bytes;
    private final String mimeType;

    public TypedByteArray(String str, byte[] bArr) {
        if (str == null) {
            str = "application/unknown";
        }
        if (bArr == null) {
            throw new NullPointerException("bytes");
        }
        this.mimeType = str;
        this.bytes = bArr;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String fileName() {
        return null;
    }

    public String mimeType() {
        return this.mimeType;
    }

    public long length() {
        return (long) this.bytes.length;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.bytes);
    }

    public InputStream in() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypedByteArray typedByteArray = (TypedByteArray) obj;
        if (!Arrays.equals(this.bytes, typedByteArray.bytes)) {
            return false;
        }
        if (this.mimeType.equals(typedByteArray.mimeType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "TypedByteArray[length=" + length() + "]";
    }
}
