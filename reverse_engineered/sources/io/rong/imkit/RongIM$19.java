package io.rong.imkit;

import io.rong.imkit.model.Event.CreateDiscussionEvent;
import io.rong.imlib.RongIMClient$CreateDiscussionCallback;
import io.rong.imlib.RongIMClient$ErrorCode;
import java.util.List;

class RongIM$19 extends RongIMClient$CreateDiscussionCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$CreateDiscussionCallback val$callback;
    final /* synthetic */ String val$name;
    final /* synthetic */ List val$userIdList;

    RongIM$19(RongIM rongIM, String str, List list, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        this.this$0 = rongIM;
        this.val$name = str;
        this.val$userIdList = list;
        this.val$callback = rongIMClient$CreateDiscussionCallback;
    }

    public void onSuccess(String str) {
        RongContext.getInstance().getEventBus().post(new CreateDiscussionEvent(str, this.val$name, this.val$userIdList));
        if (this.val$callback != null) {
            this.val$callback.onCallback(str);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
