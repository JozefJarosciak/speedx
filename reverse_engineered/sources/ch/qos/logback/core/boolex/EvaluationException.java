package ch.qos.logback.core.boolex;

public class EvaluationException extends Exception {
    private static final long serialVersionUID = 1;

    public EvaluationException(String str) {
        super(str);
    }

    public EvaluationException(String str, Throwable th) {
        super(str, th);
    }

    public EvaluationException(Throwable th) {
        super(th);
    }
}
