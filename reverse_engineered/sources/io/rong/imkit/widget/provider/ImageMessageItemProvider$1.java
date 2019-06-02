package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.SendImageManager;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class ImageMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ ImageMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    ImageMessageItemProvider$1(ImageMessageItemProvider imageMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = imageMessageItemProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            SendImageManager.getInstance().cancelSendingImage(this.val$message.getConversationType(), this.val$message.getTargetId(), this.val$message.getMessageId());
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        } else if (i == 1) {
            RongIM.getInstance().recallMessage(this.val$message);
        }
    }
}
