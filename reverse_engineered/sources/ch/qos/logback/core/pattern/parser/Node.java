package ch.qos.logback.core.pattern.parser;

public class Node {
    static final int COMPOSITE_KEYWORD = 2;
    static final int LITERAL = 0;
    static final int SIMPLE_KEYWORD = 1;
    Node next;
    final int type;
    final Object value;

    Node(int i) {
        this(i, null);
    }

    Node(int i, Object obj) {
        this.type = i;
        this.value = obj;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r2 = r5 instanceof ch.qos.logback.core.pattern.parser.Node;
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r1;
        goto L_0x0004;
    L_0x000b:
        r5 = (ch.qos.logback.core.pattern.parser.Node) r5;
        r2 = r4.type;
        r3 = r5.type;
        if (r2 != r3) goto L_0x002f;
    L_0x0013:
        r2 = r4.value;
        if (r2 == 0) goto L_0x0031;
    L_0x0017:
        r2 = r4.value;
        r3 = r5.value;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x002f;
    L_0x0021:
        r2 = r4.next;
        if (r2 == 0) goto L_0x0036;
    L_0x0025:
        r2 = r4.next;
        r3 = r5.next;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x002f:
        r0 = r1;
        goto L_0x0004;
    L_0x0031:
        r2 = r5.value;
        if (r2 != 0) goto L_0x002f;
    L_0x0035:
        goto L_0x0021;
    L_0x0036:
        r2 = r5.next;
        if (r2 != 0) goto L_0x002f;
    L_0x003a:
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.core.pattern.parser.Node.equals(java.lang.Object):boolean");
    }

    public Node getNext() {
        return this.next;
    }

    public int getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public int hashCode() {
        return (this.value != null ? this.value.hashCode() : 0) + (this.type * 31);
    }

    String printNext() {
        return this.next != null ? " -> " + this.next : "";
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        switch (this.type) {
            case 0:
                stringBuffer.append("LITERAL(" + this.value + ")");
                break;
            default:
                stringBuffer.append(super.toString());
                break;
        }
        stringBuffer.append(printNext());
        return stringBuffer.toString();
    }
}
