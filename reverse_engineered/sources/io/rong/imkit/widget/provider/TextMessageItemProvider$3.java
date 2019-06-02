package io.rong.imkit.widget.provider;

import android.view.View;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imkit.widget.ILinkClickListener;

class TextMessageItemProvider$3 implements ILinkClickListener {
    final /* synthetic */ TextMessageItemProvider this$0;
    final /* synthetic */ View val$v;

    TextMessageItemProvider$3(TextMessageItemProvider textMessageItemProvider, View view) {
        this.this$0 = textMessageItemProvider;
        this.val$v = view;
    }

    public boolean onLinkClick(String str) {
        ConversationBehaviorListener conversationBehaviorListener = RongContext.getInstance().getConversationBehaviorListener();
        if (conversationBehaviorListener != null) {
            return conversationBehaviorListener.onMessageLinkClick(this.val$v.getContext(), str);
        }
        return false;
    }
}
