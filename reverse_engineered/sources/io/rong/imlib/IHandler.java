package io.rong.imlib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserData;
import java.util.List;

public interface IHandler extends IInterface {

    public static abstract class Stub extends Binder implements IHandler {
        private static final String DESCRIPTOR = "io.rong.imlib.IHandler";
        static final int TRANSACTION_addMemberToDiscussion = 45;
        static final int TRANSACTION_addRealTimeLocationListener = 78;
        static final int TRANSACTION_addToBlacklist = 65;
        static final int TRANSACTION_cleanConversationDraft = 32;
        static final int TRANSACTION_clearConversations = 37;
        static final int TRANSACTION_clearMessages = 20;
        static final int TRANSACTION_clearMessagesUnreadStatus = 21;
        static final int TRANSACTION_clearTextMessageDraft = 69;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_createDiscussion = 44;
        static final int TRANSACTION_deleteConversationMessage = 19;
        static final int TRANSACTION_deleteMessage = 18;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_downloadMedia = 62;
        static final int TRANSACTION_getBlacklist = 70;
        static final int TRANSACTION_getBlacklistStatus = 71;
        static final int TRANSACTION_getCMPServer = 88;
        static final int TRANSACTION_getChatRoomInfo = 51;
        static final int TRANSACTION_getConversation = 28;
        static final int TRANSACTION_getConversationDraft = 31;
        static final int TRANSACTION_getConversationList = 26;
        static final int TRANSACTION_getConversationListByType = 27;
        static final int TRANSACTION_getConversationNotificationStatus = 33;
        static final int TRANSACTION_getConversationUnreadCount = 36;
        static final int TRANSACTION_getCurrentUserId = 85;
        static final int TRANSACTION_getDeltaTime = 63;
        static final int TRANSACTION_getDiscussion = 42;
        static final int TRANSACTION_getMessage = 9;
        static final int TRANSACTION_getMessageByUid = 25;
        static final int TRANSACTION_getNaviCachedTime = 87;
        static final int TRANSACTION_getNewestMessages = 14;
        static final int TRANSACTION_getNotificationQuietHours = 40;
        static final int TRANSACTION_getOlderMessages = 15;
        static final int TRANSACTION_getOlderMessagesByObjectName = 17;
        static final int TRANSACTION_getPCAuthConfig = 89;
        static final int TRANSACTION_getPublicServiceList = 59;
        static final int TRANSACTION_getPublicServiceProfile = 58;
        static final int TRANSACTION_getRealTimeLocationCurrentState = 79;
        static final int TRANSACTION_getRealTimeLocationParticipants = 77;
        static final int TRANSACTION_getRemoteHistoryMessages = 16;
        static final int TRANSACTION_getSendTimeByMessageId = 82;
        static final int TRANSACTION_getTextMessageDraft = 67;
        static final int TRANSACTION_getTotalUnreadCount = 4;
        static final int TRANSACTION_getUnreadCount = 5;
        static final int TRANSACTION_getUnreadCountById = 6;
        static final int TRANSACTION_getUnreadMentionedMessages = 91;
        static final int TRANSACTION_getVoIPCallInfo = 84;
        static final int TRANSACTION_getVoIPKey = 83;
        static final int TRANSACTION_insertMessage = 10;
        static final int TRANSACTION_joinChatRoom = 53;
        static final int TRANSACTION_joinExistChatRoom = 54;
        static final int TRANSACTION_joinGroup = 49;
        static final int TRANSACTION_joinRealTimeLocation = 75;
        static final int TRANSACTION_quitChatRoom = 55;
        static final int TRANSACTION_quitDiscussion = 47;
        static final int TRANSACTION_quitGroup = 50;
        static final int TRANSACTION_quitRealTimeLocation = 76;
        static final int TRANSACTION_reJoinChatRoom = 52;
        static final int TRANSACTION_registerMessageType = 3;
        static final int TRANSACTION_removeConversation = 29;
        static final int TRANSACTION_removeDiscussionMember = 46;
        static final int TRANSACTION_removeFromBlacklist = 66;
        static final int TRANSACTION_removeNotificationQuietHours = 39;
        static final int TRANSACTION_saveConversationDraft = 30;
        static final int TRANSACTION_saveTextMessageDraft = 68;
        static final int TRANSACTION_searchPublicService = 56;
        static final int TRANSACTION_sendLocationMessage = 12;
        static final int TRANSACTION_sendMessage = 11;
        static final int TRANSACTION_sendStatusMessage = 13;
        static final int TRANSACTION_setConnectionStatusListener = 8;
        static final int TRANSACTION_setConversationNotificationStatus = 34;
        static final int TRANSACTION_setConversationTopStatus = 35;
        static final int TRANSACTION_setDiscussionInviteStatus = 64;
        static final int TRANSACTION_setDiscussionName = 43;
        static final int TRANSACTION_setMessageContent = 90;
        static final int TRANSACTION_setMessageExtra = 22;
        static final int TRANSACTION_setMessageReceivedStatus = 23;
        static final int TRANSACTION_setMessageSentStatus = 24;
        static final int TRANSACTION_setNotificationQuietHours = 38;
        static final int TRANSACTION_setOnReceiveMessageListener = 7;
        static final int TRANSACTION_setServerInfo = 86;
        static final int TRANSACTION_setUserData = 72;
        static final int TRANSACTION_setupRealTimeLocation = 73;
        static final int TRANSACTION_startRealTimeLocation = 74;
        static final int TRANSACTION_subscribePublicService = 57;
        static final int TRANSACTION_syncGroup = 48;
        static final int TRANSACTION_updateConversationInfo = 41;
        static final int TRANSACTION_updateMessageReceiptStatus = 81;
        static final int TRANSACTION_updateRealTimeLocationStatus = 80;
        static final int TRANSACTION_uploadMedia = 61;
        static final int TRANSACTION_validateAuth = 60;

        private static class Proxy implements IHandler {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void connect(String str, IStringCallback iStringCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iStringCallback != null ? iStringCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void disconnect(boolean z, IOperationCallback iOperationCallback) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerMessageType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTotalUnreadCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getUnreadCount(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getUnreadCountById(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(onReceiveMessageListener != null ? onReceiveMessageListener.asBinder() : null);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setConnectionStatusListener(IConnectionStatusListener iConnectionStatusListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iConnectionStatusListener != null ? iConnectionStatusListener.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Message getMessage(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Message message;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(obtain2);
                    } else {
                        message = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return message;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Message insertMessage(Message message) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Message message2;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        message2 = (Message) Message.CREATOR.createFromParcel(obtain2);
                    } else {
                        message2 = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return message2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iSendMessageCallback != null ? iSendMessageCallback.asBinder() : null);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendLocationMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iSendMessageCallback != null ? iSendMessageCallback.asBinder() : null);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Message sendStatusMessage(Message message, ILongCallback iLongCallback) throws RemoteException {
                Message message2 = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iLongCallback != null ? iLongCallback.asBinder() : null);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        message2 = (Message) Message.CREATOR.createFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return message2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Message> getNewestMessages(Conversation conversation, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Message> createTypedArrayList = obtain2.createTypedArrayList(Message.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Message> getOlderMessages(Conversation conversation, long j, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Message> createTypedArrayList = obtain2.createTypedArrayList(Message.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRemoteHistoryMessages(Conversation conversation, long j, int i, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Message> getOlderMessagesByObjectName(Conversation conversation, String str, long j, int i, boolean z) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    if (!z) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Message> createTypedArrayList = obtain2.createTypedArrayList(Message.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean deleteMessage(int[] iArr) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean deleteConversationMessage(int i, String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean clearMessages(Conversation conversation) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean clearMessagesUnreadStatus(Conversation conversation) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMessageExtra(int i, String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMessageReceivedStatus(int i, int i2) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMessageSentStatus(int i, int i2) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Message getMessageByUid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Message message;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(obtain2);
                    } else {
                        message = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return message;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Conversation> getConversationList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Conversation> createTypedArrayList = obtain2.createTypedArrayList(Conversation.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Conversation> getConversationListByType(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Conversation> createTypedArrayList = obtain2.createTypedArrayList(Conversation.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Conversation getConversation(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Conversation conversation;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(obtain2);
                    } else {
                        conversation = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return conversation;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean removeConversation(int i, String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean saveConversationDraft(Conversation conversation, String str) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getConversationDraft(Conversation conversation) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean cleanConversationDraft(Conversation conversation) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getConversationNotificationStatus(int i, String str, ILongCallback iLongCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iLongCallback != null ? iLongCallback.asBinder() : null);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setConversationNotificationStatus(int i, String str, int i2, ILongCallback iLongCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iLongCallback != null ? iLongCallback.asBinder() : null);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setConversationTopStatus(int i, String str, boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getConversationUnreadCount(Conversation conversation) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean clearConversations(int[] iArr) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setNotificationQuietHours(String str, int i, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeNotificationQuietHours(IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getNotificationQuietHours(IGetNotificationQuietHoursCallback iGetNotificationQuietHoursCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGetNotificationQuietHoursCallback != null ? iGetNotificationQuietHoursCallback.asBinder() : null);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean updateConversationInfo(int i, String str, String str2, String str3) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getDiscussion(String str, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDiscussionName(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void createDiscussion(String str, List<String> list, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addMemberToDiscussion(String str, List<String> list, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeDiscussionMember(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void quitDiscussion(String str, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void syncGroup(List<Group> list, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void joinGroup(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void quitGroup(String str, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getChatRoomInfo(String str, int i, int i2, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reJoinChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void joinChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void joinExistChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void quitChatRoom(String str, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void searchPublicService(String str, int i, int i2, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void subscribePublicService(String str, int i, boolean z, IOperationCallback iOperationCallback) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getPublicServiceProfile(String str, int i, IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getPublicServiceList(IResultCallback iResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iResultCallback != null ? iResultCallback.asBinder() : null);
                    this.mRemote.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean validateAuth(String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void uploadMedia(Conversation conversation, String str, int i, IUploadCallback iUploadCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iUploadCallback != null ? iUploadCallback.asBinder() : null);
                    this.mRemote.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void downloadMedia(Conversation conversation, int i, String str, IDownloadMediaCallback iDownloadMediaCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iDownloadMediaCallback != null ? iDownloadMediaCallback.asBinder() : null);
                    this.mRemote.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getDeltaTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDiscussionInviteStatus(String str, int i, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addToBlacklist(String str, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeFromBlacklist(String str, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTextMessageDraft(Conversation conversation) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean saveTextMessageDraft(Conversation conversation, String str) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.mRemote.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean clearTextMessageDraft(Conversation conversation) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (conversation != null) {
                        obtain.writeInt(1);
                        conversation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(69, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getBlacklist(IStringCallback iStringCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iStringCallback != null ? iStringCallback.asBinder() : null);
                    this.mRemote.transact(70, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getBlacklistStatus(String str, IIntegerCallback iIntegerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iIntegerCallback != null ? iIntegerCallback.asBinder() : null);
                    this.mRemote.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setUserData(UserData userData, IOperationCallback iOperationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userData != null) {
                        obtain.writeInt(1);
                        userData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iOperationCallback != null ? iOperationCallback.asBinder() : null);
                    this.mRemote.transact(72, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int setupRealTimeLocation(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(73, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int startRealTimeLocation(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int joinRealTimeLocation(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(75, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void quitRealTimeLocation(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(76, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getRealTimeLocationParticipants(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(77, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addRealTimeLocationListener(int i, String str, IRealTimeLocationListener iRealTimeLocationListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRealTimeLocationListener != null ? iRealTimeLocationListener.asBinder() : null);
                    this.mRemote.transact(78, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRealTimeLocationCurrentState(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(79, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateRealTimeLocationStatus(int i, String str, double d, double d2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    this.mRemote.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean updateMessageReceiptStatus(String str, int i, long j) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getSendTimeByMessageId(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getVoIPKey(int i, String str, String str2, IStringCallback iStringCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iStringCallback != null ? iStringCallback.asBinder() : null);
                    this.mRemote.transact(83, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getVoIPCallInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(84, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCurrentUserId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(85, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setServerInfo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(86, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getNaviCachedTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(87, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCMPServer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(88, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getPCAuthConfig(IStringCallback iStringCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iStringCallback != null ? iStringCallback.asBinder() : null);
                    this.mRemote.transact(89, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMessageContent(int i, byte[] bArr, String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.mRemote.transact(90, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Message> getUnreadMentionedMessages(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(91, obtain, obtain2, 0);
                    obtain2.readException();
                    List<Message> createTypedArrayList = obtain2.createTypedArrayList(Message.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IHandler)) {
                return new Proxy(iBinder);
            }
            return (IHandler) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            UserData userData = null;
            int i3 = 0;
            boolean z;
            int totalUnreadCount;
            Message message;
            Conversation conversation;
            List newestMessages;
            String readString;
            String conversationDraft;
            int readInt;
            long deltaTime;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    connect(parcel.readString(), io.rong.imlib.IStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    disconnect(z, io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMessageType(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = getTotalUnreadCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = getUnreadCount(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = getUnreadCountById(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOnReceiveMessageListener(io.rong.imlib.OnReceiveMessageListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setConnectionStatusListener(io.rong.imlib.IConnectionStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    message = getMessage(parcel.readInt());
                    parcel2.writeNoException();
                    if (message != null) {
                        parcel2.writeInt(1);
                        message.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(parcel);
                    }
                    message = insertMessage(message);
                    parcel2.writeNoException();
                    if (message != null) {
                        parcel2.writeInt(1);
                        message.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(parcel);
                    }
                    sendMessage(message, parcel.readString(), parcel.readString(), io.rong.imlib.ISendMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(parcel);
                    }
                    sendLocationMessage(message, parcel.readString(), parcel.readString(), io.rong.imlib.ISendMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        message = (Message) Message.CREATOR.createFromParcel(parcel);
                    }
                    message = sendStatusMessage(message, io.rong.imlib.ILongCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (message != null) {
                        parcel2.writeInt(1);
                        message.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    newestMessages = getNewestMessages(conversation, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    newestMessages = getOlderMessages(conversation, parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 16:
                    Conversation conversation2;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation2 = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    } else {
                        conversation2 = null;
                    }
                    getRemoteHistoryMessages(conversation2, parcel.readLong(), parcel.readInt(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    Conversation conversation3;
                    boolean z2;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation3 = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    } else {
                        conversation3 = null;
                    }
                    readString = parcel.readString();
                    long readLong = parcel.readLong();
                    int readInt2 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    newestMessages = getOlderMessagesByObjectName(conversation3, readString, readLong, readInt2, z2);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = deleteMessage(parcel.createIntArray());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = deleteConversationMessage(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = clearMessages(conversation);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = clearMessagesUnreadStatus(conversation);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = setMessageExtra(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = setMessageReceivedStatus(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = setMessageSentStatus(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    message = getMessageByUid(parcel.readString());
                    parcel2.writeNoException();
                    if (message != null) {
                        parcel2.writeInt(1);
                        message.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    newestMessages = getConversationList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    newestMessages = getConversationListByType(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    conversation = getConversation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (conversation != null) {
                        parcel2.writeInt(1);
                        conversation.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = removeConversation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = saveConversationDraft(conversation, parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    conversationDraft = getConversationDraft(conversation);
                    parcel2.writeNoException();
                    parcel2.writeString(conversationDraft);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = cleanConversationDraft(conversation);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    getConversationNotificationStatus(parcel.readInt(), parcel.readString(), io.rong.imlib.ILongCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setConversationNotificationStatus(parcel.readInt(), parcel.readString(), parcel.readInt(), io.rong.imlib.ILongCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    readInt = parcel.readInt();
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z = setConversationTopStatus(readInt, readString, z);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    totalUnreadCount = getConversationUnreadCount(conversation);
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = clearConversations(parcel.createIntArray());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNotificationQuietHours(parcel.readString(), parcel.readInt(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeNotificationQuietHours(io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    getNotificationQuietHours(io.rong.imlib.IGetNotificationQuietHoursCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = updateConversationInfo(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDiscussion(parcel.readString(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDiscussionName(parcel.readString(), parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    createDiscussion(parcel.readString(), parcel.createStringArrayList(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMemberToDiscussion(parcel.readString(), parcel.createStringArrayList(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDiscussionMember(parcel.readString(), parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitDiscussion(parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncGroup(parcel.createTypedArrayList(Group.CREATOR), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    joinGroup(parcel.readString(), parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitGroup(parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    getChatRoomInfo(parcel.readString(), parcel.readInt(), parcel.readInt(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    reJoinChatRoom(parcel.readString(), parcel.readInt(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    joinChatRoom(parcel.readString(), parcel.readInt(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    joinExistChatRoom(parcel.readString(), parcel.readInt(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitChatRoom(parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    searchPublicService(parcel.readString(), parcel.readInt(), parcel.readInt(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 57:
                    boolean z3;
                    parcel.enforceInterface(DESCRIPTOR);
                    conversationDraft = parcel.readString();
                    readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        z3 = true;
                    }
                    subscribePublicService(conversationDraft, readInt, z3, io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPublicServiceProfile(parcel.readString(), parcel.readInt(), io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPublicServiceList(io.rong.imlib.IResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = validateAuth(parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    uploadMedia(conversation, parcel.readString(), parcel.readInt(), io.rong.imlib.IUploadCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    downloadMedia(conversation, parcel.readInt(), parcel.readString(), io.rong.imlib.IDownloadMediaCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    deltaTime = getDeltaTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(deltaTime);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDiscussionInviteStatus(parcel.readString(), parcel.readInt(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    addToBlacklist(parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFromBlacklist(parcel.readString(), io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    conversationDraft = getTextMessageDraft(conversation);
                    parcel2.writeNoException();
                    parcel2.writeString(conversationDraft);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = saveTextMessageDraft(conversation, parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        conversation = (Conversation) Conversation.CREATOR.createFromParcel(parcel);
                    }
                    z = clearTextMessageDraft(conversation);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    getBlacklist(io.rong.imlib.IStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    getBlacklistStatus(parcel.readString(), io.rong.imlib.IIntegerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        userData = (UserData) UserData.CREATOR.createFromParcel(parcel);
                    }
                    setUserData(userData, io.rong.imlib.IOperationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = setupRealTimeLocation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = startRealTimeLocation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = joinRealTimeLocation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitRealTimeLocation(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    newestMessages = getRealTimeLocationParticipants(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(newestMessages);
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRealTimeLocationListener(parcel.readInt(), parcel.readString(), io.rong.imlib.IRealTimeLocationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    totalUnreadCount = getRealTimeLocationCurrentState(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(totalUnreadCount);
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateRealTimeLocationStatus(parcel.readInt(), parcel.readString(), parcel.readDouble(), parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = updateMessageReceiptStatus(parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    deltaTime = getSendTimeByMessageId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(deltaTime);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    getVoIPKey(parcel.readInt(), parcel.readString(), parcel.readString(), io.rong.imlib.IStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    conversationDraft = getVoIPCallInfo();
                    parcel2.writeNoException();
                    parcel2.writeString(conversationDraft);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    conversationDraft = getCurrentUserId();
                    parcel2.writeNoException();
                    parcel2.writeString(conversationDraft);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    setServerInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    deltaTime = getNaviCachedTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(deltaTime);
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    conversationDraft = getCMPServer();
                    parcel2.writeNoException();
                    parcel2.writeString(conversationDraft);
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPCAuthConfig(io.rong.imlib.IStringCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    z = setMessageContent(parcel.readInt(), parcel.createByteArray(), parcel.readString());
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    newestMessages = getUnreadMentionedMessages(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(newestMessages);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void addMemberToDiscussion(String str, List<String> list, IOperationCallback iOperationCallback) throws RemoteException;

    void addRealTimeLocationListener(int i, String str, IRealTimeLocationListener iRealTimeLocationListener) throws RemoteException;

    void addToBlacklist(String str, IOperationCallback iOperationCallback) throws RemoteException;

    boolean cleanConversationDraft(Conversation conversation) throws RemoteException;

    boolean clearConversations(int[] iArr) throws RemoteException;

    boolean clearMessages(Conversation conversation) throws RemoteException;

    boolean clearMessagesUnreadStatus(Conversation conversation) throws RemoteException;

    boolean clearTextMessageDraft(Conversation conversation) throws RemoteException;

    void connect(String str, IStringCallback iStringCallback) throws RemoteException;

    void createDiscussion(String str, List<String> list, IResultCallback iResultCallback) throws RemoteException;

    boolean deleteConversationMessage(int i, String str) throws RemoteException;

    boolean deleteMessage(int[] iArr) throws RemoteException;

    void disconnect(boolean z, IOperationCallback iOperationCallback) throws RemoteException;

    void downloadMedia(Conversation conversation, int i, String str, IDownloadMediaCallback iDownloadMediaCallback) throws RemoteException;

    void getBlacklist(IStringCallback iStringCallback) throws RemoteException;

    void getBlacklistStatus(String str, IIntegerCallback iIntegerCallback) throws RemoteException;

    String getCMPServer() throws RemoteException;

    void getChatRoomInfo(String str, int i, int i2, IResultCallback iResultCallback) throws RemoteException;

    Conversation getConversation(int i, String str) throws RemoteException;

    String getConversationDraft(Conversation conversation) throws RemoteException;

    List<Conversation> getConversationList() throws RemoteException;

    List<Conversation> getConversationListByType(int[] iArr) throws RemoteException;

    void getConversationNotificationStatus(int i, String str, ILongCallback iLongCallback) throws RemoteException;

    int getConversationUnreadCount(Conversation conversation) throws RemoteException;

    String getCurrentUserId() throws RemoteException;

    long getDeltaTime() throws RemoteException;

    void getDiscussion(String str, IResultCallback iResultCallback) throws RemoteException;

    Message getMessage(int i) throws RemoteException;

    Message getMessageByUid(String str) throws RemoteException;

    long getNaviCachedTime() throws RemoteException;

    List<Message> getNewestMessages(Conversation conversation, int i) throws RemoteException;

    void getNotificationQuietHours(IGetNotificationQuietHoursCallback iGetNotificationQuietHoursCallback) throws RemoteException;

    List<Message> getOlderMessages(Conversation conversation, long j, int i) throws RemoteException;

    List<Message> getOlderMessagesByObjectName(Conversation conversation, String str, long j, int i, boolean z) throws RemoteException;

    void getPCAuthConfig(IStringCallback iStringCallback) throws RemoteException;

    void getPublicServiceList(IResultCallback iResultCallback) throws RemoteException;

    void getPublicServiceProfile(String str, int i, IResultCallback iResultCallback) throws RemoteException;

    int getRealTimeLocationCurrentState(int i, String str) throws RemoteException;

    List<String> getRealTimeLocationParticipants(int i, String str) throws RemoteException;

    void getRemoteHistoryMessages(Conversation conversation, long j, int i, IResultCallback iResultCallback) throws RemoteException;

    long getSendTimeByMessageId(int i) throws RemoteException;

    String getTextMessageDraft(Conversation conversation) throws RemoteException;

    int getTotalUnreadCount() throws RemoteException;

    int getUnreadCount(int[] iArr) throws RemoteException;

    int getUnreadCountById(int i, String str) throws RemoteException;

    List<Message> getUnreadMentionedMessages(int i, String str) throws RemoteException;

    String getVoIPCallInfo() throws RemoteException;

    void getVoIPKey(int i, String str, String str2, IStringCallback iStringCallback) throws RemoteException;

    Message insertMessage(Message message) throws RemoteException;

    void joinChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException;

    void joinExistChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException;

    void joinGroup(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException;

    int joinRealTimeLocation(int i, String str) throws RemoteException;

    void quitChatRoom(String str, IOperationCallback iOperationCallback) throws RemoteException;

    void quitDiscussion(String str, IOperationCallback iOperationCallback) throws RemoteException;

    void quitGroup(String str, IOperationCallback iOperationCallback) throws RemoteException;

    void quitRealTimeLocation(int i, String str) throws RemoteException;

    void reJoinChatRoom(String str, int i, IOperationCallback iOperationCallback) throws RemoteException;

    void registerMessageType(String str) throws RemoteException;

    boolean removeConversation(int i, String str) throws RemoteException;

    void removeDiscussionMember(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException;

    void removeFromBlacklist(String str, IOperationCallback iOperationCallback) throws RemoteException;

    void removeNotificationQuietHours(IOperationCallback iOperationCallback) throws RemoteException;

    boolean saveConversationDraft(Conversation conversation, String str) throws RemoteException;

    boolean saveTextMessageDraft(Conversation conversation, String str) throws RemoteException;

    void searchPublicService(String str, int i, int i2, IResultCallback iResultCallback) throws RemoteException;

    void sendLocationMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) throws RemoteException;

    void sendMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) throws RemoteException;

    Message sendStatusMessage(Message message, ILongCallback iLongCallback) throws RemoteException;

    void setConnectionStatusListener(IConnectionStatusListener iConnectionStatusListener) throws RemoteException;

    void setConversationNotificationStatus(int i, String str, int i2, ILongCallback iLongCallback) throws RemoteException;

    boolean setConversationTopStatus(int i, String str, boolean z) throws RemoteException;

    void setDiscussionInviteStatus(String str, int i, IOperationCallback iOperationCallback) throws RemoteException;

    void setDiscussionName(String str, String str2, IOperationCallback iOperationCallback) throws RemoteException;

    boolean setMessageContent(int i, byte[] bArr, String str) throws RemoteException;

    boolean setMessageExtra(int i, String str) throws RemoteException;

    boolean setMessageReceivedStatus(int i, int i2) throws RemoteException;

    boolean setMessageSentStatus(int i, int i2) throws RemoteException;

    void setNotificationQuietHours(String str, int i, IOperationCallback iOperationCallback) throws RemoteException;

    void setOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener) throws RemoteException;

    void setServerInfo(String str, String str2) throws RemoteException;

    void setUserData(UserData userData, IOperationCallback iOperationCallback) throws RemoteException;

    int setupRealTimeLocation(int i, String str) throws RemoteException;

    int startRealTimeLocation(int i, String str) throws RemoteException;

    void subscribePublicService(String str, int i, boolean z, IOperationCallback iOperationCallback) throws RemoteException;

    void syncGroup(List<Group> list, IOperationCallback iOperationCallback) throws RemoteException;

    boolean updateConversationInfo(int i, String str, String str2, String str3) throws RemoteException;

    boolean updateMessageReceiptStatus(String str, int i, long j) throws RemoteException;

    void updateRealTimeLocationStatus(int i, String str, double d, double d2) throws RemoteException;

    void uploadMedia(Conversation conversation, String str, int i, IUploadCallback iUploadCallback) throws RemoteException;

    boolean validateAuth(String str) throws RemoteException;
}
