package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.avos.avoscloud.AVStatus;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.activity.PicturePagerActivity;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;

@ProviderTag(messageContent = ImageMessage.class, showProgress = false)
public class ImageMessageItemProvider extends MessageProvider<ImageMessage> {
    private static final String TAG = "ImageMessageItemProvider";

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_image_message, null);
        ImageMessageItemProvider$ViewHolder imageMessageItemProvider$ViewHolder = new ImageMessageItemProvider$ViewHolder(this);
        imageMessageItemProvider$ViewHolder.message = (TextView) inflate.findViewById(C4974R.id.rc_msg);
        imageMessageItemProvider$ViewHolder.img = (AsyncImageView) inflate.findViewById(C4974R.id.rc_img);
        inflate.setTag(imageMessageItemProvider$ViewHolder);
        return inflate;
    }

    public void onItemClick(View view, int i, ImageMessage imageMessage, UIMessage uIMessage) {
        if (imageMessage != null) {
            Intent intent = new Intent(view.getContext(), PicturePagerActivity.class);
            intent.putExtra(AVStatus.MESSAGE_TAG, uIMessage);
            view.getContext().startActivity(intent);
        }
    }

    public void onItemLongClick(View view, int i, ImageMessage imageMessage, UIMessage uIMessage) {
        boolean z;
        int integer;
        NotFoundException e;
        String[] strArr;
        String str = null;
        if (uIMessage.getSenderUserId() != null) {
            UserInfo userInfo = uIMessage.getUserInfo();
            if (userInfo == null) {
                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
            }
            if (userInfo != null) {
                str = userInfo.getName();
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - RongIM.getInstance().getDeltaTime();
        int i2 = (uIMessage.getSentStatus().equals(Message$SentStatus.SENDING) || uIMessage.getSentStatus().equals(Message$SentStatus.FAILED)) ? 0 : 1;
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
                ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new ImageMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
            }
        } catch (NotFoundException e3) {
            e = e3;
            z = false;
            RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
            e.printStackTrace();
            integer = -1;
            if (i2 == 0) {
            }
            ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new ImageMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
        }
        strArr = (i2 == 0 && z && currentTimeMillis - uIMessage.getSentTime() <= ((long) (integer * 1000)) && uIMessage.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())) ? new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_recall)} : new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)};
        ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new ImageMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void bindView(View view, int i, ImageMessage imageMessage, UIMessage uIMessage) {
        ImageMessageItemProvider$ViewHolder imageMessageItemProvider$ViewHolder = (ImageMessageItemProvider$ViewHolder) view.getTag();
        if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
            view.setBackgroundResource(C4974R.drawable.rc_ic_bubble_no_right);
        } else {
            view.setBackgroundResource(C4974R.drawable.rc_ic_bubble_no_left);
        }
        imageMessageItemProvider$ViewHolder.img.setResource(imageMessage.getThumUri());
        int progress = uIMessage.getProgress();
        if (!uIMessage.getSentStatus().equals(Message$SentStatus.SENDING) || progress >= 100) {
            imageMessageItemProvider$ViewHolder.message.setVisibility(8);
            return;
        }
        if (progress == 0) {
            imageMessageItemProvider$ViewHolder.message.setText(RongContext.getInstance().getResources().getString(C4974R.string.rc_waiting));
        } else {
            imageMessageItemProvider$ViewHolder.message.setText(progress + "%");
        }
        imageMessageItemProvider$ViewHolder.message.setVisibility(0);
    }

    public Spannable getContentSummary(ImageMessage imageMessage) {
        return new SpannableString(RongContext.getInstance().getResources().getString(C4974R.string.rc_message_content_image));
    }
}
