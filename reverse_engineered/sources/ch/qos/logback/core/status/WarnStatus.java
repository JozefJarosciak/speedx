package ch.qos.logback.core.status;

public class WarnStatus extends StatusBase {
    public WarnStatus(String str, Object obj) {
        super(1, str, obj);
    }

    public WarnStatus(String str, Object obj, Throwable th) {
        super(1, str, obj, th);
    }
}
