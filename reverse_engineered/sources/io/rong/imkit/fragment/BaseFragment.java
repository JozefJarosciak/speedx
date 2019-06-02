package io.rong.imkit.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ConnectCallback;

public abstract class BaseFragment extends Fragment implements Callback {
    private static final String TAG = "BaseFragment";
    public static final String TOKEN = "RONG_TOKEN";
    public static final int UI_RESTORE = 1;
    private Handler mHandler;
    private LayoutInflater mInflater;
    Thread mThread;

    /* renamed from: io.rong.imkit.fragment.BaseFragment$1 */
    class C50131 extends ConnectCallback {
        C50131() {
        }

        public void onSuccess(String str) {
            BaseFragment.this.mHandler.sendEmptyMessage(1);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19420e(BaseFragment.TAG, "onError(...) ErrorCode:" + rongIMClient$ErrorCode);
        }

        public void onTokenIncorrect() {
            RLog.m19420e(BaseFragment.TAG, "onTokenIncorrect() onTokenIncorrect");
        }
    }

    public abstract boolean onBackPressed();

    public abstract void onRestoreUI();

    public void onCreate(Bundle bundle) {
        String str = null;
        this.mHandler = new Handler(this);
        this.mThread = Thread.currentThread();
        if (bundle != null) {
            str = bundle.getString(TOKEN);
        }
        if (!(str == null || RongIMClient.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED))) {
            RLog.m19422i(TAG, "onCreate auto reconnect");
            RongIM.connect(str, new C50131());
        }
        super.onCreate(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mInflater = LayoutInflater.from(view.getContext());
        super.onViewCreated(view, bundle);
    }

    protected <T extends View> T findViewById(View view, int i) {
        return view.findViewById(i);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(TOKEN, RongContext.getInstance().getToken());
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected Handler getHandler() {
        return this.mHandler;
    }

    private View obtainView(LayoutInflater layoutInflater, int i, Drawable drawable, CharSequence charSequence) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_wi_notice, null);
        ((TextView) inflate.findViewById(16908299)).setText(charSequence);
        ((ImageView) inflate.findViewById(16908294)).setImageDrawable(drawable);
        if (i > 0) {
            inflate.setBackgroundColor(i);
        }
        return inflate;
    }

    private View obtainView(LayoutInflater layoutInflater, int i, int i2, CharSequence charSequence) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_wi_notice, null);
        ((TextView) inflate.findViewById(16908299)).setText(charSequence);
        ((ImageView) inflate.findViewById(16908294)).setImageResource(i2);
        inflate.setBackgroundColor(i);
        return inflate;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                onRestoreUI();
                break;
        }
        return true;
    }
}
