package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import android.text.ClipboardManager;
import android.view.View;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.message.TextMessage;

class TextMessageItemProvider$1 implements OnArraysDialogItemListener {
    final /* synthetic */ TextMessageItemProvider this$0;
    final /* synthetic */ TextMessage val$content;
    final /* synthetic */ UIMessage val$message;
    final /* synthetic */ View val$view;

    TextMessageItemProvider$1(TextMessageItemProvider textMessageItemProvider, View view, TextMessage textMessage, UIMessage uIMessage) {
        this.this$0 = textMessageItemProvider;
        this.val$view = view;
        this.val$content = textMessage;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            ((ClipboardManager) this.val$view.getContext().getSystemService("clipboard")).setText(this.val$content.getContent());
        } else if (i == 1) {
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        } else if (i == 2) {
            RongIM.getInstance().recallMessage(this.val$message);
        }
    }
}
