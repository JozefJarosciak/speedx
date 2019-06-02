package com.beastbikes.android.modules.cycling.activity.biz;

import com.beastbikes.framework.android.fsm.C1914a;
import com.beastbikes.framework.android.fsm.UnreachableTransitionException;

/* compiled from: ActivityStateMachine */
/* renamed from: com.beastbikes.android.modules.cycling.activity.biz.b */
public class C1915b extends C1914a implements ActivityState {
    public C1915b(int i) {
        super(i);
    }

    /* renamed from: a */
    public void m9896a() throws UnreachableTransitionException {
        m9888a(1);
    }

    /* renamed from: a */
    public void m9897a(boolean z) throws UnreachableTransitionException {
        m9888a(z ? 3 : 2);
    }

    /* renamed from: b */
    public void m9898b() throws UnreachableTransitionException {
        m9888a(1);
    }

    /* renamed from: c */
    public void m9899c() throws UnreachableTransitionException {
        m9888a(4);
    }
}
