package ch.qos.logback.classic.joran.action;

import ch.qos.logback.core.joran.action.AbstractIncludeAction;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.JoranException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.UnknownHostException;
import org.xml.sax.Attributes;

public class ConditionalIncludeAction extends AbstractIncludeAction {

    class State {
        private URL url;

        State() {
        }

        URL getUrl() {
            return this.url;
        }

        void setUrl(URL url) {
            this.url = url;
        }
    }

    private URL peekPath(InterpretationContext interpretationContext) {
        if (!interpretationContext.isEmpty()) {
            Object peekObject = interpretationContext.peekObject();
            if (peekObject instanceof State) {
                URL url = ((State) peekObject).getUrl();
                if (url != null) {
                    return url;
                }
            }
        }
        return null;
    }

    private URL pushPath(InterpretationContext interpretationContext, URL url) {
        State state = new State();
        state.setUrl(url);
        interpretationContext.pushObject(state);
        return url;
    }

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        if (peekPath(interpretationContext) == null) {
            super.begin(interpretationContext, str, attributes);
        }
    }

    protected void handleError(String str, Exception exception) {
        if (exception == null || (exception instanceof FileNotFoundException) || (exception instanceof UnknownHostException)) {
            addInfo(str);
        } else {
            addWarn(str, exception);
        }
    }

    protected void processInclude(InterpretationContext interpretationContext, URL url) throws JoranException {
        pushPath(interpretationContext, url);
    }
}
