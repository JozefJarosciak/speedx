package io.rong.imlib;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.common.WakeLockUtils;
import io.rong.imlib.IHandler.Stub;
import io.rong.imlib.NativeClient.BlacklistStatus;
import io.rong.imlib.NativeClient.GetNotificationQuietHoursCallback;
import io.rong.imlib.NativeClient.ICodeListener;
import io.rong.imlib.NativeClient.IResultCallback;
import io.rong.imlib.NativeClient.IResultProgressCallback;
import io.rong.imlib.NativeClient.ISendMessageCallback;
import io.rong.imlib.NativeClient.OnReceiveMessageListener;
import io.rong.imlib.NativeClient.RealTimeLocationListener;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$ReceivedStatus;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.RemoteModelWrap;
import io.rong.imlib.model.RongListWrap;
import io.rong.imlib.model.UserData;
import io.rong.imlib.navigation.NavigationClient;
import java.util.List;

public class LibHandlerStub extends Stub {
    private static final String TAG = "LibHandlerStub";
    Handler mCallbackHandler;
    HandlerThread mCallbackThread = new HandlerThread("Rong_SDK_Callback");
    NativeClient mClient;
    Context mContext;
    String mCurrentUserId;

    private class OperationCallback implements io.rong.imlib.NativeClient.OperationCallback {
        IOperationCallback callback;

        /* renamed from: io.rong.imlib.LibHandlerStub$OperationCallback$1 */
        class C52631 implements Runnable {
            C52631() {
            }

            public void run() {
                try {
                    OperationCallback.this.callback.onComplete();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public OperationCallback(IOperationCallback iOperationCallback) {
            this.callback = iOperationCallback;
        }

        public void onSuccess() {
            if (this.callback != null) {
                LibHandlerStub.this.mCallbackHandler.post(new C52631());
            }
        }

        public void onError(final int i) {
            if (this.callback != null) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            OperationCallback.this.callback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public LibHandlerStub(Context context, String str, String str2) {
        this.mContext = context;
        this.mCallbackThread.start();
        this.mCallbackHandler = new Handler(this.mCallbackThread.getLooper());
        this.mClient = NativeClient.getInstance();
        this.mClient.init(this.mContext, str, str2);
    }

    public String getCurrentUserId() {
        return this.mClient.getCurrentUserId();
    }

    public void connect(String str, final IStringCallback iStringCallback) throws RemoteException {
        try {
            RLog.m19422i(TAG, "connect");
            this.mClient.connect(str, new IResultCallback<String>() {
                public void onSuccess(final String str) {
                    if (iStringCallback != null) {
                        LibHandlerStub.this.mCurrentUserId = str;
                        WakeLockUtils.startNextHeartbeat(LibHandlerStub.this.mContext);
                        LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                            public void run() {
                                Exception e;
                                try {
                                    iStringCallback.onComplete(str);
                                    return;
                                } catch (RemoteException e2) {
                                    e = e2;
                                } catch (NullPointerException e3) {
                                    e = e3;
                                }
                                e.printStackTrace();
                            }
                        });
                    }
                }

                public void onError(final int i) {
                    WakeLockUtils.cancelHeartbeat(LibHandlerStub.this.mContext);
                    if (iStringCallback != null) {
                        LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                            public void run() {
                                try {
                                    iStringCallback.onFailure(i);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (iStringCallback != null) {
                this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iStringCallback.onFailure(-1);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public void disconnect(boolean z, IOperationCallback iOperationCallback) throws RemoteException {
        if (this.mClient != null) {
            WakeLockUtils.cancelHeartbeat(this.mContext);
            this.mClient.disconnect(z);
            if (iOperationCallback != null) {
                try {
                    iOperationCallback.onComplete();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerMessageType(String str) {
        Class cls = null;
        try {
            cls = Class.forName(str);
        } catch (Throwable e) {
            RLog.m19421e(TAG, "registerMessageType ClassNotFoundException", e);
            e.printStackTrace();
        }
        try {
            NativeClient nativeClient = this.mClient;
            NativeClient.registerMessageType(cls);
        } catch (Throwable e2) {
            RLog.m19421e(TAG, "registerMessageType AnnotationNotFoundException", e2);
        }
    }

    public void setConnectionStatusListener(final IConnectionStatusListener iConnectionStatusListener) {
        NativeClient.setConnectionStatusListener(new ICodeListener() {
            public void onChanged(int i) {
                try {
                    RLog.m19419d(LibHandlerStub.TAG, "setConnectionStatusListener : onChanged status:" + i);
                    if (!(i == 33005 || i == 0)) {
                        WakeLockUtils.cancelHeartbeat(LibHandlerStub.this.mContext);
                    }
                    if (iConnectionStatusListener != null) {
                        iConnectionStatusListener.onChanged(i);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    RLog.m19421e(LibHandlerStub.TAG, "setConnectionStatusListener : onChanged RemoteException", e);
                }
            }
        });
    }

    public int getTotalUnreadCount() throws RemoteException {
        return this.mClient.getTotalUnreadCount();
    }

    public int getUnreadCount(int[] iArr) {
        int i = 0;
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        ConversationType[] conversationTypeArr = new ConversationType[iArr.length];
        while (i < iArr.length) {
            conversationTypeArr[i] = ConversationType.setValue(iArr[i]);
            i++;
        }
        return this.mClient.getUnreadCount(conversationTypeArr);
    }

    public int getUnreadCountById(int i, String str) {
        ConversationType value = ConversationType.setValue(i);
        if (value == null || str == null) {
            return 0;
        }
        return this.mClient.getUnreadCount(value, str);
    }

    public void setOnReceiveMessageListener(final OnReceiveMessageListener onReceiveMessageListener) {
        if (onReceiveMessageListener != null) {
            this.mClient.setOnReceiveMessageListener(new OnReceiveMessageListener() {
                public void onReceived(Message message, int i, boolean z) {
                    RLog.m19419d(LibHandlerStub.TAG, "setOnReceiveMessageListener onReceived : " + message.getObjectName());
                    try {
                        onReceiveMessageListener.onReceived(message, i, z);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public Message insertMessage(Message message) throws RemoteException {
        return this.mClient.insertMessage(message.getConversationType(), message.getTargetId(), message.getSenderUserId(), message.getContent());
    }

    public Message getMessage(int i) {
        return this.mClient.getMessage(i);
    }

    public Message getMessageByUid(String str) {
        return this.mClient.getMessageByUid(str);
    }

    public void sendMessage(Message message, String str, String str2, final ISendMessageCallback iSendMessageCallback) throws RemoteException {
        this.mClient.sendMessage(message, str, str2, new ISendMessageCallback<Message>() {
            public void onAttached(final Message message) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onAttached(message);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onSuccess(final Message message) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onSuccess(message);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final Message message, final int i) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onError(message, i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void sendLocationMessage(Message message, String str, String str2, final ISendMessageCallback iSendMessageCallback) throws RemoteException {
        this.mClient.sendLocationMessage(message, str, str2, new ISendMessageCallback<Message>() {
            public void onAttached(final Message message) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onAttached(message);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onSuccess(final Message message) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onSuccess(message);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final Message message, final int i) {
                if (iSendMessageCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iSendMessageCallback.onError(message, i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public Message sendStatusMessage(Message message, final ILongCallback iLongCallback) throws RemoteException {
        Message sendStatusMessage = this.mClient.sendStatusMessage(message.getConversationType(), message.getTargetId(), message.getContent(), 1, new IResultCallback<Integer>() {
            public void onSuccess(final Integer num) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onComplete((long) num.intValue());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        sendStatusMessage.setSenderUserId(this.mCurrentUserId);
        return sendStatusMessage;
    }

    public List<Message> getNewestMessages(Conversation conversation, int i) throws RemoteException {
        List<Message> latestMessages = this.mClient.getLatestMessages(conversation.getConversationType(), conversation.getTargetId(), i);
        if (latestMessages == null || latestMessages.size() == 0) {
            return null;
        }
        return latestMessages;
    }

    public List<Message> getOlderMessages(Conversation conversation, long j, int i) throws RemoteException {
        List<Message> historyMessages = this.mClient.getHistoryMessages(conversation.getConversationType(), conversation.getTargetId(), (int) j, i);
        if (historyMessages == null || historyMessages.size() == 0) {
            return null;
        }
        return historyMessages;
    }

    public void getRemoteHistoryMessages(Conversation conversation, long j, int i, final IResultCallback iResultCallback) throws RemoteException {
        this.mClient.getRemoteHistoryMessages(conversation.getConversationType(), conversation.getTargetId(), j, i, new IResultCallback<List<Message>>() {
            public void onError(int i) {
                if (iResultCallback != null) {
                    try {
                        iResultCallback.onFailure(i);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess(List<Message> list) {
                if (iResultCallback != null) {
                    if (list != null) {
                        try {
                            if (list.size() != 0) {
                                iResultCallback.onComplete(new RemoteModelWrap(RongListWrap.obtain(list, Message.class)));
                                return;
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    iResultCallback.onComplete(null);
                }
            }
        });
    }

    public List<Message> getOlderMessagesByObjectName(Conversation conversation, String str, long j, int i, boolean z) throws RemoteException {
        List<Message> historyMessages = this.mClient.getHistoryMessages(conversation.getConversationType(), conversation.getTargetId(), str, (int) j, i, z);
        if (historyMessages == null || historyMessages.size() == 0) {
            return null;
        }
        return historyMessages;
    }

    public boolean deleteMessage(int[] iArr) throws RemoteException {
        if (iArr == null || iArr.length == 0) {
            return false;
        }
        return this.mClient.deleteMessages(iArr);
    }

    public boolean deleteConversationMessage(int i, String str) throws RemoteException {
        return this.mClient.deleteMessage(ConversationType.setValue(i), str);
    }

    public boolean clearMessages(Conversation conversation) throws RemoteException {
        return this.mClient.clearMessages(conversation.getConversationType(), conversation.getTargetId());
    }

    public boolean clearMessagesUnreadStatus(Conversation conversation) throws RemoteException {
        return this.mClient.clearMessagesUnreadStatus(conversation.getConversationType(), conversation.getTargetId());
    }

    public boolean setMessageExtra(int i, String str) throws RemoteException {
        return this.mClient.setMessageExtra(i, str);
    }

    public boolean setMessageReceivedStatus(int i, int i2) throws RemoteException {
        return this.mClient.setMessageReceivedStatus(i, new Message$ReceivedStatus(i2));
    }

    public boolean setMessageSentStatus(int i, int i2) throws RemoteException {
        return this.mClient.setMessageSentStatus(i, Message$SentStatus.setValue(i2));
    }

    public List<Conversation> getConversationList() throws RemoteException {
        List<Conversation> conversationList = this.mClient.getConversationList();
        if (conversationList == null || conversationList.size() == 0) {
            return null;
        }
        return conversationList;
    }

    public boolean updateConversationInfo(int i, String str, String str2, String str3) {
        return this.mClient.updateConversationInfo(ConversationType.setValue(i), str, str2, str3);
    }

    public List<Conversation> getConversationListByType(int[] iArr) throws RemoteException {
        List<Conversation> conversationList = this.mClient.getConversationList(iArr);
        if (conversationList == null || conversationList.size() == 0) {
            return null;
        }
        return conversationList;
    }

    public Conversation getConversation(int i, String str) throws RemoteException {
        return this.mClient.getConversation(ConversationType.setValue(i), str);
    }

    public boolean removeConversation(int i, String str) throws RemoteException {
        ConversationType value = ConversationType.setValue(i);
        if (value != null) {
            return this.mClient.removeConversation(value, str);
        }
        RLog.m19422i(TAG, "removeConversation the conversation type is null");
        return false;
    }

    public boolean clearConversations(int[] iArr) throws RemoteException {
        int i = 0;
        if (iArr == null || iArr.length == 0) {
            return false;
        }
        ConversationType[] conversationTypeArr = new ConversationType[iArr.length];
        while (i < iArr.length) {
            conversationTypeArr[i] = ConversationType.setValue(iArr[i]);
            i++;
        }
        return this.mClient.clearConversations(conversationTypeArr);
    }

    public boolean saveConversationDraft(Conversation conversation, String str) throws RemoteException {
        RLog.m19422i(TAG, "saveConversationDraft " + str);
        return this.mClient.saveTextMessageDraft(conversation.getConversationType(), conversation.getTargetId(), str);
    }

    public String getConversationDraft(Conversation conversation) throws RemoteException {
        return this.mClient.getTextMessageDraft(conversation.getConversationType(), conversation.getTargetId());
    }

    public boolean cleanConversationDraft(Conversation conversation) throws RemoteException {
        return this.mClient.clearTextMessageDraft(conversation.getConversationType(), conversation.getTargetId());
    }

    public void getConversationNotificationStatus(int i, String str, final ILongCallback iLongCallback) throws RemoteException {
        this.mClient.getConversationNotificationStatus(ConversationType.setValue(i), str, new IResultCallback<Integer>() {
            public void onSuccess(final Integer num) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onComplete((long) num.intValue());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void setConversationNotificationStatus(int i, String str, int i2, final ILongCallback iLongCallback) throws RemoteException {
        this.mClient.setConversationNotificationStatus(ConversationType.setValue(i), str, ConversationNotificationStatus.setValue(i2), new IResultCallback<Integer>() {
            public void onSuccess(final Integer num) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onComplete((long) num.intValue());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iLongCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iLongCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public boolean setConversationTopStatus(int i, String str, boolean z) {
        ConversationType value = ConversationType.setValue(i);
        if (value != null) {
            return this.mClient.setConversationToTop(value, str, z);
        }
        RLog.m19420e(TAG, "setConversationTopStatus ConversationType is null");
        return false;
    }

    public int getConversationUnreadCount(Conversation conversation) {
        return this.mClient.getUnreadCount(conversation.getConversationType(), conversation.getTargetId());
    }

    public void getDiscussion(String str, final IResultCallback iResultCallback) throws RemoteException {
        this.mClient.getDiscussion(str, new IResultCallback<Discussion>() {
            public void onSuccess(final Discussion discussion) {
                if (iResultCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iResultCallback.onComplete(new RemoteModelWrap(discussion));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iResultCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iResultCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void setDiscussionName(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.setDiscussionName(str, str2, new OperationCallback(iOperationCallback));
    }

    public void createDiscussion(final String str, final List<String> list, final IResultCallback iResultCallback) throws RemoteException {
        this.mClient.createDiscussion(str, list, new IResultCallback<String>() {
            public void onSuccess(final String str) {
                if (iResultCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iResultCallback.onComplete(new RemoteModelWrap(new Discussion(str, str, LibHandlerStub.this.mCurrentUserId, true, list)));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iResultCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iResultCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void searchPublicService(String str, int i, int i2, final IResultCallback iResultCallback) {
        this.mClient.searchPublicService(str, i, i2, new IResultCallback<PublicServiceProfileList>() {
            public void onSuccess(PublicServiceProfileList publicServiceProfileList) {
                final RemoteModelWrap remoteModelWrap = new RemoteModelWrap((Parcelable) publicServiceProfileList);
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iResultCallback.onComplete(remoteModelWrap);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iResultCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void subscribePublicService(String str, int i, boolean z, final IOperationCallback iOperationCallback) {
        this.mClient.subscribePublicService(str, i, z, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$14$1 */
            class C52041 implements Runnable {
                C52041() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52041());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void getPublicServiceProfile(String str, int i, final IResultCallback iResultCallback) {
        this.mClient.getPublicServiceProfile(str, i, new IResultCallback<PublicServiceProfile>() {
            public void onSuccess(final PublicServiceProfile publicServiceProfile) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        RemoteModelWrap remoteModelWrap = null;
                        if (publicServiceProfile != null) {
                            remoteModelWrap = new RemoteModelWrap(publicServiceProfile);
                        }
                        try {
                            iResultCallback.onComplete(remoteModelWrap);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iResultCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void getPublicServiceList(final IResultCallback iResultCallback) {
        this.mClient.getPublicServiceList(new IResultCallback<PublicServiceProfileList>() {
            public void onSuccess(final PublicServiceProfileList publicServiceProfileList) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iResultCallback.onComplete(new RemoteModelWrap(publicServiceProfileList));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iResultCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void addMemberToDiscussion(String str, List<String> list, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.addMemberToDiscussion(str, list, new OperationCallback(iOperationCallback));
    }

    public void removeDiscussionMember(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.removeMemberFromDiscussion(str, str2, new OperationCallback(iOperationCallback));
    }

    public void quitDiscussion(String str, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.quitDiscussion(str, new OperationCallback(iOperationCallback));
    }

    public void syncGroup(List<Group> list, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.syncGroup(list, new OperationCallback(iOperationCallback));
    }

    public void joinGroup(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.joinGroup(str, str2, new OperationCallback(iOperationCallback));
    }

    public void quitGroup(String str, IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.quitGroup(str, new OperationCallback(iOperationCallback));
    }

    public void getChatRoomInfo(String str, int i, int i2, final IResultCallback iResultCallback) {
        this.mClient.queryChatRoomInfo(str, i, i2, new IResultCallback<ChatRoomInfo>() {
            public void onSuccess(ChatRoomInfo chatRoomInfo) {
                try {
                    iResultCallback.onComplete(new RemoteModelWrap((Parcelable) chatRoomInfo));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void onError(int i) {
                try {
                    iResultCallback.onFailure(i);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void joinChatRoom(String str, int i, final IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.joinChatRoom(str, i, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$18$1 */
            class C52101 implements Runnable {
                C52101() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52101());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void reJoinChatRoom(String str, int i, final IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.reJoinChatRoom(str, i, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$19$1 */
            class C52121 implements Runnable {
                C52121() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52121());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void joinExistChatRoom(String str, int i, final IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.joinExistChatRoom(str, i, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$20$1 */
            class C52151 implements Runnable {
                C52151() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52151());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void quitChatRoom(String str, final IOperationCallback iOperationCallback) throws RemoteException {
        this.mClient.quitChatRoom(str, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$21$1 */
            class C52171 implements Runnable {
                C52171() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52171());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void setNotificationQuietHours(String str, int i, final IOperationCallback iOperationCallback) {
        this.mClient.setNotificationQuietHours(str, i, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$22$1 */
            class C52191 implements Runnable {
                C52191() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52191());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void removeNotificationQuietHours(final IOperationCallback iOperationCallback) {
        this.mClient.removeNotificationQuietHours(new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$23$2 */
            class C52222 implements Runnable {
                C52222() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52222());
            }
        });
    }

    public void getNotificationQuietHours(final IGetNotificationQuietHoursCallback iGetNotificationQuietHoursCallback) {
        this.mClient.getNotificationQuietHours(new GetNotificationQuietHoursCallback() {
            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iGetNotificationQuietHoursCallback.onError(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onSuccess(final String str, final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iGetNotificationQuietHoursCallback.onSuccess(str, i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public boolean validateAuth(String str) {
        return false;
    }

    public void uploadMedia(Conversation conversation, String str, int i, final IUploadCallback iUploadCallback) {
        this.mClient.uploadMedia(conversation.getConversationType(), conversation.getTargetId(), str, i, new IResultProgressCallback<String>() {
            public void onProgress(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iUploadCallback.onProgress(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onSuccess(final String str) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iUploadCallback.onComplete(str);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iUploadCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void downloadMedia(Conversation conversation, int i, String str, final IDownloadMediaCallback iDownloadMediaCallback) throws RemoteException {
        this.mClient.downloadMedia(conversation.getConversationType(), conversation.getTargetId(), i, str, new IResultProgressCallback<String>() {
            public void onProgress(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iDownloadMediaCallback.onProgress(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onSuccess(final String str) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            RLog.m19422i(LibHandlerStub.TAG, "onSuccess " + iDownloadMediaCallback.toString());
                            iDownloadMediaCallback.onComplete(str);
                            RLog.m19422i(LibHandlerStub.TAG, "onComplete " + str);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iDownloadMediaCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public long getDeltaTime() {
        return this.mClient.getDeltaTime();
    }

    public void setDiscussionInviteStatus(String str, int i, final IOperationCallback iOperationCallback) {
        this.mClient.setDiscussionInviteStatus(str, i, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$27$1 */
            class C52311 implements Runnable {
                C52311() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52311());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void addToBlacklist(String str, final IOperationCallback iOperationCallback) {
        this.mClient.addToBlacklist(str, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$28$1 */
            class C52331 implements Runnable {
                C52331() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52331());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void removeFromBlacklist(String str, final IOperationCallback iOperationCallback) {
        this.mClient.removeFromBlacklist(str, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$29$1 */
            class C52351 implements Runnable {
                C52351() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52351());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void getBlacklistStatus(String str, final IIntegerCallback iIntegerCallback) {
        this.mClient.getBlacklistStatus(str, new IResultCallback<BlacklistStatus>() {
            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iIntegerCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onSuccess(final BlacklistStatus blacklistStatus) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iIntegerCallback.onComplete(blacklistStatus.getValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void getBlacklist(final IStringCallback iStringCallback) {
        this.mClient.getBlacklist(new IResultCallback<String>() {
            public void onSuccess(final String str) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iStringCallback.onComplete(str);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iStringCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public String getTextMessageDraft(Conversation conversation) {
        return this.mClient.getTextMessageDraft(conversation.getConversationType(), conversation.getTargetId());
    }

    public boolean saveTextMessageDraft(Conversation conversation, String str) {
        return this.mClient.saveTextMessageDraft(conversation.getConversationType(), conversation.getTargetId(), str);
    }

    public boolean clearTextMessageDraft(Conversation conversation) {
        return this.mClient.clearTextMessageDraft(conversation.getConversationType(), conversation.getTargetId());
    }

    public void setUserData(UserData userData, final IOperationCallback iOperationCallback) {
        this.mClient.setUserData(userData, new io.rong.imlib.NativeClient.OperationCallback() {

            /* renamed from: io.rong.imlib.LibHandlerStub$32$1 */
            class C52421 implements Runnable {
                C52421() {
                }

                public void run() {
                    try {
                        iOperationCallback.onComplete();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onSuccess() {
                LibHandlerStub.this.mCallbackHandler.post(new C52421());
            }

            public void onError(final int i) {
                LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                    public void run() {
                        try {
                            iOperationCallback.onFailure(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public int setupRealTimeLocation(int i, String str) {
        return this.mClient.setupRealTimeLocation(this.mContext, i, str);
    }

    public int startRealTimeLocation(int i, String str) {
        return this.mClient.startRealTimeLocation(i, str);
    }

    public int joinRealTimeLocation(int i, String str) {
        return this.mClient.joinRealTimeLocation(i, str);
    }

    public void quitRealTimeLocation(int i, String str) {
        this.mClient.quitRealTimeLocation(i, str);
    }

    public List<String> getRealTimeLocationParticipants(int i, String str) {
        return this.mClient.getRealTimeLocationParticipants(i, str);
    }

    public int getRealTimeLocationCurrentState(int i, String str) {
        return this.mClient.getRealTimeLocationCurrentState(i, str).getValue();
    }

    public void addRealTimeLocationListener(int i, String str, final IRealTimeLocationListener iRealTimeLocationListener) {
        this.mClient.addListener(i, str, new RealTimeLocationListener() {
            public void onStatusChange(RealTimeLocationStatus realTimeLocationStatus) {
                try {
                    iRealTimeLocationListener.onStatusChange(realTimeLocationStatus.getValue());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void onReceiveLocation(double d, double d2, String str) {
                try {
                    iRealTimeLocationListener.onReceiveLocation(d, d2, str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void onParticipantsJoin(String str) {
                try {
                    iRealTimeLocationListener.onParticipantsJoin(str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void onParticipantsQuit(String str) {
                try {
                    iRealTimeLocationListener.onParticipantsQuit(str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void onError(RealTimeLocationErrorCode realTimeLocationErrorCode) {
                try {
                    iRealTimeLocationListener.onError(realTimeLocationErrorCode.getValue());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateRealTimeLocationStatus(int i, String str, double d, double d2) {
        this.mClient.updateRealTimeLocationStatus(i, str, d, d2);
    }

    public boolean updateMessageReceiptStatus(String str, int i, long j) {
        return this.mClient.updateMessageReceiptStatus(str, i, j);
    }

    public long getSendTimeByMessageId(int i) {
        return this.mClient.getSendTimeByMessageId(i);
    }

    public void getVoIPKey(int i, String str, String str2, final IStringCallback iStringCallback) {
        this.mClient.getVoIPKey(i, str, str2, new IResultCallback<String>() {
            public void onSuccess(final String str) {
                if (iStringCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            Exception e;
                            try {
                                iStringCallback.onComplete(str);
                                return;
                            } catch (RemoteException e2) {
                                e = e2;
                            } catch (NullPointerException e3) {
                                e = e3;
                            }
                            e.printStackTrace();
                        }
                    });
                }
            }

            public void onError(final int i) {
                if (iStringCallback != null) {
                    LibHandlerStub.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                            try {
                                iStringCallback.onFailure(i);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public String getVoIPCallInfo() {
        return this.mClient.getVoIPCallInfo();
    }

    public void setServerInfo(String str, String str2) {
        this.mClient.setServerInfo(str, str2);
    }

    public long getNaviCachedTime() {
        return NavigationClient.getInstance().getLastCachedTime();
    }

    public String getCMPServer() {
        return NavigationClient.getInstance().getCMPServer();
    }

    public void getPCAuthConfig(final IStringCallback iStringCallback) {
        this.mClient.getPCAuthConfig(new IResultCallback<String>() {
            public void onSuccess(String str) {
                try {
                    iStringCallback.onComplete(str);
                } catch (RemoteException e) {
                }
            }

            public void onError(int i) {
                try {
                    iStringCallback.onFailure(i);
                } catch (RemoteException e) {
                }
            }
        });
    }

    public boolean setMessageContent(int i, byte[] bArr, String str) {
        return this.mClient.setMessageContent(i, bArr, str);
    }

    public List<Message> getUnreadMentionedMessages(int i, String str) {
        List<Message> unreadMentionedMessages = this.mClient.getUnreadMentionedMessages(ConversationType.setValue(i), str);
        if (unreadMentionedMessages == null || unreadMentionedMessages.size() == 0) {
            return null;
        }
        return unreadMentionedMessages;
    }
}
