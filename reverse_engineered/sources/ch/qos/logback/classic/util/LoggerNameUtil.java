package ch.qos.logback.classic.util;

public class LoggerNameUtil {
    public static int getFirstSeparatorIndexOf(String str) {
        return getSeparatorIndexOf(str, 0);
    }

    public static int getSeparatorIndexOf(String str, int i) {
        int indexOf = str.indexOf(46, i);
        int indexOf2 = str.indexOf(36, i);
        return (indexOf == -1 && indexOf2 == -1) ? -1 : indexOf == -1 ? indexOf2 : (indexOf2 == -1 || indexOf < indexOf2) ? indexOf : indexOf2;
    }
}
