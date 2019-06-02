package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.text.ClipboardManager;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imkit.model.LinkTextView;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.imkit.widget.ILinkClickListener;
import io.rong.imkit.widget.LinkTextViewMovementMethod;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class EvaluateTextMessageItemProvider extends MessageProvider<TextMessage> {

    /* renamed from: io.rong.imkit.widget.provider.EvaluateTextMessageItemProvider$5 */
    class C51805 implements OnClickListener {
        C51805() {
        }

        public void onClick(View view) {
        }
    }

    class ViewHolder {
        ImageView iv_complete;
        ImageView iv_no;
        ImageView iv_yes;
        RelativeLayout layout_praise;
        boolean longClick;
        LinkTextView message;
        TextView tv_prompt;

        ViewHolder() {
        }
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_text_message_evaluate, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.message = (LinkTextView) inflate.findViewById(C4974R.id.evaluate_text);
        viewHolder.tv_prompt = (TextView) inflate.findViewById(C4974R.id.tv_prompt);
        viewHolder.iv_yes = (ImageView) inflate.findViewById(C4974R.id.iv_yes);
        viewHolder.iv_no = (ImageView) inflate.findViewById(C4974R.id.iv_no);
        viewHolder.iv_complete = (ImageView) inflate.findViewById(C4974R.id.iv_complete);
        viewHolder.layout_praise = (RelativeLayout) inflate.findViewById(C4974R.id.layout_praise);
        inflate.setTag(viewHolder);
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

    public void onItemLongClick(final View view, int i, final TextMessage textMessage, final UIMessage uIMessage) {
        ((ViewHolder) view.getTag()).longClick = true;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null && (text instanceof Spannable)) {
                Selection.removeSelection((Spannable) text);
            }
        }
        String str = null;
        if (uIMessage.getConversationType().getName().equals(ConversationType.APP_PUBLIC_SERVICE.getName()) || uIMessage.getConversationType().getName().equals(ConversationType.PUBLIC_SERVICE.getName())) {
            PublicServiceProfile publicServiceProfile = RongUserInfoManager.getInstance().getPublicServiceProfile(PublicServiceType.setValue(uIMessage.getConversationType().getValue()), uIMessage.getTargetId());
            if (publicServiceProfile != null) {
                str = publicServiceProfile.getName();
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
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_copy), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new OnArraysDialogItemListener() {
            public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    ((ClipboardManager) view.getContext().getSystemService("clipboard")).setText(textMessage.getContent());
                } else if (i == 1) {
                    RongIM.getInstance().getRongIMClient().deleteMessages(new int[]{uIMessage.getMessageId()}, null);
                }
            }
        }).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void bindView(final View view, int i, TextMessage textMessage, final UIMessage uIMessage) {
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
            view.setBackgroundResource(C4974R.drawable.rc_ic_bubble_right);
        } else {
            view.setBackgroundResource(C4974R.drawable.rc_ic_bubble_left);
        }
        if (uIMessage.getEvaluated()) {
            viewHolder.iv_yes.setVisibility(8);
            viewHolder.iv_no.setVisibility(8);
            viewHolder.iv_complete.setVisibility(0);
            viewHolder.tv_prompt.setText("感谢您的评价");
        } else {
            viewHolder.iv_yes.setVisibility(0);
            viewHolder.iv_no.setVisibility(0);
            viewHolder.iv_complete.setVisibility(8);
            viewHolder.tv_prompt.setText("您对我的回答");
        }
        viewHolder.iv_yes.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Object extra = ((TextMessage) uIMessage.getContent()).getExtra();
                String str = "";
                if (!TextUtils.isEmpty(extra)) {
                    try {
                        str = new JSONObject(extra).optString("sid");
                    } catch (JSONException e) {
                    }
                }
                RongIMClient.getInstance().evaluateCustomService(uIMessage.getSenderUserId(), true, str);
                viewHolder.iv_complete.setVisibility(0);
                viewHolder.iv_yes.setVisibility(8);
                viewHolder.iv_no.setVisibility(8);
                viewHolder.tv_prompt.setText("感谢您的评价");
                uIMessage.setEvaluated(true);
            }
        });
        viewHolder.iv_no.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Object extra = ((TextMessage) uIMessage.getContent()).getExtra();
                String str = "";
                if (!TextUtils.isEmpty(extra)) {
                    try {
                        str = new JSONObject(extra).optString("sid");
                    } catch (JSONException e) {
                    }
                }
                RongIMClient.getInstance().evaluateCustomService(uIMessage.getSenderUserId(), false, str);
                viewHolder.iv_complete.setVisibility(0);
                viewHolder.iv_yes.setVisibility(8);
                viewHolder.iv_no.setVisibility(8);
                viewHolder.tv_prompt.setText("感谢您的评价");
                uIMessage.setEvaluated(true);
            }
        });
        final TextView textView = viewHolder.message;
        if (uIMessage.getTextMessageContent() != null) {
            int length = uIMessage.getTextMessageContent().length();
            if (view.getHandler() == null || length <= 500) {
                textView.setText(uIMessage.getTextMessageContent());
            } else {
                view.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        textView.setText(uIMessage.getTextMessageContent());
                    }
                }, 50);
            }
        }
        viewHolder.message.setClickable(true);
        viewHolder.message.setOnClickListener(new C51805());
        final View view2 = view;
        final int i2 = i;
        final TextMessage textMessage2 = textMessage;
        final UIMessage uIMessage2 = uIMessage;
        viewHolder.message.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                EvaluateTextMessageItemProvider.this.onItemLongClick(view2, i2, textMessage2, uIMessage2);
                return false;
            }
        });
        viewHolder.message.setMovementMethod(new LinkTextViewMovementMethod(new ILinkClickListener() {
            public boolean onLinkClick(String str) {
                ConversationBehaviorListener conversationBehaviorListener = RongContext.getInstance().getConversationBehaviorListener();
                if (conversationBehaviorListener != null) {
                    return conversationBehaviorListener.onMessageLinkClick(view.getContext(), str);
                }
                return false;
            }
        }));
    }
}
