package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public class ASMClassLoader extends ClassLoader {
    private static ProtectionDomain DOMAIN = ((ProtectionDomain) AccessController.doPrivileged(new C07131()));

    /* renamed from: com.alibaba.fastjson.util.ASMClassLoader$1 */
    static class C07131 implements PrivilegedAction<Object> {
        C07131() {
        }

        public Object run() {
            return ASMClassLoader.class.getProtectionDomain();
        }
    }

    public ASMClassLoader() {
        super(getParentClassLoader());
    }

    static ClassLoader getParentClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                contextClassLoader.loadClass(JSON.class.getName());
                return contextClassLoader;
            } catch (ClassNotFoundException e) {
            }
        }
        return JSON.class.getClassLoader();
    }

    public Class<?> defineClassPublic(String str, byte[] bArr, int i, int i2) throws ClassFormatError {
        return defineClass(str, bArr, i, i2, DOMAIN);
    }

    public boolean isExternalClass(Class<?> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            return false;
        }
        ClassLoader parent;
        while (parent != null) {
            if (parent == classLoader) {
                return false;
            }
            parent = parent.getParent();
        }
        return true;
    }
}
