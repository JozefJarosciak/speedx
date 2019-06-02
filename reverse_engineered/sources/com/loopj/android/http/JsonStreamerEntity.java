package com.loopj.android.http;

import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVException;
import com.google.common.base.Ascii;
import com.loopj.android.http.RequestParams.FileWrapper;
import com.loopj.android.http.RequestParams.StreamWrapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonStreamerEntity implements HttpEntity {
    private static final int BUFFER_SIZE = 4096;
    private static final StringBuilder BUILDER = new StringBuilder(128);
    private static final UnsupportedOperationException ERR_UNSUPPORTED = new UnsupportedOperationException("Unsupported operation in this implementation.");
    private static final Header HEADER_GZIP_ENCODING = new BasicHeader("Content-Encoding", AsyncHttpClient.ENCODING_GZIP);
    private static final Header HEADER_JSON_CONTENT = new BasicHeader("Content-Type", "application/json");
    private static final byte[] JSON_FALSE = "false".getBytes();
    private static final byte[] JSON_NULL = "null".getBytes();
    private static final byte[] JSON_TRUE = "true".getBytes();
    private static final String LOG_TAG = "JsonStreamerEntity";
    private static final byte[] STREAM_CONTENTS = escape("contents");
    private static final byte[] STREAM_ELAPSED = escape("_elapsed");
    private static final byte[] STREAM_NAME = escape("name");
    private static final byte[] STREAM_TYPE = escape("type");
    private final byte[] buffer = new byte[4096];
    private final Header contentEncoding;
    private final Map<String, Object> jsonParams = new HashMap();
    private final ResponseHandlerInterface progressHandler;

    public JsonStreamerEntity(ResponseHandlerInterface responseHandlerInterface, boolean z) {
        this.progressHandler = responseHandlerInterface;
        this.contentEncoding = z ? HEADER_GZIP_ENCODING : null;
    }

    public void addPart(String str, Object obj) {
        this.jsonParams.put(str, obj);
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public long getContentLength() {
        return -1;
    }

    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    public Header getContentType() {
        return HEADER_JSON_CONTENT;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw ERR_UNSUPPORTED;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalStateException("Output stream cannot be null.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.contentEncoding != null) {
            outputStream = new GZIPOutputStream(outputStream, 4096);
        }
        outputStream.write(AVException.INVALID_ACL);
        for (String str : this.jsonParams.keySet()) {
            Object obj = this.jsonParams.get(str);
            if (obj != null) {
                outputStream.write(escape(str));
                outputStream.write(58);
                boolean z = obj instanceof FileWrapper;
                if (z || (obj instanceof StreamWrapper)) {
                    outputStream.write(AVException.INVALID_ACL);
                    if (z) {
                        writeToFromFile(outputStream, (FileWrapper) obj);
                    } else {
                        writeToFromStream(outputStream, (StreamWrapper) obj);
                    }
                    outputStream.write(AVException.INVALID_EMAIL_ADDRESS);
                } else if (obj instanceof JsonValueInterface) {
                    outputStream.write(((JsonValueInterface) obj).getEscapedJsonValue());
                } else if (obj instanceof JSONObject) {
                    outputStream.write(((JSONObject) obj).toString().getBytes());
                } else if (obj instanceof JSONArray) {
                    outputStream.write(((JSONArray) obj).toString().getBytes());
                } else if (obj instanceof Boolean) {
                    outputStream.write(((Boolean) obj).booleanValue() ? JSON_TRUE : JSON_FALSE);
                } else if (obj instanceof Long) {
                    outputStream.write((((Number) obj).longValue() + "").getBytes());
                } else if (obj instanceof Double) {
                    outputStream.write((((Number) obj).doubleValue() + "").getBytes());
                } else if (obj instanceof Float) {
                    outputStream.write((((Number) obj).floatValue() + "").getBytes());
                } else if (obj instanceof Integer) {
                    outputStream.write((((Number) obj).intValue() + "").getBytes());
                } else {
                    outputStream.write(escape(obj.toString()));
                }
                outputStream.write(44);
            }
        }
        outputStream.write(STREAM_ELAPSED);
        outputStream.write(58);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        outputStream.write((currentTimeMillis2 + C0880h.f2222d).getBytes());
        Log.i(LOG_TAG, "Uploaded JSON in " + Math.floor((double) (currentTimeMillis2 / 1000)) + " seconds");
        outputStream.flush();
        AsyncHttpClient.silentCloseOutputStream(outputStream);
    }

    private void writeToFromStream(OutputStream outputStream, StreamWrapper streamWrapper) throws IOException {
        writeMetaData(outputStream, streamWrapper.name, streamWrapper.contentType);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        while (true) {
            int read = streamWrapper.inputStream.read(this.buffer);
            if (read == -1) {
                break;
            }
            base64OutputStream.write(this.buffer, 0, read);
        }
        AsyncHttpClient.silentCloseOutputStream(base64OutputStream);
        endMetaData(outputStream);
        if (streamWrapper.autoClose) {
            AsyncHttpClient.silentCloseInputStream(streamWrapper.inputStream);
        }
    }

    private void writeToFromFile(OutputStream outputStream, FileWrapper fileWrapper) throws IOException {
        writeMetaData(outputStream, fileWrapper.file.getName(), fileWrapper.contentType);
        int length = (int) fileWrapper.file.length();
        InputStream fileInputStream = new FileInputStream(fileWrapper.file);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        int i = 0;
        while (true) {
            int read = fileInputStream.read(this.buffer);
            if (read != -1) {
                base64OutputStream.write(this.buffer, 0, read);
                i += read;
                this.progressHandler.sendProgressMessage(i, length);
            } else {
                AsyncHttpClient.silentCloseOutputStream(base64OutputStream);
                endMetaData(outputStream);
                AsyncHttpClient.silentCloseInputStream(fileInputStream);
                return;
            }
        }
    }

    private void writeMetaData(OutputStream outputStream, String str, String str2) throws IOException {
        outputStream.write(STREAM_NAME);
        outputStream.write(58);
        outputStream.write(escape(str));
        outputStream.write(44);
        outputStream.write(STREAM_TYPE);
        outputStream.write(58);
        outputStream.write(escape(str2));
        outputStream.write(44);
        outputStream.write(STREAM_CONTENTS);
        outputStream.write(58);
        outputStream.write(34);
    }

    private void endMetaData(OutputStream outputStream) throws IOException {
        outputStream.write(34);
    }

    static byte[] escape(String str) {
        if (str == null) {
            return JSON_NULL;
        }
        BUILDER.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        int length = str.length();
        int i = -1;
        while (true) {
            int i2 = i + 1;
            if (i2 < length) {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case '\b':
                        BUILDER.append("\\b");
                        break;
                    case '\t':
                        BUILDER.append("\\t");
                        break;
                    case '\n':
                        BUILDER.append("\\n");
                        break;
                    case '\f':
                        BUILDER.append("\\f");
                        break;
                    case '\r':
                        BUILDER.append("\\r");
                        break;
                    case '\"':
                        BUILDER.append("\\\"");
                        break;
                    case '\\':
                        BUILDER.append("\\\\");
                        break;
                    default:
                        if ((charAt >= '\u0000' && charAt <= '\u001f') || ((charAt >= Ascii.MAX && charAt <= '') || (charAt >= ' ' && charAt <= '⃿'))) {
                            String toHexString = Integer.toHexString(charAt);
                            BUILDER.append("\\u");
                            int length2 = 4 - toHexString.length();
                            for (i = 0; i < length2; i++) {
                                BUILDER.append('0');
                            }
                            BUILDER.append(toHexString.toUpperCase(Locale.US));
                            break;
                        }
                        BUILDER.append(charAt);
                        break;
                }
                i = i2;
            } else {
                BUILDER.append(CoreConstants.DOUBLE_QUOTE_CHAR);
                try {
                    byte[] bytes = BUILDER.toString().getBytes();
                    return bytes;
                } finally {
                    BUILDER.setLength(0);
                }
            }
        }
    }
}
