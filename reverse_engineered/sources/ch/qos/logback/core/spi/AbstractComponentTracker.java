package ch.qos.logback.core.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public abstract class AbstractComponentTracker<C> implements ComponentTracker<C> {
    private static final boolean ACCESS_ORDERED = true;
    public static final long LINGERING_TIMEOUT = 10000;
    public static final long WAIT_BETWEEN_SUCCESSIVE_REMOVAL_ITERATIONS = 1000;
    private RemovalPredicator<C> byExcedent = new C03571();
    private RemovalPredicator<C> byLingering = new C03593();
    private RemovalPredicator<C> byTimeout = new C03582();
    long lastCheck = 0;
    LinkedHashMap<String, Entry<C>> lingerersMap = new LinkedHashMap(16, 0.75f, true);
    LinkedHashMap<String, Entry<C>> liveMap = new LinkedHashMap(32, 0.75f, true);
    protected int maxComponents = Integer.MAX_VALUE;
    protected long timeout = 1800000;

    private interface RemovalPredicator<C> {
        boolean isSlatedForRemoval(Entry<C> entry, long j);
    }

    /* renamed from: ch.qos.logback.core.spi.AbstractComponentTracker$1 */
    class C03571 implements RemovalPredicator<C> {
        C03571() {
        }

        public boolean isSlatedForRemoval(Entry<C> entry, long j) {
            return AbstractComponentTracker.this.liveMap.size() > AbstractComponentTracker.this.maxComponents;
        }
    }

    /* renamed from: ch.qos.logback.core.spi.AbstractComponentTracker$2 */
    class C03582 implements RemovalPredicator<C> {
        C03582() {
        }

        public boolean isSlatedForRemoval(Entry<C> entry, long j) {
            return AbstractComponentTracker.this.isEntryStale(entry, j);
        }
    }

    /* renamed from: ch.qos.logback.core.spi.AbstractComponentTracker$3 */
    class C03593 implements RemovalPredicator<C> {
        C03593() {
        }

        public boolean isSlatedForRemoval(Entry<C> entry, long j) {
            return AbstractComponentTracker.this.isEntryDoneLingering(entry, j);
        }
    }

    private static class Entry<C> {
        C component;
        String key;
        long timestamp;

        Entry(String str, C c, long j) {
            this.key = str;
            this.component = c;
            this.timestamp = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.key == null) {
                if (entry.key != null) {
                    return false;
                }
            } else if (!this.key.equals(entry.key)) {
                return false;
            }
            return this.component == null ? entry.component == null : this.component.equals(entry.component);
        }

        public int hashCode() {
            return this.key.hashCode();
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public String toString() {
            return "(" + this.key + ", " + this.component + ")";
        }
    }

    private void genericStaleComponentRemover(LinkedHashMap<String, Entry<C>> linkedHashMap, long j, RemovalPredicator<C> removalPredicator) {
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) ((java.util.Map.Entry) it.next()).getValue();
            if (removalPredicator.isSlatedForRemoval(entry, j)) {
                it.remove();
                processPriorToRemoval(entry.component);
            } else {
                return;
            }
        }
    }

    private Entry<C> getFromEitherMap(String str) {
        Entry<C> entry = (Entry) this.liveMap.get(str);
        return entry != null ? entry : (Entry) this.lingerersMap.get(str);
    }

    private boolean isEntryDoneLingering(Entry entry, long j) {
        return entry.timestamp + LINGERING_TIMEOUT < j;
    }

    private boolean isEntryStale(Entry<C> entry, long j) {
        return isComponentStale(entry.component) || entry.timestamp + this.timeout < j;
    }

    private boolean isTooSoonForRemovalIteration(long j) {
        if (this.lastCheck + 1000 > j) {
            return true;
        }
        this.lastCheck = j;
        return false;
    }

    private void removeExcedentComponents() {
        genericStaleComponentRemover(this.liveMap, 0, this.byExcedent);
    }

    private void removeStaleComponentsFromLingerersMap(long j) {
        genericStaleComponentRemover(this.lingerersMap, j, this.byLingering);
    }

    private void removeStaleComponentsFromMainMap(long j) {
        genericStaleComponentRemover(this.liveMap, j, this.byTimeout);
    }

    public Collection<C> allComponents() {
        Collection arrayList = new ArrayList();
        for (Entry entry : this.liveMap.values()) {
            arrayList.add(entry.component);
        }
        for (Entry entry2 : this.lingerersMap.values()) {
            arrayList.add(entry2.component);
        }
        return arrayList;
    }

    public Set<String> allKeys() {
        Set hashSet = new HashSet(this.liveMap.keySet());
        hashSet.addAll(this.lingerersMap.keySet());
        return hashSet;
    }

    protected abstract C buildComponent(String str);

    public void endOfLife(String str) {
        Entry entry = (Entry) this.liveMap.remove(str);
        if (entry != null) {
            this.lingerersMap.put(str, entry);
        }
    }

    public synchronized C find(String str) {
        Entry fromEitherMap;
        fromEitherMap = getFromEitherMap(str);
        return fromEitherMap == null ? null : fromEitherMap.component;
    }

    public int getComponentCount() {
        return this.liveMap.size() + this.lingerersMap.size();
    }

    public int getMaxComponents() {
        return this.maxComponents;
    }

    public synchronized C getOrCreate(String str, long j) {
        Entry fromEitherMap;
        fromEitherMap = getFromEitherMap(str);
        if (fromEitherMap == null) {
            fromEitherMap = new Entry(str, buildComponent(str), j);
            this.liveMap.put(str, fromEitherMap);
        } else {
            fromEitherMap.setTimestamp(j);
        }
        return fromEitherMap.component;
    }

    public long getTimeout() {
        return this.timeout;
    }

    protected abstract boolean isComponentStale(C c);

    protected abstract void processPriorToRemoval(C c);

    public synchronized void removeStaleComponents(long j) {
        if (!isTooSoonForRemovalIteration(j)) {
            removeExcedentComponents();
            removeStaleComponentsFromMainMap(j);
            removeStaleComponentsFromLingerersMap(j);
        }
    }

    public void setMaxComponents(int i) {
        this.maxComponents = i;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }
}
