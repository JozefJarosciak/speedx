package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.android.ASaxEventRecorder;
import ch.qos.logback.core.joran.action.IncludeAction;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import java.io.InputStream;
import java.net.URL;
import org.xml.sax.Attributes;

public class FindIncludeAction extends IncludeAction {
    private static final int EVENT_OFFSET = 1;

    public FindIncludeAction() {
        setEventOffset(1);
    }

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
    }

    protected SaxEventRecorder createRecorder(InputStream inputStream, URL url) {
        if (!url.toString().endsWith("AndroidManifest.xml")) {
            return new SaxEventRecorder(getContext());
        }
        SaxEventRecorder aSaxEventRecorder = new ASaxEventRecorder();
        aSaxEventRecorder.setFilter("logback");
        return aSaxEventRecorder;
    }

    public void end(InterpretationContext interpretationContext, String str) throws ActionException {
        if (!interpretationContext.isEmpty() && (interpretationContext.peekObject() instanceof State)) {
            URL url = ((State) interpretationContext.popObject()).getUrl();
            if (url != null) {
                addInfo("Path found [" + url.toString() + "]");
                try {
                    processInclude(interpretationContext, url);
                    return;
                } catch (Throwable e) {
                    addError("Failed to process include [" + url.toString() + "]", e);
                    return;
                }
            }
            addInfo("No paths found from includes");
        }
    }
}
