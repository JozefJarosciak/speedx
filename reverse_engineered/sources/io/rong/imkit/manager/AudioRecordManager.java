package io.rong.imkit.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.TypingMessage.TypingMessageManager;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.statistics.UserData;
import io.rong.message.VoiceMessage;
import java.io.File;

public class AudioRecordManager implements Callback {
    private static final String TAG = "AudioRecordManager";
    private int RECORD_INTERVAL;
    IAudioState cancelState;
    IAudioState idleState;
    private OnAudioFocusChangeListener mAfChangeListener;
    private AudioManager mAudioManager;
    private Uri mAudioPath;
    private Context mContext;
    private ConversationType mConversationType;
    private IAudioState mCurAudioState;
    private Handler mHandler;
    private MediaRecorder mMediaRecorder;
    private PopupWindow mRecordWindow;
    private View mRootView;
    private ImageView mStateIV;
    private TextView mStateTV;
    private String mTargetId;
    private TextView mTimerTV;
    IAudioState recordState;
    private long smStartRecTime;
    IAudioState timerState;

    /* renamed from: io.rong.imkit.manager.AudioRecordManager$1 */
    class C50851 extends PhoneStateListener {
        C50851() {
        }

        public void onCallStateChanged(int i, String str) {
            switch (i) {
                case 1:
                    AudioRecordManager.this.sendEmptyMessage(6);
                    break;
            }
            super.onCallStateChanged(i, str);
        }
    }

    /* renamed from: io.rong.imkit.manager.AudioRecordManager$2 */
    class C50862 implements OnAudioFocusChangeListener {
        C50862() {
        }

        public void onAudioFocusChange(int i) {
            RLog.m19419d(AudioRecordManager.TAG, "OnAudioFocusChangeListener " + i);
            if (i == -1) {
                AudioRecordManager.this.mAudioManager.abandonAudioFocus(AudioRecordManager.this.mAfChangeListener);
                AudioRecordManager.this.mAfChangeListener = null;
                AudioRecordManager.this.sendEmptyMessage(6);
            }
        }
    }

    /* renamed from: io.rong.imkit.manager.AudioRecordManager$3 */
    class C50873 implements ISendMessageCallback {
        C50873() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    class CancelState extends IAudioState {

        /* renamed from: io.rong.imkit.manager.AudioRecordManager$CancelState$1 */
        class C50881 implements Runnable {
            C50881() {
            }

            public void run() {
                AudioRecordManager.this.stopRec();
                AudioRecordManager.this.sendAudioFile();
                AudioRecordManager.this.destroyView();
            }
        }

        CancelState() {
        }

        void handleMessage(AudioStateMessage audioStateMessage) {
            RLog.m19419d(AudioRecordManager.TAG, getClass().getSimpleName() + " handleMessage : " + audioStateMessage.what);
            switch (audioStateMessage.what) {
                case 4:
                    AudioRecordManager.this.setRecordingView();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.recordState;
                    AudioRecordManager.this.sendEmptyMessage(2);
                    return;
                case 5:
                case 6:
                    AudioRecordManager.this.stopRec();
                    AudioRecordManager.this.destroyView();
                    AudioRecordManager.this.deleteAudioFile();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    AudioRecordManager.this.idleState.enter();
                    return;
                case 7:
                    int intValue = ((Integer) audioStateMessage.obj).intValue();
                    if (intValue > 0) {
                        android.os.Message obtain = android.os.Message.obtain();
                        obtain.what = 8;
                        obtain.obj = Integer.valueOf(intValue - 1);
                        AudioRecordManager.this.mHandler.sendMessageDelayed(obtain, 1000);
                        return;
                    }
                    AudioRecordManager.this.mHandler.postDelayed(new C50881(), 500);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    AudioRecordManager.this.idleState.enter();
                    return;
                default:
                    return;
            }
        }
    }

    class IdleState extends IAudioState {
        public IdleState() {
            RLog.m19419d(AudioRecordManager.TAG, "IdleState");
        }

        void enter() {
            super.enter();
            if (AudioRecordManager.this.mHandler != null) {
                AudioRecordManager.this.mHandler.removeMessages(7);
                AudioRecordManager.this.mHandler.removeMessages(8);
                AudioRecordManager.this.mHandler.removeMessages(2);
            }
        }

        void handleMessage(AudioStateMessage audioStateMessage) {
            RLog.m19419d(AudioRecordManager.TAG, "IdleState handleMessage : " + audioStateMessage.what);
            switch (audioStateMessage.what) {
                case 1:
                    AudioRecordManager.this.initView(AudioRecordManager.this.mRootView);
                    AudioRecordManager.this.setRecordingView();
                    AudioRecordManager.this.startRec();
                    AudioRecordManager.this.smStartRecTime = SystemClock.elapsedRealtime();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.recordState;
                    AudioRecordManager.this.sendEmptyMessage(2);
                    return;
                default:
                    return;
            }
        }
    }

    class RecordState extends IAudioState {

        /* renamed from: io.rong.imkit.manager.AudioRecordManager$RecordState$2 */
        class C50902 implements Runnable {
            C50902() {
            }

            public void run() {
                AudioRecordManager.this.stopRec();
                AudioRecordManager.this.sendAudioFile();
                AudioRecordManager.this.destroyView();
            }
        }

        RecordState() {
        }

        void handleMessage(AudioStateMessage audioStateMessage) {
            RLog.m19419d(AudioRecordManager.TAG, getClass().getSimpleName() + " handleMessage : " + audioStateMessage.what);
            switch (audioStateMessage.what) {
                case 2:
                    AudioRecordManager.this.audioDBChanged();
                    AudioRecordManager.this.mHandler.sendEmptyMessageDelayed(2, 150);
                    return;
                case 3:
                    AudioRecordManager.this.setCancelView();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.cancelState;
                    return;
                case 5:
                    final boolean access$1000 = AudioRecordManager.this.checkAudioTimeLength();
                    if (access$1000) {
                        AudioRecordManager.this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_wraning);
                        AudioRecordManager.this.mStateTV.setText(C4974R.string.rc_voice_short);
                    }
                    AudioRecordManager.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            AudioRecordManager.this.stopRec();
                            if (!access$1000) {
                                AudioRecordManager.this.sendAudioFile();
                            }
                            AudioRecordManager.this.destroyView();
                        }
                    }, 500);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    return;
                case 6:
                    AudioRecordManager.this.stopRec();
                    AudioRecordManager.this.destroyView();
                    AudioRecordManager.this.deleteAudioFile();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    AudioRecordManager.this.idleState.enter();
                    return;
                case 7:
                    int intValue = ((Integer) audioStateMessage.obj).intValue();
                    AudioRecordManager.this.setTimeoutView(intValue);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.timerState;
                    if (intValue > 0) {
                        android.os.Message obtain = android.os.Message.obtain();
                        obtain.what = 8;
                        obtain.obj = Integer.valueOf(intValue - 1);
                        AudioRecordManager.this.mHandler.sendMessageDelayed(obtain, 1000);
                        return;
                    }
                    AudioRecordManager.this.mHandler.postDelayed(new C50902(), 500);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    return;
                default:
                    return;
            }
        }
    }

    static class SingletonHolder {
        static AudioRecordManager sInstance = new AudioRecordManager();

        SingletonHolder() {
        }
    }

    class TimerState extends IAudioState {

        /* renamed from: io.rong.imkit.manager.AudioRecordManager$TimerState$1 */
        class C50911 implements Runnable {
            C50911() {
            }

            public void run() {
                AudioRecordManager.this.stopRec();
                AudioRecordManager.this.sendAudioFile();
                AudioRecordManager.this.destroyView();
            }
        }

        /* renamed from: io.rong.imkit.manager.AudioRecordManager$TimerState$2 */
        class C50922 implements Runnable {
            C50922() {
            }

            public void run() {
                AudioRecordManager.this.stopRec();
                AudioRecordManager.this.sendAudioFile();
                AudioRecordManager.this.destroyView();
            }
        }

        TimerState() {
        }

        void handleMessage(AudioStateMessage audioStateMessage) {
            RLog.m19419d(AudioRecordManager.TAG, getClass().getSimpleName() + " handleMessage : " + audioStateMessage.what);
            switch (audioStateMessage.what) {
                case 3:
                    AudioRecordManager.this.setCancelView();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.cancelState;
                    return;
                case 5:
                    AudioRecordManager.this.mHandler.postDelayed(new C50922(), 500);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    AudioRecordManager.this.idleState.enter();
                    return;
                case 6:
                    AudioRecordManager.this.stopRec();
                    AudioRecordManager.this.destroyView();
                    AudioRecordManager.this.deleteAudioFile();
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    AudioRecordManager.this.idleState.enter();
                    return;
                case 7:
                    int intValue = ((Integer) audioStateMessage.obj).intValue();
                    if (intValue > 0) {
                        android.os.Message obtain = android.os.Message.obtain();
                        obtain.what = 8;
                        obtain.obj = Integer.valueOf(intValue - 1);
                        AudioRecordManager.this.mHandler.sendMessageDelayed(obtain, 1000);
                        AudioRecordManager.this.setTimeoutView(intValue);
                        return;
                    }
                    AudioRecordManager.this.mHandler.postDelayed(new C50911(), 500);
                    AudioRecordManager.this.mCurAudioState = AudioRecordManager.this.idleState;
                    return;
                default:
                    return;
            }
        }
    }

    public static AudioRecordManager getInstance() {
        return SingletonHolder.sInstance;
    }

    @TargetApi(21)
    private AudioRecordManager() {
        this.RECORD_INTERVAL = 60;
        this.idleState = new IdleState();
        this.recordState = new RecordState();
        this.cancelState = new CancelState();
        this.timerState = new TimerState();
        RLog.m19419d(TAG, TAG);
        if (VERSION.SDK_INT < 21) {
            try {
                ((TelephonyManager) RongContext.getInstance().getSystemService(UserData.PHONE_KEY)).listen(new C50851(), 32);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        this.mCurAudioState = this.idleState;
        this.idleState.enter();
    }

    public final boolean handleMessage(android.os.Message message) {
        RLog.m19422i(TAG, "handleMessage " + message.what);
        AudioStateMessage obtain;
        switch (message.what) {
            case 2:
                sendEmptyMessage(2);
                break;
            case 7:
                obtain = AudioStateMessage.obtain();
                obtain.what = message.what;
                obtain.obj = message.obj;
                sendMessage(obtain);
                break;
            case 8:
                obtain = AudioStateMessage.obtain();
                obtain.what = 7;
                obtain.obj = message.obj;
                sendMessage(obtain);
                break;
        }
        return false;
    }

    private void initView(View view) {
        this.mHandler = new Handler(view.getHandler().getLooper(), this);
        View inflate = LayoutInflater.from(view.getContext()).inflate(C4974R.layout.rc_wi_vo_popup, null);
        this.mStateIV = (ImageView) inflate.findViewById(C4974R.id.rc_audio_state_image);
        this.mStateTV = (TextView) inflate.findViewById(C4974R.id.rc_audio_state_text);
        this.mTimerTV = (TextView) inflate.findViewById(C4974R.id.rc_audio_timer);
        this.mRecordWindow = new PopupWindow(inflate, -1, -1);
        this.mRecordWindow.showAtLocation(view, 17, 0, 0);
        this.mRecordWindow.setFocusable(true);
        this.mRecordWindow.setOutsideTouchable(false);
        this.mRecordWindow.setTouchable(false);
    }

    private void setTimeoutView(int i) {
        if (this.mRecordWindow != null) {
            this.mStateIV.setVisibility(8);
            this.mStateTV.setVisibility(0);
            this.mStateTV.setText(C4974R.string.rc_voice_rec);
            this.mStateTV.setBackgroundResource(17170445);
            this.mTimerTV.setText(String.format("%s", new Object[]{Integer.valueOf(i)}));
            this.mTimerTV.setVisibility(0);
        }
    }

    private void setRecordingView() {
        RLog.m19419d(TAG, "setRecordingView");
        if (this.mRecordWindow != null) {
            this.mStateIV.setVisibility(0);
            this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_1);
            this.mStateTV.setVisibility(0);
            this.mStateTV.setText(C4974R.string.rc_voice_rec);
            this.mStateTV.setBackgroundResource(17170445);
            this.mTimerTV.setVisibility(8);
        }
    }

    private void setCancelView() {
        RLog.m19419d(TAG, "setCancelView");
        if (this.mRecordWindow != null) {
            this.mTimerTV.setVisibility(8);
            this.mStateIV.setVisibility(0);
            this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_cancel);
            this.mStateTV.setVisibility(0);
            this.mStateTV.setText(C4974R.string.rc_voice_cancel);
            this.mStateTV.setBackgroundResource(C4974R.drawable.rc_corner_voice_style);
        }
    }

    private void destroyView() {
        RLog.m19419d(TAG, "destroyView");
        if (this.mRecordWindow != null) {
            this.mHandler.removeMessages(7);
            this.mHandler.removeMessages(8);
            this.mHandler.removeMessages(2);
            this.mRecordWindow.dismiss();
            this.mRecordWindow = null;
            this.mStateIV = null;
            this.mStateTV = null;
            this.mTimerTV = null;
            this.mHandler = null;
            this.mContext = null;
        }
    }

    public void setMaxVoiceDuration(int i) {
        this.RECORD_INTERVAL = i;
    }

    public int getMaxVoiceDuration() {
        return this.RECORD_INTERVAL;
    }

    public void startRecord(View view, ConversationType conversationType, String str) {
        this.mRootView = view;
        this.mContext = view.getContext().getApplicationContext();
        this.mConversationType = conversationType;
        this.mTargetId = str;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (this.mAfChangeListener != null) {
            this.mAudioManager.abandonAudioFocus(this.mAfChangeListener);
            this.mAfChangeListener = null;
        }
        this.mAfChangeListener = new C50862();
        sendEmptyMessage(1);
        if (TypingMessageManager.getInstance().isShowMessageTyping()) {
            RongIMClient.getInstance().sendTypingStatus(conversationType, str, "RC:VcMsg");
        }
    }

    public void willCancelRecord() {
        sendEmptyMessage(3);
    }

    public void continueRecord() {
        sendEmptyMessage(4);
    }

    public void stopRecord() {
        sendEmptyMessage(5);
    }

    void sendMessage(AudioStateMessage audioStateMessage) {
        this.mCurAudioState.handleMessage(audioStateMessage);
    }

    void sendEmptyMessage(int i) {
        AudioStateMessage obtain = AudioStateMessage.obtain();
        obtain.what = i;
        this.mCurAudioState.handleMessage(obtain);
    }

    private void startRec() {
        RLog.m19419d(TAG, "startRec");
        try {
            muteAudioFocus(this.mAudioManager, true);
            this.mAudioManager.setMode(0);
            this.mMediaRecorder = new MediaRecorder();
            try {
                int integer = this.mContext.getResources().getInteger(C4974R.integer.rc_audio_encoding_bit_rate);
                this.mMediaRecorder.setAudioSamplingRate(8000);
                this.mMediaRecorder.setAudioEncodingBitRate(integer);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            this.mMediaRecorder.setAudioChannels(1);
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setOutputFormat(3);
            this.mMediaRecorder.setAudioEncoder(1);
            this.mAudioPath = Uri.fromFile(new File(this.mContext.getCacheDir(), System.currentTimeMillis() + "temp.voice"));
            this.mMediaRecorder.setOutputFile(this.mAudioPath.getPath());
            this.mMediaRecorder.prepare();
            this.mMediaRecorder.start();
            android.os.Message obtain = android.os.Message.obtain();
            obtain.what = 7;
            obtain.obj = Integer.valueOf(10);
            this.mHandler.sendMessageDelayed(obtain, (long) ((this.RECORD_INTERVAL * 1000) - 10000));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean checkAudioTimeLength() {
        return SystemClock.elapsedRealtime() - this.smStartRecTime < 1000;
    }

    private void stopRec() {
        RLog.m19419d(TAG, "stopRec");
        try {
            muteAudioFocus(this.mAudioManager, false);
            if (this.mMediaRecorder != null) {
                this.mMediaRecorder.stop();
                this.mMediaRecorder.release();
                this.mMediaRecorder = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAudioFile() {
        RLog.m19419d(TAG, "deleteAudioFile");
        if (this.mAudioPath != null) {
            File file = new File(this.mAudioPath.getPath());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void sendAudioFile() {
        RLog.m19419d(TAG, "sendAudioFile path = " + this.mAudioPath);
        if (this.mAudioPath != null) {
            RongIM.getInstance().sendMessage(Message.obtain(this.mTargetId, this.mConversationType, VoiceMessage.obtain(this.mAudioPath, ((int) (SystemClock.elapsedRealtime() - this.smStartRecTime)) / 1000)), null, null, new C50873());
        }
    }

    private void audioDBChanged() {
        if (this.mMediaRecorder != null) {
            switch ((this.mMediaRecorder.getMaxAmplitude() / 600) / 5) {
                case 0:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_1);
                    return;
                case 1:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_2);
                    return;
                case 2:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_3);
                    return;
                case 3:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_4);
                    return;
                case 4:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_5);
                    return;
                case 5:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_6);
                    return;
                case 6:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_7);
                    return;
                default:
                    this.mStateIV.setImageResource(C4974R.drawable.rc_ic_volume_8);
                    return;
            }
        }
    }

    private void muteAudioFocus(AudioManager audioManager, boolean z) {
        if (VERSION.SDK_INT < 8) {
            RLog.m19419d(TAG, "muteAudioFocus Android 2.1 and below can not stop music");
        } else if (z) {
            audioManager.requestAudioFocus(this.mAfChangeListener, 3, 2);
        } else {
            audioManager.abandonAudioFocus(this.mAfChangeListener);
            this.mAfChangeListener = null;
        }
    }
}
