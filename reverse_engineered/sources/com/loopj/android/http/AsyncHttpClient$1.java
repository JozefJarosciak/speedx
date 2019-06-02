package com.loopj.android.http;

import android.util.Log;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class AsyncHttpClient$1 implements HttpRequestInterceptor {
    final /* synthetic */ AsyncHttpClient this$0;

    AsyncHttpClient$1(AsyncHttpClient asyncHttpClient) {
        this.this$0 = asyncHttpClient;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (!httpRequest.containsHeader("Accept-Encoding")) {
            httpRequest.addHeader("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
        }
        for (String str : AsyncHttpClient.access$000(this.this$0).keySet()) {
            if (httpRequest.containsHeader(str)) {
                Header firstHeader = httpRequest.getFirstHeader(str);
                Log.d(AsyncHttpClient.LOG_TAG, String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{str, AsyncHttpClient.access$000(this.this$0).get(str), firstHeader.getName(), firstHeader.getValue()}));
                httpRequest.removeHeader(firstHeader);
            }
            httpRequest.addHeader(str, (String) AsyncHttpClient.access$000(this.this$0).get(str));
        }
    }
}
