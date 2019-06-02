package io.rong.imkit.fragment;

import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.RLog;
import io.rong.common.SystemUtils;
import io.rong.eventbus.EventBus;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.model.EmojiMessageAdapter;
import io.rong.imkit.model.Event.ConnectEvent;
import io.rong.imkit.model.Event.GroupUserInfoEvent;
import io.rong.imkit.model.Event.InputViewEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageRecallEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.model.Event.OnReceiveMessageProgressEvent;
import io.rong.imkit.model.Event.PlayAudioEvent;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imkit.model.Event.RemoteMessageRecallEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.InputView.Event;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imkit.widget.adapter.MessageListAdapter.OnItemHandlerListener;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$ReceivedStatus;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.LocationMessage;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.VoiceMessage;
import java.util.ArrayList;
import java.util.List;

public class MessageListFragment extends UriFragment implements OnScrollListener {
    static final int DELETE_MESSAGE = 11;
    private static final int LISTVIEW_SHOW_COUNT = 5;
    static final int NOTIFY_LIST = 9;
    static final int REFRESH_ITEM = 4;
    static final int REFRESH_ITEM_READ_RECEIPT = 7;
    static final int REFRESH_LIST_WHILE_RECEIVE_MESSAGE = 3;
    static final int RENDER_HISTORY = 6;
    static final int RENDER_LIST = 2;
    static final int REQ_HISTORY = 5;
    static final int REQ_LIST = 1;
    static final int REQ_REMOTE_HISTORY = 8;
    static final int REQ_UNREAD = 12;
    static final int RESET_LIST_STACK = 10;
    private static final String TAG = "MessageListFragment";
    boolean isLoading = false;
    private boolean isOnClickBtn;
    boolean isShowNewMessageState;
    boolean isShowUnreadMessageState;
    private boolean isShowWithoutConnected = false;
    MessageListAdapter mAdapter;
    Conversation mConversation;
    GestureDetector mGestureDetector;
    boolean mHasMoreLocalMessages = true;
    boolean mHasMoreRemoteMessages = true;
    View mHeaderView;
    long mLastRemoteMessageTime = 0;
    int mLastVisiblePosition;
    ListView mList;
    int mMessageleft = -1;
    ImageButton mNewMessageBtn;
    int mNewMessageCount;
    TextView mNewMessageTextView;
    Button mUnreadBtn;
    int mUnreadCount;
    private List<Message> mUnreadMentionMessages;
    boolean needEvaluateForRobot = false;
    OnScrollListener onScrollListener;
    boolean robotMode = true;

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$1 */
    class C50461 extends SimpleOnGestureListener {
        C50461() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (f2 > 0.0f && MessageListFragment.this.mNewMessageCount >= 0 && MessageListFragment.this.mList.getLastVisiblePosition() >= MessageListFragment.this.mList.getCount() - MessageListFragment.this.mNewMessageCount) {
                MessageListFragment.this.mNewMessageTextView.setText((MessageListFragment.this.mList.getCount() - MessageListFragment.this.mList.getLastVisiblePosition()) + "");
                MessageListFragment.this.mNewMessageCount = (MessageListFragment.this.mList.getCount() - MessageListFragment.this.mList.getLastVisiblePosition()) - 1;
                if (MessageListFragment.this.mNewMessageCount > 99) {
                    MessageListFragment.this.mNewMessageTextView.setText("99+");
                } else {
                    MessageListFragment.this.mNewMessageTextView.setText(MessageListFragment.this.mNewMessageCount + "");
                }
            }
            if (MessageListFragment.this.mNewMessageCount == 0) {
                MessageListFragment.this.mNewMessageBtn.setVisibility(8);
                MessageListFragment.this.mNewMessageTextView.setVisibility(8);
            }
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$2 */
    class C50472 implements Runnable {
        C50472() {
        }

        public void run() {
            MessageListFragment.this.mAdapter.clear();
            MessageListFragment.this.mAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$3 */
    class C50483 implements OnClickListener {
        C50483() {
        }

        public void onClick(View view) {
            MessageListFragment.this.getHandler().postDelayed(new ScrollRunnable(), 500);
            MessageListFragment.this.mList.smoothScrollToPosition(MessageListFragment.this.mList.getCount() + 1);
            MessageListFragment.this.mNewMessageCount = 0;
            MessageListFragment.this.mNewMessageBtn.setVisibility(8);
            MessageListFragment.this.mNewMessageTextView.setVisibility(8);
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$4 */
    class C50524 implements OnItemHandlerListener {
        C50524() {
        }

        public void onWarningViewClick(int i, final Message message, View view) {
            RongIM.getInstance().deleteMessages(new int[]{message.getMessageId()}, new ResultCallback<Boolean>() {

                /* renamed from: io.rong.imkit.fragment.MessageListFragment$4$1$1 */
                class C50491 extends RongIMClient$SendImageMessageCallback {
                    C50491() {
                    }

                    public void onAttached(Message message) {
                    }

                    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }

                    public void onSuccess(Message message) {
                    }

                    public void onProgress(Message message, int i) {
                    }
                }

                /* renamed from: io.rong.imkit.fragment.MessageListFragment$4$1$2 */
                class C50502 implements ISendMessageCallback {
                    C50502() {
                    }

                    public void onAttached(Message message) {
                    }

                    public void onSuccess(Message message) {
                    }

                    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }

                public void onSuccess(Boolean bool) {
                    if (bool.booleanValue()) {
                        message.setMessageId(0);
                        if (message.getContent() instanceof ImageMessage) {
                            RongIM.getInstance().sendImageMessage(message, "", "", new C50491());
                        } else if (message.getContent() instanceof LocationMessage) {
                            RongIM.getInstance().sendLocationMessage(message, null, null, null);
                        } else {
                            RongIM.getInstance().sendMessage(message, null, null, new C50502());
                        }
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$5 */
    class C50535 implements OnTouchListener {
        C50535() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2 || motionEvent.getAction() == 0) {
                EventBus.getDefault().post(InputViewEvent.obtain(false));
                if (motionEvent.getAction() == 2 && MessageListFragment.this.mList.getCount() == 0 && MessageListFragment.this.mHasMoreRemoteMessages && MessageListFragment.this.mConversation.getConversationType() != ConversationType.CHATROOM && MessageListFragment.this.mConversation.getConversationType() != ConversationType.APP_PUBLIC_SERVICE && MessageListFragment.this.mConversation.getConversationType() != ConversationType.PUBLIC_SERVICE) {
                    try {
                        if (MessageListFragment.this.getResources().getBoolean(C4974R.bool.rc_enable_get_remote_history_message)) {
                            MessageListFragment.this.isLoading = true;
                            MessageListFragment.this.getHandler().sendEmptyMessage(8);
                        }
                    } catch (NotFoundException e) {
                        MessageListFragment.this.mHasMoreRemoteMessages = false;
                        RLog.m19420e(MessageListFragment.TAG, "get_remote_history_message disabled.");
                    }
                }
            }
            MessageListFragment.this.mGestureDetector.onTouchEvent(motionEvent);
            return false;
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$6 */
    class C50546 implements OnItemClickListener {
        C50546() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            RongContext.getInstance().getPrimaryInputProvider().onInactive(view.getContext());
            RongContext.getInstance().getSecondaryInputProvider().onInactive(view.getContext());
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$7 */
    class C50567 implements AnimationListener {

        /* renamed from: io.rong.imkit.fragment.MessageListFragment$7$1 */
        class C50551 implements Runnable {
            C50551() {
            }

            public void run() {
                if (!MessageListFragment.this.isOnClickBtn) {
                    Animation translateAnimation = new TranslateAnimation(0.0f, 700.0f, 0.0f, 0.0f);
                    translateAnimation.setDuration(700);
                    translateAnimation.setFillAfter(true);
                    MessageListFragment.this.mUnreadBtn.startAnimation(translateAnimation);
                }
            }
        }

        C50567() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            MessageListFragment.this.getHandler().postDelayed(new C50551(), 4000);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$8 */
    class C50578 extends ResultCallback<List<UIMessage>> {
        C50578() {
        }

        public void onSuccess(List<UIMessage> list) {
            boolean z;
            RLog.m19419d(MessageListFragment.TAG, "getLatestMessages, onSuccess " + list.size());
            MessageListFragment messageListFragment = MessageListFragment.this;
            if (list.size() == 30) {
                z = true;
            } else {
                z = false;
            }
            messageListFragment.mHasMoreLocalMessages = z;
            MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
            MessageListFragment.this.isLoading = false;
            MessageListFragment.this.getHandler().obtainMessage(2, list).sendToTarget();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19420e(MessageListFragment.TAG, "getLatestMessages, " + rongIMClient$ErrorCode.toString());
            MessageListFragment.this.mHasMoreLocalMessages = false;
            MessageListFragment.this.isLoading = false;
            MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageListFragment$9 */
    class C50589 extends ResultCallback<List<UIMessage>> {
        C50589() {
        }

        public void onSuccess(List<UIMessage> list) {
            boolean z;
            RLog.m19419d(MessageListFragment.TAG, "getHistoryMessages, onSuccess " + list.size());
            MessageListFragment messageListFragment = MessageListFragment.this;
            if (list.size() == 30) {
                z = true;
            } else {
                z = false;
            }
            messageListFragment.mHasMoreLocalMessages = z;
            MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
            MessageListFragment.this.isLoading = false;
            MessageListFragment.this.getHandler().obtainMessage(6, list).sendToTarget();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            MessageListFragment.this.mHasMoreLocalMessages = false;
            MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
            MessageListFragment.this.isLoading = false;
            RLog.m19420e(MessageListFragment.TAG, "getHistoryMessages, " + rongIMClient$ErrorCode.toString());
        }
    }

    public static class Builder {
        private ConversationType conversationType;
        private String targetId;
        private Uri uri;

        public ConversationType getConversationType() {
            return this.conversationType;
        }

        public void setConversationType(ConversationType conversationType) {
            this.conversationType = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String str) {
            this.targetId = str;
        }
    }

    public class ScrollRunnable implements Runnable {
        public void run() {
            if (MessageListFragment.this.mList.getLastVisiblePosition() < MessageListFragment.this.mList.getCount() - 1) {
                MessageListFragment.this.mList.setSelection(MessageListFragment.this.mList.getLastVisiblePosition() + 10);
                MessageListFragment.this.getHandler().postDelayed(new ScrollRunnable(), 100);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RongContext.getInstance().getEventBus().register(this);
        this.isShowUnreadMessageState = RongContext.getInstance().getUnreadMessageState();
        this.isShowNewMessageState = RongContext.getInstance().getNewMessageState();
        if (EmojiMessageAdapter.getInstance() == null) {
            EmojiMessageAdapter.init(RongContext.getInstance());
        }
        this.mAdapter = new MessageListAdapter(getActivity());
        this.mGestureDetector = new GestureDetector(new C50461());
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    protected void initFragment(Uri uri) {
        RLog.m19419d(TAG, "initFragment " + uri);
        ConversationType valueOf = ConversationType.valueOf(uri.getLastPathSegment().toUpperCase());
        Object queryParameter = uri.getQueryParameter("targetId");
        String queryParameter2 = uri.getQueryParameter(WebActivity.EXTRA_TITLE);
        if (!TextUtils.isEmpty(queryParameter) && valueOf != null) {
            this.mConversation = Conversation.obtain(valueOf, queryParameter, queryParameter2);
            if (this.mAdapter != null) {
                getHandler().post(new C50472());
            }
            this.mNewMessageBtn.setOnClickListener(new C50483());
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
                RLog.m19420e(TAG, "initFragment Not connected yet.");
                this.isShowWithoutConnected = true;
                return;
            }
            getConversation();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_messagelist, viewGroup, false);
        this.mUnreadBtn = (Button) findViewById(inflate, C4974R.id.rc_unread_message_count);
        this.mNewMessageBtn = (ImageButton) findViewById(inflate, C4974R.id.rc_new_message_count);
        this.mNewMessageTextView = (TextView) findViewById(inflate, C4974R.id.rc_new_message_number);
        this.mList = (ListView) findViewById(inflate, C4974R.id.rc_list);
        this.mHeaderView = layoutInflater.inflate(C4974R.layout.rc_item_progress, null);
        this.mList.addHeaderView(this.mHeaderView);
        this.mList.setOnScrollListener(this);
        this.mList.setSelectionAfterHeaderView();
        this.mAdapter.setOnItemHandlerListener(new C50524());
        return inflate;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                if (absListView.getFirstVisiblePosition() != 0 || this.mAdapter.getCount() <= 0 || !this.mHasMoreLocalMessages || this.isLoading) {
                    if (!(absListView.getFirstVisiblePosition() != 0 || this.mHasMoreLocalMessages || !this.mHasMoreRemoteMessages || this.isLoading || this.mConversation.getConversationType() == ConversationType.CHATROOM || this.mConversation.getConversationType() == ConversationType.APP_PUBLIC_SERVICE || this.mConversation.getConversationType() == ConversationType.PUBLIC_SERVICE)) {
                        try {
                            if (absListView.getResources().getBoolean(C4974R.bool.rc_enable_get_remote_history_message)) {
                                this.isLoading = true;
                                this.mLastRemoteMessageTime = ((UIMessage) this.mAdapter.getItem(0)).getSentTime();
                                getHandler().sendEmptyMessage(8);
                                break;
                            }
                        } catch (NotFoundException e) {
                            this.mHasMoreRemoteMessages = false;
                            RLog.m19420e(TAG, "get_remote_history_message disabled.");
                            break;
                        }
                    }
                }
                this.isLoading = true;
                getHandler().sendEmptyMessage(5);
                break;
                break;
        }
        if (this.onScrollListener != null) {
            this.onScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.onScrollListener != null) {
            this.onScrollListener.onScroll(absListView, i, i2, i3);
        }
        if (i + i2 >= i3 - this.mNewMessageCount) {
            this.mNewMessageCount = 0;
            this.mNewMessageBtn.setVisibility(8);
            this.mNewMessageTextView.setVisibility(8);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (getActionBarHandler() != null) {
            getActionBarHandler().onTitleChanged(this.mConversation.getConversationTitle());
        }
        this.mList.setOnTouchListener(new C50535());
        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(new C50546());
        super.onViewCreated(view, bundle);
    }

    public boolean onBackPressed() {
        return false;
    }

    private List<UIMessage> filterMessage(List<UIMessage> list) {
        if (this.mAdapter.getCount() <= 0) {
            return list;
        }
        List<UIMessage> arrayList = new ArrayList();
        int i = 0;
        while (i < this.mAdapter.getCount()) {
            for (UIMessage uIMessage : list) {
                if (!(arrayList.contains(uIMessage) || uIMessage.getMessageId() == ((UIMessage) this.mAdapter.getItem(i)).getMessageId())) {
                    arrayList.add(uIMessage);
                }
            }
            i++;
        }
        return arrayList;
    }

    public boolean handleMessage(android.os.Message message) {
        RLog.m19419d(TAG, "MessageListFragment msg : " + message.what);
        int intValue;
        switch (message.what) {
            case 1:
                this.mAdapter.clear();
                this.mAdapter.notifyDataSetChanged();
                EmojiMessageAdapter.getInstance().getLatestMessages(this.mConversation.getConversationType(), this.mConversation.getTargetId(), 30, new C50578());
                break;
            case 2:
                if (message.obj != null && (message.obj instanceof List)) {
                    List list = (List) message.obj;
                    this.mAdapter.clear();
                    this.mAdapter.addCollection(filterMessage(list));
                    if (list.size() <= 5) {
                        this.mList.setStackFromBottom(false);
                        this.mList.setTranscriptMode(2);
                    } else {
                        this.mList.setStackFromBottom(true);
                        this.mList.setTranscriptMode(2);
                    }
                    this.mAdapter.notifyDataSetChanged();
                    getHandler().sendEmptyMessage(10);
                }
                if (this.mUnreadMentionMessages != null && this.mUnreadMentionMessages.size() > 0) {
                    this.mList.smoothScrollToPosition(this.mAdapter.findPosition((Object) UIMessage.obtain((Message) this.mUnreadMentionMessages.get(0))));
                }
                if (this.mUnreadCount >= 10) {
                    Animation translateAnimation = new TranslateAnimation(300.0f, 0.0f, 0.0f, 0.0f);
                    Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    translateAnimation.setDuration(1000);
                    alphaAnimation.setDuration(2000);
                    Animation animationSet = new AnimationSet(true);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(alphaAnimation);
                    this.mUnreadBtn.setVisibility(0);
                    this.mUnreadBtn.startAnimation(animationSet);
                    animationSet.setAnimationListener(new C50567());
                    break;
                }
                break;
            case 4:
                intValue = ((Integer) message.obj).intValue();
                if (intValue >= this.mList.getFirstVisiblePosition() && intValue <= this.mList.getLastVisiblePosition()) {
                    RLog.m19419d(TAG, "REFRESH_ITEM Index:" + intValue);
                    this.mAdapter.getView(intValue, this.mList.getChildAt((intValue - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                    break;
                }
            case 5:
                UIMessage uIMessage = (UIMessage) this.mAdapter.getItem(0);
                this.mList.addHeaderView(this.mHeaderView);
                EmojiMessageAdapter.getInstance().getHistoryMessages(this.mConversation.getConversationType(), this.mConversation.getTargetId(), uIMessage.getMessageId(), 30, new C50589());
                break;
            case 6:
                if (message.obj instanceof List) {
                    List<UIMessage> list2 = (List) message.obj;
                    for (UIMessage add : list2) {
                        this.mAdapter.add(add, 0);
                    }
                    this.mList.setTranscriptMode(0);
                    this.mList.setStackFromBottom(false);
                    int firstVisiblePosition = this.mList.getFirstVisiblePosition();
                    this.mAdapter.notifyDataSetChanged();
                    if (firstVisiblePosition == 0) {
                        this.mList.setSelection(list2.size());
                        break;
                    }
                }
                break;
            case 7:
                intValue = ((Integer) message.obj).intValue();
                if (intValue >= this.mList.getFirstVisiblePosition() && intValue <= this.mList.getLastVisiblePosition()) {
                    RLog.m19419d(TAG, "REFRESH_ITEM Index:" + intValue);
                    this.mAdapter.getView(intValue, this.mList.getChildAt((intValue - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                    break;
                }
            case 8:
                this.mList.addHeaderView(this.mHeaderView);
                EmojiMessageAdapter.getInstance().getRemoteHistoryMessages(this.mConversation.getConversationType(), this.mConversation.getTargetId(), this.mLastRemoteMessageTime, 10, new ResultCallback<List<UIMessage>>() {
                    public void onSuccess(List<UIMessage> list) {
                        MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
                        if (list == null || list.size() == 0) {
                            MessageListFragment.this.mHasMoreRemoteMessages = false;
                        } else {
                            RLog.m19419d(MessageListFragment.TAG, "getRemoteHistoryMessages, onSuccess " + list.size());
                            MessageListFragment.this.mHasMoreRemoteMessages = list.size() >= 10;
                            List arrayList = new ArrayList();
                            for (UIMessage uIMessage : list) {
                                boolean z;
                                String uId = uIMessage.getUId();
                                int count = MessageListFragment.this.mAdapter.getCount();
                                for (int i = 0; i < count; i++) {
                                    String uId2 = ((UIMessage) MessageListFragment.this.mAdapter.getItem(i)).getUId();
                                    if (uId != null && uId2 != null && uId.equals(uId2)) {
                                        z = false;
                                        break;
                                    }
                                }
                                z = true;
                                if (z) {
                                    arrayList.add(uIMessage);
                                }
                            }
                            RLog.m19419d(MessageListFragment.TAG, "getRemoteHistoryMessages, src: " + list.size() + " dest: " + arrayList.size());
                            MessageListFragment.this.getHandler().obtainMessage(6, arrayList).sendToTarget();
                        }
                        MessageListFragment.this.isLoading = false;
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        MessageListFragment.this.mHasMoreRemoteMessages = false;
                        MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
                        MessageListFragment.this.isLoading = false;
                        RLog.m19420e(MessageListFragment.TAG, "getRemoteHistoryMessages, " + rongIMClient$ErrorCode.toString());
                    }
                });
                break;
            case 9:
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                    break;
                }
                break;
            case 10:
                resetListViewStack();
                this.mAdapter.notifyDataSetChanged();
                break;
            case 11:
                this.mAdapter.notifyDataSetChanged();
                getHandler().post(new Runnable() {
                    public void run() {
                        if (MessageListFragment.this.mList.getCount() > 0) {
                            View childAt = MessageListFragment.this.mList.getChildAt(MessageListFragment.this.mList.getFirstVisiblePosition());
                            View childAt2 = MessageListFragment.this.mList.getChildAt(MessageListFragment.this.mList.getLastVisiblePosition());
                            if (childAt != null && childAt2 != null) {
                                if (childAt2.getBottom() - (childAt.getTop() == -1 ? 0 : childAt.getTop()) < MessageListFragment.this.mList.getBottom() - (MessageListFragment.this.mList.getListPaddingBottom() + MessageListFragment.this.mList.getListPaddingTop())) {
                                    MessageListFragment.this.mList.setTranscriptMode(2);
                                    MessageListFragment.this.mList.setStackFromBottom(false);
                                } else {
                                    MessageListFragment.this.mList.setTranscriptMode(1);
                                }
                                MessageListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                break;
            case 12:
                EmojiMessageAdapter.getInstance().getHistoryMessages(this.mConversation.getConversationType(), this.mConversation.getTargetId(), ((UIMessage) this.mAdapter.getItem(0)).getMessageId(), this.mUnreadCount - 29, new ResultCallback<List<UIMessage>>() {
                    public void onSuccess(List<UIMessage> list) {
                        boolean z;
                        RLog.m19419d(MessageListFragment.TAG, "getHistoryMessages unread, onSuccess " + list.size());
                        MessageListFragment messageListFragment = MessageListFragment.this;
                        if (list.size() == MessageListFragment.this.mUnreadCount - 29) {
                            z = true;
                        } else {
                            z = false;
                        }
                        messageListFragment.mHasMoreLocalMessages = z;
                        MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
                        for (UIMessage add : list) {
                            MessageListFragment.this.mAdapter.add(add, 0);
                        }
                        MessageListFragment.this.mAdapter.notifyDataSetChanged();
                        MessageListFragment.this.mList.setStackFromBottom(false);
                        MessageListFragment.this.mList.smoothScrollToPosition(0);
                        MessageListFragment.this.isLoading = false;
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        RLog.m19420e(MessageListFragment.TAG, "getHistoryMessages, " + rongIMClient$ErrorCode.toString());
                        MessageListFragment.this.mHasMoreLocalMessages = false;
                        MessageListFragment.this.mList.removeHeaderView(MessageListFragment.this.mHeaderView);
                        MessageListFragment.this.isLoading = false;
                    }
                });
                break;
        }
        return false;
    }

    private void resetListViewStack() {
        int childCount = this.mList.getChildCount();
        View childAt = this.mList.getChildAt(0);
        View childAt2 = this.mList.getChildAt(childCount - 1);
        if (childAt != null && childAt2 != null) {
            if (childAt2.getBottom() - (childAt.getTop() == -1 ? 0 : childAt.getTop()) < this.mList.getBottom() - (this.mList.getListPaddingBottom() + this.mList.getListPaddingTop())) {
                this.mList.setTranscriptMode(2);
                this.mList.setStackFromBottom(false);
                return;
            }
            this.mList.setTranscriptMode(2);
        }
    }

    public void onEventMainThread(ReadReceiptEvent readReceiptEvent) {
        if (this.mConversation != null && this.mConversation.getTargetId().equals(readReceiptEvent.getMessage().getTargetId()) && this.mConversation.getConversationType() == readReceiptEvent.getMessage().getConversationType() && readReceiptEvent.getMessage().getConversationType() == ConversationType.PRIVATE) {
            long lastMessageSendTime = ((ReadReceiptMessage) readReceiptEvent.getMessage().getContent()).getLastMessageSendTime();
            int count = this.mAdapter.getCount() - 1;
            while (count >= 0 && ((UIMessage) this.mAdapter.getItem(count)).getSentStatus() != Message$SentStatus.READ) {
                if (((UIMessage) this.mAdapter.getItem(count)).getSentStatus() == Message$SentStatus.SENT && ((UIMessage) this.mAdapter.getItem(count)).getMessageDirection().equals(Message$MessageDirection.SEND) && lastMessageSendTime >= ((UIMessage) this.mAdapter.getItem(count)).getSentTime()) {
                    ((UIMessage) this.mAdapter.getItem(count)).setSentStatus(Message$SentStatus.READ);
                    getHandler().obtainMessage(7, Integer.valueOf(count)).sendToTarget();
                }
                count--;
            }
        }
    }

    private void refreshListWhileReceiveMessage(UIMessage uIMessage) {
        uIMessage.setIsHistoryMessage(false);
        this.mAdapter.setEvaluateForRobot(this.needEvaluateForRobot);
        this.mAdapter.setRobotMode(this.robotMode);
        this.mAdapter.add(uIMessage);
        if (!(!this.isShowNewMessageState || this.mList.getLastVisiblePosition() >= this.mList.getCount() - 1 || Message$MessageDirection.SEND == uIMessage.getMessageDirection() || !SystemUtils.isAppRunningOnTop(RongContext.getInstance(), RongContext.getInstance().getPackageName()) || uIMessage.getConversationType() == ConversationType.CHATROOM || uIMessage.getConversationType() == ConversationType.CUSTOMER_SERVICE || uIMessage.getConversationType() == ConversationType.APP_PUBLIC_SERVICE || uIMessage.getConversationType() == ConversationType.PUBLIC_SERVICE)) {
            this.mNewMessageCount++;
            if (this.mNewMessageCount > 0) {
                this.mNewMessageBtn.setVisibility(0);
                this.mNewMessageTextView.setVisibility(0);
            }
            if (this.mNewMessageCount > 99) {
                this.mNewMessageTextView.setText("99+");
            } else {
                this.mNewMessageTextView.setText(this.mNewMessageCount + "");
            }
        }
        int lastVisiblePosition = this.mList.getLastVisiblePosition();
        int count = this.mList.getCount();
        if (lastVisiblePosition == count - 1) {
            this.mList.setTranscriptMode(2);
        } else if (lastVisiblePosition < this.mList.getCount() - 1) {
            this.mList.setTranscriptMode(1);
        }
        this.mAdapter.notifyDataSetChanged();
        if (lastVisiblePosition == count - 1) {
            this.mNewMessageBtn.setVisibility(8);
            this.mNewMessageTextView.setVisibility(8);
        }
    }

    public void onEventMainThread(Message message) {
        UIMessage obtain = UIMessage.obtain(message);
        boolean readReceipt = RongIMClient.getInstance().getReadReceipt();
        RLog.m19419d(TAG, "onEventMainThread message : " + obtain.getMessageId() + " " + obtain.getSentStatus());
        if (this.mConversation != null && this.mConversation.getTargetId().equals(obtain.getTargetId()) && this.mConversation.getConversationType() == obtain.getConversationType()) {
            int findPosition = this.mAdapter.findPosition((long) obtain.getMessageId());
            if (obtain.getMessageId() > 0) {
                Message$ReceivedStatus receivedStatus = obtain.getReceivedStatus();
                receivedStatus.setRead();
                obtain.setReceivedStatus(receivedStatus);
                RongIMClient.getInstance().setMessageReceivedStatus(message.getMessageId(), receivedStatus, null);
            }
            if (findPosition == -1) {
                if (this.mMessageleft <= 0 && readReceipt && obtain.getMessageDirection().equals(Message$MessageDirection.RECEIVE) && obtain.getConversationType() == ConversationType.PRIVATE && obtain.getUId() != null && SystemUtils.isAppRunningOnTop(RongContext.getInstance(), RongContext.getInstance().getPackageName())) {
                    RongIMClient.getInstance().sendReadReceiptMessage(obtain.getConversationType(), obtain.getTargetId(), obtain.getSentTime());
                }
                this.mConversation.setSentTime(obtain.getSentTime());
                this.mConversation.setSenderUserId(obtain.getSenderUserId());
                refreshListWhileReceiveMessage(obtain);
                return;
            }
            ((UIMessage) this.mAdapter.getItem(findPosition)).setSentStatus(obtain.getSentStatus());
            ((UIMessage) this.mAdapter.getItem(findPosition)).setExtra(obtain.getExtra());
            ((UIMessage) this.mAdapter.getItem(findPosition)).setSentTime(obtain.getSentTime());
            ((UIMessage) this.mAdapter.getItem(findPosition)).setUId(obtain.getUId());
            ((UIMessage) this.mAdapter.getItem(findPosition)).setContent(obtain.getContent());
            getHandler().obtainMessage(4, Integer.valueOf(findPosition)).sendToTarget();
        }
    }

    public void onEventMainThread(GroupUserInfoEvent groupUserInfoEvent) {
        GroupUserInfo userInfo = groupUserInfoEvent.getUserInfo();
        if (userInfo != null && userInfo.getNickname() != null && this.mList != null && isResumed()) {
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int firstVisiblePosition = (this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount()) - 1;
            while (true) {
                int i = firstVisiblePosition + 1;
                if (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    Message message = (Message) this.mAdapter.getItem(i);
                    if (message != null && (TextUtils.isEmpty(message.getSenderUserId()) || userInfo.getUserId().equals(message.getSenderUserId()))) {
                        this.mAdapter.getView(i, this.mList.getChildAt((i - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                    }
                    firstVisiblePosition = i;
                } else {
                    return;
                }
            }
        }
    }

    public void onEventMainThread(OnMessageSendErrorEvent onMessageSendErrorEvent) {
        onEventMainThread(onMessageSendErrorEvent.getMessage());
    }

    public void onEventMainThread(OnReceiveMessageEvent onReceiveMessageEvent) {
        this.mMessageleft = onReceiveMessageEvent.getLeft();
        onEventMainThread(onReceiveMessageEvent.getMessage());
    }

    public void onEventMainThread(MessageContent messageContent) {
        if (this.mList != null && isResumed()) {
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int firstVisiblePosition = (this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount()) - 1;
            while (true) {
                int i = firstVisiblePosition + 1;
                if (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    if (((UIMessage) this.mAdapter.getItem(i)).getContent().equals(messageContent)) {
                        this.mAdapter.getView(i, this.mList.getChildAt((i - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                        return;
                    }
                    firstVisiblePosition = i;
                } else {
                    return;
                }
            }
        }
    }

    public void onEventMainThread(PlayAudioEvent playAudioEvent) {
        if (this.mList != null && isResumed()) {
            UIMessage uIMessage;
            int firstVisiblePosition = this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount();
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int i = firstVisiblePosition;
            while (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                uIMessage = (UIMessage) this.mAdapter.getItem(i);
                if (uIMessage.getContent().equals(playAudioEvent.content)) {
                    uIMessage.continuePalyAudio = false;
                    break;
                }
                i++;
            }
            int i2 = i + 1;
            if (playAudioEvent.continuously) {
                i = i2;
                while (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    uIMessage = (UIMessage) this.mAdapter.getItem(i);
                    if ((uIMessage.getContent() instanceof VoiceMessage) && uIMessage.getMessageDirection().equals(Message$MessageDirection.RECEIVE) && !uIMessage.getReceivedStatus().isListened()) {
                        uIMessage.continuePalyAudio = true;
                        this.mAdapter.getView(i, this.mList.getChildAt(i - firstVisiblePosition), this.mList);
                        return;
                    }
                    i++;
                }
            }
        }
    }

    public void onEventMainThread(OnReceiveMessageProgressEvent onReceiveMessageProgressEvent) {
        if (this.mList != null && isResumed()) {
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int firstVisiblePosition = (this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount()) - 1;
            while (true) {
                int i = firstVisiblePosition + 1;
                if (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    UIMessage uIMessage = (UIMessage) this.mAdapter.getItem(i);
                    if (uIMessage.getMessageId() == onReceiveMessageProgressEvent.getMessage().getMessageId()) {
                        uIMessage.setProgress(onReceiveMessageProgressEvent.getProgress());
                        this.mAdapter.getView(i, this.mList.getChildAt((i - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                        return;
                    }
                    firstVisiblePosition = i;
                } else {
                    return;
                }
            }
        }
    }

    public void onEventMainThread(Event event) {
        if (this.mAdapter != null && event == Event.ACTION) {
            getHandler().sendEmptyMessage(10);
        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        if (this.mList != null) {
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int firstVisiblePosition = (this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount()) - 1;
            while (true) {
                int i = firstVisiblePosition + 1;
                if (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    UIMessage uIMessage = (UIMessage) this.mAdapter.getItem(i);
                    if (uIMessage != null && (TextUtils.isEmpty(uIMessage.getSenderUserId()) || userInfo.getUserId().equals(uIMessage.getSenderUserId()))) {
                        uIMessage.setUserInfo(userInfo);
                        this.mAdapter.getView(i, this.mList.getChildAt((i - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                    }
                    firstVisiblePosition = i;
                } else {
                    return;
                }
            }
        }
    }

    public void onEventMainThread(PublicServiceProfile publicServiceProfile) {
        if (this.mList != null && isResumed() && this.mAdapter != null) {
            int lastVisiblePosition = this.mList.getLastVisiblePosition() - this.mList.getHeaderViewsCount();
            int firstVisiblePosition = (this.mList.getFirstVisiblePosition() - this.mList.getHeaderViewsCount()) - 1;
            while (true) {
                int i = firstVisiblePosition + 1;
                if (i <= lastVisiblePosition && i >= 0 && i < this.mAdapter.getCount()) {
                    Message message = (Message) this.mAdapter.getItem(i);
                    if (message != null && (TextUtils.isEmpty(message.getTargetId()) || publicServiceProfile.getTargetId().equals(message.getTargetId()))) {
                        this.mAdapter.getView(i, this.mList.getChildAt((i - this.mList.getFirstVisiblePosition()) + this.mList.getHeaderViewsCount()), this.mList);
                    }
                    firstVisiblePosition = i;
                } else {
                    return;
                }
            }
        }
    }

    private void getConversation() {
        RongIM.getInstance().getConversation(this.mConversation.getConversationType(), this.mConversation.getTargetId(), new ResultCallback<Conversation>() {

            /* renamed from: io.rong.imkit.fragment.MessageListFragment$13$1 */
            class C50441 implements OnClickListener {

                /* renamed from: io.rong.imkit.fragment.MessageListFragment$13$1$1 */
                class C50431 implements AnimationListener {
                    C50431() {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        MessageListFragment.this.mUnreadBtn.setVisibility(8);
                        if (MessageListFragment.this.mUnreadCount <= 30) {
                            if (MessageListFragment.this.mList.getCount() < 30) {
                                MessageListFragment.this.mList.smoothScrollToPosition(MessageListFragment.this.mList.getCount() - MessageListFragment.this.mUnreadCount);
                            } else {
                                MessageListFragment.this.mList.smoothScrollToPosition(30 - MessageListFragment.this.mUnreadCount);
                            }
                        } else if (MessageListFragment.this.mUnreadCount >= 30) {
                            MessageListFragment.this.getHandler().sendEmptyMessage(12);
                        }
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                }

                C50441() {
                }

                public void onClick(View view) {
                    MessageListFragment.this.isOnClickBtn = true;
                    MessageListFragment.this.mUnreadBtn.setClickable(false);
                    Animation translateAnimation = new TranslateAnimation(0.0f, 500.0f, 0.0f, 0.0f);
                    translateAnimation.setDuration(500);
                    MessageListFragment.this.mUnreadBtn.startAnimation(translateAnimation);
                    translateAnimation.setFillAfter(true);
                    translateAnimation.setAnimationListener(new C50431());
                }
            }

            /* renamed from: io.rong.imkit.fragment.MessageListFragment$13$2 */
            class C50452 extends ResultCallback<List<Message>> {
                C50452() {
                }

                public void onSuccess(List<Message> list) {
                    MessageListFragment.this.mUnreadMentionMessages = list;
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }

            public void onSuccess(Conversation conversation) {
                if (conversation != null) {
                    if (!TextUtils.isEmpty(MessageListFragment.this.mConversation.getConversationTitle())) {
                        conversation.setConversationTitle(MessageListFragment.this.mConversation.getConversationTitle());
                    }
                    MessageListFragment.this.mConversation = conversation;
                    if (!(!MessageListFragment.this.isShowUnreadMessageState || conversation.getConversationType() == ConversationType.APP_PUBLIC_SERVICE || conversation.getConversationType() == ConversationType.PUBLIC_SERVICE || conversation.getConversationType() == ConversationType.CUSTOMER_SERVICE || conversation.getConversationType() == ConversationType.CHATROOM)) {
                        MessageListFragment.this.mUnreadCount = MessageListFragment.this.mConversation.getUnreadMessageCount();
                    }
                    if (MessageListFragment.this.mUnreadCount > 150) {
                        MessageListFragment.this.mUnreadBtn.setText("150+" + MessageListFragment.this.getActivity().getResources().getString(C4974R.string.rc_new_messages));
                    } else {
                        MessageListFragment.this.mUnreadBtn.setText(MessageListFragment.this.mUnreadCount + MessageListFragment.this.getActivity().getResources().getString(C4974R.string.rc_new_messages));
                    }
                    if (RongIMClient.getInstance().getReadReceipt() && conversation.getConversationType() == ConversationType.PRIVATE && (!conversation.getSenderUserId().equals(RongIMClient.getInstance().getCurrentUserId()) || MessageListFragment.this.mUnreadCount > 0)) {
                        RongIMClient.getInstance().sendReadReceiptMessage(conversation.getConversationType(), conversation.getTargetId(), conversation.getSentTime());
                    }
                    MessageListFragment.this.mUnreadBtn.setOnClickListener(new C50441());
                    if (MessageListFragment.this.mConversation.getMentionedCount() > 0) {
                        RongIMClient.getInstance().getUnreadMentionedMessages(MessageListFragment.this.mConversation.getConversationType(), MessageListFragment.this.mConversation.getTargetId(), new C50452());
                    }
                    if (!(MessageListFragment.this.mConversation == null || MessageListFragment.this.mConversation.getConversationType() == ConversationType.CHATROOM)) {
                        RongIM.getInstance().clearMessagesUnreadStatus(MessageListFragment.this.mConversation.getConversationType(), MessageListFragment.this.mConversation.getTargetId(), null);
                    }
                }
                MessageListFragment.this.getHandler().sendEmptyMessage(1);
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                RLog.m19420e(MessageListFragment.TAG, "fail, " + rongIMClient$ErrorCode.toString());
            }
        });
    }

    public void onEventMainThread(ConnectEvent connectEvent) {
        RLog.m19419d(TAG, "onEventMainThread Event.ConnectEvent: isListRetrieved = " + this.isShowWithoutConnected);
        if (this.isShowWithoutConnected) {
            getConversation();
            if (this.mConversation.getConversationType() != ConversationType.CHATROOM) {
                RongIM.getInstance().clearMessagesUnreadStatus(this.mConversation.getConversationType(), this.mConversation.getTargetId(), null);
            }
            this.isShowWithoutConnected = false;
        }
    }

    public void onPause() {
        super.onPause();
        RongContext.getInstance().getEventBus().post(Event.DESTROY);
    }

    public void onResume() {
        super.onResume();
        if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED)) {
            this.isShowWithoutConnected = true;
            RLog.m19420e(TAG, "onResume Not connected yet.");
        }
        if (this.mList.getLastVisiblePosition() == this.mList.getCount() - 1) {
            this.mNewMessageCount = 0;
            this.mNewMessageTextView.setVisibility(8);
            this.mNewMessageBtn.setVisibility(8);
        }
        if (this.mConversation != null && this.mConversation.getSenderUserId() != null && RongIMClient.getInstance().getReadReceipt() && this.mConversation.getConversationType() == ConversationType.PRIVATE && !this.mConversation.getSenderUserId().equals(RongIMClient.getInstance().getCurrentUserId())) {
            RongIMClient.getInstance().sendReadReceiptMessage(this.mConversation.getConversationType(), this.mConversation.getTargetId(), this.mConversation.getSentTime());
        }
    }

    public void onEventMainThread(MessageDeleteEvent messageDeleteEvent) {
        if (messageDeleteEvent.getMessageIds() != null) {
            Object obj = null;
            for (Integer intValue : messageDeleteEvent.getMessageIds()) {
                Object obj2;
                int findPosition = this.mAdapter.findPosition((long) intValue.intValue());
                if (findPosition >= 0) {
                    this.mAdapter.remove(findPosition);
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                this.mAdapter.notifyDataSetChanged();
                getHandler().obtainMessage(11).sendToTarget();
            }
        }
    }

    public void onEventMainThread(PublicServiceFollowableEvent publicServiceFollowableEvent) {
        if (publicServiceFollowableEvent != null && !publicServiceFollowableEvent.isFollow()) {
            getActivity().finish();
        }
    }

    public void onEventMainThread(MessagesClearEvent messagesClearEvent) {
        if (messagesClearEvent.getTargetId().equals(this.mConversation.getTargetId()) && messagesClearEvent.getType().equals(this.mConversation.getConversationType())) {
            this.mAdapter.removeAll();
            getHandler().post(new Runnable() {
                public void run() {
                    MessageListFragment.this.mList.setTranscriptMode(1);
                    MessageListFragment.this.mList.setStackFromBottom(false);
                    MessageListFragment.this.mAdapter.notifyDataSetChanged();
                }
            });
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(MessageRecallEvent messageRecallEvent) {
        if (messageRecallEvent.isRecallSuccess()) {
            MessageContent recallNotificationMessage = messageRecallEvent.getRecallNotificationMessage();
            int findPosition = this.mAdapter.findPosition((long) messageRecallEvent.getMessageId());
            if (findPosition != -1) {
                ((UIMessage) this.mAdapter.getItem(findPosition)).setContent(recallNotificationMessage);
                getHandler().obtainMessage(4, Integer.valueOf(findPosition)).sendToTarget();
                return;
            }
            return;
        }
        Toast.makeText(getActivity(), C4974R.string.rc_recall_failed, 0).show();
    }

    public void onEventMainThread(RemoteMessageRecallEvent remoteMessageRecallEvent) {
        if (remoteMessageRecallEvent.isRecallSuccess()) {
            MessageContent recallNotificationMessage = remoteMessageRecallEvent.getRecallNotificationMessage();
            if (AudioPlayManager.getInstance().getPlayingUri() != null) {
                String uri = AudioPlayManager.getInstance().getPlayingUri().toString();
                try {
                    if (Integer.parseInt(uri.substring(uri.lastIndexOf(47) + 1, uri.lastIndexOf(46))) == remoteMessageRecallEvent.getMessageId()) {
                        AudioPlayManager.getInstance().stopPlay();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int findPosition = this.mAdapter.findPosition((long) remoteMessageRecallEvent.getMessageId());
            if (findPosition != -1) {
                ((UIMessage) this.mAdapter.getItem(findPosition)).setContent(recallNotificationMessage);
                getHandler().obtainMessage(4, Integer.valueOf(findPosition)).sendToTarget();
            }
        }
    }

    public void onDestroy() {
        RongContext.getInstance().getEventBus().unregister(this);
        if (this.mConversation.getConversationType() != ConversationType.CHATROOM) {
            RongIM.getInstance().clearMessagesUnreadStatus(this.mConversation.getConversationType(), this.mConversation.getTargetId(), null);
        }
        super.onDestroy();
    }

    public void setAdapter(MessageListAdapter messageListAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.clear();
        }
        this.mAdapter = messageListAdapter;
        if (this.mList != null && getUri() != null) {
            this.mList.setAdapter(messageListAdapter);
            initFragment(getUri());
        }
    }

    public MessageListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setNeedEvaluateForRobot(boolean z) {
        this.needEvaluateForRobot = z;
    }

    public void setRobotMode(boolean z) {
        this.robotMode = z;
    }
}
