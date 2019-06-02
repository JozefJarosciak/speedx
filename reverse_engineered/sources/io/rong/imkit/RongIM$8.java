package io.rong.imkit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.RLog;
import io.rong.imlib.RongIMClient$CreateDiscussionCallback;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

class RongIM$8 extends RongIMClient$CreateDiscussionCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$CreateDiscussionCallback val$callback;
    final /* synthetic */ Context val$context;
    final /* synthetic */ List val$targetUserIds;
    final /* synthetic */ String val$title;

    RongIM$8(RongIM rongIM, Context context, List list, String str, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        this.this$0 = rongIM;
        this.val$context = context;
        this.val$targetUserIds = list;
        this.val$title = str;
        this.val$callback = rongIMClient$CreateDiscussionCallback;
    }

    public void onSuccess(String str) {
        this.val$context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + this.val$context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.DISCUSSION.getName().toLowerCase()).appendQueryParameter("targetIds", TextUtils.join(",", this.val$targetUserIds)).appendQueryParameter("delimiter", ",").appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, this.val$title).build()));
        if (this.val$callback != null) {
            this.val$callback.onSuccess(str);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RLog.m19419d(RongIM.access$500(), "createDiscussionChat createDiscussion not success." + rongIMClient$ErrorCode);
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
