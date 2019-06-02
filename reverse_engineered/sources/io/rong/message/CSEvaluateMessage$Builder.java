package io.rong.message;

public class CSEvaluateMessage$Builder {
    private boolean isRobotResolved;
    private String pid;
    private String sid;
    private int source;
    private String suggest;
    private int type;
    private String uid;

    public CSEvaluateMessage build() {
        CSEvaluateMessage cSEvaluateMessage = new CSEvaluateMessage(null);
        CSEvaluateMessage.access$102(cSEvaluateMessage, this.sid);
        CSEvaluateMessage.access$202(cSEvaluateMessage, this.pid);
        CSEvaluateMessage.access$302(cSEvaluateMessage, this.uid);
        CSEvaluateMessage.access$402(cSEvaluateMessage, this.source);
        CSEvaluateMessage.access$502(cSEvaluateMessage, this.suggest);
        CSEvaluateMessage.access$602(cSEvaluateMessage, this.isRobotResolved);
        CSEvaluateMessage.access$702(cSEvaluateMessage, this.type);
        return cSEvaluateMessage;
    }

    public CSEvaluateMessage$Builder sid(String str) {
        this.sid = str;
        return this;
    }

    public CSEvaluateMessage$Builder uid(String str) {
        this.uid = str;
        return this;
    }

    public CSEvaluateMessage$Builder pid(String str) {
        this.pid = str;
        return this;
    }

    public CSEvaluateMessage$Builder source(int i) {
        this.source = i;
        return this;
    }

    public CSEvaluateMessage$Builder suggest(String str) {
        this.suggest = str;
        return this;
    }

    public CSEvaluateMessage$Builder isRobotResolved(boolean z) {
        this.isRobotResolved = z;
        return this;
    }

    public CSEvaluateMessage$Builder type(int i) {
        this.type = i;
        return this;
    }
}
