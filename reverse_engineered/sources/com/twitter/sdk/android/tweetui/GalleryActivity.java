package com.twitter.sdk.android.tweetui;

import android.app.Activity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.internal.MultiTouchImageView;

public class GalleryActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4689R.layout.tw__gallery_activity);
        MultiTouchImageView multiTouchImageView = (MultiTouchImageView) findViewById(C4689R.id.image_view);
        Picasso.with(this).load(((MediaEntity) getIntent().getSerializableExtra("MEDIA_ENTITY")).mediaUrlHttps).into(multiTouchImageView);
    }
}
