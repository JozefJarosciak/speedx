package io.rong.imkit.widget.provider;

import android.net.Uri;
import android.view.View;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.PublicServiceProfile;

@ConversationProviderTag(conversationType = "public_service", portraitPosition = 1)
public class PublicServiceConversationProvider extends PrivateConversationProvider implements ConversationProvider<UIConversation> {
    private ConversationKey mKey;

    public String getTitle(String str) {
        this.mKey = ConversationKey.obtain(str, ConversationType.PUBLIC_SERVICE);
        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(this.mKey.getKey());
        if (publicServiceInfoFromCache != null) {
            return publicServiceInfoFromCache.getName();
        }
        return "";
    }

    public Uri getPortraitUri(String str) {
        this.mKey = ConversationKey.obtain(str, ConversationType.PUBLIC_SERVICE);
        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(this.mKey.getKey());
        if (publicServiceInfoFromCache != null) {
            return publicServiceInfoFromCache.getPortraitUri();
        }
        return null;
    }

    public void bindView(View view, int i, UIConversation uIConversation) {
        super.bindView(view, i, uIConversation);
    }
}
