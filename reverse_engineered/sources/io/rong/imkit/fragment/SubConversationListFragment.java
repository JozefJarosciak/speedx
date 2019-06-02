package io.rong.imkit.fragment;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationInfo;
import io.rong.imkit.model.Draft;
import io.rong.imkit.model.Event.AudioListenedEvent;
import io.rong.imkit.model.Event.ClearConversationEvent;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.GroupUserInfoEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageRecallEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.model.Event.QuitDiscussionEvent;
import io.rong.imkit.model.Event.QuitGroupEvent;
import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imkit.model.Event.RemoteMessageRecallEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.utils.ConversationListUtils;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.imkit.widget.adapter.SubConversationListAdapter;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.VoiceMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubConversationListFragment extends UriFragment implements OnItemClickListener, OnItemLongClickListener {
    private static final String TAG = "SubConversationListFragment";
    private ConversationType currentType;
    private SubConversationListAdapter mAdapter;
    ResultCallback<List<Conversation>> mCallback = new C50761();
    private ListView mList;
    private TextView mNotificationBar;

    /* renamed from: io.rong.imkit.fragment.SubConversationListFragment$1 */
    class C50761 extends ResultCallback<List<Conversation>> {
        C50761() {
        }

        public void onSuccess(List<Conversation> list) {
            RLog.m19419d(SubConversationListFragment.TAG, "SubConversationListFragment initFragment onSuccess callback");
            if (list != null && list.size() != 0) {
                Collection arrayList = new ArrayList();
                for (Conversation conversation : list) {
                    if (SubConversationListFragment.this.mAdapter.getCount() <= 0) {
                        arrayList.add(UIConversation.obtain(conversation, false));
                    } else if (SubConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId()) < 0) {
                        arrayList.add(UIConversation.obtain(conversation, false));
                    }
                }
                SubConversationListFragment.this.mAdapter.addCollection(arrayList);
                if (SubConversationListFragment.this.mList != null && SubConversationListFragment.this.mList.getAdapter() != null) {
                    SubConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19419d(SubConversationListFragment.TAG, "SubConversationListFragment initFragment onError callback, e=" + rongIMClient$ErrorCode);
        }
    }

    public static ConversationListFragment getInstance() {
        return new ConversationListFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RongContext.getInstance().getEventBus().register(this);
        if (getActivity().getIntent() == null || getActivity().getIntent().getData() == null) {
            throw new IllegalArgumentException();
        } else if (this.mAdapter == null) {
            this.mAdapter = new SubConversationListAdapter(getActivity());
        }
    }

    public void initFragment(Uri uri) {
        String queryParameter = uri.getQueryParameter("type");
        RLog.m19419d(TAG, "initFragment uri=" + uri);
        this.currentType = null;
        for (ConversationType conversationType : new ConversationType[]{ConversationType.PRIVATE, ConversationType.DISCUSSION, ConversationType.GROUP, ConversationType.CHATROOM, ConversationType.CUSTOMER_SERVICE, ConversationType.SYSTEM, ConversationType.PUBLIC_SERVICE, ConversationType.APP_PUBLIC_SERVICE}) {
            if (conversationType.getName().equals(queryParameter)) {
                this.currentType = conversationType;
                break;
            }
        }
        ConversationType conversationType2 = null;
        if (conversationType2 != null) {
            RongIM.getInstance().getConversationList(this.mCallback, new ConversationType[]{conversationType2});
            return;
        }
        throw new IllegalArgumentException("Unknown conversation type!!");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_conversationlist, null);
        if (getResources().getBoolean(C4974R.bool.rc_is_show_warning_notification)) {
            this.mNotificationBar = (TextView) findViewById(inflate, C4974R.id.rc_status_bar);
        }
        this.mList = (ListView) findViewById(inflate, C4974R.id.rc_list);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(this);
        this.mList.setOnItemLongClickListener(this);
        super.onViewCreated(view, bundle);
    }

    public void onResume() {
        RLog.m19419d(TAG, "SubConversationListFragment onResume");
        super.onResume();
        if (getResources().getBoolean(C4974R.bool.rc_is_show_warning_notification)) {
            setNotificationBarVisibility(RongIM.getInstance().getCurrentConnectionStatus());
        }
    }

    public void onEventMainThread(ReadReceiptEvent readReceiptEvent) {
        if (this.mAdapter == null) {
            RLog.m19419d(TAG, "onEventMainThread ReadReceiptEvent adapter is null");
            return;
        }
        int findPosition = this.mAdapter.findPosition(readReceiptEvent.getMessage().getConversationType(), readReceiptEvent.getMessage().getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (((ReadReceiptMessage) readReceiptEvent.getMessage().getContent()).getLastMessageSendTime() >= uIConversation.getUIConversationTime() && uIConversation.getConversationSenderId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                uIConversation.setSentStatus(Message$SentStatus.READ);
                this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }
    }

    public void onEventMainThread(Message message) {
        RLog.m19419d(TAG, "onEventMainThread Message");
        int findPosition = this.mAdapter.findPosition(message.getConversationType(), message.getTargetId());
        if (!message.getConversationType().equals(this.currentType)) {
            return;
        }
        if (findPosition >= 0) {
            UIConversation makeUiConversation = makeUiConversation(message, findPosition);
            int findPositionForNewConversation = ConversationListUtils.findPositionForNewConversation(makeUiConversation, this.mAdapter);
            if (findPositionForNewConversation == findPosition) {
                this.mAdapter.getView(findPositionForNewConversation, this.mList.getChildAt(findPositionForNewConversation - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            }
            this.mAdapter.remove(findPosition);
            this.mAdapter.add(makeUiConversation, findPositionForNewConversation);
            this.mAdapter.notifyDataSetChanged();
            return;
        }
        UIConversation obtain = UIConversation.obtain(message, false);
        this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, this.mAdapter));
        this.mAdapter.notifyDataSetChanged();
    }

    public void onEventMainThread(OnReceiveMessageEvent onReceiveMessageEvent) {
        onEventMainThread(onReceiveMessageEvent.getMessage());
    }

    public void onEventMainThread(MessageContent messageContent) {
        RLog.m19419d(TAG, "onEventMainThread::MessageContent MessageContent");
        for (int firstVisiblePosition = this.mList.getFirstVisiblePosition(); firstVisiblePosition < this.mList.getLastVisiblePosition(); firstVisiblePosition++) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(firstVisiblePosition);
            if (uIConversation.getMessageContent().equals(messageContent)) {
                uIConversation.setMessageContent(messageContent);
                Spannable contentSummary = RongContext.getInstance().getMessageTemplate(messageContent.getClass()).getContentSummary(messageContent);
                if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                    if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                    } else {
                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                    }
                }
                uIConversation.setConversationContent(contentSummary);
                this.mAdapter.getView(firstVisiblePosition, this.mList.getChildAt(firstVisiblePosition - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }
    }

    public void onEventMainThread(Draft draft) {
        ConversationType value = ConversationType.setValue(draft.getType().intValue());
        if (value == null) {
            throw new IllegalArgumentException("the type of the draft is unknown!");
        }
        int findPosition = this.mAdapter.findPosition(value, draft.getId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (draft.getContent() == null) {
                uIConversation.setDraft("");
            } else {
                uIConversation.setDraft(draft.getContent());
            }
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(Group group) {
        int count = this.mAdapter.getCount();
        if (group.getName() != null) {
            for (int i = 0; i < count; i++) {
                UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                if (uIConversation.getConversationTargetId().equals(group.getId())) {
                    uIConversation.setUIConversationTitle(group.getName());
                    if (group.getPortraitUri() != null) {
                        uIConversation.setIconUrl(group.getPortraitUri());
                    }
                    this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }
        }
    }

    public void onEventMainThread(GroupUserInfoEvent groupUserInfoEvent) {
        RLog.m19419d(TAG, "onEvent update GroupUserInfoEvent");
        GroupUserInfo userInfo = groupUserInfoEvent.getUserInfo();
        if (userInfo != null && userInfo.getNickname() != null) {
            RongContext instance = RongContext.getInstance();
            if (instance != null) {
                int count = this.mAdapter.getCount();
                for (int i = 0; i < count; i++) {
                    UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                    String name = uIConversation.getConversationType().getName();
                    MessageContent messageContent = uIConversation.getMessageContent();
                    if (messageContent != null) {
                        MessageProvider messageTemplate = instance.getMessageTemplate(messageContent.getClass());
                        if (messageTemplate != null) {
                            CharSequence contentSummary = messageTemplate.getContentSummary(messageContent);
                            if (contentSummary != null && name.equals(ConversationType.GROUP.getName()) && uIConversation.getConversationSenderId().equals(userInfo.getUserId())) {
                                Spannable spannableStringBuilder = new SpannableStringBuilder();
                                if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                                    if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                                    } else {
                                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                                    }
                                }
                                if (uIConversation.getConversationTargetId().equals(userInfo.getGroupId())) {
                                    uIConversation.addNickname(userInfo.getUserId());
                                    spannableStringBuilder.append(userInfo.getNickname()).append(" : ").append(contentSummary);
                                    uIConversation.setConversationContent(spannableStringBuilder);
                                }
                                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        RLog.m19419d(TAG, "onEvent update userInfo");
        if (userInfo != null && userInfo.getName() != null) {
            RongContext instance = RongContext.getInstance();
            if (instance != null) {
                int count = this.mAdapter.getCount();
                for (int i = 0; i < count; i++) {
                    UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                    String name = uIConversation.getConversationType().getName();
                    MessageContent messageContent = uIConversation.getMessageContent();
                    if (!(uIConversation.hasNickname(userInfo.getUserId()) || messageContent == null)) {
                        MessageProvider messageTemplate = instance.getMessageTemplate(messageContent.getClass());
                        if (messageTemplate != null) {
                            CharSequence contentSummary = messageTemplate.getContentSummary(messageContent);
                            if (contentSummary != null) {
                                Spannable spannableStringBuilder;
                                if ((name.equals(ConversationType.GROUP.getName()) || name.equals(ConversationType.DISCUSSION.getName())) && uIConversation.getConversationSenderId().equals(userInfo.getUserId())) {
                                    spannableStringBuilder = new SpannableStringBuilder();
                                    if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                                        if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                            contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                                        } else {
                                            contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                                        }
                                    }
                                    spannableStringBuilder.append(userInfo.getName()).append(" : ").append(contentSummary);
                                    uIConversation.setConversationContent(spannableStringBuilder);
                                } else if (uIConversation.getConversationTargetId().equals(userInfo.getUserId())) {
                                    if (name.equals(ConversationType.PRIVATE.getName())) {
                                        uIConversation.setUIConversationTitle(userInfo.getName());
                                        uIConversation.setIconUrl(userInfo.getPortraitUri());
                                    } else {
                                        spannableStringBuilder = new SpannableStringBuilder();
                                        if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                                            if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                                            } else {
                                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                                            }
                                        }
                                        spannableStringBuilder.append(userInfo.getName()).append(" : ").append(contentSummary);
                                        uIConversation.setConversationContent(spannableStringBuilder);
                                        uIConversation.setIconUrl(userInfo.getPortraitUri());
                                    }
                                }
                                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onEventMainThread(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        RLog.m19419d(TAG, "ConnectionStatus = " + rongIMClient$ConnectionStatusListener$ConnectionStatus.toString());
        if (isResumed() && getResources().getBoolean(C4974R.bool.rc_is_show_warning_notification)) {
            setNotificationBarVisibility(rongIMClient$ConnectionStatusListener$ConnectionStatus);
        }
    }

    private void setNotificationBarVisibility(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        if (getResources().getBoolean(C4974R.bool.rc_is_show_warning_notification)) {
            CharSequence charSequence = null;
            if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE)) {
                charSequence = getResources().getString(C4974R.string.rc_notice_network_unavailable);
            } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                charSequence = getResources().getString(C4974R.string.rc_notice_tick);
            } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                this.mNotificationBar.setVisibility(8);
            } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
                charSequence = getResources().getString(C4974R.string.rc_notice_network_unavailable);
            } else if (rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING)) {
                charSequence = getResources().getString(C4974R.string.rc_notice_connecting);
            }
            if (charSequence == null) {
                return;
            }
            if (this.mNotificationBar.getVisibility() == 8) {
                getHandler().postDelayed(new Runnable() {
                    public void run() {
                        if (!RongIMClient.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                            SubConversationListFragment.this.mNotificationBar.setVisibility(0);
                            SubConversationListFragment.this.mNotificationBar.setText(charSequence);
                        }
                    }
                }, 4000);
                return;
            } else {
                this.mNotificationBar.setText(charSequence);
                return;
            }
        }
        RLog.m19420e(TAG, "rc_is_show_warning_notification is disabled.");
    }

    public void onEventMainThread(Discussion discussion) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
            RongContext.getInstance().getConversationGatherState(uIConversation.getConversationType().getName()).booleanValue();
            if (uIConversation.getConversationTargetId().equals(discussion.getId())) {
                uIConversation.setUIConversationTitle(discussion.getName());
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            }
        }
    }

    public void onEventMainThread(PublicServiceProfile publicServiceProfile) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            if (((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId().equals(publicServiceProfile.getTargetId())) {
                ((UIConversation) this.mAdapter.getItem(i)).setIconUrl(publicServiceProfile.getPortraitUri());
                ((UIConversation) this.mAdapter.getItem(i)).setUIConversationTitle(publicServiceProfile.getName());
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            }
        }
    }

    public void onEventMainThread(ConversationUnreadEvent conversationUnreadEvent) {
        int findPosition = this.mAdapter.findPosition(conversationUnreadEvent.getType(), conversationUnreadEvent.getTargetId());
        if (findPosition >= 0) {
            ((UIConversation) this.mAdapter.getItem(findPosition)).setUnReadMessageCount(0);
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(ConversationTopEvent conversationTopEvent) throws IllegalAccessException {
        int findPosition = this.mAdapter.findPosition(conversationTopEvent.getConversationType(), conversationTopEvent.getTargetId());
        if (findPosition >= 0) {
            int findPositionForCancleTop;
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (uIConversation.isTop()) {
                uIConversation.setTop(false);
                findPositionForCancleTop = ConversationListUtils.findPositionForCancleTop(findPosition, this.mAdapter);
            } else {
                uIConversation.setTop(true);
                findPositionForCancleTop = ConversationListUtils.findPositionForSetTop(uIConversation, this.mAdapter);
            }
            if (findPosition == findPositionForCancleTop) {
                this.mAdapter.getView(findPositionForCancleTop, this.mList.getChildAt(findPositionForCancleTop - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            }
            this.mAdapter.remove(findPosition);
            this.mAdapter.add(uIConversation, findPositionForCancleTop);
            this.mAdapter.notifyDataSetChanged();
            return;
        }
        throw new IllegalAccessException("the item has already been deleted!");
    }

    public void onEventMainThread(ConversationRemoveEvent conversationRemoveEvent) {
        int findPosition = this.mAdapter.findPosition(conversationRemoveEvent.getType(), conversationRemoveEvent.getTargetId());
        if (findPosition >= 0) {
            this.mAdapter.remove(findPosition);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(ClearConversationEvent clearConversationEvent) {
        List types = clearConversationEvent.getTypes();
        for (int count = this.mAdapter.getCount() - 1; count >= 0; count--) {
            if (types.indexOf(((UIConversation) this.mAdapter.getItem(count)).getConversationType()) >= 0) {
                this.mAdapter.remove(count);
            }
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public void onEventMainThread(ConversationNotificationEvent conversationNotificationEvent) {
        int findPosition = this.mAdapter.findPosition(conversationNotificationEvent.getConversationType(), conversationNotificationEvent.getTargetId());
        if (findPosition >= 0) {
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(MessagesClearEvent messagesClearEvent) {
        int findPosition = this.mAdapter.findPosition(messagesClearEvent.getType(), messagesClearEvent.getTargetId());
        if (messagesClearEvent != null && findPosition >= 0) {
            Conversation conversation = RongIMClient.getInstance().getConversation(messagesClearEvent.getType(), messagesClearEvent.getTargetId());
            UIConversation obtain = UIConversation.obtain(conversation, false);
            this.mAdapter.remove(findPosition);
            this.mAdapter.add(UIConversation.obtain(conversation, false), ConversationListUtils.findPositionForNewConversation(obtain, this.mAdapter));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(MessageDeleteEvent messageDeleteEvent) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            if (messageDeleteEvent.getMessageIds().contains(Integer.valueOf(((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()))) {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        if (conversation == null) {
                            RLog.m19419d(SubConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                            return;
                        }
                        UIConversation obtain = UIConversation.obtain(conversation, false);
                        SubConversationListFragment.this.mAdapter.remove(i);
                        SubConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, SubConversationListFragment.this.mAdapter));
                        SubConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                });
                return;
            }
        }
    }

    public void onEventMainThread(MessageRecallEvent messageRecallEvent) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            if (messageRecallEvent.getMessageId() == ((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()) {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        if (conversation == null) {
                            RLog.m19419d(SubConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                            return;
                        }
                        UIConversation obtain = UIConversation.obtain(conversation, false);
                        SubConversationListFragment.this.mAdapter.remove(i);
                        SubConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, SubConversationListFragment.this.mAdapter));
                        SubConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                });
                return;
            }
        }
    }

    public void onEventMainThread(RemoteMessageRecallEvent remoteMessageRecallEvent) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            if (remoteMessageRecallEvent.getMessageId() == ((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()) {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        if (conversation == null) {
                            RLog.m19419d(SubConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                            return;
                        }
                        UIConversation obtain = UIConversation.obtain(conversation, false);
                        SubConversationListFragment.this.mAdapter.remove(i);
                        SubConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, SubConversationListFragment.this.mAdapter));
                        SubConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                });
                return;
            }
        }
    }

    public void onEventMainThread(OnMessageSendErrorEvent onMessageSendErrorEvent) {
        int findPosition = this.mAdapter.findPosition(onMessageSendErrorEvent.getMessage().getConversationType(), onMessageSendErrorEvent.getMessage().getTargetId());
        if (findPosition >= 0) {
            ((UIConversation) this.mAdapter.getItem(findPosition)).setSentStatus(Message$SentStatus.FAILED);
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(QuitDiscussionEvent quitDiscussionEvent) {
        int findPosition = this.mAdapter.findPosition(ConversationType.DISCUSSION, quitDiscussionEvent.getDiscussionId());
        if (findPosition >= 0) {
            this.mAdapter.remove(findPosition);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(QuitGroupEvent quitGroupEvent) {
        int findPosition = this.mAdapter.findPosition(ConversationType.GROUP, quitGroupEvent.getGroupId());
        if (findPosition >= 0) {
            this.mAdapter.remove(findPosition);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(AudioListenedEvent audioListenedEvent) {
        int findPosition = this.mAdapter.findPosition(audioListenedEvent.getConversationType(), audioListenedEvent.getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (uIConversation.getLatestMessageId() == audioListenedEvent.getLatestMessageId()) {
                Spannable contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
                if (RongIM.getInstance().getConversation(audioListenedEvent.getConversationType(), audioListenedEvent.getTargetId()).getReceivedStatus().isListened()) {
                    contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                } else {
                    contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                }
                uIConversation.setConversationContent(contentSummary);
            }
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
        if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationClick(getActivity(), view, uIConversation)) {
            ConversationType conversationType = uIConversation.getConversationType();
            uIConversation.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(getActivity(), conversationType, uIConversation.getConversationTargetId(), uIConversation.getUIConversationTitle());
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
        CharSequence uIConversationTitle = uIConversation.getUIConversationTitle();
        if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationLongClick(getActivity(), view, uIConversation)) {
            new Builder(getActivity()).setTitle(uIConversationTitle);
            buildMultiDialog(uIConversation);
        }
        return true;
    }

    private void buildMultiDialog(final UIConversation uIConversation) {
        String[] strArr = new String[2];
        if (uIConversation.isTop()) {
            strArr[0] = getActivity().getString(C4974R.string.rc_conversation_list_dialog_cancel_top);
        } else {
            strArr[0] = getActivity().getString(C4974R.string.rc_conversation_list_dialog_set_top);
        }
        strArr[1] = getActivity().getString(C4974R.string.rc_conversation_list_dialog_remove);
        ArraysDialogFragment.newInstance(uIConversation.getUIConversationTitle(), strArr).setArraysDialogItemListener(new OnArraysDialogItemListener() {

            /* renamed from: io.rong.imkit.fragment.SubConversationListFragment$6$1 */
            class C50811 extends ResultCallback<Boolean> {
                C50811() {
                }

                public void onSuccess(Boolean bool) {
                    if (uIConversation.isTop()) {
                        Toast.makeText(RongContext.getInstance(), SubConversationListFragment.this.getString(C4974R.string.rc_conversation_list_popup_cancel_top), 0).show();
                    } else {
                        Toast.makeText(RongContext.getInstance(), SubConversationListFragment.this.getString(C4974R.string.rc_conversation_list_dialog_set_top), 0).show();
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }

            public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
                boolean z = true;
                if (i == 0) {
                    RongIM instance = RongIM.getInstance();
                    ConversationType conversationType = uIConversation.getConversationType();
                    String conversationTargetId = uIConversation.getConversationTargetId();
                    if (uIConversation.isTop()) {
                        z = false;
                    }
                    instance.setConversationToTop(conversationType, conversationTargetId, z, new C50811());
                } else if (i == 1) {
                    RongIM.getInstance().removeConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId());
                }
            }
        }).show(getFragmentManager());
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onDestroy() {
        RLog.m19419d(TAG, "onDestroy");
        RongContext.getInstance().getEventBus().unregister(this);
        getHandler().removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void onPause() {
        RLog.m19419d(TAG, "onPause");
        super.onPause();
    }

    public void setAdapter(SubConversationListAdapter subConversationListAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.clear();
        }
        this.mAdapter = subConversationListAdapter;
        if (this.mList != null && getUri() != null) {
            this.mList.setAdapter(subConversationListAdapter);
            initFragment(getUri());
        }
    }

    public SubConversationListAdapter getAdapter() {
        return this.mAdapter;
    }

    private UIConversation makeUiConversation(Message message, int i) {
        UIConversation uIConversation = null;
        if (i >= 0) {
            uIConversation = (UIConversation) this.mAdapter.getItem(i);
            if (uIConversation != null) {
                uIConversation.setMessageContent(message.getContent());
                if (message.getMessageDirection() == Message$MessageDirection.SEND) {
                    uIConversation.setUIConversationTime(message.getSentTime());
                    uIConversation.setConversationSenderId(RongIM.getInstance().getCurrentUserId());
                } else {
                    uIConversation.setUIConversationTime(message.getSentTime());
                    uIConversation.setConversationSenderId(message.getSenderUserId());
                }
                uIConversation.setConversationTargetId(message.getTargetId());
                uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
                uIConversation.setSentStatus(message.getSentStatus());
                uIConversation.setLatestMessageId(message.getMessageId());
                MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
                if (message.getMessageDirection() != Message$MessageDirection.RECEIVE || (messageTag.flag() & 3) == 0) {
                    uIConversation.setUnReadMessageCount(0);
                } else {
                    uIConversation.setUnReadMessageCount(uIConversation.getUnReadMessageCount() + 1);
                    for (ConversationInfo conversationInfo : RongContext.getInstance().getCurrentConversationList()) {
                        if (conversationInfo != null && conversationInfo.getConversationType().equals(message.getConversationType()) && conversationInfo.getTargetId().equals(message.getTargetId())) {
                            uIConversation.setUnReadMessageCount(0);
                        }
                    }
                }
            }
        }
        return uIConversation;
    }
}
