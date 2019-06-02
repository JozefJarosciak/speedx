package io.rong.imkit.widget.provider;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;

@ConversationProviderTag(conversationType = "customer_service", portraitPosition = 1)
public class CustomerServiceConversationProvider extends PrivateConversationProvider implements ConversationProvider<UIConversation> {
}
