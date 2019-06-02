package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager.CLUB_ORDERBY;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.lib.frag.FragBaseList;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import java.util.List;

public class ClubSearchFrag extends FragBaseList<String, ClubInfoCompact, ListView> {
    /* renamed from: a */
    private ClubManager f9799a;
    /* renamed from: b */
    private int f9800b = 1;
    /* renamed from: c */
    private final int f9801c = 20;
    /* renamed from: d */
    private List<ClubInfoCompact> f9802d = null;
    /* renamed from: e */
    private String f9803e;

    public /* synthetic */ void loadMore(Object obj) {
        m10877a((String) obj);
    }

    protected /* synthetic */ void onItemClick(Object obj) {
        m10876a((ClubInfoCompact) obj);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9799a = new ClubManager(getActivity());
        this.f9803e = getActivity().getIntent().getStringExtra("search_key");
        this.f9802d = (List) getActivity().getIntent().getSerializableExtra("search_result");
        if (this.f9802d != null && !this.f9802d.isEmpty()) {
            this.pullView.disablePullDown();
            onLoadSucessAddfully(this.f9802d);
            if (this.f9802d.size() < 20) {
                this.pullView.disablePullUp();
            } else {
                this.pullView.enablePullUp();
            }
        } else if (getActivity() != null) {
            getActivity().finish();
        }
    }

    /* renamed from: a */
    protected void m10876a(ClubInfoCompact clubInfoCompact) {
        super.onItemClick(clubInfoCompact);
        C2562j.m12866a(getContext(), clubInfoCompact);
    }

    public void loadNormal() {
    }

    /* renamed from: a */
    public void m10877a(String str) {
        super.loadMore(str);
        this.f9800b++;
        m10874b(this.f9803e);
    }

    /* renamed from: b */
    private void m10874b(final String str) {
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, List<ClubInfoCompact>>(this) {
            /* renamed from: b */
            final /* synthetic */ ClubSearchFrag f9798b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10868a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10869a((List) obj);
            }

            /* renamed from: a */
            protected List<ClubInfoCompact> m10868a(String... strArr) {
                try {
                    return this.f9798b.f9799a.m10538a(CLUB_ORDERBY.NONE, null, null, str, this.f9798b.f9800b, 20);
                } catch (BusinessException e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m10869a(List<ClubInfoCompact> list) {
                if (list == null || list.isEmpty()) {
                    this.f9798b.onLoadFailed(this.f9798b.getString(C1373R.string.club_search_fail_msg));
                    this.f9798b.pullView.disablePull();
                    return;
                }
                this.f9798b.onLoadSucessAddfully(list);
            }
        }, new String[0]);
    }

    protected BaseListAdapter<ClubInfoCompact> adapterToDisplay(AbsListView absListView) {
        return new C2110a(null, absListView);
    }
}
