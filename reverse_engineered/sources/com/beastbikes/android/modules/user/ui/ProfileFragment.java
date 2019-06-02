package com.beastbikes.android.modules.user.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.ui.ChooseDeviceActivity;
import com.beastbikes.android.ble.ui.SpeedXDevicesActivity;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.achievement.ui.AchievementSelfActivity;
import com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity;
import com.beastbikes.android.modules.cycling.sections.ui.FavorSegmentActivity;
import com.beastbikes.android.modules.cycling.stage.ui.StageAchievementDescActivity;
import com.beastbikes.android.modules.message.p071a.C2310a;
import com.beastbikes.android.modules.preferences.ui.UserSettingActivity;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.p077a.C2389c.C2388a;
import com.beastbikes.android.modules.user.ui.binding.widget.C2513a;
import com.beastbikes.android.modules.user.widget.C2535a;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.google.android.gms.common.Scopes;
import com.squareup.picasso.Picasso;
import com.umeng.onlineconfig.OnlineConfigAgent;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903561)
@C1460c(a = 2131820570)
@C1457a(a = "个人详情页")
public class ProfileFragment extends SessionFragment implements OnSharedPreferenceChangeListener, OnClickListener, C1371a, C2388a {
    /* renamed from: a */
    public static final Logger f6518a = LoggerFactory.getLogger(ProfileFragment.class);
    @C1458a(a = 2131757281)
    /* renamed from: A */
    private ViewGroup f6519A;
    /* renamed from: B */
    private TextView f6520B;
    @C1458a(a = 2131757284)
    /* renamed from: C */
    private ViewGroup f6521C;
    /* renamed from: D */
    private TextView f6522D;
    @C1458a(a = 2131757273)
    /* renamed from: E */
    private LinearLayout f6523E;
    @C1458a(a = 2131757277)
    /* renamed from: F */
    private View f6524F;
    @C1458a(a = 2131757285)
    /* renamed from: G */
    private ViewGroup f6525G;
    @C1458a(a = 2131757287)
    /* renamed from: H */
    private ViewGroup f6526H;
    @C1458a(a = 2131756659)
    /* renamed from: I */
    private ViewGroup f6527I;
    @C1458a(a = 2131757283)
    /* renamed from: J */
    private ViewGroup f6528J;
    /* renamed from: K */
    private TextView f6529K;
    /* renamed from: L */
    private C2513a f6530L;
    @C1458a(a = 2131757274)
    /* renamed from: M */
    private TextView f6531M;
    @C1458a(a = 2131757286)
    /* renamed from: N */
    private ViewGroup f6532N;
    @C1458a(a = 2131757276)
    /* renamed from: O */
    private ViewGroup f6533O;
    /* renamed from: P */
    private TextView f6534P;
    /* renamed from: Q */
    private C2535a f6535Q;
    /* renamed from: R */
    private C2389c f6536R;
    /* renamed from: S */
    private C2310a f6537S;
    /* renamed from: T */
    private C2341a f6538T;
    /* renamed from: U */
    private C1802i f6539U;
    /* renamed from: V */
    private SharedPreferences f6540V;
    /* renamed from: W */
    private BeastBikes f6541W;
    /* renamed from: X */
    private ProfileDTO f6542X;
    /* renamed from: Y */
    private boolean f6543Y = true;
    /* renamed from: Z */
    private boolean f6544Z;
    private int aa;
    private Intent ab;
    private boolean ac = false;
    private Menu ad;
    private boolean ae;
    private String af;
    private UserDetailDTO ag;
    @C1458a(a = 2131757289)
    /* renamed from: b */
    private CircleImageView f6545b;
    @C1458a(a = 2131757290)
    /* renamed from: c */
    private ImageView f6546c;
    @C1458a(a = 2131757291)
    /* renamed from: d */
    private TextView f6547d;
    @C1458a(a = 2131757292)
    /* renamed from: e */
    private TextView f6548e;
    @C1458a(a = 2131757293)
    /* renamed from: f */
    private TextView f6549f;
    @C1458a(a = 2131757294)
    /* renamed from: g */
    private TextView f6550g;
    @C1458a(a = 2131757295)
    /* renamed from: h */
    private FrameLayout f6551h;
    @C1458a(a = 2131757296)
    /* renamed from: i */
    private ImageView f6552i;
    @C1458a(a = 2131757297)
    /* renamed from: j */
    private TextView f6553j;
    @C1458a(a = 2131757279)
    /* renamed from: k */
    private ViewGroup f6554k;
    /* renamed from: l */
    private TextView f6555l;
    /* renamed from: m */
    private TextView f6556m;
    @C1458a(a = 2131757278)
    /* renamed from: n */
    private ViewGroup f6557n;
    /* renamed from: o */
    private TextView f6558o;
    @C1458a(a = 2131757280)
    /* renamed from: p */
    private ViewGroup f6559p;
    /* renamed from: q */
    private TextView f6560q;
    @C1458a(a = 2131757307)
    /* renamed from: r */
    private ViewGroup f6561r;
    @C1458a(a = 2131757308)
    /* renamed from: s */
    private TextView f6562s;
    @C1458a(a = 2131757313)
    /* renamed from: t */
    private ViewGroup f6563t;
    @C1458a(a = 2131757314)
    /* renamed from: u */
    private TextView f6564u;
    @C1458a(a = 2131757309)
    /* renamed from: v */
    private ViewGroup f6565v;
    @C1458a(a = 2131757310)
    /* renamed from: w */
    private TextView f6566w;
    @C1458a(a = 2131757316)
    /* renamed from: x */
    private ViewGroup f6567x;
    @C1458a(a = 2131757317)
    /* renamed from: y */
    private TextView f6568y;
    @C1458a(a = 2131757312)
    /* renamed from: z */
    private TextView f6569z;

    public void onAttach(Activity activity) {
        this.f6536R = new C2389c(activity);
        this.f6537S = new C2310a(activity);
        this.f6538T = new C2341a(activity);
        super.onAttach(activity);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity().getLocalClassName().contains("HomeActivity")) {
            this.ae = true;
            setHasOptionsMenu(true);
        }
        f6518a.info("open_medal", OnlineConfigAgent.getInstance().getConfigParams(getActivity(), "discovery_activity_banner_target_url"));
        this.f6545b.setOnClickListener(this);
        this.f6545b.setFocusable(true);
        this.f6545b.setFocusableInTouchMode(true);
        this.f6545b.requestFocus();
        this.f6523E.setOnClickListener(this);
        this.f6555l = (TextView) this.f6554k.findViewById(C1373R.id.profile_fragment_statistic_item_name);
        if (C1849a.b(getActivity())) {
            this.f6555l.setText(getResources().getString(C1373R.string.activity_param_label_distance) + "(KM)");
        } else {
            this.f6555l.setText(getResources().getString(C1373R.string.activity_param_label_distance) + "(MI)");
        }
        this.f6556m = (TextView) this.f6554k.findViewById(C1373R.id.profile_fragment_statistic_item_value);
        this.f6556m.setText(C1373R.string.activity_param_distance_default_value);
        ((TextView) this.f6557n.findViewById(C1373R.id.profile_fragment_statistic_item_name)).setText(C1373R.string.str_time_with_unit);
        this.f6558o = (TextView) this.f6557n.findViewById(C1373R.id.profile_fragment_statistic_item_value);
        this.f6558o.setText(C1373R.string.activity_param_elapsed_time_default_short_value);
        this.f6560q = (TextView) this.f6559p.findViewById(C1373R.id.profile_fragment_statistic_item_value);
        ((TextView) this.f6559p.findViewById(C1373R.id.profile_fragment_statistic_item_name)).setText(C1373R.string.activity_param_label_calorie);
        this.f6560q.setText(C1373R.string.activity_param_distance_default_value);
        this.f6520B = (TextView) this.f6519A.findViewById(C1373R.id.profile_fragment_statistic_item_value);
        ((TextView) this.f6519A.findViewById(C1373R.id.profile_fragment_statistic_item_name)).setText(C1373R.string.activity_param_label_times);
        this.f6520B.setText(C1373R.string.activity_param_distance_default_value);
        this.f6521C.setOnClickListener(this);
        ((ImageView) this.f6521C.findViewById(C1373R.id.profile_fragment_detail_item_icon)).setImageResource(C1373R.drawable.ic_profile_activities);
        ((TextView) this.f6521C.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.str_cycling_history);
        this.f6522D = (TextView) this.f6521C.findViewById(C1373R.id.profile_fragment_detail_item_desc);
        this.f6524F.setOnClickListener(this);
        this.f6527I.setOnClickListener(this);
        ((ImageView) this.f6527I.findViewById(C1373R.id.profile_fragment_detail_item_icon)).setImageResource(C1373R.drawable.ic_profile_grid_icon);
        ((TextView) this.f6527I.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.str_grid_explore);
        ((TextView) this.f6527I.findViewById(C1373R.id.profile_fragment_detail_item_desc)).setVisibility(8);
        ((TextView) this.f6527I.findViewById(C1373R.id.profile_fragment_detail_item_dot)).setVisibility(8);
        this.f6527I.setVisibility(8);
        this.f6528J.setOnClickListener(this);
        ((ImageView) this.f6528J.findViewById(C1373R.id.profile_fragment_detail_item_icon)).setImageResource(C1373R.drawable.ic_profile_club);
        ((TextView) this.f6528J.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.profile_fragment_club_title);
        this.f6529K = (TextView) this.f6528J.findViewById(C1373R.id.profile_fragment_detail_item_desc_left);
        this.f6528J.findViewById(C1373R.id.profile_fragment_detail_item_desc).setVisibility(8);
        this.f6529K.setText(C1373R.string.did_not_join_club);
        this.f6529K.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        ((TextView) this.f6528J.findViewById(C1373R.id.profile_fragment_detail_item_dot)).setVisibility(8);
        this.f6532N.setOnClickListener(this);
        ImageView imageView = (ImageView) this.f6532N.findViewById(C1373R.id.profile_fragment_detail_item_icon);
        TextView textView = (TextView) this.f6532N.findViewById(C1373R.id.profile_fragment_detail_item_desc);
        ((TextView) this.f6532N.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.profile_fragment_detail_item_speed_force_title);
        imageView.setImageResource(C1373R.drawable.ic_speed_force_icon);
        textView.setVisibility(8);
        this.f6526H.setOnClickListener(this);
        ((ImageView) this.f6526H.findViewById(C1373R.id.profile_fragment_detail_item_icon)).setImageResource(C1373R.drawable.ic_nav_route_plan);
        ((TextView) this.f6526H.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.profile_fragment_detailed_item_my_route);
        ((TextView) this.f6526H.findViewById(C1373R.id.profile_fragment_detail_item_desc)).setVisibility(8);
        this.f6525G.setOnClickListener(this);
        this.f6525G.setVisibility(8);
        ((ImageView) this.f6525G.findViewById(C1373R.id.profile_fragment_detail_item_icon)).setImageResource(C1373R.drawable.ic_profile_collect_icon);
        ((TextView) this.f6525G.findViewById(C1373R.id.profile_fragment_detail_item_subject)).setText(C1373R.string.str_segment_collection);
        ((TextView) this.f6525G.findViewById(C1373R.id.profile_fragment_detail_item_desc)).setVisibility(8);
        this.f6563t.setOnClickListener(this);
        this.f6567x.setOnClickListener(this);
        this.f6561r.setOnClickListener(this);
        this.f6565v.setOnClickListener(this);
        this.f6551h.setOnClickListener(this);
        this.ab = getActivity().getIntent();
        if (this.ab != null) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                this.f6544Z = currentUser.getObjectId().equals(b());
                this.f6541W = (BeastBikes) getActivity().getApplication();
                if (this.f6544Z) {
                    this.f6547d.setText(currentUser.getDisplayName());
                    if (C1849a.a()) {
                        this.f6526H.setVisibility(0);
                    } else {
                        this.f6526H.setVisibility(8);
                    }
                } else {
                    this.f6526H.setVisibility(8);
                }
                this.f6540V = getActivity().getSharedPreferences(currentUser.getObjectId(), 0);
                this.f6540V.registerOnSharedPreferenceChangeListener(this);
                if (VERSION.SDK_INT < 19 || !this.f6544Z) {
                    this.f6532N.setVisibility(8);
                }
                this.f6535Q = new C2535a(getActivity());
                this.f6533O.addView(this.f6535Q);
                m7739a(b(), (C2388a) this);
                m7760h();
                m7746b(b());
                if (this.f6544Z) {
                    m7751d();
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (this.f6544Z) {
                if (this.f6541W.m5258f()) {
                    this.f6527I.setVisibility(0);
                } else {
                    this.f6527I.setVisibility(8);
                }
                CharSequence title = getActivity().getTitle();
                if (!TextUtils.isEmpty(title) && title.equals(currentUser.getDisplayName())) {
                    this.f6547d.setText(currentUser.getDisplayName());
                }
                if (!TextUtils.isEmpty(currentUser.getAvatar())) {
                    Picasso.with(getActivity()).load(currentUser.getAvatar()).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f6545b);
                }
            } else {
                this.ac = true;
                this.af = this.ab.getStringExtra("nick_name");
                m7740a(this.af, this.ab.getStringExtra("remarks"));
                String stringExtra = this.ab.getStringExtra("avatar");
                if (!TextUtils.isEmpty(stringExtra)) {
                    Picasso.with(getActivity()).load(stringExtra).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f6545b);
                }
                CharSequence stringExtra2 = this.ab.getStringExtra("city");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    this.f6549f.setText(stringExtra2);
                }
            }
            m7753e();
            if (this.f6544Z) {
                m7735a();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f6540V != null) {
            this.f6540V.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        this.ad = menu;
        if (this.ac) {
            super.onCreateOptionsMenu(menu, menuInflater);
        } else if (this.ae) {
            menuInflater.inflate(C1373R.menu.setting_activity, menu);
            MenuItem item = menu.getItem(0);
            View inflate = LayoutInflater.from(getActivity()).inflate(C1373R.layout.profile_fragment_message_menu, null);
            ImageView imageView = (ImageView) inflate.findViewById(C1373R.id.profile_fragment_menu_message_icon);
            this.f6534P = (TextView) inflate.findViewById(C1373R.id.profile_fragment_menu_message_count);
            imageView.setOnClickListener(this);
            m7735a();
            item.setActionView(inflate);
        }
    }

    /* renamed from: a */
    private void m7741a(boolean z) {
        if (this.ac && this.ad != null) {
            MenuItem item = this.ad.getItem(1);
            if (item != null) {
                item.setVisible(z);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.activity_friends_update_remarks:
                this.f6530L = new C2513a(getActivity(), null, getString(C1373R.string.friends_add_friendremarks_hint), new ProfileFragment$b(this));
                this.f6530L.setCancelable(false);
                this.f6530L.show();
                break;
            case C1373R.id.activity_friends_delete_friend:
                m7755f();
                break;
            case C1373R.id.activity_setting_action_button_setting:
                Intent intent = new Intent(getActivity(), UserSettingActivity.class);
                intent.putExtra("from_setting", true);
                startActivityForResult(intent, 2);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        Context activity = getActivity();
        Intent intent;
        String str;
        switch (view.getId()) {
            case C1373R.id.profile_fragment_detail_item_grid:
                if (this.f6542X != null) {
                    intent = new Intent(getActivity(), GridExploreActivity.class);
                    intent.putExtra("user_id", b());
                    intent.putExtra(Scopes.PROFILE, this.f6542X);
                    startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.profile_fragment_detail_item_statistics:
                intent = new Intent(activity, PersonalRecordActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.profile_fragment_detail_item_club:
                if (this.f6542X != null && !TextUtils.isEmpty(this.f6542X.getClubId())) {
                    C2562j.a(getContext(), this.f6542X.getClubId());
                    return;
                }
                return;
            case C1373R.id.profile_fragment_detail_item_activities:
                Intent intent2 = new Intent(activity, CyclingRecordActivity.class);
                intent2.putExtra("user_id", b());
                String str2 = "";
                str = "";
                if (this.f6542X != null) {
                    str2 = this.f6542X.getAvatar();
                    str = this.f6542X.getNickname();
                } else if (this.ab != null) {
                    str = this.ab.getStringExtra("nick_name");
                    str2 = this.ab.getStringExtra("avatar");
                }
                if (TextUtils.isEmpty(str) && TextUtils.isEmpty(r1) && AVUser.getCurrentUser() != null) {
                    str = AVUser.getCurrentUser().getDisplayName();
                    str2 = AVUser.getCurrentUser().getAvatar();
                }
                intent2.putExtra("avatar_url", str2);
                intent2.putExtra("nick_name", str);
                intent2.putExtra("refresh", this.f6543Y);
                startActivityForResult(intent2, 77);
                if (this.f6544Z) {
                    C2580w.a(activity, "查看我的骑行纪录列表", "click_my_page_ridding_history");
                    return;
                } else {
                    C2580w.a(activity, "查看别人的骑行纪录列表", null);
                    return;
                }
            case C1373R.id.profile_fragment_detail_item_route_favorites:
                intent = new Intent(getContext(), FavorSegmentActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.profile_fragment_detail_item_speedforce:
                C2580w.a(getActivity(), "", "click_my_page_my_device");
                m7764j();
                return;
            case C1373R.id.profile_fragment_detail_item_route_book:
                C2580w.a(getActivity(), "", "click_my_page_my_road_book");
                startActivity(new Intent(getContext(), RouteSelfActivity.class));
                return;
            case C1373R.id.profile_fragment_avatar:
                if (this.f6542X != null) {
                    intent = new Intent(activity, AvatarViewer.class);
                    intent.putExtra("user_id", this.f6542X.getUserId());
                    intent.putExtra("user_avatar_url", this.f6542X.getAvatar());
                    startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.profile_fragment_friend_btn:
                if (this.f6542X != null) {
                    str = this.f6542X.getNickname();
                    if (!TextUtils.isEmpty(this.f6542X.getRemarks())) {
                        str = this.f6542X.getRemarks();
                    }
                    if (this.f6544Z) {
                        intent = new Intent(getActivity(), UserSettingActivity.class);
                        intent.putExtra("from_setting", true);
                        startActivityForResult(intent, 2);
                        return;
                    } else if (this.aa == 0 || 1 == this.aa) {
                        if (isAdded()) {
                            m7758g();
                            return;
                        }
                        return;
                    } else if ((3 == this.aa || 2 == this.aa) && RongIM.getInstance() != null && this.f6542X != null) {
                        C2580w.a(getActivity(), "", "click_profile_messenger");
                        RongIM.getInstance().startPrivateChat(getActivity(), this.f6542X.getUserId(), str);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case C1373R.id.profile_fragment_menu_message_icon:
                if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                    try {
                        RongIM.getInstance().startConversationList(getActivity(), null);
                        return;
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            case C1373R.id.profile_fragment_follower_view:
                intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.profile_fragment_fans_view:
                this.f6540V.edit().putInt("beast.follow.dot", 0).apply();
                intent = new Intent(getActivity(), FansActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.profile_fragment_medal_view:
                intent = new Intent(getActivity(), MedalsActivity.class);
                if (this.f6542X != null) {
                    intent.putExtra("user_id", b());
                    intent.putExtra("medal_count", this.f6542X.getMedalNum());
                }
                startActivity(intent);
                C2580w.a(getActivity(), "", "click_medal");
                return;
            case C1373R.id.profile_fragment_achievement_view:
                if (Integer.parseInt(this.f6568y.getText().toString()) <= 0) {
                    intent = new Intent(getActivity(), StageAchievementDescActivity.class);
                } else {
                    intent = new Intent(getActivity(), AchievementSelfActivity.class);
                    intent.putExtra("user_id", b());
                }
                getActivity().startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 77:
                switch (i2) {
                    case -1:
                        this.f6543Y = intent.getExtras().getBoolean("refresh");
                        f6518a.trace("activity record result");
                        return;
                    default:
                        return;
                }
            case 78:
                m7766k();
                return;
            default:
                return;
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        int i = -1;
        switch (str.hashCode()) {
            case -1889373225:
                if (str.equals("beast.follow.notify")) {
                    i = 3;
                    break;
                }
                break;
            case -23375699:
                if (str.equals("com.beastbikes.android.home.update_userinfo")) {
                    i = 4;
                    break;
                }
                break;
            case 1275110267:
                if (str.equals("beast.follow.dot")) {
                    i = 2;
                    break;
                }
                break;
            case 1320565787:
                if (str.equals("beast.club.status")) {
                    i = 5;
                    break;
                }
                break;
            case 1377473785:
                if (str.equals("beast.friend.new.message.count")) {
                    i = 0;
                    break;
                }
                break;
            case 1996732828:
                if (str.equals("beast.rongcloud.new.message.count")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
                m7735a();
                return;
            case 3:
                m7739a(b(), (C2388a) this);
                return;
            case 4:
                m7738a(b());
                return;
            case 5:
                if (this.f6542X != null) {
                    i = sharedPreferences.getInt(str, 0);
                    if (i == 5 || i == 0) {
                        this.f6542X.setClubId(null);
                        this.f6542X.setClubName(null);
                        m7743b(this.f6542X);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7735a() {
        if (this.f6540V != null && this.f6534P != null) {
            int i = this.f6540V.getInt("beast.rongcloud.new.message.count", 0);
            int i2 = this.f6540V.getInt("beast.friend.new.message.count", 0);
            int i3 = this.f6540V.getInt("beast.follow.dot", 0);
            i += i2;
            if (i > 0) {
                this.f6534P.setVisibility(0);
            } else {
                this.f6534P.setVisibility(8);
            }
            if (i > 99) {
                this.f6534P.setText("99+");
            } else {
                this.f6534P.setText(String.valueOf(i));
            }
            if (i3 > 0) {
                this.f6569z.setVisibility(0);
            } else {
                this.f6569z.setVisibility(8);
            }
            if (i3 > 99) {
                this.f6569z.setText("99+");
            } else {
                this.f6569z.setText(String.valueOf(i3));
            }
        }
    }

    /* renamed from: c */
    private void m7747c() {
        this.f6551h.setVisibility(0);
        if (this.f6544Z) {
            this.f6553j.setText(C1373R.string.profile_fragment_edit_user_info);
            return;
        }
        switch (this.aa) {
            case 0:
            case 1:
                this.f6553j.setText(C1373R.string.friends_add_friend);
                this.f6553j.setTextColor(Color.parseColor("#ffffff"));
                this.f6552i.setImageResource(C1373R.drawable.ic_add_friend_icon);
                this.f6552i.setVisibility(0);
                m7741a(false);
                return;
            case 2:
            case 3:
                this.f6553j.setText(C1373R.string.friends_send_message);
                this.f6553j.setTextColor(Color.parseColor("#ffffff"));
                this.f6552i.setImageResource(C1373R.drawable.ic_message);
                this.f6552i.setVisibility(0);
                m7741a(true);
                return;
            default:
                return;
        }
    }

    /* renamed from: d */
    private void m7751d() {
        if (this.f6540V != null) {
            long j = this.f6540V.getLong("message.lastdate", 0);
            getAsyncTaskQueue().a(new ProfileFragment$1(this), new Long[]{Long.valueOf(j)});
        }
    }

    /* renamed from: a */
    private void m7739a(String str, C2388a c2388a) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ProfileFragment$4(this, c2388a), new String[]{str});
        }
    }

    /* renamed from: a */
    private void m7738a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ProfileFragment$5(this), new String[]{str});
        }
    }

    /* renamed from: b */
    private void m7743b(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            String avatar = profileDTO.getAvatar();
            if (TextUtils.isEmpty(avatar)) {
                this.f6545b.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(getActivity()).load(avatar).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f6545b);
            }
            if (1 == profileDTO.getGender()) {
                this.f6546c.setImageResource(C1373R.drawable.ic_gender_male);
            } else {
                this.f6546c.setImageResource(C1373R.drawable.ic_gender_female);
            }
            CharSequence location = profileDTO.getLocation();
            if (!TextUtils.isEmpty(location)) {
                this.f6549f.setText(location);
            }
            this.f6566w.setText(String.valueOf(profileDTO.getFansNum()));
            this.f6562s.setText(String.valueOf(profileDTO.getFollowNum()));
            this.f6564u.setText(String.valueOf(profileDTO.getMedalNum()));
            this.f6568y.setText(String.valueOf(profileDTO.getAchievementNum()));
            this.af = profileDTO.getNickname();
            m7740a(profileDTO.getNickname(), profileDTO.getRemarks());
            if (this.f6541W.m5258f()) {
                if (profileDTO.getSameNum() <= 0) {
                    this.f6531M.setVisibility(8);
                } else {
                    this.f6531M.setVisibility(0);
                    if (isAdded()) {
                        this.f6531M.setText(String.format(getResources().getString(C1373R.string.profile_fragment_same_grid_count), new Object[]{Integer.valueOf(profileDTO.getSameNum())}));
                    }
                }
            }
            this.f6550g.setText("ID:" + profileDTO.getSpeedxId());
            if (TextUtils.isEmpty(profileDTO.getClubId())) {
                this.f6529K.setText(C1373R.string.str_not_join_club);
                this.f6529K.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else {
                this.f6529K.setText(profileDTO.getClubName());
                this.f6529K.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1373R.drawable.ic_arrow_right_icon, 0);
            }
            this.aa = profileDTO.getFollowStatu();
            m7747c();
        }
    }

    /* renamed from: b */
    private void m7746b(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ProfileFragment$6(this), new String[]{str});
        }
    }

    /* renamed from: e */
    private void m7753e() {
        if (this.ag != null) {
            double totalDistance = this.ag.getTotalDistance();
            boolean b = C1849a.b(getActivity());
            if (b) {
                this.f6555l.setText(getString(C1373R.string.activity_param_label_distance) + "(KM)");
            } else {
                this.f6555l.setText(getString(C1373R.string.activity_param_label_distance) + "(MI)");
            }
            if (totalDistance <= 0.0d) {
                this.f6556m.setText("0.00");
            } else if (b) {
                this.f6556m.setText(String.format("%.2f", new Object[]{Double.valueOf(totalDistance / 1000.0d)}));
            } else {
                this.f6556m.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.a(totalDistance / 1000.0d))}));
            }
            long totalElapsedTime = this.ag.getTotalElapsedTime();
            if (totalElapsedTime <= 0) {
                this.f6558o.setText("00:00");
            } else {
                long j = totalElapsedTime / 3600;
                long j2 = (totalElapsedTime % 3600) / 60;
                this.f6558o.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
                if (this.f6542X != null) {
                    this.f6542X.setTotalElapsedTime(totalElapsedTime);
                    this.f6542X.setTotalDistance(totalDistance);
                }
            }
            long latestActivityTime = this.ag.getLatestActivityTime();
            totalElapsedTime = Math.abs(System.currentTimeMillis() - latestActivityTime);
            if (latestActivityTime <= 0) {
                this.f6522D.setVisibility(8);
            } else if (totalElapsedTime < 300000) {
                this.f6522D.setText(C1373R.string.feedback_activity_just_now);
            } else {
                this.f6522D.setText(DateUtils.getRelativeTimeSpanString(latestActivityTime));
            }
            if (this.ag.getTotalCount() > 0) {
                this.f6520B.setText(String.format("%d", new Object[]{Integer.valueOf(this.ag.getTotalCount())}));
            }
            if (this.ag.getTotalCalories() > 0.0d) {
                this.f6560q.setText(String.format("%d", new Object[]{Long.valueOf(Math.round(this.ag.getTotalCalories()))}));
            }
        }
    }

    /* renamed from: a */
    private void m7740a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.f6547d.setText(str2);
            if (!TextUtils.isEmpty(str)) {
                CharSequence charSequence = "(" + str + ")";
                if (getActivity() != null) {
                    this.f6548e.setText(charSequence);
                }
            }
        } else if (!TextUtils.isEmpty(str) && getActivity() != null) {
            this.f6547d.setText(str);
        }
    }

    /* renamed from: a */
    public void m7773a(ProfileDTO profileDTO) {
        getActivity().runOnUiThread(new ProfileFragment$7(this, profileDTO));
    }

    /* renamed from: f */
    private void m7755f() {
        Object b = b();
        if (!TextUtils.isEmpty(b) && !this.f6544Z) {
            C2621c c2621c = new C2621c(getActivity());
            c2621c.b(C1373R.string.msg_unfollow_prompt_dialog);
            c2621c.b(C1373R.string.cancel, new ProfileFragment$8(this, c2621c));
            c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ProfileFragment$9(this, b, c2621c));
            c2621c.a();
        }
    }

    /* renamed from: c */
    private void m7749c(String str) {
        if (!TextUtils.isEmpty(b()) && !TextUtils.isEmpty(str)) {
            this.f6539U = new C1802i(getActivity(), null, true);
            getAsyncTaskQueue().a(new ProfileFragment$10(this, str), new String[0]);
        }
    }

    /* renamed from: g */
    private void m7758g() {
        String b = b();
        if (!TextUtils.isEmpty(b()) && !this.f6544Z) {
            getAsyncTaskQueue().a(new ProfileFragment$11(this, b), new String[0]);
        }
    }

    /* renamed from: d */
    private void m7752d(String str) {
        getAsyncTaskQueue().a(new ProfileFragment$2(this, str), new String[0]);
    }

    /* renamed from: h */
    private void m7760h() {
        getAsyncTaskQueue().a(new ProfileFragment$3(this), new String[0]);
    }

    /* renamed from: i */
    private boolean m7762i() {
        CharSequence packageName = getActivity().getPackageName();
        Object string = Secure.getString(getActivity().getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        for (String unflattenFromString : string.split(":")) {
            ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
            if (unflattenFromString2 != null && TextUtils.equals(packageName, unflattenFromString2.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: j */
    private void m7764j() {
        if (m7762i()) {
            m7766k();
        } else if (!this.f6540V.contains("beast.ble.message.on") || this.f6540V.getInt("beast.ble.message.on", 0) == 1) {
            try {
                startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 78);
            } catch (ActivityNotFoundException e) {
                m7766k();
                f6518a.error("跳转到系统设置通知使用权页面错误, " + e);
            }
        } else {
            m7766k();
        }
    }

    /* renamed from: k */
    private void m7766k() {
        List a = new C1651c(getActivity()).a();
        if (a == null || a.size() <= 0) {
            startActivity(new Intent(getActivity(), ChooseDeviceActivity.class));
        } else {
            startActivity(new Intent(getActivity(), SpeedXDevicesActivity.class));
        }
        C2580w.a(getActivity(), "查看我的中控", null);
    }
}
