package org.apache.commons.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TypeHandler {
    public static Object createValue(String str, Object obj) throws ParseException {
        return createValue(str, (Class) obj);
    }

    public static Object createValue(String str, Class cls) throws ParseException {
        if (PatternOptionBuilder.STRING_VALUE == cls) {
            return str;
        }
        if (PatternOptionBuilder.OBJECT_VALUE == cls) {
            return createObject(str);
        }
        if (PatternOptionBuilder.NUMBER_VALUE == cls) {
            return createNumber(str);
        }
        if (PatternOptionBuilder.DATE_VALUE == cls) {
            return createDate(str);
        }
        if (PatternOptionBuilder.CLASS_VALUE == cls) {
            return createClass(str);
        }
        if (PatternOptionBuilder.FILE_VALUE == cls) {
            return createFile(str);
        }
        if (PatternOptionBuilder.EXISTING_FILE_VALUE == cls) {
            return createFile(str);
        }
        if (PatternOptionBuilder.FILES_VALUE == cls) {
            return createFiles(str);
        }
        if (PatternOptionBuilder.URL_VALUE == cls) {
            return createURL(str);
        }
        return null;
    }

    public static Object createObject(String str) throws ParseException {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (Exception e) {
                throw new ParseException(new StringBuffer().append(e.getClass().getName()).append("; Unable to create an instance of: ").append(str).toString());
            }
        } catch (ClassNotFoundException e2) {
            throw new ParseException(new StringBuffer().append("Unable to find the class: ").append(str).toString());
        }
    }

    public static Number createNumber(String str) throws ParseException {
        try {
            if (str.indexOf(46) != -1) {
                return Double.valueOf(str);
            }
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public static Class createClass(String str) throws ParseException {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new ParseException(new StringBuffer().append("Unable to find the class: ").append(str).toString());
        }
    }

    public static Date createDate(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static URL createURL(String str) throws ParseException {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new ParseException(new StringBuffer().append("Unable to parse the URL: ").append(str).toString());
        }
    }

    public static File createFile(String str) throws ParseException {
        return new File(str);
    }

    public static File[] createFiles(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
