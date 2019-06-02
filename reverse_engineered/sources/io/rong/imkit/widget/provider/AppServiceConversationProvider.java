package io.rong.imkit.widget.provider;

import android.net.Uri;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.PublicServiceProfile;

@ConversationProviderTag(conversationType = "app_public_service", portraitPosition = 1)
public class AppServiceConversationProvider extends PrivateConversationProvider implements ConversationProvider<UIConversation> {
    public String getTitle(String str) {
        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(str, ConversationType.APP_PUBLIC_SERVICE).getKey());
        if (publicServiceInfoFromCache != null) {
            return publicServiceInfoFromCache.getName();
        }
        return "";
    }

    public Uri getPortraitUri(String str) {
        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(str, ConversationType.APP_PUBLIC_SERVICE).getKey());
        if (publicServiceInfoFromCache != null) {
            return publicServiceInfoFromCache.getPortraitUri();
        }
        return null;
    }
}
