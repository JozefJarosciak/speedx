package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class IncludeAction extends AbstractIncludeAction {
    private static final String CONFIG_TAG = "configuration";
    private static final String INCLUDED_TAG = "included";
    private int eventOffset = 2;

    private String getEventName(SaxEvent saxEvent) {
        return saxEvent.qName.length() > 0 ? saxEvent.qName : saxEvent.localName;
    }

    private InputStream openURL(URL url) {
        try {
            return url.openStream();
        } catch (Throwable e) {
            if (!isOptional()) {
                addError("Failed to open [" + url.toString() + "]", e);
            }
            return null;
        }
    }

    private void trimHeadAndTail(SaxEventRecorder saxEventRecorder) {
        List saxEventList = saxEventRecorder.getSaxEventList();
        if (saxEventList.size() != 0) {
            String eventName;
            boolean z;
            SaxEvent saxEvent = (SaxEvent) saxEventList.get(0);
            boolean equalsIgnoreCase;
            if (saxEvent != null) {
                eventName = getEventName(saxEvent);
                equalsIgnoreCase = INCLUDED_TAG.equalsIgnoreCase(eventName);
                z = equalsIgnoreCase;
                equalsIgnoreCase = CONFIG_TAG.equalsIgnoreCase(eventName);
            } else {
                equalsIgnoreCase = false;
                z = false;
            }
            if (z || r1) {
                saxEventList.remove(0);
                int size = saxEventList.size();
                if (size != 0) {
                    int i = size - 1;
                    saxEvent = (SaxEvent) saxEventList.get(i);
                    if (saxEvent != null) {
                        eventName = getEventName(saxEvent);
                        if ((z && INCLUDED_TAG.equalsIgnoreCase(eventName)) || (r1 && CONFIG_TAG.equalsIgnoreCase(eventName))) {
                            saxEventList.remove(i);
                        }
                    }
                }
            }
        }
    }

    protected SaxEventRecorder createRecorder(InputStream inputStream, URL url) {
        return new SaxEventRecorder(getContext());
    }

    protected void processInclude(InterpretationContext interpretationContext, URL url) throws JoranException {
        InputStream openURL = openURL(url);
        if (openURL != null) {
            try {
                ConfigurationWatchListUtil.addToWatchList(getContext(), url);
                SaxEventRecorder createRecorder = createRecorder(openURL, url);
                createRecorder.setContext(getContext());
                createRecorder.recordEvents(openURL);
                trimHeadAndTail(createRecorder);
                interpretationContext.getJoranInterpreter().getEventPlayer().addEventsDynamically(createRecorder.getSaxEventList(), this.eventOffset);
            } catch (Throwable e) {
                addError("Failed processing [" + url.toString() + "]", e);
                return;
            } finally {
                close(openURL);
            }
        }
        close(openURL);
    }

    protected void setEventOffset(int i) {
        this.eventOffset = i;
    }
}
