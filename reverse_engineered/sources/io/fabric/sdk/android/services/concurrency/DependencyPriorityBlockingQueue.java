package io.fabric.sdk.android.services.concurrency;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DependencyPriorityBlockingQueue<E extends C4842a & C4844h & C4843e> extends PriorityBlockingQueue<E> {
    /* renamed from: a */
    final Queue<E> f17219a = new LinkedList();
    private final ReentrantLock lock = new ReentrantLock();

    public E take() throws InterruptedException {
        return m19233b(0, null, null);
    }

    public E peek() {
        E e = null;
        try {
            e = m19233b(1, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return m19233b(3, Long.valueOf(j), timeUnit);
    }

    public E poll() {
        E e = null;
        try {
            e = m19233b(2, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public int size() {
        try {
            this.lock.lock();
            int size = this.f17219a.size() + super.size();
            return size;
        } finally {
            this.lock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        try {
            this.lock.lock();
            T[] a = m19232a(super.toArray(tArr), this.f17219a.toArray(tArr));
            return a;
        } finally {
            this.lock.unlock();
        }
    }

    public Object[] toArray() {
        try {
            this.lock.lock();
            Object[] a = m19232a(super.toArray(), this.f17219a.toArray());
            return a;
        } finally {
            this.lock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        try {
            this.lock.lock();
            int drainTo = super.drainTo(collection) + this.f17219a.size();
            while (!this.f17219a.isEmpty()) {
                collection.add(this.f17219a.poll());
            }
            return drainTo;
        } finally {
            this.lock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection, int i) {
        try {
            this.lock.lock();
            int drainTo = super.drainTo(collection, i);
            while (!this.f17219a.isEmpty() && drainTo <= i) {
                collection.add(this.f17219a.poll());
                drainTo++;
            }
            this.lock.unlock();
            return drainTo;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    public boolean contains(Object obj) {
        try {
            this.lock.lock();
            boolean z = super.contains(obj) || this.f17219a.contains(obj);
            this.lock.unlock();
            return z;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    public void clear() {
        try {
            this.lock.lock();
            this.f17219a.clear();
            super.clear();
        } finally {
            this.lock.unlock();
        }
    }

    public boolean remove(Object obj) {
        try {
            this.lock.lock();
            boolean z = super.remove(obj) || this.f17219a.remove(obj);
            this.lock.unlock();
            return z;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    public boolean removeAll(Collection<?> collection) {
        try {
            this.lock.lock();
            boolean removeAll = super.removeAll(collection) | this.f17219a.removeAll(collection);
            return removeAll;
        } finally {
            this.lock.unlock();
        }
    }

    /* renamed from: a */
    E m19229a(int i, Long l, TimeUnit timeUnit) throws InterruptedException {
        switch (i) {
            case 0:
                return (C4842a) super.take();
            case 1:
                return (C4842a) super.peek();
            case 2:
                return (C4842a) super.poll();
            case 3:
                return (C4842a) super.poll(l.longValue(), timeUnit);
            default:
                return null;
        }
    }

    /* renamed from: a */
    boolean m19230a(int i, E e) {
        try {
            this.lock.lock();
            if (i == 1) {
                super.remove(e);
            }
            boolean offer = this.f17219a.offer(e);
            return offer;
        } finally {
            this.lock.unlock();
        }
    }

    /* renamed from: b */
    E m19233b(int i, Long l, TimeUnit timeUnit) throws InterruptedException {
        C4842a a;
        while (true) {
            a = m19229a(i, l, timeUnit);
            if (a == null || m19231a(a)) {
                return a;
            }
            m19230a(i, a);
        }
        return a;
    }

    /* renamed from: a */
    boolean m19231a(E e) {
        return e.mo6231c();
    }

    public void recycleBlockedQueue() {
        try {
            this.lock.lock();
            Iterator it = this.f17219a.iterator();
            while (it.hasNext()) {
                C4842a c4842a = (C4842a) it.next();
                if (m19231a(c4842a)) {
                    super.offer(c4842a);
                    it.remove();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* renamed from: a */
    <T> T[] m19232a(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + length2);
        System.arraycopy(tArr, 0, objArr, 0, length);
        System.arraycopy(tArr2, 0, objArr, length, length2);
        return objArr;
    }
}
