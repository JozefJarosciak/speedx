package com.google.common.xml;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import com.google.common.escape.Escapers.Builder;

@GwtCompatible
@Beta
public class XmlEscapers {
    private static final char MAX_ASCII_CONTROL_CHAR = '\u001f';
    private static final char MIN_ASCII_CONTROL_CHAR = '\u0000';
    private static final Escaper XML_ATTRIBUTE_ESCAPER;
    private static final Escaper XML_CONTENT_ESCAPER;
    private static final Escaper XML_ESCAPER;

    private XmlEscapers() {
    }

    public static Escaper xmlContentEscaper() {
        return XML_CONTENT_ESCAPER;
    }

    public static Escaper xmlAttributeEscaper() {
        return XML_ATTRIBUTE_ESCAPER;
    }

    static {
        char c = '\u0000';
        Builder builder = Escapers.builder();
        builder.setSafeRange('\u0000', '�');
        builder.setUnsafeReplacement("�");
        while (c <= MAX_ASCII_CONTROL_CHAR) {
            if (!(c == '\t' || c == '\n' || c == '\r')) {
                builder.addEscape(c, "�");
            }
            c = (char) (c + 1);
        }
        builder.addEscape('&', "&amp;");
        builder.addEscape('<', "&lt;");
        builder.addEscape('>', "&gt;");
        XML_CONTENT_ESCAPER = builder.build();
        builder.addEscape(CoreConstants.SINGLE_QUOTE_CHAR, "&apos;");
        builder.addEscape(CoreConstants.DOUBLE_QUOTE_CHAR, "&quot;");
        XML_ESCAPER = builder.build();
        builder.addEscape('\t', "&#x9;");
        builder.addEscape('\n', "&#xA;");
        builder.addEscape('\r', "&#xD;");
        XML_ATTRIBUTE_ESCAPER = builder.build();
    }
}
