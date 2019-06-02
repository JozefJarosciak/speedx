package com.beastbikes.android.main;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

class MainActivity$a extends Handler {
    /* renamed from: a */
    private WeakReference<MainActivity> f8373a;

    public MainActivity$a(MainActivity mainActivity) {
        this.f8373a = new WeakReference(mainActivity);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        MainActivity mainActivity = (MainActivity) this.f8373a.get();
        if (mainActivity != null) {
            MainActivity.a(mainActivity);
        }
    }
}
