package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.CollectionResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ListResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser extends AbstractJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected final Object input;
    protected final JSONLexer lexer;
    private int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    protected final SymbolTable symbolTable;

    public static class ResolveTask {
        private final ParseContext context;
        private FieldDeserializer fieldDeserializer;
        private ParseContext ownerContext;
        private final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }

        public ParseContext getContext() {
            return this.context;
        }

        public String getReferenceValue() {
            return this.referenceValue;
        }

        public FieldDeserializer getFieldDeserializer() {
            return this.fieldDeserializer;
        }

        public void setFieldDeserializer(FieldDeserializer fieldDeserializer) {
            this.fieldDeserializer = fieldDeserializer;
        }

        public ParseContext getOwnerContext() {
            return this.ownerContext;
        }

        public void setOwnerContext(ParseContext parseContext) {
            this.ownerContext = parseContext;
        }
    }

    static {
        primitiveClasses.add(Boolean.TYPE);
        primitiveClasses.add(Byte.TYPE);
        primitiveClasses.add(Short.TYPE);
        primitiveClasses.add(Integer.TYPE);
        primitiveClasses.add(Long.TYPE);
        primitiveClasses.add(Float.TYPE);
        primitiveClasses.add(Double.TYPE);
        primitiveClasses.add(Boolean.class);
        primitiveClasses.add(Byte.class);
        primitiveClasses.add(Short.class);
        primitiveClasses.add(Integer.class);
        primitiveClasses.add(Long.class);
        primitiveClasses.add(Float.class);
        primitiveClasses.add(Double.class);
        primitiveClasses.add(BigInteger.class);
        primitiveClasses.add(BigDecimal.class);
        primitiveClasses.add(String.class);
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern);
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this((Object) str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this((Object) str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this((Object) cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this(null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArray = new ParseContext[8];
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.getSymbolTable();
        jSONLexer.nextToken(12);
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        if (this.input instanceof char[]) {
            return new String((char[]) this.input);
        }
        return this.input.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r13, java.lang.Object r14) {
        /*
        r12 = this;
        r11 = 58;
        r10 = 34;
        r9 = 13;
        r3 = 1;
        r8 = 16;
        r5 = r12.lexer;
        r0 = r5.token();
        r1 = 12;
        if (r0 == r1) goto L_0x0036;
    L_0x0013:
        r0 = r5.token();
        if (r0 == r8) goto L_0x0036;
    L_0x0019:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error, expect {, actual ";
        r1 = r1.append(r2);
        r2 = r5.tokenName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0036:
        r4 = r12.getContext();
        r2 = 0;
        r1 = r2;
    L_0x003c:
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r0 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        r2 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x0095 }
        r2 = r12.isEnabled(r2);	 Catch:{ all -> 0x0095 }
        if (r2 == 0) goto L_0x005a;
    L_0x004b:
        r2 = 44;
        if (r0 != r2) goto L_0x005a;
    L_0x004f:
        r5.next();	 Catch:{ all -> 0x0095 }
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r0 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        goto L_0x004b;
    L_0x005a:
        r2 = 0;
        if (r0 != r10) goto L_0x009a;
    L_0x005d:
        r0 = r12.symbolTable;	 Catch:{ all -> 0x0095 }
        r6 = 34;
        r0 = r5.scanSymbol(r0, r6);	 Catch:{ all -> 0x0095 }
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r6 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        if (r6 == r11) goto L_0x0165;
    L_0x006e:
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        r3 = "expect ':' at ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = r5.pos();	 Catch:{ all -> 0x0095 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = ", name ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0095 }
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
        r1.<init>(r0);	 Catch:{ all -> 0x0095 }
        throw r1;	 Catch:{ all -> 0x0095 }
    L_0x0095:
        r0 = move-exception;
        r12.setContext(r4);
        throw r0;
    L_0x009a:
        r6 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r6) goto L_0x00ab;
    L_0x009e:
        r5.next();	 Catch:{ all -> 0x0095 }
        r5.resetStringPosition();	 Catch:{ all -> 0x0095 }
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
    L_0x00aa:
        return r13;
    L_0x00ab:
        r6 = 39;
        if (r0 != r6) goto L_0x00ed;
    L_0x00af:
        r0 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes;	 Catch:{ all -> 0x0095 }
        r0 = r12.isEnabled(r0);	 Catch:{ all -> 0x0095 }
        if (r0 != 0) goto L_0x00bf;
    L_0x00b7:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x00bf:
        r0 = r12.symbolTable;	 Catch:{ all -> 0x0095 }
        r6 = 39;
        r0 = r5.scanSymbol(r0, r6);	 Catch:{ all -> 0x0095 }
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r6 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        if (r6 == r11) goto L_0x0165;
    L_0x00d0:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r2 = "expect ':' at ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r2 = r5.pos();	 Catch:{ all -> 0x0095 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r1 = r1.toString();	 Catch:{ all -> 0x0095 }
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x00ed:
        r6 = 26;
        if (r0 != r6) goto L_0x00f9;
    L_0x00f1:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x00f9:
        r6 = 44;
        if (r0 != r6) goto L_0x0105;
    L_0x00fd:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x0105:
        r6 = 48;
        if (r0 < r6) goto L_0x010d;
    L_0x0109:
        r6 = 57;
        if (r0 <= r6) goto L_0x0111;
    L_0x010d:
        r6 = 45;
        if (r0 != r6) goto L_0x0155;
    L_0x0111:
        r5.resetStringPosition();	 Catch:{ all -> 0x0095 }
        r5.scanNumber();	 Catch:{ all -> 0x0095 }
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        r6 = 2;
        if (r0 != r6) goto L_0x014f;
    L_0x011e:
        r0 = r5.integerValue();	 Catch:{ all -> 0x0095 }
    L_0x0122:
        r6 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        if (r6 == r11) goto L_0x0165;
    L_0x0128:
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        r3 = "expect ':' at ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = r5.pos();	 Catch:{ all -> 0x0095 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = ", name ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0095 }
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
        r1.<init>(r0);	 Catch:{ all -> 0x0095 }
        throw r1;	 Catch:{ all -> 0x0095 }
    L_0x014f:
        r0 = 1;
        r0 = r5.decimalValue(r0);	 Catch:{ all -> 0x0095 }
        goto L_0x0122;
    L_0x0155:
        r6 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r0 == r6) goto L_0x015d;
    L_0x0159:
        r6 = 91;
        if (r0 != r6) goto L_0x018d;
    L_0x015d:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r0 = r12.parse();	 Catch:{ all -> 0x0095 }
        r2 = r3;
    L_0x0165:
        if (r2 != 0) goto L_0x016d;
    L_0x0167:
        r5.next();	 Catch:{ all -> 0x0095 }
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
    L_0x016d:
        r6 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        r5.resetStringPosition();	 Catch:{ all -> 0x0095 }
        r2 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x0095 }
        if (r0 != r2) goto L_0x023c;
    L_0x0178:
        r0 = r12.symbolTable;	 Catch:{ all -> 0x0095 }
        r2 = 34;
        r0 = r5.scanSymbol(r0, r2);	 Catch:{ all -> 0x0095 }
        r2 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0);	 Catch:{ all -> 0x0095 }
        if (r2 != 0) goto L_0x01d3;
    L_0x0186:
        r2 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x0095 }
        r13.put(r2, r0);	 Catch:{ all -> 0x0095 }
        goto L_0x003c;
    L_0x018d:
        r0 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames;	 Catch:{ all -> 0x0095 }
        r0 = r12.isEnabled(r0);	 Catch:{ all -> 0x0095 }
        if (r0 != 0) goto L_0x019d;
    L_0x0195:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x019d:
        r0 = r12.symbolTable;	 Catch:{ all -> 0x0095 }
        r0 = r5.scanSymbolUnQuoted(r0);	 Catch:{ all -> 0x0095 }
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r6 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        if (r6 == r11) goto L_0x0165;
    L_0x01ac:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r2 = "expect ':' at ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r2 = r5.pos();	 Catch:{ all -> 0x0095 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r2 = ", actual ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0095 }
        r1 = r1.toString();	 Catch:{ all -> 0x0095 }
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x01d3:
        r0 = 16;
        r5.nextToken(r0);	 Catch:{ all -> 0x0095 }
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 != r9) goto L_0x021e;
    L_0x01de:
        r0 = 16;
        r5.nextToken(r0);	 Catch:{ all -> 0x0095 }
        r1 = 0;
        r0 = r12.config;	 Catch:{ Exception -> 0x0215 }
        r0 = r0.getDeserializer(r2);	 Catch:{ Exception -> 0x0215 }
        r3 = r0 instanceof com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;	 Catch:{ Exception -> 0x0215 }
        if (r3 == 0) goto L_0x0205;
    L_0x01ee:
        r0 = (com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer) r0;	 Catch:{ Exception -> 0x0215 }
        r0 = r0.createInstance(r12, r2);	 Catch:{ Exception -> 0x0215 }
    L_0x01f4:
        if (r0 != 0) goto L_0x01ff;
    L_0x01f6:
        r0 = java.lang.Cloneable.class;
        if (r2 != r0) goto L_0x0210;
    L_0x01fa:
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x0215 }
        r0.<init>();	 Catch:{ Exception -> 0x0215 }
    L_0x01ff:
        r12.setContext(r4);
        r13 = r0;
        goto L_0x00aa;
    L_0x0205:
        r3 = r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;	 Catch:{ Exception -> 0x0215 }
        if (r3 == 0) goto L_0x04ad;
    L_0x0209:
        r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0;	 Catch:{ Exception -> 0x0215 }
        r0 = r0.createInstance(r12, r2);	 Catch:{ Exception -> 0x0215 }
        goto L_0x01f4;
    L_0x0210:
        r0 = r2.newInstance();	 Catch:{ Exception -> 0x0215 }
        goto L_0x01ff;
    L_0x0215:
        r0 = move-exception;
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r2 = "create instance error";
        r1.<init>(r2, r0);	 Catch:{ all -> 0x0095 }
        throw r1;	 Catch:{ all -> 0x0095 }
    L_0x021e:
        r0 = 2;
        r12.setResolveStatus(r0);	 Catch:{ all -> 0x0095 }
        r0 = r12.context;	 Catch:{ all -> 0x0095 }
        if (r0 == 0) goto L_0x022d;
    L_0x0226:
        r0 = r14 instanceof java.lang.Integer;	 Catch:{ all -> 0x0095 }
        if (r0 != 0) goto L_0x022d;
    L_0x022a:
        r12.popContext();	 Catch:{ all -> 0x0095 }
    L_0x022d:
        r0 = r12.config;	 Catch:{ all -> 0x0095 }
        r0 = r0.getDeserializer(r2);	 Catch:{ all -> 0x0095 }
        r13 = r0.deserialze(r12, r2, r14);	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x023c:
        r2 = "$ref";
        if (r0 != r2) goto L_0x0305;
    L_0x0240:
        r0 = 4;
        r5.nextToken(r0);	 Catch:{ all -> 0x0095 }
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        r1 = 4;
        if (r0 != r1) goto L_0x02e4;
    L_0x024b:
        r2 = r5.stringVal();	 Catch:{ all -> 0x0095 }
        r0 = 13;
        r5.nextToken(r0);	 Catch:{ all -> 0x0095 }
        r0 = 0;
        r1 = "@";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x027a;
    L_0x025d:
        r1 = r12.getContext();	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x02d8;
    L_0x0263:
        r0 = r12.getContext();	 Catch:{ all -> 0x0095 }
        r0 = r0.getObject();	 Catch:{ all -> 0x0095 }
        r13 = r0;
    L_0x026c:
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 == r9) goto L_0x02da;
    L_0x0272:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x027a:
        r1 = "..";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x029f;
    L_0x0282:
        r1 = r4.getParentContext();	 Catch:{ all -> 0x0095 }
        r3 = r1.getObject();	 Catch:{ all -> 0x0095 }
        if (r3 == 0) goto L_0x0292;
    L_0x028c:
        r0 = r1.getObject();	 Catch:{ all -> 0x0095 }
    L_0x0290:
        r13 = r0;
        goto L_0x026c;
    L_0x0292:
        r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x0095 }
        r3.<init>(r1, r2);	 Catch:{ all -> 0x0095 }
        r12.addResolveTask(r3);	 Catch:{ all -> 0x0095 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x0095 }
        goto L_0x0290;
    L_0x029f:
        r1 = "$";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x02cc;
    L_0x02a7:
        r1 = r4;
    L_0x02a8:
        r3 = r1.getParentContext();	 Catch:{ all -> 0x0095 }
        if (r3 == 0) goto L_0x02b3;
    L_0x02ae:
        r1 = r1.getParentContext();	 Catch:{ all -> 0x0095 }
        goto L_0x02a8;
    L_0x02b3:
        r3 = r1.getObject();	 Catch:{ all -> 0x0095 }
        if (r3 == 0) goto L_0x02bf;
    L_0x02b9:
        r0 = r1.getObject();	 Catch:{ all -> 0x0095 }
    L_0x02bd:
        r13 = r0;
        goto L_0x026c;
    L_0x02bf:
        r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x0095 }
        r3.<init>(r1, r2);	 Catch:{ all -> 0x0095 }
        r12.addResolveTask(r3);	 Catch:{ all -> 0x0095 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x0095 }
        goto L_0x02bd;
    L_0x02cc:
        r1 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x0095 }
        r1.<init>(r4, r2);	 Catch:{ all -> 0x0095 }
        r12.addResolveTask(r1);	 Catch:{ all -> 0x0095 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x0095 }
    L_0x02d8:
        r13 = r0;
        goto L_0x026c;
    L_0x02da:
        r0 = 16;
        r5.nextToken(r0);	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x02e4:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r2 = "illegal ref, ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r2 = r5.token();	 Catch:{ all -> 0x0095 }
        r2 = com.alibaba.fastjson.parser.JSONToken.name(r2);	 Catch:{ all -> 0x0095 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r1 = r1.toString();	 Catch:{ all -> 0x0095 }
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x0305:
        if (r1 != 0) goto L_0x04aa;
    L_0x0307:
        r12.setContext(r13, r14);	 Catch:{ all -> 0x0095 }
        r1 = r12.context;	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x0315;
    L_0x030e:
        r1 = r14 instanceof java.lang.Integer;	 Catch:{ all -> 0x0095 }
        if (r1 != 0) goto L_0x0315;
    L_0x0312:
        r12.popContext();	 Catch:{ all -> 0x0095 }
    L_0x0315:
        r2 = r3;
    L_0x0316:
        r1 = r13.getClass();	 Catch:{ all -> 0x0095 }
        r7 = com.alibaba.fastjson.JSONObject.class;
        if (r1 != r7) goto L_0x0322;
    L_0x031e:
        if (r0 != 0) goto L_0x035d;
    L_0x0320:
        r0 = "null";
    L_0x0322:
        if (r6 != r10) goto L_0x0362;
    L_0x0324:
        r5.scanString();	 Catch:{ all -> 0x0095 }
        r1 = r5.stringVal();	 Catch:{ all -> 0x0095 }
        r6 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat;	 Catch:{ all -> 0x0095 }
        r6 = r5.isEnabled(r6);	 Catch:{ all -> 0x0095 }
        if (r6 == 0) goto L_0x0349;
    L_0x0333:
        r6 = new com.alibaba.fastjson.parser.JSONScanner;	 Catch:{ all -> 0x0095 }
        r6.<init>(r1);	 Catch:{ all -> 0x0095 }
        r7 = r6.scanISO8601DateIfMatch();	 Catch:{ all -> 0x0095 }
        if (r7 == 0) goto L_0x0346;
    L_0x033e:
        r1 = r6.getCalendar();	 Catch:{ all -> 0x0095 }
        r1 = r1.getTime();	 Catch:{ all -> 0x0095 }
    L_0x0346:
        r6.close();	 Catch:{ all -> 0x0095 }
    L_0x0349:
        r13.put(r0, r1);	 Catch:{ all -> 0x0095 }
    L_0x034c:
        r5.skipWhitespace();	 Catch:{ all -> 0x0095 }
        r1 = r5.getCurrent();	 Catch:{ all -> 0x0095 }
        r6 = 44;
        if (r1 != r6) goto L_0x046e;
    L_0x0357:
        r5.next();	 Catch:{ all -> 0x0095 }
        r1 = r2;
        goto L_0x003c;
    L_0x035d:
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
        goto L_0x0322;
    L_0x0362:
        r1 = 48;
        if (r6 < r1) goto L_0x036a;
    L_0x0366:
        r1 = 57;
        if (r6 <= r1) goto L_0x036e;
    L_0x036a:
        r1 = 45;
        if (r6 != r1) goto L_0x0385;
    L_0x036e:
        r5.scanNumber();	 Catch:{ all -> 0x0095 }
        r1 = r5.token();	 Catch:{ all -> 0x0095 }
        r6 = 2;
        if (r1 != r6) goto L_0x0380;
    L_0x0378:
        r1 = r5.integerValue();	 Catch:{ all -> 0x0095 }
    L_0x037c:
        r13.put(r0, r1);	 Catch:{ all -> 0x0095 }
        goto L_0x034c;
    L_0x0380:
        r1 = r5.numberValue();	 Catch:{ all -> 0x0095 }
        goto L_0x037c;
    L_0x0385:
        r1 = 91;
        if (r6 != r1) goto L_0x03b6;
    L_0x0389:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r1 = new com.alibaba.fastjson.JSONArray;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r12.parseArray(r1, r0);	 Catch:{ all -> 0x0095 }
        r13.put(r0, r1);	 Catch:{ all -> 0x0095 }
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 != r9) goto L_0x03a5;
    L_0x039d:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x03a5:
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 != r8) goto L_0x03ae;
    L_0x03ab:
        r1 = r2;
        goto L_0x003c;
    L_0x03ae:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x03b6:
        r1 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r6 != r1) goto L_0x041a;
    L_0x03ba:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r1 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r1 = r12.parseObject(r1, r0);	 Catch:{ all -> 0x0095 }
        r6 = r0.toString();	 Catch:{ all -> 0x0095 }
        r12.checkMapResolve(r13, r6);	 Catch:{ all -> 0x0095 }
        r6 = r13.getClass();	 Catch:{ all -> 0x0095 }
        r7 = com.alibaba.fastjson.JSONObject.class;
        if (r6 != r7) goto L_0x03f0;
    L_0x03d5:
        r6 = r0.toString();	 Catch:{ all -> 0x0095 }
        r13.put(r6, r1);	 Catch:{ all -> 0x0095 }
    L_0x03dc:
        r12.setContext(r4, r1, r0);	 Catch:{ all -> 0x0095 }
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 != r9) goto L_0x03f4;
    L_0x03e5:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x03f0:
        r13.put(r0, r1);	 Catch:{ all -> 0x0095 }
        goto L_0x03dc;
    L_0x03f4:
        r0 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r0 != r8) goto L_0x03fd;
    L_0x03fa:
        r1 = r2;
        goto L_0x003c;
    L_0x03fd:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r1.<init>();	 Catch:{ all -> 0x0095 }
        r2 = "syntax error, ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r2 = r5.tokenName();	 Catch:{ all -> 0x0095 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0095 }
        r1 = r1.toString();	 Catch:{ all -> 0x0095 }
        r0.<init>(r1);	 Catch:{ all -> 0x0095 }
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x041a:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r1 = r12.parse();	 Catch:{ all -> 0x0095 }
        r6 = r13.getClass();	 Catch:{ all -> 0x0095 }
        r7 = com.alibaba.fastjson.JSONObject.class;
        if (r6 != r7) goto L_0x042d;
    L_0x0429:
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
    L_0x042d:
        r13.put(r0, r1);	 Catch:{ all -> 0x0095 }
        r1 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r1 != r9) goto L_0x043e;
    L_0x0436:
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x043e:
        r1 = r5.token();	 Catch:{ all -> 0x0095 }
        if (r1 != r8) goto L_0x0447;
    L_0x0444:
        r1 = r2;
        goto L_0x003c;
    L_0x0447:
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        r3 = "syntax error, position at ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = r5.pos();	 Catch:{ all -> 0x0095 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = ", name ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0095 }
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
        r1.<init>(r0);	 Catch:{ all -> 0x0095 }
        throw r1;	 Catch:{ all -> 0x0095 }
    L_0x046e:
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r1 != r2) goto L_0x0483;
    L_0x0472:
        r5.next();	 Catch:{ all -> 0x0095 }
        r5.resetStringPosition();	 Catch:{ all -> 0x0095 }
        r5.nextToken();	 Catch:{ all -> 0x0095 }
        r12.setContext(r13, r14);	 Catch:{ all -> 0x0095 }
        r12.setContext(r4);
        goto L_0x00aa;
    L_0x0483:
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0095 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        r3 = "syntax error, position at ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = r5.pos();	 Catch:{ all -> 0x0095 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r3 = ", name ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0095 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0095 }
        r0 = r0.toString();	 Catch:{ all -> 0x0095 }
        r1.<init>(r0);	 Catch:{ all -> 0x0095 }
        throw r1;	 Catch:{ all -> 0x0095 }
    L_0x04aa:
        r2 = r1;
        goto L_0x0316;
    L_0x04ad:
        r0 = r1;
        goto L_0x01f4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls);
    }

    public <T> T parseObject(Type type) {
        T t = null;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
        } else {
            try {
                t = this.config.getDeserializer(type).deserialze(this, type, null);
            } catch (JSONException e) {
                throw e;
            } catch (Throwable th) {
                JSONException jSONException = new JSONException(th.getMessage(), th);
            }
        }
        return t;
    }

    public <T> List<T> parseArray(Class<T> cls) {
        Collection arrayList = new ArrayList();
        parseArray((Class) cls, arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        if (this.lexer.token() == 21 || this.lexer.token() == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token() != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token()));
        }
        ObjectDeserializer objectDeserializer;
        if (Integer.TYPE == type) {
            objectDeserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            objectDeserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            objectDeserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(objectDeserializer.getFastMatchToken());
        }
        ParseContext context = getContext();
        setContext(collection, obj);
        int i = 0;
        while (true) {
            if (isEnabled(Feature.AllowArbitraryCommas)) {
                while (this.lexer.token() == 16) {
                    this.lexer.nextToken();
                }
            }
            try {
                if (this.lexer.token() == 15) {
                    break;
                }
                if (Integer.TYPE == type) {
                    collection.add(IntegerCodec.instance.deserialze(this, null, null));
                } else if (String.class == type) {
                    if (this.lexer.token() == 4) {
                        r1 = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        r1 = parse();
                        if (r1 == null) {
                            r1 = null;
                        } else {
                            r1 = r1.toString();
                        }
                    }
                    collection.add(r1);
                } else {
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                        r1 = null;
                    } else {
                        r1 = objectDeserializer.deserialze(this, type, Integer.valueOf(i));
                    }
                    collection.add(r1);
                    checkListResolve(collection);
                }
                if (this.lexer.token() == 16) {
                    this.lexer.nextToken(objectDeserializer.getFastMatchToken());
                }
                i++;
            } finally {
                setContext(context);
            }
        }
        this.lexer.nextToken(16);
    }

    public Object[] parseArray(Type[] typeArr) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        } else {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() != 15) {
                    throw new JSONException("syntax error");
                }
                this.lexer.nextToken(16);
                return new Object[0];
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < typeArr.length) {
                String str;
                if (this.lexer.token() == 8) {
                    this.lexer.nextToken(16);
                    str = null;
                } else {
                    Type type = typeArr[i];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (this.lexer.token() == 2) {
                            str = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            str = TypeUtils.cast(parse(), type, this.config);
                        }
                    } else if (type != String.class) {
                        boolean isArray;
                        Type componentType;
                        if (i == typeArr.length - 1 && (type instanceof Class)) {
                            Class cls = (Class) type;
                            isArray = cls.isArray();
                            componentType = cls.getComponentType();
                        } else {
                            componentType = null;
                            isArray = false;
                        }
                        if (!isArray || this.lexer.token() == 14) {
                            str = this.config.getDeserializer(type).deserialze(this, type, null);
                        } else {
                            Object arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                            int fastMatchToken = deserializer.getFastMatchToken();
                            if (this.lexer.token() != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, null));
                                    if (this.lexer.token() != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(fastMatchToken);
                                }
                                if (this.lexer.token() != 15) {
                                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                                }
                            }
                            str = TypeUtils.cast(arrayList, type, this.config);
                        }
                    } else if (this.lexer.token() == 4) {
                        str = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        str = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i] = str;
                if (this.lexer.token() == 15) {
                    break;
                } else if (this.lexer.token() != 16) {
                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                } else {
                    if (i == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                }
            }
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return objArr;
        }
    }

    public void parseObject(Object obj) {
        Class cls = obj.getClass();
        Map fieldDeserializers = this.config.getFieldDeserializers(cls);
        if (this.lexer.token() == 12 || this.lexer.token() == 16) {
            while (true) {
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token() == 16 && isEnabled(Feature.AllowArbitraryCommas)) {
                    }
                }
                FieldDeserializer fieldDeserializer = (FieldDeserializer) fieldDeserializers.get(scanSymbol);
                if (fieldDeserializer != null) {
                    Object deserialze;
                    Class fieldClass = fieldDeserializer.getFieldClass();
                    Type fieldType = fieldDeserializer.getFieldType();
                    if (fieldClass == Integer.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        deserialze = IntegerCodec.instance.deserialze(this, fieldType, null);
                    } else if (fieldClass == String.class) {
                        this.lexer.nextTokenWithColon(4);
                        deserialze = StringCodec.deserialze(this);
                    } else if (fieldClass == Long.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        deserialze = LongCodec.instance.deserialze(this, fieldType, null);
                    } else {
                        ObjectDeserializer deserializer = this.config.getDeserializer(fieldClass, fieldType);
                        this.lexer.nextTokenWithColon(deserializer.getFastMatchToken());
                        deserialze = deserializer.deserialze(this, fieldType, null);
                    }
                    fieldDeserializer.setValue(obj, deserialze);
                    if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if (isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            }
        }
        throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        Collection arrayList;
        if (type2 instanceof Class) {
            arrayList = new ArrayList();
            parseArray((Class) type2, arrayList);
            return arrayList;
        } else if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Object obj = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(obj)) {
                r2 = new ArrayList();
                parseArray((Class) obj, r2);
                return r2;
            } else if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            } else {
                throw new JSONException("not support type : " + type);
            }
        } else {
            if (type2 instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) type2;
                Type[] bounds = typeVariable.getBounds();
                if (bounds.length != 1) {
                    throw new JSONException("not support : " + typeVariable);
                }
                Type type3 = bounds[0];
                if (type3 instanceof Class) {
                    r2 = new ArrayList();
                    parseArray((Class) type3, r2);
                    return r2;
                }
            }
            if (type2 instanceof ParameterizedType) {
                type2 = (ParameterizedType) type2;
                arrayList = new ArrayList();
                parseArray(type2, arrayList);
                return arrayList;
            }
            throw new JSONException("TODO : " + type);
        }
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        } else if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
            }
        } else {
            throw new JSONException("type not match error");
        }
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].getPath())) {
                return this.contextArray[i].getObject();
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus != 1) {
            return;
        }
        if (collection instanceof List) {
            int size = collection.size() - 1;
            List list = (List) collection;
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.setFieldDeserializer(new ListResolveFieldDeserializer(this, list, size));
            lastResolveTask.setOwnerContext(this.context);
            setResolveStatus(0);
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.setFieldDeserializer(new CollectionResolveFieldDeserializer(this, collection));
        lastResolveTask2.setOwnerContext(this.context);
        setResolveStatus(0);
    }

    public void checkMapResolve(Map map, String str) {
        if (this.resolveStatus == 1) {
            FieldDeserializer mapResolveFieldDeserializer = new MapResolveFieldDeserializer(map, str);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.setFieldDeserializer(mapResolveFieldDeserializer);
            lastResolveTask.setOwnerContext(this.context);
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, null);
    }

    public JSONObject parseObject() {
        Map jSONObject = new JSONObject();
        parseObject(jSONObject);
        return jSONObject;
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(java.util.Collection r8, java.lang.Object r9) {
        /*
        r7 = this;
        r0 = 0;
        r4 = 4;
        r6 = 16;
        r2 = r7.getLexer();
        r1 = r2.token();
        r3 = 21;
        if (r1 == r3) goto L_0x0018;
    L_0x0010:
        r1 = r2.token();
        r3 = 22;
        if (r1 != r3) goto L_0x001b;
    L_0x0018:
        r2.nextToken();
    L_0x001b:
        r1 = r2.token();
        r3 = 14;
        if (r1 == r3) goto L_0x0052;
    L_0x0023:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "syntax error, expect [, actual ";
        r1 = r1.append(r3);
        r3 = r2.token();
        r3 = com.alibaba.fastjson.parser.JSONToken.name(r3);
        r1 = r1.append(r3);
        r3 = ", pos ";
        r1 = r1.append(r3);
        r2 = r2.pos();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0052:
        r2.nextToken(r4);
        r3 = r7.getContext();
        r7.setContext(r8, r9);
        r1 = r0;
    L_0x005d:
        r0 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x006f }
        r0 = r7.isEnabled(r0);	 Catch:{ all -> 0x006f }
        if (r0 == 0) goto L_0x0074;
    L_0x0065:
        r0 = r2.token();	 Catch:{ all -> 0x006f }
        if (r0 != r6) goto L_0x0074;
    L_0x006b:
        r2.nextToken();	 Catch:{ all -> 0x006f }
        goto L_0x0065;
    L_0x006f:
        r0 = move-exception;
        r7.setContext(r3);
        throw r0;
    L_0x0074:
        r0 = r2.token();	 Catch:{ all -> 0x006f }
        switch(r0) {
            case 2: goto L_0x0093;
            case 3: goto L_0x009d;
            case 4: goto L_0x00b6;
            case 5: goto L_0x007b;
            case 6: goto L_0x00de;
            case 7: goto L_0x00e6;
            case 8: goto L_0x010a;
            case 9: goto L_0x007b;
            case 10: goto L_0x007b;
            case 11: goto L_0x007b;
            case 12: goto L_0x00ee;
            case 13: goto L_0x007b;
            case 14: goto L_0x00fc;
            case 15: goto L_0x0111;
            case 16: goto L_0x007b;
            case 17: goto L_0x007b;
            case 18: goto L_0x007b;
            case 19: goto L_0x007b;
            case 20: goto L_0x011a;
            default: goto L_0x007b;
        };	 Catch:{ all -> 0x006f }
    L_0x007b:
        r0 = r7.parse();	 Catch:{ all -> 0x006f }
    L_0x007f:
        r8.add(r0);	 Catch:{ all -> 0x006f }
        r7.checkListResolve(r8);	 Catch:{ all -> 0x006f }
        r0 = r2.token();	 Catch:{ all -> 0x006f }
        if (r0 != r6) goto L_0x008f;
    L_0x008b:
        r0 = 4;
        r2.nextToken(r0);	 Catch:{ all -> 0x006f }
    L_0x008f:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x005d;
    L_0x0093:
        r0 = r2.integerValue();	 Catch:{ all -> 0x006f }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x009d:
        r0 = com.alibaba.fastjson.parser.Feature.UseBigDecimal;	 Catch:{ all -> 0x006f }
        r0 = r2.isEnabled(r0);	 Catch:{ all -> 0x006f }
        if (r0 == 0) goto L_0x00b0;
    L_0x00a5:
        r0 = 1;
        r0 = r2.decimalValue(r0);	 Catch:{ all -> 0x006f }
    L_0x00aa:
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x00b0:
        r0 = 0;
        r0 = r2.decimalValue(r0);	 Catch:{ all -> 0x006f }
        goto L_0x00aa;
    L_0x00b6:
        r0 = r2.stringVal();	 Catch:{ all -> 0x006f }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        r4 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat;	 Catch:{ all -> 0x006f }
        r4 = r2.isEnabled(r4);	 Catch:{ all -> 0x006f }
        if (r4 == 0) goto L_0x007f;
    L_0x00c7:
        r4 = new com.alibaba.fastjson.parser.JSONScanner;	 Catch:{ all -> 0x006f }
        r4.<init>(r0);	 Catch:{ all -> 0x006f }
        r5 = r4.scanISO8601DateIfMatch();	 Catch:{ all -> 0x006f }
        if (r5 == 0) goto L_0x00da;
    L_0x00d2:
        r0 = r4.getCalendar();	 Catch:{ all -> 0x006f }
        r0 = r0.getTime();	 Catch:{ all -> 0x006f }
    L_0x00da:
        r4.close();	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x00de:
        r0 = java.lang.Boolean.TRUE;	 Catch:{ all -> 0x006f }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x00e6:
        r0 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x006f }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x00ee:
        r0 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x006f }
        r0.<init>();	 Catch:{ all -> 0x006f }
        r4 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x006f }
        r0 = r7.parseObject(r0, r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x00fc:
        r0 = new com.alibaba.fastjson.JSONArray;	 Catch:{ all -> 0x006f }
        r0.<init>();	 Catch:{ all -> 0x006f }
        r4 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x006f }
        r7.parseArray(r0, r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x010a:
        r0 = 0;
        r4 = 4;
        r2.nextToken(r4);	 Catch:{ all -> 0x006f }
        goto L_0x007f;
    L_0x0111:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x006f }
        r7.setContext(r3);
        return;
    L_0x011a:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x006f }
        r1 = "unclosed jsonArray";
        r0.<init>(r1);	 Catch:{ all -> 0x006f }
        throw r0;	 Catch:{ all -> 0x006f }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public List<ResolveTask> getResolveTaskListDirect() {
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        return (ResolveTask) this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraProcessor> getExtraProcessorsDirect() {
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public List<ExtraTypeProvider> getExtraTypeProvidersDirect() {
        return this.extraTypeProviders;
    }

    public void setContext(ParseContext parseContext) {
        if (!isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = parseContext;
        }
    }

    public void popContext() {
        if (!isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = this.context.getParentContext();
            this.contextArray[this.contextArrayIndex - 1] = null;
            this.contextArrayIndex--;
        }
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parseContext, obj, obj2);
        addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (i >= this.contextArray.length) {
            Object obj = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, obj, 0, this.contextArray.length);
            this.contextArray = obj;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse(null);
        }
        String stringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return stringVal;
    }

    public Object parse(Object obj) {
        JSONLexer lexer = getLexer();
        Object integerValue;
        Collection jSONArray;
        switch (lexer.token()) {
            case 2:
                integerValue = lexer.integerValue();
                lexer.nextToken();
                return integerValue;
            case 3:
                integerValue = lexer.decimalValue(isEnabled(Feature.UseBigDecimal));
                lexer.nextToken();
                return integerValue;
            case 4:
                integerValue = lexer.stringVal();
                lexer.nextToken(16);
                if (!lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    return integerValue;
                }
                JSONScanner jSONScanner = new JSONScanner(integerValue);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        integerValue = jSONScanner.getCalendar().getTime();
                        return integerValue;
                    }
                    jSONScanner.close();
                    return integerValue;
                } finally {
                    jSONScanner.close();
                }
            case 6:
                lexer.nextToken();
                return Boolean.TRUE;
            case 7:
                lexer.nextToken();
                return Boolean.FALSE;
            case 8:
                lexer.nextToken();
                return null;
            case 9:
                lexer.nextToken(18);
                if (lexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                lexer.nextToken(10);
                accept(10);
                long longValue = lexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(longValue);
            case 12:
                return parseObject(new JSONObject(), obj);
            case 14:
                jSONArray = new JSONArray();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 20:
                if (lexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("unterminated json string, pos " + lexer.getBufferPosition());
            case 21:
                lexer.nextToken();
                jSONArray = new HashSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 22:
                lexer.nextToken();
                jSONArray = new TreeSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            default:
                throw new JSONException("syntax error, pos " + lexer.getBufferPosition());
        }
    }

    public void config(Feature feature, boolean z) {
        getLexer().config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return getLexer().isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer lexer = getLexer();
        if (lexer.token() == i) {
            lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(lexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer lexer = getLexer();
        if (lexer.token() == i) {
            lexer.nextToken(i2);
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(lexer.token()));
    }

    public void close() {
        JSONLexer lexer = getLexer();
        try {
            if (!isEnabled(Feature.AutoCloseSource) || lexer.token() == 20) {
                lexer.close();
                return;
            }
            throw new JSONException("not close json text, token : " + JSONToken.name(lexer.token()));
        } catch (Throwable th) {
            lexer.close();
        }
    }
}
