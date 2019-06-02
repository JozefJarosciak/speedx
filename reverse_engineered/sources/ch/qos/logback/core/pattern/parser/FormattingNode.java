package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.FormatInfo;

public class FormattingNode extends Node {
    FormatInfo formatInfo;

    FormattingNode(int i) {
        super(i);
    }

    FormattingNode(int i, Object obj) {
        super(i, obj);
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof FormattingNode)) {
            return false;
        }
        FormattingNode formattingNode = (FormattingNode) obj;
        return this.formatInfo != null ? this.formatInfo.equals(formattingNode.formatInfo) : formattingNode.formatInfo == null;
    }

    public FormatInfo getFormatInfo() {
        return this.formatInfo;
    }

    public int hashCode() {
        return (this.formatInfo != null ? this.formatInfo.hashCode() : 0) + (super.hashCode() * 31);
    }

    public void setFormatInfo(FormatInfo formatInfo) {
        this.formatInfo = formatInfo;
    }
}
