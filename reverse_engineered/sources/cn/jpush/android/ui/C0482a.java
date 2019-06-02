package cn.jpush.android.ui;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: cn.jpush.android.ui.a */
final class C0482a implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ FullScreenView f915a;

    C0482a(FullScreenView fullScreenView) {
        this.f915a = fullScreenView;
    }

    public final void onClick(View view) {
        if (this.f915a.mContext != null) {
            ((Activity) this.f915a.mContext).onBackPressed();
        }
    }
}
