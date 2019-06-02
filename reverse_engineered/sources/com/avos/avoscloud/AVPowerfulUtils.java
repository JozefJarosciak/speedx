package com.avos.avoscloud;

import java.util.HashMap;
import java.util.Map;

public class AVPowerfulUtils {
    private static final String ENDPOINT = "endpoint";
    private static final String PARSE_CLASSNAME = "dbClassName";
    private static Map<String, Map<String, String>> powerfulTable = new HashMap();

    static {
        createTable();
    }

    public static void createSettings(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put(ENDPOINT, str2);
        hashMap.put(PARSE_CLASSNAME, str3);
        powerfulTable.put(str, hashMap);
    }

    private static void createTable() {
        createSettings(AVUser.class.getSimpleName(), "users", "_User");
        createSettings("_User", "users", "_User");
        createSettings(AVRole.class.getSimpleName(), "roles", AVRole.className);
        createSettings(AVRole.className, "roles", AVRole.className);
        createSettings(AVFile.class.getSimpleName(), "files", "_File");
        createSettings("_File", "files", "_File");
    }

    private static String get(String str, String str2) {
        String str3 = "";
        if (!powerfulTable.containsKey(str)) {
            return str3;
        }
        str3 = (String) ((Map) powerfulTable.get(str)).get(str2);
        if (str3 == null) {
            return "";
        }
        return str3;
    }

    private static String getAVClassEndpoint(String str, String str2, String str3) {
        String str4 = get(str, ENDPOINT);
        if (!AVUtils.isBlankString(str4)) {
            return str4;
        }
        if (AVUtils.isBlankString(str3)) {
            return String.format("classes/%s", new Object[]{str2});
        }
        return String.format("classes/%s/%s", new Object[]{str2, str3});
    }

    private static String getAVUserEndpoint(AVUser aVUser) {
        String str = get(AVUser.class.getSimpleName(), ENDPOINT);
        if (AVUtils.isBlankString(aVUser.getObjectId())) {
            return str;
        }
        return String.format("%s/%s", new Object[]{str, aVUser.getObjectId()});
    }

    private static String getAVRoleEndpoint(AVRole aVRole) {
        String str = get(AVRole.class.getSimpleName(), ENDPOINT);
        if (AVUtils.isBlankString(aVRole.getObjectId())) {
            return str;
        }
        return String.format("%s/%s", new Object[]{str, aVRole.getObjectId()});
    }

    public static String getEndpoint(String str) {
        String str2 = get(str, ENDPOINT);
        if (!AVUtils.isBlankString(str2)) {
            return str2;
        }
        if (AVUtils.isBlankString(str)) {
            throw new AVRuntimeException("Blank class name");
        }
        return String.format("classes/%s", new Object[]{str});
    }

    public static String getEndpoint(Object obj) {
        return getEndpoint(obj, false);
    }

    public static String getEndpoint(Object obj, boolean z) {
        if (obj instanceof AVUser) {
            return getAVUserEndpoint((AVUser) obj);
        }
        if (obj instanceof AVRole) {
            return getAVRoleEndpoint((AVRole) obj);
        }
        if (!(obj instanceof AVObject)) {
            return getEndpoint(obj.getClass().getSimpleName());
        }
        AVObject aVObject = (AVObject) obj;
        Class cls = aVObject.getClass();
        String simpleName = cls.getSimpleName();
        String subClassName = AVObject.getSubClassName(cls);
        if (subClassName != null) {
            return getAVClassEndpoint(simpleName, subClassName, aVObject.getObjectId());
        }
        return getAVClassEndpoint(simpleName, aVObject.getClassName(), aVObject.getObjectId());
    }

    public static String getBatchEndpoint(String str, AVObject aVObject) {
        return getBatchEndpoint(str, aVObject, false);
    }

    public static String getBatchEndpoint(String str, AVObject aVObject, boolean z) {
        return String.format("/%s/%s", new Object[]{str, getEndpoint(aVObject, z)});
    }

    public static String getEndpointByAVClassName(String str, String str2) {
        String endpoint = getEndpoint(str);
        if (AVUtils.isBlankString(endpoint)) {
            return endpoint;
        }
        return String.format("%s/%s", new Object[]{endpoint, str2});
    }

    public static String getAVClassName(String str) {
        return get(str, PARSE_CLASSNAME);
    }

    public static String getFollowEndPoint(String str, String str2) {
        return String.format("users/%s/friendship/%s", new Object[]{str, str2});
    }

    public static String getFollowersEndPoint(String str) {
        return String.format("users/%s/followers", new Object[]{str});
    }

    public static String getFolloweesEndPoint(String str) {
        return String.format("users/%s/followees", new Object[]{str});
    }

    public static String getFollowersAndFollowees(String str) {
        return String.format("users/%s/followersAndFollowees", new Object[]{str});
    }

    public static String getInternalIdFromRequestBody(Map map) {
        if (map.get("body") != null) {
            return (String) ((Map) map.get("body")).get("__internalId");
        }
        return null;
    }
}
