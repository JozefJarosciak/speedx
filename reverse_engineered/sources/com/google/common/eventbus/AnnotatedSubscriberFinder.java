package com.google.common.eventbus;

import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class AnnotatedSubscriberFinder implements SubscriberFindingStrategy {
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new C37641());

    /* renamed from: com.google.common.eventbus.AnnotatedSubscriberFinder$1 */
    static class C37641 extends CacheLoader<Class<?>, ImmutableList<Method>> {
        C37641() {
        }

        public ImmutableList<Method> load(Class<?> cls) throws Exception {
            return AnnotatedSubscriberFinder.getAnnotatedMethodsInternal(cls);
        }
    }

    private static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.name, this.parameterTypes});
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodIdentifier)) {
                return false;
            }
            MethodIdentifier methodIdentifier = (MethodIdentifier) obj;
            if (this.name.equals(methodIdentifier.name) && this.parameterTypes.equals(methodIdentifier.parameterTypes)) {
                return true;
            }
            return false;
        }
    }

    AnnotatedSubscriberFinder() {
    }

    public Multimap<Class<?>, EventSubscriber> findAllSubscribers(Object obj) {
        Multimap<Class<?>, EventSubscriber> create = HashMultimap.create();
        Iterator it = getAnnotatedMethods(obj.getClass()).iterator();
        while (it.hasNext()) {
            Method method = (Method) it.next();
            create.put(method.getParameterTypes()[0], makeSubscriber(obj, method));
        }
        return create;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> cls) {
        try {
            return (ImmutableList) subscriberMethodsCache.getUnchecked(cls);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    private static ImmutableList<Method> getAnnotatedMethodsInternal(Class<?> cls) {
        Set<Class> rawTypes = TypeToken.of((Class) cls).getTypes().rawTypes();
        Map newHashMap = Maps.newHashMap();
        for (Class methods : rawTypes) {
            for (Method method : methods.getMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isBridge()) {
                    Class[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        String valueOf = String.valueOf(String.valueOf(method));
                        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 128).append("Method ").append(valueOf).append(" has @Subscribe annotation, but requires ").append(parameterTypes.length).append(" arguments.  Event subscriber methods must require a single argument.").toString());
                    }
                    MethodIdentifier methodIdentifier = new MethodIdentifier(method);
                    if (!newHashMap.containsKey(methodIdentifier)) {
                        newHashMap.put(methodIdentifier, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(newHashMap.values());
    }

    private static EventSubscriber makeSubscriber(Object obj, Method method) {
        if (methodIsDeclaredThreadSafe(method)) {
            return new EventSubscriber(obj, method);
        }
        return new SynchronizedEventSubscriber(obj, method);
    }

    private static boolean methodIsDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }
}
