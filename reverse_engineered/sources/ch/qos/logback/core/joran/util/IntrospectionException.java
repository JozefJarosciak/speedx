package ch.qos.logback.core.joran.util;

public class IntrospectionException extends RuntimeException {
    private static final long serialVersionUID = -6760181416658938878L;

    public IntrospectionException(Exception exception) {
        super(exception);
    }

    public IntrospectionException(String str) {
        super(str);
    }
}
