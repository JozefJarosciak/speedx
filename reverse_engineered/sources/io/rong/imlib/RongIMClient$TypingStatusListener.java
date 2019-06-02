package io.rong.imlib;

import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.Collection;

public interface RongIMClient$TypingStatusListener {
    void onTypingStatusChanged(ConversationType conversationType, String str, Collection<TypingStatus> collection);
}
