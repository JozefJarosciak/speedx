package com.alibaba.fastjson.serializer;

import java.util.HashSet;
import java.util.Set;

public class SimplePropertyPreFilter implements PropertyPreFilter {
    private final Class<?> clazz;
    private final Set<String> excludes;
    private final Set<String> includes;

    public SimplePropertyPreFilter(String... strArr) {
        this(null, strArr);
    }

    public SimplePropertyPreFilter(Class<?> cls, String... strArr) {
        this.includes = new HashSet();
        this.excludes = new HashSet();
        this.clazz = cls;
        for (Object obj : strArr) {
            if (obj != null) {
                this.includes.add(obj);
            }
        }
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public Set<String> getIncludes() {
        return this.includes;
    }

    public Set<String> getExcludes() {
        return this.excludes;
    }

    public boolean apply(JSONSerializer jSONSerializer, Object obj, String str) {
        if (obj == null) {
            return true;
        }
        if (this.clazz != null && !this.clazz.isInstance(obj)) {
            return true;
        }
        if (this.excludes.contains(str)) {
            return false;
        }
        if (this.includes.size() == 0 || this.includes.contains(str)) {
            return true;
        }
        return false;
    }
}
