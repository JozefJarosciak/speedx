package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class UnknownMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ UnknownMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    UnknownMessageItemProvider$1(UnknownMessageItemProvider unknownMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = unknownMessageItemProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        }
    }
}
