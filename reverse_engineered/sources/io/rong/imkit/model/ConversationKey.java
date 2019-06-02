package io.rong.imkit.model;

import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.imlib.model.Conversation.ConversationType;

public final class ConversationKey {
    public static final String SEPARATOR = "#@6RONG_CLOUD9@#";
    private String key;
    private String targetId;
    private ConversationType type;

    private ConversationKey() {
    }

    public static ConversationKey obtain(String str, ConversationType conversationType) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            return null;
        }
        ConversationKey conversationKey = new ConversationKey();
        conversationKey.setTargetId(str);
        conversationKey.setType(conversationType);
        conversationKey.setKey(str + SEPARATOR + conversationType.getValue());
        return conversationKey;
    }

    public static ConversationKey obtain(String str) {
        if (!TextUtils.isEmpty(str) && str.contains(SEPARATOR)) {
            ConversationKey conversationKey = new ConversationKey();
            if (str.contains(SEPARATOR)) {
                String[] split = str.split(SEPARATOR);
                conversationKey.setTargetId(split[0]);
                try {
                    conversationKey.setType(ConversationType.setValue(Integer.parseInt(split[1])));
                    return conversationKey;
                } catch (NumberFormatException e) {
                    RLog.m19420e("ConversationKey ", "NumberFormatException");
                    return null;
                }
            }
        }
        return null;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public ConversationType getType() {
        return this.type;
    }

    public void setType(ConversationType conversationType) {
        this.type = conversationType;
    }
}
