package io.rong.imkit.manager;

import android.net.Uri;
import android.support.annotation.Nullable;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$OnSendMessageListener;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SendImageManager {
    private static final String TAG = "SendImageManager";
    private ExecutorService executorService;
    private UploadController uploadController;

    /* renamed from: io.rong.imkit.manager.SendImageManager$1 */
    class C50931 extends ResultCallback<Message> {
        C50931() {
        }

        public void onSuccess(Message message) {
            message.setSentStatus(Message$SentStatus.SENDING);
            RongIMClient.getInstance().setMessageSentStatus(message.getMessageId(), Message$SentStatus.SENDING, null);
            RongContext.getInstance().getEventBus().post(message);
            SendImageManager.this.uploadController.execute(message);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.manager.SendImageManager$2 */
    class C50942 extends ResultCallback<Message> {
        C50942() {
        }

        public void onSuccess(Message message) {
            message.setSentStatus(Message$SentStatus.SENDING);
            RongIMClient.getInstance().setMessageSentStatus(message.getMessageId(), Message$SentStatus.SENDING, null);
            RongContext.getInstance().getEventBus().post(message);
            SendImageManager.this.uploadController.execute(message);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    static class SingletonHolder {
        static SendImageManager sInstance = new SendImageManager();

        SingletonHolder() {
        }
    }

    private class UploadController implements Runnable {
        Message executingMessage;
        final List<Message> pendingMessages = new ArrayList();

        /* renamed from: io.rong.imkit.manager.SendImageManager$UploadController$1 */
        class C50961 extends RongIMClient$SendImageMessageCallback {
            C50961() {
            }

            public void onAttached(Message message) {
            }

            public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                UploadController.this.polling();
            }

            public void onSuccess(Message message) {
                UploadController.this.polling();
            }

            public void onProgress(Message message, int i) {
            }
        }

        public void execute(Message message) {
            synchronized (this.pendingMessages) {
                this.pendingMessages.add(message);
                if (this.executingMessage == null) {
                    this.executingMessage = (Message) this.pendingMessages.remove(0);
                    SendImageManager.this.executorService.submit(this);
                }
            }
        }

        public void reset() {
            RLog.m19424w(SendImageManager.TAG, "Rest Sending Images.");
            synchronized (this.pendingMessages) {
                for (Message message : this.pendingMessages) {
                    message.setSentStatus(Message$SentStatus.FAILED);
                    RongContext.getInstance().getEventBus().post(message);
                }
                this.pendingMessages.clear();
            }
            if (this.executingMessage != null) {
                this.executingMessage.setSentStatus(Message$SentStatus.FAILED);
                RongContext.getInstance().getEventBus().post(this.executingMessage);
                this.executingMessage = null;
            }
        }

        public void cancel(ConversationType conversationType, String str) {
            synchronized (this.pendingMessages) {
                int size = this.pendingMessages.size();
                for (int i = 0; i < size; i++) {
                    Message message = (Message) this.pendingMessages.get(i);
                    if (message.getConversationType().equals(conversationType) && message.getTargetId().equals(str)) {
                        this.pendingMessages.remove(message);
                    }
                }
                if (this.pendingMessages.size() == 0) {
                    this.executingMessage = null;
                }
            }
        }

        public void cancel(ConversationType conversationType, String str, int i) {
            synchronized (this.pendingMessages) {
                int size = this.pendingMessages.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Message message = (Message) this.pendingMessages.get(i2);
                    if (message.getConversationType().equals(conversationType) && message.getTargetId().equals(str) && message.getMessageId() == i) {
                        this.pendingMessages.remove(message);
                        break;
                    }
                }
                if (this.pendingMessages.size() == 0) {
                    this.executingMessage = null;
                }
            }
        }

        private void polling() {
            synchronized (this.pendingMessages) {
                RLog.m19419d(SendImageManager.TAG, "polling " + this.pendingMessages.size());
                if (this.pendingMessages.size() > 0) {
                    this.executingMessage = (Message) this.pendingMessages.remove(0);
                    SendImageManager.this.executorService.submit(this);
                } else {
                    this.pendingMessages.clear();
                    this.executingMessage = null;
                }
            }
        }

        public void run() {
            RongIM.getInstance().sendImageMessage(this.executingMessage, null, null, new C50961());
        }
    }

    public static SendImageManager getInstance() {
        return SingletonHolder.sInstance;
    }

    private SendImageManager() {
        this.executorService = getExecutorService();
        this.uploadController = new UploadController();
    }

    public void sendImages(ConversationType conversationType, String str, List<Uri> list, boolean z) {
        RLog.m19419d(TAG, "sendImages " + list.size());
        for (Uri uri : list) {
            MessageContent obtain = ImageMessage.obtain(uri, uri, z);
            RongIM$OnSendMessageListener onSendMessageListener = RongContext.getInstance().getOnSendMessageListener();
            if (onSendMessageListener != null) {
                Message onSend = onSendMessageListener.onSend(Message.obtain(str, conversationType, obtain));
                if (onSend != null) {
                    RongIMClient.getInstance().insertMessage(conversationType, str, null, onSend.getContent(), new C50931());
                }
            } else {
                RongIMClient.getInstance().insertMessage(conversationType, str, null, obtain, new C50942());
            }
        }
    }

    public void cancelSendingImages(ConversationType conversationType, String str) {
        RLog.m19419d(TAG, "cancelSendingImages");
        if (conversationType != null && str != null && this.uploadController != null) {
            this.uploadController.cancel(conversationType, str);
        }
    }

    public void cancelSendingImage(ConversationType conversationType, String str, int i) {
        RLog.m19419d(TAG, "cancelSendingImages");
        if (conversationType != null && str != null && this.uploadController != null && i > 0) {
            this.uploadController.cancel(conversationType, str, i);
        }
    }

    public void reset() {
        this.uploadController.reset();
    }

    private ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory("Rong SendMediaManager", false));
        }
        return this.executorService;
    }

    private ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(@Nullable Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }
}
