package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.model.UIConversation.UnreadRemindType;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Conversation.ConversationType;

public class ConversationListAdapter extends BaseAdapter<UIConversation> {
    private static final String TAG = "ConversationListAdapter";
    Context mContext;
    LayoutInflater mInflater = LayoutInflater.from(this.mContext);
    private OnPortraitItemClick mOnPortraitItemClick;

    public interface OnPortraitItemClick {
        void onPortraitItemClick(View view, UIConversation uIConversation);

        boolean onPortraitItemLongClick(View view, UIConversation uIConversation);
    }

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

    public ConversationListAdapter(Context context) {
        this.mContext = context;
    }

    public int findGatherPosition(ConversationType conversationType) {
        int count = getCount();
        if (RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
            do {
                int i = count;
                count = i - 1;
                if (i > 0) {
                }
            } while (!((UIConversation) getItem(count)).getConversationType().equals(conversationType));
            return count;
        }
        return -1;
    }

    public int findPosition(ConversationType conversationType, String str) {
        int count = getCount();
        int i;
        if (!RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
            while (true) {
                i = count;
                count = i - 1;
                if (i <= 0) {
                    break;
                } else if (((UIConversation) getItem(count)).getConversationType().equals(conversationType) && ((UIConversation) getItem(count)).getConversationTargetId().equals(str)) {
                    return count;
                }
            }
        } else {
            do {
                i = count;
                count = i - 1;
                if (i > 0) {
                }
            } while (!((UIConversation) getItem(count)).getConversationType().equals(conversationType));
            return count;
        }
        return -1;
    }

    protected View newView(Context context, int i, ViewGroup viewGroup) {
        View inflate = this.mInflater.inflate(C4974R.layout.rc_item_conversation, null);
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

    protected void bindView(View view, int i, final UIConversation uIConversation) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (uIConversation != null) {
            IContainerItemProvider conversationTemplate = RongContext.getInstance().getConversationTemplate(uIConversation.getConversationType().getName());
            if (conversationTemplate == null) {
                RLog.m19420e(TAG, "provider is null");
                return;
            }
            conversationTemplate.bindView(viewHolder.contentView.inflate(conversationTemplate), i, uIConversation);
            if (uIConversation.isTop()) {
                viewHolder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(C4974R.drawable.rc_item_top_list_selector));
            } else {
                viewHolder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(C4974R.drawable.rc_item_list_selector));
            }
            ConversationProviderTag conversationProviderTag = RongContext.getInstance().getConversationProviderTag(uIConversation.getConversationType().getName());
            int i2;
            if (conversationProviderTag.portraitPosition() == 1) {
                viewHolder.leftImageLayout.setVisibility(0);
                if (uIConversation.getConversationType().equals(ConversationType.GROUP)) {
                    i2 = C4974R.drawable.rc_default_group_portrait;
                } else if (uIConversation.getConversationType().equals(ConversationType.DISCUSSION)) {
                    i2 = C4974R.drawable.rc_default_discussion_portrait;
                } else {
                    i2 = C4974R.drawable.rc_default_portrait;
                }
                viewHolder.leftImageLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(view, uIConversation);
                        }
                    }
                });
                viewHolder.leftImageLayout.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(view, uIConversation);
                        }
                        return true;
                    }
                });
                if (uIConversation.getConversationGatherState()) {
                    viewHolder.leftImageView.setAvatar(null, i2);
                } else if (uIConversation.getIconUrl() != null) {
                    viewHolder.leftImageView.setAvatar(uIConversation.getIconUrl().toString(), i2);
                } else {
                    viewHolder.leftImageView.setAvatar(null, i2);
                }
                if (uIConversation.getUnReadMessageCount() > 0) {
                    viewHolder.unReadMsgCountIcon.setVisibility(0);
                    if (uIConversation.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                        if (uIConversation.getUnReadMessageCount() > 99) {
                            viewHolder.unReadMsgCount.setText(this.mContext.getResources().getString(C4974R.string.rc_message_unread_count));
                        } else {
                            viewHolder.unReadMsgCount.setText(Integer.toString(uIConversation.getUnReadMessageCount()));
                        }
                        viewHolder.unReadMsgCount.setVisibility(0);
                        viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_count_bg);
                    } else {
                        viewHolder.unReadMsgCount.setVisibility(8);
                        viewHolder.unReadMsgCountIcon.setImageResource(C4974R.drawable.rc_unread_remind_list_count);
                    }
                } else {
                    viewHolder.unReadMsgCountIcon.setVisibility(8);
                    viewHolder.unReadMsgCount.setVisibility(8);
                }
                viewHolder.rightImageLayout.setVisibility(8);
            } else if (conversationProviderTag.portraitPosition() == 2) {
                viewHolder.rightImageLayout.setVisibility(0);
                viewHolder.rightImageLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(view, uIConversation);
                        }
                    }
                });
                viewHolder.rightImageLayout.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(view, uIConversation);
                        }
                        return true;
                    }
                });
                if (uIConversation.getConversationType().equals(ConversationType.GROUP)) {
                    i2 = C4974R.drawable.rc_default_group_portrait;
                } else if (uIConversation.getConversationType().equals(ConversationType.DISCUSSION)) {
                    i2 = C4974R.drawable.rc_default_discussion_portrait;
                } else {
                    i2 = C4974R.drawable.rc_default_portrait;
                }
                if (uIConversation.getConversationGatherState()) {
                    viewHolder.rightImageView.setAvatar(null, i2);
                } else if (uIConversation.getIconUrl() != null) {
                    viewHolder.rightImageView.setAvatar(uIConversation.getIconUrl().toString(), i2);
                } else {
                    viewHolder.rightImageView.setAvatar(null, i2);
                }
                if (uIConversation.getUnReadMessageCount() > 0) {
                    viewHolder.unReadMsgCountRightIcon.setVisibility(0);
                    if (uIConversation.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                        viewHolder.unReadMsgCount.setVisibility(0);
                        if (uIConversation.getUnReadMessageCount() > 99) {
                            viewHolder.unReadMsgCountRight.setText(this.mContext.getResources().getString(C4974R.string.rc_message_unread_count));
                        } else {
                            viewHolder.unReadMsgCountRight.setText(Integer.toString(uIConversation.getUnReadMessageCount()));
                        }
                        viewHolder.unReadMsgCountRightIcon.setImageResource(C4974R.drawable.rc_unread_count_bg);
                    } else {
                        viewHolder.unReadMsgCount.setVisibility(8);
                        viewHolder.unReadMsgCountRightIcon.setImageResource(C4974R.drawable.rc_unread_remind_without_count);
                    }
                } else {
                    viewHolder.unReadMsgCountIcon.setVisibility(8);
                    viewHolder.unReadMsgCount.setVisibility(8);
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

    public void setOnPortraitItemClick(OnPortraitItemClick onPortraitItemClick) {
        this.mOnPortraitItemClick = onPortraitItemClick;
    }
}
