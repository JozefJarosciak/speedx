package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.Event.InputViewEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.RongDateUtils;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UnknownMessage;
import io.rong.imlib.model.UserInfo;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.RecallNotificationMessage;
import io.rong.message.TextMessage;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageListAdapter extends BaseAdapter<UIMessage> {
    boolean evaForRobot = false;
    Context mContext;
    Drawable mDefaultDrawable;
    LayoutInflater mInflater;
    OnItemHandlerListener mOnItemHandlerListener;
    boolean robotMode = true;
    View subView;
    private boolean timeGone = false;

    public interface OnItemHandlerListener {
        void onWarningViewClick(int i, Message message, View view);
    }

    class ViewHolder {
        ProviderContainerView contentView;
        ViewGroup layout;
        AsyncImageView leftIconView;
        TextView nameView;
        ProgressBar progressBar;
        ImageView readReceipt;
        AsyncImageView rightIconView;
        TextView sentStatus;
        TextView time;
        ImageView warning;

        ViewHolder() {
        }
    }

    public MessageListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mDefaultDrawable = context.getResources().getDrawable(C4974R.drawable.rc_ic_def_msg_portrait);
    }

    public void setOnItemHandlerListener(OnItemHandlerListener onItemHandlerListener) {
        this.mOnItemHandlerListener = onItemHandlerListener;
    }

    public long getItemId(int i) {
        Message message = (Message) getItem(i);
        if (message == null) {
            return -1;
        }
        return (long) message.getMessageId();
    }

    protected View newView(Context context, int i, ViewGroup viewGroup) {
        View inflate = this.mInflater.inflate(C4974R.layout.rc_item_message, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.leftIconView = (AsyncImageView) findViewById(inflate, C4974R.id.rc_left);
        viewHolder.rightIconView = (AsyncImageView) findViewById(inflate, C4974R.id.rc_right);
        viewHolder.nameView = (TextView) findViewById(inflate, C4974R.id.rc_title);
        viewHolder.contentView = (ProviderContainerView) findViewById(inflate, C4974R.id.rc_content);
        viewHolder.layout = (ViewGroup) findViewById(inflate, C4974R.id.rc_layout);
        viewHolder.progressBar = (ProgressBar) findViewById(inflate, C4974R.id.rc_progress);
        viewHolder.warning = (ImageView) findViewById(inflate, C4974R.id.rc_warning);
        viewHolder.readReceipt = (ImageView) findViewById(inflate, C4974R.id.rc_read_receipt);
        viewHolder.time = (TextView) findViewById(inflate, C4974R.id.rc_time);
        viewHolder.sentStatus = (TextView) findViewById(inflate, C4974R.id.rc_sent_status);
        if (viewHolder.time.getVisibility() == 8) {
            this.timeGone = true;
        } else {
            this.timeGone = false;
        }
        inflate.setTag(viewHolder);
        return inflate;
    }

    private boolean getNeedEvaluate(UIMessage uIMessage) {
        String str = "";
        String str2 = "";
        String str3 = "";
        if (!(uIMessage == null || uIMessage.getConversationType() == null || !uIMessage.getConversationType().equals(ConversationType.CUSTOMER_SERVICE))) {
            Object extra;
            Object obj;
            if (uIMessage.getContent() instanceof TextMessage) {
                extra = ((TextMessage) uIMessage.getContent()).getExtra();
                if (TextUtils.isEmpty(extra)) {
                    return false;
                }
                try {
                    JSONObject jSONObject = new JSONObject(extra);
                    str2 = jSONObject.optString("robotEva");
                    CharSequence optString = jSONObject.optString("sid");
                    CharSequence charSequence = str2;
                } catch (JSONException e) {
                    String str4 = str3;
                    obj = str2;
                    extra = str4;
                }
            } else {
                extra = str3;
                obj = str2;
            }
            if (uIMessage.getMessageDirection() == Message$MessageDirection.RECEIVE && (uIMessage.getContent() instanceof TextMessage) && this.evaForRobot && this.robotMode && !TextUtils.isEmpty(r1) && !TextUtils.isEmpty(r0) && !uIMessage.getIsHistoryMessage()) {
                return true;
            }
        }
        return false;
    }

    protected void bindView(View view, final int i, final UIMessage uIMessage) {
        if (uIMessage != null) {
            IContainerItemProvider evaluateProvider;
            ProviderTag messageProviderTag;
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (getNeedEvaluate(uIMessage)) {
                evaluateProvider = RongContext.getInstance().getEvaluateProvider();
                messageProviderTag = RongContext.getInstance().getMessageProviderTag(uIMessage.getContent().getClass());
            } else if (RongContext.getInstance() == null || uIMessage == null || uIMessage.getContent() == null) {
                RLog.m19420e("MessageListAdapter", "Message is null !");
                return;
            } else {
                evaluateProvider = RongContext.getInstance().getMessageTemplate(uIMessage.getContent().getClass());
                if (evaluateProvider == null) {
                    evaluateProvider = RongContext.getInstance().getMessageTemplate(UnknownMessage.class);
                    messageProviderTag = RongContext.getInstance().getMessageProviderTag(UnknownMessage.class);
                } else {
                    messageProviderTag = RongContext.getInstance().getMessageProviderTag(uIMessage.getContent().getClass());
                }
                if (evaluateProvider == null) {
                    RLog.m19420e("MessageListAdapter", uIMessage.getObjectName() + " message provider not found !");
                    return;
                }
            }
            View inflate = viewHolder.contentView.inflate(evaluateProvider);
            evaluateProvider.bindView(inflate, i, uIMessage);
            this.subView = inflate;
            if (messageProviderTag == null) {
                RLog.m19420e("MessageListAdapter", "Can not find ProviderTag for " + uIMessage.getObjectName());
                return;
            }
            UserInfo userInfo;
            if (messageProviderTag.hide()) {
                viewHolder.contentView.setVisibility(8);
                viewHolder.time.setVisibility(8);
                viewHolder.nameView.setVisibility(8);
                viewHolder.leftIconView.setVisibility(8);
                viewHolder.rightIconView.setVisibility(8);
            } else {
                viewHolder.contentView.setVisibility(0);
            }
            if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
                if (messageProviderTag.showPortrait()) {
                    viewHolder.rightIconView.setVisibility(0);
                    viewHolder.leftIconView.setVisibility(8);
                } else {
                    viewHolder.leftIconView.setVisibility(8);
                    viewHolder.rightIconView.setVisibility(8);
                }
                if (messageProviderTag.centerInHorizontal()) {
                    setGravity(viewHolder.layout, 17);
                    viewHolder.contentView.containerViewCenter();
                    viewHolder.nameView.setGravity(1);
                    viewHolder.contentView.setBackgroundColor(0);
                } else {
                    setGravity(viewHolder.layout, 5);
                    viewHolder.contentView.containerViewRight();
                    viewHolder.nameView.setGravity(5);
                }
                boolean readReceipt = RongIMClient.getInstance().getReadReceipt();
                if (uIMessage.getSentStatus() == Message$SentStatus.SENDING) {
                    if (messageProviderTag.showProgress()) {
                        viewHolder.progressBar.setVisibility(0);
                    } else {
                        viewHolder.progressBar.setVisibility(8);
                    }
                    viewHolder.warning.setVisibility(8);
                    viewHolder.readReceipt.setVisibility(8);
                } else if (uIMessage.getSentStatus() == Message$SentStatus.FAILED) {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.warning.setVisibility(0);
                    viewHolder.readReceipt.setVisibility(8);
                } else if (uIMessage.getSentStatus() == Message$SentStatus.SENT) {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.warning.setVisibility(8);
                    viewHolder.readReceipt.setVisibility(8);
                } else if (readReceipt && uIMessage.getSentStatus() == Message$SentStatus.READ) {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.warning.setVisibility(8);
                    MessageContent content = uIMessage.getMessage().getContent();
                    if ((content instanceof InformationNotificationMessage) || (content instanceof RecallNotificationMessage)) {
                        viewHolder.readReceipt.setVisibility(8);
                    } else {
                        viewHolder.readReceipt.setVisibility(0);
                    }
                } else {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.warning.setVisibility(8);
                    viewHolder.readReceipt.setVisibility(8);
                }
                if (uIMessage.getObjectName().equals("RC:VSTMsg")) {
                    viewHolder.readReceipt.setVisibility(8);
                }
                viewHolder.nameView.setVisibility(8);
                viewHolder.rightIconView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (RongContext.getInstance().getConversationBehaviorListener() != null) {
                            UserInfo userInfo;
                            if (TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                                userInfo = null;
                            } else {
                                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                                if (userInfo == null) {
                                    userInfo = new UserInfo(uIMessage.getSenderUserId(), null, null);
                                }
                            }
                            RongContext.getInstance().getConversationBehaviorListener().onUserPortraitClick(MessageListAdapter.this.mContext, uIMessage.getConversationType(), userInfo);
                        }
                    }
                });
                viewHolder.rightIconView.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (RongContext.getInstance().getConversationBehaviorListener() == null) {
                            return true;
                        }
                        UserInfo userInfo;
                        if (TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                            userInfo = null;
                        } else {
                            userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                            if (userInfo == null) {
                                userInfo = new UserInfo(uIMessage.getSenderUserId(), null, null);
                            }
                        }
                        return RongContext.getInstance().getConversationBehaviorListener().onUserPortraitLongClick(MessageListAdapter.this.mContext, uIMessage.getConversationType(), userInfo);
                    }
                });
                if (!messageProviderTag.showWarning()) {
                    viewHolder.warning.setVisibility(8);
                }
            } else {
                if (messageProviderTag.showPortrait()) {
                    viewHolder.rightIconView.setVisibility(8);
                    viewHolder.leftIconView.setVisibility(0);
                } else {
                    viewHolder.leftIconView.setVisibility(8);
                    viewHolder.rightIconView.setVisibility(8);
                }
                if (messageProviderTag.centerInHorizontal()) {
                    setGravity(viewHolder.layout, 17);
                    viewHolder.contentView.containerViewCenter();
                    viewHolder.nameView.setGravity(1);
                    viewHolder.contentView.setBackgroundColor(0);
                } else {
                    setGravity(viewHolder.layout, 3);
                    viewHolder.contentView.containerViewLeft();
                    viewHolder.nameView.setGravity(3);
                }
                viewHolder.progressBar.setVisibility(8);
                viewHolder.warning.setVisibility(8);
                viewHolder.readReceipt.setVisibility(8);
                viewHolder.nameView.setVisibility(0);
                if (uIMessage.getConversationType() == ConversationType.PRIVATE || !messageProviderTag.showPortrait() || uIMessage.getConversationType() == ConversationType.PUBLIC_SERVICE || uIMessage.getConversationType() == ConversationType.APP_PUBLIC_SERVICE) {
                    viewHolder.nameView.setVisibility(8);
                } else if (uIMessage.getConversationType().equals(ConversationType.CUSTOMER_SERVICE) && uIMessage.getUserInfo() != null && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                    viewHolder.nameView.setText(uIMessage.getUserInfo().getName());
                } else if (uIMessage.getConversationType() == ConversationType.GROUP) {
                    GroupUserInfo groupUserInfo = RongUserInfoManager.getInstance().getGroupUserInfo(uIMessage.getTargetId(), uIMessage.getSenderUserId());
                    if (groupUserInfo != null) {
                        viewHolder.nameView.setText(groupUserInfo.getNickname());
                    } else {
                        userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                        if (userInfo == null) {
                            viewHolder.nameView.setText(uIMessage.getSenderUserId());
                        } else {
                            viewHolder.nameView.setText(userInfo.getName());
                        }
                    }
                } else {
                    userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                    if (userInfo == null) {
                        viewHolder.nameView.setText(uIMessage.getSenderUserId());
                    } else {
                        viewHolder.nameView.setText(userInfo.getName());
                    }
                }
                viewHolder.leftIconView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (RongContext.getInstance().getConversationBehaviorListener() != null) {
                            UserInfo userInfo;
                            if (TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                                userInfo = null;
                            } else {
                                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                                if (userInfo == null) {
                                    userInfo = new UserInfo(uIMessage.getSenderUserId(), null, null);
                                }
                            }
                            RongContext.getInstance().getConversationBehaviorListener().onUserPortraitClick(MessageListAdapter.this.mContext, uIMessage.getConversationType(), userInfo);
                        }
                        EventBus.getDefault().post(InputViewEvent.obtain(false));
                    }
                });
            }
            viewHolder.leftIconView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    UserInfo userInfo;
                    if (TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                        userInfo = null;
                    } else {
                        userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                        if (userInfo == null) {
                            userInfo = new UserInfo(uIMessage.getSenderUserId(), null, null);
                        }
                    }
                    if ((RongContext.getInstance().getConversationBehaviorListener() != null && RongContext.getInstance().getConversationBehaviorListener().onUserPortraitLongClick(MessageListAdapter.this.mContext, uIMessage.getConversationType(), r0)) || MessageListAdapter.this.mMentionMemberSelectListener == null || (!uIMessage.getConversationType().equals(ConversationType.GROUP) && !uIMessage.getConversationType().equals(ConversationType.DISCUSSION))) {
                        return false;
                    }
                    MessageListAdapter.this.mMentionMemberSelectListener.onMemberMentioned(uIMessage.getSenderUserId());
                    return true;
                }
            });
            Uri portraitUri;
            if (viewHolder.rightIconView.getVisibility() == 0) {
                if (uIMessage.getConversationType().equals(ConversationType.CUSTOMER_SERVICE) && uIMessage.getUserInfo() != null && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                    portraitUri = uIMessage.getUserInfo().getPortraitUri();
                    if (portraitUri != null) {
                        viewHolder.rightIconView.setAvatar(portraitUri.toString(), 0);
                    }
                } else if ((uIMessage.getConversationType().equals(ConversationType.PUBLIC_SERVICE) || uIMessage.getConversationType().equals(ConversationType.APP_PUBLIC_SERVICE)) && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                    userInfo = uIMessage.getUserInfo();
                    if (userInfo != null) {
                        portraitUri = userInfo.getPortraitUri();
                        if (portraitUri != null) {
                            viewHolder.leftIconView.setAvatar(portraitUri.toString(), 0);
                        }
                    } else {
                        portraitUri = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(uIMessage.getTargetId(), uIMessage.getConversationType()).getKey()).getPortraitUri();
                        if (portraitUri != null) {
                            viewHolder.rightIconView.setAvatar(portraitUri.toString(), 0);
                        }
                    }
                } else if (!TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                    userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                    if (!(userInfo == null || userInfo.getPortraitUri() == null)) {
                        viewHolder.rightIconView.setAvatar(userInfo.getPortraitUri().toString(), 0);
                    }
                }
            } else if (viewHolder.leftIconView.getVisibility() == 0) {
                if (uIMessage.getConversationType().equals(ConversationType.CUSTOMER_SERVICE) && uIMessage.getUserInfo() != null && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                    portraitUri = uIMessage.getUserInfo().getPortraitUri();
                    if (portraitUri != null) {
                        viewHolder.leftIconView.setAvatar(portraitUri.toString(), 0);
                    }
                } else if ((uIMessage.getConversationType().equals(ConversationType.PUBLIC_SERVICE) || uIMessage.getConversationType().equals(ConversationType.APP_PUBLIC_SERVICE)) && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                    userInfo = uIMessage.getUserInfo();
                    if (userInfo != null) {
                        portraitUri = userInfo.getPortraitUri();
                        if (portraitUri != null) {
                            viewHolder.leftIconView.setAvatar(portraitUri.toString(), 0);
                        }
                    } else {
                        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(uIMessage.getTargetId(), uIMessage.getConversationType()).getKey());
                        if (!(publicServiceInfoFromCache == null || publicServiceInfoFromCache.getPortraitUri() == null)) {
                            viewHolder.leftIconView.setAvatar(publicServiceInfoFromCache.getPortraitUri().toString(), 0);
                        }
                    }
                } else if (!TextUtils.isEmpty(uIMessage.getSenderUserId())) {
                    userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
                    if (!(userInfo == null || userInfo.getPortraitUri() == null)) {
                        viewHolder.leftIconView.setAvatar(userInfo.getPortraitUri().toString(), 0);
                    }
                }
            }
            if (inflate != null) {
                inflate.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (RongContext.getInstance().getConversationBehaviorListener() == null || !RongContext.getInstance().getConversationBehaviorListener().onMessageClick(MessageListAdapter.this.mContext, view, uIMessage)) {
                            MessageProvider evaluateProvider;
                            if (MessageListAdapter.this.getNeedEvaluate(uIMessage)) {
                                evaluateProvider = RongContext.getInstance().getEvaluateProvider();
                            } else {
                                evaluateProvider = RongContext.getInstance().getMessageTemplate(uIMessage.getContent().getClass());
                            }
                            if (evaluateProvider != null) {
                                evaluateProvider.onItemClick(view, i, uIMessage.getContent(), uIMessage);
                            }
                        }
                    }
                });
                inflate.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (RongContext.getInstance().getConversationBehaviorListener() == null || !RongContext.getInstance().getConversationBehaviorListener().onMessageLongClick(MessageListAdapter.this.mContext, view, uIMessage)) {
                            MessageProvider evaluateProvider;
                            if (MessageListAdapter.this.getNeedEvaluate(uIMessage)) {
                                evaluateProvider = RongContext.getInstance().getEvaluateProvider();
                            } else {
                                evaluateProvider = RongContext.getInstance().getMessageTemplate(uIMessage.getContent().getClass());
                            }
                            if (evaluateProvider != null) {
                                evaluateProvider.onItemLongClick(view, i, uIMessage.getContent(), uIMessage);
                            }
                        }
                        return true;
                    }
                });
            }
            viewHolder.warning.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (MessageListAdapter.this.mOnItemHandlerListener != null) {
                        MessageListAdapter.this.mOnItemHandlerListener.onWarningViewClick(i, uIMessage, view);
                    }
                }
            });
            if (messageProviderTag.hide()) {
                viewHolder.time.setVisibility(8);
            } else if (!this.timeGone) {
                viewHolder.time.setText(RongDateUtils.getConversationFormatDate(new Date(uIMessage.getSentTime())));
                if (i == 0) {
                    viewHolder.time.setVisibility(0);
                    return;
                }
                if (uIMessage.getSentTime() - ((Message) getItem(i - 1)).getSentTime() > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
                    viewHolder.time.setVisibility(0);
                } else {
                    viewHolder.time.setVisibility(8);
                }
            }
        }
    }

    private final void setGravity(View view, int i) {
        ((LayoutParams) view.getLayoutParams()).gravity = i;
    }

    public void setEvaluateForRobot(boolean z) {
        this.evaForRobot = z;
    }

    public void setRobotMode(boolean z) {
        this.robotMode = z;
    }
}
