package com.loopj.android.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.ByteArrayBuffer;

public abstract class AsyncHttpResponseHandler implements ResponseHandlerInterface {
    protected static final int BUFFER_SIZE = 4096;
    protected static final int CANCEL_MESSAGE = 6;
    public static final String DEFAULT_CHARSET = "UTF-8";
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int FINISH_MESSAGE = 3;
    private static final String LOG_TAG = "AsyncHttpResponseHandler";
    protected static final int PROGRESS_MESSAGE = 4;
    protected static final int RETRY_MESSAGE = 5;
    protected static final int START_MESSAGE = 2;
    protected static final int SUCCESS_MESSAGE = 0;
    public static final String UTF8_BOM = "﻿";
    private Handler handler;
    private Looper looper;
    private Header[] requestHeaders;
    private URI requestURI;
    private String responseCharset;
    private boolean useSynchronousMode;

    private static class ResponderHandler extends Handler {
        private final AsyncHttpResponseHandler mResponder;

        ResponderHandler(AsyncHttpResponseHandler asyncHttpResponseHandler, Looper looper) {
            super(looper);
            this.mResponder = asyncHttpResponseHandler;
        }

        public void handleMessage(Message message) {
            this.mResponder.handleMessage(message);
        }
    }

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public URI getRequestURI() {
        return this.requestURI;
    }

    public Header[] getRequestHeaders() {
        return this.requestHeaders;
    }

    public void setRequestURI(URI uri) {
        this.requestURI = uri;
    }

    public void setRequestHeaders(Header[] headerArr) {
        this.requestHeaders = headerArr;
    }

    public boolean getUseSynchronousMode() {
        return this.useSynchronousMode;
    }

    public void setUseSynchronousMode(boolean z) {
        if (!z && this.looper == null) {
            z = true;
            Log.w(LOG_TAG, "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (!z && this.handler == null) {
            this.handler = new ResponderHandler(this, this.looper);
        } else if (z && this.handler != null) {
            this.handler = null;
        }
        this.useSynchronousMode = z;
    }

    public void setCharset(String str) {
        this.responseCharset = str;
    }

    public String getCharset() {
        return this.responseCharset == null ? "UTF-8" : this.responseCharset;
    }

    public AsyncHttpResponseHandler() {
        this(null);
    }

    public AsyncHttpResponseHandler(Looper looper) {
        this.responseCharset = "UTF-8";
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        if (looper == null) {
            looper = Looper.myLooper();
        }
        this.looper = looper;
        setUseSynchronousMode(false);
    }

    public void onProgress(int i, int i2) {
        String str = LOG_TAG;
        String str2 = "Progress %d from %d (%2.0f%%)";
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Double.valueOf(i2 > 0 ? ((((double) i) * 1.0d) / ((double) i2)) * 100.0d : -1.0d);
        Log.v(str, String.format(str2, objArr));
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onPreProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void onPostProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {
    }

    public void onRetry(int i) {
        Log.d(LOG_TAG, String.format("Request retry no. %d", new Object[]{Integer.valueOf(i)}));
    }

    public void onCancel() {
        Log.d(LOG_TAG, "Request got cancelled");
    }

    public final void sendProgressMessage(int i, int i2) {
        sendMessage(obtainMessage(4, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    public final void sendSuccessMessage(int i, Header[] headerArr, byte[] bArr) {
        sendMessage(obtainMessage(0, new Object[]{Integer.valueOf(i), headerArr, bArr}));
    }

    public final void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        sendMessage(obtainMessage(1, new Object[]{Integer.valueOf(i), headerArr, bArr, th}));
    }

    public final void sendStartMessage() {
        sendMessage(obtainMessage(2, null));
    }

    public final void sendFinishMessage() {
        sendMessage(obtainMessage(3, null));
    }

    public final void sendRetryMessage(int i) {
        sendMessage(obtainMessage(5, new Object[]{Integer.valueOf(i)}));
    }

    public final void sendCancelMessage() {
        sendMessage(obtainMessage(6, null));
    }

    protected void handleMessage(Message message) {
        Object[] objArr;
        switch (message.what) {
            case 0:
                objArr = (Object[]) message.obj;
                if (objArr == null || objArr.length < 3) {
                    Log.e(LOG_TAG, "SUCCESS_MESSAGE didn't got enough params");
                    return;
                } else {
                    onSuccess(((Integer) objArr[0]).intValue(), (Header[]) objArr[1], (byte[]) objArr[2]);
                    return;
                }
            case 1:
                objArr = (Object[]) message.obj;
                if (objArr == null || objArr.length < 4) {
                    Log.e(LOG_TAG, "FAILURE_MESSAGE didn't got enough params");
                    return;
                } else {
                    onFailure(((Integer) objArr[0]).intValue(), (Header[]) objArr[1], (byte[]) objArr[2], (Throwable) objArr[3]);
                    return;
                }
            case 2:
                onStart();
                return;
            case 3:
                onFinish();
                return;
            case 4:
                objArr = (Object[]) message.obj;
                if (objArr == null || objArr.length < 2) {
                    Log.e(LOG_TAG, "PROGRESS_MESSAGE didn't got enough params");
                    return;
                }
                try {
                    onProgress(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue());
                    return;
                } catch (Throwable th) {
                    Log.e(LOG_TAG, "custom onProgress contains an error", th);
                    return;
                }
            case 5:
                objArr = (Object[]) message.obj;
                if (objArr == null || objArr.length != 1) {
                    Log.e(LOG_TAG, "RETRY_MESSAGE didn't get enough params");
                    return;
                } else {
                    onRetry(((Integer) objArr[0]).intValue());
                    return;
                }
            case 6:
                onCancel();
                return;
            default:
                return;
        }
    }

    protected void sendMessage(Message message) {
        if (getUseSynchronousMode() || this.handler == null) {
            handleMessage(message);
        } else if (!Thread.currentThread().isInterrupted()) {
            AssertUtils.asserts(this.handler != null, "handler should not be null!");
            this.handler.sendMessage(message);
        }
    }

    protected void postRunnable(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (getUseSynchronousMode() || this.handler == null) {
            runnable.run();
            return;
        }
        AssertUtils.asserts(this.handler != null, "handler should not be null!");
        this.handler.post(runnable);
    }

    protected Message obtainMessage(int i, Object obj) {
        return Message.obtain(this.handler, i, obj);
    }

    public void sendResponseMessage(HttpResponse httpResponse) throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine statusLine = httpResponse.getStatusLine();
            byte[] responseData = getResponseData(httpResponse.getEntity());
            if (!Thread.currentThread().isInterrupted()) {
                if (statusLine.getStatusCode() >= 300) {
                    sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), responseData, new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                } else {
                    sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), responseData);
                }
            }
        }
    }

    byte[] getResponseData(HttpEntity httpEntity) throws IOException {
        int i = 4096;
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                long contentLength = httpEntity.getContentLength();
                if (contentLength > 2147483647L) {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
                if (contentLength > 0) {
                    i = (int) contentLength;
                }
                try {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
                    byte[] bArr = new byte[4096];
                    i = 0;
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        int i2 = i + read;
                        byteArrayBuffer.append(bArr, 0, read);
                        sendProgressMessage(i2, (int) (contentLength <= 0 ? 1 : contentLength));
                        i = i2;
                    }
                    AsyncHttpClient.silentCloseInputStream(content);
                    AsyncHttpClient.endEntityViaReflection(httpEntity);
                    return byteArrayBuffer.toByteArray();
                } catch (OutOfMemoryError e) {
                    System.gc();
                    throw new IOException("File too large to fit into available memory");
                } catch (Throwable th) {
                    AsyncHttpClient.silentCloseInputStream(content);
                    AsyncHttpClient.endEntityViaReflection(httpEntity);
                }
            }
        }
        return null;
    }
}
