package com.j256.ormlite.dao;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ReferenceObjectCache implements ObjectCache {
    private final ConcurrentHashMap<Class<?>, Map<Object, Reference<Object>>> classMaps = new ConcurrentHashMap();
    private final boolean useWeak;

    public ReferenceObjectCache(boolean z) {
        this.useWeak = z;
    }

    public static ReferenceObjectCache makeWeakCache() {
        return new ReferenceObjectCache(true);
    }

    public static ReferenceObjectCache makeSoftCache() {
        return new ReferenceObjectCache(false);
    }

    public synchronized <T> void registerClass(Class<T> cls) {
        if (((Map) this.classMaps.get(cls)) == null) {
            this.classMaps.put(cls, new ConcurrentHashMap());
        }
    }

    public <T, ID> T get(Class<T> cls, ID id) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass == null) {
            return null;
        }
        Reference reference = (Reference) mapForClass.get(id);
        if (reference == null) {
            return null;
        }
        T t = reference.get();
        if (t != null) {
            return t;
        }
        mapForClass.remove(id);
        return null;
    }

    public <T, ID> void put(Class<T> cls, ID id, T t) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass == null) {
            return;
        }
        if (this.useWeak) {
            mapForClass.put(id, new WeakReference(t));
        } else {
            mapForClass.put(id, new SoftReference(t));
        }
    }

    public <T> void clear(Class<T> cls) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass != null) {
            mapForClass.clear();
        }
    }

    public void clearAll() {
        for (Map clear : this.classMaps.values()) {
            clear.clear();
        }
    }

    public <T, ID> void remove(Class<T> cls, ID id) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass != null) {
            mapForClass.remove(id);
        }
    }

    public <T, ID> T updateId(Class<T> cls, ID id, ID id2) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass == null) {
            return null;
        }
        Reference reference = (Reference) mapForClass.remove(id);
        if (reference == null) {
            return null;
        }
        mapForClass.put(id2, reference);
        return reference.get();
    }

    public <T> int size(Class<T> cls) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass == null) {
            return 0;
        }
        return mapForClass.size();
    }

    public int sizeAll() {
        int i = 0;
        for (Map size : this.classMaps.values()) {
            i = size.size() + i;
        }
        return i;
    }

    public <T> void cleanNullReferences(Class<T> cls) {
        Map mapForClass = getMapForClass(cls);
        if (mapForClass != null) {
            cleanMap(mapForClass);
        }
    }

    public <T> void cleanNullReferencesAll() {
        for (Map cleanMap : this.classMaps.values()) {
            cleanMap(cleanMap);
        }
    }

    private void cleanMap(Map<Object, Reference<Object>> map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (((Reference) ((Entry) it.next()).getValue()).get() == null) {
                it.remove();
            }
        }
    }

    private Map<Object, Reference<Object>> getMapForClass(Class<?> cls) {
        Map<Object, Reference<Object>> map = (Map) this.classMaps.get(cls);
        if (map == null) {
            return null;
        }
        return map;
    }
}
