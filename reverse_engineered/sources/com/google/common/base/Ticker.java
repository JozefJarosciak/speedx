package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new C35041();

    /* renamed from: com.google.common.base.Ticker$1 */
    static class C35041 extends Ticker {
        C35041() {
        }

        public long read() {
            return Platform.systemNanoTime();
        }
    }

    public abstract long read();

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}
