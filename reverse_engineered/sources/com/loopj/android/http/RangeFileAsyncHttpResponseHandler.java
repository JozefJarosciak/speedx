package com.loopj.android.http;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;

public abstract class RangeFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {
    private static final String LOG_TAG = "RangeFileAsyncHttpResponseHandler";
    private boolean append = false;
    private long current = 0;

    public RangeFileAsyncHttpResponseHandler(File file) {
        super(file);
    }

    public void sendResponseMessage(HttpResponse httpResponse) throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null);
                }
            } else if (statusLine.getStatusCode() >= 300) {
                if (!Thread.currentThread().isInterrupted()) {
                    sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                }
            } else if (!Thread.currentThread().isInterrupted()) {
                Header firstHeader = httpResponse.getFirstHeader("Content-Range");
                if (firstHeader == null) {
                    this.append = false;
                    this.current = 0;
                } else {
                    Log.v(LOG_TAG, "Content-Range: " + firstHeader.getValue());
                }
                sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), getResponseData(httpResponse.getEntity()));
            }
        }
    }

    protected byte[] getResponseData(HttpEntity httpEntity) throws IOException {
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            long contentLength = httpEntity.getContentLength() + this.current;
            FileOutputStream fileOutputStream = new FileOutputStream(getTargetFile(), this.append);
            if (content != null) {
                try {
                    byte[] bArr = new byte[4096];
                    while (this.current < contentLength) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        this.current += (long) read;
                        fileOutputStream.write(bArr, 0, read);
                        sendProgressMessage((int) this.current, (int) contentLength);
                    }
                    content.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Throwable th) {
                    content.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
        }
        return null;
    }

    public void updateRequestHeaders(HttpUriRequest httpUriRequest) {
        if (this.mFile.exists() && this.mFile.canWrite()) {
            this.current = this.mFile.length();
        }
        if (this.current > 0) {
            this.append = true;
            httpUriRequest.setHeader("Range", "bytes=" + this.current + HelpFormatter.DEFAULT_OPT_PREFIX);
        }
    }
}
