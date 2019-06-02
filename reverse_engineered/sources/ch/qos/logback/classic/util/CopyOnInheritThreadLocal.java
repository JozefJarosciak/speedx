package ch.qos.logback.classic.util;

import java.util.HashMap;

public class CopyOnInheritThreadLocal extends InheritableThreadLocal<HashMap<String, String>> {
    protected HashMap<String, String> childValue(HashMap<String, String> hashMap) {
        return hashMap == null ? null : new HashMap(hashMap);
    }
}
