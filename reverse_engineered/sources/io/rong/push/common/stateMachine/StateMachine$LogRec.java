package io.rong.push.common.stateMachine;

import android.os.Message;
import android.text.TextUtils;
import java.util.Calendar;

public class StateMachine$LogRec {
    private IState mDstState;
    private String mInfo;
    private IState mOrgState;
    private StateMachine mSm;
    private IState mState;
    private long mTime;
    private int mWhat;

    StateMachine$LogRec(StateMachine stateMachine, Message message, String str, IState iState, IState iState2, IState iState3) {
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
