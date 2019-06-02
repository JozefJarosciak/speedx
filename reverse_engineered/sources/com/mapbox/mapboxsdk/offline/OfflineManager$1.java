package com.mapbox.mapboxsdk.offline;

import android.content.Context;
import android.util.Log;
import java.io.File;

class OfflineManager$1 implements Runnable {
    final /* synthetic */ OfflineManager this$0;
    final /* synthetic */ Context val$context;

    OfflineManager$1(OfflineManager offlineManager, Context context) {
        this.this$0 = offlineManager;
        this.val$context = context;
    }

    public void run() {
        try {
            String str = this.val$context.getCacheDir().getAbsolutePath() + File.separator + "mbgl-cache.db";
            File file = new File(str);
            if (file.exists()) {
                file.delete();
                Log.d("OfflineManager", "Old ambient cache database deleted to save space: " + str);
            }
        } catch (Exception e) {
            Log.e("OfflineManager", "Failed to delete old ambient cache database: " + e.getMessage());
        }
    }
}
