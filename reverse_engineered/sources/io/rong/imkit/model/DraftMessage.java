package io.rong.imkit.model;

import io.rong.imlib.model.Conversation.ConversationType;

public class DraftMessage {
    private String content;
    private ConversationType conversationType;
    private String targetId;

    public static DraftMessage obtain(ConversationType conversationType, String str, String str2) {
        DraftMessage draftMessage = new DraftMessage();
        draftMessage.content = str2;
        draftMessage.conversationType = conversationType;
        draftMessage.targetId = str;
        return draftMessage;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setConversationType(ConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public ConversationType getConversationType() {
        return this.conversationType;
    }
}
