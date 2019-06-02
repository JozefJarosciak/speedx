package com.j256.ormlite.android;

import com.j256.ormlite.logger.Log;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.LoggerFactory;

public class AndroidLog implements Log {
    private static final String ALL_LOGS_NAME = "ORMLite";
    private static final int MAX_TAG_LENGTH = 23;
    private static final int REFRESH_LEVEL_CACHE_EVERY = 200;
    private String className;
    private final boolean[] levelCache;
    private volatile int levelCacheC = 0;

    public AndroidLog(String str) {
        this.className = LoggerFactory.getSimpleClassName(str);
        int length = this.className.length();
        if (length > 23) {
            this.className = this.className.substring(length - 23, length);
        }
        Level[] values = Level.values();
        int length2 = values.length;
        int i = 0;
        length = 0;
        while (i < length2) {
            int levelToAndroidLevel = levelToAndroidLevel(values[i]);
            if (levelToAndroidLevel <= length) {
                levelToAndroidLevel = length;
            }
            i++;
            length = levelToAndroidLevel;
        }
        this.levelCache = new boolean[(length + 1)];
        refreshLevelCache();
    }

    public boolean isLevelEnabled(Level level) {
        int i = this.levelCacheC + 1;
        this.levelCacheC = i;
        if (i >= 200) {
            refreshLevelCache();
            this.levelCacheC = 0;
        }
        i = levelToAndroidLevel(level);
        if (i < this.levelCache.length) {
            return this.levelCache[i];
        }
        return isLevelEnabledInternal(i);
    }

    public void log(Level level, String str) {
        switch (level) {
            case TRACE:
                android.util.Log.v(this.className, str);
                return;
            case DEBUG:
                android.util.Log.d(this.className, str);
                return;
            case INFO:
                android.util.Log.i(this.className, str);
                return;
            case WARNING:
                android.util.Log.w(this.className, str);
                return;
            case ERROR:
                android.util.Log.e(this.className, str);
                return;
            case FATAL:
                android.util.Log.e(this.className, str);
                return;
            default:
                android.util.Log.i(this.className, str);
                return;
        }
    }

    public void log(Level level, String str, Throwable th) {
        switch (level) {
            case TRACE:
                android.util.Log.v(this.className, str, th);
                return;
            case DEBUG:
                android.util.Log.d(this.className, str, th);
                return;
            case INFO:
                android.util.Log.i(this.className, str, th);
                return;
            case WARNING:
                android.util.Log.w(this.className, str, th);
                return;
            case ERROR:
                android.util.Log.e(this.className, str, th);
                return;
            case FATAL:
                android.util.Log.e(this.className, str, th);
                return;
            default:
                android.util.Log.i(this.className, str, th);
                return;
        }
    }

    private void refreshLevelCache() {
        for (Level levelToAndroidLevel : Level.values()) {
            int levelToAndroidLevel2 = levelToAndroidLevel(levelToAndroidLevel);
            if (levelToAndroidLevel2 < this.levelCache.length) {
                this.levelCache[levelToAndroidLevel2] = isLevelEnabledInternal(levelToAndroidLevel2);
            }
        }
    }

    private boolean isLevelEnabledInternal(int i) {
        return android.util.Log.isLoggable(this.className, i) || android.util.Log.isLoggable(ALL_LOGS_NAME, i);
    }

    private int levelToAndroidLevel(Level level) {
        switch (level) {
            case TRACE:
                return 2;
            case DEBUG:
                return 3;
            case WARNING:
                return 5;
            case ERROR:
                return 6;
            case FATAL:
                return 6;
            default:
                return 4;
        }
    }
}
