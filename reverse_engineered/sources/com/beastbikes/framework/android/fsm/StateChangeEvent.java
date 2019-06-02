package com.beastbikes.framework.android.fsm;

import java.util.EventObject;

public class StateChangeEvent extends EventObject {
    private static final long serialVersionUID = 6502119784248708786L;
    private final int from;
    private final int to;

    StateChangeEvent(C1913c c1913c, int i, int i2) {
        super(c1913c);
        this.from = i;
        this.to = i2;
    }

    public C1913c getSource() {
        return (C1913c) super.getSource();
    }

    public int getFromState() {
        return this.from;
    }

    public int getToState() {
        return this.to;
    }
}
