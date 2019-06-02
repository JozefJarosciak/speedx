package io.rong.imkit.widget.provider;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import io.rong.imkit.C4974R;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.HandshakeMessage;

@ProviderTag(centerInHorizontal = true, hide = true, messageContent = HandshakeMessage.class, showPortrait = false)
public class HandshakeMessageItemProvider extends MessageProvider<HandshakeMessage> {
    public View newView(Context context, ViewGroup viewGroup) {
        return null;
    }

    public Spannable getContentSummary(HandshakeMessage handshakeMessage) {
        if (handshakeMessage == null || handshakeMessage.getContent() == null) {
            return null;
        }
        return new SpannableString(AndroidEmoji.ensure(handshakeMessage.getContent()));
    }

    public void onItemClick(View view, int i, HandshakeMessage handshakeMessage, UIMessage uIMessage) {
    }

    public void onItemLongClick(View view, int i, HandshakeMessage handshakeMessage, UIMessage uIMessage) {
        String str = null;
        if (!uIMessage.getConversationType().getName().equals(ConversationType.APP_PUBLIC_SERVICE.getName()) && !uIMessage.getConversationType().getName().equals(ConversationType.PUBLIC_SERVICE.getName())) {
            UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
            if (userInfo != null) {
                str = userInfo.getName();
            }
        } else if (uIMessage.getUserInfo() != null) {
            str = uIMessage.getUserInfo().getName();
        } else {
            PublicServiceProfile publicServiceProfile = RongUserInfoManager.getInstance().getPublicServiceProfile(PublicServiceType.setValue(uIMessage.getConversationType().getValue()), uIMessage.getTargetId());
            if (publicServiceProfile != null) {
                str = publicServiceProfile.getName();
            }
        }
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new HandshakeMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void bindView(View view, int i, HandshakeMessage handshakeMessage, UIMessage uIMessage) {
    }
}
