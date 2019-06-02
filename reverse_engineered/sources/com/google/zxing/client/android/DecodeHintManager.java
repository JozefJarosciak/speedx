package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class DecodeHintManager {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String TAG = DecodeHintManager.class.getSimpleName();

    private DecodeHintManager() {
    }

    private static Map<String, String> splitQuery(String str) {
        Map<String, String> hashMap = new HashMap();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '&') {
                i++;
            } else {
                int indexOf = str.indexOf(38, i);
                int indexOf2 = str.indexOf(61, i);
                if (indexOf < 0) {
                    Object decode;
                    Object obj;
                    if (indexOf2 < 0) {
                        decode = Uri.decode(str.substring(i).replace('+', ' '));
                        obj = "";
                    } else {
                        decode = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        obj = Uri.decode(str.substring(indexOf2 + 1).replace('+', ' '));
                    }
                    if (!hashMap.containsKey(decode)) {
                        hashMap.put(decode, obj);
                    }
                    return hashMap;
                } else if (indexOf2 < 0 || indexOf2 > indexOf) {
                    r0 = Uri.decode(str.substring(i, indexOf).replace('+', ' '));
                    if (!hashMap.containsKey(r0)) {
                        hashMap.put(r0, "");
                    }
                    i = indexOf + 1;
                } else {
                    r0 = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                    String decode2 = Uri.decode(str.substring(indexOf2 + 1, indexOf).replace('+', ' '));
                    if (!hashMap.containsKey(r0)) {
                        hashMap.put(r0, decode2);
                    }
                    i = indexOf + 1;
                }
            }
        }
        return hashMap;
    }

    static Map<DecodeHintType, ?> parseDecodeHints(Uri uri) {
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.isEmpty()) {
            return null;
        }
        Map splitQuery = splitQuery(encodedQuery);
        Map<DecodeHintType, ?> enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {
                CharSequence charSequence = (String) splitQuery.get(decodeHintType.name());
                if (charSequence != null) {
                    if (decodeHintType.getValueType().equals(Object.class)) {
                        enumMap.put(decodeHintType, charSequence);
                    } else if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else if (decodeHintType.getValueType().equals(String.class)) {
                        enumMap.put(decodeHintType, charSequence);
                    } else if (decodeHintType.getValueType().equals(Boolean.class)) {
                        if (charSequence.isEmpty()) {
                            enumMap.put(decodeHintType, Boolean.TRUE);
                        } else if ("0".equals(charSequence) || "false".equalsIgnoreCase(charSequence) || "no".equalsIgnoreCase(charSequence)) {
                            enumMap.put(decodeHintType, Boolean.FALSE);
                        } else {
                            enumMap.put(decodeHintType, Boolean.TRUE);
                        }
                    } else if (decodeHintType.getValueType().equals(int[].class)) {
                        Object obj;
                        if (!charSequence.isEmpty() && charSequence.charAt(charSequence.length() - 1) == CoreConstants.COMMA_CHAR) {
                            charSequence = charSequence.substring(0, charSequence.length() - 1);
                        }
                        String[] split = COMMA.split(charSequence);
                        Object obj2 = new int[split.length];
                        int i = 0;
                        while (i < split.length) {
                            try {
                                obj2[i] = Integer.parseInt(split[i]);
                                i++;
                            } catch (NumberFormatException e) {
                                Log.w(TAG, "Skipping array of integers hint " + decodeHintType + " due to invalid numeric value: '" + split[i] + CoreConstants.SINGLE_QUOTE_CHAR);
                                obj = null;
                            }
                        }
                        obj = obj2;
                        if (obj != null) {
                            enumMap.put(decodeHintType, obj);
                        }
                    } else {
                        Log.w(TAG, "Unsupported hint type '" + decodeHintType + "' of type " + decodeHintType.getValueType());
                    }
                }
            }
        }
        Log.i(TAG, "Hints from the URI: " + enumMap);
        return enumMap;
    }

    public static Map<DecodeHintType, Object> parseDecodeHints(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        Map<DecodeHintType, Object> enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {
                String name = decodeHintType.name();
                if (extras.containsKey(name)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else {
                        Object obj = extras.get(name);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put(decodeHintType, obj);
                        } else {
                            Log.w(TAG, "Ignoring hint " + decodeHintType + " because it is not assignable from " + obj);
                        }
                    }
                }
            }
        }
        Log.i(TAG, "Hints from the Intent: " + enumMap);
        return enumMap;
    }
}
