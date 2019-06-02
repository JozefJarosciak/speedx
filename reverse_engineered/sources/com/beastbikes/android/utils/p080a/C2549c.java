package com.beastbikes.android.utils.p080a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.beastbikes.android.authentication.AVUser;

/* compiled from: UserSP */
/* renamed from: com.beastbikes.android.utils.a.c */
public class C2549c {
    /* renamed from: a */
    private static C2549c f12031a = new C2549c();
    /* renamed from: b */
    private SharedPreferences f12032b = null;

    private C2549c() {
    }

    /* renamed from: a */
    public static C2549c m12753a() {
        return f12031a;
    }

    /* renamed from: a */
    private SharedPreferences m12752a(Context context) {
        if (this.f12032b == null) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser == null) {
                throw new RuntimeException("User is null, cannot be instantiated");
            }
            this.f12032b = context.getSharedPreferences(currentUser.getObjectId(), 0);
        }
        return this.f12032b;
    }

    /* renamed from: a */
    public Editor m12755a(Context context, String str, Object obj) {
        this.f12032b = m12752a(context);
        Editor edit = this.f12032b.edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, obj.toString());
        }
        return edit;
    }

    /* renamed from: a */
    public String m12756a(Context context, String str, String str2) {
        return m12752a(context).getString(str, str2);
    }

    /* renamed from: a */
    public Editor m12754a(Context context, String str) {
        Editor edit = m12752a(context).edit();
        edit.remove(str);
        return edit;
    }
}
