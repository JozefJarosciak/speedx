package com.google.common.cache;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal();
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;

    /* renamed from: com.google.common.cache.Striped64$1 */
    static class C35401 implements PrivilegedExceptionAction<Unsafe> {
        C35401() {
        }

        public Unsafe run() throws Exception {
            Class cls = Unsafe.class;
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (cls.isInstance(obj)) {
                    return (Unsafe) cls.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }
    }

    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;
        volatile long p0;
        volatile long p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;
        volatile long q0;
        volatile long q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        Cell(long j) {
            this.value = j;
        }

        final boolean cas(long j, long j2) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j, j2);
        }

        static {
            try {
                UNSAFE = Striped64.getUnsafe();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Throwable e) {
                throw new Error(e);
            }
        }
    }

    abstract long fn(long j, long j2);

    static {
        try {
            UNSAFE = getUnsafe();
            Class cls = Striped64.class;
            baseOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(cls.getDeclaredField("busy"));
        } catch (Throwable e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    final void retryUpdate(long j, int[] iArr, boolean z) {
        int nextInt;
        if (iArr == null) {
            iArr = new int[1];
            threadHashCode.set(iArr);
            nextInt = rng.nextInt();
            if (nextInt == 0) {
                nextInt = 1;
            }
            iArr[0] = nextInt;
        } else {
            nextInt = iArr[0];
        }
        int i = nextInt;
        Object obj = null;
        while (true) {
            Object obj2;
            Cell[] cellArr = this.cells;
            if (cellArr != null) {
                int length = cellArr.length;
                if (length > 0) {
                    Cell cell = cellArr[(length - 1) & i];
                    Cell[] cellArr2;
                    if (cell == null) {
                        if (this.busy == 0) {
                            Cell cell2 = new Cell(j);
                            if (this.busy == 0 && casBusy()) {
                                obj2 = null;
                                try {
                                    cellArr2 = this.cells;
                                    if (cellArr2 != null) {
                                        int length2 = cellArr2.length;
                                        if (length2 > 0) {
                                            length2 = (length2 - 1) & i;
                                            if (cellArr2[length2] == null) {
                                                cellArr2[length2] = cell2;
                                                obj2 = 1;
                                            }
                                        }
                                    }
                                    this.busy = 0;
                                    if (obj2 != null) {
                                        return;
                                    }
                                } catch (Throwable th) {
                                    this.busy = 0;
                                }
                            }
                        }
                        obj = null;
                    } else if (z) {
                        long j2 = cell.value;
                        if (!cell.cas(j2, fn(j2, j))) {
                            if (length >= NCPU || this.cells != cellArr) {
                                obj = null;
                            } else if (obj == null) {
                                obj = 1;
                            } else if (this.busy == 0 && casBusy()) {
                                try {
                                    if (this.cells == cellArr) {
                                        cellArr2 = new Cell[(length << 1)];
                                        for (nextInt = 0; nextInt < length; nextInt++) {
                                            cellArr2[nextInt] = cellArr[nextInt];
                                        }
                                        this.cells = cellArr2;
                                    }
                                    this.busy = 0;
                                    obj = null;
                                } catch (Throwable th2) {
                                    this.busy = 0;
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        z = true;
                    }
                    i ^= i << 13;
                    i ^= i >>> 17;
                    i ^= i << 5;
                    iArr[0] = i;
                }
            }
            if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                obj2 = null;
                try {
                    if (this.cells == cellArr) {
                        Cell[] cellArr3 = new Cell[2];
                        cellArr3[i & 1] = new Cell(j);
                        this.cells = cellArr3;
                        obj2 = 1;
                    }
                    this.busy = 0;
                    if (obj2 != null) {
                        return;
                    }
                } catch (Throwable th3) {
                    this.busy = 0;
                }
            } else {
                long j3 = this.base;
                if (casBase(j3, fn(j3, j))) {
                    return;
                }
            }
        }
    }

    final void internalReset(long j) {
        Cell[] cellArr = this.cells;
        this.base = j;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j;
                }
            }
        }
    }

    private static Unsafe getUnsafe() {
        Unsafe unsafe;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (SecurityException e) {
            try {
                unsafe = (Unsafe) AccessController.doPrivileged(new C35401());
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }
        return unsafe;
    }
}
