package ch.qos.logback.core;

import ch.qos.logback.core.spi.LifeCycle;
import java.util.HashSet;
import java.util.Set;

public class LifeCycleManager {
    private final Set<LifeCycle> components = new HashSet();

    public void register(LifeCycle lifeCycle) {
        this.components.add(lifeCycle);
    }

    public void reset() {
        for (LifeCycle lifeCycle : this.components) {
            if (lifeCycle.isStarted()) {
                lifeCycle.stop();
            }
        }
        this.components.clear();
    }
}
