package io.rong.imkit.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import io.rong.imkit.RongContext;
import io.rong.imkit.widget.InputView.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public interface IVoiceHandler {

    public interface OnPlayListener {
        void onCover(boolean z);

        void onFinish();

        void onPlay(Context context, boolean z, int i);

        void onStop();
    }

    public static class VoiceException extends RuntimeException {
        public VoiceException(Throwable th) {
            super(th);
        }
    }

    public static class VoiceHandler implements SensorEventListener, OnCompletionListener, OnErrorListener, IVoiceHandler {
        private AudioManager mAudioManager;
        private Uri mCurrentUri;
        float mLastEventValue = Float.MIN_VALUE;
        private WakeLock mLock;
        private MediaPlayer mMediaPlayer;
        private OnPlayListener mPlayListener;
        private PowerManager mPowerManager;
        private Sensor mSensor;
        private SensorManager mSensorManager;

        public VoiceHandler(Context context) {
            try {
                this.mSensorManager = (SensorManager) context.getSystemService("sensor");
                this.mPowerManager = (PowerManager) context.getSystemService("power");
                this.mAudioManager = (AudioManager) context.getSystemService("audio");
                this.mSensor = this.mSensorManager.getDefaultSensor(8);
                this.mLock = this.mPowerManager.newWakeLock(536870922, "VoiceHandler");
                RongContext.getInstance().getEventBus().register(this);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        public void setPlayListener(OnPlayListener onPlayListener) {
            this.mPlayListener = onPlayListener;
        }

        public void play(final Context context, Uri uri, final int i) throws VoiceException {
            stop();
            if (uri != null) {
                this.mCurrentUri = uri;
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setOnCompletionListener(this);
                this.mMediaPlayer.setOnErrorListener(this);
                this.mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        VoiceHandler.this.mAudioManager.setMode(i);
                        mediaPlayer.start();
                        VoiceHandler.this.mLock.acquire((long) mediaPlayer.getDuration());
                        if (VoiceHandler.this.mPlayListener != null) {
                            VoiceHandler.this.mPlayListener.onPlay(context, false, i);
                        }
                        if (VoiceHandler.this.mSensor != null) {
                            VoiceHandler.this.mSensorManager.registerListener(VoiceHandler.this, VoiceHandler.this.mSensor, 3);
                        }
                    }
                });
                File file = new File(this.mCurrentUri.getPath());
                if (file.exists()) {
                    try {
                        this.mMediaPlayer.setDataSource(new FileInputStream(file).getFD());
                        this.mMediaPlayer.prepare();
                    } catch (Throwable e) {
                        throw new VoiceException(e);
                    } catch (Throwable e2) {
                        throw new VoiceException(e2);
                    } catch (Throwable e22) {
                        throw new VoiceException(e22);
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }

        public void stop() {
            this.mCurrentUri = null;
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.stop();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                if (this.mPlayListener != null) {
                    this.mPlayListener.onStop();
                }
                this.mSensorManager.unregisterListener(this);
            }
        }

        public Uri getCurrentPlayUri() {
            return this.mCurrentUri;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.mCurrentUri = null;
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.stop();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                this.mPlayListener.onFinish();
                this.mSensorManager.unregisterListener(this);
            }
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            this.mMediaPlayer.reset();
            return false;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            if (this.mLastEventValue == Float.MIN_VALUE) {
                this.mLastEventValue = f;
            }
            if (f > this.mLastEventValue) {
                this.mLastEventValue = f;
            }
            if (f >= this.mLastEventValue) {
                if (this.mAudioManager.getMode() != 0 && this.mPlayListener != null) {
                    this.mAudioManager.setMode(0);
                    this.mPlayListener.onCover(false);
                }
            } else if (this.mAudioManager.getMode() != 2 && this.mPlayListener != null) {
                this.mAudioManager.setMode(2);
                this.mPlayListener.onCover(true);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onEvent(Event event) {
            if (this.mCurrentUri != null && event == Event.DESTROY) {
                stop();
            }
        }
    }

    Uri getCurrentPlayUri();

    void play(Context context, Uri uri, int i) throws VoiceException;

    void setPlayListener(OnPlayListener onPlayListener);

    void stop();
}
