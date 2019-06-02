package com.beastbikes.android.modules.cycling.club.ui.p130a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AdapterClubFeedBanner */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.a.a */
public class C2106a extends PagerAdapter implements OnSharedPreferenceChangeListener, C1371a {
    /* renamed from: a */
    private Context f9845a;
    /* renamed from: b */
    private ClubInfoCompact f9846b;
    /* renamed from: c */
    private LayoutInflater f9847c;
    /* renamed from: d */
    private Map<Integer, C2105a> f9848d = new HashMap();
    /* renamed from: e */
    private SharedPreferences f9849e;

    /* compiled from: AdapterClubFeedBanner */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.a.a$a */
    class C2105a {
        /* renamed from: a */
        final /* synthetic */ C2106a f9840a;
        /* renamed from: b */
        private TextView f9841b;
        /* renamed from: c */
        private CircleImageView f9842c;
        /* renamed from: d */
        private TextView f9843d;
        /* renamed from: e */
        private TextView f9844e;

        public C2105a(C2106a c2106a, View view, int i) {
            this.f9840a = c2106a;
            this.f9842c = (CircleImageView) view.findViewById(C1373R.id.club_info_logo);
            this.f9841b = (TextView) view.findViewById(C1373R.id.club_info_notice);
            this.f9843d = (TextView) view.findViewById(C1373R.id.club_info_desc);
            this.f9844e = (TextView) view.findViewById(C1373R.id.club_level);
            c2106a.f9848d.put(Integer.valueOf(i), this);
        }

        /* renamed from: a */
        public void m10925a(String str) {
            if (!TextUtils.isEmpty(str) && this.f9842c != null) {
                Log.e("path", str);
                Picasso.with(this.f9840a.f9845a).load(new File(str)).fit().placeholder(C1373R.drawable.ic_avatar_club).error(C1373R.drawable.ic_avatar_club).centerCrop().into(this.f9842c);
            }
        }

        /* renamed from: a */
        public void m10924a(int i) {
            if (this.f9840a.f9846b != null) {
                if (i == 0) {
                    if (TextUtils.isEmpty(this.f9840a.f9846b.getLogo())) {
                        this.f9842c.setImageResource(C1373R.drawable.ic_avatar_club);
                    } else {
                        Picasso.with(this.f9840a.f9845a).load(this.f9840a.f9846b.getLogo()).fit().placeholder(C1373R.drawable.ic_avatar_club).error(C1373R.drawable.ic_avatar_club).centerCrop().into(this.f9842c);
                        Picasso.with(this.f9840a.f9845a).invalidate(this.f9840a.f9846b.getLogo());
                    }
                    this.f9843d.setText(this.f9840a.f9846b.getDesc());
                    this.f9844e.setText("LV." + this.f9840a.f9846b.getClubLevel());
                    this.f9844e.setVisibility(0);
                    return;
                }
                this.f9841b.setText(this.f9840a.f9846b.getNotice());
            }
        }
    }

    public C2106a(ClubInfoCompact clubInfoCompact, Context context) {
        this.f9846b = clubInfoCompact;
        this.f9845a = context;
        this.f9847c = LayoutInflater.from(context);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f9849e = context.getSharedPreferences(currentUser.getObjectId(), 0);
            this.f9849e.registerOnSharedPreferenceChangeListener(this);
        }
    }

    /* renamed from: a */
    public void m10929a(ClubInfoCompact clubInfoCompact) {
        super.notifyDataSetChanged();
        this.f9846b = clubInfoCompact;
        for (int i = 0; i < this.f9848d.size(); i++) {
            ((C2105a) this.f9848d.get(Integer.valueOf(i))).m10924a(i);
        }
    }

    /* renamed from: a */
    public void m10930a(String str) {
        if (this.f9848d != null && !this.f9848d.isEmpty()) {
            ((C2105a) this.f9848d.get(Integer.valueOf(0))).m10925a(str);
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return 2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.f9847c.inflate(C1373R.layout.clubfeed_banner, viewGroup, false);
        C2105a c2105a = new C2105a(this, inflate, i);
        if (i == 0) {
            c2105a.f9843d.setVisibility(0);
            c2105a.f9842c.setVisibility(0);
            c2105a.f9844e.setVisibility(0);
            c2105a.f9841b.setVisibility(8);
        } else {
            c2105a.f9843d.setVisibility(8);
            c2105a.f9842c.setVisibility(8);
            c2105a.f9844e.setVisibility(8);
            c2105a.f9841b.setVisibility(0);
        }
        c2105a.m10924a(i);
        viewGroup.addView(inflate, 0);
        return inflate;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("beast.club.logo.change")) {
                m10930a(this.f9849e.getString("beast.club.logo.locale", ""));
            }
            if (str.equals("beast.club.logo") || str.equals("beast.club.name") || str.equals("beast.club.desc") || str.equals("beast.club.notices")) {
                Object string = this.f9849e.getString("beast.club.logo", "");
                if (!TextUtils.isEmpty(string)) {
                    this.f9846b.setLogo(string);
                }
                string = this.f9849e.getString("beast.club.name", "");
                if (!TextUtils.isEmpty(string)) {
                    this.f9846b.setName(string);
                }
                string = this.f9849e.getString("beast.club.desc", "");
                if (!TextUtils.isEmpty(string)) {
                    this.f9846b.setDesc(string);
                }
                string = this.f9849e.getString("beast.club.notices", "");
                if (!TextUtils.isEmpty(string)) {
                    this.f9846b.setNotice(string);
                }
                m10929a(this.f9846b);
            }
        }
    }
}
