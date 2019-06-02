package com.google.common.cache;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Beta
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on((char) CoreConstants.COMMA_CHAR).trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(Strength.WEAK)).put("softValues", new ValueStrengthParser(Strength.SOFT)).put("weakValues", new ValueStrengthParser(Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    @VisibleForTesting
    long accessExpirationDuration;
    @VisibleForTesting
    TimeUnit accessExpirationTimeUnit;
    @VisibleForTesting
    Integer concurrencyLevel;
    @VisibleForTesting
    Integer initialCapacity;
    @VisibleForTesting
    Strength keyStrength;
    @VisibleForTesting
    Long maximumSize;
    @VisibleForTesting
    Long maximumWeight;
    @VisibleForTesting
    Boolean recordStats;
    @VisibleForTesting
    long refreshDuration;
    @VisibleForTesting
    TimeUnit refreshTimeUnit;
    private final String specification;
    @VisibleForTesting
    Strength valueStrength;
    @VisibleForTesting
    long writeExpirationDuration;
    @VisibleForTesting
    TimeUnit writeExpirationTimeUnit;

    private interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2);
    }

    static abstract class DurationParser implements ValueParser {
        protected abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);

        DurationParser() {
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z = (str2 == null || str2.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "value of key %s omitted", str);
            try {
                TimeUnit timeUnit;
                switch (str2.charAt(str2.length() - 1)) {
                    case 'd':
                        timeUnit = TimeUnit.DAYS;
                        break;
                    case 'h':
                        timeUnit = TimeUnit.HOURS;
                        break;
                    case 'm':
                        timeUnit = TimeUnit.MINUTES;
                        break;
                    case AVException.PUSH_MISCONFIGURED /*115*/:
                        timeUnit = TimeUnit.SECONDS;
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[]{str, str2}));
                }
                parseDuration(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{str, str2}));
            }
        }
    }

    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }

        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            cacheBuilderSpec.accessExpirationDuration = j;
            cacheBuilderSpec.accessExpirationTimeUnit = timeUnit;
        }
    }

    static abstract class IntegerParser implements ValueParser {
        protected abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);

        IntegerParser() {
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z = (str2 == null || str2.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "value of key %s omitted", str);
            try {
                parseInteger(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (Throwable e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{str, str2}), e);
            }
        }
    }

    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        protected void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            boolean z;
            if (cacheBuilderSpec.concurrencyLevel == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "concurrency level was already set to ", cacheBuilderSpec.concurrencyLevel);
            cacheBuilderSpec.concurrencyLevel = Integer.valueOf(i);
        }
    }

    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }

        protected void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i) {
            boolean z;
            if (cacheBuilderSpec.initialCapacity == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "initial capacity was already set to ", cacheBuilderSpec.initialCapacity);
            cacheBuilderSpec.initialCapacity = Integer.valueOf(i);
        }
    }

    static class KeyStrengthParser implements ValueParser {
        private final Strength strength;

        public KeyStrengthParser(Strength strength) {
            this.strength = strength;
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z;
            if (str2 == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "key %s does not take values", str);
            if (cacheBuilderSpec.keyStrength == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", str, cacheBuilderSpec.keyStrength);
            cacheBuilderSpec.keyStrength = this.strength;
        }
    }

    static abstract class LongParser implements ValueParser {
        protected abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);

        LongParser() {
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z = (str2 == null || str2.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "value of key %s omitted", str);
            try {
                parseLong(cacheBuilderSpec, Long.parseLong(str2));
            } catch (Throwable e) {
                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{str, str2}), e);
            }
        }
    }

    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }

        protected void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z;
            Preconditions.checkArgument(cacheBuilderSpec.maximumSize == null, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            if (cacheBuilderSpec.maximumWeight == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            cacheBuilderSpec.maximumSize = Long.valueOf(j);
        }
    }

    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        protected void parseLong(CacheBuilderSpec cacheBuilderSpec, long j) {
            boolean z;
            Preconditions.checkArgument(cacheBuilderSpec.maximumWeight == null, "maximum weight was already set to ", cacheBuilderSpec.maximumWeight);
            if (cacheBuilderSpec.maximumSize == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum size was already set to ", cacheBuilderSpec.maximumSize);
            cacheBuilderSpec.maximumWeight = Long.valueOf(j);
        }
    }

    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z;
            boolean z2 = false;
            if (str2 == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "recordStats does not take values");
            if (cacheBuilderSpec.recordStats == null) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "recordStats already set");
            cacheBuilderSpec.recordStats = Boolean.valueOf(true);
        }
    }

    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }

        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.refreshTimeUnit == null, "refreshAfterWrite already set");
            cacheBuilderSpec.refreshDuration = j;
            cacheBuilderSpec.refreshTimeUnit = timeUnit;
        }
    }

    static class ValueStrengthParser implements ValueParser {
        private final Strength strength;

        public ValueStrengthParser(Strength strength) {
            this.strength = strength;
        }

        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            boolean z;
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            if (cacheBuilderSpec.valueStrength == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "%s was already set to %s", str, cacheBuilderSpec.valueStrength);
            cacheBuilderSpec.valueStrength = this.strength;
        }
    }

    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }

        protected void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            cacheBuilderSpec.writeExpirationDuration = j;
            cacheBuilderSpec.writeExpirationTimeUnit = timeUnit;
        }
    }

    private CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String split : KEYS_SPLITTER.split(str)) {
                boolean z;
                boolean z2;
                List copyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(split));
                Preconditions.checkArgument(!copyOf.isEmpty(), "blank key-value pair");
                if (copyOf.size() <= 2) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z, "key-value pair %s with more than one equals sign", split);
                String split2 = (String) copyOf.get(0);
                ValueParser valueParser = (ValueParser) VALUE_PARSERS.get(split2);
                if (valueParser != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "unknown key %s", split2);
                valueParser.parse(cacheBuilderSpec, split2, copyOf.size() == 1 ? null : (String) copyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> newBuilder = CacheBuilder.newBuilder();
        if (this.initialCapacity != null) {
            newBuilder.initialCapacity(this.initialCapacity.intValue());
        }
        if (this.maximumSize != null) {
            newBuilder.maximumSize(this.maximumSize.longValue());
        }
        if (this.maximumWeight != null) {
            newBuilder.maximumWeight(this.maximumWeight.longValue());
        }
        if (this.concurrencyLevel != null) {
            newBuilder.concurrencyLevel(this.concurrencyLevel.intValue());
        }
        if (this.keyStrength != null) {
            switch (this.keyStrength) {
                case WEAK:
                    newBuilder.weakKeys();
                    break;
                default:
                    throw new AssertionError();
            }
        }
        if (this.valueStrength != null) {
            switch (this.valueStrength) {
                case WEAK:
                    newBuilder.weakValues();
                    break;
                case SOFT:
                    newBuilder.softValues();
                    break;
                default:
                    throw new AssertionError();
            }
        }
        if (this.recordStats != null && this.recordStats.booleanValue()) {
            newBuilder.recordStats();
        }
        if (this.writeExpirationTimeUnit != null) {
            newBuilder.expireAfterWrite(this.writeExpirationDuration, this.writeExpirationTimeUnit);
        }
        if (this.accessExpirationTimeUnit != null) {
            newBuilder.expireAfterAccess(this.accessExpirationDuration, this.accessExpirationTimeUnit);
        }
        if (this.refreshTimeUnit != null) {
            newBuilder.refreshAfterWrite(this.refreshDuration, this.refreshTimeUnit);
        }
        return newBuilder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).addValue(toParsableString()).toString();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        if (Objects.equal(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equal(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equal(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equal(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equal(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equal(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equal(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit))) {
            return true;
        }
        return false;
    }

    private static Long durationInNanos(long j, TimeUnit timeUnit) {
        return timeUnit == null ? null : Long.valueOf(timeUnit.toNanos(j));
    }
}
