package com.beastbikes.framework.android.fsm;

public class UnreachableTransitionException extends Exception {
    private static final long serialVersionUID = 6122844439479059878L;

    public UnreachableTransitionException(String str, Throwable th) {
        super(str, th);
    }

    public UnreachableTransitionException(String str) {
        super(str);
    }

    public UnreachableTransitionException(Throwable th) {
        super(th);
    }
}
