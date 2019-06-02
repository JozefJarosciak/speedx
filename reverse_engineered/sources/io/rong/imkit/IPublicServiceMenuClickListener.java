package io.rong.imkit;

import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.PublicServiceMenuItem;

public interface IPublicServiceMenuClickListener {
    boolean onClick(ConversationType conversationType, String str, PublicServiceMenuItem publicServiceMenuItem);
}
