package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.helpers.ThrowableToStringArray;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.StatusUtil;
import java.io.PrintStream;
import java.util.List;

public class StatusPrinter {
    static CachingDateFormatter cachingDateFormat = new CachingDateFormatter("HH:mm:ss,SSS");
    private static PrintStream ps = System.out;

    private static void appendThrowable(StringBuilder stringBuilder, Throwable th) {
        for (String str : ThrowableToStringArray.convert(th)) {
            if (!str.startsWith(CoreConstants.CAUSED_BY)) {
                if (Character.isDigit(str.charAt(0))) {
                    stringBuilder.append("\t... ");
                } else {
                    stringBuilder.append("\tat ");
                }
            }
            stringBuilder.append(str).append(CoreConstants.LINE_SEPARATOR);
        }
    }

    public static void buildStr(StringBuilder stringBuilder, String str, Status status) {
        String str2 = status.hasChildren() ? str + "+ " : str + "|-";
        if (cachingDateFormat != null) {
            stringBuilder.append(cachingDateFormat.format(status.getDate().longValue())).append(" ");
        }
        stringBuilder.append(str2).append(status).append(CoreConstants.LINE_SEPARATOR);
        if (status.getThrowable() != null) {
            appendThrowable(stringBuilder, status.getThrowable());
        }
        if (status.hasChildren()) {
            for (Status buildStr : status) {
                buildStr(stringBuilder, str + "  ", buildStr);
            }
        }
    }

    private static void buildStrFromStatusList(StringBuilder stringBuilder, List<Status> list) {
        if (list != null) {
            for (Status buildStr : list) {
                buildStr(stringBuilder, "", buildStr);
            }
        }
    }

    public static void print(Context context) {
        print(context, 0);
    }

    public static void print(Context context, long j) {
        if (context == null) {
            throw new IllegalArgumentException("Context argument cannot be null");
        }
        StatusManager statusManager = context.getStatusManager();
        if (statusManager == null) {
            ps.println("WARN: Context named \"" + context.getName() + "\" has no status manager");
        } else {
            print(statusManager, j);
        }
    }

    public static void print(StatusManager statusManager) {
        print(statusManager, 0);
    }

    public static void print(StatusManager statusManager, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        buildStrFromStatusList(stringBuilder, StatusUtil.filterStatusListByTimeThreshold(statusManager.getCopyOfStatusList(), j));
        ps.println(stringBuilder.toString());
    }

    public static void print(List<Status> list) {
        StringBuilder stringBuilder = new StringBuilder();
        buildStrFromStatusList(stringBuilder, list);
        ps.println(stringBuilder.toString());
    }

    public static void printIfErrorsOccured(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context argument cannot be null");
        }
        StatusManager statusManager = context.getStatusManager();
        if (statusManager == null) {
            ps.println("WARN: Context named \"" + context.getName() + "\" has no status manager");
        } else if (new StatusUtil(context).getHighestLevel(0) == 2) {
            print(statusManager);
        }
    }

    public static void printInCaseOfErrorsOrWarnings(Context context) {
        printInCaseOfErrorsOrWarnings(context, 0);
    }

    public static void printInCaseOfErrorsOrWarnings(Context context, long j) {
        if (context == null) {
            throw new IllegalArgumentException("Context argument cannot be null");
        }
        StatusManager statusManager = context.getStatusManager();
        if (statusManager == null) {
            ps.println("WARN: Context named \"" + context.getName() + "\" has no status manager");
        } else if (new StatusUtil(context).getHighestLevel(j) >= 1) {
            print(statusManager, j);
        }
    }

    public static void setPrintStream(PrintStream printStream) {
        ps = printStream;
    }
}
