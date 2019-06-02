package com.beastbikes.android.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VersionUpdateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(((DownloadManager) context.getSystemService("download")).getUriForDownloadedFile(intent.getLongExtra("extra_download_id", 0)), "application/vnd.android.package-archive");
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
