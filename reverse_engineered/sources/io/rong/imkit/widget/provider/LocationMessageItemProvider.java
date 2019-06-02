package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.LocationMessage;

@ProviderTag(messageContent = LocationMessage.class)
public class LocationMessageItemProvider extends MessageProvider<LocationMessage> {
    private static final String TAG = "LocationMessageItemProvider";

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_location_message, null);
        LocationMessageItemProvider$ViewHolder locationMessageItemProvider$ViewHolder = new LocationMessageItemProvider$ViewHolder(this);
        locationMessageItemProvider$ViewHolder.img = (AsyncImageView) inflate.findViewById(C4974R.id.rc_img);
        locationMessageItemProvider$ViewHolder.title = (TextView) inflate.findViewById(C4974R.id.rc_content);
        locationMessageItemProvider$ViewHolder.mLayout = (FrameLayout) inflate.findViewById(C4974R.id.rc_layout);
        inflate.setTag(locationMessageItemProvider$ViewHolder);
        return inflate;
    }

    public void onItemClick(View view, int i, LocationMessage locationMessage, UIMessage uIMessage) {
    }

    public void onItemLongClick(View view, int i, LocationMessage locationMessage, UIMessage uIMessage) {
        int i2;
        boolean z;
        int integer;
        NotFoundException e;
        String[] strArr;
        String str = null;
        if (!uIMessage.getConversationType().getName().equals(ConversationType.APP_PUBLIC_SERVICE.getName()) && !uIMessage.getConversationType().getName().equals(ConversationType.PUBLIC_SERVICE.getName())) {
            UserInfo userInfo = uIMessage.getUserInfo();
            if (userInfo == null) {
                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
            }
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
        long currentTimeMillis = System.currentTimeMillis() - RongIM.getInstance().getDeltaTime();
        if (uIMessage.getSentStatus().equals(Message$SentStatus.SENDING) || uIMessage.getSentStatus().equals(Message$SentStatus.FAILED)) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        try {
            z = RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_enable_message_recall);
            try {
                integer = RongContext.getInstance().getResources().getInteger(C4974R.integer.rc_message_recall_interval);
            } catch (NotFoundException e2) {
                e = e2;
                RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
                e.printStackTrace();
                integer = -1;
                if (i2 == 0) {
                }
                ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new LocationMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
            }
        } catch (NotFoundException e3) {
            e = e3;
            z = false;
            RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
            e.printStackTrace();
            integer = -1;
            if (i2 == 0) {
            }
            ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new LocationMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
        }
        strArr = (i2 == 0 && z && currentTimeMillis - uIMessage.getSentTime() <= ((long) (integer * 1000)) && uIMessage.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())) ? new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_recall)} : new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)};
        ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new LocationMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void bindView(View view, int i, LocationMessage locationMessage, UIMessage uIMessage) {
        LocationMessageItemProvider$ViewHolder locationMessageItemProvider$ViewHolder = (LocationMessageItemProvider$ViewHolder) view.getTag();
        Uri imgUri = locationMessage.getImgUri();
        RLog.d(TAG, "uri = " + imgUri);
        if (imgUri == null || !imgUri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
            locationMessageItemProvider$ViewHolder.img.setResource(null);
        } else {
            locationMessageItemProvider$ViewHolder.img.setResource(imgUri);
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(locationMessageItemProvider$ViewHolder.title.getLayoutParams().width, -2);
        layoutParams.gravity = 80;
        if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
            locationMessageItemProvider$ViewHolder.mLayout.setBackgroundResource(C4974R.drawable.rc_ic_bubble_no_right);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = (int) (12.0f * view.getResources().getDisplayMetrics().density);
            locationMessageItemProvider$ViewHolder.title.setLayoutParams(layoutParams);
        } else {
            layoutParams.leftMargin = (int) (8.5d * ((double) view.getResources().getDisplayMetrics().density));
            layoutParams.rightMargin = 0;
            locationMessageItemProvider$ViewHolder.title.setLayoutParams(layoutParams);
            locationMessageItemProvider$ViewHolder.mLayout.setBackgroundResource(C4974R.drawable.rc_ic_bubble_no_left);
        }
        locationMessageItemProvider$ViewHolder.title.setText(locationMessage.getPoi());
    }

    public Spannable getContentSummary(LocationMessage locationMessage) {
        return new SpannableString(RongContext.getInstance().getResources().getString(C4974R.string.rc_message_content_location));
    }
}
