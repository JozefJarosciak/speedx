package cn.jpush.android.p002a;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;

/* renamed from: cn.jpush.android.a.c */
final class C0394c extends PhoneStateListener {
    /* renamed from: a */
    final /* synthetic */ C0393b f430a;

    C0394c(C0393b c0393b) {
        this.f430a = c0393b;
    }

    public final void onCellLocationChanged(CellLocation cellLocation) {
    }

    public final void onSignalStrengthChanged(int i) {
        this.f430a.f410a = i;
    }
}
