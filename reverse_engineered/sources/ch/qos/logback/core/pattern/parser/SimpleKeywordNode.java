package ch.qos.logback.core.pattern.parser;

import java.util.List;

public class SimpleKeywordNode extends FormattingNode {
    List<String> optionList;

    protected SimpleKeywordNode(int i, Object obj) {
        super(i, obj);
    }

    SimpleKeywordNode(Object obj) {
        super(1, obj);
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof SimpleKeywordNode)) {
            return false;
        }
        SimpleKeywordNode simpleKeywordNode = (SimpleKeywordNode) obj;
        return this.optionList != null ? this.optionList.equals(simpleKeywordNode.optionList) : simpleKeywordNode.optionList == null;
    }

    public List<String> getOptions() {
        return this.optionList;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setOptions(List<String> list) {
        this.optionList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.optionList == null) {
            stringBuffer.append("KeyWord(" + this.value + "," + this.formatInfo + ")");
        } else {
            stringBuffer.append("KeyWord(" + this.value + ", " + this.formatInfo + "," + this.optionList + ")");
        }
        stringBuffer.append(printNext());
        return stringBuffer.toString();
    }
}
