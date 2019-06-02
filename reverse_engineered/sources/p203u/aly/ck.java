package p203u.aly;

import android.os.Build;
import android.os.Build.VERSION;

/* compiled from: SerialTracker */
/* renamed from: u.aly.ck */
public class ck extends bu {
    public ck() {
        super("serial");
    }

    /* renamed from: a */
    public String mo7207a() {
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
