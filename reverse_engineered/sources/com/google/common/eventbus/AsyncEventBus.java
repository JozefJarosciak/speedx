package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

@Beta
public class AsyncEventBus extends EventBus {
    private final ConcurrentLinkedQueue<EventWithSubscriber> eventsToDispatch = new ConcurrentLinkedQueue();
    private final Executor executor;

    public AsyncEventBus(String str, Executor executor) {
        super(str);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(subscriberExceptionHandler);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    public AsyncEventBus(Executor executor) {
        super("default");
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    void enqueueEvent(Object obj, EventSubscriber eventSubscriber) {
        this.eventsToDispatch.offer(new EventWithSubscriber(obj, eventSubscriber));
    }

    protected void dispatchQueuedEvents() {
        while (true) {
            EventWithSubscriber eventWithSubscriber = (EventWithSubscriber) this.eventsToDispatch.poll();
            if (eventWithSubscriber != null) {
                dispatch(eventWithSubscriber.event, eventWithSubscriber.subscriber);
            } else {
                return;
            }
        }
    }

    void dispatch(final Object obj, final EventSubscriber eventSubscriber) {
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(eventSubscriber);
        this.executor.execute(new Runnable() {
            public void run() {
                super.dispatch(obj, eventSubscriber);
            }
        });
    }
}
