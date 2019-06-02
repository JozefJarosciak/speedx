package org.apache.commons.cli;

import ch.qos.logback.core.CoreConstants;

public class PatternOptionBuilder {
    public static final Class CLASS_VALUE;
    public static final Class DATE_VALUE;
    public static final Class EXISTING_FILE_VALUE;
    public static final Class FILES_VALUE;
    public static final Class FILE_VALUE;
    public static final Class NUMBER_VALUE;
    public static final Class OBJECT_VALUE;
    public static final Class STRING_VALUE;
    public static final Class URL_VALUE;
    static Class array$Ljava$io$File;
    static Class class$java$io$File;
    static Class class$java$io$FileInputStream;
    static Class class$java$lang$Class;
    static Class class$java$lang$Number;
    static Class class$java$lang$Object;
    static Class class$java$lang$String;
    static Class class$java$net$URL;
    static Class class$java$util$Date;

    static {
        Class class$;
        if (class$java$lang$String == null) {
            class$ = class$("java.lang.String");
            class$java$lang$String = class$;
        } else {
            class$ = class$java$lang$String;
        }
        STRING_VALUE = class$;
        if (class$java$lang$Object == null) {
            class$ = class$("java.lang.Object");
            class$java$lang$Object = class$;
        } else {
            class$ = class$java$lang$Object;
        }
        OBJECT_VALUE = class$;
        if (class$java$lang$Number == null) {
            class$ = class$("java.lang.Number");
            class$java$lang$Number = class$;
        } else {
            class$ = class$java$lang$Number;
        }
        NUMBER_VALUE = class$;
        if (class$java$util$Date == null) {
            class$ = class$("java.util.Date");
            class$java$util$Date = class$;
        } else {
            class$ = class$java$util$Date;
        }
        DATE_VALUE = class$;
        if (class$java$lang$Class == null) {
            class$ = class$("java.lang.Class");
            class$java$lang$Class = class$;
        } else {
            class$ = class$java$lang$Class;
        }
        CLASS_VALUE = class$;
        if (class$java$io$FileInputStream == null) {
            class$ = class$("java.io.FileInputStream");
            class$java$io$FileInputStream = class$;
        } else {
            class$ = class$java$io$FileInputStream;
        }
        EXISTING_FILE_VALUE = class$;
        if (class$java$io$File == null) {
            class$ = class$("java.io.File");
            class$java$io$File = class$;
        } else {
            class$ = class$java$io$File;
        }
        FILE_VALUE = class$;
        if (array$Ljava$io$File == null) {
            class$ = class$("[Ljava.io.File;");
            array$Ljava$io$File = class$;
        } else {
            class$ = array$Ljava$io$File;
        }
        FILES_VALUE = class$;
        if (class$java$net$URL == null) {
            class$ = class$("java.net.URL");
            class$java$net$URL = class$;
        } else {
            class$ = class$java$net$URL;
        }
        URL_VALUE = class$;
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static Object getValueClass(char c) {
        switch (c) {
            case '#':
                return DATE_VALUE;
            case '%':
                return NUMBER_VALUE;
            case '*':
                return FILES_VALUE;
            case '+':
                return CLASS_VALUE;
            case '/':
                return URL_VALUE;
            case ':':
                return STRING_VALUE;
            case '<':
                return EXISTING_FILE_VALUE;
            case '>':
                return FILE_VALUE;
            case '@':
                return OBJECT_VALUE;
            default:
                return null;
        }
    }

    public static boolean isValueCode(char c) {
        return c == '@' || c == CoreConstants.COLON_CHAR || c == CoreConstants.PERCENT_CHAR || c == '+' || c == '#' || c == '<' || c == '>' || c == '*' || c == '/' || c == '!';
    }

    public static Options parsePattern(String str) {
        boolean z = true;
        Options options = new Options();
        int i = 0;
        Object obj = null;
        boolean z2 = false;
        char c = ' ';
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (isValueCode(charAt)) {
                if (charAt == '!') {
                    z2 = true;
                    charAt = c;
                } else {
                    obj = getValueClass(charAt);
                    charAt = c;
                }
            } else if (c != ' ') {
                boolean z3;
                if (obj != null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                OptionBuilder.hasArg(z3);
                OptionBuilder.isRequired(z2);
                OptionBuilder.withType(obj);
                options.addOption(OptionBuilder.create(c));
                obj = null;
                z2 = false;
            }
            i++;
            c = charAt;
        }
        if (c != ' ') {
            if (obj == null) {
                z = false;
            }
            OptionBuilder.hasArg(z);
            OptionBuilder.isRequired(z2);
            OptionBuilder.withType(obj);
            options.addOption(OptionBuilder.create(c));
        }
        return options;
    }
}
