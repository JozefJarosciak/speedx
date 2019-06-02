package io.rong.imkit.mention;

import io.rong.imlib.model.Conversation.ConversationType;

public interface IMentionedInputListener {
    boolean onMentionedInput(ConversationType conversationType, String str);
}
