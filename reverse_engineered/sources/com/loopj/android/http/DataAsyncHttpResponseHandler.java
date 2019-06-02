package com.loopj.android.http;

import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.util.ByteArrayBuffer;

public abstract class DataAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "DataAsyncHttpResponseHandler";
    protected static final int PROGRESS_DATA_MESSAGE = 6;

    public void onProgressData(byte[] bArr) {
        Log.d(LOG_TAG, "onProgressData(byte[]) was not overriden, but callback was received");
    }

    public final void sendProgressDataMessage(byte[] bArr) {
        sendMessage(obtainMessage(6, new Object[]{bArr}));
    }

    protected void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 6:
                Object[] objArr = (Object[]) message.obj;
                if (objArr == null || objArr.length < 1) {
                    Log.e(LOG_TAG, "PROGRESS_DATA_MESSAGE didn't got enough params");
                    return;
                }
                try {
                    onProgressData((byte[]) objArr[0]);
                    return;
                } catch (Throwable th) {
                    Log.e(LOG_TAG, "custom onProgressData contains an error", th);
                    return;
                }
            default:
                return;
        }
    }

    byte[] getResponseData(HttpEntity httpEntity) throws IOException {
        byte[] bArr = null;
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                long contentLength = httpEntity.getContentLength();
                if (contentLength > 2147483647L) {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
                if (contentLength < 0) {
                    contentLength = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
                try {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer((int) contentLength);
                    bArr = new byte[4096];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        byteArrayBuffer.append(bArr, 0, read);
                        sendProgressDataMessage(copyOfRange(bArr, 0, read));
                    }
                    AsyncHttpClient.silentCloseInputStream(content);
                    bArr = byteArrayBuffer.toByteArray();
                } catch (OutOfMemoryError e) {
                    System.gc();
                    throw new IOException("File too large to fit into available memory");
                } catch (Throwable th) {
                    AsyncHttpClient.silentCloseInputStream(content);
                }
            }
        }
        return bArr;
    }

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = bArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new byte[i3];
        System.arraycopy(bArr, i, obj, 0, length);
        return obj;
    }
}
