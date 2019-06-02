package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.alipay.sdk.util.C0880h;
import java.net.HttpURLConnection;
import java.util.List;

/* compiled from: GraphRequestAsyncTask */
/* renamed from: com.facebook.d */
public class C2984d extends AsyncTask<Void, Void, List<C2987f>> {
    /* renamed from: a */
    private static final String f13497a = C2984d.class.getCanonicalName();
    /* renamed from: b */
    private final HttpURLConnection f13498b;
    /* renamed from: c */
    private final C2986e f13499c;
    /* renamed from: d */
    private Exception f13500d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m14473a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m14474a((List) obj);
    }

    public C2984d(C2986e c2986e) {
        this(null, c2986e);
    }

    public C2984d(HttpURLConnection httpURLConnection, C2986e c2986e) {
        this.f13499c = c2986e;
        this.f13498b = httpURLConnection;
    }

    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.f13498b + ", requests: " + this.f13499c + C0880h.f2222d;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (C1472c.b()) {
            Log.d(f13497a, String.format("execute async task: %s", new Object[]{this}));
        }
        if (this.f13499c.m14485c() == null) {
            Handler handler;
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.f13499c.m14479a(handler);
        }
    }

    /* renamed from: a */
    protected void m14474a(List<C2987f> list) {
        super.onPostExecute(list);
        if (this.f13500d != null) {
            Log.d(f13497a, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.f13500d.getMessage()}));
        }
    }

    /* renamed from: a */
    protected List<C2987f> m14473a(Void... voidArr) {
        try {
            if (this.f13498b == null) {
                return this.f13499c.m14489g();
            }
            return GraphRequest.m14338a(this.f13498b, this.f13499c);
        } catch (Exception e) {
            this.f13500d = e;
            return null;
        }
    }
}
