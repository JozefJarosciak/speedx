package com.loopj.android.http;

import android.content.Context;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.java_websocket.WebSocket;

public class SyncHttpClient extends AsyncHttpClient {
    public SyncHttpClient() {
        super(false, 80, WebSocket.DEFAULT_WSS_PORT);
    }

    public SyncHttpClient(int i) {
        super(false, i, WebSocket.DEFAULT_WSS_PORT);
    }

    public SyncHttpClient(int i, int i2) {
        super(false, i, i2);
    }

    public SyncHttpClient(boolean z, int i, int i2) {
        super(z, i, i2);
    }

    public SyncHttpClient(SchemeRegistry schemeRegistry) {
        super(schemeRegistry);
    }

    protected RequestHandle sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        responseHandlerInterface.setUseSynchronousMode(true);
        newAsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context).run();
        return new RequestHandle(null);
    }
}
