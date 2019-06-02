package com.facebook.stetho.inspector.network;

import java.io.IOException;
import java.io.InputStream;

public interface NetworkEventReporter {

    public interface InspectorHeaders {
        String firstHeaderValue(String str);

        int headerCount();

        String headerName(int i);

        String headerValue(int i);
    }

    public interface InspectorRequest extends InspectorHeaders {
        byte[] body() throws IOException;

        String friendlyName();

        Integer friendlyNameExtra();

        String id();

        String method();

        String url();
    }

    public interface InspectorResponse extends InspectorHeaders {
        int connectionId();

        boolean connectionReused();

        boolean fromDiskCache();

        String reasonPhrase();

        String requestId();

        int statusCode();

        String url();
    }

    void dataReceived(String str, int i, int i2);

    void dataSent(String str, int i, int i2);

    void httpExchangeFailed(String str, String str2);

    InputStream interpretResponseStream(String str, String str2, String str3, InputStream inputStream, ResponseHandler responseHandler);

    boolean isEnabled();

    void requestWillBeSent(InspectorRequest inspectorRequest);

    void responseHeadersReceived(InspectorResponse inspectorResponse);

    void responseReadFailed(String str, String str2);

    void responseReadFinished(String str);
}
