package ch.qos.logback.classic.html;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.helpers.Transform;
import ch.qos.logback.core.html.IThrowableRenderer;

public class DefaultThrowableRenderer implements IThrowableRenderer<ILoggingEvent> {
    static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";

    public void printFirstLine(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy.getCommonFrames() > 0) {
            stringBuilder.append("<br />").append(CoreConstants.CAUSED_BY);
        }
        stringBuilder.append(iThrowableProxy.getClassName()).append(": ").append(Transform.escapeTags(iThrowableProxy.getMessage()));
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    public void render(StringBuilder stringBuilder, ILoggingEvent iLoggingEvent) {
        stringBuilder.append("<tr><td class=\"Exception\" colspan=\"6\">");
        for (IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy(); throwableProxy != null; throwableProxy = throwableProxy.getCause()) {
            render(stringBuilder, throwableProxy);
        }
        stringBuilder.append("</td></tr>");
    }

    void render(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        printFirstLine(stringBuilder, iThrowableProxy);
        int commonFrames = iThrowableProxy.getCommonFrames();
        StackTraceElementProxy[] stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        for (int i = 0; i < stackTraceElementProxyArray.length - commonFrames; i++) {
            StackTraceElementProxy stackTraceElementProxy = stackTraceElementProxyArray[i];
            stringBuilder.append(TRACE_PREFIX);
            stringBuilder.append(Transform.escapeTags(stackTraceElementProxy.toString()));
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        }
        if (commonFrames > 0) {
            stringBuilder.append(TRACE_PREFIX);
            stringBuilder.append("\t... ").append(commonFrames).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }
}
