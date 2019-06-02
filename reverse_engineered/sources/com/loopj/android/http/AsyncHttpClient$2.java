package com.loopj.android.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class AsyncHttpClient$2 implements HttpResponseInterceptor {
    final /* synthetic */ AsyncHttpClient this$0;

    AsyncHttpClient$2(AsyncHttpClient asyncHttpClient) {
        this.this$0 = asyncHttpClient;
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                for (HeaderElement name : contentEncoding.getElements()) {
                    if (name.getName().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                        httpResponse.setEntity(new AsyncHttpClient$InflatingEntity(entity));
                        return;
                    }
                }
            }
        }
    }
}
