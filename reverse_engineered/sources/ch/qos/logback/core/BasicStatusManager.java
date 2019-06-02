package ch.qos.logback.core;

import ch.qos.logback.core.helpers.CyclicBuffer;
import ch.qos.logback.core.spi.LogbackLock;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.WarnStatus;
import java.util.ArrayList;
import java.util.List;

public class BasicStatusManager implements StatusManager {
    public static final int MAX_HEADER_COUNT = 150;
    public static final int TAIL_SIZE = 150;
    int count = 0;
    int level = 0;
    protected final List<Status> statusList = new ArrayList();
    protected final LogbackLock statusListLock = new LogbackLock();
    protected final List<StatusListener> statusListenerList = new ArrayList();
    protected final LogbackLock statusListenerListLock = new LogbackLock();
    protected final CyclicBuffer<Status> tailBuffer = new CyclicBuffer(150);

    private void fireStatusAddEvent(Status status) {
        synchronized (this.statusListenerListLock) {
            for (StatusListener addStatusEvent : this.statusListenerList) {
                addStatusEvent.addStatusEvent(status);
            }
        }
    }

    public void add(Status status) {
        fireStatusAddEvent(status);
        this.count++;
        if (status.getLevel() > this.level) {
            this.level = status.getLevel();
        }
        synchronized (this.statusListLock) {
            if (this.statusList.size() < 150) {
                this.statusList.add(status);
            } else {
                this.tailBuffer.add(status);
            }
        }
    }

    public void add(StatusListener statusListener) {
        synchronized (this.statusListenerListLock) {
            this.statusListenerList.add(statusListener);
        }
    }

    public boolean addUniquely(StatusListener statusListener, Object obj) {
        for (StatusListener statusListener2 : getCopyOfStatusListenerList()) {
            if (statusListener2.getClass().isInstance(statusListener)) {
                add(new WarnStatus("A previous listener of type [" + statusListener2.getClass() + "] has been already registered. Skipping double registration.", obj));
                return false;
            }
        }
        add(statusListener);
        return true;
    }

    public void clear() {
        synchronized (this.statusListLock) {
            this.count = 0;
            this.statusList.clear();
            this.tailBuffer.clear();
        }
    }

    public List<Status> getCopyOfStatusList() {
        List<Status> arrayList;
        synchronized (this.statusListLock) {
            arrayList = new ArrayList(this.statusList);
            arrayList.addAll(this.tailBuffer.asList());
        }
        return arrayList;
    }

    public List<StatusListener> getCopyOfStatusListenerList() {
        List arrayList;
        synchronized (this.statusListenerListLock) {
            arrayList = new ArrayList(this.statusListenerList);
        }
        return arrayList;
    }

    public int getCount() {
        return this.count;
    }

    public int getLevel() {
        return this.level;
    }

    public void remove(StatusListener statusListener) {
        synchronized (this.statusListenerListLock) {
            this.statusListenerList.remove(statusListener);
        }
    }
}
