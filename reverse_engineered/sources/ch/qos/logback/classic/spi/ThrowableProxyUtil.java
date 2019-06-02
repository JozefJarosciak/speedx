package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;

public class ThrowableProxyUtil {
    private static final int BUILDER_CAPACITY = 2048;
    public static final int REGULAR_EXCEPTION_INDENT = 1;
    public static final int SUPPRESSED_EXCEPTION_INDENT = 1;

    public static String asString(IThrowableProxy iThrowableProxy) {
        StringBuilder stringBuilder = new StringBuilder(2048);
        recursiveAppend(stringBuilder, null, 1, iThrowableProxy);
        return stringBuilder.toString();
    }

    public static void build(ThrowableProxy throwableProxy, Throwable th, ThrowableProxy throwableProxy2) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i = -1;
        if (throwableProxy2 != null) {
            i = findNumberOfCommonFrames(stackTrace, throwableProxy2.getStackTraceElementProxyArray());
        }
        throwableProxy.commonFrames = i;
        throwableProxy.stackTraceElementProxyArray = steArrayToStepArray(stackTrace);
    }

    static int findNumberOfCommonFrames(StackTraceElement[] stackTraceElementArr, StackTraceElementProxy[] stackTraceElementProxyArr) {
        int i = 0;
        if (!(stackTraceElementProxyArr == null || stackTraceElementArr == null)) {
            int length = stackTraceElementArr.length - 1;
            int length2 = stackTraceElementProxyArr.length - 1;
            while (length >= 0 && length2 >= 0 && stackTraceElementArr[length].equals(stackTraceElementProxyArr[length2].ste)) {
                i++;
                length--;
                length2--;
            }
        }
        return i;
    }

    public static void indent(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append('\t');
        }
    }

    private static void recursiveAppend(StringBuilder stringBuilder, String str, int i, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy != null) {
            subjoinFirstLine(stringBuilder, str, i, iThrowableProxy);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            subjoinSTEPArray(stringBuilder, i, iThrowableProxy);
            IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
            if (suppressed != null) {
                for (IThrowableProxy recursiveAppend : suppressed) {
                    recursiveAppend(stringBuilder, CoreConstants.SUPPRESSED, i + 1, recursiveAppend);
                }
            }
            recursiveAppend(stringBuilder, CoreConstants.CAUSED_BY, i, iThrowableProxy.getCause());
        }
    }

    static StackTraceElementProxy[] steArrayToStepArray(StackTraceElement[] stackTraceElementArr) {
        int i = 0;
        if (stackTraceElementArr == null) {
            return new StackTraceElementProxy[0];
        }
        StackTraceElementProxy[] stackTraceElementProxyArr = new StackTraceElementProxy[stackTraceElementArr.length];
        while (i < stackTraceElementProxyArr.length) {
            stackTraceElementProxyArr[i] = new StackTraceElementProxy(stackTraceElementArr[i]);
            i++;
        }
        return stackTraceElementProxyArr;
    }

    private static void subjoinExceptionMessage(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        stringBuilder.append(iThrowableProxy.getClassName()).append(": ").append(iThrowableProxy.getMessage());
    }

    public static void subjoinFirstLine(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy.getCommonFrames() > 0) {
            stringBuilder.append(CoreConstants.CAUSED_BY);
        }
        subjoinExceptionMessage(stringBuilder, iThrowableProxy);
    }

    private static void subjoinFirstLine(StringBuilder stringBuilder, String str, int i, IThrowableProxy iThrowableProxy) {
        indent(stringBuilder, i - 1);
        if (str != null) {
            stringBuilder.append(str);
        }
        subjoinExceptionMessage(stringBuilder, iThrowableProxy);
    }

    public static void subjoinFirstLineRootCauseFirst(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy.getCause() != null) {
            stringBuilder.append(CoreConstants.WRAPPED_BY);
        }
        subjoinExceptionMessage(stringBuilder, iThrowableProxy);
    }

    public static void subjoinPackagingData(StringBuilder stringBuilder, StackTraceElementProxy stackTraceElementProxy) {
        if (stackTraceElementProxy != null) {
            ClassPackagingData classPackagingData = stackTraceElementProxy.getClassPackagingData();
            if (classPackagingData != null) {
                if (classPackagingData.isExact()) {
                    stringBuilder.append(" [");
                } else {
                    stringBuilder.append(" ~[");
                }
                stringBuilder.append(classPackagingData.getCodeLocation()).append(CoreConstants.COLON_CHAR).append(classPackagingData.getVersion()).append(']');
            }
        }
    }

    public static void subjoinSTEP(StringBuilder stringBuilder, StackTraceElementProxy stackTraceElementProxy) {
        stringBuilder.append(stackTraceElementProxy.toString());
        subjoinPackagingData(stringBuilder, stackTraceElementProxy);
    }

    public static void subjoinSTEPArray(StringBuilder stringBuilder, int i, IThrowableProxy iThrowableProxy) {
        StackTraceElementProxy[] stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        int commonFrames = iThrowableProxy.getCommonFrames();
        for (int i2 = 0; i2 < stackTraceElementProxyArray.length - commonFrames; i2++) {
            StackTraceElementProxy stackTraceElementProxy = stackTraceElementProxyArray[i2];
            indent(stringBuilder, i);
            subjoinSTEP(stringBuilder, stackTraceElementProxy);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        }
        if (commonFrames > 0) {
            indent(stringBuilder, i);
            stringBuilder.append("... ").append(commonFrames).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }

    public static void subjoinSTEPArray(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        subjoinSTEPArray(stringBuilder, 1, iThrowableProxy);
    }
}
