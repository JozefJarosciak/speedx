package cn.sharesdk.framework;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mob.tools.utils.DeviceHelper;
import java.lang.reflect.Method;

/* compiled from: SSDKWebViewClient */
/* renamed from: cn.sharesdk.framework.d */
public class C0561d extends WebViewClient {
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    public void onPageFinished(WebView webView, String str) {
    }

    public void onLoadResource(WebView webView, String str) {
    }

    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        message.sendToTarget();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        message.sendToTarget();
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
    }

    public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        if (webView.getContext() instanceof Activity) {
            String[] strArr;
            CharSequence valueOf;
            CharSequence valueOf2;
            CharSequence valueOf3;
            Activity activity = (Activity) webView.getContext();
            if ("zh".equals(DeviceHelper.getInstance(activity).getOSLanguage())) {
                strArr = new String[]{String.valueOf(new char[]{'不', '受', '信', '任', '的', '证', '书', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '已', '过', '期', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', 'I', 'D', '不', '匹', '配', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '尚', '未', '生', '效', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '错', '误', '。', '你', '要', '继', '续', '吗', '？'})};
                valueOf = String.valueOf(new char[]{'证', '书', '错', '误'});
                valueOf2 = String.valueOf(new char[]{'继', '续'});
                valueOf3 = String.valueOf(new char[]{'停', '止'});
            } else {
                strArr = new String[]{"Certificate is untrusted. Do you want to continue anyway?", "Certificate has expired. Do you want to continue anyway?", "Certificate ID is mismatched. Do you want to continue anyway?", "Certificate is not yet valid. Do you want to continue anyway?", "Certificate error. Do you want to continue anyway?"};
                valueOf = "SSL Certificate Error";
                valueOf2 = "Yes";
                valueOf3 = "No";
            }
            Builder builder = new Builder(activity);
            builder.setTitle(valueOf);
            switch (sslError.getPrimaryError()) {
                case 0:
                    builder.setMessage(strArr[3]);
                    break;
                case 1:
                    builder.setMessage(strArr[1]);
                    break;
                case 2:
                    builder.setMessage(strArr[2]);
                    break;
                case 3:
                    builder.setMessage(strArr[0]);
                    break;
                default:
                    builder.setMessage(strArr[4]);
                    break;
            }
            builder.setCancelable(false);
            builder.setPositiveButton(valueOf2, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0561d f1358b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    try {
                        Method method = sslErrorHandler.getClass().getMethod("proceed", new Class[0]);
                        method.setAccessible(true);
                        method.invoke(sslErrorHandler, new Object[0]);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton(valueOf3, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0561d f1360b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    sslErrorHandler.cancel();
                }
            });
            builder.create().show();
            return;
        }
        sslErrorHandler.cancel();
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        httpAuthHandler.cancel();
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return false;
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
    }

    public void onScaleChanged(WebView webView, float f, float f2) {
    }
}
