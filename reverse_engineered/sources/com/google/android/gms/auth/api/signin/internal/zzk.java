package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzk {
    private static final Lock eu = new ReentrantLock();
    private static zzk ev;
    private final Lock ew = new ReentrantLock();
    private final SharedPreferences ex;

    zzk(Context context) {
        this.ex = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzk zzbc(Context context) {
        zzab.zzaa(context);
        eu.lock();
        try {
            if (ev == null) {
                ev = new zzk(context.getApplicationContext());
            }
            zzk zzk = ev;
            return zzk;
        } finally {
            eu.unlock();
        }
    }

    private String zzy(String str, String str2) {
        String valueOf = String.valueOf(":");
        return new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzab.zzaa(googleSignInAccount);
        zzab.zzaa(googleSignInOptions);
        String zzafm = googleSignInAccount.zzafm();
        zzx(zzy("googleSignInAccount", zzafm), googleSignInAccount.zzafo());
        zzx(zzy("googleSignInOptions", zzafm), googleSignInOptions.zzafn());
    }

    public GoogleSignInAccount zzagj() {
        return zzft(zzfv("defaultGoogleSignInAccount"));
    }

    public GoogleSignInOptions zzagk() {
        return zzfu(zzfv("defaultGoogleSignInAccount"));
    }

    public void zzagl() {
        String zzfv = zzfv("defaultGoogleSignInAccount");
        zzfx("defaultGoogleSignInAccount");
        zzfw(zzfv);
    }

    public void zzb(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzab.zzaa(googleSignInAccount);
        zzab.zzaa(googleSignInOptions);
        zzx("defaultGoogleSignInAccount", googleSignInAccount.zzafm());
        zza(googleSignInAccount, googleSignInOptions);
    }

    GoogleSignInAccount zzft(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (!TextUtils.isEmpty(str)) {
            String zzfv = zzfv(zzy("googleSignInAccount", str));
            if (zzfv != null) {
                try {
                    googleSignInAccount = GoogleSignInAccount.zzfp(zzfv);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInAccount;
    }

    GoogleSignInOptions zzfu(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (!TextUtils.isEmpty(str)) {
            String zzfv = zzfv(zzy("googleSignInOptions", str));
            if (zzfv != null) {
                try {
                    googleSignInOptions = GoogleSignInOptions.zzfr(zzfv);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInOptions;
    }

    protected String zzfv(String str) {
        this.ew.lock();
        try {
            String string = this.ex.getString(str, null);
            return string;
        } finally {
            this.ew.unlock();
        }
    }

    void zzfw(String str) {
        if (!TextUtils.isEmpty(str)) {
            zzfx(zzy("googleSignInAccount", str));
            zzfx(zzy("googleSignInOptions", str));
        }
    }

    protected void zzfx(String str) {
        this.ew.lock();
        try {
            this.ex.edit().remove(str).apply();
        } finally {
            this.ew.unlock();
        }
    }

    protected void zzx(String str, String str2) {
        this.ew.lock();
        try {
            this.ex.edit().putString(str, str2).apply();
        } finally {
            this.ew.unlock();
        }
    }
}
