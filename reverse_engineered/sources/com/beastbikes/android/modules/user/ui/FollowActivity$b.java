package com.beastbikes.android.modules.user.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.sina.weibo.SinaWeibo;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.widget.convenientbanner.p116b.C1935b;
import com.beastbikes.framework.ui.android.WebActivity;

class FollowActivity$b implements C1935b<FollowActivity$a> {
    /* renamed from: a */
    final /* synthetic */ FollowActivity f11649a;
    /* renamed from: b */
    private LinearLayout f11650b;
    /* renamed from: c */
    private ImageView f11651c;
    /* renamed from: d */
    private TextView f11652d;
    /* renamed from: e */
    private LinearLayout f11653e;
    /* renamed from: f */
    private ImageView f11654f;
    /* renamed from: g */
    private TextView f11655g;
    /* renamed from: h */
    private LinearLayout f11656h;
    /* renamed from: i */
    private ImageView f11657i;
    /* renamed from: j */
    private TextView f11658j;

    private FollowActivity$b(FollowActivity followActivity) {
        this.f11649a = followActivity;
    }

    /* renamed from: a */
    public View mo3293a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.follow_banner_item, null);
        this.f11650b = (LinearLayout) inflate.findViewById(C1373R.id.follow_banner_item_1);
        this.f11651c = (ImageView) inflate.findViewById(C1373R.id.follow_banner_item_img_1);
        this.f11652d = (TextView) inflate.findViewById(C1373R.id.follow_banner_item_label_1);
        this.f11653e = (LinearLayout) inflate.findViewById(C1373R.id.follow_banner_item_2);
        this.f11654f = (ImageView) inflate.findViewById(C1373R.id.follow_banner_item_img_2);
        this.f11655g = (TextView) inflate.findViewById(C1373R.id.follow_banner_item_label_2);
        this.f11656h = (LinearLayout) inflate.findViewById(C1373R.id.follow_banner_item_3);
        this.f11657i = (ImageView) inflate.findViewById(C1373R.id.follow_banner_item_img_3);
        this.f11658j = (TextView) inflate.findViewById(C1373R.id.follow_banner_item_label_3);
        return inflate;
    }

    /* renamed from: a */
    public void m12465a(Context context, int i, final FollowActivity$a followActivity$a) {
        if (followActivity$a != null) {
            this.f11650b.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ FollowActivity$b f11642b;

                public void onClick(View view) {
                    this.f11642b.m12464a(followActivity$a.f11637d, followActivity$a.f11635b);
                }
            });
            this.f11651c.setImageResource(followActivity$a.f11636c);
            this.f11652d.setText(followActivity$a.f11635b);
            this.f11654f.setImageResource(followActivity$a.f11639f);
            this.f11655g.setText(followActivity$a.f11638e);
            this.f11653e.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ FollowActivity$b f11644b;

                public void onClick(View view) {
                    this.f11644b.m12464a(followActivity$a.f11640g, followActivity$a.f11638e);
                }
            });
        }
    }

    /* renamed from: a */
    public void m12464a(int i, int i2) {
        final Intent intent = new Intent(this.f11649a, FollowSearchResultActivity.class);
        intent.putExtra("follow_type", i);
        intent.putExtra(WebActivity.EXTRA_TITLE, this.f11649a.getString(i2));
        if (i == 2) {
            C1541b.m8486b(this.f11649a, SinaWeibo.NAME, new C1540b(this) {
                /* renamed from: b */
                final /* synthetic */ FollowActivity$b f11648b;

                /* renamed from: a */
                public void mo3123a(final C1537a c1537a) {
                    if (c1537a != null) {
                        this.f11648b.f11649a.runOnUiThread(new Runnable(this) {
                            /* renamed from: b */
                            final /* synthetic */ C24793 f11646b;

                            public void run() {
                                intent.putExtra("open_id", c1537a.m8475b());
                                intent.putExtra("access_token", c1537a.m8474a());
                                this.f11646b.f11648b.f11649a.startActivity(intent);
                            }
                        });
                    }
                }
            });
        } else {
            this.f11649a.startActivity(intent);
        }
    }
}
