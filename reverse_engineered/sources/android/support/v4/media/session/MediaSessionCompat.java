package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.IMediaSession.Stub;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    public static abstract class Callback {
        final Object mCallbackObj;

        private class StubApi21 implements Callback {
            StubApi21() {
            }

            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                Callback.this.onCommand(str, bundle, resultReceiver);
            }

            public boolean onMediaButtonEvent(Intent intent) {
                return Callback.this.onMediaButtonEvent(intent);
            }

            public void onPlay() {
                Callback.this.onPlay();
            }

            public void onPlayFromMediaId(String str, Bundle bundle) {
                Callback.this.onPlayFromMediaId(str, bundle);
            }

            public void onPlayFromSearch(String str, Bundle bundle) {
                Callback.this.onPlayFromSearch(str, bundle);
            }

            public void onSkipToQueueItem(long j) {
                Callback.this.onSkipToQueueItem(j);
            }

            public void onPause() {
                Callback.this.onPause();
            }

            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            public void onFastForward() {
                Callback.this.onFastForward();
            }

            public void onRewind() {
                Callback.this.onRewind();
            }

            public void onStop() {
                Callback.this.onStop();
            }

            public void onSeekTo(long j) {
                Callback.this.onSeekTo(j);
            }

            public void onSetRating(Object obj) {
                Callback.this.onSetRating(RatingCompat.fromRating(obj));
            }

            public void onCustomAction(String str, Bundle bundle) {
                if (str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                    Callback.this.onPlayFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), (Bundle) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS));
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                    Callback.this.onPrepare();
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                    Callback.this.onPrepareFromMediaId(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID), bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS));
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    Callback.this.onPrepareFromSearch(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY), bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS));
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                    Callback.this.onPrepareFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS));
                } else {
                    Callback.this.onCustomAction(str, bundle);
                }
            }
        }

        private class StubApi23 extends StubApi21 implements android.support.v4.media.session.MediaSessionCompatApi23.Callback {
            StubApi23() {
                super();
            }

            public void onPlayFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPlayFromUri(uri, bundle);
            }
        }

        private class StubApi24 extends StubApi23 implements android.support.v4.media.session.MediaSessionCompatApi24.Callback {
            StubApi24() {
                super();
            }

            public void onPrepare() {
                Callback.this.onPrepare();
            }

            public void onPrepareFromMediaId(String str, Bundle bundle) {
                Callback.this.onPrepareFromMediaId(str, bundle);
            }

            public void onPrepareFromSearch(String str, Bundle bundle) {
                Callback.this.onPrepareFromSearch(str, bundle);
            }

            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPrepareFromUri(uri, bundle);
            }
        }

        public Callback() {
            if (VERSION.SDK_INT >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
            } else if (VERSION.SDK_INT >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
            } else if (VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
            } else {
                this.mCallbackObj = null;
            }
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            return false;
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onPause() {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onFastForward() {
        }

        public void onRewind() {
        }

        public void onStop() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }
    }

    interface MediaSessionImpl {
        String getCallingPackage();

        Object getMediaSession();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z);

        void setCallback(Callback callback, Handler handler);

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i);

        void setSessionActivity(PendingIntent pendingIntent);
    }

    static class MediaSessionImplApi21 implements MediaSessionImpl {
        private PendingIntent mMediaButtonIntent;
        private final Object mSessionObj;
        private final Token mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));

        public MediaSessionImplApi21(Context context, String str) {
            this.mSessionObj = MediaSessionCompatApi21.createSession(context, str);
        }

        public MediaSessionImplApi21(Object obj) {
            this.mSessionObj = MediaSessionCompatApi21.verifySession(obj);
        }

        public void setCallback(Callback callback, Handler handler) {
            MediaSessionCompatApi21.setCallback(this.mSessionObj, callback == null ? null : callback.mCallbackObj, handler);
        }

        public void setFlags(int i) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, i);
        }

        public void setPlaybackToLocal(int i) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, i);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
        }

        public void setActive(boolean z) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, z);
        }

        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, str, bundle);
        }

        public void release() {
            MediaSessionCompatApi21.release(this.mSessionObj);
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            Object obj;
            Object obj2 = this.mSessionObj;
            if (playbackStateCompat == null) {
                obj = null;
            } else {
                obj = playbackStateCompat.getPlaybackState();
            }
            MediaSessionCompatApi21.setPlaybackState(obj2, obj);
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            Object obj;
            Object obj2 = this.mSessionObj;
            if (mediaMetadataCompat == null) {
                obj = null;
            } else {
                obj = mediaMetadataCompat.getMediaMetadata();
            }
            MediaSessionCompatApi21.setMetadata(obj2, obj);
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
            this.mMediaButtonIntent = pendingIntent;
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, pendingIntent);
        }

        public void setQueue(List<QueueItem> list) {
            List list2 = null;
            if (list != null) {
                List arrayList = new ArrayList();
                for (QueueItem queueItem : list) {
                    arrayList.add(queueItem.getQueueItem());
                }
                list2 = arrayList;
            }
            MediaSessionCompatApi21.setQueue(this.mSessionObj, list2);
        }

        public void setQueueTitle(CharSequence charSequence) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
        }

        public void setRatingType(int i) {
            if (VERSION.SDK_INT >= 22) {
                MediaSessionCompatApi22.setRatingType(this.mSessionObj, i);
            }
        }

        public void setExtras(Bundle bundle) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
        }

        public Object getMediaSession() {
            return this.mSessionObj;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public String getCallingPackage() {
            if (VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
        }
    }

    static class MediaSessionImplBase implements MediaSessionImpl {
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        private final Context mContext;
        final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks = new RemoteCallbackList();
        boolean mDestroyed = false;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        private boolean mIsActive = false;
        private boolean mIsMbrRegistered = false;
        private boolean mIsRccRegistered = false;
        int mLocalStream;
        final Object mLock = new Object();
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List<QueueItem> mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        private final Object mRccObj;
        PendingIntent mSessionActivity;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private android.support.v4.media.VolumeProviderCompat.Callback mVolumeCallback = new C01471();
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$1 */
        class C01471 extends android.support.v4.media.VolumeProviderCompat.Callback {
            C01471() {
            }

            public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
                if (MediaSessionImplBase.this.mVolumeProvider == volumeProviderCompat) {
                    MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$2 */
        class C01482 implements Callback {
            C01482() {
            }

            public void onSetRating(Object obj) {
                MediaSessionImplBase.this.postToHandler(19, RatingCompat.fromRating(obj));
            }

            public void onSeekTo(long j) {
                MediaSessionImplBase.this.postToHandler(18, Long.valueOf(j));
            }
        }

        private static final class Command {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public Command(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.command = str;
                this.extras = bundle;
                this.stub = resultReceiver;
            }
        }

        class MediaSessionStub extends Stub {
            MediaSessionStub() {
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                MediaSessionImplBase.this.postToHandler(1, new Command(str, bundle, resultReceiverWrapper.mResultReceiver));
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                boolean z = (MediaSessionImplBase.this.mFlags & 1) != 0;
                if (z) {
                    MediaSessionImplBase.this.postToHandler(21, keyEvent);
                }
                return z;
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (MediaSessionImplBase.this.mDestroyed) {
                    try {
                        iMediaControllerCallback.onSessionDestroyed();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
                MediaSessionImplBase.this.mControllerCallbacks.register(iMediaControllerCallback);
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplBase.this.mControllerCallbacks.unregister(iMediaControllerCallback);
            }

            public String getPackageName() {
                return MediaSessionImplBase.this.mPackageName;
            }

            public String getTag() {
                return MediaSessionImplBase.this.mTag;
            }

            public PendingIntent getLaunchPendingIntent() {
                PendingIntent pendingIntent;
                synchronized (MediaSessionImplBase.this.mLock) {
                    pendingIntent = MediaSessionImplBase.this.mSessionActivity;
                }
                return pendingIntent;
            }

            public long getFlags() {
                long j;
                synchronized (MediaSessionImplBase.this.mLock) {
                    j = (long) MediaSessionImplBase.this.mFlags;
                }
                return j;
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                int i;
                int i2;
                int maxVolume;
                int currentVolume;
                int i3 = 2;
                synchronized (MediaSessionImplBase.this.mLock) {
                    i = MediaSessionImplBase.this.mVolumeType;
                    i2 = MediaSessionImplBase.this.mLocalStream;
                    VolumeProviderCompat volumeProviderCompat = MediaSessionImplBase.this.mVolumeProvider;
                    if (i == 2) {
                        i3 = volumeProviderCompat.getVolumeControl();
                        maxVolume = volumeProviderCompat.getMaxVolume();
                        currentVolume = volumeProviderCompat.getCurrentVolume();
                    } else {
                        maxVolume = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(i2);
                        currentVolume = MediaSessionImplBase.this.mAudioManager.getStreamVolume(i2);
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i3, maxVolume, currentVolume);
            }

            public void adjustVolume(int i, int i2, String str) {
                MediaSessionImplBase.this.adjustVolume(i, i2);
            }

            public void setVolumeTo(int i, int i2, String str) {
                MediaSessionImplBase.this.setVolumeTo(i, i2);
            }

            public void prepare() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(3);
            }

            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(4, str, bundle);
            }

            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(5, str, bundle);
            }

            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(6, uri, bundle);
            }

            public void play() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(7);
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(8, str, bundle);
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(9, str, bundle);
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(10, uri, bundle);
            }

            public void skipToQueueItem(long j) {
                MediaSessionImplBase.this.postToHandler(11, Long.valueOf(j));
            }

            public void pause() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(12);
            }

            public void stop() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(13);
            }

            public void next() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(14);
            }

            public void previous() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(15);
            }

            public void fastForward() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(16);
            }

            public void rewind() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(17);
            }

            public void seekTo(long j) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(18, Long.valueOf(j));
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(19, ratingCompat);
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(20, str, bundle);
            }

            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.mMetadata;
            }

            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionImplBase.this.getStateWithUpdatedPosition();
            }

            public List<QueueItem> getQueue() {
                List<QueueItem> list;
                synchronized (MediaSessionImplBase.this.mLock) {
                    list = MediaSessionImplBase.this.mQueue;
                }
                return list;
            }

            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.mQueueTitle;
            }

            public Bundle getExtras() {
                Bundle bundle;
                synchronized (MediaSessionImplBase.this.mLock) {
                    bundle = MediaSessionImplBase.this.mExtras;
                }
                return bundle;
            }

            public int getRatingType() {
                return MediaSessionImplBase.this.mRatingType;
            }

            public boolean isTransportControlEnabled() {
                return (MediaSessionImplBase.this.mFlags & 2) != 0;
            }
        }

        private class MessageHandler extends Handler {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;

            public MessageHandler(Looper looper) {
                super(looper);
            }

            public void post(int i, Object obj, Bundle bundle) {
                Message obtainMessage = obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }

            public void post(int i, Object obj) {
                obtainMessage(i, obj).sendToTarget();
            }

            public void post(int i) {
                post(i, null);
            }

            public void post(int i, Object obj, int i2) {
                obtainMessage(i, i2, 0, obj).sendToTarget();
            }

            public void handleMessage(Message message) {
                Callback callback = MediaSessionImplBase.this.mCallback;
                if (callback != null) {
                    switch (message.what) {
                        case 1:
                            Command command = (Command) message.obj;
                            callback.onCommand(command.command, command.extras, command.stub);
                            return;
                        case 2:
                            MediaSessionImplBase.this.adjustVolume(((Integer) message.obj).intValue(), 0);
                            return;
                        case 3:
                            callback.onPrepare();
                            return;
                        case 4:
                            callback.onPrepareFromMediaId((String) message.obj, message.getData());
                            return;
                        case 5:
                            callback.onPrepareFromSearch((String) message.obj, message.getData());
                            return;
                        case 6:
                            callback.onPrepareFromUri((Uri) message.obj, message.getData());
                            return;
                        case 7:
                            callback.onPlay();
                            return;
                        case 8:
                            callback.onPlayFromMediaId((String) message.obj, message.getData());
                            return;
                        case 9:
                            callback.onPlayFromSearch((String) message.obj, message.getData());
                            return;
                        case 10:
                            callback.onPlayFromUri((Uri) message.obj, message.getData());
                            return;
                        case 11:
                            callback.onSkipToQueueItem(((Long) message.obj).longValue());
                            return;
                        case 12:
                            callback.onPause();
                            return;
                        case 13:
                            callback.onStop();
                            return;
                        case 14:
                            callback.onSkipToNext();
                            return;
                        case 15:
                            callback.onSkipToPrevious();
                            return;
                        case 16:
                            callback.onFastForward();
                            return;
                        case 17:
                            callback.onRewind();
                            return;
                        case 18:
                            callback.onSeekTo(((Long) message.obj).longValue());
                            return;
                        case 19:
                            callback.onSetRating((RatingCompat) message.obj);
                            return;
                        case 20:
                            callback.onCustomAction((String) message.obj, message.getData());
                            return;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!callback.onMediaButtonEvent(intent)) {
                                onMediaButtonEvent(keyEvent, callback);
                                return;
                            }
                            return;
                        case 22:
                            MediaSessionImplBase.this.setVolumeTo(((Integer) message.obj).intValue(), 0);
                            return;
                        default:
                            return;
                    }
                }
            }

            private void onMediaButtonEvent(KeyEvent keyEvent, Callback callback) {
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    long actions = MediaSessionImplBase.this.mState == null ? 0 : MediaSessionImplBase.this.mState.getActions();
                    switch (keyEvent.getKeyCode()) {
                        case 79:
                        case 85:
                            Object obj;
                            if (MediaSessionImplBase.this.mState == null || MediaSessionImplBase.this.mState.getState() != 3) {
                                obj = null;
                            } else {
                                obj = 1;
                            }
                            Object obj2;
                            if ((516 & actions) != 0) {
                                obj2 = 1;
                            } else {
                                obj2 = null;
                            }
                            Object obj3;
                            if ((actions & 514) != 0) {
                                obj3 = 1;
                            } else {
                                obj3 = null;
                            }
                            if (obj != null && r0 != null) {
                                callback.onPause();
                                return;
                            } else if (obj == null && r6 != null) {
                                callback.onPlay();
                                return;
                            } else {
                                return;
                            }
                        case 86:
                            if ((actions & 1) != 0) {
                                callback.onStop();
                                return;
                            }
                            return;
                        case 87:
                            if ((actions & 32) != 0) {
                                callback.onSkipToNext();
                                return;
                            }
                            return;
                        case 88:
                            if ((actions & 16) != 0) {
                                callback.onSkipToPrevious();
                                return;
                            }
                            return;
                        case 89:
                            if ((actions & 8) != 0) {
                                callback.onRewind();
                                return;
                            }
                            return;
                        case 90:
                            if ((actions & 64) != 0) {
                                callback.onFastForward();
                                return;
                            }
                            return;
                        case 126:
                            if ((actions & 4) != 0) {
                                callback.onPlay();
                                return;
                            }
                            return;
                        case 127:
                            if ((actions & 2) != 0) {
                                callback.onPause();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        public MediaSessionImplBase(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            if (componentName == null) {
                componentName = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
                if (componentName == null) {
                    Log.w(MediaSessionCompat.TAG, "Couldn't find a unique registered media button receiver in the given context.");
                }
            }
            if (componentName != null && pendingIntent == null) {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setComponent(componentName);
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            }
            if (componentName == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.mContext = context;
            this.mPackageName = context.getPackageName();
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
            this.mTag = str;
            this.mMediaButtonReceiverComponentName = componentName;
            this.mMediaButtonReceiverIntent = pendingIntent;
            this.mStub = new MediaSessionStub();
            this.mToken = new Token(this.mStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            if (VERSION.SDK_INT >= 14) {
                this.mRccObj = MediaSessionCompatApi14.createRemoteControlClient(pendingIntent);
            } else {
                this.mRccObj = null;
            }
        }

        public void setCallback(Callback callback, Handler handler) {
            this.mCallback = callback;
            if (callback == null) {
                if (VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, null);
                }
                if (VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, null);
                    return;
                }
                return;
            }
            if (handler == null) {
                handler = new Handler();
            }
            synchronized (this.mLock) {
                this.mHandler = new MessageHandler(handler.getLooper());
            }
            Object c01482 = new C01482();
            if (VERSION.SDK_INT >= 18) {
                MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, MediaSessionCompatApi18.createPlaybackPositionUpdateListener(c01482));
            }
            if (VERSION.SDK_INT >= 19) {
                MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, MediaSessionCompatApi19.createMetadataUpdateListener(c01482));
            }
        }

        void postToHandler(int i) {
            postToHandler(i, null);
        }

        void postToHandler(int i, Object obj) {
            postToHandler(i, obj, null);
        }

        void postToHandler(int i, Object obj, Bundle bundle) {
            synchronized (this.mLock) {
                if (this.mHandler != null) {
                    this.mHandler.post(i, obj, bundle);
                }
            }
        }

        public void setFlags(int i) {
            synchronized (this.mLock) {
                this.mFlags = i;
            }
            update();
        }

        public void setPlaybackToLocal(int i) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 1;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            if (volumeProviderCompat == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 2;
            this.mVolumeProvider = volumeProviderCompat;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            volumeProviderCompat.setCallback(this.mVolumeCallback);
        }

        public void setActive(boolean z) {
            if (z != this.mIsActive) {
                this.mIsActive = z;
                if (update()) {
                    setMetadata(this.mMetadata);
                    setPlaybackState(this.mState);
                }
            }
        }

        public boolean isActive() {
            return this.mIsActive;
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            sendEvent(str, bundle);
        }

        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            update();
            sendSessionDestroyed();
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.mLock) {
                this.mState = playbackStateCompat;
            }
            sendState(playbackStateCompat);
            if (!this.mIsActive) {
                return;
            }
            if (playbackStateCompat != null) {
                if (VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setState(this.mRccObj, playbackStateCompat.getState(), playbackStateCompat.getPosition(), playbackStateCompat.getPlaybackSpeed(), playbackStateCompat.getLastPositionUpdateTime());
                } else if (VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setState(this.mRccObj, playbackStateCompat.getState());
                }
                if (VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                } else if (VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                } else if (VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, playbackStateCompat.getActions());
                }
            } else if (VERSION.SDK_INT >= 14) {
                MediaSessionCompatApi14.setState(this.mRccObj, 0);
                MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, 0);
            }
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            Bundle bundle = null;
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new Builder(mediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
            }
            synchronized (this.mLock) {
                this.mMetadata = mediaMetadataCompat;
            }
            sendMetadata(mediaMetadataCompat);
            if (!this.mIsActive) {
                return;
            }
            Object obj;
            if (VERSION.SDK_INT >= 19) {
                long j;
                obj = this.mRccObj;
                if (mediaMetadataCompat != null) {
                    bundle = mediaMetadataCompat.getBundle();
                }
                if (this.mState == null) {
                    j = 0;
                } else {
                    j = this.mState.getActions();
                }
                MediaSessionCompatApi19.setMetadata(obj, bundle, j);
            } else if (VERSION.SDK_INT >= 14) {
                obj = this.mRccObj;
                if (mediaMetadataCompat != null) {
                    bundle = mediaMetadataCompat.getBundle();
                }
                MediaSessionCompatApi14.setMetadata(obj, bundle);
            }
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            synchronized (this.mLock) {
                this.mSessionActivity = pendingIntent;
            }
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        }

        public void setQueue(List<QueueItem> list) {
            this.mQueue = list;
            sendQueue(list);
        }

        public void setQueueTitle(CharSequence charSequence) {
            this.mQueueTitle = charSequence;
            sendQueueTitle(charSequence);
        }

        public Object getMediaSession() {
            return null;
        }

        public Object getRemoteControlClient() {
            return this.mRccObj;
        }

        public String getCallingPackage() {
            return null;
        }

        public void setRatingType(int i) {
            this.mRatingType = i;
        }

        public void setExtras(Bundle bundle) {
            this.mExtras = bundle;
            sendExtras(bundle);
        }

        private boolean update() {
            if (this.mIsActive) {
                if (!this.mIsMbrRegistered && (this.mFlags & 1) != 0) {
                    if (VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.registerMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    } else {
                        ((AudioManager) this.mContext.getSystemService("audio")).registerMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                    }
                    this.mIsMbrRegistered = true;
                } else if (this.mIsMbrRegistered && (this.mFlags & 1) == 0) {
                    if (VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    } else {
                        ((AudioManager) this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                    }
                    this.mIsMbrRegistered = false;
                }
                if (VERSION.SDK_INT >= 14) {
                    if (!this.mIsRccRegistered && (this.mFlags & 2) != 0) {
                        MediaSessionCompatApi14.registerRemoteControlClient(this.mContext, this.mRccObj);
                        this.mIsRccRegistered = true;
                        return true;
                    } else if (this.mIsRccRegistered && (this.mFlags & 2) == 0) {
                        MediaSessionCompatApi14.setState(this.mRccObj, 0);
                        MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                        this.mIsRccRegistered = false;
                        return false;
                    }
                }
            }
            if (this.mIsMbrRegistered) {
                if (VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                } else {
                    ((AudioManager) this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                }
                this.mIsMbrRegistered = false;
            }
            if (this.mIsRccRegistered) {
                MediaSessionCompatApi14.setState(this.mRccObj, 0);
                MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                this.mIsRccRegistered = false;
            }
            return false;
        }

        void adjustVolume(int i, int i2) {
            if (this.mVolumeType != 2) {
                this.mAudioManager.adjustStreamVolume(this.mLocalStream, i, i2);
            } else if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onAdjustVolume(i);
            }
        }

        void setVolumeTo(int i, int i2) {
            if (this.mVolumeType != 2) {
                this.mAudioManager.setStreamVolume(this.mLocalStream, i, i2);
            } else if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onSetVolumeTo(i);
            }
        }

        PlaybackStateCompat getStateWithUpdatedPosition() {
            PlaybackStateCompat build;
            long j = -1;
            synchronized (this.mLock) {
                PlaybackStateCompat playbackStateCompat = this.mState;
                if (this.mMetadata != null && this.mMetadata.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                    j = this.mMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                }
            }
            if (playbackStateCompat != null && (playbackStateCompat.getState() == 3 || playbackStateCompat.getState() == 4 || playbackStateCompat.getState() == 5)) {
                long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (lastPositionUpdateTime > 0) {
                    lastPositionUpdateTime = ((long) (playbackStateCompat.getPlaybackSpeed() * ((float) (elapsedRealtime - lastPositionUpdateTime)))) + playbackStateCompat.getPosition();
                    if (j < 0 || lastPositionUpdateTime <= j) {
                        if (lastPositionUpdateTime < 0) {
                            j = 0;
                        } else {
                            j = lastPositionUpdateTime;
                        }
                    }
                    PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder(playbackStateCompat);
                    builder.setState(playbackStateCompat.getState(), j, playbackStateCompat.getPlaybackSpeed(), elapsedRealtime);
                    build = builder.build();
                    return build != null ? playbackStateCompat : build;
                }
            }
            build = null;
            if (build != null) {
            }
        }

        void sendVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onVolumeInfoChanged(parcelableVolumeInfo);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed() {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onSessionDestroyed();
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
            this.mControllerCallbacks.kill();
        }

        private void sendEvent(String str, Bundle bundle) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onEvent(str, bundle);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat playbackStateCompat) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediaMetadataCompat) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onMetadataChanged(mediaMetadataCompat);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List<QueueItem> list) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onQueueChanged(list);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charSequence) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onQueueTitleChanged(charSequence);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle bundle) {
            for (int beginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(beginBroadcast)).onExtrasChanged(bundle);
                } catch (RemoteException e) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }
    }

    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    public static final class QueueItem implements Parcelable {
        public static final Creator<QueueItem> CREATOR = new C01491();
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$QueueItem$1 */
        static class C01491 implements Creator<QueueItem> {
            C01491() {
            }

            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        }

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j) {
            this(null, mediaDescriptionCompat, j);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (j == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else {
                this.mDescription = mediaDescriptionCompat;
                this.mId = j;
                this.mItem = obj;
            }
        }

        QueueItem(Parcel parcel) {
            this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.mDescription.writeToParcel(parcel, i);
            parcel.writeLong(this.mId);
        }

        public int describeContents() {
            return 0;
        }

        public Object getQueueItem() {
            if (this.mItem != null || VERSION.SDK_INT < 21) {
                return this.mItem;
            }
            this.mItem = QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            return this.mItem;
        }

        @Deprecated
        public static QueueItem obtain(Object obj) {
            return fromQueueItem(obj);
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(QueueItem.getDescription(obj)), QueueItem.getQueueId(obj));
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<QueueItem> arrayList = new ArrayList();
            for (Object fromQueueItem : list) {
                arrayList.add(fromQueueItem(fromQueueItem));
            }
            return arrayList;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }
    }

    static final class ResultReceiverWrapper implements Parcelable {
        public static final Creator<ResultReceiverWrapper> CREATOR = new C01501();
        private ResultReceiver mResultReceiver;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1 */
        static class C01501 implements Creator<ResultReceiverWrapper> {
            C01501() {
            }

            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        }

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.mResultReceiver.writeToParcel(parcel, i);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR = new C01511();
        private final Object mInner;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Token$1 */
        static class C01511 implements Creator<Token> {
            C01511() {
            }

            public Token createFromParcel(Parcel parcel) {
                Object readParcelable;
                if (VERSION.SDK_INT >= 21) {
                    readParcelable = parcel.readParcelable(null);
                } else {
                    readParcelable = parcel.readStrongBinder();
                }
                return new Token(readParcelable);
            }

            public Token[] newArray(int i) {
                return new Token[i];
            }
        }

        Token(Object obj) {
            this.mInner = obj;
        }

        public static Token fromToken(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(MediaSessionCompatApi21.verifyToken(obj));
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.mInner, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.mInner);
            }
        }

        public int hashCode() {
            if (this.mInner == null) {
                return 0;
            }
            return this.mInner.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.mInner == null) {
                if (token.mInner != null) {
                    return false;
                }
                return true;
            } else if (token.mInner == null) {
                return false;
            } else {
                return this.mInner.equals(token.mInner);
            }
        }

        public Object getToken() {
            return this.mInner;
        }
    }

    public MediaSessionCompat(Context context, String str) {
        this(context, str, null, null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this.mActiveListeners = new ArrayList();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        } else {
            if (VERSION.SDK_INT >= 21) {
                this.mImpl = new MediaSessionImplApi21(context, str);
            } else {
                this.mImpl = new MediaSessionImplBase(context, str, componentName, pendingIntent);
            }
            this.mController = new MediaControllerCompat(context, this);
            if (sMaxBitmapSize == 0) {
                sMaxBitmapSize = (int) TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
            }
        }
    }

    private MediaSessionCompat(Context context, MediaSessionImpl mediaSessionImpl) {
        this.mActiveListeners = new ArrayList();
        this.mImpl = mediaSessionImpl;
        this.mController = new MediaControllerCompat(context, this);
    }

    public void setCallback(Callback callback) {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler) {
        MediaSessionImpl mediaSessionImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mediaSessionImpl.setCallback(callback, handler);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.mImpl.setSessionActivity(pendingIntent);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.mImpl.setMediaButtonReceiver(pendingIntent);
    }

    public void setFlags(int i) {
        this.mImpl.setFlags(i);
    }

    public void setPlaybackToLocal(int i) {
        this.mImpl.setPlaybackToLocal(i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(volumeProviderCompat);
    }

    public void setActive(boolean z) {
        this.mImpl.setActive(z);
        Iterator it = this.mActiveListeners.iterator();
        while (it.hasNext()) {
            ((OnActiveChangeListener) it.next()).onActiveChanged();
        }
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(str, bundle);
    }

    public void release() {
        this.mImpl.release();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.mImpl.setPlaybackState(playbackStateCompat);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.mImpl.setMetadata(mediaMetadataCompat);
    }

    public void setQueue(List<QueueItem> list) {
        this.mImpl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.mImpl.setQueueTitle(charSequence);
    }

    public void setRatingType(int i) {
        this.mImpl.setRatingType(i);
    }

    public void setExtras(Bundle bundle) {
        this.mImpl.setExtras(bundle);
    }

    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }

    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.add(onActiveChangeListener);
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.remove(onActiveChangeListener);
    }

    @Deprecated
    public static MediaSessionCompat obtain(Context context, Object obj) {
        return fromMediaSession(context, obj);
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        if (context == null || obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat(context, new MediaSessionImplApi21(obj));
    }
}
