package com.beastbikes.framework.android.p088g;

import com.beastbikes.framework.android.ApplicationContext;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: FileUtils */
/* renamed from: com.beastbikes.framework.android.g.e */
public final class C2802e {
    /* renamed from: a */
    static Logger f13021a = LoggerFactory.getLogger(C2802e.class);
    /* renamed from: b */
    private static ConcurrentHashMap<String, ReentrantReadWriteLock> f13022b = new ConcurrentHashMap();

    /* renamed from: a */
    public static boolean m13762a(File file, boolean z) {
        if (!z || !file.isDirectory()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return false;
        }
        boolean z2 = true;
        for (File a : listFiles) {
            if (C2802e.m13762a(a, z) && r2) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (file.delete() && r2) {
            return true;
        }
        return false;
    }

    private C2802e() {
    }

    /* renamed from: a */
    public static byte[] m13763a(File file) {
        Throwable e;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null) {
            f13021a.error("null file object.");
            return null;
        } else if (file.exists() && file.isFile()) {
            ReadLock readLock = C2802e.m13760a(file.getAbsolutePath()).readLock();
            readLock.lock();
            try {
                byte[] bArr = new byte[((int) file.length())];
                int i = 0;
                Closeable bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
                while (i < bArr.length) {
                    try {
                        int read = bufferedInputStream.read(bArr, i, bArr.length - i);
                        if (read > 0) {
                            i += read;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        closeable = bufferedInputStream;
                    } catch (Throwable th) {
                        e = th;
                        closeable2 = bufferedInputStream;
                    }
                }
                C2802e.m13761a(bufferedInputStream);
                readLock.unlock();
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return bArr;
            } catch (IOException e4) {
                e = e4;
                closeable = null;
                try {
                    f13021a.error("Exception during file read", e);
                    C2802e.m13761a(closeable);
                    readLock.unlock();
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    e = th2;
                    closeable2 = closeable;
                    C2802e.m13761a(closeable2);
                    readLock.unlock();
                    if (closeable2 != null) {
                        try {
                            closeable2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (Throwable th3) {
                e = th3;
                C2802e.m13761a(closeable2);
                readLock.unlock();
                if (closeable2 != null) {
                    closeable2.close();
                }
                throw e;
            }
        } else {
            f13021a.debug("not file object", new FileNotFoundException());
            return null;
        }
    }

    /* renamed from: a */
    private static ReentrantReadWriteLock m13760a(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = (ReentrantReadWriteLock) f13022b.get(str);
        if (reentrantReadWriteLock != null) {
            return reentrantReadWriteLock;
        }
        ReentrantReadWriteLock reentrantReadWriteLock2 = new ReentrantReadWriteLock();
        reentrantReadWriteLock = (ReentrantReadWriteLock) f13022b.putIfAbsent(str, reentrantReadWriteLock2);
        return reentrantReadWriteLock != null ? reentrantReadWriteLock : reentrantReadWriteLock2;
    }

    /* renamed from: a */
    public static void m13761a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                f13021a.error(e.toString());
            }
        }
    }

    /* renamed from: a */
    public static File m13759a() {
        if (ApplicationContext.j() != null) {
            return ApplicationContext.j().getDir("Paas", 0);
        }
        throw new IllegalStateException("applicationContext is null, Please call AVOSCloud.initialize first");
    }
}
