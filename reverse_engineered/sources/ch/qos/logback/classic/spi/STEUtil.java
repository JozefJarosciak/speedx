package ch.qos.logback.classic.spi;

public class STEUtil {
    static int findNumberOfCommonFrames(StackTraceElement[] stackTraceElementArr, StackTraceElementProxy[] stackTraceElementProxyArr) {
        int i = 0;
        if (stackTraceElementProxyArr != null) {
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
}
