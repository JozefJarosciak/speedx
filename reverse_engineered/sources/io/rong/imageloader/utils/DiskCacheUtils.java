package io.rong.imageloader.utils;

import io.rong.imageloader.cache.disc.DiskCache;
import java.io.File;

public final class DiskCacheUtils {
    private DiskCacheUtils() {
    }

    public static File findInCache(String str, DiskCache diskCache) {
        File file = diskCache.get(str);
        return (file == null || !file.exists()) ? null : file;
    }

    public static boolean removeFromCache(String str, DiskCache diskCache) {
        File file = diskCache.get(str);
        return file != null && file.exists() && file.delete();
    }
}
