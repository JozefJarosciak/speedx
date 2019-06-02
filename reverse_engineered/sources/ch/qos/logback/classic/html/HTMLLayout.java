package ch.qos.logback.classic.html;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.html.HTMLLayoutBase;
import ch.qos.logback.core.html.IThrowableRenderer;
import ch.qos.logback.core.pattern.Converter;
import java.util.Map;

public class HTMLLayout extends HTMLLayoutBase<ILoggingEvent> {
    static final String DEFAULT_CONVERSION_PATTERN = "%date%thread%level%logger%mdc%msg";
    IThrowableRenderer<ILoggingEvent> throwableRenderer;

    public HTMLLayout() {
        this.pattern = DEFAULT_CONVERSION_PATTERN;
        this.throwableRenderer = new DefaultThrowableRenderer();
        this.cssBuilder = new DefaultCssBuilder();
    }

    private void appendEventToBuffer(StringBuilder stringBuilder, Converter<ILoggingEvent> converter, ILoggingEvent iLoggingEvent) {
        stringBuilder.append("<td class=\"");
        stringBuilder.append(computeConverterName(converter));
        stringBuilder.append("\">");
        converter.write(stringBuilder, iLoggingEvent);
        stringBuilder.append("</td>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    protected String computeConverterName(Converter converter) {
        if (!(converter instanceof MDCConverter)) {
            return super.computeConverterName(converter);
        }
        String firstOption = ((MDCConverter) converter).getFirstOption();
        return firstOption != null ? firstOption : "MDC";
    }

    public String doLayout(ILoggingEvent iLoggingEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        startNewTableIfLimitReached(stringBuilder);
        Object obj = 1;
        long j = this.counter;
        this.counter = j + 1;
        if ((j & 1) == 0) {
            obj = null;
        }
        String toLowerCase = iLoggingEvent.getLevel().toString().toLowerCase();
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("<tr class=\"");
        stringBuilder.append(toLowerCase);
        if (obj != null) {
            stringBuilder.append(" odd\">");
        } else {
            stringBuilder.append(" even\">");
        }
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        for (Converter converter = this.head; converter != null; converter = converter.getNext()) {
            appendEventToBuffer(stringBuilder, converter, iLoggingEvent);
        }
        stringBuilder.append("</tr>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        if (iLoggingEvent.getThrowableProxy() != null) {
            this.throwableRenderer.render(stringBuilder, iLoggingEvent);
        }
        return stringBuilder.toString();
    }

    protected Map<String, String> getDefaultConverterMap() {
        return PatternLayout.defaultConverterMap;
    }

    public IThrowableRenderer getThrowableRenderer() {
        return this.throwableRenderer;
    }

    public void setThrowableRenderer(IThrowableRenderer<ILoggingEvent> iThrowableRenderer) {
        this.throwableRenderer = iThrowableRenderer;
    }

    public void start() {
        Object obj = null;
        if (this.throwableRenderer == null) {
            addError("ThrowableRender cannot be null.");
            obj = 1;
        }
        if (obj == null) {
            super.start();
        }
    }
}
