package org.slf4j.helpers;

public final class Util {
    private static final ClassContextSecurityManager SECURITY_MANAGER = new ClassContextSecurityManager();

    private static final class ClassContextSecurityManager extends SecurityManager {
        private ClassContextSecurityManager() {
        }

        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    private Util() {
    }

    public static Class<?> getCallingClass() {
        Class[] classContext = SECURITY_MANAGER.getClassContext();
        String name = Util.class.getName();
        int i = 0;
        while (i < classContext.length && !name.equals(classContext[i].getName())) {
            i++;
        }
        if (i < classContext.length && i + 2 < classContext.length) {
            return classContext[i + 2];
        }
        throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
    }

    public static final void report(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void report(String str) {
        System.err.println("SLF4J: " + str);
    }
}
