package org.slf4j.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter implements MDCAdapter {
    static boolean IS_JDK14 = isJDK14();
    private InheritableThreadLocal<Map<String, String>> inheritableThreadLocal = new InheritableThreadLocal();

    static boolean isJDK14() {
        try {
            return System.getProperty("java.version").startsWith("1.4");
        } catch (SecurityException e) {
            return false;
        }
    }

    public void put(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map == null) {
            map = Collections.synchronizedMap(new HashMap());
            this.inheritableThreadLocal.set(map);
        }
        map.put(str, str2);
    }

    public String get(String str) {
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map == null || str == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public void remove(String str) {
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map != null) {
            map.remove(str);
        }
    }

    public void clear() {
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map != null) {
            map.clear();
            if (isJDK14()) {
                this.inheritableThreadLocal.set(null);
            } else {
                this.inheritableThreadLocal.remove();
            }
        }
    }

    public Set<String> getKeys() {
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    public Map<String, String> getCopyOfContextMap() {
        Map map = (Map) this.inheritableThreadLocal.get();
        if (map == null) {
            return null;
        }
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap());
        synchronized (map) {
            synchronizedMap.putAll(map);
        }
        return synchronizedMap;
    }

    public void setContextMap(Map<String, String> map) {
        this.inheritableThreadLocal.set(Collections.synchronizedMap(new HashMap(map)));
    }
}
