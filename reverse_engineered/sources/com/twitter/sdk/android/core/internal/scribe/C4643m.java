package com.twitter.sdk.android.core.internal.scribe;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4655n;
import io.fabric.sdk.android.services.common.C4883l;
import java.util.List;

/* compiled from: TwitterCoreScribeClientHolder */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.m */
public class C4643m {
    /* renamed from: a */
    private static C4629a f16360a;

    /* renamed from: a */
    public static C4629a m18373a() {
        return f16360a;
    }

    /* renamed from: a */
    public static void m18374a(C4655n c4655n, List<C4586l<? extends C1469k>> list, C4883l c4883l) {
        f16360a = new C4629a(c4655n, "TwitterCore", list, c4883l);
    }
}
