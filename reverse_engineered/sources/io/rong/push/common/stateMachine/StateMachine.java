package io.rong.push.common.stateMachine;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class StateMachine {
    public static final boolean HANDLED = true;
    public static final boolean NOT_HANDLED = false;
    private static final int SM_INIT_CMD = -2;
    private static final int SM_QUIT_CMD = -1;
    private String mName;
    private StateMachine$SmHandler mSmHandler;
    private HandlerThread mSmThread;

    private void initStateMachine(String str, Looper looper) {
        this.mName = str;
        this.mSmHandler = new StateMachine$SmHandler(looper, this, null);
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
        StateMachine$SmHandler.access$800(this.mSmHandler, state, state2);
    }

    protected final void addState(State state) {
        StateMachine$SmHandler.access$800(this.mSmHandler, state, null);
    }

    protected final void setInitialState(State state) {
        StateMachine$SmHandler.access$900(this.mSmHandler, state);
    }

    protected final Message getCurrentMessage() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return null;
        }
        return StateMachine$SmHandler.access$1000(stateMachine$SmHandler);
    }

    protected final IState getCurrentState() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return null;
        }
        return StateMachine$SmHandler.access$1100(stateMachine$SmHandler);
    }

    protected final void transitionTo(IState iState) {
        StateMachine$SmHandler.access$1200(this.mSmHandler, iState);
    }

    protected final void transitionToHaltingState() {
        StateMachine$SmHandler.access$1200(this.mSmHandler, StateMachine$SmHandler.access$1300(this.mSmHandler));
    }

    protected final void deferMessage(Message message) {
        StateMachine$SmHandler.access$1400(this.mSmHandler, message);
    }

    protected void unhandledMessage(Message message) {
        if (StateMachine$SmHandler.access$1500(this.mSmHandler)) {
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
        StateMachine$SmHandler.access$1600(this.mSmHandler).setSize(i);
    }

    public final void setLogOnlyTransitions(boolean z) {
        StateMachine$SmHandler.access$1600(this.mSmHandler).setLogOnlyTransitions(z);
    }

    public final int getLogRecSize() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return 0;
        }
        return StateMachine$SmHandler.access$1600(stateMachine$SmHandler).size();
    }

    public final int getLogRecCount() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return 0;
        }
        return StateMachine$SmHandler.access$1600(stateMachine$SmHandler).count();
    }

    public final StateMachine$LogRec getLogRec(int i) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return null;
        }
        return StateMachine$SmHandler.access$1600(stateMachine$SmHandler).get(i);
    }

    public final Collection<StateMachine$LogRec> copyLogRecs() {
        Collection vector = new Vector();
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            Iterator it = StateMachine$LogRecords.access$1700(StateMachine$SmHandler.access$1600(stateMachine$SmHandler)).iterator();
            while (it.hasNext()) {
                vector.add((StateMachine$LogRec) it.next());
            }
        }
        return vector;
    }

    protected void addLogRec(String str) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            StateMachine$SmHandler.access$1600(stateMachine$SmHandler).add(this, StateMachine$SmHandler.access$1000(stateMachine$SmHandler), str, StateMachine$SmHandler.access$1100(stateMachine$SmHandler), StateMachine$SmHandler.access$1800(stateMachine$SmHandler)[StateMachine$SmHandler.access$1900(stateMachine$SmHandler)].state, StateMachine$SmHandler.access$2000(stateMachine$SmHandler));
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
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(obtainMessage(i));
        }
    }

    public final void sendMessage(int i, Object obj) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(obtainMessage(i, obj));
        }
    }

    public final void sendMessage(int i, int i2) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(obtainMessage(i, i2));
        }
    }

    public final void sendMessage(int i, int i2, int i3) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(obtainMessage(i, i2, i3));
        }
    }

    public final void sendMessage(int i, int i2, int i3, Object obj) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(obtainMessage(i, i2, i3, obj));
        }
    }

    public final void sendMessage(Message message) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessage(message);
        }
    }

    public final void sendMessageDelayed(int i, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(obtainMessage(i), j);
        }
    }

    public final void sendMessageDelayed(int i, Object obj, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(obtainMessage(i, obj), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(obtainMessage(i, i2), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, int i3, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(obtainMessage(i, i2, i3), j);
        }
    }

    public final void sendMessageDelayed(int i, int i2, int i3, Object obj, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(obtainMessage(i, i2, i3, obj), j);
        }
    }

    public final void sendMessageDelayed(Message message, long j) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageDelayed(message, j);
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(obtainMessage(i));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, Object obj) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(obtainMessage(i, obj));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2, int i3) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2, i3));
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int i2, int i3, Object obj) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(obtainMessage(i, i2, i3, obj));
        }
    }

    protected final void sendMessageAtFrontOfQueue(Message message) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.sendMessageAtFrontOfQueue(message);
        }
    }

    protected final void removeMessages(int i) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            stateMachine$SmHandler.removeMessages(i);
        }
    }

    protected final boolean isQuit(Message message) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return message.what == -1;
        } else {
            return StateMachine$SmHandler.access$2100(stateMachine$SmHandler, message);
        }
    }

    protected final void quit() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            StateMachine$SmHandler.access$2200(stateMachine$SmHandler);
        }
    }

    protected final void quitNow() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            StateMachine$SmHandler.access$2300(stateMachine$SmHandler);
        }
    }

    public boolean isDbg() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler == null) {
            return false;
        }
        return StateMachine$SmHandler.access$2400(stateMachine$SmHandler);
    }

    public void setDbg(boolean z) {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            StateMachine$SmHandler.access$2500(stateMachine$SmHandler, z);
        }
    }

    public void start() {
        StateMachine$SmHandler stateMachine$SmHandler = this.mSmHandler;
        if (stateMachine$SmHandler != null) {
            StateMachine$SmHandler.access$2600(stateMachine$SmHandler);
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
