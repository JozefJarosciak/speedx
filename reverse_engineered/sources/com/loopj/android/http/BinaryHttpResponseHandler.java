package com.loopj.android.http;

import android.util.Log;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;

public abstract class BinaryHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "BinaryHttpResponseHandler";
    private String[] mAllowedContentTypes;

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public String[] getAllowedContentTypes() {
        return this.mAllowedContentTypes;
    }

    public BinaryHttpResponseHandler() {
        this.mAllowedContentTypes = new String[]{"application/octet-stream", "image/jpeg", "image/png", "image/gif"};
    }

    public BinaryHttpResponseHandler(String[] strArr) {
        this.mAllowedContentTypes = new String[]{"application/octet-stream", "image/jpeg", "image/png", "image/gif"};
        if (strArr != null) {
            this.mAllowedContentTypes = strArr;
        } else {
            Log.e(LOG_TAG, "Constructor passed allowedContentTypes was null !");
        }
    }

    public final void sendResponseMessage(HttpResponse httpResponse) throws IOException {
        int i = 0;
        StatusLine statusLine = httpResponse.getStatusLine();
        Header[] headers = httpResponse.getHeaders("Content-Type");
        if (headers.length != 1) {
            sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        Header header = headers[0];
        for (String str : getAllowedContentTypes()) {
            try {
                if (Pattern.matches(str, header.getValue())) {
                    i = 1;
                }
            } catch (Throwable e) {
                Log.e(LOG_TAG, "Given pattern is not valid: " + str, e);
            }
        }
        if (i == 0) {
            sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), null, new HttpResponseException(statusLine.getStatusCode(), "Content-Type not allowed!"));
        } else {
            super.sendResponseMessage(httpResponse);
        }
    }
}
