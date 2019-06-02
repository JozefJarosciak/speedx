package ch.qos.logback.core.android;

import java.lang.reflect.Method;

public class SystemPropertiesProxy {
    private static final SystemPropertiesProxy SINGLETON = new SystemPropertiesProxy(null);
    private Class<?> SystemProperties;
    private Method getBoolean;
    private Method getString;

    private SystemPropertiesProxy(ClassLoader classLoader) {
        try {
            setClassLoader(classLoader);
        } catch (Exception e) {
        }
    }

    public static SystemPropertiesProxy getInstance() {
        return SINGLETON;
    }

    public String get(String str, String str2) throws IllegalArgumentException {
        if (this.SystemProperties == null || this.getString == null) {
            return null;
        }
        String str3;
        try {
            str3 = (String) this.getString.invoke(this.SystemProperties, new Object[]{str, str2});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            str3 = null;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    public Boolean getBoolean(String str, boolean z) throws IllegalArgumentException {
        if (this.SystemProperties == null || this.getBoolean == null) {
            return Boolean.valueOf(z);
        }
        try {
            return (Boolean) this.getBoolean.invoke(this.SystemProperties, new Object[]{str, Boolean.valueOf(z)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return Boolean.valueOf(z);
        }
    }

    public void setClassLoader(ClassLoader classLoader) throws ClassNotFoundException, SecurityException, NoSuchMethodException {
        if (classLoader == null) {
            classLoader = getClass().getClassLoader();
        }
        this.SystemProperties = classLoader.loadClass("android.os.SystemProperties");
        this.getString = this.SystemProperties.getMethod("get", new Class[]{String.class, String.class});
        this.getBoolean = this.SystemProperties.getMethod("getBoolean", new Class[]{String.class, Boolean.TYPE});
    }
}
