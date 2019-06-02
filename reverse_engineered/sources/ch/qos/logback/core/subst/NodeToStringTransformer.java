package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.util.OptionHelper;
import com.alipay.sdk.util.C0880h;
import java.util.Iterator;
import java.util.Stack;

public class NodeToStringTransformer {
    final Node node;
    final PropertyContainer propertyContainer0;
    final PropertyContainer propertyContainer1;

    public NodeToStringTransformer(Node node, PropertyContainer propertyContainer) {
        this(node, propertyContainer, null);
    }

    public NodeToStringTransformer(Node node, PropertyContainer propertyContainer, PropertyContainer propertyContainer2) {
        this.node = node;
        this.propertyContainer0 = propertyContainer;
        this.propertyContainer1 = propertyContainer2;
    }

    private void compileNode(Node node, StringBuilder stringBuilder, Stack<Node> stack) throws ScanException {
        while (node != null) {
            switch (node.type) {
                case LITERAL:
                    handleLiteral(node, stringBuilder);
                    break;
                case VARIABLE:
                    handleVariable(node, stringBuilder, stack);
                    break;
                default:
                    break;
            }
            node = node.next;
        }
    }

    private String constructRecursionErrorMessage(Stack<Node> stack) {
        StringBuilder stringBuilder = new StringBuilder("Circular variable reference detected while parsing input [");
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            Node node = (Node) it.next();
            stringBuilder.append("${").append(variableNodeValue(node)).append(C0880h.f2222d);
            if (stack.lastElement() != node) {
                stringBuilder.append(" --> ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private boolean equalNodes(Node node, Node node2) {
        return (node.type == null || node.type.equals(node2.type)) ? (node.payload == null || node.payload.equals(node2.payload)) ? node.defaultPart == null || node.defaultPart.equals(node2.defaultPart) : false : false;
    }

    private void handleLiteral(Node node, StringBuilder stringBuilder) {
        stringBuilder.append((String) node.payload);
    }

    private void handleVariable(Node node, StringBuilder stringBuilder, Stack<Node> stack) throws ScanException {
        if (haveVisitedNodeAlready(node, stack)) {
            stack.push(node);
            throw new IllegalArgumentException(constructRecursionErrorMessage(stack));
        }
        stack.push(node);
        StringBuilder stringBuilder2 = new StringBuilder();
        compileNode((Node) node.payload, stringBuilder2, stack);
        String stringBuilder3 = stringBuilder2.toString();
        String lookupKey = lookupKey(stringBuilder3);
        if (lookupKey != null) {
            compileNode(tokenizeAndParseString(lookupKey), stringBuilder, stack);
            stack.pop();
        } else if (node.defaultPart == null) {
            stringBuilder.append(stringBuilder3 + CoreConstants.UNDEFINED_PROPERTY_SUFFIX);
            stack.pop();
        } else {
            Node node2 = (Node) node.defaultPart;
            stringBuilder2 = new StringBuilder();
            compileNode(node2, stringBuilder2, stack);
            stack.pop();
            stringBuilder.append(stringBuilder2.toString());
        }
    }

    private boolean haveVisitedNodeAlready(Node node, Stack<Node> stack) {
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            if (equalNodes(node, (Node) it.next())) {
                return true;
            }
        }
        return false;
    }

    private String lookupKey(String str) {
        String property = this.propertyContainer0.getProperty(str);
        if (property != null) {
            return property;
        }
        if (this.propertyContainer1 != null) {
            property = this.propertyContainer1.getProperty(str);
            if (property != null) {
                return property;
            }
        }
        property = OptionHelper.getSystemProperty(str, null);
        if (property != null) {
            return property;
        }
        property = OptionHelper.getEnv(str);
        return property == null ? null : property;
    }

    public static String substituteVariable(String str, PropertyContainer propertyContainer, PropertyContainer propertyContainer2) throws ScanException {
        return new NodeToStringTransformer(tokenizeAndParseString(str), propertyContainer, propertyContainer2).transform();
    }

    private static Node tokenizeAndParseString(String str) throws ScanException {
        return new Parser(new Tokenizer(str).tokenize()).parse();
    }

    private String variableNodeValue(Node node) {
        return (String) ((Node) node.payload).payload;
    }

    public String transform() throws ScanException {
        StringBuilder stringBuilder = new StringBuilder();
        compileNode(this.node, stringBuilder, new Stack());
        return stringBuilder.toString();
    }
}
