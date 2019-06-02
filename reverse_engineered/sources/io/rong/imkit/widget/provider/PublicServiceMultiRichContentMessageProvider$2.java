package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class PublicServiceMultiRichContentMessageProvider$2 implements OnArraysDialogItemListener {
    final /* synthetic */ PublicServiceMultiRichContentMessageProvider this$0;
    final /* synthetic */ UIMessage val$message;

    PublicServiceMultiRichContentMessageProvider$2(PublicServiceMultiRichContentMessageProvider publicServiceMultiRichContentMessageProvider, UIMessage uIMessage) {
        this.this$0 = publicServiceMultiRichContentMessageProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        }
    }
}
