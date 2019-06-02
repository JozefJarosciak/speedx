package io.rong.imlib.stateMachine;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class StateMachine {
    public static final boolean HANDLED = true;
    public static final boolean NOT_HANDLED = false;
    private static final int SM_INIT_CMD = -2;
    private static final int SM_QUIT_CMD = -1;
    private String mName;
    private SmHandler mSmHandler;
    private HandlerThread mSmThread;

    public static class LogRec {
        private IState mDstState;
        private String mInfo;
        private IState mOrgState;
        private StateMachine mSm;
        private IState mState;
        private long mTime;
        private int mWhat;

        LogRec(StateMachine stateMachine, Message message, String str, IState iState, IState iState2, IState iState3) {
            update(stateMachine, message, str, iState, iState2, iState3);
        }

        public void update(StateMachine stateMachine, Message message, String str, IState iState, IState iState2, IState iState3) {
            this.mSm = stateMachine;
            this.mTime = System.currentTimeMillis();
            this.mWhat = message != null ? message.what : 0;
            this.mInfo = str;
            this.mState = iState;
            this.mOrgState = iState2;
            this.mDstState = iState3;
        }

        public long getTime() {
            return this.mTime;
        }

        public long getWhat() {
            return (long) this.mWhat;
        }

        public String getInfo() {
            return this.mInfo;
        }

        public IState getState() {
            return this.mState;
        }

        public IState getDestState() {
            return this.mDstState;
        }

        public IState getOriginalState() {
            return this.mOrgState;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("time=");
            Calendar.getInstance().setTimeInMillis(this.mTime);
            stringBuilder.append(String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[]{r0, r0, r0, r0, r0, r0}));
            stringBuilder.append(" processed=");
            stringBuilder.append(this.mState == null ? "<null>" : this.mState.getName());
            stringBuilder.append(" org=");
            stringBuilder.append(this.mOrgState == null ? "<null>" : this.mOrgState.getName());
            stringBuilder.append(" dest=");
            stringBuilder.append(this.mDstState == null ? "<null>" : this.mDstState.getName());
            stringBuilder.append(" what=");
            Object whatToString = this.mSm != null ? this.mSm.getWhatToString(this.mWhat) : "";
            if (TextUtils.isEmpty(whatToString)) {
                stringBuilder.append(this.mWhat);
                stringBuilder.append("(0x");
                stringBuilder.append(Integer.toHexString(this.mWhat));
                stringBuilder.append(")");
            } else {
                stringBuilder.append(whatToString);
            }
            if (!TextUtils.isEmpty(this.mInfo)) {
                stringBuilder.append(" ");
                stringBuilder.append(this.mInfo);
            }
            return stringBuilder.toString();
        }
    }

    private static class LogRecords {
        private static final int DEFAULT_SIZE = 20;
        private int mCount;
        private boolean mLogOnlyTransitions;
        private Vector<LogRec> mLogRecVector;
        private int mMaxSize;
        private int mOldestIndex;

        private LogRecords() {
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

        synchronized LogRec get(int i) {
            LogRec logRec;
            int i2 = this.mOldestIndex + i;
            if (i2 >= this.mMaxSize) {
                i2 -= this.mMaxSize;
            }
            if (i2 >= size()) {
                logRec = null;
            } else {
                logRec = (LogRec) this.mLogRecVector.get(i2);
            }
            return logRec;
        }

        synchronized void add(StateMachine stateMachine, Message message, String str, IState iState, IState iState2, IState iState3) {
            this.mCount++;
            if (this.mLogRecVector.size() < this.mMaxSize) {
                this.mLogRecVector.add(new LogRec(stateMachine, message, str, iState, iState2, iState3));
            } else {
                LogRec logRec = (LogRec) this.mLogRecVector.get(this.mOldestIndex);
                this.mOldestIndex++;
                if (this.mOldestIndex >= this.mMaxSize) {
                    this.mOldestIndex = 0;
                }
                logRec.update(stateMachine, message, str, iState, iState2, iState3);
            }
        }
    }

    private static class SmHandler extends Handler {
        private static final Object mSmHandlerObj = new Object();
        private boolean mDbg;
        private ArrayList<Message> mDeferredMessages;
        private State mDestState;
        private HaltingState mHaltingState;
        private boolean mHasQuit;
        private State mInitialState;
        private boolean mIsConstructionCompleted;
        private LogRecords mLogRecords;
        private Message mMsg;
        private QuittingState mQuittingState;
        private StateMachine mSm;
        private HashMap<State, StateInfo> mStateInfo;
        private StateInfo[] mStateStack;
        private int mStateStackTopIndex;
        private StateInfo[] mTempStateStack;
        private int mTempStateStackCount;

        private class HaltingState extends State {
            private HaltingState() {
            }

            public boolean processMessage(Message message) {
                SmHandler.this.mSm.haltedProcessMessage(message);
                return true;
            }
        }

        private class QuittingState extends State {
            private QuittingState() {
            }

            public boolean processMessage(Message message) {
                return false;
            }
        }

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
            if (this.mSm.mSmThread != null) {
                getLooper().quit();
                this.mSm.mSmThread = null;
            }
            this.mSm.mSmHandler = null;
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

        private SmHandler(Looper looper, StateMachine stateMachine) {
            super(looper);
            this.mHasQuit = false;
            this.mDbg = false;
            this.mLogRecords = new LogRecords();
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

    private void initStateMachine(String str, Looper looper) {
        this.mName = str;
        this.mSmHandler = new SmHandler(looper, this);
    }

    protected StateMachine(String str) {
        this.mSmThread = new HandlerThread(str);
        this.mSmThread.start();
        initStateMachine(str, this.mSmThread.getLooper());
    }

    protected StateMachine(String str, Looper looper) {
        initStateMachine(str, looper);
    }

    protected StateMachine(String str, Handler handler) {
        initStateMachine(str, handler.getLooper());
    }

    protected final void addState(State state, State state2) {
        this.mSmHandler.addState(state, state2);
    }

    protected final void addState(State state) {
        this.mSmHandler.addState(state, null);
    }

    protected final void setInitialState(State state) {
        this.mSmHandler.setInitialState(state);
    }

    protected final Message getCurrentMessage() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return null;
        }
        return smHandler.getCurrentMessage();
    }

    protected final IState getCurrentState() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return null;
        }
        return smHandler.getCurrentState();
    }

    protected final void transitionTo(IState iState) {
        this.mSmHandler.transitionTo(iState);
    }

    protected final void transitionToHaltingState() {
        this.mSmHandler.transitionTo(this.mSmHandler.mHaltingState);
    }

    protected final void deferMessage(Message message) {
        this.mSmHandler.deferMessage(message);
    }

    protected void unhandledMessage(Message message) {
        if (this.mSmHandler.mDbg) {
            loge(" - unhandledMessage: msg.what=" + message.what);
        }
    }

    protected void haltedProcessMessage(Message message) {
    }

    protected void onHalting() {
    }

    protected void onQuitting() {
    }

    public final String getName() {
        return this.mName;
    }

    public final void setLogRecSize(int i) {
        this.mSmHandler.mLogRecords.setSize(i);
    }

    public final void setLogOnlyTransitions(boolean z) {
        this.mSmHandler.mLogRecords.setLogOnlyTransitions(z);
    }

    public final int getLogRecSize() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return 0;
        }
        return smHandler.mLogRecords.size();
    }

    public final int getLogRecCount() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return 0;
        }
        return smHandler.mLogRecords.count();
    }

    public final LogRec getLogRec(int i) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return null;
        }
        return smHandler.mLogRecords.get(i);
    }

    public final Collection<LogRec> copyLogRecs() {
        Collection vector = new Vector();
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            Iterator it = smHandler.mLogRecords.mLogRecVector.iterator();
            while (it.hasNext()) {
                vector.add((LogRec) it.next());
            }
        }
        return vector;
    }

    protected void addLogRec(String str) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.mLogRecords.add(this, smHandler.getCurrentMessage(), str, smHandler.getCurrentState(), smHandler.mStateStack[smHandler.mStateStackTopIndex].state, smHandler.mDestState);
        }
    }

    protected boolean recordLogRec(Message message) {
        return true;
    }

    protected String getLogRecString(Message message) {
        return "";
    }

    protected String getWhatToString(int i) {
        return null;
    }

    public final Handler getHandler() {
        return this.mSmHandler;
    }

    public final Message obtainMessage() {
        return Message.obtain(this.mSmHandler);
    }

    public final Message obtainMessage(int i) {
        return Message.obtain(this.mSmHandler, i);
    }

    public final Message obtainMessage(int i, Object obj) {
        return Message.obtain(this.mSmHandler, i, obj);
    }

    public final Message obtainMessage(int i, int i2) {
        return Message.obtain(this.mSmHandler, i, i2, 0);
    }

    public final Message obtainMessage(int i, int i2, int i3) {
        return Message.obtain(this.mSmHandler, i, i2, i3);
    }

    public final Message obtainMessage(int i, int i2, int i3, Object obj) {
        return Message.obtain(this.mSmHandler, i, i2, i3, obj);
    }

    public final void sendMessage(int i) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(obtainMessage(i));
        }
    }

    public final void sendMessage(int i, Object obj) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(obtainMessage(i, obj));
        }
    }

    public final void sendMessage(int i, int i2) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(obtainMessage(i, i2));
        }
    }

    public final void sendMessage(int i, int i2, int i3) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(obtainMessage(i, i2, i3));
        }
    }

    public final void sendMessage(int i, int i2, int i3, Object obj) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(obtainMessage(i, i2, i3, obj));
        }
    }

    public final void sendMessage(Message message) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessage(message);
        }
    }

    public final void sendMessageDelayed(int i, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(obtainMessage(i), j);
        }
    }

    public final void sendMessageDelayed(int i, Object obj, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(obtainMessage(i, obj), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(obtainMessage(i, i2), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, int i3, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(obtainMessage(i, i2, i3), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, int i3, Object obj, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(obtainMessage(i, i2, i3, obj), j);
        }
    }

    public final void sendMessageDelayed(Message message, long j) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageDelayed(message, j);
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(obtainMessage(i));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, Object obj) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(obtainMessage(i, obj));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2, int i3) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2, i3));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2, int i3, Object obj) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2, i3, obj));
        }
    }

    protected final void sendMessageAtFrontOfQueue(Message message) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.sendMessageAtFrontOfQueue(message);
        }
    }

    protected final void removeMessages(int i) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.removeMessages(i);
        }
    }

    protected final boolean isQuit(Message message) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return message.what == -1;
        } else {
            return smHandler.isQuit(message);
        }
    }

    protected final void quit() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.quit();
        }
    }

    protected final void quitNow() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.quitNow();
        }
    }

    public boolean isDbg() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler == null) {
            return false;
        }
        return smHandler.isDbg();
    }

    public void setDbg(boolean z) {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.setDbg(z);
        }
    }

    public void start() {
        SmHandler smHandler = this.mSmHandler;
        if (smHandler != null) {
            smHandler.completeConstruction();
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(getName() + ":");
        printWriter.println(" total records=" + getLogRecCount());
        for (int i = 0; i < getLogRecSize(); i++) {
            printWriter.printf(" rec[%d]: %s\n", new Object[]{Integer.valueOf(i), getLogRec(i).toString()});
            printWriter.flush();
        }
        printWriter.println("curState=" + getCurrentState().getName());
    }

    protected void logAndAddLogRec(String str) {
        addLogRec(str);
        log(str);
    }

    protected void log(String str) {
        Log.d(this.mName, str);
    }

    protected void logd(String str) {
        Log.d(this.mName, str);
    }

    protected void logv(String str) {
        Log.v(this.mName, str);
    }

    protected void logi(String str) {
        Log.i(this.mName, str);
    }

    protected void logw(String str) {
        Log.w(this.mName, str);
    }

    protected void loge(String str) {
        Log.e(this.mName, str);
    }

    protected void loge(String str, Throwable th) {
        Log.e(this.mName, str, th);
    }
}
