package com.beastbikes.android.modules.cycling.activity.biz;

import com.beastbikes.framework.android.fsm.p087a.C1462a;
import com.beastbikes.framework.android.fsm.p087a.C1463b;

public interface ActivityState {
    @C1462a
    @C1463b(a = {1, 4})
    public static final int STATE_AUTO_PAUSED = 3;
    @C1462a
    public static final int STATE_COMPLETE = 4;
    @C1462a(a = true)
    @C1463b(a = {1})
    public static final int STATE_NONE = 0;
    @C1462a
    @C1463b(a = {1, 4})
    public static final int STATE_PAUSED = 2;
    @C1462a
    @C1463b(a = {2, 3, 4})
    public static final int STATE_STARTED = 1;
}
