package com.pingplusplus.android;

import android.os.AsyncTask;

/* renamed from: com.pingplusplus.android.o */
class C4298o extends AsyncTask<C4297n, Void, C4294k> {
    /* renamed from: a */
    final /* synthetic */ C4295l f14988a;

    private C4298o(C4295l c4295l) {
        this.f14988a = c4295l;
    }

    /* renamed from: a */
    protected C4294k m17010a(C4297n... c4297nArr) {
        return C4295l.m16998a(c4297nArr[0]);
    }

    /* renamed from: a */
    protected void m17011a(C4294k c4294k) {
        if (c4294k != null) {
            PingppLog.m16961a("status code: " + c4294k.f14973a);
            PingppLog.m16961a(c4294k.f14974b);
            if (c4294k.f14976d != null) {
                c4294k.f14976d.m16992a(c4294k);
                return;
            }
            return;
        }
        PingppLog.m16962d("response is null");
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m17010a((C4297n[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m17011a((C4294k) obj);
    }
}
