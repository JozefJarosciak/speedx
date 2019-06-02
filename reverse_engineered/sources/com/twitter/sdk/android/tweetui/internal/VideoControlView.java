package com.twitter.sdk.android.tweetui.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.twitter.sdk.android.tweetui.C4689R;

public class VideoControlView extends FrameLayout {
    /* renamed from: a */
    C4712a f16556a;
    /* renamed from: b */
    ImageButton f16557b;
    /* renamed from: c */
    TextView f16558c;
    /* renamed from: d */
    TextView f16559d;
    /* renamed from: e */
    SeekBar f16560e;
    @SuppressLint({"HandlerLeak"})
    /* renamed from: f */
    private final Handler f16561f = new C47091(this);

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$1 */
    class C47091 extends Handler {
        /* renamed from: a */
        final /* synthetic */ VideoControlView f16553a;

        C47091(VideoControlView videoControlView) {
            this.f16553a = videoControlView;
        }

        public void handleMessage(Message message) {
            if (message.what == 1001 && this.f16553a.f16556a != null) {
                this.f16553a.m18549d();
                this.f16553a.m18550e();
                if (this.f16553a.m18556k() && this.f16553a.f16556a.mo6170c()) {
                    sendMessageDelayed(obtainMessage(1001), 500);
                }
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$2 */
    class C47102 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ VideoControlView f16554a;

        C47102(VideoControlView videoControlView) {
            this.f16554a = videoControlView;
        }

        public void onClick(View view) {
            if (this.f16554a.f16556a.mo6170c()) {
                this.f16554a.f16556a.mo6169b();
            } else {
                this.f16554a.f16556a.mo6167a();
            }
            this.f16554a.m18555j();
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$3 */
    class C47113 implements OnSeekBarChangeListener {
        /* renamed from: a */
        final /* synthetic */ VideoControlView f16555a;

        C47113(VideoControlView videoControlView) {
            this.f16555a = videoControlView;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                long duration = ((long) (this.f16555a.f16556a.getDuration() * i)) / 1000;
                this.f16555a.f16556a.mo6168a((int) duration);
                this.f16555a.setCurrentTime((int) duration);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.f16555a.f16561f.removeMessages(1001);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            this.f16555a.f16561f.sendEmptyMessage(1001);
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$a */
    public interface C4712a {
        /* renamed from: a */
        void mo6167a();

        /* renamed from: a */
        void mo6168a(int i);

        /* renamed from: b */
        void mo6169b();

        /* renamed from: c */
        boolean mo6170c();

        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();
    }

    public VideoControlView(Context context) {
        super(context);
    }

    public VideoControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setMediaPlayer(C4712a c4712a) {
        this.f16556a = c4712a;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        m18545a();
    }

    /* renamed from: a */
    void m18545a() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C4689R.layout.tw__video_control, this);
        this.f16557b = (ImageButton) findViewById(C4689R.id.tw__state_control);
        this.f16558c = (TextView) findViewById(C4689R.id.tw__current_time);
        this.f16559d = (TextView) findViewById(C4689R.id.tw__duration);
        this.f16560e = (SeekBar) findViewById(C4689R.id.tw__progress);
        this.f16560e.setMax(1000);
        this.f16560e.setOnSeekBarChangeListener(m18548c());
        this.f16557b.setOnClickListener(m18547b());
        setDuration(0);
        setCurrentTime(0);
        m18546a(0, 0, 0);
    }

    /* renamed from: b */
    OnClickListener m18547b() {
        return new C47102(this);
    }

    /* renamed from: c */
    OnSeekBarChangeListener m18548c() {
        return new C47113(this);
    }

    /* renamed from: d */
    void m18549d() {
        int duration = this.f16556a.getDuration();
        int currentPosition = this.f16556a.getCurrentPosition();
        int bufferPercentage = this.f16556a.getBufferPercentage();
        setDuration(duration);
        setCurrentTime(currentPosition);
        m18546a(currentPosition, duration, bufferPercentage);
    }

    void setDuration(int i) {
        this.f16559d.setText(C4724c.m18599a((long) i));
    }

    void setCurrentTime(int i) {
        this.f16558c.setText(C4724c.m18599a((long) i));
    }

    /* renamed from: a */
    void m18546a(int i, int i2, int i3) {
        this.f16560e.setProgress((int) (i2 > 0 ? (1000 * ((long) i)) / ((long) i2) : 0));
        this.f16560e.setSecondaryProgress(i3 * 10);
    }

    /* renamed from: e */
    void m18550e() {
        if (this.f16556a.mo6170c()) {
            m18552g();
        } else if (this.f16556a.getCurrentPosition() > Math.max(this.f16556a.getDuration() - 500, 0)) {
            m18553h();
        } else {
            m18551f();
        }
    }

    /* renamed from: f */
    void m18551f() {
        this.f16557b.setImageResource(C4689R.drawable.tw__video_play_btn);
        this.f16557b.setContentDescription(getContext().getString(C4689R.string.tw__play));
    }

    /* renamed from: g */
    void m18552g() {
        this.f16557b.setImageResource(C4689R.drawable.tw__video_pause_btn);
        this.f16557b.setContentDescription(getContext().getString(C4689R.string.tw__pause));
    }

    /* renamed from: h */
    void m18553h() {
        this.f16557b.setImageResource(C4689R.drawable.tw__video_replay_btn);
        this.f16557b.setContentDescription(getContext().getString(C4689R.string.tw__replay));
    }

    /* renamed from: i */
    void m18554i() {
        this.f16561f.removeMessages(1001);
        if (VERSION.SDK_INT >= 12) {
            C4721a.m18593a(this, 150);
        } else {
            setVisibility(4);
        }
    }

    /* renamed from: j */
    void m18555j() {
        this.f16561f.sendEmptyMessage(1001);
        if (VERSION.SDK_INT >= 12) {
            C4721a.m18594b(this, 150);
        } else {
            setVisibility(0);
        }
    }

    /* renamed from: k */
    public boolean m18556k() {
        return getVisibility() == 0;
    }

    /* renamed from: l */
    public void m18557l() {
        this.f16561f.sendEmptyMessage(1001);
    }
}
