package io.rong.push.common.stateMachine;

import android.os.Message;
import java.util.Vector;

class StateMachine$LogRecords {
    private static final int DEFAULT_SIZE = 20;
    private int mCount;
    private boolean mLogOnlyTransitions;
    private Vector<StateMachine$LogRec> mLogRecVector;
    private int mMaxSize;
    private int mOldestIndex;

    private StateMachine$LogRecords() {
        this.mLogRecVector = new Vector();
        this.mMaxSize = 20;
        this.mOldestIndex = 0;
        this.mCount = 0;
        this.mLogOnlyTransitions = false;
    }

    synchronized void setSize(int i) {
        this.mMaxSize = i;
        this.mCount = 0;
        this.mLogRecVector.clear();
    }

    synchronized void setLogOnlyTransitions(boolean z) {
        this.mLogOnlyTransitions = z;
    }

    synchronized boolean logOnlyTransitions() {
        return this.mLogOnlyTransitions;
    }

    synchronized int size() {
        return this.mLogRecVector.size();
    }

    synchronized int count() {
        return this.mCount;
    }

    synchronized void cleanup() {
        this.mLogRecVector.clear();
    }

    synchronized StateMachine$LogRec get(int i) {
        StateMachine$LogRec stateMachine$LogRec;
        int i2 = this.mOldestIndex + i;
        if (i2 >= this.mMaxSize) {
            i2 -= this.mMaxSize;
        }
        if (i2 >= size()) {
            stateMachine$LogRec = null;
        } else {
            stateMachine$LogRec = (StateMachine$LogRec) this.mLogRecVector.get(i2);
        }
        return stateMachine$LogRec;
    }

    synchronized void add(StateMachine stateMachine, Message message, String str, IState iState, IState iState2, IState iState3) {
        this.mCount++;
        if (this.mLogRecVector.size() < this.mMaxSize) {
            this.mLogRecVector.add(new StateMachine$LogRec(stateMachine, message, str, iState, iState2, iState3));
        } else {
            StateMachine$LogRec stateMachine$LogRec = (StateMachine$LogRec) this.mLogRecVector.get(this.mOldestIndex);
            this.mOldestIndex++;
            if (this.mOldestIndex >= this.mMaxSize) {
                this.mOldestIndex = 0;
            }
            stateMachine$LogRec.update(stateMachine, message, str, iState, iState2, iState3);
        }
    }
}
