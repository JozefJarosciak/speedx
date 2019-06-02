package ch.qos.logback.core.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class StatusBase implements Status {
    private static final List<Status> EMPTY_LIST = new ArrayList(0);
    List<Status> childrenList;
    long date;
    int level;
    final String message;
    final Object origin;
    Throwable throwable;

    StatusBase(int i, String str, Object obj) {
        this(i, str, obj, null);
    }

    StatusBase(int i, String str, Object obj, Throwable th) {
        this.level = i;
        this.message = str;
        this.origin = obj;
        this.throwable = th;
        this.date = System.currentTimeMillis();
    }

    public synchronized void add(Status status) {
        if (status == null) {
            throw new NullPointerException("Null values are not valid Status.");
        }
        if (this.childrenList == null) {
            this.childrenList = new ArrayList();
        }
        this.childrenList.add(status);
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
        StatusBase statusBase = (StatusBase) obj;
        return this.level != statusBase.level ? false : this.message == null ? statusBase.message == null : this.message.equals(statusBase.message);
    }

    public Long getDate() {
        return Long.valueOf(this.date);
    }

    public synchronized int getEffectiveLevel() {
        int i;
        int i2 = this.level;
        Iterator it = iterator();
        i = i2;
        while (it.hasNext()) {
            i2 = ((Status) it.next()).getEffectiveLevel();
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    public int getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getOrigin() {
        return this.origin;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public synchronized boolean hasChildren() {
        boolean z;
        z = this.childrenList != null && this.childrenList.size() > 0;
        return z;
    }

    public int hashCode() {
        return (this.message == null ? 0 : this.message.hashCode()) + ((this.level + 31) * 31);
    }

    public synchronized Iterator<Status> iterator() {
        return this.childrenList != null ? this.childrenList.iterator() : EMPTY_LIST.iterator();
    }

    public synchronized boolean remove(Status status) {
        return this.childrenList == null ? false : this.childrenList.remove(status);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        switch (getEffectiveLevel()) {
            case 0:
                stringBuffer.append("INFO");
                break;
            case 1:
                stringBuffer.append("WARN");
                break;
            case 2:
                stringBuffer.append("ERROR");
                break;
        }
        if (this.origin != null) {
            stringBuffer.append(" in ");
            stringBuffer.append(this.origin);
            stringBuffer.append(" -");
        }
        stringBuffer.append(" ");
        stringBuffer.append(this.message);
        if (this.throwable != null) {
            stringBuffer.append(" ");
            stringBuffer.append(this.throwable);
        }
        return stringBuffer.toString();
    }
}
