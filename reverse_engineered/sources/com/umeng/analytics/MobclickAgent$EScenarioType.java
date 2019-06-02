package com.umeng.analytics;

public enum MobclickAgent$EScenarioType {
    E_UM_NORMAL(0),
    E_UM_GAME(1),
    E_UM_ANALYTICS_OEM(224),
    E_UM_GAME_OEM(225);
    
    /* renamed from: a */
    private int f16604a;

    private MobclickAgent$EScenarioType(int i) {
        this.f16604a = i;
    }

    public int toValue() {
        return this.f16604a;
    }
}
