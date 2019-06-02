package io.rong.imkit.widget.provider;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message$SentStatus;
import java.util.Date;

@ConversationProviderTag(conversationType = "private", portraitPosition = 1)
public class PrivateConversationProvider implements ConversationProvider<UIConversation> {
    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_base_conversation, null);
        PrivateConversationProvider$ViewHolder privateConversationProvider$ViewHolder = new PrivateConversationProvider$ViewHolder(this);
        privateConversationProvider$ViewHolder.title = (TextView) inflate.findViewById(C4974R.id.rc_conversation_title);
        privateConversationProvider$ViewHolder.time = (TextView) inflate.findViewById(C4974R.id.rc_conversation_time);
        privateConversationProvider$ViewHolder.content = (TextView) inflate.findViewById(C4974R.id.rc_conversation_content);
        privateConversationProvider$ViewHolder.notificationBlockImage = (ImageView) inflate.findViewById(C4974R.id.rc_conversation_msg_block);
        privateConversationProvider$ViewHolder.readStatus = (ImageView) inflate.findViewById(C4974R.id.rc_conversation_status);
        inflate.setTag(privateConversationProvider$ViewHolder);
        return inflate;
    }

    public void bindView(View view, int i, UIConversation uIConversation) {
        PrivateConversationProvider$ViewHolder privateConversationProvider$ViewHolder = (PrivateConversationProvider$ViewHolder) view.getTag();
        if (uIConversation == null) {
            privateConversationProvider$ViewHolder.title.setText(null);
            privateConversationProvider$ViewHolder.time.setText(null);
            privateConversationProvider$ViewHolder.content.setText(null);
            return;
        }
        privateConversationProvider$ViewHolder.title.setText(uIConversation.getUIConversationTitle());
        privateConversationProvider$ViewHolder.time.setText(RongDateUtils.getConversationListFormatDate(new Date(uIConversation.getUIConversationTime())));
        if (!TextUtils.isEmpty(uIConversation.getDraft()) || uIConversation.getMentionedFlag()) {
            Object spannableStringBuilder = new SpannableStringBuilder();
            CharSequence spannableString;
            if (uIConversation.getMentionedFlag()) {
                spannableString = new SpannableString(view.getContext().getString(C4974R.string.rc_message_content_mentioned));
                spannableString.setSpan(new ForegroundColorSpan(view.getContext().getResources().getColor(C4974R.color.rc_mentioned_color)), 0, spannableString.length(), 33);
                spannableStringBuilder.append(spannableString).append(" ").append(uIConversation.getConversationContent());
            } else {
                spannableString = new SpannableString(view.getContext().getString(C4974R.string.rc_message_content_draft));
                spannableString.setSpan(new ForegroundColorSpan(view.getContext().getResources().getColor(C4974R.color.rc_draft_color)), 0, spannableString.length(), 33);
                spannableStringBuilder.append(spannableString).append(" ").append(uIConversation.getDraft());
            }
            AndroidEmoji.ensure(spannableStringBuilder);
            privateConversationProvider$ViewHolder.content.setText(spannableStringBuilder);
            privateConversationProvider$ViewHolder.readStatus.setVisibility(8);
        } else {
            if (RongIMClient.getInstance().getReadReceipt()) {
                if (uIConversation.getSentStatus() == Message$SentStatus.READ && uIConversation.getConversationType().getName().equals(ConversationType.PRIVATE.getName()) && uIConversation.getConversationSenderId().equals(RongIM.getInstance().getCurrentUserId())) {
                    privateConversationProvider$ViewHolder.readStatus.setVisibility(0);
                } else {
                    privateConversationProvider$ViewHolder.readStatus.setVisibility(8);
                }
            }
            privateConversationProvider$ViewHolder.content.setText(uIConversation.getConversationContent());
        }
        ProviderTag providerTag;
        if (RongContext.getInstance() == null || uIConversation.getMessageContent() == null) {
            providerTag = null;
        } else {
            providerTag = RongContext.getInstance().getMessageProviderTag(uIConversation.getMessageContent().getClass());
        }
        if (uIConversation.getSentStatus() == null || !((uIConversation.getSentStatus() == Message$SentStatus.FAILED || uIConversation.getSentStatus() == Message$SentStatus.SENDING) && r1 != null && r1.showWarning() && uIConversation.getConversationSenderId() != null && uIConversation.getConversationSenderId().equals(RongIM.getInstance().getCurrentUserId()))) {
            privateConversationProvider$ViewHolder.content.setCompoundDrawables(null, null, null, null);
        } else {
            Drawable drawable;
            int width = BitmapFactory.decodeResource(view.getResources(), C4974R.drawable.rc_conversation_list_msg_send_failure).getWidth();
            if (uIConversation.getSentStatus() == Message$SentStatus.FAILED && TextUtils.isEmpty(uIConversation.getDraft())) {
                drawable = view.getContext().getResources().getDrawable(C4974R.drawable.rc_conversation_list_msg_send_failure);
            } else if (uIConversation.getSentStatus() == Message$SentStatus.SENDING && TextUtils.isEmpty(uIConversation.getDraft())) {
                drawable = view.getContext().getResources().getDrawable(C4974R.drawable.rc_conversation_list_msg_sending);
            } else {
                drawable = null;
            }
            if (drawable != null) {
                drawable.setBounds(0, 0, width, width);
                privateConversationProvider$ViewHolder.content.setCompoundDrawablePadding(10);
                privateConversationProvider$ViewHolder.content.setCompoundDrawables(drawable, null, null, null);
            }
        }
        ConversationNotificationStatus conversationNotifyStatusFromCache = RongContext.getInstance().getConversationNotifyStatusFromCache(ConversationKey.obtain(uIConversation.getConversationTargetId(), uIConversation.getConversationType()));
        if (conversationNotifyStatusFromCache == null || !conversationNotifyStatusFromCache.equals(ConversationNotificationStatus.DO_NOT_DISTURB)) {
            privateConversationProvider$ViewHolder.notificationBlockImage.setVisibility(8);
        } else {
            privateConversationProvider$ViewHolder.notificationBlockImage.setVisibility(0);
        }
    }

    public Spannable getSummary(UIConversation uIConversation) {
        return null;
    }

    public String getTitle(String str) {
        if (RongUserInfoManager.getInstance().getUserInfo(str) == null) {
            return str;
        }
        return RongUserInfoManager.getInstance().getUserInfo(str).getName();
    }

    public Uri getPortraitUri(String str) {
        if (RongUserInfoManager.getInstance().getUserInfo(str) == null) {
            return null;
        }
        return RongUserInfoManager.getInstance().getUserInfo(str).getPortraitUri();
    }
}
