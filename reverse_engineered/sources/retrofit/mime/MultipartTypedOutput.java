package retrofit.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.cli.HelpFormatter;

public final class MultipartTypedOutput implements TypedOutput {
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    private final String boundary;
    private final byte[] footer;
    private long length;
    private final List<MimePart> mimeParts;

    private static final class MimePart {
        private final TypedOutput body;
        private final String boundary;
        private boolean isBuilt;
        private final boolean isFirst;
        private final String name;
        private byte[] partBoundary;
        private byte[] partHeader;
        private final String transferEncoding;

        public MimePart(String str, String str2, TypedOutput typedOutput, String str3, boolean z) {
            this.name = str;
            this.transferEncoding = str2;
            this.body = typedOutput;
            this.isFirst = z;
            this.boundary = str3;
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            build();
            outputStream.write(this.partBoundary);
            outputStream.write(this.partHeader);
            this.body.writeTo(outputStream);
        }

        public long size() {
            build();
            if (this.body.length() > -1) {
                return (this.body.length() + ((long) this.partBoundary.length)) + ((long) this.partHeader.length);
            }
            return -1;
        }

        private void build() {
            if (!this.isBuilt) {
                this.partBoundary = MultipartTypedOutput.buildBoundary(this.boundary, this.isFirst, false);
                this.partHeader = MultipartTypedOutput.buildHeader(this.name, this.transferEncoding, this.body);
                this.isBuilt = true;
            }
        }
    }

    public MultipartTypedOutput() {
        this(UUID.randomUUID().toString());
    }

    MultipartTypedOutput(String str) {
        this.mimeParts = new LinkedList();
        this.boundary = str;
        this.footer = buildBoundary(str, false, true);
        this.length = (long) this.footer.length;
    }

    List<byte[]> getParts() throws IOException {
        List<byte[]> arrayList = new ArrayList(this.mimeParts.size());
        for (MimePart mimePart : this.mimeParts) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mimePart.writeTo(byteArrayOutputStream);
            arrayList.add(byteArrayOutputStream.toByteArray());
        }
        return arrayList;
    }

    public void addPart(String str, TypedOutput typedOutput) {
        addPart(str, "binary", typedOutput);
    }

    public void addPart(String str, String str2, TypedOutput typedOutput) {
        if (str == null) {
            throw new NullPointerException("Part name must not be null.");
        } else if (str2 == null) {
            throw new NullPointerException("Transfer encoding must not be null.");
        } else if (typedOutput == null) {
            throw new NullPointerException("Part body must not be null.");
        } else {
            MimePart mimePart = new MimePart(str, str2, typedOutput, this.boundary, this.mimeParts.isEmpty());
            this.mimeParts.add(mimePart);
            long size = mimePart.size();
            if (size == -1) {
                this.length = -1;
            } else if (this.length != -1) {
                this.length = size + this.length;
            }
        }
    }

    public int getPartCount() {
        return this.mimeParts.size();
    }

    public String fileName() {
        return null;
    }

    public String mimeType() {
        return "multipart/form-data; boundary=" + this.boundary;
    }

    public long length() {
        return this.length;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        for (MimePart writeTo : this.mimeParts) {
            writeTo.writeTo(outputStream);
        }
        outputStream.write(this.footer);
    }

    private static byte[] buildBoundary(String str, boolean z, boolean z2) {
        try {
            StringBuilder stringBuilder = new StringBuilder(str.length() + 8);
            if (!z) {
                stringBuilder.append("\r\n");
            }
            stringBuilder.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            stringBuilder.append(str);
            if (z2) {
                stringBuilder.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            }
            stringBuilder.append("\r\n");
            return stringBuilder.toString().getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException("Unable to write multipart boundary", e);
        }
    }

    private static byte[] buildHeader(String str, String str2, TypedOutput typedOutput) {
        try {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("Content-Disposition: form-data; name=\"");
            stringBuilder.append(str);
            String fileName = typedOutput.fileName();
            if (fileName != null) {
                stringBuilder.append("\"; filename=\"");
                stringBuilder.append(fileName);
            }
            stringBuilder.append("\"\r\nContent-Type: ");
            stringBuilder.append(typedOutput.mimeType());
            long length = typedOutput.length();
            if (length != -1) {
                stringBuilder.append("\r\nContent-Length: ").append(length);
            }
            stringBuilder.append("\r\nContent-Transfer-Encoding: ");
            stringBuilder.append(str2);
            stringBuilder.append("\r\n\r\n");
            return stringBuilder.toString().getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException("Unable to write multipart header", e);
        }
    }
}
