package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;
import com.twitter.sdk.android.tweetui.internal.VideoControlView.C4712a;

public class VideoView extends SurfaceView implements C4712a {
    /* renamed from: a */
    OnVideoSizeChangedListener f16569a;
    /* renamed from: b */
    OnPreparedListener f16570b;
    /* renamed from: c */
    Callback f16571c;
    /* renamed from: d */
    private String f16572d;
    /* renamed from: e */
    private Uri f16573e;
    /* renamed from: f */
    private int f16574f;
    /* renamed from: g */
    private int f16575g;
    /* renamed from: h */
    private SurfaceHolder f16576h;
    /* renamed from: i */
    private MediaPlayer f16577i;
    /* renamed from: j */
    private int f16578j;
    /* renamed from: k */
    private int f16579k;
    /* renamed from: l */
    private int f16580l;
    /* renamed from: m */
    private int f16581m;
    /* renamed from: n */
    private int f16582n;
    /* renamed from: o */
    private VideoControlView f16583o;
    /* renamed from: p */
    private OnCompletionListener f16584p;
    /* renamed from: q */
    private OnPreparedListener f16585q;
    /* renamed from: r */
    private int f16586r;
    /* renamed from: s */
    private OnErrorListener f16587s;
    /* renamed from: t */
    private OnInfoListener f16588t;
    /* renamed from: u */
    private int f16589u;
    /* renamed from: v */
    private boolean f16590v;
    /* renamed from: w */
    private OnCompletionListener f16591w;
    /* renamed from: x */
    private OnInfoListener f16592x;
    /* renamed from: y */
    private OnErrorListener f16593y;
    /* renamed from: z */
    private OnBufferingUpdateListener f16594z;

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$1 */
    class C47131 implements OnVideoSizeChangedListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16562a;

        C47131(VideoView videoView) {
            this.f16562a = videoView;
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            this.f16562a.f16579k = mediaPlayer.getVideoWidth();
            this.f16562a.f16580l = mediaPlayer.getVideoHeight();
            if (this.f16562a.f16579k != 0 && this.f16562a.f16580l != 0) {
                this.f16562a.getHolder().setFixedSize(this.f16562a.f16579k, this.f16562a.f16580l);
                this.f16562a.requestLayout();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$2 */
    class C47142 implements OnPreparedListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16563a;

        C47142(VideoView videoView) {
            this.f16563a = videoView;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f16563a.f16574f = 2;
            if (this.f16563a.f16585q != null) {
                this.f16563a.f16585q.onPrepared(this.f16563a.f16577i);
            }
            if (this.f16563a.f16583o != null) {
                this.f16563a.f16583o.setEnabled(true);
            }
            this.f16563a.f16579k = mediaPlayer.getVideoWidth();
            this.f16563a.f16580l = mediaPlayer.getVideoHeight();
            int f = this.f16563a.f16589u;
            if (f != 0) {
                this.f16563a.mo6168a(f);
            }
            if (this.f16563a.f16579k != 0 && this.f16563a.f16580l != 0) {
                this.f16563a.getHolder().setFixedSize(this.f16563a.f16579k, this.f16563a.f16580l);
                if (this.f16563a.f16581m != this.f16563a.f16579k || this.f16563a.f16582n != this.f16563a.f16580l) {
                    return;
                }
                if (this.f16563a.f16575g == 3) {
                    this.f16563a.mo6167a();
                    if (this.f16563a.f16583o != null) {
                        this.f16563a.f16583o.m18555j();
                    }
                } else if (!this.f16563a.mo6170c()) {
                    if ((f != 0 || this.f16563a.getCurrentPosition() > 0) && this.f16563a.f16583o != null) {
                        this.f16563a.f16583o.m18555j();
                    }
                }
            } else if (this.f16563a.f16575g == 3) {
                this.f16563a.mo6167a();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$3 */
    class C47153 implements OnCompletionListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16564a;

        C47153(VideoView videoView) {
            this.f16564a = videoView;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.f16564a.f16574f = 5;
            this.f16564a.f16575g = 5;
            if (this.f16564a.f16584p != null) {
                this.f16564a.f16584p.onCompletion(this.f16564a.f16577i);
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$4 */
    class C47164 implements OnInfoListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16565a;

        C47164(VideoView videoView) {
            this.f16565a = videoView;
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (this.f16565a.f16588t != null) {
                this.f16565a.f16588t.onInfo(mediaPlayer, i, i2);
            }
            return true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$5 */
    class C47175 implements OnErrorListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16566a;

        C47175(VideoView videoView) {
            this.f16566a = videoView;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Log.d(this.f16566a.f16572d, "Error: " + i + "," + i2);
            this.f16566a.f16574f = -1;
            this.f16566a.f16575g = -1;
            if (this.f16566a.f16583o != null) {
                this.f16566a.f16583o.m18554i();
            }
            return (this.f16566a.f16587s == null || this.f16566a.f16587s.onError(this.f16566a.f16577i, i, i2)) ? true : true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$6 */
    class C47186 implements OnBufferingUpdateListener {
        /* renamed from: a */
        final /* synthetic */ VideoView f16567a;

        C47186(VideoView videoView) {
            this.f16567a = videoView;
        }

        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            this.f16567a.f16586r = i;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$7 */
    class C47197 implements Callback {
        /* renamed from: a */
        final /* synthetic */ VideoView f16568a;

        C47197(VideoView videoView) {
            this.f16568a = videoView;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Object obj = 1;
            this.f16568a.f16581m = i2;
            this.f16568a.f16582n = i3;
            Object obj2 = this.f16568a.f16575g == 3 ? 1 : null;
            if (!(this.f16568a.f16579k == i2 && this.f16568a.f16580l == i3)) {
                obj = null;
            }
            if (this.f16568a.f16577i != null && obj2 != null && r1 != null) {
                if (this.f16568a.f16589u != 0) {
                    this.f16568a.mo6168a(this.f16568a.f16589u);
                }
                this.f16568a.mo6167a();
                if (this.f16568a.f16583o != null) {
                    this.f16568a.f16583o.m18555j();
                }
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.f16568a.f16576h = surfaceHolder;
            this.f16568a.m18574f();
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.f16568a.f16576h = null;
            if (this.f16568a.f16583o != null) {
                this.f16568a.f16583o.m18554i();
            }
            this.f16568a.m18562a(true);
        }
    }

    public VideoView(Context context) {
        super(context);
        this.f16572d = "VideoView";
        this.f16574f = 0;
        this.f16575g = 0;
        this.f16576h = null;
        this.f16577i = null;
        this.f16569a = new C47131(this);
        this.f16570b = new C47142(this);
        this.f16591w = new C47153(this);
        this.f16592x = new C47164(this);
        this.f16593y = new C47175(this);
        this.f16594z = new C47186(this);
        this.f16571c = new C47197(this);
        m18571e();
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f16572d = "VideoView";
        this.f16574f = 0;
        this.f16575g = 0;
        this.f16576h = null;
        this.f16577i = null;
        this.f16569a = new C47131(this);
        this.f16570b = new C47142(this);
        this.f16591w = new C47153(this);
        this.f16592x = new C47164(this);
        this.f16593y = new C47175(this);
        this.f16594z = new C47186(this);
        this.f16571c = new C47197(this);
        m18571e();
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.f16579k, i);
        int defaultSize2 = getDefaultSize(this.f16580l, i2);
        if (this.f16579k > 0 && this.f16580l > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == Ints.MAX_POWER_OF_TWO && mode2 == Ints.MAX_POWER_OF_TWO) {
                if (this.f16579k * defaultSize2 < this.f16580l * size) {
                    defaultSize = (this.f16579k * defaultSize2) / this.f16580l;
                } else if (this.f16579k * defaultSize2 > this.f16580l * size) {
                    defaultSize2 = (this.f16580l * size) / this.f16579k;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == Ints.MAX_POWER_OF_TWO) {
                defaultSize = (this.f16580l * size) / this.f16579k;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == Ints.MAX_POWER_OF_TWO) {
                defaultSize = (this.f16579k * defaultSize2) / this.f16580l;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.f16579k;
                defaultSize = this.f16580l;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.f16579k * defaultSize2) / this.f16580l;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.f16580l * size) / this.f16579k;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    /* renamed from: e */
    private void m18571e() {
        this.f16579k = 0;
        this.f16580l = 0;
        getHolder().addCallback(this.f16571c);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f16574f = 0;
        this.f16575g = 0;
    }

    /* renamed from: a */
    public void m18589a(Uri uri, boolean z) {
        this.f16573e = uri;
        this.f16590v = z;
        this.f16589u = 0;
        m18574f();
        requestLayout();
        invalidate();
    }

    /* renamed from: d */
    public void m18592d() {
        if (this.f16577i != null) {
            this.f16577i.stop();
            this.f16577i.release();
            this.f16577i = null;
            this.f16574f = 0;
            this.f16575g = 0;
        }
    }

    /* renamed from: f */
    private void m18574f() {
        if (this.f16573e != null && this.f16576h != null) {
            m18562a(false);
            try {
                this.f16577i = new MediaPlayer();
                if (this.f16578j != 0) {
                    this.f16577i.setAudioSessionId(this.f16578j);
                } else {
                    this.f16578j = this.f16577i.getAudioSessionId();
                }
                this.f16577i.setOnPreparedListener(this.f16570b);
                this.f16577i.setOnVideoSizeChangedListener(this.f16569a);
                this.f16577i.setOnCompletionListener(this.f16591w);
                this.f16577i.setOnErrorListener(this.f16593y);
                this.f16577i.setOnInfoListener(this.f16592x);
                this.f16577i.setOnBufferingUpdateListener(this.f16594z);
                this.f16586r = 0;
                this.f16577i.setLooping(this.f16590v);
                this.f16577i.setDataSource(getContext(), this.f16573e);
                this.f16577i.setDisplay(this.f16576h);
                this.f16577i.setAudioStreamType(3);
                this.f16577i.setScreenOnWhilePlaying(true);
                this.f16577i.prepareAsync();
                this.f16574f = 1;
                m18577g();
            } catch (Throwable e) {
                Log.w(this.f16572d, "Unable to open content: " + this.f16573e, e);
                this.f16574f = -1;
                this.f16575g = -1;
                this.f16593y.onError(this.f16577i, 1, 0);
            }
        }
    }

    public void setMediaController(VideoControlView videoControlView) {
        if (this.f16583o != null) {
            this.f16583o.m18554i();
        }
        this.f16583o = videoControlView;
        m18577g();
    }

    /* renamed from: g */
    private void m18577g() {
        if (this.f16577i != null && this.f16583o != null) {
            this.f16583o.setMediaPlayer(this);
            this.f16583o.setEnabled(m18581i());
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.f16585q = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.f16584p = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.f16587s = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.f16588t = onInfoListener;
    }

    /* renamed from: a */
    private void m18562a(boolean z) {
        if (this.f16577i != null) {
            this.f16577i.reset();
            this.f16577i.release();
            this.f16577i = null;
            this.f16574f = 0;
            if (z) {
                this.f16575g = 0;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (m18581i() && this.f16583o != null) {
            m18579h();
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (m18581i() && this.f16583o != null) {
            m18579h();
        }
        return super.onTrackballEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 82 || i == 5 || i == 6) ? false : true;
        if (m18581i() && z && this.f16583o != null) {
            if (i == 79 || i == 85) {
                if (this.f16577i.isPlaying()) {
                    mo6169b();
                    this.f16583o.m18555j();
                    return true;
                }
                mo6167a();
                this.f16583o.m18554i();
                return true;
            } else if (i == 126) {
                if (this.f16577i.isPlaying()) {
                    return true;
                }
                mo6167a();
                this.f16583o.m18554i();
                return true;
            } else if (i != 86 && i != 127) {
                m18579h();
            } else if (!this.f16577i.isPlaying()) {
                return true;
            } else {
                mo6169b();
                this.f16583o.m18555j();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: h */
    private void m18579h() {
        if (this.f16583o.m18556k()) {
            this.f16583o.m18554i();
        } else {
            this.f16583o.m18555j();
        }
    }

    /* renamed from: a */
    public void mo6167a() {
        if (m18581i()) {
            this.f16577i.start();
            this.f16574f = 3;
        }
        this.f16575g = 3;
    }

    /* renamed from: b */
    public void mo6169b() {
        if (m18581i() && this.f16577i.isPlaying()) {
            this.f16577i.pause();
            this.f16574f = 4;
        }
        this.f16575g = 4;
    }

    public int getDuration() {
        if (m18581i()) {
            return this.f16577i.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (m18581i()) {
            return this.f16577i.getCurrentPosition();
        }
        return 0;
    }

    /* renamed from: a */
    public void mo6168a(int i) {
        if (m18581i()) {
            this.f16577i.seekTo(i);
            this.f16589u = 0;
            return;
        }
        this.f16589u = i;
    }

    /* renamed from: c */
    public boolean mo6170c() {
        return m18581i() && this.f16577i.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.f16577i != null) {
            return this.f16586r;
        }
        return 0;
    }

    /* renamed from: i */
    private boolean m18581i() {
        return (this.f16577i == null || this.f16574f == -1 || this.f16574f == 0 || this.f16574f == 1) ? false : true;
    }
}
