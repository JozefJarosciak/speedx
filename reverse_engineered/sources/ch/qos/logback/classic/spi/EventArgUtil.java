package ch.qos.logback.classic.spi;

public class EventArgUtil {
    public static Object[] arrangeArguments(Object[] objArr) {
        return objArr;
    }

    public static final Throwable extractThrowable(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        return obj instanceof Throwable ? (Throwable) obj : null;
    }

    public static boolean successfulExtraction(Throwable th) {
        return th != null;
    }

    public static Object[] trimmedCopy(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object obj = new Object[length];
        System.arraycopy(objArr, 0, obj, 0, length);
        return obj;
    }
}
