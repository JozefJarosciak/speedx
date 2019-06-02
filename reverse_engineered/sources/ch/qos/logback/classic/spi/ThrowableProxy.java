package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThrowableProxy implements IThrowableProxy {
    private static final Method GET_SUPPRESSED_METHOD;
    private static final ThrowableProxy[] NO_SUPPRESSED = new ThrowableProxy[0];
    private boolean calculatedPackageData = false;
    private ThrowableProxy cause;
    private String className;
    int commonFrames;
    private String message;
    private transient PackagingDataCalculator packagingDataCalculator;
    StackTraceElementProxy[] stackTraceElementProxyArray;
    private ThrowableProxy[] suppressed = NO_SUPPRESSED;
    private Throwable throwable;

    static {
        Method method = null;
        try {
            method = Throwable.class.getMethod("getSuppressed", new Class[0]);
        } catch (NoSuchMethodException e) {
        }
        GET_SUPPRESSED_METHOD = method;
    }

    public ThrowableProxy(Throwable th) {
        int i = 0;
        this.throwable = th;
        this.className = th.getClass().getName();
        this.message = th.getMessage();
        this.stackTraceElementProxyArray = ThrowableProxyUtil.steArrayToStepArray(th.getStackTrace());
        Throwable cause = th.getCause();
        if (cause != null) {
            this.cause = new ThrowableProxy(cause);
            this.cause.commonFrames = ThrowableProxyUtil.findNumberOfCommonFrames(cause.getStackTrace(), this.stackTraceElementProxyArray);
        }
        if (GET_SUPPRESSED_METHOD != null) {
            try {
                Object invoke = GET_SUPPRESSED_METHOD.invoke(th, new Object[0]);
                if (invoke instanceof Throwable[]) {
                    Throwable[] thArr = (Throwable[]) invoke;
                    if (thArr.length > 0) {
                        this.suppressed = new ThrowableProxy[thArr.length];
                        while (i < thArr.length) {
                            this.suppressed[i] = new ThrowableProxy(thArr[i]);
                            this.suppressed[i].commonFrames = ThrowableProxyUtil.findNumberOfCommonFrames(thArr[i].getStackTrace(), this.stackTraceElementProxyArray);
                            i++;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
            }
        }
    }

    public void calculatePackagingData() {
        if (!this.calculatedPackageData) {
            PackagingDataCalculator packagingDataCalculator = getPackagingDataCalculator();
            if (packagingDataCalculator != null) {
                this.calculatedPackageData = true;
                packagingDataCalculator.calculate(this);
            }
        }
    }

    public void fullDump() {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElementProxy stackTraceElementProxy : this.stackTraceElementProxyArray) {
            stringBuilder.append('\t').append(stackTraceElementProxy.toString());
            ThrowableProxyUtil.subjoinPackagingData(stringBuilder, stackTraceElementProxy);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        }
        System.out.println(stringBuilder.toString());
    }

    public IThrowableProxy getCause() {
        return this.cause;
    }

    public String getClassName() {
        return this.className;
    }

    public int getCommonFrames() {
        return this.commonFrames;
    }

    public String getMessage() {
        return this.message;
    }

    public PackagingDataCalculator getPackagingDataCalculator() {
        if (this.throwable != null && this.packagingDataCalculator == null) {
            this.packagingDataCalculator = new PackagingDataCalculator();
        }
        return this.packagingDataCalculator;
    }

    public StackTraceElementProxy[] getStackTraceElementProxyArray() {
        return this.stackTraceElementProxyArray;
    }

    public IThrowableProxy[] getSuppressed() {
        return this.suppressed;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
}
