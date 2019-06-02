package com.loopj.android.http;

import android.util.Log;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class AsyncHttpRequest implements Runnable {
    private boolean cancelIsNotified;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private boolean isCancelled;
    private boolean isFinished;
    private boolean isRequestPreProcessed;
    private final HttpUriRequest request;
    private final ResponseHandlerInterface responseHandler;

    public AsyncHttpRequest(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, ResponseHandlerInterface responseHandlerInterface) {
        this.client = abstractHttpClient;
        this.context = httpContext;
        this.request = httpUriRequest;
        this.responseHandler = responseHandlerInterface;
    }

    public void onPreProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void onPostProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void run() {
        if (!isCancelled()) {
            if (!this.isRequestPreProcessed) {
                this.isRequestPreProcessed = true;
                onPreProcessRequest(this);
            }
            if (!isCancelled()) {
                if (this.responseHandler != null) {
                    this.responseHandler.sendStartMessage();
                }
                if (!isCancelled()) {
                    try {
                        makeRequestWithRetries();
                    } catch (Throwable e) {
                        if (isCancelled() || this.responseHandler == null) {
                            Log.e("AsyncHttpRequest", "makeRequestWithRetries returned error, but handler is null", e);
                        } else {
                            this.responseHandler.sendFailureMessage(0, null, null, e);
                        }
                    }
                    if (!isCancelled()) {
                        if (this.responseHandler != null) {
                            this.responseHandler.sendFinishMessage();
                        }
                        if (!isCancelled()) {
                            onPostProcessRequest(this);
                            this.isFinished = true;
                        }
                    }
                }
            }
        }
    }

    private void makeRequest() throws IOException {
        if (!isCancelled()) {
            if (this.request.getURI().getScheme() == null) {
                throw new MalformedURLException("No valid URI scheme was provided");
            }
            HttpResponse execute = this.client.execute(this.request, this.context);
            if (!isCancelled() && this.responseHandler != null) {
                this.responseHandler.onPreProcessResponse(this.responseHandler, execute);
                if (!isCancelled()) {
                    this.responseHandler.sendResponseMessage(execute);
                    if (!isCancelled()) {
                        this.responseHandler.onPostProcessResponse(this.responseHandler, execute);
                    }
                }
            }
        }
    }

    private void makeRequestWithRetries() throws IOException {
        int i;
        IOException iOException = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
        boolean z = true;
        while (z) {
            try {
                makeRequest();
                return;
            } catch (UnknownHostException e) {
                try {
                    boolean z2;
                    IOException iOException2;
                    IOException iOException3 = new IOException("UnknownHostException exception: " + e.getMessage());
                    if (this.executionCount > 0) {
                        int i2 = this.executionCount + 1;
                        this.executionCount = i2;
                        if (httpRequestRetryHandler.retryRequest(iOException3, i2, this.context)) {
                            z2 = true;
                            iOException2 = iOException3;
                            z = z2;
                            iOException = iOException2;
                        }
                    }
                    z2 = false;
                    iOException2 = iOException3;
                    z = z2;
                    iOException = iOException2;
                } catch (Throwable e2) {
                    Throwable th = e2;
                    Log.e("AsyncHttpRequest", "Unhandled exception origin cause", th);
                    iOException = new IOException("Unhandled exception: " + th.getMessage());
                }
            } catch (NullPointerException e3) {
                iOException = new IOException("NPE in HttpClient: " + e3.getMessage());
                i = this.executionCount + 1;
                this.executionCount = i;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.context);
            } catch (IOException e4) {
                iOException = e4;
                if (!isCancelled()) {
                    i = this.executionCount + 1;
                    this.executionCount = i;
                    z = httpRequestRetryHandler.retryRequest(iOException, i, this.context);
                } else {
                    return;
                }
            }
        }
        throw iOException;
        if (z && this.responseHandler != null) {
            this.responseHandler.sendRetryMessage(this.executionCount);
        }
    }

    public boolean isCancelled() {
        if (this.isCancelled) {
            sendCancelNotification();
        }
        return this.isCancelled;
    }

    private synchronized void sendCancelNotification() {
        if (!(this.isFinished || !this.isCancelled || this.cancelIsNotified)) {
            this.cancelIsNotified = true;
            if (this.responseHandler != null) {
                this.responseHandler.sendCancelMessage();
            }
        }
    }

    public boolean isDone() {
        return isCancelled() || this.isFinished;
    }

    public boolean cancel(boolean z) {
        this.isCancelled = true;
        this.request.abort();
        return isCancelled();
    }
}
