package ch.qos.logback.core.util;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class LocationUtil {
    public static final String CLASSPATH_SCHEME = "classpath:";
    public static final String SCHEME_PATTERN = "^\\p{Alpha}[\\p{Alnum}+.-]*:.*$";

    public static URL urlForResource(String str) throws MalformedURLException, FileNotFoundException {
        if (str == null) {
            throw new NullPointerException("location is required");
        }
        URL resourceBySelfClassLoader;
        if (!str.matches(SCHEME_PATTERN)) {
            resourceBySelfClassLoader = Loader.getResourceBySelfClassLoader(str);
        } else if (str.startsWith(CLASSPATH_SCHEME)) {
            String substring = str.substring(CLASSPATH_SCHEME.length());
            if (substring.startsWith("/")) {
                substring = substring.substring(1);
            }
            if (substring.length() == 0) {
                throw new MalformedURLException("path is required");
            }
            resourceBySelfClassLoader = Loader.getResourceBySelfClassLoader(substring);
        } else {
            resourceBySelfClassLoader = new URL(str);
        }
        if (resourceBySelfClassLoader != null) {
            return resourceBySelfClassLoader;
        }
        throw new FileNotFoundException(str);
    }
}
