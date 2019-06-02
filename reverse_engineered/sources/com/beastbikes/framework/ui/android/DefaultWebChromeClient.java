package com.beastbikes.framework.ui.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultWebChromeClient extends WebChromeClient {
    private static final String TAG = "DefaultWebChromeClient";
    private static final Logger logger = LoggerFactory.getLogger(TAG);
    private final WebActivity webActivity;

    /* renamed from: com.beastbikes.framework.ui.android.DefaultWebChromeClient$7 */
    static /* synthetic */ class C28237 {
        static final /* synthetic */ int[] $SwitchMap$android$webkit$ConsoleMessage$MessageLevel = new int[MessageLevel.values().length];

        static {
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[MessageLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[MessageLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[MessageLevel.LOG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[MessageLevel.TIP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[MessageLevel.WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public DefaultWebChromeClient(WebActivity webActivity) {
        this.webActivity = webActivity;
    }

    public void onCloseWindow(WebView webView) {
        Context context = webView.getContext();
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        new Builder(webView.getContext()).setCancelable(true).setTitle(17039380).setMessage(str2).setNegativeButton(17039360, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.cancel();
            }
        }).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
            }
        }).create().show();
        return true;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
        final View textView = new TextView(webView.getContext());
        if (!TextUtils.isEmpty(str3)) {
            textView.setText(str3);
        }
        new Builder(webView.getContext()).setCancelable(true).setTitle(17039380).setView(textView).setNegativeButton(17039360, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsPromptResult.cancel();
            }
        }).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsPromptResult.confirm(textView.getText().toString());
            }
        }).create().show();
        return true;
    }

    public void onReceivedTitle(WebView webView, String str) {
        Context context = webView.getContext();
        if (context instanceof Activity) {
            ((Activity) context).setTitle(str);
        }
    }

    public void onProgressChanged(WebView webView, int i) {
        this.webActivity.setProgress(i);
    }

    public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
        new Builder(webView.getContext()).setCancelable(true).setMessage(str2).setNegativeButton(17039360, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.cancel();
            }
        }).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
            }
        }).create().show();
        return true;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String format = String.format(Locale.getDefault(), "%s#%d: %s", new Object[]{consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message()});
        switch (C28237.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[consoleMessage.messageLevel().ordinal()]) {
            case 1:
                logger.debug(format);
                break;
            case 2:
                logger.error(format);
                break;
            case 3:
                logger.info(format);
                break;
            case 4:
                logger.trace(format);
                break;
            case 5:
                logger.warn(format);
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
