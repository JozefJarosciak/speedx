package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Beta
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new C39145();

    /* renamed from: com.google.common.util.concurrent.Striped$1 */
    static class C39101 implements Supplier<Lock> {
        C39101() {
        }

        public Lock get() {
            return new PaddedLock();
        }
    }

    /* renamed from: com.google.common.util.concurrent.Striped$2 */
    static class C39112 implements Supplier<Lock> {
        C39112() {
        }

        public Lock get() {
            return new ReentrantLock(false);
        }
    }

    /* renamed from: com.google.common.util.concurrent.Striped$5 */
    static class C39145 implements Supplier<ReadWriteLock> {
        C39145() {
        }

        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int i) {
            super();
            Preconditions.checkArgument(i > 0, "Stripes must be positive");
            this.mask = i > Ints.MAX_POWER_OF_TWO ? -1 : Striped.ceilToPowerOfTwo(i) - 1;
        }

        final int indexFor(Object obj) {
            return Striped.smear(obj.hashCode()) & this.mask;
        }

        public final L get(Object obj) {
            return getAt(indexFor(obj));
        }
    }

    private static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int i, Supplier<L> supplier) {
            boolean z;
            int i2 = 0;
            super(i);
            if (i <= Ints.MAX_POWER_OF_TWO) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Stripes must be <= 2^30)");
            this.array = new Object[(this.mask + 1)];
            while (i2 < this.array.length) {
                this.array[i2] = supplier.get();
                i2++;
            }
        }

        public L getAt(int i) {
            return this.array[i];
        }

        public int size() {
            return this.array.length;
        }
    }

    @VisibleForTesting
    static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        LargeLazyStriped(int i, Supplier<L> supplier) {
            super(i);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.supplier = supplier;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        public L getAt(int i) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            L l = this.locks.get(Integer.valueOf(i));
            if (l != null) {
                return l;
            }
            Object obj = this.supplier.get();
            return MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(i), obj), obj);
        }

        public int size() {
            return this.size;
        }
    }

    private static class PaddedLock extends ReentrantLock {
        long q1;
        long q2;
        long q3;

        PaddedLock() {
            super(false);
        }
    }

    private static class PaddedSemaphore extends Semaphore {
        long q1;
        long q2;
        long q3;

        PaddedSemaphore(int i) {
            super(i, false);
        }
    }

    @VisibleForTesting
    static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue = new ReferenceQueue();
        final int size;
        final Supplier<L> supplier;

        private static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            ArrayReference(L l, int i, ReferenceQueue<L> referenceQueue) {
                super(l, referenceQueue);
                this.index = i;
            }
        }

        SmallLazyStriped(int i, Supplier<L> supplier) {
            super(i);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.locks = new AtomicReferenceArray(this.size);
            this.supplier = supplier;
        }

        public L getAt(int i) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            Object obj = (ArrayReference) this.locks.get(i);
            L l = obj == null ? null : obj.get();
            if (l != null) {
                return l;
            }
            L l2 = this.supplier.get();
            ArrayReference arrayReference = new ArrayReference(l2, i, this.queue);
            while (!this.locks.compareAndSet(i, obj, arrayReference)) {
                ArrayReference arrayReference2 = (ArrayReference) this.locks.get(i);
                if (arrayReference2 == null) {
                    l = null;
                    continue;
                } else {
                    l = arrayReference2.get();
                    continue;
                }
                if (l != null) {
                    return l;
                }
            }
            drainQueue();
            return l2;
        }

        private void drainQueue() {
            while (true) {
                Reference poll = this.queue.poll();
                if (poll != null) {
                    ArrayReference arrayReference = (ArrayReference) poll;
                    this.locks.compareAndSet(arrayReference.index, arrayReference, null);
                } else {
                    return;
                }
            }
        }

        public int size() {
            return this.size;
        }
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i);

    abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] toArray = Iterables.toArray(iterable, Object.class);
        if (toArray.length == 0) {
            return ImmutableList.of();
        }
        int i;
        int[] iArr = new int[toArray.length];
        for (i = 0; i < toArray.length; i++) {
            iArr[i] = indexFor(toArray[i]);
        }
        Arrays.sort(iArr);
        int i2 = iArr[0];
        toArray[0] = getAt(i2);
        int i3 = i2;
        for (i = 1; i < toArray.length; i++) {
            i2 = iArr[i];
            if (i2 == i3) {
                toArray[i] = toArray[i - 1];
            } else {
                toArray[i] = getAt(i2);
                i3 = i2;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(toArray));
    }

    public static Striped<Lock> lock(int i) {
        return new CompactStriped(i, new C39101());
    }

    public static Striped<Lock> lazyWeakLock(int i) {
        return lazy(i, new C39112());
    }

    private static <L> Striped<L> lazy(int i, Supplier<L> supplier) {
        return i < 1024 ? new SmallLazyStriped(i, supplier) : new LargeLazyStriped(i, supplier);
    }

    public static Striped<Semaphore> semaphore(int i, final int i2) {
        return new CompactStriped(i, new Supplier<Semaphore>() {
            public Semaphore get() {
                return new PaddedSemaphore(i2);
            }
        });
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i, final int i2) {
        return lazy(i, new Supplier<Semaphore>() {
            public Semaphore get() {
                return new Semaphore(i2, false);
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int i) {
        return new CompactStriped(i, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i) {
        return lazy(i, READ_WRITE_LOCK_SUPPLIER);
    }

    private static int ceilToPowerOfTwo(int i) {
        return 1 << IntMath.log2(i, RoundingMode.CEILING);
    }

    private static int smear(int i) {
        int i2 = ((i >>> 20) ^ (i >>> 12)) ^ i;
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }
}
