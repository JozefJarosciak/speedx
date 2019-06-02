package com.avos.avoscloud;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AVOperationQueue {
    private volatile int currentSequence;
    private ConcurrentLinkedQueue<AVOperation> queue = new ConcurrentLinkedQueue();

    public synchronized void increaseSequence() {
        this.currentSequence += 2;
    }

    public AVOperation addSnapshotOperation(List list, SaveCallback saveCallback) {
        AVOperation snapshotOperation = AVOperation.snapshotOperation(list, saveCallback);
        snapshotOperation.setSequence(this.currentSequence);
        this.queue.offer(snapshotOperation);
        return snapshotOperation;
    }

    public AVOperation addPendingOperation(List list, SaveCallback saveCallback) {
        AVOperation pendingOperation = AVOperation.pendingOperation(list, saveCallback);
        pendingOperation.setSequence(this.currentSequence);
        this.queue.offer(pendingOperation);
        return pendingOperation;
    }

    public AVOperation popHead() {
        return (AVOperation) this.queue.poll();
    }

    public boolean noPendingRequest() {
        return this.queue.isEmpty();
    }

    public void clearOperationWithSequence(int i) {
        Iterator it = this.queue.iterator();
        while (it.hasNext()) {
            if (((AVOperation) it.next()).getSequence() == i) {
                it.remove();
            }
        }
    }
}
