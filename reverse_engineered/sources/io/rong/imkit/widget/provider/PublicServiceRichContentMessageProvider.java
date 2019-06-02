package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongKitIntent;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.PublicServiceRichContentMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@ProviderTag(centerInHorizontal = true, messageContent = PublicServiceRichContentMessage.class, showPortrait = false)
public class PublicServiceRichContentMessageProvider extends MessageProvider<PublicServiceRichContentMessage> {
    private int height;
    private Context mContext;
    private int width;

    public View newView(Context context, ViewGroup viewGroup) {
        this.mContext = context;
        PublicServiceRichContentMessageProvider$ViewHolder publicServiceRichContentMessageProvider$ViewHolder = new PublicServiceRichContentMessageProvider$ViewHolder(this, null);
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_public_service_rich_content_message, null);
        publicServiceRichContentMessageProvider$ViewHolder.title = (TextView) inflate.findViewById(C4974R.id.rc_title);
        publicServiceRichContentMessageProvider$ViewHolder.time = (TextView) inflate.findViewById(C4974R.id.rc_time);
        publicServiceRichContentMessageProvider$ViewHolder.description = (TextView) inflate.findViewById(C4974R.id.rc_content);
        publicServiceRichContentMessageProvider$ViewHolder.imageView = (AsyncImageView) inflate.findViewById(C4974R.id.rc_img);
        int width = ((WindowManager) inflate.getContext().getSystemService("window")).getDefaultDisplay().getWidth() - 35;
        inflate.setLayoutParams(new LayoutParams(width, -2));
        this.width = width - 100;
        this.height = 800;
        inflate.setTag(publicServiceRichContentMessageProvider$ViewHolder);
        return inflate;
    }

    public void bindView(View view, int i, PublicServiceRichContentMessage publicServiceRichContentMessage, UIMessage uIMessage) {
        PublicServiceRichContentMessageProvider$ViewHolder publicServiceRichContentMessageProvider$ViewHolder = (PublicServiceRichContentMessageProvider$ViewHolder) view.getTag();
        PublicServiceRichContentMessage publicServiceRichContentMessage2 = (PublicServiceRichContentMessage) uIMessage.getContent();
        publicServiceRichContentMessageProvider$ViewHolder.title.setText(publicServiceRichContentMessage2.getMessage().getTitle());
        publicServiceRichContentMessageProvider$ViewHolder.description.setText(publicServiceRichContentMessage2.getMessage().getDigest());
        int i2 = this.width;
        i2 = this.height;
        publicServiceRichContentMessageProvider$ViewHolder.imageView.setResource(publicServiceRichContentMessage2.getMessage().getImageUrl(), 0);
        publicServiceRichContentMessageProvider$ViewHolder.time.setText(formatDate(uIMessage.getReceivedTime(), "MM月dd日 HH:mm"));
    }

    private String formatDate(long j, String str) {
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public Spannable getContentSummary(PublicServiceRichContentMessage publicServiceRichContentMessage) {
        return new SpannableString(publicServiceRichContentMessage.getMessage().getTitle());
    }

    public void onItemClick(View view, int i, PublicServiceRichContentMessage publicServiceRichContentMessage, UIMessage uIMessage) {
        String url = publicServiceRichContentMessage.getMessage().getUrl();
        Intent intent = new Intent(RongKitIntent.RONG_INTENT_ACTION_WEBVIEW);
        intent.setPackage(this.mContext.getPackageName());
        intent.putExtra("url", url);
        this.mContext.startActivity(intent);
    }

    public void onItemLongClick(View view, int i, PublicServiceRichContentMessage publicServiceRichContentMessage, UIMessage uIMessage) {
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
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new PublicServiceRichContentMessageProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }
}
