package ch.qos.logback.classic.pattern;

public class ClassNameOnlyAbbreviator implements Abbreviator {
    public String abbreviate(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }
}
