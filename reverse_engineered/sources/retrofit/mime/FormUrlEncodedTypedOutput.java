package retrofit.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public final class FormUrlEncodedTypedOutput implements TypedOutput {
    final ByteArrayOutputStream content = new ByteArrayOutputStream();

    public void addField(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name");
        } else if (str2 == null) {
            throw new NullPointerException("value");
        } else {
            if (this.content.size() > 0) {
                this.content.write(38);
            }
            try {
                String encode = URLEncoder.encode(str, "UTF-8");
                String encode2 = URLEncoder.encode(str2, "UTF-8");
                this.content.write(encode.getBytes("UTF-8"));
                this.content.write(61);
                this.content.write(encode2.getBytes("UTF-8"));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String fileName() {
        return null;
    }

    public String mimeType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    public long length() {
        return (long) this.content.size();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.content.toByteArray());
    }
}
