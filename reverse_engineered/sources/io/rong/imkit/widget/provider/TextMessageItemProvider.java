package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.support.v4.app.FragmentActivity;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.LinkTextView;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.LinkTextViewMovementMethod;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

@ProviderTag(messageContent = TextMessage.class)
public class TextMessageItemProvider extends MessageProvider<TextMessage> {
    private static final String TAG = "TextMessageItemProvider";

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_text_message, null);
        TextMessageItemProvider$ViewHolder textMessageItemProvider$ViewHolder = new TextMessageItemProvider$ViewHolder(this);
        textMessageItemProvider$ViewHolder.message = (LinkTextView) inflate.findViewById(16908308);
        inflate.setTag(textMessageItemProvider$ViewHolder);
        return inflate;
    }

    public Spannable getContentSummary(TextMessage textMessage) {
        if (textMessage == null) {
            return null;
        }
        String content = textMessage.getContent();
        if (content == null) {
            return null;
        }
        if (content.length() > 100) {
            content = content.substring(0, 100);
        }
        return new SpannableString(AndroidEmoji.ensure(content));
    }

    public void onItemClick(View view, int i, TextMessage textMessage, UIMessage uIMessage) {
    }

    public void onItemLongClick(View view, int i, TextMessage textMessage, UIMessage uIMessage) {
        int integer;
        ((TextMessageItemProvider$ViewHolder) view.getTag()).longClick = true;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null && (text instanceof Spannable)) {
                Selection.removeSelection((Spannable) text);
            }
        }
        String str = null;
        if (uIMessage.getConversationType().getName().equals(ConversationType.APP_PUBLIC_SERVICE.getName()) || uIMessage.getConversationType().getName().equals(ConversationType.PUBLIC_SERVICE.getName())) {
            if (uIMessage.getUserInfo() != null) {
                str = uIMessage.getUserInfo().getName();
            } else {
                PublicServiceProfile publicServiceProfile = RongUserInfoManager.getInstance().getPublicServiceProfile(PublicServiceType.setValue(uIMessage.getConversationType().getValue()), uIMessage.getTargetId());
                if (publicServiceProfile != null) {
                    str = publicServiceProfile.getName();
                }
            }
        } else if (uIMessage.getSenderUserId() != null) {
            UserInfo userInfo = uIMessage.getUserInfo();
            if (userInfo == null || userInfo.getName() == null) {
                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
            }
            if (userInfo != null) {
                str = userInfo.getName();
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - RongIM.getInstance().getDeltaTime();
        boolean z = false;
        Object obj = (uIMessage.getSentStatus().equals(Message$SentStatus.SENDING) || uIMessage.getSentStatus().equals(Message$SentStatus.FAILED)) ? null : 1;
        try {
            z = RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_enable_message_recall);
            integer = RongContext.getInstance().getResources().getInteger(C4974R.integer.rc_message_recall_interval);
        } catch (NotFoundException e) {
            RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
            e.printStackTrace();
            integer = -1;
        }
        String[] strArr = (obj == null || !z || currentTimeMillis - uIMessage.getSentTime() > ((long) (integer * 1000)) || !uIMessage.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())) ? new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_copy), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)} : new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_copy), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_recall)};
        ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new TextMessageItemProvider$1(this, view, textMessage, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void bindView(View view, int i, TextMessage textMessage, UIMessage uIMessage) {
        TextMessageItemProvider$ViewHolder textMessageItemProvider$ViewHolder = (TextMessageItemProvider$ViewHolder) view.getTag();
        if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
            textMessageItemProvider$ViewHolder.message.setBackgroundResource(C4974R.drawable.rc_ic_bubble_right);
        } else {
            textMessageItemProvider$ViewHolder.message.setBackgroundResource(C4974R.drawable.rc_ic_bubble_left);
        }
        TextView textView = textMessageItemProvider$ViewHolder.message;
        if (uIMessage.getTextMessageContent() != null) {
            int length = uIMessage.getTextMessageContent().length();
            if (view.getHandler() == null || length <= 500) {
                textView.setText(uIMessage.getTextMessageContent());
            } else {
                view.getHandler().postDelayed(new TextMessageItemProvider$2(this, textView, uIMessage), 50);
            }
        }
        textMessageItemProvider$ViewHolder.message.setMovementMethod(new LinkTextViewMovementMethod(new TextMessageItemProvider$3(this, view)));
    }
}
