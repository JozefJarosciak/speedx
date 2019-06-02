package cn.jpush.android.ui;

/* renamed from: cn.jpush.android.ui.b */
final class C0483b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ FullScreenView f916a;

    C0483b(FullScreenView fullScreenView) {
        this.f916a = fullScreenView;
    }

    public final void run() {
        this.f916a.mWebView.clearHistory();
    }
}
