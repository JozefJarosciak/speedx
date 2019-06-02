package ch.qos.logback.core.joran.event;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.ContextAwareImpl;
import ch.qos.logback.core.status.Status;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.sax2.Driver;

public class SaxEventRecorder extends DefaultHandler implements ContextAware {
    private final ContextAwareImpl cai;
    ElementPath globalElementPath;
    private Locator locator;
    private List<SaxEvent> saxEventList;

    public SaxEventRecorder() {
        this.saxEventList = new ArrayList();
        this.globalElementPath = new ElementPath();
        this.cai = new ContextAwareImpl(null, this);
    }

    public SaxEventRecorder(Context context) {
        this.saxEventList = new ArrayList();
        this.globalElementPath = new ElementPath();
        this.cai = new ContextAwareImpl(context, this);
    }

    private Driver buildPullParser() throws JoranException {
        try {
            Driver driver = new Driver();
            try {
                driver.setFeature("http://xml.org/sax/features/validation", false);
            } catch (SAXNotSupportedException e) {
            }
            driver.setFeature("http://xml.org/sax/features/namespaces", true);
            return driver;
        } catch (Throwable e2) {
            String str = "Parser configuration error occurred";
            addError(str, e2);
            throw new JoranException(str, e2);
        }
    }

    private void handleError(String str, Throwable th) throws JoranException {
        addError(str, th);
        throw new JoranException(str, th);
    }

    public void addError(String str) {
        this.cai.addError(str);
    }

    public void addError(String str, Throwable th) {
        this.cai.addError(str, th);
    }

    public void addInfo(String str) {
        this.cai.addInfo(str);
    }

    public void addInfo(String str, Throwable th) {
        this.cai.addInfo(str, th);
    }

    public void addStatus(Status status) {
        this.cai.addStatus(status);
    }

    public void addWarn(String str) {
        this.cai.addWarn(str);
    }

    public void addWarn(String str, Throwable th) {
        this.cai.addWarn(str, th);
    }

    public void characters(char[] cArr, int i, int i2) {
        String str = new String(cArr, i, i2);
        SaxEvent lastEvent = getLastEvent();
        if (lastEvent instanceof BodyEvent) {
            ((BodyEvent) lastEvent).append(str);
        } else if (!isSpaceOnly(str)) {
            this.saxEventList.add(new BodyEvent(str, getLocator()));
        }
    }

    public void endElement(String str, String str2, String str3) {
        if (str3 == null || str3.length() == 0) {
            this.saxEventList.add(new EndEvent(str, str2, str3, getLocator()));
            this.globalElementPath.pop();
        } else {
            this.saxEventList.add(new EndEvent(str, str2, str3, getLocator()));
            this.globalElementPath.pop();
        }
    }

    public void error(SAXParseException sAXParseException) throws SAXException {
        addError("XML_PARSING - Parsing error on line " + sAXParseException.getLineNumber() + " and column " + sAXParseException.getColumnNumber(), sAXParseException);
    }

    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        addError("XML_PARSING - Parsing fatal error on line " + sAXParseException.getLineNumber() + " and column " + sAXParseException.getColumnNumber(), sAXParseException);
    }

    public Context getContext() {
        return this.cai.getContext();
    }

    SaxEvent getLastEvent() {
        if (this.saxEventList.isEmpty()) {
            return null;
        }
        return (SaxEvent) this.saxEventList.get(this.saxEventList.size() - 1);
    }

    public Locator getLocator() {
        return this.locator;
    }

    public List<SaxEvent> getSaxEventList() {
        return this.saxEventList;
    }

    String getTagName(String str, String str2) {
        return (str == null || str.length() < 1) ? str2 : str;
    }

    boolean isSpaceOnly(String str) {
        return str.trim().length() == 0;
    }

    public List<SaxEvent> recordEvents(InputSource inputSource) throws JoranException {
        Driver buildPullParser = buildPullParser();
        try {
            buildPullParser.setContentHandler(this);
            buildPullParser.setErrorHandler(this);
            buildPullParser.parse(inputSource);
            return this.saxEventList;
        } catch (Exception e) {
            handleError(e.getLocalizedMessage(), new SAXParseException(e.getLocalizedMessage(), this.locator, e));
            throw new IllegalStateException("This point can never be reached");
        } catch (Throwable e2) {
            handleError("I/O error occurred while parsing xml file", e2);
            throw new IllegalStateException("This point can never be reached");
        } catch (Throwable e22) {
            throw new JoranException("Problem parsing XML document. See previously reported errors.", e22);
        } catch (Throwable e222) {
            handleError("Unexpected exception while parsing XML document.", e222);
            throw new IllegalStateException("This point can never be reached");
        }
    }

    public final void recordEvents(InputStream inputStream) throws JoranException {
        recordEvents(new InputSource(inputStream));
    }

    public void setContext(Context context) {
        this.cai.setContext(context);
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void startDocument() {
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        if (str3 == null || str3.length() == 0) {
            this.globalElementPath.push(getTagName(str2, str3));
            this.saxEventList.add(new StartEvent(this.globalElementPath.duplicate(), str, str2, str3, attributes, getLocator()));
        } else {
            this.globalElementPath.push(getTagName(str2, str3));
            this.saxEventList.add(new StartEvent(this.globalElementPath.duplicate(), str, str2, str3, attributes, getLocator()));
        }
    }

    public void warning(SAXParseException sAXParseException) throws SAXException {
        addWarn("XML_PARSING - Parsing warning on line " + sAXParseException.getLineNumber() + " and column " + sAXParseException.getColumnNumber(), sAXParseException);
    }
}
