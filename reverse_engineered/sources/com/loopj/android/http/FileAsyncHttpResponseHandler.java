package com.loopj.android.http;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    static final /* synthetic */ boolean $assertionsDisabled = (!FileAsyncHttpResponseHandler.class.desiredAssertionStatus());
    private static final String LOG_TAG = "FileAsyncHttpResponseHandler";
    protected final boolean append;
    protected final File mFile;

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, File file);

    public abstract void onSuccess(int i, Header[] headerArr, File file);

    public FileAsyncHttpResponseHandler(File file) {
        this(file, false);
    }

    public FileAsyncHttpResponseHandler(File file, boolean z) {
        AssertUtils.asserts(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        this.mFile = file;
        this.append = z;
    }

    public FileAsyncHttpResponseHandler(Context context) {
        this.mFile = getTemporaryFile(context);
        this.append = false;
    }

    public boolean deleteTargetFile() {
        return getTargetFile() != null && getTargetFile().delete();
    }

    protected File getTemporaryFile(Context context) {
        AssertUtils.asserts(context != null, "Tried creating temporary file without having Context");
        try {
            if ($assertionsDisabled || context != null) {
                return File.createTempFile("temp_", "_handled", context.getCacheDir());
            }
            throw new AssertionError();
        } catch (Throwable e) {
            Log.e(LOG_TAG, "Cannot create temporary file", e);
            return null;
        }
    }

    protected File getTargetFile() {
        if ($assertionsDisabled || this.mFile != null) {
            return this.mFile;
        }
        throw new AssertionError();
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        onFailure(i, headerArr, th, getTargetFile());
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        onSuccess(i, headerArr, getTargetFile());
    }

    protected byte[] getResponseData(HttpEntity httpEntity) throws IOException {
        int i = 0;
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            long contentLength = httpEntity.getContentLength();
            OutputStream fileOutputStream = new FileOutputStream(getTargetFile(), this.append);
            if (content != null) {
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            AsyncHttpClient.silentCloseInputStream(content);
                            fileOutputStream.flush();
                            AsyncHttpClient.silentCloseOutputStream(fileOutputStream);
                        } else {
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                            sendProgressMessage(i, (int) contentLength);
                        }
                    }
                    AsyncHttpClient.silentCloseInputStream(content);
                    fileOutputStream.flush();
                    AsyncHttpClient.silentCloseOutputStream(fileOutputStream);
                } catch (Throwable th) {
                    AsyncHttpClient.silentCloseInputStream(content);
                    fileOutputStream.flush();
                    AsyncHttpClient.silentCloseOutputStream(fileOutputStream);
                }
            }
        }
        return null;
    }
}
