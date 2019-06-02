package io.rong.imkit;

import android.content.Intent;
import io.rong.imkit.model.Event.ConnectEvent;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ConnectCallback;

class RongIM$1 extends ConnectCallback {
    final /* synthetic */ ConnectCallback val$callback;

    RongIM$1(ConnectCallback connectCallback) {
        this.val$callback = connectCallback;
    }

    public void onSuccess(String str) {
        Intent intent = new Intent("io.rong.intent.action.SDK_CONNECTED");
        intent.setPackage(RongIM.access$100().getPackageName());
        RongIM.access$100().sendBroadcast(intent);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(str);
        }
        if (!RongUserInfoManager.getInstance().isInitialized(str)) {
            RongUserInfoManager.getInstance().init(RongIM.access$100(), RongIM.access$200(RongIM$SingletonHolder.sRongIM), str, new RongUserCacheListener());
        }
        RongContext.getInstance().getEventBus().post(ConnectEvent.obtain(true));
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
        String currentUserId = RongIMClient.getInstance().getCurrentUserId();
        if (!RongUserInfoManager.getInstance().isInitialized(currentUserId)) {
            RongUserInfoManager.getInstance().init(RongIM.access$100(), RongIM.access$200(RongIM$SingletonHolder.sRongIM), currentUserId, new RongUserCacheListener());
        }
        RongContext.getInstance().getEventBus().post(ConnectEvent.obtain(false));
    }

    public void onTokenIncorrect() {
        if (this.val$callback != null) {
            this.val$callback.onTokenIncorrect();
        }
    }
}
