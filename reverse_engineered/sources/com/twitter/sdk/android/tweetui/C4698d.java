package com.twitter.sdk.android.tweetui;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.internal.C4725d;
import com.twitter.sdk.android.tweetui.internal.VideoControlView;
import com.twitter.sdk.android.tweetui.internal.VideoView;
import io.fabric.sdk.android.C1520c;

/* compiled from: PlayerController */
/* renamed from: com.twitter.sdk.android.tweetui.d */
class C4698d {
    /* renamed from: a */
    final VideoView f16516a;
    /* renamed from: b */
    final VideoControlView f16517b;
    /* renamed from: c */
    final ProgressBar f16518c;
    /* renamed from: d */
    int f16519d = 0;
    /* renamed from: e */
    boolean f16520e = true;

    /* compiled from: PlayerController */
    /* renamed from: com.twitter.sdk.android.tweetui.d$1 */
    class C46951 implements OnPreparedListener {
        /* renamed from: a */
        final /* synthetic */ C4698d f16513a;

        C46951(C4698d c4698d) {
            this.f16513a = c4698d;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f16513a.f16518c.setVisibility(8);
        }
    }

    /* compiled from: PlayerController */
    /* renamed from: com.twitter.sdk.android.tweetui.d$2 */
    class C46962 implements OnInfoListener {
        /* renamed from: a */
        final /* synthetic */ C4698d f16514a;

        C46962(C4698d c4698d) {
            this.f16514a = c4698d;
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (i == 702) {
                this.f16514a.f16518c.setVisibility(8);
                return true;
            } else if (i != 701) {
                return false;
            } else {
                this.f16514a.f16518c.setVisibility(0);
                return true;
            }
        }
    }

    /* compiled from: PlayerController */
    /* renamed from: com.twitter.sdk.android.tweetui.d$3 */
    class C46973 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4698d f16515a;

        C46973(C4698d c4698d) {
            this.f16515a = c4698d;
        }

        public void onClick(View view) {
            if (this.f16515a.f16516a.mo6170c()) {
                this.f16515a.f16516a.mo6169b();
            } else {
                this.f16515a.f16516a.mo6167a();
            }
        }
    }

    C4698d(VideoView videoView, VideoControlView videoControlView, ProgressBar progressBar) {
        this.f16516a = videoView;
        this.f16517b = videoControlView;
        this.f16518c = progressBar;
    }

    /* renamed from: a */
    void m18500a(MediaEntity mediaEntity) {
        try {
            boolean b = C4725d.m18602b(mediaEntity);
            Uri parse = Uri.parse(C4725d.m18600a(mediaEntity).url);
            m18501a(b);
            this.f16516a.setOnPreparedListener(new C46951(this));
            this.f16516a.setOnInfoListener(new C46962(this));
            this.f16516a.m18589a(parse, b);
            this.f16516a.requestFocus();
        } catch (Throwable e) {
            C1520c.h().mo6222d("PlayerController", "Error occurred during video playback", e);
        }
    }

    /* renamed from: a */
    void m18499a() {
        if (this.f16519d != 0) {
            this.f16516a.mo6168a(this.f16519d);
        }
        if (this.f16520e) {
            this.f16516a.mo6167a();
            this.f16517b.m18557l();
        }
    }

    /* renamed from: b */
    void m18502b() {
        this.f16520e = this.f16516a.mo6170c();
        this.f16519d = this.f16516a.getCurrentPosition();
        this.f16516a.mo6169b();
    }

    /* renamed from: c */
    void m18503c() {
        this.f16516a.m18592d();
    }

    /* renamed from: a */
    void m18501a(boolean z) {
        if (z) {
            m18504d();
        } else {
            m18505e();
        }
    }

    /* renamed from: d */
    void m18504d() {
        this.f16517b.setVisibility(4);
        this.f16516a.setOnClickListener(new C46973(this));
    }

    /* renamed from: e */
    void m18505e() {
        this.f16516a.setMediaController(this.f16517b);
    }
}
