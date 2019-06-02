package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.subst.Token.Type;
import com.alipay.sdk.util.C0880h;
import java.util.List;

public class Parser {
    int pointer = 0;
    final List<Token> tokenList;

    public Parser(List<Token> list) {
        this.tokenList = list;
    }

    /* renamed from: C */
    private Node m18C() throws ScanException {
        Node E = m19E();
        if (isDefaultToken(peekAtCurentToken())) {
            advanceTokenPointer();
            E.append(makeNewLiteralNode(CoreConstants.DEFAULT_VALUE_SEPARATOR));
            E.append(m19E());
        }
        return E;
    }

    /* renamed from: E */
    private Node m19E() throws ScanException {
        Node T = m20T();
        if (T == null) {
            return null;
        }
        Node Eopt = Eopt();
        if (Eopt == null) {
            return T;
        }
        T.append(Eopt);
        return T;
    }

    private Node Eopt() throws ScanException {
        return peekAtCurentToken() == null ? null : m19E();
    }

    /* renamed from: T */
    private Node m20T() throws ScanException {
        Token peekAtCurentToken = peekAtCurentToken();
        if (peekAtCurentToken == null) {
            return null;
        }
        Node makeNewLiteralNode;
        switch (peekAtCurentToken.type) {
            case LITERAL:
                advanceTokenPointer();
                return makeNewLiteralNode(peekAtCurentToken.payload);
            case CURLY_LEFT:
                advanceTokenPointer();
                Node C = m18C();
                expectCurlyRight(peekAtCurentToken());
                advanceTokenPointer();
                makeNewLiteralNode = makeNewLiteralNode(CoreConstants.LEFT_ACCOLADE);
                makeNewLiteralNode.append(C);
                makeNewLiteralNode.append(makeNewLiteralNode(CoreConstants.RIGHT_ACCOLADE));
                return makeNewLiteralNode;
            case START:
                advanceTokenPointer();
                makeNewLiteralNode = m21V();
                expectCurlyRight(peekAtCurentToken());
                advanceTokenPointer();
                return makeNewLiteralNode;
            default:
                return null;
        }
    }

    /* renamed from: V */
    private Node m21V() throws ScanException {
        Node node = new Node(Type.VARIABLE, m19E());
        if (isDefaultToken(peekAtCurentToken())) {
            advanceTokenPointer();
            node.defaultPart = m19E();
        }
        return node;
    }

    private boolean isDefaultToken(Token token) {
        return token != null && token.type == Type.DEFAULT;
    }

    private Node makeNewLiteralNode(String str) {
        return new Node(Type.LITERAL, str);
    }

    void advanceTokenPointer() {
        this.pointer++;
    }

    void expectCurlyRight(Token token) throws ScanException {
        expectNotNull(token, C0880h.f2222d);
        if (token.type != Type.CURLY_RIGHT) {
            throw new ScanException("Expecting }");
        }
    }

    void expectNotNull(Token token, String str) {
        if (token == null) {
            throw new IllegalArgumentException("All tokens consumed but was expecting \"" + str + "\"");
        }
    }

    public Node parse() throws ScanException {
        return m19E();
    }

    Token peekAtCurentToken() {
        return this.pointer < this.tokenList.size() ? (Token) this.tokenList.get(this.pointer) : null;
    }
}
