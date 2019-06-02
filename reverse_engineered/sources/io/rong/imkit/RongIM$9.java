package io.rong.imkit;

import io.rong.imkit.RongIM.OnReceiveUnreadCountChangedListener;
import io.rong.imkit.model.ConversationTypeFilter;
import io.rong.imkit.notification.MessageCounter.Counter;

class RongIM$9 extends Counter {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ OnReceiveUnreadCountChangedListener val$listener;

    RongIM$9(RongIM rongIM, ConversationTypeFilter conversationTypeFilter, OnReceiveUnreadCountChangedListener onReceiveUnreadCountChangedListener) {
        this.this$0 = rongIM;
        this.val$listener = onReceiveUnreadCountChangedListener;
        super(conversationTypeFilter);
    }

    public void onMessageIncreased(int i) {
        this.val$listener.onMessageIncreased(i);
    }
}
