package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public class EventBus {
    private static final LoadingCache<Class<?>, Set<Class<?>>> flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new C37661());
    private final ThreadLocal<Queue<EventWithSubscriber>> eventsToDispatch;
    private final SubscriberFindingStrategy finder;
    private final ThreadLocal<Boolean> isDispatching;
    private SubscriberExceptionHandler subscriberExceptionHandler;
    private final SetMultimap<Class<?>, EventSubscriber> subscribersByType;
    private final ReadWriteLock subscribersByTypeLock;

    /* renamed from: com.google.common.eventbus.EventBus$1 */
    static class C37661 extends CacheLoader<Class<?>, Set<Class<?>>> {
        C37661() {
        }

        public Set<Class<?>> load(Class<?> cls) {
            return TypeToken.of((Class) cls).getTypes().rawTypes();
        }
    }

    /* renamed from: com.google.common.eventbus.EventBus$2 */
    class C37672 extends ThreadLocal<Queue<EventWithSubscriber>> {
        C37672() {
        }

        protected Queue<EventWithSubscriber> initialValue() {
            return new LinkedList();
        }
    }

    /* renamed from: com.google.common.eventbus.EventBus$3 */
    class C37683 extends ThreadLocal<Boolean> {
        C37683() {
        }

        protected Boolean initialValue() {
            return Boolean.valueOf(false);
        }
    }

    static class EventWithSubscriber {
        final Object event;
        final EventSubscriber subscriber;

        public EventWithSubscriber(Object obj, EventSubscriber eventSubscriber) {
            this.event = Preconditions.checkNotNull(obj);
            this.subscriber = (EventSubscriber) Preconditions.checkNotNull(eventSubscriber);
        }
    }

    private static final class LoggingSubscriberExceptionHandler implements SubscriberExceptionHandler {
        private final Logger logger;

        public LoggingSubscriberExceptionHandler(String str) {
            String valueOf = String.valueOf(String.valueOf(EventBus.class.getName()));
            String valueOf2 = String.valueOf(String.valueOf((String) Preconditions.checkNotNull(str)));
            this.logger = Logger.getLogger(new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(".").append(valueOf2).toString());
        }

        public void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
            Logger logger = this.logger;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(String.valueOf(subscriberExceptionContext.getSubscriber()));
            String valueOf2 = String.valueOf(String.valueOf(subscriberExceptionContext.getSubscriberMethod()));
            logger.log(level, new StringBuilder((valueOf.length() + 30) + valueOf2.length()).append("Could not dispatch event: ").append(valueOf).append(" to ").append(valueOf2).toString(), th.getCause());
        }
    }

    public EventBus() {
        this("default");
    }

    public EventBus(String str) {
        this(new LoggingSubscriberExceptionHandler(str));
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        this.subscribersByType = HashMultimap.create();
        this.subscribersByTypeLock = new ReentrantReadWriteLock();
        this.finder = new AnnotatedSubscriberFinder();
        this.eventsToDispatch = new C37672();
        this.isDispatching = new C37683();
        this.subscriberExceptionHandler = (SubscriberExceptionHandler) Preconditions.checkNotNull(subscriberExceptionHandler);
    }

    public void register(Object obj) {
        Multimap findAllSubscribers = this.finder.findAllSubscribers(obj);
        this.subscribersByTypeLock.writeLock().lock();
        try {
            this.subscribersByType.putAll(findAllSubscribers);
        } finally {
            this.subscribersByTypeLock.writeLock().unlock();
        }
    }

    public void unregister(Object obj) {
        for (Entry entry : this.finder.findAllSubscribers(obj).asMap().entrySet()) {
            Class cls = (Class) entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.subscribersByTypeLock.writeLock().lock();
            try {
                Set set = this.subscribersByType.get(cls);
                if (set.containsAll(collection)) {
                    set.removeAll(collection);
                    this.subscribersByTypeLock.writeLock().unlock();
                } else {
                    String valueOf = String.valueOf(String.valueOf(obj));
                    throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 65).append("missing event subscriber for an annotated method. Is ").append(valueOf).append(" registered?").toString());
                }
            } catch (Throwable th) {
                this.subscribersByTypeLock.writeLock().unlock();
                throw th;
            }
        }
    }

    public void post(Object obj) {
        Object obj2 = null;
        for (Class cls : flattenHierarchy(obj.getClass())) {
            this.subscribersByTypeLock.readLock().lock();
            try {
                Set<EventSubscriber> set = this.subscribersByType.get(cls);
                if (!set.isEmpty()) {
                    obj2 = 1;
                    for (EventSubscriber enqueueEvent : set) {
                        enqueueEvent(obj, enqueueEvent);
                    }
                }
                Object obj3 = obj2;
                this.subscribersByTypeLock.readLock().unlock();
                obj2 = obj3;
            } catch (Throwable th) {
                this.subscribersByTypeLock.readLock().unlock();
            }
        }
        if (obj2 == null && !(obj instanceof DeadEvent)) {
            post(new DeadEvent(this, obj));
        }
        dispatchQueuedEvents();
    }

    void enqueueEvent(Object obj, EventSubscriber eventSubscriber) {
        ((Queue) this.eventsToDispatch.get()).offer(new EventWithSubscriber(obj, eventSubscriber));
    }

    void dispatchQueuedEvents() {
        if (!((Boolean) this.isDispatching.get()).booleanValue()) {
            this.isDispatching.set(Boolean.valueOf(true));
            try {
                Queue queue = (Queue) this.eventsToDispatch.get();
                while (true) {
                    EventWithSubscriber eventWithSubscriber = (EventWithSubscriber) queue.poll();
                    if (eventWithSubscriber == null) {
                        break;
                    }
                    dispatch(eventWithSubscriber.event, eventWithSubscriber.subscriber);
                }
            } finally {
                this.isDispatching.remove();
                this.eventsToDispatch.remove();
            }
        }
    }

    void dispatch(Object obj, EventSubscriber eventSubscriber) {
        try {
            eventSubscriber.handleEvent(obj);
        } catch (InvocationTargetException e) {
            this.subscriberExceptionHandler.handleException(e.getCause(), new SubscriberExceptionContext(this, obj, eventSubscriber.getSubscriber(), eventSubscriber.getMethod()));
        } catch (Throwable th) {
            Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, String.format("Exception %s thrown while handling exception: %s", new Object[]{th, e.getCause()}), th);
        }
    }

    @VisibleForTesting
    Set<Class<?>> flattenHierarchy(Class<?> cls) {
        try {
            return (Set) flattenHierarchyCache.getUnchecked(cls);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }
}
