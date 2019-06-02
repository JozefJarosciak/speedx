package retrofit.mime;

import ch.qos.logback.core.joran.action.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TypedFile implements TypedInput, TypedOutput {
    private static final int BUFFER_SIZE = 4096;
    private final File file;
    private final String mimeType;

    public TypedFile(String str, File file) {
        if (str == null) {
            throw new NullPointerException("mimeType");
        } else if (file == null) {
            throw new NullPointerException(Action.FILE_ATTRIBUTE);
        } else {
            this.mimeType = str;
            this.file = file;
        }
    }

    public File file() {
        return this.file;
    }

    public String mimeType() {
        return this.mimeType;
    }

    public long length() {
        return this.file.length();
    }

    public String fileName() {
        return this.file.getName();
    }

    public InputStream in() throws IOException {
        return new FileInputStream(this.file);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(this.file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            } finally {
                fileInputStream.close();
            }
        }
    }

    public void moveTo(TypedFile typedFile) throws IOException {
        if (!mimeType().equals(typedFile.mimeType())) {
            throw new IOException("Type mismatch.");
        } else if (!this.file.renameTo(typedFile.file())) {
            throw new IOException("Rename failed!");
        }
    }

    public String toString() {
        return this.file.getAbsolutePath() + " (" + mimeType() + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypedFile)) {
            return false;
        }
        return this.file.equals(((TypedFile) obj).file);
    }

    public int hashCode() {
        return this.file.hashCode();
    }
}
