package ch.qos.logback.core.status;

public class ErrorStatus extends StatusBase {
    public ErrorStatus(String str, Object obj) {
        super(2, str, obj);
    }

    public ErrorStatus(String str, Object obj, Throwable th) {
        super(2, str, obj, th);
    }
}
