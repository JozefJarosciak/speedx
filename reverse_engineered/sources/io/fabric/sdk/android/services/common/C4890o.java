package io.fabric.sdk.android.services.common;

import org.apache.http.HttpStatus;

/* compiled from: ResponseParser */
/* renamed from: io.fabric.sdk.android.services.common.o */
public class C4890o {
    /* renamed from: a */
    public static int m19223a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i >= HttpStatus.SC_BAD_REQUEST && i <= 499) {
            return 0;
        }
        if (i >= 500) {
            return 1;
        }
        return 1;
    }
}
