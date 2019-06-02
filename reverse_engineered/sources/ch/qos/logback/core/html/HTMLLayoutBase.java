package ch.qos.logback.core.html;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.ConverterUtil;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class HTMLLayoutBase<E> extends LayoutBase<E> {
    protected long counter = 0;
    protected CssBuilder cssBuilder;
    protected Converter<E> head;
    protected String pattern;
    protected String title = "Logback Log Messages";

    private void buildHeaderRowForTable(StringBuilder stringBuilder) {
        Converter converter = this.head;
        stringBuilder.append("<tr class=\"header\">");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        while (converter != null) {
            if (computeConverterName(converter) == null) {
                converter = converter.getNext();
            } else {
                stringBuilder.append("<td class=\"");
                stringBuilder.append(computeConverterName(converter));
                stringBuilder.append("\">");
                stringBuilder.append(computeConverterName(converter));
                stringBuilder.append("</td>");
                stringBuilder.append(CoreConstants.LINE_SEPARATOR);
                converter = converter.getNext();
            }
        }
        stringBuilder.append("</tr>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    protected String computeConverterName(Converter converter) {
        String simpleName = converter.getClass().getSimpleName();
        int indexOf = simpleName.indexOf("Converter");
        return indexOf == -1 ? simpleName : simpleName.substring(0, indexOf);
    }

    public String getContentType() {
        return "text/html";
    }

    public CssBuilder getCssBuilder() {
        return this.cssBuilder;
    }

    protected abstract Map<String, String> getDefaultConverterMap();

    public Map<String, String> getEffectiveConverterMap() {
        Map<String, String> hashMap = new HashMap();
        Map defaultConverterMap = getDefaultConverterMap();
        if (defaultConverterMap != null) {
            hashMap.putAll(defaultConverterMap);
        }
        Context context = getContext();
        if (context != null) {
            defaultConverterMap = (Map) context.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
            if (defaultConverterMap != null) {
                hashMap.putAll(defaultConverterMap);
            }
        }
        return hashMap;
    }

    public String getFileFooter() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("</body></html>");
        return stringBuilder.toString();
    }

    public String getFileHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
        stringBuilder.append(" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("<html>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("  <head>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("    <title>");
        stringBuilder.append(this.title);
        stringBuilder.append("</title>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        this.cssBuilder.addCss(stringBuilder);
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("  </head>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("<body>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getPresentationFooter() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    public String getPresentationHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<hr/>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("<p>Log session start time ");
        stringBuilder.append(new Date());
        stringBuilder.append("</p><p></p>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("<table cellspacing=\"0\">");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        buildHeaderRowForTable(stringBuilder);
        return stringBuilder.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public void setCssBuilder(CssBuilder cssBuilder) {
        this.cssBuilder = cssBuilder;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void start() {
        boolean z = false;
        try {
            Parser parser = new Parser(this.pattern);
            parser.setContext(getContext());
            this.head = parser.compile(parser.parse(), getEffectiveConverterMap());
            ConverterUtil.startConverters(this.head);
        } catch (Throwable e) {
            addError("Incorrect pattern found", e);
            z = true;
        }
        if (!z) {
            this.started = true;
        }
    }

    protected void startNewTableIfLimitReached(StringBuilder stringBuilder) {
        if (this.counter >= AbstractComponentTracker.LINGERING_TIMEOUT) {
            this.counter = 0;
            stringBuilder.append("</table>");
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            stringBuilder.append("<p></p>");
            stringBuilder.append("<table cellspacing=\"0\">");
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            buildHeaderRowForTable(stringBuilder);
        }
    }
}
