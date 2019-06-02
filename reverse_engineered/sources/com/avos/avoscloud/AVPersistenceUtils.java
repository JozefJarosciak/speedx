package com.avos.avoscloud;

import android.content.SharedPreferences.Editor;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AVPersistenceUtils {
    private static ConcurrentHashMap<String, ReentrantReadWriteLock> fileLocks = new ConcurrentHashMap();
    private static AVPersistenceUtils instance = null;

    private static ReentrantReadWriteLock getLock(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = (ReentrantReadWriteLock) fileLocks.get(str);
        if (reentrantReadWriteLock != null) {
            return reentrantReadWriteLock;
        }
        ReentrantReadWriteLock reentrantReadWriteLock2 = new ReentrantReadWriteLock();
        reentrantReadWriteLock = (ReentrantReadWriteLock) fileLocks.putIfAbsent(str, reentrantReadWriteLock2);
        return reentrantReadWriteLock != null ? reentrantReadWriteLock : reentrantReadWriteLock2;
    }

    public static void removeLock(String str) {
        fileLocks.remove(str);
    }

    private AVPersistenceUtils() {
    }

    public static synchronized AVPersistenceUtils sharedInstance() {
        AVPersistenceUtils aVPersistenceUtils;
        synchronized (AVPersistenceUtils.class) {
            if (instance == null) {
                instance = new AVPersistenceUtils();
            }
            aVPersistenceUtils = instance;
        }
        return aVPersistenceUtils;
    }

    public static File getPaasDocumentDir() {
        if (AVOSCloud.applicationContext != null) {
            return AVOSCloud.applicationContext.getDir("Paas", 0);
        }
        throw new IllegalStateException("applicationContext is null, Please call AVOSCloud.initialize first");
    }

    public static File getCacheDir() {
        if (AVOSCloud.applicationContext != null) {
            return AVOSCloud.applicationContext.getCacheDir();
        }
        throw new IllegalStateException("applicationContext is null, Please call AVOSCloud.initialize first");
    }

    public static File getCommandCacheDir() {
        if (AVOSCloud.applicationContext == null) {
            throw new IllegalStateException("applicationContext is null, Please call AVOSCloud.initialize first");
        }
        File file = new File(getCacheDir(), "CommandCache");
        file.mkdirs();
        return file;
    }

    public static File getAnalysisCacheDir() {
        if (AVOSCloud.applicationContext == null) {
            throw new IllegalStateException("applicationContext is null, Please call AVOSCloud.initialize first");
        }
        File file = new File(getCacheDir(), "Analysis");
        file.mkdirs();
        return file;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.m3514d(e.toString());
            }
        }
    }

    private static File getFile(String str, String str2) {
        if (AVUtils.isBlankString(str)) {
            return new File(getPaasDocumentDir(), str2);
        }
        File file = new File(getPaasDocumentDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, str2);
    }

    public void saveToDocumentDir(String str, String str2) {
        saveToDocumentDir(str, null, str2);
    }

    public void saveToDocumentDir(String str, String str2, String str3) {
        saveContentToFile(str, getFile(str2, str3));
    }

    public static boolean saveContentToFile(String str, File file) {
        try {
            return saveContentToFile(str.getBytes("utf-8"), file);
        } catch (UnsupportedEncodingException e) {
            log.m3514d(e.toString());
            return false;
        }
    }

    public static boolean saveContentToFile(byte[] bArr, File file) {
        Closeable fileOutputStream;
        Exception e;
        Throwable th;
        Lock writeLock = getLock(file.getAbsolutePath()).writeLock();
        if (!writeLock.tryLock()) {
            return true;
        }
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                fileOutputStream.write(bArr);
                if (fileOutputStream != null) {
                    closeQuietly(fileOutputStream);
                }
                writeLock.unlock();
                return true;
            } catch (Exception e2) {
                e = e2;
                try {
                    log.m3514d(e.toString());
                    if (fileOutputStream != null) {
                        closeQuietly(fileOutputStream);
                    }
                    writeLock.unlock();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        closeQuietly(fileOutputStream);
                    }
                    writeLock.unlock();
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
            log.m3514d(e.toString());
            if (fileOutputStream != null) {
                closeQuietly(fileOutputStream);
            }
            writeLock.unlock();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                closeQuietly(fileOutputStream);
            }
            writeLock.unlock();
            throw th;
        }
    }

    public String getFromDocumentDir(String str, String str2) {
        return readContentFromFile(getFile(str, str2));
    }

    public String getFromDocumentDir(String str) {
        return getFromDocumentDir(null, str);
    }

    public static String readContentFromFile(File file) {
        byte[] readContentBytesFromFile = readContentBytesFromFile(file);
        if (readContentBytesFromFile == null || readContentBytesFromFile.length == 0) {
            return "";
        }
        return new String(readContentBytesFromFile);
    }

    public static byte[] readContentBytesFromFile(File file) {
        Exception e;
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = null;
        if (file == null) {
            avlog.m3507e("null file object.");
            return null;
        } else if (file.exists() && file.isFile()) {
            Lock readLock = getLock(file.getAbsolutePath()).readLock();
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
                    } catch (Throwable th2) {
                        th = th2;
                        closeable2 = bufferedInputStream;
                    }
                }
                closeQuietly(bufferedInputStream);
                readLock.unlock();
                return bArr;
            } catch (IOException e3) {
                e = e3;
                closeable = null;
                try {
                    if (AVOSCloud.isDebugLogEnabled()) {
                        log.m3520e("Exception during file read", e);
                    }
                    closeQuietly(closeable);
                    readLock.unlock();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    closeable2 = closeable;
                    closeQuietly(closeable2);
                    readLock.unlock();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                closeQuietly(closeable2);
                readLock.unlock();
                throw th;
            }
        } else {
            if (AVOSCloud.isDebugLogEnabled()) {
                log.m3515d("not file object", new FileNotFoundException());
            }
            return null;
        }
    }

    public void savePersistentSettingBoolean(String str, String str2, Boolean bool) {
        if (AVOSCloud.applicationContext == null) {
            log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
            return;
        }
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, bool.booleanValue());
        edit.commit();
    }

    public boolean getPersistentSettingBoolean(String str, String str2) {
        return getPersistentSettingBoolean(str, str2, Boolean.valueOf(false));
    }

    public boolean getPersistentSettingBoolean(String str, String str2, Boolean bool) {
        if (AVOSCloud.applicationContext != null) {
            return AVOSCloud.applicationContext.getSharedPreferences(str, 0).getBoolean(str2, bool.booleanValue());
        }
        log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
        return bool.booleanValue();
    }

    public void savePersistentSettingInteger(String str, String str2, Integer num) {
        if (AVOSCloud.applicationContext == null) {
            log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
            return;
        }
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, num.intValue());
        edit.commit();
    }

    public Integer getPersistentSettingInteger(String str, String str2, Integer num) {
        if (AVOSCloud.applicationContext != null) {
            return Integer.valueOf(AVOSCloud.applicationContext.getSharedPreferences(str, 0).getInt(str2, num.intValue()));
        }
        log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
        return num;
    }

    public void savePersistentSettingString(String str, String str2, String str3) {
        if (AVOSCloud.applicationContext == null) {
            log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
            return;
        }
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    public String getPersistentSettingString(String str, String str2, String str3) {
        if (AVOSCloud.applicationContext != null) {
            return AVOSCloud.applicationContext.getSharedPreferences(str, 0).getString(str2, str3);
        }
        log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
        return str3;
    }

    public void removePersistentSettingString(String str, String str2) {
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
    }

    public String removePersistentSettingString(String str, String str2, String str3) {
        String persistentSettingString = getPersistentSettingString(str, str2, str3);
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
        return persistentSettingString;
    }

    public void removeKeyZonePersistentSettings(String str) {
        Editor edit = AVOSCloud.applicationContext.getSharedPreferences(str, 0).edit();
        edit.clear();
        edit.commit();
    }
}
