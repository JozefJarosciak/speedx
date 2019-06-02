package com.baidu.mapapi;

import com.baidu.platform.comjni.tools.C1290a;

public class OpenLogUtil {
    /* renamed from: a */
    private static ModuleName f2757a;

    public static void setModuleLogEnable(ModuleName moduleName, boolean z) {
        f2757a = moduleName;
        C1290a.m4966a(z, f2757a.ordinal());
    }
}
