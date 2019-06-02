package com.beastbikes.framework.persistence;

public class PersistenceException extends Exception {
    private static final long serialVersionUID = -9106340481863581144L;

    public PersistenceException(String str, Throwable th) {
        super(str, th);
    }

    public PersistenceException(String str) {
        super(str);
    }

    public PersistenceException(Throwable th) {
        super(th);
    }
}
