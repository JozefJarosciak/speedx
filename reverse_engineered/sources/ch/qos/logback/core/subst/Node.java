package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;

public class Node {
    Object defaultPart;
    Node next;
    Object payload;
    Type type;

    enum Type {
        LITERAL,
        VARIABLE
    }

    public Node(Type type, Object obj) {
        this.type = type;
        this.payload = obj;
    }

    public Node(Type type, Object obj, Object obj2) {
        this.type = type;
        this.payload = obj;
        this.defaultPart = obj2;
    }

    void append(Node node) {
        if (node != null) {
            while (this.next != null) {
                this = this.next;
            }
            this.next = node;
        }
    }

    public void dump() {
        System.out.print(toString());
        System.out.print(" -> ");
        if (this.next != null) {
            this.next.dump();
        } else {
            System.out.print(" null");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        if (this.type != node.type) {
            return false;
        }
        if (this.payload == null ? node.payload != null : !this.payload.equals(node.payload)) {
            return false;
        }
        if (this.defaultPart == null ? node.defaultPart != null : !this.defaultPart.equals(node.defaultPart)) {
            return false;
        }
        if (this.next != null) {
            if (this.next.equals(node.next)) {
                return true;
            }
        } else if (node.next == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.defaultPart != null ? this.defaultPart.hashCode() : 0) + (((this.payload != null ? this.payload.hashCode() : 0) + ((this.type != null ? this.type.hashCode() : 0) * 31)) * 31)) * 31;
        if (this.next != null) {
            i = this.next.hashCode();
        }
        return hashCode + i;
    }

    void recursive(Node node, StringBuilder stringBuilder) {
        while (node != null) {
            stringBuilder.append(node.toString()).append(" --> ");
            node = node.next;
        }
        stringBuilder.append("null ");
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public String toString() {
        switch (this.type) {
            case LITERAL:
                return "Node{type=" + this.type + ", payload='" + this.payload + "'}";
            case VARIABLE:
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                if (this.defaultPart != null) {
                    recursive((Node) this.defaultPart, stringBuilder2);
                }
                recursive((Node) this.payload, stringBuilder);
                String str = "Node{type=" + this.type + ", payload='" + stringBuilder.toString() + "'";
                if (this.defaultPart != null) {
                    str = str + ", defaultPart=" + stringBuilder2.toString();
                }
                return str + CoreConstants.CURLY_RIGHT;
            default:
                return null;
        }
    }
}
