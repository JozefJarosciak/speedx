package ch.qos.logback.core.read;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.helpers.CyclicBuffer;

public class CyclicBufferAppender<E> extends AppenderBase<E> {
    CyclicBuffer<E> cb;
    int maxSize = 512;

    protected void append(E e) {
        if (isStarted()) {
            this.cb.add(e);
        }
    }

    public E get(int i) {
        return isStarted() ? this.cb.get(i) : null;
    }

    public int getLength() {
        return isStarted() ? this.cb.length() : 0;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void reset() {
        this.cb.clear();
    }

    public void setMaxSize(int i) {
        this.maxSize = i;
    }

    public void start() {
        this.cb = new CyclicBuffer(this.maxSize);
        super.start();
    }

    public void stop() {
        this.cb = null;
        super.stop();
    }
}
