package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SubstituteLoggerFactory implements ILoggerFactory {
    final ConcurrentMap<String, SubstituteLogger> loggers = new ConcurrentHashMap();

    public Logger getLogger(String str) {
        SubstituteLogger substituteLogger = (SubstituteLogger) this.loggers.get(str);
        if (substituteLogger != null) {
            return substituteLogger;
        }
        SubstituteLogger substituteLogger2 = new SubstituteLogger(str);
        substituteLogger = (SubstituteLogger) this.loggers.putIfAbsent(str, substituteLogger2);
        return substituteLogger != null ? substituteLogger : substituteLogger2;
    }

    public List<String> getLoggerNames() {
        return new ArrayList(this.loggers.keySet());
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList(this.loggers.values());
    }

    public void clear() {
        this.loggers.clear();
    }
}
