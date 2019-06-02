package com.twitter.sdk.android.tweetui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.internal.VideoControlView;
import com.twitter.sdk.android.tweetui.internal.VideoView;

public class PlayerActivity extends Activity {
    /* renamed from: a */
    C4698d f16493a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4689R.layout.tw__player_activity);
        ProgressBar progressBar = (ProgressBar) findViewById(C4689R.id.video_progress_view);
        VideoView videoView = (VideoView) findViewById(C4689R.id.video_view);
        VideoControlView videoControlView = (VideoControlView) findViewById(C4689R.id.video_control_view);
        MediaEntity mediaEntity = (MediaEntity) getIntent().getSerializableExtra("MEDIA_ENTITY");
        new C4730l(C1518i.a()).mo6176a(getIntent().getLongExtra("TWEET_ID", 0), mediaEntity);
        this.f16493a = new C4698d(videoView, videoControlView, progressBar);
        this.f16493a.m18500a(mediaEntity);
    }

    protected void onResume() {
        super.onResume();
        this.f16493a.m18499a();
    }

    protected void onPause() {
        this.f16493a.m18502b();
        super.onPause();
    }

    public void onDestroy() {
        this.f16493a.m18503c();
        super.onDestroy();
    }
}
