package io.rong.imkit.model;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

public class UIMessage extends Message {
    public boolean continuePalyAudio;
    private boolean evaluated = false;
    private boolean isHistroyMessage = true;
    private int mProgress;
    private UserInfo mUserInfo;
    private SpannableStringBuilder textMessageContent;

    public Message getMessage() {
        Message message = new Message();
        message.setConversationType(getConversationType());
        message.setTargetId(getTargetId());
        message.setMessageId(getMessageId());
        message.setObjectName(getObjectName());
        message.setContent(getContent());
        message.setSentStatus(getSentStatus());
        message.setSenderUserId(getSenderUserId());
        message.setReceivedStatus(getReceivedStatus());
        message.setMessageDirection(getMessageDirection());
        message.setReceivedTime(getReceivedTime());
        message.setSentTime(getSentTime());
        message.setExtra(getExtra());
        message.setUId(getUId());
        return message;
    }

    public static UIMessage obtain(Message message) {
        UIMessage uIMessage = new UIMessage();
        uIMessage.setConversationType(message.getConversationType());
        uIMessage.setTargetId(message.getTargetId());
        uIMessage.setMessageId(message.getMessageId());
        uIMessage.setObjectName(message.getObjectName());
        uIMessage.setContent(message.getContent());
        uIMessage.setSentStatus(message.getSentStatus());
        uIMessage.setSenderUserId(message.getSenderUserId());
        uIMessage.setReceivedStatus(message.getReceivedStatus());
        uIMessage.setMessageDirection(message.getMessageDirection());
        uIMessage.setReceivedTime(message.getReceivedTime());
        uIMessage.setSentTime(message.getSentTime());
        uIMessage.setExtra(message.getExtra());
        uIMessage.setUserInfo(message.getContent().getUserInfo());
        uIMessage.setUId(message.getUId());
        uIMessage.continuePalyAudio = false;
        return uIMessage;
    }

    public SpannableStringBuilder getTextMessageContent() {
        if (this.textMessageContent == null && (getContent() instanceof TextMessage)) {
            TextMessage textMessage = (TextMessage) getContent();
            if (textMessage.getContent() != null) {
                Spannable spannableStringBuilder = new SpannableStringBuilder(textMessage.getContent());
                AndroidEmoji.ensure(spannableStringBuilder);
                setTextMessageContent(spannableStringBuilder);
            }
        }
        return this.textMessageContent;
    }

    public void setTextMessageContent(SpannableStringBuilder spannableStringBuilder) {
        this.textMessageContent = spannableStringBuilder;
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        if (getUserInfo() == null) {
            this.mUserInfo = userInfo;
        }
    }

    public void setProgress(int i) {
        this.mProgress = i;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public void setEvaluated(boolean z) {
        this.evaluated = z;
    }

    public boolean getEvaluated() {
        return this.evaluated;
    }

    public void setIsHistoryMessage(boolean z) {
        this.isHistroyMessage = z;
    }

    public boolean getIsHistoryMessage() {
        return this.isHistroyMessage;
    }
}
