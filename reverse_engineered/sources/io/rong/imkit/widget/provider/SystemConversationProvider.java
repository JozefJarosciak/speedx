package io.rong.imkit.widget.provider;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;

@ConversationProviderTag(conversationType = "system", portraitPosition = 1)
public class SystemConversationProvider extends PrivateConversationProvider implements ConversationProvider<UIConversation> {
    public String getTitle(String str) {
        if (RongUserInfoManager.getInstance().getUserInfo(str) == null) {
            return str;
        }
        return RongUserInfoManager.getInstance().getUserInfo(str).getName();
    }
}
