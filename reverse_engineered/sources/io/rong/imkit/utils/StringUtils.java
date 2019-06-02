package io.rong.imkit.utils;

public class StringUtils {
    private static final String SEPARATOR = "#@6RONG_CLOUD9@#";

    public static String getKey(String str, String str2) {
        return str + "#@6RONG_CLOUD9@#" + str2;
    }

    public static String getArg1(String str) {
        if (str.contains("#@6RONG_CLOUD9@#")) {
            return str.substring(0, str.indexOf("#@6RONG_CLOUD9@#"));
        }
        return null;
    }

    public static String getArg2(String str) {
        if (str.contains("#@6RONG_CLOUD9@#")) {
            return str.substring(str.indexOf("#@6RONG_CLOUD9@#") + "#@6RONG_CLOUD9@#".length(), str.length());
        }
        return null;
    }
}
