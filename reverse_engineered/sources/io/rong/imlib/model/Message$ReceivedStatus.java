package io.rong.imlib.model;

public class Message$ReceivedStatus {
    private static final int DOWNLOADED = 4;
    private static final int LISTENED = 2;
    private static final int MULTIPLERECEIVE = 16;
    private static final int READ = 1;
    private static final int RETRIEVED = 8;
    private int flag = 0;
    private boolean isDownload = false;
    private boolean isListened = false;
    private boolean isMultipleReceive = false;
    private boolean isRead = false;
    private boolean isRetrieved = false;

    public Message$ReceivedStatus(int i) {
        boolean z;
        boolean z2 = true;
        this.flag = i;
        this.isRead = (i & 1) == 1;
        if ((i & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        this.isListened = z;
        if ((i & 4) == 4) {
            z = true;
        } else {
            z = false;
        }
        this.isDownload = z;
        if ((i & 8) == 8) {
            z = true;
        } else {
            z = false;
        }
        this.isRetrieved = z;
        if ((i & 16) != 16) {
            z2 = false;
        }
        this.isMultipleReceive = z2;
    }

    public int getFlag() {
        return this.flag;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public void setRead() {
        this.flag |= 1;
        this.isRead = true;
    }

    public boolean isListened() {
        return this.isListened;
    }

    public void setListened() {
        this.flag |= 2;
        this.isListened = true;
    }

    public boolean isDownload() {
        return this.isDownload;
    }

    public void setDownload() {
        this.flag |= 4;
        this.isDownload = true;
    }

    public boolean isRetrieved() {
        return this.isRetrieved;
    }

    public void setRetrieved() {
        this.flag |= 8;
        this.isRetrieved = true;
    }

    public boolean isMultipleReceive() {
        return this.isMultipleReceive;
    }

    public void setMultipleReceive() {
        this.flag |= 16;
        this.isMultipleReceive = true;
    }
}
