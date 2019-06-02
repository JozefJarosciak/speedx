package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVOSCloud;
import com.squareup.picasso.Downloader.ResponseException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionDownloader implements Downloader {
    private static final ThreadLocal<StringBuilder> CACHE_HEADER_BUILDER = new C43911();
    private static final String FORCE_CACHE = "only-if-cached,max-age=2147483647";
    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private static final Object lock = new Object();
    private final Context context;

    /* renamed from: com.squareup.picasso.UrlConnectionDownloader$1 */
    static class C43911 extends ThreadLocal<StringBuilder> {
        C43911() {
        }

        protected StringBuilder initialValue() {
            return new StringBuilder();
        }
    }

    private static class ResponseCacheIcs {
        private ResponseCacheIcs() {
        }

        static Object install(Context context) throws IOException {
            File createDefaultCacheDir = Utils.createDefaultCacheDir(context);
            Object installed = HttpResponseCache.getInstalled();
            if (installed == null) {
                return HttpResponseCache.install(createDefaultCacheDir, Utils.calculateDiskCacheSize(createDefaultCacheDir));
            }
            return installed;
        }

        static void close(Object obj) {
            try {
                ((HttpResponseCache) obj).close();
            } catch (IOException e) {
            }
        }
    }

    public UrlConnectionDownloader(Context context) {
        this.context = context.getApplicationContext();
    }

    protected HttpURLConnection openConnection(Uri uri) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(AVOSCloud.DEFAULT_NETWORK_TIMEOUT);
        httpURLConnection.setReadTimeout(20000);
        return httpURLConnection;
    }

    public Downloader$Response load(Uri uri, int i) throws IOException {
        if (VERSION.SDK_INT >= 14) {
            installCacheIfNeeded(this.context);
        }
        HttpURLConnection openConnection = openConnection(uri);
        openConnection.setUseCaches(true);
        if (i != 0) {
            String str;
            if (NetworkPolicy.isOfflineOnly(i)) {
                str = FORCE_CACHE;
            } else {
                StringBuilder stringBuilder = (StringBuilder) CACHE_HEADER_BUILDER.get();
                stringBuilder.setLength(0);
                if (!NetworkPolicy.shouldReadFromDiskCache(i)) {
                    stringBuilder.append("no-cache");
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(i)) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(CoreConstants.COMMA_CHAR);
                    }
                    stringBuilder.append("no-store");
                }
                str = stringBuilder.toString();
            }
            openConnection.setRequestProperty("Cache-Control", str);
        }
        int responseCode = openConnection.getResponseCode();
        if (responseCode >= 300) {
            openConnection.disconnect();
            throw new ResponseException(responseCode + " " + openConnection.getResponseMessage(), i, responseCode);
        }
        long headerFieldInt = (long) openConnection.getHeaderFieldInt("Content-Length", -1);
        return new Downloader$Response(openConnection.getInputStream(), Utils.parseResponseSourceHeader(openConnection.getHeaderField(RESPONSE_SOURCE)), headerFieldInt);
    }

    public void shutdown() {
        if (VERSION.SDK_INT >= 14 && cache != null) {
            ResponseCacheIcs.close(cache);
        }
    }

    private static void installCacheIfNeeded(Context context) {
        if (cache == null) {
            try {
                synchronized (lock) {
                    if (cache == null) {
                        cache = ResponseCacheIcs.install(context);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
