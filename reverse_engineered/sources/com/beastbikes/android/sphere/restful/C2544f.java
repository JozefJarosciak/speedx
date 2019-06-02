package com.beastbikes.android.sphere.restful;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.sys.C0869a;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1445b;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1448e;
import com.beastbikes.android.sphere.restful.p078a.C1449f;
import com.beastbikes.android.sphere.restful.p078a.C1450g;
import com.beastbikes.android.sphere.restful.p078a.C1451h;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import com.beastbikes.android.sphere.restful.p161b.C2539a;
import com.beastbikes.framework.android.p088g.C2799c;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.loopj.android.http.AsyncHttpClient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.java_websocket.WebSocket;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ServiceStubInvocation */
/* renamed from: com.beastbikes.android.sphere.restful.f */
public class C2544f implements C2538a {
    /* renamed from: g */
    private static final Logger f12013g = LoggerFactory.getLogger("ServiceStubInvocation");
    /* renamed from: a */
    final Context f12014a;
    /* renamed from: b */
    final Class<?> f12015b;
    /* renamed from: c */
    final Method f12016c;
    /* renamed from: d */
    final String f12017d;
    /* renamed from: e */
    final Map<String, String> f12018e;
    /* renamed from: f */
    final C1771b f12019f;

    /* compiled from: ServiceStubInvocation */
    /* renamed from: com.beastbikes.android.sphere.restful.f$a */
    private static class C2541a extends HttpEntityWrapper {
        /* renamed from: a */
        InputStream f12008a;
        /* renamed from: b */
        PushbackInputStream f12009b;
        /* renamed from: c */
        GZIPInputStream f12010c;

        public C2541a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public InputStream getContent() throws IOException {
            this.f12008a = this.wrappedEntity.getContent();
            this.f12009b = new PushbackInputStream(this.f12008a, 2);
            if (!C2544f.m12738b(this.f12009b)) {
                return this.f12009b;
            }
            this.f12010c = new GZIPInputStream(this.f12009b);
            return this.f12010c;
        }

        public long getContentLength() {
            return this.wrappedEntity == null ? 0 : this.wrappedEntity.getContentLength();
        }

        public void consumeContent() throws IOException {
            C2541a.m12731a(this.f12008a);
            C2541a.m12731a(this.f12009b);
            C2541a.m12731a(this.f12010c);
            super.consumeContent();
        }

        /* renamed from: a */
        public static void m12731a(InputStream inputStream) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    C2544f.f12013g.error("Cannot close input stream " + e);
                }
            }
        }
    }

    /* compiled from: ServiceStubInvocation */
    /* renamed from: com.beastbikes.android.sphere.restful.f$b */
    private static class C2543b extends SSLSocketFactory {
        /* renamed from: a */
        SSLContext f12012a = SSLContext.getInstance("TLS");

        /* compiled from: ServiceStubInvocation */
        /* renamed from: com.beastbikes.android.sphere.restful.f$b$1 */
        class C25421 implements X509TrustManager {
            /* renamed from: a */
            final /* synthetic */ C2543b f12011a;

            C25421(C2543b c2543b) {
                this.f12011a = c2543b;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        public C2543b(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            C25421 c25421 = new C25421(this);
            this.f12012a.init(null, new TrustManager[]{c25421}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.f12012a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.f12012a.getSocketFactory().createSocket();
        }
    }

    C2544f(Context context, Class<?> cls, Method method, String str, Map<String, String> map, C1771b c1771b) {
        this.f12014a = context;
        this.f12015b = cls;
        this.f12016c = method;
        this.f12017d = str;
        if (map == null) {
            map = Collections.emptyMap();
        }
        this.f12018e = map;
        this.f12019f = c1771b;
    }

    /* renamed from: a */
    public Object mo3522a(Object... objArr) throws InvocationException {
        return m12732a(C2544f.m12737b(), objArr);
    }

    /* renamed from: a */
    private Object m12732a(HttpClient httpClient, Object... objArr) throws InvocationException {
        String a;
        String str;
        String a2;
        if (!"signIn".equals(this.f12016c.getName())) {
            f12013g.info("ServiceStubInvocation request Invoking " + this.f12015b.getName() + "#" + this.f12016c.getName() + " " + Arrays.toString(objArr));
        }
        String str2 = "";
        if (this.f12015b.isAnnotationPresent(C1450g.class)) {
            a = ((C1450g) this.f12015b.getAnnotation(C1450g.class)).a();
        } else {
            a = str2;
        }
        Object obj = null;
        if (this.f12016c.isAnnotationPresent(C1447d.class)) {
            str = "POST";
            a2 = ((C1447d) this.f12016c.getAnnotation(C1447d.class)).a();
        } else if (this.f12016c.isAnnotationPresent(C1448e.class)) {
            str = "PUT";
            a2 = ((C1448e) this.f12016c.getAnnotation(C1448e.class)).a();
        } else if (this.f12016c.isAnnotationPresent(C1445b.class)) {
            str = "DELETE";
            a2 = ((C1445b) this.f12016c.getAnnotation(C1445b.class)).a();
        } else if (this.f12016c.isAnnotationPresent(C1446c.class)) {
            str = "GET";
            a2 = ((C1446c) this.f12016c.getAnnotation(C1446c.class)).a();
            String b = ((C1446c) this.f12016c.getAnnotation(C1446c.class)).b();
        } else {
            throw new UnsupportedOperationException(this.f12016c.getName() + " does not annotated by any HTTP method");
        }
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        List<NameValuePair> arrayList3 = new ArrayList();
        Iterable arrayList4 = new ArrayList();
        Annotation[][] parameterAnnotations = this.f12016c.getParameterAnnotations();
        Map treeMap = new TreeMap();
        if (parameterAnnotations != null && parameterAnnotations.length > 0) {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                String valueOf = String.valueOf(objArr[i]);
                Annotation[] annotationArr = parameterAnnotations[i];
                if (!(TextUtils.isEmpty(valueOf) || "null".equals(valueOf) || annotationArr == null || annotationArr.length <= 0)) {
                    for (Annotation annotation : annotationArr) {
                        Class annotationType = annotation.annotationType();
                        if (C1452i.class.equals(annotationType)) {
                            arrayList4.add(new BasicNameValuePair(((C1452i) annotation).a(), valueOf));
                        } else if (C1444a.class.equals(annotationType)) {
                            str2 = ((C1444a) annotation).a();
                            arrayList.add(new BasicNameValuePair(str2, valueOf));
                            treeMap.put(str2, valueOf);
                        } else if (C1449f.class.equals(annotationType)) {
                            arrayList2.add(new BasicNameValuePair(((C1449f) annotation).a(), valueOf));
                        } else if (C1451h.class.equals(annotationType)) {
                            arrayList3.add(new BasicNameValuePair(((C1451h) annotation).a(), valueOf));
                        }
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (arrayList4.size() > 0) {
            try {
                StringBuilder append = stringBuilder.append(CallerData.NA);
                if (TextUtils.isEmpty(obj)) {
                    str2 = C2544f.m12734a(arrayList4);
                } else {
                    str2 = EntityUtils.toString(new UrlEncodedFormEntity(arrayList4, obj));
                }
                append.append(str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (arrayList3.size() > 0) {
            for (NameValuePair nameValuePair : arrayList3) {
                a2 = a2.replaceAll("\\{" + nameValuePair.getName() + "\\}", nameValuePair.getValue());
            }
        }
        str2 = this.f12017d + a + a2 + stringBuilder.toString();
        C2540c c2540c = new C2540c(str2, str);
        f12013g.debug(c2540c.toString());
        try {
            HttpUriRequest httpPost;
            URI uri = new URI(str2);
            if (this.f12016c.isAnnotationPresent(C1447d.class)) {
                httpPost = new HttpPost(uri);
            } else if (this.f12016c.isAnnotationPresent(C1448e.class)) {
                httpPost = new HttpPut(uri);
            } else if (this.f12016c.isAnnotationPresent(C1445b.class)) {
                httpPost = new HttpDelete(uri);
            } else {
                httpPost = new HttpGet(uri);
            }
            NetworkInfo a3 = C2799c.m13753a(this.f12014a);
            Object a4;
            if (a3 == null || !a3.isConnected()) {
                a4 = C2539a.m12728a(this.f12014a).m12729a(a2, treeMap);
                try {
                    if (TextUtils.isEmpty(a4)) {
                        a4 = new JSONObject();
                    } else {
                        a4 = (JSONObject) new JSONTokener(a4).nextValue();
                    }
                    a4.put(AVStatus.MESSAGE_TAG, this.f12014a.getString(this.f12014a.getResources().getIdentifier("network_not_awesome", "string", this.f12014a.getPackageName())));
                    return a4;
                } catch (Exception e2) {
                    throw new InvocationException(c2540c);
                }
            }
            try {
                httpPost.setHeader("User-Agent", C2544f.m12733a(this.f12014a));
                httpPost.setHeader("Accept-Language", Locale.getDefault().getLanguage());
                httpPost.setHeader("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                for (Entry entry : this.f12018e.entrySet()) {
                    httpPost.setHeader((String) entry.getKey(), (String) entry.getValue());
                }
                if (httpPost instanceof HttpEntityEnclosingRequest) {
                    int i2;
                    MultipartEntityBuilder create = MultipartEntityBuilder.create();
                    for (i2 = 0; i2 < arrayList.size(); i2++) {
                        create.addPart(((NameValuePair) arrayList.get(i2)).getName(), new StringBody(((NameValuePair) arrayList.get(i2)).getValue(), ContentType.create(HTTP.PLAIN_TEXT_TYPE, Consts.UTF_8)));
                    }
                    for (i2 = 0; i2 < arrayList2.size(); i2++) {
                        create.addPart(((NameValuePair) arrayList2.get(i2)).getName(), new FileBody(new File(((NameValuePair) arrayList2.get(i2)).getValue())));
                    }
                    ((HttpEntityEnclosingRequest) httpPost).setEntity(create.build());
                }
                HttpResponse execute = httpClient.execute(httpPost);
                if (execute == null) {
                    throw new InvocationException(c2540c);
                }
                StatusLine statusLine = execute.getStatusLine();
                if (statusLine == null) {
                    throw new InvocationException(c2540c);
                }
                switch (statusLine.getStatusCode()) {
                    case 200:
                        HttpEntity entity = execute.getEntity();
                        if (entity == null) {
                            throw new InvocationException(c2540c, statusLine);
                        }
                        Header contentEncoding = entity.getContentEncoding();
                        if (contentEncoding != null) {
                            HeaderElement[] elements = contentEncoding.getElements();
                            int length = elements.length;
                            int i3 = 0;
                            while (i3 < length) {
                                if (elements[i3].getName().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                                    execute.setEntity(new C2541a(entity));
                                } else {
                                    i3++;
                                }
                            }
                        }
                        try {
                            str2 = EntityUtils.toString(execute.getEntity(), "UTF-8");
                            C2539a.m12728a(this.f12014a).m12730a(a2, treeMap, str2);
                            a4 = new JSONTokener(str2).nextValue();
                            if (a4 == null) {
                                return null;
                            }
                            JSONObject jSONObject = (JSONObject) a4;
                            f12013g.info("ServiceStubInvocation request response result = " + jSONObject.toString());
                            if (jSONObject.optInt("code") != 1002 || this.f12019f == null) {
                                return jSONObject;
                            }
                            this.f12019f.mo3241a();
                            jSONObject.put(AVStatus.MESSAGE_TAG, "");
                            return jSONObject;
                        } catch (Throwable e3) {
                            throw new InvocationException(c2540c, statusLine, e3);
                        }
                    default:
                        f12013g.debug(statusLine.toString());
                        throw new InvocationException(c2540c, statusLine);
                }
            } catch (Throwable e32) {
                throw new InvocationException(c2540c, null, e32);
            }
        } catch (Throwable e322) {
            throw new InvocationException(c2540c, null, e322);
        }
    }

    /* renamed from: a */
    static String m12733a(Context context) {
        String str = "Android/" + VERSION.RELEASE;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            str = str + " " + packageName + "/" + packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: b */
    private static HttpClient m12737b() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory c2543b = new C2543b(instance);
            c2543b.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(C0845b.f2060a, c2543b, WebSocket.DEFAULT_WSS_PORT));
            ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, CoreConstants.MILLIS_IN_ONE_MINUTE);
            HttpConnectionParams.setSoTimeout(basicHttpParams, CoreConstants.MILLIS_IN_ONE_MINUTE);
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    /* renamed from: b */
    private static boolean m12738b(PushbackInputStream pushbackInputStream) throws IOException {
        boolean z = true;
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int i = 0;
        while (i < 2) {
            try {
                int read = pushbackInputStream.read(bArr, i, 2 - i);
                if (read < 0) {
                    return false;
                }
                i += read;
            } finally {
                pushbackInputStream.unread(bArr, 0, i);
            }
        }
        pushbackInputStream.unread(bArr, 0, i);
        if (35615 != ((bArr[0] & 255) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    private static String m12734a(Iterable<? extends NameValuePair> iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String name = nameValuePair.getName();
            String value = nameValuePair.getValue();
            if (stringBuilder.length() > 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append(name);
            if (value != null) {
                stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }
}
