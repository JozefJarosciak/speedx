package ch.qos.logback.classic.spi;

public interface IThrowableProxy {
    IThrowableProxy getCause();

    String getClassName();

    int getCommonFrames();

    String getMessage();

    StackTraceElementProxy[] getStackTraceElementProxyArray();

    IThrowableProxy[] getSuppressed();
}
