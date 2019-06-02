package com.mapbox.services.commons;

import com.mapbox.services.Constants;
import com.mapbox.services.commons.utils.TextUtils;
import java.io.IOException;
import java.util.Locale;
import okhttp3.C5596r;
import okhttp3.C5614u;
import okhttp3.C5614u.C5613a;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MapboxService<T> {
    private boolean enableDebug = false;

    public abstract void cancelCall();

    public abstract Call<T> cloneCall();

    public abstract void enqueueCall(Callback<T> callback);

    public abstract Response<T> executeCall() throws IOException;

    public boolean isEnableDebug() {
        return this.enableDebug;
    }

    public void setEnableDebug(boolean z) {
        this.enableDebug = z;
    }

    public C5614u getOkHttpClient() {
        if (!isEnableDebug()) {
            return new C5614u();
        }
        C5596r httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.a(Level.BASIC);
        C5613a c5613a = new C5613a();
        c5613a.a(httpLoggingInterceptor);
        return c5613a.b();
    }

    public static String getHeaderUserAgent() {
        try {
            CharSequence property = System.getProperty("os.name");
            CharSequence property2 = System.getProperty("os.version");
            CharSequence property3 = System.getProperty("os.arch");
            if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2) || TextUtils.isEmpty(property3)) {
                return Constants.HEADER_USER_AGENT;
            }
            String format = String.format(Locale.US, "%s/%s (%s)", new Object[]{property, property2, property3});
            return String.format(Locale.US, "%s %s", new Object[]{Constants.HEADER_USER_AGENT, format});
        } catch (Exception e) {
            return Constants.HEADER_USER_AGENT;
        }
    }
}
