package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache.SimpleStatsCounter;
import com.google.common.cache.AbstractCache.StatsCounter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
public final class CacheBuilder<K, V> {
    static final Supplier<StatsCounter> CACHE_STATS_COUNTER = new C35062();
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_REFRESH_NANOS = 0;
    static final CacheStats EMPTY_STATS = new CacheStats(0, 0, 0, 0, 0, 0);
    static final Supplier<? extends StatsCounter> NULL_STATS_COUNTER = Suppliers.ofInstance(new C35051());
    static final Ticker NULL_TICKER = new C35073();
    static final int UNSET_INT = -1;
    private static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());
    int concurrencyLevel = -1;
    long expireAfterAccessNanos = -1;
    long expireAfterWriteNanos = -1;
    int initialCapacity = -1;
    Equivalence<Object> keyEquivalence;
    Strength keyStrength;
    long maximumSize = -1;
    long maximumWeight = -1;
    long refreshNanos = -1;
    RemovalListener<? super K, ? super V> removalListener;
    Supplier<? extends StatsCounter> statsCounterSupplier = NULL_STATS_COUNTER;
    boolean strictParsing = true;
    Ticker ticker;
    Equivalence<Object> valueEquivalence;
    Strength valueStrength;
    Weigher<? super K, ? super V> weigher;

    /* renamed from: com.google.common.cache.CacheBuilder$1 */
    static class C35051 implements StatsCounter {
        C35051() {
        }

        public void recordHits(int i) {
        }

        public void recordMisses(int i) {
        }

        public void recordLoadSuccess(long j) {
        }

        public void recordLoadException(long j) {
        }

        public void recordEviction() {
        }

        public CacheStats snapshot() {
            return CacheBuilder.EMPTY_STATS;
        }
    }

    /* renamed from: com.google.common.cache.CacheBuilder$2 */
    static class C35062 implements Supplier<StatsCounter> {
        C35062() {
        }

        public StatsCounter get() {
            return new SimpleStatsCounter();
        }
    }

    /* renamed from: com.google.common.cache.CacheBuilder$3 */
    static class C35073 extends Ticker {
        C35073() {
        }

        public long read() {
            return 0;
        }
    }

    enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    CacheBuilder() {
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder();
    }

    @GwtIncompatible("To be supported")
    @Beta
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec cacheBuilderSpec) {
        return cacheBuilderSpec.toCacheBuilder().lenientParsing();
    }

    @GwtIncompatible("To be supported")
    @Beta
    public static CacheBuilder<Object, Object> from(String str) {
        return from(CacheBuilderSpec.parse(str));
    }

    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> lenientParsing() {
        this.strictParsing = false;
        return this;
    }

    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> keyEquivalence(Equivalence<Object> equivalence) {
        boolean z;
        if (this.keyEquivalence == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "key equivalence was already set to %s", this.keyEquivalence);
        this.keyEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    Equivalence<Object> getKeyEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> valueEquivalence(Equivalence<Object> equivalence) {
        boolean z;
        if (this.valueEquivalence == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "value equivalence was already set to %s", this.valueEquivalence);
        this.valueEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    Equivalence<Object> getValueEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.valueEquivalence, getValueStrength().defaultEquivalence());
    }

    public CacheBuilder<K, V> initialCapacity(int i) {
        boolean z;
        boolean z2 = true;
        if (this.initialCapacity == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "initial capacity was already set to %s", Integer.valueOf(this.initialCapacity));
        if (i < 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.initialCapacity = i;
        return this;
    }

    int getInitialCapacity() {
        return this.initialCapacity == -1 ? 16 : this.initialCapacity;
    }

    public CacheBuilder<K, V> concurrencyLevel(int i) {
        boolean z;
        boolean z2 = true;
        if (this.concurrencyLevel == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "concurrency level was already set to %s", Integer.valueOf(this.concurrencyLevel));
        if (i <= 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.concurrencyLevel = i;
        return this;
    }

    int getConcurrencyLevel() {
        return this.concurrencyLevel == -1 ? 4 : this.concurrencyLevel;
    }

    public CacheBuilder<K, V> maximumSize(long j) {
        boolean z;
        boolean z2 = true;
        Preconditions.checkState(this.maximumSize == -1, "maximum size was already set to %s", Long.valueOf(this.maximumSize));
        if (this.maximumWeight == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "maximum weight was already set to %s", Long.valueOf(this.maximumWeight));
        if (this.weigher == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "maximum size can not be combined with weigher");
        if (j < 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "maximum size must not be negative");
        this.maximumSize = j;
        return this;
    }

    @GwtIncompatible("To be supported")
    public CacheBuilder<K, V> maximumWeight(long j) {
        boolean z;
        boolean z2 = true;
        Preconditions.checkState(this.maximumWeight == -1, "maximum weight was already set to %s", Long.valueOf(this.maximumWeight));
        if (this.maximumSize == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "maximum size was already set to %s", Long.valueOf(this.maximumSize));
        this.maximumWeight = j;
        if (j < 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "maximum weight must not be negative");
        return this;
    }

    @GwtIncompatible("To be supported")
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        Preconditions.checkState(this.weigher == null);
        if (this.strictParsing) {
            boolean z;
            if (this.maximumSize == -1) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "weigher can not be combined with maximum size", Long.valueOf(this.maximumSize));
        }
        this.weigher = (Weigher) Preconditions.checkNotNull(weigher);
        return this;
    }

    long getMaximumWeight() {
        if (this.expireAfterWriteNanos == 0 || this.expireAfterAccessNanos == 0) {
            return 0;
        }
        return this.weigher == null ? this.maximumSize : this.maximumWeight;
    }

    <K1 extends K, V1 extends V> Weigher<K1, V1> getWeigher() {
        return (Weigher) MoreObjects.firstNonNull(this.weigher, OneWeigher.INSTANCE);
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public CacheBuilder<K, V> weakKeys() {
        return setKeyStrength(Strength.WEAK);
    }

    CacheBuilder<K, V> setKeyStrength(Strength strength) {
        boolean z;
        if (this.keyStrength == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Key strength was already set to %s", this.keyStrength);
        this.keyStrength = (Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    Strength getKeyStrength() {
        return (Strength) MoreObjects.firstNonNull(this.keyStrength, Strength.STRONG);
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public CacheBuilder<K, V> weakValues() {
        return setValueStrength(Strength.WEAK);
    }

    @GwtIncompatible("java.lang.ref.SoftReference")
    public CacheBuilder<K, V> softValues() {
        return setValueStrength(Strength.SOFT);
    }

    CacheBuilder<K, V> setValueStrength(Strength strength) {
        boolean z;
        if (this.valueStrength == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Value strength was already set to %s", this.valueStrength);
        this.valueStrength = (Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    Strength getValueStrength() {
        return (Strength) MoreObjects.firstNonNull(this.valueStrength, Strength.STRONG);
    }

    public CacheBuilder<K, V> expireAfterWrite(long j, TimeUnit timeUnit) {
        boolean z;
        if (this.expireAfterWriteNanos == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "expireAfterWrite was already set to %s ns", Long.valueOf(this.expireAfterWriteNanos));
        if (j >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "duration cannot be negative: %s %s", Long.valueOf(j), timeUnit);
        this.expireAfterWriteNanos = timeUnit.toNanos(j);
        return this;
    }

    long getExpireAfterWriteNanos() {
        return this.expireAfterWriteNanos == -1 ? 0 : this.expireAfterWriteNanos;
    }

    public CacheBuilder<K, V> expireAfterAccess(long j, TimeUnit timeUnit) {
        boolean z;
        if (this.expireAfterAccessNanos == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "expireAfterAccess was already set to %s ns", Long.valueOf(this.expireAfterAccessNanos));
        if (j >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "duration cannot be negative: %s %s", Long.valueOf(j), timeUnit);
        this.expireAfterAccessNanos = timeUnit.toNanos(j);
        return this;
    }

    long getExpireAfterAccessNanos() {
        return this.expireAfterAccessNanos == -1 ? 0 : this.expireAfterAccessNanos;
    }

    @GwtIncompatible("To be supported (synchronously).")
    @Beta
    public CacheBuilder<K, V> refreshAfterWrite(long j, TimeUnit timeUnit) {
        boolean z;
        Preconditions.checkNotNull(timeUnit);
        if (this.refreshNanos == -1) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "refresh was already set to %s ns", Long.valueOf(this.refreshNanos));
        if (j > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "duration must be positive: %s %s", Long.valueOf(j), timeUnit);
        this.refreshNanos = timeUnit.toNanos(j);
        return this;
    }

    long getRefreshNanos() {
        return this.refreshNanos == -1 ? 0 : this.refreshNanos;
    }

    public CacheBuilder<K, V> ticker(Ticker ticker) {
        Preconditions.checkState(this.ticker == null);
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker);
        return this;
    }

    Ticker getTicker(boolean z) {
        if (this.ticker != null) {
            return this.ticker;
        }
        return z ? Ticker.systemTicker() : NULL_TICKER;
    }

    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> removalListener) {
        Preconditions.checkState(this.removalListener == null);
        this.removalListener = (RemovalListener) Preconditions.checkNotNull(removalListener);
        return this;
    }

    <K1 extends K, V1 extends V> RemovalListener<K1, V1> getRemovalListener() {
        return (RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }

    public CacheBuilder<K, V> recordStats() {
        this.statsCounterSupplier = CACHE_STATS_COUNTER;
        return this;
    }

    boolean isRecordingStats() {
        return this.statsCounterSupplier == CACHE_STATS_COUNTER;
    }

    Supplier<? extends StatsCounter> getStatsCounterSupplier() {
        return this.statsCounterSupplier;
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> cacheLoader) {
        checkWeightWithWeigher();
        return new LocalLoadingCache(this, cacheLoader);
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalManualCache(this);
    }

    private void checkNonLoadingCache() {
        Preconditions.checkState(this.refreshNanos == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void checkWeightWithWeigher() {
        boolean z = true;
        if (this.weigher == null) {
            if (this.maximumWeight != -1) {
                z = false;
            }
            Preconditions.checkState(z, "maximumWeight requires weigher");
        } else if (this.strictParsing) {
            if (this.maximumWeight == -1) {
                z = false;
            }
            Preconditions.checkState(z, "weigher requires maximumWeight");
        } else if (this.maximumWeight == -1) {
            logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    public String toString() {
        ToStringHelper toStringHelper = MoreObjects.toStringHelper((Object) this);
        if (this.initialCapacity != -1) {
            toStringHelper.add("initialCapacity", this.initialCapacity);
        }
        if (this.concurrencyLevel != -1) {
            toStringHelper.add("concurrencyLevel", this.concurrencyLevel);
        }
        if (this.maximumSize != -1) {
            toStringHelper.add("maximumSize", this.maximumSize);
        }
        if (this.maximumWeight != -1) {
            toStringHelper.add("maximumWeight", this.maximumWeight);
        }
        if (this.expireAfterWriteNanos != -1) {
            toStringHelper.add("expireAfterWrite", this.expireAfterWriteNanos + "ns");
        }
        if (this.expireAfterAccessNanos != -1) {
            toStringHelper.add("expireAfterAccess", this.expireAfterAccessNanos + "ns");
        }
        if (this.keyStrength != null) {
            toStringHelper.add("keyStrength", Ascii.toLowerCase(this.keyStrength.toString()));
        }
        if (this.valueStrength != null) {
            toStringHelper.add("valueStrength", Ascii.toLowerCase(this.valueStrength.toString()));
        }
        if (this.keyEquivalence != null) {
            toStringHelper.addValue((Object) "keyEquivalence");
        }
        if (this.valueEquivalence != null) {
            toStringHelper.addValue((Object) "valueEquivalence");
        }
        if (this.removalListener != null) {
            toStringHelper.addValue((Object) "removalListener");
        }
        return toStringHelper.toString();
    }
}
