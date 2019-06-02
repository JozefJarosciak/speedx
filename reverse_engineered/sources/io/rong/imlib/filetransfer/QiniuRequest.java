package io.rong.imlib.filetransfer;

import io.rong.imlib.filetransfer.FtConst.MimeType;
import java.io.File;
import org.apache.commons.cli.HelpFormatter;

public class QiniuRequest extends Request {
    private static final String Boundary = "--526f6e67436c6f7564";

    public QiniuRequest(Configuration configuration, RequestCallBack requestCallBack) {
        super(configuration, requestCallBack);
    }

    public String getContentType() {
        return "multipart/form-data; boundary=--526f6e67436c6f7564";
    }

    public long getContentLength() {
        return ((long) "\r\n----526f6e67436c6f7564--".length()) + (((long) getFormData().length()) + new File(this.url).length());
    }

    public MimeType getMimeType() {
        return this.mimeType;
    }

    public String getBoundary() {
        return Boundary;
    }

    public String getFormData() {
        return (((((((((((((HelpFormatter.DEFAULT_LONG_OPT_PREFIX + Boundary) + "\r\nContent-Disposition: form-data; name=\"token\"\r\n\r\n") + this.token) + "\r\n--") + Boundary) + "\r\nContent-Disposition: form-data; name=\"key\"\r\n\r\n") + this.fileName) + "\r\n--") + Boundary) + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\"") + this.fileName) + "\"\r\nContent-Type: ") + this.mimeType.getName()) + "\r\n\r\n";
    }
}
