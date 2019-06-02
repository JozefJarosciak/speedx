package cn.sharesdk.framework;

import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

public class ReflectablePlatformActionListener implements PlatformActionListener {
    /* renamed from: a */
    private int f1215a;
    /* renamed from: b */
    private Callback f1216b;
    /* renamed from: c */
    private int f1217c;
    /* renamed from: d */
    private Callback f1218d;
    /* renamed from: e */
    private int f1219e;
    /* renamed from: f */
    private Callback f1220f;

    public void setOnCompleteCallback(int i, Callback callback) {
        this.f1215a = i;
        this.f1216b = callback;
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (this.f1216b != null) {
            Message message = new Message();
            message.what = this.f1215a;
            message.obj = new Object[]{platform, Integer.valueOf(i), hashMap};
            UIHandler.sendMessage(message, this.f1216b);
        }
    }

    public void setOnErrorCallback(int i, Callback callback) {
        this.f1217c = i;
        this.f1218d = callback;
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.f1218d != null) {
            Message message = new Message();
            message.what = this.f1217c;
            message.obj = new Object[]{platform, Integer.valueOf(i), th};
            UIHandler.sendMessage(message, this.f1218d);
        }
    }

    public void setOnCancelCallback(int i, Callback callback) {
        this.f1219e = i;
        this.f1220f = callback;
    }

    public void onCancel(Platform platform, int i) {
        if (this.f1220f != null) {
            Message message = new Message();
            message.what = this.f1219e;
            message.obj = new Object[]{platform, Integer.valueOf(i)};
            UIHandler.sendMessage(message, this.f1220f);
        }
    }
}
