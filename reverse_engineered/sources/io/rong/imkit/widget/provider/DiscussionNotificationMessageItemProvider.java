package io.rong.imkit.widget.provider;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alipay.sdk.cons.C0844a;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.MessageProviderUserInfoHelper;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.DiscussionNotificationMessage;

@ProviderTag(centerInHorizontal = true, messageContent = DiscussionNotificationMessage.class, showPortrait = false, showSummaryWithName = false)
public class DiscussionNotificationMessageItemProvider extends MessageProvider<DiscussionNotificationMessage> {
    private static final int DISCUSSION_ADD_MEMBER = 1;
    private static final int DISCUSSION_EXIT = 2;
    private static final int DISCUSSION_MEMBER_INVITE = 5;
    private static final int DISCUSSION_REMOVE = 4;
    private static final int DISCUSSION_RENAME = 3;
    private static final String TAG = "DiscussionNotificationMessageItemProvider";
    Handler mDownloadHandler;
    HandlerThread mWorkThread = new HandlerThread(TAG);

    public DiscussionNotificationMessageItemProvider() {
        RongContext.getInstance().getEventBus().register(this);
        this.mWorkThread.start();
        this.mDownloadHandler = new Handler(this.mWorkThread.getLooper());
    }

    public void bindView(View view, int i, DiscussionNotificationMessage discussionNotificationMessage, UIMessage uIMessage) {
        DiscussionNotificationMessageItemProvider$ViewHolder discussionNotificationMessageItemProvider$ViewHolder = (DiscussionNotificationMessageItemProvider$ViewHolder) view.getTag();
        CharSequence contentSummary = getContentSummary(discussionNotificationMessage);
        if (contentSummary == null || contentSummary.length() <= 0) {
            discussionNotificationMessageItemProvider$ViewHolder.contentTextView.setVisibility(8);
            return;
        }
        discussionNotificationMessageItemProvider$ViewHolder.contentTextView.setVisibility(0);
        discussionNotificationMessageItemProvider$ViewHolder.contentTextView.setText(contentSummary);
    }

    public Spannable getContentSummary(DiscussionNotificationMessage discussionNotificationMessage) {
        if (discussionNotificationMessage == null) {
            RLog.e(TAG, "getContentSummary DiscussionNotificationMessage is null;");
            return new SpannableString("");
        }
        RLog.i(TAG, "getContentSummary call getContentSummary()  method ");
        return new SpannableString(getWrapContent(RongContext.getInstance(), discussionNotificationMessage));
    }

    public void onItemClick(View view, int i, DiscussionNotificationMessage discussionNotificationMessage, UIMessage uIMessage) {
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_discussion_notification_message, null);
        DiscussionNotificationMessageItemProvider$ViewHolder discussionNotificationMessageItemProvider$ViewHolder = new DiscussionNotificationMessageItemProvider$ViewHolder(this);
        discussionNotificationMessageItemProvider$ViewHolder.contentTextView = (TextView) inflate.findViewById(C4974R.id.rc_msg);
        discussionNotificationMessageItemProvider$ViewHolder.contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        inflate.setTag(discussionNotificationMessageItemProvider$ViewHolder);
        return inflate;
    }

    private final String getWrapContent(Context context, DiscussionNotificationMessage discussionNotificationMessage) {
        if (discussionNotificationMessage == null) {
            return "";
        }
        String[] strArr;
        int i;
        String extension = discussionNotificationMessage.getExtension();
        String operator = discussionNotificationMessage.getOperator();
        String str = "";
        String str2 = "";
        if (TextUtils.isEmpty(extension)) {
            strArr = null;
            i = 0;
        } else {
            String[] split = extension.indexOf(",") != -1 ? extension.split(",") : new String[]{extension};
            strArr = split;
            i = split.length;
        }
        String currentUserId = RongIM.getInstance().getCurrentUserId();
        if (TextUtils.isEmpty(currentUserId)) {
            return "";
        }
        String str3;
        UserInfo userInfo;
        UserInfo userInfo2;
        switch (discussionNotificationMessage.getType()) {
            case 1:
                if (strArr != null) {
                    if (!currentUserId.equals(operator)) {
                        if (i != 1) {
                            if (RongUserInfoManager.getInstance().getUserInfo(operator) == null) {
                                MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                                break;
                            }
                            str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_add), new Object[]{RongUserInfoManager.getInstance().getUserInfo(operator).getName(), Integer.valueOf(i)});
                            break;
                        }
                        str3 = strArr[0];
                        userInfo = RongUserInfoManager.getInstance().getUserInfo(str3);
                        userInfo2 = RongUserInfoManager.getInstance().getUserInfo(operator);
                        if (userInfo != null && userInfo2 != null) {
                            str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_added), new Object[]{userInfo2.getName(), userInfo.getName()});
                            break;
                        }
                        if (userInfo == null) {
                            MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, str3);
                        }
                        if (userInfo2 == null) {
                            MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                            break;
                        }
                    }
                    extension = context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_you);
                    if (i == 1) {
                        str3 = strArr[0];
                        if (RongUserInfoManager.getInstance().getUserInfo(str3) != null) {
                            str3 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_added), new Object[]{extension, RongUserInfoManager.getInstance().getUserInfo(str3).getName()});
                        } else {
                            MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, str3);
                            str3 = str2;
                        }
                    } else {
                        str3 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_add), new Object[]{extension, Integer.valueOf(i)});
                    }
                    str2 = str3;
                    break;
                }
                break;
            case 2:
                if (RongUserInfoManager.getInstance().getUserInfo(operator) == null) {
                    MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                    break;
                }
                str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_exit), new Object[]{RongUserInfoManager.getInstance().getUserInfo(operator).getName()});
                break;
            case 3:
                if (!currentUserId.equals(operator)) {
                    if (RongUserInfoManager.getInstance().getUserInfo(operator) == null) {
                        MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                        break;
                    }
                    str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_rename), new Object[]{RongUserInfoManager.getInstance().getUserInfo(operator).getName(), extension});
                    break;
                }
                str3 = context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_you);
                str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_rename), new Object[]{str3, extension});
                break;
            case 4:
                str3 = strArr[0];
                if (!currentUserId.equals(operator)) {
                    if (!currentUserId.equals(str3)) {
                        userInfo = RongUserInfoManager.getInstance().getUserInfo(str3);
                        userInfo2 = RongUserInfoManager.getInstance().getUserInfo(operator);
                        if (userInfo != null && userInfo2 != null) {
                            str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_who_removed), new Object[]{userInfo.getName(), userInfo2.getName()});
                            break;
                        }
                        if (userInfo2 == null) {
                            MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                        }
                        if (userInfo == null) {
                            MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, str3);
                            break;
                        }
                    }
                    if (RongUserInfoManager.getInstance().getUserInfo(operator) == null) {
                        MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                        break;
                    }
                    str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_removed), new Object[]{RongUserInfoManager.getInstance().getUserInfo(operator).getName()});
                    break;
                }
                if (RongUserInfoManager.getInstance().getUserInfo(str3) == null) {
                    MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                    break;
                }
                str = context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_you);
                str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_who_removed), new Object[]{r0.getName(), str});
                break;
                break;
            case 5:
                if (!currentUserId.equals(operator)) {
                    if (RongUserInfoManager.getInstance().getUserInfo(operator) != null) {
                        if (!C0844a.f2048d.equals(extension)) {
                            if ("0".equals(extension)) {
                                str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_is_open_invite_open), new Object[]{r0.getName()});
                                break;
                            }
                        }
                        str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_is_open_invite_close), new Object[]{r0.getName()});
                        break;
                    }
                    MessageProviderUserInfoHelper.getInstance().registerMessageUserInfo(discussionNotificationMessage, operator);
                    break;
                }
                str3 = context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_you);
                if (!C0844a.f2048d.equals(extension)) {
                    if ("0".equals(extension)) {
                        str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_is_open_invite_open), new Object[]{str3});
                        break;
                    }
                }
                str2 = String.format(context.getResources().getString(C4974R.string.rc_discussion_nt_msg_for_is_open_invite_close), new Object[]{str3});
                break;
                break;
            default:
                str2 = "";
                break;
        }
        RLog.i(TAG, "content return " + str2);
        return str2;
    }

    public void onItemLongClick(View view, int i, DiscussionNotificationMessage discussionNotificationMessage, UIMessage uIMessage) {
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
        ArraysDialogFragment.newInstance(str, new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)}).setArraysDialogItemListener(new DiscussionNotificationMessageItemProvider$1(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    public void onEventBackgroundThread(UserInfo userInfo) {
        if (userInfo.getName() != null) {
            this.mDownloadHandler.postDelayed(new DiscussionNotificationMessageItemProvider$2(this, userInfo), 500);
        }
    }
}
