package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class RichContentMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ RichContentMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    RichContentMessageItemProvider$1(RichContentMessageItemProvider richContentMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = richContentMessageItemProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        } else if (i == 1) {
            RongIM.getInstance().recallMessage(this.val$message);
        }
    }
}
