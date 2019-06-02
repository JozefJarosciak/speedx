package cn.jpush.android.api;

import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.p003b.p004a.C0424e;
import org.apache.http.HttpStatus;

/* renamed from: cn.jpush.android.api.o */
public final class C0419o {
    /* renamed from: a */
    public static C0424e f567a = null;
    /* renamed from: b */
    private static int f568b = HttpStatus.SC_BAD_REQUEST;
    /* renamed from: c */
    private static int f569c = 600;

    /* renamed from: a */
    public static void m1222a(WindowManager windowManager, WebView webView, ImageButton imageButton) {
        windowManager.removeView(webView);
        windowManager.removeView(imageButton);
    }
}
