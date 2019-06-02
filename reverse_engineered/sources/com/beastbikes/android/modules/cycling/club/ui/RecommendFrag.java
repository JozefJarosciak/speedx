package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager.CLUB_ORDERBY;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.lib.frag.FragBaseList;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import java.util.ArrayList;
import java.util.List;

public class RecommendFrag extends FragBaseList<String, ClubInfoCompact, ListView> {
    /* renamed from: a */
    private ClubManager f9830a;
    /* renamed from: b */
    private int f9831b = 1;
    /* renamed from: c */
    private final int f9832c = 20;
    /* renamed from: d */
    private List<ClubInfoCompact> f9833d = null;
    /* renamed from: e */
    private C1802i f9834e;

    public /* synthetic */ void loadMore(Object obj) {
        m10915a((String) obj);
    }

    protected /* synthetic */ void onItemClick(Object obj) {
        m10914a((ClubInfoCompact) obj);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9830a = new ClubManager(getActivity());
        this.pullView.disablePullDown();
        this.pullView.enablePullUp();
        this.f9834e = new C1802i(getActivity(), getString(C1373R.string.str_loading), true);
        if (HomeActivity.f4419b == 1) {
            this.f9834e.show();
        }
        this.f9833d = new ArrayList();
        m10907a(20);
    }

    /* renamed from: a */
    protected void m10914a(ClubInfoCompact clubInfoCompact) {
        super.onItemClick(clubInfoCompact);
        C2562j.m12866a(getContext(), clubInfoCompact);
    }

    public void loadNormal() {
    }

    /* renamed from: a */
    public void m10915a(String str) {
        super.loadMore(str);
        this.f9831b++;
        m10907a(20);
    }

    /* renamed from: a */
    private void m10907a(final int i) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, List<ClubInfoCompact>>(this) {
            /* renamed from: b */
            final /* synthetic */ RecommendFrag f9829b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10904a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10905a((List) obj);
            }

            /* renamed from: a */
            protected List<ClubInfoCompact> m10904a(String... strArr) {
                try {
                    return this.f9829b.f9830a.m10538a(CLUB_ORDERBY.RECOMMEND, "", "", "", this.f9829b.f9831b, i);
                } catch (BusinessException e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10905a(List<ClubInfoCompact> list) {
                if (!(this.f9829b.f9834e == null || this.f9829b.getActivity() == null || this.f9829b.getActivity().isFinishing())) {
                    this.f9829b.f9834e.dismiss();
                }
                if (list == null || list.isEmpty()) {
                    Activity activity = this.f9829b.getActivity();
                    if (activity != null) {
                        if (this.f9829b.f9831b == 1) {
                            this.f9829b.onLoadFailed(activity.getString(C1373R.string.str_loading_failed));
                            return;
                        } else {
                            this.f9829b.onLoadFailed(activity.getString(C1373R.string.club_discover_load_end));
                            return;
                        }
                    }
                    return;
                }
                this.f9829b.f9833d.addAll(list);
                this.f9829b.onLoadSucessfully(this.f9829b.f9833d);
            }
        }, new String[0]);
    }

    protected BaseListAdapter<ClubInfoCompact> adapterToDisplay(AbsListView absListView) {
        return new C2110a(null, absListView);
    }
}
