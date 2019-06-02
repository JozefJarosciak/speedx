package com.beastbikes.android.sphere.restful;

import org.apache.http.StatusLine;

public class InvocationException extends Exception {
    private final StatusLine status;
    private final C2540c target;

    public InvocationException(C2540c c2540c) {
        this(c2540c, null, null, null);
    }

    public InvocationException(C2540c c2540c, StatusLine statusLine) {
        this(c2540c, statusLine, null, null);
    }

    public InvocationException(C2540c c2540c, StatusLine statusLine, Throwable th) {
        this(c2540c, statusLine, null, null);
    }

    public InvocationException(C2540c c2540c, StatusLine statusLine, String str, Throwable th) {
        super(str, th);
        this.target = c2540c;
        this.status = statusLine;
    }
}
