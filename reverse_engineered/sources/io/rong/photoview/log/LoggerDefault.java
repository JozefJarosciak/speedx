package io.rong.photoview.log;

import android.util.Log;

public class LoggerDefault implements Logger {
    /* renamed from: v */
    public int mo6653v(String str, String str2) {
        return Log.v(str, str2);
    }

    /* renamed from: v */
    public int mo6654v(String str, String str2, Throwable th) {
        return Log.v(str, str2, th);
    }

    /* renamed from: d */
    public int mo6647d(String str, String str2) {
        return Log.d(str, str2);
    }

    /* renamed from: d */
    public int mo6648d(String str, String str2, Throwable th) {
        return Log.d(str, str2, th);
    }

    /* renamed from: i */
    public int mo6651i(String str, String str2) {
        return Log.i(str, str2);
    }

    /* renamed from: i */
    public int mo6652i(String str, String str2, Throwable th) {
        return Log.i(str, str2, th);
    }

    /* renamed from: w */
    public int mo6655w(String str, String str2) {
        return Log.w(str, str2);
    }

    /* renamed from: w */
    public int mo6656w(String str, String str2, Throwable th) {
        return Log.w(str, str2, th);
    }

    /* renamed from: e */
    public int mo6649e(String str, String str2) {
        return Log.e(str, str2);
    }

    /* renamed from: e */
    public int mo6650e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }
}
