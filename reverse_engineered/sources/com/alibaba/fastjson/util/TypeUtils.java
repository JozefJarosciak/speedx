package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.util.C0880h;
import java.beans.Introspector;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtils {
    public static boolean compatibleWithJavaBean;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap();
    private static Map<Class<?>, Method[]> methodsMap = Collections.synchronizedMap(new WeakHashMap());

    static {
        compatibleWithJavaBean = false;
        try {
            String property = System.getProperty("fastjson.compatibleWithJavaBean");
            if ("true".equals(property)) {
                compatibleWithJavaBean = true;
            } else if ("false".equals(property)) {
                compatibleWithJavaBean = false;
            }
        } catch (Throwable th) {
        }
        addBaseClassMappings();
    }

    public static final String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static final Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() != 0) {
                return Byte.valueOf(Byte.parseByte(str));
            }
            return null;
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JSONException("can not cast to byte, value : " + obj);
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() != 0) {
                return Short.valueOf(Short.parseShort(str));
            }
            return null;
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static final BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        return new BigDecimal(obj2);
    }

    public static final BigInteger castToBigInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static final Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() != 0) {
                return Float.valueOf(Float.parseFloat(obj2));
            }
            return null;
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static final Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() != 0) {
                return Double.valueOf(Double.parseDouble(obj2));
            }
            return null;
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static final Date castToDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        long j = -1;
        if (obj instanceof Number) {
            j = ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.indexOf(45) != -1) {
                String str2;
                if (str.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    str2 = JSON.DEFFAULT_DATE_FORMAT;
                } else if (str.length() == 10) {
                    str2 = "yyyy-MM-dd";
                } else if (str.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    str2 = "yyyy-MM-dd HH:mm:ss";
                } else {
                    str2 = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                try {
                    return new SimpleDateFormat(str2).parse(str);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + str);
                }
            } else if (str.length() == 0) {
                return null;
            } else {
                j = Long.parseLong(str);
            }
        }
        if (j >= 0) {
            return new Date(j);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final java.sql.Date castToSqlDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        long longValue;
        if (obj instanceof Number) {
            longValue = ((Number) obj).longValue();
        } else {
            longValue = 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Timestamp castToTimestamp(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long longValue;
        if (obj instanceof Number) {
            longValue = ((Number) obj).longValue();
        } else {
            longValue = 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Long castToLong(Object obj) {
        Calendar calendar;
        Calendar calendar2 = null;
        if (obj == null) {
            return calendar2;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return calendar2;
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException e) {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    calendar = jSONScanner.getCalendar();
                } else {
                    calendar = calendar2;
                }
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static final Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return Base64.decodeFast((String) obj);
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final Boolean castToBoolean(Object obj) {
        boolean z = true;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            if (((Number) obj).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            if (C0844a.f2048d.equals(str)) {
                return Boolean.TRUE;
            }
            if ("0".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> cls) {
        return cast(obj, (Class) cls, ParserConfig.getGlobalInstance());
    }

    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (cls == obj.getClass()) {
            return obj;
        } else {
            if (obj instanceof Map) {
                if (cls == Map.class) {
                    return obj;
                }
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean((Map) obj, cls, parserConfig);
                }
                return obj;
            } else if (cls.isArray() && (obj instanceof Collection)) {
                Collection<Object> collection = (Collection) obj;
                int i = 0;
                Object newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                for (Object cast : collection) {
                    Array.set(newInstance, i, cast(cast, cls.getComponentType(), parserConfig));
                    i++;
                }
                return newInstance;
            } else if (cls.isAssignableFrom(obj.getClass())) {
                return obj;
            } else {
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return castToBoolean(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return castToByte(obj);
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return castToShort(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return castToInt(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return castToLong(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return castToFloat(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return castToDouble(obj);
                }
                if (cls == String.class) {
                    return castToString(obj);
                }
                if (cls == BigDecimal.class) {
                    return castToBigDecimal(obj);
                }
                if (cls == BigInteger.class) {
                    return castToBigInteger(obj);
                }
                if (cls == Date.class) {
                    return castToDate(obj);
                }
                if (cls == java.sql.Date.class) {
                    return castToSqlDate(obj);
                }
                if (cls == Timestamp.class) {
                    return castToTimestamp(obj);
                }
                if (cls.isEnum()) {
                    return castToEnum(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Calendar instance;
                    Date castToDate = castToDate(obj);
                    if (cls == Calendar.class) {
                        instance = Calendar.getInstance();
                    } else {
                        try {
                            instance = (Calendar) cls.newInstance();
                        } catch (Throwable e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    instance.setTime(castToDate);
                    return instance;
                } else if ((obj instanceof String) && ((String) obj).length() == 0) {
                    return null;
                } else {
                    throw new JSONException("can not cast to : " + cls.getName());
                }
            }
        }
    }

    public static final <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                for (Object obj2 : (Object[]) cls.getMethod("values", new Class[0]).invoke(null, new Object[0])) {
                    Enum enumR = (Enum) obj2;
                    if (enumR.ordinal() == intValue) {
                        return enumR;
                    }
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Throwable e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    public static final <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return cast(obj, (ParameterizedType) type, parserConfig);
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static final <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        Type type;
        Type rawType = parameterizedType.getRawType();
        if (rawType == List.class || rawType == ArrayList.class) {
            type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                T arrayList = new ArrayList();
                for (Object cast : (Iterable) obj) {
                    arrayList.add(cast(cast, type, parserConfig));
                }
                return arrayList;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            type = parameterizedType.getActualTypeArguments()[0];
            Type type2 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                T hashMap = new HashMap();
                for (Entry entry : ((Map) obj).entrySet()) {
                    hashMap.put(cast(entry.getKey(), type, parserConfig), cast(entry.getValue(), type2, parserConfig));
                }
                return hashMap;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        int i = 0;
        try {
            String str;
            String str2;
            if (cls == StackTraceElement.class) {
                str = (String) map.get("className");
                str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number != null) {
                    i = number.intValue();
                }
                return new StackTraceElement(str, str2, str3, i);
            }
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                str = (String) obj;
                Class loadClass = loadClass(str);
                if (loadClass == null) {
                    throw new ClassNotFoundException(str + " not found");
                } else if (!loadClass.equals(cls)) {
                    return castToJavaBean(map, loadClass, parserConfig);
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    map = (JSONObject) map;
                } else {
                    Object jSONObject = new JSONObject((Map) map);
                }
                return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, map);
            }
            if (parserConfig == null) {
                parserConfig = ParserConfig.getGlobalInstance();
            }
            Map fieldDeserializers = parserConfig.getFieldDeserializers(cls);
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            T newInstance = declaredConstructor.newInstance(new Object[0]);
            for (Entry entry : fieldDeserializers.entrySet()) {
                str2 = (String) entry.getKey();
                FieldDeserializer fieldDeserializer = (FieldDeserializer) entry.getValue();
                if (map.containsKey(str2)) {
                    Object obj2 = map.get(str2);
                    Method method = fieldDeserializer.getMethod();
                    if (method != null) {
                        obj = cast(obj2, method.getGenericParameterTypes()[0], parserConfig);
                        method.invoke(newInstance, new Object[]{obj});
                    } else {
                        Field field = fieldDeserializer.getField();
                        field.set(newInstance, cast(obj2, field.getGenericType(), parserConfig));
                    }
                }
            }
            return newInstance;
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static void addClassMapping(String str, Class<?> cls) {
        if (str == null) {
            str = cls.getName();
        }
        mappings.put(str, cls);
    }

    public static void addBaseClassMappings() {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put(HashMap.class.getName(), HashMap.class);
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static Class<?> loadClass(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Class<?> cls = (Class) mappings.get(str);
        if (cls != null) {
            return cls;
        }
        if (str.charAt(0) == '[') {
            return Array.newInstance(loadClass(str.substring(1)), 0).getClass();
        }
        if (str.startsWith("L") && str.endsWith(C0880h.f2220b)) {
            return loadClass(str.substring(1, str.length() - 1));
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                cls = contextClassLoader.loadClass(str);
                addClassMapping(str, cls);
                return cls;
            }
        } catch (Throwable th) {
        }
        try {
            cls = Class.forName(str);
            addClassMapping(str, cls);
            return cls;
        } catch (Throwable th2) {
            return cls;
        }
    }

    public static Method[] getAllMethods(Class<?> cls) {
        if (cls == null || cls.equals(Object.class)) {
            return new Method[0];
        }
        Method[] methodArr = (Method[]) methodsMap.get(cls);
        if (methodArr != null) {
            return methodArr;
        }
        Object declaredMethods;
        List<Method[]> arrayList = new ArrayList();
        int i = 0;
        while (cls != null && cls != Object.class) {
            int length;
            declaredMethods = cls.getDeclaredMethods();
            if (declaredMethods != null) {
                length = declaredMethods.length;
            } else {
                length = 0;
            }
            length += i;
            arrayList.add(declaredMethods);
            cls = cls.getSuperclass();
            i = length;
        }
        declaredMethods = new Method[i];
        i = 0;
        for (Method[] methodArr2 : arrayList) {
            if (methodArr2 != null) {
                for (Method accessible : methodArr2) {
                    accessible.setAccessible(true);
                }
                System.arraycopy(methodArr2, 0, declaredMethods, i, methodArr2.length);
                length = methodArr2.length + i;
            } else {
                length = i;
            }
            i = length;
        }
        methodsMap.put(cls, declaredMethods);
        return declaredMethods;
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map) {
        return computeGetters(cls, map, true);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map, boolean z) {
        JSONField jSONField;
        Object obj;
        Map linkedHashMap = new LinkedHashMap();
        for (Method method : getAllMethods(cls)) {
            String name;
            String decapitalize;
            String name2 = method.getName();
            if (!(Modifier.isStatic(method.getModifiers()) || method.getReturnType().equals(Void.TYPE) || method.getParameterTypes().length != 0 || method.getReturnType() == ClassLoader.class || (method.getName().equals("getMetaClass") && method.getReturnType().getName().equals("groovy.lang.MetaClass")))) {
                char charAt;
                jSONField = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField == null) {
                    jSONField = getSupperMethodAnnotation(cls, method);
                }
                if (jSONField != null) {
                    if (jSONField.serialize()) {
                        if (jSONField.name().length() != 0) {
                            name = jSONField.name();
                            if (map != null) {
                                name = (String) map.get(name);
                                if (name == null) {
                                }
                            }
                            linkedHashMap.put(name, new FieldInfo(name, method, null));
                        }
                    }
                }
                if (name2.startsWith("get")) {
                    if (name2.length() >= 4 && !name2.equals("getClass")) {
                        charAt = name2.charAt(3);
                        if (Character.isUpperCase(charAt)) {
                            if (compatibleWithJavaBean) {
                                decapitalize = Introspector.decapitalize(name2.substring(3));
                            } else {
                                decapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                            }
                        } else if (charAt == '_') {
                            decapitalize = name2.substring(4);
                        } else if (charAt == 'f') {
                            decapitalize = name2.substring(3);
                        }
                        if (!isJSONTypeIgnore(cls, decapitalize)) {
                            Field field = ParserConfig.getField(cls, decapitalize);
                            if (field != null) {
                                jSONField = (JSONField) field.getAnnotation(JSONField.class);
                                if (jSONField != null) {
                                    if (jSONField.serialize()) {
                                        if (jSONField.name().length() != 0) {
                                            decapitalize = jSONField.name();
                                            if (map != null) {
                                                name = (String) map.get(decapitalize);
                                                if (name == null) {
                                                }
                                                if (map != null) {
                                                    name = (String) map.get(name);
                                                    if (name == null) {
                                                    }
                                                }
                                                linkedHashMap.put(name, new FieldInfo(name, method, field));
                                            }
                                        }
                                    }
                                }
                            }
                            name = decapitalize;
                            if (map != null) {
                                name = (String) map.get(name);
                                if (name == null) {
                                }
                            }
                            linkedHashMap.put(name, new FieldInfo(name, method, field));
                        }
                    }
                }
                if (name2.startsWith("is") && name2.length() >= 3) {
                    Field field2;
                    charAt = name2.charAt(2);
                    if (Character.isUpperCase(charAt)) {
                        if (compatibleWithJavaBean) {
                            decapitalize = Introspector.decapitalize(name2.substring(2));
                        } else {
                            decapitalize = Character.toLowerCase(name2.charAt(2)) + name2.substring(3);
                        }
                    } else if (charAt == '_') {
                        decapitalize = name2.substring(3);
                    } else if (charAt == 'f') {
                        decapitalize = name2.substring(2);
                    }
                    Field field3 = ParserConfig.getField(cls, decapitalize);
                    if (field3 == null) {
                        field2 = ParserConfig.getField(cls, name2);
                    } else {
                        field2 = field3;
                    }
                    if (field2 != null) {
                        jSONField = (JSONField) field2.getAnnotation(JSONField.class);
                        if (jSONField != null) {
                            if (jSONField.serialize()) {
                                if (jSONField.name().length() != 0) {
                                    decapitalize = jSONField.name();
                                    if (map != null) {
                                        name = (String) map.get(decapitalize);
                                        if (name == null) {
                                        }
                                        if (map != null) {
                                            name = (String) map.get(name);
                                            if (name == null) {
                                            }
                                        }
                                        linkedHashMap.put(name, new FieldInfo(name, method, field2));
                                    }
                                }
                            }
                        }
                    }
                    name = decapitalize;
                    if (map != null) {
                        name = (String) map.get(name);
                        if (name == null) {
                        }
                    }
                    linkedHashMap.put(name, new FieldInfo(name, method, field2));
                }
            }
        }
        for (Field field4 : cls.getFields()) {
            if (!Modifier.isStatic(field4.getModifiers())) {
                jSONField = (JSONField) field4.getAnnotation(JSONField.class);
                decapitalize = field4.getName();
                if (jSONField != null) {
                    if (jSONField.serialize()) {
                        if (jSONField.name().length() != 0) {
                            name = jSONField.name();
                            if (map != null) {
                                name = (String) map.get(name);
                                if (name == null) {
                                }
                            }
                            if (!linkedHashMap.containsKey(name)) {
                                linkedHashMap.put(name, new FieldInfo(name, null, field4));
                            }
                        }
                    }
                }
                name = decapitalize;
                if (map != null) {
                    name = (String) map.get(name);
                    if (name == null) {
                    }
                }
                if (!linkedHashMap.containsKey(name)) {
                    linkedHashMap.put(name, new FieldInfo(name, null, field4));
                }
            }
        }
        List<FieldInfo> arrayList = new ArrayList();
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        String[] strArr;
        if (jSONType != null) {
            String[] orders = jSONType.orders();
            if (orders == null || orders.length != linkedHashMap.size()) {
                obj = null;
                strArr = orders;
            } else {
                obj = 1;
                for (Object containsKey : orders) {
                    if (!linkedHashMap.containsKey(containsKey)) {
                        obj = null;
                        break;
                    }
                }
                strArr = orders;
            }
        } else {
            obj = null;
            strArr = null;
        }
        if (obj != null) {
            for (Object obj2 : r2) {
                arrayList.add((FieldInfo) linkedHashMap.get(obj2));
            }
        } else {
            for (FieldInfo add : linkedHashMap.values()) {
                arrayList.add(add);
            }
            if (z) {
                Collections.sort(arrayList);
            }
        }
        return arrayList;
    }

    public static JSONField getSupperMethodAnnotation(Class<?> cls, Method method) {
        for (Class methods : cls.getInterfaces()) {
            for (Method method2 : methods.getMethods()) {
                if (method2.getName().equals(method.getName()) && method2.getParameterTypes().length == method.getParameterTypes().length) {
                    int i;
                    Object obj;
                    for (i = 0; i < method2.getParameterTypes().length; i++) {
                        if (!method2.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                            obj = null;
                            break;
                        }
                    }
                    i = 1;
                    if (obj != null) {
                        JSONField jSONField = (JSONField) method2.getAnnotation(JSONField.class);
                        if (jSONField != null) {
                            return jSONField;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (!(jSONType == null || jSONType.ignores() == null)) {
            for (String equalsIgnoreCase : jSONType.ignores()) {
                if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
        }
        if (cls.getSuperclass() == Object.class || cls.getSuperclass() == null || !isJSONTypeIgnore(cls.getSuperclass(), str)) {
            return false;
        }
        return true;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        return Object.class;
    }
}
