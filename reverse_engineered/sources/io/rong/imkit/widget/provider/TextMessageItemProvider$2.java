package io.rong.imkit.widget.provider;

import android.widget.TextView;
import io.rong.imkit.model.UIMessage;

class TextMessageItemProvider$2 implements Runnable {
    final /* synthetic */ TextMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$data;
    final /* synthetic */ TextView val$textView;

    TextMessageItemProvider$2(TextMessageItemProvider textMessageItemProvider, TextView textView, UIMessage uIMessage) {
        this.this$0 = textMessageItemProvider;
        this.val$textView = textView;
        this.val$data = uIMessage;
    }

    public void run() {
        this.val$textView.setText(this.val$data.getTextMessageContent());
    }
}
