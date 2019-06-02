package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public final class ObjectDescriptor extends Descriptor {
    public void hook(Object obj) {
    }

    public void unhook(Object obj) {
    }

    public NodeType getNodeType(Object obj) {
        return NodeType.ELEMENT_NODE;
    }

    public String getNodeName(Object obj) {
        return obj.getClass().getName();
    }

    public String getLocalName(Object obj) {
        return getNodeName(obj);
    }

    public String getNodeValue(Object obj) {
        return null;
    }

    public void getChildren(Object obj, Accumulator<Object> accumulator) {
    }

    public void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
    }

    public void setAttributesAsText(Object obj, String str) {
    }

    public void getStyles(Object obj, StyleAccumulator styleAccumulator) {
    }

    public void getAccessibilityStyles(Object obj, StyleAccumulator styleAccumulator) {
    }
}
