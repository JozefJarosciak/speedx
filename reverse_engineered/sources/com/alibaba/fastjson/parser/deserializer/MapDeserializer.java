package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDeserializer implements ObjectDeserializer {
    public static final MapDeserializer instance = new MapDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        }
        Map createMap = createMap(type);
        ParseContext context = defaultJSONParser.getContext();
        try {
            defaultJSONParser.setContext(context, createMap, obj);
            T deserialze = deserialze(defaultJSONParser, type, obj, createMap);
            return deserialze;
        } finally {
            defaultJSONParser.setContext(context);
        }
    }

    protected Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Map map) {
        if (!(type instanceof ParameterizedType)) {
            return defaultJSONParser.parseObject(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class cls = parameterizedType.getActualTypeArguments()[0];
        Type type2 = parameterizedType.getActualTypeArguments()[1];
        if (String.class == cls) {
            return parseMap(defaultJSONParser, map, type2, obj);
        }
        return parseMap(defaultJSONParser, map, cls, type2, obj);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.util.Map<java.lang.String, java.lang.Object> r10, java.lang.reflect.Type r11, java.lang.Object r12) {
        /*
        r8 = 39;
        r7 = 13;
        r6 = 58;
        r5 = 34;
        r2 = r9.getLexer();
        r0 = r2.token();
        r1 = 12;
        if (r0 == r1) goto L_0x0031;
    L_0x0014:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "syntax error, expect {, actual ";
        r1 = r1.append(r3);
        r2 = r2.token();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0031:
        r3 = r9.getContext();
    L_0x0035:
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r0 = r2.getCurrent();	 Catch:{ all -> 0x0085 }
        r1 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x0085 }
        r1 = r9.isEnabled(r1);	 Catch:{ all -> 0x0085 }
        if (r1 == 0) goto L_0x0053;
    L_0x0044:
        r1 = 44;
        if (r0 != r1) goto L_0x0053;
    L_0x0048:
        r2.next();	 Catch:{ all -> 0x0085 }
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r0 = r2.getCurrent();	 Catch:{ all -> 0x0085 }
        goto L_0x0044;
    L_0x0053:
        if (r0 != r5) goto L_0x008a;
    L_0x0055:
        r0 = r9.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 34;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r2.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r6) goto L_0x0127;
    L_0x0068:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r1.<init>();	 Catch:{ all -> 0x0085 }
        r4 = "expect ':' at ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x0085 }
        r2 = r2.pos();	 Catch:{ all -> 0x0085 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x0085:
        r0 = move-exception;
        r9.setContext(r3);
        throw r0;
    L_0x008a:
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r1) goto L_0x009d;
    L_0x008e:
        r2.next();	 Catch:{ all -> 0x0085 }
        r2.resetStringPosition();	 Catch:{ all -> 0x0085 }
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r9.setContext(r3);
    L_0x009c:
        return r10;
    L_0x009d:
        if (r0 != r8) goto L_0x00df;
    L_0x009f:
        r0 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes;	 Catch:{ all -> 0x0085 }
        r0 = r9.isEnabled(r0);	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x00af;
    L_0x00a7:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00af:
        r0 = r9.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 39;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r2.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r6) goto L_0x0127;
    L_0x00c2:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r1.<init>();	 Catch:{ all -> 0x0085 }
        r4 = "expect ':' at ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x0085 }
        r2 = r2.pos();	 Catch:{ all -> 0x0085 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00df:
        r0 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames;	 Catch:{ all -> 0x0085 }
        r0 = r9.isEnabled(r0);	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x00ef;
    L_0x00e7:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00ef:
        r0 = r9.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r0 = r2.scanSymbolUnQuoted(r0);	 Catch:{ all -> 0x0085 }
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r2.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r6) goto L_0x0127;
    L_0x0100:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r4.<init>();	 Catch:{ all -> 0x0085 }
        r5 = "expect ':' at ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0085 }
        r2 = r2.pos();	 Catch:{ all -> 0x0085 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x0085 }
        r4 = ", actual ";
        r2 = r2.append(r4);	 Catch:{ all -> 0x0085 }
        r1 = r2.append(r1);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x0127:
        r1 = r0;
        r2.next();	 Catch:{ all -> 0x0085 }
        r2.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r2.getCurrent();	 Catch:{ all -> 0x0085 }
        r2.resetStringPosition();	 Catch:{ all -> 0x0085 }
        r0 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x0085 }
        if (r1 != r0) goto L_0x0187;
    L_0x0138:
        r0 = r9.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 34;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r0 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0);	 Catch:{ all -> 0x0085 }
        r1 = r10.getClass();	 Catch:{ all -> 0x0085 }
        if (r0 != r1) goto L_0x0161;
    L_0x014c:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r0 = r2.token();	 Catch:{ all -> 0x0085 }
        if (r0 != r7) goto L_0x0035;
    L_0x0157:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r9.setContext(r3);
        goto L_0x009c;
    L_0x0161:
        r1 = r9.getConfig();	 Catch:{ all -> 0x0085 }
        r1 = r1.getDeserializer(r0);	 Catch:{ all -> 0x0085 }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x0085 }
        r2 = 2;
        r9.setResolveStatus(r2);	 Catch:{ all -> 0x0085 }
        if (r3 == 0) goto L_0x017b;
    L_0x0174:
        r2 = r12 instanceof java.lang.Integer;	 Catch:{ all -> 0x0085 }
        if (r2 != 0) goto L_0x017b;
    L_0x0178:
        r9.popContext();	 Catch:{ all -> 0x0085 }
    L_0x017b:
        r0 = r1.deserialze(r9, r0, r12);	 Catch:{ all -> 0x0085 }
        r0 = (java.util.Map) r0;	 Catch:{ all -> 0x0085 }
        r9.setContext(r3);
        r10 = r0;
        goto L_0x009c;
    L_0x0187:
        r2.nextToken();	 Catch:{ all -> 0x0085 }
        r0 = r2.token();	 Catch:{ all -> 0x0085 }
        r4 = 8;
        if (r0 != r4) goto L_0x01b0;
    L_0x0192:
        r0 = 0;
        r2.nextToken();	 Catch:{ all -> 0x0085 }
    L_0x0196:
        r10.put(r1, r0);	 Catch:{ all -> 0x0085 }
        r9.checkMapResolve(r10, r1);	 Catch:{ all -> 0x0085 }
        r9.setContext(r3, r0, r1);	 Catch:{ all -> 0x0085 }
        r0 = r2.token();	 Catch:{ all -> 0x0085 }
        r1 = 20;
        if (r0 == r1) goto L_0x01ab;
    L_0x01a7:
        r1 = 15;
        if (r0 != r1) goto L_0x01b5;
    L_0x01ab:
        r9.setContext(r3);
        goto L_0x009c;
    L_0x01b0:
        r0 = r9.parseObject(r11);	 Catch:{ all -> 0x0085 }
        goto L_0x0196;
    L_0x01b5:
        if (r0 != r7) goto L_0x0035;
    L_0x01b7:
        r2.nextToken();	 Catch:{ all -> 0x0085 }
        r9.setContext(r3);
        goto L_0x009c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object parseMap(DefaultJSONParser defaultJSONParser, Map<Object, Object> map, Type type, Type type2, Object obj) {
        Map<Object, Object> map2 = null;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 12 || lexer.token() == 16) {
            ObjectDeserializer deserializer = defaultJSONParser.getConfig().getDeserializer(type);
            ObjectDeserializer deserializer2 = defaultJSONParser.getConfig().getDeserializer(type2);
            lexer.nextToken(deserializer.getFastMatchToken());
            ParseContext context = defaultJSONParser.getContext();
            while (lexer.token() != 13) {
                try {
                    if (lexer.token() == 4 && lexer.isRef()) {
                        lexer.nextTokenWithColon(4);
                        if (lexer.token() == 4) {
                            String stringVal = lexer.stringVal();
                            if ("..".equals(stringVal)) {
                                map2 = context.getParentContext().getObject();
                            } else if ("$".equals(stringVal)) {
                                ParseContext parseContext = context;
                                while (parseContext.getParentContext() != null) {
                                    parseContext = parseContext.getParentContext();
                                }
                                map2 = parseContext.getObject();
                            } else {
                                defaultJSONParser.addResolveTask(new ResolveTask(context, stringVal));
                                defaultJSONParser.setResolveStatus(1);
                            }
                            lexer.nextToken(13);
                            if (lexer.token() != 13) {
                                throw new JSONException("illegal ref");
                            }
                            lexer.nextToken(16);
                            defaultJSONParser.setContext(context);
                            return map2;
                        }
                        throw new JSONException("illegal ref, " + JSONToken.name(lexer.token()));
                    }
                    if (map.size() == 0 && lexer.token() == 4 && JSON.DEFAULT_TYPE_KEY.equals(lexer.stringVal())) {
                        lexer.nextTokenWithColon(4);
                        lexer.nextToken(16);
                        if (lexer.token() == 13) {
                            lexer.nextToken();
                            return map;
                        }
                        lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    Object deserialze = deserializer.deserialze(defaultJSONParser, type, null);
                    if (lexer.token() != 17) {
                        throw new JSONException("syntax error, expect :, actual " + lexer.token());
                    }
                    lexer.nextToken(deserializer2.getFastMatchToken());
                    map.put(deserialze, deserializer2.deserialze(defaultJSONParser, type2, deserialze));
                    if (lexer.token() == 16) {
                        lexer.nextToken(deserializer.getFastMatchToken());
                    }
                } finally {
                    defaultJSONParser.setContext(context);
                }
            }
            lexer.nextToken(16);
            defaultJSONParser.setContext(context);
            return map;
        }
        throw new JSONException("syntax error, expect {, actual " + lexer.tokenName());
    }

    protected Map<Object, Object> createMap(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            return createMap(((ParameterizedType) type).getRawType());
        }
        Class cls = (Class) type;
        if (cls.isInterface()) {
            throw new JSONException("unsupport type " + type);
        }
        try {
            return (Map) cls.newInstance();
        } catch (Throwable e) {
            throw new JSONException("unsupport type " + type, e);
        }
    }

    public int getFastMatchToken() {
        return 12;
    }
}
