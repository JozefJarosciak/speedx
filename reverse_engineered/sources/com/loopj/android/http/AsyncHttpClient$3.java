package com.loopj.android.http;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;

class AsyncHttpClient$3 implements HttpRequestInterceptor {
    final /* synthetic */ AsyncHttpClient this$0;

    AsyncHttpClient$3(AsyncHttpClient asyncHttpClient) {
        this.this$0 = asyncHttpClient;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        AuthState authState = (AuthState) httpContext.getAttribute("http.auth.target-scope");
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.getAttribute("http.auth.credentials-provider");
        HttpHost httpHost = (HttpHost) httpContext.getAttribute("http.target_host");
        if (authState.getAuthScheme() == null) {
            Credentials credentials = credentialsProvider.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()));
            if (credentials != null) {
                authState.setAuthScheme(new BasicScheme());
                authState.setCredentials(credentials);
            }
        }
    }
}
