package com.google.common.util.concurrent;

import java.util.concurrent.TimeUnit;

abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        SmoothBursty(SleepingStopwatch sleepingStopwatch, double d) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d;
        }

        void doSetRate(double d, double d2) {
            double d3 = 0.0d;
            double d4 = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * d;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
                return;
            }
            if (d4 != 0.0d) {
                d3 = (this.storedPermits * this.maxPermits) / d4;
            }
            this.storedPermits = d3;
        }

        long storedPermitsToWaitTime(double d, double d2) {
            return 0;
        }
    }

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double halfPermits;
        private double slope;
        private final long warmupPeriodMicros;

        SmoothWarmingUp(SleepingStopwatch sleepingStopwatch, long j, TimeUnit timeUnit) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j);
        }

        void doSetRate(double d, double d2) {
            double d3 = this.maxPermits;
            this.maxPermits = ((double) this.warmupPeriodMicros) / d2;
            this.halfPermits = this.maxPermits / 2.0d;
            this.slope = ((3.0d * d2) - d2) / this.halfPermits;
            if (d3 == Double.POSITIVE_INFINITY) {
                this.storedPermits = 0.0d;
            } else {
                this.storedPermits = d3 == 0.0d ? this.maxPermits : (this.storedPermits * this.maxPermits) / d3;
            }
        }

        long storedPermitsToWaitTime(double d, double d2) {
            double d3 = d - this.halfPermits;
            long j = 0;
            if (d3 > 0.0d) {
                double min = Math.min(d3, d2);
                j = (long) (((permitsToTime(d3) + permitsToTime(d3 - min)) * min) / 2.0d);
                d2 -= min;
            }
            return (long) (((double) j) + (this.stableIntervalMicros * d2));
        }

        private double permitsToTime(double d) {
            return this.stableIntervalMicros + (this.slope * d);
        }
    }

    abstract void doSetRate(double d, double d2);

    abstract long storedPermitsToWaitTime(double d, double d2);

    private SmoothRateLimiter(SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0;
    }

    final void doSetRate(double d, long j) {
        resync(j);
        double toMicros = ((double) TimeUnit.SECONDS.toMicros(1)) / d;
        this.stableIntervalMicros = toMicros;
        doSetRate(d, toMicros);
    }

    final double doGetRate() {
        return ((double) TimeUnit.SECONDS.toMicros(1)) / this.stableIntervalMicros;
    }

    final long queryEarliestAvailable(long j) {
        return this.nextFreeTicketMicros;
    }

    final long reserveEarliestAvailable(int i, long j) {
        resync(j);
        long j2 = this.nextFreeTicketMicros;
        double min = Math.min((double) i, this.storedPermits);
        double d = ((double) i) - min;
        this.nextFreeTicketMicros = (((long) (d * this.stableIntervalMicros)) + storedPermitsToWaitTime(this.storedPermits, min)) + this.nextFreeTicketMicros;
        this.storedPermits -= min;
        return j2;
    }

    private void resync(long j) {
        if (j > this.nextFreeTicketMicros) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + (((double) (j - this.nextFreeTicketMicros)) / this.stableIntervalMicros));
            this.nextFreeTicketMicros = j;
        }
    }
}
