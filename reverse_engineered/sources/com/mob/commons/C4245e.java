package com.mob.commons;

import com.mob.tools.MobLog;
import com.mob.tools.utils.FileLocker;
import java.io.File;

/* compiled from: Locks */
/* renamed from: com.mob.commons.e */
public class C4245e {
    /* renamed from: a */
    public static final void m16870a(File file, Runnable runnable) {
        C4245e.m16871a(file, true, runnable);
    }

    /* renamed from: a */
    public static final void m16871a(File file, boolean z, Runnable runnable) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileLocker fileLocker = new FileLocker();
            fileLocker.setLockFile(file.getAbsolutePath());
            if (fileLocker.lock(z)) {
                runnable.run();
                fileLocker.release();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }
}
