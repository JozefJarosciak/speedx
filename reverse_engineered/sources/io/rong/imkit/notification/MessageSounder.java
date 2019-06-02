package io.rong.imkit.notification;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import java.io.IOException;

public class MessageSounder {
    private static Context mContext;
    static MessageSounder mRoundMgr;
    Handler mHandler = new Handler();
    NewMessageReminderRunnable mLastReminderRunnable;

    /* renamed from: io.rong.imkit.notification.MessageSounder$1 */
    class C51151 implements OnPreparedListener {
        C51151() {
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
        }
    }

    /* renamed from: io.rong.imkit.notification.MessageSounder$2 */
    class C51162 implements OnCompletionListener {
        C51162() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.d("RongIMService", "playNewMessageSound---onCompletion");
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }

    class NewMessageReminderRunnable implements Runnable {
        NewMessageReminderRunnable() {
        }

        public void run() {
            switch (((AudioManager) MessageSounder.mContext.getSystemService("audio")).getRingerMode()) {
                case 1:
                    ((Vibrator) MessageSounder.mContext.getSystemService("vibrator")).vibrate(200);
                    return;
                case 2:
                    Uri defaultUri = RingtoneManager.getDefaultUri(2);
                    if (defaultUri != null) {
                        MessageSounder.this.playSound(defaultUri);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public static void init(Context context) {
        mContext = context;
        mRoundMgr = new MessageSounder();
    }

    MessageSounder() {
    }

    public static MessageSounder getInstance() {
        return mRoundMgr;
    }

    public void messageReminder() {
        if (this.mLastReminderRunnable == null) {
            this.mLastReminderRunnable = new NewMessageReminderRunnable();
            this.mHandler.post(this.mLastReminderRunnable);
            return;
        }
        this.mHandler.removeCallbacks(this.mLastReminderRunnable);
        this.mHandler.postDelayed(this.mLastReminderRunnable, 500);
    }

    private int getMobileRingerMode() {
        return ((AudioManager) mContext.getSystemService("audio")).getRingerMode();
    }

    private void playSound(Uri uri) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(new C51151());
            mediaPlayer.setOnCompletionListener(new C51162());
            mediaPlayer.setDataSource(mContext, uri);
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
    }
}
