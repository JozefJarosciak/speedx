package com.beastbikes.android.modules.cycling;

import android.text.TextUtils;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;
import java.util.TimerTask;

final class SyncService$b extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ SyncService f8445a;

    private SyncService$b(SyncService syncService) {
        this.f8445a = syncService;
    }

    public void run() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            SyncService.a().info("Synchronizer", "No authenticated user found");
            return;
        }
        String objectId = currentUser.getObjectId();
        if (TextUtils.isEmpty(objectId)) {
            SyncService.a().info("Synchronizer", "No authenticated user found");
            return;
        }
        SyncService.a().info("SyncService Upload Activity Task is run start...");
        synchronized (SyncService.a(this.f8445a)) {
            try {
                List<LocalActivity> c = SyncService.b(this.f8445a).c(objectId, "");
                if (c == null || c.isEmpty()) {
                    SyncService.a().info("Synchronizer : No unsynced data");
                    return;
                }
                SyncService.f4475a = true;
                for (LocalActivity localActivity : c) {
                    if (localActivity.getTotalDistance() > 10.0d) {
                        try {
                            SyncService.b(this.f8445a).a(localActivity);
                            SyncService.a().info("Synchronizer userId:" + objectId + " Sync samples of activity " + localActivity.getId() + " success");
                        } catch (Throwable e) {
                            SyncService.a().error("Synchronizer : Sync samples of activity " + localActivity.getId() + " error", e);
                        }
                    }
                }
                SyncService.f4475a = false;
            } catch (BusinessException e2) {
                SyncService.a().error("Synchronizer", "Query local activity error", e2);
            }
        }
    }
}
