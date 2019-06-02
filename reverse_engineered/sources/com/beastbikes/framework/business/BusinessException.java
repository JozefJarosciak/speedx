package com.beastbikes.framework.business;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 7000835461642534201L;

    public BusinessException(String str, Throwable th) {
        super(str, th);
    }

    public BusinessException(String str) {
        super(str);
    }

    public BusinessException(Throwable th) {
        super(th);
    }
}
