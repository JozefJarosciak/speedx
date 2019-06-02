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
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.message.InformationNotificationMessage;

@ProviderTag(centerInHorizontal = true, messageContent = InformationNotificationMessage.class, showPortrait = false, showProgress = false, showSummaryWithName = false, showWarning = false)
public class InfoNotificationMsgItemProvider extends MessageProvider<InformationNotificationMessage> {
    public void bindView(View view, int i, InformationNotificationMessage informationNotificationMessage, UIMessage uIMessage) {
        InfoNotificationMsgItemProvider$ViewHolder infoNotificationMsgItemProvider$ViewHolder = (InfoNotificationMsgItemProvider$ViewHolder) view.getTag();
        if (informationNotificationMessage != null && !TextUtils.isEmpty(informationNotificationMessage.getMessage())) {
            infoNotificationMsgItemProvider$ViewHolder.contentTextView.setText(informationNotificationMessage.getMessage());
        }
    }

    public Spannable getContentSummary(InformationNotificationMessage informationNotificationMessage) {
        if (informationNotificationMessage == null || TextUtils.isEmpty(informationNotificationMessage.getMessage())) {
            return null;
        }
        return new SpannableString(informationNotificationMessage.getMessage());
    }

    public void onItemClick(View view, int i, InformationNotificationMessage informationNotificationMessage, UIMessage uIMessage) {
    }

    public void onItemLongClick(View view, int i, InformationNotificationMessage informationNotificationMessage, UIMessage uIMessage) {
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_information_notification_message, null);
        InfoNotificationMsgItemProvider$ViewHolder infoNotificationMsgItemProvider$ViewHolder = new InfoNotificationMsgItemProvider$ViewHolder(this);
        infoNotificationMsgItemProvider$ViewHolder.contentTextView = (TextView) inflate.findViewById(C4974R.id.rc_msg);
        infoNotificationMsgItemProvider$ViewHolder.contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        inflate.setTag(infoNotificationMsgItemProvider$ViewHolder);
        return inflate;
    }
}
