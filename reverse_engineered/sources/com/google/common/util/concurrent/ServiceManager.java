package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap$Builder;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSetMultimap$Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Monitor.Guard;
import com.google.common.util.concurrent.Service.State;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public final class ServiceManager {
    private static final Callback<Listener> HEALTHY_CALLBACK = new Callback<Listener>("healthy()") {
        void call(Listener listener) {
            listener.healthy();
        }
    };
    private static final Callback<Listener> STOPPED_CALLBACK = new Callback<Listener>("stopped()") {
        void call(Listener listener) {
            listener.stopped();
        }
    };
    private static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
    private final ImmutableList<Service> services;
    private final ServiceManagerState state;

    private static final class EmptyServiceManagerWarning extends Throwable {
        private EmptyServiceManagerWarning() {
        }
    }

    @Beta
    public static abstract class Listener {
        public void healthy() {
        }

        public void stopped() {
        }

        public void failure(Service service) {
        }
    }

    private static final class NoOpService extends AbstractService {
        private NoOpService() {
        }

        protected void doStart() {
            notifyStarted();
        }

        protected void doStop() {
            notifyStopped();
        }
    }

    private static final class ServiceListener extends com.google.common.util.concurrent.Service.Listener {
        final Service service;
        final WeakReference<ServiceManagerState> state;

        ServiceListener(Service service, WeakReference<ServiceManagerState> weakReference) {
            this.service = service;
            this.state = weakReference;
        }

        public void starting() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, State.NEW, State.STARTING);
                if (!(this.service instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.service);
                }
            }
        }

        public void running() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, State.STARTING, State.RUNNING);
            }
        }

        public void stopping(State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, state, State.STOPPING);
            }
        }

        public void terminated(State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.state.get();
            if (serviceManagerState != null) {
                if (!(this.service instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.service, state});
                }
                serviceManagerState.transitionService(this.service, state, State.TERMINATED);
            }
        }

        public void failed(State state, Throwable th) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.state.get();
            if (serviceManagerState != null) {
                if (!(this.service instanceof NoOpService)) {
                    Logger access$200 = ServiceManager.logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(String.valueOf(this.service));
                    String valueOf2 = String.valueOf(String.valueOf(state));
                    access$200.log(level, new StringBuilder((valueOf.length() + 34) + valueOf2.length()).append("Service ").append(valueOf).append(" has failed in the ").append(valueOf2).append(" state.").toString(), th);
                }
                serviceManagerState.transitionService(this.service, state, State.FAILED);
            }
        }
    }

    private static final class ServiceManagerState {
        final Guard awaitHealthGuard = new Guard(this.monitor) {
            public boolean isSatisfied() {
                return ServiceManagerState.this.states.count(State.RUNNING) == ServiceManagerState.this.numberOfServices || ServiceManagerState.this.states.contains(State.STOPPING) || ServiceManagerState.this.states.contains(State.TERMINATED) || ServiceManagerState.this.states.contains(State.FAILED);
            }
        };
        final List<ListenerCallQueue<Listener>> listeners = Collections.synchronizedList(new ArrayList());
        final Monitor monitor = new Monitor();
        final int numberOfServices;
        boolean ready;
        final SetMultimap<State, Service> servicesByState = Multimaps.newSetMultimap(new EnumMap(State.class), new C39021());
        final Map<Service, Stopwatch> startupTimers = Maps.newIdentityHashMap();
        final Multiset<State> states = this.servicesByState.keys();
        final Guard stoppedGuard = new Guard(this.monitor) {
            public boolean isSatisfied() {
                return ServiceManagerState.this.states.count(State.TERMINATED) + ServiceManagerState.this.states.count(State.FAILED) == ServiceManagerState.this.numberOfServices;
            }
        };
        boolean transitioned;

        /* renamed from: com.google.common.util.concurrent.ServiceManager$ServiceManagerState$1 */
        class C39021 implements Supplier<Set<Service>> {
            C39021() {
            }

            public Set<Service> get() {
                return Sets.newLinkedHashSet();
            }
        }

        /* renamed from: com.google.common.util.concurrent.ServiceManager$ServiceManagerState$4 */
        class C39054 implements Function<Entry<Service, Long>, Long> {
            C39054() {
            }

            public Long apply(Entry<Service, Long> entry) {
                return (Long) entry.getValue();
            }
        }

        ServiceManagerState(ImmutableCollection<Service> immutableCollection) {
            this.numberOfServices = immutableCollection.size();
            this.servicesByState.putAll(State.NEW, immutableCollection);
        }

        void tryStartTiming(Service service) {
            this.monitor.enter();
            try {
                if (((Stopwatch) this.startupTimers.get(service)) == null) {
                    this.startupTimers.put(service, Stopwatch.createStarted());
                }
                this.monitor.leave();
            } catch (Throwable th) {
                this.monitor.leave();
            }
        }

        void markReady() {
            this.monitor.enter();
            try {
                if (this.transitioned) {
                    List newArrayList = Lists.newArrayList();
                    Iterator it = servicesByState().values().iterator();
                    while (it.hasNext()) {
                        Service service = (Service) it.next();
                        if (service.state() != State.NEW) {
                            newArrayList.add(service);
                        }
                    }
                    String valueOf = String.valueOf(String.valueOf("Services started transitioning asynchronously before the ServiceManager was constructed: "));
                    String valueOf2 = String.valueOf(String.valueOf(newArrayList));
                    throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 0) + valueOf2.length()).append(valueOf).append(valueOf2).toString());
                }
                this.ready = true;
            } finally {
                this.monitor.leave();
            }
        }

        void addListener(Listener listener, Executor executor) {
            Preconditions.checkNotNull(listener, "listener");
            Preconditions.checkNotNull(executor, "executor");
            this.monitor.enter();
            try {
                if (!this.stoppedGuard.isSatisfied()) {
                    this.listeners.add(new ListenerCallQueue(listener, executor));
                }
                this.monitor.leave();
            } catch (Throwable th) {
                this.monitor.leave();
            }
        }

        void awaitHealthy() {
            this.monitor.enterWhenUninterruptibly(this.awaitHealthGuard);
            try {
                checkHealthy();
            } finally {
                this.monitor.leave();
            }
        }

        void awaitHealthy(long j, TimeUnit timeUnit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (this.monitor.waitForUninterruptibly(this.awaitHealthGuard, j, timeUnit)) {
                    checkHealthy();
                    return;
                }
                String valueOf = String.valueOf(String.valueOf("Timeout waiting for the services to become healthy. The following services have not started: "));
                String valueOf2 = String.valueOf(String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.in(ImmutableSet.of(State.NEW, State.STARTING)))));
                throw new TimeoutException(new StringBuilder((valueOf.length() + 0) + valueOf2.length()).append(valueOf).append(valueOf2).toString());
            } finally {
                this.monitor.leave();
            }
        }

        void awaitStopped() {
            this.monitor.enterWhenUninterruptibly(this.stoppedGuard);
            this.monitor.leave();
        }

        void awaitStopped(long j, TimeUnit timeUnit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (!this.monitor.waitForUninterruptibly(this.stoppedGuard, j, timeUnit)) {
                    String valueOf = String.valueOf(String.valueOf("Timeout waiting for the services to stop. The following services have not stopped: "));
                    String valueOf2 = String.valueOf(String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.in(ImmutableSet.of(State.TERMINATED, State.FAILED))))));
                    throw new TimeoutException(new StringBuilder((valueOf.length() + 0) + valueOf2.length()).append(valueOf).append(valueOf2).toString());
                }
            } finally {
                this.monitor.leave();
            }
        }

        ImmutableMultimap<State, Service> servicesByState() {
            ImmutableSetMultimap$Builder builder = ImmutableSetMultimap.builder();
            this.monitor.enter();
            try {
                for (Entry entry : this.servicesByState.entries()) {
                    if (!(entry.getValue() instanceof NoOpService)) {
                        builder.put(entry.getKey(), entry.getValue());
                    }
                }
                return builder.build();
            } finally {
                builder = this.monitor;
                builder.leave();
            }
        }

        ImmutableMap<Service, Long> startupTimes() {
            this.monitor.enter();
            try {
                List<Entry> newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.startupTimers.size());
                for (Entry entry : this.startupTimers.entrySet()) {
                    Service service = (Service) entry.getKey();
                    Stopwatch stopwatch = (Stopwatch) entry.getValue();
                    if (!(stopwatch.isRunning() || (service instanceof NoOpService))) {
                        newArrayListWithCapacity.add(Maps.immutableEntry(service, Long.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                Collections.sort(newArrayListWithCapacity, Ordering.natural().onResultOf(new C39054()));
                ImmutableMap$Builder builder = ImmutableMap.builder();
                for (Entry entry2 : newArrayListWithCapacity) {
                    builder.put(entry2);
                }
                return builder.build();
            } finally {
                this.monitor.leave();
            }
        }

        void transitionService(Service service, State state, State state2) {
            boolean z = true;
            Preconditions.checkNotNull(service);
            if (state == state2) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.monitor.enter();
            try {
                this.transitioned = true;
                if (this.ready) {
                    Preconditions.checkState(this.servicesByState.remove(state, service), "Service %s not at the expected location in the state map %s", service, state);
                    Preconditions.checkState(this.servicesByState.put(state2, service), "Service %s in the state map unexpectedly at %s", service, state2);
                    Stopwatch stopwatch = (Stopwatch) this.startupTimers.get(service);
                    if (stopwatch == null) {
                        stopwatch = Stopwatch.createStarted();
                        this.startupTimers.put(service, stopwatch);
                    }
                    if (state2.compareTo(State.RUNNING) >= 0 && stopwatch.isRunning()) {
                        stopwatch.stop();
                        if (!(service instanceof NoOpService)) {
                            ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                        }
                    }
                    if (state2 == State.FAILED) {
                        fireFailedListeners(service);
                    }
                    if (this.states.count(State.RUNNING) == this.numberOfServices) {
                        fireHealthyListeners();
                    } else if (this.states.count(State.TERMINATED) + this.states.count(State.FAILED) == this.numberOfServices) {
                        fireStoppedListeners();
                    }
                    this.monitor.leave();
                    executeListeners();
                }
            } finally {
                this.monitor.leave();
                executeListeners();
            }
        }

        void fireStoppedListeners() {
            ServiceManager.STOPPED_CALLBACK.enqueueOn(this.listeners);
        }

        void fireHealthyListeners() {
            ServiceManager.HEALTHY_CALLBACK.enqueueOn(this.listeners);
        }

        void fireFailedListeners(final Service service) {
            String valueOf = String.valueOf(String.valueOf(service));
            new Callback<Listener>(new StringBuilder(valueOf.length() + 18).append("failed({service=").append(valueOf).append("})").toString()) {
                void call(Listener listener) {
                    listener.failure(service);
                }
            }.enqueueOn(this.listeners);
        }

        void executeListeners() {
            boolean z;
            int i = 0;
            if (this.monitor.isOccupiedByCurrentThread()) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkState(z, "It is incorrect to execute listeners with the monitor held.");
            while (i < this.listeners.size()) {
                ((ListenerCallQueue) this.listeners.get(i)).execute();
                i++;
            }
        }

        void checkHealthy() {
            if (this.states.count(State.RUNNING) != this.numberOfServices) {
                String valueOf = String.valueOf(String.valueOf(Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.equalTo(State.RUNNING)))));
                throw new IllegalStateException(new StringBuilder(valueOf.length() + 79).append("Expected to be healthy after starting. The following services are not running: ").append(valueOf).toString());
            }
        }
    }

    public ServiceManager(Iterable<? extends Service> iterable) {
        ImmutableCollection copyOf = ImmutableList.copyOf(iterable);
        if (copyOf.isEmpty()) {
            logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", new EmptyServiceManagerWarning());
            copyOf = ImmutableList.of(new NoOpService());
        }
        this.state = new ServiceManagerState(copyOf);
        this.services = copyOf;
        WeakReference weakReference = new WeakReference(this.state);
        Iterator it = copyOf.iterator();
        while (it.hasNext()) {
            Service service = (Service) it.next();
            service.addListener(new ServiceListener(service, weakReference), MoreExecutors.directExecutor());
            Preconditions.checkArgument(service.state() == State.NEW, "Can only manage NEW services, %s", service);
        }
        this.state.markReady();
    }

    public void addListener(Listener listener, Executor executor) {
        this.state.addListener(listener, executor);
    }

    public void addListener(Listener listener) {
        this.state.addListener(listener, MoreExecutors.directExecutor());
    }

    public ServiceManager startAsync() {
        Iterator it = this.services.iterator();
        while (it.hasNext()) {
            Preconditions.checkState(((Service) it.next()).state() == State.NEW, "Service %s is %s, cannot start it.", (Service) it.next(), ((Service) it.next()).state());
        }
        Iterator it2 = this.services.iterator();
        while (it2.hasNext()) {
            Service service = (Service) it2.next();
            try {
                this.state.tryStartTiming(service);
                service.startAsync();
            } catch (Throwable e) {
                Logger logger = logger;
                Level level = Level.WARNING;
                String valueOf = String.valueOf(String.valueOf(service));
                logger.log(level, new StringBuilder(valueOf.length() + 24).append("Unable to start Service ").append(valueOf).toString(), e);
            }
        }
        return this;
    }

    public void awaitHealthy() {
        this.state.awaitHealthy();
    }

    public void awaitHealthy(long j, TimeUnit timeUnit) throws TimeoutException {
        this.state.awaitHealthy(j, timeUnit);
    }

    public ServiceManager stopAsync() {
        Iterator it = this.services.iterator();
        while (it.hasNext()) {
            ((Service) it.next()).stopAsync();
        }
        return this;
    }

    public void awaitStopped() {
        this.state.awaitStopped();
    }

    public void awaitStopped(long j, TimeUnit timeUnit) throws TimeoutException {
        this.state.awaitStopped(j, timeUnit);
    }

    public boolean isHealthy() {
        Iterator it = this.services.iterator();
        while (it.hasNext()) {
            if (!((Service) it.next()).isRunning()) {
                return false;
            }
        }
        return true;
    }

    public ImmutableMultimap<State, Service> servicesByState() {
        return this.state.servicesByState();
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.state.startupTimes();
    }

    public String toString() {
        return MoreObjects.toStringHelper(ServiceManager.class).add("services", Collections2.filter(this.services, Predicates.not(Predicates.instanceOf(NoOpService.class)))).toString();
    }
}
