package ch.qos.logback.core.helpers;

import ch.qos.logback.core.CoreConstants;
import java.util.LinkedList;
import java.util.List;

public class ThrowableToStringArray {
    public static String[] convert(Throwable th) {
        List linkedList = new LinkedList();
        extract(linkedList, th, null);
        return (String[]) linkedList.toArray(new String[0]);
    }

    private static void extract(List<String> list, Throwable th, StackTraceElement[] stackTraceElementArr) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int findNumberOfCommonFrames = findNumberOfCommonFrames(stackTrace, stackTraceElementArr);
        list.add(formatFirstLine(th, stackTraceElementArr));
        for (int i = 0; i < stackTrace.length - findNumberOfCommonFrames; i++) {
            list.add("\tat " + stackTrace[i].toString());
        }
        if (findNumberOfCommonFrames != 0) {
            list.add("\t... " + findNumberOfCommonFrames + " common frames omitted");
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            extract(list, cause, stackTrace);
        }
    }

    private static int findNumberOfCommonFrames(StackTraceElement[] stackTraceElementArr, StackTraceElement[] stackTraceElementArr2) {
        int i = 0;
        if (stackTraceElementArr2 != null) {
            int length = stackTraceElementArr.length - 1;
            int length2 = stackTraceElementArr2.length - 1;
            while (length >= 0 && length2 >= 0 && stackTraceElementArr[length].equals(stackTraceElementArr2[length2])) {
                i++;
                length--;
                length2--;
            }
        }
        return i;
    }

    private static String formatFirstLine(Throwable th, StackTraceElement[] stackTraceElementArr) {
        String str = "";
        if (stackTraceElementArr != null) {
            str = CoreConstants.CAUSED_BY;
        }
        str = str + th.getClass().getName();
        return th.getMessage() != null ? str + ": " + th.getMessage() : str;
    }
}
