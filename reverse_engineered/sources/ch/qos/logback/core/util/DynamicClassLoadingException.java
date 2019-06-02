package ch.qos.logback.core.util;

public class DynamicClassLoadingException extends Exception {
    private static final long serialVersionUID = 4962278449162476114L;

    public DynamicClassLoadingException(String str, Throwable th) {
        super(str, th);
    }
}
