package com.avos.avoscloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
    public static Object toJSON(Object obj) throws JSONException {
        if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            Map map = (Map) obj;
            for (Object next : map.keySet()) {
                jSONObject.put(next.toString(), toJSON(map.get(next)));
            }
            return jSONObject;
        } else if (!(obj instanceof Iterable)) {
            return obj;
        } else {
            JSONArray jSONArray = new JSONArray();
            for (Object next2 : (Iterable) obj) {
                jSONArray.put(next2);
            }
            return jSONArray;
        }
    }

    public static boolean isEmptyObject(JSONObject jSONObject) {
        return jSONObject.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject jSONObject, String str) throws JSONException {
        return toMap(jSONObject.getJSONObject(str));
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, fromJson(jSONObject.get(str)));
        }
        return hashMap;
    }

    public static Map<String, Object> mapFromString(String str) throws JSONException {
        return toMap(new JSONObject(str));
    }

    public static List toList(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(fromJson(jSONArray.get(i)));
        }
        return arrayList;
    }

    private static Object fromJson(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        return obj;
    }

    public static String toJsonString(Map<String, Object> map) {
        return new JSONObject(map).toString();
    }
}
