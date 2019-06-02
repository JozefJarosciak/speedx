package io.rong.imlib.TypingMessage;

public class TypingStatus {
    private long sentTime;
    private String typingContentType;
    private String userId;

    public TypingStatus(String str, String str2, long j) {
        setUserId(str);
        setTypingContentType(str2);
        setSentTime(j);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getTypingContentType() {
        return this.typingContentType;
    }

    public void setTypingContentType(String str) {
        this.typingContentType = str;
    }

    public long getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(long j) {
        this.sentTime = j;
    }
}
