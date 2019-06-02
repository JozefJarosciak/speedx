package com.facebook;

import java.io.File;
import java.util.concurrent.Callable;

/* compiled from: FacebookSdk */
class c$2 implements Callable<File> {
    c$2() {
    }

    public /* synthetic */ Object call() throws Exception {
        return m14470a();
    }

    /* renamed from: a */
    public File m14470a() throws Exception {
        return C1472c.n().getCacheDir();
    }
}
