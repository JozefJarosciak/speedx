package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    private static final class ComputationExceptionReference<K, V> implements ValueReference<K, V> {
        /* renamed from: t */
        final Throwable f14267t;

        ComputationExceptionReference(Throwable th) {
            this.f14267t = th;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.f14267t);
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    private static final class ComputedReference<K, V> implements ValueReference<K, V> {
        final V value;

        ComputedReference(V v) {
            this.value = v;
        }

        public V get() {
            return this.value;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() {
            return get();
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    static final class ComputingSegment<K, V> extends Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        V getOrCompute(K r12, int r13, com.google.common.base.Function<? super K, ? extends V> r14) throws java.util.concurrent.ExecutionException {
            /*
            r11 = this;
            r2 = 1;
            r3 = 0;
        L_0x0002:
            r1 = r11.getEntry(r12, r13);	 Catch:{ all -> 0x00be }
            if (r1 == 0) goto L_0x0015;
        L_0x0008:
            r0 = r11.getLiveValue(r1);	 Catch:{ all -> 0x00be }
            if (r0 == 0) goto L_0x0015;
        L_0x000e:
            r11.recordRead(r1);	 Catch:{ all -> 0x00be }
            r11.postReadCleanup();
        L_0x0014:
            return r0;
        L_0x0015:
            if (r1 == 0) goto L_0x0021;
        L_0x0017:
            r0 = r1.getValueReference();	 Catch:{ all -> 0x00be }
            r0 = r0.isComputingReference();	 Catch:{ all -> 0x00be }
            if (r0 != 0) goto L_0x00de;
        L_0x0021:
            r4 = 0;
            r11.lock();	 Catch:{ all -> 0x00be }
            r11.preWriteCleanup();	 Catch:{ all -> 0x00b6 }
            r0 = r11.count;	 Catch:{ all -> 0x00b6 }
            r6 = r0 + -1;
            r7 = r11.table;	 Catch:{ all -> 0x00b6 }
            r0 = r7.length();	 Catch:{ all -> 0x00b6 }
            r0 = r0 + -1;
            r8 = r13 & r0;
            r0 = r7.get(r8);	 Catch:{ all -> 0x00b6 }
            r0 = (com.google.common.collect.MapMakerInternalMap.ReferenceEntry) r0;	 Catch:{ all -> 0x00b6 }
            r5 = r0;
        L_0x003d:
            if (r5 == 0) goto L_0x0102;
        L_0x003f:
            r9 = r5.getKey();	 Catch:{ all -> 0x00b6 }
            r1 = r5.getHash();	 Catch:{ all -> 0x00b6 }
            if (r1 != r13) goto L_0x00d2;
        L_0x0049:
            if (r9 == 0) goto L_0x00d2;
        L_0x004b:
            r1 = r11.map;	 Catch:{ all -> 0x00b6 }
            r1 = r1.keyEquivalence;	 Catch:{ all -> 0x00b6 }
            r1 = r1.equivalent(r12, r9);	 Catch:{ all -> 0x00b6 }
            if (r1 == 0) goto L_0x00d2;
        L_0x0055:
            r1 = r5.getValueReference();	 Catch:{ all -> 0x00b6 }
            r1 = r1.isComputingReference();	 Catch:{ all -> 0x00b6 }
            if (r1 == 0) goto L_0x0083;
        L_0x005f:
            r6 = r3;
        L_0x0060:
            if (r6 == 0) goto L_0x00fe;
        L_0x0062:
            r1 = new com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference;	 Catch:{ all -> 0x00b6 }
            r1.<init>(r14);	 Catch:{ all -> 0x00b6 }
            if (r5 != 0) goto L_0x00d8;
        L_0x0069:
            r0 = r11.newEntry(r12, r13, r0);	 Catch:{ all -> 0x00b6 }
            r0.setValueReference(r1);	 Catch:{ all -> 0x00b6 }
            r7.set(r8, r0);	 Catch:{ all -> 0x00b6 }
        L_0x0073:
            r11.unlock();	 Catch:{ all -> 0x00be }
            r11.postWriteCleanup();	 Catch:{ all -> 0x00be }
            if (r6 == 0) goto L_0x00dd;
        L_0x007b:
            r0 = r11.compute(r12, r13, r0, r1);	 Catch:{ all -> 0x00be }
            r11.postReadCleanup();
            goto L_0x0014;
        L_0x0083:
            r1 = r5.getValueReference();	 Catch:{ all -> 0x00b6 }
            r1 = r1.get();	 Catch:{ all -> 0x00b6 }
            if (r1 != 0) goto L_0x00a0;
        L_0x008d:
            r10 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED;	 Catch:{ all -> 0x00b6 }
            r11.enqueueNotification(r9, r13, r1, r10);	 Catch:{ all -> 0x00b6 }
        L_0x0092:
            r1 = r11.evictionQueue;	 Catch:{ all -> 0x00b6 }
            r1.remove(r5);	 Catch:{ all -> 0x00b6 }
            r1 = r11.expirationQueue;	 Catch:{ all -> 0x00b6 }
            r1.remove(r5);	 Catch:{ all -> 0x00b6 }
            r11.count = r6;	 Catch:{ all -> 0x00b6 }
            r6 = r2;
            goto L_0x0060;
        L_0x00a0:
            r10 = r11.map;	 Catch:{ all -> 0x00b6 }
            r10 = r10.expires();	 Catch:{ all -> 0x00b6 }
            if (r10 == 0) goto L_0x00c3;
        L_0x00a8:
            r10 = r11.map;	 Catch:{ all -> 0x00b6 }
            r10 = r10.isExpired(r5);	 Catch:{ all -> 0x00b6 }
            if (r10 == 0) goto L_0x00c3;
        L_0x00b0:
            r10 = com.google.common.collect.MapMaker$RemovalCause.EXPIRED;	 Catch:{ all -> 0x00b6 }
            r11.enqueueNotification(r9, r13, r1, r10);	 Catch:{ all -> 0x00b6 }
            goto L_0x0092;
        L_0x00b6:
            r0 = move-exception;
            r11.unlock();	 Catch:{ all -> 0x00be }
            r11.postWriteCleanup();	 Catch:{ all -> 0x00be }
            throw r0;	 Catch:{ all -> 0x00be }
        L_0x00be:
            r0 = move-exception;
            r11.postReadCleanup();
            throw r0;
        L_0x00c3:
            r11.recordLockedRead(r5);	 Catch:{ all -> 0x00b6 }
            r11.unlock();	 Catch:{ all -> 0x00be }
            r11.postWriteCleanup();	 Catch:{ all -> 0x00be }
            r11.postReadCleanup();
            r0 = r1;
            goto L_0x0014;
        L_0x00d2:
            r5 = r5.getNext();	 Catch:{ all -> 0x00b6 }
            goto L_0x003d;
        L_0x00d8:
            r5.setValueReference(r1);	 Catch:{ all -> 0x00b6 }
            r0 = r5;
            goto L_0x0073;
        L_0x00dd:
            r1 = r0;
        L_0x00de:
            r0 = java.lang.Thread.holdsLock(r1);	 Catch:{ all -> 0x00be }
            if (r0 != 0) goto L_0x00fc;
        L_0x00e4:
            r0 = r2;
        L_0x00e5:
            r4 = "Recursive computation";
            com.google.common.base.Preconditions.checkState(r0, r4);	 Catch:{ all -> 0x00be }
            r0 = r1.getValueReference();	 Catch:{ all -> 0x00be }
            r0 = r0.waitForValue();	 Catch:{ all -> 0x00be }
            if (r0 == 0) goto L_0x0002;
        L_0x00f4:
            r11.recordRead(r1);	 Catch:{ all -> 0x00be }
            r11.postReadCleanup();
            goto L_0x0014;
        L_0x00fc:
            r0 = r3;
            goto L_0x00e5;
        L_0x00fe:
            r1 = r4;
            r0 = r5;
            goto L_0x0073;
        L_0x0102:
            r6 = r2;
            goto L_0x0060;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.getOrCompute(java.lang.Object, int, com.google.common.base.Function):V");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        V compute(K r7, int r8, com.google.common.collect.MapMakerInternalMap.ReferenceEntry<K, V> r9, com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference<K, V> r10) throws java.util.concurrent.ExecutionException {
            /*
            r6 = this;
            r4 = 0;
            r1 = 0;
            java.lang.System.nanoTime();
            monitor-enter(r9);	 Catch:{ all -> 0x003d }
            r1 = r10.compute(r7, r8);	 Catch:{ all -> 0x002b }
            r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x002b }
            monitor-exit(r9);	 Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x001e;
        L_0x0012:
            r0 = 1;
            r0 = r6.put(r7, r8, r1, r0);	 Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x001e;
        L_0x0019:
            r0 = com.google.common.collect.MapMaker$RemovalCause.REPLACED;	 Catch:{ all -> 0x002f }
            r6.enqueueNotification(r7, r8, r1, r0);	 Catch:{ all -> 0x002f }
        L_0x001e:
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 != 0) goto L_0x0025;
        L_0x0022:
            java.lang.System.nanoTime();
        L_0x0025:
            if (r1 != 0) goto L_0x002a;
        L_0x0027:
            r6.clearValue(r7, r8, r10);
        L_0x002a:
            return r1;
        L_0x002b:
            r0 = move-exception;
            r2 = r4;
        L_0x002d:
            monitor-exit(r9);	 Catch:{ all -> 0x0040 }
            throw r0;	 Catch:{ all -> 0x002f }
        L_0x002f:
            r0 = move-exception;
        L_0x0030:
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x0037;
        L_0x0034:
            java.lang.System.nanoTime();
        L_0x0037:
            if (r1 != 0) goto L_0x003c;
        L_0x0039:
            r6.clearValue(r7, r8, r10);
        L_0x003c:
            throw r0;
        L_0x003d:
            r0 = move-exception;
            r2 = r4;
            goto L_0x0030;
        L_0x0040:
            r0 = move-exception;
            goto L_0x002d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.compute(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$ReferenceEntry, com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference):V");
        }
    }

    static final class ComputingSerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, int i, int i2, MapMaker$RemovalListener<? super K, ? super V> mapMaker$RemovalListener, ConcurrentMap<K, V> concurrentMap, Function<? super K, ? extends V> function) {
            super(strength, strength2, equivalence, equivalence2, j, j2, i, i2, mapMaker$RemovalListener, concurrentMap);
            this.computingFunction = function;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeComputingMap(this.computingFunction);
            readEntries(objectInputStream);
        }

        Object readResolve() {
            return this.delegate;
        }
    }

    private static final class ComputingValueReference<K, V> implements ValueReference<K, V> {
        volatile ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        public ComputingValueReference(Function<? super K, ? extends V> function) {
            this.computingFunction = function;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return true;
        }

        public V waitForValue() throws ExecutionException {
            if (this.computedReference == MapMakerInternalMap.UNSET) {
                Object obj = null;
                try {
                    synchronized (this) {
                        while (this.computedReference == MapMakerInternalMap.UNSET) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                obj = 1;
                            }
                        }
                    }
                } finally {
                    if (obj != null) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            return this.computedReference.waitForValue();
        }

        public void clear(ValueReference<K, V> valueReference) {
            setValueReference(valueReference);
        }

        V compute(K k, int i) throws ExecutionException {
            try {
                V apply = this.computingFunction.apply(k);
                setValueReference(new ComputedReference(apply));
                return apply;
            } catch (Throwable th) {
                setValueReference(new ComputationExceptionReference(th));
                ExecutionException executionException = new ExecutionException(th);
            }
        }

        void setValueReference(ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    notifyAll();
                }
            }
        }
    }

    ComputingConcurrentHashMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker);
        this.computingFunction = (Function) Preconditions.checkNotNull(function);
    }

    Segment<K, V> createSegment(int i, int i2) {
        return new ComputingSegment(this, i, i2);
    }

    ComputingSegment<K, V> segmentFor(int i) {
        return (ComputingSegment) super.segmentFor(i);
    }

    V getOrCompute(K k) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(k));
        return segmentFor(hash).getOrCompute(k, hash, this.computingFunction);
    }

    Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }
}
