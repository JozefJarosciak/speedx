package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;

public class RootCauseFirstThrowableProxyConverter extends ExtendedThrowableProxyConverter {
    protected void recursiveAppendRootCauseFirst(StringBuilder stringBuilder, String str, int i, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy.getCause() != null) {
            recursiveAppendRootCauseFirst(stringBuilder, str, i, iThrowableProxy.getCause());
            str = null;
        }
        ThrowableProxyUtil.indent(stringBuilder, i - 1);
        if (str != null) {
            stringBuilder.append(str);
        }
        ThrowableProxyUtil.subjoinFirstLineRootCauseFirst(stringBuilder, iThrowableProxy);
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        subjoinSTEPArray(stringBuilder, i, iThrowableProxy);
        IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
        if (suppressed != null) {
            for (IThrowableProxy recursiveAppendRootCauseFirst : suppressed) {
                recursiveAppendRootCauseFirst(stringBuilder, CoreConstants.SUPPRESSED, i + 1, recursiveAppendRootCauseFirst);
            }
        }
    }

    protected String throwableProxyToString(IThrowableProxy iThrowableProxy) {
        StringBuilder stringBuilder = new StringBuilder(2048);
        recursiveAppendRootCauseFirst(stringBuilder, null, 1, iThrowableProxy);
        return stringBuilder.toString();
    }
}
