package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/* renamed from: com.tencent.a.a.a.a.b */
final class C4395b extends C4394f {
    C4395b(Context context) {
        super(context);
    }

    /* renamed from: a */
    protected final void mo6050a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to InternalStorage");
            C4393a.m17215d(Environment.getExternalStorageDirectory() + "/" + C4400h.m17244f("6X8Y4XdM2Vhvn0I="));
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), C4400h.m17244f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU="))));
                bufferedWriter.write(C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw==") + "," + str);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            } catch (Throwable e) {
                Log.w("MID", e);
            }
        }
    }

    /* renamed from: a */
    protected final boolean mo6051a() {
        return C4400h.m17240a(this.a, "android.permission.WRITE_EXTERNAL_STORAGE") && Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: b */
    protected final String mo6052b() {
        String str;
        synchronized (this) {
            Log.i("MID", "read mid from InternalStorage");
            try {
                for (String str2 : C4393a.m17214a(new File(Environment.getExternalStorageDirectory(), C4400h.m17244f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=")))) {
                    String[] split = str2.split(",");
                    if (split.length == 2 && split[0].equals(C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw=="))) {
                        Log.i("MID", "read mid from InternalStorage:" + split[1]);
                        str2 = split[1];
                        break;
                    }
                }
                str2 = null;
            } catch (Throwable e) {
                Log.w("MID", e);
                str2 = null;
            }
        }
        return str2;
    }
}
