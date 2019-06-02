package io.rong.imkit;

import android.content.Context;
import io.rong.imlib.model.Conversation.ConversationType;

public interface RongIM$OnSelectMemberListener {
    void startSelectMember(Context context, ConversationType conversationType, String str);
}
