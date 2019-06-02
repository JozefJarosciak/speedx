package com.avos.avoscloud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.util.Base64;
import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.avos.avoscloud.LogUtil.log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TimeZone;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class AVUtils {
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL_DATE_FORMAT = new ThreadLocal();
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;
    public static final int TYPE_WIFI = 1;
    static AtomicInteger acu = new AtomicInteger(SupportMenu.CATEGORY_MASK);
    public static String classNameTag = "className";
    private static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    static Pattern emailPattern = Pattern.compile("^\\w+?@\\w+?[.]\\w+");
    private static Map<Class<?>, Field[]> fieldsMap = Collections.synchronizedMap(new WeakHashMap());
    public static final String objectIdTag = "objectId";
    static Pattern pattern = Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]*$");
    static Pattern phoneNumPattern = Pattern.compile("1\\d{10}");
    static Random random = new Random();
    public static final String typeTag = "__type";
    static Pattern verifyCodePattern = Pattern.compile("\\d{6}");

    public static Map<String, Object> createArrayOpMap(String str, String str2, Collection<?> collection) {
        Map hashMap = new HashMap();
        hashMap.put("__op", str2);
        List arrayList = new ArrayList();
        for (Object parsedObject : collection) {
            arrayList.add(getParsedObject(parsedObject));
        }
        hashMap.put("objects", arrayList);
        Map<String, Object> hashMap2 = new HashMap();
        hashMap2.put(str, hashMap);
        return hashMap2;
    }

    public static Field[] getAllFiels(Class<?> cls) {
        if (cls == null || cls == Object.class) {
            return new Field[0];
        }
        Field[] fieldArr = (Field[]) fieldsMap.get(cls);
        if (fieldArr != null) {
            return fieldArr;
        }
        Object declaredFields;
        int length;
        List<Field[]> arrayList = new ArrayList();
        int i = 0;
        while (cls != null && cls != Object.class) {
            declaredFields = cls.getDeclaredFields();
            if (declaredFields != null) {
                length = declaredFields.length;
            } else {
                length = 0;
            }
            length += i;
            arrayList.add(declaredFields);
            cls = cls.getSuperclass();
            i = length;
        }
        declaredFields = new Field[i];
        i = 0;
        for (Field[] fieldArr2 : arrayList) {
            if (fieldArr2 != null) {
                for (Field accessible : fieldArr2) {
                    accessible.setAccessible(true);
                }
                System.arraycopy(fieldArr2, 0, declaredFields, i, fieldArr2.length);
                length = fieldArr2.length + i;
            } else {
                length = i;
            }
            i = length;
        }
        fieldsMap.put(cls, declaredFields);
        return declaredFields;
    }

    public static boolean checkEmailAddress(String str) {
        return emailPattern.matcher(str).find();
    }

    public static boolean checkMobilePhoneNumber(String str) {
        return phoneNumPattern.matcher(str).find();
    }

    public static boolean checkMobileVerifyCode(String str) {
        return verifyCodePattern.matcher(str).find();
    }

    public static void checkClassName(String str) {
        if (isBlankString(str)) {
            throw new IllegalArgumentException("Blank class name");
        } else if (!pattern.matcher(str).matches()) {
            throw new IllegalArgumentException("Invalid class name");
        }
    }

    public static boolean isBlankString(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isBlankContent(String str) {
        return isBlankString(str) || str.trim().equals("{}");
    }

    public static boolean contains(Map<String, Object> map, String str) {
        return map.containsKey(str);
    }

    public static Map<String, Object> createDeleteOpMap(String str) {
        Map hashMap = new HashMap();
        hashMap.put("__op", "Delete");
        Map<String, Object> hashMap2 = new HashMap();
        hashMap2.put(str, hashMap);
        return hashMap2;
    }

    public static Map<String, Object> createPointerArrayOpMap(String str, String str2, Collection<AVObject> collection) {
        Map hashMap = new HashMap();
        hashMap.put("__op", str2);
        List arrayList = new ArrayList();
        for (AVObject mapFromPointerObject : collection) {
            arrayList.add(mapFromPointerObject(mapFromPointerObject));
        }
        hashMap.put("objects", arrayList);
        Map<String, Object> hashMap2 = new HashMap();
        hashMap2.put(str, hashMap);
        return hashMap2;
    }

    public static Map<String, Object> createStringObjectMap(String str, Object obj) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(str, obj);
        return hashMap;
    }

    public static Map<String, Object> mapFromPointerObject(AVObject aVObject) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, "Pointer");
        hashMap.put("className", aVObject.internalClassName());
        if (!isBlankString(aVObject.getObjectId())) {
            hashMap.put(objectIdTag, aVObject.getObjectId());
        }
        return hashMap;
    }

    public static Map<String, Object> mapFromUserObjectId(String str) {
        if (isBlankString(str)) {
            return null;
        }
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, "Pointer");
        hashMap.put("className", "_User");
        hashMap.put(objectIdTag, str);
        return hashMap;
    }

    public static Map<String, String> mapFromChildObject(AVObject aVObject, String str) {
        String internalId = aVObject.internalId();
        Map<String, String> hashMap = new HashMap(3);
        hashMap.put("cid", internalId);
        hashMap.put("className", aVObject.getClassName());
        hashMap.put(Action.KEY_ATTRIBUTE, str);
        return hashMap;
    }

    public static boolean isDigitString(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Date dateFromString(String str) {
        if (isBlankString(str)) {
            return null;
        }
        if (isDigitString(str)) {
            return new Date(Long.parseLong(str));
        }
        Date parse;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) THREAD_LOCAL_DATE_FORMAT.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(dateFormat);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            THREAD_LOCAL_DATE_FORMAT.set(simpleDateFormat);
        }
        try {
            parse = simpleDateFormat.parse(str);
        } catch (Exception e) {
            log.m3519e(e.toString());
            parse = null;
        }
        return parse;
    }

    public static String stringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static Map<String, Object> mapFromDate(Date date) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, "Date");
        hashMap.put("iso", stringFromDate(date));
        return hashMap;
    }

    public static Date dateFromMap(Map<String, Object> map) {
        return dateFromString((String) map.get("iso"));
    }

    public static Map<String, Object> mapFromGeoPoint(AVGeoPoint aVGeoPoint) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, "GeoPoint");
        hashMap.put("latitude", Double.valueOf(aVGeoPoint.getLatitude()));
        hashMap.put("longitude", Double.valueOf(aVGeoPoint.getLongitude()));
        return hashMap;
    }

    public static AVGeoPoint geoPointFromMap(Map<String, Object> map) {
        return new AVGeoPoint(((Number) map.get("latitude")).doubleValue(), ((Number) map.get("longitude")).doubleValue());
    }

    public static AVObject objectFromRelationMap(Map<String, Object> map) {
        return objectFromClassName((String) map.get(classNameTag));
    }

    public static Map<String, Object> mapFromByteArray(byte[] bArr) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, "Bytes");
        hashMap.put("base64", Base64.encodeToString(bArr, 2));
        return hashMap;
    }

    public static byte[] dataFromMap(Map<String, Object> map) {
        return Base64.decode((String) map.get("base64"), 2);
    }

    public static String jsonStringFromMapWithNull(Object obj) {
        if (AVOSCloud.showInternalDebugLog()) {
            return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.PrettyFormat);
        }
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
    }

    public static String jsonStringFromObjectWithNull(Object obj) {
        if (AVOSCloud.showInternalDebugLog()) {
            return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.PrettyFormat);
        }
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullNumberAsZero);
    }

    public static Map<String, Object> mapFromFile(AVFile aVFile) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(typeTag, AVFile.className());
        hashMap.put("metaData", aVFile.getMetaData());
        switch (AVOSCloud.getStorageType()) {
            case StorageTypeAV:
                hashMap.put("name", aVFile.getName());
                break;
            case StorageTypeQiniu:
            case StorageTypeS3:
                hashMap.put("id", aVFile.getName());
                break;
        }
        return hashMap;
    }

    public static AVFile fileFromMap(Map<String, Object> map) {
        AVFile aVFile = new AVFile("", "");
        copyPropertiesFromMapToObject(map, aVFile);
        Object obj = map.get("metaData");
        if (obj != null && (obj instanceof Map)) {
            aVFile.getMetaData().putAll((Map) obj);
        }
        if (isBlankString((String) aVFile.getMetaData("_name"))) {
            aVFile.getMetaData().put("_name", aVFile.getName());
        }
        switch (AVOSCloud.getStorageType()) {
            case StorageTypeQiniu:
            case StorageTypeS3:
                aVFile.setName((String) map.get(objectIdTag));
                break;
        }
        return aVFile;
    }

    public static AVObject parseObjectFromMap(Map<String, Object> map) {
        AVObject newAVObjectByClassName = newAVObjectByClassName((String) map.get(classNameTag));
        newAVObjectByClassName.setObjectId((String) map.get(objectIdTag));
        copyPropertiesFromMapToAVObject(map, newAVObjectByClassName);
        return newAVObjectByClassName;
    }

    public static String restfulServerData(Map<String, ?> map) {
        if (map == null) {
            return "{}";
        }
        return jsonStringFromMapWithNull(getParsedMap(map));
    }

    public static boolean hasProperty(Class<?> cls, String str) {
        for (Field name : getAllFiels(cls)) {
            if (name.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkAndSetValue(Class<?> cls, Object obj, String str, Object obj2) {
        if (cls == null) {
            return false;
        }
        try {
            for (Field field : getAllFiels(cls)) {
                if (field.getName().equals(str) && (field.getType().isInstance(obj2) || obj2 == null)) {
                    field.set(obj, obj2);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void updatePropertyFromMap(AVObject aVObject, String str, Map<String, Object> map) {
        String str2 = (String) map.get(objectIdTag);
        String str3 = (String) map.get(typeTag);
        if (str3 == null && str2 == null) {
            aVObject.put(str, map, false);
        } else if (isGeoPoint(str3)) {
            aVObject.put(str, geoPointFromMap(map), false);
        } else if (isDate(str3)) {
            aVObject.put(str, dateFromMap(map), false);
        } else if (isData(str3)) {
            aVObject.put(str, dataFromMap(map), false);
        } else if (isFile(str3)) {
            aVObject.put(str, fileFromMap(map), false);
        } else if (isFileFromUrulu(map)) {
            aVObject.put(str, fileFromMap(map), false);
        } else if (isRelation(str3)) {
            aVObject.addRelationFromServer(str, (String) map.get(classNameTag), false);
        } else if (isPointer(str3) || !(isBlankString(str2) || str3 == null)) {
            aVObject.put(str, parseObjectFromMap(map), false);
        } else {
            aVObject.put(str, map, false);
        }
    }

    public static void updatePropertyFromList(AVObject aVObject, String str, Collection<Object> collection) {
        aVObject.put(str, getObjectFrom((Collection) collection), false);
    }

    public static void copyPropertiesFromJsonStringToAVObject(String str, AVObject aVObject) {
        if (!isBlankString(str)) {
            try {
                copyPropertiesFromMapToAVObject(JSONHelper.mapFromString(str), aVObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyPropertiesFromMapToAVObject(Map<String, Object> map, AVObject aVObject) {
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str == null || !str.startsWith("_")) {
                Object value = entry.getValue();
                if (checkAndSetValue(aVObject.getClass(), aVObject, str, value)) {
                    if (!(str.startsWith("_") || AVObject.INVALID_KEYS.contains(str))) {
                        aVObject.put(str, value, false);
                    }
                } else if (value instanceof Collection) {
                    updatePropertyFromList(aVObject, str, (Collection) value);
                } else if (value instanceof Map) {
                    updatePropertyFromMap(aVObject, str, (Map) value);
                } else if (!str.startsWith("_")) {
                    aVObject.put(str, value, false);
                }
            }
        }
    }

    public static void copyPropertiesFromMapToObject(Map<String, Object> map, Object obj) {
        for (Entry entry : map.entrySet()) {
            if (checkAndSetValue(obj.getClass(), obj, (String) entry.getKey(), entry.getValue())) {
            }
        }
    }

    public static Class getClass(Map<String, ?> map) {
        Object obj = map.get(typeTag);
        if (obj == null || !(obj instanceof String)) {
            return Map.class;
        }
        if (obj.equals("Pointer")) {
            return AVObject.class;
        }
        if (obj.equals("GeoPoint")) {
            return AVGeoPoint.class;
        }
        if (obj.equals("Bytes")) {
            return byte[].class;
        }
        if (obj.equals("Date")) {
            return Date.class;
        }
        return Map.class;
    }

    public static boolean isRelation(String str) {
        return str != null && str.equals("Relation");
    }

    public static boolean isPointer(String str) {
        return str != null && str.equals("Pointer");
    }

    public static boolean isGeoPoint(String str) {
        return str != null && str.equals("GeoPoint");
    }

    public static boolean isACL(String str) {
        return str != null && str.equals("ACL");
    }

    public static boolean isDate(String str) {
        return str != null && str.equals("Date");
    }

    public static boolean isData(String str) {
        return str != null && str.equals("Bytes");
    }

    public static boolean isFile(String str) {
        return str != null && str.equals("File");
    }

    public static boolean isFileFromUrulu(Map<String, Object> map) {
        return (map.get("mime_type") != null ? 1 : 0) & true;
    }

    public static AVObject objectFromClassName(String str) {
        if (str.equals(AVPowerfulUtils.getAVClassName(AVUser.class.getSimpleName()))) {
            return AVUser.newAVUser();
        }
        return newAVObjectByClassName(str);
    }

    public static AVObject newAVObjectByClassName(String str) {
        if (str.equals(AVRole.className)) {
            return new AVRole();
        }
        if (str.equals(AVUser.userClassName())) {
            return AVUser.newAVUser();
        }
        Class subClass = AVObject.getSubClass(str);
        if (subClass == null) {
            return new AVObject(str);
        }
        try {
            return (AVObject) subClass.newInstance();
        } catch (Throwable e) {
            throw new AVRuntimeException("New subclass instance failed.", e);
        }
    }

    public static AVObject newAVObjectByClassName(String str, String str2) {
        if (!isBlankString(str)) {
            str2 = str;
        }
        return newAVObjectByClassName(str2);
    }

    public static final <T> T getFromJSON(String str, Class<T> cls) {
        return JSON.parseObject(str, (Class) cls);
    }

    public static final <T> String toJSON(T t) {
        if (!AVOSCloud.showInternalDebugLog()) {
            return JSON.toJSONString(t);
        }
        return JSON.toJSONString((Object) t, SerializerFeature.PrettyFormat);
    }

    static Map<String, Object> getParsedMap(Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap(map.size());
        for (Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), getParsedObject(entry.getValue()));
        }
        return hashMap;
    }

    static List getParsedList(Collection collection) {
        List arrayList = new ArrayList(collection.size());
        for (Object parsedObject : collection) {
            arrayList.add(getParsedObject(parsedObject));
        }
        return arrayList;
    }

    public static Object getParsedObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return getParsedMap((Map) obj);
        }
        if (obj instanceof Collection) {
            return getParsedList((Collection) obj);
        }
        if (obj instanceof AVObject) {
            return mapFromPointerObject((AVObject) obj);
        }
        if (obj instanceof AVGeoPoint) {
            return mapFromGeoPoint((AVGeoPoint) obj);
        }
        if (obj instanceof Date) {
            return mapFromDate((Date) obj);
        }
        if (obj instanceof byte[]) {
            return mapFromByteArray((byte[]) obj);
        }
        if (obj instanceof AVFile) {
            return mapFromFile((AVFile) obj);
        }
        if (obj instanceof JSONObject) {
            return JSON.parse(obj.toString());
        }
        if (obj instanceof JSONArray) {
            return JSON.parse(obj.toString());
        }
        return obj;
    }

    static List getObjectFrom(Collection collection) {
        List arrayList = new ArrayList();
        for (Object objectFrom : collection) {
            arrayList.add(getObjectFrom(objectFrom));
        }
        return arrayList;
    }

    static Object getObjectFrom(Map<String, Object> map) {
        Object obj = map.get(typeTag);
        if (obj == null || !(obj instanceof String)) {
            Map<String, Object> hashMap = new HashMap(map.size());
            for (Entry entry : map.entrySet()) {
                hashMap.put((String) entry.getKey(), getObjectFrom(entry.getValue()));
            }
            return hashMap;
        } else if (obj.equals("Pointer") || obj.equals("Object")) {
            AVObject objectFromClassName = objectFromClassName((String) map.get("className"));
            map.remove(typeTag);
            copyPropertiesFromMapToAVObject(map, objectFromClassName);
            return objectFromClassName;
        } else if (obj.equals("GeoPoint")) {
            return geoPointFromMap(map);
        } else {
            if (obj.equals("Bytes")) {
                return dataFromMap(map);
            }
            if (obj.equals("Date")) {
                return dateFromMap(map);
            }
            if (obj.equals("Relation")) {
                return objectFromRelationMap(map);
            }
            if (obj.equals("File")) {
                return fileFromMap(map);
            }
            return map;
        }
    }

    static Object getObjectFrom(Object obj) {
        if (obj instanceof Collection) {
            return getObjectFrom((Collection) obj);
        }
        if (obj instanceof Map) {
            return getObjectFrom((Map) obj);
        }
        return obj;
    }

    public static String md5(String str) {
        try {
            return computeMD5(str.getBytes("UTF-8"));
        } catch (Throwable e) {
            throw new RuntimeException("Huh,UTF-8 should be supported?", e);
        }
    }

    public static String getRandomString(int i) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    public static int getNextIMRequestId() {
        int incrementAndGet = acu.incrementAndGet();
        if (incrementAndGet > 65535) {
            while (incrementAndGet > 65535 && !acu.compareAndSet(incrementAndGet, SupportMenu.CATEGORY_MASK)) {
                incrementAndGet = acu.get();
            }
        }
        return incrementAndGet;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean isConnected(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            log.m3520e("Please add ACCESS_NETWORK_STATE permission in your manifest", e);
            return true;
        } catch (Exception e2) {
            log.m3520e("Exception: ", e2);
        }
        return false;
    }

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    public static String joinCollection(Collection<String> collection, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str2 : collection) {
            Object obj2;
            if (obj != null) {
                stringBuilder.append(str2);
                obj2 = null;
            } else {
                stringBuilder.append(str).append(str2);
                obj2 = obj;
            }
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public static String stringFromBytes(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            log.m3519e("context is null");
            return false;
        } else if (context.checkCallingOrSelfPermission(str) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPushServiceAvailable(Context context, Class cls) {
        if (context.getPackageManager().queryIntentServices(new Intent(context, cls), 65536).size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String fileMd5(String str) throws IOException {
        return computeMD5(readFile(str));
    }

    public static byte[] readFile(String str) throws IOException {
        return readFile(new File(str));
    }

    public static byte[] readFile(File file) throws IOException {
        Closeable randomAccessFile = new RandomAccessFile(file, "r");
        try {
            long length = randomAccessFile.length();
            int i = (int) length;
            if (((long) i) != length) {
                throw new IOException("File size >= 2 GB");
            }
            byte[] bArr = new byte[i];
            randomAccessFile.readFully(bArr);
            return bArr;
        } finally {
            AVPersistenceUtils.closeQuietly(randomAccessFile);
        }
    }

    public static String computeMD5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr, 0, bArr.length);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(toHexString);
            }
            return stringBuffer.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static String getJSONString(com.alibaba.fastjson.JSONObject jSONObject, String str, String str2) {
        if (jSONObject.containsKey(str)) {
            return jSONObject.getString(str);
        }
        return str2;
    }

    static long getJSONInteger(com.alibaba.fastjson.JSONObject jSONObject, String str, long j) {
        if (jSONObject.containsKey(str)) {
            return (long) jSONObject.getInteger(str).intValue();
        }
        return j;
    }

    public static int getConnectivityStatus(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (!activeNetworkInfo.isConnected()) {
                return 0;
            }
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0) {
                return 2;
            }
        }
        return 0;
    }

    public static String getConnectivityStatusString(Context context) {
        int connectivityStatus = getConnectivityStatus(context);
        if (connectivityStatus == 1) {
            return "Wifi enabled";
        }
        if (connectivityStatus == 2) {
            return "Mobile data enabled";
        }
        if (connectivityStatus == 0) {
            return "Not connected to Internet";
        }
        return null;
    }

    public static String getArchiveRequestFileName(String str, String str2, String str3, String str4, String str5) {
        if (str3.equalsIgnoreCase("put")) {
            return md5(str4 + str5);
        }
        if (str3.equalsIgnoreCase("post")) {
            return str2;
        }
        if (!str3.equalsIgnoreCase("delete")) {
            return md5(str4 + str5);
        }
        if (isBlankString(str)) {
            return str2;
        }
        return md5(str4 + str5);
    }

    public static int collectionNonNullCount(Collection collection) {
        int i = 0;
        for (Object obj : collection) {
            if (obj != null) {
                i++;
            }
        }
        return i;
    }

    public static String urlCleanLastSlash(String str) {
        if (isBlankString(str) || !str.endsWith("/")) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    public static String getSessionKey(String str) {
        StringBuilder stringBuilder = new StringBuilder(AVOSCloud.applicationId);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }

    public static void ensureElementsNotNull(List<String> list, String str) {
        for (String str2 : list) {
            if (str2 == null) {
                throw new NullPointerException(str);
            }
        }
    }

    public static boolean compareNumberString(String str, String str2) {
        return Double.compare(Double.parseDouble(str), Double.parseDouble(str2)) == 1;
    }

    public static String Base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), 10);
    }

    public static AsyncHttpClient getDirectlyClientForUse() {
        return PaasClient.storageInstance().clientInstance(false);
    }

    public static Handler getUIThreadHandler() {
        return AVOSCloud.handler;
    }

    public static Map<String, Object> createMap(String str, Object obj) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(str, obj);
        return hashMap;
    }

    public static boolean checkResponseType(int i, String str, String str2, GenericObjectCallback genericObjectCallback) {
        if (i <= 0 || PaasClient.isJSONResponse(str2)) {
            return false;
        }
        if (genericObjectCallback != null) {
            genericObjectCallback.onFailure(i, new AVException(107, "Wrong response content type:" + str2), str);
        }
        return true;
    }

    public static boolean checkDNSException(int i, Throwable th) {
        return isConnected(AVOSCloud.applicationContext) && i == 0 && (th instanceof IOException) && th.getMessage() != null && th.getMessage().toLowerCase().contains("unknownhostexception");
    }

    public static String getHostName(String str) throws URISyntaxException {
        String host = new URI(str).getHost();
        return host.startsWith("www.") ? host.substring(4) : host;
    }

    public static String getAVObjectClassName(Class<? extends AVObject> cls) {
        return AVObject.getSubClassName(cls);
    }

    public static String getAVObjectCreatedAt(AVObject aVObject) {
        return aVObject.createdAt;
    }

    public static String getAVObjectUpdatedAt(AVObject aVObject) {
        return aVObject.updatedAt;
    }

    public static String getEncodeUrl(String str, Map<String, String> map) {
        return AsyncHttpClient.getUrlWithQueryString(true, str, new RequestParams(map));
    }
}
