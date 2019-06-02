package io.rong.imkit.widget.provider;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UnknownMessage;
import io.rong.imlib.model.UserInfo;

@ProviderTag(centerInHorizontal = true, messageContent = UnknownMessage.class, showPortrait = false, showWarning = false)
public class UnknownMessageItemProvider extends MessageProvider<MessageContent> {
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uIMessage) {
        ((UnknownMessageItemProvider$ViewHolder) view.getTag()).contentTextView.setText(C4974R.string.rc_message_unknown);
    }

    public Spannable getContentSummary(MessageContent messageContent) {
        return new SpannableString(RongContext.getInstance().getResources().getString(C4974R.string.rc_message_unknown));
    }

    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uIMessage) {
    }

    public void onItemLongClick(View view, int i, MessageContent messageContent, UIMessage uIMessage) {
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
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new UnknownMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_information_notification_message, null);
        UnknownMessageItemProvider$ViewHolder unknownMessageItemProvider$ViewHolder = new UnknownMessageItemProvider$ViewHolder(this);
        unknownMessageItemProvider$ViewHolder.contentTextView = (TextView) inflate.findViewById(C4974R.id.rc_msg);
        unknownMessageItemProvider$ViewHolder.contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        inflate.setTag(unknownMessageItemProvider$ViewHolder);
        return inflate;
    }
}
