package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.spi.LifeCycle;
import org.slf4j.Marker;

public abstract class TurboFilter extends ContextAwareBase implements LifeCycle {
    private String name;
    boolean start = false;

    public abstract FilterReply decide(Marker marker, Logger logger, Level level, String str, Object[] objArr, Throwable th);

    public String getName() {
        return this.name;
    }

    public boolean isStarted() {
        return this.start;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void start() {
        this.start = true;
    }

    public void stop() {
        this.start = false;
    }
}
