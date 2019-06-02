package io.rong.message;

import android.content.Context;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

public abstract class MessageHandler<T extends MessageContent> {
    private Context context;
    protected IHandleMessageListener mHandleMessageListener;

    public abstract void decodeMessage(Message message, T t);

    public abstract void encodeMessage(Message message);

    public MessageHandler(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public void setHandleMessageListener(IHandleMessageListener iHandleMessageListener) {
        this.mHandleMessageListener = iHandleMessageListener;
    }
}
