package io.rong.imkit.model;

import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationTypeFilter {
    Level mLevel;
    List<ConversationType> mTypes;

    public enum Level {
        ALL,
        CONVERSATION_TYPE,
        NONE
    }

    public static ConversationTypeFilter obtain(ConversationType... conversationTypeArr) {
        return new ConversationTypeFilter(conversationTypeArr);
    }

    public static ConversationTypeFilter obtain(Level level) {
        return new ConversationTypeFilter(level);
    }

    public static ConversationTypeFilter obtain() {
        return new ConversationTypeFilter();
    }

    private ConversationTypeFilter(ConversationType... conversationTypeArr) {
        this.mTypes = new ArrayList();
        this.mTypes.addAll(Arrays.asList(conversationTypeArr));
        this.mLevel = Level.CONVERSATION_TYPE;
    }

    private ConversationTypeFilter() {
        this.mTypes = new ArrayList();
        this.mLevel = Level.ALL;
    }

    private ConversationTypeFilter(Level level) {
        this.mTypes = new ArrayList();
        this.mLevel = level;
    }

    public Level getLevel() {
        return this.mLevel;
    }

    public List<ConversationType> getConversationTypeList() {
        return this.mTypes;
    }

    public boolean hasFilter(Message message) {
        if (this.mLevel == Level.ALL) {
            return true;
        }
        if (this.mLevel != Level.CONVERSATION_TYPE) {
            return false;
        }
        if (this.mTypes.contains(message.getConversationType())) {
            return true;
        }
        return false;
    }
}
