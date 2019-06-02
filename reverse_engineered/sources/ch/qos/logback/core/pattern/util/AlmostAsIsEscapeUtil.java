package ch.qos.logback.core.pattern.util;

public class AlmostAsIsEscapeUtil extends RestrictedEscapeUtil {
    public void escape(String str, StringBuffer stringBuffer, char c, int i) {
        super.escape("%)", stringBuffer, c, i);
    }
}
