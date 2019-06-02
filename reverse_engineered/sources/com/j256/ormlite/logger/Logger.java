package com.j256.ormlite.logger;

import com.j256.ormlite.logger.Log.Level;
import java.util.Arrays;

public class Logger {
    private static final String ARG_STRING = "{}";
    private static final int ARG_STRING_LENGTH = ARG_STRING.length();
    private static final Object UNKNOWN_ARG = new Object();
    private final Log log;

    public Logger(Log log) {
        this.log = log;
    }

    public boolean isLevelEnabled(Level level) {
        return this.log.isLevelEnabled(level);
    }

    public void trace(String str) {
        innerLog(Level.TRACE, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(String str, Object obj) {
        innerLog(Level.TRACE, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(String str, Object obj, Object obj2) {
        innerLog(Level.TRACE, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.TRACE, null, str, obj, obj2, obj3, null);
    }

    public void trace(String str, Object[] objArr) {
        innerLog(Level.TRACE, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void trace(Throwable th, String str) {
        innerLog(Level.TRACE, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(Throwable th, String str, Object obj) {
        innerLog(Level.TRACE, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.TRACE, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void trace(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.TRACE, th, str, obj, obj2, obj3, null);
    }

    public void trace(Throwable th, String str, Object[] objArr) {
        innerLog(Level.TRACE, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void debug(String str) {
        innerLog(Level.DEBUG, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(String str, Object obj) {
        innerLog(Level.DEBUG, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(String str, Object obj, Object obj2) {
        innerLog(Level.DEBUG, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.DEBUG, null, str, obj, obj2, obj3, null);
    }

    public void debug(String str, Object[] objArr) {
        innerLog(Level.DEBUG, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void debug(Throwable th, String str) {
        innerLog(Level.DEBUG, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(Throwable th, String str, Object obj) {
        innerLog(Level.DEBUG, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.DEBUG, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void debug(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.DEBUG, th, str, obj, obj2, obj3, null);
    }

    public void debug(Throwable th, String str, Object[] objArr) {
        innerLog(Level.DEBUG, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void info(String str) {
        innerLog(Level.INFO, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(String str, Object obj) {
        innerLog(Level.INFO, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(String str, Object obj, Object obj2) {
        innerLog(Level.INFO, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void info(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.INFO, null, str, obj, obj2, obj3, null);
    }

    public void info(String str, Object[] objArr) {
        innerLog(Level.INFO, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void info(Throwable th, String str) {
        innerLog(Level.INFO, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(Throwable th, String str, Object obj) {
        innerLog(Level.INFO, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.INFO, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void info(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.INFO, th, str, obj, obj2, obj3, null);
    }

    public void info(Throwable th, String str, Object[] objArr) {
        innerLog(Level.INFO, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void warn(String str) {
        innerLog(Level.WARNING, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(String str, Object obj) {
        innerLog(Level.WARNING, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(String str, Object obj, Object obj2) {
        innerLog(Level.WARNING, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.WARNING, null, str, obj, obj2, obj3, null);
    }

    public void warn(String str, Object[] objArr) {
        innerLog(Level.WARNING, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void warn(Throwable th, String str) {
        innerLog(Level.WARNING, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(Throwable th, String str, Object obj) {
        innerLog(Level.WARNING, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.WARNING, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void warn(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.WARNING, th, str, obj, obj2, obj3, null);
    }

    public void warn(Throwable th, String str, Object[] objArr) {
        innerLog(Level.WARNING, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void error(String str) {
        innerLog(Level.ERROR, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(String str, Object obj) {
        innerLog(Level.ERROR, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(String str, Object obj, Object obj2) {
        innerLog(Level.ERROR, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void error(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.ERROR, null, str, obj, obj2, obj3, null);
    }

    public void error(String str, Object[] objArr) {
        innerLog(Level.ERROR, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void error(Throwable th, String str) {
        innerLog(Level.ERROR, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(Throwable th, String str, Object obj) {
        innerLog(Level.ERROR, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.ERROR, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void error(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.ERROR, th, str, obj, obj2, obj3, null);
    }

    public void error(Throwable th, String str, Object[] objArr) {
        innerLog(Level.ERROR, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void fatal(String str) {
        innerLog(Level.FATAL, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(String str, Object obj) {
        innerLog(Level.FATAL, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(String str, Object obj, Object obj2) {
        innerLog(Level.FATAL, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.FATAL, null, str, obj, obj2, obj3, null);
    }

    public void fatal(String str, Object[] objArr) {
        innerLog(Level.FATAL, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void fatal(Throwable th, String str) {
        innerLog(Level.FATAL, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable th, String str, Object obj) {
        innerLog(Level.FATAL, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable th, String str, Object obj, Object obj2) {
        innerLog(Level.FATAL, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(Level.FATAL, th, str, obj, obj2, obj3, null);
    }

    public void fatal(Throwable th, String str, Object[] objArr) {
        innerLog(Level.FATAL, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void log(Level level, String str) {
        innerLog(level, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Level level, String str, Object obj) {
        innerLog(level, null, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Level level, String str, Object obj, Object obj2) {
        innerLog(level, null, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3) {
        innerLog(level, null, str, obj, obj2, obj3, null);
    }

    public void log(Level level, String str, Object[] objArr) {
        innerLog(level, null, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    public void log(Level level, Throwable th, String str) {
        innerLog(level, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Level level, Throwable th, String str, Object obj) {
        innerLog(level, th, str, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Level level, Throwable th, String str, Object obj, Object obj2) {
        innerLog(level, th, str, obj, obj2, UNKNOWN_ARG, null);
    }

    public void log(Level level, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        innerLog(level, th, str, obj, obj2, obj3, null);
    }

    public void log(Level level, Throwable th, String str, Object[] objArr) {
        innerLog(level, th, str, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, objArr);
    }

    private void innerLog(Level level, Throwable th, String str, Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (this.log.isLevelEnabled(level)) {
            String buildFullMessage = buildFullMessage(str, obj, obj2, obj3, objArr);
            if (th == null) {
                this.log.log(level, buildFullMessage);
            } else {
                this.log.log(level, buildFullMessage, th);
            }
        }
    }

    private String buildFullMessage(String str, Object obj, Object obj2, Object obj3, Object[] objArr) {
        int i = 0;
        StringBuilder stringBuilder = null;
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(ARG_STRING, i);
            if (indexOf == -1) {
                break;
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(128);
            }
            stringBuilder.append(str, i, indexOf);
            i = ARG_STRING_LENGTH + indexOf;
            if (objArr == null) {
                if (i2 == 0) {
                    appendArg(stringBuilder, obj);
                } else if (i2 == 1) {
                    appendArg(stringBuilder, obj2);
                } else if (i2 == 2) {
                    appendArg(stringBuilder, obj3);
                }
            } else if (i2 < objArr.length) {
                appendArg(stringBuilder, objArr[i2]);
            }
            i2++;
        }
        if (stringBuilder == null) {
            return str;
        }
        stringBuilder.append(str, i, str.length());
        return stringBuilder.toString();
    }

    private void appendArg(StringBuilder stringBuilder, Object obj) {
        if (obj != UNKNOWN_ARG) {
            if (obj == null) {
                stringBuilder.append("null");
            } else if (obj.getClass().isArray()) {
                stringBuilder.append(Arrays.toString((Object[]) obj));
            } else {
                stringBuilder.append(obj);
            }
        }
    }
}
