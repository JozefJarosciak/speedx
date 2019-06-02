package ch.qos.logback.core.status;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StatusUtil {
    StatusManager sm;

    public StatusUtil(Context context) {
        this.sm = context.getStatusManager();
    }

    public StatusUtil(StatusManager statusManager) {
        this.sm = statusManager;
    }

    public static boolean contextHasStatusListener(Context context) {
        StatusManager statusManager = context.getStatusManager();
        if (statusManager == null) {
            return false;
        }
        List copyOfStatusListenerList = statusManager.getCopyOfStatusListenerList();
        return (copyOfStatusListenerList == null || copyOfStatusListenerList.size() == 0) ? false : true;
    }

    public static List<Status> filterStatusListByTimeThreshold(List<Status> list, long j) {
        List<Status> arrayList = new ArrayList();
        for (Status status : list) {
            if (status.getDate().longValue() >= j) {
                arrayList.add(status);
            }
        }
        return arrayList;
    }

    public void addError(Object obj, String str, Throwable th) {
        addStatus(new ErrorStatus(str, obj, th));
    }

    public void addInfo(Object obj, String str) {
        addStatus(new InfoStatus(str, obj));
    }

    public void addStatus(Status status) {
        if (this.sm != null) {
            this.sm.add(status);
        }
    }

    public void addWarn(Object obj, String str) {
        addStatus(new WarnStatus(str, obj));
    }

    public boolean containsException(Class<?> cls) {
        for (Status throwable : this.sm.getCopyOfStatusList()) {
            Throwable throwable2 = throwable.getThrowable();
            if (throwable2 != null && throwable2.getClass().getName().equals(cls.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMatch(int i, String str) {
        return containsMatch(0, i, str);
    }

    public boolean containsMatch(long j, int i, String str) {
        List<Status> filterStatusListByTimeThreshold = filterStatusListByTimeThreshold(this.sm.getCopyOfStatusList(), j);
        Pattern compile = Pattern.compile(str);
        for (Status status : filterStatusListByTimeThreshold) {
            if (i == status.getLevel() && compile.matcher(status.getMessage()).lookingAt()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMatch(String str) {
        Pattern compile = Pattern.compile(str);
        for (Status message : this.sm.getCopyOfStatusList()) {
            if (compile.matcher(message.getMessage()).lookingAt()) {
                return true;
            }
        }
        return false;
    }

    public int getHighestLevel(long j) {
        int i = 0;
        for (Status status : filterStatusListByTimeThreshold(this.sm.getCopyOfStatusList(), j)) {
            i = status.getLevel() > i ? status.getLevel() : i;
        }
        return i;
    }

    public boolean hasXMLParsingErrors(long j) {
        return containsMatch(j, 2, CoreConstants.XML_PARSING);
    }

    public boolean isErrorFree(long j) {
        return 2 > getHighestLevel(j);
    }

    public int matchCount(String str) {
        Pattern compile = Pattern.compile(str);
        int i = 0;
        for (Status message : this.sm.getCopyOfStatusList()) {
            i = compile.matcher(message.getMessage()).lookingAt() ? i + 1 : i;
        }
        return i;
    }

    public boolean noXMLParsingErrorsOccurred(long j) {
        return !hasXMLParsingErrors(j);
    }

    public long timeOfLastReset() {
        List copyOfStatusList = this.sm.getCopyOfStatusList();
        if (copyOfStatusList == null) {
            return -1;
        }
        for (int size = copyOfStatusList.size() - 1; size >= 0; size--) {
            Status status = (Status) copyOfStatusList.get(size);
            if (CoreConstants.RESET_MSG_PREFIX.equals(status.getMessage())) {
                return status.getDate().longValue();
            }
        }
        return -1;
    }
}
