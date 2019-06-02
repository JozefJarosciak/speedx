package com.avos.avoscloud;

import java.util.ArrayList;
import java.util.List;

public class AVOperation {
    private List batchRequest = new ArrayList();
    private SaveCallback callback = null;
    private boolean last = true;
    private int sequence;
    private AVOperationType type;

    public void setLast(boolean z) {
        this.last = z;
    }

    public boolean getLast() {
        return this.last;
    }

    public List getBatchRequest() {
        return this.batchRequest;
    }

    public boolean isSnapshotRequest() {
        return this.type == AVOperationType.kAVOperationSnapshot;
    }

    public boolean isPendingRequest() {
        return this.type == AVOperationType.kAVOperationPendingOperation;
    }

    public void setCallback(SaveCallback saveCallback) {
        this.callback = saveCallback;
    }

    public SaveCallback getCallback() {
        return this.callback;
    }

    public void setSequence(int i) {
        this.sequence = i;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void invokeCallback(AVException aVException) {
        if (getCallback() != null) {
            getCallback().internalDone(aVException);
        }
    }

    static AVOperation snapshotOperation(List list, SaveCallback saveCallback) {
        return cloneOperation(list, saveCallback, AVOperationType.kAVOperationSnapshot);
    }

    private static AVOperation cloneOperation(List list, SaveCallback saveCallback, AVOperationType aVOperationType) {
        AVOperation aVOperation = new AVOperation();
        aVOperation.batchRequest.addAll(list);
        aVOperation.callback = saveCallback;
        aVOperation.type = aVOperationType;
        return aVOperation;
    }

    static AVOperation pendingOperation(List list, SaveCallback saveCallback) {
        return cloneOperation(list, saveCallback, AVOperationType.kAVOperationPendingOperation);
    }
}
