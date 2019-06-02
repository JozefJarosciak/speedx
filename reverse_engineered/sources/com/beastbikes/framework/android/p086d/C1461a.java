package com.beastbikes.framework.android.p086d;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.ConnectException;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: DefaultUncaughtExceptionHandler */
/* renamed from: com.beastbikes.framework.android.d.a */
public class C1461a implements UncaughtExceptionHandler {
    /* renamed from: a */
    private static final Logger f6855a = LoggerFactory.getLogger(C1461a.class);
    /* renamed from: b */
    private UncaughtExceptionHandler f6856b = Thread.getDefaultUncaughtExceptionHandler();
    /* renamed from: c */
    private Context f6857c;

    public C1461a(Context context) {
        this.f6857c = context;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        f6855a.error(th.getMessage(), th);
        if (!(th instanceof ConnectException) && !(th instanceof UnknownHostException) && this.f6856b != null) {
            this.f6856b.uncaughtException(thread, th);
        }
    }
}
