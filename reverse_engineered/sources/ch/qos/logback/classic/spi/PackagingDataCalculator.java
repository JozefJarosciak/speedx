package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;
import java.net.URL;
import java.security.CodeSource;
import java.util.HashMap;

public class PackagingDataCalculator {
    static final StackTraceElementProxy[] STEP_ARRAY_TEMPLATE = new StackTraceElementProxy[0];
    HashMap<String, ClassPackagingData> cache = new HashMap();

    private Class bestEffortLoadClass(ClassLoader classLoader, String str) {
        Class loadClass = loadClass(classLoader, str);
        if (loadClass != null) {
            return loadClass;
        }
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != classLoader) {
            loadClass = loadClass(contextClassLoader, str);
        }
        if (loadClass != null) {
            return loadClass;
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoClassDefFoundError e2) {
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private ClassPackagingData computeBySTEP(StackTraceElementProxy stackTraceElementProxy, ClassLoader classLoader) {
        String className = stackTraceElementProxy.ste.getClassName();
        ClassPackagingData classPackagingData = (ClassPackagingData) this.cache.get(className);
        if (classPackagingData != null) {
            return classPackagingData;
        }
        Class bestEffortLoadClass = bestEffortLoadClass(classLoader, className);
        classPackagingData = new ClassPackagingData(getCodeLocation(bestEffortLoadClass), getImplementationVersion(bestEffortLoadClass), false);
        this.cache.put(className, classPackagingData);
        return classPackagingData;
    }

    private String getCodeLocation(String str, char c) {
        int lastIndexOf = str.lastIndexOf(c);
        return isFolder(lastIndexOf, str) ? str.substring(str.lastIndexOf(c, lastIndexOf - 1) + 1) : lastIndexOf > 0 ? str.substring(lastIndexOf + 1) : null;
    }

    private boolean isFolder(int i, String str) {
        return i != -1 && i + 1 == str.length();
    }

    private Class loadClass(ClassLoader classLoader, String str) {
        Class cls = null;
        if (classLoader != null) {
            try {
                cls = classLoader.loadClass(str);
            } catch (ClassNotFoundException e) {
            } catch (NoClassDefFoundError e2) {
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return cls;
    }

    public void calculate(IThrowableProxy iThrowableProxy) {
        while (iThrowableProxy != null) {
            populateFrames(iThrowableProxy.getStackTraceElementProxyArray());
            IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
            if (suppressed != null) {
                for (IThrowableProxy stackTraceElementProxyArray : suppressed) {
                    populateFrames(stackTraceElementProxyArray.getStackTraceElementProxyArray());
                }
            }
            iThrowableProxy = iThrowableProxy.getCause();
        }
    }

    String getCodeLocation(Class cls) {
        if (cls != null) {
            try {
                CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
                if (codeSource != null) {
                    URL location = codeSource.getLocation();
                    if (location != null) {
                        String url = location.toString();
                        String codeLocation = getCodeLocation(url, '/');
                        return codeLocation != null ? codeLocation : getCodeLocation(url, CoreConstants.ESCAPE_CHAR);
                    }
                }
            } catch (Exception e) {
            }
        }
        return "na";
    }

    String getImplementationVersion(Class cls) {
        if (cls == null) {
            return "na";
        }
        Package packageR = cls.getPackage();
        if (packageR == null) {
            return "na";
        }
        String implementationVersion = packageR.getImplementationVersion();
        return implementationVersion == null ? "na" : implementationVersion;
    }

    void populateFrames(StackTraceElementProxy[] stackTraceElementProxyArr) {
        int findNumberOfCommonFrames = STEUtil.findNumberOfCommonFrames(new Throwable("local stack reference").getStackTrace(), stackTraceElementProxyArr);
        int length = stackTraceElementProxyArr.length - findNumberOfCommonFrames;
        for (int i = 0; i < findNumberOfCommonFrames; i++) {
            StackTraceElementProxy stackTraceElementProxy = stackTraceElementProxyArr[length + i];
            stackTraceElementProxy.setClassPackagingData(computeBySTEP(stackTraceElementProxy, null));
        }
        populateUncommonFrames(findNumberOfCommonFrames, stackTraceElementProxyArr, null);
    }

    void populateUncommonFrames(int i, StackTraceElementProxy[] stackTraceElementProxyArr, ClassLoader classLoader) {
        int length = stackTraceElementProxyArr.length - i;
        for (int i2 = 0; i2 < length; i2++) {
            StackTraceElementProxy stackTraceElementProxy = stackTraceElementProxyArr[i2];
            stackTraceElementProxy.setClassPackagingData(computeBySTEP(stackTraceElementProxy, classLoader));
        }
    }
}
