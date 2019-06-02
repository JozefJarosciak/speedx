package ch.qos.logback.core.pattern.parser;

public class CompositeNode extends SimpleKeywordNode {
    Node childNode;

    CompositeNode(String str) {
        super(2, str);
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof CompositeNode)) {
            return false;
        }
        CompositeNode compositeNode = (CompositeNode) obj;
        return this.childNode != null ? this.childNode.equals(compositeNode.childNode) : compositeNode.childNode == null;
    }

    public Node getChildNode() {
        return this.childNode;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setChildNode(Node node) {
        this.childNode = node;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.childNode != null) {
            stringBuffer.append("CompositeNode(" + this.childNode + ")");
        } else {
            stringBuffer.append("CompositeNode(no child)");
        }
        stringBuffer.append(printNext());
        return stringBuffer.toString();
    }
}
