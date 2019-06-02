package com.mapbox.mapboxsdk.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.classic.spi.CallerData;
import com.alipay.sdk.sys.C0869a;
import com.mapbox.mapboxsdk.BuildConfig;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLException;
import okhttp3.C5468e;
import okhttp3.C5469f;
import okhttp3.C5614u;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.HttpUrl;
import okhttp3.internal.C5586l;

class HTTPRequest implements C5469f {
    private static final int CONNECTION_ERROR = 0;
    private static final String LOG_TAG = HTTPRequest.class.getName();
    private static final int PERMANENT_ERROR = 2;
    private static final int TEMPORARY_ERROR = 1;
    private static C5614u mClient = new C5614u();
    private String USER_AGENT_STRING = null;
    private C5468e mCall;
    private ReentrantLock mLock = new ReentrantLock();
    private long mNativePtr = 0;
    private C5621w mRequest;

    private native void nativeOnFailure(int i, String str);

    private native void nativeOnResponse(int i, String str, String str2, String str3, String str4, String str5, String str6, byte[] bArr);

    private HTTPRequest(long j, String str, String str2, String str3) {
        this.mNativePtr = j;
        try {
            if (MapboxAccountManager.getInstance().isConnected().booleanValue()) {
                HttpUrl e = HttpUrl.e(str);
                String toLowerCase = e.f().toLowerCase(MapboxConstants.MAPBOX_LOCALE);
                if (toLowerCase.equals("mapbox.com") || toLowerCase.endsWith(".mapbox.com") || toLowerCase.equals("mapbox.cn") || toLowerCase.endsWith(".mapbox.cn")) {
                    String str4;
                    if (e.m() == 0) {
                        str4 = str + CallerData.NA;
                    } else {
                        str4 = str + C0869a.f2161b;
                    }
                    str = str4 + "events=true";
                }
                C5620a b = new C5620a().a(str).a(str.toLowerCase(MapboxConstants.MAPBOX_LOCALE)).b("User-Agent", getUserAgent());
                if (str2.length() > 0) {
                    b = b.b("If-None-Match", str2);
                } else if (str3.length() > 0) {
                    b = b.b("If-Modified-Since", str3);
                }
                this.mRequest = b.a();
                this.mCall = mClient.a(this.mRequest);
                this.mCall.a(this);
                return;
            }
            throw new NoRouteToHostException("No Internet connection available.");
        } catch (Exception e2) {
            onFailure(e2);
        }
    }

    public void cancel() {
        if (this.mCall != null) {
            this.mCall.c();
        }
        this.mLock.lock();
        this.mNativePtr = 0;
        this.mLock.unlock();
    }

    public void onResponse(C5468e c5468e, C5627y c5627y) throws IOException {
        if (c5627y.c()) {
            Log.v(LOG_TAG, String.format("[HTTP] Request was successful (code = %d).", new Object[]{Integer.valueOf(c5627y.b())}));
        } else {
            String d = !TextUtils.isEmpty(c5627y.d()) ? c5627y.d() : "No additional information";
            Log.d(LOG_TAG, String.format("[HTTP] Request with response code = %d: %s", new Object[]{Integer.valueOf(c5627y.b()), d}));
        }
        try {
            byte[] bytes = c5627y.g().bytes();
            this.mLock.lock();
            if (this.mNativePtr != 0) {
                nativeOnResponse(c5627y.b(), c5627y.a("ETag"), c5627y.a("Last-Modified"), c5627y.a("Cache-Control"), c5627y.a("Expires"), c5627y.a("Retry-After"), c5627y.a("x-rate-limit-reset"), bytes);
            }
            this.mLock.unlock();
        } catch (Exception e) {
            onFailure(e);
        } finally {
            c5627y.g().close();
        }
    }

    public void onFailure(C5468e c5468e, IOException iOException) {
        onFailure(iOException);
    }

    private void onFailure(Exception exception) {
        int i = 2;
        if ((exception instanceof NoRouteToHostException) || (exception instanceof UnknownHostException) || (exception instanceof SocketException) || (exception instanceof ProtocolException) || (exception instanceof SSLException)) {
            i = 0;
        } else if (exception instanceof InterruptedIOException) {
            i = 1;
        }
        String message = exception.getMessage() != null ? exception.getMessage() : "Error processing the request";
        if (i == 1) {
            Log.d(LOG_TAG, String.format(MapboxConstants.MAPBOX_LOCALE, "Request failed due to a temporary error: %s", new Object[]{message}));
        } else if (i == 0) {
            Log.i(LOG_TAG, String.format(MapboxConstants.MAPBOX_LOCALE, "Request failed due to a connection error: %s", new Object[]{message}));
        } else {
            Log.w(LOG_TAG, String.format(MapboxConstants.MAPBOX_LOCALE, "Request failed due to a permanent error: %s", new Object[]{message}));
        }
        this.mLock.lock();
        if (this.mNativePtr != 0) {
            nativeOnFailure(i, message);
        }
        this.mLock.unlock();
    }

    private String getUserAgent() {
        if (this.USER_AGENT_STRING != null) {
            return this.USER_AGENT_STRING;
        }
        String a = C5586l.a(String.format("%s %s (%s) Android/%s (%s)", new Object[]{getApplicationIdentifier(), BuildConfig.MAPBOX_VERSION_STRING, BuildConfig.GIT_REVISION_SHORT, Integer.valueOf(VERSION.SDK_INT), Build.CPU_ABI}));
        this.USER_AGENT_STRING = a;
        return a;
    }

    private String getApplicationIdentifier() {
        try {
            Context applicationContext = MapboxAccountManager.getInstance().getApplicationContext();
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            return String.format("%s/%s (%s)", new Object[]{applicationContext.getPackageName(), packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)});
        } catch (Exception e) {
            return "";
        }
    }
}
