package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.HttpStatus;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.Args;

@Immutable
public class EnglishReasonPhraseCatalog implements ReasonPhraseCatalog {
    public static final EnglishReasonPhraseCatalog INSTANCE = new EnglishReasonPhraseCatalog();
    private static final String[][] REASON_PHRASES = new String[][]{null, new String[3], new String[8], new String[8], new String[25], new String[8]};

    static {
        setReason(200, "OK");
        setReason(201, "Created");
        setReason(202, "Accepted");
        setReason(204, "No Content");
        setReason(HttpStatus.SC_MOVED_PERMANENTLY, "Moved Permanently");
        setReason(HttpStatus.SC_MOVED_TEMPORARILY, "Moved Temporarily");
        setReason(HttpStatus.SC_NOT_MODIFIED, "Not Modified");
        setReason(HttpStatus.SC_BAD_REQUEST, "Bad Request");
        setReason(HttpStatus.SC_UNAUTHORIZED, "Unauthorized");
        setReason(HttpStatus.SC_FORBIDDEN, "Forbidden");
        setReason(404, "Not Found");
        setReason(500, "Internal Server Error");
        setReason(501, "Not Implemented");
        setReason(502, "Bad Gateway");
        setReason(503, "Service Unavailable");
        setReason(100, "Continue");
        setReason(HttpStatus.SC_TEMPORARY_REDIRECT, "Temporary Redirect");
        setReason(HttpStatus.SC_METHOD_NOT_ALLOWED, "Method Not Allowed");
        setReason(HttpStatus.SC_CONFLICT, "Conflict");
        setReason(HttpStatus.SC_PRECONDITION_FAILED, "Precondition Failed");
        setReason(HttpStatus.SC_REQUEST_TOO_LONG, "Request Too Long");
        setReason(HttpStatus.SC_REQUEST_URI_TOO_LONG, "Request-URI Too Long");
        setReason(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
        setReason(300, "Multiple Choices");
        setReason(HttpStatus.SC_SEE_OTHER, "See Other");
        setReason(HttpStatus.SC_USE_PROXY, "Use Proxy");
        setReason(HttpStatus.SC_PAYMENT_REQUIRED, "Payment Required");
        setReason(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable");
        setReason(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required");
        setReason(HttpStatus.SC_REQUEST_TIMEOUT, "Request Timeout");
        setReason(101, "Switching Protocols");
        setReason(203, "Non Authoritative Information");
        setReason(205, "Reset Content");
        setReason(206, "Partial Content");
        setReason(504, "Gateway Timeout");
        setReason(505, "Http Version Not Supported");
        setReason(HttpStatus.SC_GONE, "Gone");
        setReason(HttpStatus.SC_LENGTH_REQUIRED, "Length Required");
        setReason(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "Requested Range Not Satisfiable");
        setReason(HttpStatus.SC_EXPECTATION_FAILED, "Expectation Failed");
        setReason(102, "Processing");
        setReason(207, "Multi-Status");
        setReason(HttpStatus.SC_UNPROCESSABLE_ENTITY, "Unprocessable Entity");
        setReason(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, "Insufficient Space On Resource");
        setReason(HttpStatus.SC_METHOD_FAILURE, "Method Failure");
        setReason(HttpStatus.SC_LOCKED, "Locked");
        setReason(507, "Insufficient Storage");
        setReason(HttpStatus.SC_FAILED_DEPENDENCY, "Failed Dependency");
    }

    protected EnglishReasonPhraseCatalog() {
    }

    public String getReason(int i, Locale locale) {
        boolean z = i >= 100 && i < 600;
        Args.check(z, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        if (REASON_PHRASES[i2].length > i3) {
            return REASON_PHRASES[i2][i3];
        }
        return null;
    }

    private static void setReason(int i, String str) {
        int i2 = i / 100;
        REASON_PHRASES[i2][i - (i2 * 100)] = str;
    }
}
