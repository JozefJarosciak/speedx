package ch.qos.logback.core.spi;

import ch.qos.logback.core.helpers.CyclicBuffer;
import java.util.ArrayList;
import java.util.List;

public class CyclicBufferTracker<E> extends AbstractComponentTracker<CyclicBuffer<E>> {
    static final int DEFAULT_BUFFER_SIZE = 256;
    static final int DEFAULT_NUMBER_OF_BUFFERS = 64;
    int bufferSize = 256;

    public CyclicBufferTracker() {
        setMaxComponents(64);
    }

    protected CyclicBuffer<E> buildComponent(String str) {
        return new CyclicBuffer(this.bufferSize);
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    protected boolean isComponentStale(CyclicBuffer<E> cyclicBuffer) {
        return false;
    }

    List<String> lingererKeysAsOrderedList() {
        return new ArrayList(this.lingerersMap.keySet());
    }

    List<String> liveKeysAsOrderedList() {
        return new ArrayList(this.liveMap.keySet());
    }

    protected void processPriorToRemoval(CyclicBuffer<E> cyclicBuffer) {
        cyclicBuffer.clear();
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }
}
