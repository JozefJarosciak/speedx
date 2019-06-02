package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.model.UserInfo;

public class ConversationAddMemberAdapter extends BaseAdapter<UserInfo> {
    Boolean isDeleteState = Boolean.valueOf(false);
    String mCreatorId = null;
    private OnDeleteIconListener mDeleteIconListener;
    LayoutInflater mInflater;

    public interface OnDeleteIconListener {
        void onDeleteIconClick(View view, int i);
    }

    class ViewHolder {
        ImageView mDeleteIcon;
        ImageView mMemberDeIcon;
        AsyncImageView mMemberIcon;
        TextView mMemberName;

        ViewHolder() {
        }
    }

    public ConversationAddMemberAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.isDeleteState = Boolean.valueOf(false);
    }

    protected View newView(Context context, int i, ViewGroup viewGroup) {
        View inflate = this.mInflater.inflate(C4974R.layout.rc_item_conversation_member, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mMemberIcon = (AsyncImageView) findViewById(inflate, 16908294);
        viewHolder.mMemberName = (TextView) findViewById(inflate, 16908308);
        viewHolder.mDeleteIcon = (ImageView) findViewById(inflate, 16908295);
        viewHolder.mMemberDeIcon = (ImageView) findViewById(inflate, 16908296);
        inflate.setTag(viewHolder);
        return inflate;
    }

    protected void bindView(View view, final int i, UserInfo userInfo) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (userInfo.getUserId().equals("RongAddBtn") || userInfo.getUserId().equals("RongDelBtn")) {
            viewHolder.mMemberIcon.setVisibility(4);
            viewHolder.mMemberDeIcon.setVisibility(0);
            if (userInfo.getUserId().equals("RongAddBtn")) {
                viewHolder.mMemberDeIcon.setImageResource(C4974R.drawable.rc_ic_setting_friends_add);
            } else {
                viewHolder.mMemberDeIcon.setImageResource(C4974R.drawable.rc_ic_setting_friends_delete);
            }
            viewHolder.mMemberName.setVisibility(4);
            viewHolder.mDeleteIcon.setVisibility(8);
            return;
        }
        viewHolder.mMemberIcon.setVisibility(0);
        viewHolder.mMemberDeIcon.setVisibility(8);
        if (userInfo.getPortraitUri() != null) {
            viewHolder.mMemberIcon.setResource(userInfo.getPortraitUri().toString(), C4974R.drawable.rc_default_portrait);
        }
        if (userInfo.getName() != null) {
            viewHolder.mMemberName.setText(userInfo.getName());
        } else {
            viewHolder.mMemberName.setText("");
        }
        if (!isDeleteState() || userInfo.getUserId().equals(getCreatorId())) {
            viewHolder.mDeleteIcon.setVisibility(4);
            return;
        }
        viewHolder.mDeleteIcon.setVisibility(0);
        viewHolder.mDeleteIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ConversationAddMemberAdapter.this.mDeleteIconListener != null) {
                    ConversationAddMemberAdapter.this.mDeleteIconListener.onDeleteIconClick(view, i);
                }
            }
        });
    }

    public long getItemId(int i) {
        UserInfo userInfo = (UserInfo) getItem(i);
        if (userInfo == null) {
            return 0;
        }
        return (long) userInfo.hashCode();
    }

    public void setDeleteState(boolean z) {
        this.isDeleteState = Boolean.valueOf(z);
    }

    public boolean isDeleteState() {
        return this.isDeleteState.booleanValue();
    }

    public void setCreatorId(String str) {
        this.mCreatorId = str;
    }

    public String getCreatorId() {
        return this.mCreatorId;
    }

    public void setDeleteIconListener(OnDeleteIconListener onDeleteIconListener) {
        this.mDeleteIconListener = onDeleteIconListener;
    }
}
