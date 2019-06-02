package com.twitter.sdk.android.tweetcomposer;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.tweetcomposer.C4685i.C4684a;

/* compiled from: AppCardView */
/* renamed from: com.twitter.sdk.android.tweetcomposer.a */
public class C4672a extends LinearLayout {
    /* renamed from: a */
    ImageView f16453a;
    /* renamed from: b */
    ViewGroup f16454b;
    /* renamed from: c */
    TextView f16455c;
    /* renamed from: d */
    TextView f16456d;
    /* renamed from: e */
    TextView f16457e;

    public C4672a(Context context) {
        this(context, null);
    }

    public C4672a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18432a(context);
    }

    @TargetApi(11)
    public C4672a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18432a(context);
    }

    /* renamed from: a */
    void m18432a(Context context) {
        setOrientation(1);
        C4672a.inflate(context, C4666R.layout.tw__app_card, this);
        m18431a();
        m18433b();
    }

    /* renamed from: a */
    void m18431a() {
        this.f16453a = (ImageView) findViewById(C4666R.id.tw__app_image);
        this.f16456d = (TextView) findViewById(C4666R.id.tw__app_name);
        this.f16457e = (TextView) findViewById(C4666R.id.tw__app_store_name);
        this.f16455c = (TextView) findViewById(C4666R.id.tw__app_install_button);
        this.f16454b = (ViewGroup) findViewById(C4666R.id.tw__app_info_layout);
    }

    void setCard(Card card) {
        setImage(Uri.parse(card.f16419b));
        setAppName(card.f16420c);
    }

    void setImage(Uri uri) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(C4666R.dimen.tw__card_radius_medium);
        Picasso.with(getContext()).load(uri).transform(new C4684a().m18468a(dimensionPixelSize, dimensionPixelSize, 0, 0).m18469a()).fit().centerCrop().into(this.f16453a);
    }

    void setAppName(String str) {
        this.f16456d.setText(str);
    }

    protected void onMeasure(int i, int i2) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(C4666R.dimen.tw__card_maximum_width);
        int size = MeasureSpec.getSize(i);
        if (dimensionPixelSize > 0 && dimensionPixelSize < size) {
            i = MeasureSpec.makeMeasureSpec(dimensionPixelSize, MeasureSpec.getMode(i));
        }
        super.onMeasure(i, i2);
    }

    /* renamed from: b */
    void m18433b() {
        this.f16455c.setTextColor(getResources().getColor(C4666R.color.tw__composer_blue_text));
    }
}
