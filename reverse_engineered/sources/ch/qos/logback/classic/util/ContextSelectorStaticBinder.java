package ch.qos.logback.classic.util;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.selector.DefaultContextSelector;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.lang.reflect.InvocationTargetException;

public class ContextSelectorStaticBinder {
    static ContextSelectorStaticBinder singleton = new ContextSelectorStaticBinder();
    ContextSelector contextSelector;
    Object key;

    static ContextSelector dynamicalContextSelector(LoggerContext loggerContext, String str) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return (ContextSelector) Loader.loadClass(str).getConstructor(new Class[]{LoggerContext.class}).newInstance(new Object[]{loggerContext});
    }

    public static ContextSelectorStaticBinder getSingleton() {
        return singleton;
    }

    public ContextSelector getContextSelector() {
        return this.contextSelector;
    }

    public void init(LoggerContext loggerContext, Object obj) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (this.key == null) {
            this.key = obj;
        } else if (this.key != obj) {
            throw new IllegalAccessException("Only certain classes can access this method.");
        }
        String systemProperty = OptionHelper.getSystemProperty(ClassicConstants.LOGBACK_CONTEXT_SELECTOR);
        if (systemProperty == null) {
            this.contextSelector = new DefaultContextSelector(loggerContext);
        } else if (systemProperty.equals("JNDI")) {
            throw new RuntimeException("JNDI not supported");
        } else {
            this.contextSelector = dynamicalContextSelector(loggerContext, systemProperty);
        }
    }
}
