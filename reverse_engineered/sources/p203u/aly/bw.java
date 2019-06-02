package p203u.aly;

import android.content.Context;
import android.provider.Settings.Secure;

/* compiled from: AndroidIdTracker */
/* renamed from: u.aly.bw */
public class bw extends bu {
    /* renamed from: a */
    private Context f18933a;

    public bw(Context context) {
        super("android_id");
        this.f18933a = context;
    }

    /* renamed from: a */
    public String mo7207a() {
        String str = null;
        try {
            str = Secure.getString(this.f18933a.getContentResolver(), "android_id");
        } catch (Exception e) {
        }
        return str;
    }
}
