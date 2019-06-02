package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;
import java.util.List;

public class CallerData {
    public static final String CALLER_DATA_NA = ("?#?:?" + CoreConstants.LINE_SEPARATOR);
    public static final StackTraceElement[] EMPTY_CALLER_DATA_ARRAY = new StackTraceElement[0];
    public static final int LINE_NA = -1;
    private static final String LOG4J_CATEGORY = "org.apache.log4j.Category";
    public static final String NA = "?";
    private static final String SLF4J_BOUNDARY = "org.slf4j.Logger";

    public static StackTraceElement[] extract(Throwable th, String str, int i, List<String> list) {
        int i2 = 0;
        if (th == null) {
            return null;
        }
        int i3;
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i4 = -1;
        for (i3 = 0; i3 < stackTrace.length; i3++) {
            if (isInFrameworkSpace(stackTrace[i3].getClassName(), str, list)) {
                i4 = i3 + 1;
            } else if (i4 != -1) {
                break;
            }
        }
        if (i4 == -1) {
            return EMPTY_CALLER_DATA_ARRAY;
        }
        i3 = stackTrace.length - i4;
        if (i >= i3) {
            i = i3;
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        while (i2 < i) {
            stackTraceElementArr[i2] = stackTrace[i4 + i2];
            i2++;
        }
        return stackTraceElementArr;
    }

    static boolean isInFrameworkSpace(String str, String str2, List<String> list) {
        return str.equals(str2) || str.equals(LOG4J_CATEGORY) || str.startsWith(SLF4J_BOUNDARY) || isInFrameworkSpaceList(str, list);
    }

    private static boolean isInFrameworkSpaceList(String str, List<String> list) {
        if (list == null) {
            return false;
        }
        for (String startsWith : list) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static StackTraceElement naInstance() {
        return new StackTraceElement(NA, NA, NA, -1);
    }
}
