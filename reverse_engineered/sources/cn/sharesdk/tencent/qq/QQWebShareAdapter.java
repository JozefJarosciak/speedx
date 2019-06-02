package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import cn.sharesdk.framework.TitleLayout;

public class QQWebShareAdapter {
    private Activity activity;
    private boolean noTitle;
    private RelativeLayout rlBody;
    private TitleLayout title;
    private WebView webview;

    void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return this.activity;
    }

    void setTitleView(TitleLayout titleLayout) {
        this.title = titleLayout;
    }

    public TitleLayout getTitleLayout() {
        return this.title;
    }

    void setWebView(WebView webView) {
        this.webview = webView;
    }

    public WebView getWebBody() {
        return this.webview;
    }

    void setNotitle(boolean z) {
        this.noTitle = z;
    }

    boolean isNotitle() {
        return this.noTitle;
    }

    void setBodyView(RelativeLayout relativeLayout) {
        this.rlBody = relativeLayout;
    }

    public RelativeLayout getBodyView() {
        return this.rlBody;
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onStart() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void onRestart() {
    }

    public boolean onFinish() {
        return false;
    }
}
