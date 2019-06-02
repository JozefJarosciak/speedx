package io.rong.imkit.widget.provider;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.UserInfo;
import io.rong.message.RecallNotificationMessage;

@ProviderTag(centerInHorizontal = true, messageContent = RecallNotificationMessage.class, showPortrait = false, showProgress = false, showSummaryWithName = false, showWarning = false)
public class RecallMessageItemProvider extends MessageProvider<RecallNotificationMessage> {
    public void onItemClick(View view, int i, RecallNotificationMessage recallNotificationMessage, UIMessage uIMessage) {
    }

    public void bindView(View view, int i, RecallNotificationMessage recallNotificationMessage, UIMessage uIMessage) {
        RecallMessageItemProvider$ViewHolder recallMessageItemProvider$ViewHolder = (RecallMessageItemProvider$ViewHolder) view.getTag();
        if (recallNotificationMessage != null && uIMessage != null) {
            CharSequence string;
            if (recallNotificationMessage.getOperatorId().equals(RongIM.getInstance().getCurrentUserId())) {
                string = RongContext.getInstance().getString(C4974R.string.rc_you_recalled_a_message);
            } else {
                UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(recallNotificationMessage.getOperatorId());
                if (userInfo == null || userInfo.getName() == null) {
                    string = recallNotificationMessage.getOperatorId() + RongContext.getInstance().getString(C4974R.string.rc_recalled_a_message);
                } else {
                    string = userInfo.getName() + RongContext.getInstance().getString(C4974R.string.rc_recalled_a_message);
                }
            }
            recallMessageItemProvider$ViewHolder.contentTextView.setText(string);
        }
    }

    public void onItemLongClick(View view, int i, RecallNotificationMessage recallNotificationMessage, UIMessage uIMessage) {
    }

    public Spannable getContentSummary(RecallNotificationMessage recallNotificationMessage) {
        if (recallNotificationMessage == null || TextUtils.isEmpty(recallNotificationMessage.getOperatorId())) {
            return null;
        }
        CharSequence string;
        if (recallNotificationMessage.getOperatorId().equals(RongIM.getInstance().getCurrentUserId())) {
            string = RongContext.getInstance().getString(C4974R.string.rc_you_recalled_a_message);
        } else {
            UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(recallNotificationMessage.getOperatorId());
            if (userInfo == null || userInfo.getName() == null) {
                string = recallNotificationMessage.getOperatorId() + RongContext.getInstance().getString(C4974R.string.rc_recalled_a_message);
            } else {
                string = userInfo.getName() + RongContext.getInstance().getString(C4974R.string.rc_recalled_a_message);
            }
        }
        return new SpannableString(string);
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_information_notification_message, null);
        RecallMessageItemProvider$ViewHolder recallMessageItemProvider$ViewHolder = new RecallMessageItemProvider$ViewHolder(this);
        recallMessageItemProvider$ViewHolder.contentTextView = (TextView) inflate.findViewById(C4974R.id.rc_msg);
        recallMessageItemProvider$ViewHolder.contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        inflate.setTag(recallMessageItemProvider$ViewHolder);
        return inflate;
    }
}
