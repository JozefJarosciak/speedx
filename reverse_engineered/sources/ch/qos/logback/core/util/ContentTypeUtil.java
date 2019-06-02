package ch.qos.logback.core.util;

import com.mapbox.services.directions.v4.DirectionsCriteria;

public class ContentTypeUtil {
    public static String getSubType(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            return null;
        }
        indexOf++;
        return indexOf < str.length() ? str.substring(indexOf) : null;
    }

    public static boolean isTextual(String str) {
        return str == null ? false : str.startsWith(DirectionsCriteria.INSTRUCTIONS_TEXT);
    }
}
