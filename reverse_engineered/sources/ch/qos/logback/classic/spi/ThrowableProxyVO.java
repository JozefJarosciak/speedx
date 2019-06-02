package ch.qos.logback.classic.spi;

import java.io.Serializable;
import java.util.Arrays;

public class ThrowableProxyVO implements IThrowableProxy, Serializable {
    private static final long serialVersionUID = -773438177285807139L;
    private IThrowableProxy cause;
    private String className;
    private int commonFramesCount;
    private String message;
    private StackTraceElementProxy[] stackTraceElementProxyArray;
    private IThrowableProxy[] suppressed;

    public static ThrowableProxyVO build(IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy == null) {
            return null;
        }
        ThrowableProxyVO throwableProxyVO = new ThrowableProxyVO();
        throwableProxyVO.className = iThrowableProxy.getClassName();
        throwableProxyVO.message = iThrowableProxy.getMessage();
        throwableProxyVO.commonFramesCount = iThrowableProxy.getCommonFrames();
        throwableProxyVO.stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        IThrowableProxy cause = iThrowableProxy.getCause();
        if (cause != null) {
            throwableProxyVO.cause = build(cause);
        }
        IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
        if (suppressed != null) {
            throwableProxyVO.suppressed = new IThrowableProxy[suppressed.length];
            for (int i = 0; i < suppressed.length; i++) {
                throwableProxyVO.suppressed[i] = build(suppressed[i]);
            }
        }
        return throwableProxyVO;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ThrowableProxyVO throwableProxyVO = (ThrowableProxyVO) obj;
        if (this.className == null) {
            if (throwableProxyVO.className != null) {
                return false;
            }
        } else if (!this.className.equals(throwableProxyVO.className)) {
            return false;
        }
        return !Arrays.equals(this.stackTraceElementProxyArray, throwableProxyVO.stackTraceElementProxyArray) ? false : !Arrays.equals(this.suppressed, throwableProxyVO.suppressed) ? false : this.cause == null ? throwableProxyVO.cause == null : this.cause.equals(throwableProxyVO.cause);
    }

    public IThrowableProxy getCause() {
        return this.cause;
    }

    public String getClassName() {
        return this.className;
    }

    public int getCommonFrames() {
        return this.commonFramesCount;
    }

    public String getMessage() {
        return this.message;
    }

    public StackTraceElementProxy[] getStackTraceElementProxyArray() {
        return this.stackTraceElementProxyArray;
    }

    public IThrowableProxy[] getSuppressed() {
        return this.suppressed;
    }

    public int hashCode() {
        return (this.className == null ? 0 : this.className.hashCode()) + 31;
    }
}
