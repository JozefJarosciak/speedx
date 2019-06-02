package io.rong.imkit.model;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import io.rong.imkit.RongIM;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmojiMessageAdapter {
    private static EmojiMessageAdapter mLogic;

    public static void init(Context context) {
        mLogic = new EmojiMessageAdapter();
    }

    public static EmojiMessageAdapter getInstance() {
        return mLogic;
    }

    public void getHistoryMessages(ConversationType conversationType, String str, int i, int i2, final ResultCallback<List<UIMessage>> resultCallback) {
        RongIM.getInstance().getHistoryMessages(conversationType, str, i, i2, new ResultCallback<List<Message>>() {
            public void onSuccess(List<Message> list) {
                if (resultCallback != null) {
                    resultCallback.onSuccess(EmojiMessageAdapter.this.emojiMessageToUIMessage(list));
                }
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                if (resultCallback != null) {
                    resultCallback.onError(rongIMClient$ErrorCode);
                }
            }
        });
    }

    public void getRemoteHistoryMessages(ConversationType conversationType, String str, long j, int i, final ResultCallback<List<UIMessage>> resultCallback) {
        RongIMClient.getInstance().getRemoteHistoryMessages(conversationType, str, j, i, new ResultCallback<List<Message>>() {
            public void onSuccess(List<Message> list) {
                if (resultCallback != null) {
                    resultCallback.onSuccess(EmojiMessageAdapter.this.emojiMessageToUIMessage(list));
                }
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                if (resultCallback != null) {
                    resultCallback.onError(rongIMClient$ErrorCode);
                }
            }
        });
    }

    public void getLatestMessages(ConversationType conversationType, String str, int i, final ResultCallback<List<UIMessage>> resultCallback) {
        RongIM.getInstance().getLatestMessages(conversationType, str, i, new ResultCallback<List<Message>>() {
            public void onSuccess(List<Message> list) {
                if (resultCallback != null) {
                    if (list != null && list.size() > 0) {
                        Collections.reverse(list);
                    }
                    resultCallback.onSuccess(EmojiMessageAdapter.this.emojiMessageToUIMessage(list));
                }
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                if (resultCallback != null) {
                    resultCallback.onError(rongIMClient$ErrorCode);
                }
            }
        });
    }

    private final List<UIMessage> emojiMessageToUIMessage(List<Message> list) {
        List<UIMessage> arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return arrayList;
        }
        for (Message message : list) {
            UIMessage obtain = UIMessage.obtain(message);
            if (message.getContent() instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message.getContent();
                if (textMessage.getContent() != null) {
                    Spannable spannableStringBuilder = new SpannableStringBuilder(textMessage.getContent());
                    AndroidEmoji.ensure(spannableStringBuilder);
                    obtain.setTextMessageContent(spannableStringBuilder);
                }
            }
            arrayList.add(obtain);
        }
        return arrayList;
    }
}
