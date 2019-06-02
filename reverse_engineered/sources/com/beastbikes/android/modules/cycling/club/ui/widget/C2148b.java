package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.ClubActivitiesListActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubMoreActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubMsgActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubRankActivity;
import com.beastbikes.android.modules.cycling.club.ui.MemberRankingActivity;
import com.beastbikes.android.modules.social.im.ui.conversation.GroupSettingActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.ui.android.utils.Toasts;
import io.rong.imkit.RongIM;

/* compiled from: ClubFeedNav */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.b */
public class C2148b extends LinearLayout implements OnSharedPreferenceChangeListener, OnClickListener, C1371a {
    /* renamed from: a */
    private ClubInfoCompact f10073a;
    /* renamed from: b */
    private View f10074b;
    /* renamed from: c */
    private boolean f10075c = false;
    /* renamed from: d */
    private Context f10076d;
    /* renamed from: e */
    private SharedPreferences f10077e;
    /* renamed from: f */
    private TextView f10078f;
    /* renamed from: g */
    private TextView f10079g;
    /* renamed from: h */
    private TextView f10080h;
    /* renamed from: i */
    private TextView f10081i;

    public C2148b(Context context, boolean z) {
        super(context);
        this.f10076d = context;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f10077e = context.getSharedPreferences(currentUser.getObjectId(), 0);
            this.f10075c = z;
            LayoutInflater.from(context).inflate(C1373R.layout.clubfeed_nav, this);
            m11043b();
            this.f10077e = context.getSharedPreferences(currentUser.getObjectId(), 0);
            this.f10077e.registerOnSharedPreferenceChangeListener(this);
        }
    }

    public void setClubInfoCompact(ClubInfoCompact clubInfoCompact) {
        this.f10073a = clubInfoCompact;
    }

    /* renamed from: b */
    private void m11043b() {
        this.f10080h = (TextView) findViewById(C1373R.id.tv_new_msg);
        this.f10074b = findViewById(C1373R.id.tv_tip);
        this.f10074b.setOnClickListener(this);
        this.f10080h.setOnClickListener(this);
        this.f10081i = (TextView) findViewById(C1373R.id.dot_chat);
        findViewById(C1373R.id.nav_group_chat).setOnClickListener(this);
        findViewById(C1373R.id.nav_activity).setOnClickListener(this);
        findViewById(C1373R.id.nav_member).setOnClickListener(this);
        findViewById(C1373R.id.nav_scroe).setOnClickListener(this);
        findViewById(C1373R.id.nav_more).setOnClickListener(this);
        this.f10078f = (TextView) findViewById(C1373R.id.dot_activity_item_dot);
        this.f10079g = (TextView) findViewById(C1373R.id.dot_more_item_dot);
        m11044a();
    }

    public void onClick(View view) {
        if (this.f10073a != null) {
            Intent intent;
            switch (view.getId()) {
                case C1373R.id.nav_member:
                    C2580w.m12905a(this.f10076d, "俱乐部成员", null);
                    intent = new Intent(getContext(), MemberRankingActivity.class);
                    intent.putExtra("club_id", this.f10073a.getObjectId());
                    getContext().startActivity(intent);
                    return;
                case C1373R.id.nav_group_chat:
                    C2580w.m12905a(this.f10076d, "俱乐群聊", "click_cube_messenger_send");
                    if (this.f10075c) {
                        GroupSettingActivity.f6169a = this.f10073a.getName();
                        RongIM.getInstance().startGroupChat(getContext(), this.f10073a.getObjectId(), this.f10073a.getName());
                        return;
                    }
                    Toasts.show(this.f10076d, getResources().getString(C1373R.string.cannotlookbeforejoin));
                    return;
                case C1373R.id.nav_activity:
                    C2580w.m12905a(this.f10076d, "俱乐部活动", null);
                    intent = new Intent(getContext(), ClubActivitiesListActivity.class);
                    intent.putExtra("club_id", this.f10073a.getObjectId());
                    intent.putExtra("isclub", this.f10075c);
                    this.f10077e.edit().putInt("beast.club.dot.activity", 0).apply();
                    getContext().startActivity(intent);
                    return;
                case C1373R.id.nav_scroe:
                    C2580w.m12905a(this.f10076d, "俱乐部积分排行", null);
                    intent = new Intent(getContext(), ClubRankActivity.class);
                    intent.putExtra("club_id", this.f10073a.getObjectId());
                    intent.putExtra("club_info", this.f10073a);
                    getContext().startActivity(intent);
                    return;
                case C1373R.id.nav_more:
                    C2580w.m12905a(this.f10076d, "俱乐更多", null);
                    if (this.f10075c) {
                        Intent intent2 = new Intent(getContext(), ClubMoreActivity.class);
                        intent2.putExtra("club_info", this.f10073a);
                        intent2.putExtra("club_id", this.f10073a.getObjectId());
                        if (getContext() instanceof Activity) {
                            ((Activity) getContext()).startActivityForResult(intent2, 2);
                            return;
                        }
                        return;
                    }
                    Toasts.show(this.f10076d, getResources().getString(C1373R.string.cannotlookbeforejoin));
                    return;
                case C1373R.id.tv_tip:
                    return;
                case C1373R.id.tv_new_msg:
                    C2580w.m12905a(this.f10076d, "俱乐Feed消息", null);
                    intent = new Intent(getContext(), ClubMsgActivity.class);
                    intent.putExtra("is_my_club", this.f10075c);
                    this.f10077e.edit().putInt("beast.club.feed.dot.total.count", 0).apply();
                    getContext().startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.contains("beast.club.dot.activity") || str.contains("beast.club.dot.more") || str.contains("beast.club.feed.dot.total.count") || str.equals("beast.club.feed.error.warning")) {
            m11044a();
        }
    }

    /* renamed from: a */
    public void m11044a() {
        if (this.f10077e != null && this.f10075c) {
            int i = this.f10077e.getInt("beast.club.feed.dot.total.count", 0);
            int i2 = this.f10077e.getInt("beast.club.dot.more", 0);
            int i3 = this.f10077e.getInt("beast.club.dot.activity", 0);
            boolean z = this.f10077e.getBoolean("beast.club.feed.error.warning", false);
            if (i > 0) {
                this.f10080h.setVisibility(0);
                this.f10080h.setText(i + getContext().getString(C1373R.string.clubfeed_new_msg));
            } else {
                this.f10080h.setVisibility(8);
            }
            if (z) {
                this.f10074b.setVisibility(0);
            } else {
                this.f10074b.setVisibility(8);
            }
            m11042a(this.f10079g, i2);
            m11042a(this.f10078f, i3);
        }
    }

    /* renamed from: a */
    private void m11042a(TextView textView, int i) {
        if (i > 0) {
            textView.setVisibility(0);
            textView.setText(i >= 99 ? "99+" : i + "");
            return;
        }
        textView.setVisibility(8);
    }
}
