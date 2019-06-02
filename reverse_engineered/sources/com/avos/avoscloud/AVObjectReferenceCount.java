package com.avos.avoscloud;

import java.util.concurrent.atomic.AtomicInteger;

public class AVObjectReferenceCount {
    AtomicInteger count = new AtomicInteger(1);
    AVObject value;

    public AVObjectReferenceCount(AVObject aVObject) {
        this.value = aVObject;
    }

    public int increment() {
        return this.count.incrementAndGet();
    }

    public int desc() {
        return this.count.decrementAndGet();
    }

    public int getCount() {
        return this.count.get();
    }

    public AVObject getValue() {
        return this.value;
    }
}
