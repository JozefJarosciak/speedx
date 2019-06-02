package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.C4689R;

public class MediaBadgeView extends FrameLayout {
    /* renamed from: a */
    TextView f16536a;
    /* renamed from: b */
    ImageView f16537b;

    public MediaBadgeView(Context context) {
        this(context, null);
    }

    public MediaBadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaBadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18524a(context);
    }

    /* renamed from: a */
    void m18524a(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C4689R.layout.tw__media_badge, this, true);
        this.f16536a = (TextView) inflate.findViewById(C4689R.id.tw__video_duration);
        this.f16537b = (ImageView) inflate.findViewById(C4689R.id.tw__gif_badge);
    }

    public void setMediaEntity(MediaEntity mediaEntity) {
        if ("animated_gif".equals(mediaEntity.type)) {
            this.f16537b.setVisibility(0);
            this.f16536a.setVisibility(8);
        } else if ("video".equals(mediaEntity.type)) {
            this.f16536a.setVisibility(0);
            this.f16537b.setVisibility(8);
            this.f16536a.setText(C4724c.m18599a(mediaEntity.videoInfo == null ? 0 : mediaEntity.videoInfo.durationMillis));
        } else {
            this.f16536a.setVisibility(8);
            this.f16537b.setVisibility(8);
        }
    }
}
