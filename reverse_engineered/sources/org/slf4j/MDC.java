package org.slf4j;

import java.io.Closeable;
import java.util.Map;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

public class MDC {
    static final String NO_STATIC_MDC_BINDER_URL = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
    static final String NULL_MDCA_URL = "http://www.slf4j.org/codes.html#null_MDCA";
    static MDCAdapter mdcAdapter;

    public static class MDCCloseable implements Closeable {
        private final String key;

        private MDCCloseable(String str) {
            this.key = str;
        }

        public void close() {
            MDC.remove(this.key);
        }
    }

    private MDC() {
    }

    static {
        try {
            mdcAdapter = StaticMDCBinder.SINGLETON.getMDCA();
        } catch (NoClassDefFoundError e) {
            mdcAdapter = new NOPMDCAdapter();
            String message = e.getMessage();
            if (message == null || message.indexOf("StaticMDCBinder") == -1) {
                throw e;
            }
            Util.report("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
            Util.report("Defaulting to no-operation MDCAdapter implementation.");
            Util.report("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
        } catch (Throwable e2) {
            Util.report("MDC binding unsuccessful.", e2);
        }
    }

    public static void put(String str, String str2) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            mdcAdapter.put(str, str2);
        }
    }

    public static MDCCloseable putCloseable(String str, String str2) throws IllegalArgumentException {
        put(str, str2);
        return new MDCCloseable(str);
    }

    public static String get(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (mdcAdapter != null) {
            return mdcAdapter.get(str);
        } else {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
    }

    public static void remove(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            mdcAdapter.remove(str);
        }
    }

    public static void clear() {
        if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        mdcAdapter.clear();
    }

    public static Map<String, String> getCopyOfContextMap() {
        if (mdcAdapter != null) {
            return mdcAdapter.getCopyOfContextMap();
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static void setContextMap(Map<String, String> map) {
        if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        mdcAdapter.setContextMap(map);
    }

    public static MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }
}
