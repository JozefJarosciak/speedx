package io.rong.imlib.filetransfer;

import io.rong.common.RLog;
import io.rong.imlib.filetransfer.FtConst.MimeType;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.cli.HelpFormatter;

public abstract class Request {
    private HttpURLConnection conn;
    protected int connTimeout;
    protected String fileName;
    protected String method;
    protected MimeType mimeType;
    protected int readTimeout;
    protected RequestCallBack requestCallBack;
    protected String serverIp;
    protected Object tag;
    protected String token;
    protected String url;

    public abstract String getBoundary();

    public abstract long getContentLength();

    public abstract String getContentType();

    public abstract String getFormData();

    public abstract MimeType getMimeType();

    public Request(Configuration configuration, RequestCallBack requestCallBack) {
        this.connTimeout = configuration.connectTimeout;
        this.readTimeout = configuration.readTimeout;
        this.requestCallBack = requestCallBack;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void cancel() {
        if (this.conn != null) {
            this.conn.disconnect();
            this.conn = null;
        }
    }

    public void sendRequest() throws IOException {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        int i = 1;
        int i2 = 0;
        int length;
        BufferedInputStream bufferedInputStream2;
        int read;
        if (this.method.equals("POST")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(this.url));
                this.conn = (HttpURLConnection) new URL(this.serverIp).openConnection();
                this.conn.setUseCaches(false);
                this.conn.setDoOutput(true);
                this.conn.setDoInput(true);
                this.conn.setRequestMethod(this.method);
                this.conn.setRequestProperty("Connection", "close");
                this.conn.setRequestProperty("Charset", "UTF-8");
                this.conn.setRequestProperty("Content-Type", getContentType());
                String str = "\r\n--" + getBoundary() + HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
                String formData = getFormData();
                i2 = fileInputStream.available();
                if (i2 == 0) {
                    this.requestCallBack.onError(31002);
                }
                int length2 = str.length() + (i2 + formData.length());
                this.conn.setRequestProperty("Content-Length", length2 + "");
                this.conn.setFixedLengthStreamingMode(length2);
                this.conn.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(this.conn.getOutputStream());
                dataOutputStream.writeBytes(formData);
                length = formData.length();
                this.requestCallBack.onProgress(1);
                byte[] bArr = new byte[1024];
                while (true) {
                    i2 = fileInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, i2);
                    i2 += length;
                    length = (i2 * 100) / length2;
                    if (length > i) {
                        this.requestCallBack.onProgress(length);
                    } else {
                        length = i;
                    }
                    i = length;
                    length = i2;
                }
                dataOutputStream.writeBytes(str);
                this.requestCallBack.onProgress(100);
                fileInputStream.close();
                dataOutputStream.flush();
                bufferedInputStream2 = new BufferedInputStream(this.conn.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    while (true) {
                        read = bufferedInputStream2.read();
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(read);
                    }
                    length = this.conn.getResponseCode();
                    if (length < 200 || length >= 300) {
                        throw new IOException();
                    }
                    this.requestCallBack.onComplete(null);
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (this.conn != null) {
                        this.conn.disconnect();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (this.conn != null) {
                        this.conn.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream2 = null;
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (this.conn != null) {
                    this.conn.disconnect();
                }
                throw th;
            }
        } else if (this.method.equals("GET")) {
            try {
                this.conn = (HttpURLConnection) new URL(this.url).openConnection();
                this.conn.setUseCaches(false);
                this.conn.setRequestMethod(this.method);
                this.conn.setDoInput(true);
                this.conn.connect();
                bufferedInputStream2 = new BufferedInputStream(this.conn.getInputStream());
                try {
                    int contentLength = this.conn.getContentLength();
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(1024);
                    length = 0;
                    while (true) {
                        read = bufferedInputStream2.read();
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(read);
                        i2++;
                        read = (i2 * 100) / contentLength;
                        if (length < read) {
                            this.requestCallBack.onProgress(read);
                            length = read;
                        }
                    }
                    length = this.conn.getResponseCode();
                    if (length != 200) {
                        this.requestCallBack.onError(30002);
                        RLog.m19419d("fileTransfer", "download request response code is " + length);
                    } else {
                        File file = new File(this.fileName);
                        new File(file.getParentFile().getPath()).mkdirs();
                        byteArrayOutputStream2.writeTo(new FileOutputStream(file));
                        this.requestCallBack.onComplete(this.fileName);
                    }
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (this.conn != null) {
                        this.conn.disconnect();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (this.conn != null) {
                        this.conn.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (this.conn != null) {
                    this.conn.disconnect();
                }
                throw th;
            }
        }
    }
}
