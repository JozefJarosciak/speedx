package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EventSubscriber {
    private final Method method;
    private final Object target;

    EventSubscriber(Object obj, Method method) {
        Preconditions.checkNotNull(obj, "EventSubscriber target cannot be null.");
        Preconditions.checkNotNull(method, "EventSubscriber method cannot be null.");
        this.target = obj;
        this.method = method;
        method.setAccessible(true);
    }

    public void handleEvent(Object obj) throws InvocationTargetException {
        String valueOf;
        Preconditions.checkNotNull(obj);
        try {
            this.method.invoke(this.target, new Object[]{obj});
        } catch (Throwable e) {
            valueOf = String.valueOf(String.valueOf(obj));
            throw new Error(new StringBuilder(valueOf.length() + 33).append("Method rejected target/argument: ").append(valueOf).toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(String.valueOf(obj));
            throw new Error(new StringBuilder(valueOf.length() + 28).append("Method became inaccessible: ").append(valueOf).toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.method));
        return new StringBuilder(valueOf.length() + 10).append("[wrapper ").append(valueOf).append("]").toString();
    }

    public int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EventSubscriber)) {
            return false;
        }
        EventSubscriber eventSubscriber = (EventSubscriber) obj;
        if (this.target == eventSubscriber.target && this.method.equals(eventSubscriber.method)) {
            return true;
        }
        return false;
    }

    public Object getSubscriber() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }
}
