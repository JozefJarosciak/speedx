package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.FormatInfo;
import ch.qos.logback.core.pattern.IdentityCompositeConverter;
import ch.qos.logback.core.pattern.ReplacingCompositeConverter;
import ch.qos.logback.core.pattern.util.IEscapeUtil;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.ScanException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser<E> extends ContextAwareBase {
    public static final Map<String, String> DEFAULT_COMPOSITE_CONVERTER_MAP = new HashMap();
    public static final String MISSING_RIGHT_PARENTHESIS = "http://logback.qos.ch/codes.html#missingRightParenthesis";
    public static final String REPLACE_CONVERTER_WORD = "replace";
    int pointer;
    final List tokenList;

    static {
        DEFAULT_COMPOSITE_CONVERTER_MAP.put(Token.BARE_COMPOSITE_KEYWORD_TOKEN.getValue().toString(), IdentityCompositeConverter.class.getName());
        DEFAULT_COMPOSITE_CONVERTER_MAP.put(REPLACE_CONVERTER_WORD, ReplacingCompositeConverter.class.getName());
    }

    Parser(TokenStream tokenStream) throws ScanException {
        this.pointer = 0;
        this.tokenList = tokenStream.tokenize();
    }

    public Parser(String str) throws ScanException {
        this(str, new RegularEscapeUtil());
    }

    public Parser(String str, IEscapeUtil iEscapeUtil) throws ScanException {
        this.pointer = 0;
        try {
            this.tokenList = new TokenStream(str, iEscapeUtil).tokenize();
        } catch (Throwable e) {
            throw new ScanException("Failed to initialize Parser", e);
        }
    }

    /* renamed from: C */
    FormattingNode m15C() throws ScanException {
        Token curentToken = getCurentToken();
        expectNotNull(curentToken, "a LEFT_PARENTHESIS or KEYWORD");
        switch (curentToken.getType()) {
            case 1004:
                return SINGLE();
            case 1005:
                advanceTokenPointer();
                return COMPOSITE(curentToken.getValue().toString());
            default:
                throw new IllegalStateException("Unexpected token " + curentToken);
        }
    }

    FormattingNode COMPOSITE(String str) throws ScanException {
        FormattingNode compositeNode = new CompositeNode(str);
        compositeNode.setChildNode(m16E());
        Token nextToken = getNextToken();
        if (nextToken == null || nextToken.getType() != 41) {
            String str2 = "Expecting RIGHT_PARENTHESIS token but got " + nextToken;
            addError(str2);
            addError("See also http://logback.qos.ch/codes.html#missingRightParenthesis");
            throw new ScanException(str2);
        }
        nextToken = getCurentToken();
        if (nextToken != null && nextToken.getType() == 1006) {
            compositeNode.setOptions((List) nextToken.getValue());
            advanceTokenPointer();
        }
        return compositeNode;
    }

    /* renamed from: E */
    Node m16E() throws ScanException {
        Node T = m17T();
        if (T == null) {
            return null;
        }
        Node Eopt = Eopt();
        if (Eopt == null) {
            return T;
        }
        T.setNext(Eopt);
        return T;
    }

    Node Eopt() throws ScanException {
        return getCurentToken() == null ? null : m16E();
    }

    FormattingNode SINGLE() throws ScanException {
        FormattingNode simpleKeywordNode = new SimpleKeywordNode(getNextToken().getValue());
        Token curentToken = getCurentToken();
        if (curentToken != null && curentToken.getType() == 1006) {
            simpleKeywordNode.setOptions((List) curentToken.getValue());
            advanceTokenPointer();
        }
        return simpleKeywordNode;
    }

    /* renamed from: T */
    Node m17T() throws ScanException {
        Token curentToken = getCurentToken();
        expectNotNull(curentToken, "a LITERAL or '%'");
        switch (curentToken.getType()) {
            case 37:
                advanceTokenPointer();
                Token curentToken2 = getCurentToken();
                expectNotNull(curentToken2, "a FORMAT_MODIFIER, SIMPLE_KEYWORD or COMPOUND_KEYWORD");
                if (curentToken2.getType() != 1002) {
                    return m15C();
                }
                FormatInfo valueOf = FormatInfo.valueOf((String) curentToken2.getValue());
                advanceTokenPointer();
                Node C = m15C();
                C.setFormatInfo(valueOf);
                return C;
            case 1000:
                advanceTokenPointer();
                return new Node(0, curentToken.getValue());
            default:
                return null;
        }
    }

    void advanceTokenPointer() {
        this.pointer++;
    }

    public Converter<E> compile(Node node, Map map) {
        Compiler compiler = new Compiler(node, map);
        compiler.setContext(this.context);
        return compiler.compile();
    }

    void expectNotNull(Token token, String str) {
        if (token == null) {
            throw new IllegalStateException("All tokens consumed but was expecting " + str);
        }
    }

    Token getCurentToken() {
        return this.pointer < this.tokenList.size() ? (Token) this.tokenList.get(this.pointer) : null;
    }

    Token getNextToken() {
        if (this.pointer >= this.tokenList.size()) {
            return null;
        }
        List list = this.tokenList;
        int i = this.pointer;
        this.pointer = i + 1;
        return (Token) list.get(i);
    }

    public Node parse() throws ScanException {
        return m16E();
    }
}
