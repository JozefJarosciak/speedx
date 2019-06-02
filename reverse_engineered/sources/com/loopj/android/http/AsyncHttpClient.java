package com.loopj.android.http;

import android.content.Context;
import android.os.Looper;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import ch.qos.logback.classic.spi.CallerData;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.sys.C0869a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.java_websocket.WebSocket;

public class AsyncHttpClient {
    public static final int DEFAULT_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_MAX_RETRIES = 5;
    public static final int DEFAULT_RETRY_SLEEP_TIME_MILLIS = 1500;
    public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    public static final int DEFAULT_SOCKET_TIMEOUT = 10000;
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String LOG_TAG = "AsyncHttpClient";
    private final Map<String, String> clientHeaderMap;
    private int connectTimeout;
    private final DefaultHttpClient httpClient;
    private final HttpContext httpContext;
    private boolean isUrlEncodingEnabled;
    private int maxConnections;
    private final Map<Context, List<RequestHandle>> requestMap;
    private int responseTimeout;
    private ExecutorService threadPool;

    public AsyncHttpClient() {
        this(false, 80, WebSocket.DEFAULT_WSS_PORT);
    }

    public AsyncHttpClient(int i) {
        this(false, i, WebSocket.DEFAULT_WSS_PORT);
    }

    public AsyncHttpClient(int i, int i2) {
        this(false, i, i2);
    }

    public AsyncHttpClient(boolean z, int i, int i2) {
        this(getDefaultSchemeRegistry(z, i, i2));
    }

    private static SchemeRegistry getDefaultSchemeRegistry(boolean z, int i, int i2) {
        SocketFactory fixedSocketFactory;
        if (z) {
            Log.d(LOG_TAG, "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            Log.d(LOG_TAG, "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = WebSocket.DEFAULT_WSS_PORT;
            Log.d(LOG_TAG, "Invalid HTTPS port number specified, defaulting to 443");
        }
        if (z) {
            fixedSocketFactory = MySSLSocketFactory.getFixedSocketFactory();
        } else {
            fixedSocketFactory = SSLSocketFactory.getSocketFactory();
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), i));
        schemeRegistry.register(new Scheme(C0845b.f2060a, fixedSocketFactory, i2));
        return schemeRegistry;
    }

    public AsyncHttpClient(SchemeRegistry schemeRegistry) {
        this.maxConnections = 10;
        this.connectTimeout = 10000;
        this.responseTimeout = 10000;
        this.isUrlEncodingEnabled = true;
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) this.connectTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(this.maxConnections));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, this.responseTimeout);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.connectTimeout);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.threadPool = getDefaultThreadPool();
        this.requestMap = Collections.synchronizedMap(new WeakHashMap());
        this.clientHeaderMap = new HashMap();
        this.httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        this.httpClient = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.httpClient.addRequestInterceptor(new AsyncHttpClient$1(this));
        this.httpClient.addResponseInterceptor(new AsyncHttpClient$2(this));
        this.httpClient.addRequestInterceptor(new AsyncHttpClient$3(this), 0);
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(5, 1500));
    }

    public static void allowRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToWhitelist(cls);
        }
    }

    public static void blockRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToBlacklist(cls);
        }
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public HttpContext getHttpContext() {
        return this.httpContext;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.httpContext.setAttribute("http.cookie-store", cookieStore);
    }

    public void setThreadPool(ExecutorService executorService) {
        this.threadPool = executorService;
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    protected ExecutorService getDefaultThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public void setEnableRedirects(boolean z, boolean z2, boolean z3) {
        this.httpClient.getParams().setBooleanParameter("http.protocol.reject-relative-redirect", !z2);
        this.httpClient.getParams().setBooleanParameter("http.protocol.allow-circular-redirects", z3);
        this.httpClient.setRedirectHandler(new MyRedirectHandler(z));
    }

    public void setEnableRedirects(boolean z, boolean z2) {
        setEnableRedirects(z, z2, true);
    }

    public void setEnableRedirects(boolean z) {
        setEnableRedirects(z, z, z);
    }

    public void setRedirectHandler(RedirectHandler redirectHandler) {
        this.httpClient.setRedirectHandler(redirectHandler);
    }

    public void setUserAgent(String str) {
        HttpProtocolParams.setUserAgent(this.httpClient.getParams(), str);
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int i) {
        if (i < 1) {
            i = 10;
        }
        this.maxConnections = i;
        ConnManagerParams.setMaxConnectionsPerRoute(this.httpClient.getParams(), new ConnPerRouteBean(this.maxConnections));
    }

    public int getTimeout() {
        return this.connectTimeout;
    }

    public void setTimeout(int i) {
        if (i < 1000) {
            i = 10000;
        }
        setConnectTimeout(i);
        setResponseTimeout(i);
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.connectTimeout = i;
        HttpParams params = this.httpClient.getParams();
        ConnManagerParams.setTimeout(params, (long) this.connectTimeout);
        HttpConnectionParams.setConnectionTimeout(params, this.connectTimeout);
    }

    public int getResponseTimeout() {
        return this.responseTimeout;
    }

    public void setResponseTimeout(int i) {
        if (i < 1000) {
            i = 10000;
        }
        this.responseTimeout = i;
        HttpConnectionParams.setSoTimeout(this.httpClient.getParams(), this.responseTimeout);
    }

    public void setProxy(String str, int i) {
        this.httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(str, i));
    }

    public void setProxy(String str, int i, String str2, String str3) {
        this.httpClient.getCredentialsProvider().setCredentials(new AuthScope(str, i), new UsernamePasswordCredentials(str2, str3));
        this.httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(str, i));
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme(C0845b.f2060a, sSLSocketFactory, WebSocket.DEFAULT_WSS_PORT));
    }

    public void setMaxRetriesAndTimeout(int i, int i2) {
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(i, i2));
    }

    public void removeAllHeaders() {
        this.clientHeaderMap.clear();
    }

    public void addHeader(String str, String str2) {
        this.clientHeaderMap.put(str, str2);
    }

    public void removeHeader(String str) {
        this.clientHeaderMap.remove(str);
    }

    public void setBasicAuth(String str, String str2) {
        setBasicAuth(str, str2, false);
    }

    public void setBasicAuth(String str, String str2, boolean z) {
        setBasicAuth(str, str2, null, z);
    }

    public void setBasicAuth(String str, String str2, AuthScope authScope) {
        setBasicAuth(str, str2, authScope, false);
    }

    public void setBasicAuth(String str, String str2, AuthScope authScope, boolean z) {
        setCredentials(authScope, new UsernamePasswordCredentials(str, str2));
        setAuthenticationPreemptive(z);
    }

    public void setCredentials(AuthScope authScope, Credentials credentials) {
        if (credentials == null) {
            Log.d(LOG_TAG, "Provided credentials are null, not setting");
            return;
        }
        CredentialsProvider credentialsProvider = this.httpClient.getCredentialsProvider();
        if (authScope == null) {
            authScope = AuthScope.ANY;
        }
        credentialsProvider.setCredentials(authScope, credentials);
    }

    public void setAuthenticationPreemptive(boolean z) {
        if (z) {
            this.httpClient.addRequestInterceptor(new PreemtiveAuthorizationHttpRequestInterceptor(), 0);
        } else {
            this.httpClient.removeRequestInterceptorByClass(PreemtiveAuthorizationHttpRequestInterceptor.class);
        }
    }

    @Deprecated
    public void clearBasicAuth() {
        clearCredentialsProvider();
    }

    public void clearCredentialsProvider() {
        this.httpClient.getCredentialsProvider().clear();
    }

    public void cancelRequests(Context context, boolean z) {
        if (context == null) {
            Log.e(LOG_TAG, "Passed null Context to cancelRequests");
            return;
        }
        Runnable asyncHttpClient$4 = new AsyncHttpClient$4(this, context, z);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(asyncHttpClient$4).start();
        } else {
            asyncHttpClient$4.run();
        }
    }

    public void cancelAllRequests(boolean z) {
        for (List<RequestHandle> list : this.requestMap.values()) {
            if (list != null) {
                for (RequestHandle cancel : list) {
                    cancel.cancel(z);
                }
            }
        }
        this.requestMap.clear();
    }

    public RequestHandle head(String str, ResponseHandlerInterface responseHandlerInterface) {
        return head(null, str, null, responseHandlerInterface);
    }

    public RequestHandle head(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return head(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle head(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return head(context, str, null, responseHandlerInterface);
    }

    public RequestHandle head(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), null, responseHandlerInterface, context);
    }

    public RequestHandle head(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpHead = new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpHead.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpHead, null, responseHandlerInterface, context);
    }

    public RequestHandle get(String str, ResponseHandlerInterface responseHandlerInterface) {
        return get(null, str, null, responseHandlerInterface);
    }

    public RequestHandle get(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return get(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle get(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return get(context, str, null, responseHandlerInterface);
    }

    public RequestHandle get(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams)), null, responseHandlerInterface, context);
    }

    public RequestHandle get(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpGet = new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpGet.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpGet, null, responseHandlerInterface, context);
    }

    public RequestHandle post(String str, ResponseHandlerInterface responseHandlerInterface) {
        return post(null, str, null, responseHandlerInterface);
    }

    public RequestHandle post(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return post(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle post(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return post(context, str, paramsToEntity(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle post(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPost(URI.create(str).normalize()), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle post(Context context, String str, Header[] headerArr, RequestParams requestParams, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpPost = new HttpPost(URI.create(str).normalize());
        if (requestParams != null) {
            httpPost.setEntity(paramsToEntity(requestParams, responseHandlerInterface));
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpPost, str2, responseHandlerInterface, context);
    }

    public RequestHandle post(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(new HttpPost(URI.create(str).normalize()), httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, responseHandlerInterface, context);
    }

    public RequestHandle put(String str, ResponseHandlerInterface responseHandlerInterface) {
        return put(null, str, null, responseHandlerInterface);
    }

    public RequestHandle put(String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return put(null, str, requestParams, responseHandlerInterface);
    }

    public RequestHandle put(Context context, String str, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        return put(context, str, paramsToEntity(requestParams, responseHandlerInterface), null, responseHandlerInterface);
    }

    public RequestHandle put(Context context, String str, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPut(URI.create(str).normalize()), httpEntity), str2, responseHandlerInterface, context);
    }

    public RequestHandle put(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(new HttpPut(URI.create(str).normalize()), httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, responseHandlerInterface, context);
    }

    public RequestHandle delete(String str, ResponseHandlerInterface responseHandlerInterface) {
        return delete(null, str, responseHandlerInterface);
    }

    public RequestHandle delete(Context context, String str, ResponseHandlerInterface responseHandlerInterface) {
        return sendRequest(this.httpClient, this.httpContext, new HttpDelete(URI.create(str).normalize()), null, responseHandlerInterface, context);
    }

    public RequestHandle delete(Context context, String str, Header[] headerArr, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpDelete = new HttpDelete(URI.create(str).normalize());
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpDelete, null, responseHandlerInterface, context);
    }

    public RequestHandle delete(Context context, String str, Header[] headerArr, RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpUriRequest httpDelete = new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, str, requestParams));
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        return sendRequest(this.httpClient, this.httpContext, httpDelete, null, responseHandlerInterface, context);
    }

    protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        return new AsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, responseHandlerInterface);
    }

    protected RequestHandle sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (httpUriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (responseHandlerInterface == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (responseHandlerInterface.getUseSynchronousMode()) {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        } else {
            if (str != null) {
                if (!(httpUriRequest instanceof HttpEntityEnclosingRequestBase) || ((HttpEntityEnclosingRequestBase) httpUriRequest).getEntity() == null) {
                    httpUriRequest.setHeader("Content-Type", str);
                } else {
                    Log.w(LOG_TAG, "Passed contentType will be ignored because HttpEntity sets content type");
                }
            }
            responseHandlerInterface.setRequestHeaders(httpUriRequest.getAllHeaders());
            responseHandlerInterface.setRequestURI(httpUriRequest.getURI());
            Object newAsyncHttpRequest = newAsyncHttpRequest(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context);
            this.threadPool.submit(newAsyncHttpRequest);
            RequestHandle requestHandle = new RequestHandle(newAsyncHttpRequest);
            if (context != null) {
                List list = (List) this.requestMap.get(context);
                synchronized (this.requestMap) {
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.requestMap.put(context, list);
                    }
                }
                if (responseHandlerInterface instanceof RangeFileAsyncHttpResponseHandler) {
                    ((RangeFileAsyncHttpResponseHandler) responseHandlerInterface).updateRequestHeaders(httpUriRequest);
                }
                list.add(requestHandle);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((RequestHandle) it.next()).shouldBeGarbageCollected()) {
                        it.remove();
                    }
                }
            }
            return requestHandle;
        }
    }

    public void setURLEncodingEnabled(boolean z) {
        this.isUrlEncodingEnabled = z;
    }

    public static String getUrlWithQueryString(boolean z, String str, RequestParams requestParams) {
        if (str == null) {
            return null;
        }
        String replace;
        if (z) {
            replace = str.replace(" ", "%20");
        } else {
            replace = str;
        }
        if (requestParams == null) {
            return replace;
        }
        String trim = requestParams.getParamString().trim();
        if (trim.equals("") || trim.equals(CallerData.NA)) {
            return replace;
        }
        return (replace + (replace.contains(CallerData.NA) ? C0869a.f2161b : CallerData.NA)) + trim;
    }

    public static boolean isInputStreamGZIPCompressed(PushbackInputStream pushbackInputStream) throws IOException {
        boolean z = true;
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int read = pushbackInputStream.read(bArr);
        pushbackInputStream.unread(bArr);
        int i = ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[0] & 255);
        if (!(read == 2 && 35615 == i)) {
            z = false;
        }
        return z;
    }

    public static void silentCloseInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                Log.w(LOG_TAG, "Cannot close input stream", e);
            }
        }
    }

    public static void silentCloseOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable e) {
                Log.w(LOG_TAG, "Cannot close output stream", e);
            }
        }
    }

    private HttpEntity paramsToEntity(RequestParams requestParams, ResponseHandlerInterface responseHandlerInterface) {
        HttpEntity httpEntity = null;
        if (requestParams != null) {
            try {
                httpEntity = requestParams.getEntity(responseHandlerInterface);
            } catch (Throwable e) {
                if (responseHandlerInterface != null) {
                    responseHandlerInterface.sendFailureMessage(0, httpEntity, httpEntity, e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        return httpEntity;
    }

    public boolean isUrlEncodingEnabled() {
        return this.isUrlEncodingEnabled;
    }

    private HttpEntityEnclosingRequestBase addEntityToRequestBase(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }

    public static void endEntityViaReflection(HttpEntity httpEntity) {
        if (httpEntity instanceof HttpEntityWrapper) {
            try {
                for (Field field : HttpEntityWrapper.class.getDeclaredFields()) {
                    if (field.getName().equals("wrappedEntity")) {
                        break;
                    }
                }
                Field field2 = null;
                if (field2 != null) {
                    field2.setAccessible(true);
                    HttpEntity httpEntity2 = (HttpEntity) field2.get(httpEntity);
                    if (httpEntity2 != null) {
                        httpEntity2.consumeContent();
                    }
                }
            } catch (Throwable th) {
                Log.e(LOG_TAG, "wrappedEntity consume", th);
            }
        }
    }
}
