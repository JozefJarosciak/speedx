package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.util.ContextUtil;
import ch.qos.logback.core.util.Duration;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class ConfigurationAction extends Action {
    static final String DEBUG_SYSTEM_PROPERTY_KEY = "logback.debug";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    static final String SCAN_ATTR = "scan";
    static final String SCAN_PERIOD_ATTR = "scanPeriod";

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        String systemProperty = OptionHelper.getSystemProperty(DEBUG_SYSTEM_PROPERTY_KEY);
        if (systemProperty == null) {
            systemProperty = interpretationContext.subst(attributes.getValue(INTERNAL_DEBUG_ATTR));
        }
        if (OptionHelper.isEmpty(systemProperty) || systemProperty.equalsIgnoreCase("false") || systemProperty.equalsIgnoreCase("null")) {
            addInfo("debug attribute not set");
        } else {
            OnConsoleStatusListener.addNewInstanceToContext(this.context);
        }
        processScanAttrib(interpretationContext, attributes);
        new ContextUtil(this.context).addHostNameAsProperty();
        interpretationContext.pushObject(getContext());
    }

    public void end(InterpretationContext interpretationContext, String str) {
        addInfo("End of configuration.");
        interpretationContext.popObject();
    }

    String getSystemProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException e) {
            return null;
        }
    }

    void processScanAttrib(InterpretationContext interpretationContext, Attributes attributes) {
        String subst = interpretationContext.subst(attributes.getValue(SCAN_ATTR));
        if (!OptionHelper.isEmpty(subst) && !"false".equalsIgnoreCase(subst)) {
            TurboFilter reconfigureOnChangeFilter = new ReconfigureOnChangeFilter();
            reconfigureOnChangeFilter.setContext(this.context);
            String subst2 = interpretationContext.subst(attributes.getValue(SCAN_PERIOD_ATTR));
            if (!OptionHelper.isEmpty(subst2)) {
                try {
                    Duration valueOf = Duration.valueOf(subst2);
                    reconfigureOnChangeFilter.setRefreshPeriod(valueOf.getMilliseconds());
                    addInfo("Setting ReconfigureOnChangeFilter scanning period to " + valueOf);
                } catch (Throwable e) {
                    addError("Error while converting [" + subst + "] to long", e);
                }
            }
            reconfigureOnChangeFilter.start();
            LoggerContext loggerContext = (LoggerContext) this.context;
            addInfo("Adding ReconfigureOnChangeFilter as a turbo filter");
            loggerContext.addTurboFilter(reconfigureOnChangeFilter);
        }
    }
}
