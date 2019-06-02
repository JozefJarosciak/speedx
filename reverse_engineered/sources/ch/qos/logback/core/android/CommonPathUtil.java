package ch.qos.logback.core.android;

import android.os.Environment;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.OptionHelper;

public abstract class CommonPathUtil {
    private static final String ASSETS_DIRECTORY = "assets";

    public static String getAssetsDirectoryPath() {
        return ASSETS_DIRECTORY;
    }

    public static String getDatabaseDirectoryPath(String str) {
        return (EnvUtil.isAndroidOS() ? Environment.getDataDirectory().getAbsolutePath() : "/data") + "/data/" + str + "/databases";
    }

    public static String getExternalStorageDirectoryPath() {
        if (EnvUtil.isAndroidOS()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        String env = OptionHelper.getEnv("EXTERNAL_STORAGE");
        return env == null ? "/sdcard" : env;
    }

    public static String getFilesDirectoryPath(String str) {
        return (EnvUtil.isAndroidOS() ? Environment.getDataDirectory().getAbsolutePath() : "/data") + "/data/" + str + "/files";
    }

    public static String getMountedExternalStorageDirectoryPath() {
        if (!EnvUtil.isAndroidOS()) {
            return "/mnt/sdcard";
        }
        String externalStorageState = Environment.getExternalStorageState();
        return (externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) ? Environment.getExternalStorageDirectory().getAbsolutePath() : null;
    }
}
