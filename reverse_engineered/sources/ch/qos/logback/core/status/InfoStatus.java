package ch.qos.logback.core.status;

public class InfoStatus extends StatusBase {
    public InfoStatus(String str, Object obj) {
        super(0, str, obj);
    }

    public InfoStatus(String str, Object obj, Throwable th) {
        super(0, str, obj, th);
    }
}
