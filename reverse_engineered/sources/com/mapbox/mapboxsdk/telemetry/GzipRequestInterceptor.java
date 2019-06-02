package com.mapbox.mapboxsdk.telemetry;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import okhttp3.C5596r;
import okhttp3.C5596r.C5572a;
import okhttp3.C5602x;
import okhttp3.C5608s;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okio.C5635d;
import okio.C5641j;
import okio.C5647m;

public final class GzipRequestInterceptor implements C5596r {
    private static final String TAG = "GzipRequestInterceptor";

    public C5627y intercept(C5572a c5572a) throws IOException {
        C5621w a = c5572a.a();
        if (a.d() == null || a.a("Content-Encoding") != null) {
            Log.d(TAG, "Not compressing");
            return c5572a.a(a);
        }
        Log.d(TAG, "Compressing");
        return c5572a.a(a.f().a("Content-Encoding", AsyncHttpClient.ENCODING_GZIP).a(a.b(), gzip(a.d())).a());
    }

    private C5602x gzip(final C5602x c5602x) {
        return new C5602x() {
            public C5608s contentType() {
                return c5602x.contentType();
            }

            public long contentLength() {
                return -1;
            }

            public void writeTo(C5635d c5635d) throws IOException {
                C5635d a = C5647m.a(new C5641j(c5635d));
                c5602x.writeTo(a);
                a.close();
            }
        };
    }
}
