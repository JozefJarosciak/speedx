package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager.CLUB_ORDERBY;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.NearbyFrag.C2085a;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.lib.view.search.MySearchBar;
import com.beastbikes.framework.ui.android.lib.view.search.MySearchListener;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import java.io.Serializable;
import java.util.List;

public class ClubDiscoverFrag extends SessionFragment implements OnSharedPreferenceChangeListener, OnClickListener, C2085a, MySearchListener {
    /* renamed from: a */
    public static boolean f9565a = true;
    /* renamed from: d */
    private static final Logger f9566d = LoggerFactory.getLogger("ClubDiscoverFrag");
    /* renamed from: b */
    public int f9567b;
    /* renamed from: c */
    public String f9568c;
    /* renamed from: e */
    private View f9569e;
    /* renamed from: f */
    private LinearLayout f9570f;
    /* renamed from: g */
    private TextView f9571g;
    /* renamed from: h */
    private TextView f9572h;
    /* renamed from: i */
    private LinearLayout f9573i;
    /* renamed from: j */
    private ClubInfoCompact f9574j;
    /* renamed from: k */
    private MySearchBar f9575k;
    /* renamed from: l */
    private ClubManager f9576l;
    /* renamed from: m */
    private FragmentManager f9577m;
    /* renamed from: n */
    private RecommendFrag f9578n;
    /* renamed from: o */
    private NearbyFrag f9579o;
    /* renamed from: p */
    private final int f9580p = 20;
    /* renamed from: q */
    private View f9581q;
    /* renamed from: r */
    private SharedPreferences f9582r;
    /* renamed from: s */
    private String f9583s = "";

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubDiscoverFrag$1 */
    class C20831 extends AsyncTask<String, Void, ClubInfoCompact> {
        /* renamed from: a */
        final /* synthetic */ ClubDiscoverFrag f9562a;

        C20831(ClubDiscoverFrag clubDiscoverFrag) {
            this.f9562a = clubDiscoverFrag;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10734a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10735a((ClubInfoCompact) obj);
        }

        /* renamed from: a */
        protected ClubInfoCompact m10734a(String... strArr) {
            ClubInfoCompact clubInfoCompact = null;
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                try {
                    clubInfoCompact = this.f9562a.f9576l.m10533a(currentUser.getObjectId());
                } catch (BusinessException e) {
                }
            }
            return clubInfoCompact;
        }

        /* renamed from: a */
        protected void m10735a(ClubInfoCompact clubInfoCompact) {
            if (this.f9562a.f9575k != null) {
                this.f9562a.f9575k.requestFocus();
            }
            if (clubInfoCompact == null) {
                this.f9562a.m10745a();
                return;
            }
            this.f9562a.f9568c = clubInfoCompact.getObjectId();
            this.f9562a.f9567b = clubInfoCompact.getStatus();
            this.f9562a.m10747a(clubInfoCompact);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f9581q != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.f9581q.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f9581q);
            }
            return this.f9581q;
        }
        this.f9581q = layoutInflater.inflate(C1373R.layout.activity_club_discovery, viewGroup, false);
        this.f9569e = this.f9581q.findViewById(C1373R.id.club_discover_apply_tip);
        this.f9570f = (LinearLayout) this.f9581q.findViewById(C1373R.id.searchbarLL);
        this.f9571g = (TextView) this.f9581q.findViewById(C1373R.id.recommend_tab);
        this.f9572h = (TextView) this.f9581q.findViewById(C1373R.id.nearby_tab);
        this.f9573i = (LinearLayout) this.f9581q.findViewById(C1373R.id.frag_container);
        return this.f9581q;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9576l = new ClubManager(getActivity());
        this.f9575k = new MySearchBar(getActivity(), getString(C1373R.string.club_search_search_btn), getString(C1373R.string.club_search_hint));
        this.f9570f.addView(this.f9575k, new LayoutParams(-1, -2));
        this.f9575k.setSearchBarListener(this);
        this.f9577m = getChildFragmentManager();
        m10746a(1);
        this.f9571g.setOnClickListener(this);
        this.f9572h.setOnClickListener(this);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f9582r = getContext().getSharedPreferences(currentUser.getObjectId(), 0);
            this.f9582r.registerOnSharedPreferenceChangeListener(this);
        }
        this.f9583s = getString(C1373R.string.club_search_fail_msg);
        m10744d();
    }

    public void onResume() {
        super.onResume();
    }

    /* renamed from: b */
    private void m10742b(ClubInfoCompact clubInfoCompact) {
        switch (clubInfoCompact.getStatus()) {
            case 2:
                this.f9569e.setVisibility(0);
                this.f9569e.setOnClickListener(this);
                return;
            default:
                this.f9569e.setVisibility(8);
                return;
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.contains("beast.club.status")) {
            int i = sharedPreferences.getInt(str, 0);
            if (i == 6) {
                m10745a();
                this.f9582r.edit().putInt("beast.club.status", 0).apply();
            } else if (i == 2) {
                m10744d();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f9582r != null) {
            this.f9582r.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.club_discover_apply_tip:
                switch (this.f9574j.getStatus()) {
                    case 2:
                        C2562j.m12866a(getContext(), this.f9574j);
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), ClubCreateActivity.class));
                        break;
                }
                break;
            case C1373R.id.recommend_tab:
                break;
            case C1373R.id.nearby_tab:
                m10746a(2);
                return;
            default:
                return;
        }
        m10746a(1);
    }

    /* renamed from: d */
    private void m10744d() {
        getAsyncTaskQueue().m13740a(new C20831(this), new String[0]);
    }

    public List<?> loadHistoryData(String str) {
        return null;
    }

    public List<?> loadIntelligenceData(String str, String str2) {
        return null;
    }

    public CharSequence getHistoryCharSequence(Object obj) {
        return null;
    }

    public CharSequence getIntelligenceCharSequence(Object obj) {
        return null;
    }

    public void recordHistory(String str, String str2) {
    }

    public void clearHistory(String str) {
    }

    public String getSearchKey() {
        return null;
    }

    public void goSearch(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            m10740a(str2);
            C2580w.m12905a(getActivity(), "搜索俱乐部", "search_club");
        }
    }

    public void onHistoryItemClicked(Object obj) {
    }

    public void onIntelligenceItemClicked(Object obj) {
    }

    /* renamed from: a */
    public void m10747a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            this.f9574j = clubInfoCompact;
            m10742b(clubInfoCompact);
        }
    }

    /* renamed from: a */
    public void m10745a() {
        this.f9569e.setVisibility(8);
    }

    /* renamed from: a */
    private void m10740a(final String str) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, List<ClubInfoCompact>>(this) {
            /* renamed from: b */
            final /* synthetic */ ClubDiscoverFrag f9564b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10736a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10737a((List) obj);
            }

            /* renamed from: a */
            protected List<ClubInfoCompact> m10736a(String... strArr) {
                try {
                    return this.f9564b.f9576l.m10538a(CLUB_ORDERBY.NONE, null, null, str, 1, 20);
                } catch (BusinessException e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10737a(List<ClubInfoCompact> list) {
                if (!TextUtils.isEmpty(str)) {
                    if (list == null || list.isEmpty()) {
                        Toasts.show(this.f9564b.getActivity(), this.f9564b.f9583s);
                        return;
                    }
                    Intent intent = new Intent(this.f9564b.getActivity(), ClubSearchActivity.class);
                    intent.putExtra("search_result", (Serializable) list);
                    intent.putExtra("search_key", str);
                    this.f9564b.startActivity(intent);
                }
            }
        }, new String[0]);
    }

    /* renamed from: a */
    public void m10746a(int i) {
        if (getActivity() != null) {
            FragmentTransaction beginTransaction = this.f9577m.beginTransaction();
            switch (i) {
                case 1:
                    C2580w.m12905a(getContext(), "推荐俱乐部", "comment_club");
                    this.f9571g.setSelected(true);
                    this.f9572h.setSelected(false);
                    if (this.f9579o != null) {
                        beginTransaction.hide(this.f9579o);
                    }
                    if (this.f9578n == null) {
                        this.f9578n = new RecommendFrag();
                        beginTransaction.add(C1373R.id.frag_container, this.f9578n);
                        break;
                    }
                    beginTransaction.show(this.f9578n);
                    break;
                case 2:
                    C2580w.m12905a(getContext(), "同城俱乐部", "same_city_club");
                    this.f9572h.setSelected(true);
                    this.f9571g.setSelected(false);
                    if (this.f9578n != null) {
                        beginTransaction.hide(this.f9578n);
                    }
                    if (this.f9579o == null) {
                        String str = "";
                        String str2 = "";
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            str = currentUser.getCity();
                            str2 = currentUser.getGeoCode();
                        }
                        f9566d.info("geoCode: " + str2);
                        this.f9579o = new NearbyFrag();
                        this.f9579o.m10902a(this, str, str2);
                        beginTransaction.add(C1373R.id.frag_container, this.f9579o);
                        break;
                    }
                    beginTransaction.show(this.f9579o);
                    break;
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    /* renamed from: c */
    public void mo3377c() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && getActivity() != null) {
            getActivity().finish();
        }
    }
}
