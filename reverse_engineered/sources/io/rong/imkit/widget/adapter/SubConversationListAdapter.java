package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.model.UIConversation.UnreadRemindType;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Conversation.ConversationType;

public class SubConversationListAdapter extends BaseAdapter<UIConversation> {
    Context mContext;
    LayoutInflater mInflater = LayoutInflater.from(this.mContext);

    class ViewHolder {
        ProviderContainerView contentView;
        View layout;
        View leftImageLayout;
        AsyncImageView leftImageView;
        View rightImageLayout;
        AsyncImageView rightImageView;
        TextView unReadMsgCount;
        ImageView unReadMsgCountIcon;
        TextView unReadMsgCountRight;
        ImageView unReadMsgCountRightIcon;

        ViewHolder() {
        }
    }

    public long getItemId(int i) {
        UIConversation uIConversation = (UIConversation) getItem(i);
        if (uIConversation == null) {
            return 0;
        }
        return (long) uIConversation.hashCode();
    }

    public SubConversationListAdapter(Context context) {
        this.mContext = context;
    }

    public int findPosition(ConversationType conversationType, String str) {
        int count = getCount();
        while (true) {
            int i = count - 1;
            if (count <= 0) {
                return -1;
            }
            if (((UIConversation) getItem(i)).getConversationType().equals(conversationType) && ((UIConversation) getItem(i)).getConversationTargetId().equals(str)) {
                return i;
            }
            count = i;
        }
    }

    protected View newView(Context context, int i, ViewGroup viewGroup) {
        View inflate = this.mInflater.inflate(C4974R.layout.rc_item_conversation, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.layout = findViewById(inflate, C4974R.id.rc_item_conversation);
        viewHolder.leftImageLayout = findViewById(inflate, C4974R.id.rc_item1);
        viewHolder.rightImageLayout = findViewById(inflate, C4974R.id.rc_item2);
        viewHolder.leftImageView = (AsyncImageView) findViewById(inflate, C4974R.id.rc_left);
        viewHolder.rightImageView = (AsyncImageView) findViewById(inflate, C4974R.id.rc_right);
        viewHolder.contentView = (ProviderContainerView) findViewById(inflate, C4974R.id.rc_content);
        viewHolder.unReadMsgCount = (TextView) findViewById(inflate, C4974R.id.rc_unread_message);
        viewHolder.unReadMsgCountRight = (TextView) findViewById(inflate, C4974R.id.rc_unread_message_right);
        viewHolder.unReadMsgCountIcon = (ImageView) findViewById(inflate, C4974R.id.rc_unread_message_icon);
        viewHolder.unReadMsgCountRightIcon = (ImageView) findViewById(inflate, C4974R.id.rc_unread_message_icon_right);
        inflate.setTag(viewHolder);
        return inflate;
    }

    protected void bindView(View view, int i, UIConversation uIConversation) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        IContainerItemProvider conversationTemplate = RongContext.getInstance().getConversationTemplate(uIConversation.getConversationType().getName());
        conversationTemplate.bindView(viewHolder.contentView.inflate(conversationTemplate), i, uIConversation);
        if (uIConversation.isTop()) {
            viewHolder.layout.setBackgroundColor(this.mContext.getResources().getColor(C4974R.color.rc_conversation_top_bg));
        } else {
            viewHolder.layout.setBackgroundColor(this.mContext.getResources().getColor(C4974R.color.rc_text_color_primary_inverse));
        }
        ConversationProviderTag conversationProviderTag = RongContext.getInstance().getConversationProviderTag(uIConversation.getConversationType().getName());
        int i2;
        if (conversationProviderTag.portraitPosition() == 1) {
            viewHolder.leftImageLayout.setVisibility(0);
            if (uIConversation.getConversationType() == ConversationType.GROUP) {
                i2 = C4974R.drawable.rc_default_group_portrait;
            } else if (uIConversation.getConversationType() == ConversationType.DISCUSSION) {
                i2 = C4974R.drawable.rc_default_discussion_portrait;
            } else {
                i2 = C4974R.drawable.rc_default_portrait;
            }
            if (uIConversation.getIconUrl() != null) {
                viewHolder.leftImageView.setAvatar(uIConversation.getIconUrl().toString(), i2);
            } else {
                viewHolder.leftImageView.setAvatar(null, i2);
            }
            if (uIConversation.getUnReadMessageCount() > 0) {
                viewHolder.unReadMsgCountIcon.setVisibility(0);
                if (uIConversation.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                    viewHolder.unReadMsgCount.setVisibility(0);
                    if (uIConversation.getUnReadMessageCount() > 99) {
                        viewHolder.unReadMsgCount.setText(this.mContext.getResources().getString(C4974R.string.rc_message_unread_count));
                    } else {
                        viewHolder.unReadMsgCount.setText(Integer.toString(uIConversation.getUnReadMessageCount()));
                    }
                    viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_count_bg);
                } else {
                    viewHolder.unReadMsgCount.setVisibility(8);
                    viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_remind_without_count);
                }
            } else {
                viewHolder.unReadMsgCountIcon.setVisibility(8);
                viewHolder.unReadMsgCount.setVisibility(8);
            }
            viewHolder.rightImageLayout.setVisibility(8);
        } else if (conversationProviderTag.portraitPosition() == 2) {
            viewHolder.rightImageLayout.setVisibility(0);
            if (uIConversation.getConversationType() == ConversationType.GROUP) {
                i2 = C4974R.drawable.rc_default_group_portrait;
            } else if (uIConversation.getConversationType() == ConversationType.DISCUSSION) {
                i2 = C4974R.drawable.rc_default_discussion_portrait;
            } else {
                i2 = C4974R.drawable.rc_default_portrait;
            }
            if (uIConversation.getIconUrl() != null) {
                viewHolder.rightImageView.setAvatar(uIConversation.getIconUrl().toString(), i2);
            } else {
                viewHolder.rightImageView.setAvatar(null, i2);
            }
            if (uIConversation.getUnReadMessageCount() > 0) {
                viewHolder.unReadMsgCountRight.setVisibility(0);
                viewHolder.unReadMsgCountIcon.setVisibility(0);
                if (uIConversation.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                    if (uIConversation.getUnReadMessageCount() > 99) {
                        viewHolder.unReadMsgCountRight.setText(this.mContext.getResources().getString(C4974R.string.rc_message_unread_count));
                    } else {
                        viewHolder.unReadMsgCountRight.setText(Integer.toString(uIConversation.getUnReadMessageCount()));
                    }
                    viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_count_bg);
                } else {
                    viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_remind_without_count);
                }
            }
            viewHolder.leftImageLayout.setVisibility(8);
        } else if (conversationProviderTag.portraitPosition() == 3) {
            viewHolder.rightImageLayout.setVisibility(8);
            viewHolder.leftImageLayout.setVisibility(8);
        } else {
            throw new IllegalArgumentException("the portrait position is wrong!");
        }
    }
}
