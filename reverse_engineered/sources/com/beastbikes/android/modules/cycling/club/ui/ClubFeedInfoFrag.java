package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1800h;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.biz.C2052c.C2050a;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.cycling.club.biz.C2057d.C2054a;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2129e;
import com.beastbikes.android.modules.cycling.club.ui.p130a.C2109b;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.widget.C2535a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout.C2602a;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.StickyListHeadersListView;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.StickyListHeadersListView$d;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.StickyListHeadersListView$e;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903271)
public class ClubFeedInfoFrag extends SessionFragment implements OnSharedPreferenceChangeListener, OnRefreshListener, OnClickListener, OnTouchListener, C1371a, C2050a, C2054a, C2602a, StickyListHeadersListView$d, StickyListHeadersListView$e {
    /* renamed from: a */
    public boolean f5039a = false;
    @C1458a(a = 2131756365)
    /* renamed from: b */
    private SwipeRefreshAndLoadLayout f5040b;
    @C1458a(a = 2131756366)
    /* renamed from: c */
    private StickyListHeadersListView f5041c;
    @C1458a(a = 2131756367)
    /* renamed from: d */
    private FrameLayout f5042d;
    @C1458a(a = 2131756370)
    /* renamed from: e */
    private RelativeLayout f5043e;
    @C1458a(a = 2131756371)
    /* renamed from: f */
    private TextView f5044f;
    @C1458a(a = 2131756368)
    /* renamed from: g */
    private LinearLayout f5045g;
    @C1458a(a = 2131756369)
    /* renamed from: h */
    private TextView f5046h;
    /* renamed from: i */
    private C2535a f5047i;
    /* renamed from: j */
    private C2109b f5048j;
    /* renamed from: k */
    private boolean f5049k = true;
    /* renamed from: l */
    private C2129e f5050l;
    /* renamed from: m */
    private ClubInfoCompact f5051m;
    /* renamed from: n */
    private ClubInfoCompact f5052n;
    /* renamed from: o */
    private ClubManager f5053o;
    /* renamed from: p */
    private C2052c f5054p;
    /* renamed from: q */
    private String f5055q;
    /* renamed from: r */
    private C2152d f5056r;
    /* renamed from: s */
    private long f5057s = 0;
    /* renamed from: t */
    private C1800h f5058t;
    /* renamed from: u */
    private boolean f5059u;
    /* renamed from: v */
    private boolean f5060v = false;
    /* renamed from: w */
    private boolean f5061w;
    /* renamed from: x */
    private SharedPreferences f5062x;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        C2580w.a(getActivity(), "查看某个未加入的俱乐部主页", "click_club");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f5047i = new C2535a(getActivity());
            this.f5045g.addView(this.f5047i);
            this.f5047i.setTitle(C1373R.string.label_every_month_distance);
            this.f5053o = new ClubManager(getActivity());
            this.f5054p = new C2052c(getActivity());
            if (arguments.getSerializable("club_info") != null) {
                this.f5051m = (ClubInfoCompact) arguments.getSerializable("club_info");
                this.f5059u = this.f5051m.getIsPrivate();
            }
            this.f5055q = arguments.getString("club_id");
            this.f5061w = arguments.getBoolean("is_statusChanged", false);
            if (!TextUtils.isEmpty(this.f5055q)) {
                this.f5052n = m6376c();
                if (this.f5052n != null) {
                    this.f5060v = this.f5052n.getLevel() == 128;
                    this.f5039a = this.f5055q.equals(this.f5052n.getObjectId());
                    if (this.f5039a) {
                        this.f5051m = this.f5052n;
                    }
                }
                FrameLayout frameLayout = this.f5042d;
                View c2152d = new C2152d(getActivity());
                this.f5056r = c2152d;
                frameLayout.addView(c2152d);
                this.f5050l = new C2129e(getActivity());
                this.f5050l.a(this.f5040b, this.f5041c);
                this.f5040b.setChildListView(this.f5041c.getWrappedList());
                this.f5048j = new C2109b(getActivity(), this.f5051m, this.f5056r, this.f5039a);
                this.f5041c.setOnStickyHeaderChangedListener(this);
                this.f5041c.setOnStickyHeaderOffsetChangedListener(this);
                this.f5041c.m8047a(this.f5050l);
                this.f5041c.setDrawingListUnderStickyHeader(true);
                this.f5041c.setAreHeadersSticky(true);
                this.f5041c.setAdapter(this.f5048j);
                this.f5041c.setStickyHeaderTopOffset(-20);
                this.f5041c.setOnTouchListener(this);
                this.f5040b.setOnLoadListener(this);
                this.f5040b.setOnRefreshListener(this);
                this.f5043e.setOnClickListener(this);
                if (this.f5048j != null) {
                    List arrayList = new ArrayList();
                    arrayList.add(new ClubFeed(-1));
                    this.f5048j.b(arrayList, false);
                }
                C2057d.a().a(getActivity());
                m6383e();
                m6374b(this.f5055q);
                if (AVUser.getCurrentUser() != null) {
                    this.f5062x = getActivity().getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
                    this.f5062x.registerOnSharedPreferenceChangeListener(this);
                }
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onResume() {
        super.onResume();
        C2057d.a().a(this);
        if (this.f5050l != null && this.f5051m != null) {
            this.f5050l.a(this.f5051m);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 2:
                if (i2 == -1 && getActivity() != null) {
                    getActivity().finish();
                    return;
                }
                return;
            case 4:
                if (i2 == -1) {
                    m6371a(this.f5055q, false, (C2050a) this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDetach() {
        super.onDetach();
        C2057d.a().a(null);
    }

    /* renamed from: a */
    public void m6394a(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j) {
        view.setAlpha(1.0f);
    }

    /* renamed from: a */
    public void m6393a(StickyListHeadersListView stickyListHeadersListView, View view, int i) {
        if (this.f5049k && VERSION.SDK_INT >= 11) {
            view.setAlpha(1.0f - (((float) i) / ((float) view.getMeasuredHeight())));
        }
    }

    /* renamed from: c */
    private ClubInfoCompact m6376c() {
        try {
            return this.f5053o.a(b());
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: b */
    private void m6374b(String str) {
        if (!TextUtils.isEmpty(str)) {
            m6371a(str, false, null);
            getAsyncTaskQueue().a(new ClubFeedInfoFrag$1(this), new String[]{str});
        }
    }

    /* renamed from: a */
    private void m6371a(String str, boolean z, C2050a c2050a) {
        Object obj = "";
        if (this.f5052n != null) {
            obj = this.f5052n.getObjectId();
        }
        if ((TextUtils.isEmpty(str) || (this.f5059u && !str.equals(r0))) && this.f5040b != null) {
            this.f5040b.setRefreshing(false);
            return;
        }
        getAsyncTaskQueue().a(new ClubFeedInfoFrag$8(this, z, c2050a), new String[]{str});
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.addclubrl:
                if (this.f5051m != null) {
                    C2621c c2621c;
                    switch (this.f5051m.getStatus()) {
                        case 0:
                            if (this.f5052n != null && this.f5052n.getStatus() == 2 && this.f5052n.getObjectId() != this.f5051m.getObjectId()) {
                                c2621c = new C2621c(getActivity());
                                c2621c.b(C1373R.string.club_dialog_apply_again_msg).a(C1373R.string.str_ok, new ClubFeedInfoFrag$10(this, c2621c)).b(C1373R.string.cancel, new ClubFeedInfoFrag$9(this, c2621c)).a();
                                return;
                            } else if (this.f5051m.getMembers() == this.f5051m.getMaxMembers()) {
                                c2621c = new C2621c(getActivity());
                                c2621c.b(C1373R.string.club_full).a(C1373R.string.clubapplyanyway, new ClubFeedInfoFrag$12(this, c2621c)).b(C1373R.string.cancel, new ClubFeedInfoFrag$11(this, c2621c)).a();
                                return;
                            } else {
                                this.f5058t = new C1800h(getActivity(), getString(C1373R.string.club_dialog_hint), null, new ClubFeedInfoFrag$13(this));
                                this.f5058t.show();
                                return;
                            }
                        case 2:
                            c2621c = new C2621c(getActivity());
                            c2621c.b(C1373R.string.club_dialog_quit_warning).a(C1373R.string.str_ok, new ClubFeedInfoFrag$15(this, c2621c)).b(C1373R.string.cancel, new ClubFeedInfoFrag$14(this, c2621c)).a();
                            return;
                        default:
                            return;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6396a(List<ClubFeed> list) {
        if (getActivity() != null && list != null) {
            getActivity().runOnUiThread(new ClubFeedInfoFrag$2(this, list));
        }
    }

    public void onRefresh() {
        m6371a(this.f5055q, true, null);
    }

    /* renamed from: a */
    public void m6392a() {
        if (this.f5051m == null || this.f5051m.getStatus() == 2 || this.f5051m.getStatus() == 0) {
            this.f5040b.setCanLoad(false);
            Toasts.show(getActivity(), getString(C1373R.string.clubfeed_loadmore_tip));
            return;
        }
        m6381d(this.f5055q);
    }

    /* renamed from: a */
    public void m6395a(String str) {
        if (str.equals(this.f5055q)) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.runOnUiThread(new ClubFeedInfoFrag$3(this, str));
            }
        }
    }

    /* renamed from: c */
    private void m6378c(String str) {
        getAsyncTaskQueue().a(new ClubFeedInfoFrag$4(this, str), new Void[0]);
    }

    /* renamed from: d */
    private void m6379d() {
        getAsyncTaskQueue().a(new ClubFeedInfoFrag$5(this), new Void[0]);
    }

    /* renamed from: e */
    private void m6383e() {
        if (this.f5051m != null) {
            switch (this.f5051m.getStatus()) {
                case 0:
                    if (isAdded()) {
                        this.f5044f.setText(getResources().getString(C1373R.string.club_info_item_club_apply));
                    }
                    if (!this.f5051m.getIsPrivate()) {
                        this.f5059u = false;
                        this.f5043e.setVisibility(0);
                        if (this.f5045g.getVisibility() == 0) {
                            this.f5045g.setVisibility(8);
                            this.f5046h.setVisibility(8);
                            break;
                        }
                    }
                    this.f5059u = true;
                    this.f5045g.setVisibility(0);
                    this.f5046h.setVisibility(0);
                    m6384e(this.f5051m.getObjectId());
                    this.f5043e.setVisibility(0);
                    break;
                    break;
                case 1:
                case 4:
                    this.f5043e.setVisibility(4);
                    this.f5045g.setVisibility(4);
                    this.f5046h.setVisibility(4);
                    this.f5041c.setPadding(0, 0, 0, 0);
                    break;
                case 2:
                    if (this.f5051m.getIsPrivate()) {
                        this.f5045g.setVisibility(0);
                        this.f5046h.setVisibility(0);
                        m6384e(this.f5051m.getObjectId());
                    }
                    this.f5043e.setVisibility(0);
                    if (isAdded()) {
                        this.f5044f.setText(getResources().getString(C1373R.string.club_info_item_club_undo_apply));
                        break;
                    }
                    break;
            }
            if (this.f5050l != null) {
                this.f5050l.a(this.f5051m);
            }
            if (this.f5048j != null) {
                this.f5048j.a(this.f5051m);
            }
            if (this.f5061w && HomeActivity.f4419b == 1 && getActivity() != null) {
                getActivity().setTitle(this.f5051m.getName());
            }
        }
    }

    /* renamed from: d */
    private void m6381d(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ClubFeedInfoFrag$6(this), new String[]{str});
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return false;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("beast.club.status".equals(str)) {
            if (sharedPreferences.getInt("beast.club.status", 0) == 1) {
                m6374b(this.f5055q);
            }
        } else if ("beast.club.notify.notice".equals(str) || "beast.club.notify.feed".equals(str) || "beast.club.refresh".equals(str)) {
            onRefresh();
        } else if ("beast.club.notify.member.quit".equals(str)) {
            this.f5051m.setMembers(this.f5051m.getMembers() - 1);
            this.f5050l.a(this.f5051m);
        } else if ("beast.club.name".equals(str)) {
            Object string = this.f5062x.getString("beast.club.name", "");
            if (!TextUtils.isEmpty(string)) {
                this.f5051m.setName(string);
            }
        }
    }

    /* renamed from: e */
    private void m6384e(String str) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getAsyncTaskQueue().a(new ClubFeedInfoFrag$7(this, new C2389c(getActivity()), str), new String[0]);
        }
    }
}
