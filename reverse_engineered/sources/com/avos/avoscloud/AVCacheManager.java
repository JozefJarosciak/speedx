package com.avos.avoscloud;

import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import java.io.File;

public class AVCacheManager {
    private static AVCacheManager instance = null;

    private static File keyValueCacheDir() {
        File file = new File(AVPersistenceUtils.getCacheDir(), "PaasKeyValueCache");
        file.mkdirs();
        return file;
    }

    private static File getCacheFile(String str) {
        return new File(keyValueCacheDir(), str);
    }

    private AVCacheManager() {
    }

    public static synchronized AVCacheManager sharedInstance() {
        AVCacheManager aVCacheManager;
        synchronized (AVCacheManager.class) {
            if (instance == null) {
                instance = new AVCacheManager();
            }
            aVCacheManager = instance;
        }
        return aVCacheManager;
    }

    public String fileCacheKey(String str, String str2) {
        if (AVUtils.isBlankString(str2)) {
            return AVUtils.md5(str);
        }
        return AVUtils.md5(str + str2);
    }

    public boolean hasCache(String str) {
        return hasCache(str, null);
    }

    public boolean hasCache(String str, String str2) {
        return getCacheFile(str, str2).exists();
    }

    private File getCacheFile(String str, String str2) {
        return getCacheFile(fileCacheKey(str, str2));
    }

    public void get(String str, long j, String str2, GenericObjectCallback genericObjectCallback) {
        File cacheFile = getCacheFile(str, str2);
        if (!cacheFile.exists() || (j > 0 && System.currentTimeMillis() - cacheFile.lastModified() > j)) {
            genericObjectCallback.onFailure(AVErrorUtils.createException(120, AVException.cacheMissingErrorString), null);
        } else {
            genericObjectCallback.onSuccess(AVPersistenceUtils.readContentFromFile(cacheFile), null);
        }
    }

    public void delete(String str) {
        File cacheFile = getCacheFile(AVUtils.md5(str));
        String absolutePath = cacheFile.getAbsolutePath();
        if (!cacheFile.exists()) {
            return;
        }
        if (cacheFile.delete()) {
            AVPersistenceUtils.removeLock(absolutePath);
        } else {
            AVPersistenceUtils.saveContentToFile("{}", cacheFile);
        }
    }

    public boolean save(String str, String str2, String str3) {
        return AVPersistenceUtils.saveContentToFile(str2, getCacheFile(str, str3));
    }

    public void remove(String str, String str2) {
        File cacheFile = getCacheFile(str, str2);
        String absolutePath = cacheFile.getAbsolutePath();
        if (!cacheFile.exists()) {
            return;
        }
        if (cacheFile.delete()) {
            AVPersistenceUtils.removeLock(absolutePath);
        } else {
            AVPersistenceUtils.saveContentToFile("{}", cacheFile);
        }
    }

    public boolean haveCache(String str) {
        return getCacheFile(AVUtils.md5(str)).exists();
    }

    public static boolean clearAllCache() {
        return clearCacheMoreThanDays(-1);
    }

    public static boolean clearCacheMoreThanOneDay() {
        return clearCacheMoreThanDays(1);
    }

    public static boolean clearCacheMoreThanDays(int i) {
        File[] listFiles = keyValueCacheDir().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (System.currentTimeMillis() - file.lastModified() > ((((long) i) * 24) * 3600) * 1000 && file.exists()) {
                    String absolutePath = file.getAbsolutePath();
                    if (!file.delete()) {
                        return false;
                    }
                    AVPersistenceUtils.removeLock(absolutePath);
                }
            }
        } else {
            avlog.m3506d("Cache Directory Failure");
        }
        return true;
    }

    public static boolean clearFileCacheMoreThanDays(int i) {
        if (AVOSCloud.applicationContext == null) {
            log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
            return false;
        }
        File[] listFiles = AVOSCloud.applicationContext.getFilesDir().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (System.currentTimeMillis() - file.lastModified() > ((((long) i) * 24) * 3600) * 1000 && file.exists() && file.isFile()) {
                    String absolutePath = file.getAbsolutePath();
                    if (!file.delete()) {
                        return false;
                    }
                    AVPersistenceUtils.removeLock(absolutePath);
                }
            }
        } else {
            avlog.m3506d("File Cache Directory Failure");
        }
        return true;
    }
}
