package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class LocationMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ LocationMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    LocationMessageItemProvider$1(LocationMessageItemProvider locationMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = locationMessageItemProvider;
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
