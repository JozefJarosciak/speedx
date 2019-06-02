package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class PublicServiceRichContentMessageProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ PublicServiceRichContentMessageProvider this$0;
    final /* synthetic */ UIMessage val$message;

    PublicServiceRichContentMessageProvider$1(PublicServiceRichContentMessageProvider publicServiceRichContentMessageProvider, UIMessage uIMessage) {
        this.this$0 = publicServiceRichContentMessageProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        }
    }
}
