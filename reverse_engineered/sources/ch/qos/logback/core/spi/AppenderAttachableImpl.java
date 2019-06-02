package ch.qos.logback.core.spi;

import ch.qos.logback.core.Appender;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppenderAttachableImpl<E> implements AppenderAttachable<E> {
    static final long START = System.currentTimeMillis();
    private final CopyOnWriteArrayList<Appender<E>> appenderList = new CopyOnWriteArrayList();

    public void addAppender(Appender<E> appender) {
        if (appender == null) {
            throw new IllegalArgumentException("Null argument disallowed");
        }
        this.appenderList.addIfAbsent(appender);
    }

    public int appendLoopOnAppenders(E e) {
        Iterator it = this.appenderList.iterator();
        int i = 0;
        while (it.hasNext()) {
            ((Appender) it.next()).doAppend(e);
            i++;
        }
        return i;
    }

    public void detachAndStopAllAppenders() {
        Iterator it = this.appenderList.iterator();
        while (it.hasNext()) {
            ((Appender) it.next()).stop();
        }
        this.appenderList.clear();
    }

    public boolean detachAppender(Appender<E> appender) {
        return appender == null ? false : this.appenderList.remove(appender);
    }

    public boolean detachAppender(String str) {
        if (str == null) {
            return false;
        }
        boolean remove;
        Iterator it = this.appenderList.iterator();
        while (it.hasNext()) {
            Appender appender = (Appender) it.next();
            if (str.equals(appender.getName())) {
                remove = this.appenderList.remove(appender);
                break;
            }
        }
        remove = false;
        return remove;
    }

    public Appender<E> getAppender(String str) {
        if (str == null) {
            return null;
        }
        Iterator it = this.appenderList.iterator();
        while (it.hasNext()) {
            Appender<E> appender = (Appender) it.next();
            if (str.equals(appender.getName())) {
                return appender;
            }
        }
        return null;
    }

    public boolean isAttached(Appender<E> appender) {
        if (appender == null) {
            return false;
        }
        Iterator it = this.appenderList.iterator();
        while (it.hasNext()) {
            if (((Appender) it.next()) == appender) {
                return true;
            }
        }
        return false;
    }

    public Iterator<Appender<E>> iteratorForAppenders() {
        return this.appenderList.iterator();
    }
}
