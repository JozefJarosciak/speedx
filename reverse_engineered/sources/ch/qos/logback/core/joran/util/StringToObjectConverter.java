package ch.qos.logback.core.joran.util;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.ContextAware;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;

public class StringToObjectConverter {
    private static final Class<?>[] STING_CLASS_PARAMETER = new Class[]{String.class};

    public static boolean canBeBuiltFromSimpleString(Class<?> cls) {
        Package packageR = cls.getPackage();
        return cls.isPrimitive() ? true : (packageR != null && "java.lang".equals(packageR.getName())) || followsTheValueOfConvention(cls) || cls.isEnum() || isOfTypeCharset(cls);
    }

    public static Object convertArg(ContextAware contextAware, String str, Class<?> cls) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        return String.class.isAssignableFrom(cls) ? trim : Integer.TYPE.isAssignableFrom(cls) ? new Integer(trim) : Long.TYPE.isAssignableFrom(cls) ? new Long(trim) : Float.TYPE.isAssignableFrom(cls) ? new Float(trim) : Double.TYPE.isAssignableFrom(cls) ? new Double(trim) : Boolean.TYPE.isAssignableFrom(cls) ? "true".equalsIgnoreCase(trim) ? Boolean.TRUE : "false".equalsIgnoreCase(trim) ? Boolean.FALSE : null : cls.isEnum() ? convertToEnum(contextAware, trim, cls) : followsTheValueOfConvention(cls) ? convertByValueOfMethod(contextAware, cls, trim) : isOfTypeCharset(cls) ? convertToCharset(contextAware, str) : null;
    }

    private static Object convertByValueOfMethod(ContextAware contextAware, Class<?> cls, String str) {
        Object obj = null;
        try {
            obj = cls.getMethod(CoreConstants.VALUE_OF, STING_CLASS_PARAMETER).invoke(null, new Object[]{str});
        } catch (Exception e) {
            contextAware.addError("Failed to invoke valueOf{} method in class [" + cls.getName() + "] with value [" + str + "]");
        }
        return obj;
    }

    private static Charset convertToCharset(ContextAware contextAware, String str) {
        try {
            return Charset.forName(str);
        } catch (Throwable e) {
            contextAware.addError("Failed to get charset [" + str + "]", e);
            return null;
        }
    }

    private static Object convertToEnum(ContextAware contextAware, String str, Class<? extends Enum> cls) {
        return Enum.valueOf(cls, str);
    }

    private static boolean followsTheValueOfConvention(Class<?> cls) {
        try {
            if (Modifier.isStatic(cls.getMethod(CoreConstants.VALUE_OF, STING_CLASS_PARAMETER).getModifiers())) {
                return true;
            }
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e2) {
        }
        return false;
    }

    private static boolean isOfTypeCharset(Class<?> cls) {
        return Charset.class.isAssignableFrom(cls);
    }

    boolean isBuildableFromSimpleString() {
        return false;
    }
}
