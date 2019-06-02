package io.rong.imkit.widget.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.MessageInputFragment;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.manager.AudioRecordManager;
import io.rong.imkit.widget.InputView;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imlib.model.Conversation;

public class VoiceInputProvider extends MainInputProvider implements OnTouchListener {
    float lastTouchY;
    float mOffsetLimit;
    boolean upDirection;

    public void onAttached(MessageInputFragment messageInputFragment, InputView inputView) {
        super.onAttached(messageInputFragment, inputView);
        this.mOffsetLimit = 70.0f * messageInputFragment.getActivity().getResources().getDisplayMetrics().density;
    }

    public void onSwitch(Context context) {
    }

    public void onDetached() {
        super.onDetached();
    }

    public void onInputResume(InputView inputView, Conversation conversation) {
        setCurrentConversation(conversation);
    }

    public VoiceInputProvider(RongContext rongContext) {
        super(rongContext);
    }

    public Drawable obtainSwitchDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_voice);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, InputView inputView) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_wi_vo_provider, viewGroup);
        ((Button) inflate.findViewById(16908313)).setOnTouchListener(this);
        return inflate;
    }

    @TargetApi(23)
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent != null) {
            if (VERSION.SDK_INT < 23 || view.getContext().checkSelfPermission("android.permission.RECORD_AUDIO") == 0 || RongContext.getInstance().getRequestPermissionListener() == null) {
                if (motionEvent.getAction() == 0) {
                    AudioPlayManager.getInstance().stopPlay();
                    AudioRecordManager.getInstance().startRecord(view.getRootView(), this.mCurrentConversation.getConversationType(), this.mCurrentConversation.getTargetId());
                    this.lastTouchY = motionEvent.getY();
                    this.upDirection = false;
                } else if (motionEvent.getAction() == 2) {
                    if (this.lastTouchY - motionEvent.getY() > this.mOffsetLimit && !this.upDirection) {
                        AudioRecordManager.getInstance().willCancelRecord();
                        this.upDirection = true;
                    } else if (motionEvent.getY() - this.lastTouchY > (-this.mOffsetLimit) && this.upDirection) {
                        AudioRecordManager.getInstance().continueRecord();
                        this.upDirection = false;
                    }
                } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                    AudioRecordManager.getInstance().stopRecord();
                }
            } else if (motionEvent.getAction() == 0) {
                RongContext.getInstance().getRequestPermissionListener().onPermissionRequest(new String[]{"android.permission.RECORD_AUDIO"}, 100);
            }
        }
        return false;
    }

    public void onActive(Context context) {
    }

    public void onInactive(Context context) {
    }
}
