package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public class VerifyException extends RuntimeException {
    public VerifyException(String str) {
        super(str);
    }
}
