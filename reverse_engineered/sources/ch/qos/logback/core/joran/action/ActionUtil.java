package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.ContextUtil;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Properties;

public class ActionUtil {

    public enum Scope {
        LOCAL,
        CONTEXT,
        SYSTEM
    }

    public static void setProperties(InterpretationContext interpretationContext, Properties properties, Scope scope) {
        switch (scope) {
            case LOCAL:
                interpretationContext.addSubstitutionProperties(properties);
                return;
            case CONTEXT:
                new ContextUtil(interpretationContext.getContext()).addProperties(properties);
                return;
            case SYSTEM:
                OptionHelper.setSystemProperties(interpretationContext, properties);
                return;
            default:
                return;
        }
    }

    public static void setProperty(InterpretationContext interpretationContext, String str, String str2, Scope scope) {
        switch (scope) {
            case LOCAL:
                interpretationContext.addSubstitutionProperty(str, str2);
                return;
            case CONTEXT:
                interpretationContext.getContext().putProperty(str, str2);
                return;
            case SYSTEM:
                OptionHelper.setSystemProperty(interpretationContext, str, str2);
                return;
            default:
                return;
        }
    }

    public static Scope stringToScope(String str) {
        return Scope.SYSTEM.toString().equalsIgnoreCase(str) ? Scope.SYSTEM : Scope.CONTEXT.toString().equalsIgnoreCase(str) ? Scope.CONTEXT : Scope.LOCAL;
    }
}
