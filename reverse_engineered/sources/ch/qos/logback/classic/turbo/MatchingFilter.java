package ch.qos.logback.classic.turbo;

import ch.qos.logback.core.spi.FilterReply;

public abstract class MatchingFilter extends TurboFilter {
    protected FilterReply onMatch = FilterReply.NEUTRAL;
    protected FilterReply onMismatch = FilterReply.NEUTRAL;

    public final void setOnMatch(String str) {
        if ("NEUTRAL".equals(str)) {
            this.onMatch = FilterReply.NEUTRAL;
        } else if ("ACCEPT".equals(str)) {
            this.onMatch = FilterReply.ACCEPT;
        } else if ("DENY".equals(str)) {
            this.onMatch = FilterReply.DENY;
        }
    }

    public final void setOnMismatch(String str) {
        if ("NEUTRAL".equals(str)) {
            this.onMismatch = FilterReply.NEUTRAL;
        } else if ("ACCEPT".equals(str)) {
            this.onMismatch = FilterReply.ACCEPT;
        } else if ("DENY".equals(str)) {
            this.onMismatch = FilterReply.DENY;
        }
    }
}
