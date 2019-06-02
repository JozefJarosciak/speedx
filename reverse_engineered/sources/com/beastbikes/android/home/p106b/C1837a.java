package com.beastbikes.android.home.p106b;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.squareup.picasso.Picasso;

/* compiled from: HeaderViewHolder */
/* renamed from: com.beastbikes.android.home.b.a */
public class C1837a extends LinearLayout implements OnSharedPreferenceChangeListener {
    /* renamed from: a */
    private ImageView f8278a;
    /* renamed from: b */
    private LinearLayout f8279b;
    /* renamed from: c */
    private TextView f8280c;
    /* renamed from: d */
    private TextView f8281d;
    /* renamed from: e */
    private TextView f8282e;
    /* renamed from: f */
    private Context f8283f;
    /* renamed from: g */
    private AVUser f8284g;

    public C1837a(Context context) {
        super(context);
        m9596a(context);
    }

    public C1837a(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m9596a(context);
    }

    public C1837a(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9596a(context);
    }

    /* renamed from: a */
    private void m9596a(final Context context) {
        this.f8284g = AVUser.getCurrentUser();
        this.f8283f = context;
        C1837a.inflate(context, C1373R.layout.header_just_username, this);
        this.f8278a = (ImageView) findViewById(C1373R.id.iv_avatar1);
        this.f8280c = (TextView) findViewById(C1373R.id.header_nick_name);
        this.f8279b = (LinearLayout) findViewById(C1373R.id.header_club_view);
        this.f8281d = (TextView) findViewById(C1373R.id.header_club_level);
        this.f8282e = (TextView) findViewById(C1373R.id.header_club_name);
        if (this.f8284g != null) {
            this.f8278a.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C1837a f8277b;

                public void onClick(View view) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("user_id", this.f8277b.f8284g.getObjectId());
                    context.startActivity(intent);
                }
            });
            context.getSharedPreferences(this.f8284g.getObjectId(), 0).registerOnSharedPreferenceChangeListener(this);
        }
    }

    /* renamed from: a */
    public void m9597a() {
        this.f8284g = AVUser.getCurrentUser();
        if (this.f8284g != null) {
            if (TextUtils.isEmpty(this.f8284g.getAvatar())) {
                Picasso.with(this.f8283f).load(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(this.f8283f).load(this.f8284g.getAvatar()).fit().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).centerCrop().into(this.f8278a);
            }
            if (!TextUtils.isEmpty(this.f8284g.getDisplayName())) {
                this.f8280c.setText(this.f8284g.getDisplayName());
            }
            if (TextUtils.isEmpty(this.f8284g.getClubName())) {
                this.f8279b.setVisibility(8);
                return;
            }
            this.f8279b.setVisibility(0);
            this.f8282e.setText(this.f8284g.getClubName());
            this.f8281d.setText("LV" + this.f8284g.getClubLevel());
        }
    }

    /* renamed from: b */
    public void m9598b() {
        m9597a();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals("com.beastbikes.android.home.update_userinfo")) {
            m9597a();
        } else if (str.equals("beast.club.status")) {
            switch (sharedPreferences.getInt(str, 0)) {
                case 0:
                case 1:
                case 4:
                    m9597a();
                    return;
                default:
                    return;
            }
        }
    }
}
