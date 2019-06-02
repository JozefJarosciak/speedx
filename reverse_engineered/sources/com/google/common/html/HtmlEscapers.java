package com.google.common.html;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

@GwtCompatible
@Beta
public final class HtmlEscapers {
    private static final Escaper HTML_ESCAPER = Escapers.builder().addEscape(CoreConstants.DOUBLE_QUOTE_CHAR, "&quot;").addEscape(CoreConstants.SINGLE_QUOTE_CHAR, "&#39;").addEscape('&', "&amp;").addEscape('<', "&lt;").addEscape('>', "&gt;").build();

    public static Escaper htmlEscaper() {
        return HTML_ESCAPER;
    }

    private HtmlEscapers() {
    }
}
