package ch.qos.logback.core.pattern.util;

public class RestrictedEscapeUtil implements IEscapeUtil {
    public void escape(String str, StringBuffer stringBuffer, char c, int i) {
        if (str.indexOf(c) >= 0) {
            stringBuffer.append(c);
            return;
        }
        stringBuffer.append("\\");
        stringBuffer.append(c);
    }
}
