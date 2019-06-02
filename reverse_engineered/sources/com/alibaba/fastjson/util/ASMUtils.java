package com.alibaba.fastjson.util;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alipay.sdk.util.C0880h;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;

public class ASMUtils {
    public static boolean isAndroid(String str) {
        String toLowerCase = str.toLowerCase();
        return toLowerCase.contains("dalvik") || toLowerCase.contains("lemur");
    }

    public static boolean isAndroid() {
        return isAndroid(System.getProperty("java.vm.name"));
    }

    public static String getDesc(Method method) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        Class[] parameterTypes = method.getParameterTypes();
        for (Class desc : parameterTypes) {
            stringBuffer.append(getDesc(desc));
        }
        stringBuffer.append(")");
        stringBuffer.append(getDesc(method.getReturnType()));
        return stringBuffer.toString();
    }

    public static String getDesc(Class<?> cls) {
        if (cls.isPrimitive()) {
            return getPrimitiveLetter(cls);
        }
        if (cls.isArray()) {
            return "[" + getDesc(cls.getComponentType());
        }
        return "L" + getType(cls) + C0880h.f2220b;
    }

    public static String getType(Class<?> cls) {
        if (cls.isArray()) {
            return "[" + getDesc(cls.getComponentType());
        }
        if (cls.isPrimitive()) {
            return getPrimitiveLetter(cls);
        }
        return cls.getName().replaceAll("\\.", "/");
    }

    public static String getPrimitiveLetter(Class<?> cls) {
        if (Integer.TYPE.equals(cls)) {
            return "I";
        }
        if (Void.TYPE.equals(cls)) {
            return "V";
        }
        if (Boolean.TYPE.equals(cls)) {
            return "Z";
        }
        if (Character.TYPE.equals(cls)) {
            return "C";
        }
        if (Byte.TYPE.equals(cls)) {
            return "B";
        }
        if (Short.TYPE.equals(cls)) {
            return "S";
        }
        if (Float.TYPE.equals(cls)) {
            return "F";
        }
        if (Long.TYPE.equals(cls)) {
            return "J";
        }
        if (Double.TYPE.equals(cls)) {
            return "D";
        }
        throw new IllegalStateException("Type: " + cls.getCanonicalName() + " is not a primitive type");
    }

    public static Type getMethodType(Class<?> cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]).getGenericReturnType();
        } catch (Exception e) {
            return null;
        }
    }

    public static Type getFieldType(Class<?> cls, String str) {
        try {
            return cls.getField(str).getGenericType();
        } catch (Exception e) {
            return null;
        }
    }

    public static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken(16);
        }
        defaultJSONParser.accept(14, 14);
        int i = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i)));
            i++;
            if (lexer.token() == 16) {
                lexer.nextToken(14);
            } else {
                defaultJSONParser.accept(15, 16);
                return;
            }
        }
    }
}
