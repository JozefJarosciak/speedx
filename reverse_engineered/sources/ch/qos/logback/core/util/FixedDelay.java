package ch.qos.logback.core.util;

public class FixedDelay implements DelayStrategy {
    private long nextDelay;
    private final long subsequentDelay;

    public FixedDelay(int i) {
        this((long) i, (long) i);
    }

    public FixedDelay(long j, long j2) {
        String str = new String();
        this.nextDelay = j;
        this.subsequentDelay = j2;
    }

    public long nextDelay() {
        long j = this.nextDelay;
        this.nextDelay = this.subsequentDelay;
        return j;
    }
}
