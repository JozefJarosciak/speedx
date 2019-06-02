package ch.qos.logback.repackage.brut.androlib;

import ch.qos.logback.repackage.brut.common.BrutException;

public class AndrolibException extends BrutException {
    public AndrolibException(String str) {
        super(str);
    }

    public AndrolibException(String str, Throwable th) {
        super(str, th);
    }

    public AndrolibException(Throwable th) {
        super(th);
    }
}
