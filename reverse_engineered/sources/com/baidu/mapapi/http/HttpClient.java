package com.baidu.mapapi.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.Logger;
import com.baidu.platform.comapi.util.C1281e;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;

public class HttpClient {
    /* renamed from: a */
    HttpURLConnection f2794a;
    /* renamed from: b */
    private String f2795b = null;
    /* renamed from: c */
    private String f2796c = null;
    /* renamed from: d */
    private int f2797d;
    /* renamed from: e */
    private int f2798e;
    /* renamed from: f */
    private String f2799f;
    /* renamed from: g */
    private ProtoResultCallback f2800g;

    public enum HttpStateError {
        NO_ERROR,
        NETWORK_ERROR,
        INNER_ERROR,
        REQUEST_ERROR,
        SERVER_ERROR
    }

    public static abstract class ProtoResultCallback {
        public abstract void onFailed(HttpStateError httpStateError);

        public abstract void onSuccess(String str);
    }

    public HttpClient(String str, ProtoResultCallback protoResultCallback) {
        this.f2799f = str;
        this.f2800g = protoResultCallback;
    }

    /* renamed from: a */
    private HttpURLConnection m4043a() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f2795b).openConnection();
            httpURLConnection.setRequestMethod(this.f2799f);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.f2797d);
            httpURLConnection.setReadTimeout(this.f2798e);
            return httpURLConnection;
        } catch (Exception e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
            } else {
                Logger.logW("HttpClient", e.getMessage());
            }
            return null;
        }
    }

    public static String getAuthToken() {
        return C1281e.f3919z;
    }

    public static String getPhoneInfo() {
        return C1281e.m4852c();
    }

    protected boolean checkNetwork() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) JNIInitializer.getCachedContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Exception e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
            } else {
                Logger.logW("HttpClient", e.getMessage());
            }
            e.printStackTrace();
            return false;
        }
    }

    protected void request(String str) {
        InputStream inputStream;
        Exception e;
        Throwable th;
        BufferedReader bufferedReader = null;
        this.f2795b = str;
        if (checkNetwork()) {
            this.f2794a = m4043a();
            if (this.f2794a == null) {
                this.f2800g.onFailed(HttpStateError.INNER_ERROR);
                return;
            } else if (TextUtils.isEmpty(this.f2795b)) {
                this.f2800g.onFailed(HttpStateError.REQUEST_ERROR);
                return;
            } else {
                BufferedReader bufferedReader2 = null;
                try {
                    this.f2794a.connect();
                    try {
                        int responseCode = this.f2794a.getResponseCode();
                        if (200 == responseCode) {
                            inputStream = this.f2794a.getInputStream();
                            try {
                                bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                try {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        int read = bufferedReader2.read();
                                        if (read == -1) {
                                            break;
                                        }
                                        stringBuffer.append((char) read);
                                    }
                                    this.f2796c = stringBuffer.toString();
                                    if (!(inputStream == null || bufferedReader2 == null)) {
                                        bufferedReader2.close();
                                        inputStream.close();
                                    }
                                    if (this.f2794a != null) {
                                        this.f2794a.disconnect();
                                    }
                                    this.f2800g.onSuccess(this.f2796c);
                                    return;
                                } catch (Exception e2) {
                                    e = e2;
                                    bufferedReader = bufferedReader2;
                                    try {
                                        if (Logger.debugEnable()) {
                                            e.printStackTrace();
                                        } else {
                                            Logger.logW("HttpClient", e.getMessage());
                                        }
                                        this.f2800g.onFailed(HttpStateError.INNER_ERROR);
                                        bufferedReader.close();
                                        inputStream.close();
                                        if (this.f2794a == null) {
                                            this.f2794a.disconnect();
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!(inputStream == null || bufferedReader == null)) {
                                            bufferedReader.close();
                                            inputStream.close();
                                        }
                                        if (this.f2794a != null) {
                                            this.f2794a.disconnect();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    bufferedReader = bufferedReader2;
                                    bufferedReader.close();
                                    inputStream.close();
                                    if (this.f2794a != null) {
                                        this.f2794a.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                if (Logger.debugEnable()) {
                                    Logger.logW("HttpClient", e.getMessage());
                                } else {
                                    e.printStackTrace();
                                }
                                this.f2800g.onFailed(HttpStateError.INNER_ERROR);
                                bufferedReader.close();
                                inputStream.close();
                                if (this.f2794a == null) {
                                    this.f2794a.disconnect();
                                    return;
                                }
                                return;
                            }
                        }
                        HttpStateError httpStateError = HttpStateError.NO_ERROR;
                        httpStateError = responseCode >= 500 ? HttpStateError.SERVER_ERROR : responseCode >= HttpStatus.SC_BAD_REQUEST ? HttpStateError.REQUEST_ERROR : HttpStateError.INNER_ERROR;
                        if (Logger.debugEnable()) {
                            inputStream = this.f2794a.getErrorStream();
                            Logger.logW("HttpClient", inputStream.toString());
                        } else {
                            Logger.logW("HttpClient", "Get response from server failed, http response code=" + responseCode + ", error=" + httpStateError);
                            inputStream = null;
                        }
                        this.f2800g.onFailed(httpStateError);
                        if (!(inputStream == null || null == null)) {
                            bufferedReader2.close();
                            inputStream.close();
                        }
                        if (this.f2794a != null) {
                            this.f2794a.disconnect();
                            return;
                        }
                        return;
                    } catch (Exception e4) {
                        e = e4;
                        inputStream = null;
                        if (Logger.debugEnable()) {
                            e.printStackTrace();
                        } else {
                            Logger.logW("HttpClient", e.getMessage());
                        }
                        this.f2800g.onFailed(HttpStateError.INNER_ERROR);
                        if (!(inputStream == null || bufferedReader == null)) {
                            bufferedReader.close();
                            inputStream.close();
                        }
                        if (this.f2794a == null) {
                            this.f2794a.disconnect();
                            return;
                        }
                        return;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                        bufferedReader.close();
                        inputStream.close();
                        if (this.f2794a != null) {
                            this.f2794a.disconnect();
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    if (Logger.debugEnable()) {
                        e5.printStackTrace();
                    } else {
                        Logger.logW("HttpClient", e5.getMessage());
                    }
                    this.f2800g.onFailed(HttpStateError.INNER_ERROR);
                    return;
                }
            }
        }
        this.f2800g.onFailed(HttpStateError.NETWORK_ERROR);
    }

    public void setMaxTimeOut(int i) {
        this.f2797d = i;
    }

    public void setReadTimeOut(int i) {
        this.f2798e = i;
    }
}
