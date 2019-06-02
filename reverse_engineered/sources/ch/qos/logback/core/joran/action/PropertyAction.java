package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.action.ActionUtil.Scope;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.xml.sax.Attributes;

public class PropertyAction extends Action {
    static String INVALID_ATTRIBUTES = "In <property> element, either the \"file\" attribute alone, or the \"resource\" element alone, or both the \"name\" and \"value\" attributes must be set.";
    static final String RESOURCE_ATTRIBUTE = "resource";

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) {
        if ("substitutionProperty".equals(str)) {
            addWarn("[substitutionProperty] element has been deprecated. Please use the [property] element instead.");
        }
        String value = attributes.getValue("name");
        String value2 = attributes.getValue("value");
        Scope stringToScope = ActionUtil.stringToScope(attributes.getValue(Action.SCOPE_ATTRIBUTE));
        if (checkFileAttributeSanity(attributes)) {
            value2 = interpretationContext.subst(attributes.getValue(Action.FILE_ATTRIBUTE));
            try {
                loadAndSetProperties(interpretationContext, new FileInputStream(value2), stringToScope);
            } catch (FileNotFoundException e) {
                addError("Could not find properties file [" + value2 + "].");
            } catch (Throwable e2) {
                addError("Could not read properties file [" + value2 + "].", e2);
            }
        } else if (checkResourceAttributeSanity(attributes)) {
            value2 = interpretationContext.subst(attributes.getValue(RESOURCE_ATTRIBUTE));
            URL resourceBySelfClassLoader = Loader.getResourceBySelfClassLoader(value2);
            if (resourceBySelfClassLoader == null) {
                addError("Could not find resource [" + value2 + "].");
                return;
            }
            try {
                loadAndSetProperties(interpretationContext, resourceBySelfClassLoader.openStream(), stringToScope);
            } catch (Throwable e22) {
                addError("Could not read resource file [" + value2 + "].", e22);
            }
        } else if (checkValueNameAttributesSanity(attributes)) {
            ActionUtil.setProperty(interpretationContext, value, interpretationContext.subst(RegularEscapeUtil.basicEscape(value2).trim()), stringToScope);
        } else {
            addError(INVALID_ATTRIBUTES);
        }
    }

    boolean checkFileAttributeSanity(Attributes attributes) {
        return !OptionHelper.isEmpty(attributes.getValue(Action.FILE_ATTRIBUTE)) && OptionHelper.isEmpty(attributes.getValue("name")) && OptionHelper.isEmpty(attributes.getValue("value")) && OptionHelper.isEmpty(attributes.getValue(RESOURCE_ATTRIBUTE));
    }

    boolean checkResourceAttributeSanity(Attributes attributes) {
        return !OptionHelper.isEmpty(attributes.getValue(RESOURCE_ATTRIBUTE)) && OptionHelper.isEmpty(attributes.getValue("name")) && OptionHelper.isEmpty(attributes.getValue("value")) && OptionHelper.isEmpty(attributes.getValue(Action.FILE_ATTRIBUTE));
    }

    boolean checkValueNameAttributesSanity(Attributes attributes) {
        return !OptionHelper.isEmpty(attributes.getValue("name")) && !OptionHelper.isEmpty(attributes.getValue("value")) && OptionHelper.isEmpty(attributes.getValue(Action.FILE_ATTRIBUTE)) && OptionHelper.isEmpty(attributes.getValue(RESOURCE_ATTRIBUTE));
    }

    public void end(InterpretationContext interpretationContext, String str) {
    }

    public void finish(InterpretationContext interpretationContext) {
    }

    void loadAndSetProperties(InterpretationContext interpretationContext, InputStream inputStream, Scope scope) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        ActionUtil.setProperties(interpretationContext, properties, scope);
    }
}
