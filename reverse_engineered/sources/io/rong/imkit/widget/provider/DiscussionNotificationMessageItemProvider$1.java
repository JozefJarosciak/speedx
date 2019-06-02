package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class DiscussionNotificationMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ DiscussionNotificationMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    DiscussionNotificationMessageItemProvider$1(DiscussionNotificationMessageItemProvider discussionNotificationMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = discussionNotificationMessageItemProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        }
    }
}
