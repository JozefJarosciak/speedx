package io.fabric.sdk.android.services.concurrency;

public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    /* renamed from: a */
    static <Y> int m19234a(C4843e c4843e, Y y) {
        Priority b;
        if (y instanceof C4843e) {
            b = ((C4843e) y).mo6228b();
        } else {
            b = NORMAL;
        }
        return b.ordinal() - c4843e.mo6228b().ordinal();
    }
}
