package com.mapbox.mapboxsdk.offline;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import java.io.File;

public class OfflineManager {
    private static final String DATABASE_NAME = "mbgl-offline.db";
    private static final long DEFAULT_MAX_CACHE_SIZE = 52428800;
    private static final String LOG_TAG = "OfflineManager";
    private static OfflineManager instance;
    private Handler handler;
    private long mDefaultFileSourcePtr = 0;

    private native long createDefaultFileSource(String str, String str2, long j);

    private native void createOfflineRegion(long j, OfflineRegionDefinition offlineRegionDefinition, byte[] bArr, OfflineManager$CreateOfflineRegionCallback offlineManager$CreateOfflineRegionCallback);

    private native String getAccessToken(long j);

    private native void listOfflineRegions(long j, OfflineManager$ListOfflineRegionsCallback offlineManager$ListOfflineRegionsCallback);

    private native void setAccessToken(long j, String str);

    private native void setOfflineMapboxTileCountLimit(long j, long j2);

    static {
        System.loadLibrary("mapbox-gl");
    }

    private OfflineManager(Context context) {
        String databasePath = getDatabasePath(context);
        this.mDefaultFileSourcePtr = createDefaultFileSource(databasePath + File.separator + DATABASE_NAME, databasePath, DEFAULT_MAX_CACHE_SIZE);
        if (MapboxAccountManager.getInstance() != null) {
            setAccessToken(this.mDefaultFileSourcePtr, MapboxAccountManager.getInstance().getAccessToken());
        }
        deleteAmbientDatabase(context);
    }

    public static String getDatabasePath(Context context) {
        boolean z;
        String absolutePath;
        try {
            z = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(MapboxConstants.KEY_META_DATA_SET_STORAGE_EXTERNAL, false);
        } catch (NameNotFoundException e) {
            Log.e(LOG_TAG, "Failed to read the package metadata: " + e.getMessage());
            z = false;
        } catch (Exception e2) {
            Log.e(LOG_TAG, "Failed to read the storage key: " + e2.getMessage());
            z = false;
        }
        if (z && isExternalStorageReadable()) {
            try {
                absolutePath = context.getExternalFilesDir(null).getAbsolutePath();
            } catch (NullPointerException e3) {
                Log.e(LOG_TAG, "Failed to obtain the external storage path: " + e3.getMessage());
            }
            if (absolutePath != null) {
                return context.getFilesDir().getAbsolutePath();
            }
            return absolutePath;
        }
        absolutePath = null;
        if (absolutePath != null) {
            return absolutePath;
        }
        return context.getFilesDir().getAbsolutePath();
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
            return true;
        }
        Log.w(LOG_TAG, "External storage was requested but it isn't readable. For API level < 18 make sure you've requested READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE permissions in your app Manifest (defaulting to internal storage).");
        return false;
    }

    private void deleteAmbientDatabase(Context context) {
        new Thread(new OfflineManager$1(this, context)).start();
    }

    public static synchronized OfflineManager getInstance(Context context) {
        OfflineManager offlineManager;
        synchronized (OfflineManager.class) {
            if (instance == null) {
                instance = new OfflineManager(context);
            }
            offlineManager = instance;
        }
        return offlineManager;
    }

    @Deprecated
    public void setAccessToken(String str) {
        setAccessToken(this.mDefaultFileSourcePtr, str);
    }

    @Deprecated
    public String getAccessToken() {
        return getAccessToken(this.mDefaultFileSourcePtr);
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        return this.handler;
    }

    public void listOfflineRegions(@NonNull OfflineManager$ListOfflineRegionsCallback offlineManager$ListOfflineRegionsCallback) {
        listOfflineRegions(this.mDefaultFileSourcePtr, new OfflineManager$2(this, offlineManager$ListOfflineRegionsCallback));
    }

    public void createOfflineRegion(@NonNull OfflineRegionDefinition offlineRegionDefinition, @NonNull byte[] bArr, @NonNull OfflineManager$CreateOfflineRegionCallback offlineManager$CreateOfflineRegionCallback) {
        createOfflineRegion(this.mDefaultFileSourcePtr, offlineRegionDefinition, bArr, new OfflineManager$3(this, offlineManager$CreateOfflineRegionCallback));
    }

    public void setOfflineMapboxTileCountLimit(long j) {
        setOfflineMapboxTileCountLimit(this.mDefaultFileSourcePtr, j);
    }
}
