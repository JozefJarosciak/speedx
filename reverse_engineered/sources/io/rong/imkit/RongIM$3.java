package io.rong.imkit;

import io.rong.common.RLog;
import io.rong.imkit.manager.SendImageManager;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient.ConnectionStatusListener;

class RongIM$3 implements ConnectionStatusListener {
    RongIM$3() {
    }

    public void onChanged(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        if (rongIMClient$ConnectionStatusListener$ConnectionStatus != null) {
            RLog.m19419d(RongIM.access$500(), "ConnectionStatusListener onChanged : " + rongIMClient$ConnectionStatusListener$ConnectionStatus.toString());
            if (RongIM.sConnectionStatusListener != null) {
                RongIM.sConnectionStatusListener.onChanged(rongIMClient$ConnectionStatusListener$ConnectionStatus);
            }
            if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
                SendImageManager.getInstance().reset();
            } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                String currentUserId = RongIMClient.getInstance().getCurrentUserId();
                if (!RongUserInfoManager.getInstance().isInitialized(currentUserId)) {
                    RongUserInfoManager.getInstance().init(RongIM.access$100(), RongIM.access$200(RongIM$SingletonHolder.sRongIM), currentUserId, new RongUserCacheListener());
                }
            }
            RongContext.getInstance().getEventBus().post(rongIMClient$ConnectionStatusListener$ConnectionStatus);
        }
    }
}
