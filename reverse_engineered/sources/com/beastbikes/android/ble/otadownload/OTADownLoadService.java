package com.beastbikes.android.ble.otadownload;

import android.app.IntentService;
import android.content.Intent;

public class OTADownLoadService extends IntentService {
    public OTADownLoadService() {
        super("OTADownLoadService");
    }

    protected void onHandleIntent(Intent intent) {
        new C1677a(this).m9070a();
    }
}
