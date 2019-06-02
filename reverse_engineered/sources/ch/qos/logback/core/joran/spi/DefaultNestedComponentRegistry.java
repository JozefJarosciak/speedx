package ch.qos.logback.core.joran.spi;

import java.util.HashMap;
import java.util.Map;

public class DefaultNestedComponentRegistry {
    Map<HostClassAndPropertyDouble, Class<?>> defaultComponentMap = new HashMap();

    private Class<?> oneShotFind(Class<?> cls, String str) {
        return (Class) this.defaultComponentMap.get(new HostClassAndPropertyDouble(cls, str));
    }

    public void add(Class<?> cls, String str, Class<?> cls2) {
        this.defaultComponentMap.put(new HostClassAndPropertyDouble(cls, str.toLowerCase()), cls2);
    }

    public Class<?> findDefaultComponentType(Class<?> cls, String str) {
        String toLowerCase = str.toLowerCase();
        Class superclass;
        while (superclass != null) {
            Class<?> oneShotFind = oneShotFind(superclass, toLowerCase);
            if (oneShotFind != null) {
                return oneShotFind;
            }
            superclass = superclass.getSuperclass();
        }
        return null;
    }
}
