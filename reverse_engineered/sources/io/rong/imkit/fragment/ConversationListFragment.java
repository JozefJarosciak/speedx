package io.rong.imkit.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.avos.avoscloud.AVStatus;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.Draft;
import io.rong.imkit.model.Event.AudioListenedEvent;
import io.rong.imkit.model.Event.ClearConversationEvent;
import io.rong.imkit.model.Event.ConnectEvent;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.CreateDiscussionEvent;
import io.rong.imkit.model.Event.GroupUserInfoEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageRecallEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.model.Event.QuitDiscussionEvent;
import io.rong.imkit.model.Event.QuitGroupEvent;
import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imkit.model.Event.RemoteMessageRecallEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.utils.ConversationListUtils;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.adapter.ConversationListAdapter.OnPortraitItemClick;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MentionedInfo.MentionedType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.VoiceMessage;
import io.rong.push.RongPushClient;
import java.util.ArrayList;
import java.util.List;

public class ConversationListFragment extends UriFragment implements OnItemClickListener, OnItemLongClickListener, OnPortraitItemClick {
    private static String TAG = "ConversationListFragment";
    private boolean isShowWithoutConnected = false;
    private ConversationListAdapter mAdapter;
    private ResultCallback<List<Conversation>> mCallback = new C50301();
    private ListView mList;
    private ArrayList<Message> mMessageCache = new ArrayList();
    private TextView mNotificationBar;
    private ArrayList<ConversationType> mSupportConversationList = new ArrayList();

    /* renamed from: io.rong.imkit.fragment.ConversationListFragment$1 */
    class C50301 extends ResultCallback<List<Conversation>> {
        C50301() {
        }

        public void onSuccess(List<Conversation> list) {
            RLog.m19419d(ConversationListFragment.TAG, "ConversationListFragment initFragment onSuccess callback : list = " + (list != null ? Integer.valueOf(list.size()) : "null"));
            if (!(ConversationListFragment.this.mAdapter == null || ConversationListFragment.this.mAdapter.getCount() == 0)) {
                ConversationListFragment.this.mAdapter.clear();
            }
            if (list != null && list.size() != 0) {
                if (ConversationListFragment.this.mAdapter == null) {
                    ConversationListFragment.this.mAdapter = new ConversationListAdapter(RongContext.getInstance());
                }
                ConversationListFragment.this.makeUiConversationList(list);
                if (ConversationListFragment.this.mList != null && ConversationListFragment.this.mList.getAdapter() != null) {
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }
            } else if (ConversationListFragment.this.mAdapter != null) {
                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19419d(ConversationListFragment.TAG, "ConversationListFragment initFragment onError callback, e=" + rongIMClient$ErrorCode);
            if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.IPC_DISCONNECT)) {
                ConversationListFragment.this.isShowWithoutConnected = true;
            }
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationListFragment$4 */
    class C50334 extends ResultCallback<List<Conversation>> {
        C50334() {
        }

        public void onSuccess(List<Conversation> list) {
            if (list != null && list.size() != 0) {
                UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                int findPosition = ConversationListFragment.this.mAdapter.findPosition(access$600.getConversationType(), access$600.getConversationTargetId());
                if (findPosition >= 0) {
                    ConversationListFragment.this.mAdapter.remove(findPosition);
                }
                ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationListFragment$5 */
    class C50345 extends ResultCallback<Conversation> {
        C50345() {
        }

        public void onSuccess(Conversation conversation) {
            if (conversation == null) {
                RLog.m19419d(ConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                return;
            }
            UIConversation obtain = UIConversation.obtain(conversation, false);
            int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
            if (findPosition >= 0) {
                ConversationListFragment.this.mAdapter.remove(findPosition);
            }
            ConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, ConversationListFragment.this.mAdapter));
            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationListFragment$6 */
    class C50356 extends ResultCallback<List<Conversation>> {
        C50356() {
        }

        public void onSuccess(List<Conversation> list) {
            if (list != null && list.size() != 0) {
                UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                int findPosition = ConversationListFragment.this.mAdapter.findPosition(access$600.getConversationType(), access$600.getConversationTargetId());
                if (findPosition >= 0) {
                    ConversationListFragment.this.mAdapter.remove(findPosition);
                }
                ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationListFragment$7 */
    class C50367 extends ResultCallback<Conversation> {
        C50367() {
        }

        public void onSuccess(Conversation conversation) {
            if (conversation == null) {
                RLog.m19419d(ConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                return;
            }
            UIConversation obtain = UIConversation.obtain(conversation, false);
            int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
            if (findPosition >= 0) {
                ConversationListFragment.this.mAdapter.remove(findPosition);
            }
            ConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, ConversationListFragment.this.mAdapter));
            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    public static ConversationListFragment getInstance() {
        return new ConversationListFragment();
    }

    public void onCreate(Bundle bundle) {
        RLog.m19419d(TAG, "ConversationListFragment onCreate");
        super.onCreate(bundle);
        RongPushClient.clearAllPushNotifications(RongContext.getInstance());
        this.mSupportConversationList.clear();
        RongContext.getInstance().getEventBus().register(this);
    }

    public void onAttach(Activity activity) {
        RLog.m19419d(TAG, "ConversationListFragment onAttach");
        super.onAttach(activity);
    }

    protected void initFragment(Uri uri) {
        ConversationType[] conversationTypeArr = new ConversationType[]{ConversationType.PRIVATE, ConversationType.GROUP, ConversationType.DISCUSSION, ConversationType.SYSTEM, ConversationType.CUSTOMER_SERVICE, ConversationType.CHATROOM, ConversationType.PUBLIC_SERVICE, ConversationType.APP_PUBLIC_SERVICE};
        RLog.m19419d(TAG, "ConversationListFragment initFragment");
        if (uri == null) {
            RongIM.getInstance().getConversationList(this.mCallback);
            return;
        }
        for (ConversationType conversationType : conversationTypeArr) {
            if (uri.getQueryParameter(conversationType.getName()) != null) {
                this.mSupportConversationList.add(conversationType);
                if ("true".equals(uri.getQueryParameter(conversationType.getName()))) {
                    RongContext.getInstance().setConversationGatherState(conversationType.getName(), Boolean.valueOf(true));
                } else if ("false".equals(uri.getQueryParameter(conversationType.getName()))) {
                    RongContext.getInstance().setConversationGatherState(conversationType.getName(), Boolean.valueOf(false));
                }
            }
        }
        if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
            RLog.m19419d(TAG, "RongCloud haven't been connected yet, so the conversation list display blank !!!");
            this.isShowWithoutConnected = true;
        }
        if (this.mSupportConversationList.size() > 0) {
            RongIM.getInstance().getConversationList(this.mCallback, (ConversationType[]) this.mSupportConversationList.toArray(new ConversationType[this.mSupportConversationList.size()]));
        } else {
            RongIM.getInstance().getConversationList(this.mCallback);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RLog.m19419d(TAG, "onCreateView");
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_conversationlist, viewGroup, false);
        this.mNotificationBar = (TextView) findViewById(inflate, C4974R.id.rc_status_bar);
        this.mNotificationBar.setVisibility(8);
        this.mList = (ListView) findViewById(inflate, C4974R.id.rc_list);
        LinearLayout linearLayout = (LinearLayout) findViewById(inflate, C4974R.id.rc_conversation_list_empty_layout);
        TextView textView = (TextView) findViewById(inflate, C4974R.id.rc_empty_tv);
        RongIMClient$ConnectionStatusListener$ConnectionStatus currentConnectionStatus = RongIM.getInstance().getCurrentConnectionStatus();
        if (currentConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
            textView.setText(RongContext.getInstance().getResources().getString(C4974R.string.rc_conversation_list_not_connected));
        } else {
            textView.setText(RongContext.getInstance().getResources().getString(C4974R.string.rc_conversation_list_empty_prompt));
        }
        setNotificationBarVisibility(currentConnectionStatus);
        this.mList.setEmptyView(linearLayout);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (this.mAdapter == null) {
            this.mAdapter = new ConversationListAdapter(RongContext.getInstance());
        }
        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(this);
        this.mList.setOnItemLongClickListener(this);
        this.mAdapter.setOnPortraitItemClick(this);
        super.onViewCreated(view, bundle);
    }

    public void onResume() {
        super.onResume();
        if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
            RLog.m19419d(TAG, "onResume current connect status is:" + RongIM.getInstance().getCurrentConnectionStatus());
            RongPushClient.clearAllPushNotifications(RongContext.getInstance());
            setNotificationBarVisibility(RongIM.getInstance().getCurrentConnectionStatus());
            return;
        }
        RLog.m19419d(TAG, "RongCloud haven't been connected yet, so the conversation list display blank !!!");
        this.isShowWithoutConnected = true;
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
                charSequence = getResources().getString(C4974R.string.rc_notice_disconnect);
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
                            ConversationListFragment.this.mNotificationBar.setVisibility(0);
                            ConversationListFragment.this.mNotificationBar.setText(charSequence);
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

    public boolean onBackPressed() {
        return false;
    }

    public void setAdapter(ConversationListAdapter conversationListAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.clear();
        }
        this.mAdapter = conversationListAdapter;
        if (this.mList != null) {
            this.mList.setAdapter(conversationListAdapter);
        }
    }

    public ConversationListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void onEventMainThread(ConnectEvent connectEvent) {
        RLog.m19419d(TAG, "onEventMainThread Event.ConnectEvent: isListRetrieved = " + this.isShowWithoutConnected);
        if (this.isShowWithoutConnected) {
            if (this.mSupportConversationList.size() > 0) {
                RongIM.getInstance().getConversationList(this.mCallback, (ConversationType[]) this.mSupportConversationList.toArray(new ConversationType[this.mSupportConversationList.size()]));
            } else {
                RongIM.getInstance().getConversationList(this.mCallback);
            }
            ((TextView) ((LinearLayout) this.mList.getEmptyView()).findViewById(C4974R.id.rc_empty_tv)).setText(RongContext.getInstance().getResources().getString(C4974R.string.rc_conversation_list_empty_prompt));
            this.isShowWithoutConnected = false;
        }
    }

    public void onEventMainThread(ReadReceiptEvent readReceiptEvent) {
        if (this.mAdapter == null) {
            RLog.m19419d(TAG, "the conversation list adapter is null.");
            return;
        }
        int findPosition = this.mAdapter.findPosition(readReceiptEvent.getMessage().getConversationType(), readReceiptEvent.getMessage().getTargetId());
        if (!RongContext.getInstance().getConversationGatherState(readReceiptEvent.getMessage().getConversationType().getName()).booleanValue() && findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (((ReadReceiptMessage) readReceiptEvent.getMessage().getContent()).getLastMessageSendTime() >= uIConversation.getUIConversationTime() && uIConversation.getConversationSenderId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                uIConversation.setSentStatus(Message$SentStatus.READ);
                this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }
    }

    public void onEventMainThread(final OnReceiveMessageEvent onReceiveMessageEvent) {
        RLog.m19419d(TAG, "Receive MessageEvent: id=" + onReceiveMessageEvent.getMessage().getTargetId() + ", type=" + onReceiveMessageEvent.getMessage().getConversationType());
        if ((this.mSupportConversationList.size() != 0 && !this.mSupportConversationList.contains(onReceiveMessageEvent.getMessage().getConversationType())) || (this.mSupportConversationList.size() == 0 && (onReceiveMessageEvent.getMessage().getConversationType() == ConversationType.CHATROOM || onReceiveMessageEvent.getMessage().getConversationType() == ConversationType.CUSTOMER_SERVICE))) {
            RLog.m19420e(TAG, "Not included in conversation list. Return directly!");
        } else if (this.mAdapter == null) {
            RLog.m19419d(TAG, "the conversation list adapter is null. Cache the received message firstly!!!");
            this.mMessageCache.add(onReceiveMessageEvent.getMessage());
        } else {
            int findPosition = this.mAdapter.findPosition(onReceiveMessageEvent.getMessage().getConversationType(), onReceiveMessageEvent.getMessage().getTargetId());
            UIConversation makeUiConversation = makeUiConversation(onReceiveMessageEvent.getMessage(), findPosition);
            int findPositionForNewConversation = ConversationListUtils.findPositionForNewConversation(makeUiConversation, this.mAdapter);
            if (findPosition < 0) {
                this.mAdapter.add(makeUiConversation, findPositionForNewConversation);
            } else if (findPosition != findPositionForNewConversation) {
                this.mAdapter.remove(findPosition);
                this.mAdapter.add(makeUiConversation, findPositionForNewConversation);
            }
            this.mAdapter.notifyDataSetChanged();
            if (onReceiveMessageEvent.getMessage().getMessageId() > 0 && onReceiveMessageEvent.getLeft() == 0) {
                refreshUnreadCount(onReceiveMessageEvent.getMessage().getConversationType(), onReceiveMessageEvent.getMessage().getTargetId());
            }
            if (RongContext.getInstance().getConversationGatherState(onReceiveMessageEvent.getMessage().getConversationType().getName()).booleanValue()) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> list) {
                        if (list != null && list.size() != 0) {
                            for (Conversation conversation : list) {
                                if (conversation.getConversationType().equals(onReceiveMessageEvent.getMessage().getConversationType()) && conversation.getTargetId().equals(onReceiveMessageEvent.getMessage().getTargetId())) {
                                    int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                                    if (findPosition >= 0) {
                                        ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setDraft(conversation.getDraft());
                                        if (TextUtils.isEmpty(conversation.getDraft())) {
                                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setSentStatus(null);
                                        } else {
                                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setSentStatus(conversation.getSentStatus());
                                        }
                                        ConversationListFragment.this.mAdapter.getView(findPosition, ConversationListFragment.this.mList.getChildAt(findPosition - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }, new ConversationType[]{onReceiveMessageEvent.getMessage().getConversationType()});
            }
        }
    }

    public void onEventMainThread(MessageRecallEvent messageRecallEvent) {
        int count = this.mAdapter.getCount();
        int i = 0;
        while (i < count) {
            if (messageRecallEvent.getMessageId() != ((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()) {
                i++;
            } else if (((UIConversation) this.mAdapter.getItem(i)).getConversationGatherState()) {
                RongIM.getInstance().getConversationList(new C50334(), new ConversationType[]{((UIConversation) this.mAdapter.getItem(i)).getConversationType()});
                return;
            } else {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new C50345());
                return;
            }
        }
    }

    public void onEventMainThread(RemoteMessageRecallEvent remoteMessageRecallEvent) {
        int count = this.mAdapter.getCount();
        int i = 0;
        while (i < count) {
            if (remoteMessageRecallEvent.getMessageId() != ((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()) {
                i++;
            } else if (((UIConversation) this.mAdapter.getItem(i)).getConversationGatherState()) {
                RongIM.getInstance().getConversationList(new C50356(), new ConversationType[]{((UIConversation) this.mAdapter.getItem(i)).getConversationType()});
                return;
            } else {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new C50367());
                return;
            }
        }
    }

    public void onEventMainThread(Message message) {
        RLog.m19419d(TAG, "onEventMainThread Receive Message: name=" + message.getObjectName() + ", type=" + message.getConversationType());
        if ((this.mSupportConversationList.size() == 0 || this.mSupportConversationList.contains(message.getConversationType())) && !(this.mSupportConversationList.size() == 0 && (message.getConversationType() == ConversationType.CHATROOM || message.getConversationType() == ConversationType.CUSTOMER_SERVICE))) {
            int findPosition = this.mAdapter.findPosition(message.getConversationType(), message.getTargetId());
            UIConversation makeUiConversation = makeUiConversation(message, findPosition);
            int findPositionForNewConversation = ConversationListUtils.findPositionForNewConversation(makeUiConversation, this.mAdapter);
            if (findPosition < 0) {
                this.mAdapter.add(makeUiConversation, findPositionForNewConversation);
                this.mAdapter.notifyDataSetChanged();
                return;
            } else if (findPositionForNewConversation == findPosition) {
                this.mAdapter.getView(findPositionForNewConversation, this.mList.getChildAt(findPositionForNewConversation - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            } else {
                this.mAdapter.remove(findPosition);
                this.mAdapter.add(makeUiConversation, findPositionForNewConversation);
                this.mAdapter.notifyDataSetChanged();
                return;
            }
        }
        RLog.m19419d(TAG, "onEventBackgroundThread Not included in conversation list. Return directly!");
    }

    public void onEventMainThread(MessageContent messageContent) {
        RLog.m19419d(TAG, "onEventMainThread: MessageContent");
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
            if (messageContent == null || uIConversation.getMessageContent() == null || uIConversation.getMessageContent() != messageContent) {
                RLog.m19420e(TAG, "onEventMainThread MessageContent is null");
            } else {
                uIConversation.setMessageContent(messageContent);
                uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
                if (i >= this.mList.getFirstVisiblePosition()) {
                    this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }
        }
    }

    public void onEventMainThread(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        RLog.m19419d(TAG, "ConnectionStatus, " + rongIMClient$ConnectionStatusListener$ConnectionStatus.toString());
        setNotificationBarVisibility(rongIMClient$ConnectionStatusListener$ConnectionStatus);
        if (this.isShowWithoutConnected && rongIMClient$ConnectionStatusListener$ConnectionStatus.equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
            this.isShowWithoutConnected = false;
        }
    }

    public void onEventMainThread(CreateDiscussionEvent createDiscussionEvent) {
        RLog.m19419d(TAG, "onEventBackgroundThread: createDiscussionEvent");
        UIConversation uIConversation = new UIConversation();
        uIConversation.setConversationType(ConversationType.DISCUSSION);
        if (createDiscussionEvent.getDiscussionName() != null) {
            uIConversation.setUIConversationTitle(createDiscussionEvent.getDiscussionName());
        } else {
            uIConversation.setUIConversationTitle("");
        }
        uIConversation.setConversationTargetId(createDiscussionEvent.getDiscussionId());
        uIConversation.setUIConversationTime(System.currentTimeMillis());
        boolean booleanValue = RongContext.getInstance().getConversationGatherState(ConversationType.DISCUSSION.getName()).booleanValue();
        uIConversation.setConversationGatherState(booleanValue);
        if (booleanValue) {
            uIConversation.setUIConversationTitle(RongContext.getInstance().getGatheredConversationTitle(uIConversation.getConversationType()));
        }
        if (this.mAdapter.findGatherPosition(ConversationType.DISCUSSION) == -1) {
            this.mAdapter.add(uIConversation, ConversationListUtils.findPositionForNewConversation(uIConversation, this.mAdapter));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(Draft draft) {
        if (draft != null) {
            ConversationType value = ConversationType.setValue(draft.getType().intValue());
            if (value == null || !this.mSupportConversationList.contains(value)) {
                RLog.m19424w(TAG, value + " should not show in conversation list.");
                return;
            }
            RLog.m19422i(TAG, "Draft ConversationType : " + value.getName());
            int findPosition = this.mAdapter.findPosition(value, draft.getId());
            UIConversation uIConversation;
            if (findPosition >= 0) {
                uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
                if (uIConversation.getConversationTargetId().equals(draft.getId())) {
                    uIConversation.setDraft(draft.getContent());
                    if (!TextUtils.isEmpty(draft.getContent())) {
                        uIConversation.setSentStatus(null);
                    }
                    this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
                }
            } else if (!TextUtils.isEmpty(draft.getContent())) {
                uIConversation = new UIConversation();
                uIConversation.setConversationType(ConversationType.setValue(draft.getType().intValue()));
                uIConversation.setConversationTargetId(draft.getId());
                UserInfo userInfoFromCache = RongContext.getInstance().getUserInfoFromCache(draft.getId());
                if (userInfoFromCache != null) {
                    uIConversation.setUIConversationTitle(userInfoFromCache.getName());
                    if (userInfoFromCache.getPortraitUri() != null) {
                        uIConversation.setIconUrl(userInfoFromCache.getPortraitUri());
                    }
                }
                uIConversation.setUIConversationTime(System.currentTimeMillis());
                uIConversation.setConversationSenderId(RongIMClient.getInstance().getCurrentUserId());
                uIConversation.setDraft(draft.getContent());
                findPosition = ConversationListUtils.findPositionForNewConversation(uIConversation, this.mAdapter);
                if (findPosition >= 0) {
                    this.mAdapter.add(uIConversation, findPosition);
                    this.mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void onEventMainThread(Group group) {
        int count = this.mAdapter.getCount();
        RLog.m19419d(TAG, "onEventMainThread Group: name=" + group.getName() + ", id=" + group.getId());
        if (group.getName() != null) {
            for (int i = 0; i < count; i++) {
                UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                if (uIConversation != null && uIConversation.getConversationType().equals(ConversationType.GROUP) && uIConversation.getConversationTargetId().equals(group.getId())) {
                    if (RongContext.getInstance().getConversationGatherState(uIConversation.getConversationType().getName()).booleanValue()) {
                        Spannable spannableStringBuilder = new SpannableStringBuilder();
                        CharSequence contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
                        if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                            if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                            } else {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                            }
                        }
                        spannableStringBuilder.append(group.getName()).append(" : ").append(contentSummary);
                        uIConversation.setConversationContent(spannableStringBuilder);
                        if (group.getPortraitUri() != null) {
                            uIConversation.setIconUrl(group.getPortraitUri());
                        }
                    } else {
                        uIConversation.setUIConversationTitle(group.getName());
                        if (group.getPortraitUri() != null) {
                            uIConversation.setIconUrl(group.getPortraitUri());
                        }
                    }
                    this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }
        }
    }

    public void onEventMainThread(Discussion discussion) {
        int count = this.mAdapter.getCount();
        RLog.m19419d(TAG, "onEventMainThread Discussion: name=" + discussion.getName() + ", id=" + discussion.getId());
        for (int i = 0; i < count; i++) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
            if (uIConversation != null && uIConversation.getConversationType().equals(ConversationType.DISCUSSION) && uIConversation.getConversationTargetId().equals(discussion.getId())) {
                if (RongContext.getInstance().getConversationGatherState(uIConversation.getConversationType().getName()).booleanValue()) {
                    Object spannableStringBuilder = new SpannableStringBuilder();
                    CharSequence contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
                    if (contentSummary != null) {
                        if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                            if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                            } else {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                            }
                        }
                        spannableStringBuilder.append(discussion.getName()).append(" : ").append(contentSummary);
                    } else {
                        spannableStringBuilder.append(discussion.getName());
                    }
                    uIConversation.setConversationContent(spannableStringBuilder);
                } else {
                    uIConversation.setUIConversationTitle(discussion.getName());
                }
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }
    }

    public void onEventMainThread(GroupUserInfoEvent groupUserInfoEvent) {
        int count = this.mAdapter.getCount();
        GroupUserInfo userInfo = groupUserInfoEvent.getUserInfo();
        if (userInfo != null && userInfo.getNickname() != null) {
            for (int i = 0; i < count; i++) {
                UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                ConversationType conversationType = uIConversation.getConversationType();
                boolean booleanValue = RongContext.getInstance().getConversationGatherState(uIConversation.getConversationType().getName()).booleanValue();
                boolean z;
                if (uIConversation.getMessageContent() == null) {
                    z = false;
                } else {
                    ProviderTag messageProviderTag = RongContext.getInstance().getMessageProviderTag(uIConversation.getMessageContent().getClass());
                    z = messageProviderTag != null ? messageProviderTag.showSummaryWithName() : false;
                }
                if (!booleanValue && r1 && conversationType.equals(ConversationType.GROUP) && uIConversation.getConversationSenderId().equals(userInfo.getUserId())) {
                    CharSequence contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
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

    public void onEventMainThread(UserInfo userInfo) {
        int count = this.mAdapter.getCount();
        if (userInfo.getName() != null) {
            for (int i = 0; i < count; i++) {
                UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
                String name = uIConversation.getConversationType().getName();
                boolean booleanValue = RongContext.getInstance().getConversationGatherState(uIConversation.getConversationType().getName()).booleanValue();
                if (!uIConversation.hasNickname(userInfo.getUserId())) {
                    boolean z;
                    if (uIConversation.getMessageContent() == null) {
                        z = false;
                    } else {
                        ProviderTag messageProviderTag = RongContext.getInstance().getMessageProviderTag(uIConversation.getMessageContent().getClass());
                        z = messageProviderTag != null ? messageProviderTag.showSummaryWithName() : false;
                    }
                    CharSequence contentSummary;
                    if (!booleanValue && r1 && ((name.equals("group") || name.equals("discussion")) && uIConversation.getConversationSenderId().equals(userInfo.getUserId()))) {
                        contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
                        Spannable spannableStringBuilder = new SpannableStringBuilder();
                        if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                            if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                            } else {
                                contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                            }
                        }
                        spannableStringBuilder.append(userInfo.getName()).append(" : ").append(contentSummary);
                        uIConversation.setConversationContent(spannableStringBuilder);
                        this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                    } else if ((name.equals(AVStatus.INBOX_PRIVATE) || name.equals("system") || name.equals("customer_service")) && uIConversation.getConversationTargetId().equals(userInfo.getUserId())) {
                        if (!booleanValue) {
                            uIConversation.setUIConversationTitle(userInfo.getName());
                            uIConversation.setIconUrl(userInfo.getPortraitUri());
                        } else if (booleanValue && r1) {
                            contentSummary = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass()).getContentSummary(uIConversation.getMessageContent());
                            Object spannableStringBuilder2 = new SpannableStringBuilder();
                            if (contentSummary != null) {
                                if (uIConversation.getMessageContent() instanceof VoiceMessage) {
                                    if (RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId()).getReceivedStatus().isListened()) {
                                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                                    } else {
                                        contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                                    }
                                }
                                spannableStringBuilder2.append(userInfo.getName()).append(" : ").append(contentSummary);
                            } else {
                                spannableStringBuilder2.append(userInfo.getName());
                            }
                            uIConversation.setConversationContent(spannableStringBuilder2);
                        }
                        this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                    }
                }
            }
        }
    }

    public void onEventMainThread(PublicServiceProfile publicServiceProfile) {
        int count = this.mAdapter.getCount();
        boolean booleanValue = RongContext.getInstance().getConversationGatherState(publicServiceProfile.getConversationType().getName()).booleanValue();
        int i = 0;
        while (i < count) {
            if (((UIConversation) this.mAdapter.getItem(i)).getConversationType().equals(publicServiceProfile.getConversationType()) && ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId().equals(publicServiceProfile.getTargetId()) && !booleanValue) {
                ((UIConversation) this.mAdapter.getItem(i)).setUIConversationTitle(publicServiceProfile.getName());
                ((UIConversation) this.mAdapter.getItem(i)).setIconUrl(publicServiceProfile.getPortraitUri());
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                return;
            }
            i++;
        }
    }

    public void onEventMainThread(PublicServiceFollowableEvent publicServiceFollowableEvent) {
        if (publicServiceFollowableEvent != null && !publicServiceFollowableEvent.isFollow()) {
            int findPosition = this.mAdapter.findPosition(publicServiceFollowableEvent.getConversationType(), publicServiceFollowableEvent.getTargetId());
            if (findPosition >= 0) {
                this.mAdapter.remove(findPosition);
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(final ConversationUnreadEvent conversationUnreadEvent) {
        int findPosition = this.mAdapter.findPosition(conversationUnreadEvent.getType(), conversationUnreadEvent.getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (uIConversation.getConversationGatherState()) {
                RongIM.getInstance().getUnreadCount(new ResultCallback<Integer>() {
                    public void onSuccess(Integer num) {
                        int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversationUnreadEvent.getType(), conversationUnreadEvent.getTargetId());
                        if (findPosition >= 0) {
                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setUnReadMessageCount(num.intValue());
                            ConversationListFragment.this.mAdapter.getView(findPosition, ConversationListFragment.this.mList.getChildAt(findPosition - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                        }
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        System.err.print("Throw exception when get unread message count from ipc remote side!");
                    }
                }, new ConversationType[]{conversationUnreadEvent.getType()});
                return;
            }
            uIConversation.setUnReadMessageCount(0);
            uIConversation.setMentionedFlag(false);
            RLog.m19419d(TAG, "onEventMainThread ConversationUnreadEvent: set unRead count to be 0");
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(final ConversationTopEvent conversationTopEvent) throws IllegalAccessException {
        int findPosition = this.mAdapter.findPosition(conversationTopEvent.getConversationType(), conversationTopEvent.getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            boolean isTop = uIConversation.isTop();
            if (isTop != conversationTopEvent.isTop()) {
                if (uIConversation.getConversationGatherState()) {
                    RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> list) {
                            if (list != null && list.size() != 0) {
                                UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                                int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversationTopEvent.getConversationType(), conversationTopEvent.getTargetId());
                                if (findPosition >= 0) {
                                    ConversationListFragment.this.mAdapter.remove(findPosition);
                                }
                                ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        }
                    }, new ConversationType[]{uIConversation.getConversationType()});
                    return;
                }
                int findPositionForCancleTop;
                if (isTop) {
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
            return;
        }
        throw new IllegalAccessException("the item has already been deleted!");
    }

    public void onEventMainThread(final ConversationRemoveEvent conversationRemoveEvent) {
        int findPosition = this.mAdapter.findPosition(conversationRemoveEvent.getType(), conversationRemoveEvent.getTargetId());
        if (RongContext.getInstance().getConversationGatherState(conversationRemoveEvent.getType().getName()).booleanValue()) {
            if (findPosition >= 0) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> list) {
                        int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversationRemoveEvent.getType(), conversationRemoveEvent.getTargetId());
                        if (list == null || list.size() == 0) {
                            if (findPosition >= 0) {
                                ConversationListFragment.this.mAdapter.remove(findPosition);
                            }
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            return;
                        }
                        UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                        if (findPosition >= 0) {
                            ConversationListFragment.this.mAdapter.remove(findPosition);
                        }
                        ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }, new ConversationType[]{conversationRemoveEvent.getType()});
            }
        } else if (findPosition >= 0) {
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

    public void onEventMainThread(MessageDeleteEvent messageDeleteEvent) {
        int count = this.mAdapter.getCount();
        int i = 0;
        while (i < count) {
            if (!messageDeleteEvent.getMessageIds().contains(Integer.valueOf(((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()))) {
                i++;
            } else if (((UIConversation) this.mAdapter.getItem(i)).getConversationGatherState()) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> list) {
                        if (list != null && list.size() != 0) {
                            UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                            int findPosition = ConversationListFragment.this.mAdapter.findPosition(access$600.getConversationType(), access$600.getConversationTargetId());
                            if (findPosition >= 0) {
                                ConversationListFragment.this.mAdapter.remove(findPosition);
                            }
                            ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                        }
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }, new ConversationType[]{((UIConversation) this.mAdapter.getItem(i)).getConversationType()});
                return;
            } else {
                RongIM.getInstance().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        if (conversation == null) {
                            RLog.m19419d(ConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                            return;
                        }
                        UIConversation obtain = UIConversation.obtain(conversation, false);
                        int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                        if (findPosition >= 0) {
                            ConversationListFragment.this.mAdapter.remove(findPosition);
                        }
                        ConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, ConversationListFragment.this.mAdapter));
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                });
                return;
            }
        }
    }

    public void onEventMainThread(ConversationNotificationEvent conversationNotificationEvent) {
        int findPosition = this.mAdapter.findPosition(conversationNotificationEvent.getConversationType(), conversationNotificationEvent.getTargetId());
        if (findPosition >= 0) {
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onEventMainThread(MessagesClearEvent messagesClearEvent) {
        if (this.mAdapter.findPosition(messagesClearEvent.getType(), messagesClearEvent.getTargetId()) < 0) {
            return;
        }
        if (RongContext.getInstance().getConversationGatherState(messagesClearEvent.getType().getName()).booleanValue()) {
            RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                public void onSuccess(List<Conversation> list) {
                    if (list != null && list.size() != 0) {
                        UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                        int findPosition = ConversationListFragment.this.mAdapter.findPosition(access$600.getConversationType(), access$600.getConversationTargetId());
                        if (findPosition >= 0) {
                            ConversationListFragment.this.mAdapter.remove(findPosition);
                        }
                        ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }, new ConversationType[]{ConversationType.GROUP});
            return;
        }
        RongIMClient.getInstance().getConversation(messagesClearEvent.getType(), messagesClearEvent.getTargetId(), new ResultCallback<Conversation>() {
            public void onSuccess(Conversation conversation) {
                if (conversation != null) {
                    UIConversation obtain = UIConversation.obtain(conversation, false);
                    int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                    if (findPosition >= 0) {
                        ConversationListFragment.this.mAdapter.remove(findPosition);
                    }
                    ConversationListFragment.this.mAdapter.add(obtain, ConversationListUtils.findPositionForNewConversation(obtain, ConversationListFragment.this.mAdapter));
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            }
        });
    }

    public void onEventMainThread(OnMessageSendErrorEvent onMessageSendErrorEvent) {
        int findPosition = this.mAdapter.findPosition(onMessageSendErrorEvent.getMessage().getConversationType(), onMessageSendErrorEvent.getMessage().getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            uIConversation.setUIConversationTime(onMessageSendErrorEvent.getMessage().getSentTime());
            uIConversation.setMessageContent(onMessageSendErrorEvent.getMessage().getContent());
            uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
            uIConversation.setSentStatus(Message$SentStatus.FAILED);
            this.mAdapter.remove(findPosition);
            this.mAdapter.add(uIConversation, ConversationListUtils.findPositionForNewConversation(uIConversation, this.mAdapter));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(QuitDiscussionEvent quitDiscussionEvent) {
        int findPosition = this.mAdapter.findPosition(ConversationType.DISCUSSION, quitDiscussionEvent.getDiscussionId());
        if (findPosition >= 0) {
            if (!RongContext.getInstance().getConversationGatherState(ConversationType.DISCUSSION.getName()).booleanValue()) {
                this.mAdapter.remove(findPosition);
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(QuitGroupEvent quitGroupEvent) {
        final int findPosition = this.mAdapter.findPosition(ConversationType.GROUP, quitGroupEvent.getGroupId());
        boolean booleanValue = RongContext.getInstance().getConversationGatherState(ConversationType.GROUP.getName()).booleanValue();
        if (findPosition >= 0 && booleanValue) {
            RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                public void onSuccess(List<Conversation> list) {
                    if (list == null || list.size() == 0) {
                        if (findPosition >= 0) {
                            ConversationListFragment.this.mAdapter.remove(findPosition);
                        }
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                        return;
                    }
                    UIConversation access$600 = ConversationListFragment.this.makeUIConversationFromList(list);
                    int findPosition = ConversationListFragment.this.mAdapter.findPosition(access$600.getConversationType(), access$600.getConversationTargetId());
                    if (findPosition >= 0) {
                        ConversationListFragment.this.mAdapter.remove(findPosition);
                    }
                    ConversationListFragment.this.mAdapter.add(access$600, ConversationListUtils.findPositionForNewConversation(access$600, ConversationListFragment.this.mAdapter));
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }, new ConversationType[]{ConversationType.GROUP});
        } else if (findPosition >= 0) {
            this.mAdapter.remove(findPosition);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(AudioListenedEvent audioListenedEvent) {
        int findPosition = this.mAdapter.findPosition(audioListenedEvent.getConversationType(), audioListenedEvent.getTargetId());
        if (findPosition >= 0) {
            UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(findPosition);
            if (uIConversation.getLatestMessageId() == audioListenedEvent.getLatestMessageId()) {
                uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
            }
            this.mAdapter.getView(findPosition, this.mList.getChildAt(findPosition - this.mList.getFirstVisiblePosition()), this.mList);
        }
    }

    public void onPortraitItemClick(View view, UIConversation uIConversation) {
        ConversationType conversationType = uIConversation.getConversationType();
        if (RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
            RongIM.getInstance().startSubConversationList(getActivity(), conversationType);
        } else if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitClick(getActivity(), conversationType, uIConversation.getConversationTargetId())) {
            uIConversation.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(getActivity(), conversationType, uIConversation.getConversationTargetId(), uIConversation.getUIConversationTitle());
        }
    }

    public boolean onPortraitItemLongClick(View view, UIConversation uIConversation) {
        ConversationType conversationType = uIConversation.getConversationType();
        if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitLongClick(getActivity(), conversationType, uIConversation.getConversationTargetId())) {
            if (RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
                buildSingleDialog(uIConversation);
            } else {
                buildMultiDialog(uIConversation);
            }
        }
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        UIConversation uIConversation = (UIConversation) adapterView.getAdapter().getItem(i);
        ConversationType conversationType = uIConversation.getConversationType();
        if (RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
            RongIM.getInstance().startSubConversationList(getActivity(), conversationType);
        } else if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationClick(getActivity(), view, uIConversation)) {
            uIConversation.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(getActivity(), conversationType, uIConversation.getConversationTargetId(), uIConversation.getUIConversationTitle());
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
        String name = uIConversation.getConversationType().getName();
        if (RongContext.getInstance().getConversationListBehaviorListener() == null || !RongContext.getInstance().getConversationListBehaviorListener().onConversationLongClick(getActivity(), view, uIConversation)) {
            if (RongContext.getInstance().getConversationGatherState(name).booleanValue()) {
                buildSingleDialog(uIConversation);
            } else {
                buildMultiDialog(uIConversation);
            }
        }
        return true;
    }

    private void buildMultiDialog(final UIConversation uIConversation) {
        String[] strArr = new String[2];
        if (uIConversation.isTop()) {
            strArr[0] = RongContext.getInstance().getString(C4974R.string.rc_conversation_list_dialog_cancel_top);
        } else {
            strArr[0] = RongContext.getInstance().getString(C4974R.string.rc_conversation_list_dialog_set_top);
        }
        strArr[1] = RongContext.getInstance().getString(C4974R.string.rc_conversation_list_dialog_remove);
        ArraysDialogFragment.newInstance(uIConversation.getUIConversationTitle(), strArr).setArraysDialogItemListener(new OnArraysDialogItemListener() {

            /* renamed from: io.rong.imkit.fragment.ConversationListFragment$16$1 */
            class C50281 extends ResultCallback<Boolean> {
                C50281() {
                }

                public void onSuccess(Boolean bool) {
                    if (uIConversation.isTop()) {
                        Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(C4974R.string.rc_conversation_list_popup_cancel_top), 0).show();
                    } else {
                        Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(C4974R.string.rc_conversation_list_dialog_set_top), 0).show();
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
                    instance.setConversationToTop(conversationType, conversationTargetId, z, new C50281());
                } else if (i == 1) {
                    RongIM.getInstance().removeConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId());
                }
            }
        }).show(getFragmentManager());
    }

    private void buildSingleDialog(final UIConversation uIConversation) {
        ArraysDialogFragment.newInstance(uIConversation.getUIConversationTitle(), new String[]{RongContext.getInstance().getString(C4974R.string.rc_conversation_list_dialog_remove)}).setArraysDialogItemListener(new OnArraysDialogItemListener() {

            /* renamed from: io.rong.imkit.fragment.ConversationListFragment$17$1 */
            class C50291 extends ResultCallback<List<Conversation>> {
                C50291() {
                }

                public void onSuccess(List<Conversation> list) {
                    if (list != null && list.size() != 0) {
                        for (Conversation conversation : list) {
                            RongIM.getInstance().removeConversation(conversation.getConversationType(), conversation.getTargetId());
                        }
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }

            public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
                RongIM.getInstance().getConversationList(new C50291(), new ConversationType[]{uIConversation.getConversationType()});
            }
        }).show(getFragmentManager());
    }

    private void makeUiConversationList(List<Conversation> list) {
        for (Conversation conversation : list) {
            ConversationType conversationType = conversation.getConversationType();
            boolean booleanValue = RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue();
            int findPosition = this.mAdapter.findPosition(conversationType, conversation.getTargetId());
            UIConversation obtain = UIConversation.obtain(conversation, booleanValue);
            if (findPosition < 0) {
                this.mAdapter.add(obtain);
            }
            refreshUnreadCount(obtain.getConversationType(), obtain.getConversationTargetId());
        }
    }

    private UIConversation makeUiConversation(Message message, int i) {
        if (i < 0) {
            return UIConversation.obtain(message, RongContext.getInstance().getConversationGatherState(message.getConversationType().getName()).booleanValue());
        }
        UIConversation uIConversation = (UIConversation) this.mAdapter.getItem(i);
        if (uIConversation == null) {
            return uIConversation;
        }
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
        if (!uIConversation.getMentionedFlag()) {
            MentionedInfo mentionedInfo = message.getContent().getMentionedInfo();
            if (mentionedInfo != null && ((mentionedInfo.getType().equals(MentionedType.PART) && mentionedInfo.getMentionedUserIdList().contains(RongIMClient.getInstance().getCurrentUserId())) || mentionedInfo.getType().equals(MentionedType.ALL))) {
                uIConversation.setMentionedFlag(true);
            }
        }
        String str = "";
        UserInfo userInfo = message.getContent().getUserInfo();
        ConversationType conversationType = message.getConversationType();
        if (userInfo == null || !message.getTargetId().equals(userInfo.getUserId())) {
            return uIConversation;
        }
        if (!conversationType.equals(ConversationType.PRIVATE) && !conversationType.equals(ConversationType.SYSTEM)) {
            return uIConversation;
        }
        if ((uIConversation.getUIConversationTitle() == null || userInfo.getName() == null || userInfo.getName().equals(uIConversation.getUIConversationTitle())) && (uIConversation.getIconUrl() == null || userInfo.getPortraitUri() == null || userInfo.getPortraitUri().equals(uIConversation.getIconUrl()))) {
            return uIConversation;
        }
        Uri portraitUri = userInfo.getPortraitUri();
        RongIMClient.getInstance().updateConversationInfo(message.getConversationType(), message.getTargetId(), userInfo.getName(), portraitUri != null ? portraitUri.toString() : "", null);
        return uIConversation;
    }

    private UIConversation makeUIConversationFromList(List<Conversation> list) {
        Conversation conversation = (Conversation) list.get(0);
        boolean z = false;
        int i = 0;
        Conversation conversation2 = conversation;
        for (Conversation conversation3 : list) {
            if (conversation2.isTop()) {
                if (conversation3.isTop() && conversation3.getSentTime() > conversation2.getSentTime()) {
                    conversation2 = conversation3;
                }
            } else if (conversation3.isTop() || conversation3.getSentTime() > conversation2.getSentTime()) {
                conversation2 = conversation3;
            }
            if (conversation3.isTop()) {
                z = true;
            }
            i = conversation3.getUnreadMessageCount() + i;
        }
        UIConversation obtain = UIConversation.obtain(conversation2, RongContext.getInstance().getConversationGatherState(conversation2.getConversationType().getName()).booleanValue());
        obtain.setUnReadMessageCount(i);
        obtain.setTop(z);
        return obtain;
    }

    private void refreshUnreadCount(final ConversationType conversationType, final String str) {
        if (RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue()) {
            RongIM.getInstance().getUnreadCount(new ResultCallback<Integer>() {
                public void onSuccess(Integer num) {
                    int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversationType, str);
                    if (findPosition >= 0) {
                        ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setUnReadMessageCount(num.intValue());
                        ConversationListFragment.this.mAdapter.getView(findPosition, ConversationListFragment.this.mList.getChildAt(findPosition - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    System.err.print("Throw exception when get unread message count from ipc remote side!");
                }
            }, new ConversationType[]{conversationType});
            return;
        }
        RongIM.getInstance().getUnreadCount(conversationType, str, new ResultCallback<Integer>() {
            public void onSuccess(Integer num) {
                int findPosition = ConversationListFragment.this.mAdapter.findPosition(conversationType, str);
                if (findPosition >= 0) {
                    ((UIConversation) ConversationListFragment.this.mAdapter.getItem(findPosition)).setUnReadMessageCount(num.intValue());
                    ConversationListFragment.this.mAdapter.getView(findPosition, ConversationListFragment.this.mList.getChildAt(findPosition - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                }
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            }
        });
    }
}
