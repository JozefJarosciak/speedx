package io.rong.push.common.stateMachine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;

class StateMachine$SmHandler extends Handler {
    private static final Object mSmHandlerObj = new Object();
    private boolean mDbg;
    private ArrayList<Message> mDeferredMessages;
    private State mDestState;
    private HaltingState mHaltingState;
    private boolean mHasQuit;
    private State mInitialState;
    private boolean mIsConstructionCompleted;
    private StateMachine$LogRecords mLogRecords;
    private Message mMsg;
    private QuittingState mQuittingState;
    private StateMachine mSm;
    private HashMap<State, StateInfo> mStateInfo;
    private StateInfo[] mStateStack;
    private int mStateStackTopIndex;
    private StateInfo[] mTempStateStack;
    private int mTempStateStackCount;

    /* renamed from: io.rong.push.common.stateMachine.StateMachine$SmHandler$HaltingState */
    private class HaltingState extends State {
        private HaltingState() {
        }

        public boolean processMessage(Message message) {
            StateMachine$SmHandler.this.mSm.haltedProcessMessage(message);
            return true;
        }
    }

    /* renamed from: io.rong.push.common.stateMachine.StateMachine$SmHandler$QuittingState */
    private class QuittingState extends State {
        private QuittingState() {
        }

        public boolean processMessage(Message message) {
            return false;
        }
    }

    /* renamed from: io.rong.push.common.stateMachine.StateMachine$SmHandler$StateInfo */
    private class StateInfo {
        boolean active;
        StateInfo parentStateInfo;
        State state;

        private StateInfo() {
        }

        public String toString() {
            return "state=" + this.state.getName() + ",active=" + this.active + ",parent=" + (this.parentStateInfo == null ? "null" : this.parentStateInfo.state.getName());
        }
    }

    public final void handleMessage(Message message) {
        if (!this.mHasQuit) {
            if (this.mDbg) {
                this.mSm.log("handleMessage: E msg.what=" + message.what);
            }
            this.mMsg = message;
            State state = null;
            if (this.mIsConstructionCompleted) {
                state = processMsg(message);
            } else if (!this.mIsConstructionCompleted && this.mMsg.what == -2 && this.mMsg.obj == mSmHandlerObj) {
                this.mIsConstructionCompleted = true;
                invokeEnterMethods(0);
            } else {
                throw new RuntimeException("StateMachine.handleMessage: The start method not called, received msg: " + message);
            }
            performTransitions(state, message);
            if (this.mDbg && this.mSm != null) {
                this.mSm.log("handleMessage: X");
            }
        }
    }

    private void performTransitions(State state, Message message) {
        IState iState = this.mStateStack[this.mStateStackTopIndex].state;
        Object obj = (!this.mSm.recordLogRec(this.mMsg) || message.obj == mSmHandlerObj) ? null : 1;
        if (this.mLogRecords.logOnlyTransitions()) {
            if (this.mDestState != null) {
                this.mLogRecords.add(this.mSm, this.mMsg, this.mSm.getLogRecString(this.mMsg), state, iState, this.mDestState);
            }
        } else if (obj != null) {
            this.mLogRecords.add(this.mSm, this.mMsg, this.mSm.getLogRecString(this.mMsg), state, iState, this.mDestState);
        }
        State state2 = this.mDestState;
        if (state2 != null) {
            while (true) {
                if (this.mDbg) {
                    this.mSm.log("handleMessage: new destination call exit/enter");
                }
                invokeExitMethods(setupTempStateStackWithStatesToEnter(state2));
                invokeEnterMethods(moveTempStateStackToStateStack());
                moveDeferredMessageAtFrontOfQueue();
                if (state2 == this.mDestState) {
                    break;
                }
                state2 = this.mDestState;
            }
            this.mDestState = null;
        }
        if (state2 == null) {
            return;
        }
        if (state2 == this.mQuittingState) {
            this.mSm.onQuitting();
            cleanupAfterQuitting();
        } else if (state2 == this.mHaltingState) {
            this.mSm.onHalting();
        }
    }

    private final void cleanupAfterQuitting() {
        if (StateMachine.access$400(this.mSm) != null) {
            getLooper().quit();
            StateMachine.access$402(this.mSm, null);
        }
        StateMachine.access$502(this.mSm, null);
        this.mSm = null;
        this.mMsg = null;
        this.mLogRecords.cleanup();
        this.mStateStack = null;
        this.mTempStateStack = null;
        this.mStateInfo.clear();
        this.mInitialState = null;
        this.mDestState = null;
        this.mDeferredMessages.clear();
        this.mHasQuit = true;
    }

    private final void completeConstruction() {
        if (this.mDbg) {
            this.mSm.log("completeConstruction: E");
        }
        int i = 0;
        for (StateInfo stateInfo : this.mStateInfo.values()) {
            int i2 = 0;
            StateInfo stateInfo2;
            while (stateInfo2 != null) {
                stateInfo2 = stateInfo2.parentStateInfo;
                i2++;
            }
            if (i >= i2) {
                i2 = i;
            }
            i = i2;
        }
        if (this.mDbg) {
            this.mSm.log("completeConstruction: maxDepth=" + i);
        }
        this.mStateStack = new StateInfo[i];
        this.mTempStateStack = new StateInfo[i];
        setupInitialStateStack();
        sendMessageAtFrontOfQueue(obtainMessage(-2, mSmHandlerObj));
        if (this.mDbg) {
            this.mSm.log("completeConstruction: X");
        }
    }

    private final State processMsg(Message message) {
        StateInfo stateInfo = this.mStateStack[this.mStateStackTopIndex];
        if (this.mDbg) {
            this.mSm.log("processMsg: " + stateInfo.state.getName());
        }
        if (isQuit(message)) {
            transitionTo(this.mQuittingState);
        } else {
            while (!stateInfo.state.processMessage(message)) {
                stateInfo = stateInfo.parentStateInfo;
                if (stateInfo == null) {
                    this.mSm.unhandledMessage(message);
                    break;
                } else if (this.mDbg) {
                    this.mSm.log("processMsg: " + stateInfo.state.getName());
                }
            }
        }
        if (stateInfo != null) {
            return stateInfo.state;
        }
        return null;
    }

    private final void invokeExitMethods(StateInfo stateInfo) {
        while (this.mStateStackTopIndex >= 0 && this.mStateStack[this.mStateStackTopIndex] != stateInfo) {
            State state = this.mStateStack[this.mStateStackTopIndex].state;
            if (this.mDbg) {
                this.mSm.log("invokeExitMethods: " + state.getName());
            }
            state.exit();
            this.mStateStack[this.mStateStackTopIndex].active = false;
            this.mStateStackTopIndex--;
        }
    }

    private final void invokeEnterMethods(int i) {
        while (i <= this.mStateStackTopIndex) {
            if (this.mDbg) {
                this.mSm.log("invokeEnterMethods: " + this.mStateStack[i].state.getName());
            }
            this.mStateStack[i].state.enter();
            this.mStateStack[i].active = true;
            i++;
        }
    }

    private final void moveDeferredMessageAtFrontOfQueue() {
        for (int size = this.mDeferredMessages.size() - 1; size >= 0; size--) {
            Message message = (Message) this.mDeferredMessages.get(size);
            if (this.mDbg) {
                this.mSm.log("moveDeferredMessageAtFrontOfQueue; what=" + message.what);
            }
            sendMessageAtFrontOfQueue(message);
        }
        this.mDeferredMessages.clear();
    }

    private final int moveTempStateStackToStateStack() {
        int i = this.mStateStackTopIndex + 1;
        int i2 = i;
        for (int i3 = this.mTempStateStackCount - 1; i3 >= 0; i3--) {
            if (this.mDbg) {
                this.mSm.log("moveTempStackToStateStack: i=" + i3 + ",j=" + i2);
            }
            this.mStateStack[i2] = this.mTempStateStack[i3];
            i2++;
        }
        this.mStateStackTopIndex = i2 - 1;
        if (this.mDbg) {
            this.mSm.log("moveTempStackToStateStack: X mStateStackTop=" + this.mStateStackTopIndex + ",startingIndex=" + i + ",Top=" + this.mStateStack[this.mStateStackTopIndex].state.getName());
        }
        return i;
    }

    private final StateInfo setupTempStateStackWithStatesToEnter(State state) {
        this.mTempStateStackCount = 0;
        StateInfo stateInfo = (StateInfo) this.mStateInfo.get(state);
        do {
            StateInfo[] stateInfoArr = this.mTempStateStack;
            int i = this.mTempStateStackCount;
            this.mTempStateStackCount = i + 1;
            stateInfoArr[i] = stateInfo;
            stateInfo = stateInfo.parentStateInfo;
            if (stateInfo == null) {
                break;
            }
        } while (!stateInfo.active);
        if (this.mDbg) {
            this.mSm.log("setupTempStateStackWithStatesToEnter: X mTempStateStackCount=" + this.mTempStateStackCount + ",curStateInfo: " + stateInfo);
        }
        return stateInfo;
    }

    private final void setupInitialStateStack() {
        if (this.mDbg) {
            this.mSm.log("setupInitialStateStack: E mInitialState=" + this.mInitialState.getName());
        }
        StateInfo stateInfo = (StateInfo) this.mStateInfo.get(this.mInitialState);
        this.mTempStateStackCount = 0;
        while (stateInfo != null) {
            this.mTempStateStack[this.mTempStateStackCount] = stateInfo;
            stateInfo = stateInfo.parentStateInfo;
            this.mTempStateStackCount++;
        }
        this.mStateStackTopIndex = -1;
        moveTempStateStackToStateStack();
    }

    private final Message getCurrentMessage() {
        return this.mMsg;
    }

    private final IState getCurrentState() {
        return this.mStateStack[this.mStateStackTopIndex].state;
    }

    private final StateInfo addState(State state, State state2) {
        StateInfo stateInfo;
        StateInfo addState;
        if (this.mDbg) {
            this.mSm.log("addStateInternal: E state=" + state.getName() + ",parent=" + (state2 == null ? "" : state2.getName()));
        }
        if (state2 != null) {
            stateInfo = (StateInfo) this.mStateInfo.get(state2);
            addState = stateInfo == null ? addState(state2, null) : stateInfo;
        } else {
            addState = null;
        }
        stateInfo = (StateInfo) this.mStateInfo.get(state);
        if (stateInfo == null) {
            stateInfo = new StateInfo();
            this.mStateInfo.put(state, stateInfo);
        }
        if (stateInfo.parentStateInfo == null || stateInfo.parentStateInfo == addState) {
            stateInfo.state = state;
            stateInfo.parentStateInfo = addState;
            stateInfo.active = false;
            if (this.mDbg) {
                this.mSm.log("addStateInternal: X stateInfo: " + stateInfo);
            }
            return stateInfo;
        }
        throw new RuntimeException("state already added");
    }

    private StateMachine$SmHandler(Looper looper, StateMachine stateMachine) {
        super(looper);
        this.mHasQuit = false;
        this.mDbg = false;
        this.mLogRecords = new StateMachine$LogRecords();
        this.mStateStackTopIndex = -1;
        this.mHaltingState = new HaltingState();
        this.mQuittingState = new QuittingState();
        this.mStateInfo = new HashMap();
        this.mDeferredMessages = new ArrayList();
        this.mSm = stateMachine;
        addState(this.mHaltingState, null);
        addState(this.mQuittingState, null);
    }

    private final void setInitialState(State state) {
        if (this.mDbg) {
            this.mSm.log("setInitialState: initialState=" + state.getName());
        }
        this.mInitialState = state;
    }

    private final void transitionTo(IState iState) {
        this.mDestState = (State) iState;
        if (this.mDbg) {
            this.mSm.log("transitionTo: destState=" + this.mDestState.getName());
        }
    }

    private final void deferMessage(Message message) {
        if (this.mDbg) {
            this.mSm.log("deferMessage: msg=" + message.what);
        }
        Message obtainMessage = obtainMessage();
        obtainMessage.copyFrom(message);
        this.mDeferredMessages.add(obtainMessage);
    }

    private final void quit() {
        if (this.mDbg) {
            this.mSm.log("quit:");
        }
        sendMessage(obtainMessage(-1, mSmHandlerObj));
    }

    private final void quitNow() {
        if (this.mDbg) {
            this.mSm.log("quitNow:");
        }
        sendMessageAtFrontOfQueue(obtainMessage(-1, mSmHandlerObj));
    }

    private final boolean isQuit(Message message) {
        return message.what == -1 && message.obj == mSmHandlerObj;
    }

    private final boolean isDbg() {
        return this.mDbg;
    }

    private final void setDbg(boolean z) {
        this.mDbg = z;
    }
}
