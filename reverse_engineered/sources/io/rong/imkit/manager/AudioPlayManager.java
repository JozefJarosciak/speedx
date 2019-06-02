package io.rong.imkit.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import io.rong.common.RLog;

public class AudioPlayManager implements SensorEventListener {
    private static final String TAG = "AudioPlayManager";
    private AudioManager _audioManager;
    private MediaPlayer _mediaPlayer;
    private IAudioPlayListener _playListener;
    private Uri _playingUri;
    private PowerManager _powerManager;
    private Sensor _sensor;
    private SensorManager _sensorManager;
    private WakeLock _wakeLock;
    private OnAudioFocusChangeListener afChangeListener;

    /* renamed from: io.rong.imkit.manager.AudioPlayManager$2 */
    class C50842 implements OnCompletionListener {
        C50842() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            AudioPlayManager.this.muteAudioFocus(AudioPlayManager.this._audioManager, false);
            mediaPlayer.stop();
            mediaPlayer.release();
            AudioPlayManager.this._mediaPlayer = null;
            Uri access$500 = AudioPlayManager.this._playingUri;
            AudioPlayManager.this._playingUri = null;
            if (AudioPlayManager.this._playListener != null) {
                AudioPlayManager.this._playListener.onComplete(access$500);
            }
        }
    }

    static class SingletonHolder {
        static AudioPlayManager sInstance = new AudioPlayManager();

        SingletonHolder() {
        }
    }

    public static AudioPlayManager getInstance() {
        return SingletonHolder.sInstance;
    }

    @TargetApi(11)
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this._mediaPlayer == null || !this._mediaPlayer.isPlaying()) {
            if (f == this._sensor.getMaximumRange()) {
                this._audioManager.setMode(0);
                this._audioManager.setSpeakerphoneOn(true);
                setScreenOn();
            }
        } else if (f == this._sensor.getMaximumRange()) {
            this._audioManager.setMode(0);
            this._audioManager.setSpeakerphoneOn(true);
            setScreenOn();
        } else {
            this._audioManager.setSpeakerphoneOn(false);
            if (VERSION.SDK_INT >= 11) {
                this._audioManager.setMode(3);
            } else {
                this._audioManager.setMode(2);
            }
            setScreenOff();
            this._mediaPlayer.setVolume(1.0f, 1.0f);
            this._audioManager.setStreamVolume(3, this._audioManager.getStreamMaxVolume(3), 4);
        }
    }

    @TargetApi(21)
    private void setScreenOff() {
        if (this._wakeLock == null) {
            if (VERSION.SDK_INT >= 21) {
                this._wakeLock = this._powerManager.newWakeLock(32, TAG);
            } else {
                RLog.m19420e(TAG, "Does not support on level " + VERSION.SDK_INT);
            }
        }
        if (this._wakeLock != null) {
            this._wakeLock.acquire();
        }
    }

    private void setScreenOn() {
        if (this._wakeLock != null) {
            this._wakeLock.setReferenceCounted(false);
            this._wakeLock.release();
            this._wakeLock = null;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void startPlay(final Context context, Uri uri, IAudioPlayListener iAudioPlayListener) {
        if (context == null || uri == null) {
            RLog.m19420e(TAG, "startPlay context or audioUri is null.");
            return;
        }
        resetMediaPlayer();
        if (this.afChangeListener != null) {
            ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(this.afChangeListener);
            this.afChangeListener = null;
        }
        this.afChangeListener = new OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int i) {
                RLog.m19419d(AudioPlayManager.TAG, "OnAudioFocusChangeListener " + i);
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                if (i == -1) {
                    audioManager.abandonAudioFocus(AudioPlayManager.this.afChangeListener);
                    AudioPlayManager.this.afChangeListener = null;
                    AudioPlayManager.this.resetMediaPlayer();
                }
            }
        };
        try {
            this._powerManager = (PowerManager) context.getSystemService("power");
            this._audioManager = (AudioManager) context.getSystemService("audio");
            this._sensorManager = (SensorManager) context.getSystemService("sensor");
            this._sensor = this._sensorManager.getDefaultSensor(8);
            this._sensorManager.registerListener(this, this._sensor, 3);
            muteAudioFocus(this._audioManager, true);
            this._playListener = iAudioPlayListener;
            this._playingUri = uri;
            this._mediaPlayer = new MediaPlayer();
            this._mediaPlayer.setOnCompletionListener(new C50842());
            this._mediaPlayer.setDataSource(context, uri);
            this._mediaPlayer.prepare();
            this._mediaPlayer.start();
            if (this._playListener != null) {
                this._playListener.onStart(this._playingUri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (iAudioPlayListener != null) {
                iAudioPlayListener.onStop(uri);
            }
        }
    }

    public void stopPlay() {
        resetMediaPlayer();
    }

    private void resetMediaPlayer() {
        if (this._mediaPlayer != null) {
            this._mediaPlayer.stop();
            this._mediaPlayer.release();
            muteAudioFocus(this._audioManager, false);
            if (!(this._playListener == null || this._playingUri == null)) {
                this._playListener.onStop(this._playingUri);
            }
            if (this._sensorManager != null) {
                this._sensorManager.unregisterListener(this);
            }
        }
        this._playListener = null;
        this._playingUri = null;
        this._mediaPlayer = null;
    }

    public Uri getPlayingUri() {
        return this._playingUri;
    }

    @TargetApi(8)
    private void muteAudioFocus(AudioManager audioManager, boolean z) {
        if (VERSION.SDK_INT < 8) {
            RLog.m19419d(TAG, "muteAudioFocus Android 2.1 and below can not stop music");
        } else if (z) {
            audioManager.requestAudioFocus(this.afChangeListener, 3, 2);
        } else {
            audioManager.abandonAudioFocus(this.afChangeListener);
            this.afChangeListener = null;
        }
    }
}
