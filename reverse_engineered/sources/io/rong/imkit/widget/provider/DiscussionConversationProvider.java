package io.rong.imkit.widget.provider;

import android.net.Uri;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;

@ConversationProviderTag(conversationType = "discussion", portraitPosition = 1)
public class DiscussionConversationProvider extends PrivateConversationProvider implements ConversationProvider<UIConversation> {
    public String getTitle(String str) {
        if (RongUserInfoManager.getInstance().getDiscussionInfo(str) == null) {
            return RongContext.getInstance().getResources().getString(C4974R.string.rc_conversation_list_default_discussion_name);
        }
        return RongUserInfoManager.getInstance().getDiscussionInfo(str).getName();
    }

    public Uri getPortraitUri(String str) {
        return null;
    }
}
