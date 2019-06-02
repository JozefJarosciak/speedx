package com.baidu.location.p041a;

import android.os.Environment;
import java.io.File;

/* renamed from: com.baidu.location.a.e */
class C1048e extends Thread {
    /* renamed from: a */
    final /* synthetic */ C1046c f2412a;

    C1048e(C1046c c1046c) {
        this.f2412a = c1046c;
    }

    public void run() {
        this.f2412a.m3695a(new File(Environment.getExternalStorageDirectory() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}
