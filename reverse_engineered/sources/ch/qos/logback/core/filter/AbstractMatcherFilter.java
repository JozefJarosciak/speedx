package ch.qos.logback.core.filter;

import ch.qos.logback.core.spi.FilterReply;

public abstract class AbstractMatcherFilter<E> extends Filter<E> {
    protected FilterReply onMatch = FilterReply.NEUTRAL;
    protected FilterReply onMismatch = FilterReply.NEUTRAL;

    public final FilterReply getOnMatch() {
        return this.onMatch;
    }

    public final FilterReply getOnMismatch() {
        return this.onMismatch;
    }

    public final void setOnMatch(FilterReply filterReply) {
        this.onMatch = filterReply;
    }

    public final void setOnMismatch(FilterReply filterReply) {
        this.onMismatch = filterReply;
    }
}
