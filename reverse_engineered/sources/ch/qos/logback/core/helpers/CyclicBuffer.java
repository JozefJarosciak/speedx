package ch.qos.logback.core.helpers;

import java.util.ArrayList;
import java.util.List;

public class CyclicBuffer<E> {
    E[] ea;
    int first;
    int last;
    int maxSize;
    int numElems;

    public CyclicBuffer(int i) throws IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("The maxSize argument (" + i + ") is not a positive integer.");
        }
        init(i);
    }

    public CyclicBuffer(CyclicBuffer<E> cyclicBuffer) {
        this.maxSize = cyclicBuffer.maxSize;
        this.ea = new Object[this.maxSize];
        System.arraycopy(cyclicBuffer.ea, 0, this.ea, 0, this.maxSize);
        this.last = cyclicBuffer.last;
        this.first = cyclicBuffer.first;
        this.numElems = cyclicBuffer.numElems;
    }

    private void init(int i) {
        this.maxSize = i;
        this.ea = new Object[i];
        this.first = 0;
        this.last = 0;
        this.numElems = 0;
    }

    public void add(E e) {
        this.ea[this.last] = e;
        int i = this.last + 1;
        this.last = i;
        if (i == this.maxSize) {
            this.last = 0;
        }
        if (this.numElems < this.maxSize) {
            this.numElems++;
            return;
        }
        i = this.first + 1;
        this.first = i;
        if (i == this.maxSize) {
            this.first = 0;
        }
    }

    public List<E> asList() {
        List<E> arrayList = new ArrayList();
        for (int i = 0; i < length(); i++) {
            arrayList.add(get(i));
        }
        return arrayList;
    }

    public void clear() {
        init(this.maxSize);
    }

    public E get() {
        if (this.numElems <= 0) {
            return null;
        }
        this.numElems--;
        E e = this.ea[this.first];
        this.ea[this.first] = null;
        int i = this.first + 1;
        this.first = i;
        if (i != this.maxSize) {
            return e;
        }
        this.first = 0;
        return e;
    }

    public E get(int i) {
        return (i < 0 || i >= this.numElems) ? null : this.ea[(this.first + i) % this.maxSize];
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int length() {
        return this.numElems;
    }

    public void resize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative array size [" + i + "] not allowed.");
        } else if (i != this.numElems) {
            Object[] objArr = new Object[i];
            int i2 = i < this.numElems ? i : this.numElems;
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = this.ea[this.first];
                this.ea[this.first] = null;
                int i4 = this.first + 1;
                this.first = i4;
                if (i4 == this.numElems) {
                    this.first = 0;
                }
            }
            this.ea = objArr;
            this.first = 0;
            this.numElems = i2;
            this.maxSize = i;
            if (i2 == i) {
                this.last = 0;
            } else {
                this.last = i2;
            }
        }
    }
}
