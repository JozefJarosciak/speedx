package io.rong.imkit.model;

import io.rong.imlib.model.Conversation.ConversationType;

public class ConversationInfo {
    ConversationType conversationType;
    String targetId;

    ConversationInfo() {
    }

    public static ConversationInfo obtain(ConversationType conversationType, String str) {
        ConversationInfo conversationInfo = new ConversationInfo();
        conversationInfo.conversationType = conversationType;
        conversationInfo.targetId = str;
        return conversationInfo;
    }

    public ConversationType getConversationType() {
        return this.conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }
}
