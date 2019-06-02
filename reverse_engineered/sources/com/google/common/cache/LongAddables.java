package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible(emulated = true)
final class LongAddables {
    private static final Supplier<LongAddable> SUPPLIER;

    /* renamed from: com.google.common.cache.LongAddables$1 */
    static class C35311 implements Supplier<LongAddable> {
        C35311() {
        }

        public LongAddable get() {
            return new LongAdder();
        }
    }

    /* renamed from: com.google.common.cache.LongAddables$2 */
    static class C35322 implements Supplier<LongAddable> {
        C35322() {
        }

        public LongAddable get() {
            return new PureJavaLongAddable();
        }
    }

    private static final class PureJavaLongAddable extends AtomicLong implements LongAddable {
        private PureJavaLongAddable() {
        }

        public void increment() {
            getAndIncrement();
        }

        public void add(long j) {
            getAndAdd(j);
        }

        public long sum() {
            return get();
        }
    }

    LongAddables() {
    }

    static {
        Supplier c35311;
        try {
            LongAdder longAdder = new LongAdder();
            c35311 = new C35311();
        } catch (Throwable th) {
            c35311 = new C35322();
        }
        SUPPLIER = c35311;
    }

    public static LongAddable create() {
        return (LongAddable) SUPPLIER.get();
    }
}
