package ch.qos.logback.core.joran.spi;

public class NoAutoStartUtil {
    public static boolean notMarkedWithNoAutoStart(Object obj) {
        if (obj == null) {
            return false;
        }
        return ((NoAutoStart) obj.getClass().getAnnotation(NoAutoStart.class)) == null;
    }
}
