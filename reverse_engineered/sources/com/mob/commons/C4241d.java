package com.mob.commons;

import android.content.Context;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C4275R;
import java.io.File;

/* compiled from: DeviceLevelTags */
/* renamed from: com.mob.commons.d */
public class C4241d {
    /* renamed from: a */
    public static synchronized boolean m16854a(Context context, String str) {
        boolean exists;
        synchronized (C4241d.class) {
            try {
                exists = new File(C4275R.getCacheRoot(context), str).exists();
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
                exists = true;
            }
        }
        return exists;
    }

    /* renamed from: b */
    public static synchronized void m16855b(Context context, String str) {
        synchronized (C4241d.class) {
            try {
                File file = new File(C4275R.getCacheRoot(context), str);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    /* renamed from: c */
    public static synchronized void m16856c(Context context, String str) {
        synchronized (C4241d.class) {
            try {
                File file = new File(C4275R.getCacheRoot(context), str);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }
}
