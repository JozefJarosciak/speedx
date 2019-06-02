package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import io.rong.message.PublicServiceMultiRichContentMessage;
import io.rong.message.RichContentItem;
import java.util.ArrayList;

@ProviderTag(centerInHorizontal = true, messageContent = PublicServiceMultiRichContentMessage.class, showPortrait = false)
public class PublicServiceMultiRichContentMessageProvider extends MessageProvider<PublicServiceMultiRichContentMessage> {
    private C5188xa38e6656 mAdapter;
    private Context mContext;

    public void bindView(View view, int i, PublicServiceMultiRichContentMessage publicServiceMultiRichContentMessage, UIMessage uIMessage) {
        PublicServiceMultiRichContentMessageProvider$ViewHolder publicServiceMultiRichContentMessageProvider$ViewHolder = (PublicServiceMultiRichContentMessageProvider$ViewHolder) view.getTag();
        ArrayList messages = publicServiceMultiRichContentMessage.getMessages();
        if (messages.size() > 0) {
            publicServiceMultiRichContentMessageProvider$ViewHolder.tv.setText(((RichContentItem) messages.get(0)).getTitle());
            publicServiceMultiRichContentMessageProvider$ViewHolder.iv.setResource(((RichContentItem) messages.get(0)).getImageUrl(), 0);
        }
        LayoutParams layoutParams = view.getLayoutParams();
        this.mAdapter = new C5188xa38e6656(this, this.mContext, messages);
        publicServiceMultiRichContentMessageProvider$ViewHolder.lv.setAdapter(this.mAdapter);
        publicServiceMultiRichContentMessageProvider$ViewHolder.lv.setOnItemClickListener(new PublicServiceMultiRichContentMessageProvider$1(this, messages));
        layoutParams.height = publicServiceMultiRichContentMessageProvider$ViewHolder.height + getListViewHeight(publicServiceMultiRichContentMessageProvider$ViewHolder.lv);
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    private int getListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int i = 0;
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            View view = adapter.getView(i2, null, listView);
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            i = (i + view.getMeasuredHeight()) + 2;
        }
        return i;
    }

    public Spannable getContentSummary(PublicServiceMultiRichContentMessage publicServiceMultiRichContentMessage) {
        if (publicServiceMultiRichContentMessage.getMessages().size() > 0) {
            return new SpannableString(((RichContentItem) publicServiceMultiRichContentMessage.getMessages().get(0)).getTitle());
        }
        return null;
    }

    public void onItemClick(View view, int i, PublicServiceMultiRichContentMessage publicServiceMultiRichContentMessage, UIMessage uIMessage) {
        if (publicServiceMultiRichContentMessage.getMessages().size() != 0) {
            String url = ((RichContentItem) publicServiceMultiRichContentMessage.getMessages().get(0)).getUrl();
            Intent intent = new Intent(RongKitIntent.RONG_INTENT_ACTION_WEBVIEW);
            intent.setPackage(this.mContext.getPackageName());
            intent.putExtra("url", url);
            this.mContext.startActivity(intent);
        }
    }

    public void onItemLongClick(View view, int i, PublicServiceMultiRichContentMessage publicServiceMultiRichContentMessage, UIMessage uIMessage) {
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
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new PublicServiceMultiRichContentMessageProvider$2(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public View newView(Context context, ViewGroup viewGroup) {
        this.mContext = context;
        PublicServiceMultiRichContentMessageProvider$ViewHolder publicServiceMultiRichContentMessageProvider$ViewHolder = new PublicServiceMultiRichContentMessageProvider$ViewHolder(this, null);
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_public_service_multi_rich_content_message, null);
        publicServiceMultiRichContentMessageProvider$ViewHolder.lv = (ListView) inflate.findViewById(C4974R.id.rc_list);
        publicServiceMultiRichContentMessageProvider$ViewHolder.iv = (AsyncImageView) inflate.findViewById(C4974R.id.rc_img);
        publicServiceMultiRichContentMessageProvider$ViewHolder.tv = (TextView) inflate.findViewById(C4974R.id.rc_txt);
        inflate.measure(0, 0);
        publicServiceMultiRichContentMessageProvider$ViewHolder.height = inflate.getMeasuredHeight();
        inflate.setTag(publicServiceMultiRichContentMessageProvider$ViewHolder);
        return inflate;
    }
}
