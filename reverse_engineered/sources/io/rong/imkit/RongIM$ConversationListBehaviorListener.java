package io.rong.imkit;

import android.content.Context;
import android.view.View;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation.ConversationType;

public interface RongIM$ConversationListBehaviorListener {
    boolean onConversationClick(Context context, View view, UIConversation uIConversation);

    boolean onConversationLongClick(Context context, View view, UIConversation uIConversation);

    boolean onConversationPortraitClick(Context context, ConversationType conversationType, String str);

    boolean onConversationPortraitLongClick(Context context, ConversationType conversationType, String str);
}
