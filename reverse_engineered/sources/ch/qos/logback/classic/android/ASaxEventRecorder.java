package ch.qos.logback.classic.android;

import ch.qos.logback.core.joran.event.SaxEventRecorder;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.LocatorImpl;
import org.xmlpull.v1.XmlPullParser;

public class ASaxEventRecorder extends SaxEventRecorder {
    private Map<String, String> elemAttrs = null;
    private String elemNameToWatch = null;
    private StatePassFilter filter = new StatePassFilter(new String[0]);
    private int[] holderForStartAndLength = new int[2];

    static class StatePassFilter {
        private int _depth = 0;
        private final String[] _states;

        public StatePassFilter(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this._states = strArr;
        }

        public boolean checkEnd(String str) {
            if (this._depth <= 0 || !str.equals(this._states[this._depth - 1])) {
                return this._depth == this._states.length;
            } else {
                this._depth--;
                return false;
            }
        }

        public boolean checkStart(String str) {
            if (this._depth == this._states.length) {
                return true;
            }
            if (str.equals(this._states[this._depth])) {
                this._depth++;
            }
            return false;
        }

        public int depth() {
            return this._depth;
        }

        public boolean passed() {
            return this._depth == this._states.length;
        }

        public void reset() {
            this._depth = 0;
        }

        public int size() {
            return this._states.length;
        }
    }

    private void characters(XmlPullParser xmlPullParser) {
        if (this.filter.passed()) {
            super.characters(xmlPullParser.getTextCharacters(this.holderForStartAndLength), this.holderForStartAndLength[0], this.holderForStartAndLength[1]);
        }
    }

    private void checkForWatchedAttributes(XmlPullParser xmlPullParser) {
        if (this.elemNameToWatch != null && this.elemAttrs == null && xmlPullParser.getName().equals(this.elemNameToWatch)) {
            Map hashMap = new HashMap();
            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                String str = "";
                String attributeNamespace = xmlPullParser.getAttributeNamespace(i);
                if (attributeNamespace.length() > 0) {
                    int lastIndexOf = attributeNamespace.lastIndexOf("/");
                    if (lastIndexOf > -1 && lastIndexOf + 1 < attributeNamespace.length()) {
                        attributeNamespace = attributeNamespace.substring(lastIndexOf + 1);
                    }
                    attributeNamespace = attributeNamespace + ":";
                } else {
                    attributeNamespace = str;
                }
                hashMap.put(attributeNamespace + xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
            }
            this.elemAttrs = hashMap;
        }
    }

    private void endElement(XmlPullParser xmlPullParser) {
        String name = xmlPullParser.getName();
        if (this.filter.checkEnd(name)) {
            endElement(xmlPullParser.getNamespace(), name, name);
        }
    }

    private void startDocument(XmlPullParser xmlPullParser) {
        super.startDocument();
        super.setDocumentLocator(new LocatorImpl());
    }

    private void startElement(XmlPullParser xmlPullParser) {
        String name = xmlPullParser.getName();
        if (this.filter.checkStart(name)) {
            Attributes attributesImpl = new AttributesImpl();
            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                attributesImpl.addAttribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeType(i), xmlPullParser.getAttributeValue(i));
            }
            startElement(xmlPullParser.getNamespace(), name, name, attributesImpl);
        }
        checkForWatchedAttributes(xmlPullParser);
    }

    public Map<String, String> getAttributeWatchValues() {
        return this.elemAttrs;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<ch.qos.logback.core.joran.event.SaxEvent> recordEvents(org.xml.sax.InputSource r4) throws ch.qos.logback.core.joran.spi.JoranException {
        /*
        r3 = this;
        r0 = r4.getByteStream();
        if (r0 != 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Input source must specify an input stream";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r1 = new ch.qos.logback.repackage.brut.androlib.res.decoder.AXmlResourceParser;	 Catch:{ Exception -> 0x0028 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0028 }
        r0 = 0;
        r3.elemAttrs = r0;	 Catch:{ Exception -> 0x0028 }
    L_0x0016:
        r0 = r1.next();	 Catch:{ Exception -> 0x0028 }
        r2 = -1;
        if (r0 <= r2) goto L_0x0043;
    L_0x001d:
        if (r0 != 0) goto L_0x0038;
    L_0x001f:
        r0 = r3.filter;	 Catch:{ Exception -> 0x0028 }
        r0.reset();	 Catch:{ Exception -> 0x0028 }
        r3.startDocument(r1);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0016;
    L_0x0028:
        r0 = move-exception;
        r1 = r0.getMessage();
        r3.addError(r1, r0);
        r1 = new ch.qos.logback.core.joran.spi.JoranException;
        r2 = "Can't parse Android XML resource";
        r1.<init>(r2, r0);
        throw r1;
    L_0x0038:
        r2 = 1;
        if (r2 != r0) goto L_0x0048;
    L_0x003b:
        r0 = r3.filter;	 Catch:{ Exception -> 0x0028 }
        r0.reset();	 Catch:{ Exception -> 0x0028 }
        r3.endDocument();	 Catch:{ Exception -> 0x0028 }
    L_0x0043:
        r0 = r3.getSaxEventList();	 Catch:{ Exception -> 0x0028 }
        return r0;
    L_0x0048:
        r2 = 2;
        if (r2 != r0) goto L_0x004f;
    L_0x004b:
        r3.startElement(r1);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0016;
    L_0x004f:
        r2 = 3;
        if (r2 != r0) goto L_0x0056;
    L_0x0052:
        r3.endElement(r1);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0016;
    L_0x0056:
        r2 = 4;
        if (r2 != r0) goto L_0x0016;
    L_0x0059:
        r3.characters(r1);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.classic.android.ASaxEventRecorder.recordEvents(org.xml.sax.InputSource):java.util.List<ch.qos.logback.core.joran.event.SaxEvent>");
    }

    public void setAttributeWatch(String str) {
        this.elemNameToWatch = str;
    }

    public void setFilter(String... strArr) {
        this.filter = new StatePassFilter(strArr);
    }
}
